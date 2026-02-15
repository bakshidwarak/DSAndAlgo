# LeetCode 167: Two Sum II - Input Array is Sorted

## Problem Statement
Given an array of integers `numbers` that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

**Constraints:**
- The function should return indices of the two numbers such that they add up to the target
- Index1 must be less than Index2
- Returned answers are 1-based (not 0-based)
- Each input has exactly one solution
- Cannot use the same element twice

## Examples

### Example 1
- **Input:** `numbers = [2, 7, 11, 15]`, `target = 9`
- **Output:** `[1, 2]` (indices are 1-based, pointing to 2 and 7)

### Example 2
- **Input:** `numbers = [2, 3, 4]`, `target = 6`
- **Output:** `[1, 3]` (pointing to 2 and 4)

## Key Insights

1. **Sorted Array Advantage:** The array is sorted, which means we can use two-pointer technique
2. **Two-Pointer Invariant:** Start from both ends and move towards the center
3. **Directional Movement:**
   - If sum is too large, move the right pointer left (decreases sum)
   - If sum is too small, move the left pointer right (increases sum)
4. **No Extra Space Needed:** We only need pointers, no hash table required

## Algorithm Steps

### Two-Pointer Approach (Used in Code)

```
1. Initialize left pointer at start (i = 0)
2. Initialize right pointer at end (j = length - 1)
3. While i < j:
   a. Calculate sum = numbers[i] + numbers[j]
   b. If sum equals target:
      - Return [i + 1, j + 1] (convert to 1-based indexing)
   c. If sum > target:
      - Decrement j (need smaller sum)
   d. If sum < target:
      - Increment i (need larger sum)
4. Return null if no solution found
```

## Complexity Analysis

- **Time Complexity:** O(n) - Single pass with two pointers
- **Space Complexity:** O(1) - Only using constant extra space for pointers

## ASCII Visualization

```
Array: [2, 7, 11, 15], Target = 9

Initial State:
  i=0        j=3
  [2, 7, 11, 15]
   ^         ^
  sum = 2 + 15 = 17 > 9, move j left

Step 1:
  i=0      j=2
  [2, 7, 11, 15]
   ^        ^
  sum = 2 + 11 = 13 > 9, move j left

Step 2:
  i=0    j=1
  [2, 7, 11, 15]
   ^     ^
  sum = 2 + 7 = 9 = target, FOUND!
  Return [1, 2] (1-based indexing)
```

## Code Walkthrough

```java
public static int[] twoSum(int[] numbers, int target) {
    int i = 0;                          // Left pointer at start
    int j = numbers.length - 1;         // Right pointer at end

    while (i < j) {
        if (numbers[i] + numbers[j] == target) {
            return new int[] { i + 1, j + 1 };  // Convert to 1-based
        }

        if (numbers[i] + numbers[j] > target) {
            j--;  // Sum too large, move right pointer left
        } else {
            i++;  // Sum too small, move left pointer right
        }
    }
    return null;  // No solution found
}
```

**Execution Flow:**
1. Initialize pointers at opposite ends
2. Check if current sum matches target
3. Adjust pointers based on comparison
4. Loop until solution found or pointers meet
5. Return 1-based indices or null

## Edge Cases

1. **Minimum Array:** Array with exactly 2 elements - should work directly
2. **Negative Numbers:** Array with negative numbers (still sorted) - algorithm still works
3. **No Solution:** Should return null (though problem guarantees exactly one solution)
4. **Large Numbers:** Integer overflow is possible but problem typically avoids this
5. **Duplicates:** Array can have duplicate values - algorithm handles correctly

### Example Edge Cases:
- `numbers = [1, 2]`, `target = 3` → `[1, 2]`
- `numbers = [-1, 0, 1, 2]`, `target = 1` → `[1, 4]` (pointing to -1 and 2)

## Related Problems

1. **LeetCode 1 - Two Sum:** Same problem but array is unsorted (requires hash table)
2. **LeetCode 170 - Two Sum III:** Two Sum with add/find operations
3. **LeetCode 653 - Two Sum IV - BST:** Two Sum in a Binary Search Tree
4. **LeetCode 15 - 3Sum:** Find all triplets that sum to target
5. **LeetCode 18 - 4Sum:** Find all quadruplets that sum to target
## Tags

`#Array` `#Two-Pointers` `#Sorting` `#Binary-Search` `#Greedy` `#Easy`

## Key Takeaways

- Sorted arrays unlock two-pointer technique
- Two-pointer approach is O(n) time with O(1) space
- Remember to convert to 1-based indexing for return value
- This is more efficient than using hash table (O(n) space)
