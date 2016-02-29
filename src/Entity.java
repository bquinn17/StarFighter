import java.awt.Graphics;


public interface Entity {
	  
	  public int getX();
	  public int getY();
	  
	  public int getEntityWidth();
	  public int getEntityHeight();
	  
	  public boolean isInside(int x, int y);

	  public void move(int x, int y);
	  
	  public void draw(Graphics g, int x, int y);
	  

}
