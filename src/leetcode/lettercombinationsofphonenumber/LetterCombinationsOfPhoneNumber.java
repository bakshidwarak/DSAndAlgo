package leetcode.lettercombinationsofphonenumber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible
 * letter combinations that the number could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given
 * below. Note that 1 does not map to any letters.
 * 
 * 
 * 
 * Example:
 * 
 * Input: "23" Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * 
 * Although the above answer is in lexicographical order, your answer could be
 * in any order you want.
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class LetterCombinationsOfPhoneNumber {

	HashMap<Character, String> map = new HashMap<>();

	public List<String> letterCombinations(String digits) {
		List<String> result = new ArrayList<>();
		StringBuilder current = new StringBuilder();
		map.put('2', "abc");
		map.put('3', "def");
		map.put('4', "ghi");
		map.put('5', "jkl");
		map.put('6', "mno");
		map.put('7', "pqrs");
		map.put('8', "tuv");
		map.put('9', "wxyz");

		if (digits.length() != 0)
			getLetters(digits, 0, map, result, current);

		return result;

	}

	public void getLetters(String digits, int index, HashMap<Character, String> map, List<String> result,
			StringBuilder current) {

		if (index == digits.length()) {
			result.add(current.toString());
			return;
		}

		Character curr = digits.charAt(index);
		char[] letters = map.get(curr).toCharArray();
		for (char ch : letters) {
			current.append(ch);
			getLetters(digits, index + 1, map, result, current);
			current.deleteCharAt(current.length() - 1);
		}
	}

}