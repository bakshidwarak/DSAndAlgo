package leetcode.reversepolishnotation;

import java.util.Stack;

/**
 * 
 * 150. Evaluate Reverse Polish Notation
 * 
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * 
 * Valid operators are +, -, *, /. Each operand may be an integer or another
 * expression.
 * 
 * Some examples: ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9 ["4", "13",
 * "5", "/", "+"] -> (4 + (13 / 5)) -> 6 Show Company Tags Show Tags Show
 * Similar Problems
 * 
 * @author dwaraknathbs
 *
 */
public class ReversePolishNotation {

	public static void main(String[] args) {
		String[] expression = { "4", "13", "-5", "/", "+" };
		System.out.println(evalRPN(expression));

	}

	public static int evalRPN(String[] tokens) {

		Stack<Integer> operands = new Stack<>();

		for (String str : tokens) {
			if (str.matches("-?\\d+")) {
				int num = Integer.parseInt(str);
				operands.push(num);
			} else {
				int operand1 = operands.pop();
				int operand2 = operands.pop();
				switch (str) {
				case "+":

					operands.push(operand1 + operand2);
					break;
				case "-":
					operands.push(operand2 - operand1);
					break;
				case "*":
					operands.push(operand1 * operand2);
					break;
				case "/":
					operands.push(operand2 / operand1);
					break;
				}

			}

		}
		return operands.pop();
	}
}
