package DesignPatterns.ChainOfResponsibility;

/**
 * Created by rkhurana on 1/22/19.
 */
public interface Handler {
     void setSuccessor(Handler next);
     void handleRequest(String request);
}
