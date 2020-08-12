import java.util.*;

class Greedy{

    /**IMP
     * 1 PAIRS DIV BY K
     * 2 ORGANIZE STRING
     * 3 TASK SCHEDULER
     */

    void MiceHole(int[] mice, int[] holes){
        Arrays.sort(mice);
        Arrays.sort(holes);

        int difference = -1;
        /**trick is you can use 2 conditions in the middle statement of the for loop */
        for(int i =0; i< mice.length; i++){
            if(Math.abs(mice[i]-holes[i]) > difference) difference = Math.abs(mice[i]-holes[i]); 
        }

        System.out.println("the max time is  "+ difference);
    }

    void minSumFrom2Arrays(int[] arr1, int[] arr2){
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        int length = Math.max(arr1.length, arr2.length);

        for(int i =length; i>0; i--){
            int sum = 0;
            int j =i;
            // if(j>0){
            //     sum = arr1[]
            // }
        }
    }

    // void policeCatchThief(int[] arr){
    //     for(int i =)
    // }
    
    /**float vs double
     * https://study.com/academy/lesson/java-floating-point-numbers.html
     * float q = 506.12789f;
        Here the variable q saves the value 506.1247 as a float.

        float r = -101.23;
     */
    class Holder{
        String arrOrDep;
        float time;

        Holder(String type, float time){
            this.arrOrDep = type;
            this.time = time;
        }
    }
    void trainPlatform(int[] arrival, int[] departure){
        Arrays.sort(arrival);
        Arrays.sort(departure);

        ArrayList<Holder> newArray = new ArrayList<Holder>();
        int arrivalStart = 0; int departureStart = 0; 

        Holder newholder;
        /**
         * error: unclosed character literal
         * Strings in Java need to be enclosed in double quotes. Use "hello"
         */

        /**use arraylist 
         * https://stackoverflow.com/questions/18578864/double-array-initialization-in-java/47036616 
         * */
        while(arrivalStart<arrival.length){
            if(arrival[arrivalStart]<departure[departureStart]){
                newholder = new Holder("arrival",arrival[arrivalStart]);
                newArray.add(new Holder("arrival",arrival[arrivalStart]));
                arrivalStart++;
            } else {
                newArray.add(new Holder("departure",departure[departureStart]));
                departureStart++;
            }
        }

        while(departureStart<departure.length){
            newArray.add(new Holder("departure",departure[departureStart]));
            departureStart++;
        }

        // Arrays.sort(newArray);

        int platformCount = 0; int max = 0;
        for(int i =0; i<newArray.size(); i++){
            if((newArray.get(i)).arrOrDep.equals("arrival")) {
                platformCount++;
                max = Math.max(max, platformCount);
            }   
            else platformCount--;
        }

        System.out.println("the min no platforms required are "+ max);
    }

    void MaxTipCalculator(){}

    // https://leetcode.com/problems/course-schedule-iii/

    // https://leetcode.com/problems/delete-columns-to-make-sorted/
    /** points:
     * 1 string -> length()
     * 2 how to check for string at index x and character at index y?
     * 3 are 2 counters needed or a single will do?
     * here 3 are used but it can be reduced to 2 by :
     *  
     * for (int j = 0; j < A[0].length(); j++) {
            for (int i = 0; i < A.length - 1; i++) {
                if (A[i].charAt(j) > A[i + 1].charAt(j)) {
        i is used for string index
     */
    public int minDeletionSize(String[] A) {
        int index =0; int count =0;
        
        for(int i=0; i<A[0].length(); i++){
            for(int j =0; j<A.length-1; j++){
                if((A[j].charAt(index)-'0')>(A[j+1].charAt(index)-'0')) {
                    count++;
                    break;
                }
            }
            index++;
        }
        return count;
    }

    // https://leetcode.com/problems/water-bottles/
    public int numWaterBottles(int numBottles, int numExchange) {
        int i = 0; 
        while(i <= numBottles){
            if(i%numExchange==0) {
                numBottles++;
            }
            i++;
        }

        // for(i =1; i<=numBottles; i++){
        //     if(i%numExchange==0) numBottles++;
        // }
        // System.out.println(numBottles);
        return numBottles;
    }

    /** points:
     * 1 -ve no divided by k gives same remainder as (+ve)*(-1)
     * 2 if num<k add k to bring it within range    
     * 3 if frequency of els having remainder as 0 is not even, 
     * i.e there is not a balanced no of els having 0 rem, return false
     * 4 check freq for i and k-i in freq array
     */
    // https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/
    public boolean ifPairsDivByK(int[] arr, int k) {
        int[] frequency = new int[k];
        for(int num : arr){
            num %= k;
            if(num < 0) num += k;
            frequency[num]++;
        }
        
        if(frequency[0]%2 != 0) return false;
        
        for(int i = 1; i <= k/2; i++)
            if(frequency[i] != frequency[k-i]) return false;
			
        return true;
    }

    // https://leetcode.com/problems/reorganize-string/
    // https://leetcode.com/problems/task-scheduler/

    /** POINTS : 
     * 1 HASH -> PQUEUE -> LIST(ADD AND REMOVE)
     * 2 HASHMAP STORES COUNTS
     * 3 PQUEUE STORES COUNTS IN DESC ORDER
     * 4 FOR N+1(AS WE ARE WAITING THE FULL N SECONDS) ADD TASKS
     *   FROM QUEUE TO LIST
     * 5 THEN FROM LIST ADD THE COUNTS>0 BACK TO QUEUE  
     * 6 FOR( C : TASKS )
     * 7 MAP.GETORDEFAULT(C, 0)
     * 8 ADD A CHECK FOR LIST.SIZE()!=0 WHILE REMOVING
     * 9 --I>0
     * 10 MOST IMP HOW CYCLES ARE CALULATED : IF LIST IS NOT EMPTY,
     * WE COUNT THE INTERVAL CYCLES, N+1 
     * A->B->IDLE  = N+1
     * WHEN LIST IS EMPTY WE ADD TEMP SIZE
     * ONLY A-> THEN WE ADD +1(TEMP SIZE)
     * 
     */
    public int leastInterval(char[] tasks, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        int n = tasks.length; 
        // for(int i=0; i< n; i++){
        //     // optimization
        //     map.put(tasks[i], map.getOrDefault(tasks[i], 0)+1);
        //     // if(map.containsKey(tasks[i])){
        //     //     map.put(tasks[i], map.get(tasks[i])+1);
        //     // } else map.put(tasks[i], 1);
        // }
        // optimization
        for(char c: tasks) map.put(c, map.getOrDefault(c, 0)+1);

        PriorityQueue<Integer> list = new PriorityQueue<>((x,y)->y-x);
        list.addAll(map.values());
        int cycles = 0;
        while(list.size()!=0){
            ArrayList<Integer> temp = new ArrayList<>();
            for(int i =0; i<list.size(); i++){
                if(list.size()!=0) temp.add(list.remove());
            }

            // for(int i =0; i<k+1; i++){
            //     if(temp.get(i)-1 > 0) list.add(temp.get(i)-1);
            // }
            // optimization
            for(int i : temp){
                if(--i > 0) list.add(--i);
            }
            cycles+= list.size()==0?temp.size():k+1;
        }
        return cycles;
    }

    // https://leetcode.com/problems/partition-labels/
    // https://leetcode.com/problems/maximum-performance-of-a-team/
    // https://leetcode.com/problems/queue-reconstruction-by-height/
    // https://leetcode.com/problems/largest-values-from-labels/
    public static void main(String[] args) {
        Greedy solGreedy = new Greedy();
        int[] mice = new int[]{-10, -79, -79, 67, 93, -85, -28, -94 };
        int[] holes = new int[]{-2, 9, 69, 25, -31, 23, 50, 78 };

        // solGreedy.MiceHole(mice, holes);

        int[] arrivalTimes = {900, 940, 950, 1100, 1500, 1800};
        int[] departureTimes = {910, 1200, 1120, 1130, 1900, 2000};

        // solGreedy.trainPlatform(arrivalTimes, departureTimes);
        int numBottles = 9; int numExchange = 3;
        solGreedy.numWaterBottles(numBottles, numExchange);
    }
}