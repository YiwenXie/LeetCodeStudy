package SkillCategory.tree.normal.basic;

import SkillCategory.tree.Node;
import SkillCategory.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ywxie
 * @date 2022/5/12 16:11
 * @describe
 * 二叉树解题的思维模式分两类：
 * 1、是否可以通过遍历一遍二叉树得到答案？
 *  如果可以，用一个 traverse 函数配合外部变量来实现，这叫「遍历」的思维模式。
 * 2、是否可以定义一个递归函数，通过子问题（子树）的答案推导出原问题的答案？
 *  如果可以，写出这个递归函数的定义，并充分利用这个函数的返回值，这叫「分解问题」的思维模式。
 * 无论使用哪种思维模式，你都需要思考：
 *  如果单独抽出一个二叉树节点，它需要做什么事情？
 *  需要在什么时候（前/中/后序位置）做？
 *  其他的节点不用你操心，递归函数会帮你在所有节点上执行相同的操作。
 */
public class Solution {

    /**
     * 104.二叉树的最大深度
     */
    // 记录最大深度
    int res = 0;
    // 记录遍历到的节点的深度
    int depth = 0;
    // 主函数
    int maxDepth(TreeNode root){
        traverse(root);
        return res;
    }

    // 二叉树遍历框架
    void traverse(TreeNode node){
        if (node == null){
            return;
        }
        // 前序位置
        depth++;
        if (node.left == null && node.right == null){
            // 到达叶子节点，更新最大深度
            res = Math.max(res, depth);
        }
        traverse(node.left);
        traverse(node.right);
        depth--;
    }

    // 一棵二叉树的最大深度可以通过子树的最大高度推导出来，
    // 这就是分解问题计算答案的思路。
    int maxDepth2(TreeNode root){
        if (root == null){
            return 0;
        }
        // 利用定义，计算左右子树的最大深度
        int left = maxDepth2(root.left);
        int right = maxDepth2(root.right);
        // 整棵树的最大深度等于左右子树的最大深度取最大值，
        // 然后再加上根节点自己
        res = Math.max(left, right) + 1;
        return res;
    }

    List<Integer> list = new LinkedList<>();
    // 返回前序遍历结果
    // 遍历思路
    List<Integer> preorderTraverse(TreeNode root) {
        traverse2(root);
        return list;
    }

    // 二叉树遍历函数
    void traverse2(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序位置
        list.add(root.val);
        traverse(root.left);
        traverse(root.right);
    }

    // 分解思路
    /**
     * 这个解法短小精干，但为什么不常见呢？
     * 一个原因是这个算法的复杂度不好把控，比较依赖语言特性。
     * Java 的话无论 ArrayList 还是 LinkedList，addAll 方法的复杂度都是 O(N)，
     * 所以总体的最坏时间复杂度会达到 O(N^2)，
     * 除非你自己实现一个复杂度为 O(1) 的 addAll 方法，底层用链表的话并不是不可能。
     */
    List<Integer> preorderTraverse2(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null){
            return res;
        }
        res.add(root.val);
        res.addAll(preorderTraverse2(root.left));
        res.addAll(preorderTraverse2(root.right));
        return res;
    }

    // 二叉树遍历函数
    // 如果把根节点看做第 1 层，如何打印出每一个节点所在的层数？
    // 这样调用
    //traverse(root, 1);
    void traverse(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        // 前序位置
        System.out.printf("节点 %s 在第 %d 层", root, level);
        traverse(root.left, level + 1);
        traverse(root.right, level + 1);
    }

    // 定义：输入一棵二叉树，返回这棵二叉树的节点总数
    int count(TreeNode root) {
        if (root == null){
            return 0;
        }
        int leftCount = count(root.left);
        int rightCount = count(root.right);
        // 后序位置
        System.out.printf("节点 %s 的左子树有 %d 个节点，右子树有 %d 个节点",
                root, leftCount, rightCount);
        return leftCount + rightCount + 1;
    }

    /**
     * 543.二叉树的直径
     */
    // 记录最大直径的长度
    // 这个解法是正确的，但是运行时间很长，原因也很明显，traverse 遍历每个节点的时候还会调用递归函数 maxDepth，
    // 而 maxDepth 是要遍历子树的所有节点的，所以最坏时间复杂度是 O(N^2)。
    int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        // 对每个节点计算直径，求最大直径
        traverse3(root);
        return maxDiameter;
    }

    void traverse3(TreeNode root){
        if (root == null){
            return;
        }
        int left = maxDepth2(root.left);
        int right = maxDepth2(root.right);
        int myDiameter = left + right;
        maxDiameter = Math.max(myDiameter, maxDiameter);
        traverse3(root.left);
        traverse3(root.right);
    }

    /**
     * 543.二叉树的直径
     * 优化之后：把计算「直径」的逻辑放在后序位置，准确说应该是放在 maxDepth 的后序位置，
     * 因为 maxDepth 的后序位置是知道左右子树的最大深度的。
     * 时间复杂度 O(N)
     */
    // 记录最大直径的长度
    public int diameterOfBinaryTree2(TreeNode root) {
        diameterOfBinaryTree2Helper(root);
        return maxDiameter;
    }

    int diameterOfBinaryTree2Helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = diameterOfBinaryTree2Helper(root.left);
        int rightMax = diameterOfBinaryTree2Helper(root.right);
        // 后序位置，顺便计算最大直径
        int myDiameter = leftMax + rightMax;
        maxDiameter = Math.max(maxDiameter, myDiameter);
        return 1 + Math.max(leftMax, rightMax);
    }

    // 输入一棵二叉树的根节点，层序遍历这棵二叉树
    void levelTraverse(TreeNode root) {
        if (root == null){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 从上到下遍历二叉树的每一层
        while (!queue.isEmpty()){
            int size = queue.size();
            // 从左到右遍历每一层的每个节点
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }
    }

    // 通过递归函数得到层序遍历结果
    List<List<Integer>> list2 = new ArrayList<>();
    List<List<Integer>> levelTraverse2(TreeNode root) {
        if (root == null) {
            return list2;
        }
        // root 视为第 0 层
        traverse3(root, 0);
        return list2;
    }

    void traverse3(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // 前序位置，看看是否已经存储 depth 层的节点了
        if (list2.size() <= depth) {
            // 第一次进入 depth 层
            list2.add(new LinkedList<>());
        }
        // 前序位置，在 depth 层添加 root 节点的值
        list2.get(depth).add(root.val);
        traverse(root.left, depth + 1);
        traverse(root.right, depth + 1);
    }

    /**
     * 226.反转二叉树
     * 递归
     */
    public TreeNode invertTree(TreeNode root) {
        invertTreeTraverse(root);
        return root;
    }

    void invertTreeTraverse(TreeNode root){
        if (root == null){
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTreeTraverse(root.left);
        invertTreeTraverse(root.right);
    }

    /**
     * 226.反转二叉树
     * 分解
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null){
            return root;
        }
        TreeNode left = invertTree2(root.left);
        TreeNode right = invertTree2(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针
     */
    public Node connect(Node root) {
        if (root == null){
            return root;
        }
        connectTraverse(root.left, root.right);
        return root;
    }

    void connectTraverse(Node node1, Node node2){
        if (node1 == null || node2 == null){
            return;
        }
        // 将传入的两个节点穿起来
        node1.next = node2;
        // 连接相同父节点的两个子节点
        connectTraverse(node1.left, node1.right);
        connectTraverse(node2.left, node2.right);
        // 连接跨越父节点的两个子节点
        connectTraverse(node1.right, node2.left);
    }

    /**
     * 114. 二叉树展开为链表
     * 分解
     */
    public void flatten(TreeNode root) {
        // base case
        if (root == null){
            return;
        }
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
        while (p.right != null){
            p = p.right;
        }
        p.right = right;
    }
}
