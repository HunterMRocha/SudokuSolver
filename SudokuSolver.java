public class SudokuSolver {

	public static void main(String[] args){
		int[][] board = new int[][] { 
            { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, 
            { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, 
            { 0, 8, 7, 0, 0, 0, 0, 3, 1 }, 
            { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, 
            { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, 
            { 0, 5, 0, 0, 9, 0, 6, 0, 0 }, 
            { 1, 3, 0, 0, 0, 0, 2, 5, 0 }, 
            { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, 
			{ 0, 0, 5, 2, 0, 6, 3, 0, 0 }};

		System.out.print("Sudoku Board Before Backtracking Algorithm...");
		printBoard(board);
		solve(board);
		System.out.print("Sudoku Board After Backtracking Algorithm...");
		printBoard(board);



	}


	public static void printBoard(int[][] board){
		for(int i = 0; i < board.length; i++){
			System.out.println();
			if(i % 3 == 0 && i != 0){
				System.out.println("- - - - - - - - - - -");

			}
			for(int j = 0; j < board[0].length; j++){
				if(j % 3 == 0 && j != 0){
					System.out.print("| ");

				}
				System.out.print(board[i][j] + " ");

			}
		}
		System.out.println("\n");
	}

	public static int[] findEmpty(int[][] board){
		int[] emptyPos = new int[2];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if(board[i][j] == 0){
					emptyPos[0] = i; //this will return the row index
					emptyPos[1] = j; //this will return the col index
					return emptyPos; //return an arr with the row/col index of the next 'open' spot
				}
			}
		}
		return null;
	}

	public static boolean valid(int[][] board, int[] pos, int val){
		int ROW = pos[0];
		int COL = pos[1];

		//check the row
		for(int i = 0; i < board.length; i++){
			if(board[ROW][i] == val && COL != i){
				return false; 
			}
		}

		//check the col
		for(int j = 0; j < board[0].length; j++){
			if(board[j][COL] == val && ROW != j){
				return false; 
			}
		}

		//check the box (double check this)
		int box_x = COL / 3;  
		int box_y = ROW / 3;  
		for(int i = box_y*3; i < box_y*3+3; i++){
			for(int j = box_x*3; j < box_x*3+3; j++){
				if(board[i][j] == val && ROW != i && COL != j){
					return false;
				}
			}
		}
		return true;
	}

	public static boolean solve(int[][] board){

		int[] openSpot = findEmpty(board);
		int ROW, COL;
		//base case
		if(findEmpty(board) == null){
			return true; //this will only happen when we have solved the entire board
		}else{
			ROW = openSpot[0];
			COL = openSpot[1];
		}

		//this is where we are going to solve the board 
		for(int i = 1; i <= 9; i++){ //loop through rows
			if(valid(board, openSpot, i)){
				board[ROW][COL] = i;
				if(solve(board)){
					return true;
				}
				board[ROW][COL] = 0;
			}
		}

		return false; 
	}


}