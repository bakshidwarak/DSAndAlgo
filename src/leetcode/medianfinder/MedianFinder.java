

package leetcode.medianfinder;

import java.util.PriorityQueue;


/**
 * Median is the middle value in an ordered integer list. If the size of the
 * list is even, there is no middle value. So the median is the mean of the two
 * middle value. Examples: [2,3,4] , the median is 3 [2,3], the median is (2 +
 * 3) / 2 = 2.5 Design a data structure that supports the following two
 * operations: void addNum(int num) - Add a integer number from the data stream
 * to the data structure. double findMedian() - Return the median of all
 * elements so far. For example: addNum(1) addNum(2) findMedian() -> 1.5
 * addNum(3) findMedian() -> 2
 * 
 * @author dwbs
 */
public class MedianFinder {

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<Integer>((num1, num2) -> num1.compareTo(num2));
        maxHeap = new PriorityQueue<Integer>((num1, num2) -> num2.compareTo(num1));
    }

    public void addNum(int num) {

        if (maxHeap.size() == 0 || maxHeap.peek() > num)
            maxHeap.add(num);
        else
            minHeap.add(num);
        rebalance();

    }

    public void rebalance() {
        PriorityQueue<Integer> big = maxHeap.size() >= minHeap.size() ? maxHeap : minHeap;
        PriorityQueue<Integer> small = maxHeap.size() < minHeap.size() ? maxHeap : minHeap;
        if (big.size() - small.size() >= 2) {
            small.add(big.remove());
        }
    }

    /**
     * Basic idea is to use two Heaps one for items that are in lower half(
     * maxHeap) , and one for items that are in higher half(minHeap). We always
     * maintain both the heaps to be of either same size of size differed by 1.
     * So we constantly rebalance as we add a new number to the heap. At any
     * time the peek of both the heaps will give the middle two elements(if size
     * is odd) or peek of the heap with higher size will give the middle element
     * 
     * @return
     */
    public double findMedian() {
        PriorityQueue<Integer> big = maxHeap.size() > minHeap.size() ? maxHeap : minHeap;
        PriorityQueue<Integer> small = maxHeap.size() > minHeap.size() ? minHeap : maxHeap;
        if (big.size() == small.size()) {
            int num1 = small.peek();
            int num2 = big.peek();
            return (double) (num1 + num2) / 2;
        } else {
            return big.peek();

        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder(); obj.addNum(num); double param_2 =
 * obj.findMedian();
 */
