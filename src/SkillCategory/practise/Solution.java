package SkillCategory.practise;

import java.util.*;

/**
 * @author ywxie
 * @date 2022/5/5 13:02
 * @describe
 */
public class Solution {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] greater = nextGreaterElement(nums2);
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            hashMap.put(nums2[i], greater[i]);
        }
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = hashMap.get(nums1[i]);
        }
        return result;
    }

    public int[] nextGreaterElement(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }

            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }

        return result;
    }

    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }

            result[i] = stack.isEmpty() ? -1 : stack.peek() - i;
            stack.push(i);
        }

        return result;
    }

    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int[] result = new int[n];

        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }

            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % n]);
        }

        return result;
    }

    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int[] greater = nextGreaterElementHelper(nums2);
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            hashMap.put(nums2[i], greater[i]);
        }
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = hashMap.get(nums1[i]);
        }
        return result;
    }

    public int[] nextGreaterElementHelper(int[] nums) {
        int[] result = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : nums[i];
            stack.push(nums[i]);
        }
        return result;
    }

    class LRUCache {
        private HashMap<Integer, Node> map;
        private NodeList cache;
        private int size;

        public LRUCache(int capacity) {
            this.cache = new NodeList();
            map = new HashMap<>();
            size = capacity;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            makeRecently(key);
            return map.get(key).val;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                deletedKey(key);
                addRecently(key, value);
                return;
            }
            if (cache.size == size) {
                removeLeastRecently();
            }
            addRecently(key, value);
        }

        private void makeRecently(int key) {
            Node node = map.get(key);
            cache.remove(node);
            cache.addLast(node);
        }

        private void addRecently(int key, int val) {
            Node node = new Node(key, val);
            map.put(key, node);
            cache.addLast(node);
        }

        private void deletedKey(int key) {
            Node node = map.get(key);
            cache.remove(node);
            map.remove(key);
        }

        private void removeLeastRecently() {
            Node node = cache.removeFirst();
            map.remove(node.key);
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


    class LFUCache {
        HashMap<Integer, Integer> keyToVal;
        HashMap<Integer, Integer> keyToFre;
        HashMap<Integer, LinkedHashSet<Integer>> freToKey;
        int size;
        int minFre;

        public LFUCache(int capacity) {
            size = capacity;
            keyToVal = new HashMap<>();
            keyToFre = new HashMap<>();
            freToKey = new HashMap<>();
        }

        public int get(int key) {
            if (!keyToVal.containsKey(key)) {
                return -1;
            }
            increaseFre(key);
            return keyToVal.get(key);
        }

        public void put(int key, int value) {
            if (keyToVal.containsKey(key)) {
                keyToVal.put(key, value);
                increaseFre(key);
                return;
            }
            if (keyToVal.size() >= size) {
                removeMinFreKey();
            }
            keyToVal.put(key, value);
            keyToFre.put(key, 1);
            freToKey.putIfAbsent(1, new LinkedHashSet<>());
            freToKey.get(1).add(key);
            minFre = 1;
        }

        private void increaseFre(int key) {
            int fre = keyToFre.get(key);
            keyToFre.put(key, fre + 1);
            freToKey.get(fre).remove(key);
            freToKey.putIfAbsent(fre + 1, new LinkedHashSet<Integer>());
            freToKey.get(fre + 1).add(key);
            if (freToKey.get(fre).isEmpty()) {
                freToKey.remove(fre);
                if (fre == minFre) {
                    minFre++;
                }
            }
        }

        private void removeMinFreKey() {
            LinkedHashSet<Integer> setList = freToKey.get(minFre);
            int deletedKey = setList.iterator().next();
            setList.remove(deletedKey);
            if (setList.isEmpty()) {
                freToKey.remove(minFre);
            }
            keyToVal.remove(deletedKey);
            keyToFre.remove(deletedKey);
        }
    }

    /**
     * 875
     *
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int max = 0;
        for (int pile : piles) {
            max = Math.max(pile, max);
        }
        int right = max + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (f875(piles, mid) <= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int f875(int[] piles, int k) {
        int h = 0;
        for (int pile : piles) {
            h += pile / k;
            if (pile % k != 0) {
                h++;
            }
        }
        return h;
    }

    /**
     * 202
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            if (set.contains(n)) {
                return false;
            }
            set.add(n);
            n = getNext(n);
        }
        return true;
    }

    private int getNext(int n) {
        int sum = 0;
        while (n != 0) {
            int num = n % 10;
            sum += num * num;
            n = n / 10;
        }
        return sum;
    }

    public String reverseWords(String s) {
        s = s.trim();
        char[] chars = s.toCharArray();
        int left = s.length() - 1;
        int right = s.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (left >= 0) {
            while (left >= 0 && chars[left] != ' ') {
                left--;
            }
            sb.append(s.substring(left + 1, right + 1)).append(" ");
            while (left >= 0 && chars[left] == ' ') {
                left--;
            }
            right = left;
        }
        return sb.toString().trim();
    }

    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }

    int[] temp;

    public int[] sortArray(int[] nums) {
        temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        mergeSort(nums, lo, mid);
        mergeSort(nums, mid + 1, hi);
        toMerge(nums, lo, mid, hi);
    }

    private void toMerge(int[] nums, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j++];
            } else if (j == hi + 1) {
                nums[k] = temp[i++];
            } else if (temp[i] > temp[j]) {
                nums[k] = temp[j++];
            } else {
                nums[k] = temp[i++];
            }
        }
    }


}
