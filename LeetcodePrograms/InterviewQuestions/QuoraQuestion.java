//package LeetcodePrograms;
//
///**
// * @author Rishi Khurana
// */
//public class QuoraQuestion {
//
//
//    https://www.geeksforgeeks.org/minimum-incrementdecrement-to-make-array-non-increasing/
//    class MyCode {
//
//        multiple inputs
//        abe sun raha hai ?
//        static PriorityQueue<Integer> pq = new PriorityQueue<Integer>(initCapacity, new Comparator<Integer>() {
//            public int compare(Integer n1, Integer n2) {
//                return n2 - n1;
//            }
//        })
//        kya ?
//        multiple hai arrays
//        Static int count = 0;
//        Static long initialSum = 0;
//
//        public static void AddToPq (PriorityQueue<Integer> pq, int count) {
//            if (pq.isEmpty()) {
//                return;
//            }
//
//            int largest = pq.poll();
//            if (largest <= 1) {
//                return;
//            }
//
//            int remainingSum = initialSum - largest;
//            int diff = largest - remainingSum;
//            pq.add(diff);
//            initialSum = remainingSum + diff; // 5, 3
//            count++; // 2
//            AddToPq(pq, count);
//        }
//
//        total time complexity ?
//        kar changes
//        public static int AllOnes (List<List<Integer>> list) {
//
//            Iterator iter = list.Iterator();
//
//            while (iter.hasNext()) {
//                list<Integer> intList = iter.next();
//                PriorityQueue<Integer> pq = new PriorityQueue<Integer>(initCapacity, new Comparator<Integer>() {
//                    public int compare(Integer n1, Integer n2) {
//                        return n2 - n1;
//                    }
//                })
//                int initialSum = 0;
//                int count = 0;
//                Iterator intIter = intList.iterator();
//                while (intIter.hasNext()) {
//                    int value = intIter.next()
//                    pq.add(value);
//                }
//                AddToPq(pq, count);
//            }
//
//            for (int i = 0; i < a.length; i++) {
//                pq.add(a[i]);
//                initialSum += a[i];
//            }
//            AddToPq(pq, count);
//            return count;
//        }
//
//
//        public static int DecreasingArray(int a[], int n)
//        {
//            int sum = 0, dif = 0;
//
//            PriorityQueue<Integer> pq = new PriorityQueue<>();
//            ab aayi
//            // Here in the loop we will
//            // check that whether the upcoming
//            // element of array is less than top
//            // of priority queue. If yes then we
//            // calculate the difference. After
//            // that we will remove that element
//            // and push the current element in
//            // queue. And the sum is incremented
//            // by the value of
//            phir se
//            code kar isme changes
//            geeksforgeeks exact nahi ahia
//            for (int i = 0; i < n; i++)
//            {
//                if (!pq.isEmpty() && pq.element() < a[i])
//                {
//                    dif = a[i] - pq.element();
//                    sum += dif;
//                    pq.remove();
//                    pq.add(a[i]);
//                }
//                pq.add(a[i]);
//            }
//
//            return sum;
//        }
//
//        // Driver Code
//        public static void main(String[] args)
//        {
//
//            int[] a = {3, 1, 2, 1};
//            List<List<Integer>> list = new ArrayList();
//
//
//            int n = a.length;
//
//            System.out.println(DecreasingArray(a, n));
//        }
//    }
//
//
//}
