

package leetcode.countandsay;

/**
 * The count-and-say sequence is the sequence of integers with the first five
 * terms as following: 1. 1 2. 11 3. 21 4. 1211 5. 111221 1 is read off as
 * "one 1" or 11. 11 is read off as "two 1s" or 21. 21 is read off as
 * "one 2, then one 1" or 1211. Given an integer n, generate the nth term of the
 * count-and-say sequence. Note: Each term of the sequence of integers will be
 * represented as a string.
 * 
 * @author dwaraknathbs
 */
public class CountAndSay {

    public static void main(String[] args) {
        CountAndSay c= new CountAndSay();
        System.out.println(c.countAndSay(5));

    }

    public String countAndSay(int n) {
        return countAndSayHelper(n);
    }

    public String countAndSayHelper(int n) {
        if (n == 1) {
            return "1";
        }
        if (n == 2) {
            return "11";
        }
        String num = countAndSayHelper(n - 1);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < num.length(); i++) {
            int count = 1;
            while (i < num.length() - 1 && num.charAt(i) == num.charAt(i + 1)) {
                count++;
                i++;
            }
            sb.append(count);
            sb.append(num.charAt(i));

        }
        return sb.toString();

    }

}
