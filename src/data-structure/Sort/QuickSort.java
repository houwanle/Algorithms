/* 快速排序
  思路：
      在数组中找一个元素(节点)，比它小的放在节点的左边，比它大的放在节点右边。
      一趟下来，比节点小的在左边，比节点大的在右边。不断执行这个操作....
  代码实现：
      快速排序用递归比较好写。支点取中间，使用L和R表示数组的最小和最大位置
        不断进行比较，直到找到比支点小(大)的数，随后交换，不断减小范围～
      递归L到支点前一个元素(j)(执行相同的操作,同上)
      递归支点后一个元素(i)到R元素(执行相同的操作,同上)
 */
public class QuickSort {
    public static void main(String[] args){
        int[] arr = {1,2,35,4,8,91,23};
        quickSort(arr,0,6);
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

    public static void quickSort(int[] arrays, int L, int R){
        int i = L;//指向数组第一个元素
        int j = R;//指向数组最后一个元素
        int p = arrays[(L+R)/2];//支点
        //左右两端进行扫描，只要两端还没有交替，就一直扫描
        while(i <= j){
             //寻找直到比支点大的数
             while(p > arrays[i]){
                 i++;
             }
             //寻找直到比支点小的数
             while(p < arrays[j]){
                 j--;
             }

             //此时已经分别找到了比支点小的数(右边）、比支点大的数(左边)，它们进行交换
             if(i <= j){
                 int temp = arrays[i];
                 arrays[i] = arrays[j];
                 arrays[j] = temp;
                 i++;
                 j--;
             }
         }
         //“左边"再做排序，直到左边剩下一个数(递归出口）
         if(L < j){
             quickSort(arrays,L,j);
         }
         //"右边“再做排序，直到右边剩下一个数(递归出口）
         if(i < R){
             quickSort(arrays,i,R);
         }
    }
}
