/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.awt.Graphics;
import javax.swing.JButton;

/**
 *
 * @author vietddse62677
 */
public class Column extends JButton {

    private int x;
    private int y;
    private int width;
    private int height;
    private int velocityOx;

    public Column(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.setBounds(x, y, width, height);
    }

    public void moveColumnOx() {
        this.x -= this.velocityOx;
        this.setBounds(x, y, width, height);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
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

    public int getVelocityOx() {
        return velocityOx;
    }

    public void setVelocityOx(int velocityOx) {
        this.velocityOx = velocityOx;
    }

}
