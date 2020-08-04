import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

class StackNode{
    
    int key;
    StackNode next;
    StackNode previous;

    StackNode(int value){
        this.key = value;
    }
}

public class Stack1{
    
    StackNode top;
    
    void pushNode(int value1){
        StackNode newNode = new StackNode(value1);
        if(top==null){
            top = newNode;
            top.next = null;
            top.previous = null;
        } else if(top.next == null){
            top.next = newNode;
            top.next.previous = top;
        } else {
            StackNode tracker = top;
            while(tracker.next!=null){
                tracker= tracker.next;
                // System.out.println("in push "+tracker.key);
            }
            // System.out.println("out "+tracker.key);
            tracker.next = newNode;
            tracker.next.previous = tracker;
            tracker.next.next = null;
            // System.out.println("ag "+top.key);
        }
    }
    
    void popNode(){
        if(top == null){
            // System.out.println("no node to pop");
        } else {
            // System.out.println("value "+top.key);
            
            top = top.next;
            top.previous.next = null;
            top.previous = null;
        }
    }

    void printAll(){
        StackNode tracker = top;
        while(tracker!=null){
            System.out.println(tracker.key);
            tracker = tracker.next;
        }
    }

    // int noOfBracketReversals(int[] arr, Stack1 stack){
    //     for(int i =0; i<arr.length; i++){
    //         if(arr[i]=)
    //     }
    // }

    void deleteMiddle(Deque<Integer> stack){
        int n = stack.size();
        if(n==0) return;
        int m = n/2+1;
        
        deleteMiddleHelper(stack, n, m);
    }

    void deleteMiddleHelper(Deque<Integer> stack, int n, int m){
        if(n==m) {
            System.out.println("the middle is "+stack.pop());
            return;
        }
        int x = stack.pop();
        deleteMiddleHelper(stack, n-1, m);
        stack.push(x);
    }
   
    /**
     * https://www.geeksforgeeks.org/form-minimum-number-from-given-sequence/
     *  Input: D        Output: 21
        Input: I        Output: 12
        Input: DD       Output: 321
        Input: II       Output: 123
        Input: DIDI     Output: 21435
        Input: IIDDD    Output: 126543
        Input: DDIDDIID Output: 321654798 
     */

    void printPattern (String s){
        char[] charArray = s.toCharArray();
        System.out.print(charArray);
        Deque<Integer> intStack = new LinkedList<>();
        Deque<Integer> intStackPopHolder = new LinkedList<>();

        int counter = 0;

        boolean first = true;
        for(char c:charArray){

            System.out.print(c+" ");
            System.out.println(intStack);
            if(c == 'I'){
                counter++;
                if(first){
                    intStack.push(1);
                    intStack.push(2);
                    first = false;
                    counter++;
                } else{
                    intStack.push(counter);
                }
                

            } else if(c == 'D'){
                counter++;
                System.out.print("counter "+counter);
                if(first){
                    intStack.push(2);
                    intStack.push(1);
                    first = false;
                    counter++;
                } else{
                    while(!intStack.isEmpty() && intStack.peekFirst() != (counter-1)){
                        intStackPopHolder.push(intStack.pop());
                    }
                    int holeder = intStack.pop();
                    intStack.push(counter);
                    intStack.push(holeder);
                    while(!intStackPopHolder.isEmpty()){
                        intStack.push(intStackPopHolder.pop());
                    }
                    System.out.println(" line 140 " +intStack);
                }
            }
        }

        while(!intStack.isEmpty()){
            System.out.print(intStack.pop());
        }
    }

    /**  
     * 1 first go add 12 or 21
     * 2 then for 'D' shift and add
     * 3 check for all consecutive 'D's, move right as many no of times
     * 4 if possible hold a counter, when I comes reset to -1, else hold preious 'D'
     */
    // void printPattern27Jul(String s){
    //     char[] ch  = s.toCharArray();
    //     ArrayList<Integer> list = new ArrayList<>();
    //     int index = 0; int Dcounter = 0;
    //     for(int i =0; i<ch.length; i++){
    //         if(index ==0){
    //             if(ch[i] == 'I') {
    //                 list.add(1); list.add(2);
    //             } else if(ch[i] == 'D'){
    //                 list.add(2); list.add(1);
    //             }
    //         }else{
    //             if(ch[i] == 'D'){
    //                 swapList(list, )
    //             }else if(ch[i] == 'I'){

    //             }
    //         }
    //         index = index+2;

    //     }
    // }

    void longestCorrectBracketSubsequence(String s){
        char[] charArray = s.toCharArray();
        int counter = 0; int maxCounter = 0; int max = 0;
        for(char c:charArray){
            if(c=='('){
                counter++;
            } else{
                maxCounter = counter;
                counter--;
            }
            if(counter <0){
                maxCounter =0;
            }
            if(max >maxCounter) max = maxCounter;
        }
        System.out.println("max is "+max);
    }
    public static void main(String[] args) {
        Stack1 stackSolution = new Stack1();
        // stackSolution.printPattern("DDIDDIID");
        stackSolution.longestCorrectBracketSubsequence("())(())(())(");
        // stack1.pushNode(1);
        // stack1.pushNode(12);
        // stack1.pushNode(3);
        // stack1.pushNode(4);
        // stack1.printAll();
        // stack1.popNode();
        // stack1.printAll();
        // System.out.println("--> "+stack1.top.key);
        // stack1.pushNode(5);
        // stack1.printAll();
        
        // Stack<Integer> stackExample = new Stack<Integer>();
        Deque<Character> stackExample = new LinkedList<Character>();
        stackExample.push('1');
        stackExample.push('2');
        // System.out.println(stackExample.peekFirst());
        // System.out.println(stackExample.pop());
        stackExample.push('3');
        // System.out.println(stackExample.peekFirst());

        // Deque<Integer> stack = new LinkedList<>();
        // stack.push(1);
        // stack.push(2);
        // stack.push(3);
        // stack.push(4);
        // stack.push(5);
        // stack.push(6);
        // stack.push(7);
        // stack.push(8);
        // stack.push(9);

        // stackSolution.deleteMiddle(stack);
        // System.out.println(stack);
    }

    
}