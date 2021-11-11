package Demo;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
public class Mark {
	private int x,y;
	public Mark(int x,int y) {
		this.x=x;this.y=y;
	}
	
	public void paintMark(Graphics g,int i)	{
		Toolkit t=Toolkit.getDefaultToolkit();
		Image im;
		if(i==0) {
			im=t.getImage("0.png");
			g.drawImage(im, x, y,null);
		}
		if(i==1) {
			im=t.getImage("1.png");
			g.drawImage(im, x, y,null);
		}
		if(i==2) {
			im=t.getImage("2.png");
			g.drawImage(im, x, y,null);
		}
		if(i==3) {
			im=t.getImage("3.png");
			g.drawImage(im, x, y,null);
		}
		if(i==4) {
			im=t.getImage("4.png");
			g.drawImage(im, x, y,null);
		}
		if(i==5) {
			im=t.getImage("5.png");
			g.drawImage(im, x, y,null);
		}
		if(i==6) {
			im=t.getImage("6.png");
			g.drawImage(im, x, y,null);
		}
		if(i==7) {
			im=t.getImage("7.png");
			g.drawImage(im, x, y,null);
		}
		if(i==8) {
			im=t.getImage("8.png");
			g.drawImage(im, x, y,null);
		}
		if(i==9) {
			im=t.getImage("9.png");
			g.drawImage(im, x, y,null);
		}
	}
}
