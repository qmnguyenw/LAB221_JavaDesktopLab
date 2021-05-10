/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author vietddse62677
 */
public class GraphPanel extends JPanel {

    //variables
    int width;
    int height;

    HashMap<String, Node> nodes;
    ArrayList<Edge> edges;

    public GraphPanel() {
        this.setSize(400, 300);
        this.setVisible(true);
        width = 30;
        height = 30;
        nodes = new HashMap<>();
        edges = new ArrayList<>();
    }

    public void addNode(String name, String lable, String color, int x, int y) {
        //add node at pixel(x,y)
        nodes.put(name, new Node(x, y, name, lable, color));
        this.repaint();
    }

    public void addEdge(String from, String to, String lable) {
        //add an edge between nodes i and j
        edges.add(new Edge(from, to, lable));
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        FontMetrics f = g.getFontMetrics();
        int nodeHeight = Math.max(height, f.getHeight());

        g.setColor(Color.BLACK);

        //drawing edges
        for (Edge edge : edges) {
            if (nodes.get(edge.from) != null && nodes.get(edge.to) != null) {
                Line2D line = new Line2D.Double(nodes.get(edge.from).x, nodes.get(edge.from).y,
                        nodes.get(edge.to).x, nodes.get(edge.to).y);
                ((Graphics2D) g).draw(line);
//            //drawing arrow
//            ArrayList<Shape> linesList = new ArrayList<>();
//            Line2D shape = null;
//            int x = Math.abs(nodes.get(edge.from).x - nodes.get(edge.to).x) * 2 / 3
//                    + (nodes.get(edge.from).x - nodes.get(edge.to).x > 0 ? nodes.get(edge.to).x : nodes.get(edge.from).x);
//            int y = Math.abs(nodes.get(edge.from).y - nodes.get(edge.to).y) * 2 / 3
//                    + (nodes.get(edge.from).y - nodes.get(edge.to).y > 0 ? nodes.get(edge.to).y : nodes.get(edge.from).y);
//            if (line.getP1().getY() > line.getP2().getY()) {
//                shape = new Line2D.Double(line.getP2().getX(), line.getP2().getY(), x, y);
//            } else {
//                shape = new Line2D.Double(line.getP1().getX(), line.getP1().getY(), x, y);
//            }
//            double angle = Math.atan2( //find angle of line
//                    shape.getY2() - shape.getY1(),
//                    shape.getX2() - shape.getX1());
//
//            int arrowHeight = 10;
//            int halfArrowWidth = 4;
//
//            Point2D end = shape.getP2();
//            Point2D aroBase = new Point2D.Double(
//                    shape.getX2() - arrowHeight * Math.cos(angle),
//                    shape.getY2() - arrowHeight * Math.sin(angle));
//
//            Point2D end1 = new Point2D.Double(
//                    aroBase.getX() - halfArrowWidth * Math.cos(angle - Math.PI / 2),
//                    aroBase.getY() - halfArrowWidth * Math.sin(angle - Math.PI / 2));
//            //other side
//            Point2D end2 = new Point2D.Double(
//                    aroBase.getX() + halfArrowWidth * Math.cos(angle - Math.PI / 2),
//                    aroBase.getY() + halfArrowWidth * Math.sin(angle - Math.PI / 2));
//
//            linesList.add(new Line2D.Double(end1, end2));
//            linesList.add(new Line2D.Double(end, end2));
//            linesList.add(new Line2D.Double(end, end1));
//
//            for (Shape s : linesList) {
//                ((Graphics2D) g).draw(s);
//            }
//            //end of drawing arrow
                //drawing lable
                if (edge.getLable() != null) {
                    int nodeWidth = Math.max(width, f.stringWidth(edge.lable) + width / 2);
                    int xx = Math.abs(nodes.get(edge.from).x - nodes.get(edge.to).x) / 2
                            + (nodes.get(edge.from).x - nodes.get(edge.to).x > 0 ? nodes.get(edge.to).x : nodes.get(edge.from).x);
                    int yy = Math.abs(nodes.get(edge.from).y - nodes.get(edge.to).y) / 2
                            + (nodes.get(edge.from).y - nodes.get(edge.to).y > 0 ? nodes.get(edge.to).y : nodes.get(edge.from).y);
                    g.drawString(edge.lable,
                            xx, yy);
                }
            }
        }
        //drawing nodes
        for (String key : nodes.keySet()) {
            Node node = nodes.get(key);
            int nodeWidth = Math.max(width, f.stringWidth(node.lable) + width / 2);
            g.setColor(Color.WHITE);
            g.fillOval(node.x - nodeWidth / 2, node.y - nodeHeight / 2,
                    nodeWidth, nodeHeight);
            g.setColor(Color.BLACK);
            g.drawString(node.lable,
                    node.x - f.stringWidth(node.lable) / 2,
                    node.y + f.getHeight() / 2);
            if (node.color != null) {
                try {
                    g.setColor((Color) Color.class.getField(node.color.toUpperCase()).get(null));
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                g.setColor(Color.BLACK);
            }
            g.drawOval(node.x - nodeWidth / 2, node.y - nodeHeight / 2,
                    nodeWidth, nodeHeight);
        }
    }

}
