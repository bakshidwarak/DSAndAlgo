package leetcode.nextclosesttime;

/**
 * 681. Next Closest Time Medium
 * 
 * 319
 * 
 * 528
 * 
 * Favorite
 * 
 * Share Given a time represented in the format "HH:MM", form the next closest
 * time by reusing the current digits. There is no limit on how many times a
 * digit can be reused.
 * 
 * You may assume the given input string is always valid. For example, "01:34",
 * "12:09" are all valid. "1:34", "12:9" are all invalid.
 * 
 * Example 1:
 * 
 * Input: "19:34" Output: "19:39" Explanation: The next closest time choosing
 * from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later. It is not
 * 19:33, because this occurs 23 hours and 59 minutes later. Example 2:
 * 
 * Input: "23:59" Output: "22:22" Explanation: The next closest time choosing
 * from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is
 * next day's time since it is smaller than the input time numerically.
 * 
 * @author dwaraknathbs
 *
 */
public class NextClosestTime {
	String nextClosestTime(String time) {
		int[] count = new int[10];
		for (char ch : time.toCharArray()) {
			if (ch == ':')
				continue;
			count[ch - '0'] = 1;
		}
		int numeric = getNumericTime(time);
		int generatedTime = numeric;
		do {
			numeric = (numeric + 1) % 1440;
			int[] newTime = getTimeFromNumeric(numeric);
			if (newTimeContainsExistingChars(newTime, count)) {
				return stringify(newTime);
			}
		} while (numeric != generatedTime);

		return "";

	}

	public String stringify(int[] time) {
		StringBuilder sb = new StringBuilder();
		sb.append(time[0]);
		sb.append(time[1]);
		sb.append(":");
		sb.append(time[2]);
		sb.append(time[3]);
		return sb.toString();
	}

	public boolean newTimeContainsExistingChars(int[] time, int[] count) {
		for (int ch : time) {
			if (count[ch] != 1)
				return false;
		}
		return true;
	}

	public int getNumericTime(String time) {
		String[] chars = time.split(":");
		int times = Integer.parseInt(chars[0]) * 60 + Integer.parseInt(chars[1]);
		return times;
	}

	public int[] getTimeFromNumeric(int num) {
		int minutes = num % 60;
		int hours = num / 60;
		int[] result = new int[4];
		result[0] = hours / 10;
		result[1] = hours % 10;
		result[2] = minutes / 10;
		result[3] = minutes % 10;

		return result;
	}

}
