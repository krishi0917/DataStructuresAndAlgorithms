package LeetcodePrograms.InterviewQuestions;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;
public class TwilioHackerRank {
    class Result {

        /*
         * Complete the 'calculateTotalMessageCost' function below.
         *
         * The function is expected to return a DOUBLE.
         * The function accepts STRING_ARRAY messages as parameter.
         */

        public static double calculateTotalMessageCost(List<String> messages) {
            Stream<Character> s = Stream.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ',', '.', ' ');

            Set<Character> hs = s.collect(Collectors.toSet());
            double cost = 0;
            for (String msg : messages) {
                boolean flag = false;
                for (Character c : msg.toCharArray()) {
                    if (!hs.contains(c)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    int numMsgs = (msg.length() / 70);
                    int rem = msg.length() % 70 == 0 ? 0 : 1;
                    cost += (numMsgs + rem) * 0.015;
                } else {
                    int numMsgs = (msg.length() / 160);
                    int rem = msg.length() % 160 == 0 ? 0 : 1;
                    cost += (numMsgs + rem) * 0.01;
                }
            }
            return cost;
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int messagesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> messages = IntStream.range(0, messagesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        double result = Result.calculateTotalMessageCost(messages);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
