/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Hieu
 */
public class ManHinh extends JFrame {

    private TapVe tapveM = new TapVe(); //tao 1 doi tuong thuoc class TapVe
    private ConChim conchimM = new ConChim();

    public ManHinh() {
        setSize(500, 500); //tao 1 khung cua so kich thuoc 500x500
        setVisible(true); //hien thi khung cua so
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //thoat khoi chuong trinh khi nhan close
        setLocation(400, 100); //vi tri khung cua so khi xuat hien
        add(tapveM); //them tapveM vao ManHinh (JFrame)
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                //khi bam vao ban phim se lam gi
                //Không cho chim nhảy ra khỏi lồng :))
                if (conchimM.getY() >= 0) {
                    conchimM.giamY();
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                //khi bam chuot se lam gi??
                //Không cho chim nhảy ra khỏi lồng :))
                if (conchimM.getY() >= 0) {
                    conchimM.giamY();
                }
            }
        });
    }

    public static void main(String[] args) {
        ManHinh mh = new ManHinh(); //tao ra 1 doi tuong thuoc class ManHinh
    }
}
