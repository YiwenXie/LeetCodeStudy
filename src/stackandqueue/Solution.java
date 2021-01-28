package stackandqueue;

import java.util.*;

/**
 * @author ywxie
 * @date 2021/1/26 9:55
 * @describe 栈与队列
 */
public class Solution {

    /**
     * 232. 用栈实现队列
     */
    class MyQueue {

        Stack<Integer> inStack;
        Stack<Integer> outStack;

        /** Initialize your data structure here. */
        public MyQueue() {
            this.inStack = new Stack();
            this.outStack = new Stack();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            inStack.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (outStack.size() == 0){
                while (inStack.size() > 0){
                    outStack.push(inStack.pop());
                }
                return outStack.pop();
            }
            return outStack.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (outStack.size() == 0){
                while (inStack.size() > 0){
                    outStack.push(inStack.pop());
                }
                return outStack.peek();
            }
            return outStack.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            if (inStack.size() == 0 && outStack.size() == 0){
                return true;
            }
            return false;
        }
    }

    /**
     * 225. 用队列实现栈
     */
    class MyStack {

        Deque<Integer> deque;

        /** Initialize your data structure here. */
        public MyStack() {
            this.deque = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            deque.push(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return deque.pollFirst();
        }

        /** Get the top element. */
        public int top() {
            return deque.peekFirst();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return deque.isEmpty();
        }
    }

    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     */
    public boolean isValid(String s) {
        if(s.isEmpty()) {
            return true;
        }
        if (s.length() % 2 != 0 ){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char c: s.toCharArray()){
            if (c == '('){
                stack.push(')');
            }else if (c == '{'){
                stack.push('}');
            }else if (c == '['){
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 1047. 删除字符串中的所有相邻重复项
     */
    public String removeDuplicates(String S) {
        if (S.isEmpty()){
            return S;
        }
        Stack<Character> stack = new Stack<>();
        for (char c: S.toCharArray()){
            if (stack.isEmpty()){
                stack.push(c);
            }else {
                if (stack.peek() == c){
                    stack.pop();
                }else {
                    stack.push(c);
                }
            }
        }
        char[] chars = new char[stack.size()];
        int i = stack.size() - 1;
        while (!stack.isEmpty()){
            chars[i--] = stack.pop();
        }
        return String.valueOf(chars);
    }

    /**
     * 150. 逆波兰表达式求值
     * +, -, *, /
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s: tokens){
            if ("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s)){
                int b = stack.pop();
                int a = stack.pop();
                stack.push(doCalculate(a, b, s));
            }else {
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();
    }

    private int doCalculate(int a, int b, String s){
        if ("+".equals(s)){
            return a + b;
        }else if ("-".equals(s)){
            return a - b;
        }else if ("*".equals(s)){
            return a * b;
        }
        return a / b;
    }

    /**
     * 单调队列
     */
    private class MonotonicQueue{

        Deque<Integer> deque;

        public MonotonicQueue() {
            this.deque = new LinkedList<>();
        }

        // 每次弹出的时候，比较当前要弹出的数值是否等于队列出口元素的数值，如果相等则弹出。
        // 同时pop之前判断队列当前是否为空。
        void pop(int value){
            if (!deque.isEmpty() && value == deque.peekFirst()){
                deque.pollFirst();
            }
        }

        // 如果push的数值大于入口元素的数值，那么就将队列后端的数值弹出，直到push的数值小于等于队列入口元素的数值为止。
        // 这样就保持了队列里的数值是单调从大到小的了。
        void push(int value){
            while (!deque.isEmpty() && value > deque.peekLast()){
                deque.pollLast();
            }
            deque.addLast(value);
        }

        // 查询当前队列里的最大值 直接返回队列前端也就是front就可以了。
        int front(){
            return deque.getFirst();
        }
    }

    /**
     * 239. 滑动窗口最大值
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null||nums.length < 2) {
            return nums;
        }
        MonotonicQueue queue = new MonotonicQueue();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) { // 先将前k的元素放进队列
            queue.push(nums[i]);
        }
        int j = 0;
        result[j++] = queue.front(); // result 记录前k的元素的最大值
        for (int i = k; i < nums.length; i++) {
            queue.pop(nums[i - k]); // 滑动窗口移除最前面元素
            queue.push(nums[i]); // 滑动窗口前加入最后面的元素
            result[j++] = queue.front(); // 记录对应的最大值
        }
        return result;
    }

    /**
     * 347. 前 K 个高频元素
     * Lamda + Stream
     */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            frequencyMap.put(nums[i], frequencyMap.getOrDefault(nums[i], 0) + 1);
        }
        return frequencyMap.entrySet().stream().sorted((m1, m2) -> m2.getValue() - m1.getValue()).limit(k).mapToInt(Map.Entry::getKey).toArray();
    }

    /**
     * 347. 前 K 个高频元素
     * 优先级队列
     */
    public int[] topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        int[] result = new int[k];
        for (int i = 0; i < nums.length; i++){
            frequencyMap.put(nums[i], frequencyMap.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(frequencyMap::get).reversed());
        priorityQueue.addAll(frequencyMap.keySet());
        int index = 0;
        while (index < k){
            result[index++] = priorityQueue.poll();
        }
        return result;
    }

}
