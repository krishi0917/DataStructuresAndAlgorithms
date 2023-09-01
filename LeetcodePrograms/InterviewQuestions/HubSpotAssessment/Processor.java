package LeetcodePrograms.InterviewQuestions.HubSpotAssessment;

import java.util.*;
// visitor can have multiple sessions
// a session is a group of events from a single visitor with no more than 10 mins between each consecutive event

// The visitors in sessionsByUser can be in any order.
// sessions needs to be in chronological order ----> sort by startTime
// each session, url should be in chronological order
// 10 mins = 600000 milliseconds
public class Processor {
    public Map<String,List<Session>> processEvents(RawEvent events){
        List<Event> eventList = events.getEvents();

        // sort by timestamp
        Collections.sort(eventList, new Comparator<Event>() {

            public int compare(Event e1, Event e2) {
                Long v1 = e1.getTimestamp();
                Long v2 = e2.getTimestamp();
                return v1.compareTo(v2);
            }
        });
        // Intermittent timestamps to compare and calculate duration
        Map<String,Long> visitorIdToLastTimestampMap = new HashMap<>();

        Map<String,Long> visitorIdToFirstTimestampMap = new HashMap<>();
        Map<String,List<Session>> visitorIdToSessionsMap = new LinkedHashMap<>(); // key is visitorId

        for(Event event : eventList){
            String currentUrl = event.getUrl();
            String currentVisitorId = event.getVisitorId();
            long currentTimestamp = event.getTimestamp();

            if(visitorIdToSessionsMap.containsKey(currentVisitorId)){
                long lastTimestamp = visitorIdToLastTimestampMap.get(currentVisitorId);
                if(currentTimestamp - lastTimestamp <= 600000){

                    // url will be added in the same session, duration & visitorIdToIntermediateTimestampMap will be updated
                    List<Session> tempSessionList = visitorIdToSessionsMap.get(currentVisitorId);
                    Session lastSession = tempSessionList.get(tempSessionList.size() - 1);
                    tempSessionList.remove(tempSessionList.size() - 1);
                    List<String> currentUrlList = lastSession.getPages();
                    currentUrlList.add(currentUrl);
                    lastSession.setPages(currentUrlList);
                    lastSession.duration = currentTimestamp - visitorIdToFirstTimestampMap.get(currentVisitorId);
                    tempSessionList.add(lastSession);
                    visitorIdToSessionsMap.put(currentVisitorId, tempSessionList);
                }else{
                    List<String> tempPageList = new ArrayList<>();
                    tempPageList.add(currentUrl);
                    visitorIdToSessionsMap.get(currentVisitorId).add(new Session(0L,tempPageList, currentTimestamp));
                }
                visitorIdToLastTimestampMap.put(currentVisitorId, currentTimestamp);
            }else{
                List<String> pageList = new ArrayList<>();
                pageList.add(currentUrl);
                Session s = new Session(0L,pageList, currentTimestamp);
                List<Session> sessionList = new ArrayList<>();
                sessionList.add(s);
                visitorIdToSessionsMap.put(currentVisitorId, sessionList);
                visitorIdToLastTimestampMap.put(currentVisitorId,currentTimestamp); //might not be required
                visitorIdToFirstTimestampMap.put(currentVisitorId, currentTimestamp);
            }
        }
        return visitorIdToSessionsMap;
    }
}
