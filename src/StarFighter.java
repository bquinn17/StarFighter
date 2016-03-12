import javax.swing.*;

public class StarFighter extends JFrame{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StarFighterMenu starFighterMenu;
	public SidePanel sidePanel;
	private GameBoard gameBoard;

	public StarFighter(){
		super("StarFighter");
		
		starFighterMenu = new StarFighterMenu(this);
		 setJMenuBar(starFighterMenu);
		
		gameBoard = new GameBoard(this);
		sidePanel = new SidePanel(gameBoard);
		
		Box box = Box.createHorizontalBox();
	    box.add(gameBoard);
	    box.add(sidePanel);
	    getContentPane().add(box);
	    
	    gameBoard.requestFocus();
	}
	public void newGame(){
		gameBoard.endGame();
	}

	public static void main(String[] args)
	  {
		  String plafName = UIManager.getSystemLookAndFeelClassName();
		  try
		  {
		  UIManager.setLookAndFeel(plafName);
		  }
		  catch (Exception ex)
		  {
		  System.out.println("*** " + plafName + " PLAF not installed ***");
		  } 
	    StarFighter window = new StarFighter();
	    window.setBounds(100, 100, 400, 400);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setResizable(true);
	    window.setVisible(true);
	  }
}
