package DesignPatterns.SingletonEnum;

/**
 * Created by rkhurana on 11/4/18.
 */
public enum SingletonEnum {
    INSTANCE;
    int value;
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
