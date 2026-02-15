# LeetCode 119: Pascal's Triangle II

## Problem Statement
Given a non-negative index k where k ≤ 33, return the kth index row of Pascal's triangle.

Note that the row index starts from 0.

In Pascal's triangle, each number is the sum of the two numbers directly above it.

## Examples

**Example 1:**
```
Input: 3
Output: [1,3,3,1]

Pascal's Triangle:
     [1]           (row 0)
    [1,1]          (row 1)
   [1,2,1]         (row 2)
  [1,3,3,1]        (row 3) <- Answer
```

**Example 2:**
```
Input: 0
Output: [1]
```

**Example 3:**
```
Input: 4
Output: [1,4,6,4,1]
```

## Key Insights

1. **Recursive Pattern**: Each row builds from the previous row
2. **Symmetry**: Each row is symmetric (mirror structure)
3. **Base Case**: First element and last element are always 1
4. **Pascal's Formula**: middle elements = sum of two elements above

## Algorithm Steps

1. **Base Case**: If rowIndex == 0, return [1]

2. **Recursive Case**:
   - Get previous row (rowIndex - 1)
   - Build current row with length rowIndex + 1
   - First element = 1
   - Middle elements = prev[i] + prev[i-1]
   - Last element = 1

3. **Return** the constructed current row

## Complexity Analysis

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(k²) - Compute all rows from 0 to k |
| **Space Complexity** | O(k) - Return list + recursion stack |

- Each row i has i+1 elements
- Sum: 1 + 2 + 3 + ... + k = k(k+1)/2 ≈ O(k²)

## ASCII Visualization

```
Pascal's Triangle Construction:

Row 0: [1]

Row 1: [1, 1]
       Based on row 0

Row 2: [1, 2, 1]
       1 = first
       2 = 1+1 (from row 1)
       1 = last

Row 3: [1, 3, 3, 1]
       1 = first
       3 = 1+2 (from row 2, index 0+1)
       3 = 2+1 (from row 2, index 1+2)
       1 = last

Row 4: [1, 4, 6, 4, 1]
       1 = first
       4 = 1+3
       6 = 3+3
       4 = 3+1
       1 = last

Build for k=3:
getRow(3):
  getRow(2):
    getRow(1):
      getRow(0):
        return [1]
      Build row 1 from row 0:
        prev = [1]
        current = [1]
        add last 1
        return [1, 1]
    Build row 2 from row 1:
      prev = [1, 1]
      current = [1]
      middle: prev[0] + prev[1] = 1 + 1 = 2
      current = [1, 2]
      add last 1
      return [1, 2, 1]
  Build row 3 from row 2:
    prev = [1, 2, 1]
    current = [1]
    middle: prev[0] + prev[1] = 1 + 2 = 3
    middle: prev[1] + prev[2] = 2 + 1 = 3
    current = [1, 3, 3]
    add last 1
    return [1, 3, 3, 1]
```

## Code Walkthrough

```java
public List<Integer> getRow(int rowIndex) {
    // Base case: first row is [1]
    if (rowIndex == 0) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        return list;
    }

    // Recursive case: get previous row
    List<Integer> prev = getRow(rowIndex - 1);
    List<Integer> current = new ArrayList<>();

    // First element is always 1
    current.add(prev.get(0));

    // Build middle elements
    for (int i = 1; i < prev.size(); i++) {
        // Sum of two adjacent elements from previous row
        int sum = prev.get(i) + prev.get(i - 1);
        current.add(sum);
    }

    // Last element is always 1
    current.add(prev.get(prev.size() - 1));

    return current;
}
```

**Detailed Walkthrough for k=3:**
```
Call: getRow(3)
  Calls: getRow(2)
    Calls: getRow(1)
      Calls: getRow(0)
        Returns: [1]

      prev = [1]
      current = [1]
      Loop i=1 (i < 1 is false, skip)
      current.add(prev.get(0)) → [1, 1]
      Returns: [1, 1]

    prev = [1, 1]
    current = [1]
    Loop:
      i=1: sum = prev.get(1) + prev.get(0) = 1+1 = 2
           current = [1, 2]
    current.add(prev.get(1)) → [1, 2, 1]
    Returns: [1, 2, 1]

  prev = [1, 2, 1]
  current = [1]
  Loop:
    i=1: sum = prev.get(1) + prev.get(0) = 2+1 = 3
         current = [1, 3]
    i=2: sum = prev.get(2) + prev.get(1) = 1+2 = 3
         current = [1, 3, 3]
  current.add(prev.get(2)) → [1, 3, 3, 1]
  Returns: [1, 3, 3, 1]
```

## Edge Cases

1. **Row 0**: `getRow(0)` → `[1]`
2. **Row 1**: `getRow(1)` → `[1, 1]`
3. **Row 2**: `getRow(2)` → `[1, 2, 1]`
4. **Large k**: `getRow(33)` → Large array but works

## Related Problems

1. **LeetCode 118** - Pascal's Triangle (Generate all rows up to n)
2. **LeetCode 120** - Triangle (Use Pascal's triangle pattern for path sum)
3. **LeetCode 62** - Unique Paths (Related combinatorics)
4. [Unique Paths II](../uniquepathsII/notes.md)
5. **LeetCode 64** - Minimum Path Sum (DP similar to triangle)

## Tags

`Array` `DP` `Recursion` `Easy` `Google` `Amazon` `Facebook`

## Alternative Approaches

### Approach 2: Iterative Construction
```java
public List<Integer> getRowIterative(int rowIndex) {
    List<Integer> current = new ArrayList<>();
    current.add(1);

    for (int i = 1; i <= rowIndex; i++) {
        List<Integer> next = new ArrayList<>();
        next.add(1);

        for (int j = 1; j < i; j++) {
            next.add(current.get(j-1) + current.get(j));
        }

        next.add(1);
        current = next;
    }

    return current;
}
```
- Time: O(k²)
- Space: O(k) for current row (avoids recursion stack)

### Approach 3: Space-Optimized Iterative
```
- Build row in-place by updating from right to left
- Avoids creating new list for each row
- Time: O(k²)
- Space: O(1) extra (excluding output)
```

### Approach 4: Using Combinatorics
```
- Row k has binomial coefficients C(k, 0), C(k, 1), ..., C(k, k)
- Can compute directly without recursion
- Time: O(k)
- Space: O(k)
```

## Implementation Notes

1. **First/Last Elements**: Always 1 in every row
2. **Middle Building**: Sum adjacent pairs from previous row
3. **Index Alignment**: careful with previous row indexing
4. **Recursive Overhead**: Recursion stack depth = O(k)

## Performance Comparison

```
Approach             Time    Space   Notes
Recursive            O(k²)   O(k)    Intuitive, uses recursion
Iterative            O(k²)   O(k)    Avoids recursion stack
Combinatorics        O(k)    O(k)    Most efficient but complex
```

## Mathematical Insight

Each element in row k at position i is the binomial coefficient C(k,i):
```
C(k, i) = k! / (i! × (k-i)!)

For row 3:
C(3,0) = 1
C(3,1) = 3
C(3,2) = 3
C(3,3) = 1
```

## Notes

- Classic recursive problem with clean structure
- Foundation for understanding combinatorics
- Demonstrates building complex structures from simpler ones
- Pascal's triangle has many applications in combinatorics and probability
- Good practice for recursion and list manipulation
