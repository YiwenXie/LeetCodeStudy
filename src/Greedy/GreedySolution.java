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

    /**
     * 376. 摆动序列
     * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。
     * 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
     * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。
     * 第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 0 || nums.length == 1){
            return nums.length;
        }
        int result = 0;
//        if (nums.length == 2){
//            return result;
//        }
        int index = 1; //快
        boolean positiveFlag = false;
//        List<Integer> list = new ArrayList<>();
        result++;
//        list.add(nums[0]);
        for (int i = 0; i < nums.length; i++){
            while (index < nums.length && nums[index] == nums[i]){
                index++;
            }
            if (index >= nums.length){
                break;
            }
            if (i == 0){
                positiveFlag = nums[index] - nums[i] > 0;
                result++;
//                list.add(nums[index]);
                index++;
            }
            if (i > 0 && nums[index] - nums[i] > 0 && !positiveFlag){
                positiveFlag = true;
//                list.add(nums[index]);
                result++;
                index++;
            }else if (i > 0 && nums[index] - nums[i] < 0 && positiveFlag){
                positiveFlag = false;
//                list.add(nums[index]);
                result++;
                index++;
            }
        }
//        return list.size();
        return result;
    }

    // 另一种解法
    public int wiggleMaxLength2(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int curDiff = 0; // 当前一对差值
        int preDiff = 0; // 前一对差值
        int result = 1;  // 记录峰值个数，序列默认序列最右边有一个峰值
        for (int i = 1; i < nums.length; i++) {
            curDiff = nums[i] - nums[i - 1];
            // 出现峰值
            if ((curDiff > 0 && preDiff <= 0) || (preDiff >= 0 && curDiff < 0)) {
                result++;
                preDiff = curDiff;
            }
        }
        return result;
    }

    /**
     * 53. 最大子序和
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int sum = Integer.MIN_VALUE;
        // 1.暴力解法
        // 思路:第一层for 就是设置起始位置，第二层for循环遍历数组寻找最大值
        // 时间复杂度：O(n^2) 空间复杂度：O(1)
        for (int i = 0; i < nums.length; i++){
            int temp = 0;
            for (int j = i; j < nums.length; j++){
                temp += nums[j];
                sum = Math.max(temp, sum);
            }
        }

        // 2.贪心算法
        // 思路：如果 -2 1 在一起，计算起点的时候，一定是从1开始计算，因为负数只会拉低总和，这就是贪心贪的地方！
        // 局部最优：当前“连续和”为负数的时候立刻放弃，从下一个元素重新计算“连续和”，因为负数加上下一个元素 “连续和”只会越来越小。
        // 全局最优：选取最大“连续和”
        // 时间复杂度：O(n) 空间复杂度：O(1)
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            count += nums[i];
            sum = Math.max(sum, count); // 取区间累计的最大值（相当于不断确定最大子序终止位置）
            if (count <= 0){
                count = 0; // 相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
            }
        }
        return sum;

        // 3.其它解法
//        int pre = 0, maxAns = nums[0];
//        for (int x : nums) {
//            pre = Math.max(pre + x, x);
//            maxAns = Math.max(maxAns, pre);
//        }
//        return maxAns;
    }

}
