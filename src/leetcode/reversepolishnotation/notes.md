# Evaluate Reverse Polish Notation - LeetCode Problem 150

## Problem Statement
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
- ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
- ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

Constraints:
- 1 <= tokens.length <= 104
- tokens[i] is either an operator or an integer
- Division truncates toward zero
- Division by zero will not occur

## Key Insights
1. **Stack-Based**: Perfect use case for stack data structure
2. **Operator Semantics**: When operator found, pop two operands
3. **Operator Order**: Second pop is first operand (due to stack order)
4. **Integer Check**: Use regex to identify numbers vs operators
5. **Space Efficient**: Process in single pass

## Algorithm Steps

### Approach: Stack Evaluation

**Step 1: Initialize**
- Create stack for operands
- Create set of operators

**Step 2: Process Tokens**
- For each token:
  - If operand (number): push to stack
  - If operator: pop two operands, compute result, push back

**Step 3: Return**
- Return single remaining value in stack

**Pseudocode:**
```
function evalRPN(tokens):
    stack = new Stack()
    operators = {"+", "-", "*", "/"}

    for token in tokens:
        if token is number:
            stack.push(Integer.parseInt(token))
        else:
            operand1 = stack.pop()
            operand2 = stack.pop()
            result = compute(operand2, operand1, token)
            stack.push(result)

    return stack.pop()

function compute(operand2, operand1, operator):
    case "+": return operand2 + operand1
    case "-": return operand2 - operand1
    case "*": return operand2 * operand1
    case "/": return operand2 / operand1
```

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time Complexity | O(n) where n = number of tokens |
| Space Complexity | O(n) for stack |

**Time Analysis:**
- Process each token once: O(n)
- Push/pop operations: O(1) each
- Regex matching: O(1) for short strings

**Space Analysis:**
- Stack can hold up to n/2 numbers: O(n)

## ASCII Visualization

```
Example: ["2", "1", "+", "3", "*"]
Compute: ((2 + 1) * 3) = 9

Processing:
Token "2": Stack: [2]
Token "1": Stack: [2, 1]
Token "+": Pop 1, 2 → 2+1=3 → Stack: [3]
Token "3": Stack: [3, 3]
Token "*": Pop 3, 3 → 3*3=9 → Stack: [9]
Result: 9

Stack Evolution:
     []
     [2]
     [2, 1]
     [3]        (after +)
     [3, 3]
     [9]        (after *)

Example: ["4", "13", "5", "/", "+"]
Compute: (4 + (13 / 5)) = 6 (since 13/5 = 2 truncated)

Token "4": Stack: [4]
Token "13": Stack: [4, 13]
Token "5": Stack: [4, 13, 5]
Token "/": Pop 5, 13 → 13/5=2 → Stack: [4, 2]
Token "+": Pop 2, 4 → 4+2=6 → Stack: [6]
Result: 6

Stack Evolution:
     []
     [4]
     [4, 13]
     [4, 13, 5]
     [4, 2]        (after /)
     [6]           (after +)

Operator Order (Critical!):
Stack operations (LIFO):
Push: 2, 1
Pop: 1 (first pop = top)
Pop: 2 (second pop = next)

Computation: operand2 - operand1
           = 2 - 1 = 1

NOT: 1 - 2 = -1

Visual Stack Example:
     top
      ↓
    |1|  ← pop first (operand1)
    |2|  ← pop second (operand2)
    ___
    bottom

Result = operand2 - operand1 = 2 - 1 = 1
```

## Code Walkthrough

```java
public static int evalRPN(String[] tokens) {
    Stack<Integer> operands = new Stack<>();

    for (String str : tokens) {
        // Check if token is a number using regex
        if (str.matches("-?\\d+")) {
            int num = Integer.parseInt(str);
            operands.push(num);
        } else {
            // Token is an operator
            int operand1 = operands.pop();  // Second operand (popped first)
            int operand2 = operands.pop();  // First operand (popped second)

            switch (str) {
                case "+":
                    operands.push(operand1 + operand2);
                    break;
                case "-":
                    operands.push(operand2 - operand1);
                    break;
                case "*":
                    operands.push(operand1 * operand2);
                    break;
                case "/":
                    operands.push(operand2 / operand1);
                    break;
            }
        }
    }

    return operands.pop();
}

// Alternative: Using method for operator application
public int evalRPNAlternative(String[] tokens) {
    Stack<Integer> stack = new Stack<>();

    for (String token : tokens) {
        if (isOperator(token)) {
            int b = stack.pop();  // Second operand
            int a = stack.pop();  // First operand
            stack.push(applyOperator(a, b, token));
        } else {
            stack.push(Integer.parseInt(token));
        }
    }

    return stack.pop();
}

private boolean isOperator(String token) {
    return token.equals("+") || token.equals("-") ||
           token.equals("*") || token.equals("/");
}

private int applyOperator(int a, int b, String op) {
    switch (op) {
        case "+": return a + b;
        case "-": return a - b;
        case "*": return a * b;
        case "/": return a / b;
        default: return 0;
    }
}

// Alternative: Using HashMap for operators
public int evalRPNHashMap(String[] tokens) {
    Stack<Integer> stack = new Stack<>();
    Map<String, Function<Integer[], Integer>> operators = new HashMap<>();

    operators.put("+", arr -> arr[0] + arr[1]);
    operators.put("-", arr -> arr[0] - arr[1]);
    operators.put("*", arr -> arr[0] * arr[1]);
    operators.put("/", arr -> arr[0] / arr[1]);

    for (String token : tokens) {
        if (operators.containsKey(token)) {
            int b = stack.pop();
            int a = stack.pop();
            stack.push(operators.get(token).apply(new Integer[]{a, b}));
        } else {
            stack.push(Integer.parseInt(token));
        }
    }

    return stack.pop();
}
```

## Edge Cases

1. **Single Number**: ["5"] -> 5
2. **Simple Addition**: ["2", "1", "+"] -> 3
3. **Negative Results**: ["1", "2", "-"] -> -1
4. **Division Truncation**: ["6", "5", "/"] -> 1
5. **Large Numbers**: ["1000000", "1000000", "*"] -> handle overflow
6. **Negative Numbers**: ["-1", "-2", "+"] -> -3
7. **Complex Expression**: Multiple operations
8. **Division by Negative**: ["5", "-2", "/"] -> -2

## Related Problems

1. **LeetCode 224**: Basic Calculator - Infix expression
2. **LeetCode 227**: Basic Calculator II - More operators
3. **LeetCode 772**: Basic Calculator III - Complex expression
4. **LeetCode 385**: Mini Parser - Nested expression

## Tags

- Stack
- Expression Evaluation
- Reverse Polish Notation
- Arithmetic Operations
- Medium Difficulty
- Acceptance: ~45%
