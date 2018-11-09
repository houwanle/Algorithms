package QueueApplication;

public class SeqQueue implements Queue {
    static final int defaultSize = 10;
    int front;                          //队头
    int rear;                           //队尾
    int count;                          //元素个数计数器
    int maxSize;                        //最大数据元素个数
    Object[] data;                      //保存队列元素的数组

    public SeqQueue() {                 //无参构造函数
        initiate(defaultSize);
    }

    public SeqQueue(int sz) {           //有参构造函数
        initiate(sz);
    }

    private void initiate(int sz) {     //初始化
        maxSize = sz;
        front = rear = 0;
        count = 0;
        data = new Object[sz];
    }

    public void append(Object obj) throws Exception {//入队列
        if (count > 0 && front == rear) {
            throw new Exception("队列已满！");
        }

        data[rear] = obj;
        rear = (rear + 1) % maxSize;
        count++;
    }

    public Object delete() throws Exception {//出队列
        if (count == 0) {
            throw new Exception("队列已空！");
        }

        Object temp = data[front];
        front = (front+1) % maxSize;//加1后求模
        count--;
        return temp;
    }

    public Object getFront() throws Exception {//取队头数据元素
        if (count == 0) {
            throw new Exception("队列已空！");
        }
        return data[front];
    }


    public boolean notEmpty() {
        return count != 0;
    }
}
