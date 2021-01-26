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

}
