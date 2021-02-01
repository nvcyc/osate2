/**
 */
package org.osate.analysis.resources.budgets.internal.models.busload.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.osate.analysis.resources.budgets.internal.models.analysis.AnalysisElement;
import org.osate.analysis.resources.budgets.internal.models.busload.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.osate.analysis.resources.budgets.internal.models.busload.BusloadPackage
 * @generated
 */
public class BusloadSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static BusloadPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BusloadSwitch() {
		if (modelPackage == null) {
			modelPackage = BusloadPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case BusloadPackage.BUSLOAD_ELEMENT: {
				BusloadElement busloadElement = (BusloadElement)theEObject;
				T result = caseBusloadElement(busloadElement);
				if (result == null) result = caseAnalysisElement(busloadElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BusloadPackage.CONNECTION: {
				Connection connection = (Connection)theEObject;
				T result = caseConnection(connection);
				if (result == null) result = caseBusloadElement(connection);
				if (result == null) result = caseAnalysisElement(connection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BusloadPackage.BROADCAST: {
				Broadcast broadcast = (Broadcast)theEObject;
				T result = caseBroadcast(broadcast);
				if (result == null) result = caseBusloadElement(broadcast);
				if (result == null) result = caseAnalysisElement(broadcast);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BusloadPackage.BUS_OR_VIRTUAL_BUS: {
				BusOrVirtualBus busOrVirtualBus = (BusOrVirtualBus)theEObject;
				T result = caseBusOrVirtualBus(busOrVirtualBus);
				if (result == null) result = caseBusloadElement(busOrVirtualBus);
				if (result == null) result = caseAnalysisElement(busOrVirtualBus);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BusloadPackage.VIRTUAL_BUS: {
				VirtualBus virtualBus = (VirtualBus)theEObject;
				T result = caseVirtualBus(virtualBus);
				if (result == null) result = caseBusOrVirtualBus(virtualBus);
				if (result == null) result = caseBusloadElement(virtualBus);
				if (result == null) result = caseAnalysisElement(virtualBus);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BusloadPackage.BUS: {
				Bus bus = (Bus)theEObject;
				T result = caseBus(bus);
				if (result == null) result = caseBusOrVirtualBus(bus);
				if (result == null) result = caseBusloadElement(bus);
				if (result == null) result = caseAnalysisElement(bus);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BusloadPackage.BUS_LOAD_MODEL: {
				BusLoadModel busLoadModel = (BusLoadModel)theEObject;
				T result = caseBusLoadModel(busLoadModel);
				if (result == null) result = caseAnalysisElement(busLoadModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBusloadElement(BusloadElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnalysisElement(AnalysisElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnection(Connection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Broadcast</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Broadcast</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBroadcast(Broadcast object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bus Or Virtual Bus</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bus Or Virtual Bus</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBusOrVirtualBus(BusOrVirtualBus object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Virtual Bus</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Virtual Bus</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVirtualBus(VirtualBus object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bus</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bus</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBus(Bus object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bus Load Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bus Load Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBusLoadModel(BusLoadModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //BusloadSwitch
