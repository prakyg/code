package programs.backtracking;

public class Parenthesis {
	public Parenthesis() {
		super();
	}

	public static void main(String[] args) {
		Parenthesis p = new Parenthesis();
		p.printParenthesis(4);
	}

	void printParenthesis(int count) {
		recParenthesis("(", count - 1, count);
		// if(soFar.equals("")){
		// soFar = soFar.append("(");
		// leftBrackets--;
		// }
	}

	void recParenthesis(String soFar, int leftBrackets, int rightBrackets) {
		if (rightBrackets == 0) {
			System.out.println(soFar);
		}

		if (leftBrackets > 0) {
			recParenthesis(soFar.concat("("), leftBrackets - 1, rightBrackets);
		}
		if (leftBrackets < rightBrackets && rightBrackets > 0) {
			recParenthesis(soFar.concat(")"), leftBrackets, rightBrackets - 1);
		}

	}
}
