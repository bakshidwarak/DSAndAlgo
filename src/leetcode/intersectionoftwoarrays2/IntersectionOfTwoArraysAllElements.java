package leetcode.intersectionoftwoarrays2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * 350. Intersection of Two Arrays II
 * 
 * 
 * Given two arrays, write a function to compute their intersection.
 * 
 * Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * 
 * Note: Each element in the result should appear as many times as it shows in
 * both arrays. The result can be in any order. Follow up: What if the given
 * array is already sorted? How would you optimize your algorithm? What if
 * nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such
 * that you cannot load all elements into the memory at once?
 * 
 * @author dwaraknathbs
 *
 */
public class IntersectionOfTwoArraysAllElements {

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
		List<Integer> intersection = new ArrayList<Integer>();
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
	 * 
	 * This is same as the other intersection probem. Just that here we use a
	 * list instead of hashset O(nlogn)
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersectionUsingPointers(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		List<Integer> intersection = new ArrayList<>();
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
