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

/*
 * generated by Xtext
 */
package org.osate.alisa.workbench.generator

import com.google.inject.Inject
import java.util.Collections
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.osate.aadl2.ComponentClassifier
import org.osate.aadl2.ComponentImplementation
import org.osate.aadl2.ComponentType
import org.osate.aadl2.Subcomponent
import org.osate.aadl2.util.Aadl2Util
import org.osate.alisa.workbench.alisa.AssuranceCase
import org.osate.alisa.workbench.alisa.AssuranceTask
import org.osate.alisa.workbench.util.IAlisaGlobalReferenceFinder
import org.osate.categories.categories.CategoryFilter
import org.osate.reqspec.reqSpec.Requirement
import org.osate.reqspec.reqSpec.RequirementSet
import org.osate.reqspec.reqSpec.SystemRequirementSet
import org.osate.verify.util.IVerifyGlobalReferenceFinder
import org.osate.verify.verify.AllExpr
import org.osate.verify.verify.ArgumentExpr
import org.osate.verify.verify.Claim
import org.osate.verify.verify.ElseExpr
import org.osate.verify.verify.RefExpr
import org.osate.verify.verify.ThenExpr
import org.osate.verify.verify.VerificationActivity
import org.osate.verify.verify.VerificationPlan

import static extension org.osate.reqspec.util.ReqSpecUtilExtension.*
import static extension org.osate.verify.util.VerifyUtilExtension.*
import static extension org.osate.alisa.workbench.util.AlisaWorkbenchUtilExtension.*
import static extension org.osate.alisa.common.util.CommonUtilExtension.*
import org.osate.alisa.workbench.alisa.AssurancePlan
import org.osate.reqspec.reqSpec.GlobalRequirementSet

/**
 * Generates code from your model files on save.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#TutorialCodeGeneration
 */
class AlisaGenerator implements IGenerator {

	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
	}


}