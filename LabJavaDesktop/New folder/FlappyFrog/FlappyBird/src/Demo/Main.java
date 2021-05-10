package Demo;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
public class Main extends JFrame implements Runnable,MouseListener {
	Thread thread;
	Cell[] cell;
	Cell[] cell1;
	Bird bird;
	Mark[] mark;
	Score score;
	int dem,dem1,dem2,diem;
	JLabel lb,lb1,lb2;
	JPanel jpanel=new JPanel();
	BufferedImage img;
	
	public Main() {	
		this.setBounds(700,200,600,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		lb=new JLabel();
		lb1=new JLabel();
		lb2=new JLabel();
		cell=new Cell[20];
		cell1=new Cell[20];
		mark=new Mark[10];
		score=new Score();
		
		jpanel.setSize(600,500);
		jpanel.setLayout(null);
		
		ImageIcon im=new ImageIcon("bautroi.png");
		lb1.setIcon(im);
		lb1.setBounds(0,0,600,390);
		this.add(lb1);
		ImageIcon im1=new ImageIcon("nendat.png");
		lb.setIcon(im1);
		lb.setBounds(0,383,600,90);
		this.add(lb);
		this.add(lb2);
		
		Khoitao();
		
		this.addMouseListener(this);
		thread=new Thread(this);
		thread.start();
		
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void Khoitao() {
				
		dem=0;dem1=0;diem=0;dem2=1;
		
		bird =new Bird(300,250);
				
		cell[0]=new Cell(500,350,150);
		cell[1]=new Cell(640,300,200);
		cell[2]=new Cell(780,350,150);
		cell[3]=new Cell(920,250,250);
		cell[4]=new Cell(1060,350,150);
//		cell[5]=new Cell(1125,250,250);
		
		cell1[0]=new Cell(500,0,250);
		cell1[1]=new Cell(640,0,200);
		cell1[2]=new Cell(780,0,250);
		cell1[3]=new Cell(920,0,150);
		cell1[4]=new Cell(1060,0,250);
//		cell1[5]=new Cell(1125,0,150);
		
//		mark[0]=new Mark(310,250);
//		mark[1]=new Mark(330,250);
//		mark[2]=new Mark(350,250);
//		mark[3]=new Mark(350,250);
		
	}
	
	int i;
	public void paint(Graphics g) {
		
		img=new BufferedImage(jpanel.getWidth(),jpanel.getHeight(),BufferedImage.TRANSLUCENT);
		Graphics2D g2d=(Graphics2D)img.createGraphics();
		super.paint(g2d);
		String s=String.valueOf(diem);
		if(dem2==0) {
			mark[0]=new Mark(310,250);
			mark[1]=new Mark(330,250);
			mark[2]=new Mark(350,250);
			mark[3]=new Mark(350,250);
			for(i=0;i<s.length();i++) {
				int j=(int)s.charAt(i)-'0';
				mark[i].paintMark(g2d,j);
			}
			for(i=0;i<5;i++) {
				cell[i].paintCellUp(g2d);
				cell1[i].paintCellDown(g2d);
			}
			bird.paintBird(g2d);
		}
		
		if(dem2==1)	{	
			try {
			    BufferedImage im = ImageIO.read(new File("tap.png"));
				g2d.drawImage(im,250,200,null);
				BufferedImage im1 = ImageIO.read(new File("logo.png"));
				g2d.drawImage(im1,218,130,null);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String s1 = null;
		try {
			s1 = String.valueOf(score.Best(diem));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(Check()!=1&&dem!=0) {
			
			try {
			    BufferedImage im = ImageIO.read(new File("game.png"));
				g2d.drawImage(im,220,150,null);
			} catch (IOException e)	{
				e.printStackTrace();
			}
			
			mark[0]=new Mark(362,235);
			mark[1]=new Mark(384,235);
			mark[2]=new Mark(406,235);
			mark[3]=new Mark(428,235);

			try {
				BufferedImage im = ImageIO.read(new File("bang.png"));
				g2d.drawImage(im,200,200,null);
			
				BufferedImage im1 = ImageIO.read(new File("c.png"));
				g2d.drawImage(im1,223,245,null);
			
				for(i=0;i<s.length();i++) {
					int j=(int)s.charAt(i)-'0';
					mark[i].paintMark(g2d,j);
				}
			} catch (IOException e)	{
				e.printStackTrace();
			}
			
			mark[0]=new Mark(362,270);
			mark[1]=new Mark(384,270);
			mark[2]=new Mark(406,270);
			mark[3]=new Mark(428,270);
			try	{
				for(i=0;i<s1.length();i++) {
					int j=(int)s1.charAt(i)-'0';
					mark[i].paintMark(g2d,j);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}

		}
		
		g.drawImage(img,0,0,this);
	}
	
	public void Update() {
		for(i=0;i<5;i++) {
			cell[i].Update();
			cell[i].reUpdate();
			
			cell1[i].Update();
			cell1[i].reUpdate();
		}
		repaint();
	}
	//updete bird
	public void UpdateBird() {
		bird.Down();
		repaint();
	}
	
	public void run() {
		while(true)	{
			if(Check()==1&&dem==0&&dem2==0) Update();
			if(Check1()==1&&dem2==0)UpdateBird();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	//Mouse
	public void mouseClicked(MouseEvent e) {
		dem2=0;
		if(Check()==1&&dem==0) {	
			bird.Up();
		}
		else Khoitao();
	}
	public void mousePressed(MouseEvent e) {
		
	}
	public void mouseEntered(MouseEvent e) {
		
	}
	public void mouseExited(MouseEvent e) {
		
	}
	public void mouseReleased(MouseEvent e)	{
		
	}
	
	public static void main(String[] args) {   
		try	{
			Main m=new Main();
		}catch(Exception e)	{
			JOptionPane.showMessageDialog(null,"Erro");
		}
	}
	
	//ham kiem tra
	public void Dem() {
		if(bird.getX()>=cell1[dem1].getX()+50) {	
			dem1++;diem++;
		}
		if(dem1>=5) 
			dem1=0;
	}
	
	public int Check() {
		Dem();
		if(((cell1[dem1].getX()<=bird.getX()+30)&&(cell1[dem1].Height()>=bird.getY()))
				||((cell1[dem1].getY()+cell1[dem1].Height()>=bird.getY())&&(cell1[dem1].Height()<=bird.getY())&&bird.getX()>=cell1[dem1].getX())
				) {
				dem=1;
				return 0;
			}
		if(((cell[dem1].getX()<=bird.getX()+30)&&(cell[dem1].getY()<=bird.getY()+30))
			||((cell[dem1].getY()==bird.getY()+30)&&(cell[dem1].getY()>=bird.getY()+30)&&bird.getX()>=cell1[dem1].getX())	
				) {
			dem=1;
			return 0;
		}
		return 1;
	}
	
	public int Check1()	{
		//Dem();
		if(bird.getY()+30==cell[dem1].getY()&&bird.getX()+29>=cell1[dem1].getX())
			return 0;
		return 1;
	}
}
