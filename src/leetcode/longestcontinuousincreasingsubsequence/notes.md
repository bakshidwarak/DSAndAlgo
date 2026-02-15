# Longest Continuous Increasing Subsequence

## Problem Statement
**LeetCode Problem 674**: Longest Continuous Increasing Subsequence (Easy)

Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).

### Examples
**Example 1:**
```
Input: [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
Even though [1,3,5,7] is also an increasing subsequence, it's not continuous
because 5 and 7 are separated by 4.
```

**Example 2:**
```
Input: [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], its length is 1.
```

**Note**: Length of the array will not exceed 10,000.

## Key Insights
1. **Continuous/Contiguous**: Must be consecutive elements (subarray, not subsequence)
2. **Strictly Increasing**: Each element must be greater than previous
3. **Reset on Break**: When sequence breaks, start counting from 1
4. **Track Maximum**: Keep track of longest sequence seen so far
5. **Single Pass**: Can solve in O(n) with one iteration

## Algorithm Steps
```
1. Handle edge case: empty array returns 0
2. Initialize:
   - count = 1 (current sequence length)
   - maxCount = 0 (longest sequence found)
   - prev = first element
3. Iterate from index 1 to end:
   a. If current > previous:
      - Increment count (sequence continues)
   b. Else (sequence breaks):
      - Update maxCount if needed
      - Reset count to 1
   c. Update prev to current element
4. Final check: update maxCount (handle case where array ends with longest sequence)
5. Return maxCount
```

## Complexity Analysis
- **Time Complexity**: O(n)
  - Single pass through array
  - n = length of array
- **Space Complexity**: O(1)
  - Only use constant extra space
  - No additional data structures

## Visual Representation

### Example 1: [1,3,5,4,7]
```
Index:   0  1  2  3  4
Array:   1  3  5  4  7
         ^
count=1, prev=1, maxCount=0

Index:   0  1  2  3  4
Array:   1  3  5  4  7
            ^
3 > 1: count=2, prev=3, maxCount=0

Index:   0  1  2  3  4
Array:   1  3  5  4  7
               ^
5 > 3: count=3, prev=5, maxCount=0

Index:   0  1  2  3  4
Array:   1  3  5  4  7
                  ^
4 < 5: sequence breaks!
  maxCount = max(3, 0) = 3
  count = 1, prev = 4

Index:   0  1  2  3  4
Array:   1  3  5  4  7
                     ^
7 > 4: count=2, prev=7, maxCount=3

End of array:
  maxCount = max(2, 3) = 3

Result: 3 (sequence [1,3,5])
```

### Example 2: [2,2,2,2,2]
```
Index:   0  1  2  3  4
Array:   2  2  2  2  2

count=1, prev=2, maxCount=0

Each iteration:
2 == prev (not greater): reset count=1, update maxCount

Final: maxCount=1
```

### Example 3: [1,2,3,4,5]
```
Index:   0  1  2  3  4
Array:   1  2  3  4  5
         ^
count=1, maxCount=0

1->2: count=2
2->3: count=3
3->4: count=4
4->5: count=5

End: maxCount = max(5, 0) = 5

Result: 5 (entire array is increasing)
```

## Code Walkthrough

### Current Implementation
```java
public int findLengthOfLCIS(int[] nums) {
    // Edge case: empty array
    if (nums.length == 0)
        return 0;

    int count = 1;        // Current sequence length
    int maxCount = 0;     // Longest sequence found
    int prev = nums[0];   // Previous element

    // Iterate through array starting from second element
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] > prev) {
            // Continue current increasing sequence
            count++;
        } else {
            // Sequence breaks, update max and reset
            maxCount = Math.max(count, maxCount);
            count = 1;  // Start new sequence
        }
        prev = nums[i];  // Update previous element
    }

    /**
     * Ensure finally we do a max count again as there could be a fully
     * increasing subsequence
     */
    maxCount = Math.max(count, maxCount);

    return maxCount;
}
```

### Alternative: Without Prev Variable
```java
public int findLengthOfLCIS(int[] nums) {
    if (nums.length == 0) return 0;

    int count = 1;
    int maxCount = 1;

    for (int i = 1; i < nums.length; i++) {
        if (nums[i] > nums[i-1]) {
            count++;
            maxCount = Math.max(maxCount, count);
        } else {
            count = 1;
        }
    }

    return maxCount;
}
```

### Sliding Window Perspective
```java
public int findLengthOfLCIS(int[] nums) {
    if (nums.length == 0) return 0;

    int maxLen = 1;
    int start = 0;

    for (int i = 1; i < nums.length; i++) {
        // If not increasing, move start to current position
        if (nums[i] <= nums[i-1]) {
            start = i;
        }

        // Update max length
        maxLen = Math.max(maxLen, i - start + 1);
    }

    return maxLen;
}
```

## Edge Cases
1. **Empty array**: Return 0
2. **Single element**: Return 1
3. **All same elements**: Return 1
4. **Strictly decreasing**: Return 1
5. **Strictly increasing**: Return array length
6. **Increasing then decreasing**: Return length of increasing part

### Edge Case Examples
```
Input: []
Output: 0

Input: [5]
Output: 1

Input: [1,1,1,1]
Output: 1

Input: [5,4,3,2,1]
Output: 1

Input: [1,2,3,4,5]
Output: 5

Input: [1,2,3,2,1]
Output: 3 (sequence [1,2,3])

Input: [1,3,5,4,2,3,4,5]
Output: 4 (sequence [2,3,4,5])

Input: [7,8,9,1,2,3]
Output: 3 (multiple sequences of length 3, return 3)
```

## Trace Example: [1,3,5,4,7]
```
Initial: count=1, maxCount=0, prev=1

i=1, nums[1]=3:
  3 > 1: count=2, prev=3

i=2, nums[2]=5:
  5 > 3: count=3, prev=5

i=3, nums[3]=4:
  4 < 5: maxCount=max(3,0)=3, count=1, prev=4

i=4, nums[4]=7:
  7 > 4: count=2, prev=7

Final: maxCount=max(2,3)=3

Return: 3
```

## Why Final maxCount Update is Important

Consider array: [1,2,3,4,5]
```
After loop:
  count = 5 (entire array is increasing)
  maxCount = 0 (never updated because no break)

Without final update:
  Would incorrectly return 0

With final update:
  maxCount = max(5, 0) = 5
  Correctly returns 5
```

## Comparison with Similar Problems

| Problem | Continuous? | Strictly Increasing? | Complexity |
|---------|-------------|---------------------|------------|
| LCIS (this) | Yes (subarray) | Yes | O(n), O(1) |
| LIS | No (subsequence) | Yes | O(n²) or O(n log n) |
| Longest Subarray with Diff | Yes | Custom condition | O(n) |
| Maximum Subarray | Yes | Sum-based | O(n) |

## Related Problems
- **Longest Increasing Subsequence (LeetCode 300)**: Non-continuous version
- **Number of Longest Increasing Subsequence (LeetCode 673)**: Count LIS
- **Maximum Subarray (LeetCode 53)**: Similar single-pass technique
- **Best Time to Buy and Sell Stock (LeetCode 121)**: Similar pattern
- **Longest Mountain in Array (LeetCode 845)**: More complex increasing/decreasing

## Tags
- Array
- Sliding Window
- Single Pass
- Easy
- Kadane's Algorithm Variant
- Facebook Interview
