package Multithreading.LockObjects;

public class App {
	 public static void main(String[] args) {
	        System.out.println("Multithreading.Synchronized Objects: ");
	        Worker worker = new Worker();
	        worker.main();
	        System.out.println("Multithreading.Synchronized Methods: ");
	        WorkerMethodsSynchronized worker2 = new WorkerMethodsSynchronized();
	        worker2.main();
	    }
	}