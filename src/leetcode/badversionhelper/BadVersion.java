package leetcode.badversionhelper;

/**
 * 
 * 278. First Bad Version
 * 
 * You are a product manager and currently leading a team to develop a new
 * product. Unfortunately, the latest version of your product fails the quality
 * check. Since each version is developed based on the previous version, all the
 * versions after a bad version are also bad.
 * 
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first
 * bad one, which causes all the following ones to be bad.
 * 
 * You are given an API bool isBadVersion(version) which will return whether
 * version is bad. Implement a function to find the first bad version. You
 * should minimize the number of calls to the API.
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class BadVersion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	boolean isBadVersion(int version) {
		return Math.random() == version;
	}

	public int firstBadVersion(int n) {

		return badVersionHelper(0, n);
	}

	/**
	 * Simple binary search approach. However care must be taken to handle the
	 * Integer max values to avoid overflow
	 * 
	 * @param strt
	 * @param end
	 * @return
	 */
	int badVersionHelper(long strt, long end) {
		long start = strt;
		long n = end;
		if (start >= n) {
			return isBadVersion((int) start) ? (int) start : -1;
		}
		Long middle = (n + start) / 2;
		int mid = middle.intValue();
		if (isBadVersion(mid)) {
			if (!isBadVersion(mid - 1)) {
				return (int) mid;
			} else {
				return badVersionHelper(start, mid - 1);
			}
		} else {
			return (int) badVersionHelper(mid + 1, n);
		}
	}

}
