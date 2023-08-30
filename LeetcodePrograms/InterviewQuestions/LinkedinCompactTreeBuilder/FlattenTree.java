package LeetcodePrograms.LinkedinCompactTreeBuilder;

import java.util.*;

public class FlattenTree implements CompactTreeBuilder
{
    public Node bfs(Queue<Node> queue) {
        Node node = queue.poll();
        List<Node> childrens = node.getChildren();
        for(Node next: childrens) {
            queue.offer(next);
        }
        return node;
    }

    @Override
    public Node compact(Node root, int N) {
        Node newRoot = new Node(root.getData(), new ArrayList<>());
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        bfs(queue);
        Queue<Node> newQueue = new LinkedList<>();
        newQueue.offer(newRoot);

        while (queue.size() > 0) {
            Node newNode = newQueue.poll();
            int index = 0;
            while (index < N) {
                if(queue.size() > 0) {
                    Node next = bfs(queue);
                    Node newNext = new Node(next.getData(), new ArrayList<>());
                    newNode.getChildren().add(newNext);
                    newQueue.offer(newNext);
                    index++;
                } else {
                    break;
                }
            }

        }
        return newRoot;
    }

    public static void main(String []args){
        FlattenTree flattenTree = new FlattenTree();


        List<Node> childFList = new ArrayList<>();
        Node childF = new Node("F",new ArrayList<>());
        childFList.add(childF);
        List<Node> childCEList = new ArrayList<>();

        List<Node> childDList = new ArrayList<>();
        Node childD = new Node("D",childFList);
        childDList.add(childD);
        List<Node> childGHlist = new ArrayList<>();
        Node childG = new Node("G",new ArrayList<>());
        Node childH = new Node("H",new ArrayList<>());
        childGHlist.add(childG);
        childGHlist.add(childH);
        Node childC = new Node("C",childDList);
        Node childE = new Node("E",childGHlist);

        childCEList.add(childC);
        childCEList.add(childE);
        Node childB = new Node ("B",childCEList);
        List<Node> childBList = new ArrayList<>();
        childBList.add(childB);
        Node A = new Node("A",childBList);

        Node newRoot = flattenTree.compact(A,1);
        System.out.println(newRoot);
    }
}

 /*
         * A               A                 A                         A
         *  |               |                 |_B                       |_B
         *  |_B             |_B                  |_C                    |
         *     |            |  |                    |_D                 |_C
         *     |            |  |_D                     |_E              |
         *     |            |  |                          |_F           |_D
         *     |_C          |  |_E                           |_G        |
         *     | |_D        |    |_H                            |_H     |_E
         *     |    |_F     |                                           |
         *     |            |_C                                         |_F
         *     |_E            |                                         |
         *       |_G          |_F                                       |_G
         *       |            |                                         |
         *       |_H          |_G                                       |_H
         */