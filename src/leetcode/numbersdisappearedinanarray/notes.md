# LeetCode 448: Find All Numbers Disappeared in an Array

## Problem Statement
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

**Constraint**: Could you do it without extra space and in O(n) runtime?

## Examples

**Example 1:**
```
Input: [4,3,2,7,8,2,3,1]
Output: [5,6]
Explanation: Numbers 5 and 6 never appear
```

**Example 2:**
```
Input: [1,1]
Output: [2]
Explanation: Number 2 never appears
```

**Example 3:**
```
Input: [10]
Output: [1,2,3,4,5,6,7,8,9]
Explanation: All numbers except 10 are missing (but 10 > n, so all are missing)
```

## Key Insights

1. **Fixed Range**: Numbers are in range [1, n], so we can use array indices
2. **Index Marking**: Mark indices corresponding to seen numbers
3. **Negative Marking**: Use sign of element to mark without extra space
4. **Two Pass**: One pass to mark, one to find missing

## Algorithm Steps

1. **First Pass - Marking**:
   - For each number `num` in array:
     - Calculate index = |num| - 1
     - Mark element at that index as negative

2. **Second Pass - Finding**:
   - For each index i:
     - If element at i is positive, i+1 is missing

## Complexity Analysis

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(n) - Two passes through array |
| **Space Complexity** | O(1) - Only input array modification, output list doesn't count |
| **Best Case** | O(n) - All elements seen |
| **Worst Case** | O(n) - One element repeated n times |

Perfect solution meeting all constraints!

## ASCII Visualization

```
Input: [4,3,2,7,8,2,3,1]

Initial: [4, 3, 2, 7, 8, 2, 3, 1]
         [0, 1, 2, 3, 4, 5, 6, 7] (indices)

First Pass - Mark indices:
Process 4: index=3, nums[3]=7 → nums[3]=-7
  [4, 3, 2, -7, 8, 2, 3, 1]

Process 3: index=2, nums[2]=2 → nums[2]=-2
  [4, 3, -2, -7, 8, 2, 3, 1]

Process 2: index=1, nums[1]=3 → nums[1]=-3
  [4, -3, -2, -7, 8, 2, 3, 1]

Process 7: index=6, nums[6]=3 → nums[6]=-3
  [4, -3, -2, -7, 8, 2, -3, 1]

Process 8: index=7, nums[7]=1 → nums[7]=-1
  [4, -3, -2, -7, 8, 2, -3, -1]

Process 2: index=1, nums[1]=-3 (already negative)
  [4, -3, -2, -7, 8, 2, -3, -1]

Process 3: index=2, nums[2]=-2 (already negative)
  [4, -3, -2, -7, 8, 2, -3, -1]

Process 1: index=0, nums[0]=4 → nums[0]=-4
  [-4, -3, -2, -7, 8, 2, -3, -1]

Second Pass - Find missing:
Index 0: -4 (negative, 1 is present)
Index 1: -3 (negative, 2 is present)
Index 2: -2 (negative, 3 is present)
Index 3: -7 (negative, 4 is present)
Index 4: 8 (positive, 5 is MISSING)
Index 5: 2 (positive, 6 is MISSING)
Index 6: -3 (negative, 7 is present)
Index 7: -1 (negative, 8 is present)

Output: [5, 6]

Mark visualization:
Present numbers: 1, 2, 3, 4, 7, 8
Missing numbers: 5, 6
```

## Code Walkthrough

```java
public static List<Integer> findDisappearedNumbers(int[] nums) {
    List<Integer> result = new ArrayList<>();

    // First pass: Mark indices of seen numbers as negative
    for (int num : nums) {
        int val = Math.abs(num) - 1;  // Get index (1-indexed to 0-indexed)

        if (nums[val] > 0) {  // Only mark once
            nums[val] = -nums[val];  // Make negative
        }
    }

    // Second pass: Find indices that are still positive
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] > 0) {
            result.add(i + 1);  // i+1 because array is 0-indexed but numbers are 1-indexed
        }
    }

    return result;
}
```

**Key Operations:**
- `Math.abs(num)`: Get absolute value (handle already negative)
- `num - 1`: Convert to 0-indexed position
- `nums[val] = -nums[val]`: Toggle sign to mark as seen
- Second pass collects positive indices

## Edge Cases

1. **No missing numbers**: `[1,2,3]` → `[]`
2. **All missing**: `[3,3,3]` → `[1,2]`
3. **Single element**: `[1]` → `[]`
4. **Single element, no match**: `[2]` → `[1]`
5. **Duplicates**: `[1,1,1,1]` → `[2,3,4]`
6. **Unordered**: `[4,3,2,7,8,2,3,1]` → `[5,6]`

## Related Problems

1. **LeetCode 41** - First Missing Positive (Find first missing, not all)
2. **LeetCode 442** - Find All Duplicates in an Array (Find duplicates instead)
3. **LeetCode 287** - Find the Duplicate Number (Single duplicate)
4. **LeetCode 268** - Missing Number (Single missing in [0,n])
5. **LeetCode 645** - Set Mismatch (One missing, one duplicate)

## Tags

`Array` `In-Place` `Marking` `Medium` `Amazon` `Google` `Facebook` `LinkedIn`

## Alternative Approaches

### Approach 2: Hash Set
```java
public List<Integer> findDisappearedNumbers(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    for (int num : nums) {
        seen.add(num);
    }

    List<Integer> result = new ArrayList<>();
    for (int i = 1; i <= nums.length; i++) {
        if (!seen.contains(i)) {
            result.add(i);
        }
    }
    return result;
}
```
- Time: O(n)
- Space: O(n) extra space (violates constraint)

### Approach 3: Sorting
```
- Sort the array
- Check for gaps in sorted sequence
- Time: O(n log n)
- Space: O(1) or O(n) depending on sort type
```

## Pattern Recognition

This is the "**Mark-and-Scan**" pattern:
1. Use array indices as hash (fixed range [1, n])
2. Mark seen elements by modifying array values
3. Unmodified elements indicate missing numbers

**Similar problems using this pattern:**
- Find duplicate numbers
- Find single numbers among duplicates
- Track presence/absence with limited space

## Implementation Notes

1. **Sign as Marker**: Clever use of positive/negative to mark
2. **Absolute Value**: Necessary because we mark negatively
3. **Two Pass**: Clean separation of marking and scanning
4. **No Extra Space**: Critical for this problem
5. **Order Independence**: Doesn't matter if array is sorted

## Performance Comparison

```
findDisappearedNumbers([4,3,2,7,8,2,3,1]):

Mark-and-Scan:   O(n) time, O(1) space ✓
Hash Set:        O(n) time, O(n) space
Sorting:         O(n log n) time, O(1) space
```

## Notes

- This is a classic "trick" interview question
- Key insight: Use indices as hash function
- Perfect example of in-place marking algorithm
- Great practice for understanding array manipulation
- Foundation for many "missing number" type problems
- Shows importance of constraints (O(1) space requirement)
