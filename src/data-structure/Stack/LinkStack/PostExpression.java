package LinkStack;
/*表达式计算问题
    1.把中缀表达式变换成相应的后缀表达式；
    2.根据后缀表达式计算表达式的值
*/

public class PostExpression {
    public static void postExp(LinkStack s) throws Exception{
        char ch;
        int x1,x2,b = 0;

        System.out.println("输入后缀表达式（表达式以#符号结束）：");
        while((ch = (char)(b = System.in.read())) != '#'){
            if(Character.isDigit(ch))
                s.push(new Integer(Character.toString(ch)));
            else{
                x2 = ((Integer)s.pop()).intValue();
                x1 = ((Integer)s.pop()).intValue();
                switch(ch){
                    case '+': x1 += x2;break;
                    case '-': x1 -= x2;break;
                    case '*': x1 *= x2;break;
                    case '/':
                        if (x2 == 0)
                            throw new Exception("除数为0，错！");
                        else{
                            x1 /= x2;
                            break;
                        }
                }
                s.push(new Integer(x1));
            }
        }
        System.out.println("后缀表达式计算结果为：" + s.pop());
    }

    public static void main(String[] args) throws Exception{
        LinkStack myStack = new LinkStack();
        try {
            postExp(myStack);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
