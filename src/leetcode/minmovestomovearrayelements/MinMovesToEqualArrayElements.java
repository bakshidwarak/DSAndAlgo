package leetcode.minmovestomovearrayelements;

/**
 * 
 * 453. Minimum Moves to Equal Array Elements
 * 
 * Given a non-empty integer array of size n, find the minimum number of moves
 * required to make all array elements equal, where a move is incrementing n - 1
 * elements by 1.
 * 
 * Example:
 * 
 * Input: [1,2,3]
 * 
 * Output: 3
 * 
 * Explanation: Only three moves are needed (remember each move increments two
 * elements):
 * 
 * [1,2,3] => [2,3,3] => [3,4,3] => [4,4,4]
 * 
 * @author dwaraknathbs
 *
 */
public class MinMovesToEqualArrayElements {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * There is a bit of math involved. to solve this. Once we derive the math
	 * logic the code is straight forward.
	 * 
	 * Lets say sum is the sum of all the elements in the array. saw the number
	 * of moves we make is m. Say we get all the elements as x after the m
	 * moves. Now the new sum is equal to
	 * 
	 * <pre>
	 * sum+m*(n-1) --(1) As we add 1 to n-1 elements m times Also the sum after
	 * transformation is = n*x--(2) as after the transformations all the
	 * elements are x
	 * 
	 * from (1) and (2)
	 * 
	 * sum+m(n-1)=xn => sum+mn-m=xn; --(2A)
	 * 
	 * Also we know that x=min element added by 1 , m times hence x=min+m*1 =
	 * min+m ---(3)
	 * 
	 * equating (2A) and (3)
	 * 
	 * sum+mn-m=(m+min)*n; =>sum+mn-m=mn+n*min; =>sum-m=n*min =>m=sum-n*min
	 * 
	 * <pre>
	 * 
	 * 
	 * Number of Moves M= SUM+ N*( MIN ELEMENT IN THE ARRAY)
	 * 
	 * @param nums
	 * @return
	 */
	public int minMoves(int[] nums) {

		int minNum = Integer.MAX_VALUE;
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			minNum = Math.min(minNum, nums[i]);
			sum += nums[i];
		}

		return sum - nums.length * minNum;
	}

}
