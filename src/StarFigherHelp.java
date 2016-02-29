import javax.swing.JOptionPane;


public class StarFigherHelp {
	/**
	 *  Help for the Ramblecs game
	 */
	  public static void showHelp()
	  {
	    JOptionPane.showMessageDialog(null,
	     "Earn points by not dying. \n" +
	    "Die by getting hit with a rock. \n" +
	     "Move with the arrow keys so you don't die \n" +
	    "Coins give you more score \n" +
	     "Don't ask why there are coins in space\n" +
	    "We don't know either \n",
	        "How to Play",       // Dialog title
	        JOptionPane.PLAIN_MESSAGE);
	  }

	}


