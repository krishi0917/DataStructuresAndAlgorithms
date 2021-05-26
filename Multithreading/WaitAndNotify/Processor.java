package Multithreading.WaitAndNotify;
import java.util.Scanner;

public class Processor {
    public void produce() throws InterruptedException {
        synchronized (this) { //it is an intrinsic lock
            System.out.println("Producer thread running ....");
            wait();//this.wait() is fine. //can be called only within the synchronised method and thus loosing the control of the lock
            //this will gain access when another calls the notify object
            System.out.println("Resumed.");
        }
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);
        synchronized (this) {
            System.out.println("Waiting for return key.");
            scanner.nextLine();
            System.out.println("Return key pressed.");
            notify();
            Thread.sleep(1000);
            System.out.println("Consumption done.");
        }
    }
}