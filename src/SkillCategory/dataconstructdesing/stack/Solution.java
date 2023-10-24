package SkillCategory.dataconstructdesing.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author ywxie
 * @date 2022/6/29 21:58
 * @describe 单调栈
 */
public class Solution {

    /**
     * 单调栈模板
     * 分析它的时间复杂度，要从整体来看：总共有 n 个元素，每个元素都被 push 入栈了一次，而最多会被 pop 一次，没有任何冗余操作。
     * 所以总的计算规模是和元素规模 n 成正比的，也就是 O(n) 的复杂度。
     */
    int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[n];
        /* for 循环要从后往前扫描元素，
        因为我们借助的是栈的结构，倒着入栈，其实是正着出栈。while 循环是把两个「个子高」元素之间的元素排除，
        因为他们的存在没有意义，前面挡着个「更高」的元素，
        所以他们不可能被作为后续进来的元素的下一个更大元素了。*/
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }

    /**
     * 496. 下一个更大元素 I
     * 因为题目说 nums1 是 nums2 的子集，
     * 那么我们先把 nums2 中每个元素的下一个更大元素算出来存到一个映射里，
     * 然后再让 nums1 中的元素去查表即可
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 记录 nums2 中每个元素的下一个更大元素
        int[] greater = nextGreaterElement(nums2);
        // 转化成映射：元素 x -> x 的下一个最大元素
        HashMap<Integer, Integer> greaterMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            greaterMap.put(nums2[i], greater[i]);
        }
        // nums1 是 nums2 的子集，所以根据 greaterMap 可以得到结果
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = greaterMap.get(nums1[i]);
        }
        return res;
    }

    /**
     * 739. 每日温度
     * 所求的是索引间距，所以将索引入栈，而不是元素
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        // 这里放元素索引，而不是元素
        Stack<Integer> s = new Stack<>();
        /* 单调栈模板 */
        for (int i = n - 1; i >= 0; i--) {
            while (!s.isEmpty() && temperatures[s.peek()] <= temperatures[i]) {
                s.pop();
            }
            // 得到索引间距
            res[i] = s.isEmpty() ? 0 : (s.peek() - i);
            // 将索引入栈，而不是元素
            s.push(i);
        }
        return res;
    }

    /**
     * 503. 下一个更大元素 II
     * 套路就是将数组长度翻倍，通过 % 运算符求模（余数），来模拟环形特效
     * int[] arr = {1,2,3,4,5};
     * int n = arr.length, index = 0;
     * while (true) {
     * // 在环形数组中转圈
     * print(arr[index % n]);
     * index++;
     * }
     * 时间复杂度 O(N)
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> s = new Stack<>();
        // 数组长度加倍模拟环形数组
        for (int i = 2 * n - 1; i >= 0; i--) {
            // 索引 i 要求模，其他的和模板一样
            while (!s.isEmpty() && s.peek() <= nums[i % n]) {
                s.pop();
            }
            res[i % n] = s.isEmpty() ? -1 : s.peek();
            s.push(nums[i % n]);
        }
        return res;
    }
}
