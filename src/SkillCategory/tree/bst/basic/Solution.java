package SkillCategory.tree.bst.basic;

import SkillCategory.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ywxie
 * @date 2022/5/22 15:21
 * @describe
 */
public class Solution {
    /**
     * 230. 二叉搜索树中第K小的元素
     */
    int result = -1;
    int rank = 0;
    public int kthSmallest(TreeNode root, int k) {
        kthSmallestHelper(root, k);
        return result;
    }

    void kthSmallestHelper(TreeNode root, int k){
        if (root == null || k < 0){
            return;
        }
        kthSmallestHelper(root.left, k);
        rank++;
        if (rank == k){
            result = root.val;
            return;
        }
        kthSmallestHelper(root.right, k);
    }

    /**
     * 230. 二叉搜索树中第K小的元素
     * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，
     * 你将如何优化算法？
     * 有了 size 字段，外加 BST 节点左小右大的性质，
     * 对于每个节点 node 就可以通过 node.left 推导出 node 的排名
     */
    public int kthSmallest2(TreeNode root, int k) {
        MyBst bst = new MyBst(root);
        return bst.kthSmallest(k);
    }

    class MyBst {
        TreeNode root;
        public MyBst(TreeNode root) {
            this.root = root;
            countNodeNum(root);
        }

        // 返回二叉搜索树中第k小的元素
        public int kthSmallest(int k) {
            TreeNode node = root;
            while (node != null) {
                int left = 0;
                if (node.left != null){
                    left = node.left.size;
                }
                if (left < k - 1) {
                    node = node.right;
                    k -= left + 1;
                } else if (left == k - 1) {
                    break;
                } else {
                    node = node.left;
                }
            }
            return node.val;
        }

        // 统计以node为根结点的子树的结点数
        private int countNodeNum(TreeNode node) {
            if (node == null) {
                return 0;
            }
            node.size =  countNodeNum(node.left) + countNodeNum(node.right) + 1;
            return node.size;
        }
    }

    /**
     * 538\1038. 把二叉搜索树转换为累加树
     */
    public TreeNode convertBST(TreeNode root) {
        convertBSTHelper(root);
        return root;
    }

    int sum = 0;
    void convertBSTHelper(TreeNode root){
        if (root == null){
            return;
        }
        convertBSTHelper(root.right);
        sum += root.val;
        root.val = sum;
        convertBSTHelper(root.left);
    }




}
