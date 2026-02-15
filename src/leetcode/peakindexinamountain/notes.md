# LeetCode 852: Peak Index in a Mountain Array

## Problem Statement
Let's call an array A a mountain if the following properties hold:

1. A.length >= 3
2. There exists some 0 < i < A.length - 1 such that:
   - A[0] < A[1] < ... < A[i-1] < A[i]
   - A[i] > A[i+1] > ... > A[A.length - 1]

Given an array that is definitely a mountain, return any i such that the conditions are satisfied.

## Examples

**Example 1:**
```
Input: [0,1,0]
Output: 1
```

**Example 2:**
```
Input: [0,2,1,0]
Output: 1
```

**Example 3:**
```
Input: [0,1,2,1,0]
Output: 2
```

## Key Insights

1. **Strictly Increasing Then Decreasing**: Unique peak guarantees monotonicity
2. **Binary Search**: Can use binary search since monotonic
3. **Three Cases**: Position at mid can be in increasing part, at peak, or in decreasing part
4. **Move Toward Peak**: Based on comparison with neighbors

## Algorithm Steps

1. **Binary Search Setup**: low = 0, high = length - 1

2. **Three Conditions at Mid**:
   - If A[mid-1] < A[mid] and A[mid] >= A[mid+1]: peak found
   - If A[mid-1] < A[mid] and A[mid] < A[mid+1]: peak is in right half
   - If A[mid-1] > A[mid] and A[mid] > A[mid+1]: peak is in left half

3. **Return**: Index where all conditions are satisfied

## Complexity Analysis

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(log n) - Binary search |
| **Space Complexity** | O(1) - Only pointers |

Perfect efficiency for this problem!

## ASCII Visualization

```
Array: [0, 2, 1, 0]
Index: [0, 1, 2, 3]

Mountain shape:
      *peak (1)
     / \
    /   \
   /     \
  0       0
  .---2---. (array values)

Binary Search:
Initial: low=0, high=3
mid = (0+3)/2 = 1

Check at mid=1:
A[0]=0 < A[1]=2? Yes
A[1]=2 >= A[2]=1? Yes
Found peak at index 1 ✓

For array [0, 1, 2, 1, 0]:
      peak(2)
       / \
      /   \
     /     \
    1       0
0---------0

Initial: low=0, high=4
mid = 2

Check at mid=2:
A[1]=1 < A[2]=2? Yes
A[2]=2 >= A[3]=1? Yes
Found peak at index 2 ✓

For array [0, 1, 2, 3, 2, 1]:
          peak(3)
           / \
          /   \
         /     \
        2       0
0------3--------1

Initial: low=0, high=5
mid = 2

Check at mid=2:
A[1]=1 < A[2]=2? Yes
A[2]=2 < A[3]=3? Yes → Peak in right half
low = mid + 1 = 3

low=3, high=5
mid = 4

Check at mid=4:
A[3]=3 > A[4]=2? Yes
A[4]=2 > A[5]=1? Yes → Peak in left half
high = mid = 4

low=3, high=4
mid = 3

Check at mid=3:
A[2]=2 < A[3]=3? Yes
A[3]=3 >= A[4]=2? Yes
Found peak at index 3 ✓
```

## Code Walkthrough

```java
public int peakIndexInMountainArray(int[] A) {
    return getPeak(A, 0, A.length - 1);
}

public int getPeak(int[] A, int start, int end) {
    int mid = (start + end) / 2;

    // Check if mid is the peak
    // Condition: A[mid-1] < A[mid] and A[mid] >= A[mid+1]
    if (mid > 0 && mid < A.length &&
        A[mid - 1] < A[mid] && A[mid] >= A[mid + 1]) {
        return mid;
    }

    // Peak is in right half
    // Condition: A[mid-1] < A[mid] and A[mid] < A[mid+1]
    if (mid > 0 && mid < A.length &&
        A[mid - 1] < A[mid] && A[mid] < A[mid + 1]) {
        return getPeak(A, mid + 1, end);
    }

    // Peak is in left half
    // Condition: A[mid-1] > A[mid] and A[mid] > A[mid+1]
    if (mid > 0 && mid < A.length &&
        A[mid - 1] > A[mid] && A[mid] > A[mid + 1]) {
        return getPeak(A, start, mid);
    }

    return -1;  // Should not reach here
}
```

**Execution for [0, 2, 1, 0]:**
```
getPeak([0,2,1,0], 0, 3):
  mid = 1
  Check condition 1:
    A[0]=0 < A[1]=2? Yes
    A[1]=2 >= A[2]=1? Yes
    return 1 ✓

Peak found at index 1
```

## Edge Cases

1. **Minimum length**: `[0,1,0]` → 1 (smallest mountain)
2. **Symmetric**: `[0,2,1,0]` → Any peak works
3. **Right peak**: Peak near end `[0,1,2,3,2,1,0]` → 3
4. **Left peak**: Peak near start (still valid mountain) → Varies

## Related Problems

1. **LeetCode 33** - Search in Rotated Sorted Array (Similar binary search)
2. **LeetCode 154** - Find Minimum in Rotated Sorted Array II (Rotation variant)
3. **LeetCode 162** - Find Peak Element (Any valid peak, not full mountain)
4. **LeetCode 1095** - Find in Mountain Array (Search in mountain)
5. **LeetCode 1671** - Minimum Number of Removals to Make Mountain Array

## Tags

`Binary Search` `Array` `Easy` `Google` `Amazon` `Facebook`

## Alternative Approaches

### Approach 2: Linear Search
```java
public int peakIndexLinear(int[] A) {
    for (int i = 1; i < A.length - 1; i++) {
        if (A[i-1] < A[i] && A[i] > A[i+1]) {
            return i;
        }
    }
    return -1;
}
```
- Time: O(n)
- Space: O(1)
- Simpler but slower

### Approach 3: Simplified Binary Search
```java
public int peakIndexSimple(int[] A) {
    int left = 0, right = A.length - 1;

    while (left < right) {
        int mid = (left + right) / 2;
        if (A[mid] < A[mid + 1]) {
            left = mid + 1;  // Peak is right
        } else {
            right = mid;  // Peak is left or at mid
        }
    }

    return left;  // or right, they're equal
}
```
- Cleaner code
- Same O(log n) complexity

## Implementation Notes

1. **Conditions**: Three conditions cover all cases
2. **Boundary Check**: mid > 0 and mid < length ensure valid indexing
3. **Recursion**: Can also implement iteratively
4. **Comparison**: A[mid-1] vs A[mid] vs A[mid+1] determines direction

## Common Mistakes

1. **Boundary Check**: Must verify mid > 0 before accessing mid-1
2. **Equality Check**: Use >= not just > for the peak condition
3. **Direction Logic**: Mix-up which direction to search
4. **Recursion Base**: May not return anything in some paths

## Optimization Notes

1. **Binary Search**: Essential due to log time requirement
2. **Early Exit**: Stop as soon as peak found
3. **Comparison**: Only two comparisons per iteration

## Performance Comparison

```
Approach          Time    Space   Notes
Linear            O(n)    O(1)    Simple but slow
Binary Search     O(log n) O(1)   Optimal for guaranteed mountain
Simplified Search O(log n) O(log n) Less code, recursive version
```

## Notes

- Clean binary search problem
- Guarantees: monotonicity makes search trivial
- Good foundation for more complex binary search problems
- Demonstrates search space reduction
- Tests understanding of search direction logic
