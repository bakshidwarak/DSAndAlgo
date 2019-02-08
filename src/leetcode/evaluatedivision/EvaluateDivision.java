package leetcode.evaluatedivision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 399. Evaluate Division Medium
 * 
 * 990
 * 
 * 82
 * 
 * Favorite
 * 
 * Share Equations are given in the format A / B = k, where A and B are
 * variables represented as strings, and k is a real number (floating point
 * number). Given some queries, return the answers. If the answer does not
 * exist, return -1.0.
 * 
 * Example: Given a / b = 2.0, b / c = 3.0. queries are: a / c = ?, b / a = ?, a
 * / e = ?, a / a = ?, x / x = ? . return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 * 
 * The input is: vector<pair<string, string>> equations, vector<double>& values,
 * vector<pair<string, string>> queries , where equations.size() ==
 * values.size(), and the values are positive. This represents the equations.
 * Return vector<double>.
 * 
 * According to the example above:
 * 
 * equations = [ ["a", "b"], ["b", "c"] ], values = [2.0, 3.0], queries = [
 * ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. The input is
 * always valid. You may assume that evaluating the queries will result in no
 * division by zero and there is no contradiction.
 * 
 * @author dwaraknathbs
 *
 */
public class EvaluateDivision {
	static class Neighbour {
		String node;
		double value;

		public Neighbour(String node, double val) {
			this.node = node;
			this.value = val;
		}
	}

	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
		Map<String, List<Neighbour>> graph = new HashMap<>();
		for (int i = 0; i < equations.length; i++) {
			String[] eq = equations[i];
			graph.putIfAbsent(eq[0], new ArrayList<>());
			graph.putIfAbsent(eq[1], new ArrayList<>());
			graph.get(eq[0]).add(new Neighbour(eq[1], values[i]));
			graph.get(eq[1]).add(new Neighbour(eq[0], 1 / values[i]));
		}
		double[] result = new double[queries.length];
		for (int i = 0; i < queries.length; i++) {
			String[] query = queries[i];
			if (!graph.containsKey(query[0]) || !graph.containsKey(query[1])) {
				result[i] = -1.0;
			} else {
				result[i] = dfs(query[0], query[1], 1.0, new HashSet<>(), graph);
			}

		}
		return result;
	}

	public double dfs(String start, String end, double result, Set<String> set, Map<String, List<Neighbour>> graph) {
		if (set.contains(start))
			return -1.0;
		if (start.equals(end))
			return result;
		double val = -1.0;
		set.add(start);
		List<Neighbour> neighbours = graph.get(start);
		for (Neighbour n : neighbours) {
			val = dfs(n.node, end, n.value * result, set, graph);
			if (val != -1.0)
				break;
		}
		set.remove(start);
		return val;

	}

}
