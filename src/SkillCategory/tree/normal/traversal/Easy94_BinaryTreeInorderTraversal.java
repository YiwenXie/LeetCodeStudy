package SkillCategory.tree.normal.traversal;

import SkillCategory.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Easy94_BinaryTreeInorderTraversal {
    List<Integer> list = new ArrayList<>();

    /**
     * Solution: recursion
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return list;
        }
        inorderTraversal(root.left);
        list.add(root.val);
        inorderTraversal(root.right);
        return list;
    }

    /**
     * Solution: iterative method
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }
}
