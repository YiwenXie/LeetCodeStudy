package SkillCategory.backtracking.permutation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Medium46_Permutations {
    List<List<Integer>> result;
    List<Integer> path;
    Set<Integer> set;

    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        path = new ArrayList<>();
        set = new HashSet<>();

        recursion(nums);
        recursion2(new boolean[nums.length], nums);
        return result;
    }

    /**
     * Time complexity: O(n * log n )
     * Space complexity: O(n)
     *
     * @param nums
     */
    private void recursion(int[] nums) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(i)) {
                continue;
            }
            path.add(nums[i]);
            set.add(i);
            recursion(nums);
            path.remove(path.size() - 1);
            set.remove(i);
        }
    }

    private void recursion2(boolean[] used, int[] nums) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            recursion2(used, nums);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
