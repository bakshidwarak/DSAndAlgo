

package strings.assignment;

import java.util.HashSet;
import java.util.Set;


/**
 * Neuronyms What's a Neuronym? e.g. L10n, is called a Numeronym of the word
 * Localization, where 10 stands for the number of letters between the first 'L'
 * and the last 'n' in the word. Generate all such possible Numeronyms for any
 * given string (character array). for eg. for "nailed" : "n4d" "na3d", "n3ed"
 * "n2led", "na2ed", "nai2d" e.g. for the word "batch" "b3h" "ba2h" "b2ch" etc.
 * Print progressively longer strings, until there is a '2' in the permutations.
 * There is no point of going below 2, because that won't compress the string.
 * Solution: Take inspiration from
 * http://www.careercup.com/question?id=5733696185303040
 * 
 * @author dwaraknathbs
 */
public class Neuronyms {

    public static void main(String[] args) {
        Set<String> neuronyms = getNeuronyms("careercup");
        neuronyms.forEach(System.out::println);

    }

    /**
     * There are 3 cases here.
     * <li>One is having a pointer from start and other from end and keep
     * updating the string with the difference between the two
     * <li>Two the difference between start index and end of string
     * <li>Three the difference between end index and start of the string
     * <li>There could be duplicates and hence we use a hashset
     * 
     * @param word
     * @return
     */
    private static Set<String> getNeuronyms(String word) {

        HashSet<String> neuronyms = new HashSet<>();

        int n = word.length();
        for (int i = 1, j = n - 1; i < n; i++, j--) {

            if (j - i >= 2) {
                String str = word.substring(0, i) + (j - i) + word.substring(j);

                neuronyms.add(str);
            }
            if (n - i - 2 >= 2) {
                String str = word.substring(0, i + 1) + (n - i - 2) + word.substring(n - 1);

                neuronyms.add(str);
            }
            if (j - 1 >= 2) {
                String str = word.substring(0, 1) + (j - 1) + word.substring(j);

                neuronyms.add(str);
            }
        }
        return neuronyms;
    }

}
