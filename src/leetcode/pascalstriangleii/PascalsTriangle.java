package leetcode.pascalstriangleii;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a non-negative index k where k ≤ 33, return the kth index row of the
 * Pascal's triangle.
 * 
 * Note that the row index starts from 0.
 * 
 * 
 * In Pascal's triangle, each number is the sum of the two numbers directly
 * above it.
 * 
 * Example:
 * 
 * Input: 3 Output: [1,3,3,1]
 * 
 * @author dwaraknathbs
 *
 */
public class PascalsTriangle {
	public List<Integer> getRow(int rowIndex) {
		if (rowIndex == 0) {
			List<Integer> list = new ArrayList<>();
			list.add(1);
			return list;
		}

		List<Integer> prev = getRow(rowIndex - 1);
		List<Integer> current = new ArrayList<>();
		current.add(prev.get(0));
		for (int i = 1; i < prev.size(); i++) {
			int sum = prev.get(i) + prev.get(i - 1);
			current.add(sum);
		}
		current.add(prev.get(prev.size() - 1));

		return current;

	}
}
