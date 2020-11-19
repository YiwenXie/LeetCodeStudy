package simple;

/**
 * @author ywxie
 * @date 2020/11/19 15:05
 * @describe 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
 * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 */
public class searchInsert {

    //二分法
    public static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int l=0,r=n-1;
        while(l<=r){
            int mid=l+(r-l)/2;
            if(nums[mid]<target){
                l=mid+1;
            }else {
                r=mid-1;
            }
        }
        return l;
    }
}
