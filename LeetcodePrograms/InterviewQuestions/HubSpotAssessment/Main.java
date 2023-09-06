package LeetcodePrograms.InterviewQuestions.HubSpotAssessment;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        List<Event> inputEventList = HubspotRestClient.getInputFromUrl();
        Processor processor = new Processor();
        Map<String, List<Session>> processedEventsMap = processor.processEvents(inputEventList);
        SessionsByUser sessionsByUser = new SessionsByUser(processedEventsMap);
        HubspotRestClient.postRequestToUrl(sessionsByUser);
    }
}