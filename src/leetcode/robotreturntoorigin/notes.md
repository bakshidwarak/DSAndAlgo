# 657. Robot Return to Origin

## Problem Statement
There is a robot starting at position (0, 0) on a 2D plane. Given a sequence of moves as a string, determine if the robot ends up at the origin (0, 0) after completing all moves.

Valid moves are:
- 'R': move right (+1 on X-axis)
- 'L': move left (-1 on X-axis)
- 'U': move up (+1 on Y-axis)
- 'D': move down (-1 on Y-axis)

## Examples

### Example 1
```
Input: "UD"
Output: true
Explanation: Move up, then down = back to origin
```

### Example 2
```
Input: "LL"
Output: false
Explanation: Move left twice = position (-2, 0)
```

### Example 3
```
Input: "UDLR"
Output: true
Explanation: All moves cancel each other out
```

## Key Insights

1. **Cancellation principle**: R and L cancel out; U and D cancel out
2. **Count-based approach**: Only need to count occurrences of each move
3. **No coordinate tracking needed**: Don't need to actually track position
4. **Simple equality check**: Return true if count['U'] == count['D'] and count['L'] == count['R']

## Algorithm Steps

1. Create an array to count move frequencies (size 64 for ASCII)
2. Iterate through each character in the moves string
3. Increment count at index (character - 'A')
4. Check if:
   - count['D' - 'A'] == count['U' - 'A'] (vertical moves balanced)
   - count['L' - 'A'] == count['R' - 'A'] (horizontal moves balanced)
5. Return true only if both conditions are true

## Complexity Analysis

**Time Complexity:** O(n)
- Single pass through the moves string
- n = length of moves string

**Space Complexity:** O(1)
- Fixed size array of 64 elements (constant space)
- Space doesn't grow with input size

## ASCII Visualization

```
Start: (0, 0)

Move sequence: "UDLR"

U -> (0, 1)
D -> (0, 0)
L -> (-1, 0)
R -> (0, 0)

Final position: (0, 0) - Back at origin!

Character counts:
U: 1, D: 1, L: 1, R: 1
U == D? Yes (1 == 1)
L == R? Yes (1 == 1)
Result: true
```

## Code Walkthrough

```java
public boolean judgeCircle(String moves) {
    // Create array to store character counts
    // Size 64 covers ASCII for A-Z and other characters
    int[] count = new int[64];

    // Count each move character
    for (char ch : moves.toCharArray()) {
        count[ch - 'A']++;  // Map character to array index
    }

    // Return true if opposite moves are equal
    return count['D' - 'A'] == count['U' - 'A'] &&
           count['L' - 'A'] == count['R' - 'A'];
}
```

### How ASCII Indexing Works

```
'D' - 'A' = 68 - 65 = 3
'U' - 'A' = 85 - 65 = 20
'L' - 'A' = 76 - 65 = 11
'R' - 'A' = 82 - 65 = 17

So count[3], count[20], count[11], count[17] store the frequencies
```

## Edge Cases

1. **Empty string**: "" -> true (already at origin)
2. **Single character**: "U" -> false (not balanced)
3. **All same direction**: "RRRRR" -> false
4. **Perfect balance**: "UDLR" -> true
5. **Odd length**: "UDL" -> false (can't balance with odd length unless it's multiple of 2 per axis)
6. **Very long string**: Handles efficiently O(n)

## Alternative Approaches

### Approach 1: Using HashMap
```java
public boolean judgeCircle(String moves) {
    Map<Character, Integer> map = new HashMap<>();
    for (char c : moves.toCharArray()) {
        map.put(c, map.getOrDefault(c, 0) + 1);
    }
    return map.getOrDefault('U', 0).equals(map.getOrDefault('D', 0)) &&
           map.getOrDefault('L', 0).equals(map.getOrDefault('R', 0));
}
```

### Approach 2: Simple Variable Counters
```java
public boolean judgeCircle(String moves) {
    int up = 0, down = 0, left = 0, right = 0;
    for (char c : moves.toCharArray()) {
        if (c == 'U') up++;
        else if (c == 'D') down++;
        else if (c == 'L') left++;
        else if (c == 'R') right++;
    }
    return up == down && left == right;
}
```

## Related Problems

- 1790: Check if One String Swap Can Make Strings Equal
- 1790: Check if String is Equal
- 2133: Check if Every Row and Column Contains All Numbers
- 463: Island Perimeter
## Tags

`easy` `string` `simulation` `counting` `array` `mathematics`
