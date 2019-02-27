package leetcode.minimumcosttohirekworkers;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 857. Minimum Cost to Hire K Workers Hard
 * 
 * 418
 * 
 * 39
 * 
 * Favorite
 * 
 * Share There are N workers. The i-th worker has a quality[i] and a minimum
 * wage expectation wage[i].
 * 
 * Now we want to hire exactly K workers to form a paid group. When hiring a
 * group of K workers, we must pay them according to the following rules:
 * 
 * Every worker in the paid group should be paid in the ratio of their quality
 * compared to other workers in the paid group. Every worker in the paid group
 * must be paid at least their minimum wage expectation. Return the least amount
 * of money needed to form a paid group satisfying the above conditions.
 * 
 * 
 * <pre>
Example 1:

Input: quality = [10,20,5], wage = [70,50,30], K = 2
Output: 105.00000
Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
Example 2:

Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
Output: 30.66667
Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.
 * </pre>
 * 
 * Note:
 * 
 * 1 <= K <= N <= 10000, where N = quality.length = wage.length 1 <= quality[i]
 * <= 10000 1 <= wage[i] <= 10000 Answers within 10^-5 of the correct answer
 * will be considered correct.
 * 
 * @author dwaraknathbs
 *
 */
public class MinimumCostToHireKWorkers {
	static class Worker implements Comparable<Worker> {
		int quality;
		int wage;

		public Worker(int quality, int wage) {
			this.quality = quality;
			this.wage = wage;
		}

		public double ratio() {
			return (double) wage / quality;
		}

		public int compareTo(Worker next) {
			return Double.compare(this.ratio(), next.ratio());
		}
	}

	public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
		Worker[] workers = new Worker[quality.length];
		for (int i = 0; i < quality.length; i++) {
			workers[i] = new Worker(quality[i], wage[i]);
		}

		Arrays.sort(workers);
		/**
		 * Max Heap for Quality
		 */
		PriorityQueue<Integer> qualityPool = new PriorityQueue<>((a1, a2) -> -1 * Integer.compare(a1, a2));
		int currentCumulativeQuality = 0;
		double ans = Double.MAX_VALUE;
		for (Worker currentWorker : workers) {
			currentCumulativeQuality += currentWorker.quality;
			qualityPool.offer(currentWorker.quality);
			if (qualityPool.size() > K) {
				currentCumulativeQuality -= qualityPool.poll();
			}
			if (qualityPool.size() == K) {
				/**
				 * Key idea to price calculation is if we pick the wage ratio of
				 * current worker, we multiply the ratio with the cumulative
				 * quality in the group
				 */
				ans = Math.min(ans, currentCumulativeQuality * currentWorker.ratio());
			}
		}
		return ans;

	}

}
