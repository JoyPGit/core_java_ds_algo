package Java_DS_Algo;

import java.util.*;

public class Design {
    // https://leetcode.com/problems/design-hit-counter/

    // https://leetcode.com/problems/insert-delete-getrandom-o1/

    // https://leetcode.com/problems/implement-trie-prefix-tree/
    
    // https://leetcode.com/problems/lru-cache/


    /** 
     * tail always points to last node.
     * for deleting el just set haed = head.next or tail = tail.prev;
     * list.size() doesn't work use 2 flags to keep track of size
     * https://leetcode.com/problems/design-circular-deque/discuss/1078232/
     * Java-or-Doubly-LinkedList-or-99-faster-or-easy-to-read
    */
    // https://leetcode.com/problems/design-circular-deque/
    class Node{
        int value; Node prev, next;
        Node(int v){
            this.value = v;
            this.prev = null; this.next = null;
        }
    }
    
    class MyCircularDeque {

        /** Initialize your data structure here. Set the size of the deque to be k. */
        LinkedList<Node> list;
        Node head, tail;
        int size, capacity;

        /** Initialize your data structure here. Set the size of the deque to be k. */
        public MyCircularDeque(int k) {
            this.list = new LinkedList<Node>();
            this.size = 0;
            this.capacity = k;
        }

        /**
         * Adds an item at the front of Deque. Return true if the operation is
         * successful.
         */
        public boolean insertFront(int value) {
            if (isFull())
                return false;

            Node n = new Node(value);
            if (isEmpty()) {
                head = n;
                tail = head;
            }
            head.prev = n;
            n.next = head;
            head = n;
            this.size++;
            return true;
        }

        /**
         * Adds an item at the rear of Deque. Return true if the operation is
         * successful.
         */
        public boolean insertLast(int value) {
            if (isFull())
                return false;
            Node n = new Node(value);

            if (isEmpty()) {
                head = n;
                tail = n;
            }

            tail.next = n;
            n.prev = tail;
            tail = n;
            this.size++;
            return true;
        }

        /**
         * Deletes an item from the front of Deque. Return true if the operation is
         * successful.
         */
        public boolean deleteFront() {
            if (isEmpty())
                return false;
            head = head.next;
            size--;
            return true;
        }

        /**
         * Deletes an item from the rear of Deque. Return true if the operation is
         * successful.
         */
        public boolean deleteLast() {
            if (isEmpty())
                return false;
            tail = tail.prev;
            size--;
            return true;
        }

        /** Get the front item from the deque. */
        public int getFront() {
            if (isEmpty())
                return -1;
            return head.value;
        }

        /** Get the last item from the deque. */
        public int getRear() {
            if (isEmpty())
                return -1;
            return tail.value;
        }

        /** Checks whether the circular deque is empty or not. */
        public boolean isEmpty() {
            return size == 0;
        }

        /** Checks whether the circular deque is full or not. */
        public boolean isFull() {
            return size == capacity;
        }
    }
    /** 
     * use rows[] and cols[] and ldia and rdia to keep track of sum;
     * use Math.abs
    */
    // https://leetcode.com/problems/design-tic-tac-toe/
    class TicTacToe {
        int[][] board;
        int [] rows, cols; 
        int target, ldia, rdia;
        
        /** Initialize your data structure here. */
        public TicTacToe(int n) {
            board = new int[n][n];
            rows = new int[n];
            cols = new int[n];
            
            target = n; ldia = 0; rdia = 0;
        }
        
        /** Player {player} makes a move at ({row}, {col}).
            @param row The row of the board.
            @param col The column of the board.
            @param player The player, can be either 1 or 2.
            @return The current winning condition, can be either:
                    0: No one wins.
                    1: Player 1 wins.
                    2: Player 2 wins. 
        */
        public int move(int row, int col, int player) {
            if(player == 2) player = -1;
            rows[row]+=player;
            cols[col]+=player;
            if(row == col) ldia+=player; 
            if(row+col == target-1) rdia+=player;
            
            // System.out.println(rows[row]+" "+ cols[col]+" "+ldia+" "+rdia+" "+player);
            if((Math.abs(rows[row]) == target) || (Math.abs(cols[col]) == target)
               || Math.abs(ldia) == target || Math.abs(rdia) == target)
                return player==-1?2:1;
            else return 0;
        }
    }

    // https://leetcode.com/problems/stream-of-characters/ 
    // https://leetcode.com/problems/implement-magic-dictionary/
    // https://leetcode.com/problems/replace-words/
    // https://leetcode.com/problems/design-add-and-search-words-data-structure/

}
