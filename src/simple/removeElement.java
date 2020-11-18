package simple;

/**
 * @author ywxie
 * @date 2020/11/18 15:08
 * @describe 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 */
public class removeElement {

    public static int removeElement(int[] nums, int val) {
        int length = nums.length;
        int i = 0;
        while (i < length){
            if (nums[i] == val){
                nums[i] = nums[length - 1];
                length--;
            }else {
                i++;
            }
        }
        for (int k = 0; k < nums.length; k++){
            System.out.println("index:" + k + "\nnum:" + nums[k]);
        }
        return length;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        int len = removeElement(nums, val);
        System.out.println(len);
    }
}
