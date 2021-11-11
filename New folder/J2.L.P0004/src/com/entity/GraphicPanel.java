/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author vietddse62677
 */
public class GraphicPanel extends JPanel {

    /**
     * variable
     */
    public String infix;
    public String function;
    public float from, to;

    public int mouseX = -1;
    public int mouseY = -1;
    //

    public void curve(Graphics g, String f, float from, float to) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.blue);
        List<Point> list = new ArrayList<>();
        float step = (to - from) / 100;
        //drawing
        for (float x = from; x <= to; x += step) {
            String s = f.replaceAll("x", String.valueOf(x));
            ExpTree tree = new ExpTree(s);
            float y = (float) tree.postfixEval(tree.tree2Postfix());
            list.add(new Point(x, y));
            if (step == 0) {
                break;
            }
        }
        if (!list.isEmpty()) {
            float xs = (200 + 2 * list.get(0).x * 20);
            float ys = (200 - 2 * list.get(0).y * 20);
            if (step == 0) {
                g2d.draw(new Line2D.Float(xs, ys, xs, ys));
            } else {
                float xd, yd;
                for (int i = 0; i < list.size(); ++i) {
                    xd = (200 + 2 * list.get(i).x * 20);
                    yd = (200 - 2 * list.get(i).y * 20);
                    g2d.draw(new Line2D.Float(xs, ys, xd, yd));
                    xs = xd;
                    ys = yd;
                }
            }
        }
    }

    public void drawCenteredString(Graphics2D g2d, String label, int x0, int y0, float angle) {
        FontRenderContext frc = g2d.getFontRenderContext();
        Rectangle2D bounds = g2d.getFont().getStringBounds(label, frc);
        LineMetrics metrics = g2d.getFont().getLineMetrics(label, frc);
        if (angle == 0) {
            g2d.drawString(label, x0 - (float) bounds.getWidth() / 2, y0 + metrics.getHeight() / 2);
        } else {
            g2d.rotate(angle, x0, y0);
            g2d.drawString(label, x0 - (float) bounds.getWidth() / 2, y0 + metrics.getHeight() / 2);
            g2d.rotate(-angle, x0, y0);
        }
    }

    public void grid(Graphics g) {
        setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.lightGray);
        g2d.setStroke(new BasicStroke());
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        for (int x = 20; x <= 400; x += 20) {
            g2d.draw(new Line2D.Float(x, 0, x, 400));
        }
        for (int y = 0; y <= 400; y += 20) {
            g2d.draw(new Line2D.Float(0, y, 400, y));
        }
        g.setColor(Color.black);
        g2d.draw(new Line2D.Float(200, 0, 200, 400));
        g2d.draw(new Line2D.Float(0, 200, 400, 200));

        for (int i = 0; i < 9; i++) {
            if (i != 4) {
                int y = 400 - i * 40 - 40;
                g2d.draw(new Line2D.Float(198, y, 202, y));
                drawCenteredString(g2d, String.valueOf(i - 4), 212, y - 3, (float) 0);
                int x = i * 40 + 40;
                g2d.draw(new Line2D.Float(x, 198, x, 202));
                drawCenteredString(g2d, String.valueOf(i - 4), x, 188, (float) 0);
            }
        }
        drawCenteredString(g2d, "y", 212, 5, (float) 0);
        drawCenteredString(g2d, "x", 390, 186, (float) 0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent((Graphics2D) g); //To change body of generated methods, choose Tools | Templates.
        grid(g);
        if (function != null && !function.isEmpty()) {
            g.setColor(Color.MAGENTA);
            g.drawString("f(x) = " + infix, 10, 16);
            curve(g, function, from, to);
        }
        //trace
        g.setColor(Color.red);
        g.drawLine(0, mouseY, getWidth(), mouseY);
        g.drawLine(mouseX, 0, mouseX, getHeight());
        if (mouseX != -1 && mouseY != -1) {
            String s = String.format("(%.2f,%.2f", (mouseX - 200) / 40.0, (200 - mouseY) / 40.0);
            g.drawString(s, mouseX > getWidth() - 62 ? mouseX - 62 : mouseX + 2, mouseY < 12 ? mouseY + 12 : mouseY - 2);
        }
    }

}

class Point {

    protected float x, y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

}
