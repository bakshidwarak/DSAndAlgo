package string.printcombinationsofupperandlowercase;

import java.util.Arrays;

public class PrintCombinationsOfUpperAndLoweCase {

	public static void main(String[] args) {
		char[] chars = { 'a', 'b', 'c', 'd' };
		printCombinations(chars, 0);

	}

	private static void printCombinations(char[] s, int index) {
		//System.out.println(index);
		if (index == s.length) {
			for (int i = 0; i < s.length; i++) {
				System.out.print(s[i]);
			}
			System.out.println();
			return;
		}

		char tmp = s[index];
		s[index] = (char) (s[index] + 'A' - 'a');
		printCombinations(s, index + 1);
		s[index] = tmp;
		printCombinations(s, index + 1);
	}

}
