package dp.assignment.wordbreak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		String[][] brokenWords=new String[word.length()+1][word.length()+1];
		wordBreakHelperRecursive(word,dictionary,brokenWords);
		
		return brokenWords[word.length()];
	}

	private static void wordBreakHelperRecursive(String word, String[] dictionary, String[][] brokenWords) {
		for(int i=0; i<word.length();i++){
			
			if(isInDictionary(word.substring(0, i),dictionary)){
				if(brokenWords[i]==null){
					brokenWords[i]=new String[word.length()];
					
				}
			}
		}
		
	}

	private static boolean isInDictionary(String word, String[] dictionary) {
		// TODO Auto-generated method stub
		return false;
	}
}
