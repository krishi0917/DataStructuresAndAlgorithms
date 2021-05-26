package LeetcodePrograms;
import java.util.*;
/**
 * Created by rkhurana on 3/17/19.
 */
public class SerializeDeserializeNaryTree {

    public static class NaryTreeNode {
        String data;
        List<NaryTreeNode> children = null;

        public NaryTreeNode(String data) {
            this.data = data;
            this.children = new ArrayList<>();
        }

        public void addChild(NaryTreeNode child) {
            this.children.add(child);
        }
    }

    String NN="X";
    String spliter=",";

    // Encodes a tree to a single string.
    public String serialize(NaryTreeNode root) {
        StringBuilder sb=new StringBuilder();
        buildString(root,sb);
        return sb.toString();
    }
    private void buildString(NaryTreeNode node, StringBuilder sb){
        if(node==null){
            sb.append(NN);
            sb.append(spliter);
        }else{
            sb.append(node.data);
            sb.append(spliter);
            sb.append(node.children.size());
            sb.append(spliter);
            for (NaryTreeNode child:node.children){
                buildString(child,sb);
            }
        }
    }

    // Decodes your encoded data to tree.
    public NaryTreeNode deserialize(String data) {
        Deque<String> deque=new ArrayDeque<>(Arrays.asList(data.split(spliter)));
        return buildTree(deque);
    }

    private NaryTreeNode buildTree(Deque<String> deque){
        String s1=deque.removeFirst();
        if(s1.equals(NN))
            return null;

        int rootVal=Integer.valueOf(s1);
        int childrenNumber=Integer.valueOf(deque.removeFirst());

        NaryTreeNode root=new NaryTreeNode(String.valueOf(rootVal));
        root.children=new ArrayList<>();
        for (int i=0; i<childrenNumber; i++){
            root.children.add(buildTree(deque));
        }
        return root;
    }
    public static void main(String []args){
        SerializeDeserializeNaryTree nAryTree = new SerializeDeserializeNaryTree();
        //NaryTreeNode tree = new NaryTreeNode("1");
        NaryTreeNode root = new NaryTreeNode("5");
        NaryTreeNode ch1 = new NaryTreeNode("8");
        NaryTreeNode ch2 = new NaryTreeNode("9");
        NaryTreeNode ch3 = new NaryTreeNode("11");
        root.addChild(ch1);
        root.addChild(ch2);
        root.addChild(ch3);
        NaryTreeNode ch4 = new NaryTreeNode("13");
        NaryTreeNode ch5 = new NaryTreeNode("17");
        NaryTreeNode ch6 = new NaryTreeNode("18");
        NaryTreeNode ch7 = new NaryTreeNode("20");
        NaryTreeNode ch8 = new NaryTreeNode("22");
        ch1.addChild(ch4);
        ch1.addChild(ch5);
        ch4.addChild(ch6);
        ch6.addChild(ch7);
        ch7.addChild(ch8);
//        NaryTreeNode ch9 = new NaryTreeNode("23");
//        NaryTreeNode ch10 = new NaryTreeNode("25");
//        NaryTreeNode ch11 = new NaryTreeNode("27");
//        NaryTreeNode ch12 = new NaryTreeNode("28");
//        NaryTreeNode ch13 = new NaryTreeNode("32");
//        NaryTreeNode ch14 = new NaryTreeNode("31");
//        NaryTreeNode ch15 = new NaryTreeNode("33");
//        NaryTreeNode ch16 = new NaryTreeNode("34");
//        NaryTreeNode ch17 = new NaryTreeNode("29");
//        ch5.addChild(ch9);
//        ch3.addChild(ch10);
//        ch3.addChild(ch11);
//        ch10.addChild(ch12);
//        ch10.addChild(ch14);
//        ch12.addChild(ch13);
//        ch14.addChild(ch15);
//        ch14.addChild(ch16);
//        ch14.addChild(ch17);
        System.out.println(nAryTree.serialize(root));
    }
}
