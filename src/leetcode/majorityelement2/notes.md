# LeetCode 229: Majority Element II

## Problem Statement
Given an integer array of size n, find all elements that appear **more than ⌊n/3⌋ times**. The algorithm must run in **linear time** and use **O(1) space** (excluding output).

## Difficulty
Medium

## Examples

### Example 1
- **Input:** `[3, 2, 3]`
- **Output:** `[3]`
- **Explanation:** 3 appears 2 times which is > 3/3 = 1

### Example 2
- **Input:** `[1, 1, 1, 3, 3, 2, 2, 2]`
- **Output:** `[1, 2]`
- **Explanation:** Both 1 and 2 appear more than 8/3 = 2.66 times

## Key Insights
1. **Moore Voting Algorithm Variant**: Can have at most 2 elements appearing more than n/3 times
2. **Two Candidate Approach**: Maintain two candidates and their counts simultaneously
3. **Verification Step**: After finding candidates, verify they actually appear more than n/3 times
4. **Count Decrement Strategy**: When both counts are 0, replace with new element

## Algorithm Steps
1. Find up to 2 candidates:
   - Maintain two variables: ele1, ele2 with counts: count1, count2
   - For each element:
     - If it matches ele1, increment count1
     - Else if it matches ele2, increment count2
     - Else if count1 is 0, make it new ele1
     - Else if count2 is 0, make it new ele2
     - Else decrement both counts
2. Verify candidates by counting actual occurrences
3. Add those appearing > n/3 times to result

## Complexity Analysis
- **Time Complexity:** O(n) - Two passes through array
- **Space Complexity:** O(1) - Only storing two candidate elements and their counts

## ASCII Visualization

```
Array: [1, 1, 1, 3, 3, 2, 2, 2]
n = 8, threshold = 8/3 = 2

Pass 1 (Find candidates):
i=0: ele1=1, count1=1, count2=0
i=1: ele1=1, count1=2, count2=0
i=2: ele1=1, count1=3, count2=0
i=3: ele1=1, count1=3, ele2=3, count2=1
i=4: ele1=1, count1=3, ele2=3, count2=2
i=5: ele2=3, count2=2, ele1=1, count1=2 (count--), ele2=2
i=6: ele1=1, count1=1, ele2=2, count2=2
i=7: ele1=1, count1=1, ele2=2, count2=3

Candidates: ele1=1, ele2=2

Pass 2 (Verify):
Count 1: appears 3 times (> 2.66) ✓
Count 2: appears 3 times (> 2.66) ✓
Result: [1, 2]
```

## Edge Cases
1. **No majority elements:** Return empty list
2. **One element repeated:** Return that element
3. **All different elements:** Return empty list
4. **Array with 3 elements:** One element must appear > 1 time
5. **Two majority elements:** Both must be returned
6. **Single element array:** Return that element

## Related Problems
- **LeetCode 169:** Majority Element (more than n/2)
- **LeetCode 1157:** Online Majority Element In Subarray
- **LeetCode 2157:** Group Anagrams Together

## Tags
`Array` `Voting Algorithm` `Majority Element`
