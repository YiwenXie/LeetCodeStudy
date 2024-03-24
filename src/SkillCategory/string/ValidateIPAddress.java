package SkillCategory.string;

public class ValidateIPAddress {
    static boolean validateIP(String ip) {
        // your code goes here
        if (ip.length() < 4 || ip.length() > 12) {
            return false;
        }
        int right = 0;
        int left = 0;
        int count = 0;
        while (right < ip.length()) {
            while (right < ip.length() && ip.charAt(right) != '.') {
                right++;
            }
            String str = ip.substring(left, right);
            if (str.isEmpty() || str.startsWith(".") || str.length() > 3) {
                return false;
            }
            if (str.length() > 1 && str.startsWith("0")) {
                return false;
            }
            if (Integer.parseInt(str) < 0 || Integer.parseInt(str) > 255) {
                return false;
            }
            count++;
            right++;
            left = right;
        }
        return count == 4;
    }

    static boolean validateIP2(String ip) {
        String[] strs = ip.split("\\.");
        if (strs.length != 4) {
            return false;
        }
        for (String str : strs) {
            if (str.isEmpty() || str.length() > 3) {
                return false;
            }
            if (str.length() > 1 && str.startsWith("0")) {
                return false;
            }
            if (Integer.parseInt(str) < 0 || Integer.parseInt(str) > 255) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        String ip = "0.0.0.0";
        String ip = "192.168.0.1";
        System.out.println(validateIP(ip));
    }
}
