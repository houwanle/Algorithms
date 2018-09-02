/*二叉树中序游标类*/
public class BiTrInIterator extends BiTreeInterator{
    private LinkStack s = new LinkStack();//创建堆栈类对象s

    BiTrInIterator(BiTreeNode t) {//构造函数
        super(t);	//调用父类的构造函数
    }

    private BiTreeNode goFarLeft(BiTreeNode t) {//寻找最左孩子结点
        if(t == null) return null;
        while(t.getLeft() != null) {
            s.push(t);
            t = t.getLeft();
        }
        return t;
    }

    public void reset(){//覆盖基类的重置成员函数
        if(root == null) iteComplete = 1;//置结束标记
        else iteComplete = 0;

        if(root == null) return;
        current = goFarLeft(root);
    }

    public void next(){//覆盖基类的下一个结点成员函数
        if(iteComplete == 1){
            System.out.println("已到二叉树尾！");
            return;
        }

        if(current.getRight() != null){
            current = goFarLeft(current.getRight());//寻找当前结点右孩子结点的最左孩子结点
        }
        else if(s.notEmpty()){//若堆栈非空
            try {
                current = (BiTreeNode)s.pop();//退栈
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            iteComplete = 1;//置结束标记
        }
    }
}
=======================================================分割线===============================================
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
=======================================================分割线===============================================
public class LinkStack implements Stack {
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

=======================================================分割线===============================================
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

=======================================================分割线===============================================
public interface Stack {
    public void push(Object obj) throws Exception;
    public Object pop() throws Exception;
    public Object getTop() throws Exception;
    public boolean notEmpty();
}

=======================================================分割线===============================================

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

=======================================================分割线===============================================

/*中序遍历二叉树各结点的结点信息*/
public class TestBiTrInIterator {
    public static BiTreeNode geTreeNode(Object item, BiTreeNode left, BiTreeNode right) {
        BiTreeNode temp = new BiTreeNode(item,left,right);
        return temp;
    }

    public static BiTreeNode makeTree(){
        BiTreeNode b,c,d,e,f,g;

        g = geTreeNode(new Character('G'), null, null);
        d = geTreeNode(new Character('D'), null, g);
        b = geTreeNode(new Character('B'), d, null);
        e = geTreeNode(new Character('E'), null, null);
        f = geTreeNode(new Character('F'), null, null);
        c = geTreeNode(new Character('C'), e, f);
        return geTreeNode(new Character('A'), b, c);
    }

    public static void main(String[] args){
        BiTreeNode root;

        root = makeTree();
        BiTrInIterator myIter = new BiTrInIterator(root);

        System.out.print("中序遍历序列为：");
        for(myIter.reset(); !myIter.endOfBiTree(); myIter.next())
            System.out.print(myIter.getData() + " ");
    }
}
