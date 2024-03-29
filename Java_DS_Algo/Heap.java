package Java_DS_Algo;
import java.util.*;
public class Heap {
    /** 
     * kth smallest in matrix
     * HEAPSORT, SMALLEST RANGE, MEDIAN
     * 
     * IMP POINTS :
     * 1 WHILE COMPARING VALUES, IF A HASHMAP IS USED, DON'T
     * STORE THE WHOLE OBJECT, STORE ONLY CHARS OR INTEGERS
     * AND USE MAP TO SORT, BUT BE VERY CAREFUL IN SOME CASES
     * IT IT REQD TO SOTRE THE WHOLE CLASS
     * 
     * PriorityQueue<Character> q = new PriorityQueue<>((x,y)->{
     *     int[] a = map.get(x); int[] b = map.get(y);
     *      for(int i =0; i<a.length; i++){
     *          if(a[i]!=b[i]) return b[i]-a[i]; // 2 max heap
     *     }
     *     return x-y;
     * });
     * 
     * 2 ONCE A NODE HAS BEEN ADDED TO PQ, EVEN IF ITS VALUE IS CHANGED 
     * IN THE CORREPONDING MAP, IT WON'T AFFECT THE POSITION IN THE PQ
     * THAT'S WHY NETWORK DELAY QUES WAS FAILING
     * 
     * */
    // int[] visited = new int[]{200, 1, 30};
    // PriorityQueue<Integer> pq = new PriorityQueue<>((x,y)->visited[x] - visited[y]);
    // pq.add(0);
    // pq.add(1);
    // pq.add(2);
    // System.out.println(pq);
    // visited[1] = 1000;
    // System.out.println(pq);

    int[] heapArray;
    int lastIndex;
    private int lastIndexDelete = -1;
    private int largestSingle;

    Heap(int capacity) {
        heapArray = new int[capacity];
        this.lastIndex = capacity - 1;
    }
    /** 
     * 
     * HEAPSORT(nums)
     * 1 BUILD-MAX-HEAP(nums)
     * 2 for i = A:length downto 2
     * 3 exchange A[1] with A[i]
     * 4 A.heap-size = A.heap-size-1
     * 5 MAX-HEAPIFY(A,1)

     * The HEAPSORT procedure takes time O.n lg n/, 
     * since the call to BUILD-MAXHEAP takes time O.n/ 
     * and each of the n  1 calls to MAX-HEAPIFY takes time O.lg n/.
    */

    // Each call to HEAPIFY costs O(lg n), and HEAPSORT makes O(n)
    // such calls. Thus, the running time is O(nlg n).
    void heapSort(int[] nums){
        int n = nums.length;
        for(int i = n/2 -1; i>=0; i--){
            heapify(nums, i);
        }
    }
    
    // add left and right index check individually
    void heapify(int[] nums, int parentIndex){
        int min = parentIndex;
        int left = parentIndex*2;
        int right = parentIndex*2 +1;
        
        System.out.println(parentIndex*2);
        System.out.println(parentIndex*2+1);
        if(left < nums.length && nums[parentIndex]>nums[left]) min = left;
        if(right < nums.length && nums[min]>nums[right]) min = right;
        
        if(min != parentIndex){
            swap(nums, parentIndex, min);
            // heapify(nums, min);
        }
    }
    
    void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    void printHeap() {
        for (int i = 0; i < heapArray.length; i++) {
            System.out.println(this.heapArray[i]);
        }
    }

    /**
     * one big mistake while using recursion was that the last indexes were also
     * used to calculate children which then went out of bounds. As the left and
     * right children were initialised to 0, the indexes were getting wrongly set.
     * So use -1 as initial value
     * 
     * also the variables need to be initialized outside of current scope.
     */

    void deleteFromHeap() {
        for (int i = 0; i < heapArray.length; i++) {
            if (heapArray[i] == 0) {

                System.out.println("the empty index is " + i);
                System.out.println("the last value is " + heapArray[i]);
                lastIndexDelete = i;

            }
        }

        if (lastIndexDelete == -1) {
            lastIndexDelete = heapArray.length - 1;
        }

        System.out.println("lastIndexDelete " + lastIndexDelete + " value is " + heapArray[lastIndexDelete]);
        heapArray[0] = heapArray[lastIndexDelete];
        heapArray[lastIndexDelete] = 0;

        printHeap();
        // heapifySinglePath(0);
    }

    /** 
     * for using a priorityQueue (a max heap) with a hashmap
     * use new PriorityQueue<Integer>((x, y)->y-x) when setting map for 
     * a key for the first time.
     * 
     * don't use when initializing the map
     * 
     * 
    */
    // https://leetcode.com/problems/high-five/
    public int[][] highFive(int[][] items) {
        
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        
        for(int[] i: items){
            PriorityQueue<Integer> p = map.getOrDefault(i[0], 
                                                new PriorityQueue<Integer>((x, y)->y-x));
            p.add(i[1]);
            map.put(i[0], p);
        }
        
        // System.out.println(map);
        int[][] res = new int[map.size()][2]; int index = 0;
        for(HashMap.Entry<Integer, PriorityQueue<Integer>> e : map.entrySet()){
            res[index][0] = e.getKey();
            res[index++][1] = avg(map.get(e.getKey()));
        }
        
        Arrays.sort(res, (x, y)->x[0] - y[0]);
        return res;
    }
    
    int avg(PriorityQueue<Integer> pq){
        int sum = 0, k = 5;
        while(k-->0) sum+=pq.remove();
        return sum/5;
    }

    /** 
     * 1 use quicksort partition
     * 2 if x > position, hi = x-1 and vice-versa
    */
    // https://leetcode.com/problems/kth-largest-element-in-an-array
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length, lo = 0, hi = n-1;
        while(lo<=hi){
            int x = partition(nums, lo, hi);
            // System.out.println(x);
            if(x == n-k) return nums[x]; // index
            else if(x<n-k) lo = x+1;
            else hi = x-1;
        }
        return lo;
    }
    
    int partition(int[] arr, int lo, int hi){
        int j = lo, pivot = arr[hi];
        for(int i = lo; i<hi; i++){
            if(arr[i]<pivot) swap(arr, i, j++);
        }
        swap(arr, hi, j);
        return j;
    }
    

    // https://leetcode.com/problems/reduce-array-size-to-the-half/submissions/
    class Node{
        int num, count;
        Node(int a, int b){
            this.num = a;
            this.count = b;
        }
    }
    
    public int minSetSize(int[] arr) {
        int count = arr.length, half = count/2, res = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((x, y)->y.count - x.count);
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i : arr){
            map.put(i, map.getOrDefault(i, 0)+1);    
        }
        
        for(HashMap.Entry<Integer, Integer> e : map.entrySet()){
            pq.add(new Node(e.getKey(), e.getValue()));
        }
        
        while(count>half){
            count-=pq.remove().count;
            res++;
        }
        return res;
    }
    
    // use 2 heaps, smaller max heap 
    // heap is by default a min heap
    // compare diff and pop till k!=0
    // https://leetcode.com/problems/closest-binary-search-tree-value-ii
    PriorityQueue<Integer> smaller = new PriorityQueue<>((x,y)->y-x);
    PriorityQueue<Integer> larger = new PriorityQueue<>();
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        
        dfs(root, target);

        double d = 0.0;
        
        while(k--!=0){
            if(smaller.size()==0){
                d = larger.remove();
                res.add((int)d);
            } else if(larger.size()==0){
                d = smaller.remove();
                res.add((int)d);
            }
            else if(Math.abs(smaller.peek() - target)<Math.abs(larger.peek()-target)){
                d = smaller.remove();
                res.add((int)d);
            }
            else {
                d = larger.remove();
                res.add((int)d);
            }
        }
        return res;
    }
    
    void dfs(TreeNode root, double target){
        if(root == null) return;
        if(root.val<target) smaller.add(root.val);
        else larger.add(root.val);
        
        dfs(root.left, target); dfs(root.right, target);
    }


    // https://leetcode.com/problems/rank-teams-by-votes/
    public String rankTeams(String[] votes) {
        int m = 26, n = votes[0].length();
        int[][] arr = new int[m][n];
        HashSet<Character> set = new HashSet<>();
        
        for(String str : votes){
            for(int i = 0; i<str.length(); i++){
                arr[str.charAt(i)-'A'][i]++;
            }
        }
        for(char c : votes[0].toCharArray()) set.add(c);

        class Team{
            char c; List<Integer> points;
            Team(char ch, List<Integer> p){
                this.c = ch;
                this.points = p;
            }
        }
        
        PriorityQueue<Team> pq = new PriorityQueue<>((x, y)->{
            int index = 0;
            if(x.points.get(index) == y.points.get(index)) {
                while(index<n){
                    if(x.points.get(index) == y.points.get(index)) index++;
                    else return y.points.get(index) - x.points.get(index); 
                }
                return x.c-y.c;
            }
            return y.points.get(index) - x.points.get(index);
        });
        
        String res = "";
        for(int i =0; i<m; i++){
            List<Integer> list = new ArrayList<>();
            char ch = (char) (i+'A');
            if(!set.contains(ch)) continue;
            for(int j = 0; j<n; j++){
                list.add(arr[i][j]);
            }
            
            pq.add(new Team(ch, list));
        }
        
        while(pq.size()!=0){
            // System.out.println(pq.peek().c+", "+pq.peek().points);
            res+=""+pq.remove().c;
        }
        
        return res.substring(0, n);
    }




    // Clearwater
    // min distinct els after removing m els
    // https://www.geeksforgeeks.org/minimum-number-of-distinct-elements-after-removing-m-items/
    class Key{
        int id; int freq;
        Key(int i, int f){
            this.id  = i;
            this.freq = f;
        }
    }
    int distinctIds(int arr[], int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : arr){
            map.put(i, map.getOrDefault(i, 0)+1);
        }

        PriorityQueue<Key> pq= new PriorityQueue<>((x,y)->x.freq -y.freq);
        for(HashMap.Entry<Integer, Integer> entry : map.entrySet()){
            pq.add(new Key(entry.getKey(), entry.getValue()));
        }

        // if m == 0 we have removed reqd elements
        // else we have removed some extra elements, which belong to the same id, hence size+1
        // including current id
        while(pq.size()!=0) {
            Node curr = pq.remove();
            m-=curr.freq;
            if(m<0) return pq.size()+1;
            else if(m == 0) return pq.size();
        }
        return pq.size();
    }

    /*
     * * provides O(log(n)) time for the enqueuing and dequeuing methods ({@code
     * offer}, {@code poll}, {@code remove()} and {@code add}); linear time for the
     * {@code remove(Object)} and {@code contains(Object)} methods; and constant
     * time for the retrieval methods ({@code peek} ->first el, {@code element}, and
     * {@code size}).
     * 
     * removeEq, removeAt and contains capacity grow automatically listiterator,
     * hasNext size()
     */
    // k-sorted array
    /**
     * 1 add comparator
     * 
     */

    // SKYLINE
    // https://www.techiedelight.com/convert-min-heap-to-max-heap-linear-time/

    /** one optimization can be to keep heap size limited to k */
    // https://leetcode.com/problems/k-closest-points-to-origin/
    class Holder{
        int index; int dist;
        Holder(int i, int d){
            this.index = i;
            this.dist =d;
        }
    }
    
    public int[][] kClosest(int[][] points, int K) {
        int n = points.length;
        
        PriorityQueue<Holder> heap = 
            new PriorityQueue<Holder>((x, y)-> x.dist - y.dist);
        
        for(int i=0; i<n; i++){
            int a = points[i][0]; int b = points[i][1];
            int c = a*a + b*b;
            heap.add(new Holder(i, c));
        }
        
        int[][] res = new int[K][2];
        
        for(int i =0; i<K; i++){
            Holder curr = heap.remove();
            res[i][0] = points[curr.index][0];
            res[i][1] = points[curr.index][1];
        }
        
        return res;
    }

    /**
     * one optimization can be to keep heap size limited to k
     * 
     * USE A CUSTOM CLASS MOSTLY IS REQUIRED
     * 
     * one idea was to map freq with an arrayList of strings but how to fetch
     * previous count? will have to traverse over all freqs to find where string is
     * residing, remove it and then add to new freq(old freq+1)
     * 
     * BEST TO GO FOR SIMPLE HASHMAP AND pQueue
     * 
     * wny go for pQueue? hashmap stores in a sequential order so for fetching all k
     * we have to traverse the whole heap So pQueue.
     * 
     * for fetching all the els with freq say 4 we have to remove as many times,
     * keep a track of last freq and update counter when freq value changes
     * 
     * IMP : 
     * 1 STR1.COMPARETO(STR2)
     * 2 how to implement a pQueue with 2 comparators 
     * 3 heap.size() check before removing
     * 
     */
    // https://leetcode.com/problems/top-k-frequent-words/
    class Node{
        String word; int freq;
        Node(String s, int f){
            this.word =s; this.freq = f;
        }
    }
    
    public List<String> topKFrequent(String[] words, int k) {
        
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y)->{
            if(x.freq == y.freq) return (x.word).compareTo(y.word);
            return y.freq - x.freq;
        });
        
        HashMap<String, Integer> map = new HashMap<>();
        
        for(String str : words){
            map.put(str, map.getOrDefault(str, 0)+1);
        }
        
        for(HashMap.Entry<String, Integer> e : map.entrySet()){
            pq.add(new Node(e.getKey(), e.getValue()));
        }
        
        List<String> res = new ArrayList<>();
        for(int i =0; i<k; i++){
            res.add(pq.remove().word);
        }
        return res;
    }

    // IF ALL K DIFF FREQ WORDS HAD TO BE PRINTED

    /**
     * ["the", "day", "is", "bun" , bun ", ""sun", "the", "the", "the", "sun",
     * "is", "is"], k = 3 (TOP 3 FREQUENT) 
     * the - 4, day - 1, is - 3, bun - 2, sun -2 WE HAVE TO
     * PRINT the-is-bun-sun
     * bun and sun have same freq so both need to be printed
     * 
     * MAINTAIN A COUNTER AND PREVFREQ != CURRFREQ, INCREMENT COUNTER     * 
     * INITIALIZE PREVFREQ BY POPPING ONCE KFREQ CURR
     * THEN RUN WHILE LOOP
     */
    class KFreq {
        int freq;
        String str;

        KFreq(int f, String s) {
            this.freq = f;
            this.str = s;
        }
    }
    
    public ArrayList<String> topKFrequentDiff(String[] words, int k) {
        int n = words.length;
        ArrayList<String> res = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();

        // System.out.println('a' - 'b'); //prints-1
        for (int i = 0; i < n; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }

        PriorityQueue<KFreq> heap = new PriorityQueue<KFreq>((x, y) -> {
            if (x.freq == y.freq) return x.str.compareTo(y.str);
            return y.freq - x.freq;
        });

        for (Map.Entry<String, Integer> e : map.entrySet()) {
            String s = e.getKey();
            int freq = e.getValue();
            heap.add(new KFreq(freq, s));
        }

        int counter = 0; int prevFreq = 0;
        KFreq curr = null;
        if(heap.size()!=0){
            curr = heap.remove();
            prevFreq = curr.freq;
            res.add(curr.str);
        }

        while (heap.size() != 0 && counter<k) {
            curr = heap.remove();
            res.add(curr.str);
            if(curr.freq != prevFreq) counter++;
        }

        System.out.println(res);
        return res;
    }


    /** 
     * idea is simple, add els of first row
     * remove and add next el of curr col;
     * if x == m-1 continue;
     * run k-1 times
    */
    // https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix
    class Node1{
        int x, y, val;
        Node1(int x, int y, int v){
            this.x = x; this.y =y; this.val = v;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length; int n = matrix[0].length;
        
        PriorityQueue<Node1> pq = new PriorityQueue<>((x,y)->x.val - y.val);
        
        // add first row
        for(int i =0; i<m; i++){
            pq.add(new Node1(0 , i, matrix[0][i]));
        }
        
        for(int i =0; i<k-1; i++){
            Node1 curr = pq.remove();
            // System.out.println(curr.val);
            if(curr.x == m-1) continue;
            // next el in same col
            pq.add(new Node1(curr.x+1, curr.y, matrix[curr.x+1][curr.y] ));
        }
        return pq.remove().val;
    }

    /** 
     * 1 store index and sum; pass args separately
     * 2 store first row sum and then for each pop add next el of 2nd array
     * 3 check pq size
    */
    // https://leetcode.com/problems/find-k-pairs-with-smallest-sums
    class Node{
        int x, y , sum;
        Node(int x, int y, int s){
            this.x = x; this.y = y;
            this.sum = s;
        }
    }
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y)->x.sum - y.sum);
        
        List<List<Integer>> res = new ArrayList<>();
        if(m==00 || n==0) return res;
        for(int i =0; i<m; i++) pq.add(new Node(i, 0, nums1[i]+ nums2[0]));
        
        while(k-->0 && pq.size()!=0){
            Node curr = pq.remove();
            res.add(Arrays.asList(nums1[curr.x], nums2[curr.y]));
            // System.out.println(res);
            if(curr.y == n-1) continue;
            pq.add(new Node(curr.x, curr.y+1, nums1[curr.x] + nums2[curr.y+1]));
        }
        
        return res;
    }

    /** 
     * 1 create a custom class to hold index, row and val
     * 2 row and index are needed so that when we pop an element,
     *  we can find the next el in the corresponding row easily
     *  new KListNode(curr.index+1, nums.get(curr.row).get(curr.index+1), curr.row);
     * 
     *  NEXT EL'S VAL OF CURR ROW -> nums.get(curr.row).get(curr.index+1)
     * 
     * 3 use a minheap, with comparator
     * 4 add first els of each row
     * 5 use 4 vars max, start, end, range
     * 
     * 6 while q == nums.size makes the loop run till we have atleast 1 el
     * from each row
     * 7 flow -> pop an el, compare with max to get range,
     *  add another from same row
     * 8 if we already have lesser diff, move on
     *  else udpate start, end and range
     * 
     * 9 if current el is not the last in its row
     *  curr.index+1<nums.get(curr.row).size()
     *  add one, else continue
     *   
     * {{4,10,15,24,26}, {0,9,12,20}, {5,18,22,30}};
     * suppose we have 20, 22 and 24 in heap
     * once 20 is popped we update range, start, end
     * but its the last and IF WE DON'T HAVE ELS OF ALL K ROWS
     * we can't compare, while loop breaks and we return;
     * 
     */
    // https://www.geeksforgeeks.org/find-smallest-range-containing-elements-from-k-lists/
    // https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
    class KListNode{
        int index, val, row;
        KListNode(int i, int v, int r){
            this.index = i;
            this.val = v;
            this.row = r;
        }
    }

    public int[] smallestRange(ArrayList<ArrayList<Integer>> nums) {
        PriorityQueue<KListNode> q = new PriorityQueue<>((x,y)->x.val- y.val);
        int max = Integer.MIN_VALUE;

        for(int i =0; i<nums.size(); i++){
            q.add(new KListNode(0, nums.get(i).get(0) ,i));
            max = Math.max(max, nums.get(i).get(0)); // 1
        }
        int start = -1;
        int end = -1;
        int range = Integer.MAX_VALUE;

        while(q.size() == nums.size()){  // 2
            KListNode curr = q.remove();
            System.out.println(curr.val);
            if(max-curr.val < range){
                start = curr.val;
                end = max;
                range = max - curr.val;
            }
            
            // el removed so add el of the same row
            if(curr.index+1<nums.get(curr.row).size()){
                KListNode currNew = new KListNode(curr.index+1, 
                            nums.get(curr.row).get(curr.index+1), curr.row);
                q.add(currNew);
                max= Math.max(max, currNew.val);
            }
        }

        // int[] x = {start,end};
        // print1DMatrix(x);
        return new int[]{start, end};
    }

    // https://leetcode.com/problems/find-smallest-common-element-in-all-rows/
    public int smallestCommonElement(int[][] mat) {
        class Node{
            int[] arr;
            int val;
            int index;
            Node(int[] arr, int val, int index){
                this.arr = arr;
                this.val = val;
                this.index = index;
            }
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>((x, y)-> x.val - y.val);
        
        int n = mat.length, limit = mat[0].length;
        HashMap<Integer, Integer> map = new HashMap<>(); 
        
        // if count reaches n return val
        
        for(int[] i : mat){
            pq.add(new Node(i, i[0], 0));
        }
        
        while(pq.size()!=0){
            Node curr = pq.remove();
            map.put(curr.val, map.getOrDefault(curr.val, 0)+1);
            if(map.get(curr.val) == n) return curr.val;
            if(curr.index+1 < limit) 
                pq.add(new Node(curr.arr, curr.arr[curr.index+1], curr.index+1));
        }
        return -1;
    }

    /** 
     * MAX HEAP OF SMALLER ELs HOLDS THE MEDIAN IF ODD NO OF ELs
     * POINTS :
     * 1 MAX HEAP SMALLER ELES, MIN HEAP OF LARGER ELS,
     * SO THE CLOSEST ELS ARE AT THE TOP
     * 2 ADD TO MAX, ADD TO MIN, IS max.size()< LESS, ADD BACK
     * 3 IF SIZES ARE SAME, EVEN SO AVERAGE OF TOP ELS
     * ELSE MAX.PEEK()
     * 
     * aisa
     * max->min->size->max
    */
    class MedianFinder {
        // https://leetcode.com/problems/find-median-from-data-stream
        // define max heap explicitly
        PriorityQueue<Integer> max = new PriorityQueue<>((x,y)->y-x);
        PriorityQueue<Integer> min = new PriorityQueue<>();
        
        public MedianFinder() {}
        
        public void addNum(int num) {
            max.add(num);
            min.add(max.remove());
            if(max.size()<min.size()) max.add(min.remove());
        }
        
        public double findMedian() {
            if(max.size() == min.size()) return ((min.peek()+max.peek())*0.5);
            return max.peek();
        }
    }


    // https://leetcode.com/problems/minimum-cost-to-connect-sticks/
    

    // https://leetcode.com/problems/median-of-two-sorted-arrays/
    // discuss/268906/Java-Solution%3A(2ms)faster-than-100.00-(48.3)less-than-86.61.
    
    // https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/
    // https://www.geeksforgeeks.org/nearly-sorted-algorithm/
    // https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
    // https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss
    // /301357/Java-0ms-(added-Python-and-C%2B%2B)%3A-Easy-to-understand-solutions-using-Heap-and-Binary-Search

    public static void main(String[] args) {
        Heap newHeap = new Heap(17);

        // newHeap.addToheap(3);
        // newHeap.addToheap(5);
        // newHeap.addToheap(7);
        // newHeap.addToheap(9);
        newHeap.heapArray[0] = 2;
        newHeap.heapArray[1] = 4;
        newHeap.heapArray[2] = 3;
        newHeap.heapArray[3] = 6;
        newHeap.heapArray[4] = 79;
        newHeap.heapArray[5] = 7;
        newHeap.heapArray[6] = 17;
        newHeap.heapArray[7] = 57;
        newHeap.heapArray[8] = 73;
        newHeap.heapArray[9] = 97;
        newHeap.heapArray[10] = 47;
        newHeap.heapArray[11] = 27;
        newHeap.heapArray[12] = 75;
        newHeap.heapArray[13] = 72;
        newHeap.heapArray[14] = 74;
        newHeap.heapArray[15] = 77;
        newHeap.heapArray[16] = 53;

        // newHeap.printHeap();

        for (int i = (newHeap.heapArray.length) / 2 - 1; i >= 0; i--) {
            // newHeap.maxHeapify(i);
        }
        // newHeap.maxHeapify(7);

        // newHeap.printHeap();

        // newHeap.deleteFromHeap();

        // newHeap.printHeap();
        String[] topk = { "a", "ba", "aaa" };
        int k = 1;
        // newHeap.topKFrequent(topk, k);

        // newHeap.topKFrequentDiff(topk, k);
        int[][] nums = {{4,10,15,24,26}, {0,9,12,20}, {5,18,22,30}};
        ArrayList<ArrayList<Integer>> numsKList = new ArrayList<ArrayList<Integer>>();
        for(int i =0; i<nums.length; i++){
            ArrayList<Integer> newRow = new ArrayList<>();
            for(int j : nums[i]){
                newRow.add(j);
            }
            numsKList.add(newRow);
        }
        // System.out.println(numsKList);
        newHeap.smallestRange(numsKList);

        
    }
}