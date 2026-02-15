# Subsets (LeetCode 78)

## Problem Statement
Given a set of distinct integers, nums, return all possible subsets (the power set).

**Note:** The solution set must not contain duplicate subsets.

## Examples
```
Example 1:
Input: nums = [1,2,3]
Output: [
  [],
  [1],
  [2],
  [3],
  [1,2],
  [1,3],
  [2,3],
  [1,2,3]
]

Example 2:
Input: nums = []
Output: [[]]

Example 3:
Input: nums = [0]
Output: [[], [0]]
```

## Key Insights
1. Power set has 2^n subsets where n is the length of the input array
2. For each element, we have two choices: include it or exclude it
3. This is a classic backtracking problem
4. We can build subsets incrementally: for each element, add it to all existing subsets
5. A recursive approach naturally explores this binary choice tree

## Algorithm Steps

### Approach: Recursive Backtracking
1. Create a helper function that maintains:
   - Current index in nums array
   - Current subset being built
   - Result list to store all subsets
2. Base case: When index reaches end of array, add current subset to result
3. For each index:
   - Add the element to current subset
   - Recursively explore further indices
   - Remove the element (backtrack)
   - Move to next index without adding current element
4. This naturally generates all 2^n combinations

## Complexity Analysis
- **Time Complexity:** O(2^n * n) - We generate 2^n subsets, and each takes O(n) to copy to result
- **Space Complexity:** O(n) for recursion depth (not counting output)

## ASCII Visualization

```
nums = [1, 2, 3]

Recursion Tree:
                    []
                   /  \
                  /    \
              [1]       []
             /   \      /  \
         [1,2]  [1]  [2]   []
         / \     / \   / \   / \
      [1,2,3][1,2][1,3][1][2,3][2][3][]

Process:
1. Start with [] at index 0
2. Add 1: [1], recurse from index 1
3. At index 1, add 2: [1,2], recurse from index 2
4. At index 2, add 3: [1,2,3], reached end, add to result
5. Backtrack, remove 3, no more elements
6. Backtrack, remove 2, no more at index 1
7. Backtrack, recurse from index 1 without adding 1
8. And so on...

Building process (level by level):
Start: [[]]
After 1: [[], [1]]
After 2: [[], [1], [2], [1,2]]
After 3: [[], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]]
```

## Code Walkthrough

```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> current = new ArrayList<>();
    helper(nums, 0, current, result);
    return result;
}

public void helper(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
    // Add current subset to result (includes empty set when index=0)
    result.add(new ArrayList<>(current));

    // Try adding each remaining element
    for (int i = index; i < nums.length; i++) {
        // Choose: add nums[i]
        current.add(nums[i]);
        // Explore: recurse with next index
        helper(nums, i + 1, current, result);
        // Unchoose: remove nums[i] for other combinations
        current.remove(current.size() - 1);
    }
}
```

## Edge Cases
1. Empty array: [] → [[]]
2. Single element: [1] → [[], [1]]
3. Two elements: [1,2] → [[], [1], [2], [1,2]]
4. Duplicate elements: [1,1,2] → May have duplicates if not handled
5. Large array: [1..20] → 2^20 = 1,048,576 subsets

## Iterative Approach (Alternative)

```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    result.add(new ArrayList<>());

    for (int num : nums) {
        int size = result.size();
        for (int i = 0; i < size; i++) {
            List<Integer> subset = new ArrayList<>(result.get(i));
            subset.add(num);
            result.add(subset);
        }
    }

    return result;
}
```

## Related Problems
- LeetCode 77: Combinations
- [Subsets II (With duplicates)](../subsetsII/notes.md)
- LeetCode 39: Combination Sum
- LeetCode 40: Combination Sum II
- LeetCode 17: Letter Combinations of a Phone Number
## Tags
- Array
- Backtracking
- Recursion
- Bit Manipulation
- Power Set
