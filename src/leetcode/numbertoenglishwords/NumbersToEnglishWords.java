package leetcode.numbertoenglishwords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Convert a non-negative integer to its english words representation. Given
 * input is guaranteed to be less than 231 - 1.
 * 
 * For example, 123 -> "One Hundred Twenty Three" 12345 -> "Twelve Thousand
 * Three Hundred Forty Five" 1234567 -> "One Million Two Hundred Thirty Four
 * Thousand Five Hundred Sixty Seven"
 * 
 * @author dwaraknathbs
 *
 */
public class NumbersToEnglishWords {
	String[] ones = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
			"Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
	String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
	String[] bigs = { "", "Thousand", "Million", "Billion", "Trillion" };

	public String numberToWords(int num) {
		if (num == 0) {
			return "Zero";
		}
		ArrayList<String> strList = new ArrayList<>();
		int k = 0;
		while (num > 0) {

			int curr = num % 1000;

			StringBuilder internalSb = new StringBuilder();
			internalSb.append(hundred(curr));
			if (hundred(curr).trim() != "") {
				internalSb.append(" ");
			}
			num = num / 1000;
			if (curr == 0) {
				if (num == 0) {
					internalSb.append(bigs[k]);
				}
			} else {
				internalSb.append(bigs[k]);
			}

			k++;

			strList.add(internalSb.toString().trim());

		}

		Collections.reverse(strList);
		return strList.stream().filter(str -> !str.trim().equals("")).collect(Collectors.joining(" "));

	}

	public String hundred(int num) {
		if (num == 0) {
			return "";
		}
		ArrayList<String> strList = new ArrayList<>();

		int curr = num % 100;
		if (curr < 20) {
			strList.add(ones[curr]);
		} else {
			strList.add(ones[curr % 10]);
			strList.add(tens[curr / 10]);
		}
		num = num / 100;

		if (num != 0) {
			strList.add(ones[num] + " Hundred");
		}

		Collections.reverse(strList);
		return strList.stream().collect(Collectors.joining(" "));

	}
}
