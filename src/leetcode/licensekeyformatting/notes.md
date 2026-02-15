# License Key Formatting

## Problem Statement
**LeetCode Problem 482**: License Key Formatting (Easy)

You are given a license key represented as a string S which consists only of alphanumeric characters and dashes. The string is separated into N+1 groups by N dashes.

Given a number K, we would want to reformat the strings such that each group contains exactly K characters, except for the first group which could be shorter than K, but still must contain at least one character. Furthermore, there must be a dash inserted between two groups and all lowercase letters should be converted to uppercase.

Given a non-empty string S and a number K, format the string according to the rules described above.

### Examples
**Example 1:**
```
Input: S = "5F3Z-2e-9-w", K = 4
Output: "5F3Z-2E9W"
Explanation: The string S has been split into two parts, each part has 4 characters.
Note that the two extra dashes are not needed and can be removed.
```

**Example 2:**
```
Input: S = "2-5g-3-J", K = 2
Output: "2-5G-3J"
Explanation: The string S has been split into three parts, each part has 2 characters
except the first part as it could be shorter as mentioned above.
```

**Note**:
- The length of string S will not exceed 12,000
- K is a positive integer
- String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-)
- String S is non-empty

## Key Insights
1. **Right-to-Left Processing**: Groups of K from the end, first group may be shorter
2. **Remove Old Dashes**: Strip all existing dashes first
3. **Convert to Uppercase**: All letters must be uppercase
4. **Reverse Building**: Build result backwards, then reverse
5. **First Group Exception**: May contain 1 to K characters

## Algorithm Steps
```
1. Remove all dashes and convert to uppercase
2. Build result from right to left:
   a. Start from end of string
   b. Add K characters to current group
   c. Add dash after each group (except after last char)
   d. Continue until all characters processed
3. Reverse the result string
4. Return formatted string
```

## Complexity Analysis
- **Time Complexity**: O(n)
  - O(n) to remove dashes and uppercase
  - O(n) to build result
  - O(n) to reverse
  - Overall: O(n)
- **Space Complexity**: O(n)
  - StringBuilder for result
  - String without dashes

## Visual Representation

### Example 1: S = "5F3Z-2e-9-w", K = 4
```
Step 1: Remove dashes and uppercase
Original: "5F3Z-2e-9-w"
Cleaned:  "5F3Z2E9W"
Length: 8

Step 2: Build from right to left
Index:    0 1 2 3 4 5 6 7
Cleaned:  5 F 3 Z 2 E 9 W
                          ^
Start at end, k = 4

i=7: k=3, append 'W', sb = "W"
i=6: k=2, append '9', sb = "W9"
i=5: k=1, append 'E', sb = "W9E"
i=4: k=0, append '2', sb = "W9E2"
     k becomes 0 and i > 0, append '-', reset k=4
     sb = "W9E2-"

i=3: k=3, append 'Z', sb = "W9E2-Z"
i=2: k=2, append '3', sb = "W9E2-Z3"
i=1: k=1, append 'F', sb = "W9E2-Z3F"
i=0: k=0, append '5', sb = "W9E2-Z3F5"

Step 3: Reverse
Result: "5F3Z-2E9W"

Visualization:
Original:  5F3Z-2e-9-w
Cleaned:   5F3Z2E9W
Groups:    5F3Z 2E9W  (4 chars each)
Result:    5F3Z-2E9W
```

### Example 2: S = "2-5g-3-J", K = 2
```
Step 1: Remove dashes and uppercase
Original: "2-5g-3-J"
Cleaned:  "25G3J"
Length: 5

Step 2: Group from right (K=2)
Index:    0 1 2 3 4
Cleaned:  2 5 G 3 J
                  ^

Build backwards:
Groups: [3J] [5G] [2]
        K=2  K=2  K=1 (first group shorter)

Result: "2-5G-3J"

Note: First group "2" has only 1 char (5 % 2 = 1)
```

## Code Walkthrough

### Current Implementation
```java
public String licenseKeyFormatting(String S, int K) {
    // Step 1: Remove dashes and convert to uppercase
    String copy = S.toUpperCase().replaceAll("-", "");

    StringBuilder sb = new StringBuilder();

    // Step 2: Build result from right to left
    for (int k = K, i = copy.length() - 1; i >= 0; i--) {
        k--;  // Decrement counter

        // Add current character
        sb.append(copy.charAt(i));

        // If group is complete and not at start, add dash
        if (k == 0 && i > 0) {
            k = K;       // Reset counter
            sb.append('-');
        }
    }

    // Step 3: Reverse to get correct order
    return sb.reverse().toString();
}
```

### Alternative: Left-to-Right with First Group Calculation
```java
public String licenseKeyFormatting(String S, int K) {
    String clean = S.toUpperCase().replaceAll("-", "");
    if (clean.length() == 0) return "";

    StringBuilder sb = new StringBuilder();
    int firstGroupSize = clean.length() % K;

    // Handle first group (may be shorter)
    if (firstGroupSize > 0) {
        sb.append(clean.substring(0, firstGroupSize));
    }

    // Process remaining groups
    for (int i = firstGroupSize; i < clean.length(); i += K) {
        if (sb.length() > 0) {
            sb.append('-');
        }
        sb.append(clean.substring(i, i + K));
    }

    return sb.toString();
}
```

### Step-by-Step Trace for "5F3Z-2e-9-w", K=4
```
Initial:
copy = "5F3Z2E9W"
sb = ""
k = 4

Iteration 1: i=7, ch='W'
k = 3, sb = "W"

Iteration 2: i=6, ch='9'
k = 2, sb = "W9"

Iteration 3: i=5, ch='E'
k = 1, sb = "W9E"

Iteration 4: i=4, ch='2'
k = 0, sb = "W9E2"
k == 0 && i > 0, append '-'
sb = "W9E2-", k = 4

Iteration 5: i=3, ch='Z'
k = 3, sb = "W9E2-Z"

Iteration 6: i=2, ch='3'
k = 2, sb = "W9E2-Z3"

Iteration 7: i=1, ch='F'
k = 1, sb = "W9E2-Z3F"

Iteration 8: i=0, ch='5'
k = 0, sb = "W9E2-Z3F5"
i == 0, don't append dash

Reverse: "5F3Z-2E9W"
```

## Edge Cases
1. **All dashes**: Return empty after cleaning
2. **Length divisible by K**: All groups have exactly K chars
3. **Length < K**: Single group, no dashes
4. **K = 1**: Every character separated by dash
5. **Single character**: Return as-is (uppercase)
6. **No dashes in input**: Just reformat

### Edge Case Examples
```
S = "----", K = 3
Output: "" (all dashes removed, empty string)

S = "abcd", K = 2
Output: "AB-CD" (4 % 2 = 0, two equal groups)

S = "a-b-c", K = 5
Output: "ABC" (length 3 < K, single group)

S = "abc-def", K = 1
Output: "A-B-C-D-E-F" (every char separated)

S = "a", K = 1
Output: "A"

S = "2-4A0r7-4k", K = 4
Output: "24A0-R74K"
Length: 8, groups: [24A0] [R74K]

S = "2-4A0r7-4k", K = 3
Output: "24-A0R-74K"
Length: 8, first group: 8 % 3 = 2 chars
Groups: [24] [A0R] [74K]
```

## Common Mistakes
1. **Processing left-to-right**: Harder to handle first group
2. **Forgetting uppercase conversion**: Must convert all letters
3. **Not handling empty result**: After removing dashes
4. **Adding dash at end**: Check i > 0 before adding dash
5. **Not resetting k**: Must reset to K after each group

## Related Problems
- **Add Binary (LeetCode 67)**: String manipulation from right to left
- **Multiply Strings (LeetCode 43)**: String building
- **Valid Palindrome II (LeetCode 680)**: String transformation
- **Remove All Adjacent Duplicates in String (LeetCode 1047)**: String cleaning

## Tags
- String
- String Manipulation
- StringBuilder
- Easy
- Implementation
- Google Interview
