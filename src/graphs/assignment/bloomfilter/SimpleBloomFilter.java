package graphs.assignment.bloomfilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.BitSet;
import java.util.Scanner;

/***
 * Create a simple bloom filter for list of words in mac dictionary
 * @author dwaraknathbs
 *
 */
public class SimpleBloomFilter {

	static BitSet bits=new BitSet();

	void add(String str) {
		int hashCode = str.hashCode();
		int index = Math.abs(hashCode % (Integer.MAX_VALUE/3));
		bits.set(index);
	}

	boolean isProbablyPresent(String str) {
		int hashCode = str.hashCode();
		int index = Math.abs(hashCode % (Integer.MAX_VALUE/3));
		return bits.get(index);
	}

	public static void main(String[] args) throws FileNotFoundException {
		SimpleBloomFilter sbf = new SimpleBloomFilter();

		Scanner scanner = new Scanner(new File("/usr/share/dict/words"));

		while (scanner.hasNextLine()) {
			sbf.add(scanner.nextLine());
		}
		scanner.close();

		System.out.println("Words loaded. Now query for any words you would like");

		Scanner inputScanner = new Scanner(System.in);
		while (inputScanner.hasNextLine()) {
			if (sbf.isProbablyPresent(inputScanner.nextLine())) {
				System.out.println("Probably present");
			} else
				System.out.println("Absolutely not present");
		}
		inputScanner.close();

	}
}
