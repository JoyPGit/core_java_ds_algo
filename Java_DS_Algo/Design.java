package Java_DS_Algo;

public class Design {
    // https://leetcode.com/problems/design-hit-counter/

    // https://leetcode.com/problems/insert-delete-getrandom-o1/

    // https://leetcode.com/problems/implement-trie-prefix-tree/
    
    // https://leetcode.com/problems/lru-cache/

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
