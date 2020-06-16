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
package org.osate.ge.errormodel.businessObjectHandlers;

import java.util.Optional;

import javax.inject.Named;

import org.osate.ge.BusinessObjectContext;
import org.osate.ge.GraphicalConfiguration;
import org.osate.ge.GraphicalConfigurationBuilder;
import org.osate.ge.businessObjectHandlers.BusinessObjectHandler;
import org.osate.ge.businessObjectHandlers.CanDeleteContext;
import org.osate.ge.businessObjectHandlers.CustomDeleteContext;
import org.osate.ge.businessObjectHandlers.CustomDeleter;
import org.osate.ge.businessObjectHandlers.GetGraphicalConfigurationContext;
import org.osate.ge.businessObjectHandlers.GetNameContext;
import org.osate.ge.businessObjectHandlers.GetNameForDiagramContext;
import org.osate.ge.businessObjectHandlers.IsApplicableContext;
import org.osate.ge.di.Names;
import org.osate.ge.errormodel.model.ErrorTypeExtension;
import org.osate.ge.errormodel.model.ErrorTypeLibrary;
import org.osate.ge.graphics.ArrowBuilder;
import org.osate.ge.graphics.ConnectionBuilder;
import org.osate.ge.graphics.Graphic;
import org.osate.ge.graphics.LabelBuilder;
import org.osate.ge.query.StandaloneQuery;
import org.osate.ge.services.QueryService;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorModelLibrary;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorType;

public class ErrorTypeExtensionHandler implements BusinessObjectHandler, CustomDeleter {
	private static final Graphic labelGraphic = LabelBuilder.create().build();
	private static final Graphic connectionGraphic = ConnectionBuilder.create()
			.destinationTerminator(ArrowBuilder.create().open().build()).build();

	private static StandaloneQuery dstDifferentPackageQuery = StandaloneQuery
			.create((rootQuery) -> rootQuery.ancestor(4).descendantsByBusinessObjectsRelativeReference(
					ete -> getBusinessObjectPath(((ErrorTypeExtension) ete).getSupertype())));

	@Override
	public boolean isApplicable(final IsApplicableContext ctx) {
		return ctx.getBusinessObject(ErrorTypeExtension.class).isPresent();
	}

	@Override
	public boolean canDelete(final CanDeleteContext ctx) {
		return true;
	}

	@Override
	public Optional<GraphicalConfiguration> getGraphicalConfiguration(final GetGraphicalConfigurationContext ctx) {
		final BusinessObjectContext boc = ctx.getBusinessObjectContext();
		final QueryService queryService = ctx.getQueryService();
		final BusinessObjectContext destination = getDestination(boc, queryService);

		if (destination == null) {
			return Optional.of(GraphicalConfigurationBuilder.create().graphic(labelGraphic).decoration().build());
		} else {
			return Optional.of(GraphicalConfigurationBuilder.create().graphic(connectionGraphic).source(boc.getParent())
					.destination(destination).build());
		}
	}

	@Override
	public String getName(final GetNameContext ctx) {
		return "Extension";
	}

	@Override
	public String getNameForDiagram(final GetNameForDiagramContext ctx) {
		return ctx.getBusinessObjectContext().getBusinessObject(ErrorTypeExtension.class).map(ext -> {
			// Don't show the name when displaying as a connection
			if (getDestination(ctx.getBusinessObjectContext(), ctx.getQueryService()) != null) {
				return null;
			}

			// If elements are in the same package, only show the name instead of the qualified name
			final String superName;
			if (ext.getSupertype().getElementRoot() == ext.getSubtype().getElementRoot()) {
				superName = ext.getSupertype().getName();
			} else {
				superName = ext.getSupertype().getQualifiedName();
			}

			return "Extends: " + superName;
		}).orElse("");
	}

	private BusinessObjectContext getDestination(final @Named(Names.BUSINESS_OBJECT_CONTEXT) BusinessObjectContext boc,
			final QueryService queryService) {
		final BusinessObjectContext packageParent = boc.getAncestor(4);
		if (packageParent == null) {
			// Not supported. Will need to be supported if error type libraries are supported as a diagram context
			return null;
		} else {
			return queryService.getFirstResult(dstDifferentPackageQuery, boc);
		}
	}

	@Override
	public void delete(final CustomDeleteContext ctx) {
		ctx.getContainerBusinessObject(ErrorType.class).ifPresent(subtype -> {
			subtype.setSuperType(null);
		});
	}

	private static Object[] getBusinessObjectPath(final ErrorType et) {
		if (!(et.eContainer() instanceof ErrorModelLibrary)) {
			return null;
		}

		return new Object[] { et.getElementRoot(), new ErrorTypeLibrary((ErrorModelLibrary) et.eContainer()), et };
	}
}
