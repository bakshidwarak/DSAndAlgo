

package leetcode.mergearrays;

import java.util.Arrays;


/**
 * 88. Merge Sorted Array Given two sorted integer arrays nums1 and nums2, merge
 * nums2 into nums1 as one sorted array. Note: You may assume that nums1 has
 * enough space (size that is greater or equal to m + n) to hold additional
 * elements from nums2. The number of elements initialized in nums1 and nums2
 * are m and n respectively.
 * 
 * @author dwaraknathbs
 */
public class MergeArraysInPlace {

    public static void main(String[] args) {
        int m = 5;
        int n = 4;
        int[] nums1 = new int[9];
        nums1[0] = 2;
        nums1[1] = 4;
        nums1[2] = 6;
        nums1[3] = 8;
        nums1[4] = 10;
        int[] nums2 = new int[n];
        nums2[0] = 1;
        nums2[1] = 3;
        nums2[2] = 7;
        nums2[3] = 9;

        merge(nums1, m, nums2, n);

        Arrays.stream(nums1).forEach(System.out::println);

    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        /**
         * Shift the array right by length n
         */
        for (int i = m - 1; i >= 0 && n > 0; i--) {
            nums1[i + n] = nums1[i];
            nums1[i] = Integer.MAX_VALUE;
        }
        int k = 0;
        int i = 0;
        int j = n;

        /**
         * Like in mergesort use the nums1 array as temp array and keep merging
         * the arrays
         */
        while (j < n + m && i < n) {

            if (nums2[i] < nums1[j]) {
                nums1[k++] = nums2[i++];
            } else {
                nums1[k++] = nums1[j++];
            }
        }
        while (j < m) {
            nums1[k++] = nums1[j++];

        }
        while (i < n) {
            nums1[k++] = nums2[i++];

        }

        // usual merge
    }
}
