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
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

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
     * 145. 二叉树的后序遍历
     * DFS
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postOrderTraversalDFS(root, list);
        return list;
    }

    private void postOrderTraversalDFS(TreeNode node, List<Integer> list){
        if (node == null){
            return;
        }
        postOrderTraversalDFS(node.left, list);
        postOrderTraversalDFS(node.right, list);
        list.add(node.val);
    }

    /**
     * 637. 二叉树的层平均值
     * BFS
     */
    public List<Double> averageOfLevelsByBFS(TreeNode root) {
        List<Double> list = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null){
            queue.add(root);
        }
        while (!queue.isEmpty()){
            int size = queue.size();
            Double sum = 0.0;
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                sum = sum + node.val;
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
            Double average = sum / size;
            list.add(average);
        }
        return list;
    }

    /**
     * 637. 二叉树的层平均值
     * DFS
     */
    public List<Double> averageOfLevelsByDFS(TreeNode root) {
        List<Double> list = new ArrayList<>();
        List<Double> sumList = new ArrayList<>();
        List<Integer> sizeList = new ArrayList<>();
        averageOfLevelsDFS(root, 0, sumList, sizeList);
        for (int i = 0; i < sumList.size(); i++){
            list.add(sumList.get(i)/sizeList.get(i));
        }
        return list;
    }

    private void averageOfLevelsDFS(TreeNode node, int depth, List<Double> sumList, List<Integer> sizeList){
        if (node == null){
            return;
        }
        //初始化
        if (depth < sumList.size()){
            sumList.set(depth, sumList.get(depth)+(double)node.val);
            sizeList.set(depth, sizeList.get(depth)+1);

        }else {
            sumList.add((double) node.val);
            sizeList.add(1);
        }
        averageOfLevelsDFS(node.left, depth+1, sumList, sizeList);
        averageOfLevelsDFS(node.right, depth+1, sumList, sizeList);
    }

    /**
     * 515. 在每个树行中找最大值
     * BFS
     */
    public List<Integer> largestValuesByBFS(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null){
            queue.add(root);
        }
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> max = new ArrayList<>();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if (max.size() <= 0){
                    max.add(node.val);
                }else {
                    max.set(0, Math.max(node.val, max.get(0)));
                }
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
            list.add(max.get(0));
        }
        return list;
    }

    /**
     * 515. 在每个树行中找最大值
     * DFS
     */
    public List<Integer> largestValuesByDFS(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        largestValuesDFS(root, 0, list);
        return list;
    }

    private void largestValuesDFS(TreeNode node, int depth, List<Integer> list){
        if (node == null){
            return;
        }
        if (depth < list.size()){
            list.set(depth, Math.max(list.get(depth), node.val));
        }else {
            list.add(node.val);
        }
        largestValuesDFS(node.left, depth+1, list);
        largestValuesDFS(node.right, depth+1, list);
    }

    //116.填充每个节点的下一个右侧节点指针
    //117.填充每个节点的下一个右侧节点指针II
}
