package recursion.assignment.palindromedecomposition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Given a string s, partition s such that every substring of the partition is a
 * palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * For example, given s = "aab", Return
 * 
 * [ ["aa","b"], ["a","a","b"] ]
 * 
 * 
 * @author dwaraknathbs
 * 
 * 
 *         Sample output for the input abba
 * 
 *         abba offset=0 Current Decompositions {}
 * 
 *         a IS a palindrome
 * 
 *         offset=1 Current Decompositions {a}
 * 
 *         b IS a palindrome
 * 
 *         offset=2 Current Decompositions {a|b}
 * 
 *         b IS a palindrome
 * 
 *         offset=3 Current Decompositions {a|b|b}
 * 
 *         a IS a palindrome
 * 
 *         Removing a
 * 
 *         Removing b
 * 
 *         offset=2 Current Decompositions {a|b} ba not a palindrome Removing b
 *         offset=1 Current Decompositions {a} bb IS a palindrome offset=3
 *         Current Decompositions {a|bb} a IS a palindrome Removing a Removing
 *         bb offset=1 Current Decompositions {a} bba not a palindrome Removing
 *         a offset=0 Current Decompositions {} ab not a palindrome offset=0
 *         Current Decompositions {} abb not a palindrome offset=0 Current
 *         Decompositions {} abba IS a palindrome Removing abba a|b|b|a a|bb|a
 *         abba
 *
 * 
 */
public class PalindromeDecomposition {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		String[] res;
		String _strInput;
		try {
			_strInput = in.nextLine();
		} catch (Exception e) {
			_strInput = null;
		}

		res = palindromicDecomposition(_strInput);
		for (int res_i = 0; res_i < res.length; res_i++) {
			System.out.println(String.valueOf(res[res_i]));

		}

	}

	private static String[] palindromicDecomposition(String _strInput) {
		List<List<String>> results = new ArrayList<>();
		List<String> decompositions = new ArrayList<>();

		decomposePalindromes(0, results, decompositions, _strInput);
		String[] toArray = new String[results.size()];
		List<String> collect = results.stream().map(e -> e.stream().collect(Collectors.joining("|")))
				.collect(Collectors.toList());
		return collect.toArray(toArray);
	}

	private static void decomposePalindromes(int offset, List<List<String>> results, List<String> decompositions,
			String _strInput) {

		if (offset == _strInput.length()) {
			ArrayList<String> tempResult = new ArrayList<>(decompositions);
			results.add(tempResult);
			return;
		}

		/**
		 * From offset to length keep checking the substring is a palindrome
		 */
		for (int i = offset + 1; i <= _strInput.length(); i++) {
			String substring = _strInput.substring(offset, i);
//			System.out.print("offset=" + offset + " ");
//			System.out
//					.print("Current Decompositions {" + decompositions.stream().collect(Collectors.joining("|")) + "}");
//			System.out.println();
			if (isPalindrome(substring)) {
				//System.out.println(substring + " IS a palindrome");
				decompositions.add(substring);

				decomposePalindromes(i, results, decompositions, _strInput);
				/**
				 * Need to remove the last element to continue processing the
				 * prevLength+1 substrings For example .. after first iteration
				 * where {a,b,b,a} is in the decompositions, need to remove 'a'
				 * to check if ba is a palindrome, then remove b again and
				 * process bba and so on
				 */
				//System.out.println(" Removing " + decompositions.get(decompositions.size() - 1));
				decompositions.remove(decompositions.size() - 1);
			} else {
				//System.out.println(substring + " not a palindrome");

			}

		}

	}

	

	private static boolean isPalindrome(String input) {
		int start = 0;
		int end = input.length() - 1;
		char[] stringArray = input.toCharArray();
		while (start < end) {

			if (stringArray[start++] != stringArray[end--]) {
				return false;
			}
		}

		return true;
	}
}
