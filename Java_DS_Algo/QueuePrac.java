package Java_DS_Algo;

import java.util.*;

public class QueuePrac {
    
    // implement stack using queue
    class StackImpl{
        Deque<Integer> q;

        StackImpl(){
            this.q = new LinkedList<>();
        }
        void push(int num){
            int size = q.size();
            q.addLast(num);
            for(int i =0; i<size; i++){
                q.addLast(q.removeFirst());
            }
            System.out.println(this.q);
        }
        int pop(){
            System.out.println(q.getFirst());
            return q.removeFirst();
        }
    }

    // implement queue using stack
    class QueueImpl{
        Deque<Integer> stack;
        Deque<Integer> holder;
        QueueImpl(){
            this.stack = new LinkedList<>();
            this.holder = new LinkedList<>();
        }

        void enQueue(int num){
            this.stack.addLast(num);
            System.out.println(this.stack);
        }

        // making pop costly
        int deQueue(){
            while(stack.size()!=0) holder.addLast(stack.removeLast());
            System.out.println(holder.getLast());
            return holder.removeLast();
        }
    }

    public static void main(String[] args) {
        QueuePrac qprac = new QueuePrac();
        // StackImpl stack = qprac.new StackImpl();
        // stack.push(1); stack.push(2); stack.push(3); 
        // stack.pop();
        // stack.push(4);

        QueueImpl q = qprac.new QueueImpl();
        q.enQueue(2); q.enQueue(3); q.enQueue(4);
        q.deQueue();
    }
}
