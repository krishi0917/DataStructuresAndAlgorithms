package LeetcodePrograms.src;

public class ListNode {

	public ListNode next;

	public int val;

	public ListNode(int x) {
		val = x;
	}
	ListNode(){}

	public ListNode newNode(int i) {
		ListNode x = new ListNode();
		x.val = i;
		return x;

	}
}
