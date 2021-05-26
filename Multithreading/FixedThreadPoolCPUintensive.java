package Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by rkhurana on 12/1/18.
 */
public class FixedThreadPoolCPUintensive {

        public static void main(String []args){
            // get the count of the availabel cores
            int coreCount = Runtime.getRuntime().availableProcessors();
            ExecutorService service = Executors.newFixedThreadPool(coreCount);

            //submit the task for execution
            for(int i = 0 ; i < 100 ; i++) {
                service.execute(new CPUExtensiveTask());
            }
        }
    }



class CPUExtensiveTask implements Runnable {
    public void run() {
        //Some CPU extensive operations
        System.out.println("Thread Name " + Thread.currentThread().getName());
    }
}