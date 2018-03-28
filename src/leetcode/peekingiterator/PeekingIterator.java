package leetcode.peekingiterator;

import java.util.Iterator;

class PeekingIterator implements Iterator<Integer> {
    
    static class Node {
        Integer x;
        Node next;
        
        private Node(Integer x){
            this.x=x;
        }
        public static Node of(Integer val){
            return new Node(val);
        }
        
       
    }
    
    Node first=null;

	public PeekingIterator(Iterator<Integer> iterator) {
        Node temp=null;
        Node prev=null;
	    while(iterator.hasNext()){
            temp= Node.of(iterator.next());
            if(prev!=null){
                prev.next=temp;
            }
            if(first==null){
                first=temp;
            }
            prev=temp;
        }
	    
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        if(first!=null)
        return first.x;
        
        return null;
        
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
        Integer toReturn=null;
	    if(first!=null){
            toReturn=first.x;
            first=first.next;
        }
	    return toReturn;
	}

	@Override
	public boolean hasNext() {
        
        return first!=null;
	    
	}
}