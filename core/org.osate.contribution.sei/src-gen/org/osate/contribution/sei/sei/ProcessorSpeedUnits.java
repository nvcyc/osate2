package org.osate.contribution.sei.sei;

import org.eclipse.emf.common.util.URI;
import org.osate.aadl2.AbstractNamedValue;
import org.osate.aadl2.NamedValue;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.UnitLiteral;
import org.osate.pluginsupport.properties.GeneratedUnits;

/**
 * @since 1.2
 */
public enum ProcessorSpeedUnits implements GeneratedUnits<ProcessorSpeedUnits> {
	KIPS(1.0, "KIPS", "platform:/plugin/org.osate.contribution.sei/resources/properties/SEI.aadl#/0/@ownedPropertyType.4/@ownedLiteral.0"),
	MIPS(1000.0, "MIPS", "platform:/plugin/org.osate.contribution.sei/resources/properties/SEI.aadl#/0/@ownedPropertyType.4/@ownedLiteral.1"),
	GIPS(1000000.0, "GIPS", "platform:/plugin/org.osate.contribution.sei/resources/properties/SEI.aadl#/0/@ownedPropertyType.4/@ownedLiteral.2");

	private final double factorToBase;
	private final String originalName;
	private final URI uri;

	private ProcessorSpeedUnits(double factorToBase, String originalName, String uri) {
		this.factorToBase = factorToBase;
		this.originalName = originalName;
		this.uri = URI.createURI(uri);
	}

	public static ProcessorSpeedUnits valueOf(PropertyExpression propertyExpression) {
		AbstractNamedValue abstractNamedValue = ((NamedValue) propertyExpression).getNamedValue();
		return valueOf(((UnitLiteral) abstractNamedValue).getName().toUpperCase());
	}

	@Override
	public double getFactorToBase() {
		return factorToBase;
	}

	@Override
	public double getFactorTo(ProcessorSpeedUnits target) {
		return factorToBase / target.factorToBase;
	}

	@Override
	public URI getURI() {
		return uri;
	}

	@Override
	public String toString() {
		return originalName;
	}
}
