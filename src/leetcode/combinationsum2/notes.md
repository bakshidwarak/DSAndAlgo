# 40. Combination Sum II

## Problem Statement
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

### Examples
```
Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: [[1,7], [1,2,5], [2,6], [1,1,6]]

Example 2:
Input: candidates = [2,5,2,1,2], target = 5
Output: [[1,2,2], [5]]
```

### Constraints
- All numbers (including target) will be positive integers
- The solution set must not contain duplicate combinations
- Each number may only be used ONCE per combination
- Array may contain duplicates
- 1 <= candidates.length <= 100
- 1 <= candidates[i] <= 50

## Approach & Solution

### Key Insights
1. **Backtracking with constraint**: Each element used at most once (pass i+1, not i)
2. **Sorting first**: Groups duplicates together, enables duplicate handling
3. **Skip duplicates**: If current element equals previous at same level, skip it
4. **Pruning**: Break early when candidate exceeds remaining target

### Algorithm Steps
1. Sort the candidates array (critical for duplicate handling)
2. Start backtracking from index 0:
   - Base case: if target < 0, return (invalid)
   - Base case: if target == 0, add current combination to result
   - Base case: if reached end of array, return
   - For each candidate from current index:
     - Skip if: not first element at this level AND equals previous element
     - If candidate <= target:
       - Add candidate to current combination
       - Recursively call with index+1 (each element used once), reduced target
       - Remove candidate (backtrack)
     - Else: break (array sorted, remaining elements too large)
3. Return all valid combinations

### Complexity Analysis
- **Time Complexity**: O(2^n)
  - In worst case, explore all possible subsets
  - Pruning and duplicate skipping reduce actual work
  - Each element has two choices: include or exclude
- **Space Complexity**: O(n)
  - Recursion depth is at most n
  - Current combination list size at most n
  - Result list size depends on number of valid combinations

### Visualization
```
Example: candidates = [1,1,2,5,6,7,10], target = 8

Sorted: [1,1,2,5,6,7,10]

Decision Tree (showing duplicate handling):
                        []
        /       |       |      \
      [1]      [1]     [2]    [5]...
              skip!
       |
    [1,1]
   /  |  \
[1,1,2] [1,1,5] [1,1,6] [1,1,7]
 /         |       ✓
...      ✓(=8)

Duplicate Skipping Logic:
At level with candidates [1,1,2,5,6,7,10]:
- i=0: use 1, explore [1,...]
- i=1: candidates[1]==candidates[0] AND i!=index
       Skip! (would create duplicate combinations)
- i=2: use 2, explore [2,...]
- ...

Example paths:
[1,1,6]: 1+1+6=8 ✓
[1,2,5]: 1+2+5=8 ✓
[1,7]:   1+7=8 ✓
[2,6]:   2+6=8 ✓

Why [1,1,6] and [1,1,6] from different 1's are same:
After sorting, we process left-to-right.
Using first 1: explores all combos starting with first 1
Using second 1: would duplicate same combos
Skip second 1 at same recursion level!
```

## Code Walkthrough

```java
public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    // Sort to group duplicates and enable pruning
    Arrays.sort(candidates);

    List<Integer> current = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();

    helper(candidates, target, 0, current, result);

    return result;
}

public void helper(int[] candidates, int target, int index,
                  List<Integer> current, List<List<Integer>> result) {

    // Base case: invalid path
    if (target < 0) {
        return;
    }

    // Base case: found valid combination
    if (target == 0) {
        result.add(new ArrayList<>(current));
        return;
    }

    // Base case: exhausted all candidates
    if (index == candidates.length)
        return;

    /**
     * Key: From current index, find all arrays with sum=target-candidates[i]
     */
    for (int i = index; i < candidates.length; i++) {
        // Skip duplicates at same recursion level
        if (i != index && candidates[i] == candidates[i - 1]) {
            continue;
        }

        if (candidates[i] <= target) {
            // Choose: add candidate
            current.add(candidates[i]);

            // Explore: use i+1 (each element used once)
            helper(candidates, target - candidates[i], i + 1, current, result);

            // Unchoose: backtrack
            current.remove(current.size() - 1);
        } else {
            // Pruning: sorted array, remaining elements too large
            break;
        }
    }
}
```

**Critical Implementation Details:**
- `Arrays.sort(candidates)`: Essential for duplicate detection
- `if (i != index && candidates[i] == candidates[i-1]) continue;`
  - Skips duplicates at same recursion level
  - `i != index` ensures we use first occurrence
- `helper(..., i + 1, ...)`: Pass i+1 (not i) since each element used once
- `break` when candidate > target: Optimization for sorted array

**Duplicate Handling Explained:**
```java
// Why this works:
// For [1,1,2] at level 0:
// i=0: Use first 1, explore all combos with it
// i=1: candidates[1]=1, candidates[0]=1, i!=0
//      Skip! (first 1 already explored all these combos)
// i=2: Use 2, explore combos with it
```

## Edge Cases
- **No duplicates**: Works same as Combination Sum
- **All duplicates**: [2,2,2,2], target=8 → [[2,2,2,2]]
- **No solution**: [2,3,5], target=1 → []
- **Single element matches**: [5], target=5 → [[5]]
- **Multiple duplicates**: [1,1,1,2], target=3 → [[1,1,1], [1,2]]

## Related Problems
- **39. Combination Sum**: Can reuse elements unlimited times
- **216. Combination Sum III**: Use exactly k numbers
- **377. Combination Sum IV**: Count combinations with order
- **78. Subsets**: Generate all subsets (similar backtracking)

## Tags
`array` `backtracking` `recursion` `medium`
