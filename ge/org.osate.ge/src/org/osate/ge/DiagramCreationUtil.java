/**
 * Copyright (c) 2004-2020 Carnegie Mellon University and others. (see Contributors file).
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
package org.osate.ge;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.PlatformUI;
import org.osate.ge.internal.services.DiagramService;
import org.osate.ge.internal.ui.util.EditorUtil;

/**
 * @noextend
 * @since 2.1
 */
public class DiagramCreationUtil {
	private DiagramCreationUtil() {
	}

	/**
	 * Create a diagram for the business object
	 * @param contextBo	the business object for which to create the diagram
	 */
	public static void createDiagram(final Object contextBo) {
		final DiagramService diagramService = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getService(DiagramService.class);
		final IFile file = diagramService.createDiagram(contextBo);
		if (file != null) {
			EditorUtil.openEditor(file, false);
		}
	}

	/**
	 * Open or create a diagram for the business object
	 * @param bo the business object for which to open/create the diagram
	 */
	public static void openOrCreateDiagram(final Object contextBo) {
		final DiagramService diagramService = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getService(DiagramService.class);
		diagramService.openOrCreateDiagramForBusinessObject(contextBo);
		return;
	}
}
