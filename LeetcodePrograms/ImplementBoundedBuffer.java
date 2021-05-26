package LeetcodePrograms;

/**
 * @author Rishi Khurana
 *
/**
Implement a Bounded Buffer: data structure that can keep up to N elements, and has methods:
- Put: store elements in Bounded Buffer
- Get: return elements in order.
 */

import java.util.LinkedList;
import java.util.List;


class ImplementBoundedBuffer<T>{
    List<T> buffer;
    int maxSize;
    public ImplementBoundedBuffer(int size){
        buffer = new LinkedList<T>();
        maxSize = size;
    }

    public synchronized void put(T element) throws InterruptedException {
        while(isFull()){
            wait();
        }
        buffer.add(element);
        notify();

    }

    public synchronized T get() throws InterruptedException {
        while(buffer.isEmpty()){
            wait();
        }
        T element  = buffer.remove(0);
        notify();
        return element;
    }


    private boolean isFull(){
        return buffer.size() == maxSize;
    }

    public static void main(String[] args) {

    }
}


