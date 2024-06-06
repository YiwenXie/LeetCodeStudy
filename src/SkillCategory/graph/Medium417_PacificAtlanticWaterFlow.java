package SkillCategory.graph;

import java.util.ArrayList;
import java.util.List;

public class Medium417_PacificAtlanticWaterFlow {
    List<List<Integer>> result;
    int[][] direction = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    /**
     * 从太平洋边上的节点 逆流而上，将遍历过的节点都标记上。
     * 从大西洋的边上节点 逆流而长，将遍历过的节点也标记上。
     * 然后两方都标记过的节点就是既可以流太平洋也可以流大西洋的节点。
     * <p>
     * 时间复杂度分析， 关于dfs函数搜索的过程 时间复杂度是 O(n * m)，这个大家比较容易想。
     * 关键看主函数，那么每次dfs的时候，上面还是有for循环的。
     * 第一个for循环，时间复杂度是：n * (n * m) 。
     * 第二个for循环，时间复杂度是：m * (n * m)。
     * 所以本题看起来 时间复杂度好像是 ： n * (n * m) + m * (n * m) = (m * n) * (m + n) 。
     * <p>
     * 其实这是一个误区，大家再自己看 dfs函数的实现，其实 有visited函数记录 走过的节点，而走过的节点是不会再走第二次的。
     * 所以 调用dfs函数，只要参数传入的是 数组pacific，那么地图中 每一个节点其实就遍历一次，无论你调用多少次。
     * 同理，调用 dfs函数，只要 参数传入的是 数组atlantic，地图中每个节点也只会遍历一次。
     * 所以，以下这段代码的时间复杂度是 2 * n * m。
     * 地图用每个节点就遍历了两次，参数传入pacific的时候遍历一次，参数传入atlantic的时候遍历一次。
     *
     * @param heights
     * @return
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        result = new ArrayList<>();
        int rowSize = heights.length;
        int colSize = heights[0].length;
        // 记录从太平洋边出发，可以遍历的节点
        boolean[][] pacific = new boolean[rowSize][colSize];
        // 记录从大西洋出发，可以遍历的节点
        boolean[][] atlantic = new boolean[rowSize][colSize];
        // 从最上最下行的节点出发，向高处遍历
        for (int row = 0; row < rowSize; row++) {
            dfs(row, 0, pacific, heights); // 遍历最左列，接触太平洋
            dfs(row, colSize - 1, atlantic, heights); // 遍历最右列，接触大西洋
        }
        // 从最左最右列的节点出发，向高处遍历
        for (int col = 0; col < colSize; col++) {
            dfs(0, col, pacific, heights); // 遍历最上行，接触太平洋
            dfs(rowSize - 1, col, pacific, heights); // 遍历最下行，接触大西洋
        }
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                // 如果这个节点，从太平洋和大西洋出发都遍历过，就是结果
                if (pacific[i][j] && atlantic[i][j]) {
                    ArrayList<Integer> path = new ArrayList<>();
                    path.add(i);
                    path.add(j);
                    result.add(path);
                }
            }
        }
        return result;
    }

    private void dfs(int x, int y, boolean[][] visited, int[][] heights) {
        if (visited[x][y]) {
            return;
        }
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nextX = x + direction[i][0];
            int nextY = y + direction[i][1];
            if (nextX < 0 || nextX >= heights.length || nextY < 0 || nextY >= heights[0].length) {
                continue;
            }
            // 高度不合适，注意这里是从低向高判断，逆流
            if (heights[x][y] > heights[nextX][nextY]) {
                continue;
            }
            dfs(nextX, nextY, visited, heights);
        }
    }

    /**
     * 超时
     * 深搜的时间复杂度是： m * n
     * 遍历每一个节点的时候，都要做深搜，那么整体时间复杂度 就是 O(m^2 * n^2)
     *
     * @param heights
     * @return
     */
    public List<List<Integer>> pacificAtlantic2(int[][] heights) {
        result = new ArrayList<>();
        // 遍历每一个点，看是否能同时到达太平洋和大西洋
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (isResult(i, j, heights)) {
                    ArrayList<Integer> path = new ArrayList<>();
                    path.add(i);
                    path.add(j);
                    result.add(path);
                }
            }
        }
        return result;
    }

    private void dfs2(int x, int y, boolean[][] visited, int[][] heights) {
        if (visited[x][y]) {
            return;
        }
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nextX = x + direction[i][0];
            int nextY = y + direction[i][1];
            if (nextX < 0 || nextX >= heights.length || nextY < 0 || nextY >= heights[0].length) {
                continue;
            }
            // 从高向低流
            if (heights[x][y] < heights[nextX][nextY]) {
                continue;
            }
            dfs2(nextX, nextY, visited, heights);
        }
    }

    boolean isResult(int x, int y, int[][] heights) {
        boolean[][] visited = new boolean[heights.length][heights[0].length];
        dfs2(x, y, visited, heights);
        boolean isPacific = false;
        boolean isAtlantic = false;

        // 以下就是判断x，y出发，是否到达太平洋和大西洋
        for (int j = 0; j < heights[0].length; j++) {
            if (visited[0][j]) {
                isPacific = true;
                break;
            }
        }
        for (int i = 0; i < heights.length; i++) {
            if (visited[i][0]) {
                isPacific = true;
                break;
            }
        }
        for (int j = 0; j < heights[0].length; j++) {
            if (visited[heights.length - 1][j]) {
                isAtlantic = true;
                break;
            }
        }
        for (int i = 0; i < heights.length; i++) {
            if (visited[i][heights[0].length - 1]) {
                isAtlantic = true;
                break;
            }
        }
        if (isAtlantic && isPacific) return true;
        return false;
    }
}
