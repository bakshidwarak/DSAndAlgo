package arrays.and.adhoc.shuffleNElements;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Given an array of size N shuffle it in such a way that all permutations are
 * equally likely
 * 
 * @author dwaraknathbs
 *
 */
public class ShuffleNElementsInArray {

	public static void main(String[] args) {
		int[] integerArray = { 1, 2, 3, 4, 5 };
		int count = 0;
		while (count < 100) {
			int[] shuffledArray = shuffleArray(integerArray);
			Arrays.stream(shuffledArray).forEach(System.out::print);
			System.out.println();
			count++;
		}

	}

	private static int[] shuffleArray(int[] integerArray) {
		for (int i = 0; i < integerArray.length; i++) {
			int random = ThreadLocalRandom.current().nextInt(i, integerArray.length);
			swap(integerArray, i, random);

		}
		return integerArray;
	}

	private static void swap(int[] integerArray, int i, int random) {
		int temp = integerArray[i];
		integerArray[i] = integerArray[random];
		integerArray[random] = temp;

	}

}
/**
Sample Output
-----------------
32541
24531
24135
24135
53241
15432
24531
31452
42531
41235
51324
15243
51234
25314
51432
24513
52413
24351
41523
51423
51234
12453
31452
21534
32154
13254
21345
35412
13542
42315
12435
13254
14352
53124
52431
42135
53241
54312
12435
15423
52413
31254
53412
32514
14523
43125
13245
24135
35214
54123
25314
53124
41532
45123
35241
54213
45213
41253
32415
25134
52431
52143
15324
21543
43521
53214
13542
21354
24315
24153
23514
54231
24153
52341
23451
31524
54213
21453
52431
35124
42135
41523
15423
15342
15243
41253
54312
23145
32514
41352
13542
41532
54123
51243
12543
21345
52143
51324
14352
25413
**/