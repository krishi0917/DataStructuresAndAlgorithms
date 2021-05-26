package DesignPatterns.StackOverflow;

import java.util.Date;
import java.util.*;

/**
 * Created by rkhurana on 12/3/18.
 */

public class Question implements Search {
    private String title;
    private String description;
    private int viewCount;
    private int voteCount;
    private Date creationTime;
    private Date updateTime;
    private QuestionStatus status;
    private QuestionClosingRemark closingRemark;

    private Member askingMember;
    private Bounty bounty;
    private List<Photo> photos;
    private List<Comment> comments;
    private List<Answer> answers;

    public boolean close() {
        return false;
    }

    public boolean undelete() {
        return false;
    }

    public boolean addComment(Comment comment) {
        return false;
    }

    public boolean addBounty(Bounty bounty) {
        return false;
    }

    public  List<Question> search(String query) {
        // return all questions containing the string query in their title or description.
        return null;
    }
}


