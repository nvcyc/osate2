/**
 */
package org.osate.analysis.resources.budgets.internal.models.busload.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.osate.analysis.resources.budgets.internal.models.busload.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BusloadFactoryImpl extends EFactoryImpl implements BusloadFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BusloadFactory init() {
		try {
			BusloadFactory theBusloadFactory = (BusloadFactory)EPackage.Registry.INSTANCE.getEFactory(BusloadPackage.eNS_URI);
			if (theBusloadFactory != null) {
				return theBusloadFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BusloadFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BusloadFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case BusloadPackage.CONNECTION: return createConnection();
			case BusloadPackage.BROADCAST: return createBroadcast();
			case BusloadPackage.VIRTUAL_BUS: return createVirtualBus();
			case BusloadPackage.BUS: return createBus();
			case BusloadPackage.BUS_LOAD_MODEL: return createBusLoadModel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Connection createConnection() {
		ConnectionImpl connection = new ConnectionImpl();
		return connection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Broadcast createBroadcast() {
		BroadcastImpl broadcast = new BroadcastImpl();
		return broadcast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VirtualBus createVirtualBus() {
		VirtualBusImpl virtualBus = new VirtualBusImpl();
		return virtualBus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Bus createBus() {
		BusImpl bus = new BusImpl();
		return bus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BusLoadModel createBusLoadModel() {
		BusLoadModelImpl busLoadModel = new BusLoadModelImpl();
		return busLoadModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BusloadPackage getBusloadPackage() {
		return (BusloadPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static BusloadPackage getPackage() {
		return BusloadPackage.eINSTANCE;
	}

} //BusloadFactoryImpl
