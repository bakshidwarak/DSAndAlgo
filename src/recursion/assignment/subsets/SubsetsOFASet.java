package recursion.assignment.subsets;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Subsets of a set Problem: Print out all the subsets of a set.
 * 
 * E.g. {x,y} ==> {{}, {x}, {y}, {x,y}} [Remember, that we are working with
 * sets, so {y,x} is not different from {x,y}, and {x,x} is not a valid subset,
 * unless the input also has two x's] {1, 2, 3} ==> {{}, {1}, {2}, {3}, {1,2},
 * {1,3}, {2, 3}, {1,2,3}}
 * 
 * Input: Set, as an array Output: Subsets in any order.
 * 
 * This problem does not have pre-defined test-cases, in order to give you the
 * flexibility of doing outputs in any order, and in any print format.
 * 
 * Solutions:
 * http://stackoverflow.com/questions/728972/finding-all-the-subsets-of-a-set OR
 * http://stackoverflow.com/questions/4640034/calculating-all-of-the-subsets-of-a-set-of-numbers
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class SubsetsOFASet {

	public static void main(String[] args) {
		List<Integer> inputSet = new ArrayList<>();
		inputSet.add(1);
		inputSet.add(2);
		inputSet.add(3);
		inputSet.add(4);
		inputSet.add(5);
		BitSet bits = new BitSet();
		Set<Set<Integer>> result = new HashSet<>();

		generateSubsets(0, inputSet, bits, result);

		result.stream().forEach(list -> System.out
				.println("{" + list.stream().map(l -> Integer.toString(l)).collect(Collectors.joining(",")) + "}"));
		System.out.println(result.size());

	}

	/**
	 * Recursively generate subsets. The idea is an element in the array may be
	 * part of the subset or may not be part of the subset. We maintain a bitset
	 * to denote if an element at ith index is present or not. Call the function
	 * recursively by setting the ith index and clearing the ith index in the
	 * bitset
	 * 
	 * @param offset
	 * @param inputSet
	 * @param bits
	 * @param result
	 */
	private static void generateSubsets(int offset, List<Integer> inputSet, BitSet bits, Set<Set<Integer>> result) {
		if (offset == inputSet.size()) {
			HashSet<Integer> current = new HashSet<>();
			for (int i = 0; i < bits.size(); i++) {
				if (bits.get(i)) {
					current.add(inputSet.get(i));
				}
			}
			result.add(current);
			result.add(new HashSet<>());
			return;
		}

		bits.set(offset);
		generateSubsets(offset + 1, inputSet, bits, result);
		bits.clear(offset);
		generateSubsets(offset + 1, inputSet, bits, result);

	}

}
