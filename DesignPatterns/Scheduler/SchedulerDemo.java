package DesignPatterns.Scheduler;

public class SchedulerDemo {
    public static void main(String[] args) {

        RoomManager roomManager = RoomManager.getInstance();
        Scheduler scheduler = Scheduler.getInstance(roomManager);

        roomManager.addMeetingRoom(new Room(1, "Room1"));
        roomManager.addMeetingRoom(new Room(2, "Room2"));
        roomManager.addMeetingRoom(new Room(3, "Room3"));
        roomManager.addMeetingRoom(new Room(4, "Room4"));
        roomManager.addMeetingRoom(new Room(5, "Room5"));

        MeetingDetails meetingDetails1 = new MeetingDetails(1, System.currentTimeMillis()+1000, 10000);
        MeetingDetails meetingDetails2 = new MeetingDetails(2, System.currentTimeMillis()+2000, 10000);
        MeetingDetails meetingDetails3 = new MeetingDetails(3, System.currentTimeMillis()+3000, 10000);
        MeetingDetails meetingDetails4 = new MeetingDetails(4, System.currentTimeMillis()+4000, 10000);
        MeetingDetails meetingDetails5 = new MeetingDetails(5, System.currentTimeMillis()+5000, 10000);
        MeetingDetails meetingDetails6 = new MeetingDetails(6, System.currentTimeMillis()+6000, 10000);
        MeetingDetails meetingDetails7 = new MeetingDetails(7, System.currentTimeMillis()+7000, 10000);

        scheduler.scheduleMeeting(meetingDetails1);
        scheduler.scheduleMeeting(meetingDetails2);
        scheduler.scheduleMeeting(meetingDetails3);
        scheduler.scheduleMeeting(meetingDetails4);
        scheduler.scheduleMeeting(meetingDetails5);
        scheduler.scheduleMeeting(meetingDetails6);
        scheduler.scheduleMeeting(meetingDetails7);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    scheduler.checkForTheMeetingCompletion();
                }
            }
        };

        Thread t1 = new Thread(runnable);
        t1.start();
        t1.run();
    }
}
