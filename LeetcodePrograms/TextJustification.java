package LeetcodePrograms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rkhurana on 9/26/18.
 */
public class TextJustification {
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList();
        int i=0;
        while(i<words.length)
        {
            int end = i;
            int lenLine=0;
            while(end<words.length && lenLine+words[end].length() <= maxWidth){
                lenLine += words[end].length()+1; // 1 for space after word, we are adding here for last word as well so we need to remove it later
                end++;
            }
            boolean isLast = end == words.length; //we did not reach till the end
            int countWords = end-i; // since end is not inclusive
            StringBuilder line=null;
            if(countWords==1){
                line=new StringBuilder(words[i]); // if only one word, add to line and use correctLine for spaces
            }else{
                int noOfExtraSpaces = maxWidth-lenLine+1;
                int spaces = isLast ? 1 : 1+noOfExtraSpaces/(countWords-1); //spaces that are distributed equally  , +1  is for normal space
                int extraS = isLast ? 0 : noOfExtraSpaces%(countWords-1); //if equal distribution not possible then find remaing
                line = createLine(i, end, spaces, extraS, words);
            }

            result.add(correctLine(line,maxWidth));
            i=end; // imp to make i point to end pointer
        }

        return result;
    }

    private static String correctLine(StringBuilder line, int maxWidth)
    {
        while(line.length()<maxWidth){  //add spaces to single word/last line
            line.append(" ");
        }

        while(line.length()>maxWidth){     // delete the extra appended characters
            line.deleteCharAt(line.length()-1);
        }

        return line.toString();
    }

    private static StringBuilder createLine(int start, int end, int space, int extraS, String[] words){
        StringBuilder line = new StringBuilder();

        for(int i=start;i<end;i++)
        {
            line.append(words[i]);
            int ws=0;
            while(ws<space){ //after every word add space (for last word also space gets appended which will be removed in correct word
                line.append(" ");
                ws++;
            }
            if(extraS>0){
                line.append(" ");
                extraS--;
            }
        }

        return line;
    }

    public static void main(String [] args){
        String [] words = {"This", "is", "an", "example", "of", "text", "justification."};
        String []words1 = {"What","must","be","acknowledgment","shall","be"};
        List<String> wordJustification = fullJustify(words1,16);
        System.out.println(wordJustification);

    }
}
