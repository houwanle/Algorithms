package test;
/*
对于大于 1 的数，如果除了 1 和它本身，它不能再被其它正整数整除，那么我们说它是一个质数。晓萌想判断一个数是不是质数，希望找你写个程序，帮助她进行判断。

输入格式
输入包括一行，为一个整数 N(1<N≤1000)，正是晓萌给出你让你判断的数字。

输出格式
输出包括一行，如果晓萌给出的整数 N 为质数，那么输出YES；如果 N 不是质数，那么输出NO。

样例输入 
3
样例输出
YES
*/
import java.util.Scanner;

public class Test3 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int i = 2;
		for(;i <= Math.sqrt(n);i++){
			if(n % i == 0){
				System.out.println("NO");
				return;
			}
		}
		if(i > Math.sqrt(n)){
			System.out.println("YES");
		}
	}
}
