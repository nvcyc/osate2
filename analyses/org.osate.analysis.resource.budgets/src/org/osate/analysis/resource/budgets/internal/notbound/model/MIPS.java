package org.osate.analysis.resource.budgets.internal.notbound.model;

import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.analysis.resource.budgets.internal.shared.model.AnalysisElement;
import org.osate.analysis.resource.budgets.internal.shared.model.Visitor;

public class MIPS extends AnalysisElement {
	private SystemInstance si;
	private int totalComponents, totalBudgetedComponents, totalResources, totalCapacityResources;
	private double totalBudget, totalCapacity;

	public MIPS(final SystemInstance si, final ComponentInstance ci, String somName) {
		super("MIPS");
		this.si = si;
		this.setComponentInstance(ci);
		this.setSomName(somName);
	}

	public SystemInstance getSystemInstance() {
		return si;
	}

	public final int getResources() {
		return totalResources;
	}

	public final void setResources(final int totalResources) {
		this.totalResources = totalResources;
	}

	public final int getCapacityResources() {
		return totalCapacityResources;
	}

	public final void setCapacityResources(final int totalCapacityResources) {
		this.totalCapacityResources = totalCapacityResources;
	}

	public final double getTotalCapacity() {
		return totalCapacity;
	}

	public final void setTotalCapacity(final double totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	@Override
	public final double getTotalBudget() {
		return totalBudget;
	}

	@Override
	public final void setTotalBudget(final double totalBudget) {
		this.totalBudget = totalBudget;
	}

	public int getTotalComponents() {
		return totalComponents;
	}

	public void setTotalComponents(int totalComponents) {
		this.totalComponents = totalComponents;
	}

	public int getTotalBudgetedComponents() {
		return totalBudgetedComponents;
	}

	public void setTotalBudgetedComponents(int totalBudgetedComponents) {
		this.totalBudgetedComponents = totalBudgetedComponents;
	}

	@Override
	protected void visitSelfPrefix(final Visitor visitor) {
		visitor.visitMIPSPrefix(this);
	}

	@Override
	protected void visitSelfPostfix(final Visitor visitor) {
		visitor.visitMIPSPostfix(this);
	}

	@Override
	protected final void visitChildren(final Visitor visitor) {
		visit(components, visitor);
	}
}