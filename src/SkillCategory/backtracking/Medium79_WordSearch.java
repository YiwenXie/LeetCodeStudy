package SkillCategory.backtracking;

import java.util.HashSet;
import java.util.Set;

public class Medium79_WordSearch {
    Set<Character> set = new HashSet<>();

    public boolean exist(char[][] board, String word) {
        for (Character character : word.toCharArray()) {
            set.add(character);
        }
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (backtracking(i, j, used, 0, board, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtracking(int i, int j, boolean[][] used, int index, char[][] board, String word) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || used[i][j] || !set.contains(board[i][j]) || board[i][j] != word.charAt(index)) {
            return false;
        }
        used[i][j] = true;
        // [i - 1][j]  [i][j + 1] [i][j - 1] [i + 1][j]
        boolean flag1 = backtracking(i - 1, j, used, index + 1, board, word);
        boolean flag2 = backtracking(i, j + 1, used, index + 1, board, word);
        boolean flag3 = backtracking(i, j - 1, used, index + 1, board, word);
        boolean flag4 = backtracking(i + 1, j, used, index + 1, board, word);
        used[i][j] = false;
        return flag1 || flag2 || flag3 || flag4;
    }

}
