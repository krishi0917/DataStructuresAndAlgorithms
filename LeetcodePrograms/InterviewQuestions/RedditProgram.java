package LeetcodePrograms.InterviewQuestions;
/*
We have communities with IDs C1,C2,... and followers with IDs F1,F2,..., where each user can follow any of the communities.

We would like to find “similar” or “related” communities by finding all communities that share followers with the input one, Cx. Implement a function to return all related communities of Cx.

Example:
Community -> follower data:
C1: [F1, F3]
C2: [F1, F2]
C3: [F2]
C4: [F3]

Follower -> community data (mirrored data):
F1: [C1, C2]
F2: [C2, C3]
F3: [C1, C4]

get_related_communities(C4) -> [C1]
get_related_communities(C2) -> [C1,C3]

Assume the data for both maps is pre populated and you can use it for the problem.

Part 2:
Now, we want to explore communities which might not share followers directly but are related by indirect relationships (eg. C4 is indirectly related to C2 through C1). Extend the solution to accept degrees of indirect relationship as an input. Each degree adds to the result the communities directly related (sharing followers) to the communities from the previous degrees. Please note that we’re interested in related communities up-to a specific degree (as opposed to only at a specific degree).

Example:
get_related_communities_with_degree(C4, 1) -> [C1]
get_related_communities_with_degree(C4, 2) -> [C1, C2]

community_map = {
'C1': ['F1', 'F3'],
'C2': ['F1', 'F2'],
'C3': ['F2'],
'C4': ['F3'],
}

follower_map = {
'F1': ['C1', 'C2'],
'F2': ['C2', 'C3'],
'F3': ['C1', 'C4'],
}

 */

import java.util.*;

public class RedditProgram {
    Map<String, List<String>> community_map;
    Map<String, List<String>> follower_map;
    public RedditProgram(){
        community_map = new HashMap<>();
        community_map.put("c1", Arrays.asList("f1","f3"));
        community_map.put("c2", Arrays.asList("f1","f2"));
        community_map.put("c3", Arrays.asList("f2"));
        community_map.put("c4", Arrays.asList("f3"));

        follower_map = new HashMap<>();
        follower_map.put("f1", Arrays.asList("c1","c2"));
        follower_map.put("f2", Arrays.asList("c3","c2"));
        follower_map.put("f3", Arrays.asList("c1","c4"));
    }

    /*
        get_related_communities(C4) -> [C1]
`       get_related_communities(C2) -> [C1,C3]
    */
    public List<String> get_related_communities(String community){
        List<String> getRelatedFollower = community_map.get(community);
        Set<String> relatedCommunitySet = new HashSet<>();
        for(String s : getRelatedFollower){
            List<String> tmpCommunityList = follower_map.get(s);
            for(String tempCommunity : tmpCommunityList){
                    relatedCommunitySet.add(tempCommunity);
            }
        }
        return new ArrayList<>(relatedCommunitySet);
    }

    public List<String> get_related_community_of_all(List<String> communities){
        List<String> all_communities = new ArrayList<>();
        for(String community : communities){
            List<String > tempCommunities = get_related_communities(community);
            all_communities.addAll(tempCommunities);
        }
        return all_communities;
    }

    /* get_related_communities_with_degree(C4, 1) -> [C1]
       get_related_communities_with_degree(C4, 2) -> [C1, C2]
    */
    public List<String> get_related_communities_with_degree(String community, int  degree){
        List<String> communities = new ArrayList<>();
        communities.add(community);
        while(degree > 0){
            List<String> tempResult = get_related_community_of_all(communities);
            communities = tempResult;
            degree--;
        }
        communities.remove(community);
        return communities;
    }
    public static void main(String []args){
        RedditProgram  r = new RedditProgram();
        System.out.println(r.get_related_communities_with_degree("c4",2));
        // System.out.println(r.get_related_communities("c2"));
    }
}
