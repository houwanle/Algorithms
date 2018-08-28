/*汉诺塔问题*/
public class Towers {
    public static void main(String[] args){
        towers(4, 'A', 'C', 'B');
    }

    public static void towers(int n, char fromPeg, char toPeg, char auxPeg){
        //把n个圆盘从fromPeg借助auxPeg移至toPeg
        if(n == 1){//递归出口
            System.out.println(" move disk 1 from peg " + fromPeg + " to peg " + toPeg);
            return;
        }
        //把n-1个圆盘从fromPeg借助toPeg移至auxPeg
        towers(n-1, fromPeg, auxPeg, toPeg);

        //把圆盘n由fromPeg直接移至toPeg
        System.out.println(" move disk " + n + " from peg " + fromPeg + " to peg " + toPeg);

        //把n-1个圆盘从auxPeg借助fromPeg移至toPeg
        towers(n-1,auxPeg,toPeg,fromPeg);
    }
}
