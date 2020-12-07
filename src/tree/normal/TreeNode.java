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

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针
     * BFS
     */
    public Node connectByBFS(Node root) {
        if (root == null){
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                Node node = queue.poll();
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针
     * DFS
     */
    public Node connectByDFS(Node root) {
        if (root == null){
            return root;
        }
        if(root.left!=null){
            root.left.next=root.right;
            root.right.next=root.next!=null?root.next.left:null;
            connectByDFS(root.left);
            connectByDFS(root.right);
        }
        return root;
    }

    /**
     * 117.填充每个节点的下一个右侧节点指针II
     * BFS
     */
    public Node connect2ByBFS(Node root) {
        if (root == null){
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                Node node = queue.poll();
                if (i < size - 1){
                    node.next = queue.peek();
                }
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 递归
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildTreeHelper(int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end) {
        // preorder 为空，直接返回 null
        if (p_start == p_end) {
            return null;
        }
        int root_val = preorder[p_start];
        TreeNode root = new TreeNode(root_val);
        //在中序遍历中找到根节点的位置
        int i_root_index = 0;
        for (int i = i_start; i < i_end; i++) {
            if (root_val == inorder[i]) {
                i_root_index = i;
                break;
            }
        }
        int leftNum = i_root_index - i_start;
        //递归的构造左子树
        root.left = buildTreeHelper(preorder, p_start + 1, p_start + leftNum + 1, inorder, i_start, i_root_index);
        //递归的构造右子树
        root.right = buildTreeHelper(preorder, p_start + leftNum + 1, p_end, inorder, i_root_index + 1, i_end);
        return root;
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 迭代
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 递归
     * 左根右、左右根
     * 规律总结：下标位置根据根节点，与前序+中序相反
     */
    public TreeNode buildTree3(int[] inorder, int[] postorder) {
        return buildTreeHelper2(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }

    private TreeNode buildTreeHelper2(int[] inorder, int in_start, int in_end, int[] postorder, int post_start, int post_end){
        if (in_end < in_start || post_end < post_start){
            return null;
        }
        int root_val = postorder[post_end-1];
        TreeNode root = new TreeNode(root_val);
        int root_index = 0;
        //此处可用map优化
        for (int i = 0; i < in_end; i++){
            if (root_val == inorder[i]){
                root_index = i;
                break;
            }
        }
        int left_num = root_index - in_start;
        root.left = buildTreeHelper2(inorder, in_start, root_index-1, postorder, post_start, post_start+left_num-1);
        root.right = buildTreeHelper2(inorder, root_index+1, in_end, postorder, post_start+left_num, post_end-1);
        return root;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 迭代
     * 左根右\左右根
     * 规律总结：与前序+中序相反的遍历顺序
     */
    public TreeNode buildTree4(int[] inorder, int[] postorder) {
        if (postorder == null || postorder.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length-1]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = inorder.length - 1;
        for (int i = postorder.length - 2; i >= 0; i--) {
            int postorderVal = postorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.right = new TreeNode(postorderVal);
                stack.push(node.right);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex--;
                }
                node.left = new TreeNode(postorderVal);
                stack.push(node.left);
            }
        }
        return root;
    }

}
