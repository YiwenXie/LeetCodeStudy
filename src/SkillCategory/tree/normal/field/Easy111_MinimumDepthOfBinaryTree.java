package SkillCategory.tree.normal.field;

import SkillCategory.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Easy111_MinimumDepthOfBinaryTree {

    int minDepth = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        traversal(root, 1);
        return minDepth;
    }

    private void traversal(TreeNode root, int depth) {
        if (root.left == null && root.right == null) {
            minDepth = Math.min(depth, minDepth);
            return;
        }
        if (root.left != null) {
            traversal(root.left, depth + 1);
        }
        if (root.right != null) {
            traversal(root.right, depth + 1);
        }
    }

    public int minDept2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = minDept2(root.left);
        int rightDepth = minDept2(root.right);
        // 特殊情况处理：如果左子树为空，返回右子树的深度加1
        if (leftDepth == 0) {
            return rightDepth + 1;
        }
        // 特殊情况处理：如果右子树为空，返回左子树的深度加1
        if (rightDepth == 0) {
            return leftDepth + 1;
        }
        return Math.min(leftDepth, rightDepth) + 1;
    }

    int minDepth3 = 0;

    public int minDept3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            minDepth3++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return minDepth;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return minDepth3;
    }
}
