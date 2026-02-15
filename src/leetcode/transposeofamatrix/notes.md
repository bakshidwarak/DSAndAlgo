# Transpose of a Matrix (LeetCode 867)

## Problem Statement
Given a 2D integer matrix A, return the transpose of A.

The transpose of a matrix is the matrix flipped over its main diagonal, switching the row and column indices of the matrix.

**In other words:** result[j][i] = A[i][j]

## Examples
```
Example 1:
Input: A = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[1,4,7],[2,5,8],[3,6,9]]

Original:     Transpose:
  1 2 3         1 4 7
  4 5 6         2 5 8
  7 8 9         3 6 9

Example 2:
Input: A = [[1,2],[3,4]]
Output: [[1,3],[2,4]]

Original:   Transpose:
  1 2         1 3
  3 4         2 4

Example 3:
Input: A = [[1,2,3,4],[5,6,7,8]]
Output: [[1,5],[2,6],[3,7],[4,8]]

Original (2x4):   Transpose (4x2):
  1 2 3 4         1 5
  5 6 7 8         2 6
                  3 7
                  4 8
```

## Key Insights
1. Transpose converts an m×n matrix to an n×m matrix
2. The element at position [i][j] moves to position [j][i]
3. For row i and column j in original, we place at row j and column i in result
4. New matrix dimensions: if original is m×n, result is n×m
5. This is a straightforward element-by-element mapping

## Algorithm Steps

### Approach: Direct Transpose
1. Check for null or empty matrix
2. Create result matrix with swapped dimensions: new int[cols][rows]
3. Iterate through original matrix:
   - For each row i from 0 to rows-1
   - For each column j from 0 to cols-1
   - Set result[j][i] = A[i][j]
4. Return result

## Complexity Analysis
- **Time Complexity:** O(m × n) - Visit each element once
- **Space Complexity:** O(m × n) - Space for the result matrix (not counting input)

## ASCII Visualization

```
Original Matrix A (3x3):
    j=0  j=1  j=2
i=0  1    2    3
i=1  4    5    6
i=2  7    8    9

Transpose process:
For each element A[i][j], place it at result[j][i]

result matrix dimensions: 3x3 (swap of 3x3)

A[0][0]=1 -> result[0][0]=1
A[0][1]=2 -> result[1][0]=2
A[0][2]=3 -> result[2][0]=3
A[1][0]=4 -> result[0][1]=4
A[1][1]=5 -> result[1][1]=5
A[1][2]=6 -> result[2][1]=6
A[2][0]=7 -> result[0][2]=7
A[2][1]=8 -> result[1][2]=8
A[2][2]=9 -> result[2][2]=9

Result Matrix (3x3):
    j=0  j=1  j=2
i=0  1    4    7
i=1  2    5    8
i=2  3    6    9

---

Original Matrix A (2x4):
    j=0  j=1  j=2  j=3
i=0  1    2    3    4
i=1  5    6    7    8

result dimensions: 4x2 (swap of 2x4)

A[0][0]=1 -> result[0][0]=1
A[0][1]=2 -> result[1][0]=2
A[0][2]=3 -> result[2][0]=3
A[0][3]=4 -> result[3][0]=4
A[1][0]=5 -> result[0][1]=5
A[1][1]=6 -> result[1][1]=6
A[1][2]=7 -> result[2][1]=7
A[1][3]=8 -> result[3][1]=8

Result Matrix (4x2):
    j=0  j=1
i=0  1    5
i=1  2    6
i=2  3    7
i=3  4    8
```

## Code Walkthrough

```java
public int[][] transpose(int[][] A) {
    // Handle edge cases
    if (A == null || A.length == 0 || A[0].length == 0)
        return A;

    int rows = A.length;
    int cols = A[0].length;

    // Create result matrix with swapped dimensions
    int[][] result = new int[cols][rows];

    // Fill result matrix by transposing
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            result[j][i] = A[i][j];
        }
    }

    return result;
}
```

## In-Place Alternative (For Square Matrices Only)

```java
// For square matrices only (m = n)
public int[][] transpose(int[][] A) {
    for (int i = 0; i < A.length; i++) {
        for (int j = i + 1; j < A[0].length; j++) {
            // Swap A[i][j] and A[j][i]
            int temp = A[i][j];
            A[i][j] = A[j][i];
            A[j][i] = temp;
        }
    }
    return A;
}
```

## Edge Cases
1. Single element: [[1]] → [[1]]
2. Single row: [[1,2,3]] → [[1],[2],[3]]
3. Single column: [[1],[2],[3]] → [[1,2,3]]
4. 1×n matrix: [[1,2,3,4]] → [[1],[2],[3],[4]]
5. m×1 matrix: [[1],[2],[3]] → [[1,2,3]]
6. Null matrix: null → null
7. Empty matrix: [[]] → May have issues, handle carefully

## Properties of Transpose
1. (A^T)^T = A (double transpose is original)
2. (A + B)^T = A^T + B^T
3. (A × B)^T = B^T × A^T
4. Transpose of diagonal matrix is itself

## Related Problems
- LeetCode 48: Rotate Image
- LeetCode 498: Diagonal Traverse
- LeetCode 1572: Matrix Diagonal Sum
- LeetCode 54: Spiral Matrix

## Tags
- Array
- Matrix
- Simulation
- 2D Array
