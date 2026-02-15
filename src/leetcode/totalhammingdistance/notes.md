# Total Hamming Distance (LeetCode 477)

## Problem Statement
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given an array of integers, find the total Hamming distance between all pairs of the given numbers.

## Examples
```
Example 1:
Input: nums = [4, 14, 2]
Output: 6

Explanation:
In binary:
  4  = 0100
  14 = 1110
  2  = 0010

HammingDistance(4, 14) = 2 (differ at positions 0 and 3)
HammingDistance(4, 2)  = 2 (differ at positions 1 and 2)
HammingDistance(14, 2) = 2 (differ at positions 0 and 1)

Total = 2 + 2 + 2 = 6

Example 2:
Input: nums = [1, 3]
Output: 1

Explanation:
  1 = 01
  3 = 11
HammingDistance(1, 3) = 1 (differ at position 0)
```

## Key Insights
1. Instead of comparing each pair (O(n²)), we can count bit contributions
2. For each bit position, count how many 1s and 0s appear
3. If a bit has `count` ones and `n-count` zeros, it contributes `count * (n-count)` to total distance
4. This is because each 1 at that position differs from each 0 at that position
5. Time complexity reduced from O(n²) to O(32n) = O(n)

## Algorithm Steps

### Approach: Bit-by-Bit Counting
1. Create an array `digits[32]` to count 1s at each bit position
2. For each number:
   - Extract each of its 32 bits
   - Count how many 1s appear at each position
3. For each bit position:
   - If count of 1s = c, then count of 0s = n - c
   - Contribution = c * (n - c)
4. Sum all contributions

## Complexity Analysis
- **Time Complexity:** O(32n) = O(n) - Iterate through n numbers, check 32 bits each
- **Space Complexity:** O(32) = O(1) - Fixed size array for bit positions

## ASCII Visualization

```
nums = [4, 14, 2]
n = 3

Binary representations (4 bits):
  4:  0100
  14: 1110
  2:  0010

Step 1: Count 1s at each position
digits[0] = 2 (from 14, 2)
digits[1] = 2 (from 14, 4)
digits[2] = 1 (from 14)
digits[3] = 1 (from 4)
(other positions are 0)

Step 2: Calculate contribution at each position
Position 0: count=2, zeros=3-2=1, contribution = 2*1 = 2
Position 1: count=2, zeros=3-2=1, contribution = 2*1 = 2
Position 2: count=1, zeros=3-1=2, contribution = 1*2 = 2
Position 3: count=1, zeros=3-1=2, contribution = 1*2 = 2
Other:      count=0, contribution = 0*n = 0

Total distance = 2 + 2 + 2 + 2 = 8

Wait, expected is 6. Let me recount:

  4  = 0100
  14 = 1110
  2  = 0010

Position 0 (rightmost):
  4:  0
  14: 0
  2:  0
  Count of 1s = 0, contribution = 0 * 3 = 0

Position 1:
  4:  0
  14: 1
  2:  1
  Count of 1s = 2, contribution = 2 * 1 = 2

Position 2:
  4:  1
  14: 1
  2:  0
  Count of 1s = 2, contribution = 2 * 1 = 2

Position 3:
  4:  0
  14: 1
  2:  0
  Count of 1s = 1, contribution = 1 * 2 = 2

Total = 0 + 2 + 2 + 2 = 6 ✓

Pair-wise verification:
  HammingDistance(4, 14) = diff at positions 3,2 = 2
  HammingDistance(4, 2) = diff at positions 2,3 = 2 (wait, 4=0100, 2=0010)
    Position 1: 0 vs 1 = 1
    Position 2: 1 vs 0 = 1
    = 2
  HammingDistance(14, 2) = 1110 vs 0010
    Position 1: 1 vs 1 = 0
    Position 2: 1 vs 0 = 1
    Position 3: 1 vs 0 = 1
    = 2

Total = 2 + 2 + 2 = 6 ✓
```

## Code Walkthrough

```java
public int totalHammingDistance(int[] nums) {
    int[] digits = new int[32];

    // Count 1s at each bit position
    for (int i = 0; i < nums.length; i++) {
        int number = nums[i];
        int mask = 1;
        int digit = 0;

        while (digit < 32) {
            // Check if bit at position 'digit' is 1
            int k = number & mask;
            if (k == 1) {
                digits[digit]++;
            }
            number = number >>> 1;  // Right shift
            digit++;
        }
    }

    // Calculate total Hamming distance
    int distance = 0;
    for (int i = 0; i < digits.length; i++) {
        distance += digits[i] * (nums.length - digits[i]);
    }

    return distance;
}
```

## Alternative: Using Bit Manipulation

```java
public int totalHammingDistance(int[] nums) {
    int totalDistance = 0;

    for (int i = 0; i < 32; i++) {
        int countOnes = 0;

        // Count 1s at position i
        for (int num : nums) {
            if ((num & (1 << i)) != 0) {
                countOnes++;
            }
        }

        // Contribution of position i
        totalDistance += countOnes * (nums.length - countOnes);
    }

    return totalDistance;
}
```

## Edge Cases
1. Two numbers: [1, 2] → 1 (01 vs 10)
2. Single number: [5] → 0 (no pairs)
3. All zeros: [0, 0, 0] → 0 (all same)
4. All ones: [7, 7, 7] → 0 (all same)
5. Large numbers: [2147483647, 0] → 31 (differ at all 31 bits of large number)

## Key Insight Explanation

For each bit position:
- If `c` numbers have 1 and `n-c` have 0
- Total pairs differ at this position = c * (n - c)
- Each 1 pairs with each 0

Example: [1, 0, 1, 0] at position 0
- 1s: 2, 0s: 2
- Difference count = 2 * 2 = 4
- Pairs: (1,0), (1,0), (0,1), (0,1) = 4 pairs

## Related Problems
- [Hamming Distance](../totalhammingdistance/notes.md)
- LeetCode 191: Number of 1 Bits
- LeetCode 338: Counting Bits
- LeetCode 393: UTF-8 Validation
## Tags
- Bit Manipulation
- Math
- Array
- Optimization
