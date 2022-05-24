package SkillCategory.tree.construct;

import SkillCategory.tree.TreeNode;

import java.util.*;

/**
 * @author ywxie
 * @date 2022/5/16 14:35
 * @describe
 * 二叉树的构造问题一般都是使用「分解问题」的思路：
 * 构造整棵树 = 根节点 + 构造左子树 + 构造右子树。
 * 先找出根节点，然后根据根节点的值找到左右子树的元素，进而递归构建出左右子树。
 */
public class Solution {
    /**
     * 654. 最大二叉树
     */
    TreeNode constructMaximumBinaryTree(int[] nums){
        return constructMaximumBinaryTreeHelper(nums, 0, nums.length - 1);
    }

    TreeNode constructMaximumBinaryTreeHelper(int[] nums, int left, int right){
        if (left > right){
            return null;
        }
        int maxVal = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = left; i <= right; i++) {
            if (nums[i] > maxVal){
                maxIndex = i;
                maxVal = nums[i];
            }
        }
        TreeNode root = new TreeNode(maxVal);
        root.left = constructMaximumBinaryTreeHelper(nums, left, maxIndex - 1);
        root.right = constructMaximumBinaryTreeHelper(nums, maxIndex + 1, right);
        return root;
    }


    /**
     * 105. 从前序与中序遍历序列构造二叉树
     */
    HashMap<Integer, Integer> inorderHashMap;
    TreeNode buildTree(int[] preorder, int[] inorder){
        inorderHashMap = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            inorderHashMap.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd,
                             int[] inorder, int inStart, int inEnd){
        if (preStart > preEnd){
            return null;
        }
        int rootVal = preorder[preStart];
        int rootInOrderIndex = inorderHashMap.get(rootVal);
        int leftSize = rootInOrderIndex - inStart;
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTreeHelper(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, rootInOrderIndex - 1);
        root.right = buildTreeHelper(preorder, preStart + leftSize + 1, preEnd,
                inorder, rootInOrderIndex + 1, inEnd);
        return root;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     */
    TreeNode buildTree2(int[] inorder, int[] postorder){
        inorderHashMap = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            inorderHashMap.put(inorder[i], i);
        }
        return buildTree2Helper(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    TreeNode buildTree2Helper(int[] inorder, int inStart, int inEnd,
                             int[] postorder, int postStart, int postEnd){
        if (inStart > inEnd){
            return null;
        }
        int rootVal = postorder[postEnd];
        int rootInOrderIndex = inorderHashMap.get(rootVal);
        int leftSize = rootInOrderIndex - inStart;
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree2Helper(inorder, inStart, rootInOrderIndex - 1,
                postorder, postStart, postStart + leftSize - 1);
        root.right = buildTree2Helper(inorder, rootInOrderIndex + 1, inEnd,
                postorder, postStart + leftSize, postEnd - 1);
        return root;
    }


    /**
     * 889. 根据前序和后序遍历构造二叉树
     */
    // 存储 postorder 中值到索引的映射
    HashMap<Integer, Integer> postHashMap = new HashMap<>();
    TreeNode constructFromPrePost(int[] preorder, int[] postorder){
        for (int i = 0; i < postorder.length; i++) {
            postHashMap.put(postorder[i], i);
        }
        return constructFromPrePostHelper(preorder, 0, preorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    TreeNode constructFromPrePostHelper(int[] preorder, int preStart, int preEnd,
                              int[] postorder, int postStart, int postEnd){
        if (preStart > preEnd){
            return null;
        }
        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }
        // root 节点对应的值就是前序遍历数组的第一个元素
        int rootVal = preorder[preStart];
        // root.left 的值是前序遍历第二个元素
        // 通过前序和后序遍历构造二叉树的关键在于通过左子树的根节点
        // 确定 preorder 和 postorder 中左右子树的元素区间
        int leftRootVal = preorder[preStart + 1];
        // leftRootVal 在后序遍历数组中的索引
        int index = postHashMap.get(leftRootVal);
        // 左子树的元素个数
        int leftSize = index - postStart + 1;
        // 先构造出当前根节点
        TreeNode root = new TreeNode(rootVal);
        // 递归构造左右子树
        // 根据左子树的根节点索引和元素个数推导左右子树的索引边界
        root.left = constructFromPrePostHelper(
                preorder, preStart + 1, preStart + leftSize,
                postorder, postStart, index);
        root.right = constructFromPrePostHelper(
                preorder, preStart + leftSize + 1, preEnd,
                postorder, index + 1, postEnd - 1);

        return root;

    }

    /**
     * 297. 二叉树的序列化与反序列化
     */
    public class Codec {

        String NULL = "#";
        String SEP = ",";
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            traverse(root, sb);
            return sb.toString();
        }

        void traverse(TreeNode root, StringBuilder sb) {
            if (root == null){
                sb.append(NULL).append(SEP);
                return;
            }
            sb.append(root.val).append(SEP);
            traverse(root.left, sb);
            traverse(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty()){
                return null;
            }
            LinkedList<String> list = new LinkedList<>(Arrays.asList(data.split(SEP)));
            return deserializeHelper(list);
        }


        TreeNode deserializeHelper(LinkedList<String> list){
            String str = list.removeFirst();
            if (list.isEmpty()){
                return null;
            }
            if (NULL.equals(str)){
                return null;
            }

            TreeNode root = new TreeNode(Integer.parseInt(str));
            root.left = deserializeHelper(list);
            root.right = deserializeHelper(list);
            return root;
        }
    }

    /**
     * 652. 寻找重复的子树
     */
    LinkedList<TreeNode> result = new LinkedList<>();
    HashMap<String, Integer> hashMap = new HashMap<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        findDuplicateSubtreesHelper(root);
        return result;
    }

    String findDuplicateSubtreesHelper(TreeNode root){
        if (root == null){
            return "#";
        }
        String left = findDuplicateSubtreesHelper(root.left);
        String right = findDuplicateSubtreesHelper(root.right);
        String str = left + "," + right + "," + root.val;
        hashMap.put(str, hashMap.getOrDefault(str, 0) + 1);
        if (hashMap.get(str) == 2){
            result.add(root);
        }
        return str;
    }

}
