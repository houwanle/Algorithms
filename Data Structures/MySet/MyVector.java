package MySet;

public class MyVector {
    private Object[] elementData;       //数据元素
    private int elementCount;           //元素个数

    private void ensureCapacity(int minCapacity) {//扩充内存
        int oldCapacity = elementData.length;   //原数组长度
        if (minCapacity > oldCapacity) {//参数要求的长度是否大于原数组长度
            Object oldData[] = elementData;//原数组元素
            int newCapacity = oldCapacity * 2;//新的数组长度
            if (newCapacity < minCapacity) {//新的数组长度是否小于参数要求的长度
                newCapacity = minCapacity;
            }
            elementData = new Object[newCapacity];//扩充数组的容量为newCapacity
            System.arraycopy(oldData,0,elementData,0,elementCount);//把原来的数组元素复制到新数组的开始位置
        }
    }

    public MyVector() {//构造函数
        this(10);
    }

    public MyVector(int initialCapacity) {
        elementData = new Object[initialCapacity];
        elementCount = 0;
    }

    public void add(int index,Object element) {//在index处添加
        if (index >= elementCount + 1) {
            throw new ArrayIndexOutOfBoundsException(index + ">" + elementCount);
        }
        ensureCapacity(elementCount + 1);
        System.arraycopy(elementData,index,elementData,index + 1,elementCount - index);
        elementData[index] = element;
        elementCount++;
    }

    public void add(Object element) {//在最后添加
        add(elementCount,element);
    }

    public void set(int index,Object element) {//把index处元素重置为element
        if (index >= elementCount) {
            throw new ArrayIndexOutOfBoundsException(index + ">=" + elementCount);
        }
        elementData[index] = element;
    }

    public Object get(int index) {//取index处元素
        if (index >= elementCount)
            throw new ArrayIndexOutOfBoundsException(index);
        return elementData[index];
    }

    public int size() {//取元素个数
        return elementCount;
    }

    //查找：如果向量中存在数据元素element，则返回其下标；否则返回-1。
    public int indexOf(Object element) {//查找
        if (element == null) {
            for (int i = 0; i < elementCount; i++)
                if (elementData[i] == null)
                    return i;   //返回第1个null元素
        } else {
            for (int i = 0; i < elementCount; i++)
                if (element.equals(elementData[i]))
                    return i;
        }
        return -1;  //没有找到则返回-1
    }

    //存在：如果向量中存在数据元素element，则返回true;否则返回false。
    public boolean contain(Object element) {//存在
        return indexOf(element) > 0;
    }

    //删除数据元素：删除向量中的数据元素element。
    public void remove(Object element) {//删除数据元素
        int i = indexOf(element);
        if (i >= 0)
            remove(i);
    }

    //删除第index个数据元素remove(index):删除向量中下标为index的数据元素。
    public void remove(int index) {//删除下标为index的数据元素
        if (index >= elementCount) {
            throw new ArrayIndexOutOfBoundsException(index+">=" + elementCount);
        } else if (index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        int j = elementCount-index-1;
        if (j > 0) {
            System.arraycopy(elementData,index+1,elementData,index,j);
        }
        elementCount--;
        elementData[elementCount] = null;
    }
}
