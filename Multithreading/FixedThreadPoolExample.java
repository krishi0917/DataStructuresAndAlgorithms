package Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by rkhurana on 12/1/18.
 */
public class FixedThreadPoolExample {

    public static void main(String []args){
// you dont give the size because you are not in the control of the number of threads you create. Also, it creates a thread if
// it is idle for more than 60 sec
//        ExecutorService service = Executors.newCachedThreadPool();
        ExecutorService service = Executors.newFixedThreadPool(10);

        for(int i = 0 ; i < 100 ; i++)
            service.execute(new Task());
    }
}


class Task implements Runnable {
    public void run() {
        System.out.println("Thread Name " + Thread.currentThread().getName());
    }
}