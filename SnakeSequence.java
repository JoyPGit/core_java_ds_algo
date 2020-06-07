class SnakeSequence{

    int count;

    int visited;

    void SnakeSequencelength(int[][] arr, int a, int b){
        moveRight(arr, a, b);
        moveDown(arr, a, b);
        System.out.println("count "+ count);
    }

    boolean closeEnough(int n1, int n2){
        if((n1 - n2 == 1) || (n2-n1 ==1)){
            System.out.println(n1 +"  "+ n2);
            return true;
        } else return false;
    }

    void moveRight(int[][] arr, int a, int b){
        if(a<4 && b<4 && b+1 <4){
            if(closeEnough(arr[a][b], arr[a][b+1])){
                this.count++;
                SnakeSequencelength(arr, a, b+1);       
            }
        }
    }
    

    void moveDown(int[][] arr, int a, int b){
        if(a<4 && b<4  && a+1 <4){
            if(closeEnough(arr[a][b], arr[a+1][b])){
                this.count++;
                SnakeSequencelength(arr, a+1, b);       
            }
        }
    }

    public static void main(String[] args) {
        SnakeSequence nitesh = new SnakeSequence();

        int[][] arr = {{0, 6, 5, 2},
            {8, 7, 6, 5},
            {7, 3, 1, 6},
            {1, 1, 1, 7}};

        nitesh.SnakeSequencelength(arr, 0, 0);
    }
}