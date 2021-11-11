/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

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
    
    void setButton(JButton button, String path) {
        //set icon to button
        tool.setScaledButtonImgIcon(button, path);
        
        //when user click a button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //change image in label
                tool.setScaledLabelImgIcon(frame.getLabel(), path);
            }
        });
    }
    
    void setGUI() {
        setButton(frame.getButton1(),"img/1.jpg");
        setButton(frame.getButton2(),"img/2.jpg");
        setButton(frame.getButton3(),"img/3.jpg");
        setButton(frame.getButton4(),"img/4.jpg");
        setButton(frame.getButton5(),"img/5.jpg");
        tool.setScaledLabelImgIcon(frame.getLabel(), "img/1.jpg");
    }
        
    void visibleFrame() {
        frame.setVisible(true);
    }
    
    public static void main(String args[]) {
        Controller control = new Controller(new ImageIcon());
        control.setGUI();   
        control.visibleFrame();
    }
}
