import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Asteroid implements Entity, ImageObserver
{
	private int xCoord, yCoord;
	private int asteriodHeight, asteriodWidth;
	BufferedImage rock = null;

	public Asteroid()
	{
		try {
			rock = ImageIO.read(getClass().getResourceAsStream("Rock5.png"));
		}
		catch (IOException ex)
		{
			System.out.println("IOException");
		}
		asteriodHeight = rock.getHeight();
		asteriodWidth = rock.getWidth();
		yCoord = -asteriodHeight;
	}
	public void dropAsteroid(){
		move(xCoord, yCoord+1);
	}
	@Override
	public int getX()
	{
		return xCoord;
	}
	@Override 
	public int getY()
	{
		return yCoord;
	}
	@Override
	public void move(int x, int y)
	{
		xCoord = x;
		yCoord = y;

	}
	@Override
	public int getEntityWidth() {
		return asteriodWidth;
	}


	@Override
	public int getEntityHeight() {
		return asteriodHeight;
	}
	@Override
	public boolean isInside(int x, int y) {
		/*int total = 0;
		if(x>xCoord){
			total++;}
		if(x<xCoord+asteriodWidth){
			total++;}
		if(y>yCoord){
			total++;}
		if(y<yCoord+asteriodHeight){
			total++;}
		if(total == 4){
			return true;}
		else
			return false;*/
		int xCenter = xCoord+(asteriodWidth/2);
		int yCenter = yCoord+(asteriodHeight/2);
		double/*int*/ distance = /*(int)*/(Math.sqrt(((x-xCenter)*(x-xCenter))+((y-yCenter)*(y-yCenter))));
		int radius = asteriodWidth/2;
		return (distance < radius);
	}
	@Override
	public void draw(Graphics g, int x, int y) {
		x = xCoord;
		y = yCoord;
		g.drawImage(rock, x, y, this);
	}
	//unused
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y,
			int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
}
