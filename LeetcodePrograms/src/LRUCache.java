package LeetcodePrograms.src;
import java.util.*;

//programcreek solution
public class LRUCache {
//	The LRU cache is a hash table of keys and double linked nodes. The hash table makes the time of get() to be O(1).
//	The list of double linked nodes make the nodes adding/removal operations O(1).
//	The key to solve this problem is using a double linked list which enables us to quickly move nodes.


	class Node{
		int key;
		int value;
		Node prev;
		Node next;

		public Node(int key, int value){
			this.key=key;
			this.value=value;
		}
	}

	Node head;
	Node tail;
	HashMap<Integer, Node> map = null;
	int cap = 0;

	public LRUCache(int capacity) {
		this.cap = capacity;
		this.map = new HashMap<>();
	}

	public int get(int key) {
		if(map.get(key)==null){
			return -1;
		}

		//move to tail
		Node t = map.get(key);

		removeNode(t);
		offerNode(t);

		return t.value;
	}

	public void put(int key, int value) {
		if(map.containsKey(key)){
			Node t = map.get(key);
			t.value = value;

			//move to tail
			removeNode(t);
			offerNode(t);
		}else{
			if(map.size()>=cap){
				//delete head
				map.remove(head.key);
				removeNode(head);
			}

			//add to tail
			Node node = new Node(key, value);
			offerNode(node);
			map.put(key, node);
		}
	}

	private void removeNode(Node n){
		if(n.prev!=null){
			n.prev.next = n.next;
		}else{
			head = n.next;
		}

		if(n.next!=null){
			n.next.prev = n.prev;
		}else{
			tail = n.prev;
		}
	}

	private void offerNode(Node n){
		if(tail!=null){
			tail.next = n;
		}

		n.prev = tail;
		n.next = null;
		tail = n;

		if(head == null){
			head = tail;
		}
	}

	public static void main (String []args){
		LRUCache lruCache = new LRUCache(5);
		lruCache.put(2,3);
		lruCache.get(2);
	}


}