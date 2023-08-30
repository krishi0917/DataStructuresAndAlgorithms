package LeetcodePrograms.InterviewQuestions;

import java.util.*;
/*
Figure out which endpoint a given HTTP request maps to.

For example, we have some request "GET /users/abc123/preferences" which matches
the pattern of an endpoint we have "GET /users/<uid>/preferences" which has the
human-readable name "get_user_preferences".

We need to develop some type of algorithm that can efficiently get us from the
input string "GET /users/abc123/preferences" to the human-readable name
"get_user_preferences".

    | Pattern                      | Endpoint name            |
        |------------------------------|--------------------------|
        | GET /users                   | get_all_users            |
        | GET /users/<UID>             | get_user                 |
        | GET /users/<UID>/preferences | get_user_preferences     |
        | GET /users/<UID>/<UID>       | get_user_posts_in_thread |
        | GET /thread/<UID>            | get_thread               |
        | GET /thread/<UID>/comments   | get_thread_comments      |
        | GET /thread/<UID>/likes      | get_thread_likes         |
        | POST /thread                 | create_thread            |
        | DELETE /comments/<UID>       | delete_comment           |
        | ...                          | ...                      |

GET /users/abc123/preferences

In our input, any type of ID (<user_id>, <thread_id>, etc.) will be represented
by any random alphanumeric string, consider the following example input strings
and what endpoint name they should map to:

        | Input: String                                   | Expected output      |
        |-------------------------------------------------|----------------------|
        | GET /users                                      | get_all_users        |
        | GET /users/abc123                               | get_user             |
        | GET /users/def456                               | get_user             |
        | GET /users/thisuseridlookslikewords/preferences | get_user_preferences |
        | GET /thread/thisthreadidlookslikewords/comments | get_thread_comments  |
        | ...
*/

public class PinterestInterviewQuestion {
    class TrieNode {
        Map<String, TrieNode> children;
        String endpointResult;
        boolean endOfWord;
        TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    private TrieNode root;

    public PinterestInterviewQuestion() {
        root = new TrieNode();
    }

    public void addPattern(String pattern, String endpointName) {
        String[] patternSplit = pattern.split(" ");
        String methodType = patternSplit[0];
        String url = patternSplit[1];
        TrieNode childNode = root.children.get(methodType);
        if (childNode == null) {
            childNode = new TrieNode();
            root.children.put(methodType, childNode);

        }
        String[] urlSplit = url.split("/");
        addPatternutil(childNode, urlSplit, endpointName, 0);
    }

    private void addPatternutil(TrieNode node, String[] urlSplit, String endpointName, int index) {
        if (index == urlSplit.length) {
            node.endOfWord = true;
            node.endpointResult = endpointName;
            return;
        }
        TrieNode child = node.children.get(urlSplit[index]);
        if (child == null) {
            child = new TrieNode();
            node.children.put(urlSplit[index], child);
        }
        addPatternutil(child, urlSplit, endpointName,index + 1);
    }
    String result ;
    public String getEndPointName(String pattern) {
        result = "";
        String[] patternSplit = pattern.split(" ");
        String methodType = patternSplit[0];
        String url = patternSplit[1];
        String[] urlSplit = url.split("/");

        if (root.children.containsKey(methodType)) {
            TrieNode node = root.children.get(methodType);
            getEndPointNameUtil(node, urlSplit, 0);
        } else {
            result = "no mapping found";
        }
        return result;
    }

    private void getEndPointNameUtil(TrieNode node, String[] urlSplit, int index) {
        if (index == urlSplit.length) {
            if (node.endOfWord) {
                result = node.endpointResult;
                return;
            } else {
                result = "no mapping found";
                return;
            }
        }
        if (node.children.containsKey(urlSplit[index])) {
            TrieNode child = node.children.get(urlSplit[index]);
            getEndPointNameUtil(child, urlSplit, index + 1);
        }else if(node.children.containsKey("<UID>")){
            TrieNode child = node.children.get("<UID>");
            getEndPointNameUtil(child, urlSplit, index + 1);
        }
        else {
            result = "No mapping found";
            return;
        }
    }

    public static void main(String[] args) {
        PinterestInterviewQuestion pinterestInterviewQuestion = new PinterestInterviewQuestion();
        pinterestInterviewQuestion.addPattern("GET users" , "get_all_users");
        pinterestInterviewQuestion.addPattern("GET users/<UID>" , "get_user");
        pinterestInterviewQuestion.addPattern("GET users/<UID>/preferences" , "get_user_preferences");
        pinterestInterviewQuestion.addPattern("GET users/<UID>/<UID>" , "get_user_posts_in_thread");
        pinterestInterviewQuestion.addPattern("GET thread/<UID>" , "get_thread");
        pinterestInterviewQuestion.addPattern("GET thread/<UID>/comments" , "get_thread_comments ");
        pinterestInterviewQuestion.addPattern("GET thread/<UID>/likes" , "get_thread_likes");
        pinterestInterviewQuestion.addPattern("POST thread" , "get_thread_likes");
        pinterestInterviewQuestion.addPattern("DELETE comments/<UID>" , "get_all_users");

/*
        | Input: String                                   | Expected output      |
        |-------------------------------------------------|----------------------|
        | GET /users                                      | get_all_users        |
        | GET /users/abc123                               | get_user             |
        | GET /users/def456                               | get_user             |
        | GET /users/thisuseridlookslikewords/preferences | get_user_preferences |
        | GET /thread/thisthreadidlookslikewords/comments | get_thread_comments  |

 */
        System.out.println(pinterestInterviewQuestion.getEndPointName("GET users" ));
        System.out.println(pinterestInterviewQuestion.getEndPointName("GET users/abc123" ));
        System.out.println(pinterestInterviewQuestion.getEndPointName("GET users/def456" ));
        System.out.println(pinterestInterviewQuestion.getEndPointName("GET users/thisuseridlookslikewords/preferences" ));
        System.out.println(pinterestInterviewQuestion.getEndPointName("GET thread/thisthreadidlookslikewords/comments" ));
    }
}
