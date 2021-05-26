package Multithreading.ThreadPool;

/**
 * Created by rkhurana on 12/1/18.
 */
public class Task4 implements Runnable {
    public void run(){
        System.out.print("Performing task4");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
