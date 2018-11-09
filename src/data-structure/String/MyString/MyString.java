package MyString;

public class MyString {
    private char[] value;   //私有成员变量，字符数组
    private int count;      //私有成员变量，字符个数

    //字符数组拷贝。src为源串的字符数组，srcPos为源串的起始下标
    //dst为目标的字符数组，dstPos为目标串的起始下标，length为新串的长度
    static void arrayCopy(char[] src, int srcPros, char[] dst, int dstPos, int length){
        if (src.length-srcPros < length || dst.length-dstPos < length)
            throw new StringIndexOutOfBoundsException(length);
        for (int i = 0; i < length; i++){
            dst[dstPos++] = src[srcPros++];
        }
    }

    public MyString() { //构造函数1
        value = new char[0];
        count = 0;
    }

    public MyString(char[] value, int offset, int count){//构造函数2
        //value为字符数组，offset为数组起始下标，count为个数
        //即用value数组中从offset下标始、个数为count的字符串创建对象
        if (offset < 0) {
            throw new StringIndexOutOfBoundsException(offset);
        }
        if (count < 0) {
            throw new StringIndexOutOfBoundsException(count);
        }
        if (offset > value.length-count) {
            throw new StringIndexOutOfBoundsException(offset + count);
        }
        this.value = new char[count];
        this.count = count;
        arrayCopy(value,offset,this.value,0,count);//数组元素拷贝
    }

    public MyString(char[] value) {     //构造函数3
        this.count = value.length;
        this.value = new char[count];
        arrayCopy(value,0,this.value,0,count);//数组元素拷贝
    }

    public MyString(String str){        //构造函数4
        //此构造函数是为了串初始化赋值的方便，否则程序中不能使用
        //类似MyString ms4 = new MyString("hwl")的语句
        char[] chararray = str.toCharArray();
        value = chararray;
        count = chararray.length;
    }

    public char charAt(int index) {     //取字符
        if ((index < 0) || (index >= count)) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return value[index];
    }
    public int length(){        //取串长度
        return count;
    }

    public int compareTo(MyString anotherString) {//比较
        //若当前对象的串值大于anotherString的串值则返回一个正整数
        //若当前对象的串值等于anotherString的串值则返回0
        //若当前对象的串值小于anotherString的串值则返回一个负整数
        int len1 = count;
        int len2 =anotherString.count;
        int n = Math.min(len1,len2);//n为len1和len2的较小者
        char v1[] = value;
        char v2[] = anotherString.value;
        int i = 0;
        int j = 0;

        int k = i;
        int lim = n + i;
        while(k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                return c1 - c2; //返回第一个不相等字符的数值差
            }
            k++;
        }

        //当前边部分字符比较全部相等时
        return len1 - len2; //返回两个字符串长度的数值差
    }

    public MyString substring(int beginIndex, int endIndex) {//取子串
        //所取子串从下标beginIndex开始至下标endIndex的前一位置
        if (beginIndex < 0) {
            throw new StringIndexOutOfBoundsException(beginIndex);
        }
        if (endIndex > count) {
            throw new StringIndexOutOfBoundsException(endIndex);
        }
        if (beginIndex > endIndex) {
            throw new StringIndexOutOfBoundsException(endIndex-beginIndex);
        }
        return ((beginIndex==0) && (endIndex == count)) ?this:new MyString(value,beginIndex,endIndex-beginIndex);
    }

    public MyString substring(int beginIndex) {     //取子串
        //所取子串从下标beginIndex开始至串的末尾
        return substring(beginIndex,count);
    }

    public MyString concat(MyString str) {      //连接
        int otherLen = str.length();
        char[] strarray = str.toArray();
        if (otherLen == 0) {
            return this;
        }
        char buf[] = new char[count+otherLen];
        arrayCopy(value,0,buf,0,count);//字符数组拷贝
        arrayCopy(strarray,0,buf,count,otherLen);//字符数组拷贝
        return new MyString(buf);
    }

    public MyString insert(MyString str, int pos) {//插入子串
        //在当前对象字符数组的pos下标开始插入str对象的字符串
        if (pos < 0 || pos > count)
            throw new StringIndexOutOfBoundsException(pos);
        if (pos != 0) {
            MyString str1 = this.substring(0,pos);//取出主串的前一部分
            MyString str2 = this.substring(pos);//取出主串的后一部分
            MyString res1 = str1.concat(str);//连接str1和str
            MyString res2 = res1.concat(str2);//连接res1和str2
            return res2;
        } else
            return str.concat(this);
    }

    public MyString delete(int beginIndex, int endIndex) {//删除子串
        //删除当前对象从下标beginIndex开始至下标endIndex的前一下标的子串
        if (beginIndex < 0) {
            throw new StringIndexOutOfBoundsException(beginIndex);
        }
        if (endIndex > count) {
            throw new StringIndexOutOfBoundsException(endIndex);
        }
        if (beginIndex > endIndex) {
            throw new StringIndexOutOfBoundsException(endIndex-beginIndex);
        }
        if ((beginIndex == 0) && (endIndex == count))
            return new MyString();//返回串值为空的对象
        else {
            MyString str1 = this.substring(0,beginIndex);//取出主串的前一部分
            MyString str2 = this.substring(endIndex);//取出主串的后一部分
            return str1.concat(str2);
        }
    }

    public void myPrint(){//输出串值
        for (int i = 0; i < count; i++){
            System.out.print(value[i]);
        }
        System.out.println();
    }

    private char[] toArray() {//返回字符数组
        char[] buf = new char[count];
        arrayCopy(value,0,buf,0,count);
        return buf;
    }
}
