package org.osate.ge.internal.diagram.runtime;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.osate.ge.diagram.Diagram;
import org.osate.ge.internal.DockArea;

/**
 * Class to help read and write the native JSON diagram format used by the editor.
 *
 */
public class DiagramSerialization {
	private static Comparator<DiagramElement> elementComparator = new Comparator<DiagramElement>() {
		@Override
		public int compare(final DiagramElement e1, final DiagramElement e2) {
			return e1.getRelativeReference().compareTo(e2.getRelativeReference());
		}
	};
	
	public static void read(final AgeDiagram ageDiagram,
			final URI uri) {
		Objects.requireNonNull(uri, "uri must not be null");
		
		// Load the resource
		final ResourceSet rs = new ResourceSetImpl();
		final Resource resource = rs.createResource(uri);
		try {
			resource.load(Collections.emptyMap());
			if(resource.getContents().size() == 0 || !(resource.getContents().get(0) instanceof org.osate.ge.diagram.Diagram)) {
				throw new RuntimeException("Unable to load diagram.");
			}
			
			final org.osate.ge.diagram.Diagram mmDiagram = (org.osate.ge.diagram.Diagram)resource.getContents().get(0);
			
			// Read the diagram configuration
			final DiagramConfigurationBuilder configBuilder = new DiagramConfigurationBuilder(ageDiagram.getConfiguration());

			if(mmDiagram.getConfig() != null) {
				final org.osate.ge.diagram.DiagramConfiguration mmDiagramConfig = mmDiagram.getConfig();
				configBuilder.setContextBoReference(convert(mmDiagramConfig.getContext()));
				
				final org.osate.ge.diagram.AadlPropertiesSet enabledAadlProperties = mmDiagramConfig.getEnabledAadlProperties();
				if(enabledAadlProperties != null) {
					for(final String enabledProperty : enabledAadlProperties.getProperty()) {
						configBuilder.addAadlProperty(enabledProperty);
					}
				}				
			}
			
			ageDiagram.setDiagramConfiguration(configBuilder.build());
			
			// Require a context business object
			if(ageDiagram.getConfiguration().getContextBoReference() == null) {
				throw new RuntimeException("Contextless diagrams are not supported");
			}
			
			//  Read elements
			ageDiagram.modify(new DiagramModifier() {
				@Override
				public void modify(final DiagramModification m) {
					readElements(m, ageDiagram, mmDiagram);
				}
			});
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static RelativeBusinessObjectReference convert(final org.osate.ge.diagram.RelativeBusinessObjectReference ref) {
		final String[] segs = toReferenceSegments(ref);
		return segs == null ? null : new RelativeBusinessObjectReference(segs);
	}
	
	public static CanonicalBusinessObjectReference convert(final org.osate.ge.diagram.CanonicalBusinessObjectReference ref) {
		final String[] segs = toReferenceSegments(ref);
		return segs == null ? null : new CanonicalBusinessObjectReference(segs);
	}
	
	private static String[] toReferenceSegments(final org.osate.ge.diagram.Reference ref) {
		return ref == null || ref.getSeg().size() == 0 ? null : ref.getSeg().toArray(new String[ref.getSeg().size()]);
	}
	
	private static void readElements(final DiagramModification m, 
			final DiagramNode container, 
			final org.osate.ge.diagram.DiagramNode mmContainer) {
		for(final org.osate.ge.diagram.DiagramElement mmElement : mmContainer.getElement()) {
			createElement(m, container, mmContainer, mmElement);
		}
	}	
	
	private static void createElement(final DiagramModification m, 
			final DiagramNode container, 
			final org.osate.ge.diagram.DiagramNode mmContainer,
			final org.osate.ge.diagram.DiagramElement mmChild) {
		final String[] refSegs = toReferenceSegments(mmChild.getBo());
		if(refSegs == null) {
			throw new RuntimeException("Invalid element. Business Object not specified");
		}
		
		final RelativeBusinessObjectReference relReference = new RelativeBusinessObjectReference(refSegs);
		final DiagramElement newElement = new DiagramElement(container, null, null, relReference, null);
		final String autoContentsFilterId = mmChild.getAutoContentsFilter();
		if(autoContentsFilterId != null) {
			final BuiltinContentsFilter autoContentsFilter = BuiltinContentsFilter.getById(autoContentsFilterId);
			if(autoContentsFilter != null) {
				newElement.setAutoContentsFilter(autoContentsFilter);
			}
		}
		newElement.setManual(mmChild.isManual());
		
		// Size and Position
		newElement.setPosition(convertPoint(mmChild.getPosition()));
		newElement.setSize(convertDimension(mmChild.getSize()));
		
		// Dock Area
		final String dockAreaId = mmChild.getDockArea();
		if(dockAreaId != null) {
			final DockArea dockArea = DockArea.getById(dockAreaId);
			if(dockArea != null) {
				newElement.setDockArea(dockArea);
			}
		}
		
		// Bendpoints
		final org.osate.ge.diagram.BendpointList mmBendpoints = mmChild.getBendpoints();
		if(mmBendpoints != null) {
			newElement.setBendpoints(mmBendpoints.getPoint().stream().map(DiagramSerialization::convertPoint).collect(Collectors.toList()));
		}
		
		// Primary Label Position (Only Supported for Connections)
		newElement.setConnectionPrimaryLabelPosition(convertPoint(mmChild.getPrimaryLabelPosition()));

		// Add the element
		m.addElement(newElement);		
		
		// Create children
		readElements(m, newElement, mmChild);
	}
	
	private static Point convertPoint(final org.osate.ge.diagram.Point mmPoint) {
		if(mmPoint == null) {
			return null;
		}
		
		return new Point(mmPoint.getX(), mmPoint.getY());
	}
	
	private static Dimension convertDimension(final org.osate.ge.diagram.Dimension mmDimension) {
		if(mmDimension == null) {
			return null;
		}
		
		return new Dimension(mmDimension.getWidth(), mmDimension.getHeight());
	}
	
	public static void write(final AgeDiagram diagram, final URI uri) {
		// Convert from the runtime format to the metamodel format which is stored
		final org.osate.ge.diagram.Diagram mmDiagram = new Diagram();
		final org.osate.ge.diagram.DiagramConfiguration mmConfig = new org.osate.ge.diagram.DiagramConfiguration();		
		mmDiagram.setConfig(mmConfig);
		
		// Populate the diagram configuration
		final DiagramConfiguration config = diagram.getConfiguration();
		mmConfig.setContext(config.getContextBoReference() == null ? null : config.getContextBoReference().toMetamodel());
			
		final org.osate.ge.diagram.AadlPropertiesSet enabledProperties = new org.osate.ge.diagram.AadlPropertiesSet();
		mmConfig.setEnabledAadlProperties(enabledProperties);
		for(final String enabledPropertyName : config.getEnabledAadlPropertyNames()) {
			enabledProperties.getProperty().add(enabledPropertyName);
		}
		
		convertElementsToMetamodel(mmDiagram, diagram.getDiagramElements());			

		// Save the resource
		final ResourceSet rs = new ResourceSetImpl();
		final Resource resource = rs.createResource(uri);
		resource.getContents().add(mmDiagram);
		try {
			resource.save(Collections.emptyMap());
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
		
	/**
	 *  Converts specified elements from runtime datastructure into a metamodel.
	 * @param mmContainer
	 * @param elements
	 */
	private static void convertElementsToMetamodel(final org.osate.ge.diagram.DiagramNode mmContainer, Collection<DiagramElement> elements) {
		// Filter out decorations which don't have applicable fields set. For example non-moveable decorators.
		// Sort elements to ensure a consistent ordering after serialization
		elements = elements.stream().filter(e -> !e.isDecoration() || e.hasPosition()).sorted(elementComparator).collect(Collectors.toList());

		if(elements.size() > 0) {
			for(final DiagramElement e : elements) {
				convertElementToMetamodel(mmContainer, e);
			}
		}
	}
	
	private static void convertElementToMetamodel(final org.osate.ge.diagram.DiagramNode mmContainer, final DiagramElement e) {
		// Write BO Reference
		final org.osate.ge.diagram.DiagramElement newElement = new org.osate.ge.diagram.DiagramElement();
		mmContainer.getElement().add(newElement);
		
		newElement.setBo(e.getRelativeReference() == null ? null : e.getRelativeReference().toMetamodel());
		if(e.getAutoContentsFilter() != null) {
			newElement.setAutoContentsFilter(e.getAutoContentsFilter().id());
		}
		
		if(e.isManual()) {
			newElement.setManual(true);
		}

		// Shape Specific
		if(e.hasPosition()) {
			newElement.setPosition(e.getPosition().toMetamodel());
		}
		
		if(e.hasSize()) {
			newElement.setSize(e.getSize().toMetamodel());
		}
		
		if(e.getDockArea() != null && e.getDockArea() != DockArea.GROUP) { // Don't serialize null or group dock areas
			newElement.setDockArea(e.getDockArea().id);
		}
		
		// Connection Specific
		if(e.getBendpoints().size() > 0) {
			final org.osate.ge.diagram.BendpointList mmBendpoints = new org.osate.ge.diagram.BendpointList();
			newElement.setBendpoints(mmBendpoints);
			
			for(final Point bendpoint : e.getBendpoints()) {
				mmBendpoints.getPoint().add(bendpoint.toMetamodel());
			}
		}

		if(e.getConnectionPrimaryLabelPosition() != null) {
			newElement.setPrimaryLabelPosition(e.getConnectionPrimaryLabelPosition().toMetamodel());
		}
		
		convertElementsToMetamodel(newElement, e.getDiagramElements());
	}
}
