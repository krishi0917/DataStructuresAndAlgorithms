package LeetcodePrograms;

import java.util.TreeMap;


/**
 * @author Rishi Khurana
 * 1146. Snapshot Array
 *
 * SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element
 * equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 *
 * Time O(logS)
 * Space O(S)
 * where S is the number of set called.
 *
 * SnapshotArray(int length) is O(N) time
 * set(int index, int val) is O(1) in Python and O(logSnap) in Java
 * snap() is O(1)
 * get(int index, int snap_id) is O(logSnap)
 *
 */
public class SnapshotArray {
    TreeMap<Integer, Integer>[] A; // treemap array with key as snap id and value as value
    int snap_id = 0;
    public SnapshotArray(int length) {
        A = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            A[i] = new TreeMap<Integer, Integer>(); // treemap of snapId and value
            A[i].put(0, 0);
        }
    }

    public void set(int index, int val) {
        A[index].put(snap_id, val);
    }

    public int snap() {
        return snap_id++;
    }

    public int get(int index, int snap_id) {
        return A[index].floorEntry(snap_id).getValue();
    }

    public static void main(String[]args){


        SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
        snapshotArr.set(0,5);  // Set array[0] = 5
        snapshotArr.snap();  // Take a snapshot, return snap_id = 0
        snapshotArr.set(0,6);
        snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
    }
}
