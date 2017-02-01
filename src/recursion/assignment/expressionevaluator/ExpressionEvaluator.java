package recursion.assignment.expressionevaluator;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Given a string of integers as input, put between each pair of digits, one of
 * {“”, “*”, “+”} such that the expression you get will evaluate to K (a number
 * also given as input). Putting an empty string ("") between two numbers means,
 * that the numbers are joined to form a new number (e.g. 1 "" 2 = 12)
 * 
 * Order of integers given as input needs to remain the same.
 * 
 * Input: 1. String of positive integers 2. Target K (given constant)
 * 
 * Output: All possible strings that evaluate to K
 * 
 * e.g. If the input string is "222", and your target (K) is "24", two possible
 * answers are:
 * 
 * 1. 22+2 (Here, we put the "" operator in between first two 2s and then put a
 * plus between the last two) 2. 2+22 (Here, we put the plus operator between
 * first two 2s and then put the "" operator between the last two)
 * 
 * Realize that precedence of the operators matters. In higher to lower
 * precedence: 1. Join ("") 2. Multiplication (*) 3. Addition (+)
 * 
 * @author dwaraknathbs
 *
 */
public class ExpressionEvaluator {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String[] res;
		String _strDigits;
		try {
			_strDigits = in.nextLine();
		} catch (Exception e) {
			_strDigits = null;
		}

		int _iK;
		_iK = Integer.parseInt(in.nextLine().trim());

		res = expressionCreator(_strDigits, _iK);
		for (int res_i = 0; res_i < res.length; res_i++) {
			System.out.println(String.valueOf(res[res_i]));

		}

	}

	private static String[] expressionCreator(String _strDigits, int _iK) {
		ArrayList<String> paths = new ArrayList<>();
		char[] digitArray = _strDigits.toCharArray();

		generateExpressions(paths, digitArray, 0, _iK, "");
		return paths.toArray(new String[paths.size()]);
	}

	private static void generateExpressions(ArrayList<String> paths, char[] digitArray, int i, int k,
			String currentpath) {

		int valueSoFar = valueof(currentpath);
		// If the value has reached k and no more elements available to process, an expression has reached
		if (valueSoFar == k && i >= digitArray.length) {
			paths.add(currentpath + "=" + k);
			return;
		}
		//if value so far is not equal to k and all elements are exhausted. no path
		if (valueSoFar != k && i >= digitArray.length) {
			return;
		}
		//if value so far is greater than k no point processing
		if (valueSoFar > k) {
			return;
		}

		//if value so far is less than k, recursively append to path and check for the value
		if (valueSoFar < k) {
			int j = i + 1;

			//Need this check as a number is required before appending an operator
			if (!currentpath.isEmpty()) {
				generateExpressions(paths, digitArray, j, k, currentpath + "*" + digitArray[i]);
				generateExpressions(paths, digitArray, j, k, currentpath + "+" + digitArray[i]);
			}
			generateExpressions(paths, digitArray, j, k, currentpath + "" + digitArray[i]);
		}

	}

	/**
	 * Evaluates and returns the value of the current path
	 * @param currentpath
	 * @return
	 */
	private static int valueof(String currentpath) {
		if (currentpath.isEmpty())
			return 0;

		//Two stacks one for values and other for operators
		Stack<Integer> values = new Stack<>();
		Stack<Character> operators = new Stack<>();
		char[] pathArray = currentpath.toCharArray();

		for (int i = 0; i < pathArray.length;) {
			//If the current token is a number
			if (pathArray[i] >= '0' && pathArray[i] <= '9') {
				StringBuilder sb = new StringBuilder();
				sb.append(pathArray[i++]);
				//Keep checking for numbers(double or multi digit)
				while (i < pathArray.length && pathArray[i] >= '0' && pathArray[i] <= '9') {
					sb.append(pathArray[i++]);
				}
				values.push(Integer.parseInt(sb.toString()));
			} else if (pathArray[i] == '+' || pathArray[i] == '*') {
				//If the current token is an operator , check the precedence of the operator in the stack.
				if (!operators.isEmpty()) {
					char currentTop = operators.peek();
					
					if (currentTop == '*') {// if the operator has higher precedence( in this case its only *)
						operators.pop();
						
						//Pop the operands twice from the values stack and evaluate the val and push it back in the values stack
						int eval = values.pop() * values.pop();
						values.push(eval);
					}
				}
				operators.push(pathArray[i++]);

			}
		}
		//Process the stack
		while (!operators.isEmpty()) {
			char operand = operators.pop();
			if (operand == '*') {
				int eval = values.pop() * values.pop();
				values.push(eval);
			}
			if (operand == '+') {
				int eval = values.pop() + values.pop();
				values.push(eval);
			}
		}

		//Top of the values stack has the end outcome
		return values.pop();
	}

}
