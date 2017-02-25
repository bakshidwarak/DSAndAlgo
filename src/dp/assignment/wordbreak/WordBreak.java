package dp.assignment.wordbreak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Word Break Given an input string and a dictionary of words, segment the input
 * string into a space-separated sequence of dictionary words if possible.
 * 
 * For example, if the input string is "applepie" and dictionary contains a
 * standard set of English words, then we would return the string "apple pie" as
 * output.
 * 
 * If you can find multiple arrangements, then print all such combinations.
 * 
 * 
 * Solution: http://www.programcreek.com/2014/03/leetcode-word-break-ii-java/
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class WordBreak {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		String[] res;
		String _strWord;
		try {
			_strWord = in.nextLine();
		} catch (Exception e) {
			_strWord = null;
		}

		int _strDict_size = 0;
		_strDict_size = Integer.parseInt(in.nextLine().trim());
		String[] _strDict = new String[_strDict_size];
		String _strDict_item;
		for (int _strDict_i = 0; _strDict_i < _strDict_size; _strDict_i++) {
			try {
				_strDict_item = in.nextLine();
			} catch (Exception e) {
				_strDict_item = null;
			}
			_strDict[_strDict_i] = _strDict_item;
		}

		res = wordBreak(_strWord, _strDict);
		for (int res_i = 0; res_i < res.length; res_i++) {
			System.out.println(String.valueOf(res[res_i]));

		}

	}

	private static String[] wordBreak(String word, String[] dictionary) {

		List<Integer>[] wordIndices = new List[word.length() + 1];
		int firstIndex = wordBreakHelper(word, dictionary, wordIndices);

		ArrayList<String> current = new ArrayList<>();
		ArrayList<ArrayList<String>> brokenWords = new ArrayList<>();
		dfsPrint(wordIndices, firstIndex, word, current, brokenWords);
		String[] array = new String[brokenWords.size()];
		int i = 0;
		for (List<String> list : brokenWords) {
			Collections.reverse(list);
			String string = list.stream().collect(Collectors.joining(" "));
			array[i++] = string;
		}

		Arrays.sort(array);
		return array;
	}

	private static void dfsPrint(List<Integer>[] wordIndices, int firstIndex, String word, ArrayList<String> current,
			ArrayList<ArrayList<String>> brokenWords) {
		if (firstIndex == -1) {
			ArrayList<String> currentList = new ArrayList<>(current);
			brokenWords.add(currentList);
			return;

		}
		List<Integer> firstList = wordIndices[firstIndex];
		if (firstList != null) {
			for (int first : firstList) {
				current.add(word.substring(first, firstIndex + 1));
				dfsPrint(wordIndices, first - 1, word, current, brokenWords);
				current.remove(current.size() - 1);
			}
		}

	}

	/**
	 * Basically creating a table of end index to the list of start indices of
	 * words that are present in the dictionary
	 * 
	 * Then DFS the table from the last index that has a legit word till it gets
	 * all words
	 * 
	 * @param word
	 * @param dictionary
	 * @param wordIndices
	 * @return
	 */
	private static int wordBreakHelper(String word, String[] dictionary, List<Integer>[] wordIndices) {
		int firstIndex = -1;
		int end = word.length() - 1;
		for (int i = end; i >= 0; i--)
			for (int j = i; j >= 0; j--) {
				if (isInDictionary(word.substring(j, i + 1), dictionary)) {
					if (firstIndex == -1) {
						firstIndex = i;
					}
					if (wordIndices[i] == null) {
						List<Integer> numList = new ArrayList<>();
						numList.add(j);
						wordIndices[i] = numList;

					} else {
						wordIndices[i].add(j);
					}
				}

			}

		return firstIndex;

	}

	private static boolean isInDictionary(String word, String[] dictionary) {
		return Arrays.stream(dictionary).filter(str -> str.equals(word)).findFirst().isPresent();

	}
}
