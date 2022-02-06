import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class IteratorTrial {
    class Node<T extends Number>{
        Node next; T val;
        Node(T v){
            this.val = v;
        }
    }

    class LinkedList<T extends Number> implements Iterable{
        Node head, ptr;

        LinkedList(){
            ptr = head;
        }

        void addNode(T val){
            if(head == null){
                head = new Node(val);
                ptr = head;
            }
            else {
                ptr.next = new Node(val);
                ptr = ptr.next;
            }
        }

        void deleteFromStart(){
            Node q = head.next;
            head.next = q;
            head = q;
        }

        void printList(){
            Node q= head;
            while(q!=null){
                System.out.print(q.val+"->");
                q = q.next;
            }
        }

        Node<T> getHead(){
            return this.head;
        }

        @Override
        public Iterator iterator() {
            return null;
        }

    }

    // nested class instantiation
    // make T extend Number
    // need not be T, var will do
    class ListIterator<T extends Number> implements Iterator{
        Node<T> node;
        int index = 0;

        ListIterator(LinkedList<T> list){
            this.node = list.getHead();
        }

        @Override
        public boolean hasNext() {
            if(node.next!=null) return true;
            return false;
        }

        @Override
        public Object next() {
            if(hasNext()) {
                this.node = node.next;
                return this.node.val;
            }
            return null;
        }
    }


    public static void main(String[] args) {
        IteratorTrial iteratorTrial = new IteratorTrial();
        LinkedList linkedList = iteratorTrial.new LinkedList();
        linkedList.addNode(1.1); linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.printList();
        System.out.println();
        ListIterator listIterator = iteratorTrial.new ListIterator<>(linkedList);
        while(listIterator.hasNext()){
            System.out.println(listIterator.next());
        }
    }

}
