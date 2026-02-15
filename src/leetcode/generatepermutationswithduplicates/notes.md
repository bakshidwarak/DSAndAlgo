# LeetCode 47: Permutations II

## Problem Statement

Given a collection of numbers, `nums`, that might contain duplicates, return **all possible unique permutations** in any order.

### Examples

**Example 1:**
```
Input: nums = [1,1,2]
Output: [[1,1,2],[1,2,1],[2,1,1]]
```

**Example 2:**
```
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
```

**Example 3:**
```
Input: nums = [1]
Output: [[1]]
```

**Constraints:**
- 1 <= nums.length <= 8
- -10 <= nums[i] <= 10

## Key Insights

1. **Duplicate Handling**: Need to avoid generating duplicate permutations
2. **HashSet for Tracking**: Use HashSet at each recursion level to track used elements
3. **Swap-based Generation**: Generate permutations by swapping elements
4. **Start Element Selection**: At each position, try each unique element as the start
5. **Backtracking**: Swap back after recursion to restore state

## Algorithm Steps

### Backtracking with HashSet

1. **Base Case**: When start index reaches array length
   - Create a copy of current array
   - Add to result list

2. **Recursive Case**:
   - Create HashSet for current level (tracks start elements tried)
   - For each position from start to end:
     - If element not already tried at this level:
       - Add element to HashSet
       - Swap element to start position
       - Recurse with start+1
       - Swap back (backtrack)

3. **Return** all collected permutations

## Complexity Analysis

- **Time Complexity**: O(n! * n)
  - n! permutations to generate
  - Each permutation takes O(n) to create copy
  - HashSet operations: O(1) amortized
  - Overall: O(n! * n)

- **Space Complexity**: O(n)
  - Recursion depth: O(n)
  - HashSet at each level: O(n)
  - Result storage not counted
  - Overall: O(n)

## Visual Explanation

### Example: nums = [1,1,2]

```
Permutation tree (avoiding duplicates):

                    [1,1,2]
                       |
        ┌──────────────┼──────────────┐
        1              1(skip)         2
       [1,1,2]                        [2,1,1]
        |                              |
    ┌───┴───┐                      ┌───┴───┐
    1       2                      1       1(skip)
  [1,1,2] [1,2,1]                [2,1,1]
    |       |
    2       1
 [1,1,2] [1,2,1]
  ✓       ✓

Permutations generated:
[1,1,2]
[1,2,1]
[2,1,1]

Note: Second '1' at top level is skipped (already tried)
```

### HashSet Usage

```
Level 0 (start=0):
  startElements = {}
  i=0: nums[0]=1, not in set
    Add 1 to set: {1}
    Swap(0,0): [1,1,2]
    Recurse...
  i=1: nums[1]=1, already in set {1}
    Skip! (avoids duplicate)
  i=2: nums[2]=2, not in set
    Add 2 to set: {1,2}
    Swap(0,2): [2,1,1]
    Recurse...

This prevents trying both 1's as the first element!
```

### Detailed Trace

```
nums = [1,1,2]

generatePermutations(nums, 0):
  startElements = {}

  i=0: nums[0]=1
    1 not in {}
    Add 1: startElements = {1}
    swap(0,0): [1,1,2]
    generatePermutations(nums, 1):
      startElements = {}
      i=1: nums[1]=1
        1 not in {}
        Add 1: {1}
        swap(1,1): [1,1,2]
        generatePermutations(nums, 2):
          startElements = {}
          i=2: nums[2]=2
            2 not in {}
            Add 2: {2}
            swap(2,2): [1,1,2]
            generatePermutations(nums, 3):
              Base case! Add [1,1,2] ✓
            swap(2,2): [1,1,2]

      i=2: nums[2]=2
        2 not in {1}
        Add 2: {1,2}
        swap(1,2): [1,2,1]
        generatePermutations(nums, 2):
          ...
          Add [1,2,1] ✓
        swap(2,1): [1,1,2]
    swap(0,0): [1,1,2]

  i=1: nums[1]=1
    1 in {1} -> SKIP!

  i=2: nums[2]=2
    2 not in {1}
    Add 2: {1,2}
    swap(0,2): [2,1,1]
    generatePermutations(nums, 1):
      ...
      Add [2,1,1] ✓
    swap(2,0): [1,1,2]

Result: [[1,1,2], [1,2,1], [2,1,1]]
```

## Code Walkthrough

```java
public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> permutations = new ArrayList<>();

    // Generate all unique permutations
    generatePermutations(nums, 0, permutations);

    return permutations;
}

public void generatePermutations(int[] nums, int start,
                                 List<List<Integer>> permutations) {
    // Base case: reached end of array
    if (start == nums.length) {
        // Create a copy of current permutation
        ArrayList<Integer> current = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            current.add(nums[i]);
        }
        permutations.add(current);
        return;
    }

    /*
     * Key insight: Use HashSet to track which elements have been
     * tried as the start element at this recursion level.
     * This prevents duplicate permutations when input has duplicates.
     */
    HashSet<Integer> startElements = new HashSet<>();

    // Try each element as the start element
    for (int i = start; i < nums.length; i++) {
        // Skip if we've already tried this value at this position
        if (!startElements.contains(nums[i])) {
            // Mark this value as tried
            startElements.add(nums[i]);

            // Swap current element to start position
            swap(nums, i, start);

            // Recursively generate permutations for remaining elements
            generatePermutations(nums, start + 1, permutations);

            // Backtrack: swap back to restore array
            swap(nums, start, i);
        }
    }
}

public void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

## Why HashSet is Needed

### Without HashSet (Wrong)

```
nums = [1,1,2]

Would generate:
[1,1,2] from first 1
[1,1,2] from second 1  <- DUPLICATE!
[1,2,1] from first 1
[1,2,1] from second 1  <- DUPLICATE!
[2,1,1]
[2,1,1]               <- DUPLICATE!

Result has duplicates!
```

### With HashSet (Correct)

```
nums = [1,1,2]

At start=0:
  Try 1 (first occurrence)
  Skip 1 (second occurrence - already in set)
  Try 2

Result: No duplicates!
[1,1,2]
[1,2,1]
[2,1,1]
```

## Alternative Approach: Sorting + Skip

```java
public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);  // Sort to group duplicates
    boolean[] used = new boolean[nums.length];
    backtrack(nums, new ArrayList<>(), used, result);
    return result;
}

private void backtrack(int[] nums, List<Integer> current,
                      boolean[] used, List<List<Integer>> result) {
    if (current.size() == nums.length) {
        result.add(new ArrayList<>(current));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        // Skip used elements
        if (used[i]) continue;

        // Skip duplicates: if previous same element not used yet
        if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;

        used[i] = true;
        current.add(nums[i]);
        backtrack(nums, current, used, result);
        current.remove(current.size() - 1);
        used[i] = false;
    }
}
```

## Edge Cases

1. **No Duplicates**: nums = [1,2,3]
   - Generate all 3! = 6 permutations

2. **All Same**: nums = [1,1,1]
   - Output: [[1,1,1]] (only one unique)

3. **Two Duplicates**: nums = [1,1,2,3]
   - Some permutations would be duplicate without HashSet

4. **Single Element**: nums = [1]
   - Output: [[1]]

5. **Two Elements Same**: nums = [1,1]
   - Output: [[1,1]]

6. **Large Input**: nums.length = 8
   - Up to 8! = 40320 permutations

## Related Problems

1. **LeetCode 46**: Permutations (no duplicates)
2. **LeetCode 31**: Next Permutation
3. **LeetCode 60**: Permutation Sequence
4. **LeetCode 77**: Combinations
5. **LeetCode 78**: Subsets
6. **LeetCode 90**: Subsets II (with duplicates)

## Tags

- Array
- Backtracking
- Recursion
- Permutation
- Hash Table
- Duplicate Handling
- Swap-based Algorithm
