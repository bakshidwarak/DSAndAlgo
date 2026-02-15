# Intersection of Two Arrays

## Problem Statement
**LeetCode Problem 349**: Intersection of Two Arrays (Easy)

Given two arrays, write a function to compute their intersection.

**Note**:
- Each element in the result must be unique
- The result can be in any order

### Examples
**Example 1:**
```
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
```

**Example 2:**
```
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4] or [4,9]
```

## Key Insights
1. **Unique Elements Only**: Use HashSet to automatically handle duplicates
2. **Two Approaches**:
   - HashSet approach: O(n) time with O(n) space
   - Two-pointer approach: O(n log n) time with O(1) extra space
3. **Optimization**: Hash the larger array, iterate through smaller
4. **Set Operations**: This is essentially set intersection problem

## Algorithm Steps

### Approach 1: Two HashSets
```
1. Determine which array is larger
2. Add all elements of larger array to HashSet
3. Iterate through smaller array:
   - If element exists in HashSet, add to result set
4. Convert result set to array
```

### Approach 2: Two Pointers (After Sorting)
```
1. Sort both arrays
2. Use two pointers (i, j) starting at 0
3. While both pointers are valid:
   - If nums1[i] == nums2[j]: add to result, move both pointers
   - If nums1[i] < nums2[j]: move i forward
   - If nums1[i] > nums2[j]: move j forward
4. Use HashSet for result to handle duplicates
5. Convert to array
```

## Complexity Analysis

### HashSet Approach:
- **Time Complexity**: O(n + m)
  - O(n) to build HashSet from first array
  - O(m) to check second array
- **Space Complexity**: O(n) for HashSet

### Two-Pointer Approach:
- **Time Complexity**: O(n log n + m log m)
  - O(n log n) to sort first array
  - O(m log m) to sort second array
  - O(n + m) to find intersection
- **Space Complexity**: O(1) if we don't count result space

## Visual Representation

### Example: nums1 = [1,2,2,1], nums2 = [2,2]
```
HashSet Approach:
-----------------
Step 1: Hash larger array (nums1)
HashSet: {1, 2}

Step 2: Check nums2 against HashSet
Check 2: Found in HashSet -> Add to result
Check 2: Found in HashSet -> Already in result set

Result: {2}

Two-Pointer Approach:
---------------------
Step 1: Sort arrays
nums1: [1, 1, 2, 2]
nums2: [2, 2]

Step 2: Two pointers
i=0, j=0: 1 < 2, move i
   [1, 1, 2, 2]
    ^
   [2, 2]
    ^

i=1, j=0: 1 < 2, move i
   [1, 1, 2, 2]
       ^
   [2, 2]
    ^

i=2, j=0: 2 == 2, add 2, move both
   [1, 1, 2, 2]
          ^
   [2, 2]
       ^

i=3, j=1: 2 == 2, already in result, move both
   [1, 1, 2, 2]
             ^
   [2, 2]
          ^

Result: [2]
```

## Code Walkthrough

### Approach 1: Two HashSets
```java
public int[] intersection(int[] nums1, int[] nums2) {
    HashSet<Integer> intersection = new HashSet<Integer>();
    HashSet<Integer> numbersInBigArray = new HashSet<Integer>();

    // Optimize: hash the larger array
    int[] toHash = nums1;
    int[] toCompare = nums2;
    if (nums2.length > nums1.length) {
        toHash = nums2;
        toCompare = nums1;
    }

    // Build HashSet from larger array
    for (int i = 0; i < toHash.length; i++) {
        numbersInBigArray.add(toHash[i]);
    }

    // Check smaller array against HashSet
    for (int j = 0; j < toCompare.length; j++) {
        if (numbersInBigArray.contains(toCompare[j])) {
            intersection.add(toCompare[j]);  // Set handles duplicates
        }
    }

    // Convert HashSet to array
    int[] result = new int[intersection.size()];
    int i = 0;
    for (int n : intersection) {
        result[i++] = n;
    }
    return result;
}
```

### Approach 2: Two Pointers
```java
public int[] intersectionUsingPointers(int[] nums1, int[] nums2) {
    // Sort both arrays
    Arrays.sort(nums1);
    Arrays.sort(nums2);

    Set<Integer> intersection = new HashSet<>();
    int i = 0;
    int j = 0;

    // Two pointer traversal
    while (i < nums1.length && j < nums2.length) {
        if (nums1[i] == nums2[j]) {
            intersection.add(nums1[i]);  // Add to set (handles duplicates)
            i++;
            j++;
        } else if (nums1[i] < nums2[j]) {
            i++;  // nums1[i] too small, move forward
        } else {
            j++;  // nums2[j] too small, move forward
        }
    }

    // Convert to array
    int[] result = new int[intersection.size()];
    int k = 0;
    for (int n : intersection) {
        result[k++] = n;
    }
    return result;
}
```

## Edge Cases
1. **Empty arrays**: Return empty array
2. **No intersection**: Return empty array
3. **One array is subset of other**: Return the subset
4. **All elements same**: Return single element
5. **Arrays with duplicates**: Result should have unique elements only
6. **Different sizes**: Algorithm should handle efficiently

## Follow-up Questions
1. **What if arrays are already sorted?**
   - Use two-pointer approach directly (no sorting needed)
2. **What if nums1 is very small compared to nums2?**
   - Hash nums1 and iterate through nums2
3. **What if nums2 is stored on disk?**
   - Hash nums1 in memory, stream nums2 from disk

## Related Problems
- **Intersection of Two Arrays II (LeetCode 350)**: Include duplicates
- **Find Common Characters**: Intersection across multiple strings
- **Intersection of Multiple Arrays**: Extend to n arrays
- **Valid Sudoku**: Uses similar HashSet technique
- **Two Sum**: Another HashSet application

## Tags
- Array
- HashSet
- Two Pointers
- Sorting
- Set Operations
- Easy
- Google Interview
