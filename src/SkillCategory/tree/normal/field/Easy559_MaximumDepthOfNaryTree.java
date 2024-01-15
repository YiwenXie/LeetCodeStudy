package SkillCategory.tree.normal.field;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Easy559_MaximumDepthOfNaryTree {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    int maxDepth = 0;

    /**
     * Solution: BFS
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        if (root == null) {
            return maxDepth;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.children != null) {
                    for (Node child : node.children) {
                        if (child != null) {
                            queue.add(child);
                        }
                    }
                }
            }
            maxDepth++;
        }
        return maxDepth;
    }

    /**
     * Solution: recursion
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param root
     * @return
     */
    public int maxDepth2(Node root) {
        if (root == null) {
            return maxDepth;
        }
        recursion(root, 1);
        return maxDepth;
    }

    private void recursion(Node root, int depth) {
        maxDepth = Math.max(depth, maxDepth);
        if (root.children != null) {
            for (Node child : root.children) {
                if (child != null) {
                    recursion(child, depth + 1);
                }
            }
        }
    }
}
