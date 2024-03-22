package SkillCategory.DataStructureDesign;

import java.util.Stack;

public class Easy232_ImplementQueueUsingStacks {
    class MyQueue {
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;

        public MyQueue() {
            // save the order from old to latest
            stack1 = new Stack<>();
            // save the order from latest to old
            stack2 = new Stack<>();
        }

        public void push(int x) {
            // if stack2 is not empty
            while (!stack2.isEmpty()) {
                // make elements in stack1 ordered by old to latest
                stack1.push(stack2.pop());
            }
            // add latest element
            stack1.push(x);
            // add element to the stack2 in the order from latest to old
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        public int pop() {
            return stack2.pop();
        }

        public int peek() {
            return stack2.peek();
        }

        public boolean empty() {
            return stack2.isEmpty();
        }
    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
}
