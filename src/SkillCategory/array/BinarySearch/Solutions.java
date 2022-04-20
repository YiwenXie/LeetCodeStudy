package SkillCategory.array.BinarySearch;

/**
 * @author ywxie
 * @date 2022/4/19 14:49
 * @describe
 */
public class Solutions {

    /**
     * 学了704之后的练习 ok-100过
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * 思路：寻找左边界与右边界
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = leftBoundOfSearchRange(nums, target);
        result[1] = rightBoundOfSearchRange(nums, target);
        return result;
    }

    public int leftBoundOfSearchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < target){
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else {
                // 收缩右边界
                right = mid - 1;
            }
        }
        // 检查left越界
        if (left >= nums.length || nums[left] != target){
            return -1;
        }
        return left;
    }

    public int rightBoundOfSearchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < target){
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else {
                // 收缩左边界
                left = mid + 1;
            }
        }
        // 检查right越界
        if (right < 0 || nums[right] != target){
            return -1;
        }
        return right;
    }

    /**
     * 392. 判断子序列 OK-100分，提交了三次，两次错的
     * 思路：遍历target数组，每个字母都左边界，记住每一个字母的左边界，和每次遍历的起始点和S终止的位置
     0 <= s.length <= 100
     0 <= t.length <= 10^4
     注意：s和t可能为空字符串，s需要匹配的长度比t长
     两个字符串都只由小写字符组成。
     */
    public boolean isSubsequence(String s, String t) {
        //s和t可能为空字符串
        if (s.isEmpty()){
            return true;
        }
        char[] targetChars = t.toCharArray();
        char[] aimedChars = s.toCharArray();
        // 记住每一个需要被匹配字母的左边界
        int leftBound = -1;
        // 有一种情况：s需要匹配的长度比t长，需要记住S终止的位置
        int end = 0;
        for (int i = 0; i < s.length(); i++){
            // 记住每次遍历的起始点，开始遍历需+1
            leftBound = searchLeftBoundOfString(targetChars, aimedChars[i], leftBound + 1);
            if (leftBound == -1){
                return false;
            }
            end = i;
        }
        // 判断结束S遍历的终止的位置
        // 如果小于S的长度，则说明没有匹配完就结束了，说明子序列需要匹配的长度大于所给的字符串
        // 如果等于S的长度，则说明都匹配完了
        return end == s.length() - 1;
    }

    private int searchLeftBoundOfString(char[] chars, char target, int start){
        int left = start;
        int right = chars.length;
        while (left < right){
            if (chars[left] == target) {
                return left;
            }
            left++;
        }
        return -1;
    }

    /**
     * 392 如果用双指针法呢?
     */
//    public boolean isSubsequence2(String s, String t) {
//
//    }

    /**
     * 374. 猜数字大小
     */
    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (guess(mid) < 0){
                right = mid - 1;
            }else if (guess(mid) > 0){
                left = mid + 1;
            }else {
                return mid;
            }
        }
        return right + 1;
    }

    /**
     * Forward declaration of guess API.
     * @param  num   your guess
     * @return 	     -1 if num is lower than the guess number
     *			      1 if num is higher than the guess number
     *               otherwise return 0
     * int guess(int num);
     */
    private int guess(int num){
        return 0;
    }

    /**
     * 852. 山脉数组的峰顶索引
     * 思路：左单调增右单调减的索引位置，即左右两边都比自己小的位置，即数组最大值
     */
    public int peakIndexInMountainArray(int[] arr) {
        // 暴力解法 O（N）
//        return violencePeakIndexInMountainArray(arr);
        // 优化解法 O(log n)
        return optimizePeakIndexInMountainArray(arr);
    }

    private int violencePeakIndexInMountainArray(int[] arr){
        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max){
                index = i;
                max = arr[i];
            }
        }
        return index;
    }

    private int optimizePeakIndexInMountainArray(int[] arr){
        int left = 0;
        int right = arr.length;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]){
                left = mid + 1;
            }else if (arr[mid] < arr[mid - 1]){
                right = mid;
            }else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 367. 有效的完全平方数
     * 构造半分数组
     */
    public static boolean isPerfectSquare(int num) {
        if (num == 1){
            return true;
        }
        int left = 1;
        int right = num / 2;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (mid * mid > num){
                right = mid - 1;
            }else if (mid * mid < num){
                left = mid + 1;
            }else {
                return true;
            }
        }
        return false;
    }

    /**
     * 1385. 两个数组间的距离值
     */
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        return 0;
    }

    /**
     * 744. 寻找比目标字母大的最小字母
     * OK -100
     */
    public static char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (letters[mid] > target){
                if (target == 'z'){
                    left = 0;
                }
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return right >= letters.length ? letters[0]: letters[right];
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 4.23 复习
     */
    public int[] searchRange2(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        int left = searchLeft(nums, target);
        if (left == -1){
            return result;
        }
        int right = searchRight(nums, target);
        result[0] = left;
        result[1] = right;
        return result;
    }

    private int searchLeft(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] > target){
                right = mid - 1;
            }else if (nums[mid] < target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        if (left == nums.length || nums[left] != target){
            return -1;
        }
        return left;
    }

    private int searchRight(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] > target){
                right = mid - 1;
            }else if (nums[mid] < target){
                left = mid + 1;
            }else {
                left = mid + 1;
            }
        }
        if (right == 0 || nums[right] != target){
            return -1;
        }
        return right;
    }
}
