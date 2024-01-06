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
        // find the point which elements of left side are equals or smaller than point and elements of right side are larger than point
        int p = partition(nums, lo, hi);
        // make nums[lo, p - 1] all equals or smaller than point
        fastSort(nums, lo, p - 1);
        // make nums[p + 1, hi] all larger than point
        fastSort(nums, p + 1, hi);
    }

    private int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        int i = lo + 1;
        int j = hi;
        // to make nums[lo, i) <= pivot < nums(j, hi]
        while (i <= j) {
            // when end, nums[i] > pivot
            while (i < hi && nums[i] <= pivot) {
                i++;
            }
            // when end, nums[j] <= pivot
            while (j > lo && nums[j] > pivot) {
                j--;
            }
            if (i >= j) {
                break;
            }
            // when two end, nums[j] <= pivot < nums[i]
            // so need to swap nums[j] and nums[i]
            // to make nums[lo, i) <= pivot < nums(j, hi]
            swap(nums, i, j);
        }
        // when end, i > j or i >= j
        // need to mark put p to right position
        swap(nums, lo, j);
        // p's final position
        return j;
    }
}
