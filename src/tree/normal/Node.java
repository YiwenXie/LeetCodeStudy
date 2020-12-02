package tree.normal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
}
