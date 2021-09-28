import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

	public static void main(String[] args) {
		startGame();
	}
	
	private static void startGame() {
		System.out.print("Welcome to Tic Tac Toe! Choose the size of the board (Min 3):");
		Scanner sc = new Scanner(System.in);
		System.out.println();
		int size = sc.nextInt();
		while(size < 3){
			System.out.println("Please choose a size bigger than 3.");
			size = sc.nextInt();
		}
		
		char[][] board = generateBoard(size);
		showBoard(board);
		System.out.println("First player will be chosen randomly...");
		Random r = new Random();
		int first = r.nextInt(2);
		char currentPlayer = (first == 0) ? 'X' : 'O';
		System.out.println("First player to play isss " + currentPlayer);
		boolean someoneWon = false;
		boolean draw = false;
		while(!someoneWon && !draw) {
			makeMove(board, currentPlayer);
			showBoard(board);
			someoneWon = didSomeoneWin(board, currentPlayer);
			draw = isDraw(board);
			currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
		}
		
		currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
		
		if(someoneWon) {
			System.out.println("Winner is Player " + currentPlayer + ". Congratulations!");
		}
		else if(draw){
			System.out.println("Its a draw. Booring..");
		}
	}
	
	private static void showBoard(char[][] board) {
		for(int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for(int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
		}
	}
	
	private static char[][] makeMove(char[][] board, char currentPlayer){
		System.out.print("Player " + currentPlayer + ", which row do you want to place your mark in (1-" + board.length + ")?");
		Scanner sc = new Scanner(System.in);
		System.out.println();
		int row = sc.nextInt();
		
		System.out.print("Player " + currentPlayer + ", which column in row " + row + " do you want to place your mark in (1-" + board.length + ")?");
		System.out.println();
		int col = sc.nextInt();
		
		while(board[row-1][col-1] != '-') {
			System.out.print("This place is filled. Please pick again.");
			System.out.print("Player " + currentPlayer + ", which row do you want to place your mark in (1-" + board.length + ")?");
			System.out.println();
			row = sc.nextInt();
			
			System.out.print("Player " + currentPlayer + ", which column in row " + row + " do you want to place your mark in (1-" + board.length + ")?");
			System.out.println();
			col = sc.nextInt();
		}
		
		board[row-1][col-1] = currentPlayer;
		
		return board;
	}
	
	private static boolean didSomeoneWin(char[][] board, char currentPlayer) {
		int equal = board.length;
		for(int i = 0; i < board.length; i++) {
			equal = board.length;
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == currentPlayer)
					equal--;
			}
			if(equal == 0)
				return true;
		}
		
		for(int i = 0; i < board.length; i++) {
			equal = board.length;
			for(int j = 0; j < board[i].length; j++) {
				if(board[j][i] == currentPlayer)
					equal--;
			}
			if(equal == 0)
				return true;
		}
		
		equal = board.length;
		for(int i = 0; i < board.length; i++) {
			if(board[i][i] == currentPlayer)
				equal--;
		}
		
		if(equal == 0)
			return true;
		
		equal = board.length;
		int counter = board.length - 1;
		for(int i = 0; i < board.length; i++) {
			if(board[i][counter] == currentPlayer) 
				equal--;
			counter--;
		}
		
		if(equal == 0)
			return true;
		
		return false;
	}
	
	private static boolean isDraw(char[][] board) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == '-')
					return false;
			}
		}
		
		return true;
	}
	
	private static char[][] generateBoard(int size){
		char[][] board = new char[size][size];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = '-';
			}
		}
		return board;
	}
}
