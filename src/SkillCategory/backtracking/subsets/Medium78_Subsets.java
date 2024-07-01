package SkillCategory.backtracking.subsets;

import java.util.ArrayList;
import java.util.List;

public class Medium78_Subsets {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        backtracking(0, nums);
        return result;
    }

    private void backtracking(int startIndex, int[] nums) {
        result.add(new ArrayList<>(path));
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            backtracking(i + 1, nums);
            path.remove(path.size() - 1);
        }
    }
}
