/*
 * generated by Xtext
 */
package org.osate.expr.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.editor.hover.IEObjectHoverProvider;
import org.osate.expr.ui.editor.hover.html.ExprEObjectHoverProvider;

/**
 * Use this class to register components to be used within the IDE.
 */
public class ExprUiModule extends org.osate.expr.ui.AbstractExprUiModule {

	public ExprUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	public Class<? extends IEObjectHoverProvider> bindIEObjectHoverProvider() {
		return ExprEObjectHoverProvider.class;
	}

}