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
		System.out.println("Press 0 for PVP or Press 1 for Singleplayer:");
		int cpuPvp= sc.nextInt();
		char player = '-';
		char cpu = '-';
		while(cpuPvp > 1 || cpuPvp < 0) {
			System.out.println("Wrong Choice");
			System.out.println("Press 0 for PVP or Press 1 for Singleplayer:");
			cpuPvp = sc.nextInt();
		}
		
		if(cpuPvp > 0) {
			System.out.println("Press 0 for X or Press 1 for O:");
			int choice = sc.nextInt();
			while(choice > 1 || choice < 0) {
				System.out.println("Wrong Choice");
				System.out.println("Press 0 for X or Press 1 for O:");
				choice = sc.nextInt();
			}
			
			if(choice > 0) {
				player = 'O';
				cpu = 'X';
			}
					
			else {
				player = 'X';
				cpu = 'O';
			}		
		}
		System.out.println("First player will be chosen randomly...");
		Random r = new Random();
		int first = r.nextInt(2);
		char currentPlayer = (first == 0) ? 'X' : 'O';
		System.out.println("First player to play isss " + currentPlayer);
		if(currentPlayer == player) 
			showBoard(board);
		boolean someoneWon = false;
		boolean draw = false;
		while(!someoneWon && !draw) {
			if(cpuPvp > 0) {
				if(currentPlayer == player) {
					makeMove(board, currentPlayer);
				}
				else {
					cpuMove(board, cpu);
				}
					
			}
			else
				makeMove(board, currentPlayer);
			showBoard(board);
			someoneWon = didSomeoneWin(board, currentPlayer);
			draw = isDraw(board);
			currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
		}
		
		currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
		
		if(someoneWon) {
			if(cpuPvp > 0) {
				if(currentPlayer == cpu)
					System.out.println("Winner is CPU. You lost!");
				else
					System.out.println("Winner is Player " + currentPlayer + ". You won!");
			}
			else
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
	
	private static void cpuMove(char[][] board, char cpuMarker){
		Random r = new Random();
		int row = r.nextInt(board.length);
		int col = r.nextInt(board.length);
		while(board[row][col] != '-') {
			row = r.nextInt(board.length);
			col = r.nextInt(board.length);
		}
		board[row][col] = cpuMarker;
		System.out.println("CPU chose row " + (row+1) + " and column " + (col+1));
	}
	
	private static void makeMove(char[][] board, char currentPlayer){
		System.out.print("Player " + currentPlayer + ", which row do you want to place your mark in (1-" + board.length + ")?");
		Scanner sc = new Scanner(System.in);
		System.out.println();
		int row = sc.nextInt();
		
		System.out.print("Player " + currentPlayer + ", which column in row " + row + " do you want to place your mark in (1-" + board.length + ")?");
		System.out.println();
		int col = sc.nextInt();
		
		while(row > board.length || row <= 0 || col > board.length || col <= 0) {
			System.out.print("Wrong placement. Please pick again.");
			System.out.println();
			System.out.print("Player " + currentPlayer + ", which row do you want to place your mark in (1-" + board.length + ")?");
			System.out.println();
			row = sc.nextInt();
			
			System.out.print("Player " + currentPlayer + ", which column in row " + row + " do you want to place your mark in (1-" + board.length + ")?");
			System.out.println();
			col = sc.nextInt();
		}
		
		while(board[row-1][col-1] != '-') {
			System.out.print("This place is filled. Please pick again.");
			System.out.println();
			System.out.print("Player " + currentPlayer + ", which row do you want to place your mark in (1-" + board.length + ")?");
			System.out.println();
			row = sc.nextInt();
			
			System.out.print("Player " + currentPlayer + ", which column in row " + row + " do you want to place your mark in (1-" + board.length + ")?");
			System.out.println();
			col = sc.nextInt();
		}
		
		board[row-1][col-1] = currentPlayer;
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
