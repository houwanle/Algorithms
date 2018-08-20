//Brute-Force算法

public int indexof_BF_Count(MyString subStr, int start) { //统计Brute-Force算法查找子串的比较次数并返回
    int i = start,j = 0,v;
    int count = 0;
    
    while(i < this.length() && j < subStr.length()) {
        if(this.charAt(i) == subStr.charAt(j)) {
            i++;
            j++;
        }
        else{
            i = i - j + 1;
            j = 0;
        }
        count++    //统计比较次数
    }
    return count;   //返回
}


//KMP算法

public int indexOf_KMPB_Count(MyString subStr,int start) {
//统计KMP算法查找子串的比较次数并返回
    int[] next = getNext(subStr);
    int i = start,j = 0,v;
    int count = 0;
    
    while(i < this.length() && j < subStr.length()) {
        if(this.charAt(i) == subStr.charAt(j)) {
            i++;
            j++;
        }
        else if(j==0) i++;
        else j = next[j];
        count++;
    }
    return count;
}


//测试程序
public class Test{
    public static void main(String[] args) {
        int count;
        MyString ms1 = new MyString("cddcdc");
        count = ms1.indexOf_BF_Count(new MyString("abcde"),0);
        System.out.println("例子1：");
        System.out.print("indexOf_BF:");
        System.out.println("count="+count);
        count = ms1.indexOf_KMP_Count(new MyString("abcde"),0);
        System.out.print("indexOf_KMPB:");
        System.out.println("count="+count);
        
        MyString ms2 = new MyString("aaaaaaaa");
        count = ms2.indexOf_BF_Count(new MyString("aaaab"),0);
        System.out.println("例子2：");
        System.out.print("indexOf_BF:");
        System.out.println("count="+count);
        count = ms2.indexOf_KMP_Count(new MyString("aaaab"),0);
        System.out.print("indexOf_KMPB:");
        System.out.println("count=" +count);    
    }
}
