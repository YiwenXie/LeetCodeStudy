package SkillCategory.tree.operation;

import SkillCategory.tree.TreeNode;

/**
 * @author ywxie
 * @date 2022/5/23 20:52
 * @describe
 * 1、如果当前节点会对下面的子节点有整体影响，可以通过辅助函数增长参数列表，借助参数传递信息。
 * 2、在二叉树递归框架之上，扩展出一套 BST 代码框架：
 * void BST(TreeNode root, int target) {
 *     if (root.val == target)
 *         // 找到目标，做点什么
 *     if (root.val < target)
 *         BST(root.right, target);
 *     if (root.val > target)
 *         BST(root.left, target);
 * }
 */
public class Solution {

    /**
     * 98. 验证二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    boolean isValidBSTHelper(TreeNode root, TreeNode min, TreeNode max){
        if (root == null){
            return true;
        }
        if (min != null && root.val <= min.val){
            return false;
        }
        if (max != null && root.val >= max.val){
            return false;
        }

        return isValidBSTHelper(root.left, min, root) && isValidBSTHelper(root.right, root, max);
    }

    /**
     * 700. 二叉搜索树中的搜索
     * 迭代
     */
    TreeNode searchBST(TreeNode root, int val){
        TreeNode node = root;
        while (node != null){
            if (node.val > val){
                node = node.left;
            }else if (node.val < val){
                node = node.right;
            }else {
                return node;
            }
        }
        return node;
    }

    /**
     * 700. 二叉搜索树中的搜索
     * 递归
     */
    TreeNode searchBST2(TreeNode root, int val){
        if (root == null){
            return null;
        }
        if (root.val > val){
            return searchBST2(root.left, val);
        }
        if (root.val < val){
            return searchBST2(root.right, val);
        }
        return root;
    }

    /**
     * 701. 二叉搜索树中的插入操作
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 找到空位置插入新节点
        if (root == null){
            return new TreeNode(val);
        }
        // if (root.val == val)
        //     BST 中一般不会插入已存在元素
        if (root.val > val){
            root.left = insertIntoBST(root.left, val);
        }
        if (root.val < val){
            root.right =  insertIntoBST(root.right, val);
        }
        return root;
    }

    /**
     * 450. 删除二叉搜索树中的节点
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null){
            return null;
        }
        if (root.val == key) {
            // 找到啦，进行删除
            if (root.left == null && root.right == null){
                return null;
            }
            // 排除了情况 1 之后
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // A 有两个子节点，麻烦了，为了不破坏 BST 的性质，
            // A 必须找到左子树中最大的那个节点，或者右子树中最小的那个节点来接替自己。
            if (root.left != null && root.right != null) {
                // 获得右子树最小的节点
                TreeNode minNode = getMin(root.right);
                // 删除右子树最小的节点
                root.right = deleteNode(root.right, minNode.val);
                // 用右子树最小的节点替换 root 节点
                minNode.left = root.left;
                minNode.right = root.right;
                root = minNode;
                // 替换 root 节点为什么这么麻烦，直接改 val 字段不就行了？
                // 仅对于这道算法题来说是可以的，但这样操作并不完美，我们一般不会通过修改节点内部的值来交换节点。
                // 因为在实际应用中，BST 节点内部的数据域是用户自定义的，可以非常复杂，
                // 而 BST 作为数据结构（一个工具人），其操作应该和内部存储的数据域解耦，
                // 所以我们更倾向于使用指针操作来交换节点，根本没必要关心内部数据。
            }
        } else if (root.val > key) {
            // 去左子树找
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            // 去右子树找
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    TreeNode getMin(TreeNode node) {
        // BST 最左边的就是最小的
        while (node.left != null) node = node.left;
        return node;
    }

}
