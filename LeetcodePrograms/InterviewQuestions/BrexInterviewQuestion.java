package LeetcodePrograms.InterviewQuestions;

/*
        Given a set of known suspicious activites, return a list of suspicious activities found in new activities.

        A new suspicious activity is defined as having atleast k of the same values as a "node" in suspicous activities.
        A newly identified suspicipous activity can be used to identify other suspicious activities. (Hint : Recursion)

        suspiciousactivities = [
        ("Brad", "San Francisco", "withdraw"),
        ]
        Diana, London
        newactivities = [
        ("Joe", "Miami", "withdraw"),
        ("John", "San Francisco", "deposit"),
        ("Albert", "London", "withdraw"),
        ("Diana", "London", "withdraw"),
        ("Diana", "San Francisco", "withdraw"),
        ("Joe", "New York", "updateaddress"),
        ]

        k = 2;

        findsuspiciousactivities(suspiciousactivites, newactivities, k) = [
        ("Albert", "London", "withdraw")
        ("Diana", "London", "withdraw"),
        ("Diana", "San Francisco", "withdraw")
        ]

        Explanation : Initially ("Diana", "San Francisco", "withdraw") is identified as the suspicious activity list, this adds "Diana" to the suspicious activity set,
        then ("Diana", "London", "withdraw") is also suspicious, which adds "London" to suspicious activity set now,
        which now adds ("Albert", "London", "withdraw") in the final list.

        Follow-Up:
        Also return the depth of each suspicious activity identified.
*/

import java.util.*;

public class BrexInterviewQuestion {
    List<Integer> suspiciousIndexes = new ArrayList<>();

    public List<List<String>> suspiciousActivities(Set<String> suspicious_activities, List<List<String>> new_activities, int k) {
        return suspiciousActivitiesRec(suspicious_activities, new_activities, k, 1);

    }

    public List<List<String>> suspiciousActivitiesRec(Set<String> suspicious_activities, List<List<String>> new_activities, int k, int depth) {

        List<List<String>> suspiciousActivitiesList = new ArrayList<>();

        int count = 0;
        boolean flag = false;
        for (int i = 0; i < new_activities.size(); i++) {
            if (suspiciousIndexes.contains(i)) continue;
            List<String> newActivity = new_activities.get(i);

            for (String activity : newActivity) {
                if (suspicious_activities.contains(activity)) {
                    count++;
                }
                if (count == k) {
                    flag = true;
                    break;
                }

            }
            if (flag) {
                newActivity.add(0, String.valueOf(depth));
                suspiciousActivitiesList.add(newActivity);
                suspicious_activities.addAll(newActivity);
                suspiciousIndexes.add(i);

                List<List<String>> suspiciousActivitiesRecList = suspiciousActivitiesRec(suspicious_activities, new_activities, k, ++depth);
                suspiciousActivitiesList.addAll(suspiciousActivitiesRecList);
                flag = false;
            }

            count = 0;

        }
        return suspiciousActivitiesList;

    }


    public static void main(String[] args) {
        BrexInterviewQuestion solution = new BrexInterviewQuestion();
        Set<String> suspicious_activities = new HashSet<>();
        suspicious_activities.add("Brad");
        suspicious_activities.add("San Francisco");
        suspicious_activities.add("withdraw");

        List<String> newActivity1 = new ArrayList<>(Arrays.asList("Joe", "Miami", "withdraw"));
        List<String> newActivity2 = new ArrayList<>(Arrays.asList("John", "San Francisco", "deposit"));
        List<String> newActivity3 = new ArrayList<>(Arrays.asList("Albert", "London", "withdraw"));
        List<String> newActivity4 = new ArrayList<>(Arrays.asList("Diana", "London", "withdraw"));
        List<String> newActivity5 = new ArrayList<>(Arrays.asList("Diana", "San Francisco", "withdraw"));
        List<String> newActivity6 = new ArrayList<>(Arrays.asList("Joe", "New York", "update_address"));

        List<List<String>> new_activities = new ArrayList<>();
        new_activities.add(newActivity1);
        new_activities.add(newActivity2);
        new_activities.add(newActivity3);
        new_activities.add(newActivity4);
        new_activities.add(newActivity5);
        new_activities.add(newActivity6);

        List<List<String>> suspiciousActivitiesList = solution.suspiciousActivities(suspicious_activities, new_activities, 2);

        System.out.println(suspiciousActivitiesList);
    }
}
