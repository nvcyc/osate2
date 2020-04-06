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
package org.osate.analysis.resource.budgets.handlers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.osate.aadl2.Element;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.aadl2.modelsupport.Activator;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;
import org.osate.aadl2.modelsupport.errorreporting.MarkerAnalysisErrorReporter;
import org.osate.aadl2.modelsupport.util.AadlUtil;
import org.osate.aadl2.util.Aadl2Util;
import org.osate.analysis.resource.budgets.busload.NewBusLoadAnalysis;
import org.osate.result.AnalysisResult;
import org.osate.result.Diagnostic;
import org.osate.result.Result;
import org.osate.result.util.ResultUtil;

/**
 * @since 3.0
 */
public final class NewBusLoadAnalysisHandler extends NewAbstractAaxlHandler {
	private static final String MARKER_TYPE = "org.osate.analysis.resource.budgets.BusLoadAnalysisMarker";
	private static final String REPORT_SUB_DIR = "BusLoad";

	public NewBusLoadAnalysisHandler() {
		super();
	}

	@Override
	protected String getSubDirName() {
		return REPORT_SUB_DIR;
	}

	@Override
	protected String getOutputFileForAaxlFile(final IFile aaxlFile, final String filename) {
		return filename + ".csv";
	}

	@Override
	protected Job createAnalysisJob(final IFile aaxlFile, final IFile outputFile) {
		return new BusLoadJob(aaxlFile, outputFile);
	}

	private final class BusLoadJob extends WorkspaceJob {
		private final IFile aaxlFile;
		private final IFile outputFile;

		public BusLoadJob(final IFile aaxlFile, final IFile outputFile) {
			super("Bus load analysis of " + aaxlFile.getName());
			this.aaxlFile = aaxlFile;
			this.outputFile = outputFile;
		}

		@Override
		public IStatus runInWorkspace(final IProgressMonitor monitor) throws CoreException {
			final AnalysisErrorReporterManager errManager = new AnalysisErrorReporterManager(
					new MarkerAnalysisErrorReporter.Factory(MARKER_TYPE));

			// Three phases (1) analysis, (2) marker generation, (3) csv file
			final SubMonitor subMonitor = SubMonitor.convert(monitor, 3);
			boolean cancelled = false;

			try {
				final AnalysisResult analysisResult = new NewBusLoadAnalysis().invoke(subMonitor.split(1),
						(SystemInstance) AadlUtil.getElement(aaxlFile));
				if (subMonitor.isCanceled()) {
					throw new OperationCanceledException();
				}
				generateMarkers(analysisResult, errManager);
				subMonitor.worked(1);
				writeCSVFile(analysisResult, outputFile, subMonitor.split(1));
			} catch (final OperationCanceledException e) {
				cancelled = true;
			}

			return cancelled ? Status.CANCEL_STATUS : Status.OK_STATUS;
		}

	}

	/*
	 * This seems like the type of thing we want to do all the time, but the problem is that so far
	 * there hasn't been a standard way of dealing with the system operation modes. Here we
	 * have the system operation modes as the first level of results under the AnalysisResult
	 * object. If we can standardize the handling of SOMs then we can standardize these methods
	 * somewhere;
	 */

	private static void generateMarkers(final AnalysisResult analysisResult,
			final AnalysisErrorReporterManager errManager) {
		// Handle each SOM
		analysisResult.getResults().forEach(r -> {
			final String somName = r.getMessage();
			final String somPostfix = somName.isEmpty() ? "" : (" in modes " + somName);
			generateMarkersForSOM(r, errManager, somPostfix);
		});
	}

	private static void generateMarkersForSOM(final Result result, final AnalysisErrorReporterManager errManager,
			final String somPostfix) {
		generateMarkersFromDiagnostics(result.getDiagnostics(), errManager, somPostfix);
		result.getSubResults().forEach(r -> generateMarkersForSOM(r, errManager, somPostfix));
	}

	private static void generateMarkersFromDiagnostics(final List<Diagnostic> diagnostics,
			final AnalysisErrorReporterManager errManager, final String somPostfix) {
		diagnostics.forEach(issue -> {
			switch (issue.getDiagnosticType()) {
			case ERROR:
				errManager.error((Element) issue.getModelElement(), issue.getMessage() + somPostfix);
				break;
			case INFO:
				errManager.info((Element) issue.getModelElement(), issue.getMessage() + somPostfix);
				break;
			case WARNING:
				errManager.warning((Element) issue.getModelElement(), issue.getMessage() + somPostfix);
				break;
			default:
				// Do nothing.
			}
		});
	}

	// === CSV Output methods ===

	private static void writeCSVFile(final AnalysisResult analysisResult, final IFile outputFile,
			final IProgressMonitor monitor) {
		final String csvContent = getCSVasString(analysisResult);
		final InputStream inputStream = new ByteArrayInputStream(csvContent.getBytes());

		try {
			if (outputFile.exists()) {
				outputFile.setContents(inputStream, true, true, monitor);
			} else {
				outputFile.create(inputStream, true, monitor);
			}
		} catch (final CoreException e) {
			Activator.logThrowable(e);
		}
	}

	private static String getCSVasString(final AnalysisResult analysisResult) {
		final StringWriter writer = new StringWriter();
		final PrintWriter pw = new PrintWriter(writer);

		// Define TAB as the separator for the columns!
		pw.println("Sep=\t");

		// Output the analysis results
		generateCSVforAnalysis(pw, analysisResult);
		pw.close();
		return writer.toString();
	}

	private static void generateCSVforAnalysis(final PrintWriter pw, final AnalysisResult analysisResult) {
		pw.println(analysisResult.getMessage());
		pw.println();
		pw.println();
		analysisResult.getResults().forEach(somResult -> generateCSVforSOM(pw, somResult));
	}

	private static void generateCSVforSOM(final PrintWriter pw, final Result somResult) {
		if (Aadl2Util.isPrintableSOMName((SystemOperationMode) somResult.getModelElement())) {
			pw.println("Analysis results in modes " + somResult.getMessage());
			pw.println();
		}

		/*
		 * Go through the children twice: First to print summary information and then to recursively
		 * print the sub information.
		 */

		pw.println("Physical Bus\tCapacity (KB/s)\tBudget (KB/s)\tRequired Budget (KB/s)\tActual (KB/s)");

		for (final Result subResult : somResult.getSubResults()) {
			pw.print(subResult.getMessage());
			pw.print("\t");
			pw.print(ResultUtil.getReal(subResult, 0)); // Capacity
			pw.print("\t");
			pw.print(ResultUtil.getReal(subResult, 1)); // Budget
			pw.print("\t");
			pw.print(ResultUtil.getReal(subResult, 2)); // Required Capacity
			pw.print("\t");
			pw.print(ResultUtil.getReal(subResult, 3)); // Actual
			pw.println();
		}
		pw.println();

		// NO DIAGNOSTICS AT THE SOM LEVEL

		somResult.getSubResults().forEach(busResult -> generateCSVforBus(pw, busResult, null));
		pw.println(); // add a second newline, the first is from the end of generateCSVforBus()
	}

	private static void generateCSVforBus(final PrintWriter pw, final Result busResult, final Result boundTo) {
		if (boundTo == null) {
			pw.print("Bus ");
			pw.println(busResult.getMessage());
		} else {
			pw.print("Virtual bus ");
			pw.print(busResult.getMessage());
			pw.print(" bound to ");
			pw.println(boundTo.getMessage());
		}

		/*
		 * Go through the children twice: First to print summary information and then to recursively
		 * print the sub information.
		 */

		pw.println(
				"Bound Virtual Bus/Connection\tCapacity (KB/s)\tBudget (KB/s)\tRequired Budget (KB/s)\tActual (KB/s)");

		final int numBus = (int) ResultUtil.getInteger(busResult, 4);
		final List<Result> subResults = busResult.getSubResults();
		for (final Result subResult : subResults.subList(0, numBus)) {
			pw.print(subResult.getMessage());
			pw.print("\t");
			pw.print(ResultUtil.getReal(subResult, 0)); // Capacity
			pw.print("\t");
			pw.print(ResultUtil.getReal(subResult, 1)); // Budget
			pw.print("\t");
			pw.print(ResultUtil.getReal(subResult, 2)); // Required Capacity
			pw.print("\t");
			pw.print(ResultUtil.getReal(subResult, 3)); // Actual
			pw.println();
		}
		for (final Result subResult : subResults.subList(numBus, subResults.size())) {
			pw.print(subResult.getMessage());
			pw.print("\t");
			// NO CAPACITY
			pw.print("\t");
			pw.print(ResultUtil.getReal(subResult, 0)); // Budget
			pw.print("\t");
			// NO REQUIRED CAPACITY
			pw.print("\t");
			pw.print(ResultUtil.getReal(subResult, 1)); // Actual
			pw.println();
		}
		pw.println();

		if (!busResult.getDiagnostics().isEmpty()) {
			generateCSVforDiagnostics(pw, busResult.getDiagnostics());
			pw.println();
		}

		subResults.subList(0, numBus).forEach(br -> generateCSVforBus(pw, br, busResult));
		subResults.subList(numBus, subResults.size()).forEach(br -> generateCSVforConnection(pw, br, busResult));
	}

	private static void generateCSVforConnection(final PrintWriter pw, final Result connectionResult,
			final Result boundTo) {
		// only do something if there are diagnostics
		if (!connectionResult.getDiagnostics().isEmpty()) {
			pw.print("Connection ");
			pw.print(connectionResult.getMessage());
			pw.print(" bound to ");
			pw.println(boundTo.getMessage());
			generateCSVforDiagnostics(pw, connectionResult.getDiagnostics());
			pw.println();
		}
	}

	private static void generateCSVforDiagnostics(final PrintWriter pw, final List<Diagnostic> diagnostics) {
		for (final Diagnostic issue : diagnostics) {
			pw.print(issue.getDiagnosticType().getName());
			pw.print(": ");
			pw.println(issue.getMessage());
		}
	}
}
