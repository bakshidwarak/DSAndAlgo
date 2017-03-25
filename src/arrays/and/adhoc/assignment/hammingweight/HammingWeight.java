package arrays.and.adhoc.assignment.hammingweight;

import java.io.IOException;
import java.util.Scanner;

/**
 * Hamming Weight We're given a large array of 4-byte integers. We need to write
 * a method to find out how many total bits are turned on (i.e. 1s are set)
 * inside such an array. [Such a digital sum of binary representation of a
 * number, is also called its Hamming Weight].
 * 
 * e.g. 1. if input array has two numbers: 31 and 51, the answer is 9, because
 * 31 has 5 bits turned on (out of 32) and 51 has 4. 2. if the input is
 * 2147483647 and 3, the answer is 31 + 2 = 33
 * 
 * We're looking for a fast solution, even if it uses extra memory. While it is
 * possible to optimize solutions based on the machine architecture, we're not
 * looking for intense bit-hackery. Assume input in base-10. No floating points.
 * 
 * Hint: Think hash tables.
 * 
 * Solution: See the top solution here:
 * http://stackoverflow.com/questions/8871204/count-number-of-1s-in-binary-representation
 * 
 * @author dwaraknathbs
 *
 */
public class HammingWeight {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int res;

		int _intArr_size = 0;
		_intArr_size = Integer.parseInt(in.nextLine().trim());
		int[] _intArr = new int[_intArr_size];
		int _intArr_item;
		for (int _intArr_i = 0; _intArr_i < _intArr_size; _intArr_i++) {
			_intArr_item = Integer.parseInt(in.nextLine().trim());
			_intArr[_intArr_i] = _intArr_item;
		}

		res = printCountOfBitsSet(_intArr);
		System.out.println(String.valueOf(res));

	}

	static int[] bits = new int[65536];

	static {

		for (int i = 0; i < bits.length; i++) {
			bits[i] = countBitsUsingBrianKernighansAlgorithm(i);
		}

	}

	/**
	 * The best explaination for this was in EPI.
	 * 
	 * Clearly, we cannot cache the parity of every 64-bit integer—we would need
	 * 264 bits of storage, which is of the order of ten trillion exabytes.
	 * However, when computing the parity of a collection of bits, it does not
	 * matter how we group those bits, i.e., the computation is associative.
	 * Therefore, we can compute the parity of a 64-bit integer by grouping its
	 * bits into four nonoverlapping 16 bit subwords, computing the parity of
	 * each subwords, and then computing the parity of these four subresults. We
	 * choose 16 since 216 = 65536 is relatively small, which makes it feasible
	 * to cache the parity of all 16-bit words using an array. Furthermore,
	 * since 16 evenly divides 64, the code is simpler than if we were, for
	 * example, to use 10 bit subwords. We illustrate the approach with a lookup
	 * table for 2-bit words. The cache is h0, 1, 1, 0i—these are the parities
	 * of (00), (01), (10), (11), respectively. To compute the parity of
	 * (11001010) we would compute the parities of (11), (00), (10), (10). By
	 * table lookup we see these are 0, 0, 1, 1, respectively, so the final
	 * result is the parity of 0, 0, 1, 1 which is 0. To lookup the parity of
	 * the first two bits in (11101010), we right shift by 6, to get (00000011),
	 * and use this as an index into the cache. To lookup the parity of the next
	 * two bits, i.e., (10), we right shift by 4, to get (10) in the two
	 * least-significant bit places. The right shift does not remove the leading
	 * (11)—it results in (00001110). We cannot index the cache with this, it
	 * leads to an out-of-bounds access. To get the last two bits after the
	 * right shift by 4, we bitwise-AND (00001110) with (00000011) (this is the
	 * “mask” used to extract the last 2 bits). The result is (00000010).
	 * Similar masking is needed for the two other 2-bit lookups.
	 * 
	 * The time complexity is a function of the size of the keys used to index
	 * the lookup table. Let L be the width of the words for which we cache the
	 * results, and n the word size. Since there are n/L terms, the time
	 * complexity is O(n/L), assuming word-level operations, such as shifting,
	 * take O(1) time. (This does not include the time for initialization of the
	 * lookup table.) The XOR of two bits is 0 if both bits are 0 or both bits
	 * are 1; otherwise it is 1. XOR has the property of being associative (as
	 * previously described), as well as commutative, i.e., the order in which
	 * we perform the XORs does not change the result. The XOR of a group of
	 * bits is its parity. We can exploit this fact to use the CPU’s word-level
	 * XOR instruction to process multiple bits at a time. For example, the
	 * parity of hb63, b62, . . . , b3, b2, b1, b0i equals the parity of the XOR
	 * of hb63, b62, . . . , b32i and hb31, b30, . . . , b0i. The XOR of these
	 * two 32-bit values can be computed with a single shift and a single 32-bit
	 * XOR instruction
	 * 
	 * @param arr
	 * @return
	 */

	private static int printCountOfBitsSet(int[] arr) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			/**
			 * Initially had kept the word size as 8 and mask as FF, however if
			 * the value is large especially if it is the max value like
			 * 2147483647, the look up table of 256 bits is not sufficient.
			 * Hence going for a bigger size lookup table and bigger mask
			 */
			int WORD_LENGTH = 16;
			int BIT_MASK = 0xFFFF;
			// count+=bits[(int)(arr[i]>>>(3*WORD_LENGTH))&BIT_MASK]^bits[(int)(arr[i]>>>(2*WORD_LENGTH))&BIT_MASK]^bits[(int)(arr[i]>>>(1*WORD_LENGTH))&BIT_MASK]^bits[(int)arr[i]&
			// BIT_MASK];

			/**
			 * 011111111 11111111 11111111 11111111
			 * 
			 * Mask : 11111111
			 * 
			 * 1) Right Shift by 16
			 * 
			 * 00000000 00000000 01111111 11111111 & 00000000 00000000 11111111
			 * 11111111 = base 10 (32767) Lookup bit[32767] = 15 ( Precomputed
			 * and stored in the look up table)
			 * 
			 * 2) & with bit mask to get the 1s count of the LSB
			 * 
			 * 011111111 11111111 11111111 11111111 & 00000000 00000000 11111111
			 * 11111111 = base 10(65535) Look up bit[65535] = 16 ( Precomputed
			 * and stored in the look up table)
			 * 
			 * Hence total number of 1s= 16^15 =31
			 * 
			 */
			count += bits[(int) (arr[i] >>> (1 * WORD_LENGTH)) & BIT_MASK] ^ bits[(int) arr[i] & BIT_MASK];
		}
		return count;
	}

	/**
	 * Straight forward way of counting bits
	 * 
	 * @param i
	 * @return
	 */
	private static int countBits(int i) {
		int count = 0;
		while (i != 0) {
			i = i & 1;
			count++;
		}
		return count;
	}

	/**
	 * Brian Kernighan’s Algorithm: to count bits
	 * 
	 * @param i
	 * @return
	 */
	public static int countBitsUsingBrianKernighansAlgorithm(int i) {
		int count = 0;
		while (i != 0) {
			i = i & (i - 1);
			count++;
		}
		return count;
	}
}
