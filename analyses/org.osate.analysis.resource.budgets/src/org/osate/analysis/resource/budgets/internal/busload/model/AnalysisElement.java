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
package org.osate.analysis.resource.budgets.internal.busload.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 3.0
 */
abstract class AnalysisElement extends ModelElement {
	private String label;
	private String somName;
	private String category;

	/** Actual data requirements in KB/s. */
	private double actual;

	/** Budgeted data requirements in KB/s. */
	private double budget;

	/** children **/
	private List<Component> components = new ArrayList();

	AnalysisElement(final String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public final double getActual() {
		return actual;
	}

	public final void setActual(final double actual) {
		this.actual = actual;
	}

	public final double getBudget() {
		return budget;
	}

	public final void setBudget(final double budget) {
		this.budget = budget;
	}

	public final List<Component> getComponents() {
		return components;
	}

	public final void addComponent(final Component comp) {
		this.components.add(comp);
	}

	public final void setCapacityUnit(final List<Component> components) {
		this.components = components;
	}

	public final String getSomName() {
		return somName;
	}

	public final void setSomName(final String somName) {
		this.somName = somName;
	}

	public final String getCategory() {
		return category;
	}

	public final void setCategory(final String category) {
		this.category = category;
	}
}
