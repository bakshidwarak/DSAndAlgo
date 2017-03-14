package sorting.assignment.mergeksortedlists;

import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Merge K sorted arrays, size N each This is a popular facebook problem: Given
 * K sorted arrays of size N each, merge them and print the sorted output.
 * Assume N is very large compared to K. N may not even be known. i.e. the
 * arrays could be just sorted streams, e.g. timestamp streams For example:
 * 
 * Input: K = 3, N = 4 arr[][] = { {1, 3, 5, 7}, {2, 4, 6, 8}, {0, 9, 10, 11}} ;
 * 
 * First parameter: How many arrays Second parameter: Length of each array
 * 
 * Output: 0 1 2 3 4 5 6 7 8 9 10 11
 * 
 * Repeats are allowed. Negative numbers and zeros are allowed. Assume all
 * arrays are sorted in the same order (asc or desc). Preserve that sort order
 * in output.
 * 
 * Hint: Realize that you don't need to access all N*K elements in order to
 * merge. Merging can start with fewer elements.
 * 
 * Solution runtime: Optimal known solution is NKLog(K).
 * 
 * Solution:
 * https://leetcode.com/discuss/9279/a-java-solution-based-on-priority-queue
 * 
 * [Extra credit: Implement the Priority Queue instead of using existing library
 * functions.]
 * 
 * @author dwaraknathbs
 * 
 */
public class MergeKSortedLists {

	/**
	 * My Heap Implementation
	 * 
	 * @author dwaraknathbs
	 *
	 * @param <T>
	 */
	static class MyHeap<T> {

		static enum HeapType {
			MIN_HEAP(-1), MAX_HEAP(1);
			/**
			 * Multiplier is the value used to generically determine the heap
			 * property
			 */
			int multiplier;

			HeapType(int val) {
				multiplier = val;
			}

			public int multiplier() {
				return multiplier;
			}

			public void setMultiplier(int multiplier) {
				this.multiplier = multiplier;
			}

		}

		private int capacity;
		private int size = 0;
		private HeapType heapType;
		private Comparator<T> heapProperty;
		private T[] heap;

		public MyHeap(int capacity, HeapType heapType, Comparator<T> heapProperty) {
			super();
			this.capacity = capacity;
			this.heapType = heapType;
			heap = (T[]) new Object[capacity];

			this.heapProperty = heapProperty;
		}

		private int getParent(int index) {
			return index % 2 == 0 ? (index - 2) / 2 : (index - 1) / 2;
		}

		private int getLeft(int index) {
			return 2 * index + 1;

		}

		private int getRight(int index) {
			return 2 * index + 2;
		}

		public void add(T number) throws Exception {
			if (size == capacity)
				throw new Exception("Heap is full");

			heap[size] = number;
			size++;
			heapifyUp();
		}

		public T poll() throws Exception {
			if (size == 0)
				throw new Exception("Heap is empty");
			T returnNumber = heap[0];
			heap[0] = heap[size - 1];
			size--;
			heapifyDown();
			return returnNumber;
		}

		public T peek() {
			return heap[0];
		}

		private void heapifyUp() {
			int index = size - 1;
			while (index != 0 && heapProperty.compare(heap[index], heap[getParent(index)]) == heapType.multiplier) {
				swap(index, getParent(index));
				index = getParent(index);

			}

		}

		private void swap(int index, int parent) {
			T temp = heap[index];
			heap[index] = heap[parent];
			heap[parent] = temp;

		}

		private void heapifyDown() {
			int parent = 0;
			int left = getLeft(0);
			if (left < 0 || left > size - 1)
				return;
			T leftValue = heap[left];
			int right = getRight(0);

			int childToSwap = left;

			if (right > 0 && right < size - 1 && heapProperty.compare(leftValue, heap[right]) != heapType.multiplier) {
				childToSwap = right;
			}
			while (childToSwap < size
					&& heapProperty.compare(heap[parent], heap[childToSwap]) != heapType.multiplier) {
				swap(parent, childToSwap);
				parent = childToSwap;
				childToSwap = getLeft(parent);
			}
		}

		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return size == 0;
		}
	}

	/**
	 * Basic idea is to maintain a heap of size N. Initially we add the first
	 * element of each of the list to the heap. We create the resultant array by
	 * polling the heap. As we pick an element from the heap, we place the next
	 * element from the picked array into the heap. For every element added
	 * heapify is called.
	 * 
	 * runtime is O(n*k*logk)
	 * 
	 * @param _iarray
	 * @return
	 */
	private static int[] mergearrays(int[][] _iarray) {
		try {
			MyHeap.HeapType heapType = MyHeap.HeapType.MIN_HEAP;
			int maxArraySize = _iarray.length;
			int size = 0;
			/**
			 * A simple loop to determine the result size. Heap size can be the
			 * total number of arrays
			 */
			for (int i = 0; i < _iarray.length; i++) {

				size += _iarray[i].length;

				/**
				 * When we says N sorted list, the list could be sorted in
				 * ascending or descending. This loop peeks into one of the loop
				 * to find it it is forward sorted or backward and determines
				 * the heap type accordingly
				 */
				for (int j = 0, k = j + 1; k < _iarray[i].length && j < _iarray[i].length;) {
					if (_iarray[i][j] < _iarray[i][k]) {
						heapType = MyHeap.HeapType.MIN_HEAP;
						break;
					} else if (_iarray[i][j] > _iarray[i][k]) {
						heapType = MyHeap.HeapType.MAX_HEAP;
						break;
					} else {
						j++;
					}

				}

			}
			Comparator<Pair> heapProperty = (pair1, pair2) -> Integer.compare(pair1.arrayValue, pair2.arrayValue);

			MyHeap<Pair> heap = new MyHeap<>(maxArraySize, heapType, heapProperty);

			/**
			 * Adding first element of each list to the heap
			 */
			for (int i = 0; i < _iarray.length; i++) {
				heap.add(new Pair(_iarray[i][0], i, 0));
			}

			int[] result = new int[size];
			int j = 0;
			while (!heap.isEmpty()) {
				Pair current = heap.poll();
				result[j] = current.arrayValue;
				/**
				 * Add the next element from the polled array to the heap.
				 */
				if (current.elementIndex != _iarray[current.index].length - 1)
					heap.add(new Pair(_iarray[current.index][current.elementIndex + 1], current.index,
							current.elementIndex + 1));
				j++;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	static class Pair {
		int arrayValue;
		int index;
		int elementIndex;

		public Pair(int arrayValue, int index, int elementIndex) {
			super();
			this.arrayValue = arrayValue;
			this.index = index;
			this.elementIndex = elementIndex;
		}

	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		int[] res;

		int _iarray_rows = 0;
		int _iarray_cols = 0;
		_iarray_rows = Integer.parseInt(in.nextLine().trim());
		_iarray_cols = Integer.parseInt(in.nextLine().trim());

		int[][] _iarray = new int[_iarray_rows][_iarray_cols];
		for (int _iarray_i = 0; _iarray_i < _iarray_rows; _iarray_i++) {
			for (int _iarray_j = 0; _iarray_j < _iarray_cols; _iarray_j++) {
				_iarray[_iarray_i][_iarray_j] = in.nextInt();

			}
		}

		if (in.hasNextLine()) {
			in.nextLine();
		}

		res = mergearrays(_iarray);
		for (int res_i = 0; res_i < res.length; res_i++) {
			System.out.println(String.valueOf(res[res_i]));

		}

	}

}
