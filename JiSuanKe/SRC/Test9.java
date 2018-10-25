package test;
/*
给定一个数组和一个数（该数不一定在数组中），从数组里删掉这个数字，返回剩下的数组长度。
如：A[] = {1, 2, 3, 4, 5}，要删除数字 3，那么返回数组长度为 4。
亲爱的小伙伴们，题目是不是很简单呢？
提示：int removeElement(int A[], int n, int elem)
其中，n代表数组长度，elem代表要删掉的元素。

输入格式
第一行输入一个数 n(1≤n≤100)，接下来一行 n个整数，表示数组 A的所有元素 Ai(0≤Ai≤100)，
接着输入要删除的元素elem(0≤elem≤100)。
输出格式
输出一个整数，表示剩余数组长度。

样例输入
2
3 3
3
样例输出
0
 */

import java.util.Scanner;

public class Test9 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for(int i = 0; i < n; i++){
			arr[i] = sc.nextInt();
		}
		int elem = sc.nextInt();
		System.out.println(removeElement(arr, n, elem));
		
	}

	private static int removeElement(int[] arr, int n, int elem) {
		int count = 0;
		for(int i = 0; i < n; i++){
			if(arr[i] == elem){
				count++;
			}
		}
		return n - count;
	}
}
