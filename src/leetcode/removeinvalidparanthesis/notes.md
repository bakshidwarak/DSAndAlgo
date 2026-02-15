# Remove Invalid Parentheses - LeetCode Problem 301

## Problem Statement
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Constraints:
- Input string may contain letters other than parentheses
- Return all unique valid strings
- Minimize number of characters removed

## Examples

**Example 1:**
- Input: `"()())()"`
- Output: `["()()()", "(())()"]`

**Example 2:**
- Input: `"(a)())()"`
- Output: `["(a)()()", "(a())()"]`

**Example 3:**
- Input: `")("`
- Output: `[""]`

**Example 4:**
- Input: `""`
- Output: `[""]`

## Key Insights
1. **All Combinations**: Generate all possible strings by removing characters
2. **Validation**: Check if remaining string is valid
3. **Minimum Removals**: Use BFS/TreeMap to find minimum removals first
4. **Memoization**: Track visited strings to avoid duplicates
5. **Recursive Backtracking**: Mark positions and recursively try removing

## Algorithm Steps

### Approach: DFS with Backtracking

**Step 1: Recursive Exploration**
- For each position, try removing character (if parenthesis)
- Try keeping character
- Mark position as removed using space character

**Step 2: Validation**
- Check if remaining string (without spaces) is valid
- Valid means: balanced parentheses, no extra closing before opening

**Step 3: Tracking Minimum**
- Use TreeMap to group results by number of removals
- Return first group (minimum removals)

**Pseudocode:**
```
function removeInvalidParentheses(s):
    result = TreeMap()  // Groups by removal count
    backtrack(s, 0, result, 0)
    return result.firstEntry().getValue()

function backtrack(str, start, result, removed):
    if start == str.length:
        if isValid(str):
            content = str.replaceAll(" ", "")
            if result[removed] doesn't exist:
                result[removed] = new Set()
            result[removed].add(content)
        return

    ch = str[start]

    // Try removing current character (if parenthesis)
    if ch == '(' or ch == ')':
        str[start] = ' '
        backtrack(str, start+1, result, removed+1)
        str[start] = ch

    // Try keeping current character
    backtrack(str, start+1, result, removed)

function isValid(str):
    count = 0
    for ch in str:
        if ch == '(':
            count++
        else if ch == ')':
            if count == 0:
                return false
            count--
    return count == 0
```

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time Complexity | O(2^n) where n = string length |
| Space Complexity | O(n) for recursion depth and result storage |

**Analysis:**
- For each character (parenthesis), 2 choices: remove or keep
- Maximum 2^n states explored
- Pruning helps in practice

## ASCII Visualization

```
Example: "()())()" (length=7)

Recursion Tree (simplified - showing key branches):
                    "()())()"
                    /        \
            remove ( keep (
           /             \
      ")())()"        "()()")" → "(())()" ✓
      /    |              / \
    ...   ...          remove ) keep )
                          /        \
                    "()()()" ✓  "()())())"
                              /    \
                          ...

Decision Tree for Positions:
Pos 0 ('('): keep
Pos 1 (')'): keep
Pos 2 ('('): keep
Pos 3 (')'): keep
Pos 4 (')'): REMOVE (can't have extra ')')
Pos 5 ('('): keep
Pos 6 (')'): keep

Result: "()()()" ✓

Validity Check:
String: "()())()"
Process:
'(' → count=1
')' → count=0
'(' → count=1
')' → count=0
')' → count<0 (INVALID!) Must remove this

After removing position 4:
String: "()()()"
'(' → count=1
')' → count=0
'(' → count=1
')' → count=0
'(' → count=1
')' → count=0
Result: count=0 ✓ VALID

Removal Grouping:
Removals:0 → ["()()()"]
Removals:1 → ["(())()", "()()()", ...]
...
Return: first group (min removals)
```

## Code Walkthrough

```java
public List<String> removeInvalidParentheses(String s) {
    List<String> result = new ArrayList<>();
    char[] input = s.toCharArray();
    TreeMap<Integer, Set<String>> resultSet = new TreeMap<>();

    // Backtrack to find all valid combinations
    lcr(input, 0, resultSet, 0);

    // Return results with minimum removals
    Set<String> outcome = resultSet.firstEntry().getValue();
    return new ArrayList<>(outcome);
}

// Recursive backtracking function
public void lcr(char[] input, int start, TreeMap<Integer, Set<String>> result, int removed) {
    // Base case: reached end of string
    if (start == input.length) {
        // Check if valid
        if (isValid(input)) {
            // Convert to string, removing spaces (removed chars)
            String current = new String(input);
            current = current.replaceAll("\\s", "");

            // Add to appropriate group
            if (result.containsKey(removed)) {
                result.get(removed).add(current);
            } else {
                Set<String> newList = new HashSet<>();
                newList.add(current);
                result.put(removed, newList);
            }
        }
        return;
    }

    // If current character is parenthesis, try removing it
    if (input[start] == '(' || input[start] == ')') {
        char temp = input[start];
        input[start] = ' ';  // Mark as removed
        lcr(input, start + 1, result, removed + 1);
        input[start] = temp;  // Restore
    }

    // Try keeping current character
    lcr(input, start + 1, result, removed);
}

// Validation function
public boolean isValid(char[] input) {
    int count = 0;

    for (int i = 0; i < input.length; i++) {
        if (input[i] == '(') {
            count++;
        }

        if (input[i] == ')') {
            if (count == 0)
                return false;  // More ')' than '('
            count--;
        }
    }

    return count == 0;  // All '(' must be matched
}
```

## Edge Cases

1. **Empty String**: `""` -> `[""]`
2. **Valid String**: `"()"` -> `["()"]`
3. **All Open**: `"((("` -> `[""]`
4. **All Close**: `")))"` -> `[""]`
5. **Mixed**: `"()()()"` -> `["()()()"]`
6. **With Letters**: `"(a)())()"` -> `["(a)()()", "(a())()"]`
7. **Single Paren**: `"("` -> `[""]` or `")"` -> `[""]`
8. **Long String**: Optimize with pruning

## Related Problems

1. **LeetCode 20**: Valid Parentheses - Check validity
2. **LeetCode 32**: Longest Valid Parentheses - Find longest
3. **LeetCode 22**: Generate Parentheses - Generate valid combinations
4. **LeetCode 1541**: Minimum Insertions to Balance - Modify string
5. **LeetCode 1249**: Minimum Remove to Make Valid Parentheses
## Tags

- Backtracking
- String
- BFS/DFS
- All Combinations
- Set/TreeMap
- Hard Difficulty
- Acceptance: ~40%
