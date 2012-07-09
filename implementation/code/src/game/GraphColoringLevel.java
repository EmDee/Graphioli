package game;

//TODO: Javadoc

import de.graphioli.model.GridPoint;
import de.graphioli.model.SimpleVisualEdge;
import de.graphioli.model.VisualEdge;
import de.graphioli.model.VisualVertex;

public class GraphColoringLevel {

	public static final int IMPLEMENTED_LEVEL_COUNT = 3;
			
	private final int colorCount;
	private final GraphColoringVertex[] lvlVertices;
	private final VisualEdge[] lvlEdges;

	private GraphColoringLevel(int colorCount, GraphColoringVertex[] vertices, VisualEdge[] edges) {
		this.colorCount = colorCount;
		this.lvlVertices = vertices;
		this.lvlEdges = edges;
	}

	public static GraphColoringLevel getLevelInstance(int levelNumber) {
		switch (levelNumber) {
			case 1:
				return generateLevelOne();
			case 2:
				return generateLevelTwo();
			case 3:
				return generateLevelThree();
			default:
				return null;
		}
	}

	private static GraphColoringLevel generateLevelOne() {

		GraphColoringVertex[] vertices = new GraphColoringVertex[4];
		VisualEdge[] edges = new VisualEdge[5];
		GridPoint tmpPoint;

		for (int i = 0; i < 4; i++) {
			tmpPoint = new GridPoint((i / 2) * 3 + 2, (i % 2) * 3 + 2);
			vertices[i] = new GraphColoringVertex(tmpPoint);
		}
		for (int i = 0; i < 4; i++) {
			edges[i] = new SimpleVisualEdge(vertices[i], vertices[(i + 1) % 4]);
		}
		edges[4] = new SimpleVisualEdge(vertices[0], vertices[2]);

		return new GraphColoringLevel(3, vertices, edges);
	}
	
	private static GraphColoringLevel generateLevelTwo() {

		GraphColoringVertex[] vertices = new GraphColoringVertex[6];
		VisualEdge[] edges = new VisualEdge[10];

		vertices[0] = new GraphColoringVertex(new GridPoint(1, 4));
		vertices[1] = new GraphColoringVertex(new GridPoint(3, 2));
		vertices[2] = new GraphColoringVertex(new GridPoint(5, 2));
		vertices[3] = new GraphColoringVertex(new GridPoint(7, 4));
		vertices[4] = new GraphColoringVertex(new GridPoint(5, 6));
		vertices[5] = new GraphColoringVertex(new GridPoint(3, 6));
		
		for (int i = 0; i < 6; i++) {
		edges[i] = new SimpleVisualEdge(vertices[i], vertices[(i + 1) % 6]);
		}
		edges[6] = new SimpleVisualEdge(vertices[1], vertices[5]);
		edges[7] = new SimpleVisualEdge(vertices[2], vertices[4]);
		edges[8] = new SimpleVisualEdge(vertices[0], vertices[2]);
		edges[9] = new SimpleVisualEdge(vertices[3], vertices[5]);

		return new GraphColoringLevel(3, vertices, edges);
		
		
	}
	
	private static GraphColoringLevel generateLevelThree() {
		
		GraphColoringVertex[] vertices = new GraphColoringVertex[13];
		VisualEdge[] edges = new VisualEdge[14];
		
		vertices[0] = new GraphColoringVertex(new GridPoint(1, 3));
		vertices[1] = new GraphColoringVertex(new GridPoint(2, 4));
		vertices[2] = new GraphColoringVertex(new GridPoint(3, 3));
		vertices[3] = new GraphColoringVertex(new GridPoint(4, 3));
		vertices[4] = new GraphColoringVertex(new GridPoint(5, 3));
		vertices[5] = new GraphColoringVertex(new GridPoint(6, 3));
		vertices[6] = new GraphColoringVertex(new GridPoint(4, 2));
		vertices[7] = new GraphColoringVertex(new GridPoint(5, 4));
		vertices[8] = new GraphColoringVertex(new GridPoint(4, 5));
		vertices[9] = new GraphColoringVertex(new GridPoint(3, 6));
		vertices[10] = new GraphColoringVertex(new GridPoint(5, 6));
		vertices[11] = new GraphColoringVertex(new GridPoint(6, 5));
		vertices[12] = new GraphColoringVertex(new GridPoint(3, 7));
		
		for (int i = 0; i < 5; i++) {
			edges[i] = new SimpleVisualEdge(vertices[i], vertices[i + 1]);	
		}
		edges[5] = new SimpleVisualEdge(vertices[8], vertices[2]);
		edges[6] = new SimpleVisualEdge(vertices[3], vertices[6]);
		edges[7] = new SimpleVisualEdge(vertices[5], vertices[7]);
		edges[8] = new SimpleVisualEdge(vertices[9], vertices[12]);
		edges[9] = new SimpleVisualEdge(vertices[10], vertices[11]);
		edges[10] = new SimpleVisualEdge(vertices[8], vertices[3]);
		edges[11] = new SimpleVisualEdge(vertices[8], vertices[4]);
		edges[12] = new SimpleVisualEdge(vertices[8], vertices[9]);
		edges[13] = new SimpleVisualEdge(vertices[8], vertices[10]);
		
		return new GraphColoringLevel(3, vertices, edges);
	}

	public int getColorCount() {
		return this.colorCount;
	}

	public GraphColoringVertex[] getVertices() {
		return this.lvlVertices;
	}

	public VisualEdge[] getEdges() {
		return this.lvlEdges;
	}
}

