package SkillCategory.Greedy;


public class Easy860_LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        int ten = 0;
        int five = 0;
        for (int bill : bills) {
            if (bill == 10) {
                ten++;
                if (five >= 1) {
                    five--;
                } else {
                    return false;
                }
            } else if (bill == 20) {
                if (ten >= 1 && five >= 1) {
                    ten--;
                    five--;
                } else if (five >= 3) {
                    five = five - 3;
                } else {
                    return false;
                }
            } else {
                five++;
            }
        }
        return true;
    }
}
