package SkillCategory.Greedy;

import java.util.Arrays;

public class Medium435_NonOverlappingIntervals {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{-52, 31}, {-73, -26}, {82, 97}, {-65, -11}, {-62, -49}, {95, 99}, {58, 95}, {-31, 49}, {66, 98}, {-63, 2}, {30, 47}, {-40, -26}};
        System.out.println(eraseOverlapIntervals(intervals));
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        // 为什么按endi从小到大排
        Arrays.sort(intervals, (o1, o2) -> {
            return o1[1] - o2[1];
        });
        int end = intervals[0][1];
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                count++;
                // 为什么不用更新end
                // 因为end一定比intervals[i][1]小或者相等
                // 就是取 区间1 和 区间2 右边界的最小值，
                // 因为这个最小值之前的部分一定是 区间1 和区间2 的重合部分，
                // 如果这个最小值也触达到区间3，那么说明 区间 1，2，3都是重合的。
            } else {
                end = intervals[i][1];
            }
        }
        return count;
    }

    public static int eraseOverlapIntervals2(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        int end = intervals[0][1];
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                // 求重叠的区间
                count++;
                // 为什么要更新end,取 区间1 和 区间2 右边界的最小值，
                // 因为end不一定小于intervals[i][1]
                // 因为这个最小值之前的部分一定是 区间1 和区间2 的重合部分，
                // 如果这个最小值也触达到区间3，那么说明 区间 1，2，3都是重合的。
                end = Math.min(end, intervals[i][1]);
            } else {
                end = intervals[i][1];
            }
        }
        return count;
    }
}
