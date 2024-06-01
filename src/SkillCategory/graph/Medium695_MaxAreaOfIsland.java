package SkillCategory.graph;

import java.util.ArrayList;
import java.util.List;

public class Medium695_MaxAreaOfIsland {
    int result = 0;
    List<Integer> path;
    boolean[][] visited;

    public int maxAreaOfIsland(int[][] grid) {
        path = new ArrayList<>();
        visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    dfs(i, j, grid);
                    path.clear();
                }
            }
        }
        return result;
    }

    public void dfs(int i, int j, int[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || visited[i][j] || grid[i][j] == 0) {
            return;
        }
        path.add(grid[i][j]);
        visited[i][j] = true;
        result = Math.max(result, path.size());
        dfs(i - 1, j, grid);
        dfs(i + 1, j, grid);
        dfs(i, j - 1, grid);
        dfs(i, j + 1, grid);
    }

    int[][] dir = {
            {0, 1}, //right -> x + 0  y + 1
            {1, 0}, //down -> x + 1, y + 0
            {-1, 0}, //up -> x - 1, y + 0
            {0, -1} //left -> x + 0, y - 1
    };

    public int maxAreaOfIsland2(int[][] grid) {
        path = new ArrayList<>();
        visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    bfs(i, j, grid);
                    path.clear();
                }
            }
        }
        return result;
    }

    public void bfs(int x, int y, int[][] grid) {
        visited[x][y] = true;
        path.add(grid[x][y]);
        result = Math.max(result, path.size());
        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length || visited[nextX][nextY] || grid[nextX][nextY] == 0) {
                continue;
            }
            bfs(nextX, nextY, grid);
        }
    }


}
