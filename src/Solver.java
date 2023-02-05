public class Solver {
    private int[][] board;
    private static final int SIZE = 9;

    Solver(int[][] board){
        this.board = board;
    }

    private void printBoard(int[][] board) {
        for(int row = 0; row < SIZE; row++){
            if(row % 3 == 0 && row != 0){
                System.out.println("---+---+---");
            }
            for(int column = 0; column < SIZE; column++){
                if(column % 3 == 0 && column !=0){
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    private boolean numberInRow(int[][] board, int number, int row){
        //Compare a number with all the numbers in a row
        for(int i = 0; i < SIZE; i++){
            if(board[row][i] == number){
                return true;
            }
        }
        return false;
    };

    private boolean numberInColumn(int[][] board, int number, int column){
        //Compare a number with all the number in a column
        for(int i = 0; i < SIZE; i++){
            if(board[i][column] == number){
                return true;
            }
        }
        return false;
    };

    private boolean numberInBox(int[][] board, int number, int row, int column){
        //Get top left corner of a box (3x3 space)
        //Every box row and column can be: 1, 2, 3
        //Compare a number if it appears in a box
        int boxRow = row - row % 3;
        int boxColumn = column - column % 3;

        for (int i = boxRow; i < boxRow + 3; i++){
            for (int j = boxColumn; j < boxColumn + 3; j++){
                if(board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    };

    private boolean validNumber(int[][] board, int number, int row, int column){
        //We need to negate the methods because they will return false if a number is valid by that rule
        return !numberInRow(board, number, row) &&
                !numberInColumn(board, number, column) &&
                !numberInBox(board, number, row, column);
    }

    private boolean solver(int[][] board){
        //Backtracking algorithm
        for (int row = 0; row < SIZE; row++){
            for(int column = 0; column < SIZE; column++){
                //Check if current location is empty
                if(board[row][column] == 0){
                    //If empty try out numbers
                    for(int numberToTry = 1; numberToTry <= SIZE; numberToTry++){
                        //If it is a valid placement
                        if(validNumber(board,numberToTry, row, column)){
                            //If it's valid put the number in
                            board[row][column] = numberToTry;

                            if(solver(board)){
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void solve(){
        if(solver(board)){
            printBoard(board);
            System.out.println("Solved");
        } else {
            System.out.println("Unsolvable");
        }
    }

}
