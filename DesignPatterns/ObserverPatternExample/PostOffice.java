package DesignPatterns.ObserverPatternExample;

//import com.sun.tools.corba.se.idl.constExpr.Not;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by rkhurana on 11/11/18.
 */
public class PostOffice  implements  Subject{
    private ArrayList<Mail> allMail;
    private ArrayList<Observer> observers;
    public  PostOffice(){
        allMail = new ArrayList<Mail>();
        observers = new ArrayList<Observer>();
    }
    public void  addMail(Mail m){
        allMail.add(m);
        Notify();
    }
    public ArrayList<Mail> getState(){
        return  allMail;
    }

    @Override
    public void Attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void Dettach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void Notify() {
        for(int i = 0 ;  i < observers.size() ; i++)
            observers.get(i).update(this);

    }
}
