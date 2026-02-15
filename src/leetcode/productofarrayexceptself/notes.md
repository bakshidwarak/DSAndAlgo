# Product of Array Except Self - LeetCode Problem 238

## Problem Statement
Given an array `nums` of n integers where n > 1, return an array `output` such that `output[i]` is equal to the product of all the elements of `nums` except `nums[i]`.

Constraints:
- Do not use division operation
- Solve in O(n) time complexity
- Prefer O(1) space complexity (not counting output array)

## Examples

**Example 1:**
- Input: `[1,2,3,4]`
- Output: `[24,12,8,6]`
- Explanation: output[0] = 2*3*4 = 24, output[1] = 1*3*4 = 12, etc.

**Example 2:**
- Input: `[2,3,4,5]`
- Output: `[60,40,30,24]`

## Key Insights
1. **Without Division**: Cannot use total_product / nums[i]
2. **Left-Right Products**: For position i, product = (product of all left) * (product of all right)
3. **Two-Pass Approach**: First pass left-to-right, second pass right-to-left
4. **Space Optimization**: Use output array as temporary storage
5. **No Zero or One Zero**: Handle zeros carefully if present in advanced versions

## Algorithm Steps

### Approach: Left and Right Products

**Step 1: Calculate Left Products**
- output[i] = product of all elements to the left of i
- For position 0, left product is 1

**Step 2: Calculate Right Products and Combine**
- Traverse right to left, keeping track of right product
- Multiply output[i] with right product

**Pseudocode:**
```
function productExceptSelf(nums):
    n = nums.length
    output = new Array[n]

    // Step 1: Calculate left products
    leftProduct = 1
    for i from 0 to n-1:
        output[i] = leftProduct
        leftProduct *= nums[i]

    // Step 2: Calculate right products and combine
    rightProduct = 1
    for i from n-1 down to 0:
        output[i] *= rightProduct
        rightProduct *= nums[i]

    return output
```

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time Complexity | O(n) - two passes through array |
| Space Complexity | O(1) - only output array (not counted) |

**Time Analysis:**
- First pass: O(n) to calculate left products
- Second pass: O(n) to calculate right products and combine
- Total: O(n)

## ASCII Visualization

```
Example: nums = [1, 2, 3, 4]

Step 1: Calculate Left Products
Index:  0  1  2  3
nums:   1  2  3  4
left:   1  1  2  6  (product of all elements to the left)

output after step 1: [1, 1, 2, 6]

Visualization:
output[0] = 1 (no elements to left)
output[1] = 1 (just nums[0])
output[2] = 1*2 = 2 (nums[0]*nums[1])
output[3] = 1*2*3 = 6 (nums[0]*nums[1]*nums[2])

Step 2: Calculate Right Products and Combine
Index:    0  1  2   3
nums:     1  2  3   4
output:   1  1  2   6
right:  24 12  4   1

Iteration from right to left:
i=3: output[3] = 6 * 1 = 6, right = 1 * 4 = 4
i=2: output[2] = 2 * 4 = 8, right = 4 * 3 = 12
i=1: output[1] = 1 * 12 = 12, right = 12 * 2 = 24
i=0: output[0] = 1 * 24 = 24, right = 24 * 1 = 24

Final output: [24, 12, 8, 6]

Tree visualization of products:
For output[1] = 12:
    product of all = 1*2*3*4 = 24
    except nums[1] = 24 / 2 = 12

    OR (our approach)
    = (product of left) * (product of right)
    = 1 * (3 * 4)
    = 1 * 12
    = 12
```

## Code Walkthrough

```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] output = new int[n];

    // Step 1: Left products
    // output[i] contains product of all elements to the left
    output[0] = 1;
    for (int i = 1; i < n; i++) {
        output[i] = output[i - 1] * nums[i - 1];
    }

    // Step 2: Right products combined
    // rightProduct tracks product of all elements to the right
    int rightProduct = 1;
    for (int i = n - 1; i >= 0; i--) {
        output[i] *= rightProduct;  // Combine with right product
        rightProduct *= nums[i];     // Update for next iteration (move left)
    }

    return output;
}

// Alternative: Using auxiliary arrays (more space)
public int[] productExceptSelfWithSpace(int[] nums) {
    int n = nums.length;
    int[] left = new int[n];
    int[] right = new int[n];
    int[] output = new int[n];

    // Left[i] = product of all elements to left of i
    left[0] = 1;
    for (int i = 1; i < n; i++) {
        left[i] = left[i - 1] * nums[i - 1];
    }

    // Right[i] = product of all elements to right of i
    right[n - 1] = 1;
    for (int i = n - 2; i >= 0; i--) {
        right[i] = right[i + 1] * nums[i + 1];
    }

    // Combine left and right
    for (int i = 0; i < n; i++) {
        output[i] = left[i] * right[i];
    }

    return output;
}
```

## Edge Cases

1. **Length 2**: `[1,2]` -> `[2,1]`
2. **All ones**: `[1,1,1,1]` -> `[1,1,1,1]`
3. **All same**: `[2,2,2,2]` -> `[8,8,8,8]`
4. **With zero**: `[0,1,2]` -> `[2,0,0]` (only output[0] has non-zero)
5. **Large numbers**: Products may overflow (use long if needed)
6. **Negative numbers**: `[-1,1,-2]` -> `[-2,2,-1]`
7. **Large array**: Handle efficiently for n=10000+

## Related Problems

1. **LeetCode 448**: Find All Numbers Disappeared in an Array
2. **LeetCode 152**: Maximum Product Subarray
3. **LeetCode 891**: Sum of Subsequence Widths
4. **LeetCode 1590**: Make Sum Divisible by P - Modulo product
5. **LeetCode 2219**: Maximum Sum of Products After K Operations

## Tags

- Array
- Prefix Product
- Suffix Product
- Mathematical
- No Division
- Difficulty: Medium
- Acceptance: ~55%
