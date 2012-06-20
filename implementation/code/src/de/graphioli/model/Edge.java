package de.graphioli.model;

/**
 * This class represents a logical {@link Edge}.
 * 
 * @author Graphioli
 * 
 */
public class Edge {
	private Vertex originVertex;
	private Vertex targetVertex;
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
}
