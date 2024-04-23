package SkillCategory.Greedy;

import java.util.Arrays;

public class Medium452_MinimumNumberOfArrowsToBurstBalloons {
    public static void main(String[] args) {
//        int[][] points = new int[][]{{3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}};
        int[][] points = new int[][]{{-2147483646, -2147483645}, {2147483646, 2147483647}};
        System.out.println(findMinArrowShots(points));
    }

    /**
     * 题目转换成求区间交集，只要是在交集内的肯定是可以一根箭就能把涉及区域都射中
     *
     * @param points
     * @return
     */
    public static int findMinArrowShots(int[][] points) {
        // 使用Integer内置比较方法，不会溢出
        Arrays.sort(points, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        // 交集
        int[] cover = new int[2];
        cover[0] = points[0][0];
        cover[1] = points[0][1];
        // points 不为空至少需要一支箭
        int count = 1;
        for (int i = 1; i < points.length; i++) {
            int min = points[i][0];
            int max = cover[1];
            // 当前区间的最小值已经超过交集的最大值
            // 则说明需要射一箭到当前区域，并更新交集
            if (cover[1] < min) {
                count++;
                cover[0] = points[i][0];
                cover[1] = points[i][1];
            } else {
                // 更新交集
                cover[0] = Math.max(min, points[i][0]);
                cover[1] = Math.min(max, points[i][1]);
            }
        }
        return count;
        // [10,16],[2,8],[1,6],[7,12]
        // [1,6],[2,8],[7,12],[10,16]
        // [1,6],[2,8] => [2, 6] count = 1
        // [2, 6],[7, 12] =>  count = 2
        // [7, 12] [10, 16] => [10, 12]
    }
}
