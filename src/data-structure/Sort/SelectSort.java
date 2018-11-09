/* 选择排序
思路：
    找到数组中最大的元素，与数组最后一位元素交换
    当只有一个数时，则不需要选择了，因此需要n-1趟排序，比如10个数，需要9趟排序

代码实现要点：
    两个for循环，外层循环控制排序的趟数，内层循环找到当前趟数的最大值，
    随后与当前趟数组最后的一位元素交换

 */
public class SelectSort {
    public static void main(String[] args){
        int[] arr = {12,23,4,5,68,45,98,1};
        selectSort(arr);
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

    public static void selectSort(int[] arrays){
        //外层循环控制需要排序的趟数
        for (int i = 0; i < arrays.length-1;i++){
            //新的趟数、将角标重新赋值为0
            int pos = 0;
            //内存循环控制遍历数组的个数并得到最大数的角标
            for (int j = 0; j < arrays.length-i;j++){
                if (arrays[j] > arrays[pos]){
                    pos = j;
                }
            }
            //交换
            int temp = arrays[pos];
            arrays[pos] = arrays[arrays.length-1-i];
            arrays[arrays.length-1-i] = temp;
        }
    }
}
