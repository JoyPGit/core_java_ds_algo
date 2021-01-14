
import java.util.*;
import java.util.Map.Entry;

class TreeNode {
    int key;
    int val;
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

    /**
     * 
     * prune, ancestor, nodes at dist k
     * 
     * 
     * * TEMPLATES : 1 HEIGHT -> ANCESTOR (REMOVE GLOBAL), VALID BST(POST) 2 TREE
     * PRUNING 3 PATH SUM 3, IS SUBTREE, VALID BST(PRE)
     * 
     * function h(root){ if(root == null) return 0; int left = h(left); int right =
     * h(right); return max(left, right)+1; }
     * 
     * 
     * boolean allAncestorsHelper(TreeNode root, List<Integer> res, int target){
     * if(root == null) return false; if(root.val == target) return true;
     * 
     * boolean left = allAncestorsHelper(root.left, res, target); boolean right =
     * allAncestorsHelper(root.right, res, target); if(left || right)
     * res.add(root.val); return (left || right); }
     * 
     * -------------------------------------------------------
     * 
     * public TreeNode pruneTree(TreeNode root) { if(root == null) return null;
     * root.left = pruneTree(root.left); root.right = pruneTree(root.right); // root
     * decision if(root.left == null && root.right == null && root.val == 0) return
     * null; return root; }
     * 
     * 
     * ----------------------------------------------------------
     *
     * public boolean isSubtree(TreeNode s, TreeNode t) { if(s == null) return
     * false; else if(isSame(s, t)) return true; else return isSubtree(s.left, t) ||
     * isSubtree(s.right, t); }
     * 
     * boolean isSame(TreeNode a, TreeNode b){ if(a == null || b == null) return (a
     * == null && b == null); if(a.val != b.val) return false;
     * 
     * left = isSame(a.left, b.left) right = isSame(a.right, b.right) return left &&
     * right; }
     * 
     * 
     * ////////////PATH SUM 3 int pathCounter = 0; int target = 0; public int
     * pathSum3(TreeNode root, int sum) { if (root == null) return 0; target = sum;
     * outerDfs(root); return pathCounter; }
     * 
     * void outerDfs(TreeNode root){ if(root == null) return; innerDfs(root, 0); //
     * outerDfs(root.left); outerDfs(root.right); }
     * 
     * void innerDfs(TreeNode root, int sum){ if(root == null) return; int currSum =
     * sum+root.val; // System.out.println(currSum); if(currSum == target)
     * pathCounter++; innerDfs(root.left, currSum); innerDfs(root.right, currSum); }
     * 
     * 
     * https://www.techiedelight.com/convert-given-binary-tree-to-full-tree-removing-half-nodes/
     * https://leetcode.com/problems/delete-nodes-and-return-forest/
     * ---------------------------------------------------------------------------
     * /** IMP K DIST, FLATTEN, MAX SUM BST, SORTED ARRAY TO BST, ISOMORPHISM MORRIS
     * INORDER,
     * 
     * QUESTIONS 1 BST (3 POINTS : 1 LEFT.MAX<ROOT<RIGHT.MIN 2 INORDER IS SORTED
     * ARRAY) 3 POSTORDER TAKES O(N) AND PREORDER 0(N^2))
     * 
     * 1.1 VALIDATE BST 1.2 MAX SIZE BST 1.3 MAX SUM BST 1.4 DELETE IN BST 1.5
     * BALANCE A BST (SORTED ARRAY INRODER) 1.6 FIX A BST 1.7 BST TO CDLL 1.8 SORTED
     * DLL TO BALANCED BST 1.9 FLOOR AND CEILING 1.10 ALL IN RANGE 2 BINARY TREES
     * 
     * 2.1 TRAVERSAL 2.1.1 INORDER, PREORDER, POSTORDER 2.1.2 ITERATIVE TRAVERSAL
     * 
     * 2.1.3 LEVEL ORDER 2.1.3.1 QUEUE 2.1.3.2 USING RECURSION 2.1.3.3 FINDING
     * ANAGRAMS 2.1.3.4 NEXT POINTER (INSERTING NULL TO DEMARCATE LEVELS) 2.1.3.5
     * MAX LEVEL SUM
     * 
     * 2.1.4 SPIRAL TRAVERSAL 2.1.5 BOUNDARY TRAVERSAL 2.1.6 CREATE TREE FROM
     * TRAVERSAL 2.2 VIEWS 2.2.1 TOP, BOTTOM, LEFT, RIGHT 2.3 HASHMAP 2.3.1 NODES AT
     * DIST K 2.4 3 HEIGHT 3.1 HEIGHT 3.2 DIA 3.3 NODES AT DIA END 3.4 LCA (SIMILAR
     * CONCEPT OF LEFT AND RIGHT SUBTREES) 3.5 IS SUBTREE 3.6 IS SAME TREE 3.7 MIN
     * DEPTH 4 SUBTREES 4.1 IS SUBTREE 4.2 IS SAME TREE (PREORDER WITH TAIL REC) 5
     * LEAF NODES 5.1 CHECK LEAF NODES 5.2 REMOVE LEAF NODES 5.3 CHECK IF COMPLETE
     * BINARY TREE 5.4 PRUNING 5.5 HALF NDOES 6 DP QUES
     * https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/ 7 MISC
     * FOREST BURNING, MIN TIME TO INFORM EMP COUSINS IN BINARY TREE, FLATTEN TO
     * LINKED LIST ROOT TO LEAF PATHS, NEXT SIBLING, ISOMORPHISM, MIRROR, MAX SUM
     * PATH BETWEEN ANY TWO NODES SERIALIZE DESERIALIZE 8 PREORDER WITH TAIL
     * RECURSION IS-SAME, IS-VALID-BST
     * 
     * 
     */

    void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        System.out.print(root.key + ", ");
        inOrder(root.right);
    }

    TreeNode findPred(TreeNode node) {
        TreeNode current = node;
        node = node.left;
        while (node.right != null && node.right != current)
            node = node.right;
        return node;
    }

    // mirror
    // https://leetcode.com/problems/invert-binary-tree
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode holder = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(holder);
        return root;
    }

    // https://leetcode.com/problems/convert-bst-to-greater-tree
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null)
            return root;
        reverseInorder(root);
        return root;
    }

    void reverseInorder(TreeNode root) {
        if (root == null)
            return;
        reverseInorder(root.right);
        root.val += sum;
        sum = root.val;
        reverseInorder(root.left);
    }

    /**
     * STEPS : 3 
     * LEFT, PRED, LINK LEFT == NULL PRINT, 
     * MOVE RIGHT ELSE, FIND PRED ->
     * PRED'S RIGHT NULL, LINK -> MOVE LEFT -> ELSE UNLINK, PRINT, MOVE RIGHT
     * 
     * MORRIS INORDER TRAVERSAL SET CURRENT'S PREDECESSOR'S RIGHT TO CURRENT IF THE
     * PRED'S RIGHT IS NOT NULL, SET IT NULL. (NOT NULL SIGNIFIES THAT IT HAS BEEN
     * VISITED)
     * 
     * IF LEFT IS NULL, PRINT ADD AND MOVE TO RIGHT, ELSE FIND PRED, SET RIGHT IF
     * PRED RIGHT NOT NULL, SET NULL, ADD.
     * 
     * we are finding inorder predecessor so if the inorder predecessor has already
     * been visited, we need to print curr and move right as in INORDER TRAVERSAL
     */
    // https://leetcode.com/problems/binary-tree-inorder-traversal
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode pred = findPred(curr);
                // link back if right null
                if (pred.right == null) {
                    pred.right = curr;
                    curr = curr.left;
                }
                // if right is not null, we have visited, unlink
                else {
                    pred.right = null;
                    res.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return res;
    }

    /**
     * POTNTS : 1 PREV MUST BE LESS THAN ROOT, INORDER TRAVERSAL OF BST IS SORTED 
     * 2 WHENEVER CONDN IS VIOLATED, ASSIGN FIRST AND SECOND SIMULTANEOUSLY 
     * 
     * MORRIS INORDER USED HERE
     * 
     */
    // https://leetcode.com/problems/recover-binary-search-tree
    // [3,1,4,null,null,2] both assignments made simultaneously
    TreeNode prev = null;
    // new TreeNode(Integer.MIN_VALUE);
    TreeNode first = null, second = null;

    public void recoverTree(TreeNode root) {

        // List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            // prev must be lesser than root
            if (prev != null && curr.val <= prev.val) { // 1
                if (first == null)
                    first = prev;
                second = curr;
            }

            if (curr.left == null) {
                // res.add(curr.val);
                // prev assigned when visiting root
                prev = curr; // 2
                curr = curr.right;
            } else {
                TreeNode pred = findPred(curr);
                // link back if right null
                if (pred.right == null) {
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    pred.right = null;
                    // res.add(curr.val);
                    // prev assigned when visiting root
                    prev = curr;
                    curr = curr.right;
                }
            }
        }

        // System.out.println(first.val);
        // System.out.println(second.val);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    //////////////////////////////////////////// PREORDER/////
    // SIMPLE : USE STACK, POP, ADD TO RES, ADD LEFT TO STACK, ADD RIGHT TO STACK
    void preOrder(TreeNode x) {
        if (root == null)
            return;
        System.out.print(x.key + ", ");
        preOrder(x.left);
        preOrder(x.right);
    }

    // ITERATIVE
    // add right and then left, pop and add to res
    // ADD THE RIGHT AND THEN THE LEFT TO STACK
    // https://leetcode.com/problems/binary-tree-preorder-traversal
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(root);

        while (q.size() != 0) {
            TreeNode curr = q.removeLast();
            res.add(curr.val);
            if (curr.right != null) q.addLast(curr.right);
            if (curr.left != null) q.addLast(curr.left);
        }
        return res;
    }

    void postOrder(TreeNode root) {
        postOrder(root.left);
        postOrder(root.right);
        System.out.println("node key " + root.key);
    }

    /**
     * POINTS : 
     * 1 ADD TO STACK, REMOVE CURR, ADD LEFT AND RIGHT 
     * 2 4,2,6-------STACK------RES 4----------4---------- 2,6--------2,6----------4
     * --------------------4,6,2 
     * LEFT IS ADDED AND THEN WHILE ADDING TO RES, FIRST
     * RIGHT IS ADDED. SO FINAL ANS IS REVERSED
     * 
     * add, remove, reverse (for post)
     */
    // PREORDER -> RIGHT, LEFT; POST -> LEFT, RIGHT
    // https://leetcode.com/problems/binary-tree-postorder-traversal
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(root);

        while (q.size()!=0) {
            TreeNode curr = q.removeLast();
            res.add(curr.val);
            if (curr.left != null) q.addLast(curr.left);
            if (curr.right != null) q.addLast(curr.right);
        }

        Collections.reverse(res);
        return res;
    }

    // https://leetcode.com/problems/second-minimum-node-in-a-binary-tree
    public int findSecondMinimumValue(TreeNode root) {
        HashSet<Integer> set = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        preOrder(root, set, pq);
        // remove twice
        pq.remove();
        if (pq.size() == 0)
            return -1;
        return pq.remove();
    }

    void preOrder(TreeNode root, HashSet<Integer> set, PriorityQueue<Integer> pq) {
        if (root == null) return;
        if (!set.contains(root.val)) {
            set.add(root.val);
            pq.add(root.val);
        }

        preOrder(root.left, set, pq);
        preOrder(root.right, set, pq);
    }

    /**
     * MOST IMP : KEEP TRACK OF SIZE OF QUEUE ALWAYS, THIS HELPS KEEP TRACK OF ALL
     * ELS IN THIS LEVEL
     * 
     * LEVEL ORDER 2 WAYS : USE QUEUE OR USE LEVEL USING RECURSION 
     * 1 MAINTAIN A HASHMAP 
     * 2 SEARCH FOR LEVEL IN MAP AND ADD TO IT 
     * 3 TRICKY PART IS TO ADD FROM HASHMAP TO RES AS MAP ISN'T ALWAYS SORTED 
     * 4 START FROM 0, FETCH i AND
     * ADD for(int i =0; i<map.size(); i++) res.add(i, map.get(i));
     * 
     * imp : run for all heights and use level flag
     *
     */
    // RECURSIVE, using hashmap
    // https://leetcode.com/problems/binary-tree-level-order-traversal
    public List<List<Integer>> levelOrder(TreeNode root) {
        int h = height(root);
        HashMap<Integer, List<Integer>> map = new HashMap<>();
    
        for(int i =0; i<h; i++){
            helper(root, i, i, map);
        }

        List<List<Integer>> res = new ArrayList<>();
        
        for (int i = 0; i < map.size(); i++){
            res.add(i, map.get(i));
        }
        return res;
    }
    
    void helper(TreeNode root, int index, int level, HashMap<Integer, List<Integer>> map){
        if(root == null) return;
        if(index == 0) {
            List<Integer> curr = map.getOrDefault(level, new ArrayList<>());
            curr.add(root.val);
            map.put(level, curr);
            return;
        }
        
        helper(root.left, index-1 ,level, map);
        helper(root.right, index-1, level, map);
    }

    // https://www.geeksforgeeks.org/check-if-all-levels-of-two-trees-are-anagrams-or-not

    /** 
     * QUEUE: 1 ADD TO Q, 2 KEEP TRACK OF SIZE, 
     * 3 CREATE A NEW LIST 
     * 4 WHILE REMOVING THE CURR EL, KEEP ADDING TO LIST 
     * 5 ADD LIST TO RES
     * 
     * imp : size of q
    */
    // ITERATIVE
    public List<List<Integer>> levelOrderIterative(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(root);

        while (q.size() != 0) {
            int size = q.size();
            List<Integer> curList = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.removeFirst();
                curList.add(curr.val);
                if (curr.left != null)
                    q.addLast(curr.left);
                if (curr.right != null)
                    q.addLast(curr.right);
            }

            res.add(curList);
        }
        return res;
    }

    // TRY REVERSE LEVEL ORDER, LEVEL ORDER WITHOUT QUEUE AND HASHMAP

    /**
     * POINTS : 1 KEEP TRACK OF DIRECTION USING A BOOLEAN FLAG 2 SIMILAR TO LEVEL
     * ORDER 3 WHEN DIR IS FALSE, REVERSE CURR LIST AND THEN ADD TO RES
     * spiral
     */
    // https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null)
            return res;
        boolean ltor = true;
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(root);

        while (q.size() != 0) {
            int size = q.size();
            List<Integer> currList = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.removeFirst();
                currList.add(curr.val);

                if (curr.left != null)
                    q.addLast(curr.left);
                if (curr.right != null)
                    q.addLast(curr.right);
            }
            // flip before adding
            if (!ltor)
                Collections.reverse(currList);

            res.add(currList);

            ltor = !ltor;

        }
        return res;
    }

    // https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
    /**
     * 1 KEEP TRACK OF SIZE AS IN LEVEL ORDER 2 FOR EACH CURR EL, ADD NEXT AS THE
     * NEXT EL IN Q (q.getFirst()) 3 IF i == size-1, ASSIGN NULL TO NEXT, AS THE
     * NEXT EL IN Q WILL BE THE CHILD OF SOME NODE AND OF DIFF LEVEL
     */
    class RightNode {
        public int val;
        public RightNode left;
        public RightNode right;
        public RightNode next;

        public RightNode() {
        }

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
        if (root == null)
            return root;

        Deque<RightNode> q = new LinkedList<>();
        q.addLast(root);

        while (q.size() != 0) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                RightNode curr = q.removeFirst();
                if (i == size - 1)
                    curr.next = null; // last, so null
                else
                    curr.next = q.getFirst(); // get don't remove
                if (curr.left != null)
                    q.addLast(curr.left);
                if (curr.right != null)
                    q.addLast(curr.right);
            }
        }
        return root;
    }

    /**
     * VIEWS : FOR RIGHT VIEW DO REVERSE PREORDER TRAVERSAL 
     * FOR LEFT, DO NORMAL PREORDER
     */

    /**
     * 1 a bit tricky, add dist and node in map 
     * 2 if dist exists, don't add 
     * 3 IMP : how to get nodes sorted by dist? start from 0 till map.size and add by
     * fetching i from map
     * 
     * 
     * if inorder then overwrite dist, if reverse inorder, check if dist entry exists
     */
    // right view
    // https://leetcode.com/problems/binary-tree-right-side-view
    public List<Integer> rightSideView(TreeNode root) {
        HashMap<Integer, TreeNode> map = new HashMap<>();
        dfs(map, root, 0);

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < map.size(); i++) {
            res.add(map.get(i).val);
        }
        return res;
    }

    void dfs(HashMap<Integer, TreeNode> map, TreeNode root, int dist) {
        if (root == null) return;
        if (!map.containsKey(dist)) map.put(dist, root);

        dfs(map, root.right, dist + 1);
        dfs(map, root.left, dist + 1);

    }

    // https://leetcode.com/discuss/interview-question/275467/
    // uber-phone-screen-boundary-of-the-perfect-binary-tree
    // https://stackoverflow.com/questions/30275735/to-print-the-boundary-of-binary-tree
    List<Integer> boundaryOfTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null)
            return res;
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(root);

        while (q.size() != 0) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.removeFirst();
                // boundary nodes
                if (i == 0 || i == size - 1) res.add(curr.val); 

                if (curr.left != null) q.addLast(curr.left);
                if (curr.right != null) q.addLast(curr.right);
            }
        }
        System.out.println(res);
        return res;
    }

    class Key {
        TreeNode node;
        int dist;

        Key(TreeNode n, int d) {
            this.node = n;
            this.dist = d;
        }
    }

    // Method that returns the bottom view.
    ArrayList<Integer> bottomView(TreeNode root){
        // Code here
        List<Key> holder = new ArrayList<>();
        HashMap<Integer, Key> map = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();
        
        if(root == null) return res;
        Deque <Key> q = new LinkedList<>();
        q.addLast(new Key(root, 0));
        while(q.size()!=0){
            int size = q.size();
            for(int i =0; i<size; i++){
                Key curr = q.removeFirst();
                map.put(curr.dist, curr);
                q.addLast(new Key(curr.node.left, curr.dist-1));
                q.addLast(new Key(curr.node.right, curr.dist+1));
            }
        }
        
        for(Entry<Integer, Key> e : map.entrySet()){
            holder.add(e.getValue());
        }

        Collections.sort(holder, (x,y)->x.dist -y.dist);
        for(Key k : holder) res.add(k.node.val);
        return res;
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


    // use hashmap, right -> dist+1, left -> same
    // https://www.geeksforgeeks.org/diagonal-traversal-of-binary-tree/
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



    // IMP HOW TO REPRESENT A TREE ON A 2D MAP
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
     * 
     * sort by x, if x matches sort by y
     * for sorting by x use pq, as map doesn't store in a sorted fashion
     * for sorting by y, use comparator.
     * TRICKY IF MAP DOESN'T CONTAIN X, ADD TO Q,
     * so no need to add all values of map to q after dfs
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

        // for(Map.Entry<Integer, List<Dist>> entry : map.entrySet()){
        //     pq.add(entry.getKey()); 
        // }
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
        
        // add to map
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


    /** 
     * BFS -> CUSTOM CLASS
     * POINTS :
     * 1 USE BFS
     * 2 STORE PARENTS IN MAP
     * 3 BFS : 
     * ADD TO Q, WHILE REMOVING ADD TO VISITED
     * DISTANCE CAN BE FOUND USING A GENERIC COUNTER
     * 
     * * TIME CAN BE STORED IN VISITED OR CUSTOM CLASS
     */
    class DistK{
        TreeNode node; int dist;
        DistK(TreeNode n, int d){
            this.node = n; 
            this.dist = d;
        }
    }
    public List<Integer> distanceKBFS(TreeNode root, TreeNode target, int K) {
        // build hashmap
        // start bfs from target using q
        HashMap<TreeNode, TreeNode> map = new HashMap<>();
        Deque<DistK> q = new LinkedList<>();
        HashSet<TreeNode> visited = new HashSet<>();
        
        List<Integer> res = new ArrayList<>(); 
        if(root == null) return res;
        
        dfs(root, root.left, map);
        dfs(root, root.right, map);
        
        q.addLast(new DistK(target, 0));
        // visited.add(target);
        // BFS
        while(q.size()!=0){
            DistK curr = q.removeFirst();
            // System.out.println(curr.node.val);
            if(visited.contains(curr.node)) continue;
            visited.add(curr.node);
            if(curr.dist == K) {
                res.add(curr.node.val);
                continue;
            }
            
            TreeNode parent = map.getOrDefault(curr.node, null);
            if(parent != null) q.addLast(new DistK(parent, curr.dist+1)); 
            if(curr.node.left != null) 
                q.addLast(new DistK(curr.node.left, curr.dist+1)); 
            if(curr.node.right != null) 
                q.addLast(new DistK(curr.node.right, curr.dist+1));
        }
        return res;
    }
    

    public List<Integer> distKnoClass(TreeNode root, TreeNode target, int K) {
        // build hashmap
        // start bfs from target using q
        HashMap<TreeNode, TreeNode> map = new HashMap<>();
        Deque<TreeNode> q = new LinkedList<>();
        HashSet<TreeNode> visited = new HashSet<>();
        
        List<Integer> res = new ArrayList<>(); 
        if(root == null) return res;
        
        dfs(root, root.left, map);
        dfs(root, root.right, map);
        
        q.addLast(target);//new DistK(target, 0));
        
        int distance = 0; // generic counter
        // visited.add(target);
        // BFS
        while(q.size()!=0){
            int size = q.size();
            for(int k =0; k<size; k++){
                TreeNode curr = q.removeFirst();
                
                // System.out.println(curr.val);
                if(visited.contains(curr)) continue;
                visited.add(curr);
                
                if(distance == K) {
                    res.add(curr.val);
                    continue;
                }
               
                TreeNode parent = map.getOrDefault(curr, null);
                if(parent != null) q.addLast(parent); 
                if(curr.left != null) q.addLast(curr.left); 
                if(curr.right != null) q.addLast(curr.right);
            } 
            distance++;
            // System.out.println("dist "+distance);
        }
        return res;
    }
    
    void dfs(TreeNode parent, TreeNode child, HashMap<TreeNode, TreeNode> map){
        if(parent == null || child == null) return;
        map.put(child, parent);
        dfs(child, child.left, map);
        dfs(child, child.right, map);
    }

    // FOREST FIRE 
    // MICROSOFT
    /**
     * POINTS:
     * 1 SIMILAR TO NODES AT DISTANCE K
     * 2 MAINTAIN A AMAP TO STORE PARENTS
     * 3 USE BFS
     * 
    */
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
    
    // can be replaced by hashmap, see above
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


    /////////////////////// HEIGHT AND RECURSION LEFT, RIGHT
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

    /////////////// SIMILAR TO HEIGHT, LEFT, RIGHT AND THEN ROOT DECISION
    /////////////////////// ANCESTOR TEMPLATE
    // (ALSO HELPS TO AVOID GLOBAL VAR)
    // CONDITIONS FOR LEFT AND RIGHT CAN BE MERGED USING AND (OR) OR

    /** 
     * 
     *  if(allAncestorsHelper(root.left, res, target)
     *   || allAncestorsHelper(root.right, res, target)){
     *     res.add(root.val);
     *  }
     * 
     *  CAN BE SIMPLIFIED USING LEFT AND RIGHT VARS->
     * 
     *  boolean left = allAncestorsHelper(root.left, res, target);
     *  boolean right = allAncestorsHelper(root.right, res, target);
     *  if(left || right) res.add(root.val);
     *
     * 
     * remember to return root if both left and right are not null
    */
    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null ) return null;
        if(root == p || root == q) return root;
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if(left != null && right !=null) return root;
        else if(left != null) return left;
        else return right;
    }

    /** 
     * SAME AS HEIGHT, CHECK IF LEFT OR RIGHT IS TRUE
     * IF ANY IS TRUE, THEN IT'S AN ANCESTOR
     * CHECK THE RETURN STATEMENTS
     * 
    */
    // print all ancestors
    List<Integer> allAncestors(TreeNode root, int target){
        List<Integer> res= new ArrayList<>();
        if(root == null) return res;
        allAncestorsHelper(root, res, target);
        System.out.println("ancestors "+res);
        return res;
    }

    boolean allAncestorsHelper(TreeNode root, List<Integer> res, int target){
        if(root == null) return false;
        if(root.val == target) return true;
        // if(allAncestorsHelper(root.left, res, target)
        // ||allAncestorsHelper(root.right, res, target)){
        //     res.add(root.val);
        //     return true;
        // }
        boolean left = allAncestorsHelper(root.left, res, target);
        boolean right = allAncestorsHelper(root.right, res, target);
        if(left || right) res.add(root.val);
        return (left || right);
        // return false;
    }


    // https://www.geeksforgeeks.org/find-mirror-given-node-binary-tree/
    void findMirrorNode(TreeNode node1, TreeNode node2, int data) {
        if(node1==null) return;
        if(node2==null) return;

        if (node1.key == data) System.out.println("the mirror is " + node2.key);
        if (node2.key == data) System.out.println("the mirror is " + node1.key);

        findMirrorNode(node1.left, node2.right, data);
        findMirrorNode(node1.right, node2.left, data);
    }


    // https://leetcode.com/problems/symmetric-tree/
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return helper(root.left, root.right);
    }
    
    boolean helper(TreeNode a, TreeNode b){
        if(a == null && b == null) return true;
        if(a == null || b == null) return false;
        if(a.val != b.val) return false;
        boolean left = helper(a.left, b.right);
        boolean right = helper(a.right, b.left);
        return (left && right);
    }


    // USING ANCESTOR TEMPLATE (LEFT, RIGHT AND RETURN)
    public boolean isSameTreeLR(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        else if(p == null ||  q==null || p.val!=q.val) return false;
        
        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);
        return (left == true && right == true);
    }

    // https://leetcode.com/problems/same-tree/
    // SIMPLE PREORDER FOR BOTH SIMULTANEOUSLY
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false; // p == null && q == null
        if(p.val != q.val) return false;

        boolean left = isSameTree(p.left, q.left) ;
        boolean right = isSameTree(p.right, q.right);
        return left && right;
    }

    /** 
     * 
     * return f(left) && f(right)
     * 
     * THERE ARE 2 WAYS FOR BST
     * EITHER WE GO BOTTOM UP, FETCH LEFT AND RIGHT AND CHECK
     * IF ROOT LIES WITHIN RANGE OR 
     * WE GO TOP DOWN AND ADD RANGES FOR THE CHILD NODES
     * PREORDER GOING TOP DOWN TAKES O(n^2) time
     * 
     * 
     * */
    // https://leetcode.com/problems/validate-binary-search-tree/
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        return bstHelper(root, Long.MAX_VALUE, Long.MIN_VALUE);        
    }
    
    boolean bstHelper(TreeNode root, long max, long min){
        if(root == null) return true;
        if(!(max > root.val && root.val > min)) return false;
        boolean left = bstHelper(root.left, root.val, min);
        boolean right = bstHelper(root.right, max, root.val);
        return left && right;
    } 


    /** 
     * 1 PASS THE ROOT, MAX AND MIN
     * 2 CALC MAX AND MIN AT EVERY NODE
    */
    // https://leetcode.com/problems/maximum-difference-between-node-and-ancestor
    int diff = 0;
    public int maxAncestorDiff(TreeNode root) {
        if(root == null) return 0;
        dfs(root, root.val, root.val);
        return diff;
    }
    
    void dfs(TreeNode root, int max, int min){
        diff = Math.max(diff, Math.abs(max - min));
        if(root == null) return;
        
        // System.out.println("root "+root.val+"; "+diff);
        dfs(root.left, Math.max(max, root.val), Math.min(min, root.val));
        dfs(root.right, Math.max(max, root.val), Math.min(min, root.val));
    }

    // https://leetcode.com/discuss/general-discussion/454844/binary-tree-isomorphism-problem    

    // https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
    // discuss/34787/Simple-and-clean-Java-solution-with-comments-recursive.

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

    // fails for -ve values, need to add a check for that
    // https://leetcode.com/problems/serialize-and-deserialize-bst/
    int index = 0;
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "";
        String res = "";
        String result = dfsPre(root, res);
        // System.out.println("res "+result);
        return result;
    }
    
    public String dfsPre(TreeNode root, String res){
        if(root == null) return "*";
        String result = "";
        result+=""+root.val;
        result+=dfsPre(root.left, res);
        result+=dfsPre(root.right, res);
        return result;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length()==0) return null;
        if(data.length()== 1) return new TreeNode(Integer.parseInt(""+data.charAt(0)));
        
        return helper(data);
    }
        
    public TreeNode helper(String data){  
        // System.out.println("index "+index);
        
        if( index >= data.length() ||
            (""+data.charAt(index)).equals("*")) {
            this.index++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(""+data.charAt(this.index++)));
        root.left = helper(data);
        root.right = helper(data);
        return root;
    }

    ///////////////////////////////// PATH SUM
    /// SIMILAR TO HEIGHT AND ANCESTOR TEMPLATE
    
    // https://leetcode.com/problems/path-sum/
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right== null && sum-root.val ==0) return true;

        return hasPathSum(root.left, sum- root.val) || hasPathSum(root.right, sum- root.val);
    }
    
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


    // https://leetcode.com/problems/most-frequent-subtree-sum/
    int maxFrequentSum = -1;
    public int[] findFrequentTreeSum(TreeNode root) {
        HashMap<Integer, Integer>map = new HashMap<>();
        
        postOrderSum(map, root);
        
        // adding nodes to result list
        List<Integer> list = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet())
            if(entry.getValue()==maxFrequentSum) list.add(entry.getKey());
        
        // list to array
        int[] res = new int[list.size()];
        for(int i =0; i<list.size(); i++) res[i] = list.get(i);
        return res;
    }
    
    // HEIGHT TEMPLATE
    // for sum always prefer bottom up -> POSTORDER
    int postOrderSum(HashMap<Integer, Integer>map, TreeNode root){
        if(root == null) return 0;
        
        int left = postOrderSum(map, root.left);
        int right = postOrderSum(map, root.right);
        int sum = left+right+root.val;
        
        map.put(sum, map.getOrDefault(sum, 0)+1);
        maxFrequentSum = Math.max(maxFrequentSum, map.get(sum)); // flag
        return sum;
    }

    /////////////////////////////// PATH SUM + BACKTRACKING
    /** 
     * USE BACKTRACKING, 
     * 1 a current List to store values
     * 2 ADD TO RES USING new ArrayList
     * 3 REMOVE AT END(dry run code to get an idea)
     */
    // https://leetcode.com/problems/path-sum-ii/
    // add sum and calculate, leaf node, remove
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        
        dfs(root, sum, res, curr);
        
        return res;
    }
    
    void dfs(TreeNode root, int sum, List<List<Integer>> res, List<Integer> curr){
        if(root == null) return;
        // do these 2 operations for curr node and then calculate
        sum-=root.val; 
        curr.add(root.val);
        if(sum == 0 && root.left == null && root.right == null) res.add(new ArrayList<>(curr));

        dfs(root.left, sum, res, curr);
        dfs(root.right, sum, res, curr);
        curr.remove(curr.size()-1);
    }

    /** 
     * USED PREFIX SUM TO OPTIMISE
     * 1 SAME AS ABOVE INNER DFS STARTS WITH SUM 0
     * 2 ALWAYS PUT A 0, NO ELEMENTS SELECTED
     * 5 REMOVE THE CURR ENTRY AT LAST, FREQ -1, REMOVE LEFT SUBTREE WHILE
     * MOVING TO RIGHT SUBTREE
     */
    // HASHING (DFS 2+ BACKTRACKING)
    int pathCounter1 = 0; int target1 = 0;
    public int pathSumHash(TreeNode root, int sum) {
        if (root == null) return 0;
        target1 = sum;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1); // 2
        innerDfs(root, map, 0); // 1
        return pathCounter1;
    }
    
    void innerDfs(TreeNode root, HashMap<Integer, Integer> map, int sum){
        if(root == null) return;
        int currSum = sum+root.val;
        pathCounter1+=map.getOrDefault(currSum - target1, 0); // 3
        map.put(currSum, map.getOrDefault(currSum,0)+1); // 4 
        innerDfs(root.left, map, currSum);
        innerDfs(root.right, map, currSum);
        // remove at last and non need to check if currSum exists in map
        // as it has been put earlier
        map.put(currSum, map.getOrDefault(currSum, 0)-1); // 5
    }

    ////////////////////////////////////////////////
    //////////////////////// DFS PATH SUM 3 TEMPLATE
    class ListNode {
        int key;
        int val;
        ListNode next;
        ListNode random;
    
        ListNode() {
        }
        // constructor overloading
    
        ListNode(int data) {
            this.key = data;
            this.next = null;
            this.random = null;
            this.val = this.key;
        }
    }
    // https://leetcode.com/problems/subtree-of-another-tree
    /** if the root matches, then recur for that using isSame
     * else recur for left and right nodes.
     * 
     * 1 isSubtree -> isSame
     * 2 else -> isSubtree(left) || isSubtree(right)
     * 
     * SAME TREE METHOD FROM ABOVE, RUN FOR ALL NODES LIKE
     * DFS PATH SUM 3
     * 
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null) return false;
        boolean equal = (isSameTree(s, t));
        // t is passed, not t.left or t.right
        boolean left =  isSubtree(s.left, t);
        boolean right = isSubtree(s.right, t);
        return equal || left || right;
    }

    // SAME AS DFS PATH SUM 3, ONLY USE OR INSTEAD OF AND
    // https://leetcode.com/problems/linked-list-in-binary-tree/
    public boolean isSubPath(ListNode head, TreeNode root) {
        if(root == null) return false;
        if(isSame(head, root)) return true;
        return (isSubPath(head, root.left) // not head.next
        || isSubPath(head, root.right));
    }
    
    // a == null is true as the list can be short 
    boolean isSame(ListNode a, TreeNode b){
        if(a == null) return true;
        if(b == null) return false;
        // if(a==null || b == null) return a==null && b==null;
        if(a.val != b.val) return false;
        // use || not && as list can match any one child, not both
        return isSame(a.next, b.left) || isSame(a.next, b.right);
    }

    // https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/


    // 2 dfs one simple preorder, other dfs takes a param sum starting from 0
    // works on inclusion exclusion as in recursion
    // the inclusion is done in the inner dfs
    // int currSum = sum+root.val;
    // if(currSum == target) pathCounter++; 

    // CAN BE SOLVED USING HASHMAP ALSO

    // https://leetcode.com/problems/path-sum-iii/
    int count = 0;
    
    public int pathSum3(TreeNode root, int sum) {
        outerDfs(root, sum, 0);
        return count;
    }
    
    void outerDfs(TreeNode root, int sum, int curr){
        if(root == null) return;
        outerDfs(root.left, sum, curr);
        outerDfs(root.right, sum, curr);
        innerDfs(root, sum, root.val);
    }
    
    void innerDfs(TreeNode root, int sum, int curr){
        if(curr == sum) count++;
        
        if(root.left != null)innerDfs(root.left, sum, curr+root.left.val);
        if(root.right != null)innerDfs(root.right, sum, curr+root.right.val);
    }



    ////////////////////////////// LEAF NODES
    // SAME AS INSERT TEMPLATE .left = f()

    // https://leetcode.com/problems/sum-of-left-leaves/
    int sumLeftLeaf = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        preOrderLeftLeafSum(root);
        return sumLeftLeaf;
    }
    
    // IMP
    // like remove duplicates in list, one step before
    // check one level above fro leaf, not at the same node
    void preOrderLeftLeafSum(TreeNode root){
        // System.out.println(sum);
        if(root == null) return;
        if(root.left!=null && (root.left.left == null && root.left.right == null)) 
            sumLeftLeaf+=root.left.val;
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * POINST : 
     * 1 USE INSERT TEMPLATE
     * 2 IF LEAF, RETURN ROOT 
     * 3 IF ANY CHILD IS NULL, RETURN TEH OTHER CHILD
     * 4 IF NON CHILD IS NULL, RETURN ROOT
     */
    // https://practice.geeksforgeeks.org/problems/remove-half-nodes/1
    public TreeNode removeHalfNodes(TreeNode root)
    {
        if(root == null) return root;
        root.left = removeHalfNodes(root.left);
        root.right = removeHalfNodes(root.right);

        if((root.left == null && root.right == null))
            return root;

        else if(root.left == null || root.right == null) 
            return root.left == null ? root.right : root.left;
    
        return root;
    }


    // https://leetcode.com/problems/count-complete-tree-nodes/

    /////////////////////////////////////////////////////////
    //////////////////////////. BST. ////////////////////////
    //    TEMPLATE       .left = (); .right =() type
    
    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if(root == p || root == q) return root;
        if(p.val<root.val && root.val <q.val) return root;
        if(q.val<root.val && root.val <p.val) return root;
        return helper(root, p, q);
    }
    
    TreeNode helper(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return null;
        
        if(root.val > p.val && root.val >q.val) return helper(root.left, p, q);
        else if(root.val <p.val && root.val <q.val) return helper(root.right, p, q);
        else return root;
    }

    // INSERT INTO BST
    // https://leetcode.com/problems/insert-into-a-binary-search-tree
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        if(root.val>val) root.left = insertIntoBST(root.left, val);
        else root.right = insertIntoBST(root.right, val);
        return root;
    }

    // https://leetcode.com/problems/insert-into-a-binary-search-tree
    public TreeNode insertIntoBST1(TreeNode root, int val) {
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

    /** 
     * POSTORDER
     * INSERT TEMPLATE
    */
    // https://leetcode.com/problems/binary-tree-pruning/
    public TreeNode pruneTree(TreeNode root) {
        if(root == null) return null;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        // root decision
        if(root.left == null && root.right == null && root.val == 0) return null;       
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
     * 3 RUN DELETE IN LEFT SUB-TREE FOR PREDECESSOR'S VAL
     * 4 FOR PREDECESSOR, USE WHILE NOT IF
     * 
     * IF WE SET BEFOREHAND, root.left = deleteRoot()
     * NO NEED TO KEEP TWO PARAMTERS, PARAM AND CHILD
     * AND WHEN WE FIND LEFT = NULL, PARENT = ROOT.RIGHT
     * IS NOT NEEDED, JUST RETURN ROOT.RIGHT
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
                return root.right;//
            }
            else return null;
        }
        return root;
    }
    
    TreeNode predecessor(TreeNode root){
        while (root.right!=null) root = root.right;//4
        return root;
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


    // SIMILAR TO BUILDING FROM SORTED ARRAY, BUILD NEW
    /** basically two pointers are needed which will store the start and end indexes
     * and use 3rd style of function root.left = f(left) and root.right  = f(right)
     */
    // https://leetcode.com/problems/balance-a-binary-search-tree/
    public TreeNode balanceBST(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        // add to sorted list
        dfs(root, list); 
        // build from sorted
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


    // long maxGlobal = Long.MAX_VALUE; long minGlobal = Long.MIN_VALUE;
    /** 
     * doing a postorder traversal, 
     * earlier was maintaining global vars to keep track of max
     * and min;
     * then found local func args work just fine
     * 
     * keeping a global var and making it false if BST property
     * is violated and returning it at the end.
     * POSTORDER TARVERSAL f(left); f(right); statements for root 
     */ 
    

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
     * 
     * ---------------------------------------------------------------
     * f(){
     *  null -> valid
     *  valid -> update
     *  invalid -> flag -> return invalid
     * }
     * 
     * VALID CONDN : new validBST(Long.MIN_VALUE, Long.MAX_VALUE, 0);
     * INVALID CONDN : validBST(Long.MAX_VALUE, Long.MIN_VALUE, 0);
     * 
     * 
     *    * STEPS :
     * VALID IS WHEN LEFT.MAX<ROOT<RIGHT.MIN 
     * 1 IF NULL RETURN VALID CONDN NEW CLASS(MAX = min-inf, MIN = max-inf,0)
     * so root >(MAX) min-inf and root < (MIN)max-inf
     * 0 is unnecessary fhere but a template so can be reused for max sum BST
     * 
     * 2 IF VALID () RETURN CORRESPONDING MAX AND MIN
     * 3 IF INVLAID SET FLAG AND RETURN INVALID CONDN.
     * 
    */

    // LIKE HEIGHT
    // EXTRA PARAM INSTEAD OF GLOBAL VAR
    // https://leetcode.com/problems/validate-binary-search-tree/
    class BSTCustomLong{
        long max; long min; long sum;
        
        BSTCustomLong(long m1, long m2, long s){
            this.max = m1; this.min = m2; this.sum =s;
        }
    }
    
    boolean isBST = true;
    public boolean isValidBSTPost(TreeNode root) {
        postBST(root);
        return isBST;
    }
    
    public BSTCustomLong postBST(TreeNode root) {
        if (root == null) return new BSTCustomLong(Long.MIN_VALUE, Long.MAX_VALUE, 0);
        BSTCustomLong left = postBST(root.left);
        BSTCustomLong right = postBST(root.right);

        // valid BST
        if (root.val > left.max && root.val < right.min) {
            BSTCustomLong res = new BSTCustomLong(
                // think in terms of left and right subtrees
                // just find max and min 
                Math.max(right.max, root.val),//update max
                Math.min(left.min, root.val), 0);//update min
            return res;
        } else isBST = false;
        // invalid BST so MAX is sent in place of min
        return new BSTCustomLong(Long.MAX_VALUE, Long.MIN_VALUE, 0);
    }

    // https://leetcode.com/problems/validate-binary-search-tree/discuss/32112/
    // Learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-(Java-Solution)


    /** 
     * PREORDER APPROACH IS NOT EFFICIENT, WORKS ON EVERY NODE TWICE
     *  TAKES 0(n^2) 
     *  PROOF - https://www.youtube.com/watch?v=TSEeSXNfl04
     * 
     * --------------------------------------------------------------------
     * USE POSTORDER O(n)
     * 
     * * [4,8,null,6,1,9,null,-5,4,null,null,null,-3,null,10]
     * 
     * left sum = -8 (-5-3)
     * right sum = 14 (10+4)
     * root = 1
     * what to pass up? 14 or 15 or 7?
     * max sum bst, not max sized bst so 14 needs to be passed
     * but it's a bst so if we pass max(left, right)
     * 0 is passed in place of -8  and sum becomes 15,
     * SO IN SHORT, USE GLOBAL VAR
     * 
     * 
     * SEE VALIDBST ABOVE, LIKE HEIGHT
     * f(){
     *  null -> valid
     *  invalid -> return false, sum = 0
     *  valid -> update max -> pass sum
     * }
     * 
     * VALID CONDN : new validBST(Long.MIN_VALUE, Long.MAX_VALUE, 0);
     * INVALID CONDN : validBST(Long.MAX_VALUE, Long.MIN_VALUE, 0);
     * 
     * 
     * imp : keep global max and update only in case of valid BST
     * 
     * use Math.max(root.val, right.max) and Math.min(left.min, root.val)
     * as if subtree doesn't exist, worng value might be passed
     */
    // https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
    class BSTNode{
        int max, min, sum;
        BSTNode(int a, int b, int c){
            this.max = a;
            this.min = b;
            this.sum = c;
        }
    }
    
    int max = 0;
    public int maxSumBST(TreeNode root) {
        if(root == null) return 0;
        helperBST(root);
        return max;
    }
    
    
    BSTNode helperBST(TreeNode root){
        if(root == null) return new BSTNode(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
        BSTNode left = helperBST(root.left);
        BSTNode right = helperBST(root.right);
        
        // valid BST
        if(left.max < root.val && root.val < right.min){
            // System.out.println(left.sum +" "+right.sum +" "+ root.val);
            max = Math.max(max, left.sum+right.sum+root.val);
            return new BSTNode(Math.max(right.max, root.val), // max 
                           Math.min(left.min, root.val), // min 
                           left.sum+right.sum+root.val);
            // send sum, not max
        }
        else {
            return new BSTNode(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }
    }
    ////////////////////////////////////////////////////////////
    ///////////////IMP

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
        // calling first moves us to leaf nodes
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
     * 1 we do a traversal till we find target node, once we find we make an entry 
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
    

    // IMP
    /** 
     * POINTS :
     * 1 THE GLOBAL VAR PRESTART IS USED TO CREATE NEW NODES
     * 2 STEPS : create node, find index, preStart++, set children
     * 3 AND THE BOUNDARY FOR LEFT = (start, index-1)
     * AND RIGHT = (index+1, end)
     * 
     * 
     * imp : 
     * return when start>end
     * include start and end in arguments
     * 
    */
    // https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
    int preIndex = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        if(n==0) return null;
        if(n==1) return new TreeNode(preorder[0]);
        
        return helper(preorder, inorder, 0, n-1);
    }
    
    TreeNode helper(int[] preorder, int[] inorder, int start, int end){
        if(start > end) return null;
        
        TreeNode root = new TreeNode(preorder[preIndex++]);
        int curr = findIndex(inorder, root.val);
        
        root.left = helper(preorder, inorder, start, curr-1);
        root.right = helper(preorder, inorder, curr+1, end);
        return root;
    }
    
    int findIndex(int[] inorder, int key){
        for(int i =0; i<inorder.length; i++){
            if(inorder[i] == key) return i;
        }
        return -1;
    }

    /**
     * 1 SAME AS ABOVE, ONLY DIFF IS WE MOVE FROM RIGHT TO LEFT
     * 2 POSTINDEX = n-1;
     * 3 new TreeNode(postorder[postIndex--]);
     * 4 FIRST RIGHT AND THEN LEFT CHILD
     * 
     * 
     * right and then left
     * start>end and findIndex in inorder remains same
    */
    // https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
    int postIndex;
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        int n = postorder.length;
        if(n==0) return null;
        if(n==1) return new TreeNode(postorder[0]);
        postIndex = n-1;
        return helper(inorder, postorder, 0, n-1);
    }
    
    TreeNode helper2(int[] inorder, int[] postorder, int start, int end){
        if(start > end) return null;
        
        TreeNode root = new TreeNode(postorder[postIndex--]);
        int curr = findIndex(inorder, root.val);
        
        root.right = helper(inorder, postorder, curr+1, end);
        root.left = helper(inorder, postorder, start, curr-1);
        return root;
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

    // IMP
    // https://www.techiedelight.com/find-optimal-cost-to-construct-binary-search-tree/
    
    // https://leetcode.com/problems/minimum-height-trees/
    ////////////////////////////////////////////////////////////////////////////
    // https://www.techiedelight.com/convert-given-binary-tree-to-full-tree-removing-half-nodes/
    // https://leetcode.com/problems/delete-nodes-and-return-forest/

    // https://www.geeksforgeeks.org/pairwise-swap-leaf-nodes-binary-tree/
    // #:~:text=Given%20a%20binary%20tree%2C%20we,7%2C%209%2C%2010).
    // https://practice.geeksforgeeks.org/problems/check-mirror-in-n-ary-tree/0
    // https://practice.geeksforgeeks.org/problems/clone-a-binary-tree/1

    // https://www.geeksforgeeks.org/reverse-alternate-levels-binary-tree/
    // https://www.interviewbit.com/courses/programming/topics/tree-data-structure/

    // DP
    // https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/
    // https://www.youtube.com/watch?v=qZ5zayHSH2g&list=PL_z_8CaSLPWfxJPz2-YKqL9gXWdgrhvdn
    // https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/
    
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

        // tree.diagonalTraversal(tree.root);
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
        // tree.allAncestors(tree.root, 1);
        // tree.findMirrorNode(tree.root, tree.root, 1);
        // tree.findRootToLeafPathsWithGivenSumLists6Jun(tree.root, 7);

        Tree mirrorTree = new Tree();
        mirrorTree.root = new TreeNode(7);
        mirrorTree.root.left = new TreeNode(7);
        mirrorTree.root.right = new TreeNode(8);
        mirrorTree.root.left.left = new TreeNode(2);
        mirrorTree.removeHalfNodes(mirrorTree.root);
        mirrorTree.inOrder(mirrorTree.root);
        // mirrorTree.root.left.right = new TreeNode(3);
        // mirrorTree.root.right.left = new TreeNode(3);
        // mirrorTree.root.right.right = new TreeNode(8);
        // tree.root.right.right.right = new TreeNode(9);
        // tree.root.right.right.left = new TreeNode(17);


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