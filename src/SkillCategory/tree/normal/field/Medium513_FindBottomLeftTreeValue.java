package SkillCategory.tree.normal.field;

import SkillCategory.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Medium513_FindBottomLeftTreeValue {
    /**
     * BFS
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    result = node.val;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return result;
    }

    /**
     * recursion
     *
     * @param root
     * @return
     */
    int leftMostVal = 0;
    int maxDepth = 0;

    public int findBottomLeftValue2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        recursion(root, 1);
        return leftMostVal;
    }

    private void recursion(TreeNode root, int depth) {
        if (maxDepth < depth) {
            leftMostVal = root.val;
            maxDepth = depth;
        }
        if (root.left != null) {
            recursion(root.left, depth + 1);
        }
        if (root.right != null) {
            recursion(root.right, depth + 1);
        }
    }
}
