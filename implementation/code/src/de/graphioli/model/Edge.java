package de.graphioli.model;

import java.io.Serializable;

/**
 * This class represents a logical {@link Edge}.
 * 
 * @author Graphioli
 */
public class Edge implements Serializable {
	
	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -236312537166149510L;
	
	/**
	 * This edge's origin Vertex.
	 */
	private final Vertex originVertex;
	
	/**
	 * This edge's target vertex.
	 */
	private final Vertex targetVertex;
	
	/**
	 * This edge's weight.
	 */
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
	}

	/**
	 * Two edges are equal if their respective target and origin vertices are
	 * equal.
	 * 
	 * @param obj
	 *            given {@code Edge} to compare to
	 * @return if the given {@code Edge} is equal to this {@code Edge}.
	 */
	@Override
	public boolean equals(Object obj) {
		Edge other = (Edge) obj;

		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		if (this.originVertex == null) {
			if (other.originVertex != null) {
				return false;
			}
		} else if (!this.originVertex.equals(other.originVertex)) {
			return false;
		}
		if (this.targetVertex == null) {
			if (other.targetVertex != null) {
				return false;
			}
		} else if (!this.targetVertex.equals(other.targetVertex)) {
			return false;
		}
		return true;
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
	 * Returns the weight of this {@link Edge}.
	 * 
	 * @return the weight of this {@link Edge}
	 */
	public int getWeight() {
		return this.weight;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.originVertex == null) ? 0 : this.originVertex.hashCode());
		result = prime * result + ((this.targetVertex == null) ? 0 : this.targetVertex.hashCode());
		return result;
	}

	/**
	 * Sets the weight of this Edge.
	 * 
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

}
