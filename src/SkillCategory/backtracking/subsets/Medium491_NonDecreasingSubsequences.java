package SkillCategory.backtracking.subsets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Medium491_NonDecreasingSubsequences {
    public static void main(String[] args) {
        Medium491_NonDecreasingSubsequences solution = new Medium491_NonDecreasingSubsequences();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 1, 1, 1, 1};
        System.out.println(solution.findSubsequences(nums).toString());
    }

    List<List<Integer>> result;
    List<Integer> path;
    Set<String> set;

    public List<List<Integer>> findSubsequences(int[] nums) {
        result = new ArrayList<>();
        path = new ArrayList<>();
        set = new HashSet<>();
        recursion(0, nums);
        recursion2(0, nums);
        recursion3(0, nums);
        return result;
    }

    private void recursion(int startIndex, int[] nums) {
        if (startIndex > nums.length) {
            return;
        }
        if (path.size() >= 2) {
            StringBuilder sb = new StringBuilder();
            for (int num : path) {
                sb.append(num).append(",");
            }
            String string = sb.deleteCharAt(sb.length() - 1).toString();
            if (!set.contains(string)) {
                set.add(string);
                result.add(new ArrayList<>(path));
            }
        }
        for (int i = startIndex; i < nums.length; i++) {
            if (path.size() > 0 && path.get(path.size() - 1) > nums[i]) {
                continue;
            }
            path.add(nums[i]);
            recursion(i + 1, nums);
            path.remove(path.size() - 1);
        }
    }

    private void recursion2(int startIndex, int[] nums) {
        if (startIndex > nums.length) {
            return;
        }
        if (path.size() >= 2) {
            result.add(new ArrayList<>(path));
        }
        Set<Integer> set = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            if (!path.isEmpty() && path.get(path.size() - 1) > nums[i]) {
                continue;
            }
            path.add(nums[i]);
            set.add(nums[i]);
            recursion(i + 1, nums);
            path.remove(path.size() - 1);
        }
    }

    private void recursion3(int startIndex, int[] nums) {
        if (startIndex > nums.length) {
            return;
        }
        if (path.size() >= 2) {
            result.add(new ArrayList<>(path));
        }
        boolean[] used = new boolean[201];
        for (int i = startIndex; i < nums.length; i++) {
            if (used[nums[i] + 100]) {
                continue;
            }
            if (!path.isEmpty() && path.get(path.size() - 1) > nums[i]) {
                continue;
            }
            path.add(nums[i]);
            used[nums[i] + 100] = true;
            recursion(i + 1, nums);
            path.remove(path.size() - 1);
        }
    }
}
