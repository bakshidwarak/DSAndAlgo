# 561. Array Partition I

## Problem Statement
Given an array of 2n integers, your task is to group these integers into n pairs of integers, say (a1, b1), (a2, b2), ..., (an, bn) which makes the sum of min(ai, bi) for all i from 1 to n as large as possible.

### Examples
```
Input: [1, 4, 3, 2]
Output: 4
Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4)

Input: [6, 2, 6, 5, 1, 2]
Output: 9
Explanation: Optimal pairing: (1,2), (2,5), (6,6)
             Sum = min(1,2) + min(2,5) + min(6,6) = 1 + 2 + 6 = 9
```

### Constraints
- n is a positive integer in the range [1, 10000]
- All integers in the array are in the range [-10000, 10000]
- Array contains exactly 2n elements

## Approach & Solution

### Key Insights
1. **Greedy pairing**: To maximize the sum of minimums, pair adjacent elements after sorting
2. **Sorting strategy**: After sorting, pair (nums[0], nums[1]), (nums[2], nums[3]), etc.
3. **Minimizing loss**: Pairing adjacent elements minimizes the "loss" from taking the minimum
4. **Optimal pairing**: The larger element in each pair is "sacrificed", so pair it with the next smallest

### Algorithm Steps
1. Sort the array in ascending order
2. Iterate through the array with step size 2 (indices 0, 2, 4, ...)
3. For each pair at positions i and i+1, add min(nums[i], nums[i+1]) to sum
4. Since array is sorted, nums[i] is always the minimum of the pair
5. Return the total sum

### Complexity Analysis
- **Time Complexity**: O(n log n)
  - Sorting takes O(n log n) where n is array length (2n integers)
  - Iteration takes O(n)
  - Dominated by sorting: O(n log n)
- **Space Complexity**: O(1)
  - Sorting is typically done in-place
  - Only uses a constant amount of extra space for variables
  - O(log n) if counting recursion stack for quicksort

### Visualization
```
Input: [1, 4, 3, 2]

Step 1: Sort array
[1, 2, 3, 4]

Step 2: Pair adjacent elements
(1, 2)  (3, 4)
 ↓       ↓
min=1   min=3

Step 3: Sum of minimums
1 + 3 = 4

Why this is optimal:
- If we paired (1,3) and (2,4): min(1,3) + min(2,4) = 1 + 2 = 3 (worse)
- If we paired (1,4) and (2,3): min(1,4) + min(2,3) = 1 + 2 = 3 (worse)
- Pairing adjacent sorted elements: 1 + 3 = 4 (best)

Intuition: When sorted, pairing adjacent elements ensures we
"waste" the smallest possible values in each pair.
```

## Code Walkthrough

The implementation is straightforward using sorting and iteration:

```java
public int arrayPairSum(int[] nums) {
    int sum = 0;

    // Sort array in ascending order
    Arrays.sort(nums);

    // Iterate with step 2, taking every first element of each pair
    for (int i = 0; i < nums.length - 1; i = i + 2) {
        int j = i + 1;
        sum += Math.min(nums[i], nums[j]);
        // Since sorted, nums[i] <= nums[j], so this equals nums[i]
    }

    return sum;
}
```

**Optimization Note:**
Since the array is sorted, `Math.min(nums[i], nums[j])` always equals `nums[i]`. The code can be simplified to:
```java
for (int i = 0; i < nums.length; i += 2) {
    sum += nums[i];
}
```

## Edge Cases
- **Minimum size**: [1, 2] → 1 (n=1, one pair)
- **All same elements**: [5, 5, 5, 5] → 10 (any pairing gives same result)
- **Negative numbers**: [-4, -2, 0, 2] → -4 + 0 = -4
- **Mixed positive and negative**: [-1, 0, 1, 2] → -1 + 1 = 0
- **Large values**: Works within constraint range [-10000, 10000]

## Related Problems
- **561. Array Partition II**: Extension with different pairing rules
- **Greedy Algorithm Problems**: Similar optimal substructure
- **Pairing Problems**: Other optimization problems involving pairing elements

## Tags
`array` `sorting` `greedy` `easy`
