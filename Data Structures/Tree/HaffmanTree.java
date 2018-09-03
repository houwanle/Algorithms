//构造哈夫曼树和哈夫曼编码的哈夫曼树类如下：
public class HaffmanTree{
    static final int maxValue = 10000;//最大权值
    private int nodeNum;//叶结点个数

    public HaffmanTree(int n){
        nodeNum = n;
    }

    public void haffman(int[] weight, HaffNode[] node){
        //构造权值为weight的哈夫曼树haffTree
        int m1,m2,x1,x2;
        int n = nodeNum;

        //哈夫曼树haffTree初始化。n个叶结点的哈夫曼树共有2n-1个结点
        for(int i = 0; i < 2 * n - 1; i++){
            HaffNode temp = new HaffNode();
            if(i < n)
                temp.weight = weight[i];
            else
                temp.weight = 0;
            temp.parent = 0;
            temp.flag = 0;
            temp.leftChild = -1;
            temp.rightChild = -1;
            node[i] = temp;
        }

        //构造哈夫曼树haffTree的n-1个非叶结点
        for (int i = 0; i < n - 1; i++){
            m1 = m2 = maxValue;
            x1 = x2 = 0;
            for(int j = 0; j < n + i; j++){
                if(node[j].weight < m1 && node[j].flag == 0){
                    m2 = m1;
                    x2 = x1;
                    m1 = node[j].weight;
                    x1 = j;
                }
                else if(node[j].weight < m2 && node[j].flag == 0){
                    m2 = node[j].weight;
                    x2 = j;
                }
            }

            //将找出的两棵权值最小的子树合并为一棵子树
            node[x1].parent = n + i;
            node[x2].parent = n + i;
            node[x1].flag = 1;
            node[x2].flag = 1;
            node[n + i].weight = node[x1].weight + node[x2].weight;
            node[n + i].leftChild = x1;
            node[n + i].rightChild = x2;
        }
    }

    public void haffmanCode(HaffNode[] node, Code[] haffCode){
        //由哈夫曼树haffTree构造哈夫曼编码haffCode
        int n = nodeNum;
        Code cd = new Code(n);
        int child,parent;

        //求n个叶结点的哈夫曼编码
        for (int i = 0; i < n; i++){
            cd.start = n - 1;//不等长编码的最后一位为n-1
            cd.weight = node[i].weight;//取得编码对应的权值
            child = i;
            parent = node[child].parent;

            while(parent != 0){
                //由叶结点向上直到根结点循环
                if(node[parent].leftChild == child)
                    cd.bit[cd.start] = 0;//左孩子结点编码0
                else {
                    cd.bit[cd.start] = 1;//右孩子结点编码1
                }
                cd.start--;
                child = parent;
                parent = node[child].parent;
            }

            Code temp = new Code(n);

            //保存叶结点的编码和不等长编码的起始位
            for(int j = cd.start + 1; j < n; j++)
                temp.bit[j] = cd.bit[j];
            temp.start = cd.start;
            temp.weight = cd.weight;
            haffCode[i] = temp;
        }
    }
}

===============================================分割线==================================================

//基于双亲孩子仿真指针存储结构的哈夫曼树结点类如下
public class HaffNode {//哈夫曼树的结点类
    int weight;//权值
    int flag;//标记
    int parent;//双亲结点下标
    int leftChild;//左孩子下标
    int rightChild;//右孩子下标

    public HaffNode(){

    }
}

===============================================分割线==================================================
//保存哈夫曼编码的哈夫曼编码类如下：
public class Code{//哈夫曼编码类
    int[] bit;//编码用数组
    int start;//编码的起始下标
    int weight;//字符的权值

    public Code(int n){
        bit = new int[n];
        start = n - 1;
    }
}

===============================================分割线==================================================

public class TestHaffNode {
    public static void main(String[] args) {
        int n = 4;
        HaffmanTree myHaff = new HaffmanTree(n);
        int[] weight = {1,3,5,7};
        HaffNode[] node = new HaffNode[2 * n + 1];
        Code[] haffCode = new Code[n];

        myHaff.haffman(weight, node);
        myHaff.haffmanCode(node, haffCode);

        for(int i = 0; i < n; i++){
            System.out.print("Weight = " + haffCode[i].weight + " Code = ");
            for(int j = haffCode[i].start + 1; j < n; j++)
                System.out.print(haffCode[i].bit[j]);
            System.out.println();
        }
    }
}
