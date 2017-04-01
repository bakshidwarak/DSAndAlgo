

package leetcode.climbingstairs;

/**
 * 70. Climbing Stairs <br>
 * You are climbing a stair case. It takes n steps to reach to the top. Each
 * time you can either climb 1 or 2 steps. In how many distinct ways can you
 * climb to the top? Note: Given n will be a positive integer. Show Company Tags
 * Show Tags
 * 
 * @author dwaraknathbs
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs(10));
        System.out.println(climbStairsWithJustStoringTheLastVals(10));

    }

    public static int climbStairs(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (n == 0)
            return 0;
        int[] cache = new int[n + 1];
        cache[0] = 0;
        cache[1] = 1;
        cache[2] = 2;

        for (int i = 3; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }
        return cache[n];
    }

    public static int climbStairsWithJustStoringTheLastVals(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (n == 0)
            return 0;

        int first = 1;
        int second = 2;
        int nthstep = 0;

        for (int i = 3; i <= n; i++) {
            nthstep = first + second;
            first = second;
            second = nthstep;
        }
        return nthstep;
    }

}
