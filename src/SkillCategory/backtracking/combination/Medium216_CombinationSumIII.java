package SkillCategory.backtracking.combination;

import java.util.ArrayList;
import java.util.List;

public class Medium216_CombinationSumIII {
    List<List<Integer>> result;
    List<Integer> path;

    public List<List<Integer>> combinationSum3(int k, int n) {
        result = new ArrayList<>();
        path = new ArrayList<>();
        recursion(1, k, 0, n);
        return result;
    }

    private void recursion(int start, int k, int sum, int target) {
        if (sum > target) {
            return;
        }
        if (path.size() == k && sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= 9 && sum + i <= target; i++) {
            path.add(i);
            recursion(i + 1, k, sum + i, target);
            path.remove(path.size() - 1);
        }
    }
}
