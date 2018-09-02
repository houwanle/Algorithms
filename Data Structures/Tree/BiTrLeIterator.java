/*二叉树层序游标类*/
public class BiTrLeIterator extends BiTreeInterator{
    LinkQueue q = new LinkQueue();//创建链式队列类对象q

    BiTrLeIterator(BiTreeNode t) {//构造函数
        super(t);
    }

    public void reset(){//覆盖基类的重置成员函数
        if (root == null) iteComplete = 1;//置结束标记
        else iteComplete = 0;

        if (root == null) return;
        current = root;
        try {
            if (root.getLeft() != null)
                q.append(root.getLeft());//左孩子结点入队列
            if (root.getRight() != null)
                q.append(root.getRight());//右孩子结点入队列
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void next(){//覆盖基类的下一个结点成员函数
        if (iteComplete == 1){
            System.out.println("已到二叉树尾！");
            return;
        }
        if (q.notEmpty()){
            try {
                current = (BiTreeNode)q.delete();//出队列
                if (current.getLeft() != null)
                    q.append(current.getLeft());//左孩子结点入队列
                if (current.getRight() != null)
                    q.append(current.getRight());//右孩子结点入队列
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            iteComplete = 1;//置结束标记
    }
}

===============================================分割线==================================================
/*二叉树游标类*/
public class BiTreeInterator {
    BiTreeNode root;//根指针
    BiTreeNode current;//当前结点
    int iteComplete;//到达尾部标记

    BiTreeInterator() {//构造函数
    }

    BiTreeInterator(BiTreeNode tree){//构造函数
        root = tree;
        current = tree;
        iteComplete = 1;
    }

    public void reset(){//重置

    }

    public void next(){//下一个结点
    }

    public boolean endOfBiTree(){//结束否
        return iteComplete == 1;
    }

    public Object getData(){//取数据元素
        if(current == null)
            return null;
        else
            return current.data;
    }
}

===============================================分割线==================================================
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

===============================================分割线==================================================
public class Node {
    Object element;     //数据元素
    Node next;          //表示下一个结点的对象引用

    Node(Node nextval){//用于头结点的构造函数1
        next = nextval;
    }

    Node(Object obj, Node nextval){//用于其他结点的构造函数2
        element = obj;
        next = nextval;
    }

    public Node getNext(){//取next
        return next;
    }

    public void setNext(Node nextval){//设置next
        next = nextval;
    }

    public Object getElement(){//取element
        return element;
    }

    public void setElement(Object element){//设置element
        this.element = element;
    }

    public String toString() {//转换element为String类型
        return element.toString();
    }
}

===============================================分割线==================================================

public interface Queue {

    public void append(Object obj) throws Exception;

    public Object delete() throws Exception;

    public Object getFront() throws Exception;

    public boolean notEmpty();

}

===============================================分割线==================================================

/**
 * 二叉树的结点类
 */
public class BiTreeNode {
    private BiTreeNode leftChild;//左孩子结点对象引用
    private BiTreeNode rightChild;//右孩子结点对象引用
    public Object data;			//数据元素

    BiTreeNode() {
        leftChild = null;
        rightChild = null;
    }

    BiTreeNode(Object item, BiTreeNode left, BiTreeNode right){
        data = item;
        leftChild = left;
        rightChild = right;
    }

    public BiTreeNode getLeft(){
        return leftChild;
    }
    public BiTreeNode getRight(){
        return rightChild;
    }

    public Object getData(){
        return data;
    }
}
