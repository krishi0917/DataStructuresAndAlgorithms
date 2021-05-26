package DesignPatterns.StackOverflow;

import java.util.Date;

/**
 * Created by rkhurana on 12/3/18.
 */
public class Comment {
    private String text;
    private Date creationTime;
    private int flagCount;
    private int voteCount;

    private Member askingMember;

    public boolean incrementVoteCount(){return false;}
}

