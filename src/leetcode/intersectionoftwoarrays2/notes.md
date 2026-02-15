# Intersection of Two Arrays II

## Problem Statement
**LeetCode Problem 350**: Intersection of Two Arrays II (Easy)

Given two arrays, write a function to compute their intersection.

**Note**:
- Each element in the result should appear as many times as it shows in both arrays
- The result can be in any order

### Examples
**Example 1:**
```
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
```

**Example 2:**
```
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9] or [9,4]
```

## Key Insights
1. **Duplicates Matter**: Unlike problem 349, we need to include duplicate occurrences
2. **Frequency Counting**: Need to track how many times each element appears
3. **Two Main Approaches**:
   - HashMap/HashSet with List (preserves duplicates)
   - Two pointers on sorted arrays
4. **Key Difference from Part I**: Use ArrayList instead of HashSet to allow duplicates

## Algorithm Steps

### Approach 1: HashSet + ArrayList
```
1. Add all elements of one array to HashSet
2. Iterate through second array:
   - If element found in HashSet, add to result ArrayList
   - Note: This approach has a flaw - it doesn't properly handle frequencies
3. Better: Use HashMap to count frequencies
```

### Approach 2: Two Pointers (Optimal)
```
1. Sort both arrays
2. Use two pointers starting at 0
3. While both pointers are valid:
   - If nums1[i] == nums2[j]:
     - Add to result list
     - Move both pointers
   - If nums1[i] < nums2[j]: move i forward
   - If nums1[i] > nums2[j]: move j forward
4. Convert list to array
```

## Complexity Analysis

### Two-Pointer Approach (Optimal):
- **Time Complexity**: O(n log n + m log m)
  - O(n log n) to sort first array
  - O(m log m) to sort second array
  - O(n + m) to find intersection
- **Space Complexity**: O(1) excluding output
  - Only uses pointers and result list

### HashMap Approach:
- **Time Complexity**: O(n + m)
  - O(n) to build frequency map
  - O(m) to check and build result
- **Space Complexity**: O(min(n, m)) for the map

## Visual Representation

### Example: nums1 = [1,2,2,1], nums2 = [2,2]
```
Two-Pointer Approach:
---------------------
Step 1: Sort both arrays
nums1: [1, 1, 2, 2]
nums2: [2, 2]

Step 2: Use two pointers
Initial state:
   [1, 1, 2, 2]    [2, 2]
    ^               ^
    i               j

Iteration 1: 1 < 2, move i
   [1, 1, 2, 2]    [2, 2]
       ^            ^
       i            j

Iteration 2: 1 < 2, move i
   [1, 1, 2, 2]    [2, 2]
          ^         ^
          i         j

Iteration 3: 2 == 2, add 2, move both
   [1, 1, 2, 2]    [2, 2]
             ^         ^
             i         j
   result: [2]

Iteration 4: 2 == 2, add 2, move both
   [1, 1, 2, 2]    [2, 2]
              ^          ^
           (done)    (done)
   result: [2, 2]

Final result: [2, 2]
```

### Example: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
```
Step 1: Sort
nums1: [4, 5, 9]
nums2: [4, 4, 8, 9, 9]

Step 2: Two pointers
   [4, 5, 9]       [4, 4, 8, 9, 9]
    ^               ^
4 == 4: add 4, result: [4]

   [4, 5, 9]       [4, 4, 8, 9, 9]
       ^               ^
5 > 4: move j

   [4, 5, 9]       [4, 4, 8, 9, 9]
       ^                  ^
5 < 8: move i

   [4, 5, 9]       [4, 4, 8, 9, 9]
          ^               ^
9 > 8: move j

   [4, 5, 9]       [4, 4, 8, 9, 9]
          ^                  ^
9 == 9: add 9, result: [4, 9]

Final result: [4, 9]
```

## Code Walkthrough

### Approach: Two Pointers (Recommended)
```java
public int[] intersectionUsingPointers(int[] nums1, int[] nums2) {
    // Sort both arrays first
    Arrays.sort(nums1);
    Arrays.sort(nums2);

    // Use ArrayList to collect duplicates
    List<Integer> intersection = new ArrayList<>();
    int i = 0;
    int j = 0;

    // Two-pointer traversal
    while (i < nums1.length && j < nums2.length) {
        if (nums1[i] == nums2[j]) {
            // Found match: add to result and move both pointers
            intersection.add(nums1[i]);
            i++;
            j++;
        } else if (nums1[i] < nums2[j]) {
            // nums1[i] is smaller, move i to find larger value
            i++;
        } else {
            // nums2[j] is smaller, move j to find larger value
            j++;
        }
    }

    // Convert ArrayList to array
    int[] result = new int[intersection.size()];
    int k = 0;
    for (int n : intersection) {
        result[k++] = n;
    }
    return result;
}
```

### HashMap Approach (Better for Frequency Tracking)
```java
public int[] intersection(int[] nums1, int[] nums2) {
    // Build frequency map for nums1
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums1) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }

    // Find intersection with frequency consideration
    List<Integer> result = new ArrayList<>();
    for (int num : nums2) {
        if (map.containsKey(num) && map.get(num) > 0) {
            result.add(num);
            map.put(num, map.get(num) - 1);  // Decrement count
        }
    }

    // Convert to array
    int[] arr = new int[result.size()];
    for (int i = 0; i < result.size(); i++) {
        arr[i] = result.get(i);
    }
    return arr;
}
```

## Edge Cases
1. **Empty arrays**: Return empty array
2. **No intersection**: Return empty array
3. **Different frequencies**: Return minimum frequency for each element
4. **One array is subset**: Return the entire subset with frequencies
5. **All same elements**: Return appropriate count based on both arrays

### Edge Case Examples
```
nums1 = [1,1,1], nums2 = [1,1]
Output: [1,1]  (limited by nums2)

nums1 = [3,3], nums2 = [3,3,3,3]
Output: [3,3]  (limited by nums1)

nums1 = [], nums2 = [1,2]
Output: []

nums1 = [1], nums2 = [1]
Output: [1]
```

## Follow-up Questions

### Q1: What if arrays are already sorted?
**Answer**: Skip sorting step, directly apply two-pointer approach. Time: O(n+m)

### Q2: What if nums1's size is small compared to nums2's size?
**Answer**:
- Build frequency map from nums1 (smaller array)
- Iterate through nums2
- Uses less space for the map

### Q3: What if elements of nums2 are stored on disk?
**Answer**:
- Build frequency map from nums1 in memory
- Stream nums2 from disk in chunks
- Process each chunk against the map
- Avoids loading entire nums2 into memory

## Related Problems
- **Intersection of Two Arrays (LeetCode 349)**: Unique elements only
- **Find Common Characters (LeetCode 1002)**: Intersection with frequencies across strings
- **Minimum Index Sum of Two Lists (LeetCode 599)**: Similar matching concept
- **Intersection of Multiple Arrays**: Extend to n arrays

## Tags
- Array
- HashMap
- Two Pointers
- Sorting
- Frequency Counting
- Easy
- Follow-up Questions
