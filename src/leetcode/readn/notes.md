# Read N Characters Given Read4 - LeetCode Problem 157

## Problem Statement
The API: `int read4(char *buf)` reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function `int read(char *buf, int n)` that reads n characters from the file.

Constraints:
- The read function will only be called once for each test case
- You must implement using the given read4 API

## Examples

**Example 1:**
```
File content: "abc"
read(buf, 1) returns: 1, buf = ['a']
```

**Example 2:**
```
File content: "abcdef"
read(buf, 5) returns: 5, buf = ['a','b','c','d','e']
```

**Example 3:**
```
File content: "abcdefg"
read(buf, 6) returns: 6, buf = ['a','b','c','d','e','f']
```

## Key Insights
1. **API Constraint**: Can only read 4 characters at a time using read4()
2. **Exact Amount**: Need to read exactly n characters or stop at EOF
3. **Loop Mechanism**: Call read4() repeatedly until we have n characters or EOF
4. **Temporary Buffer**: Use intermediate buffer for read4() results
5. **EOF Detection**: read4() returns less than 4 when EOF reached

## Algorithm Steps

### Approach: Loop and Buffer

**Step 1: Initialize**
- Create temporary buffer of size 4 for read4()
- Track total characters read
- Track current position in output buffer

**Step 2: Loop Until Satisfied**
- Call read4() with temporary buffer
- If read4() returns 0, we've reached EOF
- Copy bytes from temporary buffer to output buffer
- Stop when we've read n characters or reached EOF

**Step 3: Return**
- Return total number of characters read (min(n, file_size))

**Pseudocode:**
```
function read(buf, n):
    total = 0

    while total < n:
        // Read 4 characters at a time
        temp = new char[4]
        count = read4(temp)

        // If nothing read, EOF reached
        if count == 0:
            break

        // Copy from temp to buf
        for i from 0 to count-1:
            if total < n:
                buf[total++] = temp[i]
            else:
                break

        // Stop if less than 4 read (EOF)
        if count < 4:
            break

    return total
```

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time Complexity | O(n) - read approximately n/4 times, each read O(1) |
| Space Complexity | O(1) - only temporary buffer of size 4 |

**Time Analysis:**
- Maximum number of read4() calls: ceil(n/4)
- Each read4() call: O(1)
- Total: O(n)

## ASCII Visualization

```
Example: File = "abcdefg", n = 6

Initial State:
buf = [_, _, _, _, _, _] (size 6)
temp = [_, _, _, _] (size 4)
total = 0

Iteration 1: Call read4(temp)
temp = ['a', 'b', 'c', 'd']
count = 4

Copy to buf:
buf = ['a', 'b', 'c', 'd', _, _]
total = 4

Iteration 2: Call read4(temp)
temp = ['e', 'f', 'g', _]
count = 3 (EOF reached)

Copy to buf:
buf = ['a', 'b', 'c', 'd', 'e', 'f']
total = 6

count < 4, so break

Return: 6

Visualization of read4() behavior:
File: |a|b|c|d|e|f|g|EOF|

First read4():  |a|b|c|d|
Second read4():    |e|f|g|
Third read4():          EOF (return 0)

Buffering mechanism:
┌──────────────────────────────────────────┐
│              File                        │
│   |a|b|c|d|e|f|g|<EOF>                  │
└──────────────────────────────────────────┘
     ↓ read4()
┌──────────────┐
│ temp buffer  │
│ |a|b|c|d|    │
└──────────────┘
     ↓ copy to buf
┌────────────────────────────────┐
│ output buffer (buf)            │
│ |a|b|c|d|_|_|                  │
└────────────────────────────────┘
           ↓ read4() again
┌──────────────┐
│ temp buffer  │
│ |e|f|g|_|    │
└──────────────┘
     ↓ copy to buf (only n - total chars)
┌────────────────────────────────┐
│ output buffer (buf)            │
│ |a|b|c|d|e|f|                  │
└────────────────────────────────┘
```

## Code Walkthrough

```java
public class ReadNGivenRead4 {

    /**
     * @param buf
     *            Destination buffer
     * @param n
     *            Maximum number of characters to read
     * @return The number of characters read
     */
    public int read(char[] buf, int n) {
        int total = 0;

        // Keep reading until we have n characters or reach EOF
        for (int i = 0; i < n;) {
            // Read up to 4 characters
            char[] temp = new char[4];
            int currentBytes = read4(temp);

            // EOF reached if read4 returns 0
            if (currentBytes == 0) {
                break;
            }

            // Copy from temp to buf
            int count = 0;
            while (count < currentBytes && i < n) {
                buf[i++] = temp[count++];
            }

            total += currentBytes;
        }

        // Ensure we don't return more than n
        if (total > n)
            total = total - (total - n);

        return total;
    }

    // Mock API - would be provided by system
    private int read4(char[] temp) {
        // TODO Auto-generated method stub
        return 0;
    }
}

// Cleaner alternative implementation:
public int readAlternative(char[] buf, int n) {
    int total = 0;

    while (total < n) {
        char[] temp = new char[4];
        int count = read4(temp);

        if (count == 0) break;

        // Copy minimum of (remaining space, characters read)
        int toCopy = Math.min(count, n - total);
        System.arraycopy(temp, 0, buf, total, toCopy);

        total += toCopy;

        // If read less than 4, we're at EOF
        if (count < 4) break;
    }

    return total;
}
```

## Edge Cases

1. **Exact Multiple of 4**: File has 8 chars, read(8) -> returns 8
2. **Less than 4 chars**: File has 2 chars, read(5) -> returns 2
3. **Single Character**: File has 1 char, read(1) -> returns 1
4. **Empty File**: File has 0 chars, read(5) -> returns 0
5. **Read Less Than Available**: File has 10 chars, read(3) -> returns 3
6. **Read More Than Available**: File has 5 chars, read(10) -> returns 5
7. **n = 4**: Exactly one read4() call
8. **n = 3**: One read4() call, copy 3 chars

## Related Problems

1. **LeetCode 158**: Read N Characters Given Read4 II - Call multiple times
2. **LeetCode 278**: First Bad Version - Binary search with API
3. **LeetCode 268**: Missing Number - Array processing
4. **LeetCode 359**: Logger Rate Limiter - API design
5. **LeetCode 588**: Design In-Memory File System - File I/O

## Tags

- API Design
- File I/O
- Buffering
- Stream Processing
- Medium Difficulty
- Acceptance: ~45%
