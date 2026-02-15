# Strobogrammatic Number II (LeetCode 247)

## Problem Statement
Write a function to find all strobogrammatic numbers that are of length n.

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

**Valid mappings:** 0↔0, 1↔1, 6↔9, 8↔8, 9↔6

## Examples
```
Example 1:
Input: n = 1
Output: ["0", "1", "8"]

Example 2:
Input: n = 2
Output: ["11", "69", "88", "96"]

Example 3:
Input: n = 3
Output: ["101", "111", "181", "609", "619", "689", "808", "818", "888", "906", "916", "986"]

Explanation:
For n=1: Only single digits that are strobogrammatic: 0, 1, 8
For n=2: Build from placing pairs - (1,1), (6,9), (8,8), (9,6)
For n=3: Middle must be 0, 1, or 8; sides must form valid pairs
```

## Key Insights
1. Strobogrammatic numbers have a mirror property
2. For even-length numbers, all digits must be paired (one is the rotated version of the other)
3. For odd-length numbers, the middle digit must be self-symmetric (0, 1, or 8)
4. We can build from the center outward, or build recursively from smaller numbers
5. Note: Numbers cannot have leading zeros (except for "0" itself)

## Algorithm Steps

### Approach: Recursive Building from Center
1. Use recursion to build numbers from the center outward
2. Handle base cases:
   - n = 1: Return ["0", "1", "8"]
   - n = 2: Return ["11", "69", "88", "96"]
3. For n > 2:
   - Recursively get all strobogrammatic numbers of length n-2
   - Wrap each with the valid pairs: (0,0), (1,1), (6,9), (8,8), (9,6)
   - Skip (0,0) at the outermost level (no leading zeros)
4. Return all generated numbers

## Complexity Analysis
- **Time Complexity:** O(5^(n/2) * n) - We generate all numbers and each takes O(n) to build
- **Space Complexity:** O(n) for the recursion depth plus O(count) for the result

## ASCII Visualization

```
Building strobogrammatic numbers of length n=3:

Base case n=1: ["0", "1", "8"]

For n=3, we need n-2=1 base:
Start with base: "0", "1", "8"

Wrap "0" with pairs:
  ("1", "1"): "101" ✓
  ("6", "9"): "609" ✓
  ("8", "8"): "808" ✓
  ("9", "6"): "906" ✓
  ("0", "0"): "000" ✗ (skip, leading zero)

Wrap "1" with pairs:
  ("1", "1"): "111" ✓
  ("6", "9"): "619" ✓
  ("8", "8"): "818" ✓
  ("9", "6"): "916" ✓
  ("0", "0"): "010" ✗ (skip, leading zero)

Wrap "8" with pairs:
  ("1", "1"): "181" ✓
  ("6", "9"): "689" ✓
  ("8", "8"): "888" ✓
  ("9", "6"): "986" ✓
  ("0", "0"): "080" ✗ (skip, leading zero)

Result: ["101", "609", "808", "906", "111", "619", "818", "916", "181", "689", "888", "986"]
```

## Code Walkthrough

```java
public List<String> findStrobogrammatic(int n) {
    return helper(n, n);
}

private List<String> helper(int n, int length) {
    List<String> result = new ArrayList<>();

    // Base case
    if (n == 0) {
        result.add("");
        return result;
    }

    if (n == 1) {
        result.add("0");
        result.add("1");
        result.add("8");
        return result;
    }

    // Recursive case: get all strobogrammatic numbers of length n-2
    List<String> middles = helper(n - 2, length);

    // Wrap each middle with valid pairs
    for (String middle : middles) {
        if (n != length) {
            // Not the outermost level, can add (0,0)
            result.add("0" + middle + "0");
        }
        result.add("1" + middle + "1");
        result.add("6" + middle + "9");
        result.add("8" + middle + "8");
        result.add("9" + middle + "6");
    }

    return result;
}
```

## Edge Cases
1. n = 0: [] (empty list)
2. n = 1: ["0", "1", "8"]
3. n = 2: ["11", "69", "88", "96"] (no leading zeros)
4. Large n: May generate very large number of results (5^(n/2))
5. n = 4: All 2-digit strobogrammatic numbers wrapped with valid pairs

## Related Problems
- LeetCode 246: Strobogrammatic Number (Check if a number is strobogrammatic)
- LeetCode 248: Strobogrammatic Number III (Count in range)
- LeetCode 140: Word Break II (Similar recursive generation)
- LeetCode 320: Generalized Abbreviation

## Tags
- String
- Recursion
- Backtracking
- Number Theory
- DFS
