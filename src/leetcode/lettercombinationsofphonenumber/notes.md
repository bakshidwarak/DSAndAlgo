# Letter Combinations of a Phone Number

## Problem Statement
**LeetCode Problem 17**: Letter Combinations of a Phone Number (Medium)

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

```
2: abc
3: def
4: ghi
5: jkl
6: mno
7: pqrs
8: tuv
9: wxyz
```

### Examples
**Example 1:**
```
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
```

**Example 2:**
```
Input: ""
Output: []
```

**Example 3:**
```
Input: "2"
Output: ["a", "b", "c"]
```

**Note**: Although the answer is in lexicographical order, your answer could be in any order you want.

## Key Insights
1. **Backtracking Problem**: Generate all combinations systematically
2. **Cartesian Product**: Combine sets of letters for each digit
3. **Recursive Structure**: Build combinations character by character
4. **Decision Tree**: At each digit, try all possible letters
5. **Early Termination**: Empty input returns empty result

## Algorithm Steps
```
1. Create digit-to-letters mapping (HashMap or array)
2. Handle edge case: empty input returns empty list
3. Use backtracking to generate combinations:
   a. Base case: if current index == digits.length, add combination
   b. Get letters for current digit
   c. For each letter:
      - Add letter to current combination
      - Recurse to next digit
      - Remove letter (backtrack)
4. Return all combinations
```

## Complexity Analysis
- **Time Complexity**: O(4^n * n)
  - n = number of digits
  - Each digit has at most 4 letters (7 and 9)
  - 4^n combinations to generate
  - Each combination takes O(n) to build
- **Space Complexity**: O(n)
  - Recursion depth = n
  - Current combination buffer = n
  - Output space not counted

## Visual Representation

### Example: digits = "23"
```
Decision Tree:
                    ""
                    |
                    2
            /       |       \
          a         b         c
          |         |         |
          3         3         3
     /   |   \  /   |   \  /   |   \
    d    e    f d   e    f d   e    f

Combinations: ad, ae, af, bd, be, bf, cd, ce, cf

Step-by-step generation:
1. Start with ""
2. Add 'a' -> "a", recurse
3. Add 'd' -> "ad", complete! Add to result
4. Backtrack to "a", try 'e' -> "ae", complete!
5. Backtrack to "a", try 'f' -> "af", complete!
6. Backtrack to "", try 'b' -> "b", recurse
7. Add 'd' -> "bd", complete!
... and so on
```

### Example: digits = "789"
```
Mapping:
7 -> pqrs (4 letters)
8 -> tuv  (3 letters)
9 -> wxyz (4 letters)

Total combinations: 4 * 3 * 4 = 48

First few combinations:
"7" -> p, q, r, s
"78" -> pt, pu, pv, qt, qu, qv, ...
"789" -> ptw, ptx, pty, ptz, puw, ...
```

## Code Walkthrough

### Current Implementation
```java
public class LetterCombinationsOfPhoneNumber {
    HashMap<Character, String> map = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        // Build digit-to-letters mapping
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        // Only recurse if input is non-empty
        if (digits.length() != 0)
            getLetters(digits, 0, map, result, current);

        return result;
    }

    public void getLetters(String digits, int index,
                          HashMap<Character, String> map,
                          List<String> result,
                          StringBuilder current) {

        // Base case: reached end of digits
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // Get current digit and its corresponding letters
        Character curr = digits.charAt(index);
        char[] letters = map.get(curr).toCharArray();

        // Try each letter for current digit
        for (char ch : letters) {
            current.append(ch);              // Choose
            getLetters(digits, index + 1, map, result, current);  // Explore
            current.deleteCharAt(current.length() - 1);  // Unchoose (backtrack)
        }
    }
}
```

### Alternative: Iterative Approach
```java
public List<String> letterCombinations(String digits) {
    if (digits.length() == 0) return new ArrayList<>();

    String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    List<String> result = new ArrayList<>();
    result.add("");  // Start with empty string

    // Build combinations iteratively
    for (char digit : digits.toCharArray()) {
        List<String> temp = new ArrayList<>();
        String letters = map[digit - '0'];

        // For each existing combination
        for (String combination : result) {
            // Try appending each letter
            for (char letter : letters.toCharArray()) {
                temp.add(combination + letter);
            }
        }

        result = temp;
    }

    return result;
}
```

### Backtracking Trace for "23"
```
Call Stack:
-----------
getLetters("23", 0, map, result, "")
  current = "", index = 0, curr = '2', letters = "abc"

  Loop iteration 1: ch = 'a'
    current.append('a') -> "a"
    getLetters("23", 1, map, result, "a")
      current = "a", index = 1, curr = '3', letters = "def"

      Loop iteration 1: ch = 'd'
        current.append('d') -> "ad"
        getLetters("23", 2, map, result, "ad")
          index == digits.length() -> add "ad" to result
        current.deleteCharAt() -> "a"

      Loop iteration 2: ch = 'e'
        current.append('e') -> "ae"
        getLetters("23", 2, map, result, "ae")
          index == digits.length() -> add "ae" to result
        current.deleteCharAt() -> "a"

      Loop iteration 3: ch = 'f'
        current.append('f') -> "af"
        getLetters("23", 2, map, result, "af")
          index == digits.length() -> add "af" to result
        current.deleteCharAt() -> "a"

    current.deleteCharAt() -> ""

  Loop iteration 2: ch = 'b'
    ... (similar process for 'b')

  Loop iteration 3: ch = 'c'
    ... (similar process for 'c')

Final result: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
```

## Edge Cases
1. **Empty string**: Return empty list
2. **Single digit**: Return list of corresponding letters
3. **Only 7s and 9s**: Maximum letters per digit (4)
4. **Invalid digits (0, 1)**: Problem states 2-9 only
5. **Very long input**: Exponential growth of combinations

### Edge Case Examples
```
Input: ""
Output: []

Input: "2"
Output: ["a", "b", "c"]

Input: "7"
Output: ["p", "q", "r", "s"]

Input: "79"
Output: ["pw", "px", "py", "pz", "qw", "qx", "qy", "qz",
         "rw", "rx", "ry", "rz", "sw", "sx", "sy", "sz"]
Total: 4 * 4 = 16 combinations
```

## Optimization Notes
1. **StringBuilder vs String**: StringBuilder is mutable, more efficient
2. **Array vs HashMap**: Array index by ('digit' - '0') is faster
3. **Iterative vs Recursive**: Iterative saves stack space
4. **Pre-allocate Result List**: If you know size (product of letter counts)

## Pattern Recognition
This problem demonstrates the **Backtracking** pattern:
1. **Choose**: Add a letter to current combination
2. **Explore**: Recurse to next digit
3. **Unchoose**: Remove the letter (backtrack)

Similar pattern appears in:
- Subsets
- Permutations
- Combinations
- N-Queens

## Related Problems
- **Generate Parentheses (LeetCode 22)**: Similar backtracking
- **Combination Sum (LeetCode 39)**: Backtracking with numbers
- **Permutations (LeetCode 46)**: Generate all permutations
- **Subsets (LeetCode 78)**: Generate all subsets
- **Word Search (LeetCode 79)**: Backtracking on grid
- **Palindrome Partitioning (LeetCode 131)**: Backtracking with strings

## Tags
- String
- Backtracking
- Recursion
- Depth-First Search
- Combinations
- Medium
- Interview Classic
- Amazon
- Google
- Microsoft
