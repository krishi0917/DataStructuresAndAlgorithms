package DesignPatterns.ObserverPatternExample;

//import javafx.geometry.Pos;
//import jdk.nashorn.internal.scripts.JO;

/**
 * Created by rkhurana on 11/11/18.
 */
public class Main {
    public static void main(String []args) {
        PostOffice postOffice = new PostOffice();
        Person Chris = new Person("Chris");
        Person John = new Person("John");
        Mail m = new Mail("Dundee", "John", "Your paycheck");
        postOffice.Attach(Chris);
        postOffice.Attach(John);
        postOffice.addMail(m);
    }
}
