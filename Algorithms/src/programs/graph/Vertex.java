package programs.graph;

import java.util.ArrayList;
import java.util.Iterator;
//import client.graph.Edge;

public class Vertex {
	// Each vertex contains information about edges emanating from it, weight(if
	// any)
	private boolean visited = false; // useful for various algorithms
	private int nodeWeight = 0;
	private ArrayList<Edge> vertexEdges = new ArrayList<Edge>(); // Each node
																	// has list
																	// of edges
																	// emanating
																	// from it

	public Vertex() {
		super();
	}

	// support iteration over vertex edges
	public Iterator getEdgesIterator() {
		return vertexEdges.iterator();
	}

	public void addEdge(Edge e) {
		vertexEdges.add(e);
	}

	// accessors
	public void setVisited(boolean isVisited) {
		this.visited = isVisited;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setNodeWeight(int nodeWeight) {
		this.nodeWeight = nodeWeight;
	}

	public int getNodeWeight() {
		return nodeWeight;
	}

}
