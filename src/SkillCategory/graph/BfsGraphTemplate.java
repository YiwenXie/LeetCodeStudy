package SkillCategory.graph;

import java.util.LinkedList;
import java.util.Queue;

public class BfsGraphTemplate {
    int[][] dir = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}}; // 表示四个方向

    // grid 是地图，也就是一个二维数组
// visited标记访问过的节点，不要重复访问
// x,y 表示开始搜索节点的下标
    void bfs(char[][] grid, boolean[][] visited, int x, int y) {
        Queue<int[]> que = new LinkedList<>(); // 定义队列
        que.add(new int[]{x, y}); // 起始节点加入队列
        visited[x][y] = true; // 只要加入队列，立刻标记为访问过的节点
        while (!que.isEmpty()) { // 开始遍历队列里的元素
            int[] cur = que.poll(); // 从队列取元素
            int curx = cur[0];
            int cury = cur[1]; // 当前节点坐标
            for (int i = 0; i < 4; i++) { // 开始想当前节点的四个方向左右上下去遍历
                int nextx = curx + dir[i][0];
                int nexty = cury + dir[i][1]; // 获取周边四个方向的坐标
                if (nextx < 0 || nextx >= grid.length || nexty < 0 || nexty >= grid[0].length) continue;  // 坐标越界了，直接跳过
                if (!visited[nextx][nexty]) { // 如果节点没被访问过
                    que.add(new int[]{nextx, nexty});  // 队列添加该节点为下一轮要遍历的节点
                    visited[nextx][nexty] = true; // 只要加入队列立刻标记，避免重复访问
                }
            }
        }

    }

}
