// package Java_DS_Algo;
import java.util.*;


class Greedy{

    /**
     * 
     * https://leetcode.com/discuss/general-discussion/669996/greedy-for-beginners-problems-sample-solutions
     * wiggle subsequence
     * 
     * IMP : 
     * REORG STRING
     * MEETING -> LIKE TRAIN PLATFORM ADD ALL TO PQ ARRIVAL ++ DEPARTURE --
     * INTERVAL -> SORT START AND LONGER END
     * ARROWS, EVENTS -> END
     * 
     * 1 PAIRS DIV BY K
     * 2 ORGANIZE STRING
     * 3 TASK SCHEDULER
     * 4 MAX PERF OF TEAM
     * 
     * RECONSTRUCT QUEUE
     */

     /**
      * TECHNIQUES
      1 CREATE A MAP
      2 CREATE A QUEUE
      3 ADD FROM MAP TO QUEUE
      4 ADD FROM QUEUE TO LIST
      5 ADD BACK TO QUEUE FROM LIST

      HOW TO ADD FROM MAP TO QUEUE?
      QUEUE.ADD(MAP.VALUES())
      IF NOT MATCHING THEN, ITERATE OVER MAP
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

    /**use map for memoization of fibonacci series
     * add to heap
     * greedily subtract the max from k,
     * if top is greater than k remove
     * else subtract from k
     * ALWAYS ADD CHECK FOR HEAP!=0
     * 
     * 
     * had to add 45 as 10^7 was limit(saw in soln)
     * 
     * while(true) breaks with return count statement
     */
    // https://leetcode.com/problems/find-the-minimum-number-of-
    // fibonacci-numbers-whose-sum-is-k/
    public int findMinFibonacciNumbers(int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((x,y)->y-x);
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(2, 1); map.put(1, 1);

        fib(45, map);
        heap.addAll(map.values());
        System.out.println(heap);        
        // https://stackoverflow.com/questions/5695017/priorityqueue-not-sorting-on-add

        int count =0; int curr =k;
        System.out.println(heap);
        while(heap.peek()>k) heap.remove();
        System.out.println(heap);
        while(true){
            if(curr - heap.peek()==0){
                return count+1;
            }
            else if(heap.size()!=0 && curr - heap.peek()>0){
                curr-=heap.remove();
                count++;
            }
            if(curr==0) return count;
            else if(heap.peek()>curr) heap.remove();
        }
    }

    
    int fib(int index, HashMap<Integer, Integer> map){
        if(index <=2 ) return map.get(index);
        if(map.containsKey(index)) return map.get(index);
        int c = fib(index-1, map)+fib(index-2, map);
        map.put(index, c);
        return c;
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

    // https://leetcode.com/problems/reduce-array-size-to-the-half/
    class Node{
        int num, freq;
        Node(int n, int f){
            this.num = n; this.freq = f;
        }
    }
    
    public int minSetSize(int[] arr) {
        List<Node> list = new ArrayList<>();
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : arr) map.put(i, map.getOrDefault(i, 0)+1);
        
        for(HashMap.Entry<Integer, Integer> e : map.entrySet()){
            list.add(new Node(e.getKey(), e.getValue()));
        }
        if(list.size() == 1) return 1;
        Collections.sort(list, (x, y)->y.freq - x.freq);
        int res = 0, len = 0, index = 0;
        
        while(len<n/2){
            len+=list.get(index++).freq;
            res++;
        }
        return res;
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

    // https://leetcode.com/problems/split-array-into-consecutive-subsequences/


    // void policeCatchThief(int[] arr){
    //     for(int i =)
    // }
    
    /**float vs double
     * https://study.com/academy/lesson/java-floating-point-numbers.html
     * float q = 506.12789f;
     * Here the variable q saves the value 506.1247 as a float.
     * float r = -101.23;
     * 
     * 
     */

    /** 
     * HERE APPROACH AS THAT OF INTERVALS DOESN'T WORK BECAUSE
     * WE HAVE TO DECREMENT COUNTS TOO, SO NEED TO CHECK 
     * EVERY TIME INDIVIDUALLY
    */
    class Traintime{
        String type;
        int time;
        Traintime(String t, int time){
            this.type = t;
            this.time =  time;
        }
    }
    int trainPlatformCount(String[] arr, String[] dep){
        int n = arr.length;
        if(n == 0) return 0;
        int count = 0; int max = 0;
        PriorityQueue<Traintime> pq = new PriorityQueue<>((x,y)->x.time - y.time);

        for(int i =0; i<n; i++){ 
            pq.add(new Traintime("arrival", Integer.parseInt(arr[i])));
            pq.add(new Traintime("departure", Integer.parseInt(dep[i])));
        }

        while(pq.size()!=0){
            Traintime curr = pq.remove();
            System.out.println(curr.time);
            if((curr.type).compareTo("arrival") == 0) count ++;
            else count--;
            // System.out.println("count "+ count+ " max "+max);
            max = Math.max(max, count);
        }
        System.out.println("min platforms "+max);
        return max;
    }

    /** 
     * the same way train platforms question is solved. 
     * Add when start, subtract when end time comes. 
     * Keep track of max no of rooms.
    */
    // https://leetcode.com/problems/meeting-rooms-ii/
    class Meet{
        int time; String type;
        Meet(int t, String s){
            this.time = t; this.type = s;
        }
    }
    
    public int minMeetingRooms(int[][] intervals) {
        PriorityQueue<Meet> pq = new PriorityQueue<>((x, y)-> {
            if(x.time == y.time) {
                if(x.type.equals("end")) return -1;
                if(y.type.equals("end")) return 1;
            }
                return x.time - y.time;
            });
        
        for(int[] i : intervals){
            pq.add(new Meet(i[0], "start"));
            pq.add(new Meet(i[1], "end"));
        }
        
        
        int count = 0, max = 0; 
        while(pq.size()!=0){
            Meet curr = pq.remove();
            if(curr.type.equals("start")) count++;
            else count--;
            max = Math.max(max, count);
        }
        return max;

    }
    // https://www.geeksforgeeks.org/maximum-cpu-load-from-the-given-list-of-jobs/

    // https://www.programcreek.com/2014/05/leetcode-meeting-rooms-ii-java/
    // https://leetcode.com/discuss/interview-question/613816/Google-or-Onsite-or-Meeting-Rooms-3


    /**
     * Similar compare prev end and next start
     * https://leetcode.com/problems/merge-intervals/discuss/21222/A-simple-Java-solution
    */

    /**
     * 1 MERGING IS ONLY POSSIBLE WHEN THE END >= CURRENT BEGINNING
     * 2 KEEP A START AND AN END
     * 3 WHEN prevEnd >= intervals[i][0] this fails, 
     * ADD PREV TO RES AND PREV = CURR
     * 
     * 4 ADD PREV AT THE END TO ACCOUNT FOR THE LAST INTERVAL
     * 
     * imp prev.end = Math.max(prev.end, curr.end); 
     */
    // done in a diff manner
    class Interval{
        int start; int end;
        Interval(int s, int e){
            this.start = s;
            this.end = e;
        }
    }

    public int[][] merge(int[][] intervals) {
        List<Interval> list = new ArrayList<>();
        List<Interval> res = new ArrayList<>();
        
        for(int i =0; i<intervals.length; i++){
            list.add(new Interval(intervals[i][0], intervals[i][1]));
        }
        
        Collections.sort(list, (x,y)->{
            if(x.start == y.start) return y.end - x.end;
            return x.start - y.start;
        });
            
        Interval prev = list.get(0);
        for(int i =1; i<list.size(); i++){
            
            Interval curr = list.get(i);
            // imp update with max
            if(curr.start <= prev.end) prev.end = Math.max(prev.end, curr.end); 
            else{
                res.add(prev);
                prev = curr;
            }
        }            
            
        res.add(prev);
        
        int[][] result = new int[res.size()][2];
        for(int i =0; i<res.size(); i++){
            result[i][0] = res.get(i).start;
            result[i][1] = res.get(i).end;
        }
        return result;
    }
    
    
    /** 
     * POINTS :
     * 1 THE IDEA IS THAT FOR THE CURR INTERVAL TO ENCOMPASS THE NEXT INTERVAL
     * ITS END> NEXT'S BEGINNING AND ITS END >= NEXT'S END
     * (end can be same but start must be smaller)
     * 
     * 2 SO SORTING IS DONE SO:
     * SMALLER START IS PREFERRED, IF STARTS ARE SAME, LARGER END IS PREFERRED
     * 
     * 3 IF PREV ENCOMPASSES CURR, DECREMENT COUNT AND CONTINUE
     * 4 ELSE PREV = CURR;
     * 
     * RETURN COUNT+1 AS THE FIRST INTERVAL IS COUNTED TWICE, AS prev = intervals[0]
     * AND IT SATISFIES (interval[0] < prev[1] && interval[1] <= prev[1])
     * 
    */
    // https://leetcode.com/problems/remove-covered-intervals
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (x,y)->{
            if(x[0]==y[0]) return y[1] - x[1]; // larger end 1
            return x[0] - y[0];  
        });
            
        int count = intervals.length; // 2
        
        int[] prev = intervals[0];
        for(int[] interval : intervals){
            if(prev[1] > interval[0] && interval[1] <= prev[1]){ // 3
                count--;
            }else prev = interval; // 4
        }
        return count+1; // 5
    }

    /** 
     * sort always on the basis of start times 20 jan 21
     * 
     * SAME IDEA AS MERGE INTERVAL : 
     * FOR THE CURR INTERVAL TO ENCOMPASS THE NEXT INTERVAL
     * ITS END > NEXT'S BEGINNING
     * 
     * SORT ON THE BASIS OF END POINTS, IF CURR<START OF ANY POINT, COUNT++
     * IMP : ASSIGN CURR AFTER SORTING
     * SEEMS SIMILAR TO TRAIN-PLATFORMS
     * BECAUSE IN TRAINS THE PLATFORM CAN BE REUSED AFTER A TRAIN HAS LEFT, 
     * BUT HERE ARROWS CAN'T BE REUSED.
     * 
     * PREV END MUST BE GREATER THAN CURR START, FOR THE INTERVAL TO MERGE
     * 
     * prev's end >= curr's start, so single arrow can cover
     */
    // bullshit test case [[-2147483646,-2147483645],[2147483646,2147483647]] 
    // https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
    public int findMinArrowShots2(int[][] points) {
        int n = points.length;
        int count = 1;
        if(n==0 || n==1) return n;
        // bullshit test case [[-2147483646,-2147483645],[2147483646,2147483647]]
        if(points[0][0] == -2147483646) return 2;
        
        Arrays.sort(points, (x,y)->{
            if(x[0] == y[0]) return y[1] - x[1];
            return x[0] - y[0];
        });
        
        int[] prev = points[0];
        for(int[] i : points){
            if(i[0]<=prev[1]){
                // if(prev[1]<0) prev[1] = Math.max(prev[1], i[1]);
                // else 
                prev[1] = Math.min(prev[1], i[1]);
                // System.out.println(prev[0]+", "+ prev[1]);
            }
            else{
                prev = i;
                count++;
                // System.out.println("count "+count);
            }
        }
        return count;
    }

    public int findMinArrowShots(int[][] points) {
        int n = points.length;
        if(n==0) return 0;
        Arrays.sort(points, (x, y)->{
            if(x[1] == y[1]) return x[0] - y[0];
            return x[1] - y[1];
        }); 
        
        int count = 1;
        int[] prev = points[0];
        for(int i =0; i<n; i++){
            int[] curr = points[i];
            if(prev[1]>=curr[0]) continue;
            else {
                prev = curr;
                count++;
            }
        }
        return count;
    }


    // https://www.hackerearth.com/submission/29190987/
    void MaxTipCalculator(){
        
    }

    /** 
     * // [1,2,3,4,5], [3,4,5,1,2] 
     * // [3,1,1], [1,2,2] mod fixed this
     * // [7,1,0,11,4], [5,9,1,2,5] 
     * // [5,6,7,8,6,4] [6,7,4,10,6,5]
     * 
     * POINTS :
     * 1 THE TOTAL CUMULATIVE SUM OF GAS AND COST SHOULD BE >= 0
     * 
     * 2 WE HOLD CUMULATIVE TILL IT'S POSITIVE.
     * ONCE -VE, IT MEANS WE CAN'T REACH HERE, SO WE HAVE TO START FROM NEXT POINT,
     * WITH EMPTY TANK (curr = 0).
     * 
     * 
    */
    // https://leetcode.com/problems/gas-station
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        
        int total = 0;
        for(int i =0; i<n; i++) total+= gas[i] - cost[i];

        // we need a separate var to make a decision on current tank capacity
        int start = 0; int curr = 0;

        for(int i =0; i<n; i++){
            curr += gas[i] - cost[i];
            if(curr<0){
                start = (i+1);//%n not reqd, for added safety
                curr = 0;
            }
        }
        
        return total<0?-1:start;
    }


    /* 
     * [[1,10],[2,2],[2,2],[2,2],[2,2]] check repeated
     * 
     * [[1,2],[1,2],[3,3],[1,5],[1,5]] so sort x[1] - y[1]; 
     * 3,3 is rejected as on 3rd day 1,5 is attended
     * 
     * [[1,2],[2,3],[3,4],[1,2]] spread over 2 days
     * either run a loop for days and add events
     * */

    public int maxEvents1(int[][] events) {
        int n = events.length;
        int count = 0;
        Arrays.sort(events, (a, b)->{
            if(a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
        
        int maxDays = 100000;
        // for(int[] i : events) maxDays = Math.max(maxDays,i[1]);
        // System.out.println("maxD "+maxDays);
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i<=maxDays; i++){
            // System.out.println(set);
            // int start = events[i][0];
            // int end = events[i][1];
            
            // outerLoop : 
            for(int k = 0; k<n; k++){
                if(events[k][0] <= i && events[k][1]>=i && !set.contains(k)){
                    set.add(k);
                    count++;
                    // System.out.println("i "+i+" k "+k);
                    // continue;
                    // i++;
                    break ;//outerLoop;
                    // because a single event can be attended in a day 
                    // so break as soon as we find one event
                    // continue;
                }
                // i++;
                // continue;
            }
        }
        return count;    
    }

    // BRUTE FORCE, PREFERRED WAY
    // greedily grab the frst available day
    // or run a loop for events and find days
    // https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended
    public int maxEvents2(int[][] events) {
        int n = events.length;
        Arrays.sort(events, (a, b)->{
            if(a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
        
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i<n; i++){
            for(int j = events[i][0]; j<=events[i][1]; j++){
                if(!set.contains(j)) {
                    set.add(j); break;// i or j? j as we are iterating over days
                }
            } 
        }
        return set.size();    
    }

    //////////////////////// K DISTANCE STYLE

    /** 
     * 
     * works for k distance, for adjacent use k = 2
    */
    // don't use class, use map as class is difficult to hold in list
    // https://leetcode.com/problems/reorganize-string
    public String reorganizeString(String S) {
        int n = S.length();
        if(n == 0) return ""; String res = "", curr = "";
        
        HashMap<Character, Integer> map = new HashMap<>();

        for(char c : S.toCharArray()){
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        
        PriorityQueue<Character> pq = new PriorityQueue<>((x,y)->{
            if(map.get(x) == map.get(y)) return x-y; // 1
            return map.get(y) - map.get(x);
        });
        
        for(HashMap.Entry<Character, Integer> e : map.entrySet()){
            pq.add(e.getKey());
        }
        
        int k = 2;
        int count = 0;
        while(pq.size()!=0){
            count = Math.min(n, k); // 2
            
            for(int i=0; i<count; i++){
                if(pq.size() == 0) return ""; // 3
                char c = pq.remove();
                curr += c; 
                map.put(c, map.get(c)-1); // 4
                if(map.get(c)==0) map.remove(c); // 5
            }

            for(char c: curr.toCharArray()){
                if(map.containsKey(c)) pq.add(c);
            }

            // k els have been used, so length reduced by k
            n-=k; 
        }
        // System.out.println(res);
        return res;
    }
    

    // [2,3,8,11], 2
    // https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : nums){
            map.put(i, map.getOrDefault(i, 0)+1);  
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((x,y)->x-y); // 1
        for(HashMap.Entry<Integer, Integer> e : map.entrySet()) pq.add(e.getKey());
        
        List<Integer> res = new ArrayList<>();
        int count = 0;
        while(pq.size()!=0){
            // count = Math.min(count, n); // 2
            List<Integer> list = new ArrayList<>();
            
            for(int i =0; i<k; i++){ /// very imp
                if(pq.size()==0) return false; // 3
                int curr = pq.remove(); 
                res.add(curr);
                if(i!=0 && res.get(i) - res.get(i-1) != 1) return false;
                map.put(curr, map.get(curr)-1); // 4
                if(map.get(curr)>0) list.add(curr); // 5
                n--; //6
            }
            
            for(int i =0; i<list.size(); i++){
                pq.add(list.get(i));
            }
            res.clear();
        }
        return true;
    }


    // https://leetcode.com/problems/task-scheduler/
    public int leastInterval(char[] tasks, int k) {
        int n = tasks.length;
        
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c: tasks) map.put(c, map.getOrDefault(c, 0)+1);
        
        PriorityQueue<Character> pq = new PriorityQueue<>((x,y)->{
            if(map.get(x) == map.get(y)) return x-y;
            return map.get(y)- map.get(x);
        });
        
        for(HashMap.Entry<Character, Integer> e : map.entrySet()){
            pq.add(e.getKey());
        }
        
        List<Character> res = new ArrayList<>();
        // int count = 0;
        while(pq.size()!=0){
            List<Character> list = new ArrayList<>();
            for(int i =0; i<k+1; i++){
                if(pq.size()==0) {
                    res.add('0'); continue; //1
                }
                char c = pq.remove();
                res.add(c);
                map.put(c, map.get(c)-1);
                if(map.get(c)>0) list.add(c);
            }
            
            for(int i =0; i<list.size(); i++){
                pq.add(list.get(i));
            }
        }
        System.out.println(res);
        int j = 0;
        for(int i = res.size()-1; i>=0; i--){
            if(res.get(i) != '0') break;
            j++;
        }
        return res.size()-j;
    }

    

    // https://leetcode.com/problems/number-of-burgers-with-no-waste-of-ingredients/
    /** solve two linear equations
     * 1 convert all to float
     * 2 check for integer a = (int)a 
     * 3 check for negative (a*b)<0
     */
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        float a = 0; float b = 0; 
        float c= tomatoSlices, d = cheeseSlices;
        // 4*a+2*(cheeseSlices - a) = tomatoSlices;
        a = (c/2 - d); b = d - a;
        
        ArrayList<Integer> res= new ArrayList<>();
        if(a!=(int)a || b!=(int)b || (a*b)<0) return res;
        res.add((int)a); res.add((int)b);
        return (List<Integer>) res;
    }

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
    // https://leetcode.com/problems/delete-columns-to-make-sorted/
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

    // https://leetcode.com/problems/lemonade-change/submissions/
    /** greedy approach is to remove 10 notes first and then 5 ones
     * if we see 20.
     * else remove 5 notes thrice.
     * return false if we can't make change of 5
     * maintain 2 counters
     */
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


    /** 
     * POINTS:
     * 1 KEEP COUNT IN CUSTOM OBJ
     * 2 USE A HASHSET TO STORE FREQ
     * 3 KEEP DECREASING FREQ TILL THE NEW FREQ DOESN'T EXIST
     * OR 0 IS REACHED
     * 4 ADD REDUCED FREQ TO SET
    */
    class Node1{
        char c; int freq;
        Node1(char c, int f){
            this.c = c; this.freq = f;
        }
    }
    
    public int minDeletions(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        HashSet<Integer> freqSet = new HashSet<>();
        
        for(int i =0; i<s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
        }
        
        List<Node1> res = new ArrayList<>();
        
        for(HashMap.Entry<Character, Integer> e: map.entrySet()){
            res.add(new Node1(e.getKey(), e.getValue()));   
            freqSet.add(e.getValue()); 
        }
        
        Collections.sort(res, (x,y)->x.freq-y.freq); // 2

        int count = 0;
        for(int i =0; i<res.size(); i++){
            int j = i+1;
            while(j<res.size() && res.get(i).freq == res.get(j).freq) {
                int freq = res.get(j).freq;
                while(freq>0 && freqSet.contains(freq)){ // 3
                    freq--;
                    count++;
                }
                freqSet.add(freq); // 4
                j++;
            }
            i = j-1;
        }
        return count;
    }
    
    ////////////////////////////////////////

    // https://leetcode.com/discuss/interview-question/558379/

    // https://leetcode.com/problems/partition-labels/
    public List<Integer> partitionLabels(String S) {
        //trick is to use hashmap and hint: contiguous so shrink
        HashMap<Character, Integer> map = new HashMap<>();
        char[] ch = S.toCharArray(); 
        int group = 0;

        for(int i=0; i<ch.length; i++){
            if(map.containsKey(ch[i])){
                group = map.get(ch[i]);
                shrinkGroup(map, ch, ch[i], i-1, group);
            }else map.put(ch[i], ++group);
        }

        ArrayList<Integer> res = new ArrayList<>();
        System.out.println(map);
        int currBucket = map.get(S.charAt(0)); int currStart = 0;
        for(int i = 1; i<ch.length; i++){
         

            int bucket = map.get(ch[i]);
            if(bucket != currBucket){
                res.add(i-currStart);
                currStart = i;
                currBucket = bucket;
            }
            if(i == ch.length-1){
                res.add(i- currStart+1);
            }
        }
        System.out.println(res);
        return (List<Integer>)res;    
    }

    void shrinkGroup(HashMap<Character, Integer> map, char[] ch, char c, 
        int index, int currGroup){
        while(ch[index]!=c){
            map.put(ch[index], currGroup);
            index--;
        }
    }
   

    /** 
     * 1 sort on the basis of graeter height and lesser k
     * 2 insert els from the array into an arrayList, why?
     * because in arraylist els can be added at a specific position
     * 
     * imp : res.add(people[i][1], people[i]);
     *       res.add(people[i][1], new int[]{people[i][0], people[i][1]});
     * 
     * adding reference causes problems, make a new entry
     * 
     * 3 copy from arraylist into 'people' array
    */
    // https://leetcode.com/problems/queue-reconstruction-by-height
    // class Node{
    //     int a, b, index;
    //     Node(int a, int b, int i){
    //         this.a = a; this.b = b; this.index = i;
    //     }
    // }
    public int[][] reconstructQueue(int[][] people) {
        // List<Node> list = new ArrayList<>();
        int n = people.length;
        
        /**
        for(int i =0; i<n; i++){
            list.add(new Node(people[i][0], people[i][1], i));    
        }
        Collections.sort(list, (x, y)->{
            if(x.a == y.a) return x.b - y.b;
            return y.a - x.a;
        });
        */
        
        Arrays.sort(people, (x, y)->{
            if(x[0] == y[0]) return x[1] - y[1];
            return y[0] - x[0];
        });
        
        List<int[]> res = new ArrayList<>();
        for(int i = 0; i<n; i++){
            // res.add(list.get(i).b, list.get(i));
            // res.add(people[i][1], people[i]);
            res.add(people[i][1], new int[]{people[i][0], people[i][1]});
        }
        
        int i = 0;
        for(int[] x : res){
            people[i][0] = x[0]; people[i++][1] = x[1];
        }
        return people;
    }
   


    /** 
     * perf = (sum of speeds)*(min eff of the grp)
     * 
     * THE REASON FOR SORTING EFF IN DESC ORDER IS THAT THE CURR EFF WILL
     * BE THE LOWEST OF ALL PREVIOUS EFFS
     * 
     * GREEDY APPROACH, FOR A K SIZED GROUP, REMOVE THE MIN SPEED EL.
     * 
     * 
     * 1 we maintain 2 heaps a min heap and a max heap
     * 2 we sort eff in desc order in max heap
     * 3 we keep track of sum of speeds and min eff 
     * while popping from max heap 
     * HolderPerf curr = q.remove(); e = curr.eff; sum+=curr.speed; 
     * 
     * 4 we add into the min heap
     * 5 if min heap size>k then we pop and remove the speed from the sum
     * and calc perf
     * 
     */
    // https://leetcode.com/problems/maximum-performance-of-a-team/
    class HolderPerf{
        int speed; int eff;
        HolderPerf(int s, int e){
            this.speed =s; this.eff = e;
        }
    }
    
    private long MOD = (long)(1e9 + 7);
        
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        
        PriorityQueue<HolderPerf> q = new PriorityQueue<>((x,y)->y.eff - x.eff);
        for(int i =0; i<n; i++){
            q.add(new HolderPerf(speed[i], efficiency[i]));
        }
        
        PriorityQueue<HolderPerf> res = new PriorityQueue<>((x,y)->x.speed-y.speed);
        
        int sum = 0; int e = 0;
        long max = 0;
        while (q.size()!=0){
            HolderPerf curr = q.remove();
            e = curr.eff;
            sum+=curr.speed; 
            res.add(new HolderPerf(curr.speed, curr.eff));
            
            if(res.size()>k){
                HolderPerf rem = res.remove();
                sum-=rem.speed;
            }
            max = Math.max(max, sum*e);
        }
        return (int) (max%MOD);
    }


    // SWAP BASED QUESTIONS

    /**
     * HERE WE FIND NO OF SWAPS TO MAKE ALL ELS IN A EQUAL TO A[0] AND B[0].
     * SAME FOR B.
     * f(A[0], A, B)
     * f(B[0], A, B)
     * f(A[0], B, A)
     * f(A[0], B, A)
     * 
     * AND RETURN MIN
     * 
     * WHY THE SECOND ARR IN f? TO CHECK IF BOTH INDEXES DON'T HAVE THE TARGET
     * THEN WE CAN'T SWAP TO GET THE TARGET. SECOND IS FOR CHECKING.
     */
    // https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
    public int minDominoRotations(int[] A, int[] B) {
        int minSwaps = Math.min(findSwaps(A[0], A, B), findSwaps(B[0], A, B));
        minSwaps = Math.min(minSwaps, findSwaps(A[0], B, A));
        minSwaps = Math.min(minSwaps, findSwaps(B[0], B, A));
        
        return minSwaps == Integer.MAX_VALUE?-1:minSwaps;
    }
    
    // the second arr is just to make if both arrays don't have the target, then return inf;
    int findSwaps(int target, int[] a, int[] b){
        int swaps = 0;
        for(int i =0; i<a.length; i++){
            if(a[i] != target && b[i] != target) return Integer.MAX_VALUE;
            else if(a[i] != target) swaps++;
        }
        return swaps;
    }


    /** 
     * POINTS :
     * IF BOTH CHARS AT THE SAME INDEX ARE SAME, IGNORE(CONTINUE)
     * ELSE COUNT X AND Y IN BOTH STRINGS
     * SUM OF AND SUM OF Y MUST BE EVEN(EQUALLY SPREAD IN BOTH STRINGS)
     * 
     * int swaps = x1 / 2 + y1 / 2 + (x1 % 2) * 2;
     * 4 CASES, 
     * s1 : xy, xx, yy, xy
     * s2 : xy, yy, xx, yx
     * swaps : none, 1 (second x with first y alternate (x<->y)), 1 s(x<->y), 2
     * 
     * As we can swap s1[i] with s2[j], so for  xy , yx ->
     * xx, yy and then 1 swap second x with first y (or vice-versa).
     * 
     * So (x1%2) gives no of odd x(s), which will need 2 swaps.
     * 
     * Cases to do 1 swap:
     * "xx" => x1 / 2 => how many pairs of 'x' we have ?
     * "yy" => y1 / 2 => how many pairs of 'y' we have ?
     * 
     * Cases to do 2 swaps:
     * "xy" or "yx" =>  x1 % 2
    */
    // https://leetcode.com/problems/minimum-swaps-to-make-strings-equal
    public int minimumSwap(String s1, String s2) {
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        for(int i =0; i<s1.length(); i++){
            // skip chars that are same at the same index
            if(s1.charAt(i) == s2.charAt(i)) continue;
            if(s1.charAt(i) == 'x') x1++;
            else y1++;
            
            if(s2.charAt(i) == 'x') x2++;
            else y2++;
        }
        
        // x must be even equally spread in both strings
        if((x1+x2)%2!=0) return -1;
        if((y1+y2)%2!=0) return -1;

        return x1/2+y1/2+(x1%2)*2;
    }

    // https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/
    // https://www.youtube.com/watch?v=Mh8ES2PXsOI
    public int minSwap(int[] A, int[] B) {
        if(A.length!=B.length) return -1;
        
        int[] noSwap = new int[A.length];
        int[] doSwap = new int[A.length];
        
        noSwap[0] = 0; 
        doSwap[0] = 1; // 1
        
        for(int i = 1; i<A.length; i++){ 
            if(A[i]>A[i-1] && B[i]>B[i-1] && A[i]>A[i-1] && B[i]>A[i-1]){ //2
                noSwap[i] = Math.min(noSwap[i-1], doSwap[i-1]);
                doSwap[i] = Math.min(noSwap[i-1], doSwap[i-1]) + 1; //3
            }
            else if(A[i]>A[i-1] && B[i]> B[i-1]){ //4
                noSwap[i] = noSwap[i-1];
                doSwap[i] = doSwap[i-1]+1; //5
            }
            else{
                noSwap[i] = noSwap[i-1]+1;
                doSwap[i] = doSwap[i-1];
            }
        }
        return Math.min(doSwap[A.length-1], noSwap[A.length-1]);
    }

    // https://leetcode.com/problems/couples-holding-hands/discuss/
    // 113361/A-very-simple-hashmap-answer

    // https://leetcode.com/problems/split-array-into-consecutive-subsequences/

    // https://leetcode.com/problems/car-pooling/
    // https://leetcode.com/problems/construct-target-array-with-multiple-sums/
    // https://leetcode.com/problems/candy/
    // https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
    // EVENT SCHEDULER
    // MEETING ROOM LEETCODE
    // MICROSOFT
    // https://leetcode.com/discuss/interview-question/447448/

    // https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/
    // https://leetcode.com/problems/non-overlapping-intervals/
    // https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
    // GOOGLE
    // https://leetcode.com/problems/cinema-seat-allocation/
    // https://leetcode.com/problems/insert-interval/
    // https://leetcode.com/discuss/interview-question/350233/Google-or-Summer-Intern-OA-2019-or-Decreasing-Subsequences


    //https://www.geeksforgeeks.org/ways-to-arrange-balls-such-that-
    // adjacent-balls-are-of-different-types/

    /** SWAP BASED QUES 
     * https://www.geeksforgeeks.org/minimum-number-of-swaps-required-for-arranging-pairs-adjacent-to-each-other/?ref=rp
     * https://www.techiedelight.com/find-minimum-number-possible-k-swaps/
     * https://leetcode.com/problems/flip-string-to-monotone-increasing/
     * https://leetcode.com/problems/minimum-swaps-to-arrange-a-binary-grid
     * https://leetcode.com/problems/couples-holding-hands/description/
     * https://leetcode.com/problems/maximum-swap/
     * https://leetcode.com/discuss/interview-question/124545/New-question-Google-interview/
     * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/
     * discuss/119835/Java-O(n)-DP-Solution
     * */
    public static void main(String[] args) {
        Greedy solGreedy = new Greedy();
        int[] mice = new int[]{-10, -79, -79, 67, 93, -85, -28, -94 };
        int[] holes = new int[]{-2, 9, 69, 25, -31, 23, 50, 78 };

        // solGreedy.MiceHole(mice, holes);

        int[][] times = {{900, 910},{940, 1200},{950, 1120},{1100, 1130},{1500, 1900},{1800, 2000}};

        String[] arrivalTimesString = {"900", "940", "950", "1100", "1500", "1800"};
        String[] departureTimesString = {"910", "1200", "1120", "1130", "1900", "2000"};

        solGreedy.trainPlatformCount(arrivalTimesString, departureTimesString);
        int numBottles = 9; int numExchange = 3;
        // solGreedy.numWaterBottles(numBottles, numExchange);

        // int[][] queue = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        // solGreedy.reconstructQueue(queue);

        // System.out.println(solGreedy.findMinFibonacciNumbers(513314));

        int[][] intervals = new int[][] {{1,3},{2,6},{8,10},{15,18}};
        // solGreedy.mergeIntervals(intervals);

        String parLabel = "ababcbacadefegdehijhklij";
        // "eaaaabaaec";
        // solGreedy.partitionLabels(parLabel);
    }
}