package MyStringBuffer;

public class MyStringBuffer {
    private char[] value;
    private int count;

    private void expandCapacity(int newCapacity) {//重新申请内存空间
        char newValue[] = new char[newCapacity];//申请内存空间
        arrayCopy(value,0,newValue,0,count);//复制原字符数组
        value = newValue;//让value指向新创建的newValue数组
    }

    private void arrayCopy(char[] src, int srcPos, char[] dst, int dstPos, int length) {//数组元素拷贝
        if (src.length-srcPos < length || dst.length-dstPos < length)
            throw new StringIndexOutOfBoundsException(length);
        for (int i = 0; i < length; i++){
            dst[dstPos++] = src[srcPos++];
        }
    }

    public MyStringBuffer(String str) {//构造函数
        char[] chararray = str.toCharArray();
        value = chararray;
        count = chararray.length;
    }

    public MyStringBuffer concat(MyStringBuffer str) {//连接
        int otherLen = str.length();
        if (otherLen == 0) {
            return this;
        }
        expandCapacity(count+otherLen);//重新申请内存空间
        arrayCopy(str.toArray(),0,this.toArray(),this.length(),str.length());
        count = count + otherLen;
        return this;//返回原串
    }

    private char[] toArray() {//返回字符数组
        return value;
    }

    private int length() {//返回字符长度
        return count;
    }

    public void myPrint(){//输出
        for (int i = 0; i < count; i++){
            System.out.print(value[i]);
        }
        System.out.println();
    }
}
