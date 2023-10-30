package SkillCategory.array.BinarySearch;

import SkillCategory.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/10/30 20:00
 */
public class Medium2476ClosestNodesQueriesInABinarySearchTree {
    /**
     * @param root
     * @param queries
     * @return time complexity: O(q * logn)
     * space complexity: O(n)
     */
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<List<Integer>> resultList = new ArrayList<>();
        // 存储有序列表
        List<Integer> treeList = new ArrayList<>();
        // tree转换成有序list
        getList(root, treeList);
        // 边界放外面，每次循环queries都要用
        int left = 0;
        int right = treeList.size();
        // 循环queries，每次从tree list中找到符合的那一个
        for (Integer query : queries) {
            List<Integer> subList = new ArrayList<>();
            int target = query;
            subList.add(getMin(treeList, target, left, right));
            subList.add(getMax(treeList, target, left, right));
            resultList.add(subList);
        }
        return resultList;
    }

    /**
     * 二叉搜索数，左中右的前序遍历下来就是从小到大排序好的
     *
     * @param root
     * @param list
     */
    public void getList(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        getList(root.left, list);
        list.add(root.val);
        getList(root.right, list);
    }

    /**
     * mini is the largest value in the tree that is smaller than or equal to queries[i].
     * If a such value does not exist, add -1 instead.
     *
     * @param treeList
     * @param target
     * @param l
     * @param r
     * @return
     */
    public int getMin(List<Integer> treeList, int target, int l, int r) {
        int left = l;
        int right = r;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 有一个最小的mid使得treeList.get(mid) > target成立
            // 因为一直在找小于target的数里的最大值，所以只要碰到大于target的，就收缩右边界
            if (treeList.get(mid) > target) {
                // 因为左闭右开，所以这里right更新不能是mid - 1只能是mid
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // 当存在一个比target小的数里的最大数时，left 一定是 mid + 1，所以返回的是left - 1 而不是left
        // 当不存在value时，说明没有比target小或等于的数，left一直没变，下标为0，所以需判断left - 1 == -1
        return left - 1 == -1 ? -1 : treeList.get(left - 1);
    }

    /**
     * maxi is the smallest value in the tree that is greater than or equal to queries[i].
     * If a such value does not exist, add -1 instead.
     *
     * @param treeList
     * @param target
     * @param l
     * @param r
     * @return
     */
    public int getMax(List<Integer> treeList, int target, int l, int r) {
        int left = l;
        int right = r;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 有一个最小的mid使得treeList.get(mid) >= target成立
            if (treeList.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // 因为left一直是mid + 1更新的，退出条件是left = right = size
        // 所以这里用left == size来判断value是否存在
        return left == treeList.size() ? -1 : treeList.get(left);
    }

}
