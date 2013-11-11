package edu.uah.rsesc.aadl.age.ui.navigator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import edu.uah.rsesc.aadl.age.services.DiagramService;
import edu.uah.rsesc.aadl.age.ui.editor.AgeDiagramEditor;

public class LabelProvider implements ILabelProvider {
	private final List<ILabelProviderListener> listeners = new ArrayList<ILabelProviderListener>();
	private final DiagramService diagramService;
	private final IResourceChangeListener resourceChangeListener = new IResourceChangeListener() {
		public void resourceChanged(final IResourceChangeEvent event) {
			if(event.getType() == IResourceChangeEvent.POST_CHANGE) {
				Display.getDefault().syncExec(new Runnable() {
					@Override
					public void run() {
						try {
							event.getDelta().accept(new IResourceDeltaVisitor() {
								@Override
								public boolean visit(IResourceDelta delta) throws CoreException {
									if(delta.getKind() == IResourceDelta.CHANGED && (delta.getFlags() & IResourceDelta.CONTENT) != 0) {
										if(isApplicable(delta.getResource())) {
											notifyListeners(delta.getResource());
										}
									}
									
									return true;
								}
								 
							 });
						} catch (CoreException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}					
				});				
	         }
	      }
	   };
	   
	public LabelProvider() {
		this.diagramService = (DiagramService)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getService(DiagramService.class);
		
		// Register to listen for workspace changes
		ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceChangeListener);
	}	
	
	@Override
	public void addListener(final ILabelProviderListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(final ILabelProviderListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void dispose() {
		listeners.clear();
		
		// Register to listen for workspace changes
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceChangeListener);
	}
	
	private void notifyListeners(final Object resource) {
		for(final ILabelProviderListener listener : listeners) {
			listener.labelProviderChanged(new LabelProviderChangedEvent(this, resource));
		}
	}
	
	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}
	
	@Override
	public Image getImage(Object element) {
		return null;
	}

	private boolean isApplicable(Object element) {
		if(element instanceof IFile) {
			final IFile file = (IFile)element;
			return AgeDiagramEditor.EXTENSION_NO_DOT.equalsIgnoreCase(file.getFileExtension());
		}
		
		return false;
	}
	
	@Override
	public String getText(Object element) {
		if(isApplicable(element)) {
			final IFile file = (IFile)element;
			final String name = diagramService.getName(file);
			return name == null ? file.getName() : name;
		}
		
		return null;
	}
}
