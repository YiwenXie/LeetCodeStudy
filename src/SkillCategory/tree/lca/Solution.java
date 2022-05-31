package SkillCategory.tree.lca;

import SkillCategory.tree.Node;
import SkillCategory.tree.TreeNode;

import java.util.HashSet;

/**
 * @author ywxie
 * @date 2022/5/29 19:10
 * @describe Lowest Common Ancestor 最近公共祖先
 */
public class Solution {

    /**
     * 236. 二叉树的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return find(root, p, q);
    }

    // 在二叉树中寻找 val1 和 val2 的最近公共祖先节点
    TreeNode find(TreeNode root, TreeNode p, TreeNode q){
        if (root == null){
            return null;
        }
        // 前序位置
        if (root.val == p.val || root.val == q.val){
            // 如果遇到目标值，直接返回
            return root;
        }
        TreeNode left = find(root.left, p, q);
        TreeNode right = find(root.right, p, q);
        // 后序位置，已经知道左右子树是否存在目标值
        if (left != null && right != null) {
            // 当前节点是 LCA 节点
            return root;
        }
        //如果你能够在左子树找到目标节点，还有没有必要去右子树找了？
        // 没有必要。但这段代码还是会去右子树找一圈，所以效率相对差一些。
        // 因为是两个节点，所以需要再去右子树找目标值
        return left == null? right:left;
    }

    /**
     * 1676.二叉树的最近公共祖先 IV
     * 输入一个包含若干节点的列表nodes（这些节点都存在于二叉树中），让你算这些节点的最近公共祖先。
     */
    HashSet<Integer> hashSet = new HashSet<>();
    TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes){
        for (TreeNode node:nodes
             ) {
            hashSet.add(node.val);
        }
        return find2(root);
    }

    TreeNode find2(TreeNode root){
        if (root == null){
            return null;
        }
        if (hashSet.contains(root.val)){
            return root;
        }
        TreeNode left = find2(root.left);
        TreeNode right = find2(root.right);
        if (left != null && right != null){
            return root;
        }
        return left != null? left:right;
    }

    /**
     * 不过需要注意的是，上面这两道题的题目都明确告诉我们这些节点必定存在于二叉树中，
     * 如果没有这个前提条件，就需要修改代码了。
     * 见下面
     */

    /**
     * 1644.二叉树的最近公共祖先 II
     * 给你输入一棵不含重复值的二叉树的，以及两个节点p和q，
     * 如果p或q不存在于树中，则返回空指针，否则的话返回p和q的最近公共祖先节点。
     * 应该对二叉树进行完全搜索（遍历每一个节点）== 后序位置
     */
    // 用于记录 p 和 q 是否存在于二叉树中
    boolean foundP = false, foundQ = false;
    TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = find3(root, p.val, q.val);
        if (!foundP || !foundQ) {
            return null;
        }
        // p 和 q 都存在二叉树中，才有公共祖先
        return res;
    }

    // 在二叉树中寻找 val1 和 val2 的最近公共祖先节点
    TreeNode find3(TreeNode root, int val1, int val2) {
        if (root == null){
            return null;
        }
        TreeNode left = find3(root, val1, val2);
        TreeNode right = find3(root, val1, val2);
        // 后序位置，判断当前节点是不是 LCA 节点
        if (left != null && right != null) {
            return root;
        }
        // 后序位置，判断当前节点是不是目标值
        if (root.val == val1 || root.val == val2) {
            // 找到了，记录一下
            if (root.val == val1) foundP = true;
            if (root.val == val2) foundQ = true;
            return root;
        }
        return left != null ? left:right;
    }

    /**
     * 235.二叉搜索树的最近公共祖先
     * 给你输入一棵不含重复值的二叉搜索树，以及存在于树中的两个节点p和q，
     * 请你计算p和q的最近公共祖先节点。
     * 但对于 BST 来说，根本不需要老老实实去遍历子树，由于 BST 左小右大的性质，将当前节点的值与val1和val2作对比即可判断当前节点是不是LCA：
     * 假设val1 < val2，那么val1 <= root.val <= val2则说明当前节点就是LCA；若root.val比val1还小，则需要去值更大的右子树寻找LCA；若root.val比val2还大，则需要去值更小的左子树寻找LCA。
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        // 保证 val1 较小，val2 较大
        int val1 = Math.min(p.val, q.val);
        int val2 = Math.max(p.val, q.val);
        return find4(root, val1, val2);
    }

    // 在 BST 中寻找 val1 和 val2 的最近公共祖先节点
    TreeNode find4(TreeNode root, int val1, int val2){
        if (root == null) {
            return null;
        }
        if (root.val > val2) {
            // 当前节点太大，去左子树找
            return find4(root.left, val1, val2);
        }
        if (root.val < val1) {
            // 当前节点太小，去右子树找
            return find4(root.right, val1, val2);
        }
        // val1 <= root.val <= val2
        // 则当前节点就是最近公共祖先
        return root;
    }

    /**
     * 1650.二叉树的最近公共祖先 III
     * 给你输入一棵存在于二叉树中的两个节点p和q，请你返回它们的最近公共祖先
     * 由于节点中包含父节点的指针，所以二叉树的根节点就没必要输入了。
     * 这道题其实不是公共祖先的问题，而是单链表相交的问题
     */
    Node lowestCommonAncestor(Node p, Node q){
        Node a = p;
        Node b = q;
        while (a != b){
            a = a == null? q:a.parent;
            b = b == null? p:b.parent;

        }
        return a;
    }
}
