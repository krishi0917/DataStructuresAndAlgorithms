package LeetcodePrograms;

import java.util.ArrayDeque;
import java.util.Deque;

public class Stringtoip {
//	The IP address is “base 256”, to convert 192.168.1.2 to decimal (base 10) the formula is:
//
//		192 x (256)^3 + 168 x (256)^2 + 1 x (256)^1 + 2 (256)^0 = ?
//		3221225472 + 11010048 + 256 + 2 = 3232235778
		public static long ipToLong(String ipAddress) {

		String[] ipAddressInArray = ipAddress.split(".");

		long result = 0;
		for (int i = 0; i < ipAddressInArray.length; i++) {

			int power = 3 - i;
			int ip = Integer.parseInt(ipAddressInArray[i]);
			result += ip * Math.pow(256, power);

		}

		return result;
		  }




	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	long x = ipToLong("111.11.11.111");
	//	System.out.println(x);

//		int y[][]  = {{1,2},{3,4}};
//		Deque<Integer> S = new ArrayDeque<>();
//
//		for(int i = 0 ;i < y.length;i++)
//			System.out.print(y[i]) ;

		int n =2 ;
		while (n-->0)
			System.out.println(n);
	}

}
