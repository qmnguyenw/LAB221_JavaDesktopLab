/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author vietddse62677
 */
public class MyImage {

    /*
        Empty constructor
     */
    public MyImage() {

    }

    /*
        *Just simply resize the image but keep the original ratio
        *@param img: the image need to resize
        *@param maxWidth: the width of the container
        *@param maxHeight: the height of the container
     */
    public BufferedImage resize(BufferedImage img, int maxWidth, int maxHeight) {
        int scaledWidth = 0, scaledHeight = 0;
        scaledWidth = maxWidth;
        scaledHeight
                = (int) (img.getHeight() * ((double) scaledWidth / img.getWidth()));

        if (scaledHeight > maxHeight) {
            scaledHeight = maxHeight;
            scaledWidth
                    = (int) (img.getWidth() * ((double) scaledHeight / img.getHeight()));
            if (scaledHeight > maxWidth) {
                scaledWidth = maxWidth;
                scaledHeight = maxHeight;
            }
        }
        Image tmp
                = img.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(scaledWidth, scaledHeight, Image.SCALE_REPLICATE);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(img, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
        return dimg;
    }

    /*
        *Resize the image base on the jLabel that will contain it
        *@param jLabel: the jLabel to display the img on it
        *@param img: the image to display on jLabel
     */
    public void display(JLabel jLabel, BufferedImage img) {
        BufferedImage newImg = new MyImage().
                resize(
                        img,
                        jLabel.getWidth(),
                        jLabel.getHeight());
        ImageIcon icon = new ImageIcon(newImg);
        jLabel.setIcon(icon);
    }
}
