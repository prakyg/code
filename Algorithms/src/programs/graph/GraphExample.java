package programs.graph;

public class GraphExample {
	public GraphExample() {
		super();
	}

	public static void main(String[] args) throws java.lang.Exception {
		int j;
		// create a matrix
		int[][] a = new int[][] { { 1, 1, 1, 1 }, { 1, 0, 0, 1 }, { 1, 0, 0, 1 }, { 1, 1, 1, 1 } };
		// create a new Graph
		Graph g = new Graph();
		// add edges

		for (int i = 0; i < 16; i++) {
			Vertex v = new Vertex();
			g.addVertex(v);
		}
		int n = 4;
		// construct edges
		for (int i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				if (a[i][j] == 1) {
					// check edge on right
					if (j < n - 1 && a[i][j + 1] == 1)
						g.addEdge(new Edge(g.getVertex(4 * i + j), g.getVertex(4 * i + j + 1)));
					// check edge on down
					if (i < n - 1 && a[i + 1][j] == 1)
						g.addEdge(new Edge(g.getVertex(4 * i + j), g.getVertex(4 * i + 4 + j)));
					// check edge on diagonal
					if (i < n - 1 && j < n - 1 && a[i + 1][j + 1] == 1)
						g.addEdge(new Edge(g.getVertex(4 * i + j), g.getVertex(4 * i + 4 + j + 1)));
				}
			}
		} // outer for loop ends
		g.printGraph();
		System.out.println(g.getCountConnectedComponents());
		// g.
		System.out.println("end");
	}
}