# 122. Best Time to Buy and Sell Stock II

## Problem Statement
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

### Examples
```
Example 1:
Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3
             Total profit = 4 + 3 = 7

Example 2:
Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4
             Note: You cannot buy on day 1, buy on day 2 and sell them later,
             as you are engaging in multiple transactions at the same time.

Example 3:
Input: [7,6,4,3,1]
Output: 0
Explanation: No transactions are done, max profit = 0
```

### Constraints
- 1 <= prices.length <= 3 * 10^4
- 0 <= prices[i] <= 10^4
- Can make unlimited transactions
- Must sell before buying again

## Approach & Solution

### Key Insights
1. **Capture all upward movements**: Every price increase is a profit opportunity
2. **Valley-peak approach**: Buy at local minimum (valley), sell at local maximum (peak)
3. **Greedy strategy**: Can accumulate all positive differences
4. **No holding required**: Can sell and immediately buy back to capture next uptrend

### Algorithm Steps
1. Initialize total profit to 0 and low to first price
2. Iterate through prices starting from index 1
3. Find ascending sequences (prices[i] > prices[i-1])
4. When ascending sequence ends:
   - Add (peak price - low price) to total profit
   - Update low to current price (new valley)
5. Continue until all prices processed
6. Return total profit

### Complexity Analysis
- **Time Complexity**: O(n)
  - Single pass through the array
  - Each price is examined once
- **Space Complexity**: O(1)
  - Only uses a few variables (totalProfit, low, i)
  - No additional data structures

### Visualization
```
Input: [7, 1, 5, 3, 6, 4]

Price Graph:
  7
    ↘
  6         ↗     ↗
  5       ↗     ↗   ↘
  4                   ↘
  3           ↗
  2
  1     ↗
  0 ──────────────────────────
    0  1  2  3  4  5  6

Buy/Sell Points:
Day 0: 7 (skip, price going down)
Day 1: 1 (BUY - valley)
Day 2: 5 (ascending continues)
Day 3: 3 (SELL at previous day=5, profit=4, BUY new low=3)
Day 4: 6 (ascending continues)
Day 5: 4 (SELL at previous day=6, profit=3)

Total: 4 + 3 = 7

Alternative Interpretation (Greedy):
Capture every positive difference:
1→5: +4
3→6: +3
Total: 7
```

## Code Walkthrough

```java
public int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0)
        return 0;

    int totalProfit = 0;
    int low = prices[0];  // Initial buying price

    for (int i = 1; i < prices.length; i++) {
        // Find ascending sequence
        while (i < prices.length && prices[i] > prices[i - 1]) {
            i++;
        }

        // Sell at peak (prices[i-1] is last ascending price)
        totalProfit += prices[i - 1] - low;

        // Update low to current valley (if not at end)
        if (i < prices.length) {
            low = prices[i];
        }
    }

    return totalProfit;
}
```

**Simpler Alternative (Greedy Approach):**
```java
public int maxProfit(int[] prices) {
    int profit = 0;
    for (int i = 1; i < prices.length; i++) {
        // Add all positive differences
        if (prices[i] > prices[i-1]) {
            profit += prices[i] - prices[i-1];
        }
    }
    return profit;
}
```

**Why the simpler approach works:**
- Buying at valley and selling at peak = sum of all upward steps
- Example: Valley=1, Peak=5: profit = (5-1) = 4
- Same as: (2-1) + (3-2) + (4-3) + (5-4) = 1+1+1+1 = 4
- Summing consecutive differences gives same result

## Edge Cases
- **Single day**: [5] → 0 (cannot buy and sell same day)
- **Monotonically increasing**: [1,2,3,4,5] → 4 (buy day 1, sell day 5)
- **Monotonically decreasing**: [5,4,3,2,1] → 0 (no profitable transactions)
- **All same price**: [3,3,3,3] → 0 (no profit opportunities)
- **Empty array**: [] → 0
- **Two elements ascending**: [1,5] → 4
- **Two elements descending**: [5,1] → 0
- **Multiple peaks**: Correctly identifies all profit opportunities

## Related Problems
- [**121. Best Time to Buy and Sell Stock**](../buyandsellstockonce/notes.md): Can only make one transaction
- **123. Best Time to Buy and Sell Stock III**: At most two transactions
- **188. Best Time to Buy and Sell Stock IV**: At most k transactions
- **309. Best Time to Buy and Sell Stock with Cooldown**: Must wait one day after selling
- **714. Best Time to Buy and Sell Stock with Transaction Fee**: Pay fee per transaction

## Tags
`array` `greedy` `dynamic-programming` `easy`
