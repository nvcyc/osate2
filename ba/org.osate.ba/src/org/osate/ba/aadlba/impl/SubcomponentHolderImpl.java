/**
 * AADL-BA-FrontEnd
 * 
 * Copyright (c) 2011-2021 TELECOM ParisTech and CNRS
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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.osate.aadl2.ClassifierFeature;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Subcomponent;
import org.osate.ba.aadlba.AadlBaPackage;
import org.osate.ba.aadlba.GroupHolder;
import org.osate.ba.aadlba.GroupableElement;
import org.osate.ba.aadlba.IndexableElement;
import org.osate.ba.aadlba.IntegerValue;
import org.osate.ba.aadlba.SubcomponentHolder;
import org.osate.ba.utils.visitor.IBAVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Subcomponent Holder</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.osate.ba.aadlba.impl.SubcomponentHolderImpl#getElement <em>Element</em>}</li>
 *   <li>{@link org.osate.ba.aadlba.impl.SubcomponentHolderImpl#getArrayIndexes <em>Array Indexes</em>}</li>
 *   <li>{@link org.osate.ba.aadlba.impl.SubcomponentHolderImpl#getGroupHolders <em>Group Holders</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SubcomponentHolderImpl extends BehaviorElementImpl implements SubcomponentHolder {
	/**
	 * The cached value of the '{@link #getElement() <em>Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected NamedElement element;

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
	 * The cached value of the '{@link #getGroupHolders() <em>Group Holders</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupHolders()
	 * @generated
	 * @ordered
	 */
	protected EList<GroupHolder> groupHolders;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SubcomponentHolderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AadlBaPackage.Literals.SUBCOMPONENT_HOLDER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NamedElement getElement() {
		if (element != null && ((EObject) element).eIsProxy()) {
			InternalEObject oldElement = (InternalEObject) element;
			element = (NamedElement) eResolveProxy(oldElement);
			if (element != oldElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							AadlBaPackage.SUBCOMPONENT_HOLDER__ELEMENT, oldElement, element));
			}
		}
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamedElement basicGetElement() {
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setElement(NamedElement newElement) {
		NamedElement oldElement = element;
		element = newElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AadlBaPackage.SUBCOMPONENT_HOLDER__ELEMENT,
					oldElement, element));
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
					AadlBaPackage.SUBCOMPONENT_HOLDER__ARRAY_INDEXES);
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
	public EList<GroupHolder> getGroupHolders() {
		if (groupHolders == null) {
			groupHolders = new EObjectContainmentEList.Unsettable<GroupHolder>(GroupHolder.class, this,
					AadlBaPackage.SUBCOMPONENT_HOLDER__GROUP_HOLDERS);
		}
		return groupHolders;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetGroupHolders() {
		if (groupHolders != null)
			((InternalEList.Unsettable<?>) groupHolders).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetGroupHolders() {
		return groupHolders != null && ((InternalEList.Unsettable<?>) groupHolders).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSubcomponent(final Subcomponent subcomponent) {
		element = subcomponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Subcomponent getSubcomponent() {
		return (Subcomponent) element;
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
		case AadlBaPackage.SUBCOMPONENT_HOLDER__ARRAY_INDEXES:
			return ((InternalEList<?>) getArrayIndexes()).basicRemove(otherEnd, msgs);
		case AadlBaPackage.SUBCOMPONENT_HOLDER__GROUP_HOLDERS:
			return ((InternalEList<?>) getGroupHolders()).basicRemove(otherEnd, msgs);
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
		case AadlBaPackage.SUBCOMPONENT_HOLDER__ELEMENT:
			if (resolve)
				return getElement();
			return basicGetElement();
		case AadlBaPackage.SUBCOMPONENT_HOLDER__ARRAY_INDEXES:
			return getArrayIndexes();
		case AadlBaPackage.SUBCOMPONENT_HOLDER__GROUP_HOLDERS:
			return getGroupHolders();
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
		case AadlBaPackage.SUBCOMPONENT_HOLDER__ELEMENT:
			setElement((NamedElement) newValue);
			return;
		case AadlBaPackage.SUBCOMPONENT_HOLDER__ARRAY_INDEXES:
			getArrayIndexes().clear();
			getArrayIndexes().addAll((Collection<? extends IntegerValue>) newValue);
			return;
		case AadlBaPackage.SUBCOMPONENT_HOLDER__GROUP_HOLDERS:
			getGroupHolders().clear();
			getGroupHolders().addAll((Collection<? extends GroupHolder>) newValue);
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
		case AadlBaPackage.SUBCOMPONENT_HOLDER__ELEMENT:
			setElement((NamedElement) null);
			return;
		case AadlBaPackage.SUBCOMPONENT_HOLDER__ARRAY_INDEXES:
			unsetArrayIndexes();
			return;
		case AadlBaPackage.SUBCOMPONENT_HOLDER__GROUP_HOLDERS:
			unsetGroupHolders();
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
		case AadlBaPackage.SUBCOMPONENT_HOLDER__ELEMENT:
			return element != null;
		case AadlBaPackage.SUBCOMPONENT_HOLDER__ARRAY_INDEXES:
			return isSetArrayIndexes();
		case AadlBaPackage.SUBCOMPONENT_HOLDER__GROUP_HOLDERS:
			return isSetGroupHolders();
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
			case AadlBaPackage.SUBCOMPONENT_HOLDER__ARRAY_INDEXES:
				return AadlBaPackage.INDEXABLE_ELEMENT__ARRAY_INDEXES;
			default:
				return -1;
			}
		}
		if (baseClass == GroupableElement.class) {
			switch (derivedFeatureID) {
			case AadlBaPackage.SUBCOMPONENT_HOLDER__GROUP_HOLDERS:
				return AadlBaPackage.GROUPABLE_ELEMENT__GROUP_HOLDERS;
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
				return AadlBaPackage.SUBCOMPONENT_HOLDER__ARRAY_INDEXES;
			default:
				return -1;
			}
		}
		if (baseClass == GroupableElement.class) {
			switch (baseFeatureID) {
			case AadlBaPackage.GROUPABLE_ELEMENT__GROUP_HOLDERS:
				return AadlBaPackage.SUBCOMPONENT_HOLDER__GROUP_HOLDERS;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	public void accept(IBAVisitor visitor) {
		visitor.visit(this);
	}

} // SubcomponentHolderImpl
