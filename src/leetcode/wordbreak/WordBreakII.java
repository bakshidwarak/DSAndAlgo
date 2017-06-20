

package leetcode.wordbreak;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, add spaces in s to construct a sentence where each word is a
 * valid dictionary word. You may assume the dictionary does not contain
 * duplicate words. Return all such possible sentences. For example, given s =
 * "catsanddog", dict = ["cat", "cats", "and", "sand", "dog"]. A solution is [
 * "cats and dog", "cat sand dog"]. UPDATE (2017/1/4): The wordDict parameter
 * had been changed to a list of strings (instead of a set of strings). Please
 * reload the code definition to get the latest changes.
 * 
 * @author dwaraknathbs
 */
public class WordBreakII {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> dictionary = new HashSet<>(wordDict);
        int[] cache = new int[s.length() + 1];
        Arrays.fill(cache, -1);
        cache[s.length()] = 1;
        wordBreakHelper(s, 0, dictionary, cache);

        List<String> current = new ArrayList<>();
        List<String> result = new ArrayList<>();

        if (cache[0] == 1) {
            dfs(s, cache, current, result, 0, dictionary);
        }
        return result;
    }

    public void dfs(String s, int[] cache, List<String> current, List<String> result, int index, HashSet<String> dictionary) {
        if (index >= s.length()) {
            result.add(current.stream().collect(Collectors.joining(" ")));
            return;
        }

        for (int i = index + 1; i <= s.length(); i++) {
            if (cache[i] == 1 && dictionary.contains(s.substring(index, i))) {
                current.add(s.substring(index, i));
                dfs(s, cache, current, result, i, dictionary);
                current.remove(current.size() - 1);
            }
        }
    }

    public boolean wordBreakHelper(String s, int start, HashSet<String> dictionary, int[] cache) {
        if (cache[start] == -1) {

            int output = 0;
            for (int i = start; i < s.length(); i++) {
                if (dictionary.contains(s.substring(start, i + 1)) && wordBreakHelper(s, i + 1, dictionary, cache)) {
                    output = 1;
                }
            }

            cache[start] = output;

        }

        return cache[start] == 1;
    }

}
