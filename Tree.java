import java.util.*;

class TreeNode {
    int key; int val;
    TreeNode left;
    TreeNode right;
    TreeNode rightpointer;

    TreeNode(int key) {
        this.key = key;
        this.val = key;
        this.left = null;
        this.right = null;
    }
}

class LinkedListNode {
    int data;
    LinkedListNode next;

    LinkedListNode(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Tree {

    TreeNode root;

    Tree() {
        this.root = null;
    }
    
    /** POINTS : 
     * 1 USE ONE OF TWO DISTINCT FUNC DEFINITIONS
     * PREORDER WITH TAIL RECURSION 
     * IS-SAME-TREE
     * VALID-BST
     * 
     * public boolean isValidBST(TreeNode root) {
        return bstHelper(root, Long.MAX_VALUE, Long.MIN_VALUE);        
    }
    
     * boolean bstHelper(TreeNode root, long max, long min){
        if(root == null) return true;
        if(!(max > root.val && root.val > min)) return false;
        return bstHelper(root.left, root.val, min) && bstHelper(root.right, max, root.val);
    }

     * return a helper func
     * in the helper func add a check for root == null
     * and not for root.left or root.right individually
     * return left and right
     * 
     *
     * 
     * --------------------------------------------------------------------
     * 2 OR USE POSTORDER AS IN MAX SIZE BST
     *  
     * SIMILAR TO HEIGHT OF TREE
     * 
     * function h(root){
     *      if(root == null) return 0;
     *      int left = h(left);
     *      int right = h(right);
     *      return max(left, right)+1;
     * }
     * 
     * --------------------------------------------------------------------
     * 3 USE ROOT.LEFT = F() AND ROOT.RIGHT = F()
     * THIS CAN BE USED WHEN BUILDING A TREE OR DELETING SOME NODES
     * BINARY TREE PRUNING
     * 
     * https://leetcode.com/problems/binary-tree-pruning/
        * public TreeNode pruneTree(TreeNode root) {
            if(root == null) return null;
            root.left = pruneTree(root.left);
            root.right = pruneTree(root.right);
            if(root.left== null && root.right ==null && root.val ==0) return null;
            return root;
         }
    ---------------------------------------------------------------------------
     * DFS IN A TREE USING LIST
     * 
     * 1 List<List<Integer>> doesn't need a multi ArrayList
     *   only use new ArrayList when adding to final list
     * 
     * 2 REMOVING FROM LIST TWICE, ONCE FOR EACH NODE
     *    
     * void dfs(TreeNode node, List<List<Integer>> parentList, List<Integer> currentList) {
        // if(node!=null){
        if(node == null) return;
        if (node.left == null && node.right == null) {
            currentList.add(node.key);
            parentList.add(new ArrayList<>(currentList));
            return;
        }
        currentList.add(node.key);
        dfs(node.left, parentList, currentList);
        currentList.remove(currentList.size() - 1);
        dfs(node.right, parentList, currentList);
        currentList.remove(currentList.size() - 1);
     }

     */ 
    

     /**  
      * QUESTIONS
      1 BST (3 POINTS : 
      1 LEFT.MAX<ROOT<RIGHT.MIN 
      2 INORDER IS SORTED ARRAY)
      3 POSTORDER TAKES O(N) AND PREORDER 0(N^2))
      
      1.1 VALIDATE BST
      1.2 MAX SIZE BST
      1.3 MAX SUM BST
      1.4 DELETE IN BST
      1.5 BALANCE A BST (SORTED ARRAY INRODER)
      1.6 FIX A BST
      1.7 BST TO CDLL
      1.8 SORTED DLL TO BALANCED BST
      1.9 FLOOR AND CEILING
      1.10 ALL IN RANGE

      2 BINARY TREES
      
      2.1 TRAVERSAL
      2.1.1 INORDER, PREORDER, POSTORDER
      2.1.2 ITERATIVE TRAVERSAL
      
      2.1.3 LEVEL ORDER
      2.1.3.1 QUEUE
      2.1.3.2 USING RECURSION
      2.1.3.3 FINDING ANAGRAMS
      2.1.3.4 NEXT POINTER (INSERTING NULL TO DEMARCATE LEVELS)
      2.1.3.5 MAX LEVEL SUM
      
      2.1.4 SPIRAL TRAVERSAL
      2.1.5 BOUNDARY TRAVERSAL
      2.1.6 CREATE TREE FROM TRAVERSAL  


      2.2 VIEWS
      2.2.1 TOP, BOTTOM, LEFT, RIGHT

      2.3 HASHMAP
      2.3.1 NODES AT DIST K

      2.4

      3 HEIGHT
      3.1 HEIGHT
      3.2 DIA
      3.3 NODES AT DIA END
      3.4 LCA (SIMILAR CONCEPT OF LEFT AND RIGHT SUBTREES)
      3.5 IS SUBTREE
      3.6 IS SAME TREE
      3.7 MIN DEPTH

      4 SUBTREES
      4.1 IS SUBTREE 
      4.2 IS SAME TREE (PREORDER WITH TAIL REC)

      5 LEAF NODES
      5.1 CHECK LEAF NODES
      5.2 REMOVE LEAF NODES
      5.3 CHECK IF COMPLETE BINARY TREE
      5.4 PRUNING
      5.5 HALF NDOES


      6 DP QUES
      https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/


      7 MISC
      FOREST BURNING, MIN TIME TO INFORM EMP
      COUSINS IN BINARY TREE, FLATTEN TO LINKED LIST
      ROOT TO LEAF PATHS, NEXT SIBLING,
      ISOMORPHISM, MIRROR, MAX SUM PATH BETWEEN ANY TWO NODES
      SERIALIZE DESERIALIZE

      8 PREORDER WITH TAIL RECURSION
      IS-SAME, IS-VALID-BST
        
      
      */
    
    void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.key+", ");
        inOrder(root.right);
    }

    // https://leetcode.com/problems/convert-bst-to-greater-tree
    int sum =0;
    public TreeNode convertBST(TreeNode root) {
        if(root == null) return root;
        reverseInorder(root);
        return root;
    }
    
    void reverseInorder(TreeNode root){
        if(root == null) return;
        reverseInorder(root.right);
        root.val += sum;
        sum = root.val;
        reverseInorder(root.left);
    }


    void preOrder(TreeNode x) {
        if (root == null) return;
        System.out.print(x.key+", ");
        preOrder(x.left);
        preOrder(x.right);
    }

    // ITERATIVE
    // ADD THE RIGHT AND THEN THE LEFT TO STACK 
    // https://leetcode.com/problems/binary-tree-preorder-traversal
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;  
        
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        
        while(q.size()!=0){
            TreeNode curr = q.removeLast();
            res.add(curr.val);
            if(curr.right!=null) q.addLast(curr.right);
            if(curr.left!=null) q.addLast(curr.left);
        }
        return res;
    }

    /** 
     * MORRIS INORDER TRAVERSAL
     * SET CURRENT'S PREDECESSOR'S RIGHT TO CURRENT
     * IF THE PRED'S RIGHT IS NOT NULL, SET IT NULL. (NOT NULL SIGNIFIES THAT IT HAS BEEN VISITED)
     * 
     * IF LEFT IS NULL ADD AND MOEV TO RIGHT,
     * ELSE FIND PRED, SET RIGHT
     * IF PRED RIGHT NOT NULL, SET NULL, ADD. 
    */
    // https://leetcode.com/problems/binary-tree-inorder-traversal
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        while(curr!=null){
            if(curr.left == null){
                res.add(curr.val);
                curr = curr.right;
            }
            else{
                TreeNode pred = findPred(curr);
                //link back if right null
                if(pred.right == null){
                    pred.right = curr;
                    curr = curr.left;
                }
                // if right  not null, we have visited, unlink
                // we are always setting the pred's right to null, so 
                // if it's not null then we have already visited it.
                else{
                    pred.right = null;
                    res.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return res;
    }
    
    TreeNode findPred(TreeNode node){
        TreeNode current = node;
        node = node.left;
        while(node.right != null && node.right != current) node = node.right;
        return node;
    }

    /** 
     * POINTS :
     * 1 ADD TO STACK, REMOVE CURR, ADD LEFT AND RIGHT
     * 2   4,2,6-------STACK------RES 
     *       4----------4----------
     *      2,6--------2,6----------4
     *        --------------------4,6,2
     * LEFT IS ADDED AND THEN WHILE ADDING TO RES, FIRST RIGHT IS ADDED.
     * SO FINAL ANS IS REVERSED
     * 
     *
    */
    // PREORDER -> RIGHT, LEFT; POST -> LEFT, RIGHT
    // https://leetcode.com/problems/binary-tree-postorder-traversal
    public List<Integer> postorderTraversal(TreeNode root) {
        Deque<TreeNode> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        
        q.addLast(root);
        while(!q.isEmpty()){
            TreeNode curr = q.removeLast();
            res.add(curr.val);
            if(curr.left!=null) q.addLast(curr.left);
            if(curr.right!=null) q.addLast(curr.right);
        }
        Collections.reverse(res);
        return res;
    }

    void postOrder(TreeNode root) {
        postOrder(root.left);
        postOrder(root.right);
        System.out.println("node key " + root.key);
    }


    // https://leetcode.com/problems/second-minimum-node-in-a-binary-tree
    public int findSecondMinimumValue(TreeNode root) {
        HashSet<Integer> set = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        preOrder(root, set, pq);
        pq.remove();
        if(pq.size()==0) return -1;
        return pq.remove();
    }
    
    void preOrder(TreeNode root, HashSet<Integer> set, PriorityQueue<Integer> pq){
        if(root == null) return;
        if(!set.contains(root.val)) {
            set.add(root.val); 
            pq.add(root.val);
        }
            
        preOrder(root.left, set, pq);
        preOrder(root.right, set, pq);
    }

    /** 
     * MOST IMP : KEEP TRACK OF SIZE OF QUEUE ALWAYS, THIS HELPS KEEP 
     * TRACK OF ALL ELS IN THIS LEVEL
     * 
     * LEVEL ORDER 2 WAYS : USE QUEUE OR USE LEVEL
     * USING RECURSION
     * 1 MAINTAIN A HASHMAP
     * 2 SEARCH FOR LEVEL IN MAP AND ADD TO IT
     * 3 TRICKY PART IS TO ADD FROM HASHMAP TO RES AS MAP ISN'T 
     * NECESSARILY SORTED
     * 4 START FROM 0, FETCH i AND ADD
     * for(int i =0; i<map.size(); i++) res.add(i, map.get(i));
     * 
     * 
     * QUEUE:
     * 1 ADD TO Q, 
     * 2 KEEP TRACK OF SIZE, 
     * 3 CREATE A NEW LIST
     * 4 WHILE REMOVING THE CURR EL, KEEP ADDING TO LIST
     * 5 ADD LIST TO RES
     *
     * */
    // https://leetcode.com/problems/binary-tree-level-order-traversal
    // RECURSIVE
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        preOrder(map, root, 0);
        
        for(int i =0; i<map.size(); i++) res.add(i, map.get(i));

        return res;
    }
    //https://www.geeksforgeeks.org/check-if-all-levels-of-two-trees-are-anagrams-or-not
    
    void preOrder(HashMap<Integer, List<Integer>> map, TreeNode root, int level){
        if(root == null) return;
        List<Integer> curr = new ArrayList<>();
        if(map.containsKey(level)){
            curr = map.get(level);
        } 
        curr.add(root.val);
        map.put(level, curr);
        
        preOrder(map, root.left, level+1);
        preOrder(map, root.right, level+1);
    }

    // ITERATIVE
    public List<List<Integer>> levelOrderIterative(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        
        while(q.size()!=0){
            int size = q.size();
            List<Integer> curList = new ArrayList<>();

            for(int i =0; i<size; i++){
                TreeNode curr = q.removeFirst();
                curList.add(curr.val);
                if(curr.left!=null) q.addLast(curr.left);
                if(curr.right!=null) q.addLast(curr.right);
            }

            res.add(curList);
        }
        return res;
    }

    // TRY REVERSE LEVEL ORDER, LEVEL ORDER WITHOUT QUEUE AND HASHMAP

    /**  
     * POINTS :
     * 1 KEEP TRACK OF DIRECTIO USING A BOOLEAN FLAG
     * 2 SIMILAR TO LEVEL ORDER
     * 3 WHEN DIR IS FALSE, REVERSE CURR LIST AND THEN ADD TO RES
    */
    // https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res= new ArrayList<>();
        
        if(root == null) return res;
        boolean ltor = true;
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        
        while(q.size()!=0){
            int size = q.size();
            List<Integer> currList = new ArrayList<>();
            
            for(int i =0; i<size; i++){
                TreeNode curr = q.removeFirst();
                currList.add(curr.val);
                
                if(curr.left!=null) q.addLast(curr.left);
                if(curr.right!=null) q.addLast(curr.right);
            }
            if(!ltor) Collections.reverse(currList);
                
            res.add(currList);
            
            ltor=!ltor;
            
        }
        return res;
    }

    // https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
    /** 
     * 1 KEEP TRACK OF SIZE AS IN LEVEL ORDER
     * 2 FOR EACH CURR EL, ADD NEXT AS THE NEXT EL IN Q (q.getFirst())
     * 3 IF i == size-1, ASSIGN NULL TO NEXT, AS THE NEXT EL IN Q WILL
     * BE THE CHILD OF SOME NODE AND OF DIFF LEVEL 
     */
    class RightNode {
        public int val;
        public RightNode left;
        public RightNode right;
        public RightNode next;
    
        public RightNode() {}
        
        public RightNode(int _val) {
            val = _val;
        }
    
        public RightNode(int _val, RightNode _left, RightNode _right, RightNode _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
    
    public RightNode connect(RightNode root) {
        if(root == null) return root;
        
        Deque<RightNode> q = new LinkedList<>();
        q.addLast(root);
        
        while(q.size()!=0){
            int size = q.size();
            
            for(int i = 0; i<size; i++){
                RightNode curr = q.removeFirst();
                if(i==size-1) curr.next = null;
                else curr.next = q.getFirst();
                if(curr.left!=null) q.addLast(curr.left);
                if(curr.right!=null) q.addLast(curr.right);
            }
        }
        return root;
    }

    
    // https://leetcode.com/discuss/interview-question/275467/
    // uber-phone-screen-boundary-of-the-perfect-binary-tree
    // https://stackoverflow.com/questions/30275735/to-print-the-boundary-of-binary-tree
    List<Integer> boundaryOfTree(TreeNode root){
        List<Integer> res= new ArrayList<>();

        if(root == null) return res;
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(root);

        while(q.size()!=0){
            int size = q.size();

            for(int i=0; i<size; i++){
                TreeNode curr = q.removeFirst();
                if(i==0 || i==size-1) res.add(curr.val);
                // System.out.println(curr.val);
                if(curr.left!=null) q.addLast(curr.left);
                if(curr.right!=null) q.addLast(curr.right);                
            }
        }
        System.out.println(res);
        return res;
    }

    HashMap<Integer, List<Integer>> diaMap = new HashMap<>();
    void diagonalTraversal(TreeNode root){
        diaHelper(root, 0);
        for(Map.Entry<Integer, List<Integer>> entry : diaMap.entrySet()){
            List<Integer> curr = entry.getValue();
            System.out.println(curr);
        }
    }

    void diaHelper(TreeNode root, int dia){
        if(root == null) return;
        List<Integer> curr = diaMap.getOrDefault(dia, new ArrayList<>());
        curr.add(root.val);
        diaMap.put(dia, curr);
        if(root.right!=null) diaHelper(root.right, dia);
        if(root.left!=null) diaHelper(root.left, dia+1);
    }

    // https://leetcode.com/problems/same-tree/
    // SIMPLE PREORDER FOR BOTH SIMULTANEOUSLY
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val == q.val) return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        return false;
    }

    // https://leetcode.com/problems/subtree-of-another-tree
    /** if the root matches, then recur for that using isSame
     * else recur for left and right nodes.
     * 
     * 1 isSubtree -> isSame
     * 2 else -> isSubtree(left) || isSubtree(right)
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s==null ) return false;
        else if(isSame(s, t)) return true;
        else return isSubtree(s.left, t)|| isSubtree(s.right, t);
    }
    
    boolean isSame(TreeNode a, TreeNode b){
        if(a == null || b==null) return a==null&&b== null;
        if(a.val == b.val) return isSame(a.left, b.left) && isSame(a.right, b.right);
        else return false;
    }

    // https://www.geeksforgeeks.org/check-if-two-trees-are-mirror/
    void mirrorTreeCheck(TreeNode node1, TreeNode node2) {

    }

    /** 
     * SIMILAR TO LEVEL ORDER
     * KEEP TRACK OF MAX OF ALL ELS IN AN ITERATION
     * ADD TO RES AT THE END
     */
    // https://leetcode.com/problems/find-largest-value-in-each-tree-row/
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(root);
            
        while(q.size()!=0){
            int max = Integer.MIN_VALUE;
            
            int size = q.size();
            for(int i =0; i<size; i++){
                TreeNode curr = q.removeFirst();
                max = Math.max(max, curr.val);
                if(curr.left!=null) q.addLast(curr.left);
                if(curr.right!=null) q.addLast(curr.right);                
            }
            // System.out.println(max);
            res.add(max);
        }
        return res;
    }

    /** POINTS : 
     * SIMILAR TO LEVEL ORDER, JUST KEEP TRACK OF THE MAX LEVEL INSTEAD OF MAX SUM
     * THE WHOLE FOR LOOP CORRSPONDS TO ONE LEVEL, 
     * SO INCREMENT AFTRE IT, OT INSIDE IT
    */
    // https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
    public int maxLevelSum(TreeNode root) {
        if(root == null) return 0;
        int max = Integer.MIN_VALUE; int res = 0;
        
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        int level = 0;
        
        while(q.size()!=0){
            int size = q.size();
            int sum =0;
            
            for(int i =0; i<size; i++){
                TreeNode curr = q.removeFirst();
                sum+=curr.val;
                if(curr.left != null) q.addLast(curr.left);
                if(curr.right != null) q.addLast(curr.right);
            }
            
            level++;
            if(max<sum) {
                max = sum; res = level;
            }
        }
        return res;
    }

    /** 
     * VIEWS
     * FOR RIGHT VIEW DO REVERSE PREORDER TRAVERSAL
     * FOR LEFT, DO NORMAL PREORDER
    */
    // https://leetcode.com/problems/binary-tree-right-side-view/
    /** 
     * DO A REVERSE PREORDER TRAVERSAL, ADD RIGHT FIRST AND THEN LEFT
     * IF DIST DOESN'T EXIST IN MAP ADD
     * TO CONVERT TO ARRAYLIST RETURN NEW ARRAYLIST(MAP.VALUES())
     */
    public List<Integer> rightSideView(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<>();
        dfs(root, map, 0);    
        return new ArrayList<>(map.values()); 
    }
    
    void dfs(TreeNode root, HashMap<Integer, Integer> map, int dist){
        if(root == null) return;
        if(!map.containsKey(dist)) map.put(dist, root.val);
        dfs(root.right, map, dist+1);
        dfs(root.left, map, dist+1);
    }

    /** 
     * X DIST IS USED TO KEEP TRACK
     * POINTS : 
     * 1 KEEP TRACK OF X DIST AS WELL AS Y DIST
     * 2 INSERT INTO MAP ON THE BASIS OF X DIST
     * 
     * 3 SORT ON THE BASIS OF Y DIST, AS X DIST IS SAME FOR AN ENTRY
     * IF Y DIST IS SAME, SORT BY VAL
     *  Collections.sort(curr,(a, b)->{
            if(a.y != b.y) return b.y - a.y;
            else return a.val - b.val;
        });
     * 
     * 4    
     */
    // https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
    class Order{
        int x, y, val;
        Order(int x, int y, int v){
            this.x = x; this.y = y; this.val = v;
        }
    }
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        HashMap<Integer, List<Order>> map = new HashMap<>();
        PriorityQueue<Integer> q = new PriorityQueue<>();
        List<List<Integer>> res = new ArrayList<>();
        
        dfs(map, q, root, 0, 0);
        while(q.size()!=0){
            List<Order> curr = map.get(q.remove());
            Collections.sort(curr,(a, b)->{
                if(a.y != b.y) return b.y - a.y;
                else return a.val - b.val;
            });
            List<Integer> list = new ArrayList<>();
            
            for(int i =0; i<curr.size(); i++) list.add(curr.get(i).val);
            res.add(list);
        }
        return res;
    }
    
    void dfs(HashMap<Integer, List<Order>> map, PriorityQueue<Integer> q, 
    TreeNode root, int x, int y){
        if(root == null) return;
        List<Order> curr = new ArrayList<>();
        
        if(map.containsKey(x)){
            curr = map.get(x);
            curr.add(new Order(x, y, root.val));
        }else{
            q.add(x);
            curr.add(new Order(x,y,root.val));
        }
        map.put(x, curr);
        
        dfs(map, q, root.left, x-1, y-1);
        dfs(map, q, root.right, x+1, y-1);
    }

    // https://leetcode.com/problems/construct-string-from-binary-tree/
    // discuss/103992/Java-Solution-Tree-Traversal
    // https://leetcode.com/problems/construct-string-from-binary-tree
    String res = "";
    public String tree2str(TreeNode t) {
        if(t == null) return res;
        helper(t);
        return res.substring(1, res.length()-1);
    }
    
    void helper(TreeNode root){
        if(root == null) return;
        res = res+'('+root.val;
        if(root.left == null && root.right == null) {res+=')';return;}
        if(root.left == null ) res +="()";
        if(root.left!=null)helper(root.left);
        helper(root.right);
        res+=')';
    }

    // https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length-1);
    }
    
    TreeNode helper(int[] arr,int low, int high){
        if(low>high) return null;
        int mid = low + (high-low)/2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = helper(arr, low, mid-1);
        root.right = helper(arr, mid+1, high);
        return root;
    }


    /** 
     * POINTS :
     * 1 THE GLOBAL VAR PRESTART IS USED TO CREATE NEW NODES
     * 2 STEPS : create node, find index, preStart++, set children
     * 3 AND THE BOUNDARY FOR LEFT = (start, index-1)
     * AND RIGHT = (index+1, end)
    */
    // https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
    int preStart = 0; // 1
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        if(n==0) return null;
        return helper(preorder, inorder, 0, n-1);
    }
    
    TreeNode helper(int[] preorder, int[] inorder, int start, int end){
        if(start>end) return null;
        if(preStart >= preorder.length) return null;

        TreeNode root = new TreeNode(preorder[preStart]);//imp
        
        int index = findIndex(preorder[preStart], inorder);
        preStart++;
        root.left = helper (preorder, inorder, start, index-1);
        root.right = helper (preorder, inorder, index+1, end);
        return root;
    }
    
    int findIndex(int val, int[] inorder){
         for(int i =0; i<inorder.length; i++){
            if(inorder[i] == val) return i;
        }
        return -1;
    }

    // https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
    // discuss/34787/Simple-and-clean-Java-solution-with-comments-recursive.

    // https://leetcode.com/problems/serialize-and-deserialize-bst/

    // https://leetcode.com/problems/path-sum/
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right== null && sum-root.val ==0) return true;

        return hasPathSum(root.left, sum- root.val) || hasPathSum(root.right, sum- root.val);
    }

    /** 
     * USE BACKTRACKING, 
     * 1 a current List to store values
     * 2 ADD TO RES USING new ArrayList
     * 3 REMOVE AT END(dry run code to get an idea)
     */
    // https://leetcode.com/problems/path-sum-ii/
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        
        dfs(root, sum, res, curr);
        
        return res;
    }
    
    void dfs(TreeNode root, int sum, List<List<Integer>> res, List<Integer> curr){
        if(root == null) return;
        sum-=root.val;
        curr.add(root.val);
        if(sum == 0 && root.left == null && root.right == null) 
            res.add(new ArrayList<>(curr));
        dfs(root.left, sum, res, curr);
        dfs(root.right, sum, res, curr);
        curr.remove(curr.size()-1);
    }

      // find height of tree
      int height(TreeNode root) {
        if (root == null) return 0;
        int left = height(root.left);
        int right = height(root.right);
        return Math.max(left, right) + 1;
    }

    // https://practice.geeksforgeeks.org/problems/diameter-of-binary-tree/1
    int maxDia;
    int diameter(TreeNode root) {
        diaHelper(root);
        return maxDia;
    }
    
    int diaHelper(TreeNode root){
        if(root == null) return 0;
        int left = diaHelper(root.left);
        int right = diaHelper(root.right);
        maxDia = Math.max(maxDia, left + right+1);
        return Math.max(left,right)+1;
    }

    // SIMILAR TO HEIGHT, LEFT, RIGHT AND THEN ROOT
    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null ) return null;
        if(root == p || root == q) return root;
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if(left != null && right !=null) return root;
        else if(left != null)return left;
        else return right;
    }
    // print all ancestors


    // https://leetcode.com/problems/sum-of-left-leaves/
    int sumLeftLeaf = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        preOrderLeftLeafSum(root);
        return sumLeftLeaf;
    }
    
    void preOrderLeftLeafSum(TreeNode root){
        // System.out.println(sum);
        if(root == null) return;
        if(root.left!=null && (root.left.left == null && root.left.right == null)) 
            sumLeftLeaf+=root.left.val;
        preOrder(root.left);
        preOrder(root.right);
    }

    
    // https://leetcode.com/problems/count-complete-tree-nodes/

    // https://leetcode.com/problems/binary-tree-maximum-path-sum/
    // similar to dia
    int maxPathSum = Integer.MIN_VALUE;    
    public int maxPathSum(TreeNode root) {
        maxHelper(root);
        return maxPathSum;
    }
    
    int maxHelper(TreeNode root){
        if(root == null) return 0;
        int left = maxHelper(root.left); 
        int right = maxHelper(root.right);
        // just make sure the value is >= 0
        if(left < 0) left = 0;
        if(right < 0) right = 0;
        
        maxPathSum = Math.max(maxPathSum, left + right + root.val);
        return Math.max(left,right)+root.val;
    }

    /** 
     * POINTS :
     * 1 POSTORDER TRAVERSAL
     * 2 MOVE TILL RIGHT END OF NODE AND THEN MAKE THE NEW CONNECTIONS
     * 3 SET LEFT = NULL
    */
    // https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
    public void flatten(TreeNode root) {
        // helper(root);
        if(root == null) return;
        flatten(root.left);
        flatten(root.right);
        TreeNode y = null;
        if(root.left!=null) {
            y = root.left;
            while(y.right!=null) y = y.right;
            y.right = root.right;
            root.right = root.left;
            root.left = null; // imp
            // return;
        }
        // return;
    }


    /** 
    https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143798/
    1ms-beat-100-simple-Java-dfs-with(without)-hashmap-including-explanation

     * 1 we do a tarversal till we find target node, once we find we make an entry 
     * and return 0, for all nodes in the path from root till target, 
     * an entry is made in hashmap with dist from target
     * 
     * 2 left = find; if(left !=-1) map.put(root, left+1)
     * same for right
     * if left = -1 and right = -1, return -1; not found 
     * 
     * 3 now in dfs, we fetch the dist from map, and 
     * if found, start dfs from this length
     * else start with the initial length (map.get(root))
     * 
     * 4 how this works once the dist till root is found, say x
     * the subtree not containing the target is traversed till a depth of
     * dist - x, as we fetch the length from map
     * and the subtree containing the target is traversed by fetching the dist at 
     * each node, so when dist = k, it is added.
     * 
     * 7 points to remember
     * 
     */
    // https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        HashMap<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();

        findK(root, target, map);//1
       
        dfsK(root, target, map, k, map.get(root), res);//2
        return (List<Integer>)res;
    }

    int findK(TreeNode root, TreeNode target, HashMap<TreeNode, Integer> map){
        if(root == null) return -1;//3
        if(root == target) {
            map.put(root, 0);//4
            return 0;
        }
        int left = findK(root, target, map);
        if(left!=-1) {
            map.put(root, left+1);//5
            return left+1;
        }
        int right = findK(root, target, map);
        if(right!=-1) {
            map.put(root, right+1);
            return right+1;
        }

        return -1;
    }
    
    void dfsK(TreeNode root, TreeNode target, HashMap<TreeNode, Integer> map, int k, 
        int dist, ArrayList<Integer> res){
        if(root == null) return;
        if(map.containsKey(root)) dist = map.get(root);//6
        if(dist == k) res.add(root.val);//7
        dfsK(root.left, target, map, k, dist+1, res);
        dfsK(root.right, target, map, k, dist+1, res);
    }


    // https://leetcode.com/problems/most-frequent-subtree-sum/
    int maxFrequentSum = -1;
    public int[] findFrequentTreeSum(TreeNode root) {
        HashMap<Integer, Integer>map = new HashMap<>();
        
        postOrderSum(map, root);
        
        List<Integer> list = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet())
            if(entry.getValue()==maxFrequentSum) list.add(entry.getKey());
        
        int[] res = new int[list.size()];
        for(int i =0; i<list.size(); i++) res[i] = list.get(i);
        return res;
    }
    
    int postOrderSum(HashMap<Integer, Integer>map, TreeNode root){
        if(root == null) return 0;
        
        int left = postOrderSum(map, root.left);
        int right = postOrderSum(map, root.right);
        int sum = left+right+root.val;
        
        map.put(sum, map.getOrDefault(sum, 0)+1);
        maxFrequentSum = Math.max(maxFrequentSum, map.get(sum));
        return sum;
    }


    // https://leetcode.com/problems/invert-binary-tree
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;
        TreeNode holder = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(holder);
        return root;
    }
    

    /**
        [1,2,3,4,5,6,null,null,null,7,8]
        [99,3,2,null,6,4,5,null,null,null,null,8,7]

        [0,3,1,null,null,null,2]
        [0,3,1,2]
    */
    // INCOMPLETE 71/76 TEST CASES PASSED
    // https://leetcode.com/problems/flip-equivalent-binary-trees/
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) return true;
        if((root1!=null && root2==null) || (root1 == null && root2 !=null)) 
            return false;
        if(root1.val != root2.val) return false;
        int level = 0;
        Deque<TreeNode> q = new LinkedList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        
        q.addLast(root1);
        while(q.size()!=0){
            int size = q.size();    
            level++;
            List<Integer> list = new ArrayList<>();
            
            for(int i =0; i<size; i++){
                TreeNode curr = q.remove();
                if(curr.left!=null) {
                    q.addLast(curr.left); list.add(curr.left.val);
                }
                if(curr.right!=null){
                    q.addLast(curr.right); list.add(curr.right.val);
                }
            }
            map.put(level, list);
        }
        q.clear();
        q.addLast(root2);
        level = 0;
        while(q.size()!=0){
            int size = q.size();    
            level++;
            List<Integer> list = new ArrayList<>();
            for(int i =0; i<size; i++){
                TreeNode curr = q.remove();
                if(curr.left!=null) {
                    q.addLast(curr.left); list.add(curr.left.val);
                }
                if(curr.right!=null){
                    q.addLast(curr.right); list.add(curr.right.val);
                }
            }
            List<Integer> previous = map.get(level);
            Collections.sort(previous);
            Collections.sort(list);
            if(!previous.equals(list)) return false;
        }
        return true;
    }

    // FOREST FIRE 
    // MICROSOFT
    void forestFire(TreeNode node) {
        int arr[] = new int[12];
        TreeNode parent = null;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(node);
        while (!q.isEmpty()) {
            TreeNode removed = q.remove();
            System.out.print(removed.key + ", ");
            arr[removed.key] = -1;
            parent = findParent(root, removed);
            if (parent != null && arr[parent.key] != -1) {
                q.add(parent);
                arr[parent.key] = -1;
                // parent.key = -1;
            }
            if (removed.left != null && arr[removed.left.key] != -1) {
                q.add(removed.left);
                arr[removed.left.key] = -1;
                // removed.left.key = -1;
            }
            if (removed.right != null && arr[removed.right.key] != -1) {
                q.add(removed.right);
                arr[removed.right.key] = -1;
                // removed.right.key = -1;
            }
        }
    }

    TreeNode findParent(TreeNode root, TreeNode node) {
        TreeNode left = null;
        TreeNode right = null;
        if (root == node)
            return null;
        if (root.left == node || root.right == node) {
            // this.parent = root;
            // System.out.println("parent "+root.key);
            return root;
        } else {
            if (node.left != null)
                left = findParent(root, node.left);
            if (node.right != null)
                right = findParent(root, node.right);
            return (left != null) ? left : right;
        }
    }


    ///////////////////////////////////////////////////////////////////////////////
    // INSERT INTO BST    
    // https://leetcode.com/problems/insert-into-a-binary-search-tree
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        TreeNode p = root; TreeNode q = null;
        while(p!=null && p.val!=val){
            q = p;
            if(p.val>val) p = p.left;
            else p = p.right;
        }
        if(q.val>val) q.left = new TreeNode(val);
        else q.right = new TreeNode(val);
        return root;
    }

    // DELETE STYLE INSERT
    // https://leetcode.com/problems/insert-into-a-binary-search-tree
    public TreeNode insertIntoBST1(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        if(root.val>val) root.left = insertIntoBST1(root.left, val);
        else root.right = insertIntoBST1(root.right, val);
        return root;
    }

    /** 
     * WE REPLACE THE KEY TO BE DELETED WITH PREDECESSOR'S VAL AND THEN 
     * RECURSIVELY DELETE THE PREDECESSOR
     * 
     * IF NO CHILDREN, RETURN NULL
     * IF ONE CHILD, RETURN THAT CHILD
     * IF BOTH, GO FOR PREDECESSOR
     * 
     * POINTS : 
     * 1 RETURN TYPE TREENODE, SO ASSIGN ROOT.LEFT OR RIGHT
     * 2 IF MATCH FOUND, REPLACE NODE'S VALUE WITH VALUE OF PREDECESSOR
     * 3 RUN DELETE IN LEFT SUB-TREE FOR PREDECSSOR'S VAL
     * 4 FOR PREDECESSOR, USE WHILE NOT IF
    */
    // https://leetcode.com/problems/delete-node-in-a-bst
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(key < root.val) root.left = deleteNode(root.left, key);//1
        if(key > root.val) root.right = deleteNode(root.right, key);
        
        if(root.val == key){
            // CHECK CAN BE FOR LEFT == NULL OR LEFT != NULL
            // TIME IS SAME FOR BOTH 0ms
            // if(root.left == null) return root.right;
            // else if(root.right == null) return root.left;
            // else {}

            if(root.left != null)  {
                TreeNode replacement = predecessor(root.left);//2
                root.val = replacement.val;
                root.left = deleteNode(root.left, replacement.val);//3
            }
            else if(root.right != null) {
                return root.right;//3
            }
            else return null;
        }
        return root;
    }
    
    TreeNode predecessor(TreeNode root){
        while (root.right!=null) root = root.right;//4
        return root;
    }

    // https://leetcode.com/problems/kth-smallest-element-in-a-bst
    int counter = 0;
    int result = 0;
    public int kthSmallest(TreeNode root, int k) {
        this.counter = k;
        kthSmallestHelper(root);
        return this.result;
    }
    
    void kthSmallestHelper(TreeNode node){
        if(node.left!=null) kthSmallestHelper(node.left);
        this.counter--;
        if(counter == 0){
            System.out.println(node.val);
            this.result = node.val;
        } 
        if(node.right!=null) kthSmallestHelper(node.right);
    }

    /** 
     * POINTS :
     * 1 ADD THE WHOLE TREE TO A LIST
     * 2 CHECK DIFF B/W ADJACENT
    */
    // https://leetcode.com/problems/minimum-distance-between-bst-nodes/
    int min = Integer.MAX_VALUE; 
    ArrayList<Integer> listBst = new ArrayList<>();
    
    public int minDiffInBST(TreeNode root) {
        dfsBST(root, listBst);
        System.out.println(listBst);
        for (int i=0; i<listBst.size()-1; i++){
            min = Math.min(min, Math.abs(listBst.get(i+1)-listBst.get(i)));
        }
        return min;
    }
    
    void dfsBST(TreeNode root, ArrayList<Integer>list){
        if(root == null) return;
        dfsBST(root.left, list);
        list.add(root.val);
        dfsBST(root.right, list);    
    }
    
    // https://leetcode.com/problems/balance-a-binary-search-tree/
    /** basically two pointers are needed which will store the start and end indexes
     * and use 3rd style of function root.left = f(left) and root.right  = f(right)
     */
    public TreeNode balanceBST(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        dfs(root, list);
        return build(list, 0, list.size()-1);
    }
    
    void dfs(TreeNode root, ArrayList<Integer> list){
        if(root == null) return;
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }
    
    TreeNode build(ArrayList<Integer> list, int start, int end){
        if(start>end) return null;
        TreeNode curr = new TreeNode(0) ;
        int mid = start+(end-start)/2;
        curr.val = list.get(mid);
        curr.left = build(list, start, mid-1);
        curr.right = build(list, mid+1, end);
        return curr;
    }

    // long maxGlobal = Long.MAX_VALUE; long minGlobal = Long.MIN_VALUE;
    /** doing a postorder traversal, 
     * earlier was maintaining global vars to keep track of max
     * and min;
     * then found local func args work just fine
     * 
     * keeping a global var and making it false if BST property
     * is violated and returning it at the end.
     * POSTORDER TARVERSAL f(left); f(right); statements for root
     * 
     * 
     * PREORDER WITH TAIL RECURSION
     * return f(left) && f(right)
     * 
     * THERE ARE 2 WAYS FOR BST
     * EITHER WE GO BOTTOM UP, FETCH LEFT AND RIGHT AND CHECK
     * IF ROOT LIES WITHIN RANGE OR 
     * WE GO TOP DOWN AND ADD RANGES FOR THE CHILD NODES
     * PREORDER GOING TOP DOWN TAKES O(n^2) time
     * */
    // https://leetcode.com/problems/validate-binary-search-tree/
    public boolean isValidBST(TreeNode root) {
        // if(root == null) return true;
        return bstHelper(root, Long.MAX_VALUE, Long.MIN_VALUE);        
    }
    
    boolean bstHelper(TreeNode root, long max, long min){
        if(root == null) return true;
        // maxGlobal = max; minGlobal = min;
        // if(!(maxGlobal > root.val && root.val > minGlobal)) {
        if(!(max > root.val && root.val > min)) return false;
        return bstHelper(root.left, root.val, min) && bstHelper(root.right, max, root.val);
    }

    /** found a way to do validBST in postorder similar to max sum in BST 
     * have a global var and set it to false if conds don't match
     * 
     * IMP : WHENEVER A BST, ALWAYS ENSURE ROOT IS GREATER THAN MAX OF
     * ALL IN LEFT SUBTREE AND SMALLER THAN MIN IN RIGHT SUBTREE
     * 
     * POINTS:
     * 1 POSTORDER TRAVERSAL TAKES O(N) TIME
     * 
     * 2 IF ROOT>LEFT.MAX AND ROOT<RIGHT.MIN
     * RETURN MAX(ROOT, RIGHT.MAX) AND MIN(LEFT.MIN, ROOT)
     * ---> COULD HAVE ONLY RETURNED ROOT BUT IT HELPS TO HANDLE CASES
     * OF NULL ROOT AND WHEN BST CONDN IS VIOLATED
     * 
     * 3 CREATE A CUSTOM CLASS TO SEND PROCESSED DATA
     * IF CONDN IS VIOLATED, RETURN (MAX_VAL, MIN_VAL); OPP OF
     * WHEN ROOT == NULL
     * 
    */
    class validBST{
        long max; long min; long sum;
        validBST(long m1, long m2, long s){
            this.max = m1; this.min = m2; this.sum =s;
        }
    }
    
    boolean isBST = true;
    public boolean isValidBSTPost(TreeNode root) {
        postBST(root);
        return isBST;
    }
    
    public validBST postBST(TreeNode root) {
        if (root == null) return new validBST(Long.MIN_VALUE, Long.MAX_VALUE, 0);
        validBST left = postBST(root.left);
        validBST right = postBST(root.right);
    
        if (root.val > left.max && root.val < right.min) {//valid BST
            validBST res = new validBST(
                Math.max(right.max, root.val),//update max
                Math.min(left.min, root.val), 0);//update min
            return res;
        } else isBST = false;
        //invalid BST so MAX is sent in place of min
        return new validBST(Long.MAX_VALUE, Long.MIN_VALUE, 0);
    }

    // https://leetcode.com/problems/validate-binary-search-tree/discuss/32112/
    // Learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-(Java-Solution)


    /** 
     * PREORDER APPROACH IS NOT EFFICIENT, WORKS ON EVERY NODE TWICE
     *  TAKES 0(n^2) 
     *  PROOF - https://www.youtube.com/watch?v=TSEeSXNfl04
     * 
     * USE POSTORDER O(n)
     * SEE VALIDBST ABOVE
     */
    // https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
    class maxSum{
        int max; int min; int sum;
        maxSum(int m1, int m2, int s){
            this.max = m1; this.min = m2; this.sum =s;
        }
    }

    int max = 0;
    public int maxSumBST(TreeNode root) {
        post(root);
        return max;
    }
    public maxSum post(TreeNode root) {
        if (root == null) return new maxSum(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
        maxSum left = post(root.left);
        maxSum right = post(root.right);
        
        if (root.val > left.max && root.val < right.min) {//valid BST
            maxSum res = new maxSum(
                Math.max(right.max, root.val),//update max
                Math.min(left.min, root.val),//update min
                root.val + left.sum + right.sum);

            max = Math.max(max, res.sum);
            return res;
        }
        //not a valid BST, can't let result be part of greater bst, so apply min/max
        return new maxSum(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
    }


    ///////////////////////////////////////////////////////////////////////////////////
  
    /**
     * today 13 aug worked on printing odd level, added if condition also worked on
     * remove nodes with one child, passing params and parent to keep track
     */

    ///////////////////////////////////////////////////////////////////////////////////////////////

    // Pairwise Swap leaf nodes in a binary tree

    class CustomClassForSwap {
        String child;
        TreeNode node;
        TreeNode nodeparent;

        CustomClassForSwap(TreeNode node, TreeNode nodeparent, String child) {
            this.node = node;
            this.nodeparent = nodeparent;
            this.child = child;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    

    void removeHalfNodes(TreeNode node, TreeNode nodeparent) {
        if (node.left != null && node.right != null) {
            removeHalfNodes(node.left, node);
            removeHalfNodes(node.right, node);
        } else {
            if (node.left == null && node.right != null) {
                if (nodeparent.left == node)
                    nodeparent.left = node.right;
                if (nodeparent.right == node)
                    nodeparent.right = node.right;
            }
            if (node.left != null && node.right == null) {
                if (nodeparent.left == node)
                    nodeparent.left = node.left;
                if (nodeparent.right == node)
                    nodeparent.right = node.left;
            }

        }
    }

    ////////////////////////////////////////////////////
    // https://www.geeksforgeeks.org/find-mirror-given-node-binary-tree/
    void findMirrorNode(TreeNode node1, TreeNode node2, int data) {
        if(node1==null) return;
        if(node2==null) return;

        if (node1.key == data) System.out.println("the mirror is " + node2.key);
        if (node2.key == data) System.out.println("the mirror is " + node1.key);

        findMirrorNode(node1.left, node2.right, data);
        findMirrorNode(node1.right, node2.left, data);
    }


    ///////////////////////////// 5th feb LCA
    /**
     * 2 problems one index++ second check the index before assigning
     */
    
    int count = 0;
    int heightCorona = 0;
    boolean stop = false;

    void findCompleteNodes(TreeNode root) {
        this.heightCorona = height(root);
        findCompleteNodesHelper(root, 1);
        System.out.println("the count is " + this.count);
    }

    void findCompleteNodesHelper(TreeNode root, int level) {
        if (!this.stop) {
            System.out.println("root " + root.key + " level " + level);
            if (level == this.heightCorona - 1) {
                System.out.println("in here");
                if (root.left != null && root.right != null) {
                    this.count = this.count + 3;
                    System.out.println(this.count);
                }
                if (root.left != null && root.right == null) {
                    this.count = this.count + 1;
                    this.stop = true;
                }
                if (root.left == null)
                    this.stop = true;
            }

            if (level < this.heightCorona - 1) {
                if (root.left != null && root.right != null) {
                    this.count++;
                    int level1 = level + 1;
                    findCompleteNodesHelper(root.left, level1);
                    // System.out.println(this.count);
                    findCompleteNodesHelper(root.right, level1);
                }
                if (root.left != null) {
                    findCompleteNodesHelper(root.left, level);
                }
                if (root.right != null) {
                    System.out.println("in right " + root.right.key);
                    findCompleteNodesHelper(root.right, level);
                }
            }

        }

    }


    void rightTraversal(TreeNode node) {
        System.out.println(node.rightpointer);
        if (node.left != null)
            rightTraversal(node.left);
        if (node.right != null)
            rightTraversal(node.right);
    }

    void sumOfAllLeaves(TreeNode node){
        int sum = sumOfAllLeavesHelper(node);
        System.out.println("sum "+sum);
    }

    int sumOfAllLeavesHelper(TreeNode node){
        int res = 0; 
        if (node != null){
            if (isLeaf(node)) res += node.key; 
            else res += sumOfAllLeavesHelper(node.left) + sumOfAllLeavesHelper(node.right); 

        } 
        return res; 
    }

    boolean isLeaf(TreeNode node){
        if(node == null) return false;
        if(node.left== null && node.right == null) return true;
        return false;
    }


    // Java program to find binary tree with given inorder 
    // traversal 


	// Function for constructing all possible trees with 
	// given inorder traversal stored in an array from 
	// arr[start] to arr[end]. This function returns a 
	// vector of trees. 
	ArrayList<TreeNode> getTrees(int arr[], int start, int end) { 

		// List to store all possible trees 
		ArrayList<TreeNode> trees= new ArrayList<TreeNode>(); 

		/* if start > end then subtree will be empty so 
		returning NULL in the list */
		if (start > end) { 
			trees.add(null); 
			return trees; 
		} 

		/* Iterating through all values from start to end 
		for constructing left and right subtree 
		recursively */
		for (int i = start; i <= end; i++) { 
			/* Constructing left subtree */
			ArrayList<TreeNode> ltrees = getTrees(arr, start, i - 1); 
			
			/* Constructing right subtree */
			ArrayList<TreeNode> rtrees = getTrees(arr, i + 1, end); 

			/* Now looping through all left and right subtrees 
			and connecting them to ith root below */
			for (int j = 0; j < ltrees.size(); j++) { 
				for (int k = 0; k < rtrees.size(); k++) { 

					// Making arr[i] as root 
					TreeNode node = new TreeNode(arr[i]); 

					// Connecting left subtree 
					node.left = ltrees.get(j); 

					// Connecting right subtree 
					node.right = rtrees.get(k); 

					// Adding this tree to list 
					trees.add(node); 
				} 
			} 
		} 
		return trees; 
	} 

    

    /**  
     * here we need to iterate over the preorder array and for each index
     * we create a new node and then allow the fiunction to find the
     * left and right children for us and return this node
     * 
     * 3 things
     * 1 increment ondex for iteration
     * 2 for start and end ensure the indexes change as per the current index found
     * 3 return the current and also when start == end
     * 
     * basically the idea is for preorder the index+1 is the left child 
     * and the index+2 is right for leaf nodes
    */
    TreeNode createTree(int[] pre, int[] in, int index, int start, int end){
        System.out.println("index "+index);
        if(start>end) return null;
        if(start==end) return new TreeNode(in[start]);
        int currIndex = findInInOrder(pre, in, index);
        if(currIndex==-1) return null;
        TreeNode curr = new TreeNode(in[currIndex]);
        System.out.println("curr "+curr.key);
        curr.left = createTree(pre, in, ++index, start, currIndex-1);
        curr.right = createTree(pre, in, ++index, currIndex+1, end);
        System.out.print(curr.key+", ");
        return curr;
    }

    int findInInOrder(int[] pre, int[] in, int index){
        for(int i =0; i<pre.length; i++){
            if(in[i] == pre[index]) return i;
        }
        return -1;
    }

    int sum(ArrayList<Integer> list){
        int sum =0;
        for(int i =0; i<list.size(); i++){
            sum+= list.get(i)*Math.pow(10, list.size()-i-1);
        }
        System.out.println(sum);
        return sum;
    }


    ////////////////////////////////////////////////////////////////////////////


    // https://www.geeksforgeeks.org/pairwise-swap-leaf-nodes-binary-tree/
    // #:~:text=Given%20a%20binary%20tree%2C%20we,7%2C%209%2C%2010).
    // https://practice.geeksforgeeks.org/problems/check-mirror-in-n-ary-tree/0
    // https://practice.geeksforgeeks.org/problems/clone-a-binary-tree/1
    // https://practice.geeksforgeeks.org/problems/fixed-two-nodes-of-a-bst/1

    // https://www.geeksforgeeks.org/reverse-alternate-levels-binary-tree/
    // https://www.interviewbit.com/courses/programming/topics/tree-data-structure/

    // DP

    // https://www.youtube.com/watch?v=qZ5zayHSH2g&list=PL_z_8CaSLPWfxJPz2-YKqL9gXWdgrhvdn
    // https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/
    // https://leetcode.com/problems/path-sum-iii/
    // https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
    // https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/
    // similar to dia, take max of currSum*(sum - currSum), but return the currSum
    long total = 0; long prod = 0; long mod = 1000000007;
    public int maxProduct(TreeNode root) {
        if(root == null) return 0;
        
        total = findTotal(root);
        
        dfs(root);
        return (int)(prod%mod);
    }
    
    long findTotal(TreeNode root){
        if(root == null) return 0;
        long left = findTotal(root.left);
        long right = findTotal(root.right);
        return (left + right + root.val)%mod;
    }
    
    long dfs(TreeNode root){
        if(root == null) return 0;
        long left = dfs(root.left);
        long right = dfs(root.right);
        long currSum = (left+right+root.val)%mod;
        prod = Math.max(prod, currSum*(total-currSum));
        return (currSum)%mod;
    }
    /////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.root = new TreeNode(4);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(6);
        tree.root.left.left = new TreeNode(1);
        tree.root.left.right = new TreeNode(3);
        tree.root.right.left = new TreeNode(5);
        tree.root.right.right = new TreeNode(8);
        tree.root.right.right.right = new TreeNode(9);
        tree.root.right.right.left = new TreeNode(7);
        // tree.root.right.right.right.right = new TreeNode(11);

        tree.diagonalTraversal(tree.root);
        // tree.spiralPrint(tree.root, 0);
        // tree.findFrequentTreeSum(tree.root);
        // tree.extremeAlternate(tree.root);
        // tree.treetoCDLL(tree.root);

        int[] pre = {4,2,1,3,6};
        int[] in = {1,2,3,4,6};
        // TreeNode tree25jun =  tree.createTree(pre, in, 0, 0, pre.length-1);
        // System.out.println(tree25jun.key);
        // System.out.println(tree25jun.left.key);
        // System.out.println(tree25jun.right.key);
        // tree.Inorder(tree25jun);
        // System.out.println("now pre");
        // tree.Preorder(tree25jun);

        // tree.addGreaterBST(tree.root);
        // tree.levelOrderTraversal(tree.root);

        int[] inOrder = {1,2,3,4,5,6,7,8,9};
        
        // tree.levelOrderTraversal(tree.treeFromInorder(inOrder, 0, inOrder.length-1));
        // tree.findLargestLessThanOrEqualToN(tree.root, 9);
        // int in[] = {4, 5, 7}; int n = in.length;
        // ArrayList<TreeNode> trees = tree.getTrees(in, 0, n - 1); 
        // System.out.println("Preorder traversal of different "+ 
        //                    " binary trees are:"); 
        // for (int i = 0; i < trees.size(); i++) { 
        //     tree.Preorder(trees.get(i)); 
        //     System.out.println(""); 
        // } 
        // tree.sumOfAllLeftLeaves(tree.root);
        // tree.deleteLeafNodesBST(tree.root, tree.root);
        // System.out.println("done");
        // System.out.println(tree.root.left.left.key);
        // tree.Inorder(tree.root);
        // tree.sumOfAllNodes(tree.root);
        // tree.sumOfAllLeaves(tree.root);
        // tree.connectNodesAtSameLevel(tree.root);
        // tree.rightTraversal(tree.root);
        // tree.iterativePostorder(tree.root);
        // tree.diagonalPrint(tree.root, 6);
        // tree.findCousins(tree.root, tree.root.left.left);
        // tree.printPath(tree.root, tree.root.left.left);
        // tree.printPathToLeafPathList(tree.root);
        // tree.findCousinsQueue(tree.root, tree.root.right.right.right);
        // tree.leftViewOfTree(tree.root, 0);
        // tree.printHashMap();

        // tree.printAllPathsUsingList(tree.root);
        // System.out.println(tree.root.right.left.key);
        // tree.lowestCommonAncestor(tree.root, tree.root.right.left,
        // tree.root.right.right);
        // tree.findMirrorNode(tree.root, tree.root, 1);
        // tree.findRootToLeafPathsWithGivenSumLists6Jun(tree.root, 7);

        Tree mirrorTree = new Tree();
        mirrorTree.root = new TreeNode(4);
        mirrorTree.root.left = new TreeNode(6);
        mirrorTree.root.right = new TreeNode(5);
        mirrorTree.root.left.left = new TreeNode(2);
        mirrorTree.root.left.right = new TreeNode(3);
        mirrorTree.root.right.left = new TreeNode(3);
        mirrorTree.root.right.right = new TreeNode(8);
        tree.root.right.right.right = new TreeNode(9);
        tree.root.right.right.left = new TreeNode(17);

        // tree.findRootToLeafPathsWithGivenSumLists6Jun(mirrorTree.root, 12);
        // tree.checkLevelAnagram(tree.root, mirrorTree.root);
        // tree.addLeafToQueue(tree.root, tree.root, "any");
        // for(int i =0; i<9;i++){
        // System.out.println(tree.queueSwapLeafNew);
        // }
        // System.out.println("queue size "+tree.queueSwapLeafNew);

        // for (CustomClassForSwap element : tree.queueSwapLeafNew) {
        // System.out.println(element.node.key);
        // }
        // tree.printLeftLeaf(tree.root);
        // tree.printRightLeaf(tree.root);
        // System.out.println("Preorder traversal of binary tree is ");
        // tree.printPreorder();

        // tree.insertIntoTree(7);

        // System.out.println("\nInorder traversal of binary tree is ");
        // tree.Inorder(tree.root);

        // System.out.println("\nPreorder traversal of binary tree is ");
        // tree.Preorder(tree.root);

        // while calling call as method of the newly cretaed tree object
        // tree.boundaryTraversalLeft(tree.root, tree.root);
        // tree.boundaryTraversalRight(tree.root, tree.root);
        // System.out.println("\nPostorder traversal of binary tree is ");
        // tree.printPostorder();
        // System.out.println("\ntree left leaves");
        // tree.leftLeafNode(tree.root.left);
        // tree.leftLeafNode(tree.root.right);

        // System.out.println("all leaves")

        // tree.queueLeft.add(tree.root);
        // System.out.println(tree.root.key);
        // tree.leftLeafNode();

        // tree.levelOrderTraversal(tree.root);
        // tree.breadthFirst(tree.root);
        // int height = tree.height(tree.root);
        // System.out.println("height is " + height);

        // tree.queueBFS.add(tree.root);
        // tree.breadthFirst(tree.root);

        // tree.printqueLevel();
        // tree.levelOrderTraversal(tree.root);

        // tree.queueReverse.add(tree.root);
        // tree.reverseLevel();

        // tree.queueLevelInsertion.add(tree.root);
        // tree.insertLevelOrder(9);
        // tree.Preorder(tree.root);
        // tree.printStack();

        // tree.postorderTraversal(tree.root);

        // tree.queueInorder.add(tree.root);
        // tree.queueInorder.add(tree.root.left);
        // tree.queueInorder.add(tree.root.right);
        // tree.iterativeInorder();

        // tree.mirror(tree.root);
        // // tree.iterativeInorder();
        // tree.levelOrderTraversal(tree.root);

        // // tree.printOddlevel(tree.root);
        // tree.removeNodesWithOneChild(tree.root, tree.root, "s");
        // tree.levelOrderTraversal(tree.root);

        // tree.printVerticalOrder(tree.root, 0);
        // tree.verticalTraversal(tree.root);
        // tree.printHashVertical();

        // tree.diameter(tree.root);

        // tree.InorderSuccessor(tree.root);
        // tree.InorderSuccessor1(tree.root);

        // tree.levelLast(tree.root);
        // tree.levelLastRecursion(tree.root,1);

        // tree.kthSmallest(tree.root, 3);
        // System.out.println("sum is "+ tree.sumRootToLeaf(tree.root, 0));
        // tree.zigzagTraversal(tree.root);
        // tree.levelTraversal(tree.root);
        // tree.zigzagTraversal(tree.root);

        // tree.levelOrderTraversal(tree.root);
        // tree.removeHalfNodes(tree.root, tree.root);
        // tree.levelOrderTraversal(tree.root);
        // tree.levelTraversal(tree.root);
        // mirrorTree.treeToLinkedList(mirrorTree.root, mirrorTree.root);
        // mirrorTree.treeToLinkedList(mirrorTree.root);
        // mirrorTree.levelOrderTraversal(mirrorTree.root);
        // tree.inorderIterative(tree.root);
        // tree.printPaths(tree.root);
        // tree.maxSumPath(tree.root);
        // tree.findLowestAncestor(tree.root.left.left, tree.root.left.right,
        // tree.root);
        // tree.treetoDLL(tree.root);
        // tree.convertAndPrint(tree.root);
        // System.out.println("tree head "+ tree.head.key);
        // tree.printList(LinkedListNode.head);
        // tree.levelOrderTraversal(tree.root);

        // tree.flatten(tree.root);
        // tree.printList(tree.root);
        // tree.findCompleteNodes(tree.root);
        // tree.forestFire(tree.root.left);
        // tree.spiralTraversal(tree.root);
        // tree.boundaryTraversal21Apr(tree.root);
        // tree.boundaryOfTree(tree.root);
        // tree.kthLargestElementBST(tree.root, 3);
        // tree.hasPathSum(tree.root, 9);
        // tree.printAncestorsUsingStack(tree.root, 1);
    }
}