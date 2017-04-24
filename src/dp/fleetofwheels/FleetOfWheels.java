package dp.fleetofwheels;

import java.util.HashMap;

/**
 * Fleet Of Wheels
 * 
 * @author dwaraknathbs
 *
 */
public class FleetOfWheels {

	public static void main(String[] args) {
		int[] input = new int[] { 6, 1, 2 };

		int[] output = fleetOfWheels(input);

		for (int i = 0; i < output.length; i++)
			System.out.println(output[i]);

	}

	public static int[] fleetOfWheels(int[] numberOFWheels) {
		int[] fleets = new int[numberOFWheels.length];
		for (int i = 0; i < numberOFWheels.length; i++) {
			fleets[i] = getNumberOfFleets(numberOFWheels[i]);
		}

		return fleets;
	}

	private static int getNumberOfFleets(int totalWheels) {

		HashMap<String, Integer> cache = new HashMap<>();

		int[] wheelCount = new int[2];
		wheelCount[0] = 2;
		wheelCount[1] = 4;
		return getNumberOfWheelsHelper(totalWheels, 0, wheelCount, cache);
	}

	/**
	 * DP with memoization. Total Number of fleets with total wheels n= Total
	 * number of wheels in fleet given that a 4 wheeler is picked + total number
	 * of wheels with the rest
	 * 
	 * @param totalWheels
	 * @param index
	 * @param wheelCount
	 * @param cache
	 * @return
	 */
	private static int getNumberOfWheelsHelper(int totalWheels, int index, int[] wheelCount,
			HashMap<String, Integer> cache) {

		if (totalWheels == 0)
			cache.put(String.valueOf(totalWheels) + "_" + String.valueOf(index), 1);
		else if (!cache.containsKey(String.valueOf(totalWheels) + "_" + String.valueOf(index))) {
			int ways = 0;
			int wheelsLeft = totalWheels;

			while (wheelsLeft > 0) {
				wheelsLeft = wheelsLeft - wheelCount[index];
				ways += getNumberOfWheelsHelper(wheelsLeft, index + 1, wheelCount, cache);
			}
			cache.put(String.valueOf(totalWheels) + "_" + String.valueOf(index), ways);
		}

		return cache.get(String.valueOf(totalWheels) + "_" + String.valueOf(index));
	}

}
