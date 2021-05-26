package LeetcodePrograms;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author Rishi Khurana
 */
public class AccountsMerge2 {
    public static List<Set<String>> mergeAccounts(Map <String, Set<String>> accounts) {
        HashMap<String, Set<String>> emailKeyMap = new HashMap<>();
        for (Map.Entry<String, Set<String>> map : accounts.entrySet()) {
            Set<String> set = map.getValue();
            for (String email : set) {
                if (emailKeyMap.containsKey(email)) {
                    Set<String> existingSet = emailKeyMap.get(email);
                    existingSet.add(map.getKey());
                    emailKeyMap.put(email, existingSet);
                } else {
                    Set<String> newSet = new HashSet<>();
                    newSet.add(map.getKey());
                    emailKeyMap.put(email, newSet);
                }
            }
        }

        List<Set<String>> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for (String email : emailKeyMap.keySet()) {

            Set<String> tempSetAccount = emailKeyMap.get(email); // c1,c5

            for (String accountName : tempSetAccount) { // c1  visitedSet c1

                if (visited.add(accountName)) { // true or false //unvisited node if true

                    Set<String> resultSubSet = new HashSet<>(); // accountName = c1

                    mergeAccountsUtil(accounts, visited, resultSubSet, accountName , emailKeyMap);

                    result.add(resultSubSet);

                }
            }
        }
        return result;
    }


    public static void mergeAccountsUtil(Map <String, Set<String>> accounts, Set<String>visited, Set<String> resultSubSet , String accountName ,
            HashMap<String, Set<String>> emailKeyMap){

        resultSubSet.add(accountName);

        for(String nextEmail : accounts.get(accountName)){

            for(String account : emailKeyMap.get(nextEmail) ){

                if(visited.add(account)){

                    mergeAccountsUtil(accounts,visited,resultSubSet,accountName,emailKeyMap);

                }
            }
        }
    }


    public static void main(String []args){
        Map <String, Set<String>> accountsMap = new HashMap<>();
        accountsMap.put("C1", Stream.of("a@x", "b@x", "c@x")
                        .collect(Collectors.toCollection(HashSet::new)));

        accountsMap.put("C2", Stream.of("d@x", "e@x")
                .collect(Collectors.toCollection(HashSet::new)));

        accountsMap.put("C3", Stream.of("f@x")
                .collect(Collectors.toCollection(HashSet::new)));

        accountsMap.put("C4", Stream.of("e@x")
                .collect(Collectors.toCollection(HashSet::new)));

        accountsMap.put("C5", Stream.of("a@x", "f@x")
                .collect(Collectors.toCollection(HashSet::new)));

        accountsMap.put("C6", Stream.of( "b@x", "g@x")
                .collect(Collectors.toCollection(HashSet::new)));

        mergeAccounts(accountsMap);

    }


    //Map, <String, Set<String>>
    //Names, Emails
    //C1 : [a@x, b@x, c@x]
    //C2 : [d@x, e@x]
    //C3 : [f@x]
    //C4 : [e@x]
    //C5 : [a@x, f@x]
    //C6 : [g@x b@x]


    //List<Set<String>>
    //[
    //[C1, C5, C3]
    //[C2, C4]
    //[C6]
    //]
    //

    }
