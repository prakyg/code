package hackerEarth.dnb;

public class HalfEdge {
	boolean filled;
	Box next;

	HalfEdge(boolean filled, Box next) {
		this.filled = filled;
		this.next = next;
	}
}
