package LeetcodePrograms;

/**
 * Created by rkhurana on 3/21/19.
 */
public class MergeKSortedviaMergeSort {
    public ListNode mergeKLists(ListNode[] lists) {
        // corner case
        if(lists == null || lists.length == 0) return null;

        ListNode head = merge(lists, 0, lists.length - 1);
        return head;
    }

    // return new head after merging list[left : right]
    public ListNode merge(ListNode[] lists, int left, int right){
        // base case
        if(left == right) return lists[left];

        int mid = left + (right - left)/2;
        ListNode l1 = merge(lists, left, mid); // merge [left : mid]
        ListNode l2 = merge(lists, mid + 1, right); // merge [mid + 1 : right]

        return mergeTwoLists(l1, l2);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        ListNode cur1 = l1;
        ListNode cur2 = l2;
        while(cur1 != null || cur2 != null){
            if(cur1 == null){
                cur.next = cur2;
                break;
            }else if(cur2 == null){
                cur.next = cur1;
                break;
            }else{
                if(cur1.val <= cur2.val){
                    cur.next = cur1;
                    cur1 = cur1.next;
                }else{
                    cur.next = cur2;
                    cur2 = cur2.next;
                }
                cur = cur.next;
            }

        }

        return dummy.next;
    }
    public static void main(String [] args){
// a = 6 -> 12 -> 14
// b = 2 -> 8 -> 15
// c = 1 -> 9 -> 10
// d = 3 -> 13 -> 19
        MergeKSortedviaMergeSort x = new MergeKSortedviaMergeSort();
        ListNode a =new ListNode();

//        n.newNode(1);
        a = a.newNode(6);
        a.next=a.newNode(12);
        a.next.next = a.newNode(14);

        ListNode b = new ListNode();
        b=b.newNode(2);
        b.next = b.newNode(8);
        b.next.next = b.newNode(15);

        ListNode c = new ListNode();
        c = c.newNode(1);
        c.next=c.newNode(9);
        c.next.next =c.newNode(10);

        ListNode d = new ListNode();
        d= d.newNode(3);
        d.next=d.newNode(13);
        d.next.next =d.newNode(19);

        ListNode [] lists = {a,b,c,d};
//        lists.add(n);
//        lists.add(m);
//        lists.add(o);
        System.out.println(x.mergeKLists(lists));
    }
}
