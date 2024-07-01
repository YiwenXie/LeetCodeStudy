package SkillCategory.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Medium130_SurroundedRegions {

    boolean[][] visited;
    public void solve(char[][] board) {
        int rowSize = board.length;
        int colSize = board[0].length;
        visited = new boolean[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            if (!visited[i][0] && board[i][0] == 'O') {
                dfs(i, 0, board);
//                bfs(i, 0, board);
            }
            if (!visited[i][colSize - 1] && board[i][colSize - 1] == 'O') {
                dfs(i, colSize - 1, board);
//                bfs(i, colSize - 1, board);
            }
        }

        for (int j = 1; j < colSize - 1; j++) {
            if (!visited[0][j] && board[0][j] == 'O') {
                dfs(0, j, board);
//                bfs(0, j, board);
            }
            if (!visited[rowSize - 1][j] && board[rowSize - 1][j] == 'O') {
                dfs(rowSize - 1, j, board);
//                bfs(rowSize - 1, j, board);
            }
        }

        for (int i = 1; i < rowSize - 1; i++) {
            for (int j = 1; j < colSize - 1; j++) {
                if (!visited[i][j] && board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    int[][] direction = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };
    private void bfs(int x, int y, char[][] board) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        queue.add(y);
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            int currentX = queue.poll();
            int currentY = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = currentX + direction[i][0];
                int nextY = currentY + direction[i][1];
                if (nextX < 0 || nextX >= board.length || nextY < 0 || nextY >= board[0].length || visited[nextX][nextY] || board[nextX][nextY] == 'X') {
                    continue;
                }
                queue.add(nextX);
                queue.add(nextY);
                visited[nextX][nextY] = true;
            }
        }
    }

    private void dfs(int i, int j, char[][] board) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] || board[i][j] == 'X') {
            return;
        }
        visited[i][j] = true;
        dfs(i - 1, j, board);
        dfs(i + 1, j, board);
        dfs(i, j - 1, board);
        dfs(i, j + 1, board);
    }
}
