package recursion.assignment.wildcard;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Wildcard Input: 10? Output: 101, 100
 * 
 * i.e. ? behaves like a wild-card. There are two possibilities for 10?, when
 * that ? is replaced with either 0 or 1.
 * 
 * Input: 1?0? Output: 1000, 1001, 1100, 1101
 * 
 * Please write a program that takes given strings as input and produces the
 * suggested output.
 * 
 * Suggested time: 20 minutes.
 * 
 * 
 * ======== Solution - Pseudo-ish code ======== // print the output if
 * (input.length == i) { System.out.println(input); return; }
 * 
 * // wild card character if (input[i] == WILD_CHAR) {
 * 
 * input[i] = '0'; // recursively call wildChar(input, i + 1);
 * 
 * input[i] = '1'; // recursively call wildChar(input, i + 1);
 * 
 * // set the number back // WILD_CHAR - ? input[i] = WILD_CHAR;
 * 
 * } else { wildChar(input, i + 1); } ========
 * 
 * @author dwaraknathbs
 *
 */
public class WildCard {
	public static void main(String[] args) {
		System.out.println("Test Case 1 : 1?1");
		HashSet<String> wildCard = getWildCards("10?");
		wildCard.forEach(System.out::println);
		
		
		System.out.println("Test Case 2 : 1?0?");
		HashSet<String> wildCard1 = getWildCards("1?0?");
		wildCard1.forEach(System.out::println);
		
		System.out.println("Test Case 3 : 1????");
		HashSet<String> wildCard2 = getWildCards("1????");
		wildCard2.forEach(System.out::println);
	}

	private static HashSet<String> getWildCards(String string) {
		char[] input = string.toCharArray();
		HashSet<String> wildCards = new HashSet();
		wildCardHelper(input, 0, wildCards);
		return wildCards;
	}

	private static void wildCardHelper(char[] input, int start, HashSet<String> wildCards) {
		// System.out.println(String.valueOf(input)+" "+start);
		if (start == input.length) {
			char[] output = Arrays.copyOf(input, input.length);
			String str = new String(output);
			if (!wildCards.contains(str) && !str.contains("?"))
				wildCards.add(str);
			return;
		}

		for (int i = start; i < input.length; i++) {
			if (input[i] == '?') {
				input[i] = '1';
				wildCardHelper(input, i + 1, wildCards);
				input[i] = '0';
				wildCardHelper(input, i + 1, wildCards);
				input[i] = '?';
			} else {
				wildCardHelper(input, i + 1, wildCards);
			}
		}

	}
}
