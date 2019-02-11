package leetcode.canplaceflowers;

/**
 * 605. Can Place Flowers Easy
 * 
 * 410
 * 
 * 237
 * 
 * Favorite
 * 
 * Share Suppose you have a long flowerbed in which some of the plots are
 * planted and some are not. However, flowers cannot be planted in adjacent
 * plots - they would compete for water and both would die.
 * 
 * Given a flowerbed (represented as an array containing 0 and 1, where 0 means
 * empty and 1 means not empty), and a number n, return if n new flowers can be
 * planted in it without violating the no-adjacent-flowers rule.
 * 
 * Example 1: Input: flowerbed = [1,0,0,0,1], n = 1 Output: True Example 2:
 * Input: flowerbed = [1,0,0,0,1], n = 2 Output: False Note: The input array
 * won't violate no-adjacent-flowers rule. The input array size is in the range
 * of [1, 20000]. n is a non-negative integer which won't exceed the input array
 * size.
 * 
 * @author dwaraknathbs
 *
 */
public class CanPlaceFlowers {
	public boolean canPlaceFlowers(int[] flowerbed, int n) {
		if (flowerbed.length == 1 && flowerbed[0] == 0) {
			return n <= 1;
		}

		return canPlace(flowerbed, 0, n);

	}

	public boolean canPlace(int[] bed, int index, int n) {
		if (n == 0)
			return true;
		if (index >= bed.length)
			return false;
		if (bed[index] == 1) {
			return canPlace(bed, index + 2, n);
		}
		if (bed[index] == 0) {
			if (index + 1 < bed.length && bed[index + 1] == 0) {
				return canPlace(bed, index + 2, n - 1);
			} else if (index + 1 == bed.length && index > 0 && bed[index - 1] == 0) {
				return canPlace(bed, index + 2, n - 1);
			} else {
				return canPlace(bed, index + 1, n);
			}
		}

		return false;
	}

}
