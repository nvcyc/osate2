/*
 * generated by Xtext
 */
package org.osate.xtext.aadl2.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class Aadl2AntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("org/osate/xtext/aadl2/parser/antlr/internal/InternalAadl2Parser.tokens");
	}
}