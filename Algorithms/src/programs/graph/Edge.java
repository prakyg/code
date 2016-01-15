package programs.graph;

import programs.graph.Vertex;

public class Edge {
	// Each edge contains information about its nodes and weight(if any)
	private int edgeWeight = 0;
	private Vertex head;
	private Vertex tail;

	public Edge(Vertex tail, Vertex head) {
		// Edge can't exist without a head or tail
		if (head != null && tail != null) {
			this.tail = tail;
			this.head = head;
		} else
			throw new UnsupportedOperationException("Edge should have both head and tail vertices");
	}

	// Accessors
	public void setEdgeWeight(int edgeWeight) {
		this.edgeWeight = edgeWeight;
	}

	public int getEdgeWeight() {
		return edgeWeight;
	}

	// allow changing head?
	public void setHead(Vertex head) {
		this.head = head;
	}

	public Vertex getHead() {
		return head;
	}

	// allow changing tail?
	public void setTail(Vertex tail) {
		this.tail = tail;
	}

	public Vertex getTail() {
		return tail;
	}

}
