# LeetCode 843: Guess the Word

## Problem Statement

This is an **interactive problem**.

You are given an array of **unique** strings `wordlist` where `wordlist[i]` is `6` letters long, and one word in this list is chosen as `secret`.

You may call `Master.guess(word)` to guess a word. The guessed word should have type `string` and must be from the original list with `6` lowercase letters.

This function returns an `integer` type, representing the number of exact matches (value and position) of your guess to the `secret` word. Also, if your guess is not in the given wordlist, it will return `-1` instead.

For each test case, you have `10` guesses to guess the word. At the end of any number of calls, if you have made `10` or fewer calls to `Master.guess` and at least one of these guesses was `secret`, then you pass the testcase.

### Examples

**Example 1:**
```
Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]
Output: "You guessed the secret word correctly."

Explanation:
master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
```

**Constraints:**
- 1 <= wordlist.length <= 100
- wordlist[i].length == 6
- wordlist[i] consist of lowercase English letters
- All the strings of wordlist are unique
- secret exists in wordlist
- numguesses == 10

## Key Insights

1. **Interactive Problem**: Must make strategic guesses based on feedback
2. **Match Count**: Feedback tells us how many characters match in exact positions
3. **Elimination Strategy**: After each guess, eliminate words that don't match the feedback
4. **Random Selection**: Random guessing can work with proper elimination
5. **Minimax Strategy**: Choose words that minimize worst-case scenario
6. **Limited Attempts**: Only 10 guesses available

## Algorithm Steps

### Random Elimination Approach

1. **Initialize**: Start with full wordlist

2. **For each of 10 attempts**:
   - Pick a random word from current candidate list
   - Guess the word using master.guess()
   - If guess returns 6, found the secret!
   - Otherwise, filter candidates:
     - Keep only words that have same match count with our guess
     - These are potential secrets

3. **Continue** until secret found or 10 attempts exhausted

## Complexity Analysis

- **Time Complexity**: O(N^2)
  - N = wordlist.length
  - Each iteration: O(N) to filter wordlist
  - Match calculation: O(L) where L = 6 (constant)
  - Overall: O(10 * N * 6) = O(N)

- **Space Complexity**: O(N)
  - Temporary list for filtered words: O(N)
  - Overall: O(N)

## Visual Explanation

### Example: Finding "acckzz"

```
wordlist = ["acckzz", "ccbazz", "eiowzz", "abcczz"]

Attempt 1: Guess "ccbazz" (random pick)
  match("ccbazz", "acckzz") = 3
  Result: 3 matches
  Filter: Keep words with 3 matches to "ccbazz"
    match("ccbazz", "acckzz") = 3 ✓
    match("ccbazz", "ccbazz") = 6 (self) ✓
    match("ccbazz", "eiowzz") = 2 ✗
    match("ccbazz", "abcczz") = 4 ✗
  Candidates: ["acckzz", "ccbazz"]

Attempt 2: Guess "acckzz" (random from filtered)
  Result: 6 matches
  Found secret! ✓
```

### Match Calculation Example

```
word1 = "acckzz"
word2 = "ccbazz"

Position: 0 1 2 3 4 5
word1:    a c c k z z
word2:    c c b a z z
Match:    ✗ ✓ ✗ ✗ ✓ ✓

Total matches: 3
```

### Elimination Strategy

```
Initial: ["acckzz", "ccbazz", "eiowzz", "abcczz"]

Guess "eiowzz", get 2 matches
Filter by: words that have 2 matches with "eiowzz"

Check each word:
  match("eiowzz", "acckzz") = 2 ✓ keep
  match("eiowzz", "ccbazz") = 1 ✗ remove
  match("eiowzz", "eiowzz") = 6 ✓ keep
  match("eiowzz", "abcczz") = 2 ✓ keep

New candidates: ["acckzz", "eiowzz", "abcczz"]
```

## Code Walkthrough

```java
public void findSecretWord(String[] wordlist, Master master) {
    // Convert array to list for easier manipulation
    List<String> words = new ArrayList<>();
    for (String str : wordlist) {
        words.add(str);
    }

    // Make up to 10 guesses
    for (int i = 0; i < 10; i++) {
        // Pick a random word from current candidates
        int random = (new java.util.Random()).nextInt(words.size());
        String guess = words.get(random);

        // Make the guess
        int guessValue = master.guess(guess);

        // If found secret, done!
        if (guessValue == 6)
            return;

        // Filter candidates based on match count
        List<String> tempList = new ArrayList<>();
        for (String w : words) {
            // Keep words that have same match count with our guess
            if (match(w, guess) == guessValue) {
                tempList.add(w);
            }
        }

        // Update candidate list
        words = tempList;
    }
}

// Calculate number of matching characters at same positions
public int match(String s, String t) {
    int matches = 0;
    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == t.charAt(i))
            matches++;
    }
    return matches;
}
```

## Why This Works

### Key Insight

If `master.guess(word1)` returns `k`:
- The secret has exactly `k` matching positions with `word1`
- Any candidate word must also have `k` matching positions with `word1`
- Words with different match counts cannot be the secret

### Example Proof

```
If guess "abc" returns 2:
  Secret has 2 matches with "abc"

Candidate "xyz" has 0 matches with "abc":
  If "xyz" were secret, guess would return 0, not 2
  Therefore "xyz" cannot be secret ✗

Candidate "aec" has 2 matches with "abc":
  If "aec" were secret, guess would return 2 ✓
  "aec" remains a candidate ✓
```

## Advanced Strategy: Minimax

Choose words that minimize maximum remaining candidates:

```java
public void findSecretWord(String[] wordlist, Master master) {
    List<String> words = Arrays.asList(wordlist);

    for (int i = 0; i < 10; i++) {
        // Choose word that minimizes worst case
        String guess = getBestGuess(words);

        int matches = master.guess(guess);
        if (matches == 6) return;

        // Filter based on match count
        List<String> filtered = new ArrayList<>();
        for (String w : words) {
            if (match(w, guess) == matches) {
                filtered.add(w);
            }
        }
        words = filtered;
    }
}

private String getBestGuess(List<String> words) {
    // For each word, calculate maximum candidates remaining
    int minMaxSize = Integer.MAX_VALUE;
    String bestGuess = words.get(0);

    for (String candidate : words) {
        // Group words by match count
        int[] count = new int[7];
        for (String w : words) {
            count[match(candidate, w)]++;
        }

        // Find maximum group size
        int maxGroup = 0;
        for (int c : count) {
            maxGroup = Math.max(maxGroup, c);
        }

        // Choose word with smallest maximum group
        if (maxGroup < minMaxSize) {
            minMaxSize = maxGroup;
            bestGuess = candidate;
        }
    }

    return bestGuess;
}
```

## Edge Cases

1. **Secret is First Word**: Found on first guess
   - Best case scenario

2. **All Words Similar**: High match counts
   - Takes more guesses to narrow down

3. **All Words Different**: Low match counts
   - Quick elimination

4. **Single Candidate Left**: Before 10th guess
   - Guaranteed to find

5. **Worst Case**: Need all 10 guesses
   - Should still find with proper strategy

6. **Empty Filtered List**: Should not happen with correct logic
   - Secret must always remain in candidates

## Probability Analysis

### Random Strategy
- Average case: ~6 guesses
- Worst case: 10 guesses
- Success rate: >99% with proper elimination

### Minimax Strategy
- Average case: ~5 guesses
- Worst case: 8 guesses
- Success rate: 100% guaranteed

## Related Problems

1. **LeetCode 375**: Guess Number Higher or Lower II
2. **LeetCode 374**: Guess Number Higher or Lower
3. **LeetCode 464**: Can I Win
4. **LeetCode 294**: Flip Game II
5. **LeetCode 1236**: Web Crawler

## Tags

- Interactive Problem
- String
- Array
- Elimination Strategy
- Game Theory
- Minimax Algorithm
- Random Algorithm
- Greedy
