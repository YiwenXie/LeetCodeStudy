package DataStructureCategory.tree.normal;

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
     * SkillCategory.tree.BFS
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
     * 迭代
     */
    public List<Integer> preOrderTraversalByBFS(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }
        return list;
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
     * 145. 二叉树的后序遍历
     * 迭代
     */

    /**
     * 637. 二叉树的层平均值
     * SkillCategory.tree.BFS
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
     * SkillCategory.tree.BFS
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
     * SkillCategory.tree.BFS
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
     * SkillCategory.tree.BFS
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
        int root_val = postorder[post_end];
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
     * SkillCategory.tree.BFS
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
     * SkillCategory.tree.BFS/迭代
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
    // Encodes a DataStructureCategory.tree to a single string.
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
        serializeHelper(node.left, new StringBuilder(sb));
        serializeHelper(node.right, new StringBuilder(sb));
    }

    // Decodes your encoded data to DataStructureCategory.tree.
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
     * 437. 路径总和 III
     * 双重递归思想
     */
    int k = 0;
    public int pathSum2(TreeNode root, int sum) {
        if (root == null){
            return 0;
        }
        pathSumHelper2(root, sum);
        pathSum2(root.left, sum);
        pathSum2(root.right, sum);
        return k;
    }

    private void pathSumHelper2(TreeNode node, int sum){
        if(node == null){
            return;
        }
        sum = sum - node.val;
        if (sum == 0){
            k = k + 1;
        }
        pathSumHelper2(node.left, sum);
        pathSumHelper2(node.right, sum);
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
    int sum = 0;
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

    /**
     * 404. 左叶子之和
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null){
            return 0;
        }
        if (root.left != null && root.left.left == null && root.left.right == null){
            sum = root.left.val;
        }
        sumOfLeftLeaves(root.left);
        sumOfLeftLeaves(root.right);
        return sum;
    }

    /**
     * 513. 找树左下角的值
     * DFS 1.确定递归参数、返回值 2.确定终止条件 3.确定单层递归的逻辑
     * 深度最大的最左叶子节点。
     * 最左->前序遍历
     * 一个深度、一个节点值
     */
    int maxDepth = Integer.MIN_VALUE;
    int maxValue;
    public int findBottomLeftValue(TreeNode root) {
        findBottomLeftValueHelper(root, 0);
        return maxValue;
    }

    private void findBottomLeftValueHelper(TreeNode node, int depth){
        if (node == null){
            return;
        }
        if (node.left == null && node.right == null){
            if (depth > maxDepth){
                maxDepth = depth;
                maxValue = node.val;
            }
            return;
        }
        if (node.left != null){
            findBottomLeftValueHelper(node.left, depth + 1);
        }
        if (node.right != null){
            findBottomLeftValueHelper(node.right, depth + 1);
        }
    }

    /**
     * 513. 找树左下角的值
     * SkillCategory.tree.BFS 即找到最后一行的第一个节点
     */
    public int findBottomLeftValueByBFS(TreeNode root){
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if (i == 0){
                    maxValue = node.val;
                }
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
        }
        return maxValue;
    }

    /**
     * 543. 二叉树的直径
     */
    int ans = 1;
    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTreeHelper(root);
        return ans - 1;
    }

    private int diameterOfBinaryTreeHelper(TreeNode node){
        if (node == null){
            return 0;
        }
        int left = diameterOfBinaryTreeHelper(node.left);
        int right = diameterOfBinaryTreeHelper(node.right);
        ans = Math.max(left+right+1, ans);
        return Math.max(left, right) + 1;
    }

    /**
     * 563. 二叉树的坡度
     * DFS
     */
    public int findTilt(TreeNode root) {
        if (root == null){
            return 0;
        }
        return Math.abs(findTiltHelper(root.left) - findTiltHelper(root.right)) + findTilt(root.left) + findTilt(root.right);
    }

    private int findTiltHelper(TreeNode node){
        if (node == null){
            return 0;
        }
        return findTiltHelper(node.left) + findTiltHelper(node.right) + node.val;
    }

    /**
     * 572. 另一个树的子树
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) {
            return true;   // t 为 null 一定都是 true
        }
        if (s == null) {
            return false;  // 这里 t 一定不为 null, 只要 s 为 null，肯定是 false?
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t) || isSubtreeHelper(s,t);
    }

    private boolean isSubtreeHelper(TreeNode s, TreeNode t){
        if (s == null && t == null){
            return true;
        }
        if (s == null || t == null || s.val != t.val){
            return false;
        }
        return isSubtreeHelper(s.left, t.left) && isSubtreeHelper(s.right, t.right);
    }

    /**
     * 606. 根据二叉树创建字符串
     */
    String leftBracket = "(";
    String rightBracket = ")";
    StringBuilder str = new StringBuilder();
    public String tree2str(TreeNode t) {
        tree2strHelper(t);
        return str.toString();
    }

    private void tree2strHelper(TreeNode node){
        if (node == null){
            return;
        }
        str.append(node.val);
        if (node.left != null || node.right != null){
            str.append(leftBracket);
            tree2str(node.left);
            str.append(rightBracket);
            if (node.right != null){
                str.append(leftBracket);
                tree2str(node.right);
                str.append(rightBracket);
            }
        }
    }

    /**
     * 617. 合并二叉树
     * DFS
     * 重叠的节点相加，不重叠的覆盖
     */
    TreeNode t;
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null){
            return t2;
        }
        if (t2 == null){
            return t1;
        }
        t1.val = t1.val + t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    public TreeNode mergeTreesByBFS(TreeNode t1, TreeNode t2){
        if (t1 == null){
            return t2;
        }
        if (t2 == null){
            return t1;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(t1);
        queue.add(t2);
        while (!queue.isEmpty()){
            TreeNode r1 = queue.remove();
            TreeNode r2 = queue.remove();
            r1.val += r2.val;
            //如果r1和r2的左子树都不为空，就放到队列中
            //如果r1的左子树为空，就把r2的左子树挂到r1的左子树上
            if(r1.left!=null && r2.left!=null){
                queue.add(r1.left);
                queue.add(r2.left);
            }
            else if(r1.left==null) {
                r1.left = r2.left;
            }
            //对于右子树也是一样的
            if(r1.right!=null && r2.right!=null) {
                queue.add(r1.right);
                queue.add(r2.right);
            }
            else if(r1.right==null) {
                r1.right = r2.right;
            }
        }
        return t1;
    }

    /**
     * 623. 在二叉树中增加一行
     * SkillCategory.tree.BFS
     * 遍历到指定深度，然后添加一层值
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (root == null){
            return root;
        }
        if (d == 1){
            TreeNode root2 = new TreeNode(v);
            root2.left = root;
            return root2;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int depth = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.remove();
                if (node.left != null){
                    if (depth == d-1){
                        TreeNode temp = node.left;
                        node.left = new TreeNode(v);
                        node.left.left = temp;
                    }
                    queue.add(node.left);
                }else {
                    if (depth == d-1){
                        node.left = new TreeNode(v);
                    }
                }
                if (node.right != null){
                    if (depth == d-1){
                        TreeNode temp = node.right;
                        node.right = new TreeNode(v);
                        node.right.right = temp;
                    }
                    queue.add(node.right);
                }else {
                    if (depth == d-1){
                        node.right = new TreeNode(v);
                    }
                }
            }
            depth++;
        }
        return root;
    }

    /**
     * 623. 在二叉树中增加一行
     * DFS
     */
    public TreeNode addOneRowByDFS(TreeNode root, int v, int d) {
        if (root == null){
            return root;
        }
        if (d == 1){
            TreeNode root2 = new TreeNode(v);
            root2.left = root;
            return root2;
        }
        addOneRowByDFSHelper(root, v, d, 1);
        return root;
    }

    private void addOneRowByDFSHelper(TreeNode root, int v, int d, int depth){
        if (root == null){
            return;
        }
        if (depth == d - 1){
            if (root.left != null){
                TreeNode temp = root.left;
                root.left = new TreeNode(v);
                root.left.left = temp;
            }else {
                root.left = new TreeNode(v);
            }
            if (root.right != null){
                TreeNode temp = root.right;
                root.right = new TreeNode(v);
                root.right.right = temp;
            }else {
                root.right = new TreeNode(v);
            }
        }
        addOneRowByDFSHelper(root.left, v, d, depth+1);
        addOneRowByDFSHelper(root.right, v, d, depth+1);
    }

    /**
     * 662. 二叉树最大宽度
     * SkillCategory.tree.BFS
     */
    int maxWidth = 0;
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null){
            return 0;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        maxWidth = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            int left = list.peek();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.remove();
                int index = list.poll();
                maxWidth = Math.max(maxWidth, index - left + 1);
                if (node.left != null){
                    list.add(index * 2);
                    queue.add(node.left);
                }
                if (node.right != null) {
                    list.add(index * 2 + 1);
                    queue.add(node.right);
                }
            }
        }
        return maxWidth;
    }

    /**
     * 662. 二叉树最大宽度
     * DFS
     */
    HashMap<Integer, Integer> indexMap = new HashMap<>();
    public int widthOfBinaryTreeByDFS(TreeNode root) {
        widthOfBinaryTreeByDFSHelper(root, 1, 1);
        return maxWidth;
    }

    private void widthOfBinaryTreeByDFSHelper(TreeNode node, int index, int depth){
        if (node == null){
            return;
        }
        if (!indexMap.containsKey(depth)){
            indexMap.put(depth, index);
        }
        maxWidth = Math.max(maxWidth, index - indexMap.get(depth) + 1);
        widthOfBinaryTreeByDFSHelper(node.left, index * 2, depth + 1);
        widthOfBinaryTreeByDFSHelper(node.right, index * 2 + 1, depth + 1);
    }

    /**
     * 671. 二叉树中第二小的节点
     * 最小的值一定是根节点。
     * 问题可以转化为求左右子树的最小值，如果左右子树最小值都大于根节点的值取较小的值。其他情况取左右子树较大的值。
     */
    public int findSecondMinimumValue(TreeNode root) {
        return findSecondMinimumValueHelper(root, root.val);
    }

    private int findSecondMinimumValueHelper(TreeNode node, int val){
        if (node == null){
            return -1;
        }
        //递归找到比根结点大的值时可以立即返回，不用再遍历当前节点下面的子节点，因为子节点的值不可能比它小。
        if (val < node.val){
            return node.val;
        }
        int left = findSecondMinimumValueHelper(node.left, val);
        int right = findSecondMinimumValueHelper(node.right, val);
        if (left > val && right > val){
            return Math.min(left, right);
        }
        return Math.max(left, right);
    }

    /**
     * 814. 二叉树剪枝
     * 后序遍历，破除先序遍历的剪枝思想！
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null){
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if(root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }

    /**
     * 872. 叶子相似的树
     * DFS
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null){
            return false;
        }
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        leafSimilarHelper(root1, list1);
        leafSimilarHelper(root2, list2);
        return list1.equals(list2);
    }

    private void leafSimilarHelper(TreeNode node, List<Integer> list){
        if (node == null){
            return;
        }
        leafSimilarHelper(node.left, list);
        leafSimilarHelper(node.right, list);
        if (node.left == null && node.right == null){
            list.add(node.val);
        }
    }

    /**
     * 897. 递增顺序查找树
     * 非重建Tree
     */
    public TreeNode increasingBST(TreeNode root) {
        if (root == null){
            return null;
        }
        LinkedList<Integer> list = new LinkedList<>();
        arrayBST(root, list);
        TreeNode node = new TreeNode(list.removeFirst());
        TreeNode pre = node;
        while (!list.isEmpty()){
            pre.right = new TreeNode(list.removeFirst());
            pre = pre.right;
        }
        return node;
    }

    private void arrayBST(TreeNode node, LinkedList<Integer> list){
        if (node == null){
            return;
        }
        arrayBST(node.left, list);
        list.add(node.val);
        arrayBST(node.right, list);
    }

    /**
     * 897. 递增顺序查找树
     * 原地重建Tree
     * 当我们遍历到一个节点时，把它的左孩子设为空，并将其本身作为上一个遍历到的节点的右孩子。
     */
    TreeNode head = new TreeNode(0);
    TreeNode cur = head;
    public TreeNode increasingBST2(TreeNode root) {
        if (root == null){
            return null;
        }
        increasingBST2(root.left);
        cur.right = new TreeNode(root.val);
        cur = cur.right;
        increasingBST2(root.right);
        return head.right;
    }

    /**
     * 863. 二叉树中所有距离为 K 的结点
     */
//    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
//
//    }

    /**
     * 965. 单值二叉树
     */
//    int val = 0;
    boolean flag = true;
    public boolean isUnivalTree(TreeNode root) {
        if (root == null){
            return false;
        }
        val = root.val;
        isUnivalTreeHelper(root.left);
        isUnivalTreeHelper(root.right);
        return flag;
    }

    private void isUnivalTreeHelper(TreeNode node){
        if (node == null){
            return;
        }
        if (val != node.val){
            flag = false;
            return;
        }
        isUnivalTreeHelper(node.left);
        isUnivalTreeHelper(node.right);
    }

    /**
     * 993. 二叉树的堂兄弟节点
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null || x == y || root.val == x || root.val == y){
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depthX= 0;
        int depthY = 0;
        int depth = 0;
        int preX = 0;
        int preY = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if (node.val == x){
                    depthX = depth;
                }
                if (node.val == y){
                    depthY = depth;
                }
                if (depthX != 0 && depthY != 0 && depthX == depthY && preX != 0 && preY != 0 && preX != preY){
                    return true;
                }
                if (node.left != null){
                    if (node.left.val == x){
                        preX = node.val;
                    }
                    if (node.left.val == y){
                        preY = node.val;
                    }
                    queue.add(node.left);
                }
                if (node.right != null){
                    if (node.right.val == x){
                        preX = node.val;
                    }
                    if (node.right.val == y){
                        preY = node.val;
                    }
                    queue.add(node.right);
                }
            }
            depth++;
        }
        return false;
    }

    /**
     * 337. 打家劫舍 III
     * 动态规划
     */
    public int rob(TreeNode root) {
        if (root == null){
            return 0;
        }
        int[] res = robHelper(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robHelper(TreeNode node){
        if (node == null){
            return new int[]{0, 0};
        }
        // 分类讨论的标准是：当前结点偷或者不偷
        // 由于需要后序遍历，所以先计算左右子结点，然后计算当前结点的状态值
        int[] left = robHelper(node.left);
        int[] right = robHelper(node.right);

        // dp[0]：以当前 node 为根结点的子树能够偷取的最大价值，规定 node 结点不偷
        // dp[1]：以当前 node 为根结点的子树能够偷取的最大价值，规定 node 结点偷

        int[] dp = new int[2];
        //如果当前结点不偷，左右子结点偷或者不偷都行，选最大者；
        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        //如果当前结点偷，左右子结点均不能偷。
        dp[1] = node.val + left[0] + right[0];
        return dp;
    }

    /**
     * 687. 最长同值路径
     */
//    int ans = 0;
    public int longestUnivaluePath(TreeNode root) {
        longestUnivaluePathHelper(root);
        return ans;
    }

    private int longestUnivaluePathHelper(TreeNode node){
        if (node == null) {
            return 0;
        }
        int left = longestUnivaluePathHelper(node.left);
        int right = longestUnivaluePathHelper(node.right);
        int arrowLeft = 0, arrowRight = 0;
        if (node.left != null && node.left.val == node.val) {
            arrowLeft += left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRight += right + 1;
        }
        ans = Math.max(ans, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }

    /**
     * 863. 二叉树中所有距离为 K 的结点
     */
    Map<TreeNode, TreeNode> parentMap = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        if (root == null){
            return null;
        }
        distanceKDFS(root, null);
        Queue<TreeNode> queue = new LinkedList();
        queue.add(null);
        queue.add(target);

        Set<TreeNode> seen = new HashSet();
        seen.add(target);
        seen.add(null);

        int dist = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                if (dist == K) {
                    List<Integer> ans = new ArrayList();
                    for (TreeNode n: queue) {
                        ans.add(n.val);
                    }
                    return ans;
                }
                queue.offer(null);
                dist++;
            } else {
                if (!seen.contains(node.left)) {
                    seen.add(node.left);
                    queue.offer(node.left);
                }
                if (!seen.contains(node.right)) {
                    seen.add(node.right);
                    queue.offer(node.right);
                }
                TreeNode par = parentMap.get(node);
                if (!seen.contains(par)) {
                    seen.add(par);
                    queue.offer(par);
                }
            }
        }
        return new ArrayList<>();
    }

    private void distanceKDFS(TreeNode node, TreeNode parent){
        if (node == null){
            return;
        }
        parentMap.put(node, parent);
        distanceKDFS(node.left, node);
        distanceKDFS(node.right, node);
    }

    /**
     * 1123. 最深叶节点的最近公共祖先
     * 回溯 -> 后序遍历
     * Link236
     * 我：先找最深节点，再找公共祖先
     * 大神解题思路：
     * 如果当前节点是最深叶子节点的最近公共祖先，那么它的左右子树的高度一定是相等的，
     * 否则高度低的那个子树的叶子节点深度一定比另一个子树的叶子节点的深度小，因此不满足条件。
     * 所以只需要dfs遍历找到左右子树高度相等的根节点即出答案。
     */
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        int leftSubTreeDepth = lcaDeepestLeavesHelper(root.left);
        int rightSubTreeDepth = lcaDeepestLeavesHelper(root.right);

        if(leftSubTreeDepth == rightSubTreeDepth) {
            return root;
        }

        return leftSubTreeDepth > rightSubTreeDepth ? lcaDeepestLeaves(root.left) : lcaDeepestLeaves(root.right);
    }

    private int lcaDeepestLeavesHelper(TreeNode node) {
        if(node == null){
            return 0;
        }
        return Math.max(lcaDeepestLeavesHelper(node.left), lcaDeepestLeavesHelper(node.right))+1;
    }
}
