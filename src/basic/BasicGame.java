package basic;

import javax.swing.JFrame;

public class BasicGame extends JFrame {
	
	/**
	 * 
	 */
	private final int mainWindowWidth = 640;
	private final int mainWindowHeight = 480;

	public BasicGame() {
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(mainWindowWidth, mainWindowHeight);
		setLocationRelativeTo(null);
		setTitle("Basic Game");
		setResizable(true);
		setVisible(true);
		
		Board board = new Board(10000, 10000);
		add(board);
		board.initializeGame();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BasicGame bg = new BasicGame();
	}

}
