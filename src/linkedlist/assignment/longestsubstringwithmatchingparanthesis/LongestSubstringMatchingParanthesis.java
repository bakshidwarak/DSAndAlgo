package linkedlist.assignment.longestsubstringwithmatchingparanthesis;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Find the longest substring that has matching opening and closing parentheses.
 * Example: if the input string is "((((())(((()", the answer is: "(())"
 * 
 * Test cases assert(maxLenMatchingParen("()")==2);
 * assert(maxLenMatchingParen("((())") == 4);
 * assert(maxLenMatchingParen("()(())") == 6);
 * assert(maxLenMatchingParen("(((((")==0);
 * assert(maxLenMatchingParen(")))")==0); assert(maxLenMatchingParen("())")==2);
 * assert(maxLenMatchingParen("(()")==2);
 * assert(maxLenMatchingParen("((((())(((()")==4);
 * 
 * @author dwaraknathbs
 *
 */
public class LongestSubstringMatchingParanthesis {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		int res;
		String _strParenExpression;
		try {
			_strParenExpression = in.nextLine();
		} catch (Exception e) {
			_strParenExpression = null;
		}

		res = maxLenMatchingParen(_strParenExpression);
		System.out.print(String.valueOf(res));

	}

	private static int maxLenMatchingParen(String expression) {
		Stack<Integer> stack = new Stack<>();
		char[] array = expression.toCharArray();
		int prevStart = 0;

		int maxSum = 0;

		for (int i = 0; i < array.length; i++) {
			if (array[i] == '(') {
				stack.push(i);
			} else if (array[i] == ')') {
				if (stack.isEmpty()) {
					/**
					 * If a closing paranethisis is encountered and the stack is
					 * empty it means there is no corresponding beginning
					 * bracket and hence the start can be incremented by i
					 */
					prevStart = i + 1;
				} else {
					/**
					 * Pop the current element. The start of the current string
					 * is given by the top of the stack. and the length is the
					 * current index munis the start
					 */
					stack.pop();
					int start = stack.isEmpty() ? prevStart - 1 : stack.peek();
					int length = i - start;

					if (maxSum < length) {
						maxSum = length;
					}

				}
			}
		}

		return maxSum;

	}

}
