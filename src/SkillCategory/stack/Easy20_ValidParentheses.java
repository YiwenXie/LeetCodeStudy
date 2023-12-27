package SkillCategory.stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Easy20_ValidParentheses {
    Set<Character> set = new HashSet<Character>(){{
       add('[');
       add('{');
       add('(');
    }};

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char temp = stack.peek();
                if ((c == ']' && temp != '[') || (c == ')' && temp != '(') || (c == '}' && temp != '{')) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
