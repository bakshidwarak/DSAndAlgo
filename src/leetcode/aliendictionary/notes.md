# 269. Alien Dictionary

## Problem Statement
There is a new alien language which uses the Latin alphabet. However, the order among letters is unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

### Examples
```
Example 1:
Input: ["wrt", "wrf", "er", "ett", "rftt"]
Output: "wertf"

Example 2:
Input: ["z", "x"]
Output: "zx"

Example 3:
Input: ["z", "x", "z"]
Output: ""
Explanation: The order is invalid (cyclic dependency)
```

### Constraints
- All letters are in lowercase
- If a is a prefix of b, then a must appear before b in the dictionary
- If the order is invalid, return an empty string
- There may be multiple valid orders; return any one of them
- Words in dictionary are sorted lexicographically by alien language rules

## Approach & Solution

### Key Insights
1. **Graph construction**: Build a directed graph where an edge from char A to char B means A comes before B in the alien alphabet
2. **Character comparison**: Compare adjacent words to find ordering relationships
3. **Topological sort**: The alien dictionary order is a topological ordering of the character dependency graph
4. **Cycle detection**: Use DFS with a recursion stack to detect cycles (invalid orderings)
5. **Post-order traversal**: Characters are added to result in post-order (reverse topological order)

### Algorithm Steps
1. Build a graph by comparing adjacent words in the dictionary
   - For each pair of adjacent words, find the first differing character
   - Create a directed edge from the first word's character to the second word's character
2. Perform DFS traversal with cycle detection
   - Use a visited set to track completely processed nodes
   - Use a recursion set to detect cycles (node currently in the call stack)
3. Push characters to a stack in post-order (after visiting all neighbors)
4. Pop from stack to build the result string (reverse topological order)
5. Return empty string if a cycle is detected

### Complexity Analysis
- **Time Complexity**: O(C)
  - Where C is the total number of characters in all words
  - Building graph: O(C) to iterate through all words and characters
  - DFS traversal: O(V + E) where V is number of unique characters and E is edges
  - Overall: O(C) as C dominates
- **Space Complexity**: O(1) or O(26)
  - Graph storage: O(26) vertices max (lowercase letters)
  - Adjacency lists: O(26) edges max in worst case
  - Recursion stack: O(26) depth max
  - Effectively constant space for alphabet size

### Visualization
```
Input: ["wrt", "wrf", "er", "ett", "rftt"]

Step 1: Compare adjacent words to build graph
"wrt" vs "wrf": t -> f  (3rd char differs)
"wrf" vs "er":  w -> e  (1st char differs)
"er" vs "ett":  r -> t  (2nd char differs)
"ett" vs "rftt": e -> r (1st char differs)

Step 2: Resulting directed graph
    w → e → r → t → f

Step 3: Topological sort (DFS post-order)
Start DFS from each unvisited vertex

Visit w: w → e → r → t → f
Post-order: f, t, r, e, w
Reverse: w, e, r, t, f

Output: "wertf"

Example with cycle:
["z", "x", "z"]
z -> x (comparing z and x)
x -> z (comparing x and z)
Cycle detected! Return ""
```

## Code Walkthrough

```java
public String alienOrder(String[] words) {
    // Build graph from word comparisons
    HashMap<Character, Vertex> graph = buildGraph(words);

    HashSet<Character> visited = new HashSet<>();
    Stack<Character> order = new Stack<>();
    HashSet<Character> set = new HashSet<>();  // Recursion stack for cycle detection

    try {
        // Perform DFS from each unvisited vertex
        for (Map.Entry<Character, Vertex> entry : graph.entrySet()) {
            if (!visited.contains(entry.getKey())) {
                traverse(entry.getValue(), visited, order, set);
            }
        }
    } catch (Exception e) {
        return "";  // Cycle detected
    }

    // Build result from stack (reverse topological order)
    StringBuilder sb = new StringBuilder();
    while (!order.isEmpty()) {
        sb.append(order.pop());
    }
    return sb.toString();
}
```

**Graph Building:**
```java
public HashMap<Character, Vertex> buildGraph(String[] words) {
    HashMap<Character, Vertex> vertexMap = new HashMap<>();

    // Step 1: Create vertices for all characters
    for (String word : words) {
        for (char ch : word.toCharArray()) {
            vertexMap.putIfAbsent(ch, new Vertex(ch));
        }
    }

    // Step 2: Build edges by comparing adjacent words
    for (int i = 1; i < words.length; i++) {
        String first = words[i - 1];
        String second = words[i];
        int n = Math.min(first.length(), second.length());

        // Find first differing character
        for (int j = 0; j < n; j++) {
            if (first.charAt(j) != second.charAt(j)) {
                // Add edge: first[j] -> second[j]
                vertexMap.get(first.charAt(j)).neighbours
                         .add(vertexMap.get(second.charAt(j)));
                break;  // Only first difference matters
            }
        }
    }
    return vertexMap;
}
```

**DFS with Cycle Detection:**
```java
public void traverse(Vertex v, HashSet<Character> visited,
                    Stack<Character> order, HashSet<Character> set) throws Exception {

    // Cycle detected: node already in current recursion path
    if (set.contains(v.ch)) {
        throw new Exception("Graph is cyclic");
    }

    // Already processed this node
    if (visited.contains(v.ch)) {
        return;
    }

    visited.add(v.ch);
    set.add(v.ch);  // Mark as in current recursion path

    // Visit all neighbors
    for (Vertex neighbour : v.neighbours) {
        traverse(neighbour, visited, order, set);
    }

    set.remove(v.ch);  // Remove from recursion path
    order.push(v.ch);   // Add to result in post-order
}
```

## Edge Cases
- **Single character words**: ["a", "b", "c"] → "abc"
- **Cyclic dependencies**: ["z", "x", "z"] → "" (invalid)
- **Prefix relationships**: ["ab", "a"] would be invalid (longer word before prefix)
- **No ordering between some chars**: Some characters may not have direct relationships
- **Single word**: ["abc"] → Could be any permutation, implementation returns characters in discovery order
- **Identical adjacent words**: No new information, handled correctly

## Related Problems
- [**207. Course Schedule**](../courseschedule/notes.md): Similar topological sort with cycle detection
- [**210. Course Schedule II**](../courseschedule/notes.md): Returns the topological ordering
- **444. Sequence Reconstruction**: Verify if sequence is unique topological sort
- **All topological sort problems**: Uses same DFS/BFS approach

## Tags
`graph` `topological-sort` `dfs` `string` `hard`
