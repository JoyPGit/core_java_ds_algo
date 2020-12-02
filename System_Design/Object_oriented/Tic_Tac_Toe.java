package System_Design.Object_oriented;

import java.util.*;
// use .java 
// java Tic_Tac_Toe.java
// 1, 2, 2, 
// 1, 1, 1, 
// 2, 1, 2, 
public class Tic_Tac_Toe {
    HashMap<String, Integer> scoreCard = new HashMap<>();
    boolean gameOver = false;
    Board currBoard;
    int currentPlayer = 2;
    int moves = 0;
    // one game instance mapped to one board?
    Tic_Tac_Toe(){
        System.out.println("match starting");
    }

    void startMatch(){
        Scanner input = new Scanner(System.in);

        System.out.println("player 1 enter your name");
        String s1 = input.nextLine();
        System.out.println("player 2 enter your name");
        String s2 = input.nextLine();
        System.out.println("enter board size");
        int size = Integer.parseInt(input.nextLine());
        // how to take input
        // player class instantiation
        // mapping
        Player p1 = new Player(s1, 0, 0);
        Player p2 = new Player(s2, 0, 0);

        // mapping players to board
        currBoard = new Board(size, p1, p2);

        // no need to maintain gameOver, break
        while(true){
            currentPlayer = currentPlayer == 1?2:1;
            currBoard.nextMove(currentPlayer, input);
            System.out.println("in here");
            this.moves++;
            currBoard.printBoard();
            if(currBoard.checkWinner(currentPlayer)){
                System.out.println("winner is "+ currentPlayer);
                break;
            }
            
            if(this.moves == 9) {
                System.out.println("no winner found, match draw");
                break;
            }
        }
        
        input.close();//
    }

    public static void main(String[] args) {
        Tic_Tac_Toe newGame = new Tic_Tac_Toe();
        newGame.startMatch();
    }

}

// is valid can be kept in runner
// moves
// how to noify if winner is found?
// issue with runner is row col need to be passed back to runner again after
// taking input from user in nextMove

class Board{
    int[][] board;
    int boardSize;
    

    Player p1; Player p2;
    // using int board, and p1 has symbol 1
    Board(int size, Player a, Player b){
        this.boardSize = size;
        this.p1 = a; this.p2 = b; 
        board = new int[boardSize][boardSize];
        System.out.println("board of size "+size+"*"+size+ " created.");
    }
    
    void printBoard(){
        for(int i =0; i<boardSize; i++){
            for(int j = 0; j<boardSize; j++){
                System.out.print(board[i][j]+", ");
            }
            System.out.println();
        }
    }

    int[] nextMove(int playerNumber, Scanner input){
        int[] indexes = new int[2];
        indexes = askForPosition(playerNumber, input);

        int row = indexes[0];
        int col = indexes[1];
        System.out.println("line 86 "+ row+ " "+col);

        if(row>=0 && row<this.boardSize
        && col>=0 && col<this.boardSize
        && this.board[row][col] == 0) {
            // assign
            System.out.println("in next move");
            this.board[row][col] = playerNumber;
            return new int[]{row, col};
            // checkWinner(playerNumber, row, col);
        }

        // if incorrect, ask again
        else{
            System.out.println("incorrect indexes entered player "+playerNumber);
            return nextMove(playerNumber, input);
        }
    }

    int[] askForPosition(int playerNumber, Scanner input){
        System.out.println("player "+playerNumber+ " enter row ");
        int x = Integer.parseInt(input.nextLine());
        System.out.println("player "+playerNumber+ " enter col ");
        int y = Integer.parseInt(input.nextLine());
        return new int[]{x,y};
    }

    // int[] row; int[] col int[] dia 
    // for faster check the row, col need to be passed
    // count needs to be stored for checking
    boolean checkWinner(int playerNumber){
        int count = 0;
        // check row
        for(int i =0; i<boardSize; i++){
            if(this.board[0][i] == playerNumber) count++;
        }
        if(count == this.boardSize) return true;
        count = 0;

        for(int i =0; i<boardSize; i++){
            if(this.board[1][i] == playerNumber) count++;
        }
        if(count == this.boardSize) return true;
        count = 0;

        for(int i =0; i<boardSize; i++){
            if(this.board[boardSize-1][i] == playerNumber) count++;
        }
        if(count == this.boardSize) return true;
        count = 0;

        // check col
        for(int i =0; i<boardSize; i++){
            if(this.board[i][0] == playerNumber) count++;
        }
        if(count == this.boardSize) return true;
        count = 0;

        for(int i =0; i<boardSize; i++){
            if(this.board[i][1] == playerNumber) count++;
        }
        if(count == this.boardSize) return true;
        count = 0;

        for(int i =0; i<boardSize; i++){
            if(this.board[i][boardSize-1] == playerNumber) count++;
        }
        if(count == this.boardSize) return true;
        count = 0;

        // if(col!=1 && row!=1){
            // check "\" dia
            for(int i =0, j=0; i<boardSize && j<boardSize; i++, j++){
                if(this.board[i][j] == playerNumber) count++;
            }
            if(count == this.boardSize) return true;
            count = 0;
            // check "/" dia
            for(int i =0, j=boardSize-1; i>=0 && j>=0; i++, j--){
                if(this.board[i][j] == playerNumber) count++;
            }
            if(count == this.boardSize) return true;
            count = 0;
        // }
        // no conflict, winner found
        return false;
    }

    
}

class Player{
    String name;
    int scores; 
    int moveCount;
    Player(String name, int score, int m){
        this.name = name;
        this.scores = score;
        this. moveCount = m;
    }
}




//////////////////////////////////////////////////////////

// how to map a game instance to a board
// multiple games, so multiple boards?
// board size fixed?, where to instantiate? in board or game class?
// space time complexity
// undo?
// statistics
// rating
// to map game to players, use gameid
// list of moves, undo or history

// https://www.youtube.com/watch?v=VSkaACPWogo


// imp ques-> each board one game?
// or each game class instance holds one game?

// shoudl i have a runner class?

// moves and winner?
