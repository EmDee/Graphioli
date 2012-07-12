package de.graphioli.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class represents the logical graph.
 * 
 * @author Team Graphioli
 */
public class Graph implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -8977195676667503454L;

	private static final Logger LOG = Logger.getLogger(Graph.class.getName());

	private final List<Vertex> vertexList;
	private final List<Edge> edgeList;

	/**
	 * Creates a new, empty {link Graph}.
	 */
	public Graph() {
		this.vertexList = new ArrayList<Vertex>();
		this.edgeList = new ArrayList<Edge>();
	}

	/**
	 * Adds the given {@link Edge} to this graph. An edge can only be added, if
	 * both of its vertices are already in the graph but the edge does not yet
	 * exist. Also the target and origin vertices must not be the same.
	 * 
	 * @param edge
	 *            the edge to add
	 * @return {@code true} if adding was successful.
	 */

	public boolean addEdge(Edge edge) {
		if (edge.getOriginVertex().equals(edge.getTargetVertex())) {
			return false;
		}

		if (!(this.vertexList.contains(edge.getOriginVertex()) && this.vertexList.contains(edge.getTargetVertex()))) {
			return false;
		}

		if (this.edgeList.contains(edge)) {
			return false;
		} else {
			this.edgeList.add(edge);
			edge.getOriginVertex().addOutgoingEdge(edge);
			edge.getTargetVertex().addIncomingEdge(edge);
			return true;
		}
	}

	/**
	 * Adds the given {@link Vertex} to this graph. An edge can only be added,
	 * if it is not contained in this graph already and its lists of outgoing
	 * and incoming edges are empty.
	 * 
	 * @param vertex
	 *            the vertex to add
	 * @return {@code true} if adding was successful.
	 */

	public boolean addVertex(Vertex vertex) {
		if (this.vertexList.contains(vertex)) {
			return false;
		}

		if (vertex.getIncomingEdges().size() == 0 && vertex.getOutgoingEdges().size() == 0) {
			this.vertexList.add(vertex);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the {@link Edge} between two given vertices.
	 * 
	 * @param origin
	 *            starting {@link Vertex}.
	 * @param target
	 *            end {@link Vertex}.
	 * @return the {@link Edge} between the two vertices, if one exists, else
	 *         <code>null</code>/
	 */
	public Edge getEdge(Vertex origin, Vertex target) {
		for (Edge edge : origin.getOutgoingEdges()) {
			if (edge.getTargetVertex().equals(target)) {
				return edge;
			}
		}
		return null;
	}

	/**
	 * @return the list of {@link Edge}s in this {@code Graph}.
	 */
	public List<Edge> getEdges() {
		return this.edgeList;
	}

	/**
	 * @return the list of vertices in this {@code Graph}.
	 */
	public List<Vertex> getVertices() {
		return this.vertexList;
	}

	/**
	 * Removes a {@link Edge} from this grapht.
	 * 
	 * @param edge
	 *            the edge to remove
	 * @return {@code true} if the edge was in this graph
	 */
	public boolean removeEdge(Edge edge) {
		int index = this.edgeList.indexOf(edge);

		if (index == -1) {
			// Edge not in list
			return false;
		} else {
			this.edgeList.remove(index);

			if (!edge.getOriginVertex().removeOutgoingEdge(edge) | !edge.getTargetVertex().removeIncomingEdge(edge)) {
				LOG.severe("Graph inconsistency in removeEdge method!");
			}
			return true;
		}
	}

	/**
	 * Removes a {@link Vertex} from this graph including all edges connected to
	 * it.
	 * 
	 * @param vertex
	 *            the vertex to remove
	 * @return {@code true} if the vertex was in this graph
	 */
	public boolean removeVertex(Vertex vertex) {
		int index = this.vertexList.indexOf(vertex);

		if (index == -1) {
			// Vertex not in list
			return false;
		} else {

			// Remove edges connected to it
			for (Edge edge : vertex.getIncomingEdges()) {
				if (!this.edgeList.remove(edge) | !edge.getOriginVertex().removeOutgoingEdge(edge)) {
					LOG.severe("Graph inconsistency in removeVertex method! (Incoming edge already removed)");
				}
			}

			for (Edge edge : vertex.getOutgoingEdges()) {
				if (!this.edgeList.remove(edge) | !edge.getTargetVertex().removeIncomingEdge(edge)) {
					LOG.severe("Graph inconsistency in removeVertex method! (Outgoing edge already removed)");
				}
			}

			this.vertexList.remove(index);
			return true;
		}
	}

}
