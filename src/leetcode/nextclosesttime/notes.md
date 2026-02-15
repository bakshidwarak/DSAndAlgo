# LeetCode 681: Next Closest Time

## Problem Statement
Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits.

There is no limit on how many times a digit can be reused. The returned time must be valid, and you may assume the given input string is always valid.

## Examples

**Example 1:**
```
Input: "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4 is 19:39,
which occurs 5 minutes later. It is not 19:33 because this occurs 23 hours
and 59 minutes later.
```

**Example 2:**
```
Input: "23:59"
Output: "22:22"
Explanation: The next closest time choosing from digits 2, 3, 5, 9 is 22:22.
It may be assumed that the returned time is next day's time since it is smaller.
```

## Key Insights

1. **Circular Time**: Hours and minutes wrap around (24-hour format)
2. **Only 1440 Minutes**: Total minutes in a day (24 * 60 = 1440)
3. **Digit Reuse**: Can use any available digit any number of times
4. **Next Closest**: Increment by 1 minute and check if valid
5. **Constraint-based Search**: Valid time if all digits from current are reused

## Algorithm Steps

1. Extract available digits from input time
2. Convert input time to minutes (0-1439)
3. Increment minute by 1 (mod 1440 for circular)
4. For each new time, convert to array of 4 digits
5. Check if all digits in array are available digits
6. Continue until valid time found
7. Continue for at most 1440 iterations (one full day)

## Complexity Analysis

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(1) - Maximum 1440 iterations |
| **Space Complexity** | O(1) - Fixed space for time/digit arrays |
| **Average Case** | Usually finds next time within minutes |

- Fixed upper bound of 1440 (minutes in a day)
- Most cases find answer quickly

## ASCII Visualization

```
Input: "19:34"
Available digits: {1, 3, 4, 9}

Conversion to numeric:
19:34 → 19*60 + 34 = 1140 + 34 = 1174

Increment and check:
1175 → 19:35 → digits [1,9,3,5] → 5 not available
1176 → 19:36 → digits [1,9,3,6] → 6 not available
1177 → 19:37 → digits [1,9,3,7] → 7 not available
1178 → 19:38 → digits [1,9,3,8] → 8 not available
1179 → 19:39 → digits [1,9,3,9] → all available ✓

Output: "19:39"

Input: "23:59"
Available digits: {2, 3, 5, 9}

Conversion:
23:59 → 23*60 + 59 = 1380 + 59 = 1439

Increment and check:
1440 % 1440 = 0 → 0:00 → digits [0,0,0,0] → 0 not available
1 → 0:01 → digits [0,0,0,1] → 0, 1 not all available
...
1334 → 22:14 → not all available
...
1342 → 22:22 → digits [2,2,2,2] → all available ✓

Output: "22:22"
```

## Code Walkthrough

```java
String nextClosestTime(String time) {
    int[] count = new int[10];  // Track available digits

    // Extract available digits from input
    for (char ch : time.toCharArray()) {
        if (ch == ':')
            continue;
        count[ch - '0'] = 1;
    }

    // Convert time to minutes
    int numeric = getNumericTime(time);
    int generatedTime = numeric;

    // Increment and search until valid time found
    do {
        numeric = (numeric + 1) % 1440;  // Circular increment

        int[] newTime = getTimeFromNumeric(numeric);

        // Check if all digits in new time are available
        if (newTimeContainsExistingChars(newTime, count)) {
            return stringify(newTime);
        }
    } while (numeric != generatedTime);  // Stop after one full day

    return "";
}

// Convert time string to minutes
public int getNumericTime(String time) {
    String[] chars = time.split(":");
    int hours = Integer.parseInt(chars[0]);
    int minutes = Integer.parseInt(chars[1]);
    return hours * 60 + minutes;
}

// Convert minutes to 4-digit array [h1, h2, m1, m2]
public int[] getTimeFromNumeric(int num) {
    int minutes = num % 60;
    int hours = num / 60;
    int[] result = new int[4];
    result[0] = hours / 10;
    result[1] = hours % 10;
    result[2] = minutes / 10;
    result[3] = minutes % 10;
    return result;
}

// Check if all digits in time array exist in available digits
public boolean newTimeContainsExistingChars(int[] time, int[] count) {
    for (int ch : time) {
        if (count[ch] != 1)  // Digit not available
            return false;
    }
    return true;
}

// Format time array back to string
public String stringify(int[] time) {
    StringBuilder sb = new StringBuilder();
    sb.append(time[0]).append(time[1]).append(":");
    sb.append(time[2]).append(time[3]);
    return sb.toString();
}
```

**Execution for "19:34":**
```
count[1]=1, count[3]=1, count[4]=1, count[9]=1
numeric = 1174
generatedTime = 1174

Loop iteration 1:
  numeric = 1175
  newTime = [1, 9, 3, 5]
  count[5] != 1, invalid

Loop iteration 2:
  numeric = 1176
  newTime = [1, 9, 3, 6]
  count[6] != 1, invalid

Loop iteration 5:
  numeric = 1179
  newTime = [1, 9, 3, 9]
  count[1]=1, count[9]=1, count[3]=1, count[9]=1, valid
  return "19:39"
```

## Edge Cases

1. **All same digits**: "11:11" → Next would be "11:11" (after 1440 minutes)
2. **Multiple valid options**: "12:34" → Next closest
3. **Crossing hour boundary**: "19:59" → Might go to 20:00, 20:02, etc.
4. **Midnight case**: "23:59" → Wraps to next day
5. **Single digit appears**: "12:11" → Can reuse digits

## Related Problems

1. **LeetCode 24** - Swap Nodes in Pairs
2. **LeetCode 50** - Pow(x, n)
3. **LeetCode 89** - Gray Code
4. **LeetCode 515** - Find Largest Value in Each Tree Row
5. **LeetCode 639** - Decode Ways II

## Tags

`String` `Simulation` `Math` `Medium` `Google` `Amazon` `Twitter`

## Alternative Approaches

### Approach 2: Generate All Valid Times
```
- Generate all valid times using available digits
- Find minimum time greater than input
- Time: O(k^4) where k = number of unique digits
- For this problem, k <= 4, so effectively constant
```

### Approach 3: Backtracking
```
- Build time digit by digit
- Only try valid combinations
- More complex but potentially faster for many invalid times
```

## Optimization Notes

1. **Circular Arithmetic**: Use modulo for wrapping
2. **Early Termination**: Stop after checking one full day
3. **Direct Comparison**: Compare entire time as minutes (easier)
4. **Digit Reuse**: No limit on reusing digits

## Notes

- This is a brute force problem due to small constraint (1440 minutes)
- More efficient for general time problems to generate candidates
- Good example of circular time arithmetic
- Key insight: Time is circular within a 24-hour period
- Related to modular arithmetic problems
