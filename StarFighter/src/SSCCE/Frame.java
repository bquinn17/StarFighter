package SSCCE;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;



//The purpose of this class is to create a simplified version of the project for trouble shooting purposes
public class Frame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SSCCE pane;
	public Frame(){
		super("StarFighter");

		pane = new SSCCE();

		Box box = Box.createHorizontalBox();
		box.add(pane);
		getContentPane().add(box);

	}

	public static class SSCCE extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private static JTextField scoreField;
		private Box box;
		private int ticks,score;
		private Timer timer;

		public SSCCE(){
			box = Box.createVerticalBox();
			box.setPreferredSize(new Dimension(130, 300));
			box.setBorder(new EmptyBorder(5, 5, 5, 5));

			JPanel panel = new JPanel();
			GridLayout layout = new GridLayout(6,2);
			panel.setLayout(layout);

			JLabel score = new JLabel("Score:");
			panel.add(score);
			scoreField = new JTextField("0");
			scoreField.setEditable(false);
			panel.add(scoreField);

			box.add(panel);
			this.add(box);

			timer = new Timer(10, new ClockListener());
			timer.start();

		}
		public static void update(int score)
		{

			scoreField.setText(String.format("%03d", score));

			/*String s = String.format("%03d", score);
			System.out.println("update" + s);*/
		}
		private class ClockListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				ticks++;
				if(ticks == 0||ticks % 100 == 0){
					score+=10;
					SSCCE.update(score);
				}
			}
		}
	}

	public static void main(String[]args){
		String plafName = UIManager.getSystemLookAndFeelClassName();
		try
		{
			UIManager.setLookAndFeel(plafName);
		}
		catch (Exception ex)
		{
			System.out.println("*** " + plafName + " PLAF not installed ***");
		} 
		Frame window = new Frame();
		window.setBounds(100, 100, 395, 355);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
	}

}
