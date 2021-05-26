package DesignPatterns.ObserverPatternExample;

import java.util.ArrayList;

/**
 * Created by rkhurana on 11/11/18.
 */
public class Person implements  Observer{

    String name;
    public  Person(String name ){
        this.name = name;
    }

    @Override
    public void update(Object o) {
        if(o instanceof  PostOffice){
            PostOffice po = (PostOffice)o;
            checkMail(po.getState());
        }
    }

    private void checkMail(ArrayList<Mail> m) {
        for(int i = 0 ; i < m.size() ; i ++){
            if(name.compareTo(m.get(i).receiverName) == 0 )
                System.out.print(name + ": " + m.get(i).content);
        }
    }
}
