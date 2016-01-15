package programs.dynamic;

//Find the number of ways to reach step n with given 1 or 2 steps.
//1+2 , 2+1 are different. So we want to find permuatations here
public class NumWaysSteps {
	int dest;
	int[] steps;

	public NumWaysSteps(int n) {
		dest = n;
	}

	public static void main(String[] args) {
		NumWaysSteps numWaysSteps = new NumWaysSteps(4);
		numWaysSteps.count();
	}

	void count() {
		int[] steps = new int[dest + 1];
		steps[0] = 0;
		steps[1] = 1;
		steps[2] = 2;
		for (int i = 3; i <= dest; i++) {
			steps[i] = steps[i - 1] + steps[i - 2];
		}
		System.out.println("Number of steps possible: " + steps[dest]);
	}
}
