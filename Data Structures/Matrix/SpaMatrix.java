/*三元组顺序表类*/
public class SpaMatrix {
	int rows;	//行数
	int cols;	//列数
	int dNum;	//非零元素个数
	MyVector v; //数组

	SpaMatrix(int max){//构造函数
		rows = cols = dNum = 0;
		v = new MyVector(max);
	}

	public void evaluate(int r, int c,int d,Three[] item) throws Exception{//给矩阵赋值
		rows = r;
		cols = c;
		dNum = d;

		for(int i = 0; i < d; i++){
			v.add(i,item[i]);
		}
	}

	public SpaMatrix transpose(){//转置
		SpaMatrix a = new SpaMatrix(v.size());//创建矩阵对象

		a.cols = rows;	//行数转为列数
		a.rows = cols;	//列数转为行数
		a.dNum = dNum;	//非零元素个数不变

		for(int i = 0; i < dNum; i++){
			Three t = (Three)v.get(i);//取得三元组
			a.v.add(i,new Three(t.col,t.row,t.value));//添加。注意：列号变为了行号，行号变为了列号
		}
		return a;	//返回矩阵对象
	}

	public void print(){//输出
		System.out.print("矩阵行数为：" + rows);
		System.out.print("矩阵列数为：" + cols);
		System.out.println(",非零元素个数为： " + dNum);

		System.out.println("矩阵非零元素三元组为：");
		for(int i = 0; i < dNum; i++){
			System.out.println("a<" + ((Three)v.get(i)).row + "," + ((Three)v.get(i)).col + ">=" +
					((Three)v.get(i)).value);
		}
	}
}

====================================================分割线=====================================================

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

====================================================分割线=====================================================

/*三元组类*/
public class Three {
	public int row;		//行号
	public int col;		//列号
	public double value; //数值

	public Three(int r, int c, double v){//构造函数1
		row = r;
		col = c;
		value = v;
	}

	Three() {//构造函数2
		this(0, 0, 0.0);
	}
}

====================================================分割线=====================================================
/*测试类*/
public class TestSpaMatrix {
    public static void main(String[] args){
        SpaMatrix matrixA = new SpaMatrix(10);
        SpaMatrix matrixB;

        Three[] a= new Three[6];
        a[0] = new Three(1,3,11.0);
        a[1] = new Three(1,5,17.0);
        a[2] = new Three(2,2,25.0);
        a[3] = new Three(4,1,19.0);
        a[4] = new Three(5,4,37.0);
        a[5] = new Three(6,7,50.0);

        try {
            matrixA.evaluate(6, 7, 6, a);
            System.out.println("原矩阵：");
            matrixA.print();

            System.out.println("转置后的矩阵：");
            matrixB = matrixA.transpose();
            matrixB.print();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
