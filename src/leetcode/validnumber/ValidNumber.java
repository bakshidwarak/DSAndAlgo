package leetcode.validnumber;

/**
 * 
 * 65. Valid Number
 * 
 * Validate if a given string is numeric.
 * 
 * Some examples: "0" => true " 0.1 " => true "abc" => false "1 a" => false
 * "2e10" => true Note: It is intended for the problem statement to be
 * ambiguous. You should gather all requirements up front before implementing
 * one.
 * 
 * @author dwaraknathbs
 *
 */
public class ValidNumber {

	public static void main(String[] args) {
		System.out.println(isNumber("3.25e"));

	}

	public static boolean isNumber(String s) {

		if (s == null || s.isEmpty() || s.trim().isEmpty())
			return false;

		char[] chars = s.trim().toCharArray();

		boolean isESeen = false;
		boolean isDecimalSeen = false;
		boolean isNumberSeen = false;
		boolean isPlusSeen = false;
		boolean isMinusSeen = false;

		for (int i = 0; i < chars.length; i++) {
			char ch = chars[i];
			if (ch < '0' || ch > '9') {
				boolean isLastCharacter = i == chars.length - 1;
				boolean isNotFirstCharacter = i != 0;
				if (ch == '+') {
					if (isPlusSeen || !isESeen && isNotFirstCharacter || isLastCharacter) {
						return false;
					}
					if (isNotFirstCharacter)
						isPlusSeen = true;
				} else if (ch == '-') {
					if (isMinusSeen || !isESeen && isNotFirstCharacter || isLastCharacter) {
						return false;
					}
					if (isNotFirstCharacter)
						isMinusSeen = true;
				} else if (ch == '.') {
					if (isDecimalSeen || isESeen) {
						return false;
					}
					isDecimalSeen = true;
				} else if (ch == 'e') {
					if (isESeen || !isNumberSeen || isLastCharacter) {
						return false;
					}
					isESeen = true;
				} else {
					return false;
				}
			} else {

				isNumberSeen = true;

			}

		}
		if ((isESeen || isDecimalSeen) && (!isNumberSeen))
			return false;
		return true;
	}
}
