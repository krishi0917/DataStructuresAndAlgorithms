package HubSpotAssessment;

import java.util.List;

public class SessionsByUser {
    String visitorId;
    List<Session> session;

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public List<Session> getSession() {
        return session;
    }

    public void setSession(List<Session> session) {
        this.session = session;
    }
}

/*
"sessionsByUser": {
        "f877b96c-9969-4abc-bbe2-54b17d030f8b": [
        {
        "duration": 41294,
        "pages": [
        "/pages/a-sad-story",
        "/pages/a-big-talk"
        ],
        "startTime": 1512709024000
        },
        {
        "duration": 0,
        "pages": [
        "/pages/a-sad-story"
        ],
        "startTime": 1512711000000
        }
        ],
*/
