package LeetcodePrograms;

import java.util.HashMap;

/**
 * Created by rkhurana on 1/26/19.
 */
//Remove Alternate Duplicate characters from a char array you have to do it in Place.Like keeping only the odd occurences of each character.
//Example: Input: “you got beautiful eyes”
//        Output: ”you gtbeaiful es”
//        Allowed Time Complexity was O(n) and Space Complexity was O(1)

public class RemoveInplaceOddOccuranceDuplicates {

    public static char[] removeDuplicates(char[] ch) {
        int j=0;
        int countActualElements=0;
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0 ; i < ch.length ; i++){
            if(map.containsKey(ch[i])){
                map.remove(ch[i]);
                j++;
            }
            else{
                map.put(ch[i],1);
                ch[i-j] = ch[i];
                countActualElements++;
            }
        }

        while(countActualElements < ch.length){
            ch[countActualElements]=' ';
            countActualElements++;
        }
        return ch;
    }

    public static void main(String []args){
        char[]ch = {'y', 'o' , 'u',' ','g','o' , 't',' ','b' ,'e','a','u','t','i','f', 'u','l',' ','e','y','e','s'};
        System.out.println(removeDuplicates(ch));
    }
}

