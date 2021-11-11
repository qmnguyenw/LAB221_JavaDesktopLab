/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import java.awt.Color;
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
public class ConChim {

    private static int y = 200;
    private static int x = 100;
    private BufferedImage bird;

    //lay ra gia tri y
    public static int getY() {
        return y;
    }

    //lay ra gia tri x
    public static int getX() {
        return x;
    }

    public void paint(Graphics g) {
        try {
//        g.setColor(Color.red);
//        g.fillRect(x, y, 45, 45);
            //them hinh anh
            bird = ImageIO.read(new File("C:\\Users\\qmngu\\Desktop\\Lab Java Desktop\\New folder\\FlappyBird\\res\\Bird.png"));
            g.drawImage(bird, x, y, null);
        } catch (IOException ex) {
            Logger.getLogger(ConChim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //con chim roi xuong dat
    public void tangY() {
        y++;
    }

    //con chim bay len troi
    public void giamY() {
//        y = y -40;
        y -= 30;
    }
}
