package dp.assignment.coinchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.sun.management.MissionControlMXBean;

public class CoinChange {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numberofCoins = scanner.nextInt();
		int[] coins = new int[numberofCoins];
		for (int i = 0; i < numberofCoins; i++)
			coins[i] = scanner.nextInt();

		System.out.println("Total currency value");
		int currency = scanner.nextInt();

		System.out.println(getMinCoinsCount(coins, currency));

		System.out.println(" Using cache");

		int[] cache = new int[currency + 1];
		Arrays.fill(cache, -1);
		System.out.println(getMinCoinsCache(coins, currency, cache));
		Arrays.fill(cache, -1);
		int[] waysCache = new int[currency + 1];

		getMinCoinsCache(coins, currency, cache, waysCache);
		System.out.println("Printing cache");
		print(waysCache, currency);
		Arrays.fill(cache, -1);
		List<Integer>[] ways=new List[currency+1];
		getMinCoinsCacheIterative(coins,currency,cache,ways);
		ArrayList<Integer> result= new ArrayList<>();
		print(ways,currency,result);
		System.out.println("Printing cache");
		result.forEach(System.out::print);
		scanner.close();

	}

	private static void print(List<Integer>[] ways, int currency,ArrayList<Integer> result) {
		if(currency==0) return;
		List<Integer> currValue=ways[currency];
		
		for(Integer inValue:currValue){
			result.add(inValue);
			print(ways,currency-inValue,result);
			result.remove((result.size()-1));
		}
		System.out.println();
		
	}

	private static void print(int[] waysCache, int currency) {
		if (currency == 0)
			return;
		System.out.println(waysCache[currency]);
		print(waysCache, currency - waysCache[currency]);

	}

	private static Integer getMinCoinsCount(int[] coins, int currency) {
		System.out.println("currency" + currency);

		Integer minCurrency = null;

		for (int i = 0; i < coins.length; i++) {
			Integer current = null;
			if (currency - coins[i] == 0)
				return 1;
			if (currency - coins[i] > 0) {
				Integer subProblemAnswer = getMinCoinsCount(coins, currency - coins[i]);

				if (subProblemAnswer != null) {
					current = 1 + subProblemAnswer;
				}

				minCurrency = minCurrency != null ? Math.min(current, minCurrency) : current;
			}
		}
		return minCurrency;

	}

	private static Integer getMinCoinsCache(int[] coins, int currency, int[] cache) {

		if (cache[currency] == -1) {
			System.out.println("currency" + currency);
			Integer minCurrency = null;

			for (int i = 0; i < coins.length; i++) {
				Integer current = null;
				if (currency - coins[i] == 0) {
					cache[currency] = 1;
				} else if (currency - coins[i] > 0) {
					Integer subProblemAnswer = getMinCoinsCache(coins, currency - coins[i], cache);

					if (subProblemAnswer != null) {
						current = 1 + subProblemAnswer;
					}

					minCurrency = minCurrency != null ? Math.min(current, minCurrency) : current;
					cache[currency] = minCurrency;
				}
			}
		}
		return cache[currency];

	}

	private static Integer getMinCoinsCache(int[] coins, int currency, int[] cache, int[] coinDenomination) {

		if (cache[currency] == -1) {
			System.out.println("currency" + currency);
			Integer minCurrency = null;

			for (int i = 0; i < coins.length; i++) {
				Integer current = null;
				if (currency - coins[i] == 0) {
					cache[currency] = 1;
					coinDenomination[currency] = coins[i];
				} else if (currency - coins[i] > 0) {
					Integer subProblemAnswer = getMinCoinsCache(coins, currency - coins[i], cache, coinDenomination);

					if (subProblemAnswer != null) {
						current = 1 + subProblemAnswer;
					}
					if (minCurrency == null) {
						minCurrency = current;
						coinDenomination[currency] = coins[i];
					}

					else if (current <= minCurrency) {
						minCurrency = current;

						coinDenomination[currency] = coins[i];
						cache[currency] = minCurrency;
					}

				}
			}
		}
		return cache[currency];

	}

	private static Integer getMinCoinsCacheIterative(int[] coins, int currency, int[] cache,
			List<Integer>[] coinDenomination) {

		if (cache[currency] == -1) {
			System.out.println("currency" + currency);
			Integer minCurrency = null;

			for (int i = 0; i < coins.length; i++) {
				Integer current = null;
				if (currency - coins[i] == 0) {
					current = 1;
					addToCache(coins, currency, coinDenomination, i);
					cache[currency] = 1;
				} else if (currency - coins[i] > 0) {
					Integer subProblemAnswer = getMinCoinsCacheIterative(coins, currency - coins[i], cache,
							coinDenomination);

					if (subProblemAnswer != null) {
						current = 1 + subProblemAnswer;
					}
					if (minCurrency == null) {
						minCurrency = current;
						addToCache(coins, currency, coinDenomination, i);
					}

					else if (current <= minCurrency) {
						minCurrency = current;

						addToCache(coins, currency, coinDenomination, i);
						cache[currency] = minCurrency;
					}

				}
				
			}
		}
		return cache[currency];

	}

	private static void addToCache(int[] coins, int currency, List<Integer>[] coinDenomination, int i) {
		if (coinDenomination[currency] == null) {
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			arrayList.add(coins[i]);
			coinDenomination[currency] = arrayList;

		}
		else
		{
			coinDenomination[currency].add(coins[i]);
		}
	}

}
