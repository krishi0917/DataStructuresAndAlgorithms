package LeetcodePrograms.InterviewQuestions;

public class AdobeQuestion {
}

/*

1. Given a json document, print all paths that lead to an leaf node that has type=identity.


Key value
Example: The following json input

  {
   "endUserIDs": {
       "experience" : {
          "aaid": {
             "type": "identity"
          },
          "xid": {
             "type": "identity"
          },
          "fid": {
             "profile": {
                 "abc": {
                     "mcid": {
                         "type": "identity"
                     },
                     "geo": {
                         "type": "geo"
                     }
                 }
             }
          }
        },
        "marketing": {
           "pqr": {
               "type": "geo"
           },
           "xyz": {
               "type": "identity"
           }
        }
    }
}
should return

List("endUserIDs.experience.aaid", "endUserIDs.experience.xid", "endUserIDs.experience.fid.profile.abc.mcid", "endUserIDs.marketing.xyz")


import java.util.*;
public class Solution{
    static class Node{
        boolean isEnd;
        String name;
        String type;
        List<Node> children;
        Node(String name, String type){
            this.name = name;
            this.type = type;
            this.children = new ArrayList<>();
        }

        Node(String name){
            this(name,null);
        }
    }

    /*
    "endUserIDs": {
       "experience" : {
          "aaid": {
             "type": "identity"
          },
          "xid": {
             "type": "identity"
          },
          "fid": {
             "profile": {
                 "abc": {
                     "mcid": {
                         "type": "identity"
                     },
                     "geo": {
                         "type": "geo"
                     }
                 }
             }
          }
        },
        "marketing": {
           "pqr": {
               "type": "geo"
           },
           "xyz": {
               "type": "identity"
           }
        }
    }
}
should return

List("endUserIDs.experience.aaid", "endUserIDs.experience.xid", "endUserIDs.experience.fid.profile.abc.mcid", "endUserIDs.marketing.xyz")

    n number of nodes
    d depth
    O(n)
    public static void findPaths(Node node , List<String>path, List<String> result){
        path.add(node.name); // path endUserId,experience. --> aaid
        if(node.children.isEmpty()){
            if("identity".equals(node.type)){
                result.add(String.join(".",path));
            }
        }else{
            for(Node child : node.children){ // experience, marketing. --> aaid,xid,fid
                findPaths(child, path, result);
            }
        }

        path.remove(path.size()-1);
    }

    public static void main(String []args){
        Node root = new Node("endUserIDs");
        List<String> result = new ArrayList<>();
        findPaths(root, new ArrayList<>(),result);
    }

}

Q2. SQL: Compute diff between 2 datasets

        Customer A bringing full snapshot S1 on Day 1, S2 on Day 2, S3 on Day 3...

        table-S1 :

        identity|cluster-id|

        408-443|C1
        345-234|C2
        454-134|C1
        ...


        table-S2:

        identity|cluster-id|

        408-443|C1
        345-234|C3
        666-223|C5
        ...

        Result:
        --------

        345-234 | changed
        454-134 | deleted
        666-223 | added


        Q3.




4. Design Graph Store where you need to store all the related identities and given an identity , fetch all related ids.


Ingest: x1 -> x2
Call API using x1
Response : [x1, x2]
Ingeset : x3 -> x4
Call API using x4
Response : [x3, x4]
Ingest x1 -> x3
Call API using x1
Response : [x1,x2,x3,x4]

x1 - > x2, x3
x2 -> x1
x3->x4, x1
x4->x3

x2,x3,x1,x4

x1->x2,x3
x3->x4,x1
x4->x3
x2->x1

*/
