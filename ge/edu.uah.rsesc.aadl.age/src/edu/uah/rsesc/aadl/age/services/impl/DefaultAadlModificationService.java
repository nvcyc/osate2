package edu.uah.rsesc.aadl.age.services.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.swt.widgets.Display;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.osate.aadl2.Element;
import org.osate.aadl2.NamedElement;

import edu.uah.rsesc.aadl.age.services.AadlModificationService;
import edu.uah.rsesc.aadl.age.ui.xtext.AgeXtextUtil;
import edu.uah.rsesc.aadl.age.util.Log;

public class DefaultAadlModificationService implements AadlModificationService {
	private final IFeatureProvider fp;
	
	public DefaultAadlModificationService(final IFeatureProvider fp) {
		this.fp = fp;
	}
	
	@Override
	public <E extends Element, R> R modify(final E element, final Modifier<E, R> modifier) {
		if(element == null) {
			return null;
		}
		
		class ModifyRunnable implements Runnable {
			public R result;
			
			@Override
			public void run() {
				final R modifierResult;
				if(!(element.eResource() instanceof XtextResource)) {
					throw new RuntimeException("Unexpected case. Resource is not an XtextResource");
				}

				// Try to get the Xtext document	
				final NamedElement root = element.getElementRoot();
				final IXtextDocument doc = AgeXtextUtil.getDocumentByQualifiedName(root.getQualifiedName());
				if(doc == null) {
					final XtextResource res = (XtextResource)element.eResource();
					final ModifySafelyResults<R> modifySafelyResult = modifySafely(res, element, modifier);
					modifierResult = modifySafelyResult.modifierResult;
					
					if(modifySafelyResult.modificationSuccessful) {
						// Save the model
						try {
							res.save(SaveOptions.defaultOptions().toOptionsMap());	
						} catch (IOException e) {
							throw new RuntimeException(e);
						};
						
						// Update the diagram
						Display.getDefault().syncExec(new Runnable() {
							public void run() {			
								// Update the entire diagram
								fp.getDiagramTypeProvider().getNotificationService().updatePictogramElements(new PictogramElement[] { fp.getDiagramTypeProvider().getDiagram() });					
							}
						});

						// Call the after modification callback
						modifier.afterCommit(res, element, modifierResult);
					}
				} else {
					final URI elementUri = EcoreUtil.getURI(element);
					final ModifySafelyResults<R> modifySafelyResult = doc.modify(new IUnitOfWork<ModifySafelyResults<R>, XtextResource>() {
						@Override
						public ModifySafelyResults<R> exec(final XtextResource res) throws Exception {
							@SuppressWarnings("unchecked")
							final E element = (E)res.getResourceSet().getEObject(elementUri, true);
							if(element == null) {
								return null;
							}
							
							return modifySafely(res, element, modifier);
						}
					});
					modifierResult = modifySafelyResult.modifierResult;
					
					// Call the after modification callback
					if(modifySafelyResult.modificationSuccessful) {
						// Call readonly on the document. This will should cause Xtext's reconciler to be called to ensure the document matches the model.
						// If modify() has been called in the display thread, then the diagram should be updated by the diagram editor as well
						doc.readOnly(new IUnitOfWork<Object, XtextResource>() {
							@Override
							public Object exec(final XtextResource res) throws Exception {
								modifier.afterCommit(res, element, modifierResult);
								return null;
							}
						});	
					}
				}

				result = modifierResult;
			}
			
		};
		
		// We want to run the modification in the display thread. Executing in the display thread will allow the diagram editor updates to be ran synchronously
		final ModifyRunnable modifyRunnable = new ModifyRunnable();
		if(Display.getDefault().getThread() == Thread.currentThread()) {
			Log.info("Executing modification without a thread switch");
			modifyRunnable.run();
		} else {
			Log.info("Executing modification after switching to display thread");
			Display.getDefault().syncExec(modifyRunnable);	
		}
		
		return modifyRunnable.result;
	}
	
	/**
	 * Class used to return the results of the modifySafely method
	 * @author philip.alldredge
	 *
	 * @param <R>
	 */
	private static class ModifySafelyResults<R> {
		public ModifySafelyResults(final boolean modificationSuccessful, final R modifierResult) {
			this.modificationSuccessful = modificationSuccessful;
			this.modifierResult = modifierResult;
		}
		
		public final boolean modificationSuccessful;
		public final R modifierResult;
	}
	/**
	 * Modifies the resource. If changes causes a validation error, the changes are reverted.
	 * @param resource
	 * @param modifier
	 * @param saveOnSuccess is whether the resource should be saved if the modification is successful. If true, the save will take place after the modification but
	 * before the afterModification callback. This ensures that any diagram operations that occur in afterModification will use the up to date model.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <E extends Element, R> ModifySafelyResults<R> modifySafely(final XtextResource resource, final E element, final Modifier<E, R> modifier) {
		if(resource.getContents().size() < 1) {
			return null;
		}

		// Ensure that the resource does not have an editing domain
		if(TransactionUtil.getEditingDomain(resource) != null) {
			throw new RuntimeException("Unexpected case: resource already has an editing domain");
		}

		// Create a new editing domain
		final TransactionalEditingDomain domain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(resource.getResourceSet());
		
		R result = null;
		boolean modificationSuccessful = false;
		try {
			// Execute a new recording command
			final RecordingCommand cmd = new RecordingCommand(domain) {
				private R modifierResult;
				
				@Override
				protected void doExecute() {
					modifierResult = modifier.modify(resource, element);
				}
				
				@Override
				public Collection<?> getResult() {
					return Collections.singletonList(modifierResult);
				}
				
			};
			domain.getCommandStack().execute(cmd);
			final Object[] cmdResult = cmd.getResult().toArray();
			if(cmdResult.length > 0) {
				result = (R)cmdResult[0];
			}
			
			// Mark the modification as successful
			modificationSuccessful = true;
		} finally {
			// Undo the changes if any of the validators fail
			final List<Diagnostic> concreteValidationErrors = resource.validateConcreteSyntax();
			// Disabled checking for resource errors because that includes errors that will occur in normal operations(unless refactoring/chain deleting/modification is implemented)
			if(!modificationSuccessful || (/*resource.getErrors().size() > 0 || */concreteValidationErrors.size() > 0) && domain.getCommandStack().canUndo()) {
				Log.error("Error. Undoing modification");
				/*if(resource.getErrors().size() > 0) {
					Log.error("Errors:");
					for(final org.eclipse.emf.ecore.resource.Resource.Diagnostic diag : resource.getErrors()) {
						Log.error(diag.toString());
					}
				}
				*/
				if(concreteValidationErrors.size() > 0) {
					Log.error("Concrete Syntax Validation Errors:");
					for(final Diagnostic diag : concreteValidationErrors) {
						Log.error(diag.toString());
					}
				}
				
				if(domain.getCommandStack().canUndo()) {
					domain.getCommandStack().undo();
				}
				modificationSuccessful = false;
				result = null;
			}

			// Flush and dispose of the editing domain
			domain.getCommandStack().flush();			
			domain.dispose();
			
			if(modificationSuccessful) {
				// Call the after modification callback
				modifier.beforeCommit(resource, element, result);
			}
		}
		
		return new ModifySafelyResults<R>(modificationSuccessful, result);
	}
}
