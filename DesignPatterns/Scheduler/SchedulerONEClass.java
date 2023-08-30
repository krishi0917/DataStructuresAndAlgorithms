package DesignPatterns.Scheduler;
import java.util.*;
import java.util.concurrent.*;
public class SchedulerONEClass {
    public enum RoomStatus {
        OCCUPIED,
        FREE
    }

    public  class Room {
        int id;
        String name;
        RoomStatus status;
        MeetingDetails currentMeetingInfo;

        public Room(Integer id, String name) {
            this.id = id;
            this.name = name;
            this.status = RoomStatus.FREE;
        }
    }

    public class MeetingDetails {
        int id;
        long startTime;
        long duration;

        public MeetingDetails(Integer id, long startTime, long duration) {
            this.id = id;
            this.startTime = startTime;
            this.duration = duration;
        }
    }

     static class RoomManager {
        List<Room> meetingRoomList;
        private static RoomManager instance;

        public static RoomManager getInstance() {
            if (instance == null) {
                synchronized (RoomManager.class) {
                    if (instance == null) {
                        instance = new RoomManager();
                    }
                }
            }
            return instance;
        }

        private RoomManager() {
            this.meetingRoomList = new LinkedList<>();
        }

        public void addMeetingRoom(Room room) {
            meetingRoomList.add(room);
        }

        public void allocateRoom(Room room, MeetingDetails meetingDetails) {
            room.status = RoomStatus.OCCUPIED;
            room.currentMeetingInfo = meetingDetails;
        }

        public void freeTheRoom(Room room, MeetingDetails meetingDetails) {
            room.status = RoomStatus.FREE;
            room.currentMeetingInfo = null;
        }
    }

    static class Scheduler {

        private RoomManager roomManager;
        private static Scheduler instance;
        Map<Room, MeetingDetails> mapOfMeetingRoomVsMeeting ;

        public static Scheduler getInstance(RoomManager roomManager) {
            if (instance == null) {
                synchronized (Scheduler.class) {
                    if (instance == null) {
                        instance = new Scheduler(roomManager);
                    }
                }
            }
            return instance;
        }

        private Scheduler(RoomManager roomManager) {
            this.roomManager = roomManager;
            this.mapOfMeetingRoomVsMeeting = new ConcurrentHashMap<>();
        }

        public void scheduleMeeting(MeetingDetails meetingDetails) {
            Room room = getFreeMeetingRoom();
            if (room != null) {
                mapOfMeetingRoomVsMeeting.put(room, meetingDetails);
                roomManager.allocateRoom(room, meetingDetails);
                System.out.println("Meeting Room Allocated for Meeting : " + meetingDetails.id);
            } else {
                System.out.println("Meeting Room Could Not Be Allocated for Meeting : " + meetingDetails.id + " .. Discarding.. ");
            }
        }

        public void checkForTheMeetingCompletion() {
            synchronized (this) {
                for (Room meetingRoom : mapOfMeetingRoomVsMeeting.keySet()) {
                    MeetingDetails meetingDetails = mapOfMeetingRoomVsMeeting.get(meetingRoom);
                    if (System.currentTimeMillis() > (meetingDetails.startTime + meetingDetails.duration)) {
                        System.out.println("Meeting Room : " + meetingRoom.name + " is free now as the meeting : " + meetingDetails.id + " has ended.. ");
                        mapOfMeetingRoomVsMeeting.remove(meetingRoom);
                        roomManager.freeTheRoom(meetingRoom, meetingDetails);
                    }
                }
            }
        }


        public Room getFreeMeetingRoom() {
            for (Room room : roomManager.meetingRoomList) {
                if (room.status == RoomStatus.FREE) {
                    return room;
                }
            }
            return null;
        }

        public static void main(String[] args) {

            DesignPatterns.Scheduler.RoomManager roomManager = DesignPatterns.Scheduler.RoomManager.getInstance();
            DesignPatterns.Scheduler.Scheduler scheduler = DesignPatterns.Scheduler.Scheduler.getInstance(roomManager);

            roomManager.addMeetingRoom(new DesignPatterns.Scheduler.Room(1, "Room1"));
            roomManager.addMeetingRoom(new DesignPatterns.Scheduler.Room(2, "Room2"));
            roomManager.addMeetingRoom(new DesignPatterns.Scheduler.Room(3, "Room3"));
            roomManager.addMeetingRoom(new DesignPatterns.Scheduler.Room(4, "Room4"));
            roomManager.addMeetingRoom(new DesignPatterns.Scheduler.Room(5, "Room5"));

            DesignPatterns.Scheduler.MeetingDetails meetingDetails1 = new DesignPatterns.Scheduler.MeetingDetails(1, System.currentTimeMillis()+1000, 10000);
            DesignPatterns.Scheduler.MeetingDetails meetingDetails2 = new DesignPatterns.Scheduler.MeetingDetails(2, System.currentTimeMillis()+2000, 10000);
            DesignPatterns.Scheduler.MeetingDetails meetingDetails3 = new DesignPatterns.Scheduler.MeetingDetails(3, System.currentTimeMillis()+3000, 10000);
            DesignPatterns.Scheduler.MeetingDetails meetingDetails4 = new DesignPatterns.Scheduler.MeetingDetails(4, System.currentTimeMillis()+4000, 10000);
            DesignPatterns.Scheduler.MeetingDetails meetingDetails5 = new DesignPatterns.Scheduler.MeetingDetails(5, System.currentTimeMillis()+5000, 10000);
            DesignPatterns.Scheduler.MeetingDetails meetingDetails6 = new DesignPatterns.Scheduler.MeetingDetails(6, System.currentTimeMillis()+6000, 10000);
            DesignPatterns.Scheduler.MeetingDetails meetingDetails7 = new DesignPatterns.Scheduler.MeetingDetails(7, System.currentTimeMillis()+7000, 10000);

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
}
