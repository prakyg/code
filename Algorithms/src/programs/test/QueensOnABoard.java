package programs.test;

public class QueensOnABoard {
	int[][] a;
	int size;

	public QueensOnABoard(int n) {
		size = n;
		a = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				a[i][j] = -1;
			}
		}
	}

	void print() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void start() {

	}

	public boolean placeQueen(int x, int y, int queenCount) {
		if (a[x][y] == -1) {
			// move is possible
			a[x][y] = 3; // let 3 denote the queen's placement
			assertQueenDominance(x, y);
			if (queenCount == 5) {
				return true; // how many queens did you want to place
			}
		} else {
			// move not possible return false
			return false;
		}
		boolean placeSuccess = false;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				placeSuccess = placeQueen(i, j, queenCount + 1);
				if (placeSuccess == true) {
					// move successful, don't try other possibilities
					return true;
				}
			}
		}
		// all 64 tries to place the queen failed
		// remove the queen from the board
		deAssertQueenDominance(x, y);
		return false;
	}

	void assertQueenDominance(int x, int y) {
		for (int i = 0; i < size; i++) {
			a[x][i] = 1; // 1 denotes square under queen's dominance
			a[i][y] = 1;
		}
		for (int i = x, j = y; x >= 0 && y >= 0; x--, y--) {
			a[i][j] = 1;
		}
		for (int i = x, j = y; x < size && y < size; x++, y++) {
			a[i][j] = 1;
		}
		for (int i = x, j = y; x >= 0 && y < size; x--, y++) {
			a[i][j] = 1;
		}
		for (int i = x, j = y; x < size && y >= 0; x++, y--) {
			a[i][j] = 1;
		}
	}

	void deAssertQueenDominance(int x, int y) {
		// problem? a spot can be under 2 queen's dominance
		// probably we should be passing new 2d arrays each time but that would
		// take a lot of space

	}

	public static void main(String[] args) throws Exception {

	}

}
