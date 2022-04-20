package SkillCategory.DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ywxie
 * @date 2020/12/9 13:49
 * @describe
 */
public class simple {

    /**
     * 509. 斐波那契数
     * 递归 O(2^n)
     */
    public int fib(int N) {
        if(N == 0){
            return 0;
        }
        if(N == 1 || N == 2){
            return 1;
        }
        return fib(N - 1) + fib (N - 2);
    }

    /**
     * 509. 斐波那契数
     * 递归\时间复杂度是 O(n) 状态转移方程
     */
    public int fib2(int N) {
        if(N == 0){
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        return doFib(N, map);
    }

    private int doFib(int N, Map<Integer, Integer> map){
        if(N == 1 || N == 2){
            return 1;
        }
        if (map.containsKey(N)){
            return map.get(N);
        }
        map.put(N,doFib(N-1, map) + doFib(N-2, map));
        return map.get(N);
    }

    /**
     * 509. 斐波那契数
     * 递归\时间复杂度是 O(n) 空间复杂度降为 O(1) 「状态压缩」
     */
    public int fib3(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 2 || N == 1) {
            return 1;
        }
        int prev = 1, curr = 1;
        for (int i = 3; i <= N; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;
    }

    /**
     * 322. 零钱兑换
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     * 你可以认为每种硬币的数量是无限的。
     * 首先，这个问题是动态规划问题，因为它具有「最优子结构」的。要符合「最优子结构」，子问题间必须互相独立。
     * 如何列出正确的状态转移方程
     * 1、确定 base case，这个很简单，显然目标金额 amount 为 0 时算法返回 0，因为不需要任何硬币就已经凑出目标金额了。
     * 2、确定「状态」，也就是原问题和子问题中会变化的变量。由于硬币数量无限，硬币的面额也是题目给定的，只有目标金额会不断地向 base case 靠近，所以唯一的「状态」就是目标金额 amount。
     * 3、确定「选择」，也就是导致「状态」产生变化的行为。目标金额为什么变化呢，因为你在选择硬币，你每选择一枚硬币，就相当于减少了目标金额。所以说所有硬币的面值，就是你的「选择」。
     * 4、明确 dp 函数/数组的定义。我们这里讲的是自顶向下的解法，所以会有一个递归的 dp 函数，一般来说函数的参数就是状态转移中会变化的量，也就是上面说到的「状态」；函数的返回值就是题目要求我们计算的量。就本题来说，状态只有一个，即「目标金额」，题目要求我们计算凑出目标金额所需的最少硬币数量。所以我们可以这样定义 dp 函数：
     * dp(n) 的定义：输入一个目标金额 n，返回凑出目标金额 n 的最少硬币数量。
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0){
            return 0;
        }
        if (amount < 0){
            return -1;
        }
        return 0;
    }

}
