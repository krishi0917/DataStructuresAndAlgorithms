package DesignPatterns.StackOverflow;

/**
 * Created by rkhurana on 12/3/18.
 */
public class Moderator extends Member {
    public boolean closeQuestion(Question question) {
        return false;
    }

    public boolean undeleteQuestion(Question question) {
        return false;
    }
}