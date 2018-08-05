package LinkStack;

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
