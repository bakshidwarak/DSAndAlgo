package datastructures.heap;

import java.util.Arrays;
import java.util.Comparator;

public class MyHeap<T> {
	
	enum HeapType{
		MIN_HEAP(-1),
		MAX_HEAP(1)
		;
		int multiplier;
		HeapType(int val){
			multiplier=val;
		}
		public int multiplier() {
			return multiplier;
		}
		public void setMultiplier(int multiplier) {
			this.multiplier = multiplier;
		}
		
	}
	private int capacity;
	private int size=0;
	private HeapType heapType;
	private Comparator<T> heapProperty;
	private T[] heap;
	
	public MyHeap(int capacity, HeapType heapType,Comparator<T> heapProperty) {
		super();
		this.capacity = capacity;
		this.heapType = heapType;
		heap = (T[])new Object[capacity];
		if(heapType==HeapType.MAX_HEAP)
			Arrays.fill(heap, Integer.MAX_VALUE);
		else
			Arrays.fill(heap, Integer.MIN_VALUE);
		this.heapProperty=heapProperty;
	}
		
	private int getParent(int index){
		return index%2==0?(index-2)/2:(index-1)/2;
	}
	
	private int getLeft(int index){
		return 2*index+1;
		
	}
	
	private int getRight(int index){
		return 2*index+2;
	}
	
	public void add(T number) throws Exception{
		if(size==capacity) throw new Exception("Heap is full");
		
		heap[size]=number;
		size++;
		heapifyUp();
	}
	
	

	public T poll() throws Exception{
		if(size==0) throw new Exception("Heap is empty");
		T returnNumber=heap[0];
		heap[0]=heap[size-1];
		size--;
		heapifyDown();
		return returnNumber;
	}
	
	

	public T peek(){
		return heap[0];
	}
	
	private void heapifyUp() {
		int index=size-1;
		while(index!=0 && heapProperty.compare(heap[index],heap[getParent(index)])==heapType.multiplier){
			swap(index,getParent(index));
			index=getParent(index);
			
		}
		
	}
	
	private void swap(int index, int parent) {
		T temp= heap[index];
		heap[index]=heap[parent];
		heap[parent]=temp;
		
	}

	private void heapifyDown() {
		int parent=0;
		T leftValue=heap[getLeft(0)];
		T rightValue=heap[getRight(0)];
		int childToSwap=getLeft(0);
		
		if(heapProperty.compare(leftValue, rightValue)!=heapType.multiplier){
			childToSwap=getRight(0);
		}
		while(childToSwap<size && heapProperty.compare(heap[parent],heap[childToSwap])!=heapType.multiplier){
			swap(parent,childToSwap);
			parent=childToSwap;
			childToSwap=getLeft(parent);
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		Comparator<Integer> heapComparator= (o1,o2)->o1.compareTo(o2);
		MyHeap<Integer> myHeap=new MyHeap(10, HeapType.MIN_HEAP,heapComparator);
		myHeap.add(-1);
		myHeap.add(-2);
		myHeap.add(-3);
		myHeap.add(-4);
		myHeap.add(-5);
		myHeap.printHeap();
		myHeap.poll();
		myHeap.printHeap();
		myHeap.add(2);
		myHeap.printHeap();
		myHeap.add(3);
		myHeap.printHeap();
		myHeap.poll();
		myHeap.printHeap();
		myHeap.poll();
		myHeap.printHeap();
	}

	private void printHeap() {
		for(int i=0; i<size; i++)
			System.out.print(heap[i]+ " ");
		System.out.println();
	}
		

}
