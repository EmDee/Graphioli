package de.graphioli.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class Graph {

	private final static Logger LOG = Logger.getLogger(Graph.class.getName());

	private final List<Vertex> vertexList;
	private final List<Edge> edgeList;

	/**
	 * Creates a new, empty {link Graph}.
	 */
	public Graph() {
		vertexList = new ArrayList<Vertex>();
		edgeList = new ArrayList<Edge>();
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
		
		if (!(vertexList.contains(edge.getOriginVertex()) && vertexList
				.contains(edge.getTargetVertex()))) {
			return false;
		}

		if (edgeList.contains(edge)) {
			return false;
		} else {
			edgeList.add(edge);
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
		if (vertexList.contains(vertex)) {
			return false;
		}
	
		if (vertex.getIncomingEdges().size() == 0
				&& vertex.getOutgoingEdges().size() == 0) {
			vertexList.add(vertex);
			return true;
		} else {
			return false;
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
		int index = vertexList.indexOf(vertex);
	
		if (index == -1) {
			// Vertex not in list
			return false;
		} else {
	
			// Remove edges connected to it
			for (Edge edge : vertex.getIncomingEdges()) {
				if (edgeList.remove(edge)) {
					LOG.severe("Graph inconsistency in removeVertex method!");
				}
			}
	
			for (Edge edge : vertex.getOutgoingEdges()) {
				if (edgeList.remove(edge)) {
					LOG.severe("Graph inconsistency in removeVertex method!");
				}
			}
	
			vertexList.remove(index);
			return true;
		}
	}

	/**
	 * Removes a {@link Edge} from this grapht.
	 * 
	 * @param edge
	 *            the edge to remove
	 * @return {@code true} if the edge was in this graph
	 */
	public boolean removeEdge(Edge edge) {
		int index = edgeList.indexOf(edge);
	
		if (index == -1) {
			// Edge not in list
			return false;
		} else {
			edgeList.remove(index);
	
			if (!edge.getOriginVertex().removeOutgoingEdge(edge)
					|| !edge.getTargetVertex().removeIncomingEdge(edge)) {
				LOG.severe("Graph inconsistency in removeEdge method!");
			}
			return true;
		}
	}
	
	public List<Vertex> getVertices() {
		return vertexList;
	}
	
	public List<Edge> getEdges() {
		return edgeList;
	}

}