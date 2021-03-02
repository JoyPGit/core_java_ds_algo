
import java.util.*;

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

/** 
 * A TECHNIQUE IS CALLING THE SAME FUNC RECURSIVELY
 * reverse k, flatten (recursive call)
 * reverse k can be adapted to reverse
 * 
 * rotate right(k%length)
 * 
 * merge (2 lists and merge sort, stop when head.next == null)
 * * find mid, merge and if(head.next == null return)
 *  
 *  
 * merging two lists        
 * while(a!=null && b!=null){
 *     ListNode c = a.next;
 *     ListNode d = b.next;
 *     a.next = b;
 *     b.next = c;
 *     a = c; b = d;
 * }
 * 
 * odd even traversal type(reversal and merging mostly)
 * 
 * duplicates, 
 * 
 * 
 * 
 * POINTS :
 * TRICKS : (USE DUMMY NODE, DIVIDE INTO LISTS AND MERGE)
 * 1 P!= NULL
 * 2 FAST, SLOW PTR TO FIND MID
 * 3 REVERSE LIST
 * 4 CYCLE, JOSEPHUS
 * 5 INTERSECTION POINT
 * 6 UNION, INTERSECTION
 * 7 PALINDROME
 * 8 MERGE LISTS (RECURSIVE, ITERATIVE)
 * 9 PARTITION LIST 
 * 10 ADD TWO NUMBERS
 * 11 REVERSE LIST IN PAIRS (RECURSIVE, ITERATIVE)
 * 12 MERGE SORT ON LIST
 * 
 *  AVOID CYCLE BY SETTING NEXT NULL
 * 
 *  MERGE 2 LIST TEMPLATE ONE FROM EACH ALTERNATE
 *  while(a!=null && b!=null){
        ListNode c = a.next;
        ListNode d = b.next;
        a.next = b;
        b.next = c;
        a = c; b = d;
 *  }
 * 
 *  ELSE USE MERGE SORT AND A DUMMY HEAD
 *  ListNode dummy = new ListNode(0);
    ListNode p = dummy;
    while(l1!=null && l2!=null){
        if(l1.val<=l2.val){
            p.next = l1;
            l1 = l1.next;
        }else{
            p.next = l2;
            l2 = l2.next;
        }
        p = p.next;
    }
    
    if(l1!=null) p.next = l1;
    if(l2!=null) p.next = l2;
    return dummy.next;
 *    
 * .next type see recursive merging
*/

class LinkedPractice {
    ListNode head = null;
    ListNode current = null;

    public void printList(ListNode head){
        ListNode p = head;
        while(p!=null) {
            System.out.print(p.val+"->");
            p = p.next;
        }
    }
    void addNode(int data) {
        if (head == null) {
            head = new ListNode(data);
            head.next = null;
            current = head;
        } else {
            current.next = new ListNode(data);
            current = current.next;
            current.next = null;
        }
    }

    /**
     * 2 WAYS TO FIND MIDDLE
     * 
     * ONE IS FAST != NULL && FAST.NEXT != NULL
     * OTHER IS FAST.NEXT != NULL && FAST.NEXT.NEXT != NULL
     * 
     * THE DIFF IN THE FIRST CASE, SLOW GOES ONE STEP AHEAD OF MIDDLE    
     * IN CASE OF EVEN NO OF NODES
     * 
     * IN SECOND CASE, FAST DOESN'T REACH THE END, BUT 
     * SLOW GOES TILL MIDDLE
     * 
     */
    ListNode middle(ListNode head){
        ListNode fast = head, slow = head;
        while(fast.next!=null && fast.next.next !=null){//1
            fast = fast.next.next; 
            slow = slow.next;
        }
        return slow;
    }

    // SAME AS ABOVE (fast.next != null && fast.next.next != null)
    // PUT THE CHECK AT THE END imp
    // https://leetcode.com/problems/linked-list-cycle/
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        
        ListNode slow = head, fast = head;
        
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true; // imp
        }
        return false;
    }

    /** 
     * to find the start of the loop, 2 ways :
     * 1 use a hashset and check if node has entry in set
     * or
     * 2 use fast and slow
    */
    // [1,2], 0; []
    // https://leetcode.com/problems/linked-list-cycle-ii
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode s = head;
        set.add(s);
        
        while(s!=null){ // s.next? -> check needed for head = []
            s = s.next;
            if(set.contains(s)) return s;
            set.add(s);
        }
        return null;
    }

    // https://leetcode.com/problems/linked-list-cycle-ii/discuss/44774/
    // Java-O(1)-space-solution-with-detailed-explanation.
    /** 
     * When fast and slow meet at point p, the length they have run are 'a+2b+c' and 'a+b'.
     * Since the fast is 2 times faster than the slow. So a+2b+c == 2(a+b), then we get 'a==c'.
     * So when another slow2 pointer run from head to 'q', at the same time, 
     * previous slow pointer will run from 'p' to 'q', so they meet at the pointer 'q' together.
     * 
    */
    public ListNode detectCycle2(ListNode head) {
        ListNode f = head, s = head;
        
        while(f.next!=null && f.next.next!=null){ // imp f.next && f.next.next
            f = f.next.next;
            s = s.next;
            if(f == s) {
                // now p covers 'a' dist and slow covers 'c' distance, check resources
                ListNode p = head;
                while(p!=s) {
                    p = p.next;
                    s = s.next;
                }
                return p;
            }
        }
        return null;
    }

    ListNode reverse(ListNode curr){
        ListNode a = null; ListNode b = curr; ListNode c= curr.next;
        if(c==null) return b;
        while(b!=null){
            b.next = a;
            a = b; b= c;
            // System.out.println("a "+a.val);
            if(c!=null) c = c.next;//2
        }
        return a;
    }

    /** 
     * use a newHead global var which will hold the last node
     * hold b.next as arg
    */
    // https://leetcode.com/problems/reverse-linked-list/
    ListNode newHead = null;
    public ListNode reverseListRecursive(ListNode head) {
        if(head == null || head.next == null) return head;
        helper(head, head.next);
        return newHead;
    }
    
    ListNode helper(ListNode a, ListNode b){
        if(b == null) {
            newHead = a;
            return a;
        }
        ListNode c = helper(b, b.next);
        // System.out.println("c "+c.val);
        c.next = a;
        a.next = null;
        return a;
    }

    /** 
     * TO USE THIS, MAKE SURE TO HOLD THE START INDEX OF THE 
     * NEXT LIST.(see reverse k nodes)
     * k is passed by value so use directly
     * Also c!=null checks for k = 1
    */
    // REVERSE A LIST TILL K ELS FROM START AND RETURN NEW HEAD
    ListNode reverseTillK(ListNode curr, int k){
        ListNode a = null; ListNode b = curr; ListNode c = curr.next;
        while(k!=0){ // 1
            b.next = a;
            a = b;
            b = c;
            if(c!=null && c.next != null) c = c.next; // 2
            k--;
        }
        return a;
    }


    // if no common
    // counter won't work as lists can be of diff length
    // the loops terminate when a reaches headB's end and b reaches headA's end
    // so null = null, no intersection 
    // https://leetcode.com/problems/intersection-of-two-linked-lists
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        
        ListNode p = headA, q = headB;
        while(p!=q){ //both null stops the iterations
            // move to other list
            if(p == null) p = headB;
            else p = p.next;
            
            if(q == null) q = headA;
            else q = q.next;
        }
        return p;
    }

    // simpler, use a flag to check if second time reset
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode a = headA; ListNode b = headB;
        boolean firsta = false; boolean firstb = false;
        while(a!=null && b!=null){
            if(a == b) return a;
            a = a.next; b = b.next;
            if(a == null && !firsta) {
                a = headB;
                firsta = true;
            }
            if(b == null && !firstb) {
                b = headA;
                firstb = true;
            }
        }
        // if it breaks out of loop then either a or b node is null;
        return null;
    }

    
    /** 
     * [1,2], 2 tricky, [1], 1
     * imp :  return head.next if(q == null)
     * */ 
    // https://leetcode.com/problems/remove-nth-node-from-end-of-list
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head; ListNode q = head;
        for(int i =0; i<n; i++){
            p = p.next;
        }
        if(p == null){
            head = head.next;
            return head;
        }
        while(p.next!=null){
            p = p.next;
            q = q.next;
        }
        q.next = q.next.next;
        return head;
    }


    // imp : compare length of list and k 
    // [1], 99; [1], 1; [1,2,3,4,5], 10
    // https://leetcode.com/problems/rotate-list
    public ListNode rotateRight(ListNode head, int k) {
        if(k==0) return head;
        if(head == null) return null;
        ListNode p = head; ListNode q = head;
        
        // r is for finding length
        ListNode r = head;
        int length = 1;
        while(r.next!=null){
            r = r.next;
            length++;
        }
        
        // boundary condns check; l=1; k=1
        if(length == 1) return head;
        k = k%length;
        // check if k == length then k%length = 0
        if(k%length == 0) return head; 
        for(int i =0; i<length-k; i++) {
            q = p;
            p = p.next;
        }
        q.next = null;
        r.next = head;
        head = p;
        return head;
    }

    /** 
     * SAME AS FINDING DUPLICATES IN ARRAY
     * USE 2 LOOPS
    */
    // https://leetcode.com/problems/remove-duplicates-from-sorted-list
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        // ListNode dummy = new ListNode(0);
        ListNode p = head; 
        // p.next = head;
        while(p!=null){
            ListNode n = p;
            while(n.next != null && n.val == n.next.val) n = n.next;
            // comparing address
            if(p != n) p.next = n.next;
            p = p.next;
        }
        return head;
    }

    /** 
     * IF DUP FOUND, DONT UPDATE P
     * HERE WE HAVE TO REMOVE THE DUPLICATE NODES COMPLETELY SO
     * A POINTER POINTING TO NODE BEFORE HEAD IS USED  
     * 
     * POINTS :
     * 1 THE SAME PRINCIPLE AS ABOVE, 2 LOOPS ONE TO INCREMENT, ONE 
     * TO FIND DUPS
     * 2 WE CHECK THE ADDRESSES, IF P.NEXT == N, THE SAME NODE
     * SO MOVE P
     * 2->3->3->4->4, p is at 2; p.next(3) != n(3), 
     * n is at second 3 so diff addr
     * p.next = n.next;
     * 3 ELSE p.next = n.next
    */
    // https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii
    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        p.next = head; // imp
        while(p.next!=null){
            ListNode n = p.next;
            
            while(n.next!=null && n.val == n.next.val) n = n.next;
            // no dups as p.next and n point to the same node, so addrs match
            // move p to the right
            if(p.next == n) p = n;
            // if dup, move to next n, dont update p
            else p.next = n.next;
        }
        return dummy.next;
    }

    // can't be done without two separate lists, 
    // as by using next as in odd even
    /**
     * MERGING OF TWO LISTS, USE TEMPLATE
     * 
     */
    // https://leetcode.com/problems/reorder-list
    public void reorderList(ListNode head) {
        if(head == null || head.next == null 
           || head.next.next == null) return;

        ListNode fast = head, slow = head;
        
        // midlde 
        while(fast.next!=null && fast.next.next !=null){//1
            fast = fast.next.next; 
            slow = slow.next;
        }
        
        // 2 lists a and b
        ListNode a = head;
        ListNode b = reverse(slow.next);//3
        // System.out.println(slow.val);
        slow.next = null;//4

        // merging two lists        
        while(a!=null && b!=null){
            ListNode c = a.next;
            ListNode d = b.next;
            a.next = b;
            b.next = c;
            a = c; b = d;
        }
    }


    // l1 still holds reference to list start
    // passing to helper means the ends must be returned
    // https://leetcode.com/problems/merge-two-sorted-lists/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while(l1!=null && l2!=null){
            if(l1.val<=l2.val){
                p.next = l1;
                l1 = l1.next;
            }else{
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        
        if(l1!=null) p.next = l1;
        if(l2!=null) p.next = l2;
        return dummy.next;
    }


    /** 
     * GO TILL MID AND THEN REVERSE FROM MID.NEXT
     * TRAVERSE BOTH LISTS SIMULTANEOUSLY
     * IF VALUE IS NOT SAME RETURN FALSE
    */
    // https://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
    boolean isPalindrome(ListNode head){
        //Your code here
        ListNode fast = head, slow = head;
        while(fast.next!=null && fast.next.next!=null){
            fast = fast.next.next; 
            slow = slow.next;
        }

        ListNode newHead = reverse(slow.next);
        fast = head;
        slow = newHead;
        while(fast!=null && slow!=null){
            if(fast.val != slow.val) return false;
            fast = fast.next; slow = slow.next;
        }
        return true;
        
    } 

    // DIVIDING INTO TWO LISTS, USING DUMMY NODES
    /**
     * WHILE CONDN (p!=null) AS ALL NODES AHEV TOP BE TRAVERSED 
     * 2 USE NEW LISTNODE(0) AND THEN RETURN NEXT;
     */
    // https://leetcode.com/problems/partition-list/
    public ListNode partition(ListNode head, int x) {
        ListNode smaller = new ListNode(0);
        ListNode small = smaller;
        ListNode bigger = new ListNode(0);
        ListNode big = bigger;
        ListNode p = head;
        while (p != null) {
            if (p.val >= x) {
                big.next = new ListNode(p.val);
                big = big.next;
            } else {
                small.next = new ListNode(p.val);
                small = small.next;
            }
            p = p.next;
        }

        // removal of first dummy nodes of both lists
        small.next = bigger.next;
        head = smaller.next;
        return head;
    }

    /** POINTS : 
     * 1 FAST.next!=NULL AND FAST.NEXT.next!=NULL
     * 2 TEMP = SLOW.next
     * 3 slow.NEXT = NULL
     * 4 
     * 
     * 4 f(){
     * l = f(); r = f(); merge(l,r);
     * // f takes only starting node
     * }
     * 
     * 5 WHILE MERGING DON'T FORGET IF(a!=null) AT THE END
     * 
     * find mid, merge and if(head.next == null return)
     * 
    */
    // https://leetcode.com/problems/sort-list
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head; // 1

        ListNode fast = head; ListNode slow = head;
        while(fast.next!=null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        ListNode temp = slow.next;
        slow.next = null;
        ListNode a = sortList(head);
        ListNode b = sortList(temp);
        return merge(a, b);
    }
    
    ListNode merge(ListNode a, ListNode b){
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while(a!=null && b!=null){
            if(a.val<b.val) {
                dummy.next = a; a = a.next;
            }else{
                dummy.next = b; b = b.next;
            }
            dummy = dummy.next;
        }
        
        if(a!=null) dummy.next = a;
        if(b!=null) dummy.next = b;
        
        return p.next;
    }

    /**
     * 1 add to heap 
     * 2 use the same technique as in adding 2 nos 
     * CREATE A NODE RES WITH VAL 0, 
     * node.next = new ListNode and then return RES.next
     */
    // https://leetcode.com/problems/merge-k-sorted-lists/
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int n = lists.length;

        if (n == 0)
            return null;
        if (n == 1 && lists[0] == null)
            return null;

        for (int i = 0; i < n; i++) {
            ListNode curr = lists[i];
            while (curr != null) {
                heap.add(curr.val);
                curr = curr.next;
            }
        }
        // System.out.println(heap);

        ListNode res = new ListNode(0);
        ListNode runner = res;

        while (heap.size() != 0) {
            runner.next = new ListNode();
            runner.next.val = heap.remove();
            runner = runner.next;
        }

        return res.next;
    }

    
    /** 
     * TEMPLATE (even!=null && even.next!=null)
     * 
     * POINTS :
     * 1 SINGLE PASS, ODD.NEXT = EVEN.NEXT AND EVEN.NEXT = EVEN.NEXT.NEXT
     * 2 UPDATE ODD AND EVEN
     * 3 REMEMBER BOUNDARY CONDITION
     * if(odd.next==null || even.next == null) break;
     * 
    */
    // https://leetcode.com/problems/odd-even-linked-list/
    public ListNode oddEvenIndexList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenStart = even;
        
        while(even!=null && even.next!=null){
            // if(odd.next==null || even.next == null) break;
            odd.next = even.next;
            even.next = even.next.next;
            odd = odd.next; even = even.next;
        }
        
        odd.next = evenStart;
        return head;
    }

    
    
    // https://leetcode.com/problems/insertion-sort-list/



    int josephusCircle(ListNode head) {
        int value = 100;
        value = head.key;
        boolean direction = true;
        ListNode tracker = null;

        // if (head == null) return -1;
        while (head != null) {
            while (tracker != null && tracker.next != null) {
                tracker.next = tracker.next.next;
                // System.out.println(tracker.key);
                tracker = tracker.next;
            }
            if (direction) {
                System.out.println("direction");
                head = reverseList(head);
                if (head.next == null)
                    return head.key;
                head = head.next;
                tracker = head;
                while (tracker != null && tracker.next != null) {
                    tracker.next = tracker.next.next;
                    // System.out.println(tracker.key);
                    tracker = tracker.next;
                }
            } else {
                System.out.println("direction change");
                head = reverseList(head);
                System.out.println("new head after reversal " + head.key);
                if (head.next == null)
                    return head.key;
                head = head.next;
                tracker = head;
                while (tracker != null && tracker.next != null) {

                    tracker.next = tracker.next.next;
                    // System.out.println(tracker.key);
                    tracker = tracker.next;
                }
            }
            direction = !direction;
        }
        if (head.next == null) {
            value = head.key;
        }
        return value;
    }

    /////////////// REVERSE

    // https://leetcode.com/problems/reverse-linked-list/submissions/
    /**
     * maintain 3 pointers assign them linearly, a=b; b=c; c= c.next b goes till
     * null, so c goes null before, put a check
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;

        ListNode a = null;
        ListNode b = head;
        ListNode c = head.next;
        while (b != null) {
            b.next = a;
            a = b; b = c;
            if (c != null) c = c.next;
        }
        return a;
    }

    void reverseListWithoutPointer(ListNode head, ListNode node, ListNode next) {
        if (next == null) {
            this.head = node;
            return;
        }
        reverseListWithoutPointer(head, next, next.next);
        next.next = node;
        node.next = null;// not adding this creates a loop between 1 and 2
    }

    // https://leetcode.com/problems/reverse-linked-list-ii
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode start = head; ListNode end = head; ListNode left = head;
        if(m==n) return head;
        for(int i =0; i<n-1; i++){
            if(i==m-2) left = end;
            if(i==m-1) start = end;
            end = end.next;
        }
        // System.out.println(start.val);
        // System.out.println(end.val);
        // if(start == end) return head;
        ListNode right = end.next;
        ListNode a = null; ListNode b = start; ListNode c = b.next;
        while(a!=end){
            b.next = a;
            a= b; b=c;
            if(c!=null) c = c.next;
        }
        if(left!=start) left.next = a; 
        else head = a;
        start.next = right;
        return head;
    }


    // https://leetcode.com/problems/swap-nodes-in-pairs/
    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if(head.next == null) return head;
        ListNode p = head;
        ListNode q = p.next.next;
        ListNode r = reverseK(p, 2);
        p.next = swapPairs(q);
        return r;
    }

    public ListNode swapPairsIte(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;
            first.next = second.next;
            current.next = second;
            current.next.next = first;
            current = current.next.next;
        }
        return dummy.next;
    }

    /**  
     * POINTS :
     * 1 FIND NEXT LIST'S START Q,
     * if q = null i.e. less than k nodes left, return head;
     * 2 r = REVERSE TILL K
     * 3 FIX OTHER sub LISTS BEFORE RETURNING r, 
     * same way this list was fixed, call same func
     * 4 P.NEXT = F()
     * 
     * f(){
     * p,q, r = reverseK
     * p.next = f(q)
     * return r
     * }
     * 
    */
    // recursive
    // https://leetcode.com/problems/reverse-nodes-in-k-group/
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode p = head;
        ListNode q = p;
        int counter = 0;
        // keep track of the next list's start
        // find q before reversing  // 1
        while (counter < k) {
        // if there are less than k nodes, no reversal, return current head
            if (q == null) return p;
            q = q.next;
            counter++;
        }
        ListNode r = reverseK(p, k); // 2
        // fix other lists before returning
        p.next = reverseKGroup(q, k); // 3
        return r; // 4
    }
    
    ListNode reverseK(ListNode curr, int k){
        ListNode a = null;
        ListNode b = curr;
        ListNode c = curr.next;
        
        int counter = 0;
        while(a!=b){
            if(counter == k) break;
            b.next = a;
            a = b;
            b = c;
            if(c.next!=null) c= c.next;
            counter++;
        }
        return a;
    }


    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
        Node(int v){
            this.val = v;
        }
    };
    /*the most imp thing was how to return 12 once we get to 11.
     * so when no child is present, we return the last node.
     * p will traverse as usual, so return q which holds p.prev
     * 
     * POINTS :
     * 1 TAKE CARE TO SET NEXT AND PREV BOTH
     * 2 SET CHILD TO NULL
     * 
     * similar recursion strategy as in reverse k nodes
     * the function makes a call to itself to get the last node
     * of it's child's list. To do this return last for this call.
     * 
     * f(){
     * p, last, if(p.child) {
     * r = f();
     * fix links and 2 things : p.child = null; q!=null
     * AND p = r
     * }
     * return last;
     * }
    */
    // https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
    class Solution {
        public Node flatten(Node head) {
            helper(head);
            return head;
        }
        
        Node helper(Node head){
            Node p = head; Node last = null;
            while(p!=null){
                // if child exists, make the new adjustments
                if(p.child!=null){
                    Node q = p.next; // new next
                    Node r = helper(p.child);

                    p.next = p.child; // 
                    p.child.prev = p; // 
                    p.child = null;//imp
                    r.next = q; // -> next
                    if(q!=null) q.prev = r; // <- prev
                    p = r; // so that p.next outside later moves it to q
                }
                last = p;
                p = p.next;
            }
            return last;
        }
    }
    
    /** 
     * sum+=l1+l2+carry
     * value sum%10
     * carry = sum/10
     * 
     * same code for each remaining list
    */
    // https://leetcode.com/problems/add-two-numbers/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode res = new ListNode(-1); ListNode p = res;
        while(l1!=null && l2!=null){
            int sum = 0; 
            sum+=l1.val;
            sum+=l2.val;
            sum+=carry;
            p.next = new ListNode(sum%10);
            carry = sum/10;
            p = p.next; l1 = l1.next; l2 = l2.next;
        }
        
        // same code for each remaining list
        while(l1!=null){
            int sum = 0; 
            sum+=l1.val;
            sum+=carry;
            p.next = new ListNode(sum%10);
            carry = sum/10;
            p = p.next; l1 = l1.next;
        }
        
        while(l2!=null){
            int sum = 0; 
            sum+=l2.val;
            sum+=carry;
            p.next = new ListNode(sum%10);
            carry = sum/10;
            p = p.next;
            l2 = l2.next;
        }
        if(carry!=0) p.next = new ListNode(carry);
        
        return res.next;
    }

    
    /**
     * reverse both lists add and then reverse the final 
     * WHILE ADDING (SUM+CARRY)%10 IS NODE VALUE 
     * AND CARRY = SUM+CARRY/10 
     * 1 add a check for a!= null, b!=null
     * 2 create a head d = c; c = listnode(0), D WILL BE PASSED FOR REVERSAL 
     * 3 chck if carry!=0 at the end
     * 4 while reversing the final list, pass head.next as the first is 0
     * 
     * 
     * sum+= carry; find carry and sum first
     * reverse->b!=null
     */
    // https://leetcode.com/problems/add-two-numbers-ii/submissions/
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode a = reverseList(l1);
        ListNode b = reverseList(l2);
        ListNode c = new ListNode(0);
        ListNode d = c;
        int carry = 0;
        while (a != null || b != null) {
            int sum = 0;

            if (a != null) {
                sum += a.val;
                a = a.next;
            }
            if (b != null) {
                sum += b.val;
                b = b.next;
            }
            sum += carry;
            // System.out.println("sum "+sum);
            carry = sum/10;
            c.next = new ListNode((sum) % 10);
            c = c.next;
        }
        if(carry!=0) c.next = new ListNode(carry); // imp [5,5]
        return reverseList(d.next);
    }

    // lru cache
    /** 
     * IMP: HOW TO GET KEY FROM LIST? STORE THE WHOLE OBJECT
     * POINTS:
     * 1 GETTING OR PUTTING A PAGE COUNTS AS A REFERENCE. SO MOVE TO FRONT
     * 2 CHECKS: 
     * GET : IF MAP CONTAINS
     * PUT : IF MAP DOESN'T CONTAIN AND IS FULL
     * 
     * 3 IF MAP IS FULL, REMOVE FROM END. WHILE REMOVING ENSURE THAT CORRESPONDING KEY
     * IS ALSO REMOVED FROM MAP. 
     * 
     * 4 ELSE REMOVE NODE AND ADD AT FRONT. MAP MAINTAINS ADDRESS, SO NO NEED TO UPDATE.
     * 
    */
    class LRUCache {
        class Node{
            int key, value;
            Node(int k, int v){
                this.key = k;
                this.value = v;
            }
        }
        int limit;
        Deque<Node> DLL;
        HashMap<Integer, Node> map;

        public LRUCache(int capacity) {
            this.limit = capacity;
            this.DLL = new LinkedList<>();
            this.map = new HashMap<>();    
        }
        
        public int get(int key) {
            Node curr;
            if(map.containsKey(key)){
                curr = map.get(key);
                DLL.remove(curr);
                DLL.addFirst(curr);
                return  curr.value;
            }
            return -1;
        }
        
        // if page is present, still a 'put' operation counts as a reference
        public void put(int key, int value) {
            Node curr;
            if(map.containsKey(key)){
                curr = map.get(key);
                // move to front
                this.DLL.addFirst(curr);
            }
            else{
                if(map.size() == limit){
                    curr = this.DLL.getLast();
                    map.remove(curr.key);
                    this.DLL.removeLast();
                }else{
                    curr = new Node(key, value);
                    this.DLL.addFirst(curr);
                }
            }
        }
    }

    class MyCircularDeque {
        LinkedList<Node> list;
        Node head, tail;
        int size, capacity;

        /** Initialize your data structure here. Set the size of the deque to be k. */
        public MyCircularDeque(int k) {
            this.list = new LinkedList<Node>();
            this.capacity = k;
            head = null;
            tail = head;
        }

        /**
         * Adds an item at the front of Deque. Return true if the operation is
         * successful.
         */
        public boolean insertFront(int value) {
            if (capacity == size)
                return false;
            if (size == 0) {
                head = new Node(value);
                tail = head;
                size++;
                return true;
            }
            head.prev = new Node(value);
            head.prev.next = head;
            head = head.prev;
            size++;
            return true;
        }

        public boolean insertLast(int value) {

            if (size == 0) {
                head = new Node(value);
                // tail = head.next;
                tail = head;
                size++;
                return true;
            }
            if (capacity == size)
                return false;
            // tail is at last node
            tail.next = new Node(value);
            tail.next.prev = tail;
            tail = tail.next;
            size++;
            return true;
        }

        public boolean deleteFront() {
            if (size == 0)
                return false;

            head = head.next;
            size--;
            return true;
        }

        public boolean deleteLast() {
            // System.out.println("size "+size);
            if (size == 0)
                return false;
            tail = tail.prev;

            size--;
            return true;
        }

        /** Get the front item from the deque. */
        public int getFront() {
            if (size == 0)
                return -1;
            return head.val;
        }

        /** Get the last item from the deque. */
        public int getRear() {
            if (size == 0)
                return -1;
            return tail.val;
        }

        /** Checks whether the circular deque is empty or not. */
        public boolean isEmpty() {
            return size == 0;
        }

        /** Checks whether the circular deque is full or not. */
        public boolean isFull() {
            return size == capacity;
        }
    }

    /** 
     * BASICALLY WE USE A HASHAMP TO MAP THE ADDRESS OF EACH NODE WITH ITS CLONE, 
     * IF NOT NULL.
     * 
     * POINTS :
     * 1 FOR EACH NODE WE CREATE CLONE
     * map.put(random2, new ListNode(random2.val));
     * 2 IF CLOBE EXISTS, MAP TO P2' RANDOM OR NEXT
     * 
    */
    // https://leetcode.com/problems/copy-list-with-random-pointer
    public ListNode copyRandomList(ListNode head) {
        if(head == null) return null;
        
        ListNode h2 = new ListNode(head.val);
        ListNode p2 = h2;
        HashMap<ListNode, ListNode> map = new HashMap<>();
        map.put(head, h2);
        ListNode p = head;
        
        while(p!=null){
            // System.out.println(map);
            ListNode random1Clone = p.random;
            ListNode next1Clone = p.next;
            
            if(random1Clone!=null) {
                if(!map.containsKey(random1Clone)) 
                    map.put(random1Clone, new ListNode(random1Clone.val));
            }
            
            if(next1Clone!=null){
                if(!map.containsKey(next1Clone)) 
                    map.put(next1Clone, new ListNode(next1Clone.val));
            }
            
            p2.random = map.get(random1Clone);
            p2.next = map.get(next1Clone);
            
            p2 = p2.next;
            p = p.next;
        }
        
        return h2;
    }

    // COPIED
    // https://leetcode.com/problems/merge-two-sorted-lists/
    public ListNode mergeTwoListsRec(ListNode l1, ListNode l2){
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		if(l1.val < l2.val){
			l1.next = mergeTwoListsRec(l1.next, l2);
			return l1;
		} else{
			l2.next = mergeTwoListsRec(l1, l2.next);
			return l2;
		}
    }


    // iteratively reverse k nodes
    public ListNode reverseKGroupIte(ListNode head, int k) {
        if (head == null || k == 1)
            return head;

        ListNode fake = new ListNode(0);
        fake.next = head;
        ListNode pre = fake;
        int i = 0;

        ListNode p = head;
        while (p != null) {
            i++;
            if (i % k == 0) {
                pre = reverse(pre, p.next);
                p = pre.next;
            } else {
                p = p.next;
            }
        }

        return fake.next;
    }

    public ListNode reverse(ListNode pre, ListNode next) {
        ListNode last = pre.next;
        ListNode curr = last.next;

        while (curr != next) {
            last.next = curr.next;
            curr.next = pre.next;
            pre.next = curr;
            curr = last.next;
        }

        return last;
    }
    
    /** 
     * POINTS :
     * 1 USE DUMMY, START FROM DUMMY
     * 2 MAKE ENTRY OF SUM AND CORRESPONDING NODE
     * 3 IF SUM IS FOUND, NODE.NEXT = p.next, CLEAR MAP
     * 
     * we clear map,set sum to 0 and start again from dummy, this way 
     * the issues with list.size() and 0 value nodes can be avoided.
     * 
    */
    // [1,-1], [0,0], [1,3,2,-3,-2,5,5,-5,1]
    // https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list
    public ListNode removeZeroSumSublists(ListNode head) {
        if(head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        HashMap<Integer, ListNode> map = new HashMap<>();
        ListNode p = dummy;
        
        int sum = 0;
        while(p!=null){
            sum+=p.val;
            if(map.containsKey(sum)){
                // delinking 0 sum nodes
                map.get(sum).next  = p.next;
                // clear, we start afresh, imp
                map.clear();
                sum = 0;
                // reset p to dummy
                p = dummy;
            }
            else {
                map.put(sum, p);
                p = p.next;
            }
        }
        return dummy.next;
    }

    // https://stackoverflow.com/questions/21528422/storing-a-doubly-linked-list-using-just-a-single-pointer-field
    // https://github.com/mission-peace/interview/blob/master/src/com/interview/linklist/SortedLLToBalancedBST.java
    // https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
    public static void main(String[] args) {
        LinkedPractice linked = new LinkedPractice();
        linked.addNode(1);
        linked.addNode(2);
        linked.addNode(3);
        linked.addNode(3);
        linked.addNode(4);
        linked.addNode(4);
        linked.addNode(5);
        // linked.addNode(6);
        // linked.addNode(7);
        // linked.addNode(8);
        // linked.addNode(9);

        // linked.deleteDuplicates(linked.head);
        // linked.reorderList(linked.head);
        // System.out.println(linked.josephusCircle(linked.head));
        // linked.reverseListWithoutPointer( linked.head,linked.head, linked.head.next);
        // linked.printList(linked.head);
        // linked.reverseKGroup(linked.head, 3);

        // Node looper = linked.head;
        // looper = looper.next.next;//5
        // linked.current.next = looper;

        // Node track = linked.head;
        // while(track!=null){
        // System.out.println(track.key);
        // track = track.next;
        // }

        // Node track2 = track;
        // while(track!=null){

        // track = track.next;
        // track2 = track2.next.next;

        // System.out.println("track2 " +track2.key);
        // System.out.println("track " +track.key);
        // if(track.key == track2.key){
        // System.out.println("loop found");
        // break;
        // }

        // }

        // linked.sortList(linked);
        // linked.oddEvenSort(linked.head);
        // linked.mergeSort(linked);
        // linked.printList(linked);
    }
}