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
     * 116. 填充每个节点的下一个右侧节点指针
     * DFS2
     */
    public Node connectByDFS2(Node root) {
        if (root == null) {
            return null;
        }
        connectTwoNode(root.left, root.right);
        return root;
    }

    // 辅助函数
    private void connectTwoNode(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        /**** 前序遍历位置 ****/
        // 将传入的两个节点连接
        node1.next = node2;

        // 连接相同父节点的两个子节点
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node2.left, node2.right);
        // 连接跨越父节点的两个子节点
        connectTwoNode(node1.right, node2.left);
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

    /**
     * 889 根据前序和后序遍历构造二叉树
     * 返回与给定的前序和后序遍历匹配的任何二叉树
     * 根左右、左右根
     * 递归
     */
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return constructFromPrePostHelper(pre, 0, pre.length-1, post, 0, post.length-1);
    }

    private TreeNode constructFromPrePostHelper(int[] pre, int pre_start, int pre_end, int[] post, int post_start, int post_end){
        if (pre_end < pre_start || post_end < post_start){
            return null;
        }
        int root_val = pre[pre_start];
        TreeNode root = new TreeNode(root_val);
        if (pre_start == pre_end){
            return root;
        }
        int left_root_index = post_start;
        int left_root_val = pre[pre_start+1];
        for (; left_root_index < post_end; left_root_index++){
            if (left_root_val == post[left_root_index]){
                break;
            }
        }
        //左子树个数
        int left_num = left_root_index - post_start;
        root.left = constructFromPrePostHelper(pre, pre_start+1, pre_start+1+left_num, post, post_start, left_root_index);
        root.right = constructFromPrePostHelper(pre, pre_start+2+left_num, pre_end, post, left_root_index+1, post_end-1);
        return root;
    }

    /**
     * 111. 二叉树的最小深度
     * DFS
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * 很多人写出的代码都不符合 1,2 这个测试用例，是因为没搞清楚题意
     * 题目中说明:叶子节点是指没有子节点的节点，这句话的意思是 1 不是叶子节点
     * 题目问的是到叶子节点的最短距离，所以所有返回结果为 1 当然不是这个结果
     * 另外这道题的关键是搞清楚递归结束条件
     *     叶子节点的定义是左孩子和右孩子都为 null 时叫做叶子节点
     *     当 root 节点左右孩子都为空时，返回 1
     *     当 root 节点左右孩子有一个为空时，返回不为空的孩子节点的深度
     *     当 root 节点左右孩子都不为空时，返回左右孩子较小深度的节点值
     */
    public int minDepth(TreeNode root) {
        if(root == null)return 0;
        int m1 = minDepth(root.left);
        int m2 = minDepth(root.right);
        //1.如果左孩子和右孩子有为空的情况，直接返回m1+m2+1
        //2.如果都不为空，返回较小深度+1
        return root.left == null || root.right == null ? m1 + m2 + 1 : Math.min(m1,m2) + 1;
    }

    /**
     * 111. 二叉树的最小深度
     * BFS
     **/
    public int minDepthByBFS(TreeNode root) {
        if (root == null){
            return 0;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int depth = 1;
        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++){
                TreeNode node = q.poll();
                if (node.left == null && node.right == null){
                    return depth;
                }
                if (node.left != null){
                    q.offer(node.left);
                }
                if (node.right != null){
                    q.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }

    /**
     * 226. 翻转二叉树
     * BFS/迭代
     */
    public TreeNode invertTreeByBFS(TreeNode root) {
        if (root == null){
            return root;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                TreeNode left = node.left;
                node.left = node.right;
                node.right = left;
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
     * 226. 翻转二叉树
     * 递归/DFS
     * 二叉树题目的一个难点就是，如何把题目的要求细化成每个节点需要做的事情。
     */
    public TreeNode invertTreeByDFS(TreeNode root){
        if (root == null){
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTreeByDFS(root.left);
        invertTreeByDFS(root.right);

        return root;

    }

    /**
     * 114. 二叉树展开为链表
     * 反向先序遍历 - 右 左 根
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            //左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                //将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
                root = root.right;
            }
        }
    }
    /**
     * 114. 二叉树展开为链表
     * DFS/递归
     * 你看，这就是递归的魅力，你说 flatten 函数是怎么把左右子树拉平的？说不清楚，
     * 但是只要知道 flatten 的定义如此，相信这个定义，让 root 做它该做的事情，
     * 然后 flatten 函数就会按照定义工作。另外注意递归框架是后序遍历，
     * 因为我们要先拉平左右子树才能进行后续操作。
     */
    public void flatten2(TreeNode root) {
        // base case
        if (root == null) {
            return;
        }
        flatten2(root.left);
        flatten2(root.right);

        /**** 后序遍历位置 ****/
        // 1、左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;
        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;
        // 3、将原先的右子树接到当前右子树的末端
        TreeNode pre = root;
        while (pre.right != null){
            pre = pre.right;
        }

        pre.right = right;
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
     * 110. 平衡二叉树
     * DFS
     * 复杂度分析：
     *     时间复杂度 O(N)：N为树的节点数；最差情况下，需要递归遍历树的所有节点。
     *     空间复杂度 O(N)：最差情况下（树退化为链表时），系统递归需要使用 O(N)的栈空间。
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null){
            return true;
        }
        return isBalancedHelper(root) != -1;
    }

    private int isBalancedHelper(TreeNode node){
        if (node == null){
            return 0;
        }
        int leftDepth = isBalancedHelper(node.left);
        if (leftDepth == -1){
            return -1;
        }
        int rightDepth = isBalancedHelper(node.right);
        if (rightDepth == -1) {
             return -1;
        }
        return Math.abs(leftDepth - rightDepth) < 2 ? Math.max(leftDepth, rightDepth) + 1 : -1;
    }

    /**
     * 654. 最大二叉树
     * 递归
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTreeHelper(nums, 0, nums.length-1);
    }

    private TreeNode constructMaximumBinaryTreeHelper(int[] nums, int low, int high){
        if (low > high){
            return null;
        }
        int maxVal = Integer.MIN_VALUE;
        int index = -1;
        for (int i = low; i <= high; i++){
            if (nums[i] > maxVal){
                maxVal = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(maxVal);
        root.left = constructMaximumBinaryTreeHelper(nums, low, index-1);
        root.right = constructMaximumBinaryTreeHelper(nums, index+1, high);
        return root;
    }


    /**
     * 652. 寻找重复的子树
     * 1、以我为根的这棵二叉树（子树）长啥样？
     * 2、以其他节点为根的子树都长啥样？
     * 我要知道以自己为根的子树长啥样，是不是得先知道我的左右子树长啥样，再加上自己，就构成了整棵子树的样子？
     * 后序遍历
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        HashMap<String, Integer> map = new HashMap<>();
        LinkedList<TreeNode> res = new LinkedList<>();
        findDuplicateSubtreesHelper(root, map, res);
        return res;
    }

    private String findDuplicateSubtreesHelper(TreeNode node, HashMap<String, Integer> map, LinkedList<TreeNode> res) {
        if (node == null){
            return "#";
        }
        String left = findDuplicateSubtreesHelper(node.left, map, res);
        String right = findDuplicateSubtreesHelper(node.right, map, res);
        String subTree = left + "," + right + "," + node.val;

        int freq = map.getOrDefault(subTree, 0);
        if (freq == 1){
            res.add(node);
        }
        map.put(subTree, freq + 1);
        return subTree;
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
     * 236. 二叉树的最近公共祖先
     * git原理
     * 递归解析：
     *     终止条件：
     *         当越过叶节点，则直接返回 nullnullnull ；
     *         当 rootrootroot 等于 p,qp, qp,q ，则直接返回 rootrootroot ；
     *     递推工作：
     *         开启递归左子节点，返回值记为 leftleftleft ；
     *         开启递归右子节点，返回值记为 rightrightright ；
     *     返回值： 根据 leftleftleft 和 rightrightright ，可展开为四种情况；
     *         当 leftleftleft 和 rightrightright 同时为空 ：说明 rootrootroot 的左 / 右子树中都不包含 p,qp,qp,q ，返回 nullnullnull ；
     *         当 leftleftleft 和 rightrightright 同时不为空 ：说明 p,qp, qp,q 分列在 rootrootroot 的 异侧 （分别在 左 / 右子树），因此 rootrootroot 为最近公共祖先，返回 rootrootroot ；
     *         当 leftleftleft 为空 ，rightrightright 不为空 ：p,qp,qp,q 都不在 rootrootroot 的左子树中，直接返回 rightrightright 。具体可分为两种情况：
     *             p,qp,qp,q 其中一个在 rootrootroot 的 右子树 中，此时 rightrightright 指向 ppp（假设为 ppp ）；
     *             p,qp,qp,q 两节点都在 rootrootroot 的 右子树 中，此时的 rightrightright 指向 最近公共祖先节点 ；
     *         当 leftleftleft 不为空 ， rightrightright 为空 ：与情况 3. 同理；
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null){
            return null;
        }
        if (right == null){
            return left;
        }
        if (left == null){
            return right;
        }
        return root;
    }

    /**
     * 222. 完全二叉树的节点个数
     * 满二叉树的节点总数： 2^h - 1
     */
    public int countNodes(TreeNode root) {
        if (root == null){
            return 0;
        }
        TreeNode l = root;
        TreeNode r = root;
        int hl = 0;
        int hr = 0;
        while (l != null){
            l = l.left;
            hl++;
        }
        while (r.right != null){
            r = r.right;
            hr++;
        }
        if (hl == hr){
            return (int) Math.pow(2, hl) - 1;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * 297. 二叉树的序列化与反序列化
     * 递归+前序遍历
     */
    String SEP = ",";
    String NULL = "#";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb){
        if (node == null) {
            sb.append(NULL).append(SEP);
            return;
        }
        sb.append(node.val).append(SEP);
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()){
            return null;
        }
        String[] nodes = data.split(SEP);
        LinkedList<String> list = new LinkedList<>(Arrays.asList(nodes));
        return deserializeHelper(list);
    }

    private TreeNode deserializeHelper(LinkedList<String> list){
        String str = list.removeFirst();
        if (list.isEmpty()){
            return null;
        }
        if (NULL.equals(str)){
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(str));
        node.left = deserializeHelper(list);
        node.right = deserializeHelper(list);
        return node;
    }

    /**
     * 112. 路径总和
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null){
            return false;
        }
        if (root.left == null && root.right == null){
            return sum - root.val == 0;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 113. 路径总和 II
     * DFS
     */
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> list = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        pathSumHelper(root, sum);
        return res;
    }

    private void pathSumHelper(TreeNode node, int sum){
        if (node == null){
            return;
        }
        sum = sum - node.val;
        list.add(node.val);
        if (sum == 0 && node.left == null && node.right == null){
            res.add(new LinkedList<>(list));
        }
        pathSumHelper(node.left, sum);
        pathSumHelper(node.right, sum);
        list.removeLast();
    }

    /**
     * 124. 二叉树中的最大路径和
     */
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxPathSumHelper(root);
        return max;
    }

    private int maxPathSumHelper(TreeNode node){
        if (node == null){
            return 0;
        }
        //计算左边分支最大值，左边分支如果为负数还不如不选择
        int left = Math.max(0, maxPathSumHelper(node.left));
        //计算右边分支最大值，右边分支如果为负数还不如不选择
        int right = Math.max(0, maxPathSumHelper(node.right));
        //left->root->right 作为路径与已经计算过历史最大值做比较
        max = Math.max(max, node.val + left + right);
        // 返回经过root的单边最大分支给当前root的父节点计算使用
        return Math.max(left, right) + node.val;
    }

    /**
     * 129. 求根到叶子节点数字之和
     */
    public int sumNumbers(TreeNode root) {
        if (root == null){
            return 0;
        }
        sumNumbersHelper(root, 0);
        return sum;
//        List<Integer> sumList = new ArrayList<>();
//        for (List<Integer> list: res){
//            int size = list.size();
//            int sum = 0;
//            for (int i = 0; i < size; i++){
//                sum = (int) (list.get(i) * (Math.pow(10, size - i - 1)) + sum);
//            }
//            sumList.add(sum);
//        }
//        int j = 0;
//        for (int num : sumList){
//            j = num + j;
//        }
//        return j;
    }

    private void sumNumbersHelper(TreeNode node, int val){
        if (node == null){
            return;
        }
        int k = val * 10 + node.val;
//        list.add(node.val);
        if (node.left == null && node.right == null){
            sum = sum + k;
//            res.add(new LinkedList<>(list));
        }
        sumNumbersHelper(node.left, k);
        sumNumbersHelper(node.right, k);
//        list.removeLast();
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
     * 257. 二叉树的所有路径
     */
    private String arrow = "->";
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new LinkedList<>();
        binaryTreePathsHelper(root, new StringBuilder(), list);
        return list;
    }

    private void binaryTreePathsHelper(TreeNode node, StringBuilder sb, List<String> list){
        if (node == null){
            return;
        }
        sb = sb.append(node.val);
        if (node.left == null && node.right == null){
            list.add(sb.toString());
        }else {
            binaryTreePathsHelper(node.left, new StringBuilder(sb).append(arrow), list);
            binaryTreePathsHelper(node.right, new StringBuilder(sb).append(arrow), list);
        }
    }
}
