import java.awt.*;


public interface Entity {
	  int getX();
	  int getY();
	  int getEntityWidth();
	  int getEntityHeight();
	  boolean isInside(int x, int y);
	  void move(int x, int y);
	  void draw(Graphics g, int x, int y);
}
