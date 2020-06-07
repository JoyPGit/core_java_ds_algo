import java.util.*;
import java.lang.*;

class Randomprac{
    int findPath(int[][] arr){
        int m = arr.length;
        int n = arr[0].length;

        for(int i =0; i<m; i++){
            for ( int j =0; j<n; j++){
                if(i != 0 && j ==0){
                    arr[i][j] = arr[i][j] + arr[i-1][j];
                } if(i ==0 && j!=0){
                    arr[i][j] = arr[i][j] + arr[i][j-1];
                } if (i!=0 && j!=0){
                    arr[i][j] = arr[i][j] + min(arr[i-1][j], arr[i][j-1], arr[i-1][j-1]);
                }
            }
        }

        for(int i =0; i<m ; i++){
            for( int j =0; j<n; j++){
                System.out.println(arr[i][j]);
            }
        }
        return arr[m-1][n-1];
    }

    int min(int a, int b, int c){
        int min = 0;
        min = Math.min(a,b);
        min = Math.min(min, c);
        return min;
    }

    void printWithoutLoop(int num){
        printWithoutLoopSubtract(num);
        printWithoutLoopAdd(num);
    }

    void printWithoutLoopSubtract(int num){
        if(num>0){
            System.out.print(num+", ");
            printWithoutLoopSubtract(num-5);    
        }
        return;
    }

    void printWithoutLoopAdd(int num){
        if(num!=16){
            System.out.print(num+", ");
            printWithoutLoopAdd(num+5);    
        }
        System.out.print(num+", ");
        return;
    }

    void printWithoutLoop2(int num){

    }
    public static void main(String[] args) {
        Randomprac sol = new Randomprac();
        int array[][]  = {{1,2,0,8},{6,5,1,2},{3,8,1,6}};

        // System.out.println("findpath "+sol.findPath(array));
        sol.printWithoutLoop(16);
    }
}


    

    /**class, interface, or enum expected
    public static void main(String[] args) {
        
        this comes when main is outside of the class*/

    /**non-static method findPath(int[][]) cannot be referenced from a static context
        System.out.println(findPath(array)); */
