/**
 * AADL-BA-FrontEnd
 * 
 * Copyright (c) 2011-2020 TELECOM ParisTech and CNRS
 * 
 * TELECOM ParisTech/LTCI
 * 
 * Authors: see AUTHORS
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the Eclipse Public License as published by Eclipse, either
 * version 2.0 of the License, or (at your option) any later version. This
 * program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the Eclipse Public License for
 * more details. You should have received a copy of the Eclipse Public License
 * along with this program. If not, see
 * https://www.eclipse.org/legal/epl-2.0/
 */
package org.osate.ba.aadlba.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.osate.aadl2.ClassifierFeature;
import org.osate.ba.aadlba.AadlBaPackage;
import org.osate.ba.aadlba.BehaviorVariable;
import org.osate.ba.aadlba.BehaviorVariableHolder;
import org.osate.ba.aadlba.ClassifierFeatureHolder;
import org.osate.ba.aadlba.ElementValues;
import org.osate.ba.aadlba.IndexableElement;
import org.osate.ba.aadlba.IntegerValue;
import org.osate.ba.aadlba.ParameterLabel;
import org.osate.ba.aadlba.SubprogramHolderProxy;
import org.osate.ba.aadlba.Target;
import org.osate.ba.utils.visitor.IBAVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Behavior Variable Holder</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.osate.ba.aadlba.impl.BehaviorVariableHolderImpl#getArrayIndexes <em>Array Indexes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BehaviorVariableHolderImpl extends DataHolderImpl implements BehaviorVariableHolder {
	/**
	 * The cached value of the '{@link #getArrayIndexes() <em>Array Indexes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrayIndexes()
	 * @generated
	 * @ordered
	 */
	protected EList<IntegerValue> arrayIndexes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BehaviorVariableHolderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AadlBaPackage.Literals.BEHAVIOR_VARIABLE_HOLDER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<IntegerValue> getArrayIndexes() {
		if (arrayIndexes == null) {
			arrayIndexes = new EObjectContainmentEList.Unsettable<IntegerValue>(IntegerValue.class, this,
					AadlBaPackage.BEHAVIOR_VARIABLE_HOLDER__ARRAY_INDEXES);
		}
		return arrayIndexes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetArrayIndexes() {
		if (arrayIndexes != null)
			((InternalEList.Unsettable<?>) arrayIndexes).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetArrayIndexes() {
		return arrayIndexes != null && ((InternalEList.Unsettable<?>) arrayIndexes).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVariable(final BehaviorVariable variable) {
		element = variable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BehaviorVariable getBehaviorVariable() {
		return (BehaviorVariable) element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setClassifierFeature(final ClassifierFeature classifierFeature) {
		element = classifierFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ClassifierFeature getClassifierFeature() {
		return (ClassifierFeature) element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AadlBaPackage.BEHAVIOR_VARIABLE_HOLDER__ARRAY_INDEXES:
			return ((InternalEList<?>) getArrayIndexes()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AadlBaPackage.BEHAVIOR_VARIABLE_HOLDER__ARRAY_INDEXES:
			return getArrayIndexes();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case AadlBaPackage.BEHAVIOR_VARIABLE_HOLDER__ARRAY_INDEXES:
			getArrayIndexes().clear();
			getArrayIndexes().addAll((Collection<? extends IntegerValue>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case AadlBaPackage.BEHAVIOR_VARIABLE_HOLDER__ARRAY_INDEXES:
			unsetArrayIndexes();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case AadlBaPackage.BEHAVIOR_VARIABLE_HOLDER__ARRAY_INDEXES:
			return isSetArrayIndexes();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == IndexableElement.class) {
			switch (derivedFeatureID) {
			case AadlBaPackage.BEHAVIOR_VARIABLE_HOLDER__ARRAY_INDEXES:
				return AadlBaPackage.INDEXABLE_ELEMENT__ARRAY_INDEXES;
			default:
				return -1;
			}
		}
		if (baseClass == ParameterLabel.class) {
			switch (derivedFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == Target.class) {
			switch (derivedFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == ElementValues.class) {
			switch (derivedFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == SubprogramHolderProxy.class) {
			switch (derivedFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == ClassifierFeatureHolder.class) {
			switch (derivedFeatureID) {
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == IndexableElement.class) {
			switch (baseFeatureID) {
			case AadlBaPackage.INDEXABLE_ELEMENT__ARRAY_INDEXES:
				return AadlBaPackage.BEHAVIOR_VARIABLE_HOLDER__ARRAY_INDEXES;
			default:
				return -1;
			}
		}
		if (baseClass == ParameterLabel.class) {
			switch (baseFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == Target.class) {
			switch (baseFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == ElementValues.class) {
			switch (baseFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == SubprogramHolderProxy.class) {
			switch (baseFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == ClassifierFeatureHolder.class) {
			switch (baseFeatureID) {
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	public void accept(IBAVisitor visitor) {
		visitor.visit(this);
	}

} // BehaviorVariableHolderImpl
