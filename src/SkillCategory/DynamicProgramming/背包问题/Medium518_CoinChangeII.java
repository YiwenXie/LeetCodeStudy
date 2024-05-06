package SkillCategory.DynamicProgramming.背包问题;

/**
 * 个数可以不限使用，说明这是一个完全背包。
 * 得到的集合是排列，说明需要考虑元素之间的顺序。
 * 本题要求的是排列，那么这个for循环嵌套的顺序可以有说法了。
 * 在动态规划：518.零钱兑换II 中就已经讲过了。
 * 如果求组合数就是外层for循环遍历物品，内层for遍历背包。
 * 如果求排列数就是外层for遍历背包，内层for循环遍历物品。
 * 如果把遍历nums（物品）放在外循环，遍历target的作为内循环的话，
 * 举一个例子：计算dp[4]的时候，结果集只有 {1,3} 这样的集合，
 * 不会有{3,1}这样的集合，因为nums遍历放在外层，3只能出现在1后面！
 * 所以本题遍历顺序最终遍历顺序：target（背包）放在外循环，将nums（物品）放在内循环，内循环从前到后遍历。
 */
public class Medium518_CoinChangeII {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
