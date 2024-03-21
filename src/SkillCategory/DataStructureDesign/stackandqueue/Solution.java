package SkillCategory.DataStructureDesign.stackandqueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author ywxie
 * @date 2022/7/28 18:00
 * @describe 栈和队列的相互实现
 */
public class Solution {
    /**
     * 225. 用队列实现栈
     */
    class MyStack {
        Deque<Integer> queue;

        public MyStack() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            queue.addLast(x);
        }

        public int pop() {
            return queue.removeLast();
        }

        public int top() {
            return queue.getLast();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

    /**
     * 232. 用栈实现队列
     */
    class MyQueue {

        Stack<Integer> stack1;
        Stack<Integer> stack2;

        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void push(int x) {
            stack1.push(x);
        }

        public int pop() {
            peek();
            return stack2.pop();
        }

        public int peek() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.peek();
        }

        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }
}
