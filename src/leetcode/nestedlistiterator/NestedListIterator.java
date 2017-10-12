package leetcode.nestedlistiterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 * 
 * Each element is either an integer, or a list -- whose elements may also be
 * integers or other lists.
 * 
 * Example 1: Given the list [[1,1],2,[1,1]],
 * 
 * By calling next repeatedly until hasNext returns false, the order of elements
 * returned by next should be: [1,1,2,1,1].
 * 
 * Example 2: Given the list [1,[4,[6]]],
 * 
 * By calling next repeatedly until hasNext returns false, the order of elements
 * returned by next should be: [1,4,6]
 * 
 * @author dwaraknathbs
 *
 */

public class NestedListIterator implements Iterator<Integer> {

	List<Integer> flattenedList = new ArrayList<>();
	int currentIndex = 0;

	public NestedListIterator(List<NestedInteger> nestedList) {
		addToList(nestedList, 0);
	}

	public void addToList(List<NestedInteger> nestedList, int index) {
		for (NestedInteger number : nestedList) {
			if (number.isInteger()) {
				flattenedList.add(number.getInteger());
				index++;
			} else {
				addToList(number.getList(), index);
			}
		}
	}

	@Override
	public Integer next() {
		return flattenedList.get(currentIndex++);
	}

	@Override
	public boolean hasNext() {
		return currentIndex < flattenedList.size();
	}
}

/**
 * // This is the interface that allows for creating nested lists. // You should
 * not implement it, or speculate about its implementation
 */
interface NestedInteger {

	// @return true if this NestedInteger holds a single integer, rather than a
	// nested list.
	public boolean isInteger();

	// @return the single integer that this NestedInteger holds, if it holds a
	// single integer
	// Return null if this NestedInteger holds a nested list
	public Integer getInteger();

	// @return the nested list that this NestedInteger holds, if it holds a
	// nested list
	// Return null if this NestedInteger holds a single integer
	public List<NestedInteger> getList();
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList); while (i.hasNext()) v[f()]
 * = i.next();
 */