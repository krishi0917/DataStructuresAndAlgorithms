package LeetcodePrograms;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


/**
 * @author Rishi Khurana
 * Rearrange characters in a string such that no two adjacent are same
 * * Given a string with repeated characters, the task is to rearrange characters in a string so that no two adjacent
 * characters are same.
 *
 * Note : It may be assumed that the string has only lowercase English alphabets.
 *
 * Examples:
 *
 * Input: aaabc
 * Output: abaca
 *
 * Input: aaabb
 * Output: ababa
 *
 * Input: aa
 * Output: Not Possible
 *
 * Input: aaaabc
 * Output: Not Possible
 */

    // Solution We have to follow the step to solve this problem, they are:
    // 1. Build a Priority_queue or max_heap, pq that stores characters and their frequencies.
    // …… Priority_queue or max_heap is built on the bases of the frequency of character.
    // 2. Create a temporary Key that will be used as the previously visited element (the previous element in the
    // resultant string. Initialize it { char = ‘#’ , freq = ‘-1’ }
    // 3. While pq is not empty.
    // ….. Pop an element and add it to the result.
    // ….. Decrease frequency of the popped element by ‘1’
    // ….. Push the previous element back into the priority_queue if it’s frequency > ‘0’
    // ….. Make the current element as the previous element for the next iteration.
    // 4. If the length of the resultant string and original string is not equal, print “not possible”. Else print
    //  result.

    class KeyComparator implements Comparator<Key> {

        // Overriding compare()method of Comparator
        public int compare(Key k1, Key k2)
        {
            if (k1.freq < k2.freq)
                return 1;
            else if (k1.freq > k2.freq)
                return -1;
            return 0;
        }
    }

    class Key {
        int freq; // store frequency of character
        char ch;
        Key(int val, char c)
        {
            freq = val;
            ch = c;
        }
    }

public class RearrangeCharacters {
        static int MAX_CHAR = 26;

        // Function to rearrange character of a string so that no char repeat twice
        static void rearrangeString(String str)
        {
            int n = str.length();

            // Store frequencies of all characters in string
            int[] count = new int[MAX_CHAR];

            for (int i = 0; i < n; i++)
                count[str.charAt(i) - 'a']++;

            // Insert all characters with their frequencies into a priority_queue
            PriorityQueue<Key> pq = new PriorityQueue<>(new KeyComparator());
            for (char c = 'a'; c <= 'z'; c++) {
                int val = c - 'a';
                if (count[val] > 0)
                    pq.add(new Key(count[val], c));
            }

            // 'str' that will store resultant value
            str = "" ;

            // work as the previous visited element initial previous element be. ( '#' and it's frequency '-1' )
            Key prev = new Key(-1, '#');

            // traverse queue
            while (pq.size() != 0) {

                // pop top element from queue and add it to string.
                Key k = pq.peek();
                pq.poll();
                str = str + k.ch;

                // If frequency of previous character is less than zero that means it is useless, we
                // need not to push it
                if (prev.freq > 0)
                    pq.add(prev);

                // make current character as the previous 'char' decrease frequency by 'one'
                (k.freq)--;
                prev = k;
            }

            // If length of the resultant string and original string is not same then string is not valid
            if (n != str.length())
                System.out.println(" Not valid String ");
            else
                System.out.println(str);
        }

        // Driver program to test above function
        public static void main(String args[])
        {
            String str1 = "bbbaa" ;
            String str2 = "aaabc";
           // Output: abaca

            String str3 = "paaabb";
            //Output: ababa

            String str4 = "aa";
            //Output: Not Possible

            String str5 = "aaaabc";
            //Output: Not Possible
            //rearrangeCharacters(str1);
//            rearrangeCharacters(str2);
//            rearrangeCharacters(str3);
//            rearrangeCharacters(str4);
            rearrangeCharacters(str5);
        }


    //PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.size(), new Comparator<ListNode>() {

        public  static void rearrangeCharacters(String s){
            PriorityQueue<CountFreq> pq = new PriorityQueue<>(s.length(), new Comparator<CountFreq>() {
                @Override
                public int compare (CountFreq o1,  CountFreq o2) {
                    Integer c1 = o1.count;
                    Integer c2 = o2.count;
                    return c2.compareTo(c1);
                }
            });
            Map<Character,Integer> map = new HashMap<>();
            for(int i = 0 ; i < s.length(); i++ ){
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i),0)+1);
            }
            for(Map.Entry<Character,Integer> chMap : map.entrySet()){
                pq.offer(new CountFreq(chMap.getKey() , chMap.getValue()));
            }

            StringBuilder sb = new StringBuilder();
            while(pq.size() != 0 ){
                CountFreq element = pq.poll();
                sb.append(element.key);
                element.count--;
                if(element.count>0)
                    pq.offer(element);

            }
            if(sb.length() != s.length())
                System.out.println("not valid string");
            else
                System.out.println("Valid string" + sb);
        }
    }
class CountFreq{
    char key;
    int count;
    public CountFreq(char k , int c){
        key = k;
        count = c;
    }

}