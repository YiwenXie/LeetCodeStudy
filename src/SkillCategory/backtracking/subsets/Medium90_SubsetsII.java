package SkillCategory.backtracking.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Medium90_SubsetsII {
    List<List<Integer>> result;
    List<Integer> path;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        result = new ArrayList<>();
        path = new ArrayList<>();
        recursion(0, nums);
        return result;
    }

    private void recursion(int startIndex, int[] nums) {
        if (startIndex > nums.length) {
            return;
        }
        result.add(new ArrayList<>(path));
        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            recursion(i + 1, nums);
            path.remove(path.size() - 1);
        }
    }
}
