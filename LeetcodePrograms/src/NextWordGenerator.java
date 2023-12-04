package LeetcodePrograms.src;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class NextWordGenerator {

    public static void main(String[] args) {
        String inputFile = "input.txt";

        try {
            String nextWord = generateNextWord(inputFile, "currentWord"); // Replace "currentWord" with the word for which you want to find the next word
            System.out.println("Alphabetically next word: " + nextWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateNextWord(String inputFile, String targetWord) throws IOException {
        TreeSet<String> words = readWordsFromFile(inputFile);

        // Finding the next word
        String nextWord = words.higher(targetWord);

        if (nextWord == null) {
            throw new IllegalArgumentException("Word not found or it is the last word");
        }

        return nextWord;
    }

    public static TreeSet<String> readWordsFromFile(String fileName) throws IOException {
        TreeSet<String> words = new TreeSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        }
        return words;
    }
}
