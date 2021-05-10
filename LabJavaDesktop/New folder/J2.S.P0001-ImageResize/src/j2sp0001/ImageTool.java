/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j2sp0001;

import java.awt.Image;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author MSI
 */
public class ImageTool {
    public void setButtonImgIcon(JButton button,String path) {
        try {
            javax.swing.ImageIcon myImg = new javax.swing.ImageIcon(path);
            Image img = myImg.getImage();
            img = img.getScaledInstance(button.getWidth()-20, button.getHeight()-20, Image.SCALE_SMOOTH);
            myImg = new javax.swing.ImageIcon(img);
            button.setIcon(myImg);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void scaleButtonImg(JButton button) {
        button.setMargin(new Insets(15, 17, 15, 3));
    }
    
    public void setLabelImgIcon(JLabel label,String path) {
        try {
            javax.swing.ImageIcon myImg = new javax.swing.ImageIcon(path);
            Image img = myImg.getImage();
            img = img.getScaledInstance(label.getWidth()-20, label.getHeight()-20, Image.SCALE_SMOOTH);
            myImg = new javax.swing.ImageIcon(img);
            label.setIcon(myImg);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void scaleLabelImg(JLabel label) {
        label.setHorizontalAlignment(JLabel.CENTER);
    }
}
