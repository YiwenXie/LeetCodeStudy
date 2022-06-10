package SkillCategory.tree.normal.operation;

import SkillCategory.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ywxie
 * @date 2022/6/9 14:20
 * @describe
 */
public class Solution {
    /**
     * 100. 相同的树
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return isSameTreeHelper(p, q);
    }

    boolean isSameTreeHelper(TreeNode p, TreeNode q){
        if (p == null && q == null){
            return true;
        }
        if (p == null || q == null){
            return false;
        }
        if (p.val != q.val){
            return false;
        }
        return isSameTreeHelper(p.left, q.left) &&
                isSameTreeHelper(p.right, q.right);

    }

    /**
     * 103. ⼆叉树的锯⻮形层序遍历
     * flag 控制方向
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        if (root == null){
            return res;
        }
        queue.add(root);
        boolean flag = true;
        while (!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (flag){
                    list.addLast(node.val);
                }else {
                    list.addFirst(node.val);
                }
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
            flag = !flag;
            res.add(list);
        }
        return res;
    }

    /**
     * 107. ⼆叉树的层序遍历 II
     * 自顶向下，头插法
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null){
            return res;
        }
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.addLast(node.val);
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
            res.addFirst(list);
        }
        return res;
    }


    /**
     * 111. ⼆叉树的最⼩深度
     * 层序遍历
     * BFS 算法和 DFS（回溯）算法的⼀⼤区别就是，
     * BFS 第⼀次搜索到的结果是最优的，这个得益于 BFS 算法的 搜索逻辑
     */
    public int minDepth(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int depth = 0;
        if (root == null){
            return depth;
        }
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null){
                    return depth;
                }
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
            depth++;
        }
        return depth;
    }
}
