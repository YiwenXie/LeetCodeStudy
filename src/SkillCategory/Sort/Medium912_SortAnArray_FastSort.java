package SkillCategory.Sort;

import java.util.Random;

public class Medium912_SortAnArray_FastSort {

    public int[] sortArray(int[] nums) {
        shuffle(nums);
        fastSort(nums, 0, nums.length - 1);
        return nums;
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

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void fastSort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        // 对 nums[lo..hi] 进行切分
        // to make nums[lo, p - 1] < pivot <= nums[p + 1, hi]
//        int p = partition(nums, lo, hi);
        // 使得 nums[lo..p-1] <= nums[p] < nums[p+1..hi]
        int p = partition2(nums, lo, hi);
        // make nums[lo, p - 1] all equals or smaller than point
        fastSort(nums, lo, p - 1);
        // make nums[p + 1, hi] all larger than point
        fastSort(nums, p + 1, hi);
    }

    private int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        int i = lo + 1;
        int j = hi;
        // to make nums[lo + 1, i] < pivot <= nums[j, hi]
        while (i <= j) {
            // when end, nums[i] >= pivot
            while (i < hi && nums[i] < pivot) {
                i++;
            }
            // when end, nums[j] < pivot
            while (j > lo && nums[j] >= pivot) {
                j--;
            }
            // i >= j => all sorted
            if (i >= j) {
                break;
            }
            // when two end, nums[j] < pivot <= nums[i]
            // so need to swap nums[j] and nums[i]
            // to make nums[lo, i] < pivot <= nums[j, hi]
            swap(nums, i, j);
        }
        // when end i >= j, nums[j] < pivot
        // to make nums[lo + 1, i] < pivot <= nums[j, hi]
        swap(nums, lo, j);
        // p's final position
        return j;
    }

    private int partition2(int[] nums, int lo, int hi) {
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
}
