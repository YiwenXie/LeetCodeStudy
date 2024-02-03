package SkillCategory.tree.bst.basic;

import SkillCategory.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Easy530_MinimumAbsoluteDifferenceInBST {
    int minDiff = Integer.MAX_VALUE;
    List<Integer> list = new ArrayList<>();

    /**
     * 遇到在二叉搜索树上求什么最值啊，差值之类的，就把它想成在一个有序数组上求最值，求差值，这样就简单多了。
     *
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        recursion(root);
        for (int i = 1; i < list.size(); i++) {
            minDiff = Math.min(minDiff, Math.abs(list.get(i) - list.get(i - 1)));
        }
        return minDiff;
    }

    private void recursion(TreeNode root) {
        if (root == null) {
            return;
        }
        recursion(root.left);
        list.add(root.val);
        recursion(root.right);
    }

    /**
     * 需要用一个pre节点记录一下cur节点的前一个节点。
     *
     * @param root
     * @return
     */
    public int getMinimumDifference2(TreeNode root) {
        recursion2(root);
        return minDiff;
    }

    TreeNode pre = null;

    private void recursion2(TreeNode cur) {
        if (cur == null) {
            return;
        }
        recursion2(cur.left);
        if (pre != null) {
            minDiff = Math.min(Math.abs(cur.val - pre.val), minDiff);
        }
        pre = cur;
        recursion2(cur.right);
    }
}
