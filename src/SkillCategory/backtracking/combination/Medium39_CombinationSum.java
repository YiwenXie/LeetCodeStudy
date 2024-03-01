package SkillCategory.backtracking.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Medium39_CombinationSum {
    List<List<Integer>> result;
    List<Integer> path;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        result = new ArrayList<>();
        path = new ArrayList<>();
        Arrays.sort(candidates);
        recursion(0, 0, candidates, target);
        return result;
    }

    private void recursion(int startIndex, int sum, int[] candidates, int target) {
        if (candidates[startIndex] > target || sum > target) {
            return;
        }
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        // iterate over the range from startIndex
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {
            path.add(candidates[i]);
            recursion(i, sum + candidates[i], candidates, target);
            // backtracking
            path.remove(path.size() - 1);
        }
    }
}
