/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.awt.Graphics;
import javax.swing.JLabel;

/**
 *
 * @author vietddse62677
 */
public class Frog extends JLabel {

    private int x;
    private int y;
    private int width;
    private int height;
    private int velocityOy;
    private String imgUrl;

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
    }

    public Frog(int x, int y, int width, int height, String imgUrl) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.imgUrl = imgUrl;
        this.setBounds(x, y, width, height);
        this.setText(imgUrl);
    }

    public void moveFrogOy() {
        y += velocityOy;
        this.setBounds(x, y, width, height);
        repaint();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getVelocityOy() {
        return velocityOy;
    }

    public void setVelocityOy(int velocityOy) {
        this.velocityOy = velocityOy;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
