import java.util.*;

public class QueuePrac {
    
    void reverseQueue(Deque<Integer> queue){
        if(queue.isEmpty()) return;
        int x = queue.pop();
        // reverseQueueHelper(queue);
        reverseQueue(queue);
        queue.add(x);
    }


    class custom6junleetcode{
        int x, y;

        custom6junleetcode(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    // Queue<> sort6JunLeetCode(Queue<Integer> list){

    //     int[] holder = new int[list.size()];
    //     int i;
    //     for(i =0; i<list.size(); i++){
    //         holder
    //     }
    //     return list;
    // }
    void reverseQueueHelper(){}
    public static void main(String[] args) {
        QueuePrac queueSol = new QueuePrac();
        Deque<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3); 

        System.out.println(queue);
        queueSol.reverseQueue(queue);
        System.out.println(queue);
    }
}