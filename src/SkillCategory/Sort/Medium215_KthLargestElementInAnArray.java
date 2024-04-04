package SkillCategory.Sort;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class Medium215_KthLargestElementInAnArray {
    /**
     * priority Queue
     * Time complexity: O(nlogk) (数组元素总数为 N,堆的大小不会超过 k)
     * Space complexity: O(k)
     *
     * @param nums -10^4 <= nums[i] <= 10^4
     * @param k    1 <= k <= nums.length <= 10^5
     * @return the kth largest element in the sorted order
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num : nums) {
            priorityQueue.add(num);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
            // nums = [3,2,1,5,6,4], k = 2
            // 1,2,3 -> 1
            // 2,3,5 -> 2
            // 3,5,6 -> 3
            // 4,5,6 -> 4
            // 5,6 -> loop end -> return 5;
        }
        return priorityQueue.poll();
    }

    /**
     * sort
     * Time complexity: O(nlogN + k)
     * Space complexity: O(1)
     *
     * @param nums -10^4 <= nums[i] <= 10^4
     * @param k    1 <= k <= nums.length <= 10^5
     * @return the kth largest element in the sorted order
     */
    public int findKthLargest2(int[] nums, int k) {
        Arrays.sort(nums);
        int result = 0;
        int n = nums.length - 1;
        while (k > 0) {
            result = nums[n--];
            k--;
            // nums = [3,2,1,5,6,4], k = 2
            // sorted nums = [1,2,3,4,5,6]
            // k = 1, n = 5, result = 6
            // k = 0, n = 4, result = 5
        }
        return result;
    }

    /**
     * Quick Select sort
     * 快速选择算法是快速排序的变体，效率更高
     * Time complexity: O(n)
     * 最好情况下，每次 partition 函数切分出的 p 都恰好是正中间索引 (lo + hi) / 2（二分），且每次切分之后会到左边或者右边的子数组继续进行切分，
     * 那么 partition 函数执行的次数是 logN，每次输入的数组大小缩短一半。
     * 所以总的时间复杂度为：等差数列
     * N + N/2 + N/4 + N/8 + ... + 1 = 2N = O(N)
     * 最坏情况下 p 一直都是 lo + 1 或者一直都是 hi - 1，这样的话时间复杂度就退化为 O(N^2) 了：
     * N + (N - 1) + (N - 2) + ... + 1 = O(N^2)
     * <p>
     * Space complexity: O(1)
     *
     * @param nums -10^4 <= nums[i] <= 10^4
     * @param k    1 <= k <= nums.length <= 10^5
     * @return the kth largest element in the sorted order
     * 「第 k 个最大的元素」，相当于数组升序排序后「排名第 n - k 的元素」，为了方便表述，后文另 k' = n - k。
     */
    public int findKthLargest3(int[] nums, int k) {
        /*
        使用 shuffle 函数的原因，通过引入随机性来避免极端情况的出现，让算法的效率保持在比较高的水平。
        随机化之后的快速选择算法的复杂度可以认为是 O(N)。
         */
        shuffle(nums);
        int lo = 0;
        int hi = nums.length - 1;
        // nums = [3,2,1,5,6,4], k = 2
        // k2 = 6 - 2 = 4
        k = nums.length - k;
        while (lo <= hi) {
            int p = partition(nums, lo, hi);
            if (p > k) {
                hi = p - 1;
            } else if (p < k) {
                lo = p + 1;
            } else {
                return nums[p];
            }
        }
        return -1;
    }

    private void shuffle(int[] nums) {
        Random random = new Random();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // need [i, n - 1]
            // random.nextInt(n - i) = [0, n - i - 1]
            // i + random.nextInt(n - i) = i + [0, n - i - 1] = [i, n - 1]
            int j = i + random.nextInt(n - i);
            swap(nums, i, j);
        }
    }

    private void fastSort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        // 对 nums[lo..hi] 进行切分
        // 使得 nums[lo..p-1] <= nums[p] < nums[p+1..hi]
        int p = partition(nums, lo, hi);
        // make nums[lo, p - 1] all equals or smaller than point
        fastSort(nums, lo, p - 1);
        // make nums[p + 1, hi] all larger than point
        fastSort(nums, p + 1, hi);
    }

    private int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        // [lo, i) <= pivot；(j, hi] > pivot
        int i = lo + 1;
        int j = hi;
        // to make nums[lo + 1, p - 1] <= pivot < nums[p + 1, hi]
        // // 当 i > j 时结束循环，以保证区间 [lo, hi] 都被覆盖
        while (i <= j) {
            // when end, nums[i] > pivot or i = hi
            while (i < hi && nums[i] <= pivot) {
                // 此 while 结束时恰好 nums[i] > pivot
                i++;
            }
            // when end, nums[j] <= pivot or j = lo
            while (j > lo && nums[j] > pivot) {
                // 此 while 结束时恰好 nums[j] <= pivot
                j--;
            }
            // 此时 [lo, i) <= pivot && (j, hi] > pivot
            if (i >= j) {
                break;
            }
            // when two end, nums[j] <= pivot < nums[i]
            // so need to swap nums[j] and nums[i]
            // to make nums[lo + 1, i] <= pivot < nums[j, hi]
            swap(nums, i, j);
        }
        // when end i >= j, nums[j] <= pivot, so can swap lo and j
        // to make nums[lo + 1, i] <= pivot < nums[j, hi]
        swap(nums, lo, j);
        // p's final position
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
