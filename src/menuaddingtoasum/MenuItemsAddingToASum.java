package menuaddingtoasum;

import java.io.*;
import java.util.*;
import java.util.stream.*;

/*
 * https://xkcd.com/287/
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class MenuItemsAddingToASum {

	static class MenuItem {

		String description;
		int priceInCents;

		public MenuItem(String desc, int priceInCents) {
			this.description = desc;
			this.priceInCents = priceInCents;
		}
	}

	public static void main(String[] args) {

		List<MenuItem> menuItems = new ArrayList<>();
		// subset_sum([3,9,8,4,5,7,10],15)
		menuItems.add(new MenuItem("3", 3));
		menuItems.add(new MenuItem("9", 9));
		menuItems.add(new MenuItem("8", 8));
		menuItems.add(new MenuItem("4", 4));
		menuItems.add(new MenuItem("5", 5));
		menuItems.add(new MenuItem("7", 7));
		menuItems.add(new MenuItem("10", 10));
		Set<String> result = getItemsAddingTo(menuItems, 15);

		for (String item : result) {

			System.out.println(item);

		}

		// 215+355+355+580

	}

	static Set<String> getItemsAddingTo(List<MenuItem> menuItems, int targetPrice) {

		Set<String> result = new HashSet<>();
		List<MenuItem> current = new ArrayList<>();

		getItemsAddedHelper(menuItems, targetPrice, 0, result, current);

		return result;

	}

	static void getItemsAddedHelper(List<MenuItem> menuItems, int targetPrice, int index, Set<String> result,
			List<MenuItem> current) {

		// Define base condition

		if (targetPrice == 0) {

			result.add(current.stream().map(menuItem -> menuItem.description).collect(Collectors.joining(",")));
			return;
		}

		if (targetPrice < 0) {
			return;
		}

		for (int i = index; i < menuItems.size(); i++) {

			int currentPrice = menuItems.get(i).priceInCents;

			current.add(menuItems.get(i));
			getItemsAddedHelper(menuItems, targetPrice - currentPrice, index + 1, result, current);
			current.remove(current.size() - 1);
			getItemsAddedHelper(menuItems, targetPrice, index + 1, result, current);

		}

	}

}

/**
 * 
 * 215, 275,335,355,420,580
 * 
 * 
 * 
 * f(1505) = currPrice+ f(1505-currPrice) i j
 * 
 */
