package LeetcodePrograms;

/**
 * Created by rkhurana on 12/6/18.
 */
public class IsMagicNumber {



    public static boolean isMagicNumber(int num) {
        if(num ==1 || num == -1)
            return true;
        if(num/10 == 0 )
            return false;
        int sumOfDigits = 0;

        while(num!=0){
            sumOfDigits+=(num%10) * (num%10);
            num = num/10;
        }
        return isMagicNumber(sumOfDigits);
    }


    public static void main(String []args){
        IsMagicNumber o = new IsMagicNumber();
        System.out.print(o.isMagicNumber(1234));
    }

}
