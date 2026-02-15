# LeetCode 277: Find the Celebrity

## Problem Statement

Suppose you are at a party with `n` people (labeled from `0` to `n - 1`), and among them, there may exist one celebrity. The definition of a celebrity is that all the other `n - 1` people know him/her, but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is ask questions like: "Hi, A. Do you know B?" to get information about whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function `bool knows(a, b)` that tells you whether A knows B. Implement a function `int findCelebrity(n)`. Your function should minimize the number of calls to `knows`.

**Note:** There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity at the party. If there is no celebrity, return `-1`.

### Examples

**Example 1:**
```
Input: graph = [
  [1,1,0],
  [0,1,0],
  [1,1,1]
]
Output: 1
Explanation: Person 1 is the celebrity.
Person 0 knows person 1, person 2 knows person 1.
Person 1 doesn't know anyone.
```

**Example 2:**
```
Input: graph = [
  [1,0,1],
  [1,1,1],
  [0,0,1]
]
Output: -1
Explanation: No celebrity exists.
```

**Constraints:**
- n == graph.length
- n == graph[i].length
- 2 <= n <= 100
- graph[i][j] is 0 or 1
- graph[i][i] == 1

## Key Insights

1. **Celebrity Properties**:
   - Everyone knows celebrity: knows(i, celeb) = true for all i != celeb
   - Celebrity knows nobody: knows(celeb, i) = false for all i != celeb

2. **Elimination Strategy**: If knows(a, b) = true, then a cannot be celebrity
   - If knows(a, b) = false, then b cannot be celebrity
   - Each call eliminates one candidate

3. **Two-Pass Algorithm**:
   - First pass: Find potential celebrity candidate
   - Second pass: Verify the candidate

4. **Optimization**: Can find answer in O(n) calls to knows()

## Algorithm Steps

### Two-Pass Approach

**Pass 1: Find Candidate**
1. Initialize candidate = 0
2. For each person i from 1 to n-1:
   - If knows(candidate, i) = true:
     - candidate cannot be celebrity
     - Set candidate = i
3. After loop, candidate is the only possible celebrity

**Pass 2: Verify Candidate**
1. For each person i from 0 to n-1 (except candidate):
   - Check if i knows candidate: knows(i, candidate) should be true
   - Check if candidate knows i: knows(candidate, i) should be false
   - If either check fails, return -1
2. If all checks pass, return candidate

## Complexity Analysis

- **Time Complexity**: O(n)
  - First pass: n-1 calls to knows()
  - Second pass: 2*(n-1) calls to knows()
  - Total: 3n-3 calls, which is O(n)

- **Space Complexity**: O(1)
  - Only using constant extra space
  - No additional data structures

## Visual Explanation

### Example: n = 4

```
People: 0, 1, 2, 3
Celebrity: 2

Knowledge matrix (knows[i][j] = 1 means i knows j):
     0  1  2  3
  0 [1, 1, 1, 0]  -> 0 knows 1 and 2
  1 [1, 1, 1, 1]  -> 1 knows everyone
  2 [0, 0, 1, 0]  -> 2 knows nobody (diagonal always 1)
  3 [1, 0, 1, 1]  -> 3 knows 0 and 2

Celebrity = 2 because:
  - Everyone (0,1,3) knows 2: column 2 all 1s (except diagonal)
  - 2 knows nobody: row 2 all 0s (except diagonal)
```

### Pass 1: Finding Candidate

```
Initial: celeb = 0

i=1: knows(0, 1)?
     Yes -> 0 cannot be celebrity
     celeb = 1

i=2: knows(1, 2)?
     Yes -> 1 cannot be celebrity
     celeb = 2

i=3: knows(2, 3)?
     No -> 3 cannot be celebrity
     celeb stays 2

Candidate after Pass 1: 2
```

### Pass 2: Verification

```
Candidate: 2

Check i=0:
  knows(0, 2)? Yes ✓ (0 knows candidate)
  knows(2, 0)? No ✓ (candidate doesn't know 0)

Check i=1:
  knows(1, 2)? Yes ✓
  knows(2, 1)? No ✓

Check i=3:
  knows(3, 2)? Yes ✓
  knows(2, 3)? No ✓

All checks pass -> return 2
```

### Why Pass 1 Works

```
Key insight: After checking knows(a, b):
- If true: a eliminated (a knows someone)
- If false: b eliminated (someone doesn't know b)

Either way, one person eliminated per call!

Example progression:
Candidates: {0, 1, 2, 3}

knows(0,1)=true  -> eliminate 0
Candidates: {1, 2, 3}

knows(1,2)=true  -> eliminate 1
Candidates: {2, 3}

knows(2,3)=false -> eliminate 3
Candidates: {2}

Only one candidate left: 2
```

## Code Walkthrough

```java
public int findCelebrity(int n) {
    // Pass 1: Find potential celebrity candidate
    int celeb = 0;

    // Compare current candidate with each person
    for (int i = 1; i < n; i++) {
        // If celeb knows i, then celeb cannot be the celebrity
        // Update celeb to i (new potential candidate)
        if (knows(celeb, i)) {
            celeb = i;
        }
        // If celeb doesn't know i, then i cannot be celebrity
        // celeb remains unchanged
    }

    // Pass 2: Verify the candidate
    for (int i = 0; i < n; i++) {
        // Skip self-check
        if (i != celeb) {
            // Check two conditions:
            // 1. Everyone should know the celebrity
            // 2. Celebrity should not know anyone

            // If someone doesn't know celeb, not a celebrity
            if (!knows(i, celeb)) {
                return -1;
            }

            // If celeb knows someone, not a celebrity
            if (knows(celeb, i)) {
                return -1;
            }
        }
    }

    // All checks passed
    return celeb;
}
```

## Alternative Approaches

### Approach 1: Brute Force (O(n^2))

```java
public int findCelebrity(int n) {
    for (int i = 0; i < n; i++) {
        boolean isCeleb = true;

        // Check if everyone knows i
        for (int j = 0; j < n; j++) {
            if (i != j && !knows(j, i)) {
                isCeleb = false;
                break;
            }
        }

        // Check if i knows nobody
        if (isCeleb) {
            for (int j = 0; j < n; j++) {
                if (i != j && knows(i, j)) {
                    isCeleb = false;
                    break;
                }
            }
        }

        if (isCeleb) return i;
    }

    return -1;
}
```

### Approach 2: Using Stack

```java
public int findCelebrity(int n) {
    Stack<Integer> stack = new Stack<>();

    // Push all candidates
    for (int i = 0; i < n; i++) {
        stack.push(i);
    }

    // Eliminate candidates
    while (stack.size() > 1) {
        int a = stack.pop();
        int b = stack.pop();

        if (knows(a, b)) {
            stack.push(b);  // a eliminated
        } else {
            stack.push(a);  // b eliminated
        }
    }

    // Verify remaining candidate
    int candidate = stack.pop();
    for (int i = 0; i < n; i++) {
        if (i != candidate) {
            if (!knows(i, candidate) || knows(candidate, i)) {
                return -1;
            }
        }
    }

    return candidate;
}
```

## Edge Cases

1. **No Celebrity**: All people know each other
   - Output: -1

2. **Two People**: n = 2
   ```
   If 0 knows 1 and 1 doesn't know 0: 1 is celebrity
   If mutual or reversed: -1
   ```

3. **First Person is Celebrity**: celeb = 0
   - Algorithm still works

4. **Last Person is Celebrity**: celeb = n-1
   - Candidate updated throughout Pass 1

5. **Everyone Knows Everyone**:
   ```
   No one can be celebrity
   Output: -1
   ```

6. **Isolated Person**: Someone who knows nobody and nobody knows them
   - Not a celebrity (others must know celebrity)

## Proof of Correctness

**Claim**: After Pass 1, if celebrity exists, it must be the candidate.

**Proof**:
- Let C be the celebrity (if exists)
- In Pass 1, for each comparison:
  - If knows(a, b) = true and a = C: impossible (C knows nobody)
  - If knows(a, b) = false and b = C: impossible (everyone knows C)
- Therefore, C is never eliminated
- Since all others are eliminated, C must be the final candidate

**Verification**: Pass 2 confirms candidate satisfies both celebrity properties.

## Related Problems

1. **LeetCode 997**: Find the Town Judge (similar celebrity problem)
2. **LeetCode 1615**: Maximal Network Rank
3. **LeetCode 1559**: Detect Cycles in 2D Grid
4. **LeetCode 323**: Number of Connected Components in an Undirected Graph

## Tags

- Graph
- Greedy
- Two Pointers
- Array
- Elimination Strategy
- Social Network
