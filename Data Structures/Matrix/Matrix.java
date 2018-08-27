package Matrix;

/*矩阵类*/
public class Matrix {
    private MyVector values;//成员变量，矩阵元素
    private int h;			//成员变量，矩阵行数
    private int w;			//成员变量，矩阵列数


    public Matrix(int h, int w){//构造函数，h为行数，w为列数
        if(!(h > 0 && w > 0))
            throw new ArrayIndexOutOfBoundsException("h or w <" + 1);
        values = new MyVector(h);//创建有h行的对象
        for(int i = 0; i < h; i++){
            MyVector row = new MyVector(w);//创建有w列的对象
            values.add(row);			//让行元素引用等于row
            for(int j = 0; j < w; j++){
                row.add(null);  	//初始化矩阵元素为null
            }
        }
        this.h = h;
        this.w = w;
    }

    public void set(int row, int col, Object value){//置元素
        if(!(row >= 0 && w >= 0 && row < h && col < w))
            throw new ArrayIndexOutOfBoundsException("h or w < " + "-1");
        MyVector selrow = (MyVector)values.get(row);
        selrow.set(col, value);
    }

    public Object get(int row, int col){//取元素
        if(!(row >= 0 && w >= 0 && row < h && col < w))
            throw new ArrayIndexOutOfBoundsException("h or w < " + "-1");
        MyVector selrow = (MyVector)values.get(row);
        return selrow.get(col);
    }

    public int width(){//矩阵列数
        return w;
    }
    public int height(){
        return h;
    }

    public Matrix add(Matrix b){//矩阵加
        if(height()!=b.height() || width()!= b.width()){
            throw new ArrayIndexOutOfBoundsException("Matrix error");
        }
        Matrix result = new Matrix(height(), width());

        for(int i = 0; i < height(); i++){
            for(int j = 0; j < width();j++){
                Integer i1 = (Integer)get(i, j);
                Integer i2 = (Integer)(b.get(i, j));
                result.set(i, j, new Integer(i1.intValue() + i2.intValue()));
            }
        }
        return result;
    }

    public void print(){//输出矩阵元素
        for(int i = 0;i < h; i++){
            for(int j = 0; j < w; j++){
                System.out.print(get(i, j) + " ");
            }
            System.out.println();
        }
    }
}

==========================================分割线============================================
    
 public class MyVector {
    private Object[] elementData;	//数据元素
    private int elementCount;		//元素个数

    public int indexOf(Object element){//查找
        if(element == null){
            for(int i = 0; i < elementCount; i++)
                if(elementData[i] == null)
                    return i;	//返回第一个null元素
        }
        else{
            for(int i = 0; i < elementCount; i++)
                if(element.equals(elementData[i]))
                    return i;
        }
        return -1;			//没有找到则返回-1
    }

    public boolean contain(Object element){//存在
        return indexOf(element) >= 0;
    }

    public void remove(Object element){//删除数据元素
        int i = indexOf(element);
        if(i >= 0){
            remove(i);
        }
    }

    public void remove(int index){
        if(index >= elementCount){
            throw new ArrayIndexOutOfBoundsException(index + ">=" + elementCount);
        }
        else if(index < 0){
            throw new ArrayIndexOutOfBoundsException(index);
        }
        int j = elementCount - index-1;
        if(j > 0){
            System.arraycopy(elementData, index+1, elementData, index, j);
        }
        elementCount--;
        elementData[elementCount] = null;
    }

    private void ensureCapacity(int minCapacity){//扩充内存
        int oldCapacity = elementData.length;//原数组长度
        if(minCapacity > oldCapacity){//参数要求的长度是否大于原数组长度
            Object oldData[] = elementData;//原数组元素
            int newCapacity = oldCapacity * 2;//新的数组长度
            if(newCapacity < minCapacity){//新的数组长度是否小于参数要求的长度
                newCapacity  = minCapacity;
            }
            elementData = new Object[newCapacity];//扩充数组容量为newCapacity
            System.arraycopy(oldData, 0, elementData, 0, elementCount);//把原来的数组元素复制到新数组的开始位置

        }
    }
    public MyVector(){//构造函数
        this(10);
    }
    public MyVector(int initialCapacity) {//构造函数
        elementData = new Object[initialCapacity];
        elementCount = 0;
    }

    public void add(int index,Object element){//在index处添加
        if(index >= elementCount + 1){
            throw new ArrayIndexOutOfBoundsException(index + ">" + elementCount);
        }
        ensureCapacity(elementCount + 1);
        System.arraycopy(elementData, index, elementData, index + 1, elementCount - index);
        elementData[index] = element;
        elementCount++;
    }

    public void add(Object element){//在最后添加
        add(elementCount,element);
    }

    public void set(int index,Object element){
        //把index处元素重置为element
        if(index >= elementCount){
            throw new ArrayIndexOutOfBoundsException(index + ">=" + elementCount);
        }
        elementData[index] = element;
    }

    public Object get(int index){//取index处元素
        if(index >= elementCount)
            throw new ArrayIndexOutOfBoundsException(index);
        return elementData[index];
    }

    public int size(){//取元素个数
        return elementCount;
    }
}

===============================================分割线================================================

/*测试类*/
public class TestMatrix {
    public static void main(String[] args){//创建第一个矩阵
        Matrix mt1 = new Matrix(3,4);
        for(int i = 0; i < mt1.height(); i++){
            for(int j = 0; j < mt1.width(); j++){
                mt1.set(i, j, new Integer(i+j));//给矩阵元素赋值
            }
        }

        Matrix mt2 = new Matrix(3, 4);//创建第二个矩阵
        for(int i = 0; i < mt2.height(); i++){
            for(int j = 0; j < mt2.width(); j++){
                mt2.set(i, j, new Integer((int)(Math.random() * 10)));
                //调用随机数函数产生随机数给矩阵元素赋值
            }
        }

        System.out.println("Matrix 1:");
        mt1.print();//输出第一个矩阵的元素
        System.out.println("Matrix 2:");
        mt2.print();//输出第二个矩阵的元素

        Matrix mt3 = mt2.add(mt1);//矩阵加
        System.out.println("results after adding:");
        mt3.print();//输出矩阵加后的元素
    }
}
