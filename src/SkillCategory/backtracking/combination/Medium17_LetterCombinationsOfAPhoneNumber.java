package SkillCategory.backtracking.combination;

import java.util.ArrayList;
import java.util.List;

public class Medium17_LetterCombinationsOfAPhoneNumber {
    private static final String[] s2 = new String[]{"a", "b", "c"};
    private static final String[] s3 = new String[]{"d", "e", "f"};
    private static final String[] s4 = new String[]{"g", "h", "i"};
    private static final String[] s5 = new String[]{"j", "k", "l"};
    private static final String[] s6 = new String[]{"m", "n", "o"};
    private static final String[] s7 = new String[]{"p", "q", "r", "s"};
    private static final String[] s8 = new String[]{"t", "u", "v"};
    private static final String[] s9 = new String[]{"w", "x", "y", "z"};

    static char[][] letters = new char[10][];

    static {
        letters[2] = new char[]{'a', 'b', 'c'};
        letters[3] = new char[]{'d', 'e', 'f'};
        letters[4] = new char[]{'g', 'h', 'i'};
        letters[5] = new char[]{'j', 'k', 'l'};
        letters[6] = new char[]{'m', 'n', 'o'};
        letters[7] = new char[]{'p', 'q', 'r', 's'};
        letters[8] = new char[]{'t', 'u', 'v'};
        letters[9] = new char[]{'w', 'x', 'y', 'z'};
    }

    /**
     * Time Complexity: O(3^m * 4^n)
     * Space Complexity: O(3^m * 4^n)
     * 其中 m 是对应四个字母的数字个数，n 是对应三个字母的数字个数
     */
    List<String> result;

    public List<String> letterCombinations(String digits) {
        result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return result;
        }
        sb = new StringBuilder();
        // my
//        recursion(0, digits.length() - 1, digits.split(""), new StringBuilder());
        // optimal
        recursion2(0, digits.split(""));
        return result;
    }

//    private void recursion(int lo, int hi, String[] digits, StringBuilder sb) {
//        if (sb.length() == digits.length) {
//            result.add(sb.toString());
//            return;
//        }
//        for (int i = lo; i <= hi; i++) {
//            String[] strings = getStringArrayByDigit(digits[lo]);
//            for (String string : strings) {
//                sb.append(string);
//                recursion(i + 1, hi, digits, new StringBuilder(sb));
//                sb.deleteCharAt(sb.length() - 1);
//            }
//        }
//    }

    StringBuilder sb;

    private void recursion2(int index, String[] digits) {
        if (index == digits.length) {
            result.add(sb.toString());
            return;
        }
        char[] chars = letters[Integer.parseInt(digits[index])];
        for (char c : chars) {
            sb.append(c);
            recursion2(index + 1, digits);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private String[] getStringArrayByDigit(String digit) {
        switch (digit) {
            case "2":
                return s2;
            case "3":
                return s3;
            case "4":
                return s4;
            case "5":
                return s5;
            case "6":
                return s6;
            case "7":
                return s7;
            case "8":
                return s8;
            case "9":
                return s9;
            default:
                return null;
        }
    }
}
