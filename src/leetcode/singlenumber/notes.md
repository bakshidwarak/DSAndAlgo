# 136. Single Number

## Problem Statement
Given an array of integers where every element appears twice except for one element that appears once, find that single element.

Constraints:
- Must have linear runtime complexity O(n)
- Must implement without using extra memory (O(1) space)

## Examples

### Example 1
```
Input: [2,2,1]
Output: 1
Explanation: 1 appears once, 2 appears twice
```

### Example 2
```
Input: [4,1,2,1,2]
Output: 4
Explanation: 4 appears once, 1 and 2 appear twice
```

### Example 3
```
Input: [0,1,0,1,99]
Output: 99
```

## Key Insights

1. **XOR properties**: Key to solving this elegantly
   - a ^ a = 0 (XOR with itself gives 0)
   - a ^ 0 = a (XOR with 0 gives a)
   - XOR is commutative: a ^ b = b ^ a
   - XOR is associative: (a ^ b) ^ c = a ^ (b ^ c)

2. **XOR eliminates pairs**: When we XOR all elements, duplicate pairs cancel out
3. **Single element remains**: Only the unique element is left

## Algorithm Steps

1. Initialize result = 0
2. For each number in the array:
   - XOR it with result: result = result ^ num
3. Return result

## Complexity Analysis

**Time Complexity:** O(n)
- Single pass through the array
- n = length of array

**Space Complexity:** O(1)
- Only one variable (result)
- No additional data structures

## ASCII Visualization

```
Array: [4, 1, 2, 1, 2]

XOR operations:
result = 0

result = 0 ^ 4 = 4
  Binary: 0000 ^ 0100 = 0100

result = 4 ^ 1 = 5
  Binary: 0100 ^ 0001 = 0101

result = 5 ^ 2 = 7
  Binary: 0101 ^ 0010 = 0111

result = 7 ^ 1 = 6
  Binary: 0111 ^ 0001 = 0110

result = 6 ^ 2 = 4
  Binary: 0110 ^ 0010 = 0100

Final result = 4 (the single number)

Verification:
4 ^ 4 = 0 (both 4's cancel)
1 ^ 1 = 0 (both 1's cancel)
2 ^ 2 = 0 (both 2's cancel)
Remaining = 4
```

## Code Walkthrough

```java
public int singleNumber(int[] nums) {
    int result = 0;

    // XOR all elements
    for (int i = 0; i < nums.length; i++) {
        result = result ^ nums[i];
    }

    return result;
}
```

## Enhanced with Comments

```java
public int singleNumber(int[] nums) {
    /**
     * Basic idea: if you XOR a number with itself, it returns 0.
     * So if we keep XOR-ing numbers as we go, the duplicate ones
     * get cancelled out and only the unique number is left.
     */
    int result = 0;

    // XOR each element with result
    for (int i = 0; i < nums.length; i++) {
        result = result ^ nums[i];
    }

    return result;
}
```

## Why XOR Works

```
Property 1: a ^ a = 0
  Example: 5 ^ 5
  Binary: 0101 ^ 0101 = 0000

Property 2: a ^ 0 = a
  Example: 5 ^ 0
  Binary: 0101 ^ 0000 = 0101

Property 3: XOR is commutative
  a ^ b = b ^ a

Property 4: XOR is associative
  (a ^ b) ^ c = a ^ (b ^ c)

For array [a, b, a, c, b]:
result = 0
result ^= a -> result = a
result ^= b -> result = a ^ b
result ^= a -> result = (a ^ b) ^ a = (a ^ a) ^ b = 0 ^ b = b
result ^= c -> result = b ^ c
result ^= b -> result = (b ^ c) ^ b = (b ^ b) ^ c = 0 ^ c = c

Final: c (the single element)
```

## Step-by-step Example

```
Array: [2, 2, 1]

Step 1:
result = 0
Binary: 0000

Step 2: result ^ 2
result = 0000 ^ 0010 = 0010 (2)

Step 3: result ^ 2
result = 0010 ^ 0010 = 0000 (0)

Step 4: result ^ 1
result = 0000 ^ 0001 = 0001 (1)

Final result = 1
```

## Edge Cases

1. **Single element**: [1] -> 1
2. **Negative numbers**: [-1,-1,3] -> 3
3. **Zero**: [0,1,0] -> 1
4. **Large numbers**: [Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE] -> Integer.MIN_VALUE

## How It Handles Negatives

XOR works with two's complement representation for negative numbers:

```
Example: [-2, -2, 1]

-2 in binary (32-bit two's complement):
  1111...1110

-2 ^ -2 = 0
0 ^ 1 = 1

Result = 1 (correct)
```

## Alternative Approaches (Not Optimal)

### Approach 2: Using HashMap (O(n) space)
```java
public int singleNumberHashMap(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }
    for (int num : map.keySet()) {
        if (map.get(num) == 1)
            return num;
    }
    return -1;
}
// Time: O(n), Space: O(n) - violates constraint
```

### Approach 3: Sorting (O(n log n) time)
```java
public int singleNumberSort(int[] nums) {
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 1; i += 2) {
        if (nums[i] != nums[i + 1])
            return nums[i];
    }
    return nums[nums.length - 1];
}
// Time: O(n log n) - violates constraint
```

## Related Problems

- 137: Single Number II (every element appears 3 times except one)
- 260: Single Number III (two elements appear once)
- 268: Missing Number (missing element in array)
- 287: Find the Duplicate Number

## Tags

`easy` `bit-manipulation` `xor` `array` `optimization` `math`
