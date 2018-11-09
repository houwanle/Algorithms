package MyString;

public class TestMyString {
    public static void main(String[] args) {
        char[] var1 = {'d','u','j','i','a','n','h','u','a'};
        char[] var2 = {'y','a','n','g','j','i','n','f','e','n','g'};
        int length1 = var1.length;

        MyString ms1 = new MyString(var1,0,length1);//用构造函数2
        MyString ms2 = new MyString(var2);//用构造函数3
        MyString ms3 = new MyString("sunhonglei");//用构造函数4
        MyString ms4 = new MyString("zhangjiahui");//用构造函数4

        MyString ms5 = ms1.concat(ms2);//测试连接
        ms1.myPrint();
        ms5.myPrint();

        MyString ms6 = ms4.substring(0,4);//测试取子串
        ms6.myPrint();

        MyString ms7 = ms4.insert(new MyString("123"),4);//测试插入子串
        ms7.myPrint();

        MyString ms8 = ms4.delete(3,6);//测试删除子串
        ms8.myPrint();
    }
}
