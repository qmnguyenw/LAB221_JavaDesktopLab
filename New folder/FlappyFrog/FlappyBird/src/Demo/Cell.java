package Demo;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
public class Cell {
	private int x,y,height;
	
	public Cell(int x,int y,int height)	{
		this.x=x;this.y=y;this.height=height;
	}
	
	public void paintCellUp(Graphics g)	{
		Toolkit t=Toolkit.getDefaultToolkit();
		Image img=t.getImage("cotduoi.png");
		g.drawImage(img, x, y,50, height,null);
	}
	public void paintCellDown(Graphics g) {
		Toolkit t=Toolkit.getDefaultToolkit();
		Image img=t.getImage("cottren.png");
		g.drawImage(img, x, y,50, height,null);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int Height()	{
		return height;
	}
	
	public void Update() {
		x--;
	}
	
	public void reUpdate() {
		if(x+50<=0) x=644;
	}
}
