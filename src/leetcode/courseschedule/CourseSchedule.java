package leetcode.courseschedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 207. Course Schedule
 * 
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to
 * first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it
 * possible for you to finish all courses?
 * 
 * For example:
 * 
 * 2, [[1,0]] There are a total of 2 courses to take. To take course 1 you
 * should have finished course 0. So it is possible.
 * 
 * 2, [[1,0],[0,1]] There are a total of 2 courses to take. To take course 1 you
 * should have finished course 0, and to take course 0 you should also have
 * finished course 1. So it is impossible.
 * 
 * @author dwaraknathbs
 *
 */
public class CourseSchedule {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	static class Graph {
		int course;
		List<Graph> prereq = new ArrayList<>();

		public Graph(int course) {
			this.course = course;
		}

		public boolean equals(Graph g) {
			return this.course == g.course;
		}

		public int hashcode() {
			return course;
		}
	}

	/**
	 * Basic idea is, if I construct a graph of the course and the prereq, the
	 * course cannot be completed if the resulting graph is cyclic
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		Map<Integer, Graph> nodes = new HashMap<>();
		for (int i = 0; i < numCourses; i++) {
			nodes.put(i, new Graph(i));
		}

		for (int i = 0; i < prerequisites.length; i++) {
			int course = prerequisites[i][0];
			int pre = prerequisites[i][1];

			nodes.get(course).prereq.add(nodes.get(pre));
		}

		return hasCycle(nodes);
	}

	public boolean hasCycle(Map<Integer, Graph> nodes) {
		Set<Graph> visited = new HashSet<>();
		Set<Graph> backEdge = new HashSet<>();

		for (Graph g : nodes.values()) {
			if (!visited.contains(g)) {
				if (isCyclic(g, visited, backEdge)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isCyclic(Graph node, Set<Graph> visited, Set<Graph> backEdge) {
		if (visited.contains(node) && backEdge.contains(node))
			return true;

		if (backEdge.contains(node)) {
			return true;
		}

		if (!visited.contains(node)) {
			visited.add(node);
			backEdge.add(node);

			for (Graph pre : node.prereq) {
				if (isCyclic(pre, visited, backEdge)) {
					return true;
				}
			}

			backEdge.remove(node);
		}

		return false;
	}

}
