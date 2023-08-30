package LeetcodePrograms.InterviewQuestions;
    /*
 * When Databricks acquires customers, they can commit to a certain amount of money they will spend   * we will call this revenue.
 *
 * To implement:
 * insert(revenue: int) -> int (auto-incrementing customer id starting at 0)
 * insert(revenue: int, referrer: int) -> int (auto-incrementing customer id starting at 0)
 * get_lowest_k_by_total_revenue(k: int, min_total_revenue: int) -> Set<int>
 *
 * Total revenue = initial revenue + initial revenue of directly referred customers
 *
 * Example:
 * insert(10) -> 0
 * insert(20, 0) -> 1
 * insert(40, 1) -> 2

 * get_lowest_k_by_total_revenue(1, 35) -> Set(2)
 * get_lowest_k_by_total_revenue(2, 35) -> Set(1, 2)
 *
 *
 *
 * Follow up question
 * Example:
 *  * Example:
 * insert(10) -> 0
 * insert(20, 0) -> 1
 * insert(40, 1) -> 2

 * 0 -> 10
 * 1 -> 20
 * 2->  40
 * 4-> 50
 *

 map
get_nested_revenue(0, 0) -> 10
get_nested_revenue(0, 1) -> 30
get_nested_revenue(0, 2) -> 70  explanation 0 referred 1 and 1 referred 2 and this is 2 level nesting
get_nested_revenue(1, 1) -> 60  explanation 1 referred 2 so 1's revenue + 2nd revenue
*/

    import java.util.*;
    class DatabricksQuestion {
        Map<Integer,Integer> userIdtoRevenueMap = new HashMap<>();
        Map<Integer,List<Integer>> userIdtoReferralsMap = new HashMap<>();
        int userId =-1;

        public int insert(int revenue){
            if(revenue<=0){
                System.out.println("wrong information added");
                return -1;
            }
            userId++;
            userIdtoRevenueMap.put(userId,revenue);
            return userId;
        }

        public int insert(int revenue, int referral){
            if(revenue<=0){
                System.out.println("wrong information added");
                return -1;
            }
            userId++;
            userIdtoRevenueMap.put(userId,revenue);
            if(userIdtoRevenueMap.get(referral)!= null){
                userIdtoRevenueMap.put(referral, userIdtoRevenueMap.get(referral) + revenue);
            }else{
                System.out.println("no referral exist");
            }

            //follow up part
            if(!userIdtoReferralsMap.containsKey(referral)){
                List<Integer> tempList = new ArrayList<>();
                tempList.add(userId);
                userIdtoReferralsMap.put(referral , tempList );
            }
            else {
                List<Integer> tempList = userIdtoReferralsMap.get(referral);
                tempList.add(userId);
                userIdtoReferralsMap.put(referral , tempList);
            }

            return userId;
        }

        public Set<Integer> get_lowest_k_by_total_revenue(int k , int minRevenue){
            PriorityQueue<Map.Entry<Integer,Integer>> pq =new PriorityQueue<Map.Entry<Integer,Integer>>( new Comparator<Map.Entry<Integer, Integer>>() {
                @Override
                public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                        int i1 = o1.getValue();
                        int i2 = o2.getValue();
                        return i1-i2;
                }
            });
            for(Map.Entry<Integer,Integer> entry : userIdtoRevenueMap.entrySet() ){
                pq.offer(entry);
            }
            Set<Integer> result = new HashSet<>();
            while(!pq.isEmpty() && k>0){
                Map.Entry<Integer,Integer> entry = pq.poll();
                if(entry.getValue() >= minRevenue){
                    result.add(entry.getKey());
                    k--;
                }
            }
            return result;
        }

        // follow up question
        class Pair {
            int userId;
            int depth ;
            public Pair(int u , int d){
                userId = u;
                depth = d;
            }
        }

        public int get_nested_revenue (int userId , int maxNesting){
           Queue<Pair> queue = new LinkedList<>();
           queue.add(new Pair(userId, 0 ));
           int totalSum = 0;
           boolean flag = false;
           while(!queue.isEmpty()){
               Pair p = queue.poll();
               if(p.depth == maxNesting + 1){
                    flag = true;
               }
               if(flag){
                   continue;

               }
               if(userIdtoRevenueMap.containsKey(p.userId)){
                   totalSum +=userIdtoRevenueMap.get(p.userId);
               }
               List<Integer> userIdList = userIdtoReferralsMap.get(p.userId);
               int currentDepth = p.depth;
               for(int i : userIdList){
                   queue.add(new Pair(i , currentDepth + 1));
               }
           }
           return totalSum;
        }
        public static void main(String[] args) {
            DatabricksQuestion s = new DatabricksQuestion();
            System.out.println( s.insert(10));
            System.out.println(s.insert(20, 0)) ;//-> 1
            System.out.println(s.insert(40, 1));// -> 2
            System.out.println(s.insert(60, 1)); // -> 3
            System.out.println(s.insert(30, 2));// -> 4
            System.out.println(s.insert(70, 2)); // -> 5
            System.out.println(s.insert(90, 2)); //-->6
            System.out.println(s.insert(110, 3));// -> 7
            System.out.println(s.get_nested_revenue(1,2));
//            System.out.println(s.get_lowest_k_by_total_revenue(1,35));
//            System.out.println(s.get_lowest_k_by_total_revenue(2,35));
        }
    }



/*
*To implement:
get_nested_revenue(id: int, max_nesting: int) -> int

 userId
 0 -> 1
 1 -> 2 , 4   ->(1,1) = 90
 2-> 3
 3->4
 4-> 5
*/
