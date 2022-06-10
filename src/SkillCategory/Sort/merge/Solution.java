package SkillCategory.Sort.merge;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ywxie
 * @date 2022/6/6 15:00
 * @describe 归并排序
 * 时间复杂度：O(NlogN)
 */
public class Solution {
    /**
     * 时间复杂度：O(NlogN)
     * 递归算法复杂度计算公式：子问题个数 X 解决一个子问题的复杂度
     * 时间复杂度集中于merge函数：
     *  执行的次数：二叉树节点的个数
     *  每次执行的复杂度：每个节点代表的子数组的长度
     *  总的时间复杂度：整棵树中【数组元素】的个数
     */
    class Merge{
        // 用于辅助合并有序数组
        int[] temp;

        void sort(int[] nums){
            // 先给辅助数组开辟内存空间，避免递归中频繁分配和释放内存可能产生的性能问题
            temp = new int[nums.length];
            sort(nums, 0, nums.length - 1);
        }

        void sort(int[] nums, int lo, int hi){
            if(lo >= hi){
                return;
            }
            int mid = lo + (hi - lo) / 2;
            sort(nums, lo, mid);
            sort(nums, mid + 1, hi);
            merge(nums, lo, mid, hi);

        }

        void merge(int[] nums, int lo, int mid, int hi){
            for (int i = lo; i <= hi; i++) {
                temp[i] = nums[i];
            }
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) {
                if (i == mid + 1){
                    nums[k] = temp[j++];
                }else if (j == hi + 1){
                    nums[k] = temp[i++];
                }else if (temp[i] > temp[j]){
                    nums[k] = temp[j++];
                }else {
                    nums[k] = temp[i++];
                }
            }
        }
    }

    /**
     * 912. 排序数组
     */
    public int[] sortArray(int[] nums){
        Merge merge = new Merge();
        merge.sort(nums);
        return nums;
    }

    /**
     * 315. 计算右侧小于当前元素的个数
     */
    List<Integer> countSmaller(int[] nums){
        Merge2 merge = new Merge2();
        merge.sort(nums);
        int[] count = merge.count;
        List<Integer> list = new LinkedList<>();
        for (int c: count
        ) {
            list.add(c);
        }
        return list;
    }

    class Merge2{
        class Pair{
            int val;
            int index;
            public Pair(int val, int index) {
                // 记录数组的元素值
                this.val = val;
                // 记录元素在数组中的索引值
                this.index = index;
            }
        }
        // 用于辅助合并有序数组
        Pair[] temp;
        // 记录每个元素后面比自己小的元素的个数
        int[] count;

        void sort(int[] nums){
            int n = nums.length;
            // 先给辅助数组开辟内存空间，避免递归中频繁分配和释放内存可能产生的性能问题
            temp = new Pair[n];
            count = new int[n];
            Pair[] arr = new Pair[n];
            // 记录元素原始的索引位置，以便在 count 数组中更新结果
            for (int i = 0; i < n; i++) {
                arr[i] = new Pair(nums[i], i);
            }
            sort(arr, 0, n - 1);
        }

        void sort(Pair[] arr, int lo, int hi){
            if(lo >= hi){
                return;
            }
            int mid = lo + (hi - lo) / 2;
            sort(arr, lo, mid);
            sort(arr, mid + 1, hi);
            merge(arr, lo, mid, hi);

        }

        void merge(Pair[] arr, int lo, int mid, int hi){
            for (int i = lo; i <= hi; i++) {
                temp[i] = arr[i];
            }
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) {
                if (i == mid + 1){
                    arr[k] = temp[j++];
                }else if (j == hi + 1){
                    arr[k] = temp[i++];
                    count[arr[k].index] += j - mid - 1;
                }else if (temp[i].val > temp[j].val){
                    arr[k] = temp[j++];
                }else {
                    arr[k] = temp[i++];
                    count[arr[k].index] += j - mid - 1;
                }
            }
        }
    }


    /**
     * 493.翻转对
     */
    public int reversePairs(int[] nums) {
        Merge3 merge3 = new Merge3();
        // 执行归并排序
        merge3.sort(nums);
        return merge3.count;
    }

    class Merge3{
        // 用于辅助合并有序数组
        int[] temp;
        // 记录翻转对的个数
        int count = 0;

        void sort(int[] nums){
            // 先给辅助数组开辟内存空间，避免递归中频繁分配和释放内存可能产生的性能问题
            temp = new int[nums.length];
            sort(nums, 0, nums.length - 1);
        }

        void sort(int[] nums, int lo, int hi){
            if(lo >= hi){
                return;
            }
            int mid = lo + (hi - lo) / 2;
            sort(nums, lo, mid);
            sort(nums, mid + 1, hi);
            merge(nums, lo, mid, hi);

        }

        void merge(int[] nums, int lo, int mid, int hi){
            for (int i = lo; i <= hi; i++) {
                temp[i] = nums[i];
            }

            // 解法1.这段代码提交会超时，毕竟额外添加了一个嵌套 for 循环，需优化
//            // 在合并有序数组之前，加点私货
//            for (int i = lo; i <= mid; i++) {
//                // 对于左半边的每个 nums[i]，都去右半边寻找符合条件的元素
//                for (int j = mid + 1; j <= hi; j++) {
//                    // nums 中的元素可能较大，乘 2 可能溢出，所以转化成 long
//                    if ((long)nums[i] > (long)nums[j] * 2) {
//                        count++;
//                    }
//                }
//            }

            // 解法2：
            // 注意子数组 nums[lo..mid] 是排好序的，也就是 nums[i] <= nums[i+1]。所以，对于 nums[i], lo <= i <= mid，
            //我们在找到的符合 nums[i] > 2*nums[j] 的 nums[j], mid+1 <= j <= hi，也必然也符合 nums[i+1] > 2*nums[j]。
            // 进行效率优化，维护左闭右开区间 [mid+1, end) 中的元素乘 2 小于 nums[i]
            // 为什么 end 是开区间？因为这样的话可以保证初始区间 [mid+1, mid+1) 是一个空区间
            int end = mid + 1;
            for (int i = lo; i <= mid; i++) {
                // nums 中的元素可能较大，乘 2 可能溢出，所以转化成 long
                while (end <= hi && (long)nums[i] > (long)nums[end] * 2) {
                    end++;
                }
                count += end - (mid + 1);
            }

            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) {
                if (i == mid + 1){
                    nums[k] = temp[j++];
                }else if (j == hi + 1){
                    nums[k] = temp[i++];
                }else if (temp[i] > temp[j]){
                    nums[k] = temp[j++];
                }else {
                    nums[k] = temp[i++];
                }
            }
        }
    }


    /**
     * 327.区间和的个数
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        Merge4 merge4 = new Merge4();
        merge4.sort(nums, lower, upper);
        return merge4.count;
    }

    class Merge4{

        // 用于辅助合并有序数组
        long[] temp;
        // 记录区间和的个数
        int count = 0;
        int lower;
        int upper;

        int sort(int[] nums, int lower, int upper){
            this.lower = lower;
            this.upper = upper;
            int n = nums.length;
            // 先给辅助数组开辟内存空间，避免递归中频繁分配和释放内存可能产生的性能问题
            temp = new long[n + 1];
            // 构建前缀和数组，注意 int 可能溢出，用 long 存储
            long[] preSum = new long[n + 1];
            for (int i = 1; i < n + 1; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }
            sort(preSum,0, n);
            return count;
        }

        void sort(long[] nums, int lo, int hi){
            if(lo >= hi){
                return;
            }
            int mid = lo + (hi - lo) / 2;
            sort(nums, lo, mid);
            sort(nums, mid + 1, hi);
            merge(nums, lo, mid, hi);

        }

        void merge(long[] nums, int lo, int mid, int hi){
            for (int i = lo; i <= hi; i++) {
                temp[i] = nums[i];
            }

            // 在合并有序数组之前加点私货（这段代码会超时）
            // for (int i = lo; i <= mid; i++) {
            //     for (int j = mid + 1; j <= hi; k++) {
            //         // 寻找符合条件的 nums[j]
            //         long delta = nums[j] - nums[i];
            //         if (delta <= upper && delta >= lower) {
            //             count++;
            //         }
            //     }
            // }

            // 进行效率优化
            // 维护左闭右开区间 [start, end) 中的元素和 nums[i] 的差在 [lower, upper] 中
            int start = mid + 1, end = mid + 1;
            for (int i = lo; i <= mid; i++) {
                // 如果 nums[i] 对应的区间是 [start, end)，
                // 那么 nums[i+1] 对应的区间一定会整体右移，类似滑动窗口
                while (start <= hi && nums[start] - nums[i] < lower) {
                    start++;
                }
                while (end <= hi && nums[end] - nums[i] <= upper) {
                    end++;
                }
                count += end - start;
            }

            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) {
                if (i == mid + 1){
                    nums[k] = temp[j++];
                }else if (j == hi + 1){
                    nums[k] = temp[i++];
                }else if (temp[i] > temp[j]){
                    nums[k] = temp[j++];
                }else {
                    nums[k] = temp[i++];
                }
            }
        }
    }




}
