package SkillCategory.DynamicProgramming.No11ConstSmallerProblems;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/11/29 21:20
 */
public class Medium790DominoAndTrominoTiling {
    // Since the answer may be very large, return it modulo 10^9 + 7.
    final static int MOD = (int) (Math.pow(10, 9) + 7);

    public int numTilings(int n) {
        // initial dp
        // i = col = problem's size
        // j = i col's status
        // 使用 dp[i][s] 表示平铺到第 i 列时，各个状态 j 对应的平铺方法数量。
        int[][] dp = new int[n + 1][4];

        // base case
        // 0 = No.i col is not be filled
        dp[0][0] = 0;
        // 1 = No.i col's 1st row is filled
        dp[0][1] = 0;
        // 2 = No.i col's 2nd row is filled
        dp[0][2] = 0;
        // 3 = No.i col is all filled
        // status = [][|] => ignore i - 1, only have a way => [|][|]
        dp[0][3] = 1;
        for (int i = 1; i < n + 1; i++) {
            // [][] => i col is not filled
            //      1.when No.i-1 col is [] can use No.i-1 col's state [|] to meet [] => dp[i - 1][3]
            dp[i][0] = (dp[i - 1][3]) % MOD;
            // [][-] => i col's 1st row is filled
            //      1.when No.i-1 col is [] can use No.i-1 col's state [] to meet [|][-] => dp[i - 1][0]
            //      2.when No.i-1 col is [] can use No.i-1 col's state [_] to meet [-][-] => dp[i - 1][2]
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
            // [][_] => i col's 2nd row is filled
            //      1.when No.i-1 col is [] can use No.i-1 col's state [] to meet [|][_] => dp[i - 1][0]
            //      2.when No.i-1 col is [-] can use No.i-1 col's state [-] to meet [_][_] => dp[i - 1][1]
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
            // [][|] => i col is all filled
            //      1.when No.i-1 col is [] can use No.i-1 col's state [] to meet [|][|] => dp[i - 1][0]
            //      2.when No.i-1 col is [-] can use No.i-1 col's state [-] to meet [_][|] => dp[i - 1][1]
            //      3.when No.i-1 col is [_] can use No.i-1 col's state [_] to meet [-][|] => dp[i - 1][2]
            //      4.when No.i-1 col is [|] can use No.i-1 col's state [|] to meet [|][|] => dp[i - 1][3]
            dp[i][3] = (((dp[i - 1][0] + dp[i - 1][1]) % MOD + dp[i - 1][2]) % MOD + dp[i - 1][3]) % MOD;
        }
        // No.n col is filled that meet the question's require
        return dp[n][3];
    }
}
