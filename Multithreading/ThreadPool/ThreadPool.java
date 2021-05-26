package Multithreading.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.*;

/**
 * Created by rkhurana on 12/1/18.
 */
public class ThreadPool {
    private BlockingQueue taskQueue = null ;
    private List<PoolThread> threads = new ArrayList<>();
    private boolean isStopped = false;

    public ThreadPool(int noOfThreads , int maxNoOfTasks){
        taskQueue = new ArrayBlockingQueue(maxNoOfTasks);
        for(int i= 0 ;i < noOfThreads  ; i++){
            threads.add(new PoolThread(taskQueue));
        }

        for(PoolThread thread : threads){
            thread.start();
        }
    }

    public synchronized void execute(Runnable task){ //task that needs to be executed, put them in the task queue
        if(this.isStopped)  //dont put any more task
            throw new IllegalArgumentException("threadpool stopped");
        try{
            this.taskQueue.put(task);  //put the task into the taskqueue
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized  void stop(){
        this.isStopped=true; //stop thread pool
        for(PoolThread thread : threads){
            thread.stopThread(); //stop all threads
        }
    }
}
