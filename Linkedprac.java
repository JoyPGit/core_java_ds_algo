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

    void sortList(Linkedprac list) {
        ListNode head = list.head;
        ListNode currentMax = head;
        ListNode current = head.next;
        ListNode prev = head;
        while (current.next != null) {
            // if(current.key<current.next.key){
            // currentMax = current.next;
            // } else currentMax = current;
            System.out.println("prev " + prev.key);
            System.out.println("curr " + current.key);
            if (prev.key > current.key) {
                prev.next = current.next;
                current.next = prev;
            }
            prev = current;
            current = current.next;

        }

    }

    void mergeSort(Linkedprac list) {
        ListNode middle = getMiddle(list);// finding middle is tricky
        ListNode nextOfMiddle = middle.next;

        mergeSort(list);
        System.out.println("middle" + middle.key);
    }

    ListNode getMiddle(Linkedprac list) {
        ListNode current = list.head;
        ListNode middle = list.head;

        while (current.next != null && current.next.next != null) {
            current = current.next.next;
            middle = middle.next;
        }
        return middle;
    }

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

    // https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
    public static void main(String[] args) {
        Linkedprac linked = new Linkedprac();
        linked.addNode(1);
        linked.addNode(2);
        linked.addNode(3);
        linked.addNode(4);
        linked.addNode(5);
        linked.addNode(6);
        linked.addNode(7);
        linked.addNode(8);
        linked.addNode(9);

        // System.out.println(linked.josephusCircle(linked.head));
        // linked.reverseListWithoutPointer( linked.head,linked.head, linked.head.next);
        // linked.printList(linked.head);
        linked.reverseKGroup(linked.head, 3);

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