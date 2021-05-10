/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Hieu
 */
public class TapVe extends JPanel implements Runnable {

    private OngNuoc ongnuocT = new OngNuoc();
    private ConChim conchimT = new ConChim();
    private KiemTra kiemtraT = new KiemTra();
    private DiemSo diemsoT = new DiemSo();
    private static boolean bl = false;
    private BufferedImage bg, nendat, gameover;
    private Thread threadT;

    public TapVe() {
        threadT = new Thread(this);
        threadT.start(); //khi goi lenh nay se duoc chuyen den phuong thuc run() phia duoi
    }

    public void paint(Graphics g) {
        try {
//          Ve nen (background) cho game
//        g.setColor(Color.cyan); //dat mau ve
//        g.fillRect(0, 0, getWidth(), getHeight()); //ve len ManHinh (JFrame)

            bg = ImageIO.read(new File("C:\\Users\\qmngu\\Desktop\\Lab Java Desktop\\New folder\\FlappyBird\\res\\BG.png"));
            g.drawImage(bg, 0, 0, null);

            //them ong nuoc vao nen game
            ongnuocT.paint(g);

//            g.setColor(Color.green);
//            g.fillRect(0, 400, getWidth(), getHeight());
//
//            g.setColor(Color.orange);
//            g.fillRect(0, 410, getWidth(), getHeight());
            nendat = ImageIO.read(new File("C:\\Users\\qmngu\\Desktop\\Lab Java Desktop\\New folder\\FlappyBird\\res\\NenDat.png"));
            g.drawImage(nendat, 0, 400, null);

            //them con chim vao game
            conchimT.paint(g);

            g.setColor(Color.BLACK); //mau chu
            g.setFont(new Font("Arial", Font.BOLD, 20)); //font, type, size
            g.drawString("Điểm: " + diemsoT.getDiem(), 10, 20); //ve chu

            if (bl == true) {
                gameover = ImageIO.read(new File("C:\\Users\\qmngu\\Desktop\\Lab Java Desktop\\New folder\\FlappyBird\\res\\GameOver.png"));
                g.drawImage(gameover, 120, 175, null);
            }
        } catch (IOException ex) {
            Logger.getLogger(TapVe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        //1 vong lap khong bao gio dung
        while (true) {
            repaint(); // goi lai paint
            ongnuocT.giamX(); // goi den phuong thuc giamX cua class OngNuoc
            try {
                Thread.sleep(15); // chuong trinh dung lai 5 ms
            } catch (InterruptedException ex) {
                Logger.getLogger(TapVe.class.getName()).log(Level.SEVERE, null, ex);
            }
            ongnuocT.laplaiX();  // goi den phuong thuc .... cua class OngNuoc
            conchimT.tangY();
            diemsoT.congdiem();
            //kiem tra dieu kien neu chet thi dung game
            if (kiemtraT.chet() == true) {
                bl = true;
                repaint();
                threadT.stop();
            }
        }
    }
}
