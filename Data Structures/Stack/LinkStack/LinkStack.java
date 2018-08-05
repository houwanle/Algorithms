package LinkStack;

public class LinkStack implements Stack{
    Node head;                  //堆栈头
    int size;                   //结点个数


    public LinkStack() {        //构造函数
        head = null;
        size = 0;
    }

    @Override
    public void push(Object obj) {//入栈
        head = new Node(obj,head);//新结点作为新栈顶
        size++;
    }

    @Override
    public Object pop() throws Exception {//出栈
        if(size == 0)
            throw new Exception("堆栈已空!");
        Object obj = head.element;//原栈顶数据元素
        head = head.next;//原栈顶结点脱链
        size--;
        return obj;
    }

    @Override
    public Object getTop(){
        return head.element;
    }

    @Override
    public boolean notEmpty() {//非空否
        return head != null;
    }
}
