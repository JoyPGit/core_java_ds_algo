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

    void print1DMatrix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.println(arr[i] + ";");
                System.out.println();
            } else
                System.out.print(arr[i] + ", ");
        }
    }

    void printArrayList(ArrayList<Integer> holder){
        for(int i =0; i<holder.size(); i++){
            System.out.println(holder.get(i));
        }
    }

    void printLinkedList(LinkedList<Integer> list){
        for(int i =0; i<list.size(); i++){
            if(i == list.size()-1) System.out.println(list.get(i)+"--|");
            else System.out.print(list.get(i)+"->");
        }
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
    
    // now add functions
    void Inorder(TreeNode x) {
        if (x == null) {
            return;
        }
        Inorder(x.left);
        System.out.print(x.key+", ");
        Inorder(x.right);
    }

    void Preorder(TreeNode x) {
        if (x == null) {
            return;
        }
        System.out.print(x.key+", ");
        Preorder(x.left);
        Preorder(x.right);
    }

    TreeNode checkForPosition(int value, TreeNode node, TreeNode parent1) {
        TreeNode parent = node;
        // System.out.println("parent "+ parent);
        // if(root!=null){
        // if(value < this.root.key){
        // checkForPosition(value, root.left);
        // } else {
        // checkForPosition(value, root.right);
        // }
        // return parent; //return node
        // } else return root;
        if (node != null) {
            if (value < parent.key) {
                System.out.println("less than root");
                checkForPosition(value, parent.left, parent);
                return parent.left;
            } else {
                checkForPosition(value, parent.right, parent);
                return parent.right;
            }
        } else {
            return parent;
        }

        // return this.root;
    }

    void insertIntoTree(int value) {
        // what should i pass as param,
        // the tree or the node key; tree need not be passed as it can be accessed
        TreeNode position = checkForPosition(value, this.root, null);
        // System.out.println("position "+ position.key);
        if (position.left == null && position.key > value) {
            position.left = new TreeNode(value);
        } else
            position.right = new TreeNode(value);
    }

    ///////////////////////////////////////////////////////////////////////////////////
    void boundaryTraversalLeft(TreeNode node, TreeNode nodeparent) {
        // don't use while loop with recursion
        // while(node.left!=null){
        // // boundaryTraversalLeft(node.left, node);
        // System.out.println("left node is "+ node.key);
        // node.left = node.left.left;
        // }
        if (node.left != null) {
            System.out.println("node left " + node.left.key);
            boundaryTraversalLeft(node.left.left, node.left);
        } else {
            System.out.println("node left " + node.right.key);
            boundaryTraversalLeft(node.right, node);
        }
    }

    void boundaryTraversalRight(TreeNode node, TreeNode nodeparent) {

        while (node.right != null) {
            // boundaryTraversalLeft(node.left, node);
            System.out.println("right node is " + node.key);
            node.right = node.right.right;
        }

    }

    public Queue<TreeNode> queueLeft = new LinkedList<TreeNode>();

    void leftLeafNode() {
        while (!queueLeft.isEmpty()) {
            TreeNode current = queueLeft.remove();
            if (current.left != null) {
                // System.out.println(node.key);
                // leftLeafNode(node.left);
                // if the key is left then print it else keep on adding to the queue
                System.out.println(current.left.key);
                queueLeft.add(current.left);
            }
            if (current.right != null) {
                queueLeft.add(current.right);
            }
        }

        // System.out.println(node.key);
        ;
    }

    // void sumOfLeafNodes(TreeNode node){
    // if(node!= null && (node.left!= null && node.right!=null)){
    // sumOfLeafNodes(node.left);
    // sumOfLeafNodes(node.right);
    // } else System.out.println(node.key);
    // }

    void sumOfLeftLeafNodes(TreeNode node) {
        // int sum = 0;
        TreeNode track = node;
        while (track != null) {
            if (track.left != null) {
                track = track.left;
                System.out.println("track.key " + track.key);
                if (track.left == null && track.right == null) {
                    System.out.println(track.key);
                    // sum += node.key;
                } else
                    return;
            }
        }
    }

    public Queue<Integer> queLevel = new LinkedList<Integer>();

    // public Queue<TreeNode> queLevel = new Array<TreeNode>();
    void levelOrderTraversal(TreeNode node) {
        int height = this.height(node);
        for (int i = 0; i < height; i++) {
            printGivenLevel(node, i);
            System.out.println("---");
        }
    }

    void printGivenLevel(TreeNode node, int level) {
        if (level == 0) {
            System.out.println(node.key);
        } else {
            if (node.left != null) {
                printGivenLevel(node.left, level - 1);
            }
            if (node.right != null) {
                printGivenLevel(node.right, level - 1);
            }
        }
    }

    void printqueLevel() {
        // int size = queLevel.size();
        // // TreeNode track = queLevel.element();
        // for(int i =0; i<size; i++){
        // System.out.println(track.key);
        // queLevel.get(i);
        // }
        System.out.println("queue " + queLevel);
    }

    public Queue<TreeNode> queueBFS = new LinkedList<TreeNode>();

    void breadthFirst(TreeNode node) { // bfs is same as level order traversal
        while (!queueBFS.isEmpty()) {
            TreeNode current = queueBFS.remove();
            System.out.println("removed element " + current.key);
            // methods on Queue add, remove, peek, size
            // while(node) how to set a condition for traversal?
            if (current.left != null || current.right != null) {
                if (current.left != null) {
                    queueBFS.add(current.left);
                }
                if (current.right != null) {
                    queueBFS.add(current.right);
                }
            }
            // else return;
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////
    // Insertion in a Binary Tree in level order
    // see usage of return to break out
    public Queue<TreeNode> queueLevelInsertion = new LinkedList<TreeNode>();

    int insertLevelOrder(int value) {
        while (!queueLevelInsertion.isEmpty()) {
            TreeNode current = queueLevelInsertion.remove();
            System.out.println("current key " + current.key);
            if (current.left != null) {
                System.out.println("current left " + current.left.key);
                queueLevelInsertion.add(current.left);
            } else {
                current.left = new TreeNode(value);
                System.out.println("added at current left of " + current.key);
                return 1;
            }
            if (current.right != null) {
                System.out.println("current right " + current.right.key);
                queueLevelInsertion.add(current.right);
            } else {
                current.right = new TreeNode(value);
                System.out.println("added at current right of " + current.key);
                return 1;
            }
        }
        return 2;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    void postorderTraversal(TreeNode node) {

        if (node.left != null)
            postorderTraversal(node.left);
        if (node.right != null)
            postorderTraversal(node.right);
        System.out.println("node key " + node.key);
    }

    ///////////////////////////////////////////////////////////////////////////////////
    // find height of tree
    int height(TreeNode root) {
        if (root == null) return 0;
        return Math.max(height(root.left), height(root.right))+1;
    }

    ///////////////////////////////////////////////////////////////////////////////////
    // reverse level order traversal
    public Queue<TreeNode> queueReverse = new LinkedList<TreeNode>();
    // public Stack <Integer> stackReverse = new Stack<Integer>(); //throws error
    // https://stackoverflow.com/questions/
    // 29551507/the-type-stack-is-not-generic-it-cannot-be-parameterized-with-arguments-charac
    java.util.Stack<TreeNode> stackReverse2 = new java.util.Stack<TreeNode>();
    // implementing a Stack in Java
    // https://www.geeksforgeeks.org/stack-class-in-java/

    void reverseLevel() {

        while (!queueReverse.isEmpty()) {
            TreeNode currentReverse = queueReverse.remove();
            // System.out.println("removed ele "+currentReverse.key);
            stackReverse2.push(currentReverse);

            if (currentReverse.left != null) {
                queueReverse.add(currentReverse.left);
            }
            if (currentReverse.right != null) {
                queueReverse.add(currentReverse.right);
            }
        }
        while (!stackReverse2.empty()) {
            System.out.println(stackReverse2.pop().key);
        }
    }

    void printStack() {
        while (!stackReverse2.empty()) {
            System.out.println(stackReverse2.pop().key);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // public java.util.Stack<TreeNode> stackInorder = new
    // java.util.Stack<TreeNode>();
    public Queue<TreeNode> queueInorder = new LinkedList<TreeNode>();

    void iterativeInorder() {
        while (!queueInorder.isEmpty()) {
            TreeNode right = queueInorder.remove();
            TreeNode left = queueInorder.remove();
            queueInorder.add(right);

            System.out.println("left key" + left.key);
            if (left.left != null) {
                queueInorder.add(left.left);
            }
            if (left.right != null) {
                queueInorder.add(left.right);
            }
        }
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////

    // public Queue<TreeNode> queueMirror = new LinkedList<TreeNode>();
    void mirror(TreeNode node) {
        // queueMirror.remove()
        // System.out.println(" " +node.key);
        TreeNode left;
        if (node != null) {// && node.left!=null || node.right!=null){
            left = node.left;
            node.left = node.right;
            node.right = left;

            if (node.left != null)
                System.out.println("left " + node.left.key);
            if (node.right != null)
                System.out.println("right " + node.right.key);
        }
        if (node.left != null)
            mirror(node.left);
        if (node.right != null)
            mirror(node.right);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    void printOddlevel(TreeNode node) {
        // calling another function (height)
        int height = height(node);
        System.out.println("height " + height);
        // the trick is to trace..the conditions can be fetched from there
        for (int i = 0; i < height; i++) {
            if (i % 2 == 0)
                printLevel(node, i);
        }
    }

    void printLevel(TreeNode node, int level) {
        // int currentLevel = level;
        // ArrayList<Integer> newlist = new ArrayList<Integer>();
        // for checking if array contains use arraylist
        // newlist.add(0);
        // newlist.add(2);
        // newlist.add(4);
        if (node != null) {
            if (level == 0) {
                System.out.println("key " + node.key + " level " + level);

            } else {
                printLevel(node.left, level - 1);
                printLevel(node.right, level - 1);
            }
        }

    }

    void removeNodesWithOneChild(TreeNode node, TreeNode nodeParent, String s) {
        if (node != null) {
            System.out.println("key " + node.key);
            if (node.left != null && node.right != null) {
                removeNodesWithOneChild(node.left, node, "left");
                removeNodesWithOneChild(node.right, node, "right");
            } else if (node.left == null) {
                if (s == "left") {
                    nodeParent.left = node.right;// removeNodes call again..fuck*** complex
                    removeNodesWithOneChild(nodeParent.left, nodeParent, "s");
                } else {
                    nodeParent.right = node.right;
                    removeNodesWithOneChild(nodeParent.right, nodeParent, "s");
                }

            } else if (node.right == null && node.left != null) {
                if (s == "right") {
                    nodeParent.right = node.left;
                    removeNodesWithOneChild(nodeParent.right, nodeParent, "s");
                } else {
                    nodeParent.left = node.left;
                    removeNodesWithOneChild(nodeParent.left, nodeParent, "s");
                }
            }
        }
    }

    /////
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

    // Queue<TreeNode> queueSwapLeaf = new LinkedList<TreeNode>();
    Queue<CustomClassForSwap> queueSwapLeafNew = new LinkedList<CustomClassForSwap>();

    void addLeafToQueue(TreeNode node, TreeNode nodeParent, String s) {
        // int sting = 's';
        // if (node != null) {
        if (node.left == null && node.right == null) {
            // queueSwapLeaf.add(node);
            // queueSwapLeaf.add(nodeParent);
            // queueSwapLeaf.add(s);
            // sting;
            System.out.println("leaf " + node.key);
            CustomClassForSwap count = new CustomClassForSwap(node, nodeParent, s);
            queueSwapLeafNew.add(count);
        }
        if (node.left != null) {
            addLeafToQueue(node.left, node, "left");
        }
        if (node.right != null) {
            addLeafToQueue(node.right, node, "right");
        }
        // }//DON'T USE ELSE IF..IT MATCHES THE FIRST ELSE IF...USE IF TWICE ELSE
        // IT WON'T GO TILL THE RIGHT NODE
    }

    // void swap(Queue queue){
    // for (int i=2; i<queue.size(); i++){
    // if(i%2==0){
    // TreeNode
    // }
    // }
    // }
    /////////////////////////////////////////////////////////////////////////////////////////////////

    public Queue<TreeNode> queueVertical = new LinkedList<TreeNode>();

    public HashMap<Integer, Vector<Integer>> hashVertical = new HashMap<>();

    Vector<Integer> verticalVector = new Vector<>();

    void printVerticalOrder(TreeNode node, int value) {

        /**
         * value is key and the hash is the the node key. a vector is implemented for
         * chaining the node values vector fetches value, if present it is added to the
         * list. else a new list is created
         */
        Vector<Integer> key = hashVertical.get(value);
        if (key == null) {
            verticalVector.add(value);
        } else
            verticalVector.add(value);
        // hashVertical.put(value, verticalVector);
        hashVertical.put(value, verticalVector);

        if (node != null) {

            if (node.left != null) {
                System.out.print(node.left.key + "  --> ");
                System.out.println(value - 1);
                // verticalVector.get(node.left.key);

                printVerticalOrder(node.left, value - 1);
                // hashVertical.put(value - 1, node.left.key);
                // hashVertical.put(node.left.key, value-1);
            } // else return;
            if (node.right != null) {
                System.out.print(node.right.key + "  --> ");
                System.out.println(value + 1);
                printVerticalOrder(node.right, value + 1);
                // hashVertical.put(value + 1, node.right.key);
                // hashVertical.put(node.right.key, value+1);
            } // else return;
        }
    }

    void printHashVertical() {
        System.out.println("hash values");
        for (int i = 0; i < 11; i++) {
            System.out.print(i + " --> >");
            System.out.println(hashVertical.get(i));
        }
    }

    void printLeaf(TreeNode node) {
        // if (node!=null){
        // if(node.left == null && node.right ==null){
        // System.out.println("leaf node "+ node.key);
        // } else {
        // printLeaf(node.left);
        // printLeaf(node.right);
        // }
        // }
    }
    // void printLeftLeaf(TreeNode node){
    // while(node!=null){
    // if(node.left== null && node.right == null){
    // System.out.println("leaf "+node.key);
    // }
    // node = node.left;
    // if(node.right != null){
    // printRightLeaf(node.right);
    // }
    // }

    // }

    // void printRightLeaf(TreeNode node){
    // while(node!=null){
    // if(node.left== null && node.right == null){
    // System.out.println("leaf "+node.key);
    // }
    // node = node.right;
    // if(node.left!=null){
    // printLeftLeaf(node.left);
    // }

    // }
    // }


    int max(int a, int b) {
        int maximum = a >= b ? a : b;
        System.out.println("maximum " + maximum);
        return maximum;
    }

    void diameter(TreeNode node) {
        int dia = height(node.left) + height(node.right);
        System.out.println("heightnew(node.left) " + height(node.left));
        System.out.println("heightnew(node.right) " + height(node.right));
        System.out.println("dia " + dia);
    }

    int InorderSuccessor(TreeNode node) {
        int closest = 100000000;
        if (node != null) {
            int rootValue = node.key;
            if (node.left != null) {
                if (node.left.key < closest && node.left.key > rootValue) {
                    closest = node.left.key;
                    InorderSuccessor(node.left);
                }
            }
            if (node.right != null) {
                if (node.right.key < closest && node.right.key > rootValue) {
                    closest = node.right.key;
                    InorderSuccessor(node.right);
                }
            }

            System.out.println("closest " + closest);
        }
        return closest;
    }

    TreeNode InorderSuccessor1(TreeNode node) {
        TreeNode returnable = null;
        if (node.right != null) {
            if (node.right.left != null) {
                returnable = findLargest(node.right.left);
            } else
                returnable = node.right;
        }
        System.out.println("returnable address " + returnable);
        System.out.println("returnable key " + returnable.key);
        return returnable;
    }

    TreeNode findLargest(TreeNode node) {
        int largest = -1;
        TreeNode largestNode = null;
        if (node.key > largest)
            largestNode = node;
        if (node.left != null)
            if (node.left.key > largest)
                findLargest(node.left);
        if (node.right != null)
            if (node.right.key > largest)
                findLargest(node.right);
        return largestNode;
    }


    void levelLast(TreeNode node) {
        java.util.Stack<TreeNode> stackLevelLast = new java.util.Stack<TreeNode>();
        /**
         * Queue is abstract; cannot be instantiated always use linkedlist, queue is an
         * interface can't be instantiated java.util.Queue<TreeNode> queLevelLast = new
         * java.util.LinkedList<TreeNode>();
         */
        java.util.Queue<TreeNode> queLevelLast = new java.util.LinkedList<TreeNode>();

        int level = 1;
        // stackLevelLast.push(node);
        queLevelLast.add(node);
        while (!queLevelLast.isEmpty()) {
            // TreeNode x = stackLevelLast.pop();
            TreeNode x = queLevelLast.remove();
            System.out.println("key " + x.key);
            // System.out.println("level "+level);
            if (level % 2 == 0) {
                if (x.left != null) {
                    queLevelLast.add(x.left);
                }
                if (x.right != null) {
                    queLevelLast.add(x.right);
                }
            } else {
                if (x.right != null) {
                    stackLevelLast.add(x.right);
                }
                if (x.left != null) {
                    stackLevelLast.add(x.left);
                }

                if (!stackLevelLast.isEmpty()) {
                    while (!stackLevelLast.isEmpty()) {
                        TreeNode y = stackLevelLast.pop();
                        queLevelLast.add(y);
                    }
                }
            }

            level++;
        }
    }

    // void levelLastRecursion(TreeNode node, int levelStart){
    // Queue<TreeNode> queLevelLastRecursion = new java.util.LinkedList<TreeNode>();
    // java.util.Stack stacklevelLastRecusrion = new java.util.Stack<TreeNode>();

    // int level = levelStart;

    // // while(!queLevelLastRecursion.isEmpty()){
    // TreeNode x = queLevelLastRecursion.remove();
    // System.out.println("key "+ x.key + " level "+level);

    // // if(level%2==0){
    // level++;
    // if(x.left!=null) {
    // queLevelLastRecursion.add(node);
    // levelLastRecursion(x.left, level);
    // }
    // if(x.right!=null) {
    // queLevelLastRecursion.add(node);
    // levelLastRecursion(x.right, level);
    // }
    // // }
    // // else {
    // // level++;
    // // if(x.left!=null) levelLastRecursion(x.left, level);
    // // if(x.right!=null) levelLastRecursion(x.right, level);
    // // }
    // // }

    // }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    // https://practice.geeksforgeeks.org/problems/find-k-th-smallest-element-in-bst/1
    /**
     * techniques tail or head or middle recursion using a class variable, func
     * variable gets initialized to 0 everytime still dont know how to use return as
     * in the question placement of the statement MATTERS A LOT.
     */
    int countkth = 0;

    void kthSmallest(TreeNode node, int k) {

        if (node.left != null)
            kthSmallest(node.left, k);
        this.countkth++;
        // System.out.println("count "+this.countkth);
        // System.out.println(node.key);
        if (this.countkth == k)
            System.out.println("key is " + node.key);

        if (node.right != null)
            kthSmallest(node.right, k);

    }

    int sum = 0;

    int sumRootToLeaf(TreeNode node, int root) {
        int left = 0;
        int right = 0;
        if (node.left != null && node.right != null) {
            left = sumRootToLeaf(node.left, (node.key + root) * 10);
            right = sumRootToLeaf(node.right, (node.key + root) * 10);
            return left + right;
        } else
            return (node.key + root);
    }

    /**
     * inorder to achieve zig zag level, we need level, then we need to keep track
     * of dirn
     */
    boolean booleanEvenLevel = true;

    void zigzagTraversal(TreeNode node) {

        int h = height(node);

        for (int i = 0; i < h; i++) {
            printLevelOrderInside(node, i);
            booleanEvenLevel = !booleanEvenLevel;

        }

        // System.out.println("size "+zigzagQueue.size());
        // zigzagQueue.add(limiterNode);
    }

    void printLevelOrderInside(TreeNode node, int level) {
        if (level == 0)
            System.out.println(node.key);
        else {
            if (booleanEvenLevel) {
                if (node.left != null)
                    printLevelOrderInside(node.left, level - 1);
                if (node.right != null)
                    printLevelOrderInside(node.right, level - 1);
            } else {
                if (node.right != null)
                    printLevelOrderInside(node.right, level - 1);
                if (node.left != null)
                    printLevelOrderInside(node.left, level - 1);
            }
        }
    }

    void levelTraversal(TreeNode node) {
        Queue<TreeNode> levelQueue2 = new LinkedList<TreeNode>();
        levelQueue2.add(node);

        while (!levelQueue2.isEmpty()) {
            int size = levelQueue2.size();

            for (int i = 0; i < size; i++) {
                TreeNode popped = levelQueue2.remove();
                System.out.println(popped.key);
                if (popped.left != null)
                    levelQueue2.add(popped.left);
                if (popped.right != null)
                    levelQueue2.add(popped.right);
            }
            System.out.println("new line ");
        }
    }

    void mirrorTreeCheck(TreeNode node1, TreeNode node2) {
        // https://www.geeksforgeeks.org/check-if-two-trees-are-mirror/

    }

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

    TreeNode treeToLinkedList(TreeNode node) {
        TreeNode track = null;
        TreeNode left = null;
        TreeNode right = null;
        if (node.right != null)
            right = treeToLinkedList(node.right);
        if (node.left != null)
            left = treeToLinkedList(node.left);
        if (left != null)
            track = left;
        while (track.right != null && track != null)
            track = track.right;
        if (right != null && track != null)
            track.right = right;
        System.out.println("node " + node.key);
        if (track != null)
            node.right = track;
        return node;
    }

    // void inorderIterative(TreeNode root) {
    // Stack<TreeNode> stackInorderIterative = new Stack<TreeNode>();
    // TreeNode track = null;
    // TreeNode current = root;
    // int count = 12;
    // stackInorderIterative.push(current);
    // // while(!stackInorderIterative.isEmpty()){
    // while (true) {
    // while (current != null) {
    // stackInorderIterative.push(current);
    // current = current.left;
    // }
    // TreeNode poppedItem = stackInorderIterative.pop();
    // System.out.println(poppedItem.key);
    // // track = stackInorderIterative.pop();
    // // System.out.println(track.key);
    // // current = track;
    // if (poppedItem.right != null) {
    // current = poppedItem.right;
    // // System.out.println("right "+ current.key);
    // }
    // // if (track.key == stackInorderIterative.peek().key)
    // // stackInorderIterative.pop();
    // // if(stackInorderIterative.peek() == root) count--;
    // if (stackInorderIterative.isEmpty())
    // return;

    // }

    // }

    /*
     * Given a binary tree, print out all of its root-to-leaf paths, one per line.
     * Uses a recursive helper to do the work.
     */
    void printPaths(TreeNode node) {
        int path[] = new int[1000];
        printPathsRecur(node, path, 0);
    }

    /*
     * Recursive helper function -- given a node, and an array containing the path
     * from the root node up to but not including this node, print out all the
     * root-leaf paths.
     */
    void printPathsRecur(TreeNode node, int path[], int pathLen) {
        if (node == null)
            return;

        /* append this node to the path array */
        path[pathLen] = node.key;
        pathLen++;

        /* it's a leaf, so print the path that led to here */
        if (node.left == null && node.right == null)
            printArray(path, pathLen);
        else {
            /* otherwise try both subtrees */
            printPathsRecur(node.left, path, pathLen);
            printPathsRecur(node.right, path, pathLen);
        }
    }

    /* Utility function that prints out an array on a line. */
    void printArray(int ints[], int len) {
        int i;
        for (i = 0; i < len; i++) {
            System.out.print(ints[i] + " ");
        }
        System.out.println("");
    }

    //////////////////////////////////////////////////////////////////////////

    int currentMax = 0;

    void maxSumPath(TreeNode node) {
        int[] path = new int[30];
        findPaths(node, path, 0);
        System.out.println("max " + currentMax);
    }

    void findPaths(TreeNode node, int[] array, int index) {
        array[index] = node.key;
        index++;
        if (node.left == null && node.right == null) {
            findSum(array, index);
        } else {
            if (node.left != null)
                findPaths(node.left, array, index);
            if (node.right != null)
                findPaths(node.right, array, index);
        }
    }

    void findSum(int[] arr, int index) {
        int sum = 0;
        for (int i = 0; i <= index; i++) {
            sum += arr[i];
        }
        System.out.println("sum" + sum);
        if (sum > currentMax)
            currentMax = sum;
    }

    ////////////////////////////////////////////////////////////////////////////
    void findLowestAncestor(TreeNode node1, TreeNode node2, TreeNode root) {
        int index = 0;
        findPath(root, node1.key, index);
        // printArray(arr1, arr1.length);
        // int[] arr2 = findPath(root, node2.key, index);
        // findLastCommon(arr1, arr2);
    }

    int[] path1 = new int[20];

    void findPath(TreeNode node, int data, int index) {
        path1[index] = node.key;
        System.out.println(path1[index]);
        index++;
        if (node.key == data) {
            return;
        } else {
            if (node.left != null)
                findPath(node.left, data, index);// (node.left, data, ++index);
            if (node.right != null)
                findPath(node.right, data, index);
        }
        // return path1;
    }

    // CHECK GRAPH DFS 
    void printAllPathsUsingList(TreeNode node) {
        List<List<Integer>> parentList = new ArrayList<>();
        List<Integer> currenttList = new ArrayList<>();

        dfs(node, parentList, currenttList);
        System.out.println("paths using List<List<Integer>> --> " + parentList.size());
        System.out.println(parentList);

    }

    void dfs(TreeNode node, List<List<Integer>> parentList, List<Integer> currentList) {
        // if(node!=null){
        if(node == null) return;
        if (node.left == null && node.right == null) {
            // parentList.add(new ArrayList<>(currentList));
            currentList.add(node.key);
            parentList.add(new ArrayList<>(currentList));
            return;
        }
        currentList.add(node.key);
        dfs(node.left, parentList, currentList);
        currentList.remove(currentList.size() - 1);
        dfs(node.right, parentList, currentList);
        currentList.remove(currentList.size() - 1);
        // }
    }

    // https://stackoverflow.com/questions/20750746/how-to-do-a-for-loop-over-a-nested-arraylist
    void printAllLists(List<List<Integer>> list) {
        for (List<Integer> outerlist : list) {
            for (Integer innerlist : outerlist) {
                System.out.println(innerlist);
            }
            System.out.println("new one");
        }
    }
    // TreeNode treeToLinkedList(TreeNode node, TreeNode nodeparent) {
    // if (node.key != nodeparent.key) {
    // TreeNode track = null; TreeNode track2 = null;
    // // TreeNode right = null;
    // // while(track.right!=null) track = track.right;
    // // track = track.right;
    // if (nodeparent.right != null) nodeparent.right = treeToLinkedList(node.right,
    // node);
    // // while(track.right!=null) track =track.right;
    // if (nodeparent.left != null) {
    // track = treeToLinkedList(node.left, node);
    // track2 = nodeparent.right;

    // System.out.println("track "+track.key);
    // nodeparent.right = track;
    // while(track.right!=null) {track = track.right;}
    // track.right = track2;
    // }
    // return nodeparent;
    // }
    // return node;
    // }

    void convertAndPrint(TreeNode node, LinkedListNode head) {
        treetoDLL(node);
        printList(head);
    }

    /**
     * tree to linkd list, ensure you change the same tree, not a new tree or list
     */
    LinkedList<LinkedListNode> list = new LinkedList<LinkedListNode>();
    LinkedListNode head = null;
    LinkedListNode current = null;

    void treetoDLL(TreeNode node) {
        if (node != null) {
            treetoDLL(node.left);
            // if(node.left==null && node.right == null){
            LinkedListNode newLinkedListNode = new LinkedListNode(node.key);
            list.add(newLinkedListNode);
            System.out.println("node " + node.key);
            if (head == null) {
                head = newLinkedListNode;
                current = head;
            } else {
                current = current.next;
                current = newLinkedListNode;
            }
            System.out.println(" current " + current.data);
            // }
            treetoDLL(node.right);
        }

    }

    void printList(LinkedListNode node) {

        LinkedListNode current = node;
        while (current.next != null) {
            System.out.println("in here");
            System.out.println(current.data + "->");
            current = current.next;
        }
    }

    ////////////////////////////////////////////////////

    // flatten a binary tree to linked list

    void flatten(TreeNode node) {
        TreeNode currentRight = null;
        TreeNode currentRightButOne = null;
        TreeNode current = null;
        if (node.right != null)
            flatten(node.right);
        if (node.left != null)
            flatten(node.left);
        if (node.left != null) {
            current = node.left;
            currentRightButOne = node.left;
            if (node.right != null) {
                currentRight = node.right;
                node.right = node.left;
                while (current.right != null) {
                    currentRightButOne = current;
                    current = current.right;
                }
                current.right = currentRight;
                System.out.println("currentRightButOne " + currentRightButOne.key);
            }
            node.right = node.left;
            System.out.println(current.key);
        }
    }

    void printList(TreeNode node) {
        TreeNode current = node;
        while (current != null) {
            System.out.println(current.key + "->");
            current = current.right;
        }
    }

    void findMirrorNode(TreeNode node1, TreeNode node2, int data) {
        if(node1==null) return;
        if(node2==null) return;

        if (node1.key == data) System.out.println("the mirror is " + node2.key);
        if (node2.key == data) System.out.println("the mirror is " + node1.key);

        findMirrorNode(node1.left, node2.right, data);
        findMirrorNode(node1.right, node2.left, data);
    }

    HashMap<Integer, Integer> leftHashMap = new HashMap<Integer, Integer>();

    void leftViewOfTree(TreeNode node, int depth) {
        if (!leftHashMap.containsKey(depth)) {
            leftHashMap.put(depth, node.key);
            depth++;
            if (node.left != null)
                leftViewOfTree(node.left, depth);
            if (node.right != null)
                leftViewOfTree(node.right, depth);
        } else {
            depth++;
            if (node.left != null)
                leftViewOfTree(node.left, depth);
            if (node.right != null)
                leftViewOfTree(node.right, depth);
        }

        /**
         * using ++depth causes the depth variable value to increase in function call
         */
    }

    // void printHashMap() {
    //     Iterator hmIterator = this.leftHashMap.entrySet().iterator();

    //     while (hmIterator.hasNext()) {
    //         HashMap.Entry mapElement = (HashMap.Entry) hmIterator.next();
    //         System.out.println(mapElement.getKey() + " : " + mapElement.getValue());
    //     }
    // }

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

    void spiralTraversal(TreeNode node) {
        int height = height(node);
        boolean ltor = false;
        // System.out.println(height);
        for (int i = 0; i < height; i++) {
            if (ltor)
                spiralHelper(node, i, ltor);
            if (!ltor)
                spiralHelper(node, height - i, ltor);
            System.out.println();
            ltor = !ltor;
        }

    }
    void spiralHelper(TreeNode node, int count, boolean direction) {
        if (count == 0)
            System.out.print(node.key + ", ");
        else if (direction) {
            if (node.left != null)
                spiralHelper(node.left, count - 1, direction);
            if (node.right != null)
                spiralHelper(node.right, count - 1, direction);
        } else if (!direction) {
            if (node.right != null)
                spiralHelper(node.right, count - 1, direction);
            if (node.left != null)
                spiralHelper(node.left, count - 1, direction);
        }
    }

    void boundaryTraversal21Apr(TreeNode node) {
        System.out.println(node.key);
        if (node.left != null)
            printLeftBoundary(node.left);
        System.out.println();
        if (node.left != null)
            printLeftLeaf(node.left);
        System.out.println();
        if (node.right != null)
            printRightLeaf(node.right);
        System.out.println();
        if (node.right != null)
            printRightBoundary(node.right);
    }

    void printLeftBoundary(TreeNode node) {
        if (node.left != null && node.right != null)
            System.out.print(node.key + ", ");
        if (node.left != null)
            printLeftBoundary(node.left);
        else if (node.right != null)
            printLeftBoundary(node.right);
    }

    void printLeftLeaf(TreeNode node) {
        if (node.left == null && node.right == null)
            System.out.print(node.key + ", ");
        if (node.left != null)
            printLeftLeaf(node.left);
        if (node.right != null)
            printLeftLeaf(node.right);
    }

    void printRightLeaf(TreeNode node) {
        if (node.left == null && node.right == null)
            System.out.print(node.key + ", ");
        if (node.left != null)
            printRightLeaf(node.left);
        if (node.right != null)
            printRightLeaf(node.right);
    }

    void printRightBoundary(TreeNode node) {
        if (node.right != null)
            printRightBoundary(node.right);
        else if (node.left != null)
            printRightBoundary(node.left);
        if (node.left != null && node.right != null)
            System.out.print(node.key + ", ");

    }

    // void addGreaterBST(TreeNode node){
    // addGr;
    // }
    int counter = 0;
    int desiredK = 0;

    void kthLargestElementBST(TreeNode node, int k) {
        desiredK = k;
        kthLargestElementBSTHelper(node);
    }

    void kthLargestElementBSTHelper(TreeNode node) {
        if (node.left != null)
            kthLargestElementBSTHelper(node.left);
        this.counter++;
        if (counter == desiredK)
            System.out.println(node.key);
        if (node.right != null)
            kthLargestElementBSTHelper(node.right);
    }

    /**
     * List <List <Integer>> wasn't working so created ArrayList<Arraylist<Integer>>
     * 
     */

     // https://leetcode.com/problems/path-sum/
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right== null && sum-root.val ==0) return true;

        return hasPathSum(root.left, sum- root.val) || hasPathSum(root.right, sum- root.val);
    }



    public ArrayList<ArrayList<Integer>> hasPathSumList(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();

        hasPathSumHelper(root, sum, new ArrayList<Integer>(), paths);

        printArrayListofArrayLists(paths);
        return paths;
    }

    void hasPathSumHelper(TreeNode node, int sum, ArrayList<Integer> current, ArrayList<ArrayList<Integer>> paths) {
        if (node == null) {
            return;
        }

        if (node.key == sum && node.left == null && node.right == null) {
            paths.add(current);
        }

        current.add(node.key);
        hasPathSumHelper(node.left, sum - node.key, new ArrayList<Integer>(current), paths);
        hasPathSumHelper(node.right, sum - node.key, new ArrayList<Integer>(current), paths);
    }

    void printArrayListofArrayLists(ArrayList<ArrayList<Integer>> lists) {
        System.out.println("in here" + lists);
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i));
            for (int j = 0; j < lists.get(i).size(); j++) {
                System.out.println(lists.get(i).get(j));
            }
        }
    }

    // void printAncestorsUsingStack(TreeNode node, int key){
    // Stack<Integer> stack3May = new Stack<>();
    // // Deque<Integer> stack3May = new ArrayDeque<Integer>();
    // stack3May.push(node.key);
    // printAncestorsUsingStackHelper(node, key, stack3May);
    // }

    // void printAncestorsUsingStackHelper(TreeNode node, int key, Stack stack){
    // if(node.key == key) {
    // while(!stack.empty()){
    // System.out.print(stack.pop()+", ");
    // }
    // }
    // if(node.left!=null){
    // stack.push(node.left.key);
    // printAncestorsUsingStackHelper(node.left, key, stack);
    // }
    // if(node.right!=null) {
    // stack.push(node.right.key);
    // printAncestorsUsingStackHelper(node.right, key, stack);
    // }

    // }

    void connectNodesAtSameLevel(TreeNode node) {
        Queue<TreeNode> connectNodesQueue = new LinkedList<>();

        connectNodesQueue.add(node);
        while (!connectNodesQueue.isEmpty()) {
            int size = connectNodesQueue.size();
            TreeNode prev = connectNodesQueue.peek();

            for (int i = 0; i < size; i++) {
                TreeNode x = connectNodesQueue.remove();
                if (x.left != null)
                    connectNodesQueue.add(x.left);
                if (x.right != null)
                    connectNodesQueue.add(x.right);
                prev.rightpointer = connectNodesQueue.peek();
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

    void iterativePostorder(TreeNode node) {
        Deque<TreeNode> stackPostorder = new LinkedList<>();
        System.out.println("in here");
        TreeNode left = null;
        TreeNode right = null;
        if (node != null)
            stackPostorder.push(node);

        while (!stackPostorder.isEmpty()) {

            if (node.left != null)
                left = node.left;

            while (left != null) {
                stackPostorder.push(left);
                left = left.left;
                // System.out.println("left "+left.key);
            }
            System.out.println("line 1656 " + stackPostorder.peek().key);

            while ((stackPostorder.peek()).right == null) {
                TreeNode x = stackPostorder.removeFirst();
                System.out.print(x.key + ", ");
                System.out.println("line 1661 " + stackPostorder.peek().key);
            }
            right = stackPostorder.peek().right;
            if (right.right != null)
                left = right.left;
            if (right.right != null) {

                right = right.right;
            }
        }

    }

    // TreeNode parent = null; int level = 0;
    // boolean areCousins(TreeNode root, TreeNode a, TreeNode b, int level, TreeNode
    // parent){
    // // dfs(a, parent, 0);

    // }

    static void diagonalPrintUtil(TreeNode root, int d, HashMap<Integer, Vector<Integer>> diagonalPrint, int count) {

        // Base case
        if (root == null)
            return;

        System.out.println(count);
        if (count == 0) {
            System.out.println("the key is " + root.key);
        }

        // get the list at the particular d value
        Vector<Integer> k = diagonalPrint.get(d);

        // k is null then create a vector and store the data
        if (k == null) {
            k = new Vector<>();
            k.add(root.key);
        }

        // k is not null then update the list
        else {
            k.add(root.key);
        }

        // Store all nodes of same line together as a vector
        diagonalPrint.put(d, k);
        // count--;
        // Increase the vertical distance if left child
        diagonalPrintUtil(root.left, d + 1, diagonalPrint, --count);

        // Vertical distance remains same for right child
        diagonalPrintUtil(root.right, d, diagonalPrint, --count);
    }

    // Print diagonal traversal of given binary tree
    static void diagonalPrint(TreeNode root, int k) {
        // create a map of vectors to store Diagonal elements
        HashMap<Integer, Vector<Integer>> diagonalPrint = new HashMap<>();
        diagonalPrintUtil(root, 0, diagonalPrint, k);

        System.out.println("Diagonal Traversal of Binary Tree");
        // for (Entry<Integer, Vector<Integer>> entry : diagonalPrint.entrySet()) {
            // System.out.println(entry.getValue());
        }
// }

    void findCousins(TreeNode root, TreeNode node) {
        int level = findLevel(root, node, 0);
        System.out.println("level " + level);
        // int height = heightCorona(root) - 1;
        // System.out.println("height " + height);

        // for (int i = 0; i <= height; i++) {
            printLevelHelper(root, node, level);
        // }
    }

    int findLevel(TreeNode root, TreeNode node, int level) {
        int l1 = -1;
        int l2 = -1;
        // if(node== null) return -1;
        if (root == node) {
            System.out.println("found");
            return level;
        }
        level++;
        if (root.left != null) {
            l1 = findLevel(root.left, node, level);
        }
        if (root.right != null) {
            l2 = findLevel(root.right, node, level);
        }
        return Math.max(l1, l2);
    }

    // void printLevelHelper(TreeNode root, TreeNode node, int level, int desiredLevel) {
    void printLevelHelper(TreeNode root, TreeNode node, int level) {
        if(root == node){
            return;
        }
        if(level == 0) System.out.println(root.key);
        level--;
        if(root.left!=null) printLevelHelper(root.left, node, level);
        if(root.right!=null) printLevelHelper(root.right, node, level);

    }


    void findCousinsQueue(TreeNode root, TreeNode node){

        // int level = findLevel(root, node, 0);

        Deque<TreeNode> queue = new LinkedList<>();
        boolean thisLevel = false;
        boolean nullStart = false;
        boolean nullEnd = false;

        queue.add(root); queue.add(null);
        while(!queue.isEmpty()){
            TreeNode current = queue.remove();
            if(current == null){
                if(queue.isEmpty()) return;
                if(nullStart) nullEnd = true;
                if(thisLevel) nullStart =true;
                queue.add(null);
            } else{
                if(nullStart && !nullEnd && current != node) {
                    System.out.println(current.key + ", ");
                }
                else {
                    if (current.left!=null){
                        queue.add(current.left);
                        if(current.left == node){
                            thisLevel = true;
                        }
                    }
                }
                 
                if(current.right!=null) {
                    if(current.right!=null ){
                        queue.add(current.right);
                        if(current.right == node){
                            thisLevel = true;
                        }    
                    }
                }
            }
        }

    }

    void printPath(TreeNode root, TreeNode dest){
        ArrayList<Integer> path = new ArrayList<>();
        printPathHelper(root, dest, path);
        System.out.println(path);
    }
    boolean printPathHelper(TreeNode root, TreeNode dest, ArrayList<Integer> path){
        if(root == null) return false;
        if(root == dest
        ||printPathHelper(root.left, dest, path)
        ||printPathHelper(root.right, dest, path)){
            path.add(root.key);
            System.out.println(root.key);
            return true;
        }
        return false;
    }

    void printPathToLeafPathList(TreeNode node){
        Deque<Integer> path = new LinkedList<>();
        printPathToLeafPathListHelper(node, path);
    }

    void printPathToLeafPathListHelper(TreeNode node, Deque<Integer> path){
        if(node == null) return;

        path.addLast(node.key);
        
        if(node.left == null  && node.right == null){
            System.out.println(path);
        }

        printPathToLeafPathListHelper(node.left, path);
        printPathToLeafPathListHelper(node.right, path);

        path.removeLast();
    }

    void sumOfAllLeaves(TreeNode node){

        int sum = sumOfAllLeavesHelper(node);
        System.out.println("sum "+sum);
    }

    int sumOfAllLeavesHelper(TreeNode node){
        int res = 0; 
        if (node != null){

            if (isLeaf(node)) 
                res += node.key; 
            else res += sumOfAllLeavesHelper(node.left) + sumOfAllLeavesHelper(node.right); 

        } 
        return res; 
    }

    boolean isLeaf(TreeNode node){
        if(node== null) return false;
        if(node.left== null && node.right == null) return true;
        return false;
    }

    void sumOfAllNodes(TreeNode node){

        int sum = sumOfAllNodesHelper(node);
        System.out.println("sum "+sum);
    }

    int sumOfAllNodesHelper(TreeNode node){
        if(node == null) return 0;

        return node.key + sumOfAllNodesHelper(node.left) + sumOfAllNodesHelper(node.right);

    }


    void deleteLeafNodesBST(TreeNode parent, TreeNode node){
        if(parent==null|| node == null) return;
        if(node.left == null && node.right == null) {
            // node = null;
            if(parent.left == node) parent.left =null;
            if(parent.right == node) parent.right =null;
            // return;
        }
        deleteLeafNodesBST(node, node.left);
        deleteLeafNodesBST(node, node.right);
    }

    TreeNode currentholderForN = null;
    void findLargestLessThanOrEqualToN(TreeNode root, int N){
        currentholderForN =  root;
        findNHelper(root, N, 10000000);
        System.out.println("found "+currentholderForN.key);
    }

    void findNHelper(TreeNode root, int val, int diff){
        int difference = diff; 
        if(root == null) return;

        if((val - root.key)==0) {
            currentholderForN = root; 
            return;
        }
        if(root.key<val) {
            if((val - root.key) < diff) {
                currentholderForN = root;
                difference = (val - root.key);
            }
            findNHelper(root.right, val, difference);
        }
        else if(root.key>val){
            currentholderForN = root;
            findNHelper(root.left, val, diff);
        } 
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

    void checkLevelAnagram(TreeNode node1, TreeNode node2){
    //https://www.geeksforgeeks.org/check-if-all-levels-of-two-trees-are-anagrams-or-not

        ArrayList<TreeNode> listAnagram1 =  new ArrayList<>();
        ArrayList<TreeNode> listAnagram2 =  new ArrayList<>();

        listAnagram1.add(node1); listAnagram1.add(null);
        listAnagram2.add(node2); listAnagram2.add(null);
        levelAnagram(node2, listAnagram2);
        levelAnagram(node1, listAnagram1);
        

        System.out.println("line 1990 "+listAnagram1.size());
        System.out.println("line 1991 "+listAnagram2.size());

        int start = 0; int end =0; 
        for (int i =0; i<listAnagram1.size(); i++){
            TreeNode current = listAnagram1.get(i);
            if(current == null) {
                start = i+1;
            }
            while(listAnagram1.get(i+1)!=null){
                i++;
            }
            end = i;
            System.out.println("start " +listAnagram1.get(start).key +" index "+start);
            System.out.println("end "+listAnagram1.get(end).key +" index "+end);
            System.out.println("the answer is "+ checkAnagram(listAnagram1, listAnagram2, start, end));
        }
    }

    void levelAnagram(TreeNode node, ArrayList<TreeNode> list){
        int i = 0;//imp
        while(true){
            if (list.get(i) == null) {
                if(i+1 == list.size()){//imp
                    break;
                } 
                list.add(null);
            }
            else{
                TreeNode current = list.get(i);
                if(current.left!=null) list.add(current.left);
                if(current.right!=null) list.add(current.right);
            }
            i++;
        }
    }
    
    boolean checkAnagram(ArrayList<TreeNode> list1, ArrayList<TreeNode> list2, int start, int end){
        System.out.println(list2.size());
        HashMap<Integer, Integer> hashlevelAnagram = new HashMap<Integer, Integer>();
        for( int i=start; i<=end; i++ ){
            hashlevelAnagram.put(list1.get(i).key, i);//imp
        }

        for(int j = start; j<=end; j++){
            if(list2.get(start) != null){
                int value = hashlevelAnagram.get(list2.get(j).key);
                System.out.println("value " + value);
                if(value == -1) return false;
            }
        }
        hashlevelAnagram.clear();
        // System.out.println("size now "+hashlevelAnagram.size());
        return true;
    }

    ////////////////////////////////////////////////
    void findRootToLeafPathsWithGivenSum6Jun(TreeNode root, int sum){
        int[] paths = new int[6];        
        if(root == null) return;
        findRootToLeafPathsWithGivenSumHelper(root, sum , paths, 0);
    }

    void findRootToLeafPathsWithGivenSumHelper(TreeNode root, int sum, int[] paths, int index){
        
        if(sum == root.key) {
            System.out.println("in here");
            paths[index] = root.key;
           
            for (int i = 0; i < paths.length; i++) {
                System.out.print(paths[i]+", ");
            }
            return;
        }
        paths[index] = root.key;
        index++;
        if(root.left!=null) findRootToLeafPathsWithGivenSumHelper(root.left, sum - root.key, paths, index);
        if(root.right!=null) findRootToLeafPathsWithGivenSumHelper(root.right, sum - root.key, paths, index);
    }

    ////////////////////////////////////////////////////////
    void printListOfLists(ArrayList<ArrayList<Integer>> subsets){
        for (ArrayList<Integer> subset: subsets) {
            // System.out.println("in print, size " + subset.size());
            System.out.println(subset);
        }
    }
    /////////////////////////////////////////////////////////


    //////////////////////////////
    // List<List<Integer>> findRootToLeafPathsWithGivenSumLists6Jun(TreeNode root, int sum){
    //     List<List<Integer>> lists = new ArrayList<>();
    //     if(root == null) return null;
    //     findRootToLeafPathsWithGivenSumListsHelper(root, sum , lists, new ArrayList<Integer>());

    //     printListOfLists(lists);

    //     return lists;
    // }

    void findRootToLeafPathsWithGivenSumListsHelper(TreeNode root, int sum, 
    ArrayList<ArrayList<Integer>> lists, ArrayList<Integer> list){
        if(sum == root.key) {
            System.out.println("in here");
            list.add(root.key);
            // List<Integer> curList = new ArrayList<>(list);
            lists.add(list);
           
            // for (int i = 0; i < paths.length; i++) {
            //     System.out.print(paths[i]+", ");
            // }
            return;
        }
        list.add(root.key);
        if(root.left!=null) findRootToLeafPathsWithGivenSumListsHelper(root.left, sum - root.key, lists, new ArrayList<>(list));
        if(root.right!=null) findRootToLeafPathsWithGivenSumListsHelper(root.right, sum - root.key, lists, new ArrayList<>(list));
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

    //not the correct solution, some changes are needed

    int sumBST = 0;
    void addGreaterBST(TreeNode root2) {
        if (root2 == null)
            return;
        addGreaterBST(root2.right);
        root2.key += sum;
        sum = root2.key;
        addGreaterBST(root2.left);

    }

    TreeNode treeFromInorder(int[] arr, int start, int end){
        if(start>end) return null;
        int mid = (start+end)/2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = treeFromInorder(arr, start, mid-1);
        root.right = treeFromInorder(arr, mid+1, end);
        return root;
    }


    int countPath=0;
    public int sumNumbers(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        f(root, list, 0);
        return countPath;
    }
    
    void f(TreeNode node, ArrayList<Integer> l, int index){
        if(node==null){
            return;
        }
        
        if(node!=null){
            l.add(index, node.key);
            if(node.left == null && node.right == null){
                this.countPath+=sum(l);
                // l.remove(index);
            }else{
                // index ++;
                int newIndex = index+1;
                f(node.left, l, newIndex);
                // l.remove(l.size()-1);
                // index ++;
                f(node.right, l, newIndex);
            }
            l.remove(index);
        }
    }
    
    int sum(ArrayList<Integer> list){
        int sum =0;
        for(int i =0; i<list.size(); i++){
            sum+= list.get(i)*Math.pow(10, list.size()-i-1);
        }
        System.out.println(sum);
        return sum;
    }

    void spiralPrint(TreeNode root, int level){
        int h = height(root);
        Deque<TreeNode> list = new LinkedList<>();
        list.add(root); 
        boolean ltor = true;
        //adding null delimiter
        spiralPrintHelper(root, list, level, ltor);
    }


    void spiralPrintHelper(TreeNode node, Deque<TreeNode> list, int level, boolean ltor){
        while(list.size()!=0){
            TreeNode curr = list.removeFirst();
            System.out.println(curr.key+"->");
            count++;
            if(count%2!=0){

            }
            ltor = !ltor;//true then left to right
            if(ltor) {
                if(curr.left!=null) list.add(curr.left);
                if(curr.right!=null) list.add(curr.right);
            } else{
                if(curr.right!=null) list.add(curr.right);
                if(curr.left!=null) list.add(curr.left);
            }
            
        }
    }

    void extremeAlternate(TreeNode root){
        Deque<TreeNode> list = new LinkedList<>();
        ArrayList<Integer> holder = new ArrayList<>();

        list.add(root); list.add(null);
        extremeAlternateHelper(root, list, holder);
        int count = 0;

        for(int i =0; i<holder.size(); i++){
            if(holder.get(i) == -1){
                count++;
                if(count%2 ==1) {
                    System.out.println(holder.get(i-1));
                    if((i+1)<holder.size()) System.out.println(holder.get(i+1));
                    i++;
                }
            }
        }
    }

    void extremeAlternateHelper(TreeNode root, Deque<TreeNode> list, 
    ArrayList<Integer> holder){
        boolean carryOn = true;
        while(carryOn){
            if(list.size()==1 && list.getLast() == null) carryOn = false;
            TreeNode curr = list.removeFirst();
            if(curr==null) {
                list.add(null);
                holder.add(-1);
            }else{
                holder.add(curr.key);
                if(curr.left!=null)list.add(curr.left);
                if(curr.right!=null)list.add(curr.right);
            }
        }
    }

    LinkedList treetoCDLL(TreeNode root){
        LinkedList<Integer> list = new LinkedList<>();
        treetoCDLLHelper(root, list);
        printLinkedList(list);
        return list;
    }

    void treetoCDLLHelper(TreeNode root, LinkedList<Integer> list){
        if(root.left!=null) treetoCDLLHelper(root.left, list);
        list.add(root.key);
        if(root.right!=null) treetoCDLLHelper(root.right, list);
    }


    ////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////
    

    // VIEWS
    // https://leetcode.com/problems/binary-tree-right-side-view/
    /** WHAT IF THE LEFT SUBTREE IS LONGER?
     * KEEP TRACK OF LENGTH TOO
     */

    //////////////TRAVERSALS
    // https://leetcode.com/problems/binary-tree-inorder-traversal/
    /** POINTS :
     * 1 TWO WHILE LOOPS
     * 2 STACK IS NOT EMPTY AND ROOT !=NULL
     * 3 WHILE ROOT!=NULL, PUSH, ROOT = ROOT.LEFT
     * 4 WHEN NULL, POP, ADD TO RESULT AND ROOT = ROOT.RIGHT
    */
    public List<Integer> inorderTraversal(TreeNode root) {
        Deque<TreeNode> q = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null) return list;
        
        while(q.size()!=0 || root!=null){
            while(root!=null){
                q.addLast(root);
                root = root.left;
            }
            root = q.removeLast();
            list.add(root.val);
            root = root.right;
            
        }
        return list;
        
    }
    // https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
    /** used a Queue, added null for demarcation
     * 1 add null whenever curr == null and q size is >1 and removeFirst
     * if size is 1 (only null iis present)
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
    
    public RightNode connectRight(RightNode root) {
        if(root == null) return null;
        Deque<RightNode> q = new LinkedList<>();
        root.next = null;
        q.addLast(root); q.addLast(null);
        while(q.size()!=0){
            RightNode curr = q.removeFirst();
            if(curr == null && q.size() >1 ) q.addLast(null);
            else if(curr == null && q.size() ==1 ) q.removeFirst();
            else if(curr!=null){
                curr.next = q.getFirst();
                if(curr.left!=null) q.addLast(curr.left);
                if(curr.right!=null) q.addLast(curr.right);    
            }
        }
        
        return root;
        
    }

    // https://leetcode.com/problems/binary-tree-inorder-traversal/
    // https://leetcode.com/problems/balance-a-binary-search-tree/


    // https://leetcode.com/problems/most-frequent-subtree-sum/
    int sumFrequent;
    HashMap<Integer, Integer> listFrequent = new HashMap<>();
    
    public int[] findFrequentTreeSum(TreeNode root) {

        postOrder(root);
        int max = 0;
        for (Integer value : listFrequent.values()) {
            // ...
            if(max<value) max = value;
        }
        for (Integer value : listFrequent.values()) {
            if(value == max) count++;
        }

        int[] res= new int[count]; int i=0;
        
        for (HashMap.Entry<Integer, Integer> entry : listFrequent.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if(value == max) res[i++] = key;
            // ...
        }
        print1DMatrix(res);
        return res;
    }
    
    int postOrder(TreeNode node){
        int left = 0; int right = 0;
        if(node.left == null && node.right ==null) {
            if(listFrequent.containsKey(node.key)) listFrequent.put(node.key, 
                listFrequent.get(node.key)+1);
            else listFrequent.put(node.key, 1);
            return node.key;
        } 
        else{
            if(node.left!=null) left = postOrder(node.left);
            if(node.right!=null) right = postOrder(node.right);

            int sum = left+right+node.key;
            if(listFrequent.containsKey(sum)) listFrequent.put(sum, 
                    listFrequent.get(sum)+1);
            else listFrequent.put(sum, 1);

            return sum;
        }
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

    // https://leetcode.com/problems/same-tree/
    // SIMPLE PREORDER FOR BOTH SIMULTANEOUSLY
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val == q.val) return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        return false;
    }

    // https://leetcode.com/problems/minimum-distance-between-bst-nodes/
    int min = Integer.MAX_VALUE; 
    ArrayList<Integer> listBst = new ArrayList<>();
    
    public int minDiffInBST(TreeNode root) {
        dfsBST(root, listBst);
        System.out.println(list);
        for (int i=0; i<list.size()-1; i++){
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
    

    // https://leetcode.com/problems/validate-binary-search-tree/
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


    // https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
    /** 
     * PREORDER APPROACH IS NOT EFFICIENT, WORKS ON EVERY NODE TWICE
     *  TAKES 0(n^2) 
     *  PROOF - https://www.youtube.com/watch?v=TSEeSXNfl04
     * 
     * USE POSTORDER O(n)
     * SEE VALIDBST ABOVE
     */

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

    // https://leetcode.com/problems/binary-tree-maximum-path-sum/
    
    /** 

    https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

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

    // https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
  

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

    // https://leetcode.com/problems/balance-a-binary-search-tree/
    /** basically two pointers are needed which will store the start and end indexes
     * and use 3rd style of function root.left = f(left) and root.right  = f(right)
     */
    public TreeNode balanceBST(TreeNode root) {
        ArrayList<Integer> list = new ArrayList();
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

        tree.printAllPathsUsingList(tree.root);
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

        // System.out.println("all leaves");
        // tree.sumOfLeftLeafNodes(tree.root.left);
        // tree.sumOfLeftLeafNodes(tree.root.right);

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
        // tree.kthLargestElementBST(tree.root, 3);
        // tree.hasPathSum(tree.root, 9);
        // tree.printAncestorsUsingStack(tree.root, 1);
    }
}