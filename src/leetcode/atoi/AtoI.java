package leetcode.atoi;

public class AtoI {

	/**
	 * Conditions to consider
	 * 
	 * 1) The overflow. 2)The sign 3)+123 is a valid number too so we need to
	 * check for the sign
	 * 
	 * @param str
	 * @return
	 */
	public int myAtoi(String str) {

		long result = 0;
		if (str == null || str.length() == 0)
			return 0;
		boolean isNegative = false;

		str = str.trim();
		for (int i = 0; i < str.length(); i++) {
			if (i == 0 && str.charAt(i) == '-') {
				isNegative = true;
				continue;
			}
			if (str.charAt(i) == '+' && i == 0) {
				continue;
			}
			if (str.charAt(i) > '9' || str.charAt(i) < '0')
				break;
			result = (long) (result * 10 + Long.valueOf(str.charAt(i) - '0'));

			if (isNegative && (-1 * result) < Integer.MIN_VALUE)
				return Integer.MIN_VALUE;

			if (result > Integer.MAX_VALUE && !isNegative)
				return Integer.MAX_VALUE;

		}

		if (isNegative)
			result = result * -1;

		return (int) result;

	}

}
