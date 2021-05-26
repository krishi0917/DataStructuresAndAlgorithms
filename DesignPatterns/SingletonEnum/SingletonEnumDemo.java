package DesignPatterns.SingletonEnum;

/**
 * Created by rkhurana on 11/4/18.
 */
public class SingletonEnumDemo {
    public static void main(String[] args) {
        SingletonEnum singleton = SingletonEnum.INSTANCE;
        System.out.println(singleton.getValue());
        singleton.setValue(2);
        System.out.println(singleton.getValue());
    }
}
