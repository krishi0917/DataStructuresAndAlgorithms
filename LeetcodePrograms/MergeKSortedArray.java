package LeetcodePrograms;

/**
 * Created by rkhurana on 6/16/18.
 */

import java.util.*;

/**
 * Created by rkhurana on 6/16/18.
 */
public class MergeKSortedArray {
    public class MergedContainer implements Comparable<LeetCode.MergedContainer>{
        int[] arr;
        int index;


        public MergedContainer(int[] arr, int index) {
            this.arr = arr;
            this.index = index;
        }



        public int compareTo(LeetCode.MergedContainer o) {
            return this.arr[this.index] - o.arr[o.index];
        }
    }
    public static int mergeKLists(int [][] array) {
        if (array==null||array.length==0) return 0;
        PriorityQueue<Integer> queue= new PriorityQueue<Integer>(array.length,new Comparator<Integer>(){
            @Override
        public int compare(Integer o1,Integer o2){
            if (o1<o2)
                return -1;
            else if (o1 ==o2)
                return 0;
            else
                return 1;
            }
        });
        PriorityQueue<MergedContainer> queue2= new PriorityQueue<MergedContainer>();
        for(int i=0;i < array.length;i++){
            for(int j=0;j<array[0].length;j++){
                queue.add(array[i][j]);
            }
        }

           while(!queue.isEmpty())
            System.out.println(queue.poll());
//        for(int i = 0 ; i  < array.length ; i++)
//        queue2.add(new MergedContainer(array[i],0));
//
//        while(!queue2.isEmpty()){
//            System.out.println(queue2.poll());
//        }
//        return null;
        return 0;
    }

    public static void main(String [] args){
        int arr[][] =  {{2, 6, 12, 34},
            {1, 9, 20, 1000},
            {23, 34, 90, 2000}};
        System.out.println(mergeKLists(arr));
    }


}
