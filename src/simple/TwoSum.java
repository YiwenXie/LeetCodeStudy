package simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ywxie
 * @date 2020/7/17 20:40
 * @describe
 */
class TwoSum {

    //我的解法
    static int[] getSum(int[] nums, int target){
        int length = nums.length;
        int index[] = new int[2];
        for (int i = 0;i < length;i++){
            for (int j = i + 1; j < length; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    index[0] = i;
                    index[1] = j;
                    break;
                }
            }
        }
        return index;
    }

    // 别人的解法 复杂度1
    static int[] getSum2(int[] nums, int target){
        int length = nums.length;
        int index[] = new int[2];
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i = 0; i < length; i++){
            if (map1.containsKey(target - nums[i]) && map1.get(target - nums[i]) != i){
                index[0] = i;
                index[1] = map1.get(target - nums[i]);
                break;
            }
            map1.put(nums[i], i);
        }
        return index;
    }

    public static void main(String[] args){
        int[] nums = {3,2,3};
        int target = 6;
        int[] index = getSum(nums, target);
//        int[] index = getSum2(nums, target);
        System.out.println("i = " + index[0] + ", j = " + index[1]);
    }
}
