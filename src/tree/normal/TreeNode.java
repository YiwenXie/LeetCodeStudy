package tree.normal;

import java.util.*;

/**
 * @author ywxie
 * @date 2020/12/1 14:12
 * @describe shu
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
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
    public List<List<Integer>> levelOrderBottom2(TreeNode root){
        List<List<Integer>> res = new LinkedList<>();
        dfs(root, 0, res);
        return res;
    }

    public void dfs(TreeNode node, int depth, List<List<Integer>> res) {
        if(node == null){
            return;
        }
        if(depth == res.size()){
            res.add(0, new ArrayList<>());
        }
        res.get(res.size()-depth-1).add(node.val);
        dfs(node.left, depth+1, res);
        dfs(node.right,depth+1, res);
    }
}
