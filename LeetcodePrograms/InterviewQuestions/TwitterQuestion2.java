package LeetcodePrograms.InterviewQuestions;

import java.util.*;

/* If we represent a conversation as an N-ary tree, then we can define an "engagement" as a user replying to other people’s comments to a Tweet he/she made
  Given the root node to a conversation tree, determine how many times the author is engaged in the entire conversation.
  Definition:‹‹
   Engaged = A -> (N # of !A nodes) -> A
    Input: Root Node
    Output: number

  https://docs.google.com/presentation/d/1mnXM7dglHulbkqeU48UO0kaYvpFPw0POyTTDbOP0sRE/edit#slide=id.p


 self.username = username -> string
   self.children = children -> List<NaryNode>
*/
public class TwitterQuestion2 {

    static class NaryNode {
        String username;
        List<NaryNode> children;

        public NaryNode(String data) {
            this.username = data;
            this.children = new ArrayList<>();
        }

        public void addChildren(NaryNode child) {
            this.children.add(child);
        }
    }

    int count = 0;

    public int findEngagemntRecursion(NaryNode node) {
        if (node == null) {
            return 0;
        }
        findEngagemntUtil(node.children, node.username, false);
        return count;
    }

    public void findEngagemntUtil(List<NaryNode> node, String user, boolean flag) {
        if (node.size() == 0) {
            return;
        }

        for (int i = 0; i < node.size(); i++) { // B side --> C
            if (node.get(i).username.equals(user)) { //
                if (flag == true) { // Note: what is two A comes then dont know what to do..
                    count++;     // count =1
                    List<NaryNode> childNodes = node.get(i).children;
                    if (childNodes.size() == 0) {
                        return;
                    }
                    findEngagemntUtil(childNodes, user, false);
                }
            } else {
                //username is different
                List<NaryNode> childNodes = node.get(i).children;
                findEngagemntUtil(childNodes, user, true);
            }
        }
    }

    public static void main(String[] args) {

        NaryNode nodeA1 = new NaryNode("A");
        NaryNode nodeA2 = new NaryNode("A");
        NaryNode nodeA3 = new NaryNode("A");
        NaryNode nodeA4 = new NaryNode("A");
        NaryNode nodeA5 = new NaryNode("A");

        NaryNode nodeB1 = new NaryNode("B");
        NaryNode nodeB2 = new NaryNode("B");
        NaryNode nodeB3 = new NaryNode("B");
        NaryNode nodeB4 = new NaryNode("B");

        NaryNode nodeC1 = new NaryNode("C");
        NaryNode nodeC2 = new NaryNode("C");
        NaryNode nodeC3 = new NaryNode("C");
        NaryNode nodeC4 = new NaryNode("C");

        NaryNode nodeD1 = new NaryNode("D");

        nodeC1.addChildren(nodeA2);
        nodeB1.addChildren(nodeC1);
        nodeA1.addChildren(nodeB1);

        nodeC2.addChildren(nodeA3);
        nodeA4.addChildren(nodeC2);
        nodeA4.addChildren(nodeD1);

        nodeC3.addChildren(nodeA4);
        nodeA1.addChildren(nodeC3);

        TwitterQuestion2 s = new TwitterQuestion2();
        int count1 = s.findEngagemntRecursion(nodeA1);
        int count2 = s.findEngagemntIterative(nodeA1);
        System.out.println(count2);
    }

    static class Item {
        boolean flag;
        NaryNode node;

        public Item(NaryNode n, boolean f) {
            node = n;
            flag = f;
        }
    }
// check recursive once, its not working
    public int findEngagemntIterative(NaryNode node) {
        int sum=0;
        String nodeUser = node.username;
        Queue<Item> queue = new LinkedList<>();
        queue.add(new Item(node, false));
        while (!queue.isEmpty()) {
            Item i = queue.poll();
            if (i.flag == false) {
                List<NaryNode> naryNodes = i.node.children;
                for (NaryNode tempNode : naryNodes) {
                    if (tempNode.username.equals(nodeUser)) {
                        queue.add(new Item(tempNode, false));
                    } else {
                        queue.add(new Item(tempNode, true));
                    }
                }
            } else { //flag = true ; check if its the main user, then set to false and increment the count else keep it going
                boolean mainUserFound = false;
                if(i.node.username.equals(nodeUser)) {
                    sum++;
                    mainUserFound = true;
                }
                List<NaryNode> naryNodes = i.node.children;
                for (NaryNode tempNode : naryNodes) {
                    if(mainUserFound){
                        queue.add(new Item(tempNode, false));
                    }else{
                        queue.add(new Item(tempNode, true));
                    }

                }
            }
        }
        return sum;
    }
}
