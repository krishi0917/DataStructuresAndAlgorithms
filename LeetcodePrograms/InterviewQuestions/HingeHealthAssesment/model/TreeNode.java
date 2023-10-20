package LeetcodePrograms.InterviewQuestions.HingeHealthAssesment.model;
import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    long id;
    String name;
    List<TreeNode> children;
    public TreeNode(long id, String name){
        this.id = id;
        this.name = name;
        children = new ArrayList<>();
    }

    public List<TreeNode> getChildren(){
        return children;
    }

}
