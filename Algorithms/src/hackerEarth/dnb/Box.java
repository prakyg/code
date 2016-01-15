package hackerEarth.dnb;

import java.util.ArrayList;
import java.util.List;

public class Box {
	// array of edges in following format : top, right, bottom, left
	int x, y;
	HalfEdge[] edges;
	int edgeCount = 0; // optimization?
	static List<Box> threeEdgedBoxes = new ArrayList<Box>();

	Box(int x, int y, HalfEdge[] edges) {
		this.x = x;
		this.y = y;
		this.edges = edges;
		for (int i = 0; i < 4; i++) {
			if (this.edges[i].filled)
				edgeCount++;
		}
		if (edgeCount == 3) {
			threeEdgedBoxes.add(this);
		}
	}

	int canAddEdgeSafely() {
		// An edge can be added safely (without giving a box) iff
		// 2 adjacent boxes which share that edge have 0 or 1 edges only
		if (edgeCount >= 2)
			return -1;
		for (int i = 0; i < 4; i++) {
			if (!edges[i].filled // if edge is already filled, ignore it
					&& (edges[i].next == null || edges[i].next.edgeCount <= 1)) {
				return i;
			}
		}
		return -1;
	}

	int getUnfilledEdgeIndex() {
		for (int i = 0; i < 4; i++) {
			if (!edges[i].filled) {
				return i;
			}
		}
		return -1;
	}
}
