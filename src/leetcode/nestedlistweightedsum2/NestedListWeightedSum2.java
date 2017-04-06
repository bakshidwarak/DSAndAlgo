

package leetcode.nestedlistweightedsum2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 364. Nested List Weight Sum II
 * 
 * Given a nested list of integers, return the sum of all integers in the list
 * weighted by their depth. Each element is either an integer, or a list --
 * whose elements may also be integers or other lists. Different from the
 * previous question where weight is increasing from root to leaf, now the
 * weight is defined from bottom up. i.e., the leaf level integers have weight
 * 1, and the root level integers have the largest weight. Example 1: Given the
 * list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
 * Example 2: Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at
 * depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
 * 
 * @author dwaraknath bs
 */
public class NestedListWeightedSum2 {

    static interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather
        // than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds
        // a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested
        // integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a
        // nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public int depthSumInverse(List<NestedInteger> nestedList) {

        int level = 0;
        Map<Integer, List<Integer>> result = new HashMap<>();
        depthSumInverseHelper(nestedList, level, result);
        int sum = 0;
        int key = 0;
        /**
         * Process the map level by level and keep multiplying its elements by
         * size-key. As the map is revserse ordered by level
         */
        while (result.containsKey(key)) {
            int minisum = 0;

            List<Integer> currentLevel = result.get(key);
            for (Integer num : currentLevel) {
                System.out.print(num + " ");
                minisum += num;
            }

            sum += minisum * (result.size() - key);
            key++;
        }

        return sum;

    }

    /**
     * Do a DFS to keep storing elements in every level
     * 
     * @param nestedList
     * @param level
     * @param result
     */
    public void depthSumInverseHelper(List<NestedInteger> nestedList, int level, Map<Integer, List<Integer>> result) {

        if (nestedList == null)
            return;
        /**
         * If an entry does not exists for a current level add an entry. This is
         * to make sure the hashmap has the size equal to max depth
         */
        if (!result.containsKey(level)) {
            List<Integer> newList = new ArrayList<>();
            result.put(level, newList);
        }
        for (NestedInteger element : nestedList) {
            if (element.isInteger()) {
                if (result.containsKey(level)) {
                    result.get(level).add(element.getInteger());
                }

            } else {
                depthSumInverseHelper(element.getList(), level + 1, result);
            }
        }
    }
}
