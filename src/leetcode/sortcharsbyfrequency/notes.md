# 451. Sort Characters By Frequency

## Problem Statement
Given a string, sort it in decreasing order based on the frequency of characters.

The output frequency order doesn't have to follow the order of the first appearance. Any valid answer is acceptable.

## Examples

### Example 1
```
Input: "tree"
Output: "eert"
Explanation:
  'e' appears 2 times
  't' appears 1 time
  'r' appears 1 time
  So output has 'e' twice, then 't', then 'r' (any order for t and r)
```

### Example 2
```
Input: "cccaaa"
Output: "cccaaa"
Explanation: Both 'c' and 'a' appear 3 times, so either cccaaa or aaaccc is valid
```

### Example 3
```
Input: "Aabb"
Output: "bbAa"
Explanation:
  'b' appears 2 times
  'A' appears 1 time
  'a' appears 1 time
  (Note: 'A' and 'a' are different characters)
```

## Key Insights

1. **Frequency counting**: Count occurrence of each character
2. **Bucket/array indexing**: Use array index as frequency
3. **Iterate from max frequency down**: Build result from highest to lowest
4. **ASCII value as index**: Use character ASCII for array lookup

## Algorithm Steps

1. Count frequency of each character using array of size 128 (ASCII)
2. Find maximum frequency
3. Iterate from max frequency down to 1:
   - For each frequency level, iterate through all characters
   - If character has this frequency, append it that many times to result
4. Return result string

## Complexity Analysis

**Time Complexity:** O(n + max_freq * alphabet_size)
- Counting: O(n)
- Building result: O(max_freq * 128) = O(n) in most cases
- Overall: O(n)

**Space Complexity:** O(1)
- Fixed size frequency array (128 for ASCII)
- max_freq at most n

## ASCII Visualization

```
Input: "tree"

Step 1: Count frequencies
Index: 0   ...  101('e')  114('r')  116('t')  ...
Count: 0   ...  2         1         1         ...

max_freq = 2

Step 2: Build from high frequency to low
For j = 2 down to 1:
  j=2:
    Check all indices
    Index 101 ('e') has freq=2? YES
    Append "ee" to result
  j=1:
    Index 114 ('r') has freq=1? YES -> append "r"
    Index 116 ('t') has freq=1? YES -> append "t"

Result: "eert"
```

## Code Walkthrough

```java
public String frequencySort(String s) {
    // Step 1: Count character frequencies
    // Array of size 128 covers all ASCII characters
    int[] freq = new int[128];
    int max = 0;

    for (int i = 0; i < s.length(); i++) {
        // Get ASCII value of character
        int charIndex = s.charAt(i);
        freq[charIndex]++;
        // Track maximum frequency
        max = Math.max(max, freq[charIndex]);
    }

    // Step 2: Build result string from highest to lowest frequency
    StringBuilder sb = new StringBuilder();

    // Iterate from maximum frequency down to 1
    for (int j = max; j > 0; j--) {
        // For each frequency, check all characters
        for (int t = 0; t < freq.length; t++) {
            // If this character has current frequency
            if (freq[t] == j) {
                // Append it freq[t] times (which equals j)
                for (int m = 0; m < j; m++) {
                    sb.append((char) t);
                }
            }
        }
    }

    return sb.toString();
}
```

## Detailed Walkthrough

```
Input: "cccaaa"

Step 1: Count frequencies
freq[97]('a') = 3
freq[99]('c') = 3
max = 3

Step 2: Build result
j=3:
  t=0 to 127:
    t=97: freq[97]==3? YES
      Append 'a' 3 times: "aaa"
    t=99: freq[99]==3? YES
      Append 'c' 3 times: "aaaccc"
j=2: No character has freq 2, skip
j=1: No character has freq 1, skip

Result: "aaaccc"
```

## How ASCII Indexing Works

```
Character to ASCII:
'a' = 97
'A' = 65
'b' = 98
'B' = 66
'0' = 48
'z' = 122
'Z' = 90

So freq[97] stores count of 'a'
freq[65] stores count of 'A'

'a' and 'A' have different indices, treated as different characters
```

## Edge Cases

1. **Single character**: "a" -> "a"
2. **All same character**: "aaaa" -> "aaaa"
3. **All different with same frequency**: "abc" -> any order is valid
4. **Case sensitive**: "Aa" -> "aA" or "Aa" (both valid)
5. **Special characters**: "a!b!" -> "!!ab"
6. **Numbers and letters**: "a1b1" -> "11ab"

## Alternative Approach: Using PriorityQueue

```java
public String frequencySortPQ(String s) {
    // Count frequencies
    Map<Character, Integer> map = new HashMap<>();
    for (char c : s.toCharArray()) {
        map.put(c, map.getOrDefault(c, 0) + 1);
    }

    // Use max heap (priority queue) to sort by frequency
    PriorityQueue<Character> pq = new PriorityQueue<>(
        (a, b) -> map.get(b) - map.get(a)  // Descending order
    );

    for (char c : map.keySet()) {
        pq.offer(c);
    }

    // Build result
    StringBuilder sb = new StringBuilder();
    while (!pq.isEmpty()) {
        char c = pq.poll();
        int freq = map.get(c);
        for (int i = 0; i < freq; i++) {
            sb.append(c);
        }
    }

    return sb.toString();
}
// Time: O(n log k) where k = unique characters
// Space: O(k)
```

## Comparison of Approaches

| Approach | Time | Space | Notes |
|----------|------|-------|-------|
| Frequency Array | O(n) | O(1) | Best for fixed charset |
| PriorityQueue | O(n log k) | O(k) | Good for large alphabets |
| Sorting | O(n log n) | O(n) | Simpler but slower |

## Why Frequency Array is Better

1. **O(n) guaranteed time**: No sorting overhead
2. **Fixed space**: ASCII size is constant
3. **Simple iteration**: Direct array lookup
4. **Cache friendly**: Array access is fast

## Related Problems

- 347: Top K Frequent Elements
- 692: Top K Frequent Words
- 1636: Sort Array by Increasing Frequency
- 49: Group Anagrams
## Tags

`medium` `hash-map` `string` `frequency-counting` `array` `sorting`
