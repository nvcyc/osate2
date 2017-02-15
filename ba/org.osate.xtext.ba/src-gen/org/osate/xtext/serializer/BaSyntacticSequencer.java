/*
 * generated by Xtext
 */
package org.osate.xtext.serializer;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AlternativeAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.GroupAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;
import org.osate.xtext.services.BaGrammarAccess;

@SuppressWarnings("all")
public class BaSyntacticSequencer extends AbstractSyntacticSequencer {

	protected BaGrammarAccess grammarAccess;
	protected AbstractElementAlias match_BehaviorCondition_ExternalConditionParserRuleCall_2_or_InternalConditionParserRuleCall_3;
	protected AbstractElementAlias match_BehaviorVariableSet___LeftCurlyBracketKeyword_4_0_RightCurlyBracketKeyword_4_1__q;
	protected AbstractElementAlias match_CommunicationAction_AsteriskExclamationMarkGreaterThanSignKeyword_2_1_or_AsteriskExclamationMarkLessThanSignKeyword_2_0;
	protected AbstractElementAlias match_CommunicationAction_ExclamationMarkGreaterThanSignKeyword_1_1_3_or_ExclamationMarkKeyword_1_1_4_0_or_ExclamationMarkKeyword_1_1_6_0_or_ExclamationMarkLessThanSignKeyword_1_1_2_or_GreaterThanSignGreaterThanSignKeyword_1_1_1_or_QuestionMarkKeyword_1_1_0_0_or_QuestionMarkKeyword_1_1_5_0;
	protected AbstractElementAlias match_ExecuteCondition_TimeoutExecuteConditionParserRuleCall_1_q;
	protected AbstractElementAlias match_PropertyName___FullStopKeyword_1_1_0___Lower_boundKeyword_1_1_1_1_or_Upper_boundKeyword_1_1_1_0____q;
	protected AbstractElementAlias match_PropertyReference_IDTerminalRuleCall_0_0_1_q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (BaGrammarAccess) access;
		match_BehaviorCondition_ExternalConditionParserRuleCall_2_or_InternalConditionParserRuleCall_3 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getBehaviorConditionAccess().getExternalConditionParserRuleCall_2()), new TokenAlias(false, false, grammarAccess.getBehaviorConditionAccess().getInternalConditionParserRuleCall_3()));
		match_BehaviorVariableSet___LeftCurlyBracketKeyword_4_0_RightCurlyBracketKeyword_4_1__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getBehaviorVariableSetAccess().getLeftCurlyBracketKeyword_4_0()), new TokenAlias(false, false, grammarAccess.getBehaviorVariableSetAccess().getRightCurlyBracketKeyword_4_1()));
		match_CommunicationAction_AsteriskExclamationMarkGreaterThanSignKeyword_2_1_or_AsteriskExclamationMarkLessThanSignKeyword_2_0 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getCommunicationActionAccess().getAsteriskExclamationMarkGreaterThanSignKeyword_2_1()), new TokenAlias(false, false, grammarAccess.getCommunicationActionAccess().getAsteriskExclamationMarkLessThanSignKeyword_2_0()));
		match_CommunicationAction_ExclamationMarkGreaterThanSignKeyword_1_1_3_or_ExclamationMarkKeyword_1_1_4_0_or_ExclamationMarkKeyword_1_1_6_0_or_ExclamationMarkLessThanSignKeyword_1_1_2_or_GreaterThanSignGreaterThanSignKeyword_1_1_1_or_QuestionMarkKeyword_1_1_0_0_or_QuestionMarkKeyword_1_1_5_0 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getCommunicationActionAccess().getExclamationMarkGreaterThanSignKeyword_1_1_3()), new TokenAlias(false, false, grammarAccess.getCommunicationActionAccess().getExclamationMarkKeyword_1_1_4_0()), new TokenAlias(false, false, grammarAccess.getCommunicationActionAccess().getExclamationMarkKeyword_1_1_6_0()), new TokenAlias(false, false, grammarAccess.getCommunicationActionAccess().getExclamationMarkLessThanSignKeyword_1_1_2()), new TokenAlias(false, false, grammarAccess.getCommunicationActionAccess().getGreaterThanSignGreaterThanSignKeyword_1_1_1()), new TokenAlias(false, false, grammarAccess.getCommunicationActionAccess().getQuestionMarkKeyword_1_1_0_0()), new TokenAlias(false, false, grammarAccess.getCommunicationActionAccess().getQuestionMarkKeyword_1_1_5_0()));
		match_ExecuteCondition_TimeoutExecuteConditionParserRuleCall_1_q = new TokenAlias(false, true, grammarAccess.getExecuteConditionAccess().getTimeoutExecuteConditionParserRuleCall_1());
		match_PropertyName___FullStopKeyword_1_1_0___Lower_boundKeyword_1_1_1_1_or_Upper_boundKeyword_1_1_1_0____q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getPropertyNameAccess().getFullStopKeyword_1_1_0()), new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getPropertyNameAccess().getLower_boundKeyword_1_1_1_1()), new TokenAlias(false, false, grammarAccess.getPropertyNameAccess().getUpper_boundKeyword_1_1_1_0())));
		match_PropertyReference_IDTerminalRuleCall_0_0_1_q = new TokenAlias(false, true, grammarAccess.getPropertyReferenceAccess().getIDTerminalRuleCall_0_0_1());
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if(ruleCall.getRule() == grammarAccess.getExternalConditionRule())
			return getExternalConditionToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getIDRule())
			return getIDToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getInternalConditionRule())
			return getInternalConditionToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getTimeoutExecuteConditionRule())
			return getTimeoutExecuteConditionToken(semanticObject, ruleCall, node);
		return "";
	}
	
	/**
	 * ExternalCondition:
	 * 	'TBD_ExternalCondition'
	 * ;
	 */
	protected String getExternalConditionToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "TBD_ExternalCondition";
	}
	
	/**
	 * terminal ID:	('a'..'z'
	 *         |'A'..'Z'
	 *         ) ( ('_')? ('a'..'z'
	 *         |'A'..'Z'
	 *         |'0'..'9'))*;
	 */
	protected String getIDToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "";
	}
	
	/**
	 * InternalCondition:
	 * 	'TBD_InternalCondition'
	 * ;
	 */
	protected String getInternalConditionToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "TBD_InternalCondition";
	}
	
	/**
	 * TimeoutExecuteCondition:
	 * 	'TBD_TimeoutExecuteCondition'
	 * ;
	 */
	protected String getTimeoutExecuteConditionToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "TBD_TimeoutExecuteCondition";
	}
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if(match_BehaviorCondition_ExternalConditionParserRuleCall_2_or_InternalConditionParserRuleCall_3.equals(syntax))
				emit_BehaviorCondition_ExternalConditionParserRuleCall_2_or_InternalConditionParserRuleCall_3(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_BehaviorVariableSet___LeftCurlyBracketKeyword_4_0_RightCurlyBracketKeyword_4_1__q.equals(syntax))
				emit_BehaviorVariableSet___LeftCurlyBracketKeyword_4_0_RightCurlyBracketKeyword_4_1__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_CommunicationAction_AsteriskExclamationMarkGreaterThanSignKeyword_2_1_or_AsteriskExclamationMarkLessThanSignKeyword_2_0.equals(syntax))
				emit_CommunicationAction_AsteriskExclamationMarkGreaterThanSignKeyword_2_1_or_AsteriskExclamationMarkLessThanSignKeyword_2_0(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_CommunicationAction_ExclamationMarkGreaterThanSignKeyword_1_1_3_or_ExclamationMarkKeyword_1_1_4_0_or_ExclamationMarkKeyword_1_1_6_0_or_ExclamationMarkLessThanSignKeyword_1_1_2_or_GreaterThanSignGreaterThanSignKeyword_1_1_1_or_QuestionMarkKeyword_1_1_0_0_or_QuestionMarkKeyword_1_1_5_0.equals(syntax))
				emit_CommunicationAction_ExclamationMarkGreaterThanSignKeyword_1_1_3_or_ExclamationMarkKeyword_1_1_4_0_or_ExclamationMarkKeyword_1_1_6_0_or_ExclamationMarkLessThanSignKeyword_1_1_2_or_GreaterThanSignGreaterThanSignKeyword_1_1_1_or_QuestionMarkKeyword_1_1_0_0_or_QuestionMarkKeyword_1_1_5_0(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_ExecuteCondition_TimeoutExecuteConditionParserRuleCall_1_q.equals(syntax))
				emit_ExecuteCondition_TimeoutExecuteConditionParserRuleCall_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_PropertyName___FullStopKeyword_1_1_0___Lower_boundKeyword_1_1_1_1_or_Upper_boundKeyword_1_1_1_0____q.equals(syntax))
				emit_PropertyName___FullStopKeyword_1_1_0___Lower_boundKeyword_1_1_1_1_or_Upper_boundKeyword_1_1_1_0____q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_PropertyReference_IDTerminalRuleCall_0_0_1_q.equals(syntax))
				emit_PropertyReference_IDTerminalRuleCall_0_0_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Ambiguous syntax:
	 *     ExternalCondition | InternalCondition
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) (ambiguity) (rule start)
	 */
	protected void emit_BehaviorCondition_ExternalConditionParserRuleCall_2_or_InternalConditionParserRuleCall_3(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('{' '}')?
	 *
	 * This ambiguous syntax occurs at:
	 *     dataClassifier=[DataClassifier|ID] (ambiguity) ';' (rule end)
	 */
	protected void emit_BehaviorVariableSet___LeftCurlyBracketKeyword_4_0_RightCurlyBracketKeyword_4_1__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     '*!<' | '*!>'
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) (ambiguity) (rule start)
	 */
	protected void emit_CommunicationAction_AsteriskExclamationMarkGreaterThanSignKeyword_2_1_or_AsteriskExclamationMarkLessThanSignKeyword_2_0(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     (
	  *         '?' | 
	  *         '>>' | 
	  *         '!<' | 
	  *         '!>' | 
	  *         '!' | 
	  *         '?' | 
	  *         '!'
	  *     )
	 *
	 * This ambiguous syntax occurs at:
	 *     reference=Reference (ambiguity) (rule end)
	 */
	protected void emit_CommunicationAction_ExclamationMarkGreaterThanSignKeyword_1_1_3_or_ExclamationMarkKeyword_1_1_4_0_or_ExclamationMarkKeyword_1_1_6_0_or_ExclamationMarkLessThanSignKeyword_1_1_2_or_GreaterThanSignGreaterThanSignKeyword_1_1_1_or_QuestionMarkKeyword_1_1_0_0_or_QuestionMarkKeyword_1_1_5_0(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     TimeoutExecuteCondition?
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) (ambiguity) (rule start)
	 */
	protected void emit_ExecuteCondition_TimeoutExecuteConditionParserRuleCall_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('.' ('upper_bound' | 'lower_bound'))?
	 *
	 * This ambiguous syntax occurs at:
	 *     propertyName=Identifier (ambiguity) (rule end)
	 */
	protected void emit_PropertyName___FullStopKeyword_1_1_0___Lower_boundKeyword_1_1_1_1_or_Upper_boundKeyword_1_1_1_0____q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ID?
	 *
	 * This ambiguous syntax occurs at:
	 *     propertySet?='#' (ambiguity) propertyNames+=PropertyName
	 */
	protected void emit_PropertyReference_IDTerminalRuleCall_0_0_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
