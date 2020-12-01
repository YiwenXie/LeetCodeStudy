package simple;

/**
 * @author ywxie
 * @date 2020/11/20 14:18
 * @describe 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 */
public class merge {

    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int p = 0;
        int length = m + n;
        int[] nums3 = new int[length];
        while (i < m && j < n){
            if (nums1[i] < nums2[j]){
//                nums3[p] = nums1[i];
                i++;
            }else {
                nums1[p] = nums2[j];
//                nums3[p] = nums2[j];
                j++;
            }
            p++;
        }
        while (j < n){
            nums3[p] = nums2[j];
            j++;
            p++;
        }
        while (i < m){
            nums3[p] = nums1[i];
            i++;
            p++;
        }
        nums1 = nums3.clone();
        return nums1;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;
        int[] num3 = merge(nums1, m, nums2, n);
        for (int i: num3){
            System.out.println(i);
        }
    }
}
