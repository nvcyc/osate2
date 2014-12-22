/*
* generated by Xtext
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
					put(grammarAccess.getDescriptionElementAccess().getAlternatives(), "rule__DescriptionElement__Alternatives");
					put(grammarAccess.getOrganizationAccess().getGroup(), "rule__Organization__Group__0");
					put(grammarAccess.getStakeholderAccess().getGroup(), "rule__Stakeholder__Group__0");
					put(grammarAccess.getStakeholderAccess().getGroup_2(), "rule__Stakeholder__Group_2__0");
					put(grammarAccess.getStakeholderAccess().getGroup_2_1_0(), "rule__Stakeholder__Group_2_1_0__0");
					put(grammarAccess.getStakeholderAccess().getGroup_2_1_1(), "rule__Stakeholder__Group_2_1_1__0");
					put(grammarAccess.getStakeholderAccess().getGroup_2_1_2(), "rule__Stakeholder__Group_2_1_2__0");
					put(grammarAccess.getStakeholderAccess().getGroup_2_1_3(), "rule__Stakeholder__Group_2_1_3__0");
					put(grammarAccess.getStakeholderAccess().getGroup_2_1_4(), "rule__Stakeholder__Group_2_1_4__0");
					put(grammarAccess.getReferencePathAccess().getGroup(), "rule__ReferencePath__Group__0");
					put(grammarAccess.getReferencePathAccess().getGroup_1(), "rule__ReferencePath__Group_1__0");
					put(grammarAccess.getImportAccess().getGroup(), "rule__Import__Group__0");
					put(grammarAccess.getQualifiedNameAccess().getGroup(), "rule__QualifiedName__Group__0");
					put(grammarAccess.getQualifiedNameAccess().getGroup_1(), "rule__QualifiedName__Group_1__0");
					put(grammarAccess.getQualifiedNameWithWildcardAccess().getGroup(), "rule__QualifiedNameWithWildcard__Group__0");
					put(grammarAccess.getAadlClassifierReferenceAccess().getGroup(), "rule__AadlClassifierReference__Group__0");
					put(grammarAccess.getAadlClassifierReferenceAccess().getGroup_0(), "rule__AadlClassifierReference__Group_0__0");
					put(grammarAccess.getAadlClassifierReferenceAccess().getGroup_2(), "rule__AadlClassifierReference__Group_2__0");
					put(grammarAccess.getOrganizationAccess().getNameAssignment_1(), "rule__Organization__NameAssignment_1");
					put(grammarAccess.getOrganizationAccess().getStakeholderAssignment_2(), "rule__Organization__StakeholderAssignment_2");
					put(grammarAccess.getStakeholderAccess().getNameAssignment_1(), "rule__Stakeholder__NameAssignment_1");
					put(grammarAccess.getStakeholderAccess().getTitleAssignment_2_1_0_1(), "rule__Stakeholder__TitleAssignment_2_1_0_1");
					put(grammarAccess.getStakeholderAccess().getDescriptionAssignment_2_1_1_1(), "rule__Stakeholder__DescriptionAssignment_2_1_1_1");
					put(grammarAccess.getStakeholderAccess().getRoleAssignment_2_1_2_1(), "rule__Stakeholder__RoleAssignment_2_1_2_1");
					put(grammarAccess.getStakeholderAccess().getEmailAssignment_2_1_3_1(), "rule__Stakeholder__EmailAssignment_2_1_3_1");
					put(grammarAccess.getStakeholderAccess().getPhoneAssignment_2_1_4_1(), "rule__Stakeholder__PhoneAssignment_2_1_4_1");
					put(grammarAccess.getModelAccess().getContentAssignment(), "rule__Model__ContentAssignment");
					put(grammarAccess.getDescriptionAccess().getDescriptionAssignment(), "rule__Description__DescriptionAssignment");
					put(grammarAccess.getDescriptionElementAccess().getTextAssignment_0(), "rule__DescriptionElement__TextAssignment_0");
					put(grammarAccess.getDescriptionElementAccess().getRefAssignment_1(), "rule__DescriptionElement__RefAssignment_1");
					put(grammarAccess.getReferencePathAccess().getRefAssignment_0(), "rule__ReferencePath__RefAssignment_0");
					put(grammarAccess.getReferencePathAccess().getSubpathAssignment_1_1(), "rule__ReferencePath__SubpathAssignment_1_1");
					put(grammarAccess.getImportAccess().getImportedNamespaceAssignment_1(), "rule__Import__ImportedNamespaceAssignment_1");
					put(grammarAccess.getStakeholderAccess().getUnorderedGroup_2_1(), "rule__Stakeholder__UnorderedGroup_2_1");
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
