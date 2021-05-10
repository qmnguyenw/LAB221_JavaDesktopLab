
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class MyCanvas extends Canvas {

    DrawFunctionGraph dfg;
    Controler ct;

    int widthgraph = 0;
    int heightgraph = 0;
    double max_unit_horrizontal_coordinates_unchange = 0;
    double max_unit_vertical_coordinates_unchange = 0;
    double x_square_unchange = 0;
    double y_square_unchange = 0;

    double max_unit_horrizontal_coordinates = 0;
    double max_unit_vertical_coordinates = 0;
    double x_square = 0;
    double y_square = 0;
    double ratio = 1;
    double each_unit = 1;
    String s = "";
    double X_canvas = 0;
    double Y_canvas = 0;
    double A = 0;
    double B = 0;
    double C = 0;
    double from = 0;
    double to = 0;

    public double getX_square() {
        return x_square;
    }

    public void setX_square(double x_square) {
        this.x_square = x_square;
    }

    public double getY_square() {
        return y_square;
    }

    public void setY_square(double y_square) {
        this.y_square = y_square;
    }

    public double getX_square_unchange() {
        return x_square_unchange;
    }

    public void setX_square_unchange(double x_square_unchange) {
        this.x_square_unchange = x_square_unchange;
    }

    public double getY_square_unchange() {
        return y_square_unchange;
    }

    public void setY_square_unchange(double y_square_unchange) {
        this.y_square_unchange = y_square_unchange;
    }

    public double getMax_unit_horrizontal() {
        return max_unit_horrizontal_coordinates;
    }

    public void setMax_unit_horrizontal(double max_unit_horrizontal_coordinates) {
        this.max_unit_horrizontal_coordinates = max_unit_horrizontal_coordinates;
    }

    public double getMax_unit_vertical() {
        return max_unit_vertical_coordinates;
    }

    public void setMax_unit_vertical(double max_unit_vertical_coordinates) {
        this.max_unit_vertical_coordinates = max_unit_vertical_coordinates;
    }

    public double getMax_unit_horrizontal_coordinates_unchange() {
        return max_unit_horrizontal_coordinates_unchange;
    }

    public void setMax_unit_horrizontal_coordinates_unchange(double max_unit_horrizontal_coordinates_unchange) {
        this.max_unit_horrizontal_coordinates_unchange = max_unit_horrizontal_coordinates_unchange;
    }

    public double getMax_unit_vertical_coordinates_unchange() {
        return max_unit_vertical_coordinates_unchange;
    }

    public void setMax_unit_vertical_coordinates_unchange(double max_unit_vertical_coordinates_unchange) {
        this.max_unit_vertical_coordinates_unchange = max_unit_vertical_coordinates_unchange;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public double getX_canvas() {
        return X_canvas;
    }

    public void setX_canvas(double X) {
        this.X_canvas = X;
    }

    public double getY_canvas() {
        return Y_canvas;
    }

    public void setY_canvas(double Y) {
        this.Y_canvas = Y;
    }

    public double getEach_unit() {
        return each_unit;
    }

    public void setEach_unit(double each_unit) {
        this.each_unit = each_unit;
    }

    public MyCanvas() {
    }

    public MyCanvas(DrawFunctionGraph dfg, Controler ct) {
        this.dfg = dfg;
        this.ct = ct;

        this.setBackground(Color.white);
        this.setSize(dfg.getPnlGraph().getWidth(), dfg.getPnlGraph().getHeight());

        widthgraph = dfg.getPnlGraph().getWidth() - 5;
        heightgraph = dfg.getPnlGraph().getWidth() - 5;
        x_square_unchange = widthgraph / 20;
        y_square_unchange = heightgraph / 20;
        max_unit_horrizontal_coordinates_unchange = (widthgraph / 2) / (2 * x_square_unchange);
        max_unit_vertical_coordinates_unchange = (heightgraph / 2) / (2 * y_square_unchange);
    }

    @Override
    public void paint(Graphics g) {
        //draw snare
        drawSnare(g);
        //draw graph
        drawGraph(g);
        //draw coordinates string
        drawCoordinatesString(g);
        //draw function text
        drawFuctionText(g);
        //draw function graph
        drawFunctionGraph(g);
    }

    public void drawSnare(Graphics g) {

        int a = 0;
        int b = 0;

        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < 20; i++) {
            g.drawLine(a, 0, a, heightgraph);
            g.drawLine(0, b, widthgraph, b);
            a += x_square_unchange;
            b += y_square_unchange;
        }
    }

    public void drawGraph(Graphics g) {

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, widthgraph, heightgraph);
        //vertical axis
        g.drawLine(widthgraph / 2, 0, widthgraph / 2, heightgraph);
        //horizontal axis
        g.drawLine(0, heightgraph / 2, widthgraph, heightgraph / 2);
    }

    public void drawCoordinatesString(Graphics g) {

        int a = 0;
        int b = heightgraph / 2;
        int c = widthgraph / 2;
        int d = 0;

        //draw - and | 
        for (int i = 0; i < (20 / 2); i++) {
            a += 2 * x_square_unchange;
            d += 2 * y_square_unchange;

            g.drawLine(c - (int) (x_square_unchange) / 5, d, c + (int) (y_square_unchange) / 5, d);
            g.drawLine(a, b - (int) (x_square_unchange) / 5, a, b + (int) (y_square_unchange) / 5);
        }

        //reset d,a
        a = 0;
        d = 0;

        //draw number '-max_unit' -> 'max_unit' on Coordinates
        for (int j = -(int) (max_unit_horrizontal_coordinates - each_unit); j <= (int) (max_unit_horrizontal_coordinates - each_unit); j += each_unit) {
            a += 2 * x_square_unchange;
            d += 2 * y_square_unchange;

            if (j == 0) {
                j += each_unit;
                a += 2 * x_square_unchange;
                d += 2 * y_square_unchange;
            }
            //drawStringHorizontalAxis
            g.drawString(Integer.toString(j), a - (int) (x_square_unchange) / 6, b - (int) (y_square_unchange) / 3);
            //drawStringVerticalAxis
            int k = -j;
            g.drawString(Integer.toString(k), c + (int) (x_square_unchange) / 2, d + (int) (y_square_unchange) / 5);
        }
    }

    public void drawFuctionText(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawString(s, 10, 15);
    }

    public void drawFunctionGraph(Graphics g) {
        //Y = AX^2+BX+C
        //get the coefficient from Controller
        A = ct.getA();
        B = ct.getB();
        C = ct.getC();

        from = 2 * x_square * (max_unit_horrizontal_coordinates - (-ct.getFrom()));
        to = 2 * y_square * (max_unit_horrizontal_coordinates - (-ct.getTo()));

        //get the first point at canvas
        X_canvas = (int) from;
        Y_canvas = (int) (A * X_canvas * X_canvas + B * X_canvas + C);
        Point lastpoint = new Point((int) X_canvas, (int) Y_canvas);
        //
        for (int i = (int) from + 1; i <= (int) to; i++) {
            X_canvas = i;
            Y_canvas = (int) (A * X_canvas * X_canvas + B * X_canvas + C);
            g.setColor(Color.BLUE);
            g.drawLine(lastpoint.x, lastpoint.y, (int) X_canvas, (int) Y_canvas);
            lastpoint = new Point((int) X_canvas, (int) Y_canvas);
        }
    }
}
