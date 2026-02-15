# Jewels and Stones

## Problem Statement
**LeetCode Problem 771**: Jewels and Stones (Easy)

You're given strings J representing the types of stones that are jewels, and S representing the stones you have. Each character in S is a type of stone you have. You want to know how many of the stones you have are also jewels.

The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".

### Examples
**Example 1:**
```
Input: J = "aA", S = "aAAbbbb"
Output: 3
Explanation: We have 3 jewels: 'a', 'A', 'A'
```

**Example 2:**
```
Input: J = "z", S = "ZZ"
Output: 0
Explanation: 'z' and 'Z' are different (case sensitive)
```

**Note**:
- S and J will consist of letters and have length at most 50
- The characters in J are distinct

## Key Insights
1. **Set Membership**: Check if each stone in S is a jewel (exists in J)
2. **Case Sensitive**: 'a' and 'A' are different jewel types
3. **HashSet Optimal**: Use HashSet for O(1) lookup
4. **Boolean Array Alternative**: Can use array for ASCII characters (more space efficient)
5. **Count Occurrences**: Each occurrence in S counts separately

## Algorithm Steps
```
1. Create a boolean array or HashSet to store jewel types
2. Iterate through J:
   - Mark each character as a jewel type
3. Iterate through S:
   - Check if current stone is a jewel
   - If yes, increment count
4. Return count
```

## Complexity Analysis

### Boolean Array Approach:
- **Time Complexity**: O(J + S)
  - O(J) to mark jewel types
  - O(S) to count jewels in stones
- **Space Complexity**: O(1)
  - Fixed array size (58 for 'A'-'z')
  - Independent of input size

### HashSet Approach:
- **Time Complexity**: O(J + S)
  - O(J) to build HashSet
  - O(S) to check each stone
- **Space Complexity**: O(J)
  - Store J distinct characters

## Visual Representation

### Example: J = "aA", S = "aAAbbbb"
```
Step 1: Mark jewel types
Array indices (relative to 'A'):
  A B C ... Z a b c ... z
  0 1 2 ... 25 32 33 34 ... 57

jewels['A' - 'A'] = jewels[0] = true
jewels['a' - 'A'] = jewels[32] = true

Jewels array:
Index:  0    1-31  32   33-57
Value:  true false true false
Char:   'A'        'a'

Step 2: Count jewels in stones
S = "aAAbbbb"
     ^
'a' - 'A' = 32, jewels[32] = true, count = 1

S = "aAAbbbb"
      ^
'A' - 'A' = 0, jewels[0] = true, count = 2

S = "aAAbbbb"
       ^
'A' - 'A' = 0, jewels[0] = true, count = 3

S = "aAAbbbb"
        ^
'b' - 'A' = 33, jewels[33] = false, count = 3

... (remaining 'b's are not jewels)

Final count: 3
```

### Example: J = "z", S = "ZZ"
```
Step 1: Mark jewels
jewels['z' - 'A'] = jewels[57] = true

Step 2: Check stones
S = "ZZ"
     ^
'Z' - 'A' = 25, jewels[25] = false, count = 0

S = "ZZ"
      ^
'Z' - 'A' = 25, jewels[25] = false, count = 0

Final count: 0

Note: 'z' and 'Z' map to different indices (57 vs 25)
```

## Code Walkthrough

### Boolean Array Approach (Current Implementation)
```java
public int numJewelsInStones(String J, String S) {
    // Array to store jewel types
    // Size 58 covers 'A'-'Z' (26) + 'a'-'z' (26) with 6 chars between
    boolean jewels[] = new boolean[58];
    int count = 0;

    // Handle null inputs
    if (J == null || S == null)
        return count;

    // Step 1: Mark all jewel types
    for (char ch : J.toCharArray()) {
        jewels[ch - 'A'] = true;  // Convert to array index
    }

    // Step 2: Count jewels in stones
    for (char ch : S.toCharArray()) {
        if (jewels[ch - 'A'])     // Check if this stone is a jewel
            count++;
    }

    return count;
}
```

### HashSet Approach (Alternative)
```java
public int numJewelsInStones(String J, String S) {
    if (J == null || S == null)
        return 0;

    // Build set of jewel types
    Set<Character> jewelSet = new HashSet<>();
    for (char jewel : J.toCharArray()) {
        jewelSet.add(jewel);
    }

    // Count jewels in stones
    int count = 0;
    for (char stone : S.toCharArray()) {
        if (jewelSet.contains(stone)) {
            count++;
        }
    }

    return count;
}
```

### One-Liner (Java 8 Streams)
```java
public int numJewelsInStones(String J, String S) {
    Set<Character> jewels = J.chars()
                             .mapToObj(c -> (char)c)
                             .collect(Collectors.toSet());

    return (int) S.chars()
                  .filter(c -> jewels.contains((char)c))
                  .count();
}
```

## Edge Cases
1. **Empty J**: No jewels, return 0
2. **Empty S**: No stones, return 0
3. **No match**: All stones are not jewels, return 0
4. **All match**: All stones are jewels, return S.length()
5. **Case sensitivity**: "aA" and "aa" give different results
6. **Null inputs**: Handle gracefully

### Edge Case Examples
```
J = "", S = "abc"
Output: 0

J = "abc", S = ""
Output: 0

J = "a", S = "bbb"
Output: 0

J = "abc", S = "aabbcc"
Output: 6

J = "a", S = "AAA"
Output: 0 (case sensitive!)

J = "aA", S = "aAaA"
Output: 4
```

## ASCII Table Reference
```
Character Range Mapping:
'A' = 65 -> index 0
'B' = 66 -> index 1
...
'Z' = 90 -> index 25
'[' = 91 -> index 26
...
'_' = 95 -> index 30
'`' = 96 -> index 31
'a' = 97 -> index 32
'b' = 98 -> index 33
...
'z' = 122 -> index 57

Array size 58 covers 'A'(65) to 'z'(122)
Offset by 'A' (65) to start at index 0
```

## Optimization Notes
1. **Array vs HashSet**:
   - Array: Faster for small character sets (O(1) access, cache-friendly)
   - HashSet: Better for large/unknown character sets
2. **Early Termination**: Not applicable (must check all stones)
3. **Space Trade-off**: Array uses fixed space, HashSet uses only needed space

## Related Problems
- **Unique Morse Code Words (LeetCode 804)**: Similar character mapping
- **Number of Good Pairs (LeetCode 1512)**: Count matching elements
- **Count Items Matching a Rule (LeetCode 1773)**: Similar filtering
- **Check If String Is a Prefix of Array (LeetCode 1961)**: String matching
- **Intersection of Two Arrays (LeetCode 349)**: Similar set membership problem

## Tags
- String
- Hash Table
- HashSet
- Array
- Easy
- Simple
- Interview Warm-up
