import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
public static final Random RANDOM = new Random();
public static void main(String[] args) {
	Board board = new Board(); 
	Scanner scanner = new Scanner(System.in); 
	int exitchoice = 1; 
	board.displayBoard();
	System.out.println("Select Turn: \n 1. Computer is X \n 2. User is O"); 
	int choice = scanner.nextInt();
	if (choice == board.Computer) {
		Point p = new Point(RANDOM.nextInt(3), RANDOM.nextInt(3)); 
		board.play(p, board.Computer);
		board.displayBoard();
	}
	while (!board.GameOver()) {
		boolean validMove = true; 
		do {
			if (!validMove) {
				System.out.println("Not a valid move!"); 
			}
			System.out.println("Please enter next move:  "); 
			Point userMove = new Point(scanner.nextInt(), scanner.nextInt());
			validMove = board.play(userMove, board.human ); 
			
		
		}while(!validMove); 
		board.displayBoard();
		if (board.GameOver()) {
			break; 
		}
		board.minimax(0, board.Computer);
		System.out.println("Computer chose position: "+board.computermove);
		board.play(board.computermove, board.Computer);
		board.displayBoard();
		System.out.println("Do you want to continue: \n 1. Yes \n 2. no"); 
		exitchoice = scanner.nextInt();
		if (exitchoice == 2 ) {
			break; 
		} else {
			continue; 
		}
		
		}
	if (board.hasPlayerWon(board.Computer)){
		System.out.println("Computer has won the game"); 
	} else if (board.hasPlayerWon(board.human)) {
		System.out.println("You have won the game!! ");
	} else if (exitchoice == 2) {
		System.out.println("Good day!"); 
	}else {
		System.out.println("There is a draw"); 
	}
	}
}

