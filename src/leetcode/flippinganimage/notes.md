# LeetCode 832: Flipping an Image

## Problem Statement

Given an `n x n` binary matrix `image`, flip the image **horizontally**, then invert it, and return the resulting image.

To flip an image horizontally means that each row of the image is reversed.
- For example, flipping `[1,1,0]` horizontally results in `[0,1,1]`

To invert an image means that each `0` is replaced by `1`, and each `1` is replaced by `0`.
- For example, inverting `[0,1,1]` results in `[1,0,0]`

### Examples

**Example 1:**
```
Input: image = [[1,1,0],[1,0,1],[0,0,0]]
Output: [[1,0,0],[0,1,0],[1,1,1]]
Explanation:
First reverse each row: [[0,1,1],[1,0,1],[0,0,0]]
Then invert the image: [[1,0,0],[0,1,0],[1,1,1]]
```

**Example 2:**
```
Input: image = [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
Explanation:
First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]
Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
```

**Constraints:**
- n == image.length
- n == image[i].length
- 1 <= n <= 20
- images[i][j] is either 0 or 1

## Key Insights

1. **Two Operations**: Flip (reverse) and Invert (XOR with 1)
2. **Row-by-Row Processing**: Each row is independent
3. **XOR Operation**: Inverting 0/1 is equivalent to XOR with 1
   - 0 ^ 1 = 1
   - 1 ^ 1 = 0
4. **Combined Operation**: Can flip and invert simultaneously
5. **In-place Possible**: Can modify the input array directly

## Algorithm Steps

1. **For each row** in the image:
   - For each column from end to beginning (reverse order):
     - Read value from position j (from right)
     - Invert it using XOR: value ^ 1
     - Store at position k (from left)

2. **Return** the modified image

## Complexity Analysis

- **Time Complexity**: O(n^2)
  - Process n rows
  - Each row has n elements
  - Overall: O(n * n) = O(n^2)

- **Space Complexity**: O(n^2)
  - Result array: O(n^2)
  - If modifying in-place: O(1)

## Visual Explanation

### Example 1: Step-by-Step

```
Input: [[1,1,0],[1,0,1],[0,0,0]]

Step 1: Flip horizontally (reverse each row)
Row 0: [1,1,0] -> [0,1,1]
Row 1: [1,0,1] -> [1,0,1]
Row 2: [0,0,0] -> [0,0,0]

After flip: [[0,1,1],[1,0,1],[0,0,0]]

Step 2: Invert (0->1, 1->0)
Row 0: [0,1,1] -> [1,0,0]
Row 1: [1,0,1] -> [0,1,0]
Row 2: [0,0,0] -> [1,1,1]

Final: [[1,0,0],[0,1,0],[1,1,1]]
```

### Combined Operation

```
For row [1,1,0]:

j=2, k=0: A[2]=0, invert: 0^1=1, result[0]=1
j=1, k=1: A[1]=1, invert: 1^1=0, result[1]=0
j=0, k=2: A[0]=1, invert: 1^1=0, result[2]=0

Result: [1,0,0]

This combines flip and invert in one pass!
```

### Visual Grid Transformation

```
Input:
1 1 0
1 0 1
0 0 0

After horizontal flip:
0 1 1
1 0 1
0 0 0

After invert:
1 0 0  ✓
0 1 0  ✓
1 1 1  ✓
```

## Code Walkthrough

```java
public int[][] flipAndInvertImage(int[][] A) {
    // Result array
    int[][] result = new int[A.length][A[0].length];

    // Process each row
    for (int i = 0; i < A.length; i++) {
        // Flip and invert simultaneously
        // j: read from right to left (flip)
        // k: write from left to right
        for (int j = A[0].length - 1, k = 0; j >= 0; j--, k++) {
            // Flip: read from position j
            // Invert: XOR with 1
            result[i][k] = A[i][j] ^ 1;
        }
    }

    return result;
}
```

## In-place Solution

```java
public int[][] flipAndInvertImage(int[][] image) {
    int n = image[0].length;

    for (int[] row : image) {
        // Process each row
        for (int i = 0; i < (n + 1) / 2; i++) {
            // Swap and invert simultaneously
            int temp = row[i] ^ 1;
            row[i] = row[n - 1 - i] ^ 1;
            row[n - 1 - i] = temp;
        }
    }

    return image;
}
```

## XOR Properties Explained

```
XOR with 1 inverts binary values:
0 ^ 1 = 1  (0 becomes 1)
1 ^ 1 = 0  (1 becomes 0)

This is equivalent to:
NOT operation: !value
Arithmetic: 1 - value
Bitwise: value ^ 1
```

## Optimizations

### 1. Skip Middle Element in Odd-Length Rows

For odd-length rows, middle element is flipped onto itself:
```
Row [1, 0, 1] with length 3
Middle element at index 1
After flip: [1, 0, 1] (same position)
After invert: [0, 1, 0]

Middle element: 0 ^ 1 = 1
```

### 2. Early Exit for Symmetric Rows

If row is symmetric and all same value, result is predictable:
```
[0,0,0] -> [1,1,1]
[1,1,1] -> [0,0,0]
```

## Edge Cases

1. **Single Element**: image = [[0]]
   - Flip: [[0]]
   - Invert: [[1]]
   - Output: [[1]]

2. **1x2 Matrix**: image = [[1,0]]
   - Flip: [[0,1]]
   - Invert: [[1,0]]
   - Output: [[1,0]]

3. **All Zeros**: image = [[0,0],[0,0]]
   - Output: [[1,1],[1,1]]

4. **All Ones**: image = [[1,1],[1,1]]
   - Output: [[0,0],[0,0]]

5. **Identity Matrix**: image = [[1,0],[0,1]]
   - Flip: [[0,1],[1,0]]
   - Invert: [[1,0],[0,1]]
   - Back to identity!

6. **Maximum Size**: 20x20 matrix
   - Should handle efficiently

7. **Symmetric Rows**: [1,0,1]
   - Flip: [1,0,1] (unchanged)
   - Invert: [0,1,0]

## Alternative Solutions

### Using Streams (Java 8+)

```java
public int[][] flipAndInvertImage(int[][] image) {
    return Arrays.stream(image)
        .map(row -> {
            // Reverse and invert
            for (int i = 0, j = row.length - 1; i <= j; i++, j--) {
                int temp = row[i] ^ 1;
                row[i] = row[j] ^ 1;
                row[j] = temp;
            }
            return row;
        })
        .toArray(int[][]::new);
}
```

### Functional Approach

```java
public int[][] flipAndInvertImage(int[][] image) {
    for (int[] row : image) {
        reverse(row);
        invert(row);
    }
    return image;
}

private void reverse(int[] arr) {
    for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

private void invert(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        arr[i] ^= 1;
    }
}
```

## Related Problems

1. **LeetCode 867**: Transpose Matrix
2. **LeetCode 48**: Rotate Image
3. **LeetCode 566**: Reshape the Matrix
4. **LeetCode 896**: Monotonic Array
5. **LeetCode 73**: Set Matrix Zeroes
6. **LeetCode 289**: Game of Life

## Tags

- Array
- Matrix
- Two Pointers
- Bit Manipulation
- XOR Operation
- In-place Algorithm
