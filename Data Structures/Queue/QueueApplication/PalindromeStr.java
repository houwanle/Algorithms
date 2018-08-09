import java.util.Scanner;

/*
  编写判断一个字符序列是否是回文的函数，并设计一个主函数进行测试。
    算法思想：设字符数组str中存放了要判断的字符串。把字符数组中
    的字符逐个分别存入一个队列和一个堆栈，然后逐个出队列和退栈并
    比较出队列的字符和退栈的字符是否相等，若全相等则该字符序列是
    回文，否则就不是回文。

    输入：
    ABCDEDCBA
    ABCDEDBAC
    输出：
    ABCDEDCBA是回文！
    ABCDEDBAC不是回文！
 */

public class PalindromeStr {
    public static void palindromeStr(String str) throws Exception{
        int n = str.length();
        SeqStack myStack = new SeqStack(n);
        SeqQueue myQueue = new SeqQueue(n);

        for (int i = 0; i < n; i++) {
            myQueue.append(str.substring(i, i+1));
            myStack.push(str.substring(i, i+1));
        }

        while(myQueue.notEmpty() || myStack.notEmpty()) {
            if (!myQueue.delete().equals(myStack.pop())) {
                System.out.println(str + "不是回文！");
                return;
            }
        }
        System.out.println(str + "是回文！");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();

        try {
            palindromeStr(str1);
            palindromeStr(str2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
