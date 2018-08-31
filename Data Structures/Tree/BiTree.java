/**
 * 二叉树类设计
 */
public class BiTree {
    private BiTreeNode root;//根指针

    private void preOrder(BiTreeNode t, Visit vs){//前序遍历
        if(t != null){
            vs.print(t.data);
            preOrder(t.getLeft(), vs);
            preOrder(t.getRight(), vs);
        }
    }

    private void inOrder(BiTreeNode t, Visit vs){//中序遍历
        if(t != null){
            inOrder(t.getLeft(), vs);
            vs.print(t.data);
            inOrder(t.getRight(), vs);
        }
    }

    private void postOrder(BiTreeNode t, Visit vs){//后序遍历
        if(t != null){
            postOrder(t.getLeft(), vs);
            postOrder(t.getRight(), vs);
            vs.print(t.data);
        }
    }

    BiTree() {			//构造函数
        root = null;
    }

    BiTree(Object item, BiTree left, BiTree right){//构造函数
        BiTreeNode l, r;
        if(left == null) l = null;
        else 			 l = left.root;

        if(right == null) r = null;
        else			  r = right.root;
        root = new BiTreeNode(item,l,r);
    }

    public void preOrder(Visit vs){//前序遍历
        preOrder(root,vs);
    }

    public void inOrder(Visit vs){//中序遍历
        inOrder(root,vs);
    }

    public void postOrder(Visit vs){//后序遍历
        postOrder(root,vs);
    }
}

===============================================分割线================================================
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

===============================================分割线================================================

public class Visit {
    public void print(Object item){
        System.out.print(item + " ");
    }
}

===============================================分割线================================================

public class TestBiTree {
    public static void main(String[] args) {
        //构造图7-10(a)所示的二叉树
        BiTree g = new BiTree(new Character('G'),null,null);
        BiTree d = new BiTree(new Character('D'),null,g);
        BiTree b = new BiTree(new Character('B'),d,null);
        BiTree e = new BiTree(new Character('E'),null,null);
        BiTree f = new BiTree(new Character('F'),null,null);
        BiTree c = new BiTree(new Character('C'),e,f);
        BiTree a = new BiTree(new Character('A'),b,c);

        Visit vs = new Visit();//创建Visit类对象
        System.out.print("前序遍历结点序列为：");
        a.preOrder(vs);
        System.out.println();

        System.out.print("中序遍历结点序列为：");
        a.inOrder(vs);
        System.out.println();

        System.out.print("后序遍历结点序列为：");
        a.postOrder(vs);
        System.out.println();
    }
}
