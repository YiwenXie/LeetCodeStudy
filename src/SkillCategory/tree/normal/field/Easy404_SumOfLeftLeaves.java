package SkillCategory.tree.normal.field;

import SkillCategory.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Easy404_SumOfLeftLeaves {
    int result = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                TreeNode left = node.left;
                if (left != null && left.left == null && left.right == null) {
                    result += left.val;
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return result;
    }
}
