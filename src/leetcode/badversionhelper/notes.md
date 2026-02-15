# 278. First Bad Version

## Problem Statement
You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API `bool isBadVersion(version)` which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

### Examples
```
Input: n = 5, bad = 4
Output: 4
Explanation:
call isBadVersion(3) → false
call isBadVersion(5) → true
call isBadVersion(4) → true
Then 4 is the first bad version.

Input: n = 1, bad = 1
Output: 1
```

### Constraints
- 1 <= bad <= n <= 2^31 - 1
- Need to minimize API calls
- All versions after bad version are also bad

## Approach & Solution

### Key Insights
1. **Binary search**: Since versions are ordered and all bad versions come after the first bad, use binary search
2. **Search space reduction**: If version is bad, search left half; if good, search right half
3. **Overflow prevention**: Use `long` for calculations to avoid integer overflow with large n
4. **Find first occurrence**: When current is bad but previous is good, that's the first bad version

### Algorithm Steps
1. Initialize search range: start = 0, end = n
2. Calculate middle point: mid = (start + end) / 2
3. Check if mid is bad version:
   - If bad and previous (mid-1) is not bad: return mid (first bad version found)
   - If bad: search left half (start, mid-1)
   - If not bad: search right half (mid+1, end)
4. Base case: when start >= end, check if start is bad version
5. Recursively search until first bad version is found

### Complexity Analysis
- **Time Complexity**: O(log n)
  - Binary search divides search space in half each iteration
  - Maximum log₂(n) API calls needed
  - Much better than linear scan O(n)
- **Space Complexity**: O(log n)
  - Recursive call stack depth is O(log n)
  - Each recursive call uses O(1) space
  - Can be optimized to O(1) with iterative approach

### Visualization
```
Example: n = 8, first bad = 5

Versions: [1, 2, 3, 4, 5, 6, 7, 8]
Status:   [G, G, G, G, B, B, B, B]  (G=Good, B=Bad)

Step 1: search(0, 8)
        mid = 4
        isBadVersion(4) → false
        Search right: search(5, 8)

        [1, 2, 3, 4, 5, 6, 7, 8]
         L        M  →  new L

Step 2: search(5, 8)
        mid = 6
        isBadVersion(6) → true
        isBadVersion(5) → true
        Search left: search(5, 5)

        [5, 6, 7, 8]
         L  M  ←

Step 3: search(5, 5)
        start = 5
        isBadVersion(5) → true
        isBadVersion(4) → false
        Return 5 ✓

Total API calls: 4
Linear search would need: 5 calls
```

## Code Walkthrough

```java
public int firstBadVersion(int n) {
    return badVersionHelper(0, n);
}

int badVersionHelper(long strt, long end) {
    long start = strt;
    long n = end;

    // Base case: single element or crossed over
    if (start >= n) {
        return isBadVersion((int) start) ? (int) start : -1;
    }

    // Calculate middle point (using long to prevent overflow)
    Long middle = (n + start) / 2;
    int mid = middle.intValue();

    // Check if mid is a bad version
    if (isBadVersion(mid)) {
        // Check if this is the FIRST bad version
        if (!isBadVersion(mid - 1)) {
            return mid;  // Found it!
        } else {
            // First bad is in left half
            return badVersionHelper(start, mid - 1);
        }
    } else {
        // Mid is good, first bad must be in right half
        return badVersionHelper(mid + 1, n);
    }
}
```

**Key Implementation Details:**
- Uses `long` for start, end, and middle to handle n up to 2^31 - 1
- Checks `mid-1` to determine if `mid` is the first bad version
- Recursive approach with clear base case
- Returns -1 if no bad version found (shouldn't happen per problem constraints)

**Overflow Prevention:**
```java
// Why use long?
// If start and end are both near Integer.MAX_VALUE:
int bad_mid = (start + end) / 2;  // May overflow!
long safe_mid = (start + end) / 2;  // Safe with long
```

## Edge Cases
- **First version is bad**: n=5, bad=1 → return 1
- **Last version is bad**: n=5, bad=5 → return 5
- **Only one version**: n=1, bad=1 → return 1
- **Large n values**: n=2147483647 (Integer.MAX_VALUE) → uses long to prevent overflow
- **Middle is first bad**: Correctly identifies when mid-1 is good and mid is bad
- **All versions are bad**: Returns 1 (first version)

## Related Problems
- **704. Binary Search**: Classic binary search template
- **35. Search Insert Position**: Binary search variant
- **374. Guess Number Higher or Lower**: Similar API-based binary search
- **162. Find Peak Element**: Binary search on unknown array
- **33. Search in Rotated Sorted Array**: Modified binary search

## Tags
`binary-search` `interactive` `easy`
