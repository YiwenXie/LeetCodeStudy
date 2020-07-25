package simple;

import java.util.*;

/**
 * @author ywxie
 * @date 2020/7/17 22:57
 * @describe
 */
public class ValidParentheses {
    //我的解法，想到用栈，但不知道怎么使用栈
    public boolean isValid(String s){
        //如果字符串为空，为true
        if (s.isEmpty()){
            return true;
        }
        //如果字符串长度为奇数，为false
        if (s.length() % 2 != 0 ){
            return false;
        }
        int length = s.length();
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < length; i++){
            if (!indexMap.isEmpty()){
                boolean isMatched = indexMap.containsKey(i);
                //已经进行匹配的字符串不必再进行匹配
                if (isMatched){
                    break;
                }
            }
            char a = s.charAt(i);
            for (int j = i+1; j < length; j++){
                char b = s.charAt(j);
                //判断是否为匹配字符
                boolean isEqual = getValue(a).equals(String.valueOf(b));
                if (isEqual){
                    //判断是否以正确的顺序闭合,未解决
//                    boolean isTrueClose = getValue(s.charAt(leftIndex)).equals(String.valueOf(b));
//                    boolean isTrueClose = (j == length-i-1);
//                    if (!isTrueClose){
//                        return false;
//                    }
                    //保存下标，被匹配到的下标为key，寻找匹配的下标为value
                    indexMap.put(j,i);
                    //获取到第一个匹配字符后，退出当前for循环
                    break;
                }
                //判断j是否为最后一个字符，如果是，则说明已遍历完所有字符但并没有找到匹配字符，返回false
                if (!getValue(a).equals(b) && j == length-1){
                    return false;
                }
            }
        }
        return true;
    }

    //别人的解法：栈的特性先进后出，遇到右括号就弹出栈，比较当前字符是否与弹出栈的字符一致，解决不正确顺序闭合问题
    public boolean isValid2(String s) {
        if(s.isEmpty()) {
            return true;
        }
        if (s.length() % 2 != 0 ){
            return false;
        }
        Stack<Character> stack= new Stack<>();
        for(char c:s.toCharArray()){
            if(c=='(') {
                stack.push(')');
            } else if(c=='{') {
                stack.push('}');
            } else if(c=='[') {
                stack.push(']');
            } else if(stack.empty()||c!=stack.pop()) {
                return false;
            }
        }
        if(stack.empty()) {
            return true;
        }
        return false;
    }

    private static String getValue(char ch) {
        switch(ch) {
            case '(': return ")";
            case '{': return "}";
            case '[': return "]";
            default: return "";
        }
    }

    public static void main(String[] args) {
        String s = "()[]{}";
        String s1 = "(]";
        String s2 = "([)]";
        String s3 = "{[]}";
        String s4 = "(([]){})";
        ValidParentheses vp = new ValidParentheses();
//        System.out.println(vp.isValid(s));
        System.out.println(vp.isValid2(s4));
    }
}
