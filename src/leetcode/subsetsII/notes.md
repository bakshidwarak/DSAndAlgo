# Subsets II (LeetCode 90)

## Problem Statement
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

**Note:** The solution set must not contain duplicate subsets.

## Examples
```
Example 1:
Input: nums = [1,2,2]
Output: [
  [],
  [1],
  [1,2],
  [1,2,2],
  [2],
  [2,2]
]

Example 2:
Input: nums = [4,4,0]
Output: [
  [],
  [0],
  [4],
  [4,4],
  [0,4],
  [0,4,4]
]
```

## Key Insights
1. The main difference from Subsets I is handling duplicates
2. Key insight: Sort the array first so duplicates are adjacent
3. For duplicate elements at the same recursion level, process only the first one
4. Skip duplicates when iterating through elements at the same level
5. Rule: `if (i == index || nums[i] != nums[i-1])` ensures we skip duplicates

## Algorithm Steps

### Approach: Recursive Backtracking with Duplicate Handling
1. Sort the array first to group duplicates together
2. Create a helper function that maintains:
   - Current index in nums array
   - Current subset being built
   - Result list
3. Base case: Add current subset to result
4. For each index:
   - Only process element if it's the first in this level OR different from previous
   - Add element to current subset
   - Recurse with next index
   - Remove element (backtrack)
5. This ensures duplicates are only used once per position

## Complexity Analysis
- **Time Complexity:** O(2^n * n) - We generate 2^n subsets, each takes O(n) to copy
- **Space Complexity:** O(n) for recursion depth (not counting output)

## ASCII Visualization

```
nums = [1,2,2]
After sorting: [1,2,2]

Recursion Tree with Duplicate Handling:
                    []
                   /
              [1]     (skip further since index=0)
             /   \
         [1,2]  [1,2] (only first [1,2] branch)
         / \       \
      [1,2,2][1,2][1,2,2]

With duplicate skipping:
- At index 0: add 1
- At index 1: add first 2, process recursively
- At index 2: add second 2 only if we're building on the previous 2
- Skip the second 2 when at index 1 level (it's a duplicate at same level)

Condition check: if (i == index || nums[i] != nums[i-1])
- When i=1, nums[1]=2: i==index (1==1), process ✓
- When i=2, nums[2]=2: i!=index (2!=1), nums[2]==nums[1] (2==2), skip ✗

Actually, we get all subsets:
Starting from index 0:
- Skip [1]: continue from index 1
  - Add nums[1]=2: [2], continue from index 2
    - Add nums[2]=2: [2,2]
  - At index 2, nums[2]==nums[1], so skip
- Add nums[0]=1: [1]
  - At index 1, add nums[1]=2: [1,2]
    - At index 2, add nums[2]=2: [1,2,2]
  - At index 2, skip (duplicate)

Result: [[], [1], [1,2], [1,2,2], [2], [2,2]]
```

## Code Walkthrough

```java
public List<List<Integer>> subsetsWithDup(int[] nums) {
    // Sort to group duplicates together
    Arrays.sort(nums);

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> current = new ArrayList<>();
    helper(nums, 0, current, result);
    return result;
}

public void helper(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
    // Add current subset to result
    result.add(new ArrayList<>(current));

    // Try adding each remaining element
    for (int i = index; i < nums.length; i++) {
        // Skip duplicates at this recursion level
        // Only process if it's the first element or different from previous
        if (i == index || nums[i] != nums[i - 1]) {
            // Choose: add nums[i]
            current.add(nums[i]);
            // Explore: recurse with next index
            helper(nums, i + 1, current, result);
            // Unchoose: remove nums[i]
            current.remove(current.size() - 1);
        }
    }
}
```

## Edge Cases
1. Empty array: [] → [[]]
2. All duplicates: [1,1,1] → [[], [1], [1,1], [1,1,1]]
3. No duplicates: [1,2,3] → Same as Subsets I
4. Two elements, one duplicate: [1,1] → [[], [1], [1,1]]
5. Multiple different duplicates: [1,1,2,2] → All unique subsets

## Comparison with Subsets I

| Aspect | Subsets I | Subsets II |
|--------|-----------|-----------|
| Input | Distinct elements | May contain duplicates |
| Sorting | Not needed | Required |
| Duplicate check | Not needed | `if (i == index \|\| nums[i] != nums[i-1])` |
| Output | All 2^n subsets | Unique subsets only |

## Related Problems
- [Subsets](../subsets/notes.md)
- LeetCode 77: Combinations
- LeetCode 40: Combination Sum II
- LeetCode 47: Permutations II
- LeetCode 17: Letter Combinations of a Phone Number
## Tags
- Array
- Backtracking
- Recursion
- Duplicate Handling
- Power Set
