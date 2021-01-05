package Java_DS_Algo;

import java.util.*;

public class QueuePrac {
    
    // defining class inside makes it a class variable, instantiate to use
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

    /** 
     * 1 sort on the basis of graeter height and lesser k
     * 2 insert els from the array into an arrayList, why?
     * because in arraylist els can be added at a specific position
     * 
     * imp : res.add(people[i][1], people[i]);
     *       res.add(people[i][1], new int[]{people[i][0], people[i][1]});
     * 
     * adding reference causes problems, make a new entry
     * 
     * 3 copy from arraylist into 'people' array
    */
    // https://leetcode.com/problems/queue-reconstruction-by-height
    // class Node{
    //     int a, b, index;
    //     Node(int a, int b, int i){
    //         this.a = a; this.b = b; this.index = i;
    //     }
    // }
    public int[][] reconstructQueue(int[][] people) {
        // List<Node> list = new ArrayList<>();
        int n = people.length;
        
        /**
        for(int i =0; i<n; i++){
            list.add(new Node(people[i][0], people[i][1], i));    
        }
        Collections.sort(list, (x, y)->{
            if(x.a == y.a) return x.b - y.b;
            return y.a - x.a;
        });
        */
        
        Arrays.sort(people, (x, y)->{
            if(x[0] == y[0]) return x[1] - y[1];
            return y[0] - x[0];
        });
        
        List<int[]> res = new ArrayList<>();
        for(int i = 0; i<n; i++){
            // res.add(list.get(i).b, list.get(i));
            // res.add(people[i][1], people[i]);
            res.add(people[i][1], new int[]{people[i][0], people[i][1]});
        }
        
        int i = 0;
        for(int[] x : res){
            people[i][0] = x[0]; people[i++][1] = x[1];
        }
        return people;
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
