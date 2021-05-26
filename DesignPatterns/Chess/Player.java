package DesignPatterns.Chess;

/**
 * Created by rkhurana on 12/3/18.
 */
public class Player {
    private Person person;
    private boolean whiteSide = false;

    public Player(Person person, boolean whiteSide){
        this.person = person;
        this.whiteSide = whiteSide;
    }

    public boolean isWhiteSide() {
        return this.whiteSide == true;
    }
}