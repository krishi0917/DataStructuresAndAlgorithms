package LeetcodePrograms;

/**
 * Created by rkhurana on 10/24/18.
 */
public class dummy {
    public static void checkMethod(String str){
        int i=0;
//        int x = Character.getNumericValue(str.charAt(i));
        //int y = Character.getNumericValue(str.charAt('5'));
  //      int z = str.charAt('5') ;
        int x = str.charAt(i) - '0';
        char ch = str.charAt(i);
        int y = Character.getNumericValue('A');
        System.out.println(x + "  "  + y+"  " );
    }
    public static void main(String [] args){
        checkMethod("123");
    }
}
