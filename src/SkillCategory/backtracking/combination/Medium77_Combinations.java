package SkillCategory.backtracking.combination;

import java.util.ArrayList;
import java.util.List;

public class Medium77_Combinations {
    /**
     * Time Complexity: O(n * 2^n)
     * Space Complexity: O(n)
     *
     * @param n 1 <= n <= 20
     * @param k 1 <= k <= n
     * @return all possible combinations of k numbers chosen from the range [1, n].
     */
    List<List<Integer>> result;

    public List<List<Integer>> combine(int n, int k) {
        result = new ArrayList<>();
        recursion(1, n, k, new ArrayList<>());
        return result;
    }

    /**
     * @param lo   low range
     * @param hi   high range
     * @param k    k
     * @param path save combination
     */
    private void recursion(int lo, int hi, int k, List<Integer> path) {
        // the size of combination meet the requirement (k numbers chosen)
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        // iterate over the range from lo to hi
//        for (int i = lo; i <= hi; i++) {
        // 已经选择的元素个数：path.size();
        // 所需需要的元素个数为: k - path.size();
        // 列表中剩余元素（n-i） >= 所需需要的元素个数（k - path.size()）
        // 在集合n中至多要从该起始位置 : i <= n - (k - path.size()) + 1，开始遍历
        for (int i = lo; i <= hi - (k - path.size()) + 1; i++) {
            // add number
            path.add(i);
            // recursion select next element
            recursion(i + 1, hi, k, path);
            // backtracking
            path.remove(path.size() - 1);
        }
    }
}
