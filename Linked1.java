// import java.util.LinkedList;

// // import java.util.*;

// /**a class linked1, a sub class node, node will contain reference to itself
//  * instantiate node within linked1
//  * take care of return type Node
//  * also Node head == null is throwing error
//  */
// public class Linked1 {

//     // void printAll(){
//     //     for
//     // }
//     public class Node{
//         Node front;
//         Node back;
//         int key;

//         Node(int key){
//             this.key = key;
//             this.front = null;
//             this.back = null;
//         }
//     }

//     Node head = null;
//     Node last = null;

//     void insert(int value){

//         if (this.head != null) {
//             this.last.back = new Node(value);
//             this.last.back.front = this.last;
//             this.last.back.back = this.head;
//             this.last = this.last.back;
//         } else {
//             this.head = new Node(value);
//             this.last = this.head;
//         }
//     }

//     void delete (int value){
//         Node tracker = this.head;
//         while(tracker.key!=value){
//             tracker= tracker.back;
//             if(tracker.key == value){
//                 break;
//             }
//         } 
//         tracker.back.front = tracker.front;
//         tracker.front.back = tracker.back; 

//     }

//     void printList(){
//         Node pointer = this.head;
//         while(pointer.back != this.last.back){
//             System.out.println(pointer.key+"-->");
//             pointer = pointer.back;
//         }
//         System.out.println(pointer.key);
//     }

//     void frontAndBack(int value){
//         Node pointer = this.head;
//         while(pointer.key!= value){
//             pointer = pointer.back;
//         }
//         System.out.println("back is " + pointer.back.key);
//         System.out.println("the front is " + pointer.front.key);
//     }
//     void front(){}
//     public static void main(String[] args) {

//         Linked1 circularList = new Linked1();
//         circularList.insert(2);
//         // circularList.printList();

//         circularList.insert(3);
//         circularList.insert(6);
//         circularList.insert(22);
//         circularList.insert(24);

//         circularList.printList();

//         circularList.insert(56);

//         circularList.printList();

//         circularList.frontAndBack(56);

//         //LinkedList collection
//         //this takes care of the front and back or next pointer by itself. Just specify
//         //the data type

//         LinkedList<Integer> linkedGeneric= new LinkedList<Integer>();

//         linkedGeneric.add(1);
//         System.out.println("getlast "+linkedGeneric.getLast());

//         // LinkedList<Node> newlinked = new LinkedList<Node>();
//         // newlinked.add(new Node(3));
//         // newlinked.add(new Node(2));
//         // newlinked.add(new Node(4));
//         // newlinked.add(new Node(5));

//         // while(newlinked.)/
//     }
// }
import java.util.*;

class Node {
    int key;
    Node next;

    Node(int d) {
        this.key = d;
    }
}

class Linked1 {
    Node head;

    Linked1(int d) {
        this.head = new Node(d);
        this.head.next = null;
    }

    void add(int d) {
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = new Node(d);
    }

    void delete(int d) {
        Node current = head;
        Node prev = null;
        while (current.key != d) {
            prev = current;
            current = current.next;
        }
        System.out.println("curr" + current.key);
        System.out.println("pev" + prev.key);
        prev.next = prev.next.next;
    }

    void printList(Linked1 list) {
        Node current = list.head;
        while (current != null) {
            System.out.print(current.key + "->");
            current = current.next;
        }
        System.out.println();
    }

    void oddEvenGroup() {
        Node odd = head;
        Node even = null;
        if (head.next != null)
            even = head.next;
        Node evencount = even;

        while (odd.next != null) {
            odd.next = evencount.next;
            odd = odd.next;
            evencount.next = odd.next;
            evencount = evencount.next;
        }

        odd.next = even;
    }

    void sortLinkedList() {

    }

    void mergeKSortedLists(int k, Linked1 list1, Linked1 list2, Linked1 list3) {
        Linked1 listK = null;
        while (list1.head.next != null || list2.head.next != null || list3.head.next != null) {
            // System.err.println("in while");
            int min = 100000000;
            for (int i = 0; i < k; i++) {
                if (list1.head.key < min) {
                    min = list1.head.key;
                    if (list1.head.next != null)
                        list1.head = list1.head.next;
                }
                if (list2.head.key < min) {
                    min = list2.head.key;
                    if (list2.head.next != null)
                        list2.head = list2.head.next;
                }
                if (list3.head.key < min) {
                    min = list3.head.key;
                    if (list3.head.next != null)
                        list3.head = list3.head.next;
                }
            }
            System.out.println("min" + min);
            listK = new Linked1(min);
        }

        printList(listK);
    }

    {
        
        class Node {
            int data;
            Node next;
            Node() {}
            Node(int d) {
                data = d;
                next = null;
            }
        } class Add_LinkedList {
            Node head;
            void printList(Node head) {
                while (head != null) {
                    System.out.print(head.data + " ");
                    head = head.next;
                }
                // System.out.println("");
            }
            public void push(int new_data) {
                /* 1 & 2: Allocate the Node &
                          Put in the data*/
                Node new_node = new Node(new_data);
                /* 3. Make next of new Node as head */
                new_node.next = head;
                /* 4. Move the head to point to new Node */
                head = new_node;
            }
            public void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                int T = sc.nextInt();
                while (T > 0) {
                    int n1 = sc.nextInt();
                    Add_LinkedList list1 = new Add_LinkedList();
                    for (int i = 1; i <= n1; i++) {
                        int a = sc.nextInt();
                        list1.push(a);
                    }
                    int n2 = sc.nextInt();
                    Add_LinkedList list2 = new Add_LinkedList();
                    for (int i = 0; i < n2; i++) {
                        int b = sc.nextInt();
                        list2.push(b);
                    }
                    Add_LinkedList list3 = new Add_LinkedList();
                    // Add g = new Add();
                    // Node rs = g.addTwoLists(list1.head, list2.head);
                    // list3.printList(rs);
                    System.out.println();
                    T--;
                }
            }
        }
        }
    /*
     * This is a function problem.You only need to complete the function given below
     */
    /*
     * The Node is defined as class Node { int data; Node next; Node(int d) {data =
     * d; next = null; } Node(){} }
     */

    // This function will add the numbers represented by two linked lists
    Node addTwoLists(Node first, Node second) {
        // Your code here
        Node holder1 = first;
        Node holder2 = second;
        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;

        while (first.next != null) {
            sum1 = (sum1 + first.key) * 10;
            first = first.next;
        }
        while (second.next != null) {
            sum2 = (sum2 + second.key) * 10;
            second = second.next;
        }

        sum3 = sum1 + sum2;

        Node head = new Node(sum3 % 10);
        sum3 = sum3 / 10;
        Node tracker = head;
        while (sum3 != 0) {
            tracker.next = new Node(sum3 % 10);
            sum3 = sum3 / 10;
            tracker = tracker.next;
        }

        return head;// 2 2 6 4 6 5 5 9 3 2 7 0 5 9 6 9 3 5 7 9 5 9
    }

    public static void main(String[] args) {
        Linked1 list = new Linked1(1);
        list.add(2);
        list.add(8);
        list.add(9);

        Linked1 lists2 = new Linked1(3);
        lists2.add(4);
        lists2.add(5);

        Linked1 lists3 = new Linked1(6);
        lists3.add(7);

        list.printList(list);
        // list.delete(4);
        // list.oddEvenGroup();
        // list.printList();

        list.mergeKSortedLists(3, list, lists2, lists3);
    }
}