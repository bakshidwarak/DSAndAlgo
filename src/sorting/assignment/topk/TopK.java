package sorting.assignment.topk;

import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Top K [Popular Facebook problem and a Computer Science classic, which you may
 * have touched in the class also]
 * 
 * Problem statement: Find K largest elements from a given stream of numbers. By
 * definition, we don't know the size of the input stream. Hence produce K
 * largest elements seen so far, at any given time.
 * 
 * Input may or may not be sorted and could have duplicates Represent input
 * stream as an array. Don't rely on its size. Feel free to use built-in
 * functions if you need a specific data-structure. If your output is correct,
 * but a test-case is failing because order of output elements is different,
 * then don't worry about it. Move on.
 * 
 * =====
 * 
 * 
 * Interview time: 45 minutes
 * 
 * Solution(s):
 * http://www.geeksforgeeks.org/k-largestor-smallest-elements-in-an-array/
 * (Solution #6 is the only one that'll work when size of the input stream is
 * not known)
 * 
 * [Trivia: When size of input array (stream) is known to be N, then either #5
 * or #6 will work. Both solutions have same complexity, but #6 will work faster
 * because it doesn't muck with the entire input, and only deals with K elements
 * separately]
 * 
 * @author dwaraknathbs
 *
 */

public class TopK {
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
			while (childToSwap < size && heapProperty.compare(heap[parent], heap[childToSwap]) != heapType.multiplier) {
				swap(parent, childToSwap);
				parent = childToSwap;
				childToSwap = getLeft(parent);
			}
		}

		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return size == 0;
		}

		public void replaceHead(T number) {
			heap[0] = number;
			heapifyDown();

		}
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		int[] res;

		int _iStream_size = 0;
		_iStream_size = Integer.parseInt(in.nextLine().trim());
		int[] _iStream = new int[_iStream_size];
		int _iStream_item;
		for (int _iStream_i = 0; _iStream_i < _iStream_size; _iStream_i++) {
			_iStream_item = Integer.parseInt(in.nextLine().trim());
			_iStream[_iStream_i] = _iStream_item;
		}

		int _iK;
		_iK = Integer.parseInt(in.nextLine().trim());

		res = topK(_iStream, _iK);
		for (int res_i = 0; res_i < res.length; res_i++) {
			System.out.println(String.valueOf(res[res_i]));

		}

	}

	private static int[] topK(int[] stream, int k) {
		try {
			return topKUsingMinHeap(stream, k);

			// topKUsingMaxHeap(stream, k);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return topKUsingOrderStatistic(stream, k);

	}

	/**
	 * This approach also needs the size of the stream to be known.
	 * 
	 * Basically it uses the same principle of finding the element of rank k.
	 * When we do we partition around the element k and in that respect sort the
	 * array
	 * 
	 * @param stream
	 * @param k
	 * @return
	 */
	private static int[] topKUsingOrderStatistic(int[] stream, int k) {

		int kthrankelement = findKthRankElementIndex(stream, k, 0, stream.length);

		// partitionAroundKthRankElement(stream,kthrankelement);
		int[] result = new int[k];
		for (int i = 0; i < k; i++) {
			result[i] = stream[stream.length - 1 - i];
		}
		return result;

	}

	private static int partitionAroundKthRankElement(int[] stream, int kthrankelement) {
		int pivot = stream[kthrankelement];
		int i = 0;
		int j = i + 1;

		while (j < stream.length) {

			if (stream[j] < pivot) {
				swap(stream, i + 1, j);
				i++;

			} else if (stream[j] == pivot) {
				swap(stream, 0, j);
				continue;
			}
			j++;
		}
		swap(stream, 0, i);
		return i;

	}

	private static void swap(int[] stream, int i, int j) {
		int temp = stream[i];
		stream[i] = stream[j];
		stream[j] = temp;

	}

	private static int findKthRankElementIndex(int[] stream, int k, int start, int end) {

		int pivot = partitionAroundKthRankElement(stream, start);
		if (pivot == k)
			return start;
		if (pivot > k) {
			return findKthRankElementIndex(stream, k, start, pivot - 1);
		} else
			return findKthRankElementIndex(stream, k, pivot + 1, end);
	}

	/**
	 * This approach uses a max heap. This approach will work only if the stream
	 * is finite. That is only if we already know the size of the stream
	 * 
	 * Basic idea is to construct the max heap with the numbers in the stream
	 * and print the first k elements from the heap.
	 * 
	 * Complexity O(nlogn+k) space O(n)
	 * 
	 * @param stream
	 * @param k
	 * @return
	 * @throws Exception
	 */
	private static int[] topKUsingMaxHeap(int[] stream, int k) throws Exception {
		Comparator<Integer> heapProperty = (o1, o2) -> o1.compareTo(o2);
		MyHeap<Integer> maxHeap = new MyHeap<>(stream.length, MyHeap.HeapType.MAX_HEAP, heapProperty);
		int[] result = new int[k];
		for (int i = 0; i < stream.length; i++) {
			maxHeap.add(stream[i]);
		}

		for (int i = 0; i < k; i++) {
			result[i] = maxHeap.poll();
		}
		return result;

	}

	/**
	 * This approach is by far the best approach when the stream size is not
	 * known. Maintain a min heap of k elements, the root of the heap always has
	 * the minimum element.
	 * 
	 * When you encounter a number greater than the root , replace the root and
	 * heapify so that the root always is the min element
	 * 
	 * Return the elements in the heap
	 * 
	 * Complexity O(klogk+(n-k)log(n-k)+k) space O(k)
	 * 
	 * @param stream
	 * @param k
	 * @return
	 * @throws Exception
	 */
	private static int[] topKUsingMinHeap(int[] stream, int k) throws Exception {
		Comparator<Integer> heapProperty = (o1, o2) -> Integer.compare(o1, o2);
		MyHeap<Integer> minHeap = new MyHeap<>(stream.length, MyHeap.HeapType.MIN_HEAP, heapProperty);

		int[] result = new int[k];
		for (int i = 0; i < k; i++) {
			minHeap.add(stream[i]);
		}

		for (int i = k; i < stream.length; i++) {
			int minElement = minHeap.peek();
			if (minElement < stream[i]) {
				minHeap.replaceHead(stream[i]);
			}
		}
		for (int i = k - 1; i >= 0; i--) {
			result[i] = minHeap.poll();
		}
		return result;
	}
}
