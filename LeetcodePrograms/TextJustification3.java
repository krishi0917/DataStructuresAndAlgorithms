package LeetcodePrograms;

import java.util.ArrayList;
import java.util.List;

public class TextJustification3  {
    public static List<String> fullJustify(String text, int maxWidth) {
        String[]words = text.split(" ");
        List<String> res = new ArrayList<>();
        int n = words.length;
        int index = 0;

        while (index < n) {
            int len = 0; // used for the else part when you have to write the words in a line
            // to make sure the number of words are there without spaces
            int totalChars = words[index].length(); // used to find if it increases the max width
            len+=words[index].length();
            int last = index + 1;
            while (last < n) {  /// total number of words in this line
                if (totalChars + 1 + words[last].length() > maxWidth)
                    break;
                totalChars += 1 + words[last].length(); // words including the space as well
                len +=words[last].length();
                last++;
            }

            int gaps = last - index - 1;
            StringBuilder sb = new StringBuilder();
            if (last == n || gaps == 0) {    // if there is only word or its the last line
                for (int i = index; i < last; i++) {
                    sb.append(words[i]);
                    sb.append(' ');
                }
                sb.deleteCharAt(sb.length() - 1); // extra character that is added just before this
                while (sb.length() < maxWidth) { // add the remaining characters with spaces
                    sb.append(' ');
                }
            } else {
                int spaces = (maxWidth - len) / gaps;
                int rest = (maxWidth - len) % gaps;
                for(int i = index ; i < last  ; i++){
                    sb.append(words[i]);
                    int s=0;
                    if(i == last - 1) // this condition will come when you have printed the last word in the line
                        // we cannot do the last - 1 in the for loop because in that condition last word ie "an" in
                        // our case wont be printed.
                        break;
                    while(s++ < spaces){
                        sb.append(" ");
                    }
                    if(rest-- >0){
                        sb.append(" ");
                    }
                }
            }
            res.add(sb.toString());
            index = last;
        }
        return res;
    }

    public static void main(String []args){
        String words = "winter is coming.";
       // String []words1 = {"What","must","be","acknowledgment","shall","be"};
        int maxWidth = 18;
        System.out.println(fullJustify(words,maxWidth));
    }

}
