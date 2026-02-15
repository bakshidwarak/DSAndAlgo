# LeetCode 47: Permutations II

## Problem Statement
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

## Examples

**Example 1:**
```
Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
```

**Example 2:**
```
Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```

## Key Insights

1. **Duplicate Handling**: Sort array to group duplicates
2. **Track Used**: Use set to track which indices have been used
3. **Skip Duplicates**: Skip using duplicate at same recursion level
4. **Backtracking**: Build permutations by choosing one element at a time

## Algorithm Steps

1. **Sort the array** to group duplicate elements together

2. **Use Set for Tracking**: Track which indices have been used in current permutation

3. **Backtracking Logic**:
   - For each index from 0 to n-1:
     - If index already used, skip
     - If index > 0 and same value as previous and previous not used, skip (avoid duplicate permutations)
     - Choose element at index
     - Mark as used
     - Recurse with index+1
     - Unmark as used (backtrack)
     - Remove element (backtrack)

4. **Base Case**: When permutation length == n, add to result

## Complexity Analysis

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(n! × n) - Generate all permutations, each takes O(n) |
| **Space Complexity** | O(n!) - Store all permutations + O(n) recursion |

- Number of permutations: n!/frequency
- For unique elements: n! permutations

## ASCII Visualization

```
Input: [1, 1, 2]

After sorting: [1, 1, 2]
Indices:       [0, 1, 2]

Backtracking tree:
                 []
            /     |     \
          1(0)   1(1)   2(2)
          /        |
        1(1)      2(2)    (skip 1(0) - duplicate at same level)
        /          |
      2(2)       1(0)
      /           |
    [1,1,2]     [1,2,1]


          1(0)
          /
        2(2)
        /
      1(1)
      /
    [1,2,1]

          2(2)
          /  \
       1(0)  1(1)
       /      /
     1(1)   1(0)
     /      /
  [2,1,1] [2,1,1] (same, but from different indices)

Actually, correct tree with skip logic:
                []
            /    |    \
          1(0)  1(1)  2(2)
          /       |     (1(0) also used)
        1(1)     2(2)   Skip 1(1) - prev 1 not used
        /         |
      2(2)      1(0)
      |          |
    [1,1,2]    [1,2,1]


         2(2)
         /
       1(0)  (1(1) skipped - prev not used)
       |
     1(1)
     |
   [2,1,1]

Permutations generated:
[1,1,2], [1,2,1], [2,1,1]
```

## Code Walkthrough

```java
public List<List<Integer>> permuteUnique(int[] nums) {
    // Sort to group duplicates
    Arrays.sort(nums);

    int[] current = new int[nums.length];
    List<List<Integer>> result = new ArrayList<>();
    Set<Integer> taken = new HashSet<>();

    // Start backtracking from index 0
    helper(nums, 0, current, result, taken);

    return result;
}

public void helper(int[] nums, int index, int[] current,
                   List<List<Integer>> result, Set<Integer> taken) {

    // Base case: permutation complete
    if (index == nums.length) {
        List<Integer> ans = new ArrayList<>();
        for (int num : current) {
            ans.add(num);
        }
        result.add(ans);
        return;
    }

    // Try each number
    for (int i = 0; i < nums.length; i++) {
        // Skip if already used
        if (taken.contains(i)) {
            continue;
        }

        // Skip duplicate: if same value as previous and previous not used
        if (i > 0 && nums[i - 1] == nums[i] && !taken.contains(i - 1))
            continue;

        // Choose
        current[index] = nums[i];
        taken.add(i);

        // Recurse
        helper(nums, index + 1, current, result, taken);

        // Backtrack
        taken.remove(i);
    }
}
```

**Execution for [1, 1, 2]:**
```
After sort: [1, 1, 2]

helper([1,1,2], 0, [], [], {})
  i=0: nums[0]=1
    Choose nums[0]=1
    current = [1]
    taken = {0}
    helper([1,1,2], 1, [1], [], {0})
      i=0: taken.contains(0)? Yes, skip
      i=1: nums[1]=1, nums[0]==nums[1] and !taken.contains(0)?
           nums[0]==1, nums[1]==1, taken={0}, so YES - skip
      i=2: nums[2]=2
        Choose nums[2]=2
        current = [1, 2]
        taken = {0, 2}
        helper([1,1,2], 2, [1,2], [], {0,2})
          i=0: taken.contains(0)? Yes, skip
          i=1: nums[1]=1
            Choose nums[1]=1
            current = [1, 2, 1]
            taken = {0, 1, 2}
            helper([1,1,2], 3, [1,2,1], result, {0,1,2})
              index == 3 == length
              Add [1, 2, 1] to result
          i=2: taken.contains(2)? Yes, skip
        Backtrack: taken = {0, 2}
      Backtrack: taken = {0}

  i=1: nums[1]=1, nums[0]==nums[1] and !taken.contains(0)?
       1==1 and 0 not in {}, so YES (0 not used) - skip

  i=2: nums[2]=2
    Choose nums[2]=2
    current = [2]
    taken = {2}
    ... continue similarly

Result: [[1,1,2], [1,2,1], [2,1,1]]
```

## Edge Cases

1. **No duplicates**: `[1,2,3]` → All 6 permutations
2. **All same**: `[1,1,1]` → Only 1 permutation
3. **Two same**: `[1,1,2]` → 3 permutations
4. **Single element**: `[1]` → 1 permutation
5. **Empty**: `[]` → 1 empty permutation

## Related Problems

1. **LeetCode 46** - Permutations (Without duplicates)
2. **LeetCode 31** - Next Permutation (Generate next permutation)
3. **LeetCode 60** - Permutation Sequence (k-th permutation)
4. **LeetCode 784** - Letter Case Permutation (Substring permutations)
5. **LeetCode 267** - Palindrome Permutation II (Permutation with constraint)

## Tags

`Backtracking` `Array` `Medium` `Google` `Amazon` `Microsoft` `Facebook`

## Alternative Approaches

### Approach 2: Swap-Based (Without using taken set)
```java
public void helper(int[] nums, int start, List<List<Integer>> result, Set<List<Integer>> seen) {
    if (start == nums.length) {
        result.add(new ArrayList<>(Arrays.asList(...)));
        return;
    }

    for (int i = start; i < nums.length; i++) {
        swap(nums, start, i);
        helper(nums, start + 1, result, seen);
        swap(nums, start, i);
    }
}
```
- Uses swapping instead of taken set
- Different backtracking approach

### Approach 3: Without Sorting (Track at each level)
```
- Don't sort, instead use set at each recursion level
- More memory but avoids sorting
- Time: O(n! × n)
- Space: O(n! × n)
```

## Implementation Notes

1. **Sorting is Key**: Groups duplicates for easier skipping
2. **Skip Logic**: If nums[i-1] == nums[i] and i-1 not used, skip
3. **Index Tracking**: Set tracks indices used, not values
4. **Backtracking**: Remove from set when unwind recursion

## Common Mistakes

1. **Wrong Skip Logic**: Must check if previous value is unused
2. **Integer Overflow**: No issue here, but good to verify
3. **Duplicates**: Without skip logic, will generate duplicates
4. **Set vs Boolean Array**: Set is cleaner, boolean array is faster

## Optimization Notes

1. **Early Termination**: None really applicable
2. **Memoization**: Not useful here
3. **Pruning**: Skip duplicate indices at each level

## Performance Comparison

```
Approach        Time        Space        Notes
Sorted + Set    O(n!×n)     O(n!+n)      Clean, recommended
Swap-based      O(n!×n)     O(n!)        More complex
Unsorted Set    O(n!×n)     O(n!+n)      Less efficient
```

## Notes

- Classic backtracking problem with duplicate handling
- Key insight: Sort to group duplicates, then skip smartly
- Foundation for understanding constraint-based permutation generation
- Tests understanding of recursion, sets, and backtracking
- Important interview problem
