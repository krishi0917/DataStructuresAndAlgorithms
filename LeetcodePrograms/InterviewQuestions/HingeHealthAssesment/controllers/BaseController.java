package LeetcodePrograms.InterviewQuestions.HingeHealthAssesment.controllers;

import LeetcodePrograms.InterviewQuestions.HingeHealthAssesment.bizlogic.BizLogic;
import LeetcodePrograms.InterviewQuestions.HingeHealthAssesment.model.*;

import java.util.*;

public class BaseController {
    TreeNode rootNode;
    private long currentId;

    public BaseController(){
        rootNode = new TreeNode(1,"root");
        rootNode.setChildren(new ArrayList<>());
        currentId = 1;
    }
    public boolean postTree(Request request){
        BizLogic bizLogic = new BizLogic(rootNode);
        currentId++;
         return bizLogic.addChild(request, currentId);
    }

    // @GetMapping("/api/tree")
    public void getTreeStructure() {
        TreeNode root = rootNode;
        NewResult newResult = new NewResult();
        dfshelper2(root, newResult);
        System.out.println(newResult);
    }

    public void dfshelper2(TreeNode node, NewResult newResult) {
        if (node == null) {
            return;
        }

        NewObject newObject = new NewObject();
        newObject.setLabel(node.getName());

        Map<Long, NewObject> map = new HashMap<>();
        List<NewResult> list = new ArrayList<>();
        for (TreeNode treeNode : node.getChildren()) {
            NewResult nr = new NewResult();
            dfshelper2(treeNode, nr);
            list.add(nr);
        }
        newObject.setChildren(list);
        map.put(node.getId(), newObject);
        newResult.setMap(map);
    }

    // @PostMapping("/api/tree")
    public void rpostTree(Request requestBody){
        BizLogic bizLogic = new BizLogic(rootNode);
        currentId++;
        bizLogic.addChild(requestBody, currentId);

    }
}
