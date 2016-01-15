package hackerEarth.dnb;

public class TestClass {
	Box[][] gameBoard = new Box[5][5];

	public static void main(String[] args) throws Exception {
		int[][] A = Reader.readInput();
		TestClass w = new TestClass(A);
		if (Box.threeEdgedBoxes.isEmpty()) {
			// completion of box not on offer
			String op = w.addEdgeSafely();
			if (op != null) {
				System.out.println(op);
				// turn complete
				return;
			}
		} else {
			if (Box.threeEdgedBoxes.size() == 1) {
				// fill the unfilled edge of this box
				Box tempBox = Box.threeEdgedBoxes.get(0);
				int temp = tempBox.getUnfilledEdgeIndex();
				System.out.println(tempBox.x + " " + tempBox.y + " " + temp);
			}
		}
	}

	TestClass(int[][] A) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				HalfEdge[] edges = new HalfEdge[4];
				edges[0] = new HalfEdge(isTopEdge(A[i][j]), getTopBox(i, j));
				edges[1] = new HalfEdge(isRightEdge(A[i][j]), getRightBox(i, j));
				edges[2] = new HalfEdge(isBottomEdge(A[i][j]), getBottomBox(i, j));
				edges[3] = new HalfEdge(isLeftEdge(A[i][j]), getLeftBox(i, j));
				gameBoard[i][j] = new Box(i, j, edges);
			}
		}
		// printBoard();

	}

	private void printBoard() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print("Box i=" + i + " j=" + j);
				System.out.print(", edgecount=" + gameBoard[i][j].edgeCount);
				System.out.print(" Top edge= " + gameBoard[i][j].edges[0].filled);
				System.out.print(" Right edge= " + gameBoard[i][j].edges[1].filled);
				System.out.print(" Bottom edge= " + gameBoard[i][j].edges[2].filled);
				System.out.println(" left edge= " + gameBoard[i][j].edges[3].filled);
				if (gameBoard[i][j].edges[0].next != null)
					System.out.println(" Next box to top edge count= " + gameBoard[i][j].edges[0].next.edgeCount);
			}
		}
	}

	private boolean isTopEdge(int i) {
		return ((i & 1) == 1);
	}

	private boolean isRightEdge(int i) {
		return ((i & 2) == 2);
	}

	private boolean isBottomEdge(int i) {
		return ((i & 4) == 4);
	}

	private boolean isLeftEdge(int i) {
		return ((i & 8) == 8);
	}

	private Box getTopBox(int i, int j) {
		if (i > 0) {
			return gameBoard[i - 1][j];
		} else {
			return null;
		}
	}

	private Box getRightBox(int i, int j) {
		if (j < 4) {
			return gameBoard[i][j + 1];
		} else {
			return null;
		}
	}

	private Box getBottomBox(int i, int j) {
		if (i < 4) {
			return gameBoard[i + 1][j];
		} else {
			return null;
		}
	}

	private Box getLeftBox(int i, int j) {
		if (j > 0) {
			return gameBoard[i][j - 1];
		} else {
			return null;
		}
	}

	private String addEdgeSafely() {
		// check if an edge can be added without opening any chain
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				// System.out.println("i= "+i+ " j= "+ j);
				int edgeCandidate = gameBoard[i][j].canAddEdgeSafely();
				if (edgeCandidate != -1) {
					// This box has atleast one edge that can be added safely
					// add that edge and return
					return ("" + i + " " + j + " " + edgeCandidate);
				}
			}
		}

		// if not, open the shortest chain and hope for luck
		return null;
	}
}
