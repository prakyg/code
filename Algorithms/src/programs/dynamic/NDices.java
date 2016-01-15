package programs.dynamic;

//Number of ways N dices with A faces can make sum S
/*A variation of this problem will be to make sure that each dice is selected
*/
public class NDices {
	int[] faces;
	int N;
	int sum;
	int[][] ways;

	public NDices(int N, int[] faces, int sum) {
		this.N = N;
		this.faces = faces;
		this.sum = sum;
	}

	public static void main(String[] args) throws Exception {
		NDices obj = new NDices(3, new int[] { 1, 2, 3, 4 }, 8);
		// obj.calculateWays();
		NDices obj2 = new NDices(3, new int[] { 1, 2, 3, 4 }, 8);
		obj2.calculateWaysAllDiceIncluded();
	}

	void calculateWays() {
		ways = new int[N][sum + 1]; // ways upto dice d and sum s

		// initialization
		// for sum = 0, no ways
		for (int i = 0; i < N; i++)
			ways[i][0] = 1; // this corresponds to the case when we don't pick
							// the dice. So sum 0 can be reach with N coins when
							// don't pick any 1 of them
		// initialize dice 1
		for (int s = 1; s <= sum; s++) {
			for (int f = 0; f < faces.length; f++) {
				ways[0][s] += (s - faces[f] == 0 ? 1 : 0);
			}
		}

		for (int i = 1; i < N; i++) {
			for (int s = 1; s <= sum; s++) {
				// each sum can be calculated by picking one out of A choices of
				// dice faces OR not picking that dice at all
				// case 1 : dice not picked
				ways[i][s] = getWaysSafely(i - 1, s);
				// case 2 : select one of Ath faces and calculate ways for
				// s-faceValue (including that dice)
				for (int f = 0; f < faces.length; f++) {
					ways[i][s] += getWaysSafely(i - 1, s - faces[f]); // dices
																		// are
																		// limited
																		// so
																		// i-1,
																		// had
																		// each
																		// dice
																		// had
																		// been
																		// unlimited
																		// then
																		// i-1
				}
			}
		}
		print();
	}

	int getWaysSafely(int x, int y) {
		if (x >= 0 && y >= 0) {
			return ways[x][y];
		}
		// one of x or y index is negative,return 0
		return 0;
	}

	void calculateWaysAllDiceIncluded() {
		ways = new int[N][sum + 1]; // ways upto dice d and sum s

		// initialization
		// for sum = 0, no ways
		for (int i = 0; i < N; i++)
			ways[i][0] = 0; // #CHANGE: We need to pick all N coins to reach a
							// particular sum, since coins don't have a value 0,
							// hence this will be 0
		// initialize dice 1
		for (int s = 1; s <= sum; s++) {
			for (int f = 0; f < faces.length; f++) {
				ways[0][s] += (s - faces[f] == 0 ? 1 : 0);
			}
		}

		for (int i = 1; i < N; i++) {
			for (int s = 1; s <= sum; s++) {
				// each sum can be calculated by picking one out of A choices of
				// dice faces OR not picking that dice at all
				// case 1 : dice not picked
				// ways[i][s] = getWaysSafely(i-1,s); //#CHANGE

				// case 2 : select one of Ath faces and calculate ways for
				// s-faceValue (including that dice)
				for (int f = 0; f < faces.length; f++) {
					ways[i][s] += getWaysSafely(i - 1, s - faces[f]); // dices
																		// are
																		// limited
																		// so
																		// i-1,
																		// had
																		// each
																		// dice
																		// had
																		// been
																		// unlimited
																		// then
																		// i-1
				}
			}
		}
		print();
	}

	void print() {
		for (int i = 0; i < N; i++) {
			System.out.println("Dice included upto = " + (i + 1));
			for (int s = 1; s <= sum; s++) {
				System.out.print("     Sum=" + s);
				System.out.print(" ways:" + ways[i][s]);
			}
			System.out.println();
		}
	}
}
