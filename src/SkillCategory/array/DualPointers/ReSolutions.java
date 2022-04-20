package SkillCategory.array.DualPointers;

/**
 * @author ywxie
 * @date 2022/4/20 14:35
 * @describe
 */
public class ReSolutions {

    /**
     * 26. 删除有序数组中的重复项
     * 4.21需要再练习
     */
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]){
                nums[++slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    /**
     * 27.移除元素
     */
    public static int removeElement(int[] nums, int val){
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val){
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }

    /**
     *2 83. 移动零
     */
    public void moveZeroes(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0){
                nums[slow++] = nums[fast];
            }
        }
        for (; slow < nums.length; slow++) {
            nums[slow] = 0;
        }
    }
}
