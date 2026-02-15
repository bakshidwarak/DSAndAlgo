# 384. Shuffle an Array

## Problem Statement
Design a class Solution that:
1. Takes an array of integers in the constructor
2. Provides a reset() method that returns the original array
3. Provides a shuffle() method that returns a random permutation of the array

Each permutation should be equally likely to be returned.

## Examples

```
nums = [1,2,3]

shuffle() -> [3,1,2]  (random permutation)
shuffle() -> [1,3,2]  (different random permutation)
reset()   -> [1,2,3]  (back to original)
shuffle() -> [1,2,3]  (random permutation, could be same order)
```

## Key Insights

1. **Fisher-Yates Algorithm**: Industry-standard for shuffling
2. **Random selection**: Pick random element and swap with current position
3. **In-place shuffling**: Can shuffle in O(n) space (result array)
4. **Uniform distribution**: Each permutation has equal probability
5. **Original preservation**: Keep original array for reset()

## Shuffle Algorithms

### Fisher-Yates Algorithm (Better)
```
for i = n-1 down to 1:
    j = random(0, i)
    swap(arr[i], arr[j])
```

### Naive Random Approach (Used in Code)
```
Used array to mark taken indices
Avoids picking same element twice
Not as efficient but clear
```

## Complexity Analysis

**Constructor:** O(n)
- Stores reference to original array

**shuffle():** O(n)
- Generates random permutation
- n random selections

**reset():** O(1) or O(n)
- Depends on implementation
- Could create new array or use stored original

**Space Complexity:**
- O(n) for result array in shuffle()
- O(1) if not counting output

## ASCII Visualization

```
Original: [1, 2, 3]
Index:     0  1  2

Shuffle attempt:

Using HashSet to track taken indices:

Iteration i=0:
  rand = random(0, 3) = 1
  result[0] = arr[1] = 2
  taken = {1}

Iteration i=1:
  rand = random(0, 3) = 0
  if taken.contains(0)? NO
  result[1] = arr[0] = 1
  taken = {1, 0}

Iteration i=2:
  rand = random(0, 3) = 2
  if taken.contains(2)? NO
  result[2] = arr[2] = 3
  taken = {1, 0, 2}

Result: [2, 1, 3]
```

## Code Walkthrough

```java
public class ShuffleAnArray {
    int[] nums;              // Original array
    int[] permutation;       // Current permutation
    HashSet<Integer> taken = new HashSet<>();  // Track used indices

    // Constructor: store original array
    public ShuffleAnArray(int[] nums) {
        this.nums = nums;
        this.permutation = nums;
    }

    // Reset: return to original configuration
    public int[] reset() {
        permutation = nums;
        taken.clear();
        return permutation;
    }

    // Shuffle: return random permutation
    public int[] shuffle() {
        // Create new result array
        int[] result = new int[nums.length];

        // Fill result with random elements from permutation
        for (int i = 0; i < permutation.length;) {
            // Generate random index
            int rand = (int) (Math.random() * permutation.length);

            // Skip if already taken
            if (taken.contains(rand))
                continue;

            // Add element at random index to result
            result[i++] = permutation[rand];

            // Mark index as taken
            taken.add(rand);
        }

        // Clear taken set for next shuffle
        taken.clear();

        // Update permutation to current shuffle
        permutation = result;

        return result;
    }
}
```

## Fisher-Yates Implementation (Better)

```java
public class ShuffleArrayOptimal {
    int[] original;

    public ShuffleArrayOptimal(int[] nums) {
        original = nums.clone();
    }

    public int[] reset() {
        return original.clone();
    }

    public int[] shuffle() {
        int[] result = original.clone();

        // Fisher-Yates shuffling
        Random random = new Random();
        for (int i = result.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);  // 0 to i inclusive
            // Swap
            int temp = result[i];
            result[i] = result[j];
            result[j] = temp;
        }

        return result;
    }
}
```

### Why Fisher-Yates is Better

1. **No HashSet overhead**: O(1) space for shuffle logic
2. **Guaranteed termination**: Always completes in O(n)
3. **Mathematical proof**: Proven to generate uniform distribution
4. **Industry standard**: Used in Java Collections.shuffle()

## Probability Analysis

Each permutation has probability = 1/n! of being selected.

For array [1,2,3] with n=3:
- 3! = 6 possible permutations
- Each has probability = 1/6

### Verification
```
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]

Fisher-Yates generates uniform distribution over all 6
```

## Edge Cases

1. **Empty array**: [] -> []
2. **Single element**: [1] -> [1]
3. **Two elements**: [1,2] -> [1,2] or [2,1]
4. **Duplicate values**: [1,1,2] -> valid shuffles include [1,1,2], [1,2,1], [2,1,1]
5. **Multiple calls**: Multiple shuffle() calls should give different results

## Usage Example

```java
ShuffleAnArray solution = new ShuffleAnArray(new int[]{1,2,3});

// First shuffle
int[] shuffled1 = solution.shuffle();  // e.g., [2,3,1]

// Reset
int[] reset = solution.reset();  // [1,2,3]

// Another shuffle
int[] shuffled2 = solution.shuffle();  // e.g., [3,1,2]
```

## Related Problems

- 1837: Sum of Digits in Base K
- 2007: Find Original Array From Doubled Array
- Randomized algorithms in general
## Tags

`medium` `array` `randomization` `random` `shuffle` `design` `fisher-yates`
