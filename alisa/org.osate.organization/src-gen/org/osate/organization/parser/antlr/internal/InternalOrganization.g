/*
* generated by Xtext
*/
grammar InternalOrganization;

options {
	superClass=AbstractInternalAntlrParser;
	
}

@lexer::header {
package org.osate.organization.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package org.osate.organization.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.osate.organization.services.OrganizationGrammarAccess;

}

@parser::members {

 	private OrganizationGrammarAccess grammarAccess;
 	
    public InternalOrganizationParser(TokenStream input, OrganizationGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }
    
    @Override
    protected String getFirstRuleName() {
    	return "Organization";	
   	}
   	
   	@Override
   	protected OrganizationGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}
}

@rulecatch { 
    catch (RecognitionException re) { 
        recover(input,re); 
        appendSkippedTokens();
    } 
}




// Entry rule entryRuleOrganization
entryRuleOrganization returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getOrganizationRule()); }
	 iv_ruleOrganization=ruleOrganization 
	 { $current=$iv_ruleOrganization.current; } 
	 EOF 
;

// Rule Organization
ruleOrganization returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='organization' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getOrganizationAccess().getOrganizationKeyword_0());
    }
(
(
		lv_name_1_0=RULE_ID
		{
			newLeafNode(lv_name_1_0, grammarAccess.getOrganizationAccess().getNameIDTerminalRuleCall_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getOrganizationRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"ID");
	    }

)
)(
(
		{ 
	        newCompositeNode(grammarAccess.getOrganizationAccess().getStakeholderStakeholderParserRuleCall_2_0()); 
	    }
		lv_stakeholder_2_0=ruleStakeholder		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getOrganizationRule());
	        }
       		add(
       			$current, 
       			"stakeholder",
        		lv_stakeholder_2_0, 
        		"Stakeholder");
	        afterParserOrEnumRuleCall();
	    }

)
)+)
;





// Entry rule entryRuleStakeholder
entryRuleStakeholder returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getStakeholderRule()); }
	 iv_ruleStakeholder=ruleStakeholder 
	 { $current=$iv_ruleStakeholder.current; } 
	 EOF 
;

// Rule Stakeholder
ruleStakeholder returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='stakeholder' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getStakeholderAccess().getStakeholderKeyword_0());
    }
(
(
		lv_name_1_0=RULE_ID
		{
			newLeafNode(lv_name_1_0, grammarAccess.getStakeholderAccess().getNameIDTerminalRuleCall_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getStakeholderRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"ID");
	    }

)
)(	otherlv_2='[' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getStakeholderAccess().getLeftSquareBracketKeyword_2_0());
    }
(

(
	{ 
	  getUnorderedGroupHelper().enter(grammarAccess.getStakeholderAccess().getUnorderedGroup_2_1());
	}
	(
		(

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getStakeholderAccess().getUnorderedGroup_2_1(), 0)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getStakeholderAccess().getUnorderedGroup_2_1(), 0);
	 				}
					({true}?=>(	otherlv_4='title' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getStakeholderAccess().getTitleKeyword_2_1_0_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getStakeholderAccess().getTitleValueStringParserRuleCall_2_1_0_1_0()); 
	    }
		lv_title_5_0=ruleValueString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getStakeholderRule());
	        }
       		set(
       			$current, 
       			"title",
        		lv_title_5_0, 
        		"ValueString");
	        afterParserOrEnumRuleCall();
	    }

)
)))
					{ 
	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStakeholderAccess().getUnorderedGroup_2_1());
	 				}
 				)
			)  |

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getStakeholderAccess().getUnorderedGroup_2_1(), 1)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getStakeholderAccess().getUnorderedGroup_2_1(), 1);
	 				}
					({true}?=>(	otherlv_6='description' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getStakeholderAccess().getDescriptionKeyword_2_1_1_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getStakeholderAccess().getDescriptionValueStringParserRuleCall_2_1_1_1_0()); 
	    }
		lv_description_7_0=ruleValueString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getStakeholderRule());
	        }
       		set(
       			$current, 
       			"description",
        		lv_description_7_0, 
        		"ValueString");
	        afterParserOrEnumRuleCall();
	    }

)
)))
					{ 
	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStakeholderAccess().getUnorderedGroup_2_1());
	 				}
 				)
			)  |

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getStakeholderAccess().getUnorderedGroup_2_1(), 2)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getStakeholderAccess().getUnorderedGroup_2_1(), 2);
	 				}
					({true}?=>(	otherlv_8='role' 
    {
    	newLeafNode(otherlv_8, grammarAccess.getStakeholderAccess().getRoleKeyword_2_1_2_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getStakeholderAccess().getRoleValueStringParserRuleCall_2_1_2_1_0()); 
	    }
		lv_role_9_0=ruleValueString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getStakeholderRule());
	        }
       		set(
       			$current, 
       			"role",
        		lv_role_9_0, 
        		"ValueString");
	        afterParserOrEnumRuleCall();
	    }

)
)))
					{ 
	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStakeholderAccess().getUnorderedGroup_2_1());
	 				}
 				)
			)  |

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getStakeholderAccess().getUnorderedGroup_2_1(), 3)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getStakeholderAccess().getUnorderedGroup_2_1(), 3);
	 				}
					({true}?=>(	otherlv_10='email' 
    {
    	newLeafNode(otherlv_10, grammarAccess.getStakeholderAccess().getEmailKeyword_2_1_3_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getStakeholderAccess().getEmailValueStringParserRuleCall_2_1_3_1_0()); 
	    }
		lv_email_11_0=ruleValueString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getStakeholderRule());
	        }
       		set(
       			$current, 
       			"email",
        		lv_email_11_0, 
        		"ValueString");
	        afterParserOrEnumRuleCall();
	    }

)
)))
					{ 
	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStakeholderAccess().getUnorderedGroup_2_1());
	 				}
 				)
			)  |

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getStakeholderAccess().getUnorderedGroup_2_1(), 4)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getStakeholderAccess().getUnorderedGroup_2_1(), 4);
	 				}
					({true}?=>(	otherlv_12='phone' 
    {
    	newLeafNode(otherlv_12, grammarAccess.getStakeholderAccess().getPhoneKeyword_2_1_4_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getStakeholderAccess().getPhoneValueStringParserRuleCall_2_1_4_1_0()); 
	    }
		lv_phone_13_0=ruleValueString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getStakeholderRule());
	        }
       		set(
       			$current, 
       			"phone",
        		lv_phone_13_0, 
        		"ValueString");
	        afterParserOrEnumRuleCall();
	    }

)
)))
					{ 
	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStakeholderAccess().getUnorderedGroup_2_1());
	 				}
 				)
			)  

		)*	
	)
)
	{ 
	  getUnorderedGroupHelper().leave(grammarAccess.getStakeholderAccess().getUnorderedGroup_2_1());
	}

)	otherlv_14=']' 
    {
    	newLeafNode(otherlv_14, grammarAccess.getStakeholderAccess().getRightSquareBracketKeyword_2_2());
    }
)?)
;







// Entry rule entryRuleDescription
entryRuleDescription returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getDescriptionRule()); }
	 iv_ruleDescription=ruleDescription 
	 { $current=$iv_ruleDescription.current; } 
	 EOF 
;

// Rule Description
ruleDescription returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		{ 
	        newCompositeNode(grammarAccess.getDescriptionAccess().getDescriptionDescriptionElementParserRuleCall_0()); 
	    }
		lv_description_0_0=ruleDescriptionElement		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getDescriptionRule());
	        }
       		add(
       			$current, 
       			"description",
        		lv_description_0_0, 
        		"DescriptionElement");
	        afterParserOrEnumRuleCall();
	    }

)
)+
;





// Entry rule entryRuleDescriptionElement
entryRuleDescriptionElement returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getDescriptionElementRule()); }
	 iv_ruleDescriptionElement=ruleDescriptionElement 
	 { $current=$iv_ruleDescriptionElement.current; } 
	 EOF 
;

// Rule DescriptionElement
ruleDescriptionElement returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		lv_text_0_0=RULE_STRING
		{
			newLeafNode(lv_text_0_0, grammarAccess.getDescriptionElementAccess().getTextSTRINGTerminalRuleCall_0_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getDescriptionElementRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"text",
        		lv_text_0_0, 
        		"STRING");
	    }

)
)
    |(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getDescriptionElementRule());
	        }
        }
	otherlv_1=RULE_ID
	{
		newLeafNode(otherlv_1, grammarAccess.getDescriptionElementAccess().getRefEObjectCrossReference_1_0()); 
	}

)
))
;





// Entry rule entryRuleReferencePath
entryRuleReferencePath returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getReferencePathRule()); }
	 iv_ruleReferencePath=ruleReferencePath 
	 { $current=$iv_ruleReferencePath.current; } 
	 EOF 
;

// Rule ReferencePath
ruleReferencePath returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getReferencePathRule());
	        }
        }
	otherlv_0=RULE_ID
	{
		newLeafNode(otherlv_0, grammarAccess.getReferencePathAccess().getRefEObjectCrossReference_0_0()); 
	}

)
)(	otherlv_1='.' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getReferencePathAccess().getFullStopKeyword_1_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getReferencePathAccess().getSubpathReferencePathParserRuleCall_1_1_0()); 
	    }
		lv_subpath_2_0=ruleReferencePath		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getReferencePathRule());
	        }
       		set(
       			$current, 
       			"subpath",
        		lv_subpath_2_0, 
        		"ReferencePath");
	        afterParserOrEnumRuleCall();
	    }

)
)))
;







// Entry rule entryRuleValueString
entryRuleValueString returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getValueStringRule()); } 
	 iv_ruleValueString=ruleValueString 
	 { $current=$iv_ruleValueString.current.getText(); }  
	 EOF 
;

// Rule ValueString
ruleValueString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
    this_STRING_0=RULE_STRING    {
		$current.merge(this_STRING_0);
    }

    { 
    newLeafNode(this_STRING_0, grammarAccess.getValueStringAccess().getSTRINGTerminalRuleCall()); 
    }

    ;





// Entry rule entryRuleQualifiedName
entryRuleQualifiedName returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getQualifiedNameRule()); } 
	 iv_ruleQualifiedName=ruleQualifiedName 
	 { $current=$iv_ruleQualifiedName.current.getText(); }  
	 EOF 
;

// Rule QualifiedName
ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(    this_ID_0=RULE_ID    {
		$current.merge(this_ID_0);
    }

    { 
    newLeafNode(this_ID_0, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); 
    }
(
	kw='.' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 
    }
    this_ID_2=RULE_ID    {
		$current.merge(this_ID_2);
    }

    { 
    newLeafNode(this_ID_2, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); 
    }
)*)
    ;





// Entry rule entryRuleQualifiedNameWithWildcard
entryRuleQualifiedNameWithWildcard returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getQualifiedNameWithWildcardRule()); } 
	 iv_ruleQualifiedNameWithWildcard=ruleQualifiedNameWithWildcard 
	 { $current=$iv_ruleQualifiedNameWithWildcard.current.getText(); }  
	 EOF 
;

// Rule QualifiedNameWithWildcard
ruleQualifiedNameWithWildcard returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getQualifiedNameWithWildcardAccess().getQualifiedNameParserRuleCall_0()); 
    }
    this_QualifiedName_0=ruleQualifiedName    {
		$current.merge(this_QualifiedName_0);
    }

    { 
        afterParserOrEnumRuleCall();
    }
(
	kw='.*' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getQualifiedNameWithWildcardAccess().getFullStopAsteriskKeyword_1()); 
    }
)?)
    ;









RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;


