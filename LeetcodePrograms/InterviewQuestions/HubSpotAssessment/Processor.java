package LeetcodePrograms.InterviewQuestions.HubSpotAssessment;

import java.util.*;

public class Processor {
    public Map<String, List<Session>> processEvents(List<Event> eventList){
        // sort by timestamp
        Collections.sort(eventList, new Comparator<Event>() {

            public int compare(Event e1, Event e2) {
                Long v1 = e1.getTimestamp();
                Long v2 = e2.getTimestamp();
                return v1.compareTo(v2);
            }
        });

        // Intermittent visitor to timestamp map to compare and calculate duration
        Map<String,Long> visitorToRecentTimeMap = new HashMap<>();
        Map<String,Long> visitorToFirstTimeMap = new HashMap<>();
        Map<String,List<Session>> visitorToSessionsMap = new LinkedHashMap<>();

        for(Event event : eventList){
            String currentUrl = event.getUrl();
            String currentVisitorId = event.getVisitorId();
            long currentTimestamp = event.getTimestamp();

            // visitor coming again
            if(visitorToSessionsMap.containsKey(currentVisitorId)){

                long lastTimestamp = visitorToRecentTimeMap.get(currentVisitorId);
                if(currentTimestamp - lastTimestamp <= 600000){ // needs to be added in the same session
                    List<Session> tempSessionList = visitorToSessionsMap.get(currentVisitorId);
                    Session lastSession = tempSessionList.get(tempSessionList.size() - 1);
                    tempSessionList.remove(tempSessionList.size() - 1);
                    List<String> currentUrlList = lastSession.getPages();
                    currentUrlList.add(currentUrl);
                    lastSession.setPages(currentUrlList);
                    lastSession.duration = currentTimestamp - visitorToFirstTimeMap.get(currentVisitorId);
                    tempSessionList.add(lastSession);
                    visitorToSessionsMap.put(currentVisitorId, tempSessionList);
                }else{
                    //difference in current and recent > 60000, hence a new session is created in the same visitor with the duration = 0, currentTimestamp and currentUtl
                    List<String> tempPageList = new ArrayList<>();
                    tempPageList.add(currentUrl);
                    visitorToSessionsMap.get(currentVisitorId).add(new Session(0L,tempPageList, currentTimestamp));
                    visitorToFirstTimeMap.put(currentVisitorId, currentTimestamp);
                }
                visitorToRecentTimeMap.put(currentVisitorId, currentTimestamp);
            }else{
                // visitor coming for the first time, create a new session with the duration = 0, currentTimestamp and currentUrl
                List<String> pageList = new ArrayList<>();
                pageList.add(currentUrl);
                List<Session> sessionList = new ArrayList<>();
                sessionList.add(new Session(0L,pageList, currentTimestamp));
                visitorToSessionsMap.put(currentVisitorId, sessionList);

                // create an entry in visitorToRecentTimeMap and visitorToFirstTimeMap with currentTimestamp as its a new visitor
                visitorToRecentTimeMap.put(currentVisitorId, currentTimestamp);
                visitorToFirstTimeMap.put(currentVisitorId, currentTimestamp);
            }
        }

        return visitorToSessionsMap;
    }
}
