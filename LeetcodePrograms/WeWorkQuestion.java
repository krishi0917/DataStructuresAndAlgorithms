package LeetcodePrograms;
//  decode("i20e") -> 20
//  decode("i5ei-30e") -> 5, -30
//  decode("3:foo") -> "foo"
//  decode("3:foo2:abi5e") -> "foo", "ab", 5
//  decode("li20e3:fooe") [ 20, "foo" ]

import java.util.ArrayList;
import java.util.List;

public class WeWorkQuestion {

    public static void decode(String s){
        char[] ch =  s.toCharArray();
        int i=0, chLength = ch.length ;
        List<String> list = new ArrayList<>();
        boolean listFlag = false;
        StringBuilder sbList = new StringBuilder();
        while(i < chLength){
            if (i!=0)
                System.out.print(",");
            if(s.charAt(i) == 'l') {
                list = new ArrayList<>();
                listFlag = true;
                i++;
            }
            if( s.charAt(i)== 'i') {
                i++;
                while (s.charAt(i) != 'e'){
                    System.out.print(s.charAt(i));
                    if (listFlag) {
                        sbList.append(s.charAt(i));
                    }
                    i++;
                }
                i++;
                if(listFlag)
                    list.add(sbList.toString());
            }
            else {
                Integer curLength = 0;
                StringBuilder sb = new StringBuilder();
                while (Character.isDigit(s.charAt(i))) {
                    sb.append(s.charAt(i));
                    i++;
                }
                if (sb.length() > 0) {
                    curLength = Integer.parseInt(sb.toString());
                }
                StringBuilder sb2 = new StringBuilder();
                if (s.charAt(i) == ':') {
                    int j = curLength + i +1;
                    i++;
                    while (i < j) {
                        sb2.append(s.charAt(i));
                        i++;
                    }
                    if(listFlag) {
                        list.add(sb2.toString());

                    }
                    if(i < ch.length && s.charAt(i) == 'e') {
                        listFlag = false;
                        i++;
                    }
                }
                System.out.print(sb2);
            }
        }
        if(listFlag){
                System.out.println(list);
        }
    }
    public static void main(String[]args){
        decode("i20e");
        System.out.println();
        decode("i5ei-30e");//-> 5, -30
        System.out.println();
        decode("3:foo");// -> "foo"
        System.out.println();
         decode("3:foo2:abi5e");// -> "foo", "ab", 5
        System.out.println();
        decode("li20e3:fooe");


    }
}
