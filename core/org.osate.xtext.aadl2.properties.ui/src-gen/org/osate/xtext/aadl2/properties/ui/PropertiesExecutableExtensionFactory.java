/*
 * generated by Xtext
 */
package org.osate.xtext.aadl2.properties.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class PropertiesExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return org.osate.xtext.aadl2.properties.ui.internal.PropertiesActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return org.osate.xtext.aadl2.properties.ui.internal.PropertiesActivator.getInstance().getInjector("org.osate.xtext.aadl2.properties.Properties");
	}
	
}
