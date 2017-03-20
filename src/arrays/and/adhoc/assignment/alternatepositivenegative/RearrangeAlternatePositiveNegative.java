package arrays.and.adhoc.assignment.alternatepositivenegative;

import java.util.Arrays;

/**
 * Alternating positive and negative Given an array containing both +ve and -ve
 * integers, return an array of alternating positive integers and negative
 * integers such that each set of integers are in the same order as in the input
 * array (stable). e.g. input {2, 3, -4, -9, -1, -7, 1, -5, -6} output {2, -4,
 * 3, -9, 1, -1, -7, -5, -6}.
 * 
 * Can you implement it without using any additional space?
 * 
 * 
 * Solution:
 * 
 * >>> A=[2, 3, -4, -9, -1, -7, 1, -5, -6] >>> for i in range(len(A)): ... j=0
 * ... while j+2 < len(A): ... S=[A[j], A[j+1], A[j+2]] ... S=[1 if x >=0 else
 * -1 for x in S] ... if S[0]==S[1] and S[1]!=S[2]: ...
 * A[j+1],A[j+2]=A[j+2],A[j+1] ... j+=1 ... >>> A [2, -4, 3, -9, 1, -1, -7, -5,
 * -6]
 * 
 * [With no additional space, you can't do better than O(N^2)]
 * 
 * @author dwaraknathbs
 *
 */
public class RearrangeAlternatePositiveNegative {

	public static void main(String[] args) {
		int[] a = new int[] { 1, 3, 5, -3, 2 };
		rearrange(a);
		Arrays.stream(a).forEach(System.out::print);

	}

	/**
	 * Basic idea is we use two pointers one for the current running index and
	 * other for the outofplace element which we are trying to fix
	 * 
	 * @param a
	 */
	private static void rearrange(int[] a) {

		/**
		 * Initialize out of place to a non-existing index
		 */
		int outOfPlace = -1;

		for (int i = 0; i < a.length; i++) {
			/**
			 * If outOfPlace element is not known yet. Check if the current
			 * element is out of position and set its index. Else keep
			 * continuing till we find the first out of place element
			 */
			if (outOfPlace == -1) {
				if ((i % 2 == 0 && a[i] < 0) || (i % 2 == 1 && a[i] >= 0)) {
					outOfPlace = i;
				}

			} else if (outOfPlace >= 0) {
				/**
				 * We already have one element that is out of position. Now we
				 * are finding an element that can fit its place. Once we find
				 * the place we rotate
				 */
				if ((a[i] >= 0 && a[outOfPlace] < 0) || (a[i] < 0 && a[outOfPlace] >= 0)) {
					/**
					 * Rotate the array from index till the outofposition by one
					 * element
					 */
					int tmp = a[i];
					for (int j = i; j > outOfPlace; j--) {
						a[j] = a[j - 1];
					}
					a[outOfPlace] = tmp;
					/**
					 * The postion next to the out of position element fixes
					 * itself in the rotation and the next outofposition element
					 * is the element after that
					 */
					if (i - outOfPlace >= 2)
						outOfPlace = outOfPlace + 2;
					else
						/**
						 * When we have found the fixing element right next to
						 * the outofposition element, virtually we have only
						 * swapped the values and hence the outofplace is reset
						 * to -1. It is just like a fresh array starting with
						 * the index
						 */
						outOfPlace = -1;
				}
			}

		}

	}

}
