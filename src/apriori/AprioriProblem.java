package apriori;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AprioriProblem {

	// This is the text editor interface.
	// Anything you type or change here will be seen by the other person in real
	// time.

	// https://bluejeans.com/7211488154

	/*
	 * 
	 * Apriori Problem (~45 min)
	 * 
	 * Given a list of transactions, How can we calculate the frequency counts
	 * of all possible item-sets?
	 * 
	 * For example,
	 * 
	 * [INPUT] list of transactions | -- | -----------------------------| | ID |
	 * Purchased items | | -- | -----------------------------| | 1 | apple,
	 * banana, lemon | | 2 | banana, berry, lemon, orange | | 3 | banana, berry,
	 * lemon | | -- | -----------------------------|
	 * 
	 * 
	 * [OUTPUT] frequency counts of all possible item-sets. Note: some outputs
	 * are omitted for brevity. | ---------------------------- | --------- | |
	 * Itemset | Frequency | | ---------------------------- | --------- | |
	 * apple, banana | 1 | | apple, lemon | 1 | | banana, berry | 2 | | banana,
	 * lemon | 3 | | ... | | apple, banana, lemon | 1 | | banana, berry, lemon |
	 * 2 | | ... | | banana, berry, lemon, orange | 1 | | ... | |
	 * ---------------------------- | --------- |
	 * 
	 */

	public static void main(String[] args) {

		List<List<String>> transactions = new ArrayList<>();

		List<String> items = new ArrayList<>();
		items.add("apple");
		items.add("banana");
		items.add("lemon");
		transactions.add(items);
		items = new ArrayList<>();
		items.add("banana");
		items.add("berry");
		items.add("lemon");
		items.add("orange");
		transactions.add(items);

		items = new ArrayList<>();
		items.add("banana");
		items.add("berry");
		items.add("lemon");
		transactions.add(items);

		HashMap<String, Integer> frequencyTable = getFrequencyCount(transactions);

		frequencyTable.entrySet().stream().map(entry -> entry.getKey() + " --> " + entry.getValue())
				.forEach(System.out::println);
	}

	static HashMap<String, Integer> getFrequencyCount(List<List<String>> transactions) {

		HashMap<String, Integer> frequencyMap = new HashMap<>();
		// iterate the transactions
		for (List<String> transaction : transactions) {
			Set<String> itemCombinations = getSubset(transaction);
			for (String itemSet : itemCombinations) {
				int count = 1;
				if (frequencyMap.containsKey(itemSet)) {
					count = frequencyMap.get(itemSet);
					count++;

				}
				frequencyMap.put(itemSet, count);
			}
		}

		return frequencyMap;
	}

	public static Set<String> getSubset(List<String> transaction) {

		Set<Set<String>> subsets = new HashSet<>();
		Set<String> subset = new HashSet<>();
		getSubsetHelper(transaction, 0, subsets, subset);

		return subsets.stream().map(set -> set.stream().collect(Collectors.joining(","))).collect(Collectors.toSet());

	}

	public static void getSubsetHelper(List<String> transaction, int start, Set<Set<String>> subsets,
			Set<String> subset) {

		if (start >= transaction.size()) {

			/**
			 * As we need all subsets >=2
			 */
			if (subset.size() > 1)
				subsets.add(new HashSet<>(subset));

			return;
		}

		for (int i = start; i < transaction.size(); i++) {
			subset.add(transaction.get(i));
			getSubsetHelper(transaction, i + 1, subsets, subset);
			subset.remove(transaction.get(i));
			getSubsetHelper(transaction, i + 1, subsets, subset);
			subset.remove(transaction.get(i));

		}

	}

}
