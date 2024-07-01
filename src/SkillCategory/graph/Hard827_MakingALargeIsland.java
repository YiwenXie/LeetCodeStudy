package SkillCategory.graph;

import java.util.*;

public class Hard827_MakingALargeIsland {
    int[][] direction = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    /**
     * 暴力想法:
     *      遍历地图尝试将每一个 0 改成1，然后去搜索地图中的最大的岛屿面积。
     *      计算地图的最大面积：遍历地图 + 深搜岛屿，时间复杂度为 n * n。
     *
     * 优化思路：
     *      只要用一次深搜把每个岛屿的面积记录下来就好。
     *      第一步：一次遍历地图，得出各个岛屿的面积，并做编号记录。可以使用map记录，key为岛屿编号，value为岛屿面积
     *      第二步：在遍历地图，遍历0的方格（因为要将0变成1），并统计该1（由0变成的1）周边岛屿面积，
     *      将其相邻面积相加在一起，遍历所有 0 之后，就可以得出 选一个0变成1 之后的最大面积。
     * @param grid
     * @return
     */
    boolean[][] visited;
    int count = 0;
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        visited = new boolean[n][n];
        Map<Integer, Integer> map = new HashMap<>();
        int mark = 2;// 记录每个岛屿的编号
        boolean isAllGrid = true;// 标记是否整个地图都是陆地
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    isAllGrid = false;
                }
                if (!visited[i][j] && grid[i][j] == 1) {
                    count = 0;
                    dfs(i, j, grid, mark);// 将与其链接的陆地都标记上 true
                    map.put(mark, count);// 记录每一个岛屿的面积
                    mark++;// 记录下一个岛屿编号
                }
            }
        }
        if (isAllGrid) {
            return n * n;
        }
        // 以下逻辑是根据添加陆地的位置，计算周边岛屿面积之和
        int result = 0;// 记录最后结果
        Set<Integer> visitedGrid = new HashSet<>();// 标记访问过的岛屿
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int gridCount = 1;// 记录连接之后的岛屿数量
                visitedGrid.clear(); // 每次使用时，清空
                if (grid[i][j] == 0) {
                    // 计算相邻坐标
                    for (int k = 0; k < 4; k++) {
                        int nearI = i + direction[k][0];
                        int nearJ = j + direction[k][1];
                        if (nearI < 0 || nearI >= n || nearJ < 0 || nearJ >= n) {
                            continue;
                        }
                        // 添加过的岛屿不要重复添加
                        if (visitedGrid.contains(grid[nearI][nearJ])) {
                            continue;
                        }
                        // 把相邻四面的岛屿数量加起来
                        gridCount += map.get(grid[nearI][nearJ]);
                        visitedGrid.add(grid[nearI][nearJ]);// 标记该岛屿已经添加过
                    }
                }
                result = Math.max(result, gridCount);
            }
        }
        return result;
    }

    private void dfs(int x, int y, int[][] grid, int mark) {
        if (visited[x][y] || grid[x][y] == 0) {
            return;
        }
        visited[x][y] = true;// 标记访问过
        grid[x][y] = mark;// 给陆地标记新标签
        count++;
        for (int i = 0; i < 4; i++) {
            int nextX = x + direction[i][0];
            int nextY = y + direction[i][1];
            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length) {
                continue;
            }
            dfs(nextX, nextY, grid, mark);
        }
    }
}
