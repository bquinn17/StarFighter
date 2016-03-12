import javax.swing.*;


public class StarFigherHelp {
	/**
	 *  Help for the Ramblecs game
	 */
	  public static void showHelp()
	  {
	    JOptionPane.showMessageDialog(null,
	     "Press the start button to begin.\n" +
	    "Dodge the asteroids by moving the rocket ship back and forth\n" +
				 "by tapping or holding down your arrow keys.\n" +
	     "Points are earn continuously for staying alive.\n" +
	    "Earn extra points by collecting the golden coins.\n" +
	     "To start a new game, go to File > New Game.\n" +
	    "You may pause the game at any time,\n" +
				 "but if you do your score will be set back to 0.\n",
	        "How to Play",       // Dialog title
	        JOptionPane.PLAIN_MESSAGE);
	  }

	}