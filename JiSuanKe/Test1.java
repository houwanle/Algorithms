/**
 * @author Mr.Hou
 * 
 * 输入为一行，包括了用空格分隔的三个整数 AA、BB、CC（数据范围均在-40−40 ~ 4040 之间）。
 * 输出为一行，为“A+B+CA+B+C”的计算结果。
 * 
 * 样例输入
	22 1 3
     样例输出 
	26 
 */

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int sum = a+b+c;
		
		System.out.println(sum);
	}
}