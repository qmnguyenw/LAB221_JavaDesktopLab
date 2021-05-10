/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

/**
 *
 * @author vietddse62677
 */
public class Node {

    int x, y;
    String name;
    String lable;
    String color;

    public Node(int x, int y, String name, String lable, String color) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.lable = lable;
        this.color = color;
    }

    //constructor without color
    public Node(int x, int y, String name, String lable) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.lable = lable;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
