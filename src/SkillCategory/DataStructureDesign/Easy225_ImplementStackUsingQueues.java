package SkillCategory.DataStructureDesign;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
public class Easy225_ImplementStackUsingQueues {

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top());
        System.out.println(myStack.pop());
        System.out.println(myStack.empty());
    }

    static class MyStack {

        private Queue<Integer> queue1;
        private Queue<Integer> queue2;

        public MyStack() {
            this.queue1 = new LinkedList<>();
            this.queue2 = new LinkedList<>();
        }

        /**
         * 在加入元素时先将q1中的元素依次出栈压入q2，
         * 然后将新加入的元素压入q1，再将q2中的元素依次出栈压入q1
         *
         * @param x
         */
        public void push(int x) {
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
            // make q1 's head is last node
            queue1.offer(x);
            while (!queue2.isEmpty()) {
                queue1.add(queue2.poll());
            }
        }

        public int pop() {
            return queue1.poll();
        }

        public int top() {
            return queue1.peek();
        }

        public boolean empty() {
            return queue1.isEmpty();
        }
    }

    static class MyStack2 {

        private Queue<Integer> queue;

        public MyStack2() {
            this.queue = new LinkedList<>();
        }

        /**
         * 每 offer 一个数（x）进来，都重新排列，把这个数（x）放到队列的队首
         *
         * @param x
         */
        public void push(int x) {
            queue.offer(x);
            int size = queue.size();
            while (size > 1) {
                queue.offer(queue.poll());
                size--;
            }
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

    class MyStack3 {

        private Deque<Integer> deque;

        public MyStack3() {
            this.deque = new LinkedList<>();
        }

        public void push(int x) {
            deque.offerLast(x);
        }

        public int pop() {
            if (deque.isEmpty()) {
                return -1;
            }
            return deque.pollLast();
        }

        public int top() {
            if (deque.isEmpty()) {
                return -1;
            }
            return deque.peekLast();
        }

        public boolean empty() {
            return deque.isEmpty();
        }
    }


}
