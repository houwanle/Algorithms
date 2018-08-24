package MyVector;
/*
    首先，创建一个只能保存10个数据元素的MyVector类的对象；
    然后，依次把1至10的整数保存到该对象中；
    第三，向该对象添加第11个数据元素；
    最后，输出该对象中的所有数据元素。

 */
public class TestMyVector {
    public static void main(String[] args) {
        int i;
        Integer t;
        MyVector mv = new MyVector(10);
        for (i = 0; i < 10; i++)
            mv.add(new Integer(i+1));

        mv.add(new Integer(11));    //执行此语句时自动扩充内存单元个数

        System.out.println("size = "+ mv.size());
        for (i = 0; i < mv.size(); i++) {
            t = (Integer)mv.get(i);
            System.out.print(t + " ");
        }
    }
}
