package LeetcodePrograms;

import java.util.Iterator;
import java.util.*;

/**
 * @author Rishi Khurana
 */
public class NextDoor2  {
    List<HttpRequest> httpRequest = new ArrayList<>();
    static class HttpRequest{
        int id;
        String ip;
        int priorityInteger;
        public HttpRequest(int id , String ip , int priorityInteger) {
            this.id = id;
            this.ip = ip;
            this.priorityInteger = priorityInteger;
        }
    }

    PriorityQueue pq = new PriorityQueue(new Comparator<HttpRequest>()      {
        @Override
        public int compare(HttpRequest r1 , HttpRequest r2){
            Integer i1 = r1.priorityInteger;
            Integer i2 = r2.priorityInteger;
            return i1.compareTo(i2);
        }
    });


    public  void add(HttpRequest request){
        if(request != null){
            pq.add(request);
        }
    }

    public  void process(){

        // Sort by ascending starting point using an anonymous Comparator
        Collections.sort(httpRequest, new Comparator<HttpRequest>() {
                public int compare(HttpRequest r1 , HttpRequest r2){
                    Integer i1 = r1.priorityInteger;
                    Integer i2 = r2.priorityInteger;
                    return i1.compareTo(i2);
            }
        });

        Iterator iter = pq.iterator();
        while(iter.hasNext()){
            HttpRequest next = (HttpRequest)iter.next();
            System.out.println("processing request" + next.id + " ip" + next.ip);
        }
    }

    public static void main(String[] args) {
        NextDoor2 s = new NextDoor2();
        HttpRequest req1 = new HttpRequest(1 , "1.1.1.1" , 1);
        HttpRequest req2 = new HttpRequest(2 , "1.1.1.3",2);
        HttpRequest req3 = new HttpRequest(3 , "1.1.1.2",3);

        s.add(req1);
        s.add(req2);
        s.add(req3);
        s.process();
    }
}
