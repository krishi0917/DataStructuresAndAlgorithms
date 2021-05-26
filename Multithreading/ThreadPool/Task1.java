package Multithreading.ThreadPool;

/**
 * Created by rkhurana on 12/1/18.
 */
public class Task1 implements Runnable {
    public void run(){
        System.out.print("Performing task1");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
