package stackandqueue;

import java.util.Stack;

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

}
