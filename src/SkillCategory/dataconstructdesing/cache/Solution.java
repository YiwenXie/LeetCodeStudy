package SkillCategory.dataconstructdesing.cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/**
 * @author ywxie
 * @date 2022/6/11 21:56
 * @describe 数据结构设计
 */
public class Solution {
    /**
     * 146. LRU 缓存
     * LRU（Least Recently Used）最近最少使用
     * 每次淘汰那些最久没被使用的数据,相当于把数据按照时间排序
     * 手动实现
     * 哈希链表
     * 「为什么必须要用双向链表」:
     * 需要删除操作。删除一个节点不光要得到该节点本身的指针，也需要操作其前驱节点的指针，
     * 而双向链表才能支持直接查找前驱，保证操作的时间复杂度 O(1)。
     */
    class LRUCache {

        // key -> Node(key, val)
        private HashMap<Integer, Node> map;
        // Node(k1, v1) <-> Node(k2, v2)...
        private DoubleList cache;
        // 最大容量
        private int cap;

        public LRUCache(int capacity) {
            this.cap = capacity;
            map = new HashMap<>();
            cache = new DoubleList();

        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            // 将该数据提升为最近使用的
            makeRecently(key);
            return map.get(key).value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                // 删除旧的数据
                deleteKey(key);
                // 新插入的数据为最近使用的数据
                addRecently(key, value);
                return;
            }
            if (cap == cache.size()) {
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


    /**
     * 460. LFU 缓存
     * LFU 算法的淘汰策略是 Least Frequently Used
     * LFU 算法是淘汰访问频次最低的数据，如果访问频次最低的数据有多条，需要淘汰最旧的数据。
     */
    class LFUCache {
        // key 到 val 的映射，我们后文称为 KV 表
        HashMap<Integer, Integer> keyToVal;
        // key 到 freq 的映射，我们后文称为 KF 表
        HashMap<Integer, Integer> keyToFreq;
        // freq 到 key 列表的映射，我们后文称为 FK 表
        HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
        // 记录最小的频次
        int minFreq;
        // 记录 LFU 缓存的最大容量
        int cap;

        public LFUCache(int capacity) {
            keyToVal = new HashMap<>();
            keyToFreq = new HashMap<>();
            freqToKeys = new HashMap<>();
            this.cap = capacity;
            this.minFreq = 0;
        }

        public int get(int key) {
            if (!keyToVal.containsKey(key)) {
                return -1;
            }
            // 增加 key 对应的 freq
            increaseFreq(key);
            return keyToVal.get(key);
        }

        public void put(int key, int value) {
            if (this.cap <= 0) return;

            /* 若 key 已存在，修改对应的 val 即可 */
            if (keyToVal.containsKey(key)) {
                keyToVal.put(key, value);
                // key 对应的 freq 加一
                increaseFreq(key);
                return;
            }

            /* key 不存在，需要插入 */
            /* 容量已满的话需要淘汰一个 freq 最小的 key */
            if (this.cap <= keyToVal.size()) {
                removeMinFreqKey();
            }

            /* 插入 key 和 val，对应的 freq 为 1 */
            // 插入 KV 表
            keyToVal.put(key, value);
            // 插入 KF 表
            keyToFreq.put(key, 1);
            // 插入 FK 表
            freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
            freqToKeys.get(1).add(key);
            // 插入新 key 后最小的 freq 肯定是 1
            this.minFreq = 1;
        }

        void increaseFreq(int key) {
            int freq = keyToFreq.get(key);
            /* 更新 KF 表 */
            keyToFreq.put(key, freq + 1);
            /* 更新 FK 表 */
            // 将 key 从 freq 对应的列表中删除
            freqToKeys.get(freq).remove(key);
            // 将 key 加入 freq + 1 对应的列表中
            freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
            freqToKeys.get(freq + 1).add(key);
            // 如果 freq 对应的列表空了，移除这个 freq
            if (freqToKeys.get(freq).isEmpty()) {
                freqToKeys.remove(freq);
                // 如果这个 freq 恰好是 minFreq，更新 minFreq
                if (freq == this.minFreq) {
                    this.minFreq++;
                }
            }
        }

        void removeMinFreqKey() {
            // freq 最小的 key 列表
            LinkedHashSet<Integer> keyList = freqToKeys.get(this.minFreq);
            // 其中最先被插入的那个 key 就是该被淘汰的 key
            int deletedKey = keyList.iterator().next();
            /* 更新 FK 表 */
            keyList.remove(deletedKey);
            if (keyList.isEmpty()) {
                freqToKeys.remove(this.minFreq);
                // 问：这里需要更新 minFreq 的值吗？
            }
            /* 更新 KV 表 */
            keyToVal.remove(deletedKey);
            /* 更新 KF 表 */
            keyToFreq.remove(deletedKey);

        }
    }
}
