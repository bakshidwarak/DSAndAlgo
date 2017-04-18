package sorting.assignment.charsinaasciistring;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Sort all characters in a string Sort an array of characters (ASCII only, not
 * UTF8).
 * 
 * Input: A string of characters, like a full English sentence, delimited by a
 * newline or NULL. Duplicates are okay. Output: A string of characters, in
 * sorted order of their ASCII values. You can overwrite the existing array.
 * 
 * Solution Complexity: Aim for linear time and constant additional space.
 * 
 * (What to understand from this problem: ASCII is great, because it's limited
 * to 256. Remember that for any input that is bound to a range)
 * 
 * Interview time: 15 minutes.
 * 
 * Solution: This is a trivial problem :-)
 * 
 * @author dwaraknathbs
 *
 */
public class SortCharsInAAsciiString {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String res;
		String _inString;
		try {
			_inString = in.nextLine();
		} catch (Exception e) {
			_inString = null;
		}

		res = sortCharacters(_inString);
		System.out.println(res);

	}

	/**
	 * As the range is closed, we could maintain an array of size 256 and keep
	 * incrementing the count when we see a character. Finally sweep the buffer
	 * array and print chars based on their number of occurrence
	 * 
	 * @param inString
	 * @return
	 */
	static String sortCharacters(String inString) {
		int[] buffer = new int[256];
		Arrays.fill(buffer, 0);
		char[] input = inString.toCharArray();

		for (int i = 0; i < input.length; i++) {
			buffer[input[i]]++;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < buffer.length; i++) {
			for (int k = 0; k < buffer[i]; k++) {
				sb.append((char) i);
			}
		}

		return sb.toString();

	}

}
