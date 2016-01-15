package programs.dynamic;

//Find the number of ways to reach step n with given steps array.
//1+2 , 2+1 are different. So we want to find permuatations here
public class NumWaysVariableSteps {
	int[] stepSize;
	int n;
	int[] numWayToStepI;

	public NumWaysVariableSteps(int[] a, int x) {
		stepSize = a;
		n = x;
		numWayToStepI = new int[n + 1];
	}

	public static void main(String[] args) {
		NumWaysVariableSteps coinsCombinationToSum = new NumWaysVariableSteps(new int[] { 1, 2, 3 }, 4);
		coinsCombinationToSum.showCombinations();
	}

	void showCombinations() {
		// need to initialize sumOfComb[] like a fibonacci series for all
		// initial values of the coins array
		numWayToStepI[0] = 0;
		for (int i = 1; i <= n; i++) {
			int sum = 0;
			for (int j = 0; j < stepSize.length; j++) {
				if (i - stepSize[j] > 0)
					sum = sum + numWayToStepI[i - stepSize[j]];
				else if ((i - stepSize[j] == 0)) { // this is equivalent to
													// initializing a[0] with 1
					sum++;
				}
			}
			numWayToStepI[i] = sum;
		}
		for (int i = 0; i <= n; i++) {
			System.out.println("Possibilities for " + i + " sum = " + numWayToStepI[i]);
		}
	}

	void showCombinationsWithInit() {
		// need to initialize sumOfComb[] like a fibonacci series for all
		// initial values of the coins array
		initialize();
		for (int i = stepSize[0] + 1; i <= n; i++) {
			int sum = 0;
			for (int j = 0; j < stepSize.length; j++) {
				if (i - stepSize[j] > 0)
					sum = sum + numWayToStepI[i - stepSize[j]];
				else if ((i - stepSize[j] == 0)) {
					sum++;
				}
			}
			numWayToStepI[i] = sum;
		}
		for (int i = 0; i <= n; i++) {
			System.out.println("Possibilities for " + i + " sum = " + numWayToStepI[i]);
		}
	}

	void initialize() {
		// consider coins array to be always sorted, no denomination <=0
		int x = stepSize[0];
		numWayToStepI[x] = 1;
		x--;
		while (x >= 0) {
			numWayToStepI[x] = 0;
			x--;
		}
		// for (int i=0;i<coins.length;i++){
		//
		// }
	}
}
