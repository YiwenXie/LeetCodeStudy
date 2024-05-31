package SkillCategory.graph;

public class Medium200_NumberOfIslands {
    int result = 0;

    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    result++;
                    dfs(i, j, grid, visited);
                }
            }
        }
        return result;
    }

    public void dfs(int x, int y, char[][] grid, boolean[][] visited) {
        //搜索边界：索引越界或已遍历过或遍历到了"0"
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || visited[x][y] || grid[x][y] == '0') {
            return;
        }
        visited[x][y] = true;
        //根据"每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成"，对上下左右的相邻顶点进行dfs
        dfs(x + 1, y, grid, visited);
        dfs(x - 1, y, grid, visited);
        dfs(x, y - 1, grid, visited);
        dfs(x, y + 1, grid, visited);
    }

    boolean[][] visited;
    int[][] dir = {
            {0, 1}, //right -> x + 0  y + 1
            {1, 0}, //down -> x + 1, y + 0
            {-1, 0}, //up -> x - 1, y + 0
            {0, -1} //left -> x + 0, y - 1
    };

    public int numIslands2(char[][] grid) {
        int count = 0;
        visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    count++;
                    bfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int x, int y) {
        if (visited[x][y] || grid[x][y] == '0')
            return;

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            if (nextX < 0 || nextY < 0 || nextX >= grid.length || nextY >= grid[0].length)
                continue;
            bfs(grid, nextX, nextY);
        }
    }
}
