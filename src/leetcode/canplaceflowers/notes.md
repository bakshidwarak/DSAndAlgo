# 605. Can Place Flowers

## Problem Statement
Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

### Examples
```
Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: true
Explanation: Can plant at index 2

Example 2:
Input: flowerbed = [1,0,0,0,1], n = 2
Output: false
Explanation: Can only plant at index 2, need space for 2 flowers
```

### Constraints
- The input array won't violate no-adjacent-flowers rule
- The input array size is in the range of [1, 20000]
- n is a non-negative integer which won't exceed the input array size
- 0 means empty plot, 1 means occupied

## Approach & Solution

### Key Insights
1. **Recursive checking**: Recursively check if we can place n flowers starting from index
2. **Greedy placement**: Place flower as soon as valid spot is found
3. **Skip occupied plots**: When encountering 1, skip next position too
4. **Look-ahead validation**: Check if next position is also empty before placing

### Algorithm Steps
1. Handle special case: single empty plot can have 1 flower
2. Start recursive placement from index 0
3. For each position:
   - If n == 0, we've placed all flowers, return true
   - If reached end of array, return false
   - If current position is 1, skip to index+2 (can't plant adjacent)
   - If current position is 0:
     - Check if next position is also 0 OR we're at second-to-last position
     - If valid, place flower (recursively check with n-1, index+2)
     - If not valid, skip to next position (index+1)
4. Return false if unable to place all flowers

### Complexity Analysis
- **Time Complexity**: O(n)
  - Where n is the length of flowerbed array
  - Each position visited at most once
  - Recursion doesn't revisit positions
- **Space Complexity**: O(n)
  - Recursion stack depth can be up to n in worst case
  - Can be optimized to O(1) with iterative approach

### Visualization
```
Example: flowerbed = [1,0,0,0,1], n = 1

Index:  0  1  2  3  4
Value: [1, 0, 0, 0, 1]
        ●  ○  ○  ○  ●  (● = planted, ○ = empty)

Process:
i=0: value=1 (occupied)
     Skip to i=2

i=2: value=0 (empty)
     Check i+1: value=0 (also empty)
     Can place! n becomes 0
     Return true

Visual placement:
Before: [1, 0, 0, 0, 1]
         ●  ○  ○  ○  ●

After:  [1, 0, X, 0, 1]
         ●  ○  ●  ○  ●
               ↑
            placed here

Example: flowerbed = [0,0,1,0,0], n = 2

Index:  0  1  2  3  4
Value: [0, 0, 1, 0, 0]

i=0: value=0, next=0
     Place flower! n=1
     Jump to i=2

i=2: value=1
     Skip to i=4

i=4: value=0, at second-to-last
     Place flower! n=0
     Success!

Result: [X, 0, 1, 0, X]
         ●  ○  ●  ○  ●
```

## Code Walkthrough

```java
public boolean canPlaceFlowers(int[] flowerbed, int n) {
    // Special case: single empty plot
    if (flowerbed.length == 1 && flowerbed[0] == 0) {
        return n <= 1;
    }

    return canPlace(flowerbed, 0, n);
}

public boolean canPlace(int[] bed, int index, int n) {
    // Base case: all flowers placed
    if (n == 0)
        return true;

    // Base case: reached end of array
    if (index >= bed.length)
        return false;

    // If current position is occupied
    if (bed[index] == 1) {
        // Skip to position after next (can't plant adjacent)
        return canPlace(bed, index + 2, n);
    }

    // Current position is empty
    if (bed[index] == 0) {
        // Check if we can plant here
        if (index + 1 < bed.length && bed[index + 1] == 0) {
            // Next position is also empty, plant here
            return canPlace(bed, index + 2, n - 1);
        } else if (index + 1 == bed.length && index > 0 && bed[index - 1] == 0) {
            // At last position and previous is empty
            return canPlace(bed, index + 2, n - 1);
        } else {
            // Can't plant here, try next position
            return canPlace(bed, index + 1, n);
        }
    }

    return false;
}
```

**Iterative Approach (Cleaner):**
```java
public boolean canPlaceFlowers(int[] flowerbed, int n) {
    int count = 0;

    for (int i = 0; i < flowerbed.length; i++) {
        if (flowerbed[i] == 0) {
            boolean leftEmpty = (i == 0) || (flowerbed[i-1] == 0);
            boolean rightEmpty = (i == flowerbed.length-1) || (flowerbed[i+1] == 0);

            if (leftEmpty && rightEmpty) {
                flowerbed[i] = 1;  // Plant flower
                count++;
                if (count >= n) return true;
            }
        }
    }

    return count >= n;
}
```

## Edge Cases
- **Single empty plot**: [0], n=1 → true
- **Single occupied plot**: [1], n=1 → false
- **All occupied**: [1,1,1], n=1 → false
- **All empty**: [0,0,0], n=2 → true (plant at 0 and 2)
- **Alternating**: [1,0,1,0,1], n=1 → false (no valid positions)
- **n = 0**: Always return true
- **Large array with scattered 1s**: Correctly counts available spots
- **End positions**: [0,1,0], n=1 → true (can plant at index 0)

## Related Problems
- **495. Teemo Attacking**: Similar interval-based logic
- **1326. Minimum Number of Taps to Open to Water a Garden**: Greedy interval covering

## Tags
`array` `greedy` `easy`
