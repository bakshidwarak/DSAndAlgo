package leetcode.romantoint;

import java.util.HashMap;

/**
 * Given a roman numeral, convert it to an integer.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * @author dwaraknathbs
 *
 */
public class RomanToInt {

	public int romanToInt(String s) {

		HashMap<Character, Integer> romanMap = new HashMap<>();
		romanMap.put('M', 1000);
		romanMap.put('D', 500);
		romanMap.put('C', 100);
		romanMap.put('L', 50);
		romanMap.put('X', 10);
		romanMap.put('V', 5);
		romanMap.put('I', 1);

		int sum = romanMap.get(s.charAt(0));
		for (int i = 1; i < s.length(); i++) {
			int val = romanMap.get(s.charAt(i));
			if (val > romanMap.get(s.charAt(i - 1))) {
				sum += romanMap.get(s.charAt(i)) - romanMap.get(s.charAt(i - 1));
				sum -= romanMap.get(s.charAt(i - 1));
			} else {
				sum += romanMap.get(s.charAt(i));
			}
		}

		return sum;
	}
}