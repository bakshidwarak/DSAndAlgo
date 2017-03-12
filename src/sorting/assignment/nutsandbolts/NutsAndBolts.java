package sorting.assignment.nutsandbolts;

/**
 * In other words: You are given a collection of nuts of different size and
 * corresponding bolts. You can choose any nut & any bolt together, from which
 * you can determine whether the nut is larger than bolt, smaller than bolt or
 * matches the bolt exactly. However there is no way to compare two nuts
 * together or two bolts together. (i.e. we cannot sort all nuts or sort all
 * bolts). Write an algorithm to match each bolt to its matching nut.
 * 
 * You can make the following assumptions: 1. There are equal number of nuts and
 * bolts 2. A given nut uniquely matches a bolt. i.e. There are no extra
 * unmatched nuts or extra bolts. i.e. every nut has one and only one matching
 * bolt and vice-versa.
 * 
 * e.g. Input: N3,N2,N1,N4 B4,B2,B3,B1
 * 
 * Output (in any order): N1B1 N2B2 N3B3 N4B4
 * 
 * Test cases: Please hard-code test-cases in your main() method. That will be
 * more convenient to you in this problem. Ignore the existing dummy test-case.
 * 
 * (Goal: Understand the application of QuickSort. Apply the concept of a Pivot
 * and partitioning)
 * 
 * Solution complexity: Is the time/space complexity same as quicksort, or
 * worse, or better?
 * 
 * Interview time: 30 minutes.
 * 
 * Solution: http://www.geeksforgeeks.org/nuts-bolts-problem-lock-key-problem/
 * 
 * @author dwaraknathbs
 *
 */
public class NutsAndBolts {

	public static void main(String[] args) {
		int[] bolts = { 3, 8, 5, 4, 2 };
		int[] nuts = { 5, 2, 8, 3, 4 };
		pairNutsAndBolts(nuts, bolts, 0, nuts.length - 1);
		printPairs(nuts, bolts);

	}

	private static void printPairs(int[] nuts, int[] bolts) {
		for (int i = 0; i < nuts.length; i++) {
			System.out.println("[" + bolts[i] + "," + nuts[i] + "]");
		}

	}

	/**
	 * Basic idea is to use the concept of partitioning in quicksort
	 * 
	 * Partition either the bolts array (it could also be other way around) and
	 * find the index of the pivot element
	 * 
	 * Use the pivot element from the first step as pivot to partion the other
	 * array
	 * 
	 * Recursively partition to eventually sort the two arrays.
	 * 
	 * @param nuts
	 * @param bolts
	 * @param low
	 * @param high
	 */
	private static void pairNutsAndBolts(int[] nuts, int[] bolts, int low, int high) {
		if (low >= high)
			return;

		int boltPivot = bolts[low];
		int boltPivotPosition = partition(bolts, low, high, boltPivot);
		int nutsPivotPosition = partition(nuts, low, high, bolts[boltPivotPosition]);

		pairNutsAndBolts(nuts, bolts, low, boltPivotPosition - 1);
		pairNutsAndBolts(nuts, bolts, boltPivotPosition + 1, high);

	}

	/**
	 * Partition is a little different from quicksort. Here the index of the
	 * pivot element is not known by the value is known.
	 * 
	 * Hence when we find an element equal to that of the pivot we swap iot with
	 * the low element and continue the loop
	 * 
	 * Invariant element[i]< pivot < element[j]
	 * 
	 * @param item
	 * @param low
	 * @param high
	 * @param pivot
	 * @return
	 */
	private static int partition(int[] item, int low, int high, int pivot) {

		int i = low;
		int j = i + 1;
		while (j <= high) {
			if (item[j] < pivot) {
				swap(item, i + 1, j);
				i++;
			} else if (item[j] == pivot) {
				swap(item, low, j);
				continue;
			}

			j++;
		}
		swap(item, i, low);
		return i;
	}

	private static void swap(int[] item, int i, int j) {
		int temp = item[i];
		item[i] = item[j];
		item[j] = temp;

	}

}
