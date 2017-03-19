package linkedlist.assignment.paranthesisvalidate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Valid matching parans Write a function that checks if the given input string
 * has matching opening and closing parentheses. Valid parantheses are: ‘(‘,
 * ‘)’, ‘{‘, ‘}’, ‘[‘, ‘]’
 * 
 * Test cases:
 * 
 * 1. Input: “( ( 1 + 2 ) * 3 )”, Output: True
 * 
 * 2. Input: “( { 1 + 2 ) * 3 )”, Output: False
 * 
 * 3. Input: “( ( (1 + 2) * 3 ))”, Output: True
 * 
 * 
 * 4. Input: “{([])}”, Output: True
 * 
 * 
 * 5. Input: “} ( 1 * 2) + 3 * ( 5 - 6)”: False
 * 
 * Solution:
 * https://docs.google.com/document/d/1UBFl633FsCZLq2Vejihj2g80Vdl9BNF6-N1D4J-clsI/edit?usp=sharing
 * 
 * @author dwaraknathbs
 *
 */
public class ValidateParanthesis {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		boolean res;
		String _strExpression;
		try {
			_strExpression = in.nextLine();
		} catch (Exception e) {
			_strExpression = null;
		}

		res = hasMatchingParantheses(_strExpression);
		System.out.println(String.valueOf(res ? 1 : 0));

	}

	private static boolean hasMatchingParantheses(String _strExpression) {
		// TODO Auto-generated method stub
		return hasMatchingParanthesesUsingStack(_strExpression);
	}

	/**
	 * This is a simple approach by managing counters
	 * 
	 * @param _strExpression
	 * @return
	 */
	private static boolean hasMatchingParanthesesWithCounters(String _strExpression) {
		int paranCount = 0;
		int curlyCount = 0;
		int square = 0;
		char[] expr = _strExpression.toCharArray();

		for (int i = 0; i < expr.length; i++) {
			if (expr[i] == '{')
				curlyCount++;
			if (expr[i] == '}')
				curlyCount--;
			if (expr[i] == '[')
				square++;
			if (expr[i] == ']')
				square--;
			if (expr[i] == '(')
				paranCount++;
			if (expr[i] == ')')
				paranCount--;

			/**
			 * If unbalanced count will be negative
			 */
			if (curlyCount < 0 || square < 0 || paranCount < 0)
				return false;

		}

		/**
		 * When all the characters are parsed all the counters should be zero
		 */
		return curlyCount == 0 && square == 0 && paranCount == 0;
	}

	/**
	 * This is a simple approach by managing counters
	 * 
	 * @param _strExpression
	 * @return
	 */
	private static boolean hasMatchingParanthesesUsingStack(String _strExpression) {

		Stack<Character> stack = new Stack<>();

		char[] expr = _strExpression.toCharArray();

		for (int i = 0; i < expr.length; i++) {
			/**
			 * Push every opening braces in a stack
			 */
			if (expr[i] == '{' || expr[i] == '[' || expr[i] == '(')
				stack.push(expr[i]);

			if (expr[i] == '}' || expr[i] == ']' || expr[i] == ')') {
				/**
				 * When a closing brace is encountered if the stack is empty we
				 * encountered a closing brace before opening. Hence return
				 * false
				 */
				if (stack.isEmpty())
					return false;
				
				/**
				 * Peek the current top and check if the character is a complimenent to the character being closed
				 */
				char c = stack.peek();
				if (!isComplement(c, expr[i]))
					return false;
				stack.pop();
			}

		}

		/**
		 * When all the characters are parsed all the counters should be zero
		 */
		return stack.isEmpty();
	}

	private static boolean isComplement(char c, char d) {

		if (c == '{' && d == '}')
			return true;
		if (c == '[' && d == ']')
			return true;
		if (c == '(' && d == ')')
			return true;
		return false;
	}
}
