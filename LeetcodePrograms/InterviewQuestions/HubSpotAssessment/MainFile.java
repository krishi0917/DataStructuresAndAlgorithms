package LeetcodePrograms.InterviewQuestions.HubSpotAssessment;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainFile {
    public static void main(String []args) {
        Processor p = new Processor();
        RawEvent events = GetEvents();
        Map<String, List<Session>> visitorToSessionMap = p.processEvents(events);
        SessionsByUser sessionsByUser = new SessionsByUser();
        for (String key : visitorToSessionMap.keySet()) {
            Visitor v = new Visitor(key);
            v.sessionList = visitorToSessionMap.get(key);
            sessionsByUser.Add(v);
        }
        Result result = new Result();
        result.sessionsByUser = sessionsByUser;
        System.out.println("result" + sessionsByUser);
    }

    public static RawEvent GetEvents(){
        RawEvent rawEvent = new RawEvent();

        Event event1 = new Event();
        event1.setUrl("/pages/a-big-river");
        event1.setTimestamp(1512754583000L);
        event1.setVisitorId("d1177368-2310-11e8-9e2a-9b860a0d9039");

        Event event2 = new Event();
        event2.setUrl("/pages/a-small-dog");
        event2.setTimestamp(1512754631000L);
        event2.setVisitorId("d1177368-2310-11e8-9e2a-9b860a0d9039");

        Event event3 = new Event();
        event3.setUrl("/pages/a-big-talk");
        event3.setTimestamp(1512709065294L);
        event3.setVisitorId("f877b96c-9969-4abc-bbe2-54b17d030f8b");

        Event event4 = new Event();
        event4.setUrl("/pages/a-sad-story");
        event4.setTimestamp(1512711000000L);
        event4.setVisitorId("f877b96c-9969-4abc-bbe2-54b17d030f8b");

        Event event5 = new Event();
        event5.setUrl("/pages/a-big-river");
        event5.setTimestamp(1512754436000L);
        event5.setVisitorId("d1177368-2310-11e8-9e2a-9b860a0d9039");

        Event event6 = new Event();
        event6.setUrl("/pages/a-sad-story");
        event6.setTimestamp(1512709024000L);
        event6.setVisitorId("f877b96c-9969-4abc-bbe2-54b17d030f8b");

        List<Event> eventList = Arrays.asList(event1, event2, event3, event4, event5, event6);
        rawEvent.events = eventList;
        return rawEvent;
    }
}
/*
input
 "events": [
          {
         "url": "/pages/a-big-river",
         "visitorId": "d1177368-2310-11e8-9e2a-9b860a0d9039",
         "timestamp": 1512754436000
         },

         {
         "url": "/pages/a-big-river",
         "visitorId": "d1177368-2310-11e8-9e2a-9b860a0d9039",
         "timestamp": 1512754583000
         },
         {
         "url": "/pages/a-small-dog",
         "visitorId": "d1177368-2310-11e8-9e2a-9b860a0d9039",
         "timestamp": 1512754631000
         },


duration    195000
         ]
         }
*/

/*

            System.out.println(key);
            List<Session> sessionList = result.get(key);
            for(Session session : sessionList){
                System.out.println("duration " + session.duration);
                System.out.println("pages { ");
                for(String page : session.pages){
                    System.out.println(page);
                }
                System.out.println("}");
                System.out.println("startTime "+ session.startTime);
            }

            System.out.println("===========================");

 */