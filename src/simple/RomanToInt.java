package simple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ywxie
 * @date 2020/7/17 21:46
 * @describe
 */
public class RomanToInt {

    //我的解法
    static int romanToInt(String s){
        Map<String, Integer> stringToNumberMap = new HashMap<>();
        //通常情况下，罗马数字中小的数字在大的数字的右边。
        // 但也存在特例，例如 4 不写做 IIII，而是 IV。
        // 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
        // 同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
        stringToNumberMap.put("I", 1);
        stringToNumberMap.put("V", 5);
        stringToNumberMap.put("X", 10);
        stringToNumberMap.put("L", 50);
        stringToNumberMap.put("C", 100);
        stringToNumberMap.put("D", 500);
        stringToNumberMap.put("M", 1000);
        char [] stringArr = s.toCharArray();
        int length = stringArr.length;
        int number = 0;
        int cha = 0;
        for (int i = 0; i < length; i++){
            String arr0 = String.valueOf(stringArr[i]);
            int number1 = stringToNumberMap.get(arr0);
            if (i+1 <= length-1){
                if (length == 2 && i == 1){
                    break;
                }
                String arr1 = String.valueOf(stringArr[i+1]);
                int number2 = stringToNumberMap.get(arr1);
                if (stringToNumberMap.get(arr0).equals(stringToNumberMap.get("I")) &&
                        (stringToNumberMap.get(arr1).equals(stringToNumberMap.get("V")) ||
                                stringToNumberMap.get(arr1).equals(stringToNumberMap.get("X"))      )){
                    cha = number2-number1;
                    number = cha + number;
                    i++;
                    continue;
                }else if (stringToNumberMap.get(arr0).equals(stringToNumberMap.get("X")) &&
                        (stringToNumberMap.get(arr1).equals(stringToNumberMap.get("L")) ||
                                stringToNumberMap.get(arr1).equals(stringToNumberMap.get("C"))      )) {
                    cha = number2-number1;
                    number = cha + number;
                    i++;
                    continue;
                }else if (stringToNumberMap.get(arr0).equals(stringToNumberMap.get("C")) &&
                        (stringToNumberMap.get(arr1).equals(stringToNumberMap.get("D")) ||
                                stringToNumberMap.get(arr1).equals(stringToNumberMap.get("M"))      )){
                    cha = number2-number1;
                    number = cha + number;
                    i++;
                    continue;
                }
            }
            number = number + number1;
        }
        return number;
    }

    //别人的解法
    static int romanToInt2(String s){
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for(int i = 1;i < s.length(); i ++) {
            int num = getValue(s.charAt(i));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private static int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        String s = "IV";
        System.out.println(romanToInt(s));
//        System.out.println(romanToInt2(s));
    }
}
