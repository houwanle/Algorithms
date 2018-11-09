package MySet;

public class MySet {
    private MyVector values = new MyVector();//成员变量

    public void add(Object obj) {//添加
        if (obj == null)
            return;
        if (values.indexOf(obj) < 0)
            values.add(obj);//调用MyVector类的在最后添加成员函数
    }

    public void remove(Object obj) {//删除
        values.remove(obj);//调用MyVector类的删除数据元素成员函数
    }

    public boolean contain(Object obj) {//属于
        return values.contain(obj);//调用MyVector类的包含成员函数
    }

    public boolean include(Object obj) {//包含
        if (obj instanceof MySet) {//判断obj是否是MySet的实例
            MySet set = (MySet)obj;

            int counter = 0;//循环计数初值
            while(counter < values.size()) {//循环
                Object temp = values.get(counter);//取得元素
                counter++;                      //计数值加1
                if (!contain(temp))//判断是否属于
                    return false;//不属于则返回false
            }
            return true;//所有元素均属于则返回true
        } else
            return false;
    }

    public boolean equals(Object obj) {//相等
        if (obj instanceof MySet) {
            MySet set = (MySet)obj;
            if (include(set) && set.include(this))//判断是否相相互包含
                return true;            //互相包含则返回true
            else
                return false;           //否则返回false
        }
        else
            return false;
    }

    public int size(){//元素个数
        return values.size();
    }

    public boolean isEmpty(){//集合空否
        return values.size()>0;
    }

    public void print(){        //输出
        int counter = 0;
        while(counter < values.size()) {
            System.out.print(values.get(counter) + " ");
            counter++;
        }
    }
}
