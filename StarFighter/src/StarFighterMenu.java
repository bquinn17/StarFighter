import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StarFighterMenu extends JMenuBar implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StarFighter gameField; 
	private JCheckBoxMenuItem sound;
	private JMenuItem newgame, exit, gameRules;
	
	
	public StarFighterMenu(StarFighter game){
		gameField = game;
		newgame = new JMenuItem("New Game");
	    newgame.addActionListener(this);
	    
	    exit = new JMenuItem("Exit");
	    exit.addActionListener(this);
	    
	    gameRules = new JMenuItem("How To Play");
	    gameRules.addActionListener(this);
	    
	    sound = new JCheckBoxMenuItem("Play Sound", false);
	    //game.soundEnable();
	    
	    JMenu preferences = new JMenu("Preferences");
	    JMenu helpMenu = new JMenu("Help");
	    JMenu fileMenu = new JMenu("File");
	    
	    this.add(fileMenu);
	    this.add(helpMenu);
	    
	    fileMenu.add(preferences);
	    preferences.add(sound); 
	    fileMenu.add(newgame);
	    fileMenu.add(exit);
	    
	    helpMenu.add(gameRules);
	}
	public boolean soundEnabled(){
		return (sound.isSelected());
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
