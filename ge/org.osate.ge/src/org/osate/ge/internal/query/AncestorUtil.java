package org.osate.ge.internal.query;

import org.osate.ge.internal.diagram.AgeDiagramElement;
import org.osate.ge.internal.diagram.DiagramElementContainer;

public class AncestorUtil {
	/**
	 * Returns the first ancestor in the diagram element tree.
	 * @return
	 */
	public static <A> DiagramElementContainer getContainer(DiagramElementContainer container) {
		if(container instanceof AgeDiagramElement) {
			return ((AgeDiagramElement) container).getContainer();
		}
		
		return null;
	}
}
