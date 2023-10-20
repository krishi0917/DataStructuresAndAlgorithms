package LeetcodePrograms.InterviewQuestions.HingeHealthAssesment;

import LeetcodePrograms.InterviewQuestions.HingeHealthAssesment.controllers.BaseController;
import LeetcodePrograms.InterviewQuestions.HingeHealthAssesment.model.Request;

public class DemoApplication {

	public static void main(String[] args) {
		BaseController baseController = new BaseController();
		Request request2 = new Request(1,"ant");
		Request request3= new Request(1,"bear");
		Request request4= new Request(3,"cat");
		Request request5= new Request(3,"dog");
		Request request6 = new Request(5,"elephant");
		Request request7 = new Request(1,"frog");
		baseController.postTree(request2);
		baseController.postTree(request3);
		baseController.postTree(request4);

		baseController.postTree(request5);
		baseController.postTree(request6);
		baseController.postTree(request7);
		baseController.getTreeStructure();
	}
}
