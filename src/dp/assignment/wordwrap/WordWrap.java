package dp.assignment.wordwrap;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Word Wrap Given a sequence of words, and a limit on the number of characters
 * that can be put in one line (line width). Put line breaks in the given
 * sequence such that the lines are printed neatly. Assume that the length of
 * each word is smaller than the line width.
 * 
 * The word processors like MS Word do task of placing line breaks. The idea is
 * to have balanced lines. In other words, not have few lines with lots of extra
 * spaces and some lines with small amount of extra spaces.
 * 
 * The extra spaces includes spaces put at the end of every line except the last
 * one. The problem is to minimize the following total cost. Cost of a line =
 * (Number of extra spaces in the line)^3 Total Cost = Sum of costs for all
 * lines
 * 
 * For example, consider the following string and line width M = 15 "Geeks for
 * Geeks presents word wrap problem"
 * 
 * Following is the optimized arrangement of words in 3 lines Geeks for Geeks
 * presents word wrap problem
 * 
 * The total extra spaces in line 1, line 2 and line 3 are 0, 2 and 3
 * respectively. So optimal value of total cost is 0 + 2*2 + 3*3 = 13 Please
 * note that the total cost function is not sum of extra spaces, but sum of
 * cubes (or square is also used) of extra spaces. The idea behind this cost
 * function is to balance the spaces among lines. For example, consider the
 * following two arrangement of same set of words:
 * 
 * 1) There are 3 lines. One line has 3 extra spaces and all other lines have 0
 * extra spaces. Total extra spaces = 3 + 0 + 0 = 3. Total cost = 3*3*3 + 0*0*0
 * + 0*0*0 = 27.
 * 
 * 2) There are 3 lines. Each of the 3 lines has one extra space. Total extra
 * spaces = 1 + 1 + 1 = 3. Total cost = 1*1*1 + 1*1*1 + 1*1*1 = 3. Total extra
 * spaces are 3 in both scenarios, but second arrangement should be preferred
 * because extra spaces are balanced in all three lines. The cost function with
 * cubic sum serves the purpose because the value of total cost in second
 * scenario is less.
 * 
 * Solution: http://www.geeksforgeeks.org/dynamic-programming-set-18-word-wrap/
 * 
 * (Slightly different variation:
 * https://leetcode.com/problems/text-justification/)
 * 
 * @author dwaraknathbs
 *
 */
public class WordWrap {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		int res;

		int _strArr_size = 0;
		_strArr_size = Integer.parseInt(in.nextLine().trim());
		String[] _strArr = new String[_strArr_size];
		String _strArr_item;
		for (int _strArr_i = 0; _strArr_i < _strArr_size; _strArr_i++) {
			try {
				_strArr_item = in.nextLine();
			} catch (Exception e) {
				_strArr_item = null;
			}
			_strArr[_strArr_i] = _strArr_item;
		}

		int _lineWidth;
		_lineWidth = Integer.parseInt(in.nextLine().trim());

		res = minSpaceCost(_strArr, _lineWidth);
		System.out.println(String.valueOf(res));

	}

	static int minSpaceCost(String[] words, int lineWidth) {
		int[] minCosts = new int[words.length];
		Arrays.fill(minCosts, Integer.MAX_VALUE);
		
		for (int p = words.length - 1; p >= 0; p--) {
			for (int i = p ; i <words.length; i++) {
				int cost = getCost(i, p, words, lineWidth);
				if (p + 1 < words.length && cost!=Integer.MAX_VALUE)
					minCosts[p] = Math.min(minCosts[p], cost + minCosts[p+1]);
				else
					minCosts[p] = Math.min(minCosts[p], cost);
			}
		}

		return minCosts[0];
	}

	private static int getCost(int i, int p, String[] words, int lineWidth) {
		int wordLength=0;
		for(int l=i; l>=p;l--){
			wordLength+=words[l].length();
		}
		
		if(lineWidth-wordLength<0) return Integer.MAX_VALUE;
		return (int) Math.pow(((lineWidth-wordLength)+(i-p)),3);
	}

}
