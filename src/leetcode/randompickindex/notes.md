# Random Pick Index - LeetCode Problem 398

## Problem Statement
Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Constraints:
- The array size can be very large
- Solution that uses too much extra space will not pass the judge
- Each index should have equal probability of returning

## Examples

**Example 1:**
```
Input: nums = [1,2,3,3,3], Solution.pick(3)
Output: 2, 3, or 4 (each with equal probability 1/3)

Input: nums = [1,2,3,3,3], Solution.pick(1)
Output: 0 (always, since only one occurrence)
```

## Key Insights
1. **Uniform Distribution**: Each occurrence must have equal probability 1/k where k = count of target
2. **HashMap Storage**: Map each number to its indices for O(1) lookup and random pick
3. **Space-Time Tradeoff**: O(n) space for O(1) random access and pick
4. **Advanced Approach**: Reservoir Sampling for O(1) space (scan array each time)
5. **Probability**: For target with k occurrences, each has 1/k chance of selection

## Algorithm Steps

### Approach 1: HashMap (More Space)

**Construction (O(n)):**
1. Iterate through nums
2. For each element, store its index in a HashMap
3. Map structure: number -> List of indices

**Pick (O(1)):**
1. Get list of indices for target from HashMap
2. Generate random index in range [0, list.size())
3. Return the index at that position

### Approach 2: Reservoir Sampling (O(1) Space)

**Pick (O(n)):**
1. Scan array, counting occurrences of target
2. When target found at position i:
   - Generate random number
   - If random < (1/count), select this index
   - Increment count
3. Return selected index

**Pseudocode (Approach 1):**
```
function RandomPickIndex(nums):
    map = new HashMap()
    for i from 0 to nums.length:
        if not map.contains(nums[i]):
            map[nums[i]] = new List()
        map[nums[i]].add(i)

function pick(target):
    indices = map[target]
    randomIndex = random(0, indices.size())
    return indices[randomIndex]
```

## Complexity Analysis

**Approach 1: HashMap**

| Metric | Value |
|--------|-------|
| Construction Time | O(n) |
| Pick Time | O(1) |
| Space Complexity | O(n) - store all indices |

**Approach 2: Reservoir Sampling**

| Metric | Value |
|--------|-------|
| Construction Time | O(n) - no preprocessing |
| Pick Time | O(n) - scan array each time |
| Space Complexity | O(1) |

## ASCII Visualization

```
Example: nums = [1,2,3,3,3]

HashMap Approach:
Construction Phase:
nums[0]=1 → map: {1: [0]}
nums[1]=2 → map: {1: [0], 2: [1]}
nums[2]=3 → map: {1: [0], 2: [1], 3: [2]}
nums[3]=3 → map: {1: [0], 2: [1], 3: [2, 3]}
nums[4]=3 → map: {1: [0], 2: [1], 3: [2, 3, 4]}

HashMap Structure:
┌─────────────────────────────────────┐
│          HashMap                    │
├─────────────────────────────────────┤
│ 1 → [0]                             │
│ 2 → [1]                             │
│ 3 → [2, 3, 4]                       │
└─────────────────────────────────────┘

pick(3) Operations:
1. Get indices: [2, 3, 4]
2. Generate random: 0, 1, or 2
3. Return: indices[random]
   - Random 0 → return 2 (probability 1/3)
   - Random 1 → return 3 (probability 1/3)
   - Random 2 → return 4 (probability 1/3)

Reservoir Sampling for pick(3):
Scan array:
i=0: nums[0]=1 (not target, continue)
i=1: nums[1]=2 (not target, continue)
i=2: nums[2]=3 (target, count=1, select with prob 1/1 → selected)
i=3: nums[3]=3 (target, count=2, select with prob 1/2)
i=4: nums[4]=3 (target, count=3, select with prob 1/3)

Each index has equal final probability:
- For any occurrence at position i
- Probability = (1/k) where k = total count of target
```

## Code Walkthrough

```java
public class RandomPickIndex {

    class Pair {
        int num;
        List<Integer> indices = new ArrayList<>();

        public Pair(int num) {
            this.num = num;
        }
    }

    HashMap<Integer, Pair> pairs = new HashMap<>();

    // Constructor: O(n) time, O(n) space
    public RandomPickIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            Pair p = pairs.get(nums[i]);

            // Create new pair if first occurrence
            if (p == null) {
                p = new Pair(nums[i]);
            }

            // Add index to the pair
            p.indices.add(i);
            pairs.put(nums[i], p);
        }
    }

    // Pick: O(1) time
    public int pick(int target) {
        Pair p = pairs.get(target);

        // Generate random index in range [0, size)
        int randomIndex = (new Random()).nextInt(p.indices.size());

        // Return the actual array index
        return p.indices.get(randomIndex);
    }
}

// Alternative: Reservoir Sampling (O(1) space)
public class RandomPickIndexReservoir {

    int[] nums;
    Random random;

    public RandomPickIndexReservoir(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }

    public int pick(int target) {
        int count = 0;
        int result = -1;

        // Scan array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                count++;

                // Select current index with probability 1/count
                if (random.nextInt(count) == 0) {
                    result = i;
                }
            }
        }

        return result;
    }
}
```

## Edge Cases

1. **Single Occurrence**: `[1,2,3]` pick(2) -> always return 1
2. **All Same**: `[5,5,5,5]` pick(5) -> return 0, 1, 2, or 3 equally
3. **Target at Start**: `[3,1,2]` pick(3) -> return 0
4. **Target at End**: `[1,2,3]` pick(3) -> return 2
5. **Large Array**: n=10000, handle efficiently
6. **Many Duplicates**: `[1,1,1,1,1,1]` pick(1) -> equal distribution
7. **Negative Numbers**: `[-1,-2,3]` handle correctly
8. **Multiple Picks**: Multiple calls should have equal distribution

## Related Problems

1. **LeetCode 382**: Linked List Random Node - Random pick from linked list
2. **LeetCode 497**: Random Point in Non-overlapping Rectangles - Weighted random pick
3. **LeetCode 710**: Random Pick with Blacklist
4. **LeetCode 1157**: Online Majority Element In Subarray - Preprocessing for queries
## Tags

- Hash Map
- Random Selection
- Reservoir Sampling
- Probability
- Arrays
- Difficulty: Medium
- Acceptance: ~55%
