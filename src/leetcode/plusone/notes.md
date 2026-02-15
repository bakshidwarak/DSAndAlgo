# Plus One - LeetCode Problem 66

## Problem Statement
Given a non-empty array of digits representing a non-negative integer, increment the number by one and return the resulting array of digits.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contains a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

## Examples

**Example 1:**
- Input: `[1,2,3]`
- Output: `[1,2,4]`
- Explanation: The array represents the integer 123. Adding 1 gives 124.

**Example 2:**
- Input: `[4,3,2,1]`
- Output: `[4,3,2,2]`
- Explanation: The array represents the integer 4321. Adding 1 gives 4322.

**Example 3:**
- Input: `[9]`
- Output: `[1,0]`
- Explanation: The array represents the integer 9. Adding 1 gives 10.

## Key Insights
1. **Carry Propagation**: When we add 1, we may need to handle carry (e.g., 19 -> 20, 99 -> 100)
2. **Right to Left**: Process from the rightmost digit (least significant) to the left
3. **Special Case**: If all digits are 9, we need an extra digit (e.g., [9,9,9] -> [1,0,0,0])
4. **Early Termination**: If there's no carry after incrementing, we can stop

## Algorithm Steps

### Approach 1: Simple Loop
1. Start from the rightmost digit (index = length - 1)
2. Add 1 to the rightmost digit
3. If the digit becomes 10 (i.e., was 9), set it to 0 and continue to the left
4. If no carry exists, we're done
5. If we exit the loop with carry, insert 1 at the beginning

**Pseudocode:**
```
function plusOne(digits):
    for i from length-1 to 0:
        if digits[i] < 9:
            digits[i]++
            return digits
        digits[i] = 0

    // All digits were 9
    create newArray of size length+1
    newArray[0] = 1
    return newArray
```

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time Complexity | O(n) where n is the length of the array |
| Space Complexity | O(1) if not counting output, O(n) if all digits are 9 (need new array) |

**Time Analysis:**
- In average case: O(1) - just increment rightmost digit
- In worst case: O(n) - all digits are 9, need to traverse entire array

## ASCII Visualization

```
Example 1: [1,2,3] -> [1,2,4]
Step-by-step:
┌─────────────────┐
│ 1 │ 2 │ 3 │     │
└─────────────────┘
         ↓ (check index 2)
│ 1 │ 2 │ 3 │ < 9, so increment
└─────────────────┘
         ↓
│ 1 │ 2 │ 4 │ ← Result
└─────────────────┘

Example 3: [9] -> [1,0]
┌─────────────┐
│     │ 9 │   │
└─────────────┘
         ↓ (check index 0)
│ 9 │ = 9, set to 0, carry left
└─────────────┘
         ↓
Carry remaining, create new array
│ 1 │ 0 │ ← Result
└─────────────┘
```

## Code Walkthrough

```java
// Main logic for incrementing:
for (int i = digits.length - 1; i >= 0; i--) {
    if (digits[i] < 9) {
        // If digit is not 9, simply increment and return
        digits[i]++;
        return digits;
    }
    // Digit is 9, set to 0 and continue
    digits[i] = 0;
}

// If we reach here, all digits were 9
// Create new array with 1 followed by zeros
int[] result = new int[digits.length + 1];
result[0] = 1;
return result;
```

## Edge Cases

1. **Single digit 9**: `[9]` -> `[1,0]` (need new array)
2. **Multiple 9s**: `[9,9,9]` -> `[1,0,0,0]`
3. **No 9 in rightmost**: `[1,2,3]` -> `[1,2,4]` (no carry beyond first iteration)
4. **Mixed digits**: `[1,9,9]` -> `[2,0,0]`
5. **Single digit**: `[0]` -> `[1]`
6. **Large array**: Handle for array of length 1000+

## Related Problems

1. **LeetCode 67**: Add Binary - Similar carry propagation concept
2. **LeetCode 415**: Add Strings - Similar digit-by-digit addition
3. **LeetCode 989**: Add to Array-Form of Integer - Similar increment operation
4. **LeetCode 2**: Add Two Numbers - More complex addition with linked lists
## Tags

- Array
- Math
- Carry
- In-place Modification
- Difficulty: Easy
- Acceptance: ~40%
