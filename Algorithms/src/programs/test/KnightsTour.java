package programs.test;

public class KnightsTour {
	int[][] a = new int[8][8];
	int[] moveX = { 2, 1, -1, -2, -2, -1, 1, 2 };
	int[] moveY = { 1, 2, 2, 1, -1, -2, -2, -1 };

	KnightsTour() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				a[i][j] = -1;
			}
		}
	}

	public static void main(String[] args) {
		KnightsTour k = new KnightsTour();
		// k.a[0][0]=0;
		k.recurse(0, 0, 0);
		k.print();
	}

	boolean recurse(int i, int j, int count) {
		// System.out.println("entering fxn with count : "+count);
		if (a[i][j] == -1) {
			a[i][j] = count;
			if (count == 63) {
				return true;
			}
		} else {
			return false;
		}

		boolean isSuccessfulMove = false;
		for (int x = 0; x < 8; x++) {
			if (isMovePossible(i, j, x)) {
				if (count == 62)
					System.out.println("----------------------------------");
				print();
				System.out.println("----------------------------------");
				isSuccessfulMove = recurse(i + moveX[x], j + moveY[x], count + 1);
				if (isSuccessfulMove) {
					print();
					return true; // break, no need to try other possibilities
				}
			}
		}
		// flow only comes here if all 8 loop iterations i.e. moves failed
		a[i][j] = -1;
		return false;
	}

	boolean isMovePossible(int i, int j, int x) {
		// System.out.println(i+" "+j+","+(i+moveX[x])+" "+(j+moveY[x]));
		if (i + moveX[x] >= 0 && j + moveY[x] > 0) {
			if (i + moveX[x] < 8 && j + moveY[x] < 8)
				return true;
		}
		return false;
	}

	void print() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}
}
