# LeetCode 746: Min Cost Climbing Stairs

## Problem Statement
On a staircase, the i-th step has a cost `cost[i]`. Once you pay the cost, you can climb either 1 or 2 steps. Find the **minimum cost to reach the top** of the stairs. You can start from either step 0 or step 1.

## Difficulty
Easy

## Examples

### Example 1
- **Input:** `cost = [10, 15, 20]`
- **Output:** `15`
- **Explanation:** Start at step 1 (pay 15), reach top with total cost 15

### Example 2
- **Input:** `cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]`
- **Output:** `6`
- **Explanation:** Pay 1 for most steps, skip the 100-cost steps by jumping 2

## Key Insights
1. **Dynamic Programming**: Optimal solution depends on previous two steps
2. **Two States**: Track cost to reach each step
3. **Choice at Each Step**: Can come from 1 step before or 2 steps before
4. **Space Optimization**: Only need last two values, not entire array
5. **Reverse Iteration**: Process from 1 to n to calculate costs bottom-up

## Algorithm Steps
1. Initialize two variables: first = cost[0], second = 0
2. For each step from 1 to array length:
   - Current cost = current step cost + minimum of previous two
   - Update first and second
3. Return first (cost to reach top)

## Complexity Analysis
- **Time Complexity:** O(n) - Single pass through cost array
- **Space Complexity:** O(1) - Only using two variables

## ASCII Visualization

```
cost = [10, 15, 20]

Step 0: first = 10, second = 0
        Cost to reach step 0 = 10

Step 1: curr = 15, total = 15 + min(10, 0) = 25
        But wait, we need to update first and second
        curr = 15 + min(10, 0) = 25
        second = 10, first = 25

Step 2 (at top): curr = 20, total = 20 + min(25, 10) = 30
        But we take min(first, second) = min(25, 10) = 10
        second = 25, first = 30

Wait, let me recalculate with iteration from 1 to n:
i=1: curr=15, total = 15 + min(10, 0) = 25
     second=10, first=25
i=2: curr=20, total = 20 + min(25, 10) = 30
     But output is min of paths...

Actually starting from index 1:
first=cost[0]=10
i=1: curr=cost[1]=15, total=15+min(10,0)=15, second=10, first=15
i=2: curr=cost[2]=20 (or 0 for top), total=0+min(15,10)=10, second=15, first=15

Result: 15 (start at step 1)
```

## Edge Cases
1. **Two steps only:** Return min(cost[0], cost[1])
2. **Single step:** Return that cost
3. **All same cost:** Return min path sum
4. **Increasing costs:** Later steps might be skipped
5. **Decreasing costs:** Take cheaper later steps

## Related Problems
- **LeetCode 70:** Climbing Stairs
- **LeetCode 139:** Word Break
- **LeetCode 198:** House Robber
- **LeetCode 213:** House Robber II

## Tags
`Dynamic Programming` `Greedy` `Array`
