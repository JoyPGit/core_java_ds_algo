import java.util.HashMap;
import java.util.*;

class ListNode {
    int key;
    int val;
    ListNode next;

    ListNode() {
    }
    // constructor overloading

    ListNode(int data) {
        this.key = data;
        this.next = null;
        this.val = this.key;
    }
}

/** 
 * POINTS :
 * TRICKS : (USE DUMMY NODE, DIVIDE INTO LISTS AND MERGE)
 * 1 P!= NULL OR P.NEXT != NULL
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
*/

class Linkedprac {
    ListNode head = null;
    ListNode current = null;

    void printList(Linkedprac list) {
        ListNode current = list.head;
        System.out.print(head.key + "->");
        while (current.next != null) {
            current = current.next;
            if (current.next != null)
                System.out.print(current.key + "->");
            else
                System.out.println(current.key);
        }
    }

    void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.key + "->");
            head = head.next;
            if (head.next == null) {
                System.out.print(head.key + "-~~!");
                System.out.println();
                return;
            }
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

    void findIntersection(Linkedprac list1, Linkedprac list2) {
        // HashMap h =
    }

    // https://leetcode.com/problems/merge-two-sorted-lists/
    public ListNode mergeTwoListsRec(ListNode l1, ListNode l2){
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		if(l1.val < l2.val){
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else{
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode s = null;
        if(l1== null) return l2;
        if(l2== null) return l1;
        if(l1.val<l2.val) {
            s = l1; l1= l1.next;
        }
        else {
            s = l2; l2 = l2.next;
        }
        ListNode head = s;
        while(l1!=null && l2!=null){
            if(l1.val<=l2.val){
                s.next = l1; l1 = l1.next;
            }
            else{
                s.next = l2; l2 = l2.next;
            }
            s= s.next;
        }
        
        while(l1!=null) {
            s.next  = l1; s = s.next; l1= l1.next;
        }
        
        while(l2!=null) {
            s.next  = l2; s = s.next; l2= l2.next;
        }
        return head;
    }

    // https://leetcode.com/problems/sort-list/submissions/
    /** POINTS : 
     * 1 FAST!=NULL AND FAST.NEXT!=NULL
     * 2 TEMP = SLOW
     * 3 TEMP.NEXT = NULL
     * 
     * 4 f(){
     * l = f(); r = f(); merge(l,r);
     * // f takes only starting node
     * }
     * 
     * 5 WHILE MERGING DON'T FORGET IF(a!=null) AT THE END
     * 
     * WHY DO WE NEED TEMP?
     * SLOW GOES BEYOND MID, I.E IF THERE ARE 4 ELS, SLOW GOES TILL 2ND INDEX
     * SO TEMP HOLDS 1ST INDEX AND BREAKS THE LINK. SO THAT THE DIVISION IS MADE.
    */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head; ListNode slow = head;
        ListNode temp = null;
        while(fast!=null && fast.next != null){
            temp = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        
        temp.next = null;
        ListNode a = sortList(head);
        ListNode b = sortList(slow);
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

    
    
    // https://leetcode.com/problems/insertion-sort-list/

    void oddEvenSort(ListNode node) {
        ListNode prev = node;
        ListNode current = prev.next;
        ListNode last = node;

        while (last.next != null) {
            last = last.next;
        }

        System.out.println("last " + last.key);
        while (current.next != null) {
            if (current.key % 2 != 0) {
                prev.next = current.next;
                last.next = current;
                last = last.next;
                last.next = null;
            }
            // System.out.println("prev " + prev.key);
            // System.out.println("curr " + current.key);
            // System.out.println("last " + last.key);
            prev = prev.next;
            current = prev.next;
            /*
             * errors while coding 1 current = current.next as current has been set as the
             * last node, current.next will be null 2 current = prev.next.next
             */
        }
    }

    void reverseInKGroup(ListNode node, int k) {
        ListNode start = node;
        ListNode end = node;
        ListNode nodeBefore = start;
        ListNode nodeAfter;

        int i = 0;
        while (i < k) {
            if (end.next != null) {
                end = end.next;
                i++;
            }
        }
        reverseInKGrouphelper(start, end);
        nodeBefore = end;
        start = end.next;
        i = 0;
    }

    void reverseInKGrouphelper(ListNode start, ListNode end) {
        ListNode ptr1 = start;
        ListNode ptr2 = null;
        if (ptr1.next != null) {
            ptr2 = ptr1.next;
        }
        ptr1.next.next = ptr1;
        ptr2 = ptr2.next;
        ptr1 = ptr1.next;
    }

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
            printList(head);
            if (direction) {
                System.out.println("direction");
                printList(head);
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
                printList(head);
            } else {
                System.out.println("direction change");
                head = reverseList(head);
                System.out.println("new head after reversal " + head.key);
                if (head.next == null)
                    return head.key;
                head = head.next;
                printList(head);
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

    // https://leetcode.com/problems/reverse-linked-list/submissions/
    /**
     * maintain 3 pointers assign them linearly, a=b; b=c; c= c.next b goes till
     * null, so c goes null before, put a check
     */
    public ListNode reverseList(ListNode head) {
        if (head == null)
            return head;
        ListNode a = null;
        ListNode b = head;
        ListNode c = head.next;
        while (b != null) {
            b.next = a;
            a = b;
            b = c;
            if (c != null)
                c = c.next;
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

    // https://leetcode.com/problems/add-two-numbers-ii/submissions/
    /**
     * reverse both lists add and then reverse the final WHILE ADDING (SUM+CARRY)%10
     * IS NODE VALUE AND CARRY = SUM+CARRY/10 1 add a check for a!= null, b!=null
     * and carry!=0 2 create a head d = c; c = listnode(0), D WILL BE PASSED FOR
     * REVERSAL 3 while reversing the final list, pass head.next as the first is 0 4
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode a = reverseList(l1);
        ListNode b = reverseList(l2);
        ListNode c = new ListNode(0);
        ListNode d = c;
        int carry = 0;
        int sum = 0;
        while (a != null || b != null || carry != 0) {
            if (a != null) {
                sum += a.val;
                a = a.next;
            }
            if (b != null) {
                sum += b.val;
                b = b.next;
            }
            // System.out.println("sum "+sum);
            c.next = new ListNode((sum + carry) % 10);
            c = c.next;
            carry = (sum + carry) / 10;
            // else carry =0;
            sum = 0;
        }

        return reverseList(d.next);
    }

    // https://leetcode.com/problems/partition-list/
    /**
     * 1 WHEN ALL NODES ARE TO BE TRAVERSED, USE P!=NULL, ELSE P.NEXT!=NULL 2 USE
     * NEW LISTNODE(0) AND THEN RETURN NEXT;
     */
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

        small.next = bigger.next;
        head = smaller.next;
        return head;
    }

    void oddEvenGroup(ListNode head) {
        ListNode odd = head;
        ListNode even = null;
        if (head.next != null)
            even = head.next;
        ListNode evencount = even;

        while (odd.next != null) {
            odd.next = evencount.next;
            odd = odd.next;
            evencount.next = odd.next;
            evencount = evencount.next;
        }

        odd.next = even;
    }

    // https://leetcode.com/problems/odd-even-linked-list/
    /**
     * TECHNIQUES : 1 TARAVERSE ALL NODES, USE P!=NULL 2 USE STARTING LISTNODE(0); 3
     * MERGE TWO LISTS
     */
    public ListNode oddEvenList(ListNode head) {
        ListNode odd = new ListNode(0);
        ListNode o = odd;
        ListNode even = new ListNode(0);
        ListNode e = even;
        ListNode p = head;

        boolean isOdd = false;
        while (p != null) {
            if (isOdd) {
                o.next = new ListNode(p.val);
                o = o.next;
            } else {
                e.next = new ListNode(p.val);
                e = e.next;
            }
            p = p.next;
            isOdd = !isOdd;
        }

        e.next = odd.next;
        head = even.next;
        return head;
    }

    public ListNode oddEvenListSinglePass(ListNode head) {
        if (head != null) {
        
            ListNode odd = head, even = head.next, evenHead = even; 
        
            while (even != null && even.next != null) {
                odd.next = odd.next.next; 
                even.next = even.next.next; 
                odd = odd.next;
                even = even.next;
            }
            odd.next = evenHead; 
        }
        return head;
    }

    // https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    /**
     * boundary conditions : n=4 and {1,2,3,4}; n=3 and {1,2,3,4} here tracker goes
     * till end and ptr is at head, so return head as soon as tracker is null as n
     * is then beyond the length of list.
     * 
     * if ptr == head, just set ptr.next = ptr.next.next and return head; POINTS:
     * LENGTH GREATER, TRACKER = NULL, RETURN PTR == HEAD, PTR.NEXT = PTR.NEXT.NEXT,
     * RETURN
     * 
     * * INSTEAD OF TRAVERSING TWICE, RUN LOOP FOR N-K TIMES
     * 
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n == 0)
            return head;
        ListNode tracker = head;
        ListNode ptr = head;

        for (int i = 0; i < n; i++) {
            tracker = tracker.next;
            if (tracker == null) {
                head = head.next;
                return head;
            }
        }

        while (tracker.next != null) {
            tracker = tracker.next;
            ptr = ptr.next;
        }

        ptr.next = ptr.next.next;

        return head;
    }

    // https://leetcode.com/problems/rotate-list/submissions/
    /**
     * POINTS : 1 FIND LENGTH 2 K = K%N 3 KEEP TRACK OF END 4 INSTEAD OF TRAVERSING
     * TWICE, RUN LOOP FOR N-K TIMES
     */
    public ListNode rotateRight(ListNode head, int k) {
        int n = 0;
        ListNode end = head;
        ListNode p = head;
        if (head == null)
            return head;

        while (end.next != null) {
            n++;
            end = end.next;
        }
        n++;
        k = k % n;

        // System.out.println(k);

        for (int i = 0; i < n - k - 1; i++)
            p = p.next;
        // System.out.println(p.val);
        end.next = head;
        head = p.next;
        p.next = null;

        return head;
    }

    // https://leetcode.com/problems/merge-k-sorted-lists/
    /**
     * 1 add to heap 2 use the sam etechnique as in adding 2 nos CREATE A NODE RES
     * WITH VAL 0, node.next = new ListNode and then return RES.next
     */
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

    // https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
    public ListNode deleteDuplicates(ListNode head) {
        ListNode first = new ListNode(0); boolean dup = false;
        ListNode p = first;
        first.next = head;
        while(first.next!=null) {
            ListNode tracker = first.next;
            
            while(tracker!=null && tracker.next!=null && tracker.val == tracker.next.val){
                dup = true;
                tracker = tracker.next;
            }
            if(dup) first.next = tracker.next;
            else first = first.next;
            dup = false;
        }   
        // printList(p.next);
        return p.next;
    }

    // https://leetcode.com/problems/swap-nodes-in-pairs/
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode second = head.next;
        ListNode third = second.next;

        second.next = head;
        head.next = swapPairs(third);

        return second;
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

    // https://leetcode.com/problems/reverse-nodes-in-k-group/
    // recursive
    public ListNode reverseKGroup(ListNode head, int k) {
        // 1. test weather we have more then k node left,
        // if less then k node left we just return head
        ListNode node = head;
        int count = 0;
        while (count < k) {
            if (node == null)
                return head;
            node = node.next;
            count++;
        }
        // 2.reverse k node at current level
        // pre node points to the the answer of sub-problem
        ListNode pre = reverseKGroup(node, k);
        while (count > 0) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
            count = count - 1;
        }
        return pre;
    }

    // iteratively
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
     * 2 WAYS TO FIND MIDDLE
     * ONE IS FAST!=NULL && FAST.NEXT != NULL
     * OTHER IS FAST.NEXT!=NULL && FAST.NEXT.NEXT!=NULL
     * 
     * THE DIFF IS IN THE FIRST CASE, SLOW GOES ONE STEP AHEAD OF MIDDLE 
     * IN CASE OF EVEN NO OF NODES
     */
    // https://leetcode.com/problems/reorder-list/submissions/
    public void reorderList(ListNode head) {
        if(head == null || head.next ==null || head.next.next == null) return;

        ListNode m = head; ListNode n = null;
        ListNode fast = head; ListNode slow = head; ListNode temp = null;
        while(fast!=null && fast.next!=null){
            temp = slow;
            slow = slow.next; fast = fast.next.next;
        }
        
        if(fast==null) { temp.next = null; n = reverse(slow); }    
        else if(fast.next==null) { n = reverse(slow.next); slow.next = null; }
        // System.out.println(n.val);

        ListNode s = n.next;
        if(s==null) {n.next = m.next; m.next = n;}
        
        while(s!=null){
            s = n.next; ListNode r = m.next;
            m.next = n; n.next = r; m = r; n = s; 
        }
    }

    /** A VARIATION OF REVERSE LIST, 1->2->3->4->5->6->7->8->9->10 
     * TO 1->10->3->8->5->6->7->4->9->2
     * POINTS TO REMEMBER : 
     * 1 FOR FAST SLOW, P.NEXT!=NULL
     * 2 E = E.NEXT AND THEN
     * 3 INSIDE USE IF(P.NEXT==NULL) BREAK;
     * 4 ENSURE LAST NODE IS NULL E.NEXT = NULL
     * 5 IN REVERSE IF(C!=NULL) C= C.NEXT;
     * 
     * REMEMBER TO SET NULL
    */
    public void reorderListCustom(ListNode head) {
        if(head == null) return;
        ListNode p = head; 
        ListNode e = new ListNode(0); ListNode b = e;
        
        while(p.next!=null){//1
            System.out.println("p "+p.val);
            
            e.next = p.next; p.next = p.next.next; 
            e = e.next;    
            if(p.next==null) break;//2
            p = p.next;
        }
        
        // System.out.println(p.val);
        e.next = null;//3
        b = b.next; p = head;
            
        ListNode q = reverse(b); 
        ListNode t = q;
        while(t!=null) {
            System.out.print(t.val+", ");t = t.next;
        }
        while(p!=null && q!=null){
            ListNode s = q.next; ListNode r = p.next;
            // System.out.println("s "+s.val);
            
            p.next = q; q.next = r; p = r; q = s;
        }
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

     class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };
    /*the most imp thing was how to return 12 once we get to 11.
    so when no child is present, we return the last node.
    p will traverse as usual, so return q which holds p.prev
     * 
     * POINTS :
     * 1 TAKE CARE TO SET NEXT AND PREV BOTH
     * 2 SET CHILD TO NULL
    */
    // https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
    class Solution {
        public Node flatten(Node head) {
            helper(head);
            return head;
        }
        
        Node helper(Node head){
            Node p = head; Node q = null;
            while(p!=null){
                // if child exists, make the new adjustments
                if(p.child!=null){
                    Node r = p.next; // new next
                    p.next = p.child; // -> next
                    p.child.prev = p; // <- prev
                    Node s = helper(p.child);
                    // if(s==null) continue;
                    p.child = null;//imp
                    s.next = r; // -> next
                    if(r!=null) r.prev = s; // <- prev
                }
                q = p;
                p = p.next;
            }
            return q;
        }
    }
    

    /*
     * public ListNode reverseKGroup(ListNode head, int k) { ListNode begin; if
     * (head==null || head.next ==null || k==1) return head; ListNode dummyhead =
     * new ListNode(-1); dummyhead.next = head; begin = dummyhead; int i=0; while
     * (head != null){ i++; if (i%k == 0){ begin = reverse(begin, head.next); head =
     * begin.next; } else { head = head.next; } } return dummyhead.next;
     * 
     * }
     * 
     * public ListNode reverse(ListNode begin, ListNode end){ ListNode b =
     * begin.next; ListNode next, first; ListNode a = begin; first = b; while
     * (b!=end){ next = b.next; curr.next = a; a = curr; b = next; } begin.next =
     * prev; first.next = curr; return first; }
     */

    // https://stackoverflow.com/questions/21528422/storing-a-doubly-linked-list-using-just-a-single-pointer-field
    // https://github.com/mission-peace/interview/blob/master/src/com/interview/linklist/SortedLLToBalancedBST.java
    // https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
    public static void main(String[] args) {
        Linkedprac linked = new Linkedprac();
        linked.addNode(1);
        linked.addNode(2);
        linked.addNode(3);
        linked.addNode(4);
        linked.addNode(5);
        // linked.addNode(6);
        // linked.addNode(7);
        // linked.addNode(8);
        // linked.addNode(9);

        // linked.deleteDuplicates(linked.head);
        linked.reorderList(linked.head);
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