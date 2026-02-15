# 3Sum (LeetCode 15)

## Problem Statement
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j != k and nums[i] + nums[j] + nums[k] == 0.

The solution set must not contain duplicate triplets.

## Examples
```
Example 1:
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]

Example 2:
Input: nums = [0]
Output: []

Example 3:
Input: nums = [-2,0,1]
Output: []

Example 4:
Input: nums = [-1,-1,0,1]
Output: [[-1,0,1]]
```

## Key Insights
1. We need to find three numbers that sum to zero
2. Sorting allows us to use two-pointer technique after fixing one element
3. Sorting also makes it easy to skip duplicates
4. For each element, we find a two-sum that equals the negative of that element
5. Use HashSet to avoid duplicate triplets in result

## Algorithm Steps

### Approach: Sort + Two Pointers + HashSet for Duplicates
1. Sort the array
2. For each element at index i (as the first element of triplet):
   - Use two pointers: j = i+1, l = n-1
   - While j < l:
     - Calculate sum = nums[i] + nums[j] + nums[l]
     - If sum == 0: Add to set as a Pair (to avoid duplicates), move both pointers
     - If sum < 0: Move j forward (need larger sum)
     - If sum > 0: Move l backward (need smaller sum)
3. Convert set of Pairs to list of lists

## Complexity Analysis
- **Time Complexity:** O(n²) - Sorting O(n log n) + For each element, two-pointer search is O(n)
- **Space Complexity:** O(1) or O(n) depending on whether we count the output

## ASCII Visualization

```
Original: [-1, 0, 1, 2, -1, -4]
After sorting: [-4, -1, -1, 0, 1, 2]
Indices:        0   1   2   3  4  5

Target: sum = 0

i = 0 (nums[0] = -4):
  Need: nums[j] + nums[l] = 4
  j=1, l=5: -1 + 2 = 1 (< 4), j++
  j=2, l=5: -1 + 2 = 1 (< 4), j++
  j=3, l=5: 0 + 2 = 2 (< 4), j++
  j=4, l=5: 1 + 2 = 3 (< 4), j++
  j=5, l=5: j >= l, stop
  No triplet found

i = 1 (nums[1] = -1):
  Need: nums[j] + nums[l] = 1
  j=2, l=5: -1 + 2 = 1 (== 1), found! [-1, -1, 2]
    j++, l--
  j=3, l=4: 0 + 1 = 1 (== 1), found! [-1, 0, 1]
    j++, l--
  j=4, l=4: j >= l, stop

i = 2 (nums[2] = -1):
  j=3, l=5: 0 + 2 = 2 (> 1), l--
  j=3, l=4: 0 + 1 = 1 (== 1), found! [-1, 0, 1]
    But this is duplicate, so check with HashSet
  j++, l--
  j=4, l=4: j >= l, stop

i = 3 (nums[3] = 0):
  Need: nums[j] + nums[l] = 0
  j=4, l=5: 1 + 2 = 3 (> 0), l--
  j=4, l=4: j >= l, stop

Result: [[-1, -1, 2], [-1, 0, 1]]
```

## Code Walkthrough

```java
public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();

    HashSet<Pair> resultPairs = new HashSet<>();
    if (nums.length < 3)
        return result;

    // Sort array for two-pointer technique
    Arrays.sort(nums);

    // For each element, find two-sum
    for (int i = 0; i < nums.length; i++) {
        int j = i + 1;
        int l = nums.length - 1;

        while (j < l) {
            // Check if sum equals 0
            if (nums[j] + nums[l] == -1 * nums[i]) {
                resultPairs.add(new Pair(nums[i], nums[j], nums[l]));
                j++;
                l--;
                continue;
            }
            else if (nums[j] + nums[l] < -1 * nums[i]) {
                j++;  // Need larger sum
            }
            else {
                l--;  // Need smaller sum
            }
        }
    }

    // Convert Pair objects to List<Integer>
    for (Pair p : resultPairs) {
        result.add(getList(p.num1, p.num2, p.num3));
    }

    return result;
}

public List<Integer> getList(int num1, int num2, int num3) {
    List<Integer> result = new ArrayList<>();
    result.add(num1);
    result.add(num2);
    result.add(num3);
    return result;
}
```

## Edge Cases
1. Empty array: [] → []
2. Array with < 3 elements: [1, 2] → []
3. All zeros: [0, 0, 0] → [[0, 0, 0]]
4. No solution: [1, 2, 3] → []
5. Negative numbers: [-1, -2, -3] → []
6. Mixed with duplicates: [-1, -1, 2, 0, 1] → [[-1, -1, 2], [-1, 0, 1]]

## Related Problems
- LeetCode 16: 3Sum Closest
- LeetCode 18: 4Sum
- LeetCode 167: Two Sum II
- LeetCode 1: Two Sum

## Tags
- Array
- Two Pointers
- Sorting
- Hashing
- No Duplicates
