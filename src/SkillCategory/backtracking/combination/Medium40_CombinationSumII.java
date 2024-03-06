package SkillCategory.backtracking.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Medium40_CombinationSumII {
    List<List<Integer>> result;
    List<Integer> path;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        result = new ArrayList<>();
        path = new ArrayList<>();
        Arrays.sort(candidates);
        recursion(0, 0, candidates, target);
        return result;
    }

    private void recursion(int startIndex, int sum, int[] candidates, int target) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        // iterate over the range from startIndex
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                // why continue ?
                // combination must be unique
                // so when candidates have same numbers, need to ignore same number in same tree layer
                continue;
            }
            path.add(candidates[i]);
            // why i + 1: Each number in candidates may only be used once in the combination.
            recursion(i + 1, sum + candidates[i], candidates, target);
            // backtracking
            path.remove(path.size() - 1);
        }
    }
}
