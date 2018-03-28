package leetcode.minimumgeneticmutation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * A gene string can be represented by an 8-character long string, with choices
 * from "A", "C", "G", "T".
 * 
 * Suppose we need to investigate about a mutation (mutation from "start" to
 * "end"), where ONE mutation is defined as ONE single character changed in the
 * gene string.
 * 
 * For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
 * 
 * Also, there is a given gene "bank", which records all the valid gene
 * mutations. A gene must be in the bank to make it a valid gene string.
 * 
 * Now, given 3 things - start, end, bank, your task is to determine what is the
 * minimum number of mutations needed to mutate from "start" to "end". If there
 * is no such a mutation, return -1.
 * 
 * Note:
 * 
 * Starting point is assumed to be valid, so it might not be included in the
 * bank. If multiple mutations are needed, all mutations during in the sequence
 * must be valid. You may assume start and end string is not the same.
 * 
 * Example 1:
 * 
 * start: "AACCGGTT" end: "AACCGGTA" bank: ["AACCGGTA"]
 * 
 * return: 1
 * 
 * Example 2:
 * 
 * start: "AACCGGTT" end: "AAACGGTA" bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 * 
 * return: 2
 * 
 * Example 3:
 * 
 * start: "AAAAACCC" end: "AACCCCCC" bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 * 
 * return: 3
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class MinimumGeneticMutation {
	
	static class Pair {
        String  mutation;
        int level;
        
        public static Pair of(String mutation, int level){
            Pair p= new Pair();
            p.mutation=mutation;
            p.level=level;
            return p;
        }
    }
	
	/**
	 * Basic idea is BFS
	 * @param start
	 * @param end
	 * @param bank
	 * @return
	 */
    public int minMutation(String start, String end, String[] bank) {
       
        HashSet<String> bankSet=new HashSet<String>(Arrays.asList(bank));
        Queue<Pair> queue= new LinkedList<>();
        queue.add(Pair.of(start,0));
        Set<String> visited= new HashSet<>();

	    char[] genes={'A','C','G','T'};
        
        while(!queue.isEmpty()){
            
            Pair current=queue.remove();
            int level=current.level;
            
            if(visited.contains(current.mutation)){
                continue;
            }
             visited.add(current.mutation);
            if(current.mutation.equals(end)){
                return level;
            }
            
            char[] currMutation=current.mutation.toCharArray();
            for(int i=0; i<currMutation.length;i++){
                for(int j=0; j<genes.length;j++){
                    char tmp=currMutation[i];
                    if(currMutation[i]==genes[j]) continue;
                    currMutation[i]=genes[j];
                    String flipped=new String(currMutation);
                    if(bankSet.contains(flipped)){
                        queue.add(Pair.of(flipped,level+1));
                    }
                    currMutation[i]=tmp;
                }
            }
        }
        return -1;
        
        
    }

}
