/*迷宫——回溯法*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

class InterSection {//路口类
    int left;	//向左方向
    int forward;//向前方向
    int right;	//向右方向
}

public class Maze{
    int mazeSize;		//路口个数
    InterSection[] intSec;//路口集合
    int exit;			//出口

    Maze(String fileName) {//构造函数
        String line;
        Integer temp;

        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            line = in.readLine();//读文件第一行
            mazeSize = Integer.parseInt(line.trim());//去空格并变换类型

            intSec = new InterSection[mazeSize + 1];//创建对象

            for(int i = 1; i <= mazeSize; i++){//读文件的后续行
                line = in.readLine();//读文件的当前行
                StringTokenizer tokenizer = new StringTokenizer(line);//把当前行数值变换成StringTokenizer对象
                InterSection curr = new InterSection();//创建对象
                curr.left = Integer.parseInt(tokenizer.nextToken());//截取tokenizer对象的第一部分并变换成int类型
                curr.forward = Integer.parseInt(tokenizer.nextToken());//截取tokenizer对象的第二部分并变换成int类型
                curr.right = Integer.parseInt(tokenizer.nextToken());//截取tokenizer对象的第三部分并变换成int类型

                intSec[i] = curr;//对象赋值
            }

            exit = Integer.parseInt(in.readLine());//读文件的最末行
            in.close();//关闭
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean travMaze(int intSecValue){//搜索
        //用回溯法搜索迷宫的所有分支，intSecValue为当前所处路口号
        //当搜索到出口时函数返回1，否则返回0
        if(intSecValue > 0){
            if(intSecValue == exit){//搜索到出口
                System.out.print(intSecValue + "<==");//输出出口号
                return true;
            }

            //向当前路口的左分支搜索
            else if(travMaze(intSec[intSecValue].left)){
                System.out.print(intSecValue + "<==");
                return true;
            }

            //向当前路口的前分支探索
            else if(travMaze(intSec[intSecValue].forward)){
                System.out.print(intSecValue + "<==");
                return true;
            }

            //向当前路口的右分支探索
            else if(travMaze(intSec[intSecValue].right)){
                System.out.print(intSecValue + "<==");
                return true;
            }
        }
        return false;//搜索出口失败

    }
}

==================================================分割线===================================================

public class TestMaze {
    public static void main(String[] args) {
        String fileName = "Maze1.txt";
        Maze m = new Maze(fileName);
        int start = 1;

        if(m.travMaze(start)){
            System.out.println();
            System.out.println("此迷宫的一条通路如上所示");
        }
        else {
            System.out.println("此迷宫无通路出来！");
        }
    }
}

==================================================分割线===================================================
Maze1.txt内容如下：
6
0 2 0
3 5 6
0 0 4
0 0 0
0 0 0
7 0 0
7
