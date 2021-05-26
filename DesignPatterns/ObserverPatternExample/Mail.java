package DesignPatterns.ObserverPatternExample;

/**
 * Created by rkhurana on 11/11/18.
 */
public class Mail {
    String receiverName;
    String address;
    String content;
    public  Mail(String address , String receiverName , String content){
        this.address = address;
        this.content = content;
        this.receiverName = receiverName;


    }
}
