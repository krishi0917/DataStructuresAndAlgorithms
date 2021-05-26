package DesignPatterns.StackOverflow;

import java.util.List;

/**
 * Created by rkhurana on 12/3/18.
 */
public  abstract class Member {
    private Account account;
    private List<Badge> badges;

    public int getReputation() {
        return 0;
    }

    public String getEmail() {
        return null;
    }

    public boolean createQuestion(Question question) {
        return false;
    }

    public boolean createTag(Tag tag) {
        return false;
    }
}
