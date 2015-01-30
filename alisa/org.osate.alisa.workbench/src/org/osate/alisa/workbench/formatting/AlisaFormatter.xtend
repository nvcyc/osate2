/*
 * generated by Xtext
 */
package org.osate.alisa.workbench.formatting

import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter
import org.eclipse.xtext.formatting.impl.FormattingConfig
import com.google.inject.Inject
import org.osate.alisa.workbench.services.AlisaGrammarAccess

// import com.google.inject.Inject;
// import org.osate.alisa.workbench.services.AlisaGrammarAccess
/**
 * This class contains custom formatting description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation.html#formatting
 * on how and when to use it 
 * 
 * Also see {@link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer} as an example
 */
class AlisaFormatter extends AbstractDeclarativeFormatter {

	@Inject extension AlisaGrammarAccess

	override protected void configureFormatting(FormattingConfig c) {
		c.setAutoLinewrap(120);
		c.setWrappedLineIndentation(2);
		c.setLinewrap(0, 1, 2).before(SL_COMMENTRule)
		c.setLinewrap(0, 1, 2).before(ML_COMMENTRule)
		c.setLinewrap(0, 1, 1).after(ML_COMMENTRule)
		for (pair : findKeywordPairs("[", "]")) {
			c.setIndentationIncrement().after(pair.first);
			c.setLinewrap().after(pair.first);
			c.setIndentationDecrement().before(pair.second);
			c.setLinewrap().before(pair.second);
		}
		c.setLinewrap().after(assuranceCaseConfigurationRule);
		c.setLinewrap().after(alisaWorkAreaRule);
		for (kw : findKeywords("constants", "description")) {
			c.setLinewrap().before(kw);
		}
	}
}
