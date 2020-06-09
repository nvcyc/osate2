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
package org.osate.ge.internal.businessObjectHandlers;

import java.util.Optional;

import org.osate.aadl2.FlowKind;
import org.osate.aadl2.instance.FlowSpecificationInstance;
import org.osate.ge.BusinessObjectContext;
import org.osate.ge.GraphicalConfiguration;
import org.osate.ge.GraphicalConfigurationBuilder;
import org.osate.ge.businessObjectHandlers.BusinessObjectHandler;
import org.osate.ge.businessObjectHandlers.GetGraphicalConfigurationContext;
import org.osate.ge.businessObjectHandlers.IsApplicableContext;
import org.osate.ge.graphics.Color;
import org.osate.ge.graphics.Style;
import org.osate.ge.graphics.StyleBuilder;
import org.osate.ge.internal.util.AadlHelper;
import org.osate.ge.internal.util.AadlInheritanceUtil;
import org.osate.ge.query.StandaloneQuery;
import org.osate.ge.query.Supplier;
import org.osate.ge.services.QueryService;

public class FlowPathSpecificationInstanceHandler extends FlowSpecificationInstanceHandler
implements BusinessObjectHandler {
	private static final Supplier<FlowSpecificationInstance, Object[]> getPathToFlowSpecificationInstanceSource = (
			fsi) -> AadlHelper.getPathToBusinessObject(fsi.getComponentInstance(), fsi.getSource());
			private static final StandaloneQuery srcQuery = StandaloneQuery.create((rootQuery) -> rootQuery.parent()
					.descendantsByBusinessObjectsRelativeReference(getPathToFlowSpecificationInstanceSource).first());
			private static final StandaloneQuery partialSrcQuery = StandaloneQuery.create((rootQuery) -> rootQuery.parent()
					.descendantsByBusinessObjectsRelativeReference(getPathToFlowSpecificationInstanceSource, 1).first());
			private static final Supplier<FlowSpecificationInstance, Object[]> getPathToFlowSpecificationInstanceDestination = (
					fsi) -> AadlHelper.getPathToBusinessObject(fsi.getComponentInstance(), fsi.getDestination());
					private static final StandaloneQuery dstQuery = StandaloneQuery.create((rootQuery) -> rootQuery.parent()
							.descendantsByBusinessObjectsRelativeReference(getPathToFlowSpecificationInstanceDestination).first());
					private static final StandaloneQuery partialDstQuery = StandaloneQuery.create((rootQuery) -> rootQuery.parent()
							.descendantsByBusinessObjectsRelativeReference(getPathToFlowSpecificationInstanceDestination, 1).first());

					@Override
					public boolean isApplicable(final IsApplicableContext ctx) {
						return ctx.getBusinessObject(FlowSpecificationInstance.class)
								.filter(fsi -> fsi.getFlowSpecification().getKind() == FlowKind.PATH).isPresent();
					}

					@Override
					public Optional<GraphicalConfiguration> getGraphicalConfiguration(final GetGraphicalConfigurationContext ctx) {
						final BusinessObjectContext boc = ctx.getBusinessObjectContext();
						final FlowSpecificationInstance fsi = boc.getBusinessObject(FlowSpecificationInstance.class).get();
						final QueryService queryService = ctx.getQueryService();

						BusinessObjectContext src = queryService.getFirstResult(srcQuery, boc);
						BusinessObjectContext dst = queryService.getFirstResult(dstQuery, boc);

						boolean partial = false;

						if (src == null) {
							src = queryService.getFirstResult(partialSrcQuery, boc);
							partial = true;
						}

						if (dst == null) {
							dst = queryService.getFirstResult(partialDstQuery, boc);
							partial = true;
						}

						final StyleBuilder sb = StyleBuilder
								.create(AadlInheritanceUtil.isInherited(boc) ? Styles.INHERITED_ELEMENT : Style.EMPTY).lineWidth(4.0)
								.backgroundColor(Color.BLACK);

						if (partial) {
							sb.dotted();
						}

						return Optional.of(GraphicalConfigurationBuilder.create()
								.graphic(AadlGraphics.getFlowSpecificationGraphic(fsi.getFlowSpecification())).style(sb.build())
								.source(src).destination(dst).build());
					}
}
