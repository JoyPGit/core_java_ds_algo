import java.util.*;

// https://leetcode.com/problems/distribute-candies-to-people
/** candies>=count helps to stop 
 * assign remaining candies to the next person
 * as we are traversing the array multiple times, 
 * use mod circular array concept
 */
public class Mathprob {
    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        int count = 1; int i =0;
        while(candies>=count){
            res[i%num_people] += count;       
            i++;
            candies-=count++;
        }
        if(candies!=0) res[i%num_people] += candies;
        
        return res;
    }

    // https://leetcode.com/problems/lemonade-change/
    public boolean lemonadeChange(int[] bills) {
        int n = bills.length;
        
        int c5 = 0; int c10 =0; 
        for(int i :bills){
            if(i==5) c5++;
            else if(i == 10) {
                c10++;
                if(c5>=1) c5--; 
                else return false;
            }
            else {
                if(c5>=1 && c10>=1){
                    c5--; c10--;
                } else if(c5>=3) c5-=3;
                else return false;
            }
            // System.out.println("c5 "+c5 + " c10 "+c10);
        }
        return true;
    }
}