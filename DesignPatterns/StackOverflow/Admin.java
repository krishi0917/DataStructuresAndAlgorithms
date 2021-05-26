package DesignPatterns.StackOverflow;

/**
 * Created by rkhurana on 12/3/18.
 */
public class Admin extends Member {
    public boolean blockMember(Member member) {
        return false;
    }

    public boolean unblockMember(Member member) {
        return false;
    }
}