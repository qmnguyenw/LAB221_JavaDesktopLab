package Demo;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
public class Bird { 
	int x,y;
	public Bird(int x,int y) {
		this.x=x;this.y=y;
	}
	
	public void paintBird(Graphics g) {
		Toolkit t=Toolkit.getDefaultToolkit();
		Image im=t.getImage("chim4.png");
		g.drawImage(im,x,y,35,35,null);
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public void Up(){
		if(y-30>=0) y-=30;
	}
	
	public void Down(){
		if(y+30<=420) y++;
	}
}
