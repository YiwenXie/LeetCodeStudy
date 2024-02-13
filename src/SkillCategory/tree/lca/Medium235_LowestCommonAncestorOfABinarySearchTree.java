package SkillCategory.tree.lca;

import SkillCategory.tree.TreeNode;

public class Medium235_LowestCommonAncestorOfABinarySearchTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int min = Math.min(p.val, q.val);
        int max = Math.max(p.val, q.val);
        return helper(root, min, max);
    }

    private TreeNode helper(TreeNode root, int min, int max) {
        if (root == null) {
            return null;
        }
        if (root.val == min || root.val == max) {
            return root;
        }
        TreeNode left = null;
        TreeNode right = null;
        if (min < root.val && root.val < max) {
            left = helper(root.left, min, max);
            right = helper(root.right, min, max);
        } else if (root.val > max) {
            left = helper(root.left, min, max);
        } else if (root.val < min) {
            right = helper(root.right, min, max);
        }
        if (left != null && right != null) {
            return root;
        } else if (left == null && right != null) {
            return right;
        } else if (right == null && left != null) {
            return left;
        }
        return null;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor2(root.right, p, q);
        } else if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor2(root.left, p, q);
        }
        return root;
    }

    /**
     * iterative method
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }
}
