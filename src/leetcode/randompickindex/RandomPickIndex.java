

package leetcode.randompickindex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


/**
 * Given an array of integers with possible duplicates, randomly output the
 * index of a given target number. You can assume that the given target number
 * must exist in the array. Note: The array size can be very large. Solution
 * that uses too much extra space will not pass the judge. Example: int[] nums =
 * new int[] {1,2,3,3,3}; Solution solution = new Solution(nums); // pick(3)
 * should return either index 2, 3, or 4 randomly. Each index should have equal
 * probability of returning. solution.pick(3); // pick(1) should return 0. Since
 * in the array only nums[0] is equal to 1. solution.pick(1);
 * 
 * @author dwaraknathbs
 */
public class RandomPickIndex {

    class Pair {
        int num;
        List<Integer> indices = new ArrayList<>();

        public Pair(int num) {
            this.num = num;
        }
    }

    HashMap<Integer, Pair> pairs = new HashMap<>();

    public RandomPickIndex(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            Pair p = pairs.get(nums[i]);
            if (p == null) {
                p = new Pair(nums[i]);
            }
            p.indices.add(i);
            pairs.put(nums[i], p);

        }

    }

    public int pick(int target) {
        Pair p = pairs.get(target);
        int randomIndex = (new Random()).nextInt(p.indices.size());
        return p.indices.get(randomIndex);

    }
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(nums); int param_1 = obj.pick(target);
 */
