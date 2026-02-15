# LeetCode 211: Add and Search Word - Data Structure Design

## Problem Statement
Design a data structure that supports two operations:

1. **`addWord(word)`:** Add a word into the data structure
2. **`search(word)`:** Search for a word or pattern in the data structure

The search operation can search for literal words or regular expression patterns:
- Regular lowercase letters match exact characters
- Dot character `.` matches any single letter

**Constraints:**
- All words consist of lowercase letters a-z
- Both `word` and `search` patterns contain only letters a-z and dots
- At most a few dots in search patterns
- Can have multiple search calls

## Examples

```
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");

wordDictionary.search("pad");      // false
wordDictionary.search("bad");      // true
wordDictionary.search(".ad");      // true (matches "bad" and "dad")
wordDictionary.search("b..");      // true (matches "bad")
```

## Key Insights

1. **Trie Data Structure:** Efficient for word storage and prefix matching
2. **Pattern Matching with Wildcards:** Dot matches any character
3. **Recursive Search:** DFS to handle wildcards at each position
4. **Word Termination Flag:** Mark end of valid words in Trie
5. **Time-Space Trade-off:** O(26^n) search worst case with dots, but typical case O(n)

## Algorithm Steps

### Trie Node Structure
```
class Trie:
  - isWord: boolean (marks end of word)
  - children: array of 26 Trie nodes (for 'a' to 'z')
```

### Add Word Operation
```
1. Start at root
2. For each character in word:
   a. If child node doesn't exist, create it
   b. Move to child node
3. Mark current node as word end (isWord = true)
```

### Search Operation
```
1. Use recursive DFS from root
2. If index == word.length:
   - Return true if current node marks word end
3. If current character is '.':
   - Try matching any of 26 children
   - Return true if any subtree matches
4. If current character is letter:
   - Check if child exists
   - Recursively search from child node
5. Return false if no match found
```

## Complexity Analysis

- **Add Word Time:** O(m) - m is word length
- **Add Word Space:** O(m) - New nodes for word characters
- **Search Time:** O(26^m) worst case (all dots), O(m) average case
- **Search Space:** O(h) - Recursion stack height

## ASCII Visualization

```
After addWord("bad"), addWord("dad"), addWord("mad"):

                root
               /    \
              b      d      m
              |      |      |
              a      a      a
              |      |      |
              d*     d*     d*
           (word)  (word)  (word)

Trie representation:
children[1] = node 'b'
children[3] = node 'd'
children[12] = node 'm'
At each node, children array tracks next letters

Search ".ad":
  index=0, char='.'
    Try all 26 children
    Found 'b' child → search("ad", node_b, 1)
      index=1, char='a'
        Found 'a' child → search("d", node_a, 2)
          index=2, char='d'
            Found 'd' child → search("", node_d, 3)
              index=3 == length && node_d.isWord = true
              Return true ✓
  Result: true (matches "bad")

Search "b..":
  index=0, char='b'
    Found 'b' child → search("..", node_b, 1)
      index=1, char='.'
        Try all 26 children
        Found 'a' child → search(".", node_a, 2)
          index=2, char='.'
            Try all 26 children
            Found 'd' child → search("", node_d, 3)
              index=3 == length && node_d.isWord = true
              Return true ✓
  Result: true (matches "bad")
```

## Code Walkthrough

```java
class Trie {
    boolean isWord;
    Trie[] children = new Trie[26];  // For 'a' to 'z'
}

public class WordDictionary {
    Trie words = new Trie();

    /** Add word to dictionary */
    public void addWord(String word) {
        char[] wordChars = word.toCharArray();
        Trie temp = words;

        for (int i = 0; i < wordChars.length; i++) {
            Trie node = temp.children[wordChars[i] - 'a'];

            // Create new node if doesn't exist
            if (node == null) {
                temp.children[wordChars[i] - 'a'] = new Trie();
            }
            temp = temp.children[wordChars[i] - 'a'];
        }

        temp.isWord = true;  // Mark end of word
    }

    /** Search for word or pattern */
    public boolean search(String word) {
        return searchHelper(word, 0, words);
    }

    private boolean searchHelper(String word, int index, Trie root) {
        // Base case: processed entire word
        if (index == word.length()) {
            return root.isWord;
        }

        char ch = word.charAt(index);

        if (ch == '.') {
            // Try matching with any of 26 letters
            for (int i = 0; i < root.children.length; i++) {
                if (root.children[i] != null
                        && searchHelper(word, index + 1, root.children[i]))
                    return true;
            }
        } else {
            // Exact character match
            if (root != null && root.children[ch - 'a'] != null) {
                return searchHelper(word, index + 1, root.children[ch - 'a']);
            }
        }

        return false;
    }
}
```

**Execution Flow for Add:**
1. Convert word to char array
2. Start at root Trie node
3. For each character, create child if needed
4. Navigate to child node
5. Mark final node as word terminator

**Execution Flow for Search:**
1. Call recursive helper from root
2. If dot encountered, try all 26 possibilities
3. If letter, navigate to specific child
4. If reach end and isWord is true, pattern matches
5. Backtrack if path doesn't lead to valid word

## Edge Cases

1. **Exact Word Search:** Pattern without dots
   - `search("bad")` with added "bad" → true

2. **Pattern with Single Dot:** Dot as wildcard
   - `search(".ad")` matches "bad", "dad", "mad"

3. **Pattern with Multiple Dots:** Multiple wildcards
   - `search("...") matches any 3-letter word

4. **Pattern Starting with Dot:** `".ad"`
   - `search(".ad")` matches "bad", "dad", "mad"

5. **Pattern Ending with Dot:** `"ba."`
   - `search("ba.")` matches "bad"

6. **All Dots:** `"..."`
   - `search("...")` matches all 3-letter words

7. **Non-existent Pattern:**
   - `search("xyz")` when no such word exists → false

8. **Empty Word or Pattern:**
   - Cannot happen per constraints

### Example Edge Cases:
```
addWord("bad"), search("bad") → true
addWord("bad"), search("b.d") → true
addWord("bad"), search("b..") → true
addWord("bad"), search("...") → true
addWord("bad"), search(".ad") → true
addWord("bad"), search("xyz") → false
```

## Related Problems

1. **LeetCode 208 - Implement Trie (Prefix Tree):** Basic Trie implementation
2. **LeetCode 212 - Word Search II:** Word search with dictionary
3. **LeetCode 236 - Lowest Common Ancestor:** Tree traversal patterns
4. **LeetCode 421 - Maximum XOR of Two Numbers:** Trie with bits
5. **LeetCode 1166 - Design File System:** Similar design problem
## Trie Advantages

1. **Prefix Matching:** Efficient for words starting with prefix
2. **Auto-complete:** Natural fit for suggestion systems
3. **Spell Checking:** Quick validation
4. **Lexicographic Ordering:** Can be traversed in order
5. **Space Efficient:** Shared prefixes save memory

## Tags

`#Data-Structure-Design` `#Trie` `#Depth-First-Search` `#Regex` `#Medium`

## Key Takeaways

- Trie stores words efficiently with shared prefixes
- Each Trie node tracks 26 possible children (a-z)
- `isWord` flag marks end of valid words
- Dot wildcard handled by trying all 26 children
- Recursive DFS explores all possibilities
- Time complexity depends on pattern (dots increase search time)
- Space complexity O(ALPHABET_SIZE * N) for N words
