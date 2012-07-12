package de.graphioli.algorithms;

import de.graphioli.model.Edge;
import de.graphioli.model.Graph;
import de.graphioli.model.VisualVertex;

/**
 * This class checks if a newly added edge makes the drawing of the graph
 * unplanar.
 * 
 * @author Team Graphioli
 */
public final class PlanarityCheck {

	/**
	 * Private empty constructor, to ensure that no instance is being created.
	 */
	private PlanarityCheck() {
	}

	/**
	 * This method checks for all the {@link Edge}s in the {@link Graph} if they
	 * intersect with a new new {@link Edge}.
	 * 
	 * @param graph
	 *            The {@link Graph} whose drawing is checked for planarity
	 * @param newEdge
	 *            The new {@link Edge}
	 * @return <code>true</code> if the {@link Graph} with the new {@link Edge}
	 *         is still planar drawn, <code>false</code> if new {@link Edge}
	 *         intersects with another one
	 */
	public static boolean performAlgorithm(Graph graph, Edge newEdge) {
		for (Edge edge : graph.getEdges()) {
			if (intersectionBetween(edge, newEdge)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method checks if the given {@link Edge}s intersect.
	 * 
	 * @param edgeA
	 *            the one edge to check
	 * @param edgeB
	 *            the other edge to check
	 * @return <code>true</code> if the edges intersect, <code>false</code> if
	 *         not
	 */
	private static boolean intersectionBetween(Edge edgeA, Edge edgeB) {

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

		// Numerators
		double nx = (xA1 * yA2 - yA1 * xA2) * (xB1 - xB2) - (xA1 - xA2) * (xB1 * yB2 - yB1 * xB2);
//		double ny = (xA1 * yA2 - yA1 * xA2) * (yB1 - yB2) - (yA1 - yA2) * (xB1 * yB2 - yB1 * xB2);

		// Denominator
		double d = (xA1 - xA2) * (yB1 - yB2) - (yA1 - yA2) * (xB1 - xB2);

		if (d == 0) {
			// edges are parallel
			return false;
		}

		// Coordinates of point of intersection
		double x = nx / d;
//		double y = ny / d;

		if (xA1 < xA2) {
			if (xB1 < xB2) {
				if (xA1 < x && xB1 < x && x < xA2 && x < xB2) {
					return true;
				}
			} else {
				if (xA1 < x && xB2 < x && x < xA2 && x < xB1) {
					return true;
				}
			}
		} else {
			if (xB1 < xB2) {
				if (xA2 < x && xB1 < x && x < xA1 && x < xB2) {
					return true;
				}
			} else {
				if (xA2 < x && xB2 < x && x < xA1 && x < xB1) {
					return true;
				}
			}
		}
		return false;
		// If point of intersection is in the line segments (edges)
//		return (x - xA1) / (xA2 - xA1) < 1
//				&& (x - xB1) / (xB2 - xB1) < 1
//				&& (y - yA1) / (yA2 - yA1) < 1
//				&& (y - yB1) / (yB2 - yB1) < 1;
	}
}
