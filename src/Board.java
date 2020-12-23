import java.util.ArrayList;
import java.util.List;

public class Board {
	public static final int VACANT = 0; 
	public static final int Computer = 1; 
	public static final int human = 2 ; 
	public int[][] board = new int[3][3]; 
	public Point computermove; 
	
	
	
	public boolean GameOver() {
		return hasPlayerWon(Computer) || hasPlayerWon(human) || getAvailableCells().isEmpty() ; 
 		
	}
	public boolean hasPlayerWon(int player) {
		if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0]==player)|| (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2]==player)) {
			return true; 
		}
		for (int i = 0; i<3; i++) {
			if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == player)|| (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == player)) {
				return true; 
				
			}
			
			}
		return false;
 		}
	
	public List<Point> getAvailableCells(){
		List<Point> availableCells = new ArrayList<>() ; 
		for(int i = 0 ; i <3 ; i++) {
			for (int j = 0 ; j <3 ; j++) {
				if (board[i][j] == VACANT) {
					availableCells.add(new Point(i, j)); 
					
				}
				
				
			}
			
		}
		return availableCells ; 
		
	}
	
	public boolean play(Point point, int player) {
		if (board[point.x][point.y] != VACANT) {
			return false; 
		} else {
			board[point.x][point.y] = player; 
			return true;
			
		}
		
	}
	public void displayBoard() { 
		System.out.println();
		for (int i = 0 ; i <3 ; i++) {
			for (int j = 0; j< 3; j++) {
				String value = "[ ]"; 
				if (board[i][j] == Computer) {
					value = "[X]"; 
					
				} else if (board[i][j] == human) {
					value = "[O]";
					
				}
				System.out.print(value + " " ); 
				
			}
			System.out.println();
		}
		
	}
	// minimax algorithm
	public int minimax(int depth, int turn) {
		if (hasPlayerWon(Computer)) {
			
			//computer win
			return 1; 
		} 
		if (hasPlayerWon(human)) {
			
			//user win 
			return -1 ;
		}
		List<Point> availableCells = getAvailableCells();
		if (availableCells.isEmpty()) {
			return 0 ; 
		}
		int minimum = Integer.MAX_VALUE; 
		int maximum = Integer.MIN_VALUE; 
		
		for (int i = 0 ; i < availableCells.size() ; i++) {
			Point point = availableCells.get(i);
			if (turn == Computer) {
				play(point, Computer); 
				int currentScore = minimax(depth+1, human);
				maximum = Math.max(maximum, currentScore); 
				
				if (depth == 0) {
					System.out.println("The minimax score for the computer in " + point + "is "+ currentScore);
				}
				if (currentScore >= 0) {
					if (depth == 0 ) {
						computermove = point; 
					}
				}
				if (currentScore == 1) {
					board[point.x][point.y] = VACANT;
					 
					
					break; 
				}
				if (i == availableCells.size()- 1 && maximum < 0 ) {
					if (depth == 0 ) {
						computermove = point; 
					}
				}
				
				
			} else if (turn == human) {
				play(point, human);
				int currentScore = minimax(depth+1, Computer);
				
				minimum = Math.min(minimum, currentScore); 
				
				if (minimum == -1) {
					board[point.x][point.y] = VACANT;
					
					break;
				}
				
			}
			board[point.x][point.y] = VACANT; 
				
		}
	
		return turn == Computer ? maximum : minimum ;
	}
		
	}
	


