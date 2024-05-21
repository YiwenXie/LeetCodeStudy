package SkillCategory.DynamicProgramming.No11ConstSmallerProblems;

import java.util.ArrayList;
import java.util.List;

public class Easy118_PascalTriangle {
    /**
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     * 时间复杂度：O(numRows^2)。
     * 空间复杂度：O(1)。不考虑返回值的空间占用。
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        // int[][] dp = new int[numRows][];
        List<List<Integer>> result = new ArrayList<>();

        // base case
        // dp[0][0] = 1;
        result.add(new ArrayList<>(1));
        result.get(0).add(1);

        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>(i + 1);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    // dp[i][j] = dp[i - 1][j];
                    row.add(1);
                } else {
                    // dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
            }
            result.add(row);
        }
        return result;
        // int[][] 转换成 List<List<Integer>>
//        return Arrays.stream(dp).map(l -> Arrays.stream(l).boxed().collect(Collectors.toList())).collect(Collectors.toList());
    }
}
