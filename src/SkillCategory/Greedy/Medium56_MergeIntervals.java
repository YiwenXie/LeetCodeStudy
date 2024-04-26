package SkillCategory.Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Medium56_MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {3, 3}, {8, 10}, {15, 18}};
        System.out.println(Arrays.deepToString(merge(intervals)));
    }

    public static int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        Arrays.sort(intervals, ((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        }));
        int[] cover = new int[]{intervals[0][0], intervals[0][1]};
        // [1,3] [2,6] [3,3] [8,10] [15,18]
        // [1,6] [3,3] [8,10] [15,18]
        // [1,6] [8,10] [15,18]
        // [8,10] [15,18]
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= cover[1]) {
                cover[1] = Math.max(cover[1], intervals[i][1]);
            } else {
                result.add(new int[]{cover[0], cover[1]});
                cover[0] = intervals[i][0];
                cover[1] = intervals[i][1];
            }
        }
        result.add(cover);
        return result.toArray(new int[result.size()][]);
    }
}
