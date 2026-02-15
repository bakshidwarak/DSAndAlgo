# Isomorphic Strings

## Problem Statement
**LeetCode Problem 205**: Isomorphic Strings (Easy)

Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t. All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

### Examples
**Example 1:**
```
Input: s = "egg", t = "add"
Output: true
Explanation: 'e' -> 'a', 'g' -> 'd'
```

**Example 2:**
```
Input: s = "foo", t = "bar"
Output: false
Explanation: 'o' cannot map to both 'a' and 'r'
```

**Example 3:**
```
Input: s = "paper", t = "title"
Output: true
Explanation: 'p' -> 't', 'a' -> 'i', 'e' -> 'l', 'r' -> 'e'
```

**Example 4:**
```
Input: s = "ab", t = "aa"
Output: false
Explanation: Two different characters cannot map to the same character
```

**Note**: You may assume both s and t have the same length.

## Key Insights
1. **Bijection Required**: One-to-one mapping between characters
2. **Two-Way Mapping**: Need to check both s->t and t->s mappings
3. **Character Array Optimization**: Use arrays instead of HashMap for ASCII characters
4. **Consistency Check**: Once a mapping is established, it must remain consistent
5. **No Collision**: No two different characters can map to the same character

## Algorithm Steps
```
1. Create two mapping arrays (size 256 for ASCII):
   - sTot: maps characters from s to t
   - tTos: maps characters from t to s
2. Iterate through both strings simultaneously:
   a. Check if s[i] already has a mapping:
      - If yes, verify it matches current t[i]
   b. Check if t[i] already has a mapping:
      - If yes, verify it matches current s[i]
   c. If any mismatch found, return false
   d. Establish mappings: s[i] -> t[i] and t[i] -> s[i]
3. If all characters pass, return true
```

## Complexity Analysis
- **Time Complexity**: O(n)
  - Single pass through strings
  - n = length of strings
- **Space Complexity**: O(1)
  - Two fixed-size arrays (256 each)
  - Independent of input size

## Visual Representation

### Example 1: s = "egg", t = "add"
```
Position:  0   1   2
s:         e   g   g
t:         a   d   d

Iteration 0: s[0]='e', t[0]='a'
  sTot[a] = 0 (not mapped), tTos[e] = 0 (not mapped)
  Establish: sTot[a] = 'e', tTos[e] = 'a'

Iteration 1: s[1]='g', t[1]='d'
  sTot[d] = 0 (not mapped), tTos[g] = 0 (not mapped)
  Establish: sTot[d] = 'g', tTos[g] = 'd'

Iteration 2: s[2]='g', t[2]='d'
  sTot[d] = 'g' (matches current s[2]='g') ✓
  tTos[g] = 'd' (matches current t[2]='d') ✓
  Consistent mapping!

Result: true

Mapping Table:
s -> t     t -> s
e -> a     a -> e
g -> d     d -> g
```

### Example 2: s = "foo", t = "bar"
```
Position:  0   1   2
s:         f   o   o
t:         b   a   r

Iteration 0: s[0]='f', t[0]='b'
  Establish: sTot[b] = 'f', tTos[f] = 'b'

Iteration 1: s[1]='o', t[1]='a'
  Establish: sTot[a] = 'o', tTos[o] = 'a'

Iteration 2: s[2]='o', t[2]='r'
  sTot[r] = 0 (not mapped yet)
  tTos[o] = 'a' (but current t[2]='r') ✗
  Conflict! 'o' is already mapped to 'a', cannot map to 'r'

Result: false
```

### Example 3: s = "ab", t = "aa"
```
Position:  0   1
s:         a   b
t:         a   a

Iteration 0: s[0]='a', t[0]='a'
  Establish: sTot[a] = 'a', tTos[a] = 'a'

Iteration 1: s[1]='b', t[1]='a'
  sTot[a] = 'a' (but current s[1]='b') ✗
  Conflict! 'a' in t is already mapped to 'a' in s, cannot map to 'b'

Result: false

This shows the importance of bidirectional checking!
```

## Code Walkthrough

```java
private static boolean isIsomorphic(String s, String t) {
    // Create two mapping arrays (256 for extended ASCII)
    int[] sTot = new int[256];  // Maps t[i] -> s[i]
    Arrays.fill(sTot, 0);

    int[] tToS = new int[256];  // Maps s[i] -> t[i]
    Arrays.fill(tToS, 0);

    char[] first = s.toCharArray();
    char[] second = t.toCharArray();

    for (int i = 0; i < s.length(); i++) {
        // Check if t[i] is already mapped to a different character in s
        if (sTot[second[i]] != 0 && sTot[second[i]] != first[i])
            return false;

        // Check if s[i] is already mapped to a different character in t
        if (tToS[first[i]] != 0 && tToS[first[i]] != second[i])
            return false;

        // Establish bidirectional mapping
        sTot[second[i]] = first[i];
        tToS[first[i]] = second[i];
    }

    return true;
}
```

### HashMap Alternative (More Readable)
```java
public boolean isIsomorphic(String s, String t) {
    Map<Character, Character> sToT = new HashMap<>();
    Map<Character, Character> tToS = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
        char c1 = s.charAt(i);
        char c2 = t.charAt(i);

        // Check s -> t mapping
        if (sToT.containsKey(c1)) {
            if (sToT.get(c1) != c2) {
                return false;
            }
        } else {
            sToT.put(c1, c2);
        }

        // Check t -> s mapping
        if (tToS.containsKey(c2)) {
            if (tToS.get(c2) != c1) {
                return false;
            }
        } else {
            tToS.put(c2, c1);
        }
    }

    return true;
}
```

## Edge Cases
1. **Empty strings**: Return true
2. **Single character**: Always true
3. **All same characters**: Must map to all same characters
4. **Self-mapping**: "abc" and "abc" -> true
5. **Reverse pattern**: "ab" and "ba" -> true
6. **Length mismatch**: Given as precondition (same length)

### Edge Case Examples
```
s = "", t = ""
Output: true

s = "a", t = "a"
Output: true

s = "aaa", t = "bbb"
Output: true (a -> b consistently)

s = "abc", t = "abc"
Output: true (self-mapping allowed)

s = "ab", t = "ca"
Output: true (a -> c, b -> a)

s = "abba", t = "abab"
Output: false (pattern mismatch)
```

## Common Mistakes
1. **One-Way Mapping Only**: Forgetting to check reverse mapping
   - "ab" -> "aa" would pass with only s->t check
2. **Using 0 as Valid Mapping**: Need to distinguish unmapped vs mapped to '\0'
3. **Not Handling Case Sensitivity**: 'A' and 'a' are different characters

## Related Problems
- **Word Pattern (LeetCode 290)**: Similar mapping between words and pattern
- **Word Pattern II (LeetCode 291)**: Backtracking version
- **Find and Replace Pattern (LeetCode 890)**: Apply isomorphism to find words
- **Isomorphic Strings II**: With case insensitivity
- **Group Isomorphic Strings**: Group strings by isomorphism class

## Tags
- String
- Hash Table
- Character Mapping
- Bijection
- Array
- Easy
- Interview Favorite
