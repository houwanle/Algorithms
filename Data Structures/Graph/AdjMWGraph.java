/*邻接矩阵图类AdjMWGraph的设计*/
public class AdjMWGraph {
    static final int maxWeight = 10000;

    private SeqList vertices;//存储结点的顺序表
    private int[][] edge;//存储边的二维数组
    private int numOfEdges;//边的数量

    public AdjMWGraph(int maxV){//构造函数，maxV为结点个数
        vertices = new SeqList(maxV);//创建顺序表vertices
        edge = new int[maxV][maxV];//创建二维数组edge
        for(int i = 0; i < maxV; i++){
            for(int j = 0; j < maxV; j++){
                if(i == j) edge[i][j] = 0;
                else edge[i][j] = maxWeight;
            }
        }
        numOfEdges = 0;
    }

    public int getNumOfVertices(){//返回结点个数
        return vertices.size();
    }

    public int getNumOfEdges(){//返回边的个数
        return numOfEdges;
    }

    public Object getValue(int v) throws Exception{//返回结点v的数据元素
        return vertices.getData(v);
    }

    public int getWeight(int v1, int v2) throws Exception{
        //返回边<v1,v2>的权值
        if(v1 < 0 || v1 >= vertices.size() || v2 < 0 || v2 >= vertices.size())
            throw new Exception("参数v1或v2越界出错！");
        return edge[v1][v2];
    }

    public void insertVertex(Object vertex) throws Exception{
        //插入结点
        vertices.insert(vertices.size(), vertex);//调用顺序表的插入函数
    }

    public void insertEdge(int v1,int v2,int weight) throws Exception{
        //插入边<v1,v2>，权值为weight
        if(v1 < 0 || v1 >= vertices.size() || v2 < 0 || v2 >= vertices.size())
            throw new Exception("参数v1或v2越界出错！");
        edge[v1][v2] = weight;//置边的权值
        numOfEdges++;//边的个数加1
    }

    public void deleteEdge(int v1,int v2) throws Exception{
        //删除边<v1,v2>
        if(v1 < 0 || v1 >= vertices.size() || v2 < 0 || v2 > vertices.size())
            throw new Exception("参数v1或v2越界出错！");
        if(edge[v1][v2] == maxWeight || v1 == v2)
            throw new Exception("该边不存在！");

        edge[v1][v2] = maxWeight;//置边的权值为无穷大
        numOfEdges--;//边的个数减1
    }

    public int getFirstNeighbor(int v) throws Exception{
        //取结点v的第一个邻接节点。若存在返回该结点的下标序号，否则返回-1
        if(v < 0 || v > vertices.size())
            throw new Exception("参数v越界出错！");
        for(int col = 0; col < vertices.size(); col++)
            if(edge[v][col] > 0 && edge[v][col] < maxWeight)
                return col;
        return -1;
    }

    public int getNextNeighbor(int v1, int v2) throws Exception{
        //取结点v1的邻接结点v2后的邻接结点。若存在返回该结点的下标序号，否则返回-1
        if(v1 < 0 || v1 >= vertices.size() || v2 < 0 || v2 >= vertices.size())
            throw new Exception("参数v1或v2越界出错！");
        for(int col = v2 + 1; col < vertices.size(); col++)
            if(edge[v1][col] > 0 && edge[v1][col] < maxWeight)
                return col;
        return -1;
    }
}

=============================================分割线=============================================

/*创建图的数据和方法类*/
public class RowColWeight {
    public int row;//行下标
    public int col;//列下标
    public int weight;//权值

    public RowColWeight(int r, int c, int w){//构造函数
        row = r;
        col = c;
        weight = w;
    }

    public static void createGraph(AdjMWGraph g, Object[] v, int n, RowColWeight[] rc, int e) throws Exception{
        //static成员函数，用所给参数创建AdjMWGraph类对象g
        //v为结点的数据元素集合，n为结点个数，rc为边的集合，e为边的个数
        for (int i = 0; i < n; i++)//在图g中插入n个结点
            g.insertVertex(v[i]);
        for (int k = 0; k < e; k++)//在图g中插入e条边
            g.insertEdge(rc[k].row, rc[k].col, rc[k].weight);
    }
}

=============================================分割线=============================================

public class SeqList implements List{
    final int defaultSize = 10;

    int maxSize;
    int size;
    Object[] listArray;

    public SeqList() {
        initiate(defaultSize);
    }

    public SeqList(int size){
        initiate(size);
    }

    private void initiate(int sz) {
        maxSize = sz;
        size = 0;
        listArray = new Object[sz];
    }

    public void insert(int i, Object obj) throws Exception {
        if(size == maxSize){
            throw new Exception("顺序表已满无法插入！");
        }
        if(i < 0 || i > size){
            throw new Exception("参数错误！");
        }

        for(int j = size; j > i; j--)
            listArray[j] = listArray[j-1];

        listArray[i] = obj;
        size++;
    }

    public Object delete(int i) throws Exception {
        if(size == 0){
            throw new Exception("顺序表已空无法删除！");
        }
        if(i < 0 || i > size-1){
            throw new Exception("参数错误！");
        }
        Object it = listArray[i];
        for(int j = i; j < size-1; j++)
            listArray[j] = listArray[j+1];

        size--;
        return it;
    }

    public Object getData(int i) throws Exception {
        if(i < 0 || i >= size){
            throw new Exception("参数错误！");
        }
        return listArray[i];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int MoreDataDelete(SeqList L,Object x) throws Exception{
        int i,j;
        int tag = 0;

        for(i = 0; i < L.size; i++){
            if(x.equals(L.getData(i))){
                L.delete(i);
                i--;
                tag = 1;
            }
        }
        return tag;
    }
}

=============================================分割线=============================================

public interface List {
    public void insert(int i, Object obj) throws Exception;//插入
    public Object delete(int i) throws Exception;//删除
    public Object getData(int i) throws Exception;//取数据元素
    public int size();//求元素个数
    public boolean isEmpty();//是否为空
}

=============================================分割线=============================================

public class TestAdjMWGraph {
    public static void main(String[] args) {
        int n = 5, e = 5;
        AdjMWGraph g = new AdjMWGraph(n);
        Character[] a = {new Character('A'),
                new Character('B'),
                new Character('C'),
                new Character('D'),
                new Character('E')};
        RowColWeight[] rcw = {new RowColWeight(0, 1, 10),
                new RowColWeight(0, 4, 20),
                new RowColWeight(1, 3, 30),
                new RowColWeight(2, 1, 40),
                new RowColWeight(3, 2, 50)};

        try {
            RowColWeight.createGraph(g, a, n, rcw, e);
            System.out.println("结点个数为：" + g.getNumOfVertices());
            System.out.println("边的条数为：" + g.getNumOfEdges());

            g.deleteEdge(0, 4);//删除有向边<0,4>

            System.out.println();
            System.out.println("结点个数为：" + g.getNumOfVertices());
            System.out.println("边的条数为：" + g.getNumOfEdges());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
