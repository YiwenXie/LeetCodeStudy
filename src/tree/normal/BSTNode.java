package tree.normal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author ywxie
 * @date 2020/12/24 15:52
 * @describe
 */
public class BSTNode {
    public class TreeNode{
        private int val;
        private TreeNode left;
        private TreeNode right;
        private TreeNode(int x) { val = x; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 98. 验证二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    private boolean isValidBSTHelper(TreeNode node, TreeNode min, TreeNode max){
        if (node == null){
            return true;
        }
        if (min != null && node.val <= min.val){
            return false;
        }
        if (max != null && node.val >= max.val){
            return false;
        }
        return isValidBSTHelper(node.left, min, node) && isValidBSTHelper(node.right, node, max);
    }

    /**
     * 700. 二叉搜索树中的搜索
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null){
            return null;
        }
        if (root.val == val){
            return root;
        }
        if (root.val > val){
            return searchBST(root.left, val);
        }
        return searchBST(root.right, val);
    }

    /**
     * 701. 二叉搜索树中的插入操作
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null){
            return new TreeNode(val);
        }
        if (root.val > val){
            root.left = insertIntoBST(root.left, val);
        }
        if (root.val < val){
            root.right = insertIntoBST(root.right, val);
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
        if (root.val == key){
            if (root.left == null && root.right == null){
                return null;
            }
            if (root.left == null){
                return root.right;
            }
            if (root.right == null){
                return root.left;
            }
            if (root.left != null && root.right != null){
                // 找到右子树的最小节点
                TreeNode minNode = getMinBST(root.right);
                // 把 root 改成 minNode
                root.val = minNode.val;
                // 转而去删除 minNode
                root.right = deleteNode(root.right, minNode.val);
            }
        }else if (root.val > key){
            root.left = deleteNode(root.left, key);
        }else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    private TreeNode getMinBST(TreeNode node){
        if (node == null){
            return null;
        }
        while (node.left != null){
            node = node.left;
        }
        return node;
    }

    /**
     * 108. 将有序数组转换为二叉搜索树
     * 中序遍历构造二叉树
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0){
            return null;
        }
        return sortedArrayToBSTHelper(nums, 0, nums.length-1);
    }

    private TreeNode sortedArrayToBSTHelper(int[] nums, int low, int high){
        if (low > high) {
            return null;
        }
        int mid = low + (high - low) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBSTHelper(nums, low, mid-1);
        node.right = sortedArrayToBSTHelper(nums, mid+1, high);
        return node;
    }

    /**
     * 230. 二叉搜索树中第K小的元素
     */
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        kthSmallestHelper(root, k, res);
        return res.get(k-1);
    }

    private void kthSmallestHelper(TreeNode root, int k, List<Integer> res) {
        if (root == null){
            return;
        }
        kthSmallestHelper(root.left, k, res);
        res.add(root.val);
        if (k == res.size()){
            return;
        }
        kthSmallestHelper(root.right, k, res);
    }

    /**
     * 538. 把二叉搜索树转换为累加树
     * 中序遍历的反向遍历
     * 左根右-右根左
     */
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        return convertBSTHelper(root);
    }

    private TreeNode convertBSTHelper(TreeNode node) {
        if (node != null){
            convertBSTHelper(node.right);
            sum += node.val;
            node.val = sum;
            //做事情
            convertBSTHelper(node.left);
        }
        return node;
    }

    /**
     * 173. 二叉搜索树迭代器
     * 中序遍历的反向
     */
    class BSTIterator {

        Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            this.stack = new Stack<>();
//            inorder(root);
            while (root != null){
                stack.push(root);
                root = root.left;
            }
        }

        private void inorder(TreeNode node){
            if (node == null){
                return;
            }
            inorder(node.right);
            stack.push(node);
            inorder(node.left);
        }

        public int next() {
            TreeNode node = stack.pop();
//            return node.val;
            int result = node.val;
            if (node.right != null){
                node = node.right;
                while (node != null){
                    stack.push(node);
                    node = node.left;
                }
            }
            return result;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    /**
     * 235. 二叉搜索树的最近公共祖先
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if ((root.val - p.val) * (root.val - q.val) <= 0){
            return root;
        }else if (root.val < p.val && root.val < q.val){
            return lowestCommonAncestor1(root.right, p, q);
        }else {
            return lowestCommonAncestor1(root.left, p, q);
        }
    }

    /**
     * 530. 二叉搜索树的最小绝对差
     * 符合条件的节点:中序遍历中相邻的两个节点
     * 特性：二叉搜索树
     */
    int min = Integer.MAX_VALUE;
    TreeNode preNode = null;
    public int getMinimumDifference(TreeNode root) {
        getMinimumDifferenceHelper(root);
        return min;
    }

    private void getMinimumDifferenceHelper(TreeNode node){
        if (node == null) {
            return;
        }
        getMinimumDifferenceHelper(node.left);
        if (preNode != null){
            min = Math.min(node.val - preNode.val, min);
        }
        preNode = node;
        getMinimumDifferenceHelper(node.right);
    }

    /**
     * 449. 序列化和反序列化二叉搜索树
     */
}
