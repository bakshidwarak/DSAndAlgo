# LeetCode 904: Fruit Into Baskets

## Problem Statement

You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array `fruits` where `fruits[i]` is the type of fruit the `ith` tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
- You only have **two baskets**, and each basket can only hold a **single type** of fruit. There is no limit on the amount of fruit each basket can hold.
- Starting from any tree of your choice, you must pick **exactly one fruit from every tree** (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
- Once you reach a tree with fruit that cannot fit in your baskets, you must stop.

Given the integer array `fruits`, return the **maximum** number of fruits you can pick.

### Examples

**Example 1:**
```
Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.
```

**Example 2:**
```
Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].
```

**Example 3:**
```
Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we started at the first tree, we would only pick from trees [1,2].
```

**Constraints:**
- 1 <= fruits.length <= 10^5
- 0 <= fruits[i] < fruits.length

## Key Insights

1. **Sliding Window Problem**: Find longest subarray with at most 2 distinct elements
2. **Two Baskets = Two Types**: Can only have 2 distinct fruit types at any time
3. **Contiguous Collection**: Must pick fruits continuously from left to right
4. **HashMap for Counting**: Track count of each fruit type in current window
5. **Dynamic Window**: Expand right, shrink left when constraint violated

## Algorithm Steps

### Sliding Window Approach

1. **Initialize**:
   - Left pointer at 0
   - HashMap to count fruit types
   - maxFruits = 0

2. **Expand Window** (move right pointer):
   - Add current fruit to map
   - While map has more than 2 fruit types:
     - Remove fruits from left
     - Move left pointer right
     - Remove fruit type if count becomes 0

3. **Track Maximum**:
   - Update maxFruits = max(maxFruits, right - left + 1)

4. **Return** maxFruits

## Complexity Analysis

- **Time Complexity**: O(n)
  - Right pointer moves n times
  - Left pointer moves at most n times
  - Each element processed at most twice
  - Overall: O(n)

- **Space Complexity**: O(1)
  - HashMap stores at most 3 fruit types (before shrinking)
  - Constant space regardless of input size
  - Overall: O(1)

## Visual Explanation

### Example: fruits = [1,2,3,2,2]

```
Step-by-step sliding window:

Window [1]: {1:1} -> 1 fruit ✓
Window [1,2]: {1:1, 2:1} -> 2 fruits ✓
Window [1,2,3]: {1:1, 2:1, 3:1} -> 3 types ✗
  Shrink: remove 1
Window [2,3]: {2:1, 3:1} -> 2 fruits ✓
Window [2,3,2]: {2:2, 3:1} -> 3 fruits ✓
Window [2,3,2,2]: {2:3, 3:1} -> 4 fruits ✓

Maximum: 4 (from [2,3,2,2])
```

### Detailed Trace

```
fruits = [1, 2, 3, 2, 2]
         ^
         left=0, right=0

Step 1: Add 1
  map = {1:1}
  count = 1, max = 1

Step 2: Add 2
  map = {1:1, 2:1}
  count = 2, max = 2

Step 3: Add 3
  map = {1:1, 2:1, 3:1}
  3 types! Need to shrink
  Remove from left: 1
  map = {2:1, 3:1}
  left = 1
  count = 2, max = 2

Step 4: Add 2
  map = {2:2, 3:1}
  count = 3, max = 3

Step 5: Add 2
  map = {2:3, 3:1}
  count = 4, max = 4

Final answer: 4
```

### Window Visualization

```
fruits = [1, 2, 3, 2, 2]

Valid windows (at most 2 types):
[1]         length 1
[1,2]       length 2  ✓
[2]         length 1
[2,3]       length 2
[2,3,2]     length 3
[2,3,2,2]   length 4  ✓ MAXIMUM
[3]         length 1
[3,2]       length 2
[3,2,2]     length 3
[2]         length 1
[2,2]       length 2

Maximum valid window: 4
```

## Code Walkthrough

### HashMap-based Solution

```java
public int totalFruit(int[] fruits) {
    Map<Integer, Integer> basket = new HashMap<>();
    int left = 0;
    int maxFruits = 0;

    // Expand window with right pointer
    for (int right = 0; right < fruits.length; right++) {
        // Add current fruit to basket
        basket.put(fruits[right],
                  basket.getOrDefault(fruits[right], 0) + 1);

        // Shrink window if more than 2 types
        while (basket.size() > 2) {
            // Remove fruit from left
            basket.put(fruits[left],
                      basket.get(fruits[left]) - 1);

            // Remove type if count becomes 0
            if (basket.get(fruits[left]) == 0) {
                basket.remove(fruits[left]);
            }

            left++;
        }

        // Update maximum
        maxFruits = Math.max(maxFruits, right - left + 1);
    }

    return maxFruits;
}
```

### Optimized Solution (Track Last Two Types)

```java
public int totalFruit(int[] fruits) {
    int type1 = -1, type2 = -1;
    int count1 = 0, count2 = 0;
    int maxFruits = 0, currentCount = 0;

    for (int fruit : fruits) {
        if (fruit == type1) {
            count1++;
            currentCount++;
        } else if (fruit == type2) {
            count2++;
            currentCount++;
        } else {
            // New type, replace older type
            currentCount = count2 + 1;
            type1 = type2;
            count1 = count2;
            type2 = fruit;
            count2 = 1;
        }

        // Update if type2 streak continues
        if (fruit == type2) {
            count2++;
        } else {
            count2 = 0;
        }

        maxFruits = Math.max(maxFruits, currentCount);
    }

    return maxFruits;
}
```

## Problem Restatement

This problem is equivalent to:
- **Longest subarray with at most K distinct elements** (where K=2)
- **Maximum contiguous sequence with 2 unique values**

## Edge Cases

1. **Single Fruit Type**: fruits = [1,1,1,1]
   - Output: 4 (all fruits)

2. **Two Fruit Types**: fruits = [1,2,1,2,1,2]
   - Output: 6 (all fruits)

3. **All Different**: fruits = [1,2,3,4,5]
   - Output: 2 (any adjacent pair)

4. **Three Types**: fruits = [1,1,2,2,3,3]
   - Output: 4 ([1,1,2,2] or [2,2,3,3])

5. **Single Element**: fruits = [1]
   - Output: 1

6. **Two Elements**: fruits = [1,2]
   - Output: 2

7. **Alternating**: fruits = [1,2,1,2,1,2]
   - Output: 6

## Variations

### Generalize to K Baskets

```java
public int totalFruitK(int[] fruits, int k) {
    Map<Integer, Integer> basket = new HashMap<>();
    int left = 0, maxFruits = 0;

    for (int right = 0; right < fruits.length; right++) {
        basket.put(fruits[right],
                  basket.getOrDefault(fruits[right], 0) + 1);

        while (basket.size() > k) {
            basket.put(fruits[left],
                      basket.get(fruits[left]) - 1);
            if (basket.get(fruits[left]) == 0) {
                basket.remove(fruits[left]);
            }
            left++;
        }

        maxFruits = Math.max(maxFruits, right - left + 1);
    }

    return maxFruits;
}
```

## Related Problems

1. **LeetCode 3**: Longest Substring Without Repeating Characters
2. **LeetCode 159**: Longest Substring with At Most Two Distinct Characters
3. **LeetCode 340**: Longest Substring with At Most K Distinct Characters
4. **LeetCode 76**: Minimum Window Substring
5. **LeetCode 209**: Minimum Size Subarray Sum
6. **LeetCode 424**: Longest Repeating Character Replacement

## Tags

- Array
- Hash Table
- Sliding Window
- Two Pointers
- String (when generalized)
- At Most K Distinct Elements
