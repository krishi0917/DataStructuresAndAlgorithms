package LeetcodePrograms.ImplementHashTable;

import java.util.*;

public class GenericHashMap<K,V> {
	
	ArrayList<GenericHashNode<K, V>>bucket=new ArrayList<>();
	int numBuckets=10;
	int size;
	public GenericHashMap()
	{	for(int i=0;i<numBuckets;i++)
		{
			bucket.add(null);
		}
	}
	public int getSize()
	{
		return size;
	}
	public boolean isEmpty()
	{
		return size==0;
	}

	private int getBucketIndex(K key) {
		int hashCod=key.hashCode();
		return hashCod%numBuckets;
	}

	public V get(K key)
	{
		int index=getBucketIndex(key);
		GenericHashNode<K, V> head=bucket.get(index);
		while(head!=null)
		{
			if(head.key.equals(key))
			{
				return head.value;
			}
			head=head.next;

		}
		return null;	
	}
	public V remove(K key) {
		int index=getBucketIndex(key);
		GenericHashNode<K, V> head=bucket.get(index);
		if(head==null) {
			return null;
		}
		if(head.key.equals(key)) {
			V val=head.value;
			head=head.next;
			bucket.set(index, head);
			size--;
			return val;
		}
		else {
			GenericHashNode<K, V> prev=null;
			while(head!=null) {
				
				if(head.key.equals(key)) {
					prev.next=head.next;
					size--;
					return head.value;
				}
				prev=head;
				head=head.next;
			}
			size--;
			return null;
		}
	}
	public void add(K key,V value) {
		int index=getBucketIndex(key);
		GenericHashNode<K, V> head=bucket.get(index);
		GenericHashNode<K, V> toAdd=new GenericHashNode<>();
		toAdd.key=key;
		toAdd.value=value;
		if(head==null) {
			bucket.set(index, toAdd);
			size++;
			
		}
		else {
			while(head!=null) {
				if(head.key.equals(key)) {
					head.value=value;
					size++;
					break;
				}
				head=head.next;
			}
			if(head==null) {
				head=bucket.get(index);
				toAdd.next=head;
				bucket.set(index, toAdd);
				size++;
			}
		}
		if((1.0*size)/numBuckets>0.7) {
			//do something
			ArrayList<GenericHashNode<K, V>>tmp=bucket;
			bucket=new ArrayList<>();
			numBuckets=2*numBuckets;
			for(int i=0;i<numBuckets;i++) {
				bucket.add(null);
			}
			for(GenericHashNode<K, V> headNode:tmp) {
				while(headNode!=null) {
					add(headNode.key, headNode.value);
					headNode=headNode.next;
				}
			}
		}
		
	}
	public static void main(String[] args)
	{
		GenericHashMap<String,Integer> genericHashMap =new GenericHashMap<>();
		genericHashMap.add("this",1 );
		System.out.println(genericHashMap.remove("this"));
		System.out.println(genericHashMap.remove("this"));
		
	}

}