package SkillCategory.DataStructureDesign.cache;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
public class Medium146_LRUCache {
    /**
     * 146. LRU 缓存
     * LRU（Least Recently Used）最近最少使用
     * 每次淘汰那些最久没被使用的数据,相当于把数据按照时间排序
     * 哈希链表
     * 手动实现 : 双向链表 + HashMap
     * 「为什么必须要用双向链表」:
     * 需要删除操作。删除一个节点不光要得到该节点本身的指针，也需要操作其前驱节点的指针，
     * 而双向链表才能支持直接查找前驱，保证操作的时间复杂度 O(1)。
     */
    class LRUCache {

        // key -> Node(key, val)
        private HashMap<Integer, Node> map;
        // Node(k1, v1) <-> Node(k2, v2)...
        private NodeList cache;
        // 最大容量
        private int cap;

        public LRUCache(int capacity) {
            this.cap = capacity;
            map = new HashMap<>();
            cache = new NodeList();

        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            // 将该数据提升为最近使用的
            makeRecently(key);
            return map.get(key).val;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                // 删除旧的数据
                deleteKey(key);
                // 新插入的数据为最近使用的数据
                addRecently(key, value);
                return;
            }
            if (cap == cache.size) {
                // 删除最久未使用的元素
                removeLeastRecently();
            }
            // 添加为最近使用的元素
            addRecently(key, value);
        }

        /* 将某个 key 提升为最近使用的 */
        private void makeRecently(int key) {
            Node x = map.get(key);
            // 先从链表中删除这个节点
            cache.remove(x);
            // 重新插到队尾
            cache.addLast(x);
        }

        /* 添加最近使用的元素 */
        private void addRecently(int key, int val) {
            Node x = new Node(key, val);
            // 链表尾部就是最近使用的元素
            cache.addLast(x);
            // 别忘了在 map 中添加 key 的映射
            map.put(key, x);
        }

        /* 删除某一个 key */
        private void deleteKey(int key) {
            Node x = map.get(key);
            // 从链表中删除
            cache.remove(x);
            // 从 map 中删除
            map.remove(key);
        }

        /**
         * 「为什么要在链表中同时存储 key 和 val，而不是只存储 val」
         * 需要用 deletedNode 得到 deletedKey。
         * 当缓存容量已满，我们不仅仅要删除最后一个 Node 节点，还要把 map 中映射到该节点的 key 同时删除，
         * 而这个 key 只能由 Node 得到。
         * 如果 Node 结构中只存储 val，那么我们就无法得知 key 是什么，就无法删除 map 中的键，造成错误。
         */
        /* 删除最久未使用的元素 */
        private void removeLeastRecently() {
            // 链表头部的第一个元素就是最久未使用的
            Node deletedNode = cache.removeFirst();
            // 同时别忘了从 map 中删除它的 key
            int deletedKey = deletedNode.key;
            map.remove(deletedKey);
        }

        class NodeList {
            private Node head;
            private Node tail;
            public int size;

            public NodeList() {
                this.head = new Node();
                this.tail = new Node();
                this.size = 0;
                head.next = tail;
                tail.pre = head;
            }

            public void addLast(Node node) {
                node.pre = tail.pre;
                node.next = tail;
                tail.pre.next = node;
                tail.pre = node;
                size++;
            }

            public Node removeFirst() {
                if (head.next == tail) {
                    return null;
                }
                Node node = head.next;
                remove(node);
                return node;
            }

            public void remove(Node node) {
                node.pre.next = node.next;
                node.next.pre = node.pre;
                size--;
            }
        }

        class Node {
            public int key;
            public int val;
            private Node pre;
            private Node next;

            public Node() {
            }

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
    }

    /**
     * 146. LRU 缓存
     * LRU（Least Recently Used）最近最少使用
     * 用Java内置函数LinkedHashMap
     */
    class LRUCache2 {
        int cap;
        LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

        public LRUCache2(int capacity) {
            this.cap = capacity;
        }

        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            // 将 key 变为最近使用
            makeRecently(key);
            return cache.get(key);
        }

        public void put(int key, int val) {
            if (cache.containsKey(key)) {
                // 修改 key 的值
                cache.put(key, val);
                // 将 key 变为最近使用
                makeRecently(key);
                return;
            }

            if (cache.size() >= this.cap) {
                // 链表头部就是最久未使用的 key
                int oldestKey = cache.keySet().iterator().next();
                cache.remove(oldestKey);
            }
            // 将新的 key 添加链表尾部
            cache.put(key, val);
        }

        private void makeRecently(int key) {
            int val = cache.get(key);
            // 删除 key，重新插入到队尾
            cache.remove(key);
            cache.put(key, val);
        }
    }


}
