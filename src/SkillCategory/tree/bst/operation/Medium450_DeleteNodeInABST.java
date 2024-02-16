package SkillCategory.tree.bst.operation;

import SkillCategory.tree.TreeNode;

public class Medium450_DeleteNodeInABST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.right == null) {
                return root.left;
            } else if (root.left == null) {
                return root.right;
            } else {
                TreeNode left = root.left;
                TreeNode min = root.right;
                // find right child tree minimum value
                while (min.left != null) {
                    min = min.left;
                }
                // make left child become minimum value left child
                min.left = left;
                // release left
                root.left = null;
                // return right child
                root = root.right;
            }
        }
        return root;
    }
}
