package org.osate.ge.internal.graphiti.diagram;

import org.osate.ge.internal.diagram.runtime.DiagramElement;

public interface ColoringProvider {
	java.awt.Color getForegroundColor(final DiagramElement de);
}
