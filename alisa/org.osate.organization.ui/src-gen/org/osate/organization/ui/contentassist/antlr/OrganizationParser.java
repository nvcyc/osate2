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
package org.osate.organization.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import org.osate.organization.services.OrganizationGrammarAccess;

public class OrganizationParser extends AbstractContentAssistParser {
	
	@Inject
	private OrganizationGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected org.osate.organization.ui.contentassist.antlr.internal.InternalOrganizationParser createParser() {
		org.osate.organization.ui.contentassist.antlr.internal.InternalOrganizationParser result = new org.osate.organization.ui.contentassist.antlr.internal.InternalOrganizationParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getOrganizationAccess().getGroup(), "rule__Organization__Group__0");
					put(grammarAccess.getStakeholderAccess().getGroup_0(), "rule__Stakeholder__Group_0__0");
					put(grammarAccess.getStakeholderAccess().getGroup_0_3_0(), "rule__Stakeholder__Group_0_3_0__0");
					put(grammarAccess.getStakeholderAccess().getGroup_0_3_1(), "rule__Stakeholder__Group_0_3_1__0");
					put(grammarAccess.getStakeholderAccess().getGroup_0_3_2(), "rule__Stakeholder__Group_0_3_2__0");
					put(grammarAccess.getStakeholderAccess().getGroup_0_3_3(), "rule__Stakeholder__Group_0_3_3__0");
					put(grammarAccess.getStakeholderAccess().getGroup_0_3_4(), "rule__Stakeholder__Group_0_3_4__0");
					put(grammarAccess.getStakeholderAccess().getGroup_0_3_5(), "rule__Stakeholder__Group_0_3_5__0");
					put(grammarAccess.getStakeholderAccess().getGroup_1(), "rule__Stakeholder__Group_1__0");
					put(grammarAccess.getStakeholderAccess().getGroup_1_0(), "rule__Stakeholder__Group_1_0__0");
					put(grammarAccess.getQIDAccess().getGroup(), "rule__QID__Group__0");
					put(grammarAccess.getQIDAccess().getGroup_1(), "rule__QID__Group_1__0");
					put(grammarAccess.getOrganizationAccess().getNameAssignment_1(), "rule__Organization__NameAssignment_1");
					put(grammarAccess.getOrganizationAccess().getStakeholderAssignment_2(), "rule__Organization__StakeholderAssignment_2");
					put(grammarAccess.getStakeholderAccess().getNameAssignment_0_1(), "rule__Stakeholder__NameAssignment_0_1");
					put(grammarAccess.getStakeholderAccess().getFullnameAssignment_0_3_0_2(), "rule__Stakeholder__FullnameAssignment_0_3_0_2");
					put(grammarAccess.getStakeholderAccess().getTitleAssignment_0_3_1_1(), "rule__Stakeholder__TitleAssignment_0_3_1_1");
					put(grammarAccess.getStakeholderAccess().getDescriptionAssignment_0_3_2_1(), "rule__Stakeholder__DescriptionAssignment_0_3_2_1");
					put(grammarAccess.getStakeholderAccess().getRoleAssignment_0_3_3_1(), "rule__Stakeholder__RoleAssignment_0_3_3_1");
					put(grammarAccess.getStakeholderAccess().getEmailAssignment_0_3_4_1(), "rule__Stakeholder__EmailAssignment_0_3_4_1");
					put(grammarAccess.getStakeholderAccess().getPhoneAssignment_0_3_5_1(), "rule__Stakeholder__PhoneAssignment_0_3_5_1");
					put(grammarAccess.getStakeholderAccess().getSupervisorAssignment_1_0_1(), "rule__Stakeholder__SupervisorAssignment_1_0_1");
					put(grammarAccess.getStakeholderAccess().getUnorderedGroup(), "rule__Stakeholder__UnorderedGroup");
					put(grammarAccess.getStakeholderAccess().getUnorderedGroup_0_3(), "rule__Stakeholder__UnorderedGroup_0_3");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			org.osate.organization.ui.contentassist.antlr.internal.InternalOrganizationParser typedParser = (org.osate.organization.ui.contentassist.antlr.internal.InternalOrganizationParser) parser;
			typedParser.entryRuleOrganization();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public OrganizationGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(OrganizationGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
