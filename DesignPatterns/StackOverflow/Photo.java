package DesignPatterns.StackOverflow;

import java.util.Date;

/**
 * Created by rkhurana on 12/3/18.
 */

public class Photo {
    private int photoId;
    private String photoPath;
    private Date creationDate;

    private Member creatingMember;

    public boolean delete() {
        return false;
    }
}

