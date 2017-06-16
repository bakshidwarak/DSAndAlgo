

package strings.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Longest repeated substring Given a string, find the longest repeated
 * substring in it. Repeated is occurring more than once. It doesn't matter how
 * many times it occurs beyond 2 times. If there are multiple such strings of
 * the same size, then get any one of them If there are no repeated substrings,
 * then don't print anything (empty output) Solution:
 * http://www.geeksforgeeks.org/suffix-tree-application-3-longest-repeated-
 * substring/ [Context: This is purely an exercise in building a Suffix Tree
 * Suffix trees are difficult. You'd probably wonder if they really ask those in
 * an interview. They are in-fact, are rarely asked, which is why we don't cover
 * it in the class. But we've seen them at FB and Uber. In all occasions, it's
 * been asked as a follow up question. Once you code up an N^2 algorithm for the
 * problem on hand, there are a few minutes left, in which time, the interviewer
 * would wonder if you know of Suffix trees. It is NEVER asked to implement one
 * in an interview. That's stupid. If at that time, you do know of suffix trees,
 * then you have a chance to convert that interviewer from a 3 (good) to a 4
 * (advocate). It suggests you have taken a keen interest in your prep work and
 * by extension, in general CS. Another reason we include it in the course: It's
 * possibly one of the hardest data structures. Once you have a handle on it, a
 * lot of other things will look easy ;-) Doing difficult problems like these
 * also has a strong ancillary benefit: it helps you indirectly interview your
 * interviewer/company. You want to work for a team that challenges you; not the
 * team that gives you a free pass. i.e. Don't skimp on it. Take it head on -
 * there are clear benefits. ]
 * 
 * @author dwaraknathbs
 */
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

        public Trie addWord(String word) {
            Trie temp = this;

            for (int j = 0; j < word.length(); j++) {
                temp = temp.addNode(word.charAt(j));
            }
            temp.isWord = true;

            return temp;
        }
    }

    public static void main(String[] args) {
        System.out.println(LRS("geeksforgeeks".toLowerCase()));

    }

    /**
     * Brute force approach. Generate all the substrings and keep a count in the
     * HashMap. finally sweep the hashmap to find the one with the greatest
     * count O(n^2)
     * 
     * @param iString
     * @return
     */
    static String LRSUsingMapBruteForce(String iString) {

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

    /**
     * This approach generates all the possible suffixes for the given String
     * and creates a Trie with the suffixes. The idea is as we DFS the Trie, if
     * we find a word( a part of the suffix) and see that it still has children
     * it means that there is another duplicate substring in the string
     * 
     * @param input
     * @return
     */
    static String LRS(String input) {

        Trie node = new Trie();
        /**
         * Generate suffixes and ingest into the trie
         */
        for (int i = input.length() - 1; i >= 0; i--) {
            String suffix = input.substring(i);
            node.addWord(suffix);
        }

        /**
         * As reference cannot be replaced , a List of String is used but it
         * will always have just the one string object
         */
        List<String> maxString = new ArrayList<>();
        maxString.add(0, "");
        StringBuilder sb = new StringBuilder();
        walkTrieToGetMaxString(node, sb, maxString);

        return maxString.get(0);

    }

    /**
     * Does a DFS of the Trie. As it finds a suffix it checks if the node has
     * further children, if so then the suffix processed thus far is a candidate
     * substring
     * 
     * @param node
     * @param sb
     * @param maxString
     */
    private static void walkTrieToGetMaxString(Trie node, StringBuilder sb, List<String> maxString) {
        if (node == null)
            return;

        if (node.isWord && hasChildren(node)) {

            if (sb.length() > maxString.get(0).length()) {
                maxString.set(0, sb.toString());

            }

        }
        for (int i = 0; i < node.nodes.length; i++) {
            if (node.nodes[i] != null) {
                char ch = (char) (i + 'a');
                sb.append(ch);
                walkTrieToGetMaxString(node.nodes[i], sb, maxString);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

    }

    private static boolean hasChildren(Trie temp) {
        for (int i = 0; i < temp.nodes.length; i++) {
            if (temp.nodes[i] != null)
                return true;
        }
        return false;
    }

}
