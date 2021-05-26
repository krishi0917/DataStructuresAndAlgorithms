package DesignPatterns.ChainOfResponsibility;

/**
 * Created by rkhurana on 1/22/19.
 */
public class ConcreteHandler1 implements Handler {
    private Handler successor = null;

    @Override
    public void setSuccessor(Handler next) {
        this.successor = next;
    }

    @Override
    public void handleRequest(String request) {
        System.out.println("Concrete Handler 1 got the request");
        if(request.contains("R1")){
            System.out.print("Serving request");
        }
        else{
            if(successor!=null){
                successor.handleRequest(request);
            }
            else{
                System.out.println("this request cannot be served");
            }
        }

    }
}
