import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StarFighterMenu extends JMenuBar implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StarFighter gameField; 
	private JMenuItem newgame, exit, gameRules;
	
	
	public StarFighterMenu(StarFighter game){
		gameField = game;
		newgame = new JMenuItem("New Game");
	    newgame.addActionListener(this);
	    
	    exit = new JMenuItem("Exit");
	    exit.addActionListener(this);
	    
	    gameRules = new JMenuItem("How To Play");
	    gameRules.addActionListener(this);
	    

	    JMenu preferences = new JMenu("Preferences");
	    JMenu helpMenu = new JMenu("Help");
	    JMenu fileMenu = new JMenu("File");
	    
	    this.add(fileMenu);
	    this.add(helpMenu);
	    
	    fileMenu.add(preferences);
	    fileMenu.add(newgame);
	    fileMenu.add(exit);
	    
	    helpMenu.add(gameRules);
	}
	public void actionPerformed(ActionEvent e)
	  {
	    JMenuItem src = (JMenuItem)e.getSource();
	    if(src == newgame){
	    	gameField.newGame();
	    }
	    if(src == exit){
	    	System.exit(1);
	    }
	    if(src == gameRules){
	    	StarFigherHelp.showHelp();
	    }
	  }	
}
