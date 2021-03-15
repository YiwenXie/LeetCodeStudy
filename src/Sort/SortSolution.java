package Sort;

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

}
