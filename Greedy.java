import java.util.*;

import utilCustom.*;

class Greedy{

    void printList(ArrayList<Integer> list){
        for(int i : list){
            System.out.println(list.get(i));
        }
    }
    void printListArray(ArrayList<int[]> list){
        // for(int i =0; i<list.size(); i++){
        //     System.out.print("["+list.get(i)[0]+","+list.get(i)[1]+"], ");
        // }
        for(int[]i : list){
            System.out.print("["+i[0]+","+i[1]+"], ");
        }
    }

    /**IMP
     * 1 PAIRS DIV BY K
     * 2 ORGANIZE STRING
     * 3 TASK SCHEDULER
     * 4 MAX PERF OF TEAM
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

    // https://leetcode.com/problems/number-of-burgers-with-no-waste-of-ingredients/
    /** soove two linear equations
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
     *    WE COUNT THE INTERVAL CYCLES, N+1 
     *    A->B->IDLE  = N+1
     *    WHEN LIST IS EMPTY WE ADD TEMP SIZE
     *    ONLY A-> THEN WE ADD +1(TEMP SIZE)
     * 
     */
    public int scheduleTasks(char[] tasks, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c: tasks) map.put(c, map.getOrDefault(c, 0)+1);

        PriorityQueue<Integer> q = new PriorityQueue<>((x,y)->y-x);
        q.addAll(map.values());
        int cycles = 0;
        while(q.size()!=0){
            ArrayList<Integer> temp = new ArrayList<>();
            for(int i =0; i<q.size(); i++){
                if(q.size()!=0) temp.add(q.remove());
            }

            for(int i : temp){
                if(--i > 0) q.add(i);
            }
            cycles+= q.size()==0?temp.size():k+1;
        }
        return cycles;
    }

    // https://leetcode.com/problems/reorganize-string/
    class Reorg{
        char c; int freq;
        Reorg(char ch, int f){
            this.c = ch;
            this.freq = f;
        }
    }
    public String reorganizeString(String s) {
        char[] ch = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(int i =0; i<ch.length; i++){
            if(map.containsKey(ch[i])){
                map.put(ch[i], map.get(ch[i])+1);
            }
            else map.put(ch[i], 1);
        }
        
        PriorityQueue<Reorg> q = new PriorityQueue<>((x,y)-> y.freq-x.freq);
        for (Map.Entry<Character, Integer> entry : map.entrySet()) 
            q.add(new Reorg(entry.getKey(), entry.getValue()));
        
        int k = 2;
        String res ="";
        ArrayList<Reorg> temp = new ArrayList<>();
        
        while(q.size()!=0){
            Reorg curr = q.remove();
            res+=curr.c;
            // System.out.println(res);
            temp.add(new Reorg(curr.c, curr.freq-1));    
            
            if(temp.size()<k) continue;
            Reorg pushBack = temp.remove(0);
            // System.out.println("pushBack "+pushBack.c+" "+pushBack.freq);
            if(pushBack.freq>0) q.add(pushBack);
            
        }
        
        return res.length() == s.length()?res:"";
    }

    

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
   

    // https://leetcode.com/problems/queue-reconstruction-by-height/
    class People{
        int h; int k;
        People(int height, int num){
            this.h = height;
            this.k = num;
        }
    }
    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        ArrayList<People> list = new ArrayList<People>();
        

        for(int i = 0; i<n; i++){
            list.add(new People(people[i][0], people[i][1]));
        }
        Collections.sort(list, (x, y)-> y.h-x.h);
        
        for(int i =1; i<n; i++){
            People curr = list.remove(i);
            list.add(curr.k, curr);
        }
        int i=0; 
        for(People p :list) {
            people[i][0] = p.h;
            people[i][1] = p.k;
            i++;
        }

        for(People p:list) System.out.println(p.h+" "+p.k);
        utilCustom.Utility.printMatrix(people);
        return people;
        // return list.toArray();
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

    class Divide{
        int key, freq;
        Divide(int k, int f){
            this.key = k; this.freq =f;
        }
    }
    /** technique :
     * 1 sort 
     * 2 add in hashmap
     * 3 add in pQueue which is sorted in terms of lower key
     * 4 add to ArrayList(why not map? 
     *  because in arraylist you can fetch at index, required for 
     *  putting back to heap)
     * 
     * the TRICK is to remove k elements in one go so that we don't get the 
     * same element again from heap on removal
     * 
     * 5 if freq is zero don't put back
     * 6 final check for consecutive
     */
    // https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
    public boolean isPossibleDivide(int[] nums, int k) {
        Arrays.sort(nums);
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i:nums) map.put(i, map.getOrDefault(i, 0)+1);

        PriorityQueue<Divide> heap = new PriorityQueue<Divide>((x,y)->x.key-y.key);
        for(Map.Entry<Integer, Integer> e : map.entrySet()){
            heap.add(new Divide(e.getKey(), e.getValue()));
        }

        ArrayList<Divide> list = new ArrayList<Divide>(k);

        while(heap.size()!=0){

            for(int i =0; i<k; i++){
                if(heap.size()!=0){
                    Divide curr = heap.remove();
                    if((i-1)!=0 && (curr.key - list.get(i-1).key)!=1) return false; 
                    list.add(new Divide(curr.key, curr.freq));
                }else return false;
            }
            
            for(int i =0; i<list.size(); i++){
                Divide retrace = list.get(i); 
                if(retrace.freq>1) {
                    heap.add(new Divide(retrace.key, retrace.freq -1));
                }
            }
            
            // checking for consecutive
            for(int i=0; i<k; i++){
                if((list.get(i+1).key - list.get(i).key) !=1) return false;
            }

            list.clear();

        }
        return true;
    }
   

    /**Similar compare prev end and next start
     * https://leetcode.com/problems/merge-intervals/discuss/21222/A-simple-Java-solution
    */
    // https://leetcode.com/problems/merge-intervals/
    /** 
     * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
     * Output: [[1,6],[8,10],[15,18]]
     * 
     * POINTS:
     * 1 Arrays.sort()
     * 2 ARRAYLIST OF int[]
     * 3 prevInterval = intervals[0] prevInterval holds a reference to the intervals[0]
     * so any time it is changed the entry in the list changes, until prevInterval is
     * updated to point ot something else 
     * 
     * 4 prevInterval is added to list and its ref maintained
     * we iterate over the list and if current[0]<pev[1], then we update 
     * prev[1] to max of prev[1], current[1], 
     * for cases like [1,8] and [2,4], prev must be [1,8]
     * else update reference and add to list
     * 
     * 5 list.toArrays(new int[list.size()][]);
     */

    public int[][] mergeIntervals(int[][] intervals) {
		if (intervals.length <= 1) return intervals;

		// Sort by smaller starting point
        // Arrays.sort(intervals, (x, y) -> Integer.compare(x[0], y[0]));
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);

        ArrayList<int[]> result = new ArrayList<>();
        
        int[] prevInterval = intervals[0];
        
        result.add(prevInterval);

        for (int[] interval : intervals) {
			if (interval[0] <= prevInterval[1]){
                // Overlapping intervals, update the end if needed
                prevInterval[1] = Math.max(prevInterval[1], interval[1]);
            } 
            else {                           
                // Disjoint intervals, add the new interval to the list
				prevInterval = interval;
				result.add(prevInterval);
            }
        }
        printListArray(result);
		return result.toArray(new int[result.size()][]);
    }

    /** SORT ON THE BASIS OF END POINTS, IF CURR<START OF ANY POINT, COUNT++
     * IMP : ASSIGN CURR AFTER SORTING
     */
    // https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
    public int findMinArrowShots(int[][] points) {
        int n = points.length;
        if(n==0) return 0;
        // if(n==1) return 1;
        Arrays.sort(points, (a, b) -> a[1] - b[1]);
        int count = 1;
        int curr = points[0][1];
        
        for(int i =1; i<n; i++){
            if(curr>=points[i][0]) continue;
            curr = points[i][1];
            count++;
        }
        
        return count;
    }

    // https://leetcode.com/problems/maximum-performance-of-a-team/
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

     // https://leetcode.com/problems/split-array-into-consecutive-subsequences/

    // https://leetcode.com/problems/car-pooling/
    // https://leetcode.com/problems/construct-target-array-with-multiple-sums/
    // https://leetcode.com/problems/largest-values-from-labels/
    // https://leetcode.com/problems/candy/
    // https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
    // EVENT SCHEDULER
    // MEETING ROOM LEETCODE
    // https://www.youtube.com/watch?v=i2bBG7CaVxs
    // https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
    // MICROSOFT
    // https://leetcode.com/discuss/interview-question/447448/

    // https://leetcode.com/problems/non-overlapping-intervals/

    // https://leetcode.com/discuss/interview-question/613816/Google-or-Onsite-or-Meeting-Rooms-3
    // https://leetcode.com/problems/insert-interval/
    // https://leetcode.com/discuss/interview-question/350233/Google-or-Summer-Intern-OA-2019-or-Decreasing-Subsequences
    public static void main(String[] args) {
        Greedy solGreedy = new Greedy();
        int[] mice = new int[]{-10, -79, -79, 67, 93, -85, -28, -94 };
        int[] holes = new int[]{-2, 9, 69, 25, -31, 23, 50, 78 };

        // solGreedy.MiceHole(mice, holes);

        int[] arrivalTimes = {900, 940, 950, 1100, 1500, 1800};
        int[] departureTimes = {910, 1200, 1120, 1130, 1900, 2000};

        // solGreedy.trainPlatform(arrivalTimes, departureTimes);
        int numBottles = 9; int numExchange = 3;
        // solGreedy.numWaterBottles(numBottles, numExchange);

        // int[][] queue = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        // solGreedy.reconstructQueue(queue);

        // System.out.println(solGreedy.findMinFibonacciNumbers(513314));

        int[][] intervals = new int[][] {{1,3},{2,6},{8,10},{15,18}};
        // solGreedy.mergeIntervals(intervals);

        String parLabel = "ababcbacadefegdehijhklij";
        // "eaaaabaaec";
        solGreedy.partitionLabels(parLabel);
    }
}