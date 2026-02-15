# 905. Sort Array By Parity

## Problem Statement
Given an array A of non-negative integers, return an array consisting of all even elements of A, followed by all odd elements of A.

Constraints:
- Any order for even elements is acceptable
- Any order for odd elements is acceptable
- 1 <= A.length <= 5000
- 0 <= A[i] <= 5000

## Examples

### Example 1
```
Input: [3,1,2,4]
Output: [2,4,3,1] (or [4,2,1,3], [4,2,3,1], [2,4,1,3])
Explanation: Even elements [2,4] come before odd elements [3,1]
```

### Example 2
```
Input: [0]
Output: [0]
```

### Example 3
```
Input: [1,3,2,4]
Output: [2,4,1,3]
```

## Key Insights

1. **Two pointers from opposite ends**: even pointer from start, odd from end
2. **Fill both simultaneously**: Build result array from both directions
3. **Two-pass approach**: Simple and efficient
4. **O(n) time, O(n) space**: Create new array for result

## Algorithm Steps

1. Create result array of same length
2. Initialize even pointer at 0, odd pointer at end
3. Iterate through original array:
   - If element is even, place at even pointer position, increment pointer
   - If element is odd, place at odd pointer position, decrement pointer
4. Return result array

## Complexity Analysis

**Time Complexity:** O(n)
- Single pass through the array
- n = length of array

**Space Complexity:** O(n)
- Result array of same size
- No other additional structures

## ASCII Visualization

```
Input: [3, 1, 2, 4]
Index:  0  1  2  3

Initialize:
even = 0, odd = 3, result = [0, 0, 0, 0]

Iteration 1: A[0] = 3 (odd)
  3 % 2 == 0? NO (3 is odd)
  result[odd--] = 3 -> result = [0, 0, 0, 3], odd = 2

Iteration 2: A[1] = 1 (odd)
  1 % 2 == 0? NO (1 is odd)
  result[odd--] = 1 -> result = [0, 0, 1, 3], odd = 1

Iteration 3: A[2] = 2 (even)
  2 % 2 == 0? YES (2 is even)
  result[even++] = 2 -> result = [2, 0, 1, 3], even = 1

Iteration 4: A[3] = 4 (even)
  4 % 2 == 0? YES (4 is even)
  result[even++] = 4 -> result = [2, 4, 1, 3], even = 2

Final: [2, 4, 1, 3]
```

## Code Walkthrough

```java
class Solution {
    public int[] sortArrayByParity(int[] A) {
        // Initialize two pointers and result array
        int even = 0;           // Pointer for placing even elements
        int odd = A.length - 1; // Pointer for placing odd elements
        int[] result = new int[A.length];

        // Fill result array
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                // Element is even, place at even position
                result[even++] = A[i];
            } else {
                // Element is odd, place at odd position
                result[odd--] = A[i];
            }
        }

        return result;
    }
}
```

## Detailed Walkthrough

```
Input: [3,1,2,4]

Initial state:
A = [3, 1, 2, 4]
even = 0, odd = 3
result = [?, ?, ?, ?]

i=0: A[0]=3
  3 % 2 == 0? NO
  result[3] = 3, odd = 2
  result = [?, ?, ?, 3]

i=1: A[1]=1
  1 % 2 == 0? NO
  result[2] = 1, odd = 1
  result = [?, ?, 1, 3]

i=2: A[2]=2
  2 % 2 == 0? YES
  result[0] = 2, even = 1
  result = [2, ?, 1, 3]

i=3: A[3]=4
  4 % 2 == 0? YES
  result[1] = 4, even = 2
  result = [2, 4, 1, 3]

Return: [2, 4, 1, 3]
```

## Edge Cases

1. **All even**: [2,4,6] -> [2,4,6]
2. **All odd**: [1,3,5] -> [5,3,1] or any permutation
3. **Single element even**: [2] -> [2]
4. **Single element odd**: [1] -> [1]
5. **Zero**: [0,1] -> [0,1]
6. **Mixed with zero**: [0,1,2,3] -> [0,2,1,3]

## In-place Alternative (Two-pointer from same direction)

```java
public int[] sortArrayByParityInPlace(int[] A) {
    int left = 0, right = A.length - 1;

    while (left < right) {
        // Find odd number from left
        while (left < right && A[left] % 2 == 0)
            left++;

        // Find even number from right
        while (left < right && A[right] % 2 == 1)
            right--;

        // Swap if needed
        if (left < right) {
            int temp = A[left];
            A[left] = A[right];
            A[right] = temp;
            left++;
            right--;
        }
    }

    return A;
}
```

### In-place Complexity
- Time: O(n)
- Space: O(1) - modifies original array

## Comparison of Approaches

| Approach | Time | Space | Notes |
|----------|------|-------|-------|
| Two pointers opposite | O(n) | O(n) | Simpler, preserves order within groups |
| Two pointers same direction | O(n) | O(1) | Modifies original, may reorder within groups |

## Related Problem: 922. Sort Array By Parity II

```
Given array where n is even, n/2 even numbers, n/2 odd numbers
Place even at even indices, odd at odd indices

For [4,2,5,7]:
Output: [4,5,2,7] or [2,5,4,7]
```

Implementation hint:
```java
public int[] sortArrayByParityII(int[] A) {
    int even = 0, odd = 1;
    int[] result = new int[A.length];

    for (int num : A) {
        if (num % 2 == 0) {
            result[even] = num;
            even += 2;
        } else {
            result[odd] = num;
            odd += 2;
        }
    }
    return result;
}
```

## Why This Approach?

1. **No sorting algorithm**: O(n) is better than O(n log n)
2. **Direct placement**: Know exactly where each element goes
3. **Stability optional**: Don't need to maintain relative order
4. **Clear code**: Easy to understand and implement

## Related Problems

- 922: Sort Array By Parity II
- 283: Move Zeroes
- 75: Sort Colors
- 88: Merge Sorted Array
## Tags

`easy` `array` `two-pointer` `sorting` `partition`
