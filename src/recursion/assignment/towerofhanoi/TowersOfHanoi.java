package recursion.assignment.towerofhanoi;

import java.util.Scanner;

/**
 * Tower of Hanoi A Tower of Hanoi is a game, that consists of three rods, and a
 * number of disks of different sizes which can slide onto any rod. The game
 * starts with the disks in a neat stack in ascending order of size on one rod,
 * the smallest at the top. The objective of the puzzle is to move the entire
 * stack to another rod, obeying the following simple rules:
 * 
 * 1. Only one disk can be moved at a time. 2. Each move consists of taking the
 * upper disk from one of the stacks and placing it on top of another stack i.e.
 * a disk can only be moved if it is the uppermost disk on a stack. 3. No disk
 * may be placed on top of a smaller disk.
 * 
 * Input: Number of disks in the first rod. e.g if the number is 5, you can
 * assume that disks are arranged with 1 on top of 2, 2 on top of 3 and so on,
 * until 4 on top of 5, with 5 at the bottom.
 * 
 * Output: Series of steps which leads to all disks landing on the third rod.
 * Each step can be represented by printing the number of disks on each rod
 * after the step is taken.
 * 
 * There are no test-cases set, because of the fluid nature of the output. Feel
 * free to hard code the input and display the output on STDOUT.
 * 
 * Solution: http://www.cs.cmu.edu/~cburch/survey/recurse/hanoiimpl.html
 * 
 * (Suggested time: 45 minutes; maybe a little more if you are printing output
 * nicely)
 * 
 * @author dwaraknathbs
 *
 */
public class TowersOfHanoi {

	public static void main(String[] args) {
		String rodA = "A";
		String rodB = "B";
		String rodC = "C";

		Scanner scanner = new Scanner(System.in);
		int numberofdisks = scanner.nextInt();

		move(numberofdisks, rodA, rodB, rodC);
		scanner.close();

	}

	/**
	 * Recursion contract: To Move N disks from A to B, we first need to move
	 * N-1 disks to a temp rod , move the biggest disk to B and then the
	 * subproblem is to move the rest of the disk to the destination keeping A as
	 * temp and so on
	 * 
	 * @param disk
	 * @param source
	 * @param destination
	 * @param spare
	 */
	private static void move(int disk, String source, String destination, String spare) {
		if (disk == 1) {
			System.out.println(String.format(" Moving Disk %d from %s to %s", disk, source, destination));
			return;
		}
		move(disk - 1, source, spare, destination);
		System.out.println(String.format(" Moving Disk %d from %s to %s", disk, source, destination));
		move(disk - 1, spare, destination, source);

	}

}
