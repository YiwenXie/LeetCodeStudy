package SkillCategory.hashtable;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/19 20:19
 */
public class Easy202HappyNumber {
    public boolean isHappy(int n) {
        String[] nums = String.valueOf(n).split("");
        int sum = 0;
        Set<Integer> set = new HashSet<>();
        while (sum != 1) {
            sum = 0;
            for (String num : nums
            ) {
                sum += Integer.parseInt(num) * Integer.parseInt(num);
            }
            if (set.contains(sum)) {
                return false;
            }
            set.add(sum);
            nums = String.valueOf(sum).split("");
        }
        return true;
    }

    /**
     * Solution: HashTable
     * Time complexity: O(logn)
     * Space complexity: O(logn)
     *
     * @param n
     * @return
     */
    public boolean isHappy2(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            n = getNext(n);
            if (set.contains(n)) {
                return false;
            }
            set.add(n);
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

    /**
     * Solution: Dual Pointer slow and fast
     * 通过反复调用 getNext(n) 得到的链是一个隐式的链表。隐式意味着我们没有实际的链表节点和指针，但数据仍然形成链表结构。
     * 起始数字是链表的头 “节点”，链中的所有其他数字都是节点。next 指针是通过调用 getNext(n) 函数获得。
     * 意识到我们实际有个链表，那么这个问题就可以转换为检测一个链表是否有环。
     * Time complexity: O(logn)
     * Space complexity: O(1) 对于这种方法，我们不需要哈希集来检测循环。指针需要常数的额外空间。
     *
     * @param n
     * @return
     */
    public boolean isHappy3(int n) {
        int slow = n;
        int fast = getNext(n);
        while (fast != 1 && slow != fast) {
            slow = getNext(n);
            fast = getNext(getNext(n));
        }
        return fast == 1;
    }
}
