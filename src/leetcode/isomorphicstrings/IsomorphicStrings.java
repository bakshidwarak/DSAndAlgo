package leetcode.isomorphicstrings;

import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/isomorphic-strings/?tab=Description Given two
 * strings s and t, determine if they are isomorphic.
 * 
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * 
 * All occurrences of a character must be replaced with another character while
 * preserving the order of characters. No two characters may map to the same
 * character but a character may map to itself.
 * 
 * For example, Given "egg", "add", return true.
 * 
 * Given "foo", "bar", return false.
 * 
 * Given "paper", "title", return true.
 * 
 * Note: You may assume both s and t have the same length.
 * 
 * @author dwaraknathbs
 *
 */
public class IsomorphicStrings {

	public static void main(String[] args) {
		System.out.println(isIsomorphic("ab", "aa"));
		System.out.println(isIsomorphic("foo", "bar"));
		System.out.println(isIsomorphic("paper", "title"));

	}

	private static boolean isIsomorphic(String s, String t) {
		
		int[] sTot = new int[256];
		Arrays.fill(sTot,0);
		int[] tToS = new int[256];		
		Arrays.fill(tToS,0);
		char[] first = s.toCharArray();
		char[] second = t.toCharArray();

		for (int i = 0; i < s.length(); i++) {
			
			if (sTot[second[i]]!=0 && sTot[second[i]] != first[i])
				return false;
			if(tToS[first[i]] !=0 && tToS[first[i]]!=second[i])
				return false;
			sTot[second[i]]=first[i];
			tToS[first[i]]= second[i];

		}
		return true;
	}

}
