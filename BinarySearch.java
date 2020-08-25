import java.util.*;

public class BinarySearch {


    int binarySearch(int[] arr, int low, int high, int key) {
        if (low >= 0 && high < arr.length) {
            int mid = (low + high) / 2;
            if (arr[mid] == key)
                return mid;
            if (arr[mid] < key)
                binarySearch(arr, mid + 1, high, key);
            if (arr[mid] > key)
                binarySearch(arr, low, mid, key);
        }
        return -1;
    }
    

    void print1DMatrix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if(i==arr.length-1) {
                System.out.println(arr[i]+";");
                System.out.println();
            } 
            else System.out.print(arr[i] + ", ");
        }
    }

    void printList(ArrayList<Integer> list){
        for(int i =0; i<list.size(); i++){
            if(i==list.size()-1) {
                System.out.println(list.get(i)+";");
                System.out.println();
            } 
            else System.out.println(list.get(i) +", ");
        }
    }
    
    /**
     * https://www.hackerrank.com/challenges/
     * climbing-the-leaderboard/problem?isFullScreen=true
     * 
     * approach
     * implement an arraylist which will not have
     * any duplicates. use binary search for alice
     */

    int[] climbingLeaderboard(int[] scores, int[] alice) {
        int n = scores.length;
        // int[] fina = new int[n];
        ArrayList<Integer> fina = new ArrayList<Integer>();
        fina.add(scores[0]);

        for(int i= 1; i<n; i++ ){
            if(scores[i]!=scores[i-1]){
                fina.add(scores[i]);
            }
        }

        int x= alice.length;
        int[] res = new int[x];

        for(int i =0; i<x; i++){
            System.out.println("alice[i] "+alice[i]);
            if(fina.get(0)<alice[i]) res[i] = 1;
            else if(fina.get(fina.size()-1)>alice[i]) res[i] = fina.size()+1;
            else res[i] = leaderboardBinarySearch(fina, 0, fina.size()-1, alice[i])+1;
        }

        print1DMatrix(res);
        return res;

    }

    int leaderboardBinarySearch(ArrayList<Integer> list, int start, int end, int val){
        if(start<=end){
            int mid = (start+end)/2;
            System.out.println("mid "+list.get(mid));
            if(list.get(mid) == val){
                return mid;
            }
            else if(list.get(mid)>val && list.get(mid+1)<val){
                return mid+1;
            }
            else if(list.get(mid)<val && list.get(mid-1)<val){
                return mid;
            }
            else if(list.get(mid)>val){
                return leaderboardBinarySearch(list, mid+1, end, val);
            } 
            else if(list.get(mid)<val){
                return leaderboardBinarySearch(list, start, mid-1, val);
            } 
        }
        return -1;
    }


     /**
      * https://www.geeksforgeeks.org/find-the-row-with-maximum-number-1s/
      */
      boolean searchRowColSortedMatrix(final int[][] arr, final int num) {
        int row = -1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1][0] > num && arr[i][0] < num)
                row = i;
        }
        if (row == -1) {
            if (arr[arr.length - 1][0] < num)
                row = arr.length - 1;
            else if (row == -1)
                return false;
        }
        System.out.println(row);
        return binsearch(arr, row, num, 0, arr[0].length - 1);
    }

    boolean binsearch(final int[][] arr, final int row, final int num, final int start, final int end) {
        if (start > end)
            return false;
        int mid = -1;
        if (start <= end) {
            mid = (start + end) / 2;
        }
        if (arr[row][mid] == num)
            return true;
        else if (arr[row][mid] > num)
            return binsearch(arr, row, num, start, mid - 1);
        else
            return binsearch(arr, row, num, mid + 1, end);
    }

    // https://jackygao.wordpress.com/2014/09/04/the-painters-partition-problem-part-ii/

      public static void main(String[] args) {
        BinarySearch binSear = new BinarySearch();
        int[] scores = {100, 100, 50, 40, 40, 20, 10};
        int[] alice = {5, 25, 50, 120};
        binSear.climbingLeaderboard(scores, alice);
      }
    
}