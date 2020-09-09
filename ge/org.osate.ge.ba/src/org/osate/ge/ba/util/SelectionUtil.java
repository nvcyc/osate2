/**
 * Copyright (c) 2004-2020 Carnegie Mellon University and others. (see Contributors file).
 * All Rights Reserved.
 *
 * NO WARRANTY. ALL MATERIAL IS FURNISHED ON AN "AS-IS" BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO WARRANTIES OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER INCLUDING, BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE
 * OR MERCHANTABILITY, EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL. CARNEGIE MELLON UNIVERSITY DOES NOT
 * MAKE ANY WARRANTY OF ANY KIND WITH RESPECT TO FREEDOM FROM PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * SPDX-License-Identifier: EPL-2.0
 *
 * Created, in part, with funding and support from the United States Government. (see Acknowledgments file).
 *
 * This program includes and/or can make use of certain third party source code, object code, documentation and other
 * files ("Third Party Software"). The Third Party Software that is used by this program is dependent upon your system
 * configuration. By using this program, You agree to comply with any and all relevant Third Party Software terms and
 * conditions contained in any such Third Party Software or separate license file distributed with such Third Party
 * Software. The parties who own the Third Party Software ("Third Party Licensors") are intended third party benefici-
 * aries to this license with respect to the terms applicable to their Third Party Software. Third Party Software li-
 * censes only apply to the Third Party Software and not any other portion of this program or this program as a whole.
 */
package org.osate.ge.ba.util;

import java.util.List;
import java.util.Optional;

import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.osate.aadl2.DefaultAnnexSubclause;
import org.osate.aadl2.Element;
import org.osate.aadl2.NamedElement;
import org.osate.ba.aadlba.BehaviorAnnex;
import org.osate.ba.aadlba.BehaviorState;
import org.osate.ba.aadlba.BehaviorTransition;
import org.osate.ba.aadlba.BehaviorVariable;
import org.osate.ge.BusinessObjectContext;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public class SelectionUtil {
	private static EObjectAtOffsetHelper eObjectAtOffsetHelper = new EObjectAtOffsetHelper();

	public static ISelection getCurrentSelection() {
		final IWorkbenchWindow win = getActiveWorkbenchWindow();
		if (win == null) {
			return StructuredSelection.EMPTY;
		}

		return win.getSelectionService().getSelection();
	}

	public static IWorkbenchWindow getActiveWorkbenchWindow() {
		final IWorkbench wb = PlatformUI.getWorkbench();
		if (wb == null) {
			return null;
		}

		return wb.getActiveWorkbenchWindow();
	}

	public static IWorkbenchPage getActivePage() {
		final IWorkbenchWindow window = getActiveWorkbenchWindow();
		if (window == null) {
			return null;
		}

		return window.getActivePage();
	}

	public static Optional<IEditorPart> getActiveEditor() {
		final IWorkbenchPage page = getActivePage();
		if (page == null) {
			return Optional.empty();
		}

		return Optional.ofNullable(page.getActiveEditor());
	}

	public static IEditorPart getActiveEditorFromContext(final Object evaluationContext) {
		if (!(evaluationContext instanceof IEvaluationContext)) {
			return null;
		}

		final IEvaluationContext context = (IEvaluationContext) evaluationContext;
		final Object editorObj = context.getVariable(ISources.ACTIVE_EDITOR_NAME);
		return editorObj instanceof IEditorPart ? (IEditorPart) editorObj : null;
	}

	public static ImmutableList<BusinessObjectContext> getSelectedBusinessObjectContexts() {
		return getSelectedBusinessObjectContexts(getCurrentSelection(), false);
	}

	public static ImmutableList<BusinessObjectContext> getSelectedBusinessObjectContexts(final ISelection selection,
			final boolean ignoreInvalidType) {
		return getAdaptedSelection(selection, BusinessObjectContext.class, ignoreInvalidType);
	}

	/**
	 *
	 * @param ignoreInvalidType if true then the selection will skip over instances that cannot be adapted. If false, an empty list will be returned in such cases.
	 */
	private static <T> ImmutableList<T> getAdaptedSelection(final ISelection selection, final Class<T> adapter,
			final boolean ignoreInvalidType) {
		if (!(selection instanceof IStructuredSelection)) {
			return ImmutableList.of();
		}

		final IStructuredSelection ss = (IStructuredSelection) selection;
		final Builder<T> results = ImmutableList.builder();
		for (final Object sel : ss.toList()) {
			final T o = Adapters.adapt(sel, adapter);
			if (o == null) {
				if (!ignoreInvalidType) {
					return ImmutableList.of();
				}
			} else {
				results.add(o);
			}
		}

		return results.build();
	}

	public static ImmutableList<BusinessObjectContext> getSelectedBusinessObjectContexts(final ISelection selection) {
		if (!(selection instanceof IStructuredSelection)) {
			return ImmutableList.of();
		}

		final IStructuredSelection ss = (IStructuredSelection) selection;
		final ImmutableList.Builder<BusinessObjectContext> bocs = ImmutableList.builderWithExpectedSize(ss.size());
		for (final Object sel : ss.toList()) {
			final BusinessObjectContext boc = Adapters.adapt(sel, BusinessObjectContext.class);
			if (boc == null) {
				return ImmutableList.of();
			}

			bocs.add(boc);
		}

		return bocs.build();
	}

	/**
	 * Returns a business object which is a valid diagram context based on the currnent selection. Returns null if such a business object could not be determined based on the current selection.
	 * @param selection
	 * @param activeEditor
	 * @return
	 */
	public static Optional<DefaultAnnexSubclause> getDiagramContext(final ISelection selection,
			final IEditorPart activeEditor) {
		DefaultAnnexSubclause contextBo = null;
		if (activeEditor instanceof XtextEditor) {
			final EObject selectedObject = getEObjectFromSelection((XtextEditor) activeEditor, selection);
			contextBo = findDiagramContextForSelectedObject(selectedObject);
		}

		if (selection instanceof IStructuredSelection) {
			final List<BusinessObjectContext> selectedBusinessObjectContexts = getSelectedBusinessObjectContexts();
			if (selectedBusinessObjectContexts.size() == 1) {
				contextBo = findDiagramContextForSelectedObject(
						selectedBusinessObjectContexts.get(0).getBusinessObject());
			}
		}

		return Optional.ofNullable(contextBo);
	}

	private static DefaultAnnexSubclause findDiagramContextForSelectedObject(final Object element) {
		if (element instanceof BehaviorState || element instanceof BehaviorTransition
				|| element instanceof BehaviorVariable) {
			return findDiagramContextForSelectedObject(((Element) element).getOwner());
		}

		// Get default annex subclause as context
		if (element instanceof BehaviorAnnex) {
			return ((BehaviorAnnex) element).getOwner() instanceof DefaultAnnexSubclause
					? (DefaultAnnexSubclause) ((BehaviorAnnex) element).getOwner()
					: null;
		}

		if (element instanceof DefaultAnnexSubclause
				&& BaUtil.ANNEX_NAME.equalsIgnoreCase(((NamedElement) element).getName())) {
			return (DefaultAnnexSubclause) element;
		}

		return null;
	}

	private static EObject getEObjectFromSelection(final XtextEditor editor, final ISelection selection) {
		// Check the selection before accessing the document
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) selection;
			if (ss.size() != 1) {
				return null;
			}

			if (!(ss.getFirstElement() instanceof EObjectNode)) {
				return null;
			}
		} else if (!(selection instanceof ITextSelection)) {
			return null;
		}

		return editor.getDocument().readOnly(
				resource -> {
					EObject targetElement = null;
					if (selection instanceof IStructuredSelection) {
						final IStructuredSelection ss = (IStructuredSelection) selection;
						targetElement = ((EObjectNode) ss.getFirstElement()).getEObject(resource);
					} else if (selection instanceof ITextSelection) {
						final int offset = ((ITextSelection) selection).getOffset();
						targetElement = eObjectAtOffsetHelper.resolveContainedElementAt(resource, offset);

						// Check if the node for the semantic element that was retrieved by the offset helper starts after the line number of the node
						// retrieved using the offset. If it is, return the container. This prevents returning a classifier when the select is actually in
						// whitespace before the classifier.
						final IParseResult parseResult = resource.getParseResult();
						if(targetElement != null && parseResult != null) {
							final ILeafNode leaf = NodeModelUtils.findLeafNodeAtOffset(parseResult.getRootNode(), offset);
							final INode targetElementNode = NodeModelUtils.getNode(targetElement);
							if(leaf.getStartLine() < targetElementNode.getStartLine()) {
								targetElement = targetElement.eContainer();
							}
						}

						// If there isn't a selected element, that usually means the selection is outside of the root package. Get the first EObject in the resource
						if(targetElement == null && resource.getContents().size() > 0) {
							targetElement = resource.getContents().get(0);
						}
					}

					return targetElement;
				});
	}
}