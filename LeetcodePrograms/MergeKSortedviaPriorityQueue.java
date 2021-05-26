package LeetcodePrograms;



import java.util.*;

/**
 * Created by rkhurana on 6/16/18.
 */
public class MergeKSortedviaPriorityQueue {
    public static ListNode mergeKLists(List<ListNode> lists) {
        if (lists==null||lists.size()==0) return null;

        PriorityQueue<ListNode> queue= new PriorityQueue<>(lists.size(),new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val<o2.val)
                    return -1;
                else if (o1.val==o2.val)
                    return 0;
                else
                    return 1;
            }
//            Integer I1 = o1.val;
//            Integer I2 = o2.val;
//            return I1.compareTo(I2);
//            }
        });

        ListNode dummy = new ListNode(0);
        ListNode tail=dummy;

        for (ListNode node:lists)
            if (node!=null)
                queue.add(node);

        while (!queue.isEmpty()){
            tail.next=queue.poll();
            tail=tail.next;
            System.out.println(tail.next);
            if (tail.next!=null)
                queue.add(tail.next);
        }
        return dummy.next;
    }

    public static void main(String [] args){
// n = 6 -> 7 -> 9
// m = 2 -> 3 -> 15
// o = 1 -> 8 -> 10
        ListNode n =new ListNode();

//        n.newNode(1);
        n = n.newNode(6);
        n.next=n.newNode(7);
        n.next.next = n.newNode(9);

        ListNode m = new ListNode();
        m=m.newNode(2);
        m.next = m.newNode(3);
        m.next.next = m.newNode(15);

        ListNode o = new ListNode();
        o = o.newNode(1);
        o.next=o.newNode(8);
        o.next.next =o.newNode(10);

        List<ListNode> lists= new ArrayList<>();
        lists.add(n);
        lists.add(m);
        lists.add(o);
        System.out.println(mergeKLists(lists));
    }


}
