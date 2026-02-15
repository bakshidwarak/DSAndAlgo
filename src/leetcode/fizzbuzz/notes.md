# LeetCode 412: Fizz Buzz

## Problem Statement

Given an integer `n`, return a string array `answer` (**1-indexed**) where:
- `answer[i] == "FizzBuzz"` if `i` is divisible by `3` and `5`
- `answer[i] == "Fizz"` if `i` is divisible by `3`
- `answer[i] == "Buzz"` if `i` is divisible by `5`
- `answer[i] == i` (as a string) if none of the above conditions are true

### Examples

**Example 1:**
```
Input: n = 3
Output: ["1","2","Fizz"]
```

**Example 2:**
```
Input: n = 5
Output: ["1","2","Fizz","4","Buzz"]
```

**Example 3:**
```
Input: n = 15
Output: ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]
```

**Constraints:**
- 1 <= n <= 10^4

## Key Insights

1. **Divisibility Rules**: Check if number is divisible by 3, 5, or both
2. **Order Matters**: Must check divisibility by both 3 and 5 before individual checks
3. **String Building**: Use StringBuilder for efficient concatenation
4. **Modulo Operator**: Use `i % 3 == 0` to check divisibility by 3
5. **Classic Interview Problem**: Tests basic programming and logic skills

## Algorithm Steps

1. **Initialize** result list

2. **For each number from 1 to n**:
   - Create StringBuilder for current answer
   - Check if NOT divisible by 3 AND NOT divisible by 5:
     - Append the number itself
   - Otherwise:
     - If divisible by 3: append "Fizz"
     - If divisible by 5: append "Buzz"
   - Add StringBuilder to result

3. **Return** result list

## Complexity Analysis

- **Time Complexity**: O(n)
  - Iterate through numbers 1 to n once
  - Each iteration does constant work
  - Overall: O(n)

- **Space Complexity**: O(n)
  - Result list stores n strings
  - Each string is O(1) size
  - Overall: O(n)

## Visual Explanation

### Example: n = 15

```
Number  | Divisible by 3? | Divisible by 5? | Output
--------|-----------------|-----------------|----------
1       | No              | No              | "1"
2       | No              | No              | "2"
3       | Yes             | No              | "Fizz"
4       | No              | No              | "4"
5       | No              | Yes             | "Buzz"
6       | Yes             | No              | "Fizz"
7       | No              | No              | "7"
8       | No              | No              | "8"
9       | Yes             | No              | "Fizz"
10      | No              | Yes             | "Buzz"
11      | No              | No              | "11"
12      | Yes             | No              | "Fizz"
13      | No              | No              | "13"
14      | No              | No              | "14"
15      | Yes             | Yes             | "FizzBuzz"
```

### Decision Tree for Each Number

```
For number i:
                    i
                    |
         ┌──────────┴──────────┐
         |                     |
    i%3==0 && i%5==0?          |
         |                     |
      ┌──┴──┐              No  |
     Yes    No                 |
      |      |                 |
"FizzBuzz"   |                 |
             |                 |
         ┌───┴───┐             |
         |       |             |
     i%3==0?  i%5==0?          |
         |       |             |
      ┌──┴──┐ ┌──┴──┐      ┌──┴──┐
     Yes   No Yes   No     String(i)
      |       |
    "Fizz" "Buzz"
```

## Code Walkthrough

```java
public List<String> fizzBuzz(int n) {
    List<String> result = new ArrayList<>();

    // Iterate from 1 to n (inclusive)
    for (int i = 1; i <= n; i++) {
        StringBuilder sb = new StringBuilder();

        // Check if number should be added as string
        // (not divisible by 3 or 5)
        if (i % 3 != 0 && i % 5 != 0) {
            sb.append(i);
        }
        // Otherwise, build Fizz/Buzz/FizzBuzz
        else {
            // Check divisibility by 3
            if (i % 3 == 0)
                sb.append("Fizz");

            // Check divisibility by 5
            if (i % 5 == 0)
                sb.append("Buzz");

            // If divisible by both, sb will have "FizzBuzz"
            // If divisible by 3 only, sb will have "Fizz"
            // If divisible by 5 only, sb will have "Buzz"
        }

        // Add to result list
        result.add(sb.toString());
    }

    return result;
}
```

## Alternative Implementations

### Approach 1: If-Else Chain

```java
public List<String> fizzBuzz(int n) {
    List<String> result = new ArrayList<>();

    for (int i = 1; i <= n; i++) {
        if (i % 15 == 0) {           // Divisible by both 3 and 5
            result.add("FizzBuzz");
        } else if (i % 3 == 0) {     // Divisible by 3 only
            result.add("Fizz");
        } else if (i % 5 == 0) {     // Divisible by 5 only
            result.add("Buzz");
        } else {                      // Not divisible by 3 or 5
            result.add(String.valueOf(i));
        }
    }

    return result;
}
```

### Approach 2: Ternary Operators

```java
public List<String> fizzBuzz(int n) {
    List<String> result = new ArrayList<>();

    for (int i = 1; i <= n; i++) {
        String str = (i % 3 == 0 ? "Fizz" : "") + (i % 5 == 0 ? "Buzz" : "");
        result.add(str.isEmpty() ? String.valueOf(i) : str);
    }

    return result;
}
```

### Approach 3: Extensible (Easy to Add More Rules)

```java
public List<String> fizzBuzz(int n) {
    List<String> result = new ArrayList<>();

    for (int i = 1; i <= n; i++) {
        StringBuilder sb = new StringBuilder();

        // Easy to extend with more divisibility rules
        if (i % 3 == 0) sb.append("Fizz");
        if (i % 5 == 0) sb.append("Buzz");
        // Could add: if (i % 7 == 0) sb.append("Jazz");

        if (sb.length() == 0) {
            sb.append(i);
        }

        result.add(sb.toString());
    }

    return result;
}
```

## Pattern Analysis

### Numbers Divisible by 3
```
3, 6, 9, 12, 15, 18, 21, 24, 27, 30, ...
Pattern: Every 3rd number
```

### Numbers Divisible by 5
```
5, 10, 15, 20, 25, 30, 35, 40, 45, 50, ...
Pattern: Every 5th number
```

### Numbers Divisible by Both (LCM of 3 and 5 = 15)
```
15, 30, 45, 60, 75, 90, ...
Pattern: Every 15th number
```

## Edge Cases

1. **n = 1**:
   - Output: ["1"]

2. **n = 3**:
   - Output: ["1", "2", "Fizz"]

3. **n = 5**:
   - Output: ["1", "2", "Fizz", "4", "Buzz"]

4. **n = 15**:
   - First occurrence of "FizzBuzz"

5. **Large n (10000)**:
   - Should handle efficiently in O(n)

6. **Powers of 3**: 3, 9, 27, 81
   - All should be "Fizz"

7. **Powers of 5**: 5, 25, 125, 625
   - All should be "Buzz"

## Interview Extensions

Common follow-up questions:

1. **Add more rules**: What if 7 -> "Jazz"?
   - Use the extensible StringBuilder approach

2. **Make it configurable**: Pass rules as parameters
   ```java
   Map<Integer, String> rules = new HashMap<>();
   rules.put(3, "Fizz");
   rules.put(5, "Buzz");
   rules.put(7, "Jazz");
   ```

3. **Thread safety**: Make it work with multiple threads
   - Use thread-local storage or immutable objects

4. **Optimize space**: Don't store all results
   - Print on-the-fly or use generator/iterator pattern

## Related Problems

1. **LeetCode 1195**: Fizz Buzz Multithreaded
2. **LeetCode 293**: Flip Game
3. **LeetCode 258**: Add Digits
4. **LeetCode 202**: Happy Number
5. **LeetCode 263**: Ugly Number

## Tags

- Math
- String
- Simulation
- Classic Problem
- Interview Favorite
