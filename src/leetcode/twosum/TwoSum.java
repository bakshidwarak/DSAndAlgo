package leetcode.twosum;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * 170. Two Sum III - Data structure design
 * 
 * 
 * Design and implement a TwoSum class. It should support the following
 * operations: add and find.
 * 
 * add - Add the number to an internal data structure. find - Find if there
 * exists any pair of numbers which sum is equal to the value.
 * 
 * For example, add(1); add(3); add(5); find(4) -> true find(7) -> false Show
 * Company Tags Show Tags Show Similar Problems
 * 
 * @author dwaraknathbs
 *
 */
public class TwoSum {
	
	private Comparator<Integer> comparator= new Comparator<Integer>() {
		
		@Override
		public int compare(Integer o1, Integer o2) {
			
			return o1.compareTo(o2);
		}
	};
	/**
	 * Using a HashMap for the numbers seem a decent option. 
	 * 
	 * We could sort the array but that might increase the complexity to nlogn instead of n.
	 * 
	 * Another option could be to keep storing the sum but the total number of sums = Nc2. Which is N(N+1)/2 .. O(n2) space
	 * 
	 * 
	 */

	Map<Integer, Integer> numList = new TreeMap<>(comparator);

	/** Initialize your data structure here. */
	public TwoSum() {

	}

	/** Add the number to an internal data structure.. */
	public void add(int number) {
		if (numList.containsKey(number)) {
			numList.put(number, numList.get(number) + 1);
		} else {
			numList.put(number, 1);
		}

	}

	/**
	 * Find if there exists any pair of numbers which sum is equal to the value.
	 */
	public boolean find(int value) {
		
		for (int n : numList.keySet()) {
			int difference = value - n;

			if (numList.containsKey(difference)) {
				int operandCount = numList.get(difference);
				if (difference == n && operandCount < 2)
					continue;
				return true;
			}

		}
		return false;
	}

	public static void main(String[] args) {
		TwoSum obj = new TwoSum();
		obj.add(0);
		obj.add(0);
		//obj.add(5);
		System.out.println(obj.find(0));
		System.out.println(obj.find(7));
	}
}
