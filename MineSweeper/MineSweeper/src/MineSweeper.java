import java.util.*;

public class MineSweeper {
    Random rnd = new Random();
    Scanner sc = new Scanner(System.in);
    private int row, column, mine;
    String[][] mineboard, fakeboard;

    MineSweeper(int row, int column) {
        this.row = row;
        this.column = column;
        this.mineboard = new String[this.row][this.column];
        this.fakeboard = new String[this.row][this.column];
        mine = (this.row * this.column) / 4;
    }

    public String[][] drawBoard() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                mineboard[i][j] = "-";
                fakeboard[i][j] = "-";
            }
        }
        return fakeboard;
    }

    public String[][] addMines() {
        for (int i = 0; i < mine; i++) {
            mineboard[rnd.nextInt(this.row)][rnd.nextInt(this.column)] = "*";
        }

        return mineboard;
    }

    public void showBoard() {
        drawBoard();
        addMines();
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                System.out.print(this.fakeboard[i][j] + " ");
            }
            System.out.println();
        }

    }

    public void PlayMineSweeper() {
        boolean game = true;
        System.out.println("Welcome To the Mine Sweeper");
        showBoard();
        System.out.println("Please Enter row,column to play ");
        while (game) {
            int row = sc.nextInt();
            int col = sc.nextInt();

            if (checkBorders(row, col)) {
                System.out.println("Out of bounds,please re enter row and column:");
            } else {
                if (hasMine(row, col)) {
                    updateBoard(row, col);
                    System.out.println("Enter new row,column to play ");
                    game = false;
                    System.out.println("Game Over");
                } else {
                    updateBoard(row, col);
                    System.out.println("Enter new row,column to play ");
                }
            }

            if(isVictory())
            {
                System.out.println("You Won!");
                game=false;
            }  
        }

    }

    public void lookaround(int row, int column) {
        int num=0;
        for (int i = row-1; i <=row+1 ; i++) {
            if(!(i >= 0 && i < fakeboard.length)){
                continue;
            }
            for (int j = column-1; j <= column+1; j++) {
                if((i == row && j == column) || (!(j>=0 && j < fakeboard[0].length))){
                    continue;
                }
                else{
                    if(mineboard[i][j].equals("*")){
                        num++;
                    }
                }
                
            }
        }
        fakeboard[row][column] = ""+num+"";
        
    }

    public void updateBoard(int row, int col) {

        if (this.mineboard[row][col] == "*") {
            fakeboard[row][col] = "*";
        } else {
            // fakeboard[row][col] = "0";
            lookaround(row, col);
        }

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                System.out.print(this.fakeboard[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isVictory(){
        int tile = 0;
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if(mineboard[i][j].equals("-"))
                {
                    tile++;
                }
            }
        }
        if(tile != 0)
            return false;
        else
            return true;

    }

    public boolean checkBorders(int row, int col) {
        return this.row <= row || this.column <= col ? true : false;
    }

    public boolean hasMine(int row, int column) {
        return this.mineboard[row][column] == "*" ? true : false;
    }

}
