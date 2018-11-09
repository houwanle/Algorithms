package SeqPQueue;
/*
    设计一个程序模仿操作系统的进程管理问题。进程服务按优先级高的
    先服务、优先级相同的先到先服务的原则管理。

    模仿数据包括两部分：进程编号和优先级。一个模仿数据集合如下，
    其中第一列表示进程编号第二列表示进程优先级：
    1 30
    2 20
    3 40
    4 20
    5 0

 */

public class ProcessManagement {
    public static void main(String[] args) throws  Exception{
        SeqPQueue myQueue = new SeqPQueue();
        Element temp;

        myQueue.append(new Element(new Integer(1), 30));
        myQueue.append(new Element(new Integer(2), 20));
        myQueue.append(new Element(new Integer(3), 40));
        myQueue.append(new Element(new Integer(4), 20));
        myQueue.append(new Element(new Integer(5), 0));

        System.out.println("进程号 优先级");
        while(myQueue.notEmpty()){
            temp = myQueue.delete();
            System.out.print(temp.getElem() + " ");
            System.out.println(temp.getPriority());
        }
    }
}
