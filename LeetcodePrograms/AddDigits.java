package LeetcodePrograms;

/**
 * Created by rkhurana on 7/25/18.
 */
public class AddDigits {
    public static int addDigits(int num) {
        while (num > 9) {
            int temp = 0;
            while (num > 0) {
                temp += num % 10;
                num /= 10;
            }
            num = temp;
        }
        return num;
    }

    public static void main(String [] args){
        System.out.println(addDigits(258));
    }
}
