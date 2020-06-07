import java.util.*;

class stoneGame {

    int sum = 0;
    int count = 1;

    int start = 0;
    int end = 0;
    int M = 1;
    int x = 0;

    public int stoneGameII(int[] piles) {
        end = piles.length;
        int start =0;
        while (start!= piles.length-1) {// 1 while instead of recursion
            
            int length = end - start;

            
            x = 2*M;
            System.out.println("X "+x);
            M = x;

            System.out.println("M "+M);
            if (count % 2 != 0) { // 2 count
                for (int i = start; i < x-1; i++) {
                    if (i < end-1) {
                        sum = sum + piles[i];
                        System.out.print("Sum " + sum);
                        start++;
                        System.out.println("start "+start);
                    }
                }
            }
            count++;
        }
        return sum;
    }

    public static void main(String[] args) {
        stoneGame sol = new stoneGame();
        int[] piles = new int[] { 2, 7, 9, 4, 4 };

        System.out.print("maxSum" + sol.stoneGameII(piles));
    }

}