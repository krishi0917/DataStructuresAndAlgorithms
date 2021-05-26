package DesignPatterns.SingletonEnum;

//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

/**
 * Created by rkhurana on 11/8/18.
 */
public class Singleton {
    private static Singleton instance;
    private Singleton(){

    }
    public static Singleton getInstance(){
        if(instance == null)
            instance = new Singleton();
        return instance;

    }
}
