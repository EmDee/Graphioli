package de.graphioli.model;

import java.util.ArrayList;
import java.util.UUID;
import de.graphioli.utils.UIDManager;

/**
 * This class represents a logical vertex.
 * 
 * @author Graphioli
 * 
 */
public class Vertex {

	private UUID uuid;
	private ArrayList<Edge> incomingEdges = new ArrayList<Edge>();
	private ArrayList<Edge> outgoingEdges = new ArrayList<Edge>();
	private VertexState vertexState = VertexState.UNVISITED;

	/**
	 * Creates a new {@link Vertex} with an UID.
	 */
	public Vertex() {
		uuid = UIDManager.generateUniqueID();
	}

	/**
	 * Returns all vertices that are connected to this {@link Vertex}.
	 * 
	 * @return a list with all adjacent vertices
	 */
	public ArrayList<Vertex> getAdjacentVertices() {
		ArrayList<Vertex> tmpAdjacentVertices = new ArrayList<Vertex>();

		for (Edge edge : this.outgoingEdges) {
			Vertex tmpVertex = edge.getTargetVertex();
			tmpAdjacentVertices.add(tmpVertex);
		}

		return tmpAdjacentVertices;
	}

	/**
	 * Returns the unique identifier for this {@link Vertex}.
	 * 
	 * @return this vertex' uuid
	 */
	public UUID getUID() {
		return this.uuid;
	}

	/**
	 * Returns an iterable list of incoming {@link Edge}s.
	 * 
	 * @return the list of incoming {@link Edge}s
	 */
	public ArrayList<Edge> getIncomingEdges() {
		return this.incomingEdges;
	}

	/**
	 * 
	 * 
	 * 
	 * Adds an incoming {@link Edge} to the list of incoming {@link Edge}s.
	 * 
	 * @param edge
	 *            given {@link Edge} to add
	 * @return <code>true</code> if the {@link Edge} is not already in the list
	 *         of incoming {@link Edge}s, <code>false</code> otherwise
	 */
	boolean addIncomingEdge(Edge edge) {
		for (Edge tmpEdge : this.incomingEdges) {
			if (tmpEdge.equals(edge)) {
				return false;
			}
		}
		this.incomingEdges.add(edge);
		return true;
	}

	/**
	 * Removes an incoming {@link Edge} from the list of incoming {@link Edge}s.
	 * 
	 * @param edge
	 *            given {@link Edge} to remove
	 * @return <code>true</code> if the {@link Edge} was found in the list of
	 *         incoming {@link Edge}s and removed, <code>false</code> otherwise
	 */
	boolean removeIncomingEdge(Edge edge) {
		for (Edge tmpEdge : this.incomingEdges) {
			if (tmpEdge.equals(edge)) {
				this.incomingEdges.remove(tmpEdge);
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns an iterable list of outgoing {@link Edge}s.
	 * 
	 * @return the list of outgoing {@link Edge}s
	 */
	public ArrayList<Edge> getOutgoingEdges() {
		return this.outgoingEdges;
	}

	/**
	 * Adds an outgoing {@link Edge} to the list of outgoing {@link Edge}s.
	 * 
	 * @param edge
	 *            given {@link Edge} to add
	 * @return <code>true</code> if the {@link Edge} is not already in the list
	 *         of outgoing {@link Edge}s, <code>false</code> otherwise
	 */
	boolean addOutgoingEdge(Edge edge) {
		for (Edge tmpEdge : this.outgoingEdges) {
			if (tmpEdge.equals(edge)) {
				return false;
			}
		}
		this.outgoingEdges.add(edge);
		return true;
	}

	/**
	 * Removes an outgoing {@link Edge} from the list of outgoing {@link Edge}s.
	 * 
	 * @param edge
	 *            given {@link Edge} to remove
	 * @return <code>true</code> if the {@link Edge} was found in the list of
	 *         outgoing {@link Edge}s and removed, <code>false</code> otherwise
	 */
	boolean removeOutgoingEdge(Edge edge) {
		for (Edge tmpEdge : this.outgoingEdges) {
			if (tmpEdge.equals(edge)) {
				this.outgoingEdges.remove(tmpEdge);
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a given {@link Vertex} is adjacent to this {@link Vertex}.
	 * 
	 * @param vertex
	 *            given {@link Vertex} to check for adjacency
	 * @return <code>true</code> if the given {@link Vertex} is adjacent to this
	 *         {@link Vertex}, <code>false</code> otherwise.
	 */
	public boolean isAdjacentTo(Vertex vertex) {
		for (Edge edge : outgoingEdges) {
			if (edge.getTargetVertex().equals(vertex)) {
				return true;
			}
		}

		for (Edge edge : incomingEdges) {
			if (edge.getOriginVertex().equals(vertex)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Gets the state of this {@link Vertex} according to {@link VertexState}.
	 * 
	 * @return the vertexState of this {@link Vertex}
	 */
	public VertexState getVertexStage() {
		return vertexState;
	}

	/**
	 * Sets the state of this {@link Vertex}. Default is
	 * <code>VertexState.UNVISITED</code>. See {@link VertexState} for all the
	 * different states.
	 * 
	 * @param vertexStage
	 *            the vertexStage to set
	 */
	public void setVertexStage(VertexState vertexStage) {
		this.vertexState = vertexStage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	/**
	 * Two vertices are equal, if their {@link UUID} is equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
}