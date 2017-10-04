package leetcode.removeinvalidparanthesis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * Remove the minimum number of invalid parentheses in order to make the input
 * string valid. Return all possible results.
 * 
 * Note: The input string may contain letters other than the parentheses ( and
 * ).
 * 
 * <pre>
Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
 * </pre> 
 * 
 * @author dwaraknathbs
 *
 */
public class RemoveInvalidParanthesis {

	public List<String> removeInvalidParentheses(String s) {

		List<String> result = new ArrayList<>();
		char[] input = s.toCharArray();
		TreeMap<Integer, Set<String>> resultSet = new TreeMap<>();

		lcr(input, 0, resultSet, 0);
		Set<String> outcome = resultSet.firstEntry().getValue();
		return new ArrayList<>(outcome);

	}

	public void lcr(char[] input, int start, TreeMap<Integer, Set<String>> result, int removed) {
		if (start == input.length) {

			if (isValid(input)) {
				String current = new String(input);
				current = current.replaceAll("\\s", "");
				if (result.containsKey(removed)) {
					result.get(removed).add(current);
				} else {
					Set<String> newList = new HashSet<>();
					newList.add(current);
					result.put(removed, newList);
				}

			}
			return;
		}
		if (input[start] == '(' || input[start] == ')') {
			char temp = input[start];
			input[start] = ' ';
			lcr(input, start + 1, result, removed + 1);
			input[start] = temp;
		}

		lcr(input, start + 1, result, removed);
	}

	public boolean isValid(char[] input) {
		int count = 0;
		for (int i = 0; i < input.length; i++) {
			if (input[i] == '(') {
				count++;
			}
			if (input[i] == ')') {
				if (count == 0)
					return false;
				count--;
			}
		}
		return count == 0;
	}

}
