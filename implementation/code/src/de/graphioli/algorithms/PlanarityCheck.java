package de.graphioli.algorithms;

import de.graphioli.model.*;

public final class PlanarityCheck {
	
	public static boolean performAlgorithm(Graph graph, Edge newEdge) {
		for (Edge edge : graph.getEdges()) {
			checkForIntersection(edge, newEdge);
		}
		return true;
	}
	
	private static boolean checkForIntersection(Edge edgeA, Edge edgeB) {
		
		VisualVertex vertexA1 = (VisualVertex) edgeA.getOriginVertex();
		VisualVertex vertexA2 = (VisualVertex) edgeA.getTargetVertex();
		VisualVertex vertexB1 = (VisualVertex) edgeB.getOriginVertex();
		VisualVertex vertexB2 = (VisualVertex) edgeB.getTargetVertex();
		
		double xA1 = vertexA1.getGridPoint().getPositionX();
		double yA1 = vertexA1.getGridPoint().getPositionY();
		double xA2 = vertexA2.getGridPoint().getPositionX();
		double yA2 = vertexA2.getGridPoint().getPositionY();
		double xB1 = vertexB1.getGridPoint().getPositionX();
		double yB1 = vertexB1.getGridPoint().getPositionY();
		double xB2 = vertexB2.getGridPoint().getPositionX();
		double yB2 = vertexB2.getGridPoint().getPositionY();
		
		// Numerator
		double nx = (xA1 * yA2 - yA1 * xA2) * (xB1 - xB2) - (xA1 - xA2) * (xB1 * yB2 - yB1 * xB2);
		double ny = (xA1 * yA2 - yA1 * xA2) * (yB1 - yB2) - (yA1 - yA2) * (xB1 * yB2 - yB1 * xB2);
		
		// Denominator
		double d = (xA1 - xA2) * (yB1 - yB2) - (yA1 - yA2) * (xB1 - xB2);
		
		if (d == 0) return false;
		
		// Koordinaten des Schnittpunktes
		double x = nx/d;
		double y = ny/d;
		
		if ((x - xA1) / (xA2 - xA1) > 1 || (x - xB1) / (xB2 - xB1) > 1 || 
				(y - yA1) / (yA2 - yA1) > 1 || (y - yB1) / (yB2 - yB1) > 1) {
			return false;
		} else {
			return true;
		}
	}
}
