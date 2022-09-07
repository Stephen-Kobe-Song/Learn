# 蓝桥杯java基础题十七题

1、第一题输出a+b

~~~java
package lanQiaoTrain;
import java.util.*;

public class oneTrain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        Integer a = sc.nextInt();
        Integer b = sc.nextInt();
        System.out.println(a + b);
	}
}
~~~

2、给定一个长度为n的数列，将这个数列按从小到大的顺序排列。1<=n<=200 输入格式 第一行为一个整数n。第二行包含n个整数，为待排序的数，每个整数的绝对值小于10000。 输出格式 输出一行，按从小到大的顺序输出排序后的数列；

~~~java
public class twoTrain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		获得n
		int num = sc.nextInt();
		if (num >=1 && num <= 200) {
//		新建一个大小为num的数组
		int[] arr =new int[num];
		sc.nextLine();
		String line = sc.nextLine();
//		分隔
		String[] str1 = line.split(" ");
//		数据存入数组
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(str1[i]);
		}
//		工具类排序
		Arrays.sort(arr);
//		打印输出
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		}
	}
}

~~~

3、给定n个十六进制正整数，输出它们对应的八进制数。输入格式：输入的第一行为一个正整数n （1<=n<=10）。接下来n行，每行一个由0~9、大写字母A~F组成的字符串，表示要转换的十六进制正整数，每个十六进制数长度不超过100000。

~~~java
public class threeTrain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//获得输入16进制数的个数
		int count = sc.nextInt();
		if (count >= 1 && count <= 10) {
			// 创建一个数组
			int[] arr = new int[count];
			// 对每一个数进行操作
			for (int i = 0; i < count; i++) {
				// 获得输入的16进制数字符串
				String strnum = sc.next();
				// 转换为16进制数
				Integer hexnum = Integer.valueOf(strnum,16);
				// 转换为八进制数
				String num = Integer.toOctalString(hexnum);
				// 转换为Int类型
				int result = Integer.parseInt(num);
				// 存入数组
				arr[i] = result;
			}
			for (int i = 0; i < arr.length; i++) {
				System.out.println(arr[i] + " ");
			}
		}
	}
}

~~~

4、从键盘输入一个不超过8位的正的十六进制数字符串，将它转换为正的十进制数后输出。注：十六进制数中的10~15分别用大写的英文字母A、B、C、D、E、F表示。

~~~java
public class fourTrain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String num = sc.nextLine();
		// 输入一个十六进制数num。转为为一个long型的十进制数。
		long intnum = Long.parseLong(num,16);
		System.out.println(intnum);
	}
}
~~~

5、十六进制数是在程序设计时经常要使用到的一种整数的表示方式。它有0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F共16个符号，分别表示十进制数的0至15。十六进制的计数方法是满16进1，所以十进制数16在十六进制中是10，而十进制的17在十六进制中是11，以此类推，十进制的30在十六进制中是1E。给出一个非负整数，将它表示成十六进制的形式。

~~~java
public class fiveTrain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		if (num > 0) {
			String hexString = Integer.toHexString(num);
			int result = Integer.parseInt(hexString);
			System.out.println(result);
		}
	}
}
~~~

6、特殊回文数：123321是一个非常特殊的数，它从左边读和从右边读是一样的。输入一个正整数n， 编程求所有这样的五位和六位十进制数，满足各位数字之和等于n 。

~~~java
public class sixTrain {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int num = sc.nextInt();
	sc.close();
	for (int i = 10000; i < 1000000; i++) {
		if (isHuiWen(i)) {
			// 五位数
			if (gs(i) == 5) {
				if (i/10000 * 2 + i/1000%10 *2 +i/100%10 == num ) {
					System.out.println(i);
				}
			}
			if (gs(i) == 6) {
				if (i/100000 *2 + i/10000%10 *2 + i/1000%10 * 2 == num) {
					System.out.println(i);
				}
			}
		}
	}
	
	}
// 判断是否为回文数
public static boolean isHuiWen(int a) {
	String str1 = String.valueOf(a);
	int i = 0;
	int j = str1.length() - 1;
	while (i<j) {
		if (str1.charAt(i++) != str1.charAt(j--)) {
			return false;
		}
	}
	return true;
}

// 判断位数
public static int gs(int n) {
	String str = String.valueOf(n);
	int g = str.length();
	return g;
}
}
~~~

7、问题描述：1221是一个非常特殊的数，它从左边读和从右边读是一样的，编程求所有这样的四位十进制数。
输出格式按从小到大的顺序输出满足条件的四位十进制数。

~~~java
public class sevenTrain {
public static void main(String[] args) {
	for (int i = 1000; i < 10000; i++) {
		if(isHuiwen(i)) {
			System.out.println(i);
		}
	}
}
public static boolean isHuiwen(int n) {
	String string = String.valueOf(n);
	int i = 0;
	int length = string.length() - 1;
	while (i<length) {
		if (string.charAt(i++) != string.charAt(length--)) {
		return false;
	}
		
	}
	return true;
}
}
~~~

8、问题描述：153是一个非常特殊的数，它等于它的每位数字的立方和，即153=1*1*1+5*5*5+3*3*3。
编程求所有满足这种条件的三位十进制数。

~~~java
public class eighthTrain {
	public static void main(String[] args) {
		for (int i = 100; i < 1000; i++) {
			int a = i, sum = 0;
			while (a !=0) {
				sum += (int) Math.pow(a%10, 3);
				a/=10;
			}
			if (sum == i) {
				System.out.println(i);
			}
		}
	}
}
~~~

9、杨辉三角形又称Pascal三角形，它的第i+1行是(a+b)i的展开式的系数。它的一个重要性质是：三角形中的每个数字等于它两肩上的数字相加。

~~~java
public class nigthTrain {
	public static void main(String[] args) {
		int n,i,j;
		int arr[][] =new int[34][34];
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		sc.close();
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				// 第一列填值
				arr[i][0] = 1;
				// 对角线填值
				if (i == j) {
					arr[i][j] = 1;
				}
			}
		}
		// 求和,每个元素的值等于这个元素上一行上一列的值加上上一行本列元素的值
		for (i = 2; i < n; i++) {
			for (j = 1; j < n; j++) {
				arr[i][j] = arr[i-1][j-1] + arr[i-1][j];
			}
		}
		// 输出
		for (i = 0;  i< n; i++) {
			for (j = 0; j < n; j++) {
				// 只输出元素不为零的值
				if (arr[i][j] != 0) {
					System.out.print(arr[i][j] + " ");
				}
			}
			System.out.println();
		}
	}
}
~~~

10、 给出一个包含 n个整数的数列，问整数a在数列中的第一次出现是第几个。

~~~java
public class tenTrain {
	/* 给出一个包含
	 * n个整数的数列，问整数a在数列中的第一次出现是第几个。 */
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int index = 0;
	int n = sc.nextInt();
	int[] arr = new int[n];
	sc.nextLine();
	String string = sc.nextLine();
	int findnum = sc.nextInt();
	sc.close();
	String[] line = string.split(" ");
	for (int i = 0; i < arr.length; i++) {
		arr[i] = Integer.parseInt(line[i]);
	}
	for (int k = 0; k < arr.length; k++) {
		if (arr[k] == findnum) {
			index =k + 1;
			break;
		}
	}
	if (index > 0) {
		System.out.println(index);
	}else {
		System.out.println(-1);
	}
}
}
~~~

11、问题描述：
给出n个数，找出这n个数的最大值，最小值，和。*/输入格式：
第一行为整数n，表示数的个数。第二行有n个数，为给定的n个数，每个数的绝对值都小于10000。输出格式：
输出三行，每行一个整数。第一行表示这些数中的最大值，第二行表示这些数中的最小值，第三行表示这些数的和。

~~~java
public class eleventhTrain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		int[] arr = new int[n];
		String line = sc.nextLine();
		sc.close();
		
		String[] strings = line.split(" ");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(strings[i]) ;
		}
		int max = arr[0],min = arr[0],sum=0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (arr[i] > max ) {
				max = arr[i];
			}
			if (arr[i] < min) {
				min = arr[i];
			}
		}
		System.out.println(max);
		System.out.println(min);
		System.out.println(sum);
	}
}

~~~

12、问题描述：
利用字母可以组成一些美丽的图形，下面给出了一个例子：

ABCDEFG

BABCDEF

CBABCDE

DCBABCD

EDCBABC

这是一个5行7列的图形，请找出这个图形的规律，并输出一个n行m列的图形。

~~~java
public class twelvthTrain {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int n = sc.nextInt();
	int m = sc.nextInt();
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			System.out.print((char)('A' + Math.abs(j -i))); // 强制转换成字符型
		}
		System.out.println();
	}
			
}
}

~~~

13、对于长度为5位的一个01串，每一位都可能是0或1，一共有32种可能。它们的前几个是：

00000

00001

00010

00011

00100

请按从小到大的顺序输出这32种01串。

~~~java
public class ThirteenthTrain {
public static void main(String[] args) {
	for (int i = 0; i < 2; i++) {
		for (int j = 0; j < 2; j++) {
			for (int a = 0; a < 2; a++) {
				for (int b = 0; b < 2; b++) {
					for (int c = 0; c < 2; c++) {
						System.out.print(i);
						System.out.print(j);
						System.out.print(a);
						System.out.print(b);
						System.out.print(c);
						System.out.println();
					}
				}
			}
		}
	}
	
}
}
~~~

14、给定一个年份，判断这一年是不是闰年。当以下情况之一满足时，这一年是闰年：

1. 年份是4的倍数而不是100的倍数；

2. 年份是400的倍数。

其他的年份都不是闰年。

~~~java
public class FourteenthTrain {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int year = sc.nextInt();
	if (year%4 ==0 && year%100 != 0) {
		System.out.println("yes");
	}
	else if (year%400 ==0) {
		System.out.println("yes");
	}
	else {
		System.out.println("no");
	}
}
}
~~~

15、输出一行，包含一个实数，四舍五入保留小数点后7位，表示圆的面积。

~~~java
public class fivthTrain {
public static void main(String[] args) {
	final double PI = 3.14159265358979323;
	Scanner sc = new Scanner(System.in);
	double R = sc.nextDouble();
	double s = PI * Math.pow(R, 2);
	System.out.println(String.format("%.7f", s));
}
}
~~~

16、Fibonacci数列的递推公式为：Fn=Fn-1+Fn-2，其中F1=F2=1。当n比较大时，Fn也非常大，现在我们想知道，Fn除以10007的余数是多少。

~~~java
public class SixthTrain {
	static int m = 10007;
public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	int F[] = new int[n+2];
	F[1]=1;
	F[2]=1;
	if(n>2){
	for(int i=3;i<=n;i++){
	F[i]=(F[i-1]+F[i-2])%10007;//用来保存余数
	}
}
	System.out.println(F[n]);
}
}
~~~

17、问题描述：

给定圆的半径r，求圆的面积。

输入格式：

输入包含一个整数r，表示圆的半径。

~~~java
public class yuanarea {
	public static double PI= 3.1415926;
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	double r = sc.nextDouble();
	double area = PI*r *r;
	System.out.println(String.format("%.7f", area));
}
}
~~~

18、问题描述：

求1+2+3+...+n的值。

输入格式：

输入包括一个整数n。

输出格式：

输出一行，包括一个整数，表示1+2+3+...+n的值。

~~~java
public class seventeenthTrain {


public static void main(String[] args) {
	
	Scanner sc = new Scanner(System.in);
	int n = sc.nextInt();
	int rs = n + (n *(n-1))/2;
	System.out.println(rs);
}
}
~~~

