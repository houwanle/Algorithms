package Sort;
/* 冒泡排序
    思路：
       俩俩交换，大的放在后面，第一次排序后最大值已在数组末尾。
       因为俩俩交换，需要n-1趟排序，比如10个数，需要9趟排序
    代码实现要点：
       两个for循环，外层循环控制排序的趟数，内层循环控制比较的次数
       每趟过后，比较的次数都应该要减1
    优化：如果一趟排序后也没有交换位置，那么该数组已有序～

 */
public class BubbleSort {
    public static void main(String[] args){
        int[] arr = {25,14,2,6,4,78,415,456,741};
        bubbleSort(arr);
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

    private static void bubbleSort(int[] arrays){
        //外层循环是排序的趟数
        for (int i = 0; i < arrays.length-1; i++){
            //每比较一趟就重新初始化为0
            int isChange = 0;

            //内层循环是当前趟数需要比较的次数
            for (int j = 0; j < arrays.length-1-i; j++){
                //前一位与后一位比较，如果前一位比后一位要大，那么交换
                if (arrays[j] > arrays[j+1]){
                    int temp = arrays[j];
                    arrays[j] = arrays[j+1];
                    arrays[j+1] = temp;

                    //如果进到这里面了，说明发生置换了
                    isChange = 1;
                }
            }
            //如果比较完一趟没有发生置换，那么说明已经排好序了，不需要再执行下去了
            if (isChange == 0){
                break;
            }
        }
    }
}
