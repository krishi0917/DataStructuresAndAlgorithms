package LeetcodePrograms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Example {
    static int window;
    public interface prototype{

         int next();
    }
//    public class NewClass implements prototype {
//
//        List<Integer> list = new ArrayList<>();
//        public int next() {
//            int min = 1;
//            int max = 100;
//            int randomNum = (int) ((Math.random() * ((max - min) + 1)) + min);
//            return randomNum;
//        }
//
//        public void InsertElement() {
//            list.add(next());
//        }
//
//    }

    public Example(){
        window = 5;
    }
    static int foo(){
        prototype p = new prototype() {
            @Override
            public int next() {
                int min = 1;
                int max = 100;
                return (int) ((Math.random() * ((max - min) + 1)) + window);
            }
        };
        return p.next();
    }

    public void Insert(){
        foo();
    }



    public static void main(String []args){
        foo();


    }
}
