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
package org.osate.aadl2.instantiation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.ConnectionInstanceEnd;
import org.osate.aadl2.instance.ConnectionKind;
import org.osate.aadl2.instance.ConnectionReference;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.aadl2.instance.util.InstanceSwitch;
import org.osate.aadl2.instance.util.InstanceUtil.InstantiatedClassifier;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;
import org.osate.aadl2.modelsupport.modeltraversal.AadlProcessingSwitchWithProgress;

class ValidateConnectionsSwitch extends AadlProcessingSwitchWithProgress {
	private final Map<InstanceObject, InstantiatedClassifier> classifierCache;
	private final Map<FeatureInstance, Set<ConnectionInstance>> inDataPortConnectionsMap;

	public ValidateConnectionsSwitch(IProgressMonitor monitor, AnalysisErrorReporterManager errManager,
			Map<InstanceObject, InstantiatedClassifier> classifierCache) {
		super(monitor, errManager);
		this.classifierCache = classifierCache;
		inDataPortConnectionsMap = new HashMap<>();
	}

	@Override
	protected void initSwitches() {
		instanceSwitch = new InstanceSwitch<String>() {

			@Override
			public String caseComponentInstance(final ComponentInstance ci) {
				if (monitor.isCanceled()) {
					cancelTraversal();
					return DONE;
				}
				validateConnections(ci);
				return DONE;
			}
		};
	}

	public void postProcess() {
		flagInDataPortsWIthMultipleConnections();
	}

	private void validateConnections(ComponentInstance ci) {
		removeShortAccessConnections(ci);
		recordInDataPortConnections(ci);
		// more
	}

	/**
	 * If a feature group contains access and port features we may have created incomplete access connections that
	 * end, e.g., on a thread, but for which there is also a complete connection to a subcomponent of this thread.
	 * This method finds these connections and deletes them from the instance model.
	 *
	 * @param ci - Validate connections owned by ci
	 */
	private void removeShortAccessConnections(ComponentInstance ci) {
		List<ConnectionInstance> connis = ci.getConnectionInstances();
		List<ConnectionInstance> toRemove = new ArrayList<>();
		Map<ConnectionInstanceEnd, List<ConnectionInstance>> bySrc = connis.stream()
				.collect(Collectors.groupingBy(ConnectionInstance::getSource));
		Map<ConnectionInstanceEnd, List<ConnectionInstance>> byDst = connis.stream()
				.collect(Collectors.groupingBy(ConnectionInstance::getSource));

		connis.stream().forEach(conni -> {
			if (conni.getKind() != ConnectionKind.ACCESS_CONNECTION) {
				return;
			}
			ConnectionInstanceEnd src = conni.getSource();
			ConnectionInstanceEnd dst = conni.getDestination();

			boolean remove = bySrc.getOrDefault(src, Collections.emptyList())
					.stream()
					.anyMatch(test -> shouldCompare(test, conni) && startsWith(test, conni));
			remove |= bySrc.getOrDefault(dst, Collections.emptyList())
					.stream()
					.anyMatch(test -> shouldCompare(test, conni) && startsWithReverse(test, conni));
			remove |= byDst.getOrDefault(dst, Collections.emptyList())
					.stream()
					.anyMatch(test -> shouldCompare(test, conni) && endsWith(test, conni));
			remove |= byDst.getOrDefault(src, Collections.emptyList())
					.stream()
					.anyMatch(test -> shouldCompare(test, conni) && endsWithReverse(test, conni));
			if (remove) {
				toRemove.add(conni);
			}
		});
		connis.removeAll(toRemove);
	}

	private boolean shouldCompare(ConnectionInstance test, ConnectionInstance conni) {
		return test != conni && test.getKind() == ConnectionKind.ACCESS_CONNECTION;
	}

	private boolean startsWith(ConnectionInstance test, ConnectionInstance conni) {
		List<ConnectionReference> testRefs = test.getConnectionReferences();
		List<ConnectionReference> connRefs = conni.getConnectionReferences();
		if (connRefs.size() >= testRefs.size()) {
			return false;
		}

		Iterator<ConnectionReference> testing = testRefs.iterator();
		Iterator<ConnectionReference> prefix = connRefs.iterator();

		while (prefix.hasNext()) {
			ConnectionReference t = testing.next();
			ConnectionReference p = prefix.next();
			if (t.getConnection() != p.getConnection()) {
				return false;
			}
			if (!prefix.hasNext()) {
				return t.getDestination() == p.getDestination();
			}
		}
		return false;
	}

	private boolean startsWithReverse(ConnectionInstance test, ConnectionInstance conni) {
		List<ConnectionReference> testRefs = test.getConnectionReferences();
		List<ConnectionReference> connRefs = conni.getConnectionReferences();
		if (connRefs.size() >= testRefs.size()) {
			return false;
		}

		Iterator<ConnectionReference> testing = testRefs.iterator();
		ListIterator<ConnectionReference> prefix = connRefs.listIterator(connRefs.size());

		while (prefix.hasPrevious()) {
			ConnectionReference t = testing.next();
			ConnectionReference p = prefix.previous();
			if (t.getConnection() != p.getConnection()) {
				return false;
			}
			if (!prefix.hasPrevious()) {
				return t.getDestination() == p.getSource();
			}
		}
		return false;
	}

	private boolean endsWith(ConnectionInstance test, ConnectionInstance conni) {
		List<ConnectionReference> testRefs = test.getConnectionReferences();
		List<ConnectionReference> connRefs = conni.getConnectionReferences();
		if (connRefs.size() >= testRefs.size()) {
			return false;
		}

		ListIterator<ConnectionReference> testing = test.getConnectionReferences()
				.listIterator(test.getConnectionReferences().size());
		ListIterator<ConnectionReference> prefix = connRefs.listIterator(connRefs.size());

		while (prefix.hasPrevious()) {
			ConnectionReference t = testing.previous();
			ConnectionReference p = prefix.previous();
			if (t.getConnection() != p.getConnection()) {
				return false;
			}
			if (!prefix.hasPrevious()) {
				return t.getSource() == p.getSource();
			}
		}
		return false;
	}

	private boolean endsWithReverse(ConnectionInstance test, ConnectionInstance conni) {
		List<ConnectionReference> testRefs = test.getConnectionReferences();
		List<ConnectionReference> connRefs = conni.getConnectionReferences();
		if (connRefs.size() >= testRefs.size()) {
			return false;
		}

		ListIterator<ConnectionReference> testing = test.getConnectionReferences()
				.listIterator(test.getConnectionReferences().size());
		Iterator<ConnectionReference> prefix = connRefs.iterator();

		while (prefix.hasNext()) {
			ConnectionReference t = testing.previous();
			ConnectionReference p = prefix.next();
			if (t.getConnection() != p.getConnection()) {
				return false;
			}
			if (!prefix.hasNext()) {
				return t.getSource() == p.getDestination();
			}
		}
		return false;
	}

	private void recordInDataPortConnections(final ComponentInstance ci) {
		final List<ConnectionInstance> connis = ci.getConnectionInstances();
		for (final ConnectionInstance conni : connis) {
			final ConnectionInstanceEnd cie = conni.getDestination();
			if (cie instanceof FeatureInstance) {
				final FeatureInstance fi = (FeatureInstance) cie;
				if (fi.getFeature() instanceof DataPort) {
					Set<ConnectionInstance> set = inDataPortConnectionsMap.get(fi);
					if (set == null) {
						set = new HashSet<>();
						inDataPortConnectionsMap.put(fi, set);
					}
					set.add(conni);
				}
			}
		}
	}

	// XXX: Clean this up!
	private void flagInDataPortsWIthMultipleConnections() {
		for (final Entry<FeatureInstance, Set<ConnectionInstance>> entry : inDataPortConnectionsMap.entrySet()) {
			final FeatureInstance fi = entry.getKey();
			final Set<ConnectionInstance> connis = entry.getValue();
			if (connis.size() > 1) {
				final Set<ConnectionInstance> allModes = new HashSet<>();
				final Map<SystemOperationMode, Set<ConnectionInstance>> modeToConnection = new HashMap<>();
				for (final ConnectionInstance ci : connis) {
					final List<SystemOperationMode> inModes = ci.getInSystemOperationModes();
					if (inModes != null && !inModes.isEmpty()) {
						for (final SystemOperationMode som : inModes) {
							Set<ConnectionInstance> conns = modeToConnection.get(som);
							if (conns == null) {
								conns = new HashSet<>();
								modeToConnection.put(som, conns);
							}
							conns.add(ci);
						}
					} else {
						allModes.add(ci);
					}
				}

				// Look for problems

				// are all the connections modeless?
				if (modeToConnection.isEmpty()) {
					if (allModes.size() > 1) {
						error(fi, "More than one connection instance ends at data port");
						for (final ConnectionInstance ci : allModes) {
							error(ci, "More than one connection instance ends at data port "
									+ fi.getInstanceObjectPath());
						}
					}
				} else {
					for (final Entry<SystemOperationMode, Set<ConnectionInstance>> mToC : modeToConnection.entrySet()) {
						final Set<ConnectionInstance> conns = mToC.getValue();
						conns.addAll(allModes);
						if (conns.size() > 1) {
							final String somName = mToC.getKey().getName();
							error(fi, "More than one connection instance ends at data port in system operation mode "
									+ somName);
							for (final ConnectionInstance ci : conns) {
								error(ci,
										"More than one connection instance ends at data port "
										+ fi.getInstanceObjectPath() + " in system operation mode "
										+ somName);
							}
						}
					}
				}
			}
		}
	}
}
