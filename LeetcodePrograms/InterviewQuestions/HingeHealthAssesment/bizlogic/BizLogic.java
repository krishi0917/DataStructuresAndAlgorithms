package LeetcodePrograms.InterviewQuestions.HingeHealthAssesment.bizlogic;

import LeetcodePrograms.InterviewQuestions.HingeHealthAssesment.model.NewObject;
import LeetcodePrograms.InterviewQuestions.HingeHealthAssesment.model.Request;
import LeetcodePrograms.InterviewQuestions.HingeHealthAssesment.model.TreeNode;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BizLogic {
    private TreeNode rootNode;

    public BizLogic(TreeNode rootNode){
        this.rootNode = rootNode;

    }

    public boolean addChild(Request requestBody, long childId) {
        long parentId = requestBody.parentId;
        String childName = requestBody.labelName;
        boolean isAdded = FindParentAndAddChild(parentId, childId, childName);
        if(isAdded){
            System.out.println("element added successfully ");
        }
        else{
            System.out.println("parent Not found");
        }
        return isAdded;
    }

    public boolean FindParentAndAddChild(long parentId, long childId, String childName){
        TreeNode HeadNode = rootNode;
        if(parentId == HeadNode.getId()){
            HeadNode.getChildren().add(new TreeNode(childId, childName));
            return true;
        }
        boolean isAdded = false;

        // bfs
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(HeadNode);
        while(!queue.isEmpty() || !isAdded){
            TreeNode currentNode = queue.poll();
            if(currentNode.getId() == parentId ){
                currentNode.getChildren().add(new TreeNode(childId,childName));
                isAdded = true;
            }else{
                List<TreeNode> currentChildren = currentNode.getChildren();
                queue.addAll(currentChildren);
            }
        }
        return isAdded;
    }
}
