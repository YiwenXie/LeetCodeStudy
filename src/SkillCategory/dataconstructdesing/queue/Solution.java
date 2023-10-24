package SkillCategory.dataconstructdesing.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ywxie
 * @date 2022/7/27 17:03
 * @describe 单调队列，滑动窗口 describes
 */
public class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                //先填满窗口的前 k - 1
                window.push(nums[i]);
            } else {
                // 窗口向前滑动，加入新数字
                window.push(nums[i]);
                // 记录当前窗口的最大值
                res.add(window.max());
                // 移出旧数字
                window.pop(nums[i - k + 1]);
            }
        }
        // 需要转成 int[] 数组再返回
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    /*public int[] maxSlidingWindow2(int[] nums, int k) {
        if(nums==null||nums.length<2) {
            return nums;
        }
        MonotonicQueue queue = new MonotonicQueue();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) { // 先将前k的元素放进队列
            queue.push(nums[i]);
        }
        int j = 0;
        result[j++] = queue.front(); // result 记录前k的元素的最大值
        for (int i = k; i < nums.length; i++) {
            queue.pop(nums[i - k]); // 滑动窗口移除最前面元素
            queue.push(nums[i]); // 滑动窗口前加入最后面的元素
            result[j++] = queue.front(); // 记录对应的最大值
        }
        return result;
    }*/
}
