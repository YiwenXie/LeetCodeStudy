package SkillCategory.Sort;

/**
 * @author ywxie
 * @date 2021/3/15 10:43
 * @describe 排序算法
 */
public class SortSolution {

    //插入排序
    /**
     * 直接插入排序
     * 基本思想：
     *  将数组中的所有元素依次跟前面已经排好的元素相比较，如果选择的元素比已排序的元素小，则交换，直到全部元素都比较过为止。
     *  1. 从第一个元素开始，该元素可以认为已经被排序
     *  * 2. 取出下一个元素，在已经排序的元素序列中从后向前扫描
     *  * 3. 如果该元素（已排序）大于新元素，将该元素移到下一位置
     *  * 4. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     *  * 5. 将新元素插入到该位置后
     *  * 6. 重复步骤2~5
     */
    public void insertionSort(int[] arr){
//        for (int i = 1; i < arr.length; i++){
//            int temp = arr[i];
//            for (int j = i; j >= 0; j--){
//                if (j > 0 && arr[j - 1] > temp){
//                    arr[j] = arr[j - 1];
//                }else {
//                    arr[j] = temp;
//                    break;
//                }
//            }
//        }
        // 交换次数较多的实现
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = i + 1 ; j > 0; j-- ) {
                if(arr[j - 1] <= arr[j]){
                    break;
                }
                int temp = arr[j];      //交换操作
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }
        }
    }

    //插入排序
    /**
     * 希尔排序
     * 希尔排序，也称递减增量排序算法，1959年Shell发明。是插入排序的一种高速而稳定的改进版本。
     * 希尔排序是先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，
     * 待整个序列中的记录“基本有序”时，再对全体记录进行依次直接插入排序。
     *
     * 将待排序数组按照步长gap进行分组，然后将每组的元素利用直接插入排序的方法进行排序；
     * 每次再将gap折半减小，循环上述操作；当gap=1时，利用直接插入，完成排序。
     *
     * 1. 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；（一般初次取数组半长，之后每次再减半，直到增量为1）
     *  * 2. 按增量序列个数k，对序列进行k 趟排序；
     *  * 3. 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
     *  *    仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     */
    public void shellSort(int[] arr){
        int gap = 1, i, j, len = arr.length;
        int temp;
        while (gap < len / 3) {
            gap = gap * 3 + 1;      // <O(n^(3/2)) by Knuth,1973>: 1, 4, 13, 40, 121, ...
        }
        for (; gap > 0; gap /= 3) {
            for (i = gap; i < len; i++) {
                temp = arr[i];
                for (j = i - gap; j >= 0 && arr[j] > temp; j -= gap) {
                    arr[j + gap] = arr[j];
                }
                arr[j + gap] = temp;
            }
        }
    }

    /**
     * 快速排序
     * 若要对 nums[lo..hi] 进行排序，我们先找一个分界点 p，
     * 通过交换元素使得 nums[lo..p-1] 都小于等于 nums[p]，且 nums[p+1..hi] 都大于 nums[p]，
     * 然后递归地去 nums[lo..p-1] 和 nums[p+1..hi] 中寻找新的分界点，最后整个数组就被排序了。
     * 先构造分界点，然后去左右子数组构造分界点，你看这不就是一个二叉树的前序遍历吗？
     */
    public void fastSort(int[] nums, int lo, int hi){
        /****** 前序遍历位置 ******/
        // 通过交换元素构建分界点 p
        int p = partition(nums, lo, hi);
        /************************/

        fastSort(nums, lo, p - 1);
        fastSort(nums, p + 1, hi);
    }

    /**
     * 归并排序
     * 若要对 nums[lo..hi] 进行排序，我们先对 nums[lo..mid] 排序，再对 nums[mid+1..hi] 排序，
     * 最后把这两个有序的子数组合并，整个数组就排好序了。
     * 先对左右子数组排序，然后合并（类似合并有序链表的逻辑），你看这是不是二叉树的后序遍历框架？
     */
    // 定义：排序 nums[lo..hi]
    void generalSort(int[] nums, int lo, int hi) {
        int mid = (lo + hi) / 2;
        // 排序 nums[lo..mid]
        generalSort(nums, lo, mid);
        // 排序 nums[mid+1..hi]
        generalSort(nums, mid + 1, hi);

        /****** 后序位置 ******/
        // 合并 nums[lo..mid] 和 nums[mid+1..hi]
        merge(nums, lo, mid, hi);
        /*********************/
    }


}
