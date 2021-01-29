/**
 * Copyright (c) 2004-2021 Carnegie Mellon University and others. (see Contributors file).
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

import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.ConnectionInstanceEnd;
import org.osate.result.Result;
import org.osate.result.ResultType;
import org.osate.result.util.ResultUtil;

/**
 * @since 3.0
 */
public final class Broadcast extends AnalysisElement {
	/** The feature that is the source of the broadcast */
	private final ConnectionInstanceEnd source;

	/** The connections that share the broadcast. */
	private final List<Connection> connections = new ArrayList<>();

	public Broadcast(final ConnectionInstanceEnd source) {
		super("broadcast");
		this.source = source;
	}

	public final ConnectionInstanceEnd getSource() {
		return source;
	}

	// Non-public, only used for building the model
	Connection addConnection(final ConnectionInstance connection) {
		final Connection theConnection = new Connection(connection);
		connections.add(theConnection);
		return theConnection;
	}

	public final List<Connection> getConnections() {
		return connections;
	}

	@Override
	<S> S visitSelfPrefix(Visitor<S> visitor, S state) {
		return ((BusLoadVisitor<S>) visitor).visitBroadcastPrefix(this, state);
	}

	@Override
	<S> S updateStateForChild(Visitor<S> visitor, S state, ModelElement child) {
		return ((BusLoadVisitor<S>) visitor).updateStateForChildOfBroadcast(this, state, child);
	}

	@Override
	<S> void visitChildren(Visitor<S> visitor, S state) {
		visit(connections, visitor, state);
	}

	@Override
	<S> void visitSelfPostfix(Visitor<S> visitor, S state) {
		((BusLoadVisitor<S>) visitor).visitBroadcastPostfix(this, state);
	}

	@Override
	public Result makeResult(final Result parentResult) {
		final Result broadcastResult = ResultUtil.createResult("Broadcast from " + source.getInstanceObjectPath(),
				source, ResultType.SUCCESS);
		return addResultToParent(parentResult, broadcastResult);
	}
}
