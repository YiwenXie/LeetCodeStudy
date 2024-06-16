package SkillCategory.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Medium1020_NumberOfEnclaves {

    int result;

    /**
     * 本题要求找到不靠边的陆地面积，那么我们只要从周边找到陆地
     * 然后 通过 dfs或者bfs 将周边靠陆地且相邻的陆地都变成海洋，
     * 然后再去重新遍历地图的时候，统计此时还剩下的陆地就可以了。
     *
     * @param grid
     * @return
     */
    public int numEnclaves(int[][] grid) {
        int rowSize = grid.length;
        int colSize = grid[0].length;
        for (int i = 0; i < rowSize; i++) {
            if (grid[i][0] == 1) {
                dfs(i, 0, grid);
            }
            if (grid[i][colSize - 1] == 1) {
                dfs(i, colSize - 1, grid);
            }
        }
        // 初始化的時候，j 的上下限有調整過，必免重複操作。
        for (int j = 1; j < colSize - 1; j++) {
            if (grid[0][j] == 1) {
                dfs(0, j, grid);
            }
            if (grid[rowSize - 1][j] == 1) {
                dfs(rowSize - 1, j, grid);
            }
        }
        result = 0;
        for (int i = 1; i < rowSize - 1; i++) {
            for (int j = 1; j < colSize - 1; j++) {
                if (grid[i][j] == 1) {
                    dfs(i, j, grid);
                }
            }
        }
        return result;
    }

    private void dfs(int i, int j, int[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }
        // 将陆地变海洋
        grid[i][j] = 0;
        result++;
        dfs(i - 1, j, grid);
        dfs(i + 1, j, grid);
        dfs(i, j - 1, grid);
        dfs(i, j + 1, grid);
    }

    int[][] dir = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    private void bfs(int x, int y, int[][] grid) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(x);
        que.offer(y);
        result++;
        grid[x][y] = 0;

        while (!que.isEmpty()) {
            int currX = que.poll();
            int currY = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = currX + dir[i][0];
                int nextY = currY + dir[i][1];

                if (nextX < 0 || nextY < 0 || nextX >= grid.length || nextY >= grid[0].length)
                    continue;

                if (grid[nextX][nextY] == 1) {
                    que.offer(nextX);
                    que.offer(nextY);
                    result++;
                    grid[nextX][nextY] = 0;
                }
            }
        }
    }
}
