package leetcode.FourSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

	class Four {
		int a;
		int b;
		int c;
		int d;

		public Four(int a, int b, int c, int d) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + a;
			result = prime * result + b;
			result = prime * result + c;
			result = prime * result + d;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Four other = (Four) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (a != other.a)
				return false;
			if (b != other.b)
				return false;
			if (c != other.c)
				return false;
			if (d != other.d)
				return false;
			return true;
		}

		private FourSum getOuterType() {
			return FourSum.this;
		}

		/**
		 * 18. 4Sum Medium 751 146
		 * 
		 * 
		 * Given an array nums of n integers and an integer target, are there
		 * elements a, b, c, and d in nums such that a + b + c + d = target?
		 * Find all unique quadruplets in the array which gives the sum of
		 * target.
		 * 
		 * Note:
		 * 
		 * The solution set must not contain duplicate quadruplets.
		 * 
		 * Example:
		 * 
		 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
		 * 
		 * A solution set is: [ [-1, 0, 0, 1], [-2, -1, 1, 2], [-2, 0, 0, 2] ]
		 * 
		 * @param nums
		 * @param target
		 * @return
		 */
		public List<List<Integer>> fourSum(int[] nums, int target) {

			Arrays.sort(nums);
			List<List<Integer>> result = new ArrayList<>();
			for (int i = 0; i < nums.length; i++) {

				for (int j = nums.length - 1; j > 0; j--) {
					int left = i + 1;
					int right = j - 1;
					int revised = target - (nums[i] + nums[j]);
					while (left < right) {

						if (nums[left] + nums[right] > revised) {
							right--;
						} else if (nums[left] + nums[right] == revised) {
							List<Integer> curr = new ArrayList<>();
							curr.add(nums[i]);
							curr.add(nums[left]);
							curr.add(nums[right]);
							curr.add(nums[j]);
							if (!result.contains(curr))
								result.add(curr);
							left++;
							right--;

						} else {
							left++;
						}
					}

				}

			}

			return result;

		}
	}
}
