package LeetcodePrograms;
import java.io.*;
/**
 * @author Rishi Khurana
 */
public class ImplementQueueUsingLinkedList {
    /* package whatever; // don't place package name! */


    /* Name of the class has to be "Main" only if the class is public. */
        static class Queue <T>{

            static class Node<T>{
                Node next;
                T val;
                public Node(T val){
                    this.val = val;
                    next = null;
                }
            }

            Node<T> head = null;
            Node<T> tail = null;
            int currentlength = 0;
            int totalLength;

            public Queue(int size){
                totalLength = size;

            }

            public  void push( T element){

                if( head == null){
                    Node<T> node = new Node(element);
                    //  node.val = element;
                    head = node;
                    tail = node;
                    currentlength++;
                }else{
                    if(currentlength < totalLength){
                        Node<T> n = new Node (element);
                        tail.next = n;
                        tail = tail.next;
                        currentlength++;
                    }
                }
            }

            public synchronized T pop(){

                if(head == null){
                    return null;
                }
                T poppedElement = head.val;
                head = head.next;
                currentlength--;
                return poppedElement;
            }

            public T peek(){
                if(head == null){
                    return null;
                }
                return head.val;
            }

            public boolean isEmpty(){

                return currentlength == 0;

            }

            public int size(){
                return currentlength;

            }

        }


        public static void main (String[] args)
        {
            System.out.println("Hello World!");

            Queue<String> q = new Queue(5);

            q.pop();
            q.push("one");
            q.push("two");
            q.push("three");


            System.out.println("Front element is: " + q.peek());
            q.pop();
            System.out.println("Front element is: " + q.peek());

            System.out.println("Queue size is " + q.size());

            q.pop();
            q.pop();

            if (q.isEmpty())
                System.out.println("Queue Is Empty");
            else
                System.out.println("Queue Is Not Empty");



        }
    }

