package leetcode.excelsheetcolumntitle;

/**
 * Given a positive integer, return its corresponding column title as appear in
 * an Excel sheet.
 * 
 * For example:
 * 
 * 1 -> A 2 -> B 3 -> C ... 26 -> Z 27 -> AA 28 -> AB
 * 
 * @author dwaraknathbs
 *
 */
public class ExcelSheetColumnTitle {

	public String convertToTitle(int n) {
		char[] chars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
				'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		StringBuilder sb = new StringBuilder();
		convert(n, chars, sb);

		return sb.toString();

	}

	public void convert(int n, char[] chars, StringBuilder sb) {
		if (n <= 26) {
			sb.append(chars[n - 1]);
			return;
		}

		n--;
		int firstPart = n / 26;
		int secondPart = n % 26;

		convert(firstPart, chars, sb);

		sb.append(chars[secondPart]);
	}
}