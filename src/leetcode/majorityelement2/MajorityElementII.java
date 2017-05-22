

package leetcode.majorityelement2;

import java.util.ArrayList;
import java.util.List;


/**
 * Given an integer array of size n, find all elements that appear more than ⌊
 * n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.
 * 
 * @author dwaraknathbs
 */
public class MajorityElementII {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        if (nums.length == 0)
            return result;
        if (nums.length < 2) {
            result.add(nums[0]);
            return result;
        }

        int count1 = 0, count2 = 0;
        int ele1 = nums[0], ele2 = nums[1];
        for (int i = 0; i < nums.length; i++) {
            if (ele1 == nums[i])
                count1++;
            else if (ele2 == nums[i])
                count2++;
            else if (count1 == 0) {
                count1 = 1;
                ele1 = nums[i];
            } else if (count2 == 0) {
                count2 = 1;
                ele2 = nums[i];
            } else {
                count1--;
                count2--;
            }

        }
        populateResult(result, ele1, ele2, nums);

        return result;
    }

    public void populateResult(List<Integer> result, int ele1, int ele2, int[] nums) {
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == ele1) {
                count1++;
                continue;
            }
            if (nums[i] == ele2)
                count2++;
        }
        if (count1 > (nums.length / 3))
            result.add(ele1);
        if (count2 > (nums.length / 3))
            result.add(ele2);
    }

}
