/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.net.URL;
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
        img = img.getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
    
    public void setScaledLabelImgIcon(JLabel label,String path) {
        label.setBorder(null);
        URL url = getClass().getClassLoader().getResource(path);
        if(url==null) {
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setBorder(new LineBorder(Color.black));
            label.setText("No image in file path");
            return;
        }
        //construct an image icon from path
        ImageIcon myIcon = new ImageIcon(url);
        myIcon = resizedIcon(myIcon, label.getWidth(), label.getHeight());
        label.setIcon(myIcon);
    }
    
}
