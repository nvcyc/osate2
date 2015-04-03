/*******************************************************************************
 * Copyright (C) 2013 University of Alabama in Huntsville (UAH)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * The US Government has unlimited rights in this work in accordance with W31P4Q-10-D-0092 DO 0073.
 *******************************************************************************/
package org.osate.ge.diagrams.common.styles;

import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.util.ColorConstant;
import org.osate.ge.styles.StyleFactory;

public class SuprogramCallSequenceStyleFactory implements StyleFactory {
	@Override
	public Style create(final String styleId, final Diagram diagram) {
		final IGaService gaService = Graphiti.getGaService();
       	final Style style = gaService.createPlainStyle(diagram, styleId);

        // Set the style
        style.setForeground(gaService.manageColor(diagram, ColorConstant.BLACK));
        style.setBackground(gaService.manageColor(diagram, ColorConstant.WHITE));
        style.setLineStyle(LineStyle.DOT);
        style.setLineVisible(true);
        style.setLineWidth(2);
        style.setTransparency(0.0);

        return style;
	}
}
