package LeetcodePrograms.InterviewQuestions;
import java.util.*;
public class TwitterQuestion {
/*
    String requests[][]={
            {"create","xyz","1","1619916081"}
    };
*/
    static class SpaceObject{
        String type;
        String userId;
        String timeStamp;
        public SpaceObject(String t , String uId, String tStamp){
            type = t;
            userId = uId;
            timeStamp = tStamp;

        }
    }
    static Map<String, List<SpaceObject>> spaceMap = new HashMap<>();
    public static Map<String,Double> calculateTotalSpaceSpent(String[][] requests){
/*
        for(String [] request : requests){
            String space = request[1];
            if(spaceMap.containsKey(space)){

                List<SpaceObject> tempList = spaceMap.get(space);
                tempList.add(new SpaceObject(request[0],request[2],request[3]));
                spaceMap.put(space,tempList);
            }else{
                SpaceObject spaceObject = new SpaceObject(request[0],request[2],request[3]);
                List<SpaceObject> tempList = new ArrayList<>();
                tempList.add(spaceObject);
                spaceMap.put(space, tempList);
            }
        }
*/
        //map <space,Map<user,timedifference>>
        //create<xyz,user1,123
        //join<xyz,user2
        Map<String, Map<String,String>> spaceMap = new HashMap<>();
        for(String [] request : requests){
            String type = request[0];
            String space = request[1];
            String user = request[2];
            String timestamp = request[3];
            if(type.equals("create") || type.equals("join")){
                // space xyz is already created. now someone is joining
                if(spaceMap.containsKey(space)){
                    Map<String,String> tempMap = spaceMap.get(space);
                    tempMap.put(user,timestamp);
                    spaceMap.put(space,tempMap);
                }else { // space is not even created
                    Map<String, String> tempMap = new HashMap<>();
                    tempMap.put(user, timestamp);
                    spaceMap.put(space, tempMap);
                }
            }else{ // spacemap is created and now we have to make the calculations
                Map<String,String> userMap = spaceMap.get(space);
                String startingTimeStamp = userMap.get(user);
                Double totalTimeStamp = Double.parseDouble(timestamp) - Double.parseDouble(startingTimeStamp);
                userMap.put(user, totalTimeStamp.toString());
                spaceMap.put(space,userMap);
            }
        }


/*
        Map<String,Double> result = new HashMap<>();
        for(Map.Entry<String, List<SpaceObject>>entryMap : spaceMap.entrySet()){
            List<SpaceObject> tempSpaceObjectList = entryMap.getValue();
            Map<String,String> tempMap = new HashMap<>(); // userId and startTimeStamp
            for(SpaceObject spaceObject : tempSpaceObjectList ){
                if(spaceObject.type.equals("create") || spaceObject.type.equals("join")){
                    tempMap.put(spaceObject.userId,spaceObject.timeStamp);
                }else{
                    // check if there is already a user with the current total
                    double tempSum = 0;
                    if(result.containsKey(spaceObject.userId)){
                        tempSum = result.get(spaceObject.userId);
                    }else{
                        // new entry
                        tempSum = 0;
                    }
                    tempSum += Double.parseDouble(spaceObject.timeStamp) - Double.parseDouble(tempMap.get(spaceObject.userId));
                    result.put(spaceObject.userId , tempSum);
                }
            }
            }
*/


        return null;
    }

    public static void main(String []args){
        String requests[][] ={
                {"create","xyz","1","1619916081"},
                {"join","xyz","2","1619916681"},
                {"create","abc","3","1619916881"},
                {"leave","xyz","2","1619920281"},
                {"join","abc","4","1619920881"},
                {"create","ghi","5","1619923999"},
                {"leave","xyz","1","1619923881"},
                {"leave","abc","3","1619927481"},
                {"leave","abc","4","1619927481"},
                {"leave","ghi","5","1619958001"}
        };
        System.out.println(TwitterQuestion.calculateTotalSpaceSpent(requests));
    }
}
