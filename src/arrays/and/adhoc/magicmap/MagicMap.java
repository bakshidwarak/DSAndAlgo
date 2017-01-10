package arrays.and.adhoc.magicmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implement a magic map that adheres to the following interface
 * 
 * get(key) - O(1) - Gets a value for key K 
 * set(key,value) - O(1) - Sets/Updates the value for key 
 * delete(key) - O(1) - Deletes the key
 *  get_random - Get random element - O(1)
 * 
 * @author dwaraknathbs
 *
 */
public class MagicMap {

	ArrayList<Pair> pairsList = new ArrayList<>();
	AtomicInteger size = new AtomicInteger();
	HashMap<Object, Integer> map = new HashMap<>();
	
	public synchronized void set(Object key,Object value){
		if(map.containsKey(key)){
			int index=map.get(key);
			Pair pair=pairsList.get(index);
			pair.setValue(value);
		}
		else
		{
			Pair pair= new Pair(key, value);
			pairsList.add(pair);
			int val=size.getAndIncrement();
			map.put(key, val);
		}
	}
	public Object get(Object key) throws Exception{
		validateKey(key);
		int index= map.get(key);
		Pair pair= pairsList.get(index);
		return pair.getValue();
	}
	private void validateKey(Object key) throws Exception {
		if(!map.containsKey(key)) throw new Exception("Element does not exist");
	}
	
	public void delete(Object key) throws Exception{
		validateKey(key);
		//Get the index of the key from map
		int index= map.get(key);
		//swap the element of the index with the last element
		Pair pairAtEnd=pairsList.get(size.get()-1);
		swap(index,size.get()-1);
		//Update in the map
		map.put(pairAtEnd.getKey(), index);
		//reduce size of the list
		size.decrementAndGet();
		//remove the element from the map
		map.remove(key);
	}
	
	public Object get_random(){
		int random = ThreadLocalRandom.current().nextInt(0, size.get());
		Pair pair=pairsList.get(random);
		return pair.getValue();
	}

	private void swap(int index, int i) {
		Pair temp= pairsList.get(index);
		pairsList.set(index,pairsList.get(i));
		pairsList.set(i, temp);
		
	}
	public static void main(String[] args) throws Exception {
		MagicMap magicMap= new MagicMap();
		magicMap.set("1", "first");
		magicMap.set("2", "Second");
		magicMap.set("3", "third");
		magicMap.set("4", "forth");
		magicMap.printMap();
		System.out.println("Random Value"+magicMap.get_random());
		magicMap.delete("3");
		magicMap.printMap();
		
		System.out.println("Random Value"+magicMap.get_random());
		magicMap.delete("1");
		magicMap.printMap();
		System.out.println("Random Value"+magicMap.get_random());
		magicMap.delete("2");
		magicMap.printMap();
		System.out.println("Random Value"+magicMap.get_random());
		magicMap.delete("4");
		magicMap.printMap();

	}
	
	public void printMap(){
		System.out.println("__________________________PRINTING MAP__________________________");
		map.entrySet().stream().forEach(entry->System.out.println(entry.getKey() + "="+ pairsList.get(entry.getValue()).getValue()));
	}

}

class Pair {
	private Object key;
	private Object value;

	public Pair(Object key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	

}
