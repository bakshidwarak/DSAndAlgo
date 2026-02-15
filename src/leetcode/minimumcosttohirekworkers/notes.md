# LeetCode 857: Minimum Cost to Hire K Workers

## Problem Statement
There are N workers with qualities and wage expectations. Hire exactly K workers following rules:
1. All workers paid proportionally to their quality relative to each other
2. All workers must earn at least their minimum wage expectation
Return the **minimum total cost** to form such a group.

## Difficulty
Hard

## Examples

### Example 1
- **Input:** `quality = [10,20,5]`, `wage = [70,50,30]`, K=2
- **Output:** `105.00`
- **Explanation:** Pay worker 0 $70 and worker 2 $35 (ratio 7:1.4 ≈ 5:1)

### Example 2
- **Input:** `quality = [3,1,10,10,1]`, `wage = [4,8,2,2,7]`, K=3
- **Output:** `30.67`
- **Explanation:** Hire workers with proper wage ratios

## Key Insights
1. **Wage-Quality Ratio**: Key insight is that payment_to_worker_i = ratio * quality_i
2. **Sort by Ratio**: Sort workers by wage/quality ratio
3. **Greedy Selection**: For each ratio, select K workers with lowest quality sum
4. **Max Heap for Quality**: Use max heap to track and remove highest quality workers
5. **Optimal Ratio**: The highest-ratio worker in group determines the ratio for all

## Algorithm Steps
1. Create Worker class with quality, wage, and ratio
2. Sort workers by wage/quality ratio
3. Use max heap to track quality values
4. For each worker (as the ratio-limiting worker):
   - Add current quality to heap
   - Keep only K workers (remove highest quality if > K)
   - If exactly K workers: calculate cost = sum_quality * current_ratio
5. Return minimum cost found

## Complexity Analysis
- **Time Complexity:** O(n log n) - Sorting + heap operations
- **Space Complexity:** O(n) - Worker array and heap

## ASCII Visualization

```
Input: quality=[10,20,5], wage=[70,50,30], K=2

Worker 0: ratio=70/10=7.0, quality=10
Worker 1: ratio=50/20=2.5, quality=20
Worker 2: ratio=30/5=6.0, quality=5

Sort by ratio:
Worker 1: ratio=2.5, quality=20
Worker 2: ratio=6.0, quality=5
Worker 0: ratio=7.0, quality=10

Process:
i=0 (ratio=2.5, quality=20): heap=[20], size=1
i=1 (ratio=6.0, quality=5):  heap=[20,5], size=2, cost=25*6.0=150
i=2 (ratio=7.0, quality=10): heap=[20,10] (remove 20 if needed)
     After heap: [10,5], cost=(10+5)*7=105

Minimum: 105
```

## Edge Cases
1. **K equals N:** Hire everyone
2. **All same ratio:** Choose K workers with lowest quality sum
3. **K = 1:** Just pick worker with lowest wage
4. **All same quality:** Sort by wage and pick K cheapest
5. **Large wage/quality differences:** Ratio becomes critical

## Related Problems
- **LeetCode 253:** Meeting Rooms II
- **LeetCode 1383:** Maximum Performance of a Team
- **LeetCode 1526:** Minimum Number of Taps to Open to Water a Garden

## Tags
`Greedy` `Heap` `Sorting` `Ratio Optimization`
