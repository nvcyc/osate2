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
package org.osate.analysis.resource.budgets.notbound;

import java.util.Deque;
import java.util.LinkedList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.osate.aadl2.Element;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.aadl2.modelsupport.modeltraversal.SOMIterator;
import org.osate.aadl2.util.Aadl2Util;
import org.osate.analysis.resource.budgets.internal.busload.model.BusLoadModel;
import org.osate.analysis.resource.budgets.internal.busload.model.Component;
import org.osate.analysis.resource.budgets.internal.busload.model.Memory;
import org.osate.analysis.resource.budgets.internal.busload.model.Visitor;
import org.osate.result.AnalysisResult;
import org.osate.result.Result;
import org.osate.result.ResultType;
import org.osate.result.util.ResultUtil;
import org.osate.ui.dialogs.Dialog;

public class NewNotBoundResoureAnalysis {
	public NewNotBoundResoureAnalysis() {
		super();
	}

	public AnalysisResult invoke(final IProgressMonitor monitor, final SystemInstance systemInstance) {
		final IProgressMonitor pm = monitor == null ? new NullProgressMonitor() : monitor;
		return analyzeBody(pm, systemInstance);
	}

	private AnalysisResult analyzeBody(final IProgressMonitor monitor, final Element obj) {
		if (obj instanceof InstanceObject) {
			final SystemInstance root = ((InstanceObject) obj).getSystemInstance();
			final AnalysisResult analysisResult = ResultUtil.createAnalysisResult("Bus  Load", root);
			analysisResult.setResultType(ResultType.SUCCESS);
			analysisResult.setMessage("Bus load analysis of " + root.getFullName());

			final SOMIterator soms = new SOMIterator(root);
			while (soms.hasNext()) {
				final SystemOperationMode som = soms.nextSOM();
				final Result somResult = ResultUtil.createResult(
						Aadl2Util.isPrintableSOMName(som) ? Aadl2Util.getPrintableSOMMembers(som) : "", som,
						ResultType.SUCCESS);
				analysisResult.getResults().add(somResult);
				final BusLoadModel model = BusLoadModel.buildModel(root, som);

				// Analyze the model
				model.visit(new NotBoundAnalysisVisitor(somResult));
			}
			monitor.done();

			return analysisResult;
		} else {
			Dialog.showError("Power Requirements Analysis Error", "Can only check system instances");
			return null;
		}
	}

	// ==== Analysis Visitor ====

	private class NotBoundAnalysisVisitor implements Visitor {
		private Deque<Result> previousResult = new LinkedList<>();
		private Result currentResult;

		public NotBoundAnalysisVisitor(final Result rootResult) {
			this.currentResult = rootResult;
		}

		@Override
		public void visitMemoryPrefix(final Memory memory) {
			final ComponentInstance memInstance = memory.getMemoryInstance();

			// Create a new result object for the memory
			final Result memResult = ResultUtil.createResult(memInstance.getName(), memInstance, ResultType.SUCCESS);
			currentResult.getSubResults().add(memResult);
			previousResult.push(currentResult);
			currentResult = memResult;
		}

		@Override
		public void visitMemoryPostfix(final Memory memory) {
			// unroll the result stack
			final Result memResult = currentResult;
			currentResult = previousResult.pop();

			// Compute total capacity
			double totalCapacityMemory = 0.0;
			double totalCapacityRAM = 0.0;
			double totalCapacityROM = 0.0;

			for (final Component c : memory.getComponents()) {
				totalCapacityMemory += c.getCapacity();
				totalCapacityRAM += c.getCapacityRAM();
				totalCapacityROM += c.getCapacityROM();
			}

			memory.setTotalCapacityMemory(totalCapacityMemory); // in Kb
			memory.setTotalCapacityRAM(totalCapacityRAM); // in Kb
			memory.setTotalCapacityROM(totalCapacityROM); // in Kb

			double totalBudgetMemory = 0.0;
			double totalBudgetRAM = 0.0;
			double totalBudgetROM = 0.0;

			for (final Component c : memory.getComponents()) {
				if (totalCapacityMemory > 0) {
					totalBudgetMemory += c.getBudget();
				}
				if (totalCapacityRAM > 0) {
					totalBudgetRAM += c.getBudgetRAM();
				}
				if (totalCapacityROM > 0) {
					totalBudgetROM += c.getBudgetROM();
				}
			}

			memory.setTotalBudgetMemory(totalBudgetMemory); // in Kb
			memory.setTotalBudgetRAM(totalBudgetRAM); // in Kb
			memory.setTotalBudgetROM(totalBudgetROM); // in Kb

			// TODO record any other variables needed for excel file. Add in diagnosis


		}
	}
}
