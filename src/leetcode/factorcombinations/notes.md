# LeetCode 254: Factor Combinations

## Problem Statement

Numbers can be regarded as the product of their factors. For example:
```
8 = 2 x 2 x 2
  = 2 x 4
```

Write a function that takes an integer `n` and returns all possible combinations of its factors.

**Note:**
- You may assume that `n` is always positive
- Factors should be in the range [2, n-1]
- The number n itself is not considered a factor combination

### Examples

**Example 1:**
```
Input: n = 1
Output: []
Explanation: 1 has no factors other than itself
```

**Example 2:**
```
Input: n = 12
Output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
```

**Example 3:**
```
Input: n = 37
Output: []
Explanation: 37 is prime
```

**Example 4:**
```
Input: n = 32
Output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
```

**Constraints:**
- 1 <= n <= 10^7

## Key Insights

1. **Backtracking Problem**: Generate all valid combinations using backtracking
2. **Factor Condition**: A number i is a factor if n % i == 0
3. **Start Parameter**: Need to track starting point to avoid duplicates
4. **Base Case**: When n becomes 1, we've found a valid combination
5. **Size Check**: Only add combinations with more than 1 factor (exclude n itself)
6. **Optimization**: Only need to check factors up to n

## Algorithm Steps

### Backtracking Approach

1. **Initialize**:
   - Result list to store all combinations
   - Current factors list to track current combination
   - Start at 2 (smallest factor)

2. **Base Case**:
   - If n <= 1 and currentFactors has more than 1 element
   - Add current combination to results

3. **Recursive Case**:
   - For each i from start to n:
     - If i divides n evenly (n % i == 0):
       - Add i to current factors
       - Recurse with n/i, starting from i
       - Remove i (backtrack)

4. **Return** all found combinations

## Complexity Analysis

- **Time Complexity**: O(2^log n)
  - In worst case, explore all possible factorizations
  - Number of factorizations is bounded by partitions of prime factorization
  - For highly composite numbers, can be exponential
  - Roughly O(2^k) where k is number of prime factors

- **Space Complexity**: O(log n)
  - Recursion depth limited by number of times n can be divided
  - Maximum depth when repeatedly dividing by 2: log2(n)
  - Storage for currentFactors: O(log n)
  - Result storage not counted in space complexity

## Visual Explanation

### Example: n = 12

```
Factor tree exploration:

                    12
        /     /      |      \      \
       2     3       4       6      12
      /|    / \      |       |
     6 |   4   |     3       2
    /| |  / \  |     |
   2 3 | 2  2  |     |
   | | |    |  |     |
   3 2 |    |  |     |
     | |    |  |     |
  [2,2,3] [2,6] [3,4]

Valid combinations (size > 1):
[2, 2, 3]
[2, 6]
[3, 4]
```

### Detailed Trace for n = 12

```
getFactors(12)
  factorsHelper(12, [], 2)

Level 1: n=12, start=2
  i=2: 12%2=0 ✓
    currentFactors = [2]
    factorsHelper(6, [2], 2)

    Level 2: n=6, start=2
      i=2: 6%2=0 ✓
        currentFactors = [2, 2]
        factorsHelper(3, [2,2], 2)

        Level 3: n=3, start=2
          i=2: 3%2=1 ✗
          i=3: 3%3=0 ✓
            currentFactors = [2, 2, 3]
            factorsHelper(1, [2,2,3], 3)
            n=1, size>1 ✓
            ADD [2, 2, 3] ✓
            backtrack

      i=3: 6%3=0 ✓
        currentFactors = [2, 3]
        factorsHelper(2, [2,3], 3)
        Level 3: n=2, start=3
          i=3: 3>2, exit loop
          i=2: (not reached)
        No more factors
        backtrack

      i=4,5,6: skip (not factors or too large)

    Backtrack to Level 1

  i=3: 12%3=0 ✓
    currentFactors = [3]
    factorsHelper(4, [3], 3)

    Level 2: n=4, start=3
      i=3: 4%3=1 ✗
      i=4: 4%4=0 ✓
        currentFactors = [3, 4]
        factorsHelper(1, [3,4], 4)
        n=1, size>1 ✓
        ADD [3, 4] ✓

  i=4: 12%4=0 ✓
    currentFactors = [4]
    factorsHelper(3, [4], 4)
    Level 2: n=3, start=4
      i=4,5,...: all > 3
    Only [4, 3] but not added (size check fails here)

  i=6: 12%6=0 ✓
    currentFactors = [6]
    factorsHelper(2, [6], 6)
    Level 2: n=2, start=6
      i=6,7,...: all > 2
    [6,2] but 2 found
    Actually processes:
      i=2 is less than start=6, so loop continues
      Eventually finds [2,6]

Final result: [[2,6], [2,2,3], [3,4]]
```

### Example: n = 32 (Power of 2)

```
32 factorizations:
[2, 16]         -> 2 * 16
[2, 2, 8]       -> 2 * 2 * 8
[2, 2, 2, 4]    -> 2 * 2 * 2 * 4
[2, 2, 2, 2, 2] -> 2^5
[2, 4, 4]       -> 2 * 4 * 4
[4, 8]          -> 4 * 8

Tree shows many 2's because 32 = 2^5
```

## Code Walkthrough

```java
public List<List<Integer>> getFactors(int n) {
    List<List<Integer>> factors = new ArrayList<>();
    List<Integer> currentFactors = new ArrayList<>();

    // Start backtracking from 2 (smallest factor)
    factorsHelper(n, factors, currentFactors, 2);

    return factors;
}

public void factorsHelper(int n, List<List<Integer>> factors,
                          List<Integer> currentFactors, int start) {
    // Base case: n reduced to 1
    if (n <= 1) {
        // Only add if we have more than 1 factor
        // (to exclude the number itself)
        if (currentFactors.size() > 1) {
            factors.add(new ArrayList<>(currentFactors));
        }
        return;
    }

    // Try all possible factors from start to n
    for (int i = start; i <= n; i++) {
        // Check if i is a factor of n
        if (n % i == 0) {
            // Choose: add factor to current combination
            currentFactors.add(i);

            // Explore: recursively find factors of n/i
            // Start from i to avoid duplicates (e.g., [2,3] vs [3,2])
            factorsHelper(n / i, factors, currentFactors, i);

            // Unchoose: backtrack
            currentFactors.remove(currentFactors.size() - 1);
        }
    }
}
```

## Why Start Parameter?

Without start parameter, we get duplicates:

```
n = 12, without start:
[2, 6] and [6, 2] both generated
[2, 3, 2] and [3, 2, 2] both generated

With start parameter:
Only [2, 6] (start=2, then i=6)
Only [2, 2, 3] (factors in ascending order)
```

## Optimizations

### 1. Early Termination

```java
for (int i = start; i * i <= n; i++) {  // Only check up to sqrt(n)
    if (n % i == 0) {
        // Add i
        currentFactors.add(i);
        factorsHelper(n / i, factors, currentFactors, i);
        currentFactors.remove(currentFactors.size() - 1);
    }
}
// Also add n itself as a factor (to terminate combinations)
if (currentFactors.size() > 0) {
    currentFactors.add(n);
    factors.add(new ArrayList<>(currentFactors));
    currentFactors.remove(currentFactors.size() - 1);
}
```

### 2. Prime Check

```java
if (isPrime(n)) {
    return new ArrayList<>();  // No factorizations
}
```

## Edge Cases

1. **n = 1**: No factors
   - Output: []

2. **Prime Number**: n = 37, 13, 7
   - Output: []

3. **Powers of 2**: n = 16, 32, 64
   - Many combinations

4. **Perfect Square**: n = 36
   ```
   [2, 18], [2, 2, 9], [2, 2, 3, 3], [2, 3, 6], [3, 12], [3, 3, 4], [4, 9], [6, 6]
   ```

5. **Small Composite**: n = 4
   - Output: [[2, 2]]

6. **Highly Composite**: n = 24
   - Many factor combinations

## Related Problems

1. **LeetCode 39**: Combination Sum
2. **LeetCode 40**: Combination Sum II
3. **LeetCode 77**: Combinations
4. **LeetCode 78**: Subsets
5. **LeetCode 46**: Permutations
6. **LeetCode 89**: Gray Code

## Tags

- Backtracking
- Recursion
- Math
- Number Theory
- Factorization
- Combination Generation
