/*n阶对称矩阵类*/
public class SynmeMatrix {
    double a[];		//矩阵元素
    int n;			//阶数
    int m;			//一位数组个数

    SynmeMatrix(int n){//构造函数
        m = n * (n + 1)/2;//计算一维数组个数
        a = new double[m];//创建一维数组
        this.n = n;		//保存阶数
    }

    public void evaluateMatrix(double[][] b){//矩阵赋值1
        int k = 0;
        int i ,j;
        for(i = 0; i < n; i++){
            for(j = 0; j < n; j++)
                if(i >= j)
                    a[k++] = b[i][j];//只保存下三角元素
        }
    }

    public void evaluateMatrix(double[] b){//矩阵赋值2
        for(int k = 0; k < m; k++)
            a[k] = b[k];
    }

    public SynmeMatrix add(SynmeMatrix myB){//矩阵加
        SynmeMatrix t = new SynmeMatrix(n);
        int i,j,k;
        for(i = 1; i <= n; i++){
            for(j = 1; j <= n; j++){
                if(i >= j)
                    k = i * (i-1)/2 + j - 1;
                else
                    k = j * (j - 1)/2 + i - 1;
                t.a[k] = a[k] + myB.a[k];
            }
        }
        return t;
    }

    public void print(){//输出矩阵元素
        int i,j,k;
        for(i = 1; i <= n; i++){
            for(j = 1; j <= n; j++){
                if(i >= j)
                    k = i * (i - 1)/2 + j - 1;
                else
                    k = j * (j - 1)/2 + i - 1;
                System.out.print(" " + a[k]);
            }
            System.out.println();
        }
    }
}

================================================分割线=================================================

public class TestSynmeMatrix {
    public static void main(String[] args) {
        SynmeMatrix matrixA = new SynmeMatrix(3);
        SynmeMatrix matrixB = new SynmeMatrix(3);
        SynmeMatrix matrixC;

        double[][] a = {{1,0,0},{2,3,0},{4,5,6}};
        double[] b = {1,2,3,4,5,6};

        matrixA.evaluateMatrix(a);//测试构造函数1
        matrixB.evaluateMatrix(b);//测试构造函数2

        System.out.println("matrixA矩阵为：");
        matrixA.print();
        System.out.println("matrixB矩阵为：");
        matrixB.print();
        matrixC = matrixA.add(matrixB);//测试矩阵加
        System.out.println("matrixC矩阵为：");
        matrixC.print();
    }
}
