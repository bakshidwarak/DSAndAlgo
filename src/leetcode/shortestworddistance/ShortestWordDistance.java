package leetcode.shortestworddistance;

/**
 * 243. Shortest Word Distance
 * 
 * Given a list of words and two words word1 and word2, return the shortest
 * distance between these two words in the list.
 * 
 * For example, Assume that words = ["practice", "makes", "perfect", "coding",
 * "makes"].
 * 
 * Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = "makes",
 * word2 = "coding", return 1.
 * 
 * Note: You may assume that word1 does not equal to word2, and word1 and word2
 * are both in the list.
 * 
 * @author dwaraknathbs
 *
 */
public class ShortestWordDistance {

	public static void main(String[] args) {
		String[] words={"practice", "makes", "perfect", "coding", "makes"};
		System.out.println(shortestDistance(words,"practice","perfect"));

	}
	
	 public static  int shortestDistance(String[] words, String word1, String word2) {
	        int index1=-1;
	        int index2=-1;
	        int min=Integer.MAX_VALUE;
	        int i=0;
	        for(String str : words){
	            if(str.equals(word1)){
	                index1=i;
	            }
	            if(str.equals(word2)){
	                index2=i;
	            }
	            i++;
	            if(index2!=-1 && index1!=-1)
	            min=Math.min(min,Math.abs(index2-index1));
	        }
	        return min;
	    }

}
