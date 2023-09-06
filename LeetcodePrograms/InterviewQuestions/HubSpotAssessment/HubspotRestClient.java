package LeetcodePrograms.InterviewQuestions.HubSpotAssessment;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class HubspotRestClient {
    public static final String GetEventsUrl = "https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=343b3e9c28328edeaaf15bcea4a3";
    public static final String PostUserSessionsUrl = "https://candidate.hubteam.com/candidateTest/v3/problem/result?userKey=343b3e9c28328edeaaf15bcea4a3";

    public static List<Event> getInputFromUrl(){
        List<Event> inputEvents = new ArrayList<>();
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(GetEventsUrl)).build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            // Check if the request was successful (status code 200)
            if (response.statusCode() == 200) {
                String responseBody = response.body();

                // Parse the JSON response
                JSONObject jsonObject = new JSONObject(responseBody);
                JSONArray eventsArray = jsonObject.getJSONArray("events");

                // Iterate through the "events" array
                for (int i = 0; i < eventsArray.length(); i++) {

                    JSONObject eventObject = eventsArray.getJSONObject(i);
                    String url = eventObject.getString("url");
                    String visitorId = eventObject.getString("visitorId");
                    long timestamp = eventObject.getLong("timestamp");

                    Event event = new Event(url,visitorId,timestamp);
                    inputEvents.add(event);
                }
            } else {
                // Handle the case where the request was not successful
                System.err.println("Error: Request failed with status code " + response.statusCode());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inputEvents;
    }

    public static void postRequestToUrl(SessionsByUser sessionsByUser)  {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            String requestBody = objectMapper.writeValueAsString(sessionsByUser);

            // Create an HttpClient
            HttpClient httpClient = HttpClient.newHttpClient();

            // Build the request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(PostUserSessionsUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(requestBody)))
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the request was successful (status code 200)
            if (response.statusCode() == 200) {
                System.out.println("Response: " + response.body());
            } else {
                // Handle the case where the request was not successful
                System.err.println("Error: Request failed with status code " + response.statusCode() + "and response as " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
