package LeetcodePrograms;

/**
 * Created by rkhurana on 1/30/19.
 */
public class ImplementPow {
    public static double myPow(double x, int n) {
        double res=1;
        while(n!=0)
        {
            if(n%2==0) {
                x=x*x;
                n/=2;
            }
            else {
                if(n>0)
                {
                    res*=x;
                    n--;
                }
                else
                {
                    res/=x;
                    n++;
                }
            }
        }
        return res;
    }

    public static boolean isPowerOfTwo(int n) {
        if (n == 0)
            return false;
        while (n % 2 == 0)
            n /= 2;
        return (n == 1);
    }
    public static void main(String []args){
//        System.out.println(isPowerOfTwo(8));
        System.out.println(myPow(5,3));
//        System.out.println(myPow(7,2));
//        System.out.println(myPow(9,3));
    }
}
