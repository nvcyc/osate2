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
package org.osate.ge.gef.graphics;

import org.eclipse.gef.fx.utils.NodeUtils;
import org.eclipse.gef.geometry.planar.IGeometry;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

// TODO: Rename
public class RectangleNode extends Region implements GraphicNode {
	private final javafx.scene.shape.Rectangle rect = new Rectangle();

	public RectangleNode() {
		this(false);
	}

	public RectangleNode(final boolean rounded) {
		this.getChildren().addAll(rect);

		setLineWidth(2);
		setBackgroundColor(Color.WHITE);
		setOutlineColor(Color.BLACK);
		setRounded(rounded);
		rect.setStrokeType(StrokeType.INSIDE);
	}

	public void setRounded(final boolean value) {
		final double roundedArcSize = value ? 25.0 : 0;
		rect.setArcWidth(roundedArcSize);
		rect.setArcHeight(roundedArcSize);
	}

	@Override
	public void resize(double width, double height) {
		super.resize(width, height);
		rect.setWidth(width);
		rect.setHeight(height);
	}

	@Override
	public final void setBackgroundColor(final Color value) {
		rect.setFill(value);
	}

	@Override
	public final void setOutlineColor(final Color value) {
		rect.setStroke(value);
	}

	@Override
	public final void setLineWidth(final double value) {
		rect.setStrokeWidth(value);
	}

	@Override
	public IGeometry getOutline() {
		return NodeUtils.getGeometricOutline(rect);
	}
}
