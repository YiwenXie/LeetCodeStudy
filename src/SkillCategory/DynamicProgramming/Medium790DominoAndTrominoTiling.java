package SkillCategory.DynamicProgramming;

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
        // j = 0 = ways of ([|][ ] => No.i-1 col is filled and No.i col is not be filled)
        // j = 1 = ways of ([|][-] => No.i-1 col is filled and No.i col's 1st row is filled)
        // j = 2 = ways of ([|][_] => No.i-1 col is filled and No.i col's 2nd row is filled)
        // j = 3 = ways of ([|][|] => No.i-1 col is filled and No.i col is all filled)
        // base case
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[0][2] = 0;
        // status = [][|] => ignore i - 1, only have a way => [|][|]
        dp[0][3] = 1;
        for (int i = 1; i < n + 1; i++) {
            // [|][] => need No.i-1 col is filled, i col is not filled,
            //      1.use [|][] => need i - 1 col is filled => dp[i - 1][3]
            dp[i][0] = (dp[i - 1][3]) % MOD;
            // [][-] => need No.i-1 col is filled, i col's 1st row is filled
            //      1.use [|][-] => need i - 2 col is filled and i - 1 col is empty => dp[i - 1][0]
            //      2.use [-][-] => need i - 2 col is filled and i - 1 col's 2nd is filled => dp[i - 1][2]
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
            // [][_] => need No.i-1 col is filled, i col's 2nd row is filled
            //      1.use [|][_] => need i - 2 col is filled and i - 1 col is empty => dp[i - 1][0]
            //      2.use [_][_] => need i - 2 col is filled and i - 1 col's 1st is filled => dp[i - 1][1]
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
            // [|][|] => need No.i-1 col is filled, i col is all filled
            //      1.use [=][=] => need i - 2 col is filled and i - 1 col is empty => dp[i - 1][0]
            //      2.use [_][|] => need i - 2 col is filled and i - 1 col's 1st is filled => dp[i - 1][1]
            //      3.use [-][|] => need i - 2 col is filled and i - 1 col's 2nd is filled => dp[i - 1][2]
            //      4.use [|][|] => need i - 2 col is filled and i - 1 col's is all filled => dp[i - 1][3]
            dp[i][3] = (((dp[i - 1][0] + dp[i - 1][1]) % MOD + dp[i - 1][2]) % MOD + dp[i - 1][3]) % MOD;
        }
        // No.n col is filled that meet the question's require
        return dp[n][3];
    }
}
