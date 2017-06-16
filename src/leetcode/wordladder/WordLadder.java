package leetcode.wordladder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * 127. Word Ladder
 * 
 * 
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * the length of shortest transformation sequence from beginWord to endWord,
 * such that:
 * 
 * Only one letter can be changed at a time. Each transformed word must exist in
 * the word list. Note that beginWord is not a transformed word. For example,
 * 
 * Given: beginWord = "hit" endWord = "cog" wordList =
 * ["hot","dot","dog","lot","log","cog"] As one shortest transformation is "hit"
 * -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
 * 
 * Note: Return 0 if there is no such transformation sequence. All words have
 * the same length. All words contain only lowercase alphabetic characters. You
 * may assume no duplicates in the word list. You may assume beginWord and
 * endWord are non-empty and are not the same.
 * 
 * @author dwaraknathbs
 *
 */
public class WordLadder {
	
	 static class Pair {
	        String word1;
	        String word2;
	        public Pair(String word1,String word2){
	            this.word1=word1;
	            this.word2=word2;
	        }
	    }

	public static void main(String[] args) {
		List<String> wordList= new ArrayList<>();
		wordList.add("hot");
		wordList.add("dog");
		wordList.add("fog");
		
		System.out.println(ladderLength("hot", "fog", wordList));

	}
	
	 public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
	        HashMap<String,String> backRef= new HashMap<>();
	        Queue<Pair> wordLadder= new LinkedList<Pair>();
	        HashSet<String> visited= new HashSet<String>();
	        wordLadder.add(new Pair(beginWord,null));
	        
	        while(!wordLadder.isEmpty()){
	            Pair current=wordLadder.remove();
	            String word=current.word1;
	            if(visited.contains(word)) continue;
	            if(word.equals(endWord)) break;
	            backRef.put(word,current.word2);
	            visited.add(word);
	           
	            
	            for(String neighbour: getNeighbours(word,wordList)){
	                wordLadder.add(new Pair(neighbour,word));
	            }
	        }
	        
	        String curr=endWord;
	        int count=0;
	        while(!curr.equals(beginWord)){
	            count++;
	            curr=backRef.get(curr);
	        }
	        return count;
	    }
	    
	 /**
	  * Returns time limit exceeded
	  * @param word
	  * @param wordList
	  * @return
	  */
	   static  List<String> getNeighboursold(String word,List<String> wordList){
	        List<String> neighbours= new ArrayList<String>();
	        char[] wordChars=word.toCharArray();
	        for(String current: wordList){
	            char[] currentChars=current.toCharArray();
	            int count=0;
	            for(int i=0; i<currentChars.length; i++){
	                if(currentChars[i]!=wordChars[i]){
	                    count++;
	                }
	                
	            }
	            if(count==1) neighbours.add(current);
	        }
	        return neighbours;
	    }
	   
	static List<String> getNeighbours(String word, List<String> wordList) {
		List<String> neighbours = new ArrayList<String>();
		char[] wordChars = word.toCharArray();
		for (int i = 0; i < wordChars.length; i++) {
			for (char c = 'a'; c <= 'z'; c++) {
				char old=wordChars[i];
				wordChars[i] = c;
				String str = new String(wordChars);
				if (wordList.contains(str))
					neighbours.add(str);
				wordChars[i]=old;
			}
		}
		return neighbours;
	}

}
