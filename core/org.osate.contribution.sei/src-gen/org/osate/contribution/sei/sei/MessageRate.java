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
public enum MessageRate implements GeneratedUnits<MessageRate> {
	PERSECOND(1.0, "perSecond", "platform:/plugin/org.osate.contribution.sei/resources/properties/SEI.aadl#/0/@ownedProperty.25/@ownedPropertyType/@ownedUnitsType/@ownedLiteral.0");

	private final double factorToBase;
	private final String originalName;
	private final URI uri;

	private MessageRate(double factorToBase, String originalName, String uri) {
		this.factorToBase = factorToBase;
		this.originalName = originalName;
		this.uri = URI.createURI(uri);
	}

	public static MessageRate valueOf(PropertyExpression propertyExpression) {
		AbstractNamedValue abstractNamedValue = ((NamedValue) propertyExpression).getNamedValue();
		return valueOf(((UnitLiteral) abstractNamedValue).getName().toUpperCase());
	}

	@Override
	public double getFactorToBase() {
		return factorToBase;
	}

	@Override
	public double getFactorTo(MessageRate target) {
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
