/**
 * Copyright (c) 2004-2021 Carnegie Mellon University and others. (see Contributors file). 
 * All Rights Reserved.
 * 
 * NO WARRANTY. ALL MATERIAL IS FURNISHED ON AN "AS-IS" BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO WARRANTIES OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER INCLUDING, BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE
 * OR MERCHANTABILITY, EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL. CARNEGIE MELLON UNIVERSITY DOES NOT
 * MAKE ANY WARRANTY OF ANY KIND WITH RESPECT TO FREEDOM FROM PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Created, in part, with funding and support from the United States Government. (see Acknowledgments file).
 * 
 * This program includes and/or can make use of certain third party source code, object code, documentation and other
 * files ("Third Party Software"). The Third Party Software that is used by this program is dependent upon your system
 * configuration. By using this program, You agree to comply with any and all relevant Third Party Software terms and
 * conditions contained in any such Third Party Software or separate license file distributed with such Third Party
 * Software. The parties who own the Third Party Software ("Third Party Licensors") are intended third party benefici-
 * aries to this license with respect to the terms applicable to their Third Party Software. Third Party Software li-
 * censes only apply to the Third Party Software and not any other portion of this program or this program as a whole.
 */
package org.osate.assure.assure;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Verification Activity Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.osate.assure.assure.VerificationActivityResult#getTargetReference <em>Target Reference</em>}</li>
 *   <li>{@link org.osate.assure.assure.VerificationActivityResult#getPreconditionResult <em>Precondition Result</em>}</li>
 *   <li>{@link org.osate.assure.assure.VerificationActivityResult#getValidationResult <em>Validation Result</em>}</li>
 * </ul>
 *
 * @see org.osate.assure.assure.AssurePackage#getVerificationActivityResult()
 * @model
 * @generated
 */
public interface VerificationActivityResult extends VerificationResult {
	/**
	 * Returns the value of the '<em><b>Target Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Reference</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Reference</em>' containment reference.
	 * @see #setTargetReference(QualifiedVAReference)
	 * @see org.osate.assure.assure.AssurePackage#getVerificationActivityResult_TargetReference()
	 * @model containment="true"
	 * @generated
	 */
	QualifiedVAReference getTargetReference();

	/**
	 * Sets the value of the '{@link org.osate.assure.assure.VerificationActivityResult#getTargetReference <em>Target Reference</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Reference</em>' containment reference.
	 * @see #getTargetReference()
	 * @generated
	 */
	void setTargetReference(QualifiedVAReference value);

	/**
	 * Returns the value of the '<em><b>Precondition Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Precondition Result</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Precondition Result</em>' containment reference.
	 * @see #setPreconditionResult(VerificationResult)
	 * @see org.osate.assure.assure.AssurePackage#getVerificationActivityResult_PreconditionResult()
	 * @model containment="true"
	 * @generated
	 */
	VerificationResult getPreconditionResult();

	/**
	 * Sets the value of the '{@link org.osate.assure.assure.VerificationActivityResult#getPreconditionResult <em>Precondition Result</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precondition Result</em>' containment reference.
	 * @see #getPreconditionResult()
	 * @generated
	 */
	void setPreconditionResult(VerificationResult value);

	/**
	 * Returns the value of the '<em><b>Validation Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Validation Result</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Validation Result</em>' containment reference.
	 * @see #setValidationResult(VerificationResult)
	 * @see org.osate.assure.assure.AssurePackage#getVerificationActivityResult_ValidationResult()
	 * @model containment="true"
	 * @generated
	 */
	VerificationResult getValidationResult();

	/**
	 * Sets the value of the '{@link org.osate.assure.assure.VerificationActivityResult#getValidationResult <em>Validation Result</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Validation Result</em>' containment reference.
	 * @see #getValidationResult()
	 * @generated
	 */
	void setValidationResult(VerificationResult value);

} // VerificationActivityResult
