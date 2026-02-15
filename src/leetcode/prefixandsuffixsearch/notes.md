# Prefix and Suffix Search - LeetCode Problem 745

## Problem Statement
Given many words, words[i] has weight i.

Design a class WordFilter that supports one function, `WordFilter.f(String prefix, String suffix)`. It will return the word with given prefix and suffix with maximum weight. If no word exists, return -1.

## Examples

**Example 1:**
```
Input: WordFilter(["apple"])
       WordFilter.f("a", "e")      // returns 0
       WordFilter.f("b", "")       // returns -1
```

**Example 2:**
```
Input: WordFilter(["apple", "app", "application"])
       WordFilter.f("app", "e")    // returns 0 (apple)
       WordFilter.f("a", "e")      // returns 0 (apple)
       WordFilter.f("app", "lication") // returns 2 (application)
```

## Key Insights
1. **Trie Structure**: Use Trie to efficiently search patterns
2. **Suffix + Prefix Combination**: Store all suffix-prefix combinations for each word
3. **Weight Tracking**: Keep index (weight) in each Trie node
4. **Separator**: Use a character ('{') not in word boundaries to separate suffix and prefix
5. **Space-Time Tradeoff**: O(n*m^2) space for O(m) query time, where n=words.length, m=word length

## Algorithm Steps

### Approach: Trie with Suffix-Prefix Combinations

**Construction (O(n*m^2) time and space):**
1. For each word at index i:
   - For each suffix position s from 0 to length:
     - Extract suffix: word[s:]
     - Create combined pattern: suffix + '{' + word
     - Insert into Trie, storing index i at each node

**Query (O(m) time):**
1. Construct search pattern: suffix + '{' + prefix
2. Traverse Trie following pattern
3. Return the last (maximum) index found

**Pseudocode:**
```
function WordFilter(words):
    trie = new Trie()
    for i from 0 to words.length:
        word = words[i]
        for s from word.length down to 0:
            suffix = word[s:]
            pattern = suffix + "{" + word
            trie.addWord(pattern, i)

function f(prefix, suffix):
    pattern = suffix + "{" + prefix
    indices = trie.matches(pattern)
    return indices.isEmpty ? -1 : indices.lastElement
```

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Construction Time | O(n*m^2) where n=words.length, m=max word length |
| Query Time | O(m) where m=combined pattern length |
| Space Complexity | O(n*m^2) for Trie structure |

**Details:**
- For each of n words, create m suffixes
- For each suffix, insert O(m) length string
- Query: traverse O(m) characters

## ASCII Visualization

```
Word: "apple" (index 0)

Suffixes generated:
s=5: "e" → Trie: e{apple → [0]
s=4: "le" → Trie: le{apple → [0]
s=3: "ple" → Trie: ple{apple → [0]
s=2: "ple" → Trie: ple{apple → [0] (duplicate)
s=1: "pple" → Trie: pple{apple → [0]
s=0: "apple" → Trie: apple{apple → [0]

Trie Structure (partial):
         root
         |
         a (indices: [0])
         |
         p (indices: [0])
         |
         p (indices: [0])
         |
         l (indices: [0])
         |
         e (indices: [0])
         |
         { (indices: [0])
         |
         a (indices: [0])
         |
         p (indices: [0])
         ...

Query: f("a", "e") → search for "e{a"
- Search "e{apple" in trie
- Last index found: 0

Example with multiple words:
Words: ["apple"(0), "app"(1), "application"(2)]

For "app" at index 1:
- Suffix "": "" → Trie: "{app" → [1]
- Suffix "p": "p{app" → [1]
- Suffix "pp": "pp{app" → [1]
- Suffix "app": "app{app" → [1]

Query: f("app", "e")
- Search: "e{app"
- Traverse: e → { → a → p → p
- Matches found: indices ending at "apple"(0)
- Return: 0
```

## Code Walkthrough

```java
public class WordFilter {
    Trie tree = new Trie();

    public WordFilter(String[] words) {
        // For each word
        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            // For each suffix position
            for (int s = word.length(); s >= 0; s--) {
                // Extract suffix starting at position s
                String suffix = word.substring(s);

                // Create pattern: suffix + '{' + word
                // The '{' separator ensures no collision with word characters
                tree.addWord(suffix + "{" + word, i);
            }
        }
    }

    public int f(String prefix, String suffix) {
        // Search pattern: suffix + '{' + prefix
        List<Integer> psearch = tree.matches(suffix + "{" + prefix);

        // Return the last (maximum weight) index
        int val = psearch.size() == 0 ? -1 : psearch.get(psearch.size() - 1);
        return val;
    }

    static class Trie {
        List<Integer> set = new ArrayList<>();
        Trie[] children = new Trie[27];  // 26 letters + '{'

        public void addWord(String word, int index) {
            Trie curr = this;

            // Insert each character
            for (char ch : word.toCharArray()) {
                // Index 26 is for '{'
                int idx = (ch == '{') ? 26 : (ch - 'a');

                if (curr.children[idx] == null) {
                    curr.children[idx] = new Trie();
                }

                // Add index to this node
                curr.children[idx].set.add(index);
                curr = curr.children[idx];
            }
        }

        public List<Integer> matches(String pattern) {
            Trie curr = this;

            // Traverse trie following pattern
            for (char ch : pattern.toCharArray()) {
                int idx = (ch == '{') ? 26 : (ch - 'a');

                if (curr.children[idx] == null) {
                    return new ArrayList<>();
                }

                curr = curr.children[idx];
            }

            // Return all indices at this node
            return curr.set;
        }
    }
}
```

## Edge Cases

1. **Single Word**: WordFilter(["word"]) → single queries
2. **Empty Prefix**: f("", "word") → entire word must match suffix
3. **Empty Suffix**: f("word", "") → entire word must match prefix
4. **No Match**: f("x", "y") → return -1
5. **Multiple Matches**: Return word with highest index
6. **Duplicate Words**: Words at different indices can be identical
7. **Single Character**: All single-char words
8. **Long Words**: Up to 15000 words, 10 chars each

## Related Problems

1. **LeetCode 208**: Implement Trie (Prefix Tree)
2. **LeetCode 211**: Design Add and Search Words Data Structure
3. **LeetCode 648**: Replace Words - Trie-based word filtering
4. **LeetCode 676**: Implement Magic Dictionary - Trie with queries
5. **LeetCode 642**: Design Search Autocomplete System
## Tags

- Trie
- String Matching
- Design
- Prefix/Suffix Search
- Hard Difficulty
- Acceptance: ~35%
