package LeetcodePrograms.InterviewQuestions;

import java.util.*;

public class MicrosoftTech {
    public static List<String> findTruncatedResult(List<String> input) {
        String stopToken = "<END>";
        StringBuilder sb = new StringBuilder(); // Efficient string building
        StringBuilder tempBuffer = new StringBuilder(); // Buffer to track partial matches of stopToken
        boolean stopFlag = false;
        int stopIndex = 0;

        for (String chunk : input) {
            if (stopFlag) break; // Stop processing once the stop token is found

            for (char c : chunk.toCharArray()) {
                if (c == stopToken.charAt(stopIndex)) {
                    tempBuffer.append(c);
                    stopIndex++;

                    if (stopIndex == stopToken.length()) {
                        stopFlag = true; // Entire stop token matched, stop further processing
                        break;
                    }
                } else {
                    if (stopIndex > 0) {
                        sb.append(tempBuffer); // Append previously matched part if interrupted
                        tempBuffer.setLength(0); // Reset temp buffer
                    }
                    sb.append(c);
                    stopIndex = 0; // Reset stop index
                }
            }
        }
        System.out.println("checking " + sb.toString());
        return Collections.singletonList(sb.toString()); // Convert final result into a list
    }

    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input.add("Hello there.");
        input.add("I'm doing great today.<END>Thanks for asking.");
        input.add("How are you doing?");

        input.add("I'm doing great today:<E");
        input.add("ND>, How are you doing?");

        System.out.println(findTruncatedResult(input));
        // Expected Output: ["Hello there.", "I'm doing great today."]
    }
}
