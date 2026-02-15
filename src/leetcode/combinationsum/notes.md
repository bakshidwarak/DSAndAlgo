# 39. Combination Sum

## Problem Statement
Given a set of candidate numbers (candidates) without duplicates and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

The same repeated number may be chosen from candidates unlimited number of times.

### Examples
```
Example 1:
Input: candidates = [2,3,6,7], target = 7
Output: [[7], [2,2,3]]

Example 2:
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2], [2,3,3], [3,5]]
```

### Constraints
- All numbers (including target) will be positive integers
- The solution set must not contain duplicate combinations
- Same number can be used unlimited times
- 1 <= candidates.length <= 30
- 1 <= candidates[i] <= 200
- All elements of candidates are unique

## Approach & Solution

### Key Insights
1. **Backtracking**: Explore all possible combinations recursively
2. **Sorting first**: Enables early termination when candidate exceeds remaining target
3. **Reusability**: Can use same number multiple times (don't increment start index when recursing)
4. **Base cases**: target=0 (valid solution), target<0 (invalid path)

### Algorithm Steps
1. Sort the candidates array (enables pruning)
2. Initialize empty result list and current combination list
3. Start backtracking from index 0:
   - Base case: if target == 0, add current combination to result
   - Base case: if target < 0, return (invalid path)
   - For each candidate from start index to end:
     - If candidate <= target:
       - Add candidate to current combination
       - Recursively call with same start index (can reuse number), reduced target
       - Remove candidate from current combination (backtrack)
4. Return all valid combinations

### Complexity Analysis
- **Time Complexity**: O(2^t) where t = target/min(candidates)
  - In worst case, explore all possible combinations
  - Tree height is at most target/min_candidate
  - Each level can branch up to n times
  - Bounded by O(n^(target/min))
- **Space Complexity**: O(target/min)
  - Recursion depth is at most target/min_candidate
  - Current combination list size is at most target/min
  - Result list size depends on number of valid combinations

### Visualization
```
Example: candidates = [2,3], target = 7

Decision Tree (simplified):
                    []
        /                      \
      [2]                      [3]
    /     \                  /     \
  [2,2]   [2,3]          [3,3]   [3,3,3]→invalid
  /   \     |              |
[2,2,2] [2,2,3]→valid   [3,3,3]→invalid
   |
[2,2,2,2]→invalid

Step-by-step for target=7:
Start: current=[], target=7

Try 2: current=[2], target=5
  Try 2: current=[2,2], target=3
    Try 2: current=[2,2,2], target=1
      Try 2: invalid (2>1)
      Try 3: invalid (3>1)
    Try 3: current=[2,2,3], target=0 ✓ Found!
  Try 3: current=[2,3], target=2
    Try 2: current=[2,3,2], but we start from index, skip
    Try 3: invalid (3>2)

Try 3: current=[3], target=4
  Try 3: current=[3,3], target=1
    (both invalid)

Result: [[2,2,3], [7]]

Why sorting helps:
[2,3,6,7] sorted
When at [2,2], remaining=3:
  Check 2: valid
  Check 3: valid
  Check 6: 6>3, STOP (no need to check 7)
```

## Code Walkthrough

```java
public List<List<Integer>> combinationSum(int[] candidates, int target) {
    // Sort for optimization (early termination)
    Arrays.sort(candidates);

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> current = new ArrayList<>();

    helper(candidates, 0, target, current, result);

    return result;
}

public void helper(int[] nums, int start, int target,
                  List<Integer> current, List<List<Integer>> result) {

    // Base case: found valid combination
    if (target == 0) {
        result.add(new ArrayList<>(current));  // Create copy!
        return;
    }

    // Base case: invalid path
    if (target < 0) {
        return;
    }

    // Try each candidate from start index
    for (int i = start; i < nums.length; i++) {
        if (nums[i] <= target) {
            // Choose: add candidate
            current.add(nums[i]);

            // Explore: recursively search (i, not i+1, allows reuse)
            helper(nums, i, target - nums[i], current, result);

            // Unchoose: backtrack
            current.remove(current.size() - 1);
        }
        // Optimization: if nums[i] > target, all subsequent nums[j] > target too
        // So we can break early (array is sorted)
    }
}
```

**Key Implementation Details:**
- `Arrays.sort(candidates)`: Enables pruning optimization
- `helper(nums, i, ...)`: Pass `i` not `i+1` to allow number reuse
- `new ArrayList<>(current)`: Create copy when adding to result (current is modified)
- `current.remove(current.size()-1)`: Backtrack by removing last element

## Edge Cases
- **Single candidate**: [2], target=6 → [[2,2,2]]
- **No solution**: [2], target=3 → []
- **Target equals candidate**: [7], target=7 → [[7]]
- **Multiple same combinations**: Avoided by starting from index `i` not 0
- **Large target**: May have many combinations
- **Minimum target**: target=1 with candidates [1] → [[1]]

## Related Problems
- [**40. Combination Sum II**](../combinationsum2/notes.md): Each number used at most once
- **216. Combination Sum III**: Find k numbers that sum to n
- **377. Combination Sum IV**: Count number of combinations (order matters)
- **77. Combinations**: Generate all k-size combinations

## Tags
`array` `backtracking` `recursion` `medium`
