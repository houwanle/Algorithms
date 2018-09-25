package Graph;
/*
 * CloseVertex 类
 */
public class MinSpanTree {
    Object vertex;//边的弧头结点数据
    int weight;//权值

    MinSpanTree() {
    }

    MinSpanTree(Object obj, int w){
        vertex = obj;
        weight = w;
    }
}
====================================================================================

/*
    普利母函数设计
 */
public class Prim {
    static final int maxWeight = 9999;
    public static void prim(AdjMWGraph g, MinSpanTree[] closeVertex) throws Exception{
        //用普里姆算法建立带权图g的最小生成树closeVerte
        int n = g.getNumOfVertices();
        int minCost;
        int[] lowCost = new int[n];
        int k = 0;

        for(int i = 1; i < n; i++)
            lowCost[i] = g.getWeight(0,i);//lowCost的初始值
        MinSpanTree temp = new MinSpanTree();

        //从结点0出发构造最小生成树
        temp.vertex = g.getValue(0);
        closeVertex[0] = temp;//保存结点0
        lowCost[0] = -1;//标记结点0

        for (int i = 1; i < n; i++){
            //寻找当前最小权值的边所对应的狐头结点k
            minCost = maxWeight;//maxWeight为定义的最大权值
            for(int j = 1; j < n; j++){
                if(lowCost[j] < minCost && lowCost[j] > 0){
                    minCost = lowCost[j];
                    k = j;
                }
            }

            MinSpanTree curr = new MinSpanTree();
            curr.vertex = g.getValue(k);//保存狐头结点k
            curr.weight = minCost;//保存相应权值
            closeVertex[i] = curr;
            lowCost[k] = -1;//标记结点k

            //根据加入集合U的结点k修改lowCost中的数值
            for(int j = 1; j < n; j++){
                if(g.getWeight(k, j) < lowCost[j])
                    lowCost[j] = g.getWeight(k, j);
            }
        }
    }
}
====================================================================================

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
====================================================================================

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
====================================================================================

public interface List {
    public void insert(int i, Object obj) throws Exception;//插入
    public Object delete(int i) throws Exception;//删除
    public Object getData(int i) throws Exception;//取数据元素
    public int size();//求元素个数
    public boolean isEmpty();//是否为空
}
====================================================================================

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

    public static void createGraph(SearchAdjMWGraph g, Object[] v, int n, RowColWeight[] rc, int e) throws Exception{
        //static成员函数，用所给参数创建AdjMWGraph类对象g
        //v为结点的数据元素集合，n为结点个数，rc为边的集合，e为边的个数
        for (int i = 0; i < n; i++)//在图g中插入n个结点
            g.insertVertex(v[i]);
        for (int k = 0; k < e; k++)//在图g中插入e条边
            g.insertEdge(rc[k].row, rc[k].col, rc[k].weight);
    }
}
====================================================================================

/*深度和广度优先遍历成员函数类*/
public class SearchAdjMWGraph {
    static final int maxWeight = 10000;

    private SeqList vertices;
    private int[][] edge;
    private int numOfEdges;

    public SearchAdjMWGraph(int maxV){//构造函数，maxV为结点个数
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

    public Object getValue(int v) throws Exception{//返回结点v的数据元素
        return vertices.getData(v);
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

    public int getNumOfVertices(){//返回结点个数
        return vertices.size();
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

    private void depthFirstSearch(int v, boolean[] visited, Visit vs) throws Exception{
        //连通图以v为初始结点序号、访问操作为vs的深度优先遍历
        //数组visited标记了相应结点是否已访问过，0表示为访问过，1表示已访问
        vs.print(getValue(v));//访问该节点
        visited[v] = true;//置已访问标记

        int w = getFirstNeighbor(v);//取第一个邻接结点
        while (w != -1){//当邻接结点存在时循环
            if(!visited[w])//如果没有访问过
                depthFirstSearch(w, visited, vs);//以w为初始结点递归
            w = getNextNeighbor(v,w);//取下一个邻接结点
        }
    }

    private void broadFirstSearch(int v, boolean[] visited, Visit vs) throws Exception{
        //连通图以v为初始结点序号、访问操作为vs的广度优先遍历
        //数组visited标记了相应结点是否已访问过，0表示未访问过，1表示已访问
        int u,w;
        SeqQueue queue = new SeqQueue();//创建顺序队列queue

        vs.print(getValue(v));//访问结点v
        visited[v] = true;//置已访问标记

        queue.append(new Integer(v));//结点v入队列
        while(queue.notEmpty()){//队列非空时循环
            u = ((Integer)queue.delete()).intValue();//出队列
            w = getFirstNeighbor(u);//取结点u的第一个邻接结点
            while(w != -1) {//当邻接结点存在时循环
                if(!visited[w]){//若该结点没有访问过
                    vs.print(getValue(w));//访问结点w
                    visited[w] = true;//置已访问标记
                    queue.append(new Integer(w));//结点w入队列
                }

                //取结点u的邻接结点w的下一个邻接结点
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void depthFirstSearch(Visit vs) throws Exception{
        //非连通图的深度优先遍历
        boolean[] visited = new boolean[getNumOfVertices()];

        for (int i = 0; i < getNumOfVertices(); i++)
            visited[i] = false;//置所有节点均未访问过
        for (int i = 0; i < getNumOfVertices(); i++)//对每个结点循环
            if(!visited[i])//如果该结点未访问
                depthFirstSearch(i,visited,vs);//以结点1为初始结点深度优先遍历
    }

    public void broadFirstSearch(Visit vs) throws Exception{
        //非连通图的广度优先遍历
        boolean[] visited = new boolean[getNumOfVertices()];

        for(int i = 0; i < getNumOfVertices(); i++)
            visited[i] = false;//置所有节点均未访问过
        for (int i = 0; i < getNumOfVertices(); i++)//对每个结点循环
            if(!visited[i])//如果该结点未访问过
                broadFirstSearch(i,visited,vs);//以结点i为初始节点广度优先遍历
    }

    public int getWeight(int v1, int v2) throws Exception{
        //返回边<v1,v2>的权值
        if(v1 < 0 || v1 >= vertices.size() || v2 < 0 || v2 >= vertices.size())
            throw new Exception("参数v1或v2越界出错！");
        return edge[v1][v2];
    }
}
====================================================================================

public class Visit {
    public void print(Object item){
        System.out.print(item + " ");
    }
}   
====================================================================================
public class TestPrim {
    static final int maxVertices = 100;

    public static void createGraph(AdjMWGraph g, Object[] v, int n, RowColWeight[] rc, int e) throws Exception{
        for(int i = 0; i < n; i++)
            g.insertVertex(v[i]);
        for(int k = 0; k < e; k++)
            g.insertEdge(rc[k].row,rc[k].col, rc[k].weight);
    }

    public static void main(String[] args){
        AdjMWGraph g = new AdjMWGraph(maxVertices);
        Character[] a = {new Character('A'),
                new Character('B'),
                new Character('C'),
                new Character('D'),
                new Character('E'),
                new Character('F'),
                new Character('G')};
        RowColWeight[] rcw = {new RowColWeight(0, 1, 50),new RowColWeight(1, 0, 50),
                new RowColWeight(0, 2, 60),new RowColWeight(2, 0, 60),
                new RowColWeight(1, 3, 65),new RowColWeight(3, 1, 65),
                new RowColWeight(1, 4, 40),new RowColWeight(4, 1, 40),
                new RowColWeight(2, 3, 52),new RowColWeight(3, 2, 52),
                new RowColWeight(2, 6, 45),new RowColWeight(6, 2, 45),
                new RowColWeight(3, 4, 50),new RowColWeight(4, 3, 50),
                new RowColWeight(3, 5, 30),new RowColWeight(5, 3, 30),
                new RowColWeight(3, 6, 42),new RowColWeight(6, 3, 42),
                new RowColWeight(4, 5, 70),new RowColWeight(5, 4, 70)};
        int n = 7,e = 20;
        try {
            createGraph(g, a, n, rcw, e);
            MinSpanTree[] closeVertex = new MinSpanTree[7];
            Prim.prim(g, closeVertex);//调用prim函数

            //输出Prim函数得到的最小生成树的结点序列和权值序列
            System.out.println("初始顶点 = " + closeVertex[0].vertex);
            for(int i = 1; i < n; i++){
                System.out.print("顶点 = " + closeVertex[i].vertex);
                System.out.println(" 边的权值 = " + closeVertex[i].weight);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
