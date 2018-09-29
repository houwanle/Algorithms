import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Test1 {
/**  Java 实现大数次方、阶乘    
 *   @param args  
 *   
 *   JAVA中有两个类BigInteger和BigDecimal分别表示大整数类和大浮点数类，
 *   至于两个类的对象能表示最大范围不清楚，理论上能够表示无线大的数，只要计算机内存足够大。   
 */   
	public static void main(String[] args) {         
		Scanner sc = new Scanner(System.in);
		// 计算 1949的2016次方        
		BigDecimal bd = new BigDecimal(1949);        
		bd = bd.pow(2016); // 1949^2016        
		System.out.println(bd);          
		
		// 计算2016的阶乘 result=2016！        
		BigInteger n = BigInteger.valueOf(2016);        
		BigInteger result = BigInteger.ONE;        
		for (BigInteger i = BigInteger.ONE; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {            
			result = result.multiply(i);        
		}        
		
		System.out.println(result);    
	}
}
