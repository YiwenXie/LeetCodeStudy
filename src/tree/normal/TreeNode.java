package tree.normal;

import java.util.*;

/**
 * @author ywxie
 * @date 2020/12/1 14:12
 * @describe 二叉树
 */
public class TreeNode {
    private int val;
    private TreeNode left;
    private TreeNode right;
    private TreeNode(int x) { val = x; }

    //103. 二叉树的锯齿形层次遍历
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root != null){
            stack.push(root);
        }
        int layer = 0;
        boolean isOdd = false;
        while(!stack.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int size = stack.size();
            if (layer % 2 != 0){
                isOdd = true;
            }else {
                isOdd = false;
            }
            Stack<TreeNode> st = new Stack<>();
            for(int i = 0; i < size; i++){
                TreeNode node = stack.pop();
                list.add(node.val);
                if (isOdd){
                    if (node.left != null){
                        st.push(node.left);
                    }
                    if (node.right != null){
                        st.push(node.right);
                    }
                }else {
                    if (node.right != null){
                        st.push(node.right);
                    }
                    if (node.left != null){
                        st.push(node.left);
                    }
                }
            }
            stack = st;
            layer++;
            res.add(list);
        }
        return res;
    }

    /**
     * 107. 二叉树的层次遍历 II
     * BFS
     */
    public List<List<Integer>> levelOrderBottomByBFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        //or
//        LinkedList<List<Integer>> res2 = new LinkedList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null){
            queue.add(root);
        }
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i ++){
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
            res.add(list);
            //or
//            res2.addFirst(list);
        }
        int length = res.size();
        List<List<Integer>> lists = new ArrayList<>();
        for (int j = length-1; j >= 0; j--){
            lists.add(res.get(j));
        }
        return lists;
        //or
//        return res2;
    }

    /**
     * 107. 二叉树的层次遍历 II
     * DFS
     */
    public List<List<Integer>> levelOrderBottomByDFS(TreeNode root){
        List<List<Integer>> res = new LinkedList<>();
        levelOrderBottomDFS(root, 0, res);
        return res;
    }

    private void levelOrderBottomDFS(TreeNode node, int depth, List<List<Integer>> res) {
        if(node == null){
            return;
        }
        if(depth == res.size()){
            res.add(0, new ArrayList<>());
        }
        res.get(res.size()-depth-1).add(node.val);
        levelOrderBottomDFS(node.left, depth+1, res);
        levelOrderBottomDFS(node.right,depth+1, res);
    }

    /**
     * 199. 二叉树的右视图
     * BFS层次遍历，每次保留最后即最右结点
     */
    public List<Integer> rightSideViewByBFS(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null){
            queue.add(root);
        }
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
                if (i == size -1){
                    list.add(node.val);
                }
            }
        }
        return list;
    }

    /**
     * 199. 二叉树的右视图
     * DFS
     */
    public List<Integer> rightSideViewByDFS(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        rightSideViewDFS(root, 0, list);
        return list;
    }

    private void rightSideViewDFS(TreeNode node, int depth, List<Integer> list){
        if (node == null){
            return;
        }
        if (depth == list.size()){
            list.add(node.val);
        }
        depth++;
        rightSideViewDFS(node.right, depth, list);
        rightSideViewDFS(node.left, depth, list);
    }

    /**
     * 144. 二叉树的前序遍历
     * DFS
     */
    public List<Integer> preOrderTraversalByDFS(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrderTraversalDFS(root, list);
        return list;
    }

    private void preOrderTraversalDFS(TreeNode node, List<Integer> list){
        if (node == null){
            return;
        }
        list.add(node.val);
        preOrderTraversalDFS(node.left, list);
        preOrderTraversalDFS(node.right, list);
    }

    /**
     * 144. 二叉树的前序遍历
     * BFS
     */
    public List<Integer> preOrderTraversalByBFS(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        return list;
    }

    //102.二叉树的层序遍历
    //107.二叉树的层次遍历II
    //199.二叉树的右视图
    //637.二叉树的层平均值
    //429.N叉树的前序遍历
    //515.在每个树行中找最大值
    //
    //116.填充每个节点的下一个右侧节点指针
    //
    //117.填充每个节点的下一个右侧节点指针II
}
