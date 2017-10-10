package leetcode.hindex;

/**
 * Given an array of citations (each citation is a non-negative integer) of a
 * researcher, write a function to compute the researcher's h-index.
 * 
 * According to the definition of h-index on Wikipedia: "A scientist has index h
 * if h of his/her N papers have at least h citations each, and the other N − h
 * papers have no more than h citations each."
 * 
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher
 * has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations
 * respectively. Since the researcher has 3 papers with at least 3 citations
 * each and the remaining two with no more than 3 citations each, his h-index is
 * 3.
 * 
 * Note: If there are several possible values for h, the maximum one is taken as
 * the h-index.
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class HIndex {

	public int hIndex(int[] citations) {

		/**
		 * Use Bucket sort. We create a bucket list. to maintain the count of
		 * every citation. For citations greater than the length of the papers
		 * we add to the last element in the buckt.
		 */
		int[] bucket = new int[citations.length + 1];
		for (int i = 0; i < citations.length; i++) {
			if (citations[i] < citations.length) {
				bucket[citations[i]]++;
			} else {
				bucket[citations.length]++;
			}
		}
		/**
		 * We traverse from the end and keep a count. Whenever the count becomes
		 * >=index we return the index as h-index
		 */
		int count = 0;
		for (int i = citations.length; i >= 0; i--) {
			count += bucket[i];
			if (count >= i) {
				return i;
			}
		}

		return 0;
	}

	/**
	 * Binary search approach.Given the array is already sorted
	 * 
	 * @param citations
	 * @param start
	 * @param end
	 * @return
	 */
	public int hIndexHelper(int[] citations, int start, int end) {
		System.out.println(start + " " + end);
		if (start > end) {

			return citations.length - start;

		}

		int mid = (start + end) / 2;
		if (citations[mid] == citations.length - mid) {
			return citations.length - mid;
		}

		if (citations[mid] > citations.length - mid) {
			return hIndexHelper(citations, start, mid - 1);
		}

		return hIndexHelper(citations, mid + 1, end);
	}

}
