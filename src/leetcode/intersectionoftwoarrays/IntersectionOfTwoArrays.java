package leetcode.intersectionoftwoarrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * 349. Intersection of Two Arrays
 * 
 * Given two arrays, write a function to compute their intersection.
 * 
 * Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * 
 * Note: Each element in the result must be unique. The result can be in any
 * order.
 * 
 * @author dwaraknathbs
 *
 */
public class IntersectionOfTwoArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * One approach is to use two hashsets.
	 * O(n) Space O(n)
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersection(int[] nums1, int[] nums2) {
		HashSet<Integer> intersection = new HashSet<Integer>();
		HashSet<Integer> numbersInBigArray = new HashSet<Integer>();
		int[] toHash = nums1;
		int[] toCompare = nums2;
		if (nums2.length > nums1.length) {
			toHash = nums2;
			toCompare = nums1;
		}
		for (int i = 0; i < toHash.length; i++) {
			numbersInBigArray.add(toHash[i]);
		}

		for (int j = 0; j < toCompare.length; j++) {
			if (numbersInBigArray.contains(toCompare[j])) {
				intersection.add(toCompare[j]);
			}
		}

		int[] result = new int[intersection.size()];
		int i = 0;
		for (int n : intersection) {
			result[i++] = n;
		}
		return result;
	}

	/**
	 * Sort both the arrays and manipulate pointers ( like in two sum)
	 * O(nlogn)
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersectionUsingPointers(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		Set<Integer> intersection = new HashSet<>();
		int i = 0;
		int j = 0;

		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] == nums2[j]) {
				intersection.add(nums1[i]);
				i++;
				j++;
			} else if (nums1[i] < nums2[j]) {
				i++;
			} else {
				j++;
			}
		}
		int[] result = new int[intersection.size()];
		int k = 0;
		for (int n : intersection) {
			result[k++] = n;
		}
		return result;
	}

}
