package SkillCategory.backtracking.permutation;

import java.util.ArrayList;
import java.util.List;

public class Medium47_PermutationsII {
    public static void main(String[] args) {

    }

    List<List<Integer>> result;
    List<Integer> path;

    public List<List<Integer>> permuteUnique(int[] nums) {
        result = new ArrayList<>();
        path = new ArrayList<>();
        recursion(new boolean[nums.length], nums);
        return result;
    }

    private void recursion(boolean[] usedIndex, int[] nums) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        boolean[] usedInSameLayer = new boolean[21];
        for (int i = 0; i < nums.length; i++) {
            if (usedInSameLayer[nums[i] + 10] || usedIndex[i]) {
                continue;
            }
            path.add(nums[i]);
            usedInSameLayer[nums[i] + 10] = true;
            usedIndex[i] = true;
            recursion(usedIndex, nums);
            path.remove(path.size() - 1);
            usedIndex[i] = false;
        }
    }
}
