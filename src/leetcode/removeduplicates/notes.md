# Remove Duplicates from Sorted Array - LeetCode Problem 26

## Problem Statement
Given a sorted array `nums`, remove the duplicates in-place such that each element appears only once and return the new length.

Do not allocate extra space for another array, you must do this in-place with constant memory.

Constraints:
- Array is sorted
- Duplicates must be removed in-place
- Return the new length
- Elements after new length don't matter

## Examples

**Example 1:**
- Input: nums = [1,1,2]
- Output: 2, nums = [1,2,_]
- Explanation: Function should return length = 2, with the first two elements being 1 and 2

**Example 2:**
- Input: nums = [0,0,1,1,1,2,2,3,3,4]
- Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]

## Key Insights
1. **Two Pointer Technique**: One pointer for traversal, one for placement
2. **In-Place Modification**: Only overwrite duplicate elements
3. **Sorted Assumption**: Makes it easy to detect duplicates
4. **No Need to Delete**: Just rearrange; elements after length don't matter
5. **Virtual Removal**: Don't actually remove, just shift unique elements

## Algorithm Steps

### Approach: Two Pointer

**Step 1: Initialize**
- i = 1 (traverse from second element)
- j = 1 (position for next unique element)

**Step 2: Iterate**
- If nums[i] != nums[i-1] (new unique element):
  - Place at position j: nums[j] = nums[i]
  - Increment j
- Always increment i

**Step 3: Return**
- Return j (count of unique elements)

**Pseudocode:**
```
function removeDuplicates(nums):
    if nums.length == 0:
        return 0

    j = 1
    for i from 1 to nums.length:
        if nums[i] != nums[i-1]:
            nums[j] = nums[i]
            j++

    return j
```

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time Complexity | O(n) where n = nums.length |
| Space Complexity | O(1) constant space |

**Time Analysis:**
- Single pass through array: O(n)
- Each element visited once

**Space Analysis:**
- Only variables used: i, j
- No additional data structures

## ASCII Visualization

```
Example: [1,1,2]

Initial State:
nums: |1|1|2|
       i=1
       j=1

Iteration 1: i=1
nums[1]=1, nums[0]=1 (equal, don't move)
nums: |1|1|2|
         i→
       j=1

Iteration 2: i=2
nums[2]=2, nums[1]=1 (not equal, copy)
nums[2] = nums[2] (place 2)
nums[j] = nums[2]
nums: |1|2|2|
           ↑placed
         j→

Final: j=2, return 2
Result: [1,2,_]

Example: [0,0,1,1,1,2,2,3,3,4]

Visual Simulation:
Original: |0|0|1|1|1|2|2|3|3|4|
          i=1,j=1

Step by step:
i=1: 0==0, skip
i=2: 1!=0, nums[1]=1, j→2
i=3: 1==1, skip
i=4: 1==1, skip
i=5: 2!=1, nums[2]=2, j→3
i=6: 2==2, skip
i=7: 3!=2, nums[3]=3, j→4
i=8: 3==3, skip
i=9: 4!=3, nums[4]=4, j→5

Result: |0|1|2|3|4| (first 5 elements)
Length: 5

Virtual Removal Visualization:
Original: 0  0  1  1  1  2  2  3  3  4
          ↓  ×  ↓  ×  ×  ↓  ×  ↓  ×  ↓
Result:   0  1  2  3  4  ×  ×  ×  ×  ×
Keep:     ✓  ✓  ✓  ✓  ✓
```

## Code Walkthrough

```java
public int removeDuplicates(int[] nums) {
    int i = 1;  // Traverse from second element
    int j = 1;  // Position for next unique element

    while (i < nums.length) {
        // Check if current element is different from previous
        if (nums[i] != nums[i - 1]) {
            // Place unique element at position j
            nums[j] = nums[i];
            j++;
        }
        // Always increment i
        i++;
    }

    // j is the count of unique elements
    return j;
}

// More concise version
public int removeDuplicatesCondensed(int[] nums) {
    if (nums.length == 0) return 0;

    int j = 1;
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] != nums[i - 1]) {
            nums[j++] = nums[i];
        }
    }
    return j;
}

// Alternative: using HashSet (not in-place)
public int removeDuplicatesSet(int[] nums) {
    Set<Integer> unique = new LinkedHashSet<>(Arrays.asList(
        Arrays.stream(nums).boxed().toArray(Integer[]::new)));

    int j = 0;
    for (int num : unique) {
        nums[j++] = num;
    }
    return j;
}
```

## Edge Cases

1. **Single Element**: [1] -> length = 1
2. **All Same**: [1,1,1,1] -> length = 1, nums = [1,_,_,_]
3. **All Different**: [1,2,3,4] -> length = 4
4. **Empty Array**: [] -> length = 0
5. **Two Elements Same**: [1,1] -> length = 1
6. **Two Elements Different**: [1,2] -> length = 2
7. **Negatives**: [-1,-1,0,1] -> length = 3
8. **Large Array**: 10000+ elements

## Related Problems

1. **LeetCode 80**: Remove Duplicates from Sorted Array II - Allow duplicates up to 2
2. **LeetCode 27**: Remove Element - Remove specific value
3. **LeetCode 283**: Move Zeroes - Move zeros to end
4. **LeetCode 26**: Remove Duplicates - This problem
5. **LeetCode 88**: Merge Sorted Array - In-place merge
## Tags

- Array
- Two Pointers
- In-Place Modification
- Sorted Array
- Easy Difficulty
- Acceptance: ~50%
