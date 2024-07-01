package SkillCategory.tree.bst.construct;

import SkillCategory.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Medium95_UniqueBinarySearchTreesII {
    List<TreeNode> list;
    public List<TreeNode> generateTrees(int n) {
        list = new ArrayList<>();
        constructTree(1, n);
        return list;
    }

    public TreeNode constructTree(int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        TreeNode root = null;
        for (int i = lo; i <= hi; i++) {
            root = new TreeNode(i);
            root.left = constructTree(lo, i - 1);
            root.right = constructTree(i + 1, hi);
        }
        list.add(root);
        return root;
    }

//    public TreeNode dp(int n) {
//        int[] dp
//    }
}
