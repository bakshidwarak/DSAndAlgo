package arrays.and.adhoc.assignment.runlengthencoding;

import java.io.IOException;
import java.util.Scanner;

/***
 * Run Length Encoding Simple version of the problem: Compress a string (only
 * has alphabet characters), with basic encoding, where you simply count the
 * number of repeated characters. Then also write a routine to decompress it.
 * 
 * e.g. Input: "AAAAA" Output: "5A"
 * 
 * Input: "BAAAB" Output: "B3AB"
 * 
 * Input: "ABAB" Output: "ABAB" [We are not concerned about characters repeating
 * in groups]
 * 
 * Solution: Compression solution to this is very simple. It pretty much needs
 * one loop. Decompression is equally simple. Let us know if that is not clear.
 * 
 * Important twists to the problem: String can have any character from the basic
 * ASCII set (ASCII values 0 to 127). i.e. it can now include numbers.
 * Compressed length must not exceed original length. It can be same or less.
 * 
 * Solution hint: Given that you cannot have numbers in your solution, can you
 * use something else? Is there a one-character solution? Can you make use of
 * higher order ASCII values?
 * 
 * Test cases are given for compression of 2nd (twisted) case.
 * 
 * @author dwaraknathbs
 *
 */
public class RunLengthEncoding {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		String res;
		String _strinput;
		try {
			_strinput = in.nextLine();
		} catch (Exception e) {
			_strinput = null;
		}

		res = RLE(_strinput);
		System.out.println(res);

	}

	/**
	 * Note: This looks like a trivial loop but we ought to be careful. Make
	 * sure we maintain three running variables, count, curr and prev. Every
	 * time prev is different from current add prev to the builder and prepend
	 * with count only if it is greater than 1
	 * 
	 * @param _strinput
	 * @return
	 */
	private static String RLE(String _strinput) {
		char[] chars = _strinput.toCharArray();
		int currCount = 1;
		/**
		 * First element
		 */
		char last = chars[0];
		char curr = 0;
		StringBuilder sb = new StringBuilder();

		/**
		 * Start from second element
		 */
		for (int i = 1; i < chars.length; i++) {

			curr = chars[i];
			if (curr == last) {
				currCount++;
			} else {
				/**
				 * If curr is not equal to prev we need to add to the builder
				 * after checking the count, if count was > 1 prepend count
				 * first
				 */
				if (currCount > 1) {
					sb.append(currCount);
				}
				sb.append(last);
				/**
				 * Make last item as current and set count to 1
				 */
				last = curr;
				currCount = 1;
			}
		}

		if (currCount > 1) {
			sb.append(currCount);
		}
		sb.append(last);
		return sb.toString();
	}
}
