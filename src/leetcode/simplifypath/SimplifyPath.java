package leetcode.simplifypath;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 * 
 * For example, path = "/home/", => "/home" path = "/a/./b/../../c/", => "/c"
 * click to show corner cases.
 * 
 * Corner Cases: Did you consider the case where path = "/../"? In this case,
 * you should return "/". Another corner case is the path might contain multiple
 * slashes '/' together, such as "/home//foo/". In this case, you should ignore
 * redundant slashes and return "/home/foo". Show Company Tags Show Tags

 * 
 * 
 * @author dwaraknathbs
 *
 */
public class SimplifyPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String simplifyPath(String path) {
		Deque<String> deque = new LinkedList<String>();
		String[] pathParams = path.split("/");

		for (String str : pathParams) {
			if (str.equals(".") || str.equals("/") || str.trim().equals(""))
				continue;

			if (str.equals("..")) {
				if (deque.size() == 0)
					continue;
				;

				deque.removeLast();
				continue;
			}

			deque.addLast(str);
		}
		if (deque.isEmpty())
			return "/";
		StringBuilder sb = new StringBuilder();

		while (!deque.isEmpty()) {
			sb.append("/");
			sb.append(deque.removeFirst());
		}

		return sb.toString();
	}

}
