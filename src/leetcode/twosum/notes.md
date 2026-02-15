# Two Sum III - Data Structure Design (LeetCode 170)

## Problem Statement
Design a data structure that supports the following two operations:

1. `add(number)`: Add the number to an internal data structure.
2. `find(value)`: Find if there exists any pair of numbers which sum is equal to the value.

You may assume that the same number can be added more than once.

## Examples
```
TwoSum twoSum = new TwoSum();
twoSum.add(1);
twoSum.add(3);
twoSum.add(5);
twoSum.find(4);    // return true (1 + 3 = 4)
twoSum.find(7);    // return false (no pair sums to 7)

Another example:
twoSum.add(0);
twoSum.add(0);
twoSum.find(0);    // return true (0 + 0 = 0)
```

## Key Insights
1. Use a Map to store numbers and their frequencies (handles duplicates)
2. For finding, iterate through all numbers and check if complement exists
3. Special case: if looking for pair of same number, need count >= 2
4. TreeMap keeps keys sorted (optional optimization)
5. Trade-off: Fast add O(1), slower find O(n)
6. Alternative: Store all possible sums O(n²) space, O(1) find

## Algorithm Steps

### Approach: HashMap with Frequency Counting
**For add(number):**
1. Check if number already exists in map
2. If yes: increment its count
3. If no: add it with count 1

**For find(value):**
1. Iterate through each number in the map
2. Calculate needed complement: difference = value - number
3. Check if complement exists in map
4. Special check: if complement == number, verify count >= 2
5. If valid pair found, return true
6. If no pair found after checking all, return false

## Complexity Analysis
- **Time Complexity:**
  - add(): O(1)
  - find(): O(n) where n is number of unique numbers
- **Space Complexity:** O(n) for storing n unique numbers

## ASCII Visualization

```
Initial state:
numList = {}

After add(1):
numList = {1: 1}

After add(3):
numList = {1: 1, 3: 1}

After add(5):
numList = {1: 1, 3: 1, 5: 1}

find(4):
  For number = 1:
    difference = 4 - 1 = 3
    Does 3 exist? Yes, and 3 != 1
    Return true

find(7):
  For number = 1:
    difference = 7 - 1 = 6
    Does 6 exist? No
  For number = 3:
    difference = 7 - 3 = 4
    Does 4 exist? No
  For number = 5:
    difference = 7 - 5 = 2
    Does 2 exist? No
  Return false

---

After add(0):
numList = {0: 1, 1: 1, 3: 1, 5: 1}

After add(0):
numList = {0: 2, 1: 1, 3: 1, 5: 1}

find(0):
  For number = 0:
    difference = 0 - 0 = 0
    Does 0 exist? Yes
    Is difference == number? Yes (0 == 0)
    Is count >= 2? Yes (count = 2)
    Return true

find(1):
  For number = 0:
    difference = 1 - 0 = 1
    Does 1 exist? Yes
    Is difference == number? No (1 != 0)
    Return true
```

## Code Walkthrough

```java
public class TwoSum {
    // Map to store number and its frequency
    Map<Integer, Integer> numList = new TreeMap<>();

    // Add a number to the data structure
    public void add(int number) {
        if (numList.containsKey(number)) {
            // Increment count if number already exists
            numList.put(number, numList.get(number) + 1);
        } else {
            // Add new number with count 1
            numList.put(number, 1);
        }
    }

    // Find if there exists a pair that sums to the value
    public boolean find(int value) {
        // Check each number in the map
        for (int n : numList.keySet()) {
            int difference = value - n;

            // Check if complement exists
            if (numList.containsKey(difference)) {
                int operandCount = numList.get(difference);

                // Special case: if looking for pair of same number
                if (difference == n && operandCount < 2)
                    continue;

                return true;
            }
        }

        return false;
    }
}
```

## Edge Cases
1. Finding sum of same number (need 2 occurrences):
   - add(0), add(0), find(0) → true
   - add(0), find(0) → false

2. Negative numbers:
   - add(-5), add(5), find(0) → true

3. Duplicate additions:
   - add(1), add(1), add(1), find(2) → true

4. Large numbers:
   - add(Integer.MAX_VALUE), add(1), find(Integer.MAX_VALUE + 1) → May overflow

5. No pair exists:
   - add(1), add(2), find(4) → false

## Alternative: Store All Sums (Trade-Off)

```java
public class TwoSum {
    Set<Long> sums = new HashSet<>();
    List<Integer> nums = new ArrayList<>();

    public void add(int number) {
        // Add all possible sums with existing numbers
        for (int num : nums) {
            sums.add((long) num + number);
        }
        nums.add(number);
    }

    public boolean find(int value) {
        return sums.contains((long) value);
    }
}
```

**Trade-off:**
- Previous: Fast add O(1), slow find O(n)
- This: Slow add O(n), fast find O(1)

## Related Problems
- LeetCode 1: Two Sum
- LeetCode 167: Two Sum II - Input Array Is Sorted
- LeetCode 653: Two Sum IV - Input is a BST
- LeetCode 1099: Two Sum Less Than K

## Tags
- Hash Table
- Design
- Data Structure
- Two Pointers
