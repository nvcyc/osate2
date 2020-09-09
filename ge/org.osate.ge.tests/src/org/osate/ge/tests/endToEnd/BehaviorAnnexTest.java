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

package org.osate.ge.tests.endToEnd;

import static org.junit.Assert.*;
import static org.osate.ge.aadl2.internal.AadlReferenceUtil.*;
import static org.osate.ge.tests.endToEnd.util.OsateGeTestCommands.*;
import static org.osate.ge.tests.endToEnd.util.OsateGeTestUtil.*;
import static org.osate.ge.tests.endToEnd.util.UiTestUtil.*;

import java.util.function.BiFunction;

import org.junit.Test;
import org.osate.ge.RelativeBusinessObjectReference;
import org.osate.ge.ba.BehaviorAnnexReferenceUtil;
import org.osate.ge.tests.endToEnd.util.DiagramElementReference;
import org.osate.ge.tests.endToEnd.util.DiagramReference;

public class BehaviorAnnexTest {
	private static final String BA_TEST = "ba_test";
	private static final String TYPE = " Type";
	private static final String NAME_SUFFIX = "_type";

	@Test
	public void testBehaviorAnnex() {
		prepareForTesting();
		createAadlProject(BA_TEST);

		// Create package
		createNewPackageWithPackageDiagram(BA_TEST, BA_TEST);

		final DiagramReference diagram = defaultDiagram(BA_TEST, BA_TEST);
		final RelativeBusinessObjectReference pkgRef = getRelativeReferenceForPackage(BA_TEST);
		final DiagramElementReference pkgElement = packageElement(BA_TEST);

		// Open text editor
		doubleClickInAadlNavigator(BA_TEST, BA_TEST + ".aadl");

		createAndTestBehaviorSpecificationForClassifier("Abstract", diagram, pkgElement, pkgRef, false, true);
		createAndTestBehaviorSpecificationForClassifier("Bus", diagram, pkgElement, pkgRef, false, false);
		createAndTestBehaviorSpecificationForClassifier("Data", diagram, pkgElement, pkgRef, false, false);
		createAndTestBehaviorSpecificationForClassifier("Device", diagram, pkgElement, pkgRef, true, true);
		createAndTestBehaviorSpecificationForClassifier("Memory", diagram, pkgElement, pkgRef, false, false);
		createAndTestBehaviorSpecificationForClassifier("Process", diagram, pkgElement, pkgRef, false, false);
		createAndTestBehaviorSpecificationForClassifier("Processor", diagram, pkgElement, pkgRef, false, false);
		createAndTestBehaviorSpecificationForClassifier("Subprogram", diagram, pkgElement, pkgRef, true, false);
		createAndTestBehaviorSpecificationForClassifier("Subprogram Group", diagram, pkgElement, pkgRef, false, false);
		createAndTestBehaviorSpecificationForClassifier("System", diagram, pkgElement, pkgRef, false, false);
		createAndTestBehaviorSpecificationForClassifier("Thread", diagram, pkgElement, pkgRef, true, true);
		createAndTestBehaviorSpecificationForClassifier("Thread Group", diagram, pkgElement, pkgRef, false, false);
		createAndTestBehaviorSpecificationForClassifier("Virtual Bus", diagram, pkgElement, pkgRef, false, false);
		createAndTestBehaviorSpecificationForClassifier("Virtual Processor", diagram, pkgElement, pkgRef, true, false);
	}

	private void createAndTestBehaviorSpecificationForClassifier(final String classifier,
			final DiagramReference diagram,
			final DiagramElementReference pkgElement, final RelativeBusinessObjectReference pkgRef, final boolean requiresInitialState,
			final boolean allowsOnDispatchCondition) {
		final String classifierName = getName(classifier);
		// Create type
		createElementAndLayout(diagram, pkgElement, getType(classifier),
				getClassifierRelativeReference("new_classifier"), classifierName);

		final String srcStateName = "src_state";
		// Use Open -> Behavior Annex Diagram command to create new behavior annex diagram
		final BiFunction<DiagramElementReference, String, DiagramReference> openBehaviorAnnexDiagramCommand = (ref,
				newStatePrefix) -> openDiagramFromReference(ref, newStatePrefix, 0);
		// Run tests for type
		createAndTestBehaviorSpecification(BehaviorAnnexReferenceUtil.getSpecificationRelativeReference(0),
				classifierName, diagram, pkgRef, srcStateName, openBehaviorAnnexDiagramCommand, requiresInitialState,
				allowsOnDispatchCondition);

		// Create impl
		createImplementationWithExistingType(diagram, pkgElement, classifier + " Implementation", "impl", BA_TEST,
				classifierName);

		// Use Open -> New Diagram... command to create new Behavior Annex diagram
		final BiFunction<DiagramElementReference, String, DiagramReference> openNewDiagramCommand = (ref,
				newStatePrefix) -> openNewDiagramFromReference(ref, newStatePrefix, 1);
		// Run tests for impl
		createAndTestBehaviorSpecification(BehaviorAnnexReferenceUtil.getSpecificationRelativeReference(1),
				classifierName + ".impl", diagram, pkgRef, srcStateName, openNewDiagramCommand, requiresInitialState,
				allowsOnDispatchCondition);
	}

	private void createAndTestBehaviorSpecification(final RelativeBusinessObjectReference behaviorSpecification,
			final String classifierName,
			final DiagramReference diagram, final RelativeBusinessObjectReference pkgRef, final String srcStateName,
			final BiFunction<DiagramElementReference, String, DiagramReference> openDiagram,
			final boolean requiresInitialState,
			final boolean allowsOnDispatchCondition) {
		createBehaviorAnnexWithStateRename(diagram, pkgRef, classifierName, behaviorSpecification, srcStateName);

		final RelativeBusinessObjectReference classifierRef = getClassifierRelativeReference(classifierName);
		final DiagramElementReference specificationDiagramRef = new DiagramElementReference(pkgRef, classifierRef,
				behaviorSpecification);
		final DiagramElementReference src = specificationDiagramRef
				.join(BehaviorAnnexReferenceUtil.getStateRelativeReference(srcStateName));
		final String newStatePrefix = classifierName.replace(".", "_");
		final DiagramReference baDiagram = openDiagram.apply(src, newStatePrefix);

		createBehaviorStateAndVariable(baDiagram, new DiagramElementReference(behaviorSpecification), newStatePrefix,
				"dest_state");

		final DiagramElementReference dest = new DiagramElementReference(behaviorSpecification)
				.join(BehaviorAnnexReferenceUtil.getStateRelativeReference("dest_state"));
		testBehaviorSpecification(
				new DiagramElementReference(behaviorSpecification,
						BehaviorAnnexReferenceUtil.getStateRelativeReference(srcStateName)),
				dest, baDiagram, behaviorSpecification, requiresInitialState, allowsOnDispatchCondition);
	}

	private void createBehaviorStateAndVariable(final DiagramReference diagram,
			final DiagramElementReference behaviorSpecification, final String prefix,
			final String newStateName) {
		// Create 2nd state
		createElementAndLayout(diagram, behaviorSpecification, "State",
				BehaviorAnnexReferenceUtil.getStateRelativeReference(prefix + "_new_state"), newStateName);

		// Create variable
		createBehaviorVariable(diagram, behaviorSpecification, "Base_Types::Character", prefix + "_new_variable");
	}

	// Open Behavior Annex diagram
	private DiagramReference openDiagramFromReference(final DiagramElementReference ref, final String newStatePrefix,
			final int index) {
		clickContextMenuOfOutlineViewItem(ref.toOutlineTreeItemPath(),
				new String[] { "Open", "Behavior Annex Diagram" });

		waitForWindowWithTitle("Create New Diagram?");
		clickButtonForShell("Create New Diagram?", "Yes");

		final DiagramReference baDiagram = defaultDiagram(BA_TEST,
				BA_TEST + "_" + newStatePrefix + "_behavior_" + index);
		waitForDiagramActive(baDiagram);

		return baDiagram;
	}

	// Create Behavior Annex diagram
	private DiagramReference openNewDiagramFromReference(final DiagramElementReference ref, final String newStatePrefix,
			final int index) {
		clickContextMenuOfOutlineViewItem(ref.toOutlineTreeItemPath(), new String[] { "Open", "New Diagram..." });

		waitForWindowWithTitle("Create Diagram");
		clickButtonForShell("Create Diagram", "OK");

		final DiagramReference baDiagram = defaultDiagram(BA_TEST,
				BA_TEST + "_" + newStatePrefix + "_behavior_" + index);
		waitForDiagramActive(baDiagram);

		return baDiagram;
	}

	private void testBehaviorSpecification(final DiagramElementReference src, final DiagramElementReference dest,
			final DiagramReference diagram,
			final RelativeBusinessObjectReference behaviorSpecification, final boolean requiresInitialState,
			final boolean allowsOnDispatch) {
		clickCheckboxInPropertiesView(diagram, "AADL", 1, dest);

		assertTrue(isCheckboxInPropertiesViewChecked(diagram, "AADL", 2, src) == requiresInitialState);
		final boolean isComplete = isCheckboxInPropertiesViewChecked(diagram, "AADL", 0, src);
		if (isComplete && !allowsOnDispatch) {
			// Swap complete states from src to destination for classifiers
			// that require complete states and do not allow dispatch conditions
			clickCheckboxInPropertiesView(diagram, "AADL", 0, dest);
			clickCheckboxInPropertiesView(diagram, "AADL", 0, src);
		}

		// Create a transition between the states
		createConnectionElement(diagram, src, dest, "Transition",
				element(behaviorSpecification,
				BehaviorAnnexReferenceUtil.getTransitionRelativeReference(0)));

		// Cannot set source to final
		assertTrue(!isCheckboxInPropertiesViewEnabled(diagram, "AADL", 1, src));

		// Assert if source states cannot be set to complete or already complete
		assertTrue(isComplete || isCheckboxInPropertiesViewEnabled(diagram, "AADL", 0, src) == allowsOnDispatch);

		if (!isComplete && allowsOnDispatch) {
			// Set completeness
			clickCheckboxInPropertiesView(diagram, "AADL", 0, src);
		}
	}

	private static String getName(final String name) {
		return new StringBuilder(name.replace(" ", "_")).append(NAME_SUFFIX).toString();
	}

	private static String getType(final String name) {
		return new StringBuilder(name).append(TYPE).toString();
	}
}