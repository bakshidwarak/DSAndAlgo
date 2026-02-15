# LeetCode 611: Valid Triangle Number

## Problem Statement
Given an array consisting of non-negative integers, count the number of triplets chosen from the array that can form valid triangles using the triangle inequality theorem.

**Triangle Inequality Theorem:**
For three sides to form a valid triangle, the sum of any two sides must be greater than the third side.
- `a + b > c`
- `b + c > a`
- `a + c > b`

For sorted array where `a ≤ b ≤ c`, only need to check: `a + b > c`

**Constraints:**
- Array length won't exceed 1000
- Integers in range [0, 1000]
- Cannot use same element twice (different indices)

## Examples

### Example 1
- **Input:** `nums = [2, 2, 3, 4]`
- **Output:** `3`
- **Explanation:** Valid triplets are:
  - (2, 3, 4) using first 2
  - (2, 3, 4) using second 2
  - (2, 2, 3)

### Example 2
- **Input:** `nums = [4]`
- **Output:** `0`
- **Explanation:** Need at least 3 elements

## Key Insights

1. **Sorted Array Advantage:** Sort first to use two-pointer technique
2. **Triangle Inequality Shortcut:** After sorting, only check `nums[i] + nums[j] > nums[k]`
3. **Counting Multiple Valid Triplets:** When `nums[i] + nums[j] > nums[k]`, all elements between j and k also form valid triangles
4. **Pointer Movement:** Fix one side and use two pointers for other sides
5. **Efficiency:** Avoid brute force O(n^3) by using two-pointer O(n^2)

## Algorithm Steps

### Two-Pointer Approach (Used in Code)

```
1. Sort the array in ascending order
2. For each element at index i (as smallest side):
   a. If nums[i] == 0, skip (0 cannot be triangle side)
   b. Initialize j = i + 1 (next side)
   c. Initialize k = i + 2 (largest side)
   d. While j < length - 1:
      - If nums[i] == 0, skip j
      - Increment k while nums[i] + nums[j] > nums[k]
      - Count all valid triplets: add (k - j - 1) to count
      - Move j to next position
3. Return total count
```

## Complexity Analysis

- **Time Complexity:** O(n²) - Sorting O(n log n) + nested loops O(n²)
- **Space Complexity:** O(1) - Only using pointers (excluding sort space)

## ASCII Visualization

```
Input: nums = [2, 2, 3, 4]

Step 1: Sort
nums = [2, 2, 3, 4]

Step 2-3: For each i, use two pointers

i=0 (smallest = 2):
  j=1  k=2
  [2, 2, 3, 4]
       ^  ^
  nums[0] + nums[1] = 2 + 2 = 4 > nums[2] = 3? YES ✓
  k advances: while 2 + 2 > nums[k], k++
  After loop: k = 3 (beyond 3, stops at 4)

  Valid triplets: k - j - 1 = 3 - 1 - 1 = 1
  (triplet: [2, 2, 3])

  count = 1, j++

  j=2  k=3
  [2, 2, 3, 4]
        ^  ^
  nums[0] + nums[2] = 2 + 3 = 5 > nums[3] = 4? YES ✓
  k is already at end (k = 4, j < 3 check fails)

  Valid triplets: k - j - 1 = 3 - 2 - 1 = 0
  (no additional triplets)

i=1 (smallest = 2):
  j=2  k=3
  [2, 2, 3, 4]
        ^  ^
  nums[1] + nums[2] = 2 + 3 = 5 > nums[3] = 4? YES ✓
  k is already at end

  Valid triplets: k - j - 1 = 3 - 2 - 1 = 0

Total count = 1 (Wait, expected 3!)

Actually, let me recalculate:

i=0 (i < length - 2 = 2):
  j=1  k=2
  [2, 2, 3, 4]
       ^  ^
  2 + 2 = 4 > 3? YES
  Increment k while: 4 > 4? NO, stop at k=3
  count += 3 - 1 - 1 = 1
  j=2

  j=2  k=3
  [2, 2, 3, 4]
        ^  ^
  j < length - 1? 2 < 3? YES
  2 + 3 = 5 > 4? YES
  Increment k while: 5 > 4? YES, k++ to k=4
  5 > (out of bounds)? STOP, k=4
  count += 4 - 2 - 1 = 1
  j++ to 3

  j=3  (j < 3? NO, exit inner loop)

i=1 (i < 2):
  j=2  k=2 initialized? Actually k=3 from before? Let me re-read code.

Actually, the code reinitializes k for each j:
  k = i + 2 at start
  Then increments k in inner while loop

Let me retrace:

i=0:
  j=1, k=2
  [2, 2, 3, 4]
       ^  ^
  nums[0]=2, nums[1]=2, nums[2]=3
  2 + 2 = 4 > 3? YES
  while(2 + 2 > nums[k]):
    k++ (k becomes 3)
    2 + 2 = 4 > nums[3]=4? NO, exit while
  count += 3 - 1 - 1 = 1
  j++ (j becomes 2)

  j=2, k=3 (k still 3, not reset)
  [2, 2, 3, 4]
        ^  ^
  nums[0]=2, nums[2]=3, nums[3]=4
  2 + 3 = 5 > 4? YES
  while(2 + 3 > nums[k]):
    k++ (k becomes 4, which is out of bounds)
  count += 4 - 2 - 1 = 1
  j++ (j becomes 3)

  j=3, but j < length-1? 3 < 3? NO, exit inner while

i=1:
  j=2, k=3? (re-initialized? No, k = i + 2 = 1 + 2 = 3? Let me check)
  Actually in the code, k is re-initialized: k = i + 2

  Wait, looking at the code more carefully:
  k = i + 2;  // This is inside the i loop but outside the j loop

  So k is reset for each i, not reset for each j.

Actually, I see the issue now. The code says:
```
int j = i + 1;
int k = i + 2;  // k initialized once per i
while (j < nums.length - 1) {
    while (k < nums.length && nums[i] + nums[j] > nums[k]) {
        k++;
    }
    count += k - j - 1;
    j++;
}
```

So k is NOT reset for each j. It only increments and never decrements.

Let me retrace correctly:

i=0:
  j=1, k=2
  While j < 3 (length - 1 = 3):
    while(2 + 2 > nums[2]=3): k++ → k=3
    while(2 + 2 > nums[3]=4): NO
    count += 3 - 1 - 1 = 1
    j++ → j=2

    while(2 + 3 > nums[3]=4): k++ → k=4
    while(k < 4): NO
    count += 4 - 2 - 1 = 1
    j++ → j=3

    j < 3? NO, exit

count = 2 so far

i=1:
  j=2, k=3? Let me check, k = i + 2 = 1 + 2 = 3
  While j < 3:
    while(2 + 3 > nums[3]=4): k++ → k=4
    count += 4 - 2 - 1 = 1
    j++ → j=3
    j < 3? NO, exit

count = 3 ✓

Result: 3 valid triplets ✓
```

## Code Walkthrough

```java
public int triangleNumber(int[] nums) {
    Arrays.sort(nums);  // Step 1: Sort array
    int count = 0;

    // Step 2: Iterate through array
    for (int i = 0; i < nums.length - 2; i++) {
        // Skip zero sides
        if (nums[i] == 0)
            continue;

        int j = i + 1;      // Second smallest side
        int k = i + 2;      // Largest side

        // Step 3: For each pair (i, j), find valid k values
        while (j < nums.length - 1) {
            if (nums[j] == 0) {
                j++;
                continue;
            }

            // Increment k while triangle inequality is satisfied
            while (k < nums.length && nums[i] + nums[j] > nums[k]) {
                k++;
            }

            // Count all valid triangles with sides i, j, and [j+1..k-1]
            count += k - j - 1;
            j++;
        }
    }
    return count;
}
```

**Execution Flow:**
1. Sort array to enable two-pointer technique
2. For each smallest side i
3. Initialize j (next side) and k (largest side)
4. For each j, increment k while triangle inequality holds
5. Count all valid third sides between j and k
6. Move j forward for next iteration

## Edge Cases

1. **Empty or Small Array:** Less than 3 elements
   - `[]` → 0
   - `[1]` → 0
   - `[1, 2]` → 0

2. **All Zeros:** Cannot form triangles
   - `[0, 0, 0]` → 0

3. **No Valid Triangles:** All different sizes
   - `[1, 2, 4]` → 0 (1+2 not > 4)

4. **All Same Values:** Any three form valid triangle
   - `[1, 1, 1]` → 1
   - `[2, 2, 2, 2]` → C(4,3) = 4

5. **Large Values:** Can have large counts
   - `[0, 0, 0, 1, 1, 1]` → Multiple valid combinations

### Example Edge Cases:
```
Input: [], Output: 0
Input: [1], Output: 0
Input: [0, 0, 0], Output: 0
Input: [1, 2, 4], Output: 0
Input: [1, 1, 1], Output: 1
Input: [2, 2, 3, 4], Output: 3
```

## Related Problems

1. **LeetCode 15 - 3Sum:** Find all triplets with target sum
2. **LeetCode 16 - 3Sum Closest:** Find triplet closest to target
3. **LeetCode 18 - 4Sum:** Find all quadruplets with target sum
4. **LeetCode 167 - Two Sum II:** Two sum in sorted array
5. **LeetCode 259 - 3Sum Smaller:** Count triplets with sum < target
## Tags

`#Array` `#Two-Pointers` `#Sorting` `#Math` `#Medium`

## Key Takeaways

- Sorting enables two-pointer technique for O(n²) solution
- For sorted array, only need to check `a + b > c`
- When `a + b > c`, all elements between j and k form valid triangles
- k pointer never decreases (monotonic property)
- Zero values cannot be triangle sides
- Counting formula: `k - j - 1` gives number of valid third sides
