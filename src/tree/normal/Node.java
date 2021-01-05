package tree.normal;

import java.util.*;

/**
 * @author ywxie
 * @date 2020/12/2 14:46
 * @describe N叉树
 */
public class Node {
    private int val;
    private List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }

    /**
     * 429. N 叉树的层序遍历
     * BFS
     */
    public List<List<Integer>> levelOrderByBFS(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        if (root != null){
            queue.add(root);
        }
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++){
                Node node = queue.poll();
                list.add(node.val);
                if (!node.children.isEmpty()){
                    queue.addAll(node.children);
                }
            }
            res.add(list);
        }
        return res;
    }

    /**
     * 429. N 叉树的层序遍历
     * DFS
     */
    public List<List<Integer>> levelOrderByDFS(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrderDFS(root, 0 ,res);
        return res;
    }

    private void levelOrderDFS(Node node, int depth, List<List<Integer>> res){
        if (node == null){
            return;
        }
        if (depth == res.size()){
            res.add(new ArrayList<>());
        }
        res.get(depth).add(node.val);
        for (Node children: node.children){
            levelOrderDFS(children, depth+1, res);
        }

    }

    /**
     * 559. N 叉树的最大深度
     * DFS
     */
    public int maxDepthByDFS(Node root) {
        if (root == null){
            return 0;
        }
        if (root.children == null){
            return 1;
        }
        int depth = 0;
        for (Node node: root.children){
            depth = Math.max(maxDepthByDFS(node), depth);
        }
        return depth+1;
    }

    public int maxDepthByBFS(Node root){
        if (root == null){
            return 0;
        }
        if (root.children == null){
            return 1;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                Node node = queue.poll();
                if (node.children != null){
                    for (Node child: node.children){
                        queue.add(child);
                    }
                }
            }
            depth++;
        }
        return depth;
    }

    /**
     * 589. N叉树的前序遍历
     */
    List<Integer> res = new LinkedList<>();
    public List<Integer> preorder(Node root) {
        if (root == null){
            return res;
        }
        res.add(root.val);
        for (Node child: root.children){
            preorder(child);
        }
        return res;
    }

    /**
     * 590. N叉树的后序遍历
     * DFS
     */
    public List<Integer> postorder(Node root) {
        if (root == null){
            return res;
        }
        for (Node child: root.children){
            postorder(child);
        }
        res.add(root.val);
        return res;
    }
}
