package LeetcodePrograms;
import java.util.*;
/**
 * @author Rishi Khurana
 */
class GoogleCodingQuestion {
    /** Initialize your data structure here. */

    // t1 main enter
    // t2 foo enter
    // t2 foo1 enter
    // t3 foo exit
    // t4 foo1 exit
    // t4 foo enter
    // t5 foo exit
    // t5 main exit

    class Pair {
        String t1;
        String t2;
        Pair(String t1, String t2) {
            this.t1 = t1;
            this.t2 = t2;
        }

        Pair(String t1) {
            this.t1 = t1;
        }
    }

    public void logs (List<String> logs) {
        HashMap<String, List<Pair>> map = new HashMap();

        Iterator iter = logs.iterator();

        while (iter.hasNext()) {
            String line = (String)iter.next();
            String[] data = line.split(" ");

            String method = data[1];

            if (map.containsKey(method)) {
                // check logic for entry exit
                if ("exit".equals(data[2])) {
                    // this means find the first pair which has null t2
                    List<Pair> pairs = map.get(method);
                    Iterator pairIter = pairs.iterator();
                    // set the exit time to the pair
                    while (pairIter.hasNext()) {
                        Pair p = (Pair)pairIter.next();
                        if (p.t2 == null) {
                            p.t2 = data[0];
                            break;
                        }
                    }
                } else {
                    // we found another entry
                    map.get(method).add(new Pair(data[0]));
                }

            } else {
                map.put(method, new ArrayList());
                // assuming first log will be an entry
                map.get(method).add(new Pair(data[0]));

            }
        }


    }


}
//    key      value
//main -> [t1, t4]
//        foo1 -> [t2, t3]
//        foo -> [t2, t4]