package LinkList;

public class LinList implements List{
    Node head;      //头指针
    Node current;   //当前结点位置
    int size;       //数据元素个数

    LinList() {
        head = current = new Node(null);//构造函数
        size = 0;
    }

    public void index(int i) throws Exception{//定位
        if(i < -1 || i > size-1){
            throw new Exception("参数错误!");
        }
        if(i == -1) return;
        current = head.next;
        int j = 0;
        while((current != null) && j < i){
            current = current.next;
            j++;
        }
    }

    @Override
    public void insert(int i, Object obj) throws Exception {//插入
        if(i < 0 || i > size){
            throw new Exception("参数错误!");
        }
        index(i-1);
        current.setNext(new Node(obj,current.next));
        size++;
    }

    @Override
    public Object delete(int i) throws Exception {//删除
        if(size == 0){
            throw new Exception("链表已空无元素可删!");
        }
        if(i < 0 || i > size-1){
            throw new Exception("参数错误!");
        }
        index(i-1);
        Object obj = current.next.getElement();
        current.setNext(current.next.next);
        size--;
        return obj;
    }

    @Override
    public Object getData(int i) throws Exception {//取数据元素
        if(i < -1 || i > size-1){
            throw new Exception("参数错误!");
        }
        index(i);
        return current.getElement();
    }

    @Override
    public int size() {//取数据元素个数
        return size;
    }

    @Override
    public boolean isEmpty() {//是否为空
        return size == 0;
    }
}
