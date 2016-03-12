import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SidePanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameBoard gameBoard;
	private JTextField highScoreField, scoreField;
	private JButton go;
	private Box box;
	private boolean newHighScore;
	private int highScoreInt;
	
	private String filename = "highscoreDoc.txt";
	private String oldScore;
	
	SidePanel(GameBoard gameboard)
	{
		gameBoard = gameboard;

		// setup GUI:
		box = Box.createVerticalBox();
		box.setPreferredSize(new Dimension(130, 300));
		box.setBorder(new EmptyBorder(5, 5, 5, 5));


		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(6,2);
		panel.setLayout(layout);

		JLabel score = new JLabel("Score:");
		//score.setForeground(Color.yellow);
		panel.add(score);
		scoreField = new JTextField("000");
		scoreField.setEditable(false);
		//scoreField.setBackground(Color.cyan);
		panel.add(scoreField);

		JLabel highScore = new JLabel("Highscore:");
		panel.add(highScore);
		
		setBackground(Color.GRAY);
		panel.setBackground(Color.GRAY);
		
		try
		{
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			
                System.err.println(bufferedReader.readLine());
                oldScore = bufferedReader.readLine();
			
			bufferedReader.close();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("can't open high score file");
		}
		catch (IOException e)
		{
			System.err.println("can't read high score file");
		}
		
		
		highScoreField = new JTextField(oldScore);
		System.err.println(oldScore);
		highScoreField.setEditable(false);
		panel.add(highScoreField);

		go = new JButton("Go");
		go.addActionListener(new GoButtonListener());

		box.add(panel);
		box.add(go);

		this.add(box);

		gameboard.requestFocus();
	}
	public void startOver(){
		go.setText("Stop");
		requestFocus();
	}
	public void gameOver(){
		go.setText("Game Over");
		requestFocus();
	}
	// Called from GameBoard.
	public boolean getHighScore()
	{
		return newHighScore;
			
	}
	public String gethighScore()
	{
		return highScoreField.getText();
	}
	public void update(int score)
	{
		scoreField.setText(String.format("%03d", score));
		highScoreInt = Integer.parseInt(highScoreField.getText());
		if(score > highScoreInt)
		{
			highScoreField.setText(String.format("%03d", score));
			newHighScore = true;
		try 
		{
			File file = new File("highScoreDoc.txt");
			FileOutputStream write = new FileOutputStream(file);
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(write));
			if(newHighScore)
			{
				bufferedWriter.write("High Score:");
				bufferedWriter.newLine();
				bufferedWriter.write(gethighScore());
			}
			bufferedWriter.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println("write file not found");
		}
		catch (IOException e)
		{
			System.err.println("write IO exception");
		}
		}
	}

	// Handles Go button events.
	private class GoButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String cmd = e.getActionCommand();
			if(!gameBoard.isgameOver){if ("Go".equals(cmd))
			{
				go.setText("Stop");
				gameBoard.newGame();
				gameBoard.requestFocus();
			}
			else
			{
				gameBoard.stopGame();
				go.setText("Go");
				requestFocus();
			}}
			/*else{
				go.setText("Go");
				requestFocus();
			}*/
		}
	}
}


