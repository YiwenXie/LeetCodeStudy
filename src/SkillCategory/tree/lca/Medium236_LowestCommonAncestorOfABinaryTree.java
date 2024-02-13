package SkillCategory.tree.lca;

import SkillCategory.tree.TreeNode;

public class Medium236_LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // 实现情况一的逻辑，顺便包含了情况二。
        // 因为遇到 q 或者 p 就返回，这样也包含了 q 或者 p 本身就是 公共祖先的情况。
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 后序遍历（左右中）就是天然的回溯过程，可以根据左右子树的返回值，来处理中节点的逻辑。
        // 情况一：
        //      如果找到一个节点，发现左子树出现结点p，右子树出现节点q，
        //      或者 左子树出现结点q，右子树出现节点p，
        //      那么该节点就是节点p和q的最近公共祖先。
        // 情况二：
        //      节点本身p(q)，它拥有一个子孙节点q(p)。
        // 如果left 和 right都不为空，说明此时root就是最近公共节点。
        if (left != null && right != null) {
            return root;
        } else if (left == null && right != null) {
            // 如果left为空，right不为空，就返回right，说明目标节点是通过right返回的
            return right;
        } else if (right == null && left != null) {
            return left;
        }
        return null;
    }
}
