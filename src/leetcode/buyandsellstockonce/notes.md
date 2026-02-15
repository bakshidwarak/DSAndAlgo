# 121. Best Time to Buy and Sell Stock

## Problem Statement
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

### Examples
```
Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5
             Not 7-1 = 6, as selling price needs to be after buying price

Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0
Explanation: No transaction is done, i.e. max profit = 0
```

### Constraints
- 1 <= prices.length <= 10^5
- 0 <= prices[i] <= 10^4
- Can make at most one transaction

## Approach & Solution

### Key Insights
1. **Track minimum price**: Keep track of the lowest price seen so far
2. **Calculate potential profit**: At each price, calculate profit if we sell now
3. **Update maximum profit**: Keep track of the best profit seen
4. **Update buying price**: When profit becomes negative, we found a new potential buying point

### Algorithm Steps
1. Initialize maxProfit = 0, buyingPrice = first price, profitSoFar = 0
2. Iterate through prices starting from index 1:
   - Calculate profit = current price - buyingPrice
   - If profit < 0 (current price lower than buying price):
     - Update maxProfit with profitSoFar
     - Update buyingPrice to current price (new valley)
   - Else (profit >= 0):
     - Update profitSoFar if current profit is better
3. After loop, update maxProfit one final time with profitSoFar
4. Return maxProfit

### Complexity Analysis
- **Time Complexity**: O(n)
  - Single pass through the array
  - Each price examined once
- **Space Complexity**: O(1)
  - Only uses a few variables
  - No additional data structures

### Visualization
```
Input: [7, 1, 5, 3, 6, 4]

Price Graph:
  7 ●
  6       ●         ● ↑ sell here
  5         ●     ↗
  4                   ●
  3             ●
  2
  1     ● ↓ buy here
  0 ────────────────────────
    0  1  2  3  4  5  6

Process:
i=0: buyingPrice=7, maxProfit=0, profitSoFar=0

i=1: price=1
     profit = 1-7 = -6 (negative!)
     maxProfit = max(0, 0) = 0
     buyingPrice = 1 (new low)

i=2: price=5
     profit = 5-1 = 4 (positive)
     profitSoFar = 4

i=3: price=3
     profit = 3-1 = 2 (less than profitSoFar)
     profitSoFar stays 4

i=4: price=6
     profit = 6-1 = 5 (better!)
     profitSoFar = 5

i=5: price=4
     profit = 4-1 = 3 (less than profitSoFar)
     profitSoFar stays 5

Final: maxProfit = max(0, 5) = 5
```

## Code Walkthrough

```java
public int maxProfit(int[] prices) {
    if (prices.length == 0)
        return 0;

    int maxProfit = 0;
    int buyingPrice = prices[0];  // Initial buying price
    int profitSoFar = 0;

    for (int i = 1; i < prices.length; i++) {
        int profit = prices[i] - buyingPrice;

        if (profit < 0) {
            // Found a new lower price, potential new buying point
            maxProfit = Math.max(maxProfit, profitSoFar);
            buyingPrice = prices[i];
            // Note: profitSoFar is NOT reset here in original code
        } else {
            // Positive profit, update if better than before
            if (profit > profitSoFar)
                profitSoFar = profit;
        }
    }

    // Final check for maximum profit
    maxProfit = Math.max(maxProfit, profitSoFar);
    return maxProfit;
}
```

**Cleaner Alternative:**
```java
public int maxProfit(int[] prices) {
    int minPrice = Integer.MAX_VALUE;
    int maxProfit = 0;

    for (int price : prices) {
        if (price < minPrice) {
            minPrice = price;
        } else {
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
    }

    return maxProfit;
}
```

**Key Differences:**
- Alternative is cleaner and more intuitive
- Tracks absolute minimum price seen so far
- Updates maxProfit continuously

## Edge Cases
- **Empty array**: [] → 0 (handled by check)
- **Single element**: [5] → 0 (no transaction possible)
- **Monotonically decreasing**: [7,6,5,4,3,2,1] → 0 (no profit)
- **Monotonically increasing**: [1,2,3,4,5] → 4 (buy first, sell last)
- **All same price**: [3,3,3,3] → 0 (no profit)
- **Two elements**: [2,1] → 0, [1,2] → 1
- **Valley then peak**: [7,1,5] → 4 (buy at valley)

## Related Problems
- [**122. Best Time to Buy and Sell Stock II**](../besttimetobuyandsellstockII/notes.md): Multiple transactions allowed
- **123. Best Time to Buy and Sell Stock III**: At most two transactions
- **188. Best Time to Buy and Sell Stock IV**: At most k transactions
- **309. Best Time to Buy and Sell Stock with Cooldown**: With cooldown period
- **714. Best Time to Buy and Sell Stock with Transaction Fee**: With transaction fee

## Tags
`array` `dynamic-programming` `greedy` `easy`
