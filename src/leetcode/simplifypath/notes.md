# 71. Simplify Path

## Problem Statement
Given an absolute path for a file (Unix-style), simplify it to its canonical form.

Rules:
- '/' is the path separator
- '.' refers to current directory
- '..' refers to parent directory
- Multiple consecutive slashes should be treated as single slash
- Redundant slashes should be removed

## Examples

### Example 1
```
Input: "/home/"
Output: "/home"
Explanation: Trailing slash removed
```

### Example 2
```
Input: "/a/./b/../../c/"
Output: "/c"
Explanation:
  /a/./b -> /a/b (. means current, no change)
  ../../c -> /c (go up two levels, then into c)
```

### Example 3
```
Input: "/a//b////c/d//././/.."
Output: "/a/b/c"
Explanation: Remove redundant slashes and handle . and ..
```

## Key Insights

1. **Deque for efficient operations**: Add/remove from both ends
2. **Split by separator**: Break path into components
3. **Skip empty and current**: Ignore empty strings and '.'
4. **Handle parent**: Pop last directory on '..'
5. **Reconstruct**: Join remaining directories with '/'

## Algorithm Steps

1. Split the path string by '/'
2. Create a Deque to store valid directory names
3. For each component:
   - Skip if empty string (consecutive slashes)
   - Skip if '.' (current directory)
   - If '..':
     - Pop from deque if not empty (go to parent)
     - Otherwise, stay at root
   - Otherwise, add directory name to deque
4. Join all directories with '/' prefix
5. Return result (or "/" if empty)

## Complexity Analysis

**Time Complexity:** O(n)
- Split operation: O(n)
- Processing each component: O(n) total
- Joining result: O(n)
- Overall: O(n)

**Space Complexity:** O(n)
- Deque stores directory names
- Split array stores path components

## ASCII Visualization

```
Input: "/a/./b/../../c/"

Split by '/': ["", "a", ".", "b", "..", "..", "c", ""]

Process each component:
1. "" -> skip (empty)
2. "a" -> add to deque: ["a"]
3. "." -> skip (current directory)
4. "b" -> add to deque: ["a", "b"]
5. ".." -> pop: ["a"]
6. ".." -> pop: []
7. "c" -> add: ["c"]
8. "" -> skip (empty)

Deque final: ["c"]

Reconstruct: "/" + "c" = "/c"
```

## Code Walkthrough

```java
public String simplifyPath(String path) {
    // Use deque for efficient add/remove from both ends
    Deque<String> deque = new LinkedList<>();

    // Split path by '/'
    String[] pathParams = path.split("/");

    // Process each path component
    for (String str : pathParams) {
        // Skip empty strings (from consecutive slashes) and current directory
        if (str.equals(".") || str.equals("/") || str.trim().equals(""))
            continue;

        // Handle parent directory
        if (str.equals("..")) {
            // Remove last directory if it exists
            if (deque.size() > 0)
                deque.removeLast();
            continue;
        }

        // Add directory name to deque
        deque.addLast(str);
    }

    // Build result string
    if (deque.isEmpty())
        return "/";

    StringBuilder sb = new StringBuilder();
    while (!deque.isEmpty()) {
        sb.append("/");
        sb.append(deque.removeFirst());
    }

    return sb.toString();
}
```

## Detailed Walkthrough

```
Input: "/a/./b/../../c/"

Step 1: Split
pathParams = ["", "a", ".", "b", "..", "..", "c", ""]

Step 2: Process
i=0: str="" -> skip (empty)
i=1: str="a" -> addLast("a"), deque=["a"]
i=2: str="." -> skip (current)
i=3: str="b" -> addLast("b"), deque=["a","b"]
i=4: str=".." -> removeLast(), deque=["a"]
i=5: str=".." -> removeLast(), deque=[]
i=6: str="c" -> addLast("c"), deque=["c"]
i=7: str="" -> skip (empty)

Step 3: Build result
isEmpty? NO
sb = ""
sb += "/" -> "/"
sb += deque.removeFirst()="c" -> "/c"

Return "/c"
```

## Edge Cases

1. **Root only**: "/" -> "/"
2. **Multiple slashes**: "////" -> "/"
3. **No parent at root**: "/../" -> "/"
4. **All dots**: "/..." -> "/..."
5. **Complex path**: "/a//b////c/d//././/.." -> "/a/b/c"
6. **Trailing directory**: "/a/b/c/" -> "/a/b/c"
7. **Current dir at start**: "/./a" -> "/a"

## Why Deque?

LinkedList implements Deque:
- addLast(): O(1) add to end
- removeLast(): O(1) remove from end
- removeFirst(): O(1) remove from beginning

Stack-like behavior for handling '..'

## Alternative Using Stack

```java
public String simplifyPathStack(String path) {
    Stack<String> stack = new Stack<>();
    String[] components = path.split("/");

    for (String component : components) {
        if (component.isEmpty() || component.equals(".")) {
            continue;
        } else if (component.equals("..")) {
            if (!stack.isEmpty())
                stack.pop();
        } else {
            stack.push(component);
        }
    }

    StringBuilder sb = new StringBuilder();
    for (String dir : stack) {
        sb.append("/").append(dir);
    }

    return sb.isEmpty() ? "/" : sb.toString();
}
```

## Related Problems

- 1106: Parsing A Boolean Expression
- 1249: Minimum Remove to Make Valid Parentheses
- 1541: Minimum Insertions to Balance a Parentheses String
## Tags

`medium` `string` `stack` `deque` `parsing` `path-manipulation`
