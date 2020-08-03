import java.util.*;

public class SlidingWindow {

    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length; int sum =0; 
        int min =Integer.MAX_VALUE; int left =0;
        
        
        for(int i =0; i<n; i++){
            sum+=nums[i];
            while(sum >= s){
                min = Math.min(i-left+1, min);
                // System.out.println("min "+min);
                // System.out.println("sum "+sum);
                sum = sum - nums[left++];
                // System.out.println("sum new "+sum);
                // System.out.println("left "+left);
            }
        }
        return min == Integer.MAX_VALUE?0:min;
    }

    public static void main(String[] args) {
        SlidingWindow slide = new SlidingWindow();
        int sum = 11; int[] nums = {1,2,3,4,5};    
        slide.minSubArrayLen(sum, nums);

    }
}