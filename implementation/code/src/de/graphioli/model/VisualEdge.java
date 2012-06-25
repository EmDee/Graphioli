package de.graphioli.model;

import java.awt.Image;

public abstract class VisualEdge extends Edge {
	
	private boolean visible;

	public VisualEdge(Vertex vertexA, Vertex vertexB) {
		super(vertexA, vertexB);
		this.visible = true;
		// TODO Auto-generated constructor stub
	}

	public Image getBufferedImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isVisible() {
		return this.visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
