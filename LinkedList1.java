import java.util.HashMap;
import java.util.*;

class ListNode {
    int key;
    ListNode next;

    ListNode(int data) {
        this.key = data;
        this.next = null;
    }
}

class LinkedList1 {
    ListNode head = null;
    ListNode current = null;

    void printList(LinkedList1 list) {
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

    void printList(ListNode head){
        while(head!=null){
            System.out.print(head.key+"->");
            head = head.next;
            if(head.next == null) {
                System.out.print(head.key+"-~~!");
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

    void findIntersection(LinkedList1 list1, LinkedList1 list2) {
        // HashMap h =
    }

    void sortList(LinkedList1 list) {
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

    void mergeSort(LinkedList1 list) {
        ListNode middle = getMiddle(list);// finding middle is tricky
        ListNode nextOfMiddle = middle.next;

        mergeSort(list);
        System.out.println("middle" + middle.key);
    }

    ListNode getMiddle(LinkedList1 list) {
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
        while (head!= null) {
            while(tracker!=null && tracker.next!=null){
                tracker.next = tracker.next.next;
                // System.out.println(tracker.key);
                tracker= tracker.next;
            }
            printList(head);
            if (direction) {
                System.out.println("direction");
                printList(head);
                head = reverseList(head);
                if(head.next==null) return head.key;
                head = head.next;
                tracker = head;
                while(tracker!=null && tracker.next!=null){
                    tracker.next = tracker.next.next;
                    // System.out.println(tracker.key);
                    tracker= tracker.next;
                }
                printList(head);
            } else {
                System.out.println("direction change");
                head = reverseList(head);
                System.out.println("new head after reversal "+head.key);
                if(head.next==null) return head.key;
                head = head.next;
                printList(head);
                tracker = head;
                while(tracker!=null && tracker.next!=null){
                    
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

    ListNode reverseList(ListNode node) {
        // printList(node);
        ListNode prev = null;
        ListNode curr = node;
        ListNode next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            // System.out.println(prev.key);

        }
        return prev;
    }

    void reverseListWithoutPointer(ListNode head, ListNode node, ListNode next){
        if(next==null) {
            this.head = node;
            return;
        }
        reverseListWithoutPointer(head, next, next.next);
        next.next = node;
        node.next = null;//not adding this creates a loop between 1 and 2
    }

    public static void main(String[] args) {
        LinkedList1 linked = new LinkedList1();
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
        linked.reverseListWithoutPointer( linked.head,linked.head, linked.head.next);
        linked.printList(linked.head);

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