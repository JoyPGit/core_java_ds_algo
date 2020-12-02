package System_Design.Object_oriented;

import java.util.*;



public class Tic_Tac_Toe_2 {
    class TicPlayer {
        String name; int moves; int type;
        TicPlayer(String str, int t){
            this.name = str;
            this.type = t;
        }
        /** 
         * row[], col[] can store values, 
         * so if any time any row[index]>=size, winner is found
        */
    }
    /** 
     * of size 3 only, will expand later
     * check winner only if score >=3
     * askForMove
     * makeMove(mark board)
     * updateStats
     * checkWinner
     * 
     * use currentPlayer and update after makeMove
     * 
    */
    int[][] board; int size;
    TicPlayer p1; TicPlayer p2;
    TicPlayer currentPlayer;
    TicPlayer winner;
    int totalMoves;
    /** 
     * a commmon rowSum, colSum, leftDia and rightDia for both players
    */
    int[] rowSum; int[] colSum; int ldia; int rdia; 

    Scanner input;
    Tic_Tac_Toe_2(){
        this.input = new Scanner(System.in);
        // ask for p1 name
        // ask for p2 name
        System.out.println("player 1 enter your name");
        String s1 = input.nextLine();
        System.out.println("player 2 enter your name");
        String s2 = input.nextLine();

        System.out.println("enter board size");
        this.size = Integer.parseInt(input.nextLine());

        p1 = new TicPlayer(s1, 1);
        p2 = new TicPlayer(s2, -1);

        this.board = new int[this.size][this.size];
        this.rowSum = new int[this.size];
        this.colSum = new int[this.size];
        this.ldia = 0; this.rdia = 0;
        this.totalMoves = 0;
        this.currentPlayer = p1;
        this.winner = null;
    }

    void printBoard(){
        for(int i =0; i<this.size; i++){
            for(int j = 0; j<this.size; j++){
                System.out.print(this.board[i][j]+", ");
            }
            System.out.println();
        }
    }

    void askForMove(){
        System.out.println(this.currentPlayer.name +" enter row");
        int row = Integer.parseInt(input.nextLine());
        System.out.println(this.currentPlayer.name +" enter col");
        int col = Integer.parseInt(input.nextLine());

        // while or if?
        if(!isValid(row, col)){
            // placement
            System.err.println("incorrect indexes entered, pls check board.");
            askForMove();
        }else{
            this.updateBoard(currentPlayer.type, row, col);
            // 2*(size -1) calls saved
            if(this.currentPlayer.moves>=this.size && this.checkWinner(row, col)){
                System.out.println("winner is "+ this.currentPlayer.name);
                this.winner = this.currentPlayer;
            }
            this.printBoard();
            this.currentPlayer = this.currentPlayer == p1?p2:p1;
        }
    }
    

    boolean isValid(int row, int col){
        if(row>=0 && row<this.size
        && col>=0 && col<this.size
        && this.board[row][col] == 0) return true;
        return false;
    }

    /**
     * update stats for the currentPlayer
     * @param row
     * @param col
     * mark ldia and rdia
     * row == col and row == size - col - 1 works.
    */
    void updateBoard(int type, int row, int col){
        // +1 for p1, -1 for p2
        this.currentPlayer.moves += this.currentPlayer.type;

        // 1,1 is tricky
        if(row == col) ldia+=type;
        else if(row == this.size -col-1) rdia+=type;
        this.rowSum[row]+=type;
        this.colSum[col]+=type;
        this.board[row][col] = this.currentPlayer.type;
        this.totalMoves++;
        System.out.println("board vars updated");
    }


    // call only when player1's score >=2
    boolean checkWinner(int row, int col){
        if(this.rowSum[row] == Math.abs(this.size) 
        || this.colSum[col] == Math.abs(this.size)
        || this.ldia == Math.abs(this.size)
        || this.rdia == Math.abs(this.size)) return true;
        return false;    
    }

    /** 
     * game initializer
    */
    void play(){
        while(this.winner==null){
            this.askForMove();
            if(this.totalMoves == (this.size)*(this.size)) {
                System.out.println("match is a draw.");
                break;
            }
        }
        this.input.close();
    }

    public static void main(String[] args) {
        Tic_Tac_Toe_2 updatedGame = new Tic_Tac_Toe_2();
        updatedGame.play();
    }

}


/** 
 * 1 how to keep the game going? while loop?
 * 2 time limit?
 * 3 undo?
 * 
 * use class, can make generic
 * use currentPlayer
 * score can be -ve but value in each player's row can't be
 * when to close input?
 * draw? 
*/

/** 
 * issues faced :
 * 1 how to identify who is the current player
 * 2 how to start the game
 * 3 significance of player class, initialize vars
 * 4 currentPlayer set to p2, instead of p1
 * 5 
 * 1, 0, -1, 
 * 0, 1, -1, 
 * 1, 0, 0, 
 * winner found
 * 
 * 7 
 * 1, 1, -1,
 * 1, -1, 1, 
 * -1, -1, 1, 

 * 6 draw?
 * 
 * 7 Exception throw
*/

/** 
 * optimization:
 * single rowSum, colSUm
 * call only when moves>=size, atleast 3
*/

/**
 * scalable?
 * 1 sharding
 * 2 consistent hashing
 * 3 rate limiter, one player one game
 */