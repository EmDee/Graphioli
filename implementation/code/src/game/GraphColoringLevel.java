package game;

import de.graphioli.model.GridPoint;
import de.graphioli.model.SimpleVisualEdge;
import de.graphioli.model.VisualEdge;

/**
 * This class represents a level of a graph coloring game.
 * 
 * @author Team Graphioli
 */
public final class GraphColoringLevel {

	/*
	 * the number of levels that are implemented
	 */
	public static final int IMPLEMENTED_LEVEL_COUNT = 7;

	/**
	 * The number of colors to color the graph.
	 */
	private final int colorCount;

	/**
	 * The vertices of the level.
	 */
	private final GraphColoringVertex[] lvlVertices;

	/**
	 * The edges of the level.
	 */
	private final VisualEdge[] lvlEdges;

	/**
	 * Creates a new instance of the graph coloring level.
	 * 
	 * @param colorCount
	 *            the number of colors
	 * @param vertices
	 *            the vertices of the level
	 * @param edges
	 *            the edges of the level
	 */
	private GraphColoringLevel(int colorCount, GraphColoringVertex[] vertices, VisualEdge[] edges) {
		this.colorCount = colorCount;
		this.lvlVertices = vertices;
		this.lvlEdges = edges;
	}

	/**
	 * Returns the level with the given level number.
	 * 
	 * @param levelNumber
	 *            the number of the level to be returned
	 * @return the level instance
	 */
	public static GraphColoringLevel getLevelInstance(int levelNumber) {
		switch (levelNumber) {
			case 1:
				return generateLevelOne();
			case 2:
				return generateLevelTwo();
			case 3:
				return generateLevelThree();
			case 4:
				return generateLevelFour();
			case 5:
				return generateLevelFive();
			case 6:
				return generateLevelSix();
			case 7:
				return generateLevelSeven();
			default:
				return null;
		}
	}

	/**
	 * Generates the first level of graph coloring.
	 * 
	 * @return the first level
	 */
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

	/**
	 * Generates the second level of graph coloring.
	 * 
	 * @return the second level
	 */
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

	/**
	 * Generates the third level of graph coloring.
	 * 
	 * @return the third level
	 */
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

	/**
	 * Generates the fourth level of graph coloring.
	 * 
	 * @return the fourth level
	 */
	private static GraphColoringLevel generateLevelFour() {

		GraphColoringVertex[] vertices = new GraphColoringVertex[14];
		VisualEdge[] edges = new VisualEdge[29];

		vertices[0] = new GraphColoringVertex(new GridPoint(2, 3));
		vertices[1] = new GraphColoringVertex(new GridPoint(4, 2));
		vertices[2] = new GraphColoringVertex(new GridPoint(7, 2));
		vertices[3] = new GraphColoringVertex(new GridPoint(10, 3));
		vertices[4] = new GraphColoringVertex(new GridPoint(6, 4));
		vertices[5] = new GraphColoringVertex(new GridPoint(1, 5));
		vertices[6] = new GraphColoringVertex(new GridPoint(4, 5));
		vertices[7] = new GraphColoringVertex(new GridPoint(6, 6));
		vertices[8] = new GraphColoringVertex(new GridPoint(9, 5));
		vertices[9] = new GraphColoringVertex(new GridPoint(3, 8));
		vertices[10] = new GraphColoringVertex(new GridPoint(8, 7));
		vertices[11] = new GraphColoringVertex(new GridPoint(2, 9));
		vertices[12] = new GraphColoringVertex(new GridPoint(6, 10));
		vertices[13] = new GraphColoringVertex(new GridPoint(8, 9));

		edges[0] = new SimpleVisualEdge(vertices[0], vertices[1]);
		edges[1] = new SimpleVisualEdge(vertices[0], vertices[4]);
		edges[2] = new SimpleVisualEdge(vertices[0], vertices[5]);
		edges[3] = new SimpleVisualEdge(vertices[0], vertices[6]);
		edges[4] = new SimpleVisualEdge(vertices[1], vertices[2]);
		edges[5] = new SimpleVisualEdge(vertices[1], vertices[4]);
		edges[6] = new SimpleVisualEdge(vertices[2], vertices[3]);
		edges[7] = new SimpleVisualEdge(vertices[2], vertices[4]);
		edges[8] = new SimpleVisualEdge(vertices[2], vertices[8]);
		edges[9] = new SimpleVisualEdge(vertices[3], vertices[8]);
		edges[10] = new SimpleVisualEdge(vertices[4], vertices[6]);
		edges[11] = new SimpleVisualEdge(vertices[4], vertices[7]);
		edges[12] = new SimpleVisualEdge(vertices[4], vertices[8]);
		edges[13] = new SimpleVisualEdge(vertices[5], vertices[6]);
		edges[14] = new SimpleVisualEdge(vertices[5], vertices[9]);
		edges[15] = new SimpleVisualEdge(vertices[6], vertices[7]);
		edges[16] = new SimpleVisualEdge(vertices[6], vertices[9]);
		edges[17] = new SimpleVisualEdge(vertices[6], vertices[12]);
		edges[18] = new SimpleVisualEdge(vertices[7], vertices[8]);
		edges[19] = new SimpleVisualEdge(vertices[7], vertices[10]);
		edges[20] = new SimpleVisualEdge(vertices[7], vertices[12]);
		edges[21] = new SimpleVisualEdge(vertices[7], vertices[13]);
		edges[22] = new SimpleVisualEdge(vertices[8], vertices[10]);
		edges[23] = new SimpleVisualEdge(vertices[9], vertices[11]);
		edges[24] = new SimpleVisualEdge(vertices[9], vertices[12]);
		edges[25] = new SimpleVisualEdge(vertices[10], vertices[13]);
		edges[26] = new SimpleVisualEdge(vertices[11], vertices[5]);
		edges[27] = new SimpleVisualEdge(vertices[11], vertices[12]);
		edges[28] = new SimpleVisualEdge(vertices[12], vertices[13]);

		return new GraphColoringLevel(3, vertices, edges);
	}

	/**
	 * Generates the fifth level of graph coloring.
	 * 
	 * @return the fifth level
	 */
	private static GraphColoringLevel generateLevelFive() {

		GraphColoringVertex[] vertices = new GraphColoringVertex[29];
		VisualEdge[] edges = new VisualEdge[74];

		vertices[0] = new GraphColoringVertex(new GridPoint(1, 2));
		vertices[1] = new GraphColoringVertex(new GridPoint(3, 2));
		vertices[2] = new GraphColoringVertex(new GridPoint(5, 2));
		vertices[3] = new GraphColoringVertex(new GridPoint(7, 2));
		vertices[4] = new GraphColoringVertex(new GridPoint(9, 2));
		vertices[5] = new GraphColoringVertex(new GridPoint(8, 3));
		vertices[6] = new GraphColoringVertex(new GridPoint(6, 3));
		vertices[7] = new GraphColoringVertex(new GridPoint(4, 3));
		vertices[8] = new GraphColoringVertex(new GridPoint(2, 3));
		vertices[9] = new GraphColoringVertex(new GridPoint(3, 4));
		vertices[10] = new GraphColoringVertex(new GridPoint(5, 4));
		vertices[11] = new GraphColoringVertex(new GridPoint(7, 4));
		vertices[12] = new GraphColoringVertex(new GridPoint(6, 5));
		vertices[13] = new GraphColoringVertex(new GridPoint(4, 5));
		vertices[14] = new GraphColoringVertex(new GridPoint(5, 6));
		vertices[15] = new GraphColoringVertex(new GridPoint(1, 10));
		vertices[16] = new GraphColoringVertex(new GridPoint(3, 10));
		vertices[17] = new GraphColoringVertex(new GridPoint(5, 10));
		vertices[18] = new GraphColoringVertex(new GridPoint(7, 10));
		vertices[19] = new GraphColoringVertex(new GridPoint(9, 10));
		vertices[20] = new GraphColoringVertex(new GridPoint(8, 9));
		vertices[21] = new GraphColoringVertex(new GridPoint(6, 9));
		vertices[22] = new GraphColoringVertex(new GridPoint(4, 9));
		vertices[23] = new GraphColoringVertex(new GridPoint(2, 9));
		vertices[24] = new GraphColoringVertex(new GridPoint(3, 8));
		vertices[25] = new GraphColoringVertex(new GridPoint(5, 8));
		vertices[26] = new GraphColoringVertex(new GridPoint(7, 8));
		vertices[27] = new GraphColoringVertex(new GridPoint(6, 7));
		vertices[28] = new GraphColoringVertex(new GridPoint(4, 7));

		for (int i = 0; i < 14; i++) {
			edges[i] = new SimpleVisualEdge(vertices[i], vertices[i + 1]);
		}
		for (int i = 15; i < 28; i++) {
			edges[i - 1] = new SimpleVisualEdge(vertices[i], vertices[i + 1]);
		}
		edges[27] = new SimpleVisualEdge(vertices[0], vertices[8]);
		edges[28] = new SimpleVisualEdge(vertices[0], vertices[15]);
		edges[29] = new SimpleVisualEdge(vertices[1], vertices[8]);
		edges[30] = new SimpleVisualEdge(vertices[1], vertices[7]);
		edges[31] = new SimpleVisualEdge(vertices[2], vertices[7]);
		edges[32] = new SimpleVisualEdge(vertices[2], vertices[6]);
		edges[33] = new SimpleVisualEdge(vertices[3], vertices[6]);
		edges[34] = new SimpleVisualEdge(vertices[3], vertices[5]);
		edges[35] = new SimpleVisualEdge(vertices[4], vertices[19]);
		edges[36] = new SimpleVisualEdge(vertices[5], vertices[19]);
		edges[37] = new SimpleVisualEdge(vertices[5], vertices[11]);
		edges[38] = new SimpleVisualEdge(vertices[5], vertices[20]);
		edges[39] = new SimpleVisualEdge(vertices[6], vertices[10]);
		edges[40] = new SimpleVisualEdge(vertices[6], vertices[11]);
		edges[41] = new SimpleVisualEdge(vertices[7], vertices[9]);
		edges[42] = new SimpleVisualEdge(vertices[7], vertices[10]);
		edges[43] = new SimpleVisualEdge(vertices[8], vertices[15]);
		edges[44] = new SimpleVisualEdge(vertices[8], vertices[23]);
		edges[45] = new SimpleVisualEdge(vertices[9], vertices[13]);
		edges[46] = new SimpleVisualEdge(vertices[9], vertices[23]);
		edges[47] = new SimpleVisualEdge(vertices[9], vertices[24]);
		edges[48] = new SimpleVisualEdge(vertices[10], vertices[12]);
		edges[49] = new SimpleVisualEdge(vertices[10], vertices[13]);
		edges[50] = new SimpleVisualEdge(vertices[11], vertices[20]);
		edges[51] = new SimpleVisualEdge(vertices[11], vertices[26]);
		edges[52] = new SimpleVisualEdge(vertices[12], vertices[14]);
		edges[53] = new SimpleVisualEdge(vertices[12], vertices[26]);
		edges[54] = new SimpleVisualEdge(vertices[12], vertices[27]);
		edges[55] = new SimpleVisualEdge(vertices[13], vertices[28]);
		edges[56] = new SimpleVisualEdge(vertices[14], vertices[27]);
		edges[57] = new SimpleVisualEdge(vertices[14], vertices[28]);
		edges[58] = new SimpleVisualEdge(vertices[15], vertices[23]);
		edges[59] = new SimpleVisualEdge(vertices[16], vertices[22]);
		edges[60] = new SimpleVisualEdge(vertices[16], vertices[23]);
		edges[61] = new SimpleVisualEdge(vertices[17], vertices[21]);
		edges[62] = new SimpleVisualEdge(vertices[17], vertices[22]);
		edges[63] = new SimpleVisualEdge(vertices[18], vertices[20]);
		edges[64] = new SimpleVisualEdge(vertices[18], vertices[21]);
		edges[65] = new SimpleVisualEdge(vertices[20], vertices[26]);
		edges[66] = new SimpleVisualEdge(vertices[21], vertices[25]);
		edges[67] = new SimpleVisualEdge(vertices[21], vertices[26]);
		edges[68] = new SimpleVisualEdge(vertices[22], vertices[24]);
		edges[69] = new SimpleVisualEdge(vertices[22], vertices[25]);
		edges[70] = new SimpleVisualEdge(vertices[24], vertices[28]);
		edges[71] = new SimpleVisualEdge(vertices[25], vertices[27]);
		edges[72] = new SimpleVisualEdge(vertices[25], vertices[28]);
		edges[73] = new SimpleVisualEdge(vertices[13], vertices[24]);

		return new GraphColoringLevel(4, vertices, edges);
	}
	
	private static GraphColoringLevel generateLevelSix(){
		GraphColoringVertex[] vertices = new GraphColoringVertex[25];
		VisualEdge[] edges = new VisualEdge[37];
		
		for (int i = 0; i < 25; i++) {
			vertices[i] = new GraphColoringVertex(new GridPoint(i % 5 + 3, i / 5 + 3));
		}
		
		edges[0] = new SimpleVisualEdge(vertices[0], vertices[1]);
		edges[1] = new SimpleVisualEdge(vertices[0], vertices[6]);
		edges[2] = new SimpleVisualEdge(vertices[1], vertices[6]);
		edges[3] = new SimpleVisualEdge(vertices[2], vertices[7]);
		edges[4] = new SimpleVisualEdge(vertices[3], vertices[4]);
		edges[5] = new SimpleVisualEdge(vertices[3], vertices[8]);
		edges[6] = new SimpleVisualEdge(vertices[3], vertices[9]);
		edges[7] = new SimpleVisualEdge(vertices[4], vertices[8]);
		edges[8] = new SimpleVisualEdge(vertices[4], vertices[9]);
		edges[9] = new SimpleVisualEdge(vertices[5], vertices[6]);
		edges[10] = new SimpleVisualEdge(vertices[5], vertices[10]);
		edges[11] = new SimpleVisualEdge(vertices[6], vertices[7]);
		edges[12] = new SimpleVisualEdge(vertices[6], vertices[10]);
		edges[13] = new SimpleVisualEdge(vertices[6], vertices[11]);
		edges[14] = new SimpleVisualEdge(vertices[6], vertices[12]);
		edges[15] = new SimpleVisualEdge(vertices[7], vertices[11]);
		edges[16] = new SimpleVisualEdge(vertices[7], vertices[12]);
		edges[17] = new SimpleVisualEdge(vertices[7], vertices[13]);
		edges[18] = new SimpleVisualEdge(vertices[8], vertices[9]);
		edges[19] = new SimpleVisualEdge(vertices[8], vertices[12]);
		edges[20] = new SimpleVisualEdge(vertices[8], vertices[14]);
		edges[21] = new SimpleVisualEdge(vertices[11], vertices[12]);
		edges[22] = new SimpleVisualEdge(vertices[11], vertices[16]);
		edges[23] = new SimpleVisualEdge(vertices[12], vertices[18]);
		edges[24] = new SimpleVisualEdge(vertices[14], vertices[18]);
		edges[25] = new SimpleVisualEdge(vertices[15], vertices[16]);
		edges[26] = new SimpleVisualEdge(vertices[15], vertices[20]);
		edges[27] = new SimpleVisualEdge(vertices[16], vertices[20]);
		edges[28] = new SimpleVisualEdge(vertices[16], vertices[21]);
		edges[29] = new SimpleVisualEdge(vertices[16], vertices[22]);
		edges[30] = new SimpleVisualEdge(vertices[17], vertices[22]);
		edges[31] = new SimpleVisualEdge(vertices[18], vertices[24]);
		edges[32] = new SimpleVisualEdge(vertices[19], vertices[23]);
		edges[33] = new SimpleVisualEdge(vertices[19], vertices[24]);
		edges[34] = new SimpleVisualEdge(vertices[20], vertices[21]);
		edges[35] = new SimpleVisualEdge(vertices[21], vertices[22]);
		edges[36] = new SimpleVisualEdge(vertices[23], vertices[24]);
		
		return new GraphColoringLevel(4, vertices, edges);
	}
	
	private static GraphColoringLevel generateLevelSeven() {
		GraphColoringVertex[] vertices = new GraphColoringVertex[26];
		VisualEdge[] edges = new VisualEdge[52]; 
		
		vertices[0] = new GraphColoringVertex(new GridPoint(4, 1));
		vertices[1] = new GraphColoringVertex(new GridPoint(6, 1));
		vertices[2] = new GraphColoringVertex(new GridPoint(1, 2));
		vertices[3] = new GraphColoringVertex(new GridPoint(9, 2));
		vertices[4] = new GraphColoringVertex(new GridPoint(2, 4));
		vertices[5] = new GraphColoringVertex(new GridPoint(5, 4));
		vertices[6] = new GraphColoringVertex(new GridPoint(8, 4));
		vertices[7] = new GraphColoringVertex(new GridPoint(1, 5));
		vertices[8] = new GraphColoringVertex(new GridPoint(4, 5));
		vertices[9] = new GraphColoringVertex(new GridPoint(6, 5));
		vertices[10] = new GraphColoringVertex(new GridPoint(9, 5));
		vertices[11] = new GraphColoringVertex(new GridPoint(0, 6));
		vertices[12] = new GraphColoringVertex(new GridPoint(3, 6));
		vertices[13] = new GraphColoringVertex(new GridPoint(7, 6));
		vertices[14] = new GraphColoringVertex(new GridPoint(10, 6));
		vertices[15] = new GraphColoringVertex(new GridPoint(4, 7));
		vertices[16] = new GraphColoringVertex(new GridPoint(6, 7));
		vertices[17] = new GraphColoringVertex(new GridPoint(2, 8));
		vertices[18] = new GraphColoringVertex(new GridPoint(4, 8));
		vertices[19] = new GraphColoringVertex(new GridPoint(6, 8));
		vertices[20] = new GraphColoringVertex(new GridPoint(8, 8));
		vertices[21] = new GraphColoringVertex(new GridPoint(3, 10));
		vertices[22] = new GraphColoringVertex(new GridPoint(5, 10));
		vertices[23] = new GraphColoringVertex(new GridPoint(7, 10));
		vertices[24] = new GraphColoringVertex(new GridPoint(2, 11));
		vertices[25] = new GraphColoringVertex(new GridPoint(8, 11));
		
		edges[0] = new SimpleVisualEdge(vertices[0], vertices[5]);
		edges[1] = new SimpleVisualEdge(vertices[1], vertices[5]);
		edges[2] = new SimpleVisualEdge(vertices[2], vertices[4]);
		edges[3] = new SimpleVisualEdge(vertices[2], vertices[5]);
		edges[4] = new SimpleVisualEdge(vertices[2], vertices[7]);
		edges[5] = new SimpleVisualEdge(vertices[2], vertices[11]);
		edges[6] = new SimpleVisualEdge(vertices[3], vertices[5]);
		edges[7] = new SimpleVisualEdge(vertices[3], vertices[6]);
		edges[8] = new SimpleVisualEdge(vertices[3], vertices[9]);
		edges[9] = new SimpleVisualEdge(vertices[3], vertices[14]);
		edges[10] = new SimpleVisualEdge(vertices[4], vertices[7]);
		edges[11] = new SimpleVisualEdge(vertices[4], vertices[8]);
		edges[12] = new SimpleVisualEdge(vertices[5], vertices[8]);
		edges[13] = new SimpleVisualEdge(vertices[5], vertices[9]);
		edges[14] = new SimpleVisualEdge(vertices[5], vertices[15]);
		edges[15] = new SimpleVisualEdge(vertices[5], vertices[16]);
		edges[16] = new SimpleVisualEdge(vertices[6], vertices[9]);
		edges[17] = new SimpleVisualEdge(vertices[6], vertices[10]);
		edges[18] = new SimpleVisualEdge(vertices[6], vertices[13]);
		edges[19] = new SimpleVisualEdge(vertices[7], vertices[11]);
		edges[20] = new SimpleVisualEdge(vertices[7], vertices[12]);
		edges[21] = new SimpleVisualEdge(vertices[8], vertices[12]);
		edges[22] = new SimpleVisualEdge(vertices[8], vertices[15]);
		edges[23] = new SimpleVisualEdge(vertices[9], vertices[13]);
		edges[24] = new SimpleVisualEdge(vertices[10], vertices[13]);
		edges[25] = new SimpleVisualEdge(vertices[10], vertices[14]);
		edges[26] = new SimpleVisualEdge(vertices[11], vertices[15]);
		edges[27] = new SimpleVisualEdge(vertices[12], vertices[15]);
		edges[28] = new SimpleVisualEdge(vertices[13], vertices[16]);
		edges[29] = new SimpleVisualEdge(vertices[14], vertices[16]);
		edges[30] = new SimpleVisualEdge(vertices[15], vertices[16]);
		edges[31] = new SimpleVisualEdge(vertices[15], vertices[17]);
		edges[32] = new SimpleVisualEdge(vertices[15], vertices[22]);
		edges[33] = new SimpleVisualEdge(vertices[16], vertices[19]);
		edges[34] = new SimpleVisualEdge(vertices[16], vertices[20]);
		edges[35] = new SimpleVisualEdge(vertices[16], vertices[22]);
		edges[36] = new SimpleVisualEdge(vertices[17], vertices[18]);
		edges[37] = new SimpleVisualEdge(vertices[17], vertices[21]);
		edges[38] = new SimpleVisualEdge(vertices[17], vertices[22]);
		edges[39] = new SimpleVisualEdge(vertices[17], vertices[24]);
		edges[40] = new SimpleVisualEdge(vertices[18], vertices[21]);
		edges[41] = new SimpleVisualEdge(vertices[18], vertices[22]);
		edges[42] = new SimpleVisualEdge(vertices[19], vertices[20]);
		edges[43] = new SimpleVisualEdge(vertices[19], vertices[22]);
		edges[44] = new SimpleVisualEdge(vertices[19], vertices[23]);
		edges[45] = new SimpleVisualEdge(vertices[20], vertices[23]);
		edges[46] = new SimpleVisualEdge(vertices[20], vertices[25]);
		edges[47] = new SimpleVisualEdge(vertices[22], vertices[23]);
		edges[48] = new SimpleVisualEdge(vertices[22], vertices[24]);
		edges[49] = new SimpleVisualEdge(vertices[22], vertices[25]);
		edges[50] = new SimpleVisualEdge(vertices[23], vertices[25]);
		edges[51] = new SimpleVisualEdge(vertices[13], vertices[14]);
		
		return new GraphColoringLevel(3, vertices, edges);
	}

	/**
	 * @return the number of colors in the level
	 */
	public int getColorCount() {
		return this.colorCount;
	}

	/**
	 * @return the vertices of the level
	 */
	public GraphColoringVertex[] getVertices() {
		return this.lvlVertices;
	}

	/**
	 * @return the edges of the level
	 */
	public VisualEdge[] getEdges() {
		return this.lvlEdges;
	}
}
