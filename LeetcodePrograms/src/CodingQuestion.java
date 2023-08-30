package LeetcodePrograms.src;

public class CodingQuestion {
    public static void main(String arg[]){
        String number ="1211";
        StringBuffer st = new StringBuffer();
        for (int i=1;i<number.length();){
            char co = (number.charAt(i-1));
            int count = Character.getNumericValue(co);
            char letter = number.charAt(i);
            for (int j=0;j<count;j++){
                st.append(letter);
            }
            i=i+2;
        }
        System.out.println(st);
    }
}
