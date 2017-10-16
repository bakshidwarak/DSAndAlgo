package leetcode.wordsearch;

/**
 * Given a 2D board and a word, find if the word exists in the grid. The word can be constructed from letters of
 * sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter
 * cell may not be used more than once. For example, Given board =
 * 
 * <pre>
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
 * </pre>
 * 
 * word = "ABCCED", -> returns true, word = "SEE", -> returns true, word = "ABCB", -> returns false.
 * 
 * @author dwaraknathbs
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board.length == 0)
            return false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if (findWord(board, i, j, word, 0)) {
                    return true;
                }

            }
        }
        return false;
    }

    public boolean findWord(char[][] board, int row, int col, String word, int wordIndex) {

        if (wordIndex >= word.length()) {
            return true;
        }
        if (row >= board.length || col >= board[0].length || row < 0 || col < 0) {
            return false;
        }

        if (board[row][col] != word.charAt(wordIndex)) {
            return false;
        }
        char ch = board[row][col];
        board[row][col] = '1';
        boolean isPresent = findWord(board, row + 1, col, word, wordIndex + 1)
                || findWord(board, row, col + 1, word, wordIndex + 1)
                || findWord(board, row - 1, col, word, wordIndex + 1)
                || findWord(board, row, col - 1, word, wordIndex + 1);

        board[row][col] = ch;

        return isPresent;
    }
}
