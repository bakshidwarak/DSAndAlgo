package linkedlist.assignment.lrucache;

import java.util.HashMap;

/**
 * Design and implement an LRU cache LRU cache evicts the least recently used
 * item from the cache. Item insertions, lookups and evictions should be very
 * efficient.
 * 
 * @author dwaraknathbs
 *
 */
public class LRUCache<K, V> {

	/**
	 * Maintain a doubly linked list as value and maintain a key , value and prev and next
	 * @author dwaraknathbs
	 *
	 * @param <K>
	 * @param <V>
	 */
	static class Node<K, V> {
		K key;
		V val;
		Node<K, V> next;
		Node<K, V> prev;

	}

	private HashMap<K, Node<K, V>> cache = new HashMap<>();
	
	/**
	 * Head of the DLL stores the most recently used element
	 */
	private Node<K, V> head;
	
	/**
	 * Tail of the DLL stores the least recently used element
	 */
	private Node<K, V> tail;
	private int MAX_SIZE;
	private int size = 0;
	
	

	public LRUCache(int mAX_SIZE) {
		
		MAX_SIZE = mAX_SIZE;
	}

	public static void main(String[] args) {
		LRUCache<Integer, String> mycache= new LRUCache<>(5);
		mycache.set(1, "Tom");
		mycache.set(2, "John");
		mycache.set(3, "Ron");
		mycache.set(4, "Harry");
		mycache.set(5, "Hermoine");
		mycache.set(6, "Nevin");
		mycache.set(7, "Kevin");
		mycache.set(8, "Peter");
		mycache.set(9, "Dumbledore");
		System.out.println(mycache.get(6));
		mycache.set(10, "Snape");
		mycache.set(11, "Riddle");
		mycache.set(11, "hello");
		System.out.println(mycache.get(11));
		mycache.set(17, "Kevin");
		mycache.set(18, "Peter");
		mycache.set(29, "Dumbledore");
		mycache.remove(6);
		
		

	}

	/**
	 * When setting a new key value pair,make the new node / recently updated node the head of the DLL
	 * @param key
	 * @param val
	 */
	public void set(K key, V val) {
		Node<K, V> node = null;
		if (cache.containsKey(key)) {
			node = cache.get(key);
			node.val = val;

		} else {
			if (size > MAX_SIZE)
				evict();

			node = new Node<>();
			node.key = key;
			node.val = val;
			cache.put(key, node);
			System.out.println("Current size"+size++);
		}

		placeNodeAtFront(node);

	}

	private void placeNodeAtFront(Node<K, V> node) {
		node.next = head;
		node.prev = null;
		if (head != null) {
			head.prev = node;
		}
		else
		{
			tail=node;
		}
		head = node;
	}

	/**
	 * When accessing the key, make the node associated with the key the head of the DLL
	 * @param key
	 * @return
	 */
	public V get(K key) {
		if (!cache.containsKey(key))
			throw new RuntimeException("Key does not exist");

		Node<K, V> node = cache.get(key);
		placeNodeAtFront(node);
		return node.val;

	}

	/**
	 * Evict removes the least recently used element which is marked by the tail
	 */
	private void evict() {

		System.out.println("evicting"+ tail.key);
		if (tail!=null &&tail.prev != null) {
			tail.prev.next = null;
		}
		tail = tail.prev;
		cache.remove(tail.key);
		size--;

	}

	public void remove(K key) {
		if (!cache.containsKey(key))
			throw new RuntimeException("Key does not exist");

		Node<K, V> node = cache.get(key);
		if (node.prev != null)
			node.prev.next = node.next;

		if (node.next != null)
			node.next.prev = node.prev;

		size--;
		cache.remove(key);
	}

}
