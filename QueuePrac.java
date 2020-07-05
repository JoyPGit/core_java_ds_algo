import java.util.*;

public class QueuePrac {
    void printMatrix(int[][] arr){
        for(int i =0; i<arr.length; i++){
            for(int j =0; j< arr[0].length; j++){
                System.out.print(arr[i][j] + ", ");
            }
            System.out.println(" ");
        }
    }

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

    //[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
    public int[][] reconstructQueue(int[][] people) {
        for(int i=1; i<people.length; i++){
            int index = check(people, i);
            int h = people[i][0]; int k = people[i][1];
            System.out.println("index "+index);
            move(people, i, index);
            people[index][0] = h;
            people[index][1] = k;
            printMatrix(people);

        }
        printMatrix(people);
        return people;
    }
    
    int check(int[][] arr, int index){
        int h = arr[index][0];
        int c = arr[index][1];
        int count = 0; int index1 = 0;
        for(int i =0; i<arr.length; i++){
            if(i == index) continue;
            if(arr[i][0]>=h) count++;
            if(count == c) index1 = i;
            if(i == index && count == c) {
                return index;
            }
        }
        
        return index1;
    }
    
    void move(int[][] people, int start, int end){
        for(int i = start; i<end; i++){
            people[i][0] = people[i+1][0];
            people[i][1] = people[i+1][1];
        }
    }
    public static void main(String[] args) {
        QueuePrac queueSol = new QueuePrac();
        Deque<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3); 

        // System.out.println(queue);
        // queueSol.reverseQueue(queue);
        // System.out.println(queue);

        int[][] people = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        queueSol.reconstructQueue(people);
    }
}