package org.osate.analysis.resource.budgets.internal.busload.model;

import org.osate.aadl2.instance.ConnectionInstanceEnd;

public class ConnectionEnd extends AnalysisElement {
	/** The connection instance end represented. */
	private final ConnectionInstanceEnd connInstanceEnd;
	private final double budget;
	private final double supply;

	public ConnectionEnd(final ConnectionInstanceEnd connInstanceEnd, double budget, double supply) {
		super("connectionEnd");
		this.connInstanceEnd = connInstanceEnd;
		this.budget = budget;
		this.supply = supply;
	}

	public final ConnectionInstanceEnd getConnectionInstanceEnd() {
		return connInstanceEnd;
	}

	@Override
	void visitChildren(final Visitor visitor) {
		// no children
	}

	@Override
	void visitSelfPrefix(final Visitor visitor) {
		// visitor.visitConnectionEnd(this);
	}

	@Override
	void visitSelfPostfix(final Visitor visitor) {
		// leaf node, already visited with prefix
	}
}