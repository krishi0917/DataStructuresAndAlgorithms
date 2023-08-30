package LeetcodePrograms.InterviewQuestions.HubSpotAssessment;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class EventsRestClient {

    public static void main(String []args) throws IOException {
        EventsRestClient e = new EventsRestClient();
        String s = restClient();
        System.out.println(s);
    }
    public static String restClient() throws IOException {
        String url = "https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=bc7d8712b664eadc7f9698f9b4a7";
        HttpURLConnection connection = (HttpURLConnection) new URL(url ).openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if(responseCode == 200){
            String response = "";
            Scanner scanner = new Scanner(connection.getInputStream());
            while(scanner.hasNextLine()){
                response += scanner.nextLine();
                response += "\n";
            }
            scanner.close();
            return response;
        }
        return null;
    }
}
