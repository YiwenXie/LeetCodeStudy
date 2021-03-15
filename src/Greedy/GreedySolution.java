package Greedy;

import java.util.Arrays;

/**
 * @author ywxie
 * @date 2021/3/15 10:06
 * @describe 贪心算法
 * 以局部最优推出整体最优，而且想不到反例，那么就试一试贪心
 *
 * 贪心算法一般分为如下四步：
 *     将问题分解为若干个子问题
 *     找出适合的贪心策略
 *     求解每一个子问题的最优解
 *     将局部最优解堆叠成全局最优解
 *
 * 贪心没有套路，说白了就是常识性推导加上举反例。
 */
public class GreedySolution {

    /**
     * 455. 分发饼干
     * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。
     * 如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
     * 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     * [10,9,8,7]
     * [5,6,7,8]
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0;
        // 方案一
        int index = 0; //记录上一次被分配的饼干下标
        for (int i = 0; i < g.length; i++){
            while (index < s.length){
                if (s[index] >= g[i]){
                    result++;
                    index++;
                    break;
                }
                index++;
            }
            // 其实这里是优化操作呢，这样就不用全部遍历完孩子了。
            // 因为找完了所有饼干都不满足孩子的胃口
            if (index >= s.length){
                return result;
            }
        }
        // 方案二 双指针法
//        int index = s.length - 1; //饼干下标
//        for (int i = g.length - 1; i >= 0; i--){
//            if (index >= 0 && s[index] <= g[i]){
//                result++;
//                index--;
//            }
//        }
        return result;
    }
}
