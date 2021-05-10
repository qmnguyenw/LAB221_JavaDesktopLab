/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

/**
 *
 * @author MSI
 */
public class ImageTool {
    
    ImageIcon resizedIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        //resize image with resized width and height
        img = img.getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_FAST);
        return new ImageIcon(img);
    }
    
    public void setScaledButtonImgIcon(JButton button,String path) {
        //construct an image icon from path
        ImageIcon myIcon = new ImageIcon(getClass().getClassLoader().getResource(path));
        //create new icon by scaled image
        myIcon = resizedIcon(myIcon, button.getWidth() - 10, button.getHeight() - 10);
        button.setIcon(myIcon);
    }
    
    public void setScaledLabelImgIcon(JLabel label,String path) {
        //construct an image icon from path
        ImageIcon myIcon = new ImageIcon(getClass().getClassLoader().getResource(path));
        myIcon = resizedIcon(myIcon, label.getWidth(), label.getHeight());
        label.setIcon(myIcon);
    }
    
    public void setIconLabel(JLabel label, String path) {
        URL imgUrl = getClass().getClassLoader().getResource(path + ".png");
        //if file not exist
        if (imgUrl == null) {
            label.setBorder(new LineBorder(Color.BLACK));
            label.setText("File not exist in input path");
            return;
        }
        
        try {
            //if file not image type
            if (ImageIO.read(imgUrl) == null) {
                label.setBorder(new LineBorder(Color.BLACK));
                label.setText("File is not image");
                return;
            }
        } catch (IOException ex) {//image io cannot read url
            label.setBorder(new LineBorder(Color.BLACK));
            label.setText("Cannot read file");
            return;
        }
        
        label.setBorder(null);
        label.setText("");
        //create an icon from path
        ImageIcon icon = new ImageIcon(imgUrl);
        icon = resizedIcon(icon, label.getWidth(), label.getHeight());
        //set icon to label
        label.setIcon(icon);
    }
}
