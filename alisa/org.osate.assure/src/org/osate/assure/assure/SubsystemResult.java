/**
 * Copyright 2015 Carnegie Mellon University. All Rights Reserved.
 * 
 * NO WARRANTY. THIS CARNEGIE MELLON UNIVERSITY AND SOFTWARE ENGINEERING INSTITUTE
 * MATERIAL IS FURNISHED ON AN "AS-IS" BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO
 * WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER INCLUDING,
 * BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE OR MERCHANTABILITY,
 * EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL. CARNEGIE MELLON
 * UNIVERSITY DOES NOT MAKE ANY WARRANTY OF ANY KIND WITH RESPECT TO FREEDOM FROM
 * PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 * 
 * Released under the Eclipse Public License (http://www.eclipse.org/org/documents/epl-v10.php)
 * 
 * See COPYRIGHT file for full details.
 */
package org.osate.assure.assure;

import org.eclipse.emf.common.util.EList;

import org.osate.aadl2.Subcomponent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Subsystem Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.osate.assure.assure.SubsystemResult#getTargetSystem <em>Target System</em>}</li>
 *   <li>{@link org.osate.assure.assure.SubsystemResult#getMessage <em>Message</em>}</li>
 *   <li>{@link org.osate.assure.assure.SubsystemResult#getClaimResult <em>Claim Result</em>}</li>
 *   <li>{@link org.osate.assure.assure.SubsystemResult#getSubsystemResult <em>Subsystem Result</em>}</li>
 * </ul>
 *
 * @see org.osate.assure.assure.AssurePackage#getSubsystemResult()
 * @model
 * @generated
 */
public interface SubsystemResult extends AssureResult {
	/**
	 * Returns the value of the '<em><b>Target System</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target System</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target System</em>' reference.
	 * @see #setTargetSystem(Subcomponent)
	 * @see org.osate.assure.assure.AssurePackage#getSubsystemResult_TargetSystem()
	 * @model
	 * @generated
	 */
	Subcomponent getTargetSystem();

	/**
	 * Sets the value of the '{@link org.osate.assure.assure.SubsystemResult#getTargetSystem <em>Target System</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target System</em>' reference.
	 * @see #getTargetSystem()
	 * @generated
	 */
	void setTargetSystem(Subcomponent value);

	/**
	 * Returns the value of the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message</em>' attribute.
	 * @see #setMessage(String)
	 * @see org.osate.assure.assure.AssurePackage#getSubsystemResult_Message()
	 * @model
	 * @generated
	 */
	String getMessage();

	/**
	 * Sets the value of the '{@link org.osate.assure.assure.SubsystemResult#getMessage <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message</em>' attribute.
	 * @see #getMessage()
	 * @generated
	 */
	void setMessage(String value);

	/**
	 * Returns the value of the '<em><b>Claim Result</b></em>' containment reference list.
	 * The list contents are of type {@link org.osate.assure.assure.ClaimResult}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Claim Result</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Claim Result</em>' containment reference list.
	 * @see org.osate.assure.assure.AssurePackage#getSubsystemResult_ClaimResult()
	 * @model containment="true"
	 * @generated
	 */
	EList<ClaimResult> getClaimResult();

	/**
	 * Returns the value of the '<em><b>Subsystem Result</b></em>' containment reference list.
	 * The list contents are of type {@link org.osate.assure.assure.SubsystemResult}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subsystem Result</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subsystem Result</em>' containment reference list.
	 * @see org.osate.assure.assure.AssurePackage#getSubsystemResult_SubsystemResult()
	 * @model containment="true"
	 * @generated
	 */
	EList<SubsystemResult> getSubsystemResult();

} // SubsystemResult
