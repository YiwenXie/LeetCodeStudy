package SkillCategory.Sort.fast;

import SkillCategory.tree.TreeNode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author ywxie
 * @date 2022/6/1 15:40
 * @describe 快排
 * 快速排序是先将一个元素排好序，然后再将剩下的元素排好序。
 * 快速排序的核心无疑是 partition 函数， partition 函数的作用是在 nums[lo..hi] 中寻找一个分界点 p，
 * 通过交换元素使得 nums[lo..p-1] 都小于等于 nums[p]，且 nums[p+1..hi] 都大于 nums[p]：
 * 快速排序理想情况的时间复杂度是 O(NlogN)，空间复杂度 O(logN)，
 * 极端情况下的最坏时间复杂度是 O(N^2)，空间复杂度是 O(N)。
 * 「不稳定排序」
 */
public class Solution {

    /**
     * 912. 排序数组
     */
    public int[] sortArray(int[] nums) {
        // 快速排序对数组进行原地排序
        Quick quick = new Quick();
        quick.sort(nums);
        return nums;
    }

    /**
     * 快速排序
     */
    class Quick {

        public void sort(int[] nums) {
            // 为了避免出现耗时的极端情况，先随机打乱
            shuffle(nums);
            // 排序整个数组（原地修改）
            sort(nums, 0, nums.length - 1);
        }

        private void sort(int[] nums, int lo, int hi) {
            if (lo >= hi) {
                return;
            }
            // 对 nums[lo..hi] 进行切分
            // 使得 nums[lo..p-1] <= nums[p] < nums[p+1..hi]
            int p = partition(nums, lo, hi);

            sort(nums, lo, p - 1);
            sort(nums, p + 1, hi);
        }

        // 对 nums[lo..hi] 进行切分
        private int partition(int[] nums, int lo, int hi) {
            int pivot = nums[lo];
            // 关于区间的边界控制需格外小心，稍有不慎就会出错
            // 我这里把 i, j 定义为开区间，同时定义：
            // [lo, i) <= pivot；(j, hi] > pivot
            // 之后都要正确维护这个边界区间的定义
            int i = lo + 1, j = hi;
            // 当 i > j 时结束循环，以保证区间 [lo, hi] 都被覆盖
            while (i <= j) {
                while (i < hi && nums[i] <= pivot) {
                    i++;
                    // 此 while 结束时恰好 nums[i] > pivot
                }
                while (j > lo && nums[j] > pivot) {
                    j--;
                    // 此 while 结束时恰好 nums[j] <= pivot
                }
                // 此时 [lo, i) <= pivot && (j, hi] > pivot

                if (i >= j) {
                    break;
                }
                swap(nums, i, j);
            }
            // 将 pivot 放到合适的位置，即 pivot 左边元素较小，右边元素较大
            swap(nums, lo, j);
            return j;
        }

        // 洗牌算法，将输入的数组随机打乱
        private void shuffle(int[] nums) {
            Random rand = new Random();
            int n = nums.length;
            for (int i = 0 ; i < n; i++) {
                // 生成 [i, n - 1] 的随机数
                int r = i + rand.nextInt(n - i);
                swap(nums, i, r);
            }
        }

        // 原地交换数组中的两个元素
        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    /**
     * 215. 数组中的第K个最大元素
     */
    public int findKthLargest(int[] nums, int k) {
        //1.自有函数
//        Arrays.sort(nums);
//        return nums[nums.length - k];

        //2.二叉堆（优先级队列）大顶堆
//        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });
//        for (int num:nums
//             ) {
//            priorityQueue.add(num);
//        }
//        int i = 0;
//        int result = -1;
//        while (i < k){
//            result = priorityQueue.poll();
//            i++;
//        }
//        return result;

        // 3.小顶堆，堆顶是最小元素
        // Java 的 PriorityQueue 默认实现是小顶堆，有的语言的优先队列可能默认是大顶堆，可能需要做一些调整。
        // 二叉堆插入和删除的时间复杂度和堆中的元素个数有关，在这里我们堆的大小不会超过 k，
        // 所以插入和删除元素的复杂度是 O(logk)，再套一层 for 循环，
        // 假设数组元素总数为 N，总的时间复杂度就是 O(Nlogk)。
        // 空间复杂度很显然就是二叉堆的大小，为 O(k)。
//        PriorityQueue<Integer>
//                pq = new PriorityQueue<>();
//        for (int e : nums) {
//            // 每个元素都要过一遍二叉堆
//            pq.offer(e);
//            // 堆中元素多于 k 个时，删除堆顶元素
//            if (pq.size() > k) {
//                pq.poll();
//            }
//        }
        // pq 中剩下的是 nums 中 k 个最大元素，
        // 堆顶是最小的那个，即第 k 个最大元素
//        return pq.peek();

        // 4.快速选择排序
        // 时间复杂度为什么是 O(N)
        // 这个算法的时间复杂度也主要集中在 partition 函数上，我们需要估算 partition 函数执行了多少次，每次执行的时间复杂度是多少。
        // 最好情况下，每次 partition 函数切分出的 p 都恰好是正中间索引 (lo + hi) / 2（二分），
        // 且每次切分之后会到左边或者右边的子数组继续进行切分，那么 partition 函数执行的次数是 logN，每次输入的数组大小缩短一半。
        // 快速选择算法中的 partition 函数也可能出现极端情况，最坏情况下 p 一直都是 lo + 1 或者一直都是 hi - 1，这样的话时间复杂度就退化为 O(N^2)
        // 首先随机打乱数组
        Quick quick = new Quick();
        quick.shuffle(nums);
        int lo = 0, hi = nums.length - 1;
        // 转化成「排名第 k 的元素」
        k = nums.length - k;
        while (lo <= hi) {
            // 在 nums[lo..hi] 中选一个分界点
            int p = quick.partition(nums, lo, hi);
            if (p < k) {
                // 第 k 大的元素在 nums[p+1..hi] 中
                lo = p + 1;
            } else if (p > k) {
                // 第 k 大的元素在 nums[lo..p-1] 中
                hi = p - 1;
            } else {
                // 找到第 k 大元素
                return nums[p];
            }
        }
        return -1;
    }
}
