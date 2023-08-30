package LeetcodePrograms;
// use this program for the interview
import java.util.ArrayList;
import java.util.List;

public class TextJustification3  {
    public static List<String> fullJustify(String text, int maxWidth) {
        String[]words = text.split(" ");
        List<String> res = new ArrayList<>();
        int n = words.length;
        int index = 0;

        while (index < n) {
            int len = 0;  //len is total characters without spaces. used to find spaces in the following else part
            int totalChars = words[index].length();// total characters in that particular word and in the future extra spaces will also be added in this
            // total chars is also used to find if it increases the max width
            len+=words[index].length();
            int last = index + 1; //this last will be used in the else part while forming the whole sentence in that particular line. We will iterate till last
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
                sb.deleteCharAt(sb.length() - 1); // extra space in the end is added in the above for loop
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
                        // for loop will end before even printing the list word in the first line. In our case, its "an"
                        // for ex when an is printed for the line "this is an", no extra spaces needs to be put after an and hence we break the loop
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
        String words2 = "This is an example of text jistify qwerty";
       // String []words1 = {"What","must","be","acknowledgment","shall","be"};
        int maxWidth = 16;
        System.out.println(fullJustify(words2,maxWidth));
    }

}
