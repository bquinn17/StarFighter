import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;


public class SpaceShip implements Entity, ImageObserver
{
	private int xCoord, yCoord;
	private int SpaceShipHeight, SpaceShipWidth;
	BufferedImage rocket = null;

	public SpaceShip()
	{

		try {
			rocket = ImageIO.read(getClass().getResourceAsStream("Rocket2.png"));
		}
		catch (IOException ex)
		{
			System.out.println("IOException");
		}
		SpaceShipHeight = rocket.getHeight();
		SpaceShipWidth = rocket.getWidth();
		
		xCoord = (240-getEntityWidth())/2;
		yCoord = 300-getEntityHeight();
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
	public int getEntityHeight(){
		//32
		return SpaceShipHeight;
	}
	@Override
	public int getEntityWidth(){
		//26
		return SpaceShipWidth;
	}

	@Override
	public void move(int x, int y)
	{
		xCoord = x;
		yCoord = y;
	}
	@Override
	public boolean isInside(int x, int y){
		int total = 0;
		if(x>xCoord+1){
			total++;}
		if(x<xCoord+SpaceShipWidth-2){
			total++;}
		if(y>yCoord){
			total++;}
		if(y<yCoord+SpaceShipHeight){
			total++;}
		return (total == 4);
	}
	
	@Override
	public void draw(Graphics g, int x, int y) {
		xCoord = x;
		yCoord = y;
		g.drawImage(rocket, x, y, this);


	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}
}