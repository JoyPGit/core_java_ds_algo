import java.util.*;
import java.util.HashMap;
import java.util.Stack;

class Node {
    int key;
    Node left;
    Node right;

    Node(int d) {
        this.key = d;
        this.left = null;
        this.right = null;
    }
}

class CustomClassForPairSwap {
    int parentKeyValue;
    String whichChild;

    CustomClassForPairSwap(int d, String s) {
        this.parentKeyValue = d;
        this.whichChild = s;
    }

    int returnValue(Node node) {
        return this.parentKeyValue;
    }
}

class Treepractice {
    Node root;
    HashMap<Integer, CustomClassForPairSwap> pairSwapMap = new HashMap<Integer, CustomClassForPairSwap>();

    Treepractice(int d) {
        this.root = new Node(d);
    }

    void rootCheck() {
        System.out.println("rootcheck " + this.root.key);
    }

    void findParent(int value, Node node, Node nodeParent) {
        /**
         * do a traversal and then match the key value of the parent which will be
         * passed as a third parameter
         */
        if (node.key == value) {
            System.out.println("parent is " + nodeParent.key);
        }
        System.out.println("in here");
        if (node.left != null)
            findParent(value, node.left, node);
        if (node.right != null)
            findParent(value, node.right, node);
    }

    void findLeaf(Node node) {
        if (node.left != null)
            findLeaf(node.left);
        if (node.right != null)
            findLeaf(node.right);
        if (node.left == null && node.right == null) {
            System.out.println("leaf key" + node.key);

        }
    }

    // FOCUS ON THIS METHOD
    void insertLeafWithParent(Node node, Node nodeParent, String s) {
        if (node.left != null)
            insertLeafWithParent(node.left, node, "left");
        if (node.right != null)
            insertLeafWithParent(node.right, node, "right");
        if (node.left == null && node.right == null) {
            System.out.println("leaf key" + node.key);
            CustomClassForPairSwap parent = new CustomClassForPairSwap(node.key, s);
            System.out.println("parent " + parent);
            pairSwapMap.put(node.key, parent);
        }
    }

    // ITERATE OVER THE HASHMAP
    void iterateOverHashMap() {
        Iterator hmIterator = pairSwapMap.entrySet().iterator();

        // for (Map.Entry<Integer, CustomClassForPairSwap> entry :
        // pairSwapMap.entrySet()) {
        // System.out.println("leaf " + entry.getKey() + " " + "parent " +
        // entry.getValue().returnValue());
        // // CustomClassForPairSwap v = entry.getKey());
        // // System.out.println("parent entry get mem "+entry.getKey().getMemory());
        // }

        while (hmIterator.hasNext()) {
            HashMap.Entry mapElement = (HashMap.Entry) hmIterator.next();
            CustomClassForPairSwap class1 = (CustomClassForPairSwap) mapElement.getValue();
            System.out.println(mapElement.getKey() + " : " + class1.whichChild);

        }
    }

    // public boolean isNumber(String s) {
    // int lengthOfString = s.length();

    // // CharSequence cs = s.subSequence(0, length);
    // CharSequence e = 'e';
    // CharSequence dot = '.';

    // for(int i =0; i<lengthOfString; i++){
    // if (!s.contains(e)){
    // if(!s.contains(dot)){
    // if(s.charAt(i)=='0'||'1'||'2'||'3'||'4'||'5'||'6'||'7'||'8'||'9'){
    // return true;
    // }
    // }
    // }

    // // else if ()
    // }
    // }

    void pathSum2(int sum) {

    }

    public void iterativePostorder(Node node) {
        Stack<Node> stack = new Stack<Node>();
        stack.push(node);
        stack.push(node.left);
        stack.push(node.right);
        Node current = node;
        while (!stack.isEmpty()) {
            System.out.println("curr " + current.key);
            if (current.left != null) {
                System.out.println("in if");
                stack.push(current.left);
                current = current.left;
            } else if (current.left == null && current.right == null) {
                System.out.println("in else if 1");
                System.out.println(stack.pop().key);
                current = stack.peek();
            } else if (current.right != null) {
                System.out.println("in else if 2");
                stack.push(current.right);
                current = current.right;
            }
            // else if(current.right == null){
            // System.out.println(stack.pop().key);
            // }
        }
    }

    void LCA(Node node, int a, int b) {
        if (a < b) {
            System.out.println("in here " + node.key);
            if (node.key > a && node.key > b) {
                LCA(node.left, a, b);
            }
            if (node.key < a && node.key < b) {
                LCA(node.right, a, b);
            }
            if (node.key > a && node.key < b) {
                System.out.println("LCA is " + node.key);
            }
        }
    }

    void Successor(Node node) {
        System.out.println("successor " + findMin(node.right).key);
    }

    Node findMin(Node node) {
        Node holder = null;
        int min = 100000;
        if (node.left != null && node.left.key < min) {
            min = node.left.key;
            holder = node.left;
            findMin(node.left);
        }
        if (node.right != null && node.right.key < min) {
            min = node.right.key;
            holder = node.right;
            findMin(node.right);
        }
        System.out.println("min " + min);

        return holder;
    }

    /**
     * there are 2 ways to find the parent of a node, pass the parent as a node or
     * check for key == node.left || node.right
     * 
     * Also many times a function calls another recursive function
     */

    LeafCount[] arrayLeaf = new LeafCount[9];

    void findAllLeaves(Node node) {

        int count = 0;
        int i = 0;
        if (node.left != null) {
            count++;
            findAllLeaves(node.left);
        }
        if (node.right != null) {
            count++;
            findAllLeaves(node.right);
        }
        if (node.left == null && node.right == null) {
            LeafCount c = new LeafCount(count, node);
            // arrayLeaf.push(c); doesn't work
            arrayLeaf[i] = c;
            i++;
        }
        // return arrayLeaf;
        // for ( LeafCount x : arrayLeaf ) {
        // System.out.println(x); // s is a Scanner object
        // }
    }

    class LeafCount {
        int count;
        Node node;

        LeafCount(int c, Node n) {
            this.count = c;
            this.node = n;
        }
    }

    void findLengthsOfPathsTillLeaf(Node node) {
        // HashMap<Node, Integer> = New HashMap<Node, Integer>();
        // LeafCount c = findAllLeaves(node);

    }

    void maxPathSum(Node node) {
        // int a = findMax(node.left, node.left.key) + findMax(node.right,
        // node.right.key) + node.key;
        // int a = findMax(node.left) + findMax(node.right) + node.key;
        // System.out.println(findMax(node.left, node.left.key));
        // System.out.println(findMax(node.right, node.right.key));
        // System.out.println("max sum "+ a);
        // return a;//findMax(node.left) + findMax(node.right);
    }

    // int findMax(Node node, int su){
    // int max = -1; int sum = su;
    // // if(node.left!=null){
    // // sum+= node.left.key;
    // // findMax(node.left, sum);
    // // }
    // if(node.right!=null){
    // sum+= node.right.key ;
    // findMax(node.right, sum);
    // } if(node.left == null && node.right == null){
    // if(sum>max) max = sum;
    // System.out.println("in here max "+ max);
    // System.out.println("in here sum "+ sum);
    // }
    // return max;
    // }

    // void findMax(Node node){
    // int max = -1; int sum = node.key;
    // if(node.left!=null){
    // sum= sum + findMax(node.left);
    // }
    // if(node.right == null){
    // sum = sum + findMax(node.right);
    // } if(node.left == null && node.right == null){
    // if(sum>max) {
    // max = sum;
    // System.out.println( max);
    // }
    // // System.out.println("in here max "+ max);
    // // System.out.println("in here sum "+ sum);
    // }

    // }

    ///////////////////////////// 5th feb
    void lowestCommonAncestor(Node root, Node node1, Node node2) {
        int[] path1 = new int[10];
        int[] path2 = new int[10];
        int[] resultPath1 = findPath(path1, root, node1.key, 0);
        for (int i = 0; i < resultPath1.length; i++) {
            System.out.println("resultPath1 " + resultPath1[i]);
        }

        int[] resultPath2 = findPath(path2, root, node2.key, 0);
        for (int i = 0; i < resultPath2.length; i++) {
            System.out.println("resultPath2 " + resultPath2[i]);
        }
        System.out.println("lca is " + findCommon(path1, path2));
    }

    int[] findPath(int[] path, Node root, int key, int index) {
        Node node = root;
        path[index] = node.key;
        index++;// ERROR HERE---CHECK
        if (node.key == key)
            return path;
        else {
            if (node.left != null) {
                findPath(path, node.left, key, index);
            }
            if (node.right != null) {
                findPath(path, node.right, key, index);
            }
        }
        return path;
    }

    int findCommon(int[] arr1, int[] arr2) {
        int maxLength = 0;
        int[] minArray = null;
        int[] maxArray = null;
        int common = -1;
        int common1 = -1;
        if (arr1.length < arr2.length) {
            maxArray = arr2;
            minArray = arr1;
        } else {
            maxArray = arr1;
            minArray = arr2;
        }

        // for(int i =0; i<maxArray.length; i++){
        // common1 = binarySearch(minArray, 0, minArray.length-1, maxArray[i]);
        // if(common < common1 ) common = common1;
        // }

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j] && arr1[i] != 0)
                    common = j;
            }
        }
        return arr2[common];
    }

    void pathSumExists(Node node, int sum) {
        if (node != null) {
            int sumIn = sum;
            if (node.key == sum) {
                System.out.println("true");
            } else {
                sumIn = sumIn - node.key;
                pathSumExists(node.left, sum);
                pathSumExists(node.right, sum);
            }
        }
    }

    public static void main(String[] args) {
        Treepractice treenew = new Treepractice(5);

        treenew.root.left = new Node(2);
        treenew.root.right = new Node(7);
        treenew.root.left.left = new Node(1);
        treenew.root.left.right = new Node(4);
        treenew.root.right.left = new Node(6);
        treenew.root.right.right = new Node(8);

        treenew.lowestCommonAncestor(treenew.root, treenew.root.right.left, treenew.root.right.right);

        // treenew.findParent(7, treenew.root, treenew.root);
        // treenew.findLeaf(treenew.root);
        // treenew.insertLeafWithParent(treenew.root, treenew.root, "S");
        // treenew.iterateOverHashMap();

        // treenew.isNumber("s");

        // treenew.iterativePostorder(treenew.root);
        // treenew.LCA(treenew.root, 6, 8);
        // treenew.Successor(treenew.root);
        // treenew.findAllLeaves(treenew.root);

        // for ( LeafCount x : this.arrayLeaf ) {
        // System.out.println(x); // s is a Scanner object
        // }

        // treenew.maxPathSum(treenew.root);

        // treenew.rootCheck();
        // treenew.pathSumExists(treenew.root, 8);
    }
}
