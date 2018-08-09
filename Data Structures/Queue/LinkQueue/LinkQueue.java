package LinkQueue;

public class LinkQueue implements Queue {
    Node front;             //队头
    Node rear;              //队尾
    int count;              //计数器

    public LinkQueue() {    //无参构造函数
        initiate();
    }

    public LinkQueue(int sz) {//有参数构造函数
        initiate();
    }

    private void initiate() {//初始化
        front = rear = null;
        count = 0;
    }

    @Override
    public void append(Object obj) throws Exception {//插入
        Node newNode = new Node(obj,null);//创建新结点

        if (rear != null)
            rear.next = newNode;//链入新结点
        rear = newNode;         //置队尾
        if (front == null)
            front = newNode;    //置队头
        count++;
    }

    @Override
    public Object delete() throws Exception {//删除
        if (count == 0)
            throw new Exception("队列已空！");

        Node temp = front;
        front = front.next;//原队头结点脱链
        count--;
        return temp.getElement();
    }

    @Override
    public Object getFront() throws Exception {//取队头数据元素
        if (count == 0)
            throw new Exception("队列已空！");
        return front.getElement();
    }

    @Override
    public boolean notEmpty() {//非空否
        return count != 0;
    }
}
