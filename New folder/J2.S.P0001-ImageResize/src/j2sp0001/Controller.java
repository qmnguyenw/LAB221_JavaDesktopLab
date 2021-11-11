/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j2sp0001;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author MSI
 */
public class Controller {
    ImageTool tool;
    ImageIcon frame;
    
    public Controller(ImageIcon frame) {
        this.frame = frame;
        tool = new ImageTool();
    }
    
    void setButtonAndLabel() {
        tool.setButtonImgIcon(frame.getjButton1(),"src/j2sp0001/1.jpg");
        tool.setButtonImgIcon(frame.getjButton2(),"src/j2sp0001/2.jpg");
        tool.setButtonImgIcon(frame.getjButton3(),"src/j2sp0001/3.jpg");
        tool.setButtonImgIcon(frame.getjButton4(),"src/j2sp0001/4.jpg");
        tool.setButtonImgIcon(frame.getjButton5(),"src/j2sp0001/5.jpg");
        tool.scaleButtonImg(frame.getjButton1());
        tool.scaleButtonImg(frame.getjButton2());
        tool.scaleButtonImg(frame.getjButton3());
        tool.scaleButtonImg(frame.getjButton4());
        tool.scaleButtonImg(frame.getjButton5());
        frame.getjLabel1().setText("");
        tool.setLabelImgIcon(frame.getjLabel1(), "src/j2sp0001/1.jpg");
    }
    
    void setActionListener() {
        frame.getjButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tool.setLabelImgIcon(frame.getjLabel1(), "src/j2sp0001/1.jpg");
            }
        });
        
        frame.getjButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tool.setLabelImgIcon(frame.getjLabel1(), "src/j2sp0001/2.jpg");
            }
        });
        
        frame.getjButton3().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tool.setLabelImgIcon(frame.getjLabel1(), "src/j2sp0001/3.jpg");
            }
        });
        
        frame.getjButton4().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tool.setLabelImgIcon(frame.getjLabel1(), "src/j2sp0001/4.jpg");
            }
        });
        
        frame.getjButton5().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tool.setLabelImgIcon(frame.getjLabel1(), "src/j2sp0001/5.jpg");
            }
        });
        
    }
    
    void visible() {
        frame.setVisible(true);
    }
    
    public static void main(String args[]) {
        Controller control = new Controller(new ImageIcon());
        control.setButtonAndLabel();
        control.setActionListener();
        control.visible();
    }
}
