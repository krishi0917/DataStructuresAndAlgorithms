package LeetcodePrograms;

/**
 * Created by rkhurana on 3/13/19.
 */
public class ReverseNodesKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return null;

        int len = 0;
        ListNode l = head; //len is the length of this list
        while(l != null)
        {
            len++;
            l = l.next;
        }

        int round = len / k;   //cut the list, so we have 'round' lists with size k
        if(round == 0) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        for(int i = 0; i < round; i++)
        {
            //for each list with size k, reverse it
            ListNode start = pre.next;
            ListNode then = start.next;
            for(int j = 0; j < k-1; j++)
            {
                start.next = then.next;
                then.next = pre.next;
                pre.next = then;
                then = start.next;
            }
            pre = start;
        }
        return dummy.next;
    }

}
