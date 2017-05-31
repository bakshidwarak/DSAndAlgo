package strings.assignment;

import java.util.HashMap;

public class LongestRepeatingSubstring {

	static class Trie {
		Trie[] nodes = new Trie[26];// Assuming lower case English letters
		boolean isWord;
		boolean hasChildren;

		public Trie addNode(char ch) {
			if (nodes[ch - 'a'] == null) {
				nodes[ch - 'a'] = new Trie();
			}
			return nodes[ch - 'a'];

		}
	}

	public static void main(String[] args) {
		System.out.println(LRSUsingMap("geeksforgeeks"));

	}

	static String LRSUsingMap(String iString) {

		HashMap<String, Integer> substrCnt = new HashMap<>();
		for (int i = 0; i < iString.length(); i++) {
			for (int j = i + 1; j <= iString.length(); j++) {
				String str = iString.substring(i, j);
				int count = 1;
				if (substrCnt.containsKey(str)) {
					count = substrCnt.get(str);
					count++;
				}
				substrCnt.put(str, count);
			}
		}

		String mxString = "";
		int maxCount = 0;
		int maxLength = 0;
		for (java.util.Map.Entry<String, Integer> entry : substrCnt.entrySet()) {
			int curr = (Integer) entry.getValue();
			String currWord = (String) entry.getKey();
			if (curr > 1 && currWord.length() > maxLength) {
				maxCount = curr;
				mxString = currWord;
				maxLength = currWord.length();
			} else if (curr > maxCount && currWord.length() == maxLength) {
				maxCount = curr;
				mxString = currWord;
				maxLength = currWord.length();
			}
		}

		return mxString;

	}

	static String LRS(String input) {

		Trie node= new Trie();
		for(int i=input.length()-1; i>=0; i--){
			Trie temp=node;
			String currentSuffix=input.substring(i);
			for(int j=0; j<currentSuffix.length();j++){
				temp=temp.addNode(currentSuffix.charAt(j));
			}
			temp.isWord=true;
		}
		

		return mxString;

	}

}
