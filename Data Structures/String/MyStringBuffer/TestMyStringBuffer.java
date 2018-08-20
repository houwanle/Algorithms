package MyStringBuffer;


public class TestMyStringBuffer {
    public static void main(String[] args) {
        MyStringBuffer msb1 = new MyStringBuffer("sunhonglei");
        MyStringBuffer msb2 = new MyStringBuffer("zhangjiahui");

        System.out.print("msb1的连接前输出值：");
        msb1.myPrint();
        MyStringBuffer msb3 = msb1.concat(msb2);
        System.out.print("msb1的连接后输出值：");
        msb1.myPrint();
        System.out.print("msb3的输出值：");
        msb3.myPrint();
    }
}
