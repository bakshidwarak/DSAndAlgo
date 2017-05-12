package strings.assignment;

import java.util.HashSet;
import java.util.Set;

public class Neuronyms {

	public static void main(String[] args) {
		Set<String> neuronyms= getNeuronyms("nailed");
		neuronyms.forEach(System.out::println);

	}

	private static Set<String> getNeuronyms(String word) {
		
		HashSet<String> neuronyms= new HashSet<>();
		
		int n = word.length();
		for(int i=1,j=n-1;i<n;i++,j--){
			
			if(j-i>=2){
				neuronyms.add(word.substring(0, i) +(j-i)+word.substring(j));
			}
			if(n-i-1>=2){
				neuronyms.add(word.substring(0, i+1) +(n-i-1)+word.substring(n-1));
			}
			if(j>=2){
				neuronyms.add(word.substring(0, 1) +(j)+word.substring(j));
			}
		}
		return neuronyms;
	}

}
