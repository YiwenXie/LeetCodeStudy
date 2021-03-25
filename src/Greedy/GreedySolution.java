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
     * 其关键在于：「不能让“连续和”为负数的时候加上下一个元素，而不是 不让“连续和”加上一个负数」。
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

        // 4.动态规划
        // 时间复杂度：O(n) 空间复杂度：O(n)
//        int[] dp = new int[nums.length];// dp[i]表示包括i之前的最大连续子序列和
//        dp[0] = nums[0];
//        int result = dp[0];
//        for (int i = 1; i < nums.length; i++) {
//            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]); // 状态转移公式
//            result = Math.max(result, dp[i]);// result 保存dp[i]的最大值
//        }
//        return result;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     *
     *     只收集正利润就是贪心所贪的地方！
     * 「局部最优：收集每天的正利润，全局最优：求得最大利润」。
     * 「本题中理解利润拆分是关键点！」 不要整块的去看，而是把整体利润拆为每天的利润。
     * 一旦想到这里了，很自然就会想到贪心了，即：只收集每天的正利润，最后稳稳的就是最大利润了。
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result += Math.max(prices[i] - prices[i - 1], 0);
        }
        return result;
    }

    /**
     * 55. 跳跃游戏
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个下标。
     *
     * 「贪心算法局部最优解：每次取最大跳跃步数（取最大覆盖范围），整体最优解：最后得到整体最大覆盖范围，看是否能到终点」。
     * 这道题目关键点在于：不用拘泥于每次究竟跳跳几步，而是看覆盖范围，覆盖范围内已经是可以跳过来的，不用管是怎么跳的。
     */
    public boolean canJump(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }
        if (nums[0] <= 0){
            return false;
        }
        int step = 0;
        for (int i = 0; i <= step; i++){
            step = Math.max(i + nums[i], step);
            if (step >= nums.length - 1){
                return true;
            }
        }
        return false;
    }

    /**
     * 45. 跳跃游戏 II
     *
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     *
     * 「所以真正解题的时候，要从覆盖范围出发，不管怎么跳，覆盖范围内一定是可以跳到的，以最小的步数增加覆盖范围，
     * 覆盖范围一旦覆盖了终点，得到的就是最小步数！」
     * 「这里需要统计两个覆盖范围，当前这一步的最大覆盖和下一步最大覆盖」。
     * 如果移动下标达到了当前这一步的最大覆盖最远距离了，还没有到终点的话，那么就必须再走一步来增加覆盖范围，直到覆盖范围覆盖了终点。
     */
    public static int jump(int[] nums) {
        if (nums.length <= 1){
            return 0;
        }
        int result = 0;
        int next = 0;// 下一步覆盖最远距离下标
        int cur = 0;// 当前覆盖最远距离下标
        // 方法一
//        for(int i = 0; i < nums.length; i++){
//            next = Math.max(nums[i] + i, next); // 更新下一步覆盖最远距离下标
//            if (i == cur){ // 遇到当前覆盖最远距离下标
//                if (cur != nums.length - 1){// 如果当前覆盖最远距离下标不是终点
//                    result++;// 需要走下一步
//                    cur = next;// 更新当前覆盖最远距离下标（相当于加油了）
//                    if (next >= nums.length - 1){// 下一步的覆盖范围已经可以达到终点，结束循环
//                        break;
//                    }
//                }else {
//                    break;// 当前覆盖最远距离下标是集合终点，不用做ans++操作了，直接结束
//                }
//            }
//        }
        // 方法二
        for (int i = 0; i < nums.length - 1; i++) { // 注意这里是小于nums.length - 1，这是关键所在
            next = Math.max(nums[i] + i, next); // 更新下一步覆盖的最远距离下标
            if (i == cur) {                 // 遇到当前覆盖的最远距离下标
                cur = next;         // 更新当前覆盖的最远距离下标
                result++;
            }
        }
        return result;
    }

    /**
     * 1005. K 次取反后最大化的数组和
     * 给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个索引 i 并将 A[i] 替换为 -A[i]，
     * 然后总共重复这个过程 K 次。（我们可以多次选择同一个索引 i。）
     * 以这种方式修改数组后，返回数组可能的最大和。
     */
    public int largestSumAfterKNegations(int[] A, int K) {
        Arrays.sort(A);// 从小到大排序
        int result = 0;
        int index = Integer.MIN_VALUE; // 记录最后一个负数的下标
        for (int i = 0; i < A.length; i++){
            if (A[i] < 0){
                if (K > 0){
                    A[i] = -A[i];
                    K--;
                }
                result += A[i];
                index = i;
                continue;
            }
            if (A[i] == 0 && K > 0){
                K = 0;
                continue;
            }
            if (A[i] > 0 && K >= 0) {
                if (K % 2 != 0) {
                    if (index >= 0 && index <= i) {
                        if (-A[index] > -A[i]) {
                            result = result - A[index];
                            A[index] = -A[index];
                            result += A[index];
                        } else {
                            A[i] = -A[i];
                        }
                    } else {
                        A[i] = -A[i];
                    }
                }
                K = 0;
                result += A[i];
            }

//                while (K > 0){
//                    if (index >= 0 && index <= i){
//                        if (-A[index] > -A[i]){
//                            result = result - A[index];
//                            A[index] = -A[index];
//                            result += A[index];
//                        }else {
//                            A[i] = -A[i];
//                        }
//                    }else {
//                        A[i] = -A[i];
//                    }
//                    K--;
//                }
//                result += A[i];
        }
        return result;
    }

    /**
     * 134. 加油站
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        //方法二，贪心
        // 「那么局部最优：当前累加rest[j]的和curSum一旦小于0，起始位置至少要是j+1，
        // 因为从j开始一定不行。全局最优：找到可以跑一圈的起始位置」。
        int curSum = 0;
        int totalSum = 0;
        int start = -1;
        for (int i = 0; i < gas.length; i++){
            curSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (curSum < 0){
                start = i + 1;
                curSum = 0;
            }
        }
        if (totalSum < 0){
            return -1;
        }
        return start;
        // 方法一，非贪心
//        int curSum = 0;
//        int min = Integer.MAX_VALUE;// 从起点出发，油箱里的油量最小值
//        for (int i = 0; i < gas.length; i++){
//            int rest = gas[i] - cost[i];
//            curSum += rest;
//            min = Math.min(curSum, min);
//        }
//        // 1.如果gas的总和小于cost总和，那么无论从哪里出发，一定是跑不了一圈的
//        if (curSum < 0){
//            return -1;
//        }
//        // 2.rest[i] = gas[i]-cost[i]为一天剩下的油，i从0开始计算累加到最后一站，
//        // 如果累加没有出现负数，说明从0出发，油就没有断过，那么0就是起点。
//        if (min >= 0){
//            return 0;
//        }
//        // 3.如果累加的最小值是负数，汽车就要从非0节点出发，
//        // 从后向前，看哪个节点能这个负数填平，能把这个负数填平的节点就是出发节点。
//        for (int i = gas.length - 1; i >= 0; i--){
//            int rest = gas[i] - cost[i];
//            min += rest;
//            if (min >= 0){
//                return i;
//            }
//        }
//        return -1;
        //我自己：超出时间限制了
//        int result = -1;
//        for (int i = 0; i < gas.length; i++){
//            if (gas[i] - cost[i] < 0){
//                result = -1;
//                continue;
//            }
//            int rest = gas[i];
//            result = i;
//            int start;
//            int end = i;
//            for (int j = i + 1; j != i; j++){
//                if (j >= gas.length){
//                    j = 0;
//                    start = gas.length - 1;
//                }else {
//                    start = j - 1;
//                }
//                if (rest - cost[start] < 0){
//                    result = -1;
//                    break;
//                }
//                rest = rest - cost[start] + gas[j];
//                end = j;
//            }
//            if (result != -1){
//                rest = rest - cost[end];
//                if (rest >= 0){
//                    return result;
//                }else{
//                    result = -1;
//                }
//            }
//        }
//        return result;
    }

    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        System.out.println(canCompleteCircuit(gas, cost));
    }

}
