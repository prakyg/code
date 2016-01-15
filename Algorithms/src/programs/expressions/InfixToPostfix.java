package programs.expressions;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class InfixToPostfix {
	public InfixToPostfix() {
		super();
	}

	public static void main(String[] args) {
		InfixToPostfix i = new InfixToPostfix();
		// String x = i.infixToPostfix("1+2+3");
		// i.infixToPostfix("3+4*2/( 1 - 5 ) ^ 2 ^ 3");
		// i.infixToPostfix("9 + 2 * 6");
		// i.infixToPostfix("9 * 2 + 9");
		// i.infixToPostfix("9 * ( 2 + 9 )");
		// i.infixToPostfix("9 * ( 2 + 7 ) / 9");
		i.infixToPostfix("11+12");
	}

	String infixToPostfix(String expression) {
		char[] tokens = expression.toCharArray();

		Queue<Character> queue = new ArrayDeque<Character>();
		Deque<Character> stack = new ArrayDeque<Character>();
		for (int i = 0; i < tokens.length; i++) {
			// whitespace check
			if (tokens[i] == ' ') {
				continue;
			}
			if (tokens[i] >= '0' && tokens[i] <= '9') {
				queue.add(tokens[i]);
			} else if (tokens[i] == '(')
				stack.push(tokens[i]);

			// Closing brace encountered, solve entire brace
			else if (tokens[i] == ')') {
				while (stack.peek() != '(')
					queue.add(stack.pop());
				stack.pop(); // pop the ) bracket
			} else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/'
					|| tokens[i] == '^') {
				boolean precedence = true;
				do {
					if (!stack.isEmpty()) {
						precedence = getPrecedence(tokens[i], stack.peek());
						if (!precedence) {
							// pop the operator at the top of the stack to queue
							queue.add(stack.pop());
						}
					}
				} while (precedence != true && !stack.isEmpty());
				// finally push tokens[i] onto the stack
				stack.push(tokens[i]);
			} else {
				// character not supported or corrupted
				// throw new Exception
			}
		} // for loop ends
		while (!stack.isEmpty()) {
			queue.add(stack.pop());
		}
		System.out.println("postfix expression is " + queue.toString());
		this.evaluatePostFix(queue);
		return queue.toString();
	}

	private boolean getPrecedence(char op1, char op2) {
		/*
		 * operator precedence associativity ^ 4 Right 3 Left / 3 Left + 2 Left
		 * ? 2 Left
		 */
		if (op1 == '+' || op1 == '-') {
			if (this.isOperator(op2))
				return false;
		} else if (op1 == '*' || op1 == '/') {
			if (op2 == '^' || op2 == '/' || op2 == '*') {
				return false;
			}
		} else { // op1 can only be ^ (see the if condition of calling fxn)
			// ^ doesn't has the highest precedence
		}
		return true;
	}

	void evaluatePostFix(Queue<Character> postFix) {
		Deque<Integer> stack = new ArrayDeque<Integer>();
		while (!postFix.isEmpty()) {
			Character ch = postFix.poll();
			if (!isOperator(ch)) {
				stack.push(Integer.parseInt("" + ch));
			} else {
				int t = calculateExpression(stack.pop(), stack.pop(), ch);
				stack.push(t);
			}
		}
		System.out.println("result = " + stack.pop());

	}

	int calculateExpression(int y, int x, char c) {
		int result = 0;
		switch (c) {
		case '+':
			result = x + y;
			break;
		case '-':
			result = x - y;
			break;
		case '/':
			result = x / y;
			break;
		case '*':
			result = x * y;
			break;
		case '^':
			result = (int) Math.pow(x, y);
			break;
		}
		return result;
	}

	boolean isOperator(char ch) {
		if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^') {
			return true;
		}
		return false;
	}
}
/*
 * The algorithm in detail
 * (https://en.wikipedia.org/wiki/Shunting-yard_algorithm)
 * 
 * While there are tokens to be read:
 * 
 * Read a token. If the token is a number, then add it to the output queue. If
 * the token is a function token, then push it onto the stack. If the token is a
 * function argument separator (e.g., a comma):
 * 
 * Until the token at the top of the stack is a left parenthesis, pop operators
 * off the stack onto the output queue. If no left parentheses are encountered,
 * either the separator was misplaced or parentheses were mismatched.
 * 
 * If the token is an operator, o1, then:
 * 
 * while there is an operator token, o2, at the top of the operator stack, and
 * either
 * 
 * o1 is left-associative and its precedence is less than or equal to that of
 * o2, or o1 is right associative, and has precedence less than that of o2,
 * 
 * then pop o2 off the operator stack, onto the output queue;
 * 
 * push o1 onto the operator stack.
 * 
 * If the token is a left parenthesis (i.e. "("), then push it onto the stack.
 * If the token is a right parenthesis (i.e. ")"):
 * 
 * Until the token at the top of the stack is a left parenthesis, pop operators
 * off the stack onto the output queue. Pop the left parenthesis from the stack,
 * but not onto the output queue. If the token at the top of the stack is a
 * function token, pop it onto the output queue. If the stack runs out without
 * finding a left parenthesis, then there are mismatched parentheses.
 * 
 * When there are no more tokens to read:
 * 
 * While there are still operator tokens in the stack:
 * 
 * If the operator token on the top of the stack is a parenthesis, then there
 * are mismatched parentheses. Pop the operator onto the output queue.
 * 
 * Exit.
 */
