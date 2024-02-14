package SkillCategory.tree.normal.operation;

import SkillCategory.tree.TreeNode;

public class Medium114_FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        // base case
        if (root == null) return;

        // 利用定义，把左右子树拉平
        flatten(root.left);
        flatten(root.right);

        /**** 后序遍历位置 ****/
        // 1、左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;

        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

    public void flatten2(TreeNode root) {
        recursion(root);
    }

    private TreeNode recursion(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftTail = recursion(root.left);
        TreeNode rightTail = recursion(root.right);
        // if left child is not null
        if (root.left != null) {
            // make left tail's right to link root's right child
            leftTail.right = root.right;
            // make root's right child become root's left child
            // left child already linked right child
            root.right = root.left;
            // make root left child pointer is null
            root.left = null;
        }
        if (rightTail != null) {
            return rightTail;
        }
        if (leftTail != null) {
            return leftTail;
        }
        return root;
    }
}
