package leetcode.readn;

/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * 
 * The return value is the actual number of characters read. For example, it
 * returns 3 if there is only 3 characters left in the file.
 * 
 * By using the read4 API, implement the function int read(char *buf, int n)
 * that reads n characters from the file.
 * 
 * Note: The read function will only be called once for each test case.
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class ReadNGivenRead4 {

	/**
	 * @param buf
	 *            Destination buffer
	 * @param n
	 *            Maximum number of characters to read
	 * @return The number of characters read
	 */
	public int read(char[] buf, int n) {

		int total = 0;

		for (int i = 0; i < n;) {

			char[] temp = new char[4];
			int currentBytes = read4(temp);
			System.out.println(currentBytes);
			int count = 0;
			if (currentBytes == 0) {
				break;
			}

			while (count < currentBytes && i < n) {
				buf[i++] = temp[count++];
			}

			total += currentBytes;

		}
		if (total > n)
			total = total - (total - n);
		return total;

	}

	private int read4(char[] temp) {
		// TODO Auto-generated method stub
		return 0;
	}
}