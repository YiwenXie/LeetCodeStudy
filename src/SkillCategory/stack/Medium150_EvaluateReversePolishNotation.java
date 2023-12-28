package SkillCategory.stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Medium150_EvaluateReversePolishNotation {
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "*";
    private static final String DIVIDE = "/";

    private static final Set<String> set = new HashSet<String>(3){{
        add(PLUS);
        add(MINUS);
        add(MULTIPLY);
        add(DIVIDE);
    }};

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            if (set.contains(s)) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(getValue(a, b, s));
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.peek();
    }

    private int getValue(int a, int b, String s) {
        switch (s) {
            case PLUS:
                return a + b;
            case MINUS:
                return a - b;
            case MULTIPLY:
                return a * b;
            case DIVIDE:
                return a / b;
            default:
                return 0;
        }
    }
}
