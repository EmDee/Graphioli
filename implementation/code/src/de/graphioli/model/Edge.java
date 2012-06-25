package de.graphioli.model;

/**
 * This class represents a logical {@link Edge}.
 * 
 * @author Graphioli
 * 
 */
public class Edge {
	private final Vertex originVertex;
	private final Vertex targetVertex;
	private int weight;

	/**
	 * Creates a new Edge with the specified Vertices vertexA and vertexB.
	 * 
	 * @param vertexA
	 *            given origin {@link Vertex}
	 * @param vertexB
	 *            given target {@link Vertex}
	 */
	public Edge(Vertex vertexA, Vertex vertexB) {
		this.originVertex = vertexA;
		this.targetVertex = vertexB;
		vertexA.addOutgoingEdge(this);
		vertexB.addIncomingEdge(this);
	}

	/**
	 * Returns the origin Vertex.
	 * 
	 * @return this origin {@link Vertex}
	 */
	public Vertex getOriginVertex() {
		return this.originVertex;
	}

	/**
	 * Returns the target Vertex.
	 * 
	 * @return this target {@link Vertex}
	 */
	public Vertex getTargetVertex() {
		return this.targetVertex;
	}

	/**
	 * 
	 * Returns the weight of this {@link Edge}.
	 * 
	 * @return the weight of this {@link Edge}
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * 
	 * Sets the weight of this Edge.
	 * 
	 * @param weight
	 *            the weight to set
	 */
	public boolean setWeight(int weight) {
		this.weight = weight;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((originVertex == null) ? 0 : originVertex.hashCode());
		result = prime * result
				+ ((targetVertex == null) ? 0 : targetVertex.hashCode());
		return result;
	}

	/**
	 * Two edges are equal if their respective target and origin vertices are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (originVertex == null) {
			if (other.originVertex != null)
				return false;
		} else if (!originVertex.equals(other.originVertex))
			return false;
		if (targetVertex == null) {
			if (other.targetVertex != null)
				return false;
		} else if (!targetVertex.equals(other.targetVertex))
			return false;
		return true;
	}
	
	

}
