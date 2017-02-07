package recursion.assignment.NQueens;

import java.util.Arrays;

public class NQueens {
	
	public static void main(String[] args) {
		char[][] chessBoard= new char[5][5];
		for(int i=0; i<chessBoard.length;i++){
			chessBoard[i]= new char[chessBoard.length];
			Arrays.fill(chessBoard[i], 'X');
		}
		solveNQueensUsingBackTracing(chessBoard,5);
		
	}

	private static void solveNQueensUsingBackTracing(char[][] chessBoard, int i) {
		
		int col=0; int row=0;
		
		placeQueens(chessBoard,col,row);
		
	}

	private static void placeQueens(char[][] chessBoard, int col, int row) {
		
		for(int column=col;column<chessBoard.length; column++){
			for(int currentRow=row; row<chessBoard.length; row++){
				if(chessBoard[column][row]=='X'){
					
				}
			}
		}
		
	}

}
