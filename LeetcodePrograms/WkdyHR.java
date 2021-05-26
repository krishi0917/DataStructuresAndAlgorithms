package LeetcodePrograms;

import java.util.HashMap;


public class WkdyHR {

    public static String compare(String firstWord, String secondWord , String mainWord){
        int i=0;
        int rank = 1;
        char[] mainWord1 = mainWord.toCharArray();
        char[] firstWord1 = firstWord.toCharArray();
        char[] secondWord1 = secondWord.toCharArray();
        HashMap<Character , Integer > ranking = new HashMap<>();
//        'A' < 'a'
//         65 < 97
        for(int k = 65 ; k <= 90 ; k++  ) {
            ranking.put((char)k, rank);
            rank++;
            ranking.put((char)(k + 32) , rank);
            rank++;
        }

        ranking.put(' ' , 0);
        while(i < mainWord.length() ){
            if((int)mainWord1[i] == (int)firstWord1[i] ) {
                i++;

                if(mainWord.length() == firstWord.length() )
                    return "inside";
                continue;
            }
            else if( ranking.get(   mainWord1[i]) <    ranking.get(firstWord1[i]) )
                return "before";

            else if (   ranking.get(mainWord1[i]) >  ranking.get((firstWord1[i]) )){
                    int j=0;
                    while (j < mainWord.length()){
                        if (    (int) mainWord1[j] ==(int) secondWord1[j]) {
                            j++;
                            if(mainWord.length() == firstWord.length() )
                                return "inside";
                            continue;
                        }
                        if ( ranking.get(   mainWord1[j]) >   ranking.get(secondWord1[j]))
                            return "after";
                        else
                            return "inside";

                    }

            }

                return "inside";

        }
        return "before";
    }





    public static void main(String []args){
        String firstWord = "Apple";
        String secondWord = "Pear";
            System.out.println(compare(firstWord , secondWord , "Aa"));
        System.out.println(compare(firstWord , secondWord , "Aq"));
        System.out.println(compare(firstWord , secondWord , "App")); //null
        System.out.println(compare(firstWord , secondWord , "Apple"));


//        String fw = "EG";
//        String sw = "KtP";
//        System.out.println(compare(fw , sw , "Rys"));
//        System.out.println(compare(fw , sw , "hMo"));  //should be inside as
//        System.out.println(compare(fw , sw , "dLQ"));
//        System.out.println(compare(fw , sw , "JrF"));





    }
}
