package dp.decodeletter;

import java.util.Arrays;

/*

a - 1
b - 2
c - 3
...
z - 26

abc - 1234

1- 2,3,4
12 34

f(0)-> i->1 to N 1+ F(1)
lc - 123
aw - 123

'123' => 3

'11111111111'

3,11,1,11
1,11,11,11
11,


                1234, 0
          1 234   12 34
          1 2 34

   1 2 345\
   12 345






*/

public class DecodeLetter {

	public static void main(String[] args) {

		System.out.println(getNumberOfCombinations("11111"));

	}

	public static int getNumberOfCombinations(String input) {

		int[] cache = new int[input.length()];

		Arrays.fill(cache, -1);

		return getCombinationHelper(input, 0, cache);

	}

	public static int getCombinationHelper(String input, int start, int[] cache) {

		if (cache[start] == -1) {
			if (start == input.length() - 1 && Integer.valueOf(input.charAt(start)) > 0) {
				cache[start] = 1;
				return cache[start];
			} else if (start == input.length() - 1 && Integer.valueOf(input.charAt(start)) == 0) {
				cache[start] = 0;
				return cache[start];
			}

			int count = 0;
			int num = 0;

			for (int j = 1; j < 3; j++) {

				num = Integer.valueOf(input.substring(start, start + j));
				if (num <= 26 && num > 0) {
					if (start + j < input.length()) {
						count += getCombinationHelper(input, start + j, cache);
					}
					else
					{
						count++;
					}
				} else {
					break;
				}

			}
			cache[start] = count;

		}
		return cache[start];

	}
}