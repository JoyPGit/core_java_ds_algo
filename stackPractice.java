import java.util.*;

public class stackPractice {
    
    void print1DMatrix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if(i==arr.length-1) {
                System.out.println(arr[i]+";");
                System.out.println();
            } 
            else System.out.print(arr[i] + ", ");
        }
    }

    void stockSpan(int[] arr){
        Deque<Integer> stock = new ArrayDeque<Integer>();
        int[] span = new int[arr.length];

        span[0]=1;
        stock.add(0);
        for(int i =1; i<arr.length; i++){
            while(!stock.isEmpty() && arr[stock.peek()]<arr[i]) stock.pop();

            if(stock.isEmpty()) span[i] = i+1;
            else {
                System.out.println("peek "+stock.peek());
                span[i] = i - stock.peek();
            }
            print1DMatrix(span);
            stock.push(i);
        }
        print1DMatrix(span);
    }

    public int[] maxSlidingWindowStack(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int [n-k+1]; int index= 0;

        Deque<Integer> list = new LinkedList<>();

        for(int i =0; i<k; i++){
            while(list.size()!=0 && list.getLast()<nums[i]){
                list.removeLast();
            }

            list.add(nums[i]);
            // System.out.println(list);
        }
        res[index++] = list.getFirst();
    
        System.out.println("first k");
        print1DMatrix(res);

        for(int i =k; i<n; i++){
            if(list.getFirst()==nums[i-k]){
                list.removeFirst();                
            }
            
            while(list.size()!=0 && list.getLast()<nums[i]){
                System.out.println("in here");
                list.removeLast();
            }
            
            list.add(nums[i]);
            System.out.println(list);
            res[index++] = list.getFirst();
        }
        print1DMatrix(res);
        return res;
    }

    public static void main(String[] args) {
        stackPractice stack = new stackPractice();

        int[] price = 
        // { 10, 4, 5, 90, 120, 80 }; 
        {100, 80,60,70,60,75,85};
        // stack.stockSpan(price);

        int[] nums = // {7, 2, 4};
        {9,10,9,-7,-4,-8,2,-6};

                // { 1, -1 };
        // {1,3,-1,-3,5,3,6,7};
        int k = 5;//1;// 3;//2
        stack.maxSlidingWindowStack(nums, k);
    }

}