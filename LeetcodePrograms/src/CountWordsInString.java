package LeetcodePrograms.src;

import java.util.ArrayList;


/**
 * @author Rishi Khurana
 */
public class CountWordsInString {
    public static int countWords(String word) {
        if (word == null || word.isEmpty()) {
            return 0;
        }
        int wordCount = 0;
        boolean isWord = false;
        int endOfLine = word.length() - 1;
        char[] characters = word.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if (Character.isLetter(characters[i]) && i != endOfLine) {
                isWord = true;
            } else if (!Character.isLetter(characters[i]) && isWord) {
                wordCount++;
                isWord = false;
            } else if (Character.isLetter(characters[i]) && i == endOfLine) {
                wordCount++;
            }
        }
        return wordCount;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
//        strings.add("hello");
//        strings.add("hello world!");
//        strings.add("Hello, World!");
//        strings.add(" hello hello ");
        strings.add("   hello   world!   ");

        for (String string : strings) {
            System.out.println(countWords(string));
        }

    }
}
