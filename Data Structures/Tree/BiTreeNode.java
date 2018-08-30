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
===============================================分割线======================================================
/*
 * 二叉树遍历类Traverse
 */
public class Traverse {
    public static void preOrder(BiTreeNode t, Visit vs){
        //前序遍历二叉树t，访问结点操作为vs.print(t.data)
        if(t != null){
            vs.print(t.data);
            preOrder(t.getLeft(), vs);
            preOrder(t.getRight(), vs);
        }
    }

    public static void inOrder(BiTreeNode t, Visit vs){
        //中序遍历二叉树t，访问结点操作为vs.print(t.data)
        if(t != null) {
            inOrder(t.getLeft(), vs);
            vs.print(t.data);
            inOrder(t.getRight(), vs);
        }
    }

    public static void postOrder(BiTreeNode t, Visit vs){
        //后序遍历二叉树t，访问节点操作为vs.print(t.data)
        if(t != null){
            postOrder(t.getLeft(), vs);
            postOrder(t.getRight(), vs);
            vs.print(t.data);
        }
    }

    public static void levelOrder(BiTreeNode t, Visit vs) throws Exception{
        //层序遍历二叉树t，访问结点操作为vs.print(t.data)
        LinkQueue q = new LinkQueue();//创建链式队列类对象
        if(t == null) return;
        BiTreeNode curr;
        q.append(t);//根结点入队列
        while(q.notEmpty()){//当队列非空时循环
            curr = (BiTreeNode)q.delete();//出队列
            vs.print(curr.data);//访问该结点
            if(curr.getLeft() != null)
                q.append(curr.getLeft());//左孩子结点入队列
            if(curr.getRight() != null)
                q.append(curr.getRight());//右孩子结点入队列
        }
    }


    public static void printBiTree(BiTreeNode root, int level){
        //二叉树root第level层结点数据元素值的横向输出
        if(root != null){
            //子二叉树root.getRight()第level+1层结点数据元素的横向输出
            printBiTree(root.getRight(), level + 1);

            if(level != 0){
                //走过6*(leve-1)个空格
                for(int i = 0; i < 6 * (level - 1); i++){
                    System.out.print(" ");
                }
                System.out.print("---");//输出横线
            }
            System.out.println(root.data);//输出结点的数据元素值
            //子二叉树root.getLeft()第level + 1层结点数据元素值的横向输出
            printBiTree(root.getLeft(), level + 1);
        }
    }


    public static BiTreeNode search(BiTreeNode t, Object x){
        //查找数据元素
        BiTreeNode temp;

        if(t == null) return null;//查找失败出口
        if(t.data.equals(x)) return t;//查找成功出口

        if(t.getLeft() != null){
            temp = search(t.getLeft(), x);//在左子树查找
            if(temp != null) return temp;//查找成功则结束递归
        }

        if(t.getRight() != null){
            temp = search(t.getRight(), x);//在右子树查找
            if(temp != null) return temp;//查找成功则结束递归
        }

        return null;//查找失败出口
    }
}

===============================================分割线======================================================

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

===============================================分割线======================================================

public interface Queue {

    public void append(Object obj) throws Exception;

    public Object delete() throws Exception;

    public Object getFront() throws Exception;

    public boolean notEmpty();

}

===============================================分割线======================================================

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

===============================================分割线======================================================

public class Visit {
    public void print(Object item){
        System.out.print(item + " ");
    }
}

===============================================分割线======================================================
/*测试类*/
public class TestBiTree {
    public static void printBiTree(BiTreeNode root, int level){
        //二叉树root各层结点数据元素值的横向输出
        if(root != null){
            //子二叉树root.getRight()第level+1层结点数据元素的横向输出
            printBiTree(root.getRight(), level + 1);

            if(level != 0){
                //走过6*(leve-1)个空格
                for(int i = 0; i < 6 * (level - 1); i++){
                    System.out.print(" ");
                }
                System.out.print("---");//输出横线
            }
            System.out.println(root.data);//输出结点的数据元素值
            //子二叉树root.getLeft()第level + 1层结点数据元素值的横向输出
            printBiTree(root.getLeft(), level + 1);
        }
    }

    public static BiTreeNode search(BiTreeNode t, Object x){
        //查找数据元素
        BiTreeNode temp;

        if(t == null) return null;//查找失败出口
        if(t.data.equals(x)) return t;//查找成功出口

        if(t.getLeft() != null){
            temp = search(t.getLeft(), x);//在左子树查找
            if(temp != null) return temp;//查找成功则结束递归
        }

        if(t.getRight() != null){
            temp = search(t.getRight(), x);//在右子树查找
            if(temp != null) return temp;//查找成功则结束递归
        }

        return null;//查找失败出口
    }

    public static BiTreeNode getTreeNode(Object item, BiTreeNode left, BiTreeNode right){
        //构造二叉树
        BiTreeNode temp = new BiTreeNode(item,left,right);
        return temp;
    }

    public static BiTreeNode makeTree(){
        //构造图7-10(a)所示的不带头结点的二叉链存储结构的二叉树
        BiTreeNode b,c,d,e,f,g;

        g = getTreeNode(new Character('G'), null, null);
        d = getTreeNode(new Character('D'), null, g);
        b = getTreeNode(new Character('B'), d, null);
        e = getTreeNode(new Character('E'), null, null);
        f = getTreeNode(new Character('F'), null, null);
        c = getTreeNode(new Character('C'), e, f);
        return getTreeNode(new Character('A'), b, c);
    }

    public static void main(String[] args){
        BiTreeNode root1;
        BiTreeNode temp;
        Visit vs = new Visit();//创建Visit类对象vs

        root1 = makeTree();
        System.out.println("二叉树为：");
        printBiTree(root1, 0);
        System.out.println();

        System.out.print("前序遍历结点序列为：");
        Traverse.preOrder(root1, vs);//调用Traverse类preOrder()成员函数
        System.out.println();

        System.out.print("中序遍历结点序列为：");
        Traverse.inOrder(root1, vs);
        System.out.println();

        System.out.print("后序遍历结点序列为：");
        Traverse.postOrder(root1, vs);
        System.out.println();

        System.out.print("层序遍历结点序列为：");
        try {
            Traverse.levelOrder(root1, vs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();

        temp = search(root1, new Character('C'));
        if(temp != null)
            System.out.println("查找到的结点数据值为：" + temp.data);
        else
            System.out.println("查找失败");
    }
}
