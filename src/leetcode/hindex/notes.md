# H-Index

## Problem Statement
**LeetCode Problem**: H-Index

Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

### Examples
**Example 1:**
```
Input: citations = [3, 0, 6, 1, 5]
Output: 3
Explanation: The researcher has 5 papers in total and each had received
3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers
with at least 3 citations each and the remaining two with no more than
3 citations each, his h-index is 3.
```

**Note**: If there are several possible values for h, the maximum one is taken as the h-index.

## Key Insights
1. **H-Index Definition**: A researcher has h-index = h if h papers have at least h citations each
2. **Bucket Sort Approach**: We can use counting sort with buckets to achieve O(n) time complexity
3. **Threshold Logic**: Papers with citations > n can all be counted in the last bucket (they all contribute to higher h-indices)
4. **Reverse Traversal**: By traversing buckets from high to low, we find the first index where count >= index

## Algorithm Steps

### Approach 1: Bucket Sort (Optimal)
```
1. Create a bucket array of size n+1
2. Count citations:
   - For citations < n: increment bucket[citations]
   - For citations >= n: increment bucket[n]
3. Traverse buckets from right to left:
   - Maintain cumulative count
   - When count >= index, return that index as h-index
```

### Approach 2: Binary Search (For Sorted Arrays)
```
1. If array is sorted, use binary search
2. For each mid position, check if citations[mid] >= (n - mid)
3. This gives us the number of papers with at least (n - mid) citations
```

## Complexity Analysis
### Bucket Sort Approach:
- **Time Complexity**: O(n)
  - O(n) to build buckets
  - O(n) to traverse buckets
- **Space Complexity**: O(n) for bucket array

### Binary Search Approach (sorted array):
- **Time Complexity**: O(log n)
- **Space Complexity**: O(1)

## Visual Representation

### Example: citations = [3, 0, 6, 1, 5]
```
Initial array:
Index:     0  1  2  3  4
Value:     3  0  6  1  5

Step 1: Create buckets (size = 6)
Bucket Index:  0  1  2  3  4  5
Count:         1  1  0  1  0  2

Explanation:
- 0 appears 1 time -> bucket[0] = 1
- 1 appears 1 time -> bucket[1] = 1
- 3 appears 1 time -> bucket[3] = 1
- 5 >= 5 -> bucket[5]++
- 6 >= 5 -> bucket[5]++

Step 2: Traverse from right to left
i=5: count = 2, is 2 >= 5? No
i=4: count = 2, is 2 >= 4? No
i=3: count = 3, is 3 >= 3? Yes! Return 3

Visual representation:
Papers sorted by citations: [0, 1, 3, 5, 6]
                             |     |
                             |-----|
                           3 papers with >= 3 citations
```

## Code Walkthrough

```java
public int hIndex(int[] citations) {
    // Create bucket array (size n+1 to handle all cases)
    int[] bucket = new int[citations.length + 1];

    // Step 1: Fill buckets with citation counts
    for (int i = 0; i < citations.length; i++) {
        if (citations[i] < citations.length) {
            bucket[citations[i]]++;
        } else {
            // All citations >= n go in last bucket
            bucket[citations.length]++;
        }
    }

    // Step 2: Traverse buckets from right to left
    int count = 0;
    for (int i = citations.length; i >= 0; i--) {
        count += bucket[i];
        // When we have 'i' papers with at least 'i' citations
        if (count >= i) {
            return i;
        }
    }

    return 0;
}
```

### Binary Search Approach (For Sorted Array)
```java
public int hIndexHelper(int[] citations, int start, int end) {
    if (start > end) {
        return citations.length - start;
    }

    int mid = (start + end) / 2;

    // citations[mid] represents the citation count
    // citations.length - mid represents papers with >= citations[mid]
    if (citations[mid] == citations.length - mid) {
        return citations.length - mid;
    }

    if (citations[mid] > citations.length - mid) {
        // Too many citations, search left
        return hIndexHelper(citations, start, mid - 1);
    }

    // Too few citations, search right
    return hIndexHelper(citations, mid + 1, end);
}
```

## Edge Cases
1. **Empty array**: Return 0
2. **All zeros**: H-index = 0
3. **All citations > n**: H-index = n
4. **Single paper with 0 citations**: H-index = 0
5. **Single paper with many citations**: H-index = 1
6. **All papers have same citations**:
   - If citations >= n: h-index = n
   - If citations < n: h-index = citations

## Related Problems
- **H-Index II**: Same problem but array is sorted (use binary search)
- **Top K Frequent Elements**: Similar bucket sort technique
- **Sort Colors**: Another counting/bucket sort problem

## Tags
- Array
- Sorting
- Bucket Sort
- Counting Sort
- Binary Search
- Medium
