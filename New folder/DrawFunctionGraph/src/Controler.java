
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Controler {

    DrawFunctionGraph dfg;
    MyCanvas canvas;
    CheckInput ck;

    double A = 0;
    double B = 0;
    double C = 0;
    double max_unit_horrizontal_coordinates = 0;
    double max_unit_vertical_coordinates = 0;
    double from = 0;
    double to = 0;
    boolean flag = true;

    public double getA() {
        return A;
    }

    public double getB() {
        return B;
    }

    public double getC() {
        return C;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Controler() {
        dfg = new DrawFunctionGraph();
        canvas = new MyCanvas(this.dfg, this);
        ck = new CheckInput(this.dfg, this.canvas, this);
    }

    //Button Controller
    public void buttonController() {
        //Draw
        dfg.getBtnDraw().addActionListener((e) -> {
            if (!ck.checkFunction()) {
                ck.displayError();
            } else {
                setTextOnCanvas();
                identifyCf();
                canvas.repaint();
            }
        });

        //Save
        dfg.getBtnSave().addActionListener((e) -> {
            saveCanvas(canvas);
        });

        //Quit
        dfg.getBtnQuit().addActionListener((e) -> {
            System.exit(0);
        });
    }

    //Set blue text of function on Canvas
    public void setTextOnCanvas() {
        canvas.setS("f(x)=" + dfg.getTxtFunction().getText());
    }

    //Identify coefficent of variable
    public void identifyCf() {
        String cf = dfg.getTxtFunction().getText().replaceAll("\\s+", "");
        String status = ck.getStatus();
        //coefficient of x^2 - 'a'
        double a = 0;
        //coefficient of x - 'a'
        double b = 0;
        //coefficient 'c'
        double c = 0;

        //index of 1st x
        int x1 = cf.indexOf("x");
        //index of 2nd x
        int x2 = cf.indexOf("x", x1 + 1);

        //when equation not include 'a' or 'b' or 'c', that means 'a' or 'b' or 'c' automatic = 0
        //find 'a' when user input equation include 'a'
        if (status.equals("ax^2+bx+c") || status.equals("ax^2+bx") || status.equals("ax^2+c") || status.equals("ax^2")) {
            switch (cf.substring(0, x1)) {
                case "":
                case "+":
                    a = Double.parseDouble("1");
                    break;
                case "-":
                    a = Double.parseDouble("-1");
                    break;
                default:
                    a = Double.parseDouble(cf.substring(0, x1));
                    break;
            }
        }

        //find 'b' when user input equation include 'b'
        //when including 'a' || 'c'
        if (status.equals("ax^2+bx+c") || status.equals("ax^2+bx")) {
            switch (cf.substring(x1 + 3, x2)) {
                case "+":
                case "":
                    b = Double.parseDouble("1");
                    break;
                case "-":
                    b = Double.parseDouble("-1");
                    break;
                default:
                    b = Double.parseDouble(cf.substring(x1 + 3, x2));
                    break;
            }
        }

        //when NOT including 'a' || 'c'
        if (status.equals("bx+c") || status.equals("bx")) {
            switch (cf.substring(0, x1)) {
                case "":
                case "+":
                    b = Double.parseDouble("1");
                    break;
                case "-":
                    b = Double.parseDouble("-1");
                    break;
                default:
                    b = Double.parseDouble(cf.substring(0, x1));
                    break;
            }
        }

        //find 'c' when user input equation include 'c'
        //when include 'a' && 'b'
        if (status.equals("ax^2+bx+c")) {
            c = Double.parseDouble(cf.substring(x2 + 1));
        }
        //when include 'a' && NOT 'b'
        if (status.equals("ax^2+c")) {
            c = Double.parseDouble(cf.substring(x1 + 3));
        }
        //when include 'b' && NOT 'a'
        if (status.equals("bx+c")) {
            c = Double.parseDouble(cf.substring(x1 + 1));
        }
        //when not include 'a' && 'b'
        if (status.equals("c")) {
            c = Double.parseDouble(cf);
        }

        //check from and to
        ck.checkFromAndTo();

        //flag check from and to
        if (flag) {
            //DrawFucntionGraph        
            ArrayList<Double> x_coordinate = new ArrayList<>();
            ArrayList<Double> y_coordinate = new ArrayList<>();
            ArrayList<Double> x_canvas = new ArrayList<>();
            ArrayList<Double> y_canvas = new ArrayList<>();

            //add roots of graph's coordinates equation to list
            for (double i = from; i <= to + 2; i++) {
                //x_coordinate root is 'from' to 'to'
                x_coordinate.add(i);
                y_coordinate.add(a * Math.pow(i, 2) + b * i + c);
            }

            ck.checkRatio(x_coordinate, y_coordinate);

            //max unit of horrizontal
            max_unit_horrizontal_coordinates = canvas.getMax_unit_horrizontal();
            //max unit of vertical
            max_unit_vertical_coordinates = canvas.getMax_unit_vertical();

            x_coordinate.forEach((x_coordinates) -> {
                //find x_canvas of canvas's coordiante and add x_canvas to list from x_coordinate
                x_canvas.add(2 * canvas.getX_square() * (max_unit_horrizontal_coordinates - (-x_coordinates)));
            });

            //find y_canvas from y_coordinate
            y_coordinate.forEach((y_coordinates) -> {
                y_canvas.add(2 * canvas.getY_square() * (max_unit_vertical_coordinates - y_coordinates));
            });

            //Using CRAMER Algorithm to find A,B,C
            //X^2
            ArrayList<Double> x_canvas_pow2_subtract = new ArrayList<>();
            //X
            ArrayList<Double> x_canvas_subtract = new ArrayList<>();
            //Y
            ArrayList<Double> y_canvas_subtract = new ArrayList<>();

            //find coefficent of 2 linear equations with 2 roots 
            for (int i = 0; i < x_canvas.size() - 1; i++) {
                //X1^2 - X2^2
                x_canvas_pow2_subtract.add(Math.pow(x_canvas.get(i), 2) - Math.pow(x_canvas.get(i + 1), 2));
                //X1-X2
                x_canvas_subtract.add(x_canvas.get(i) - x_canvas.get(i + 1));
                //Y1-Y2
                y_canvas_subtract.add(y_canvas.get(i) - y_canvas.get(i + 1));
            }
            //Find det D,Dx,Dy
            //D = a1*b2 - a2*b1
            double D = x_canvas_pow2_subtract.get(0) * x_canvas_subtract.get(1) - x_canvas_pow2_subtract.get(1) * x_canvas_subtract.get(0);
            //Dx = c1*b2 - c2*b1
            double Dx = y_canvas_subtract.get(0) * x_canvas_subtract.get(1) - y_canvas_subtract.get(1) * x_canvas_subtract.get(0);
            //Dy = a1*c2 - a2*c1        
            double Dy = x_canvas_pow2_subtract.get(0) * y_canvas_subtract.get(1) - x_canvas_pow2_subtract.get(1) * y_canvas_subtract.get(0);
            //Find A,B,C
            A = Dx / D;
            B = Dy / D;
            C = y_canvas.get(0) - A * Math.pow(x_canvas.get(0), 2) - B * x_canvas.get(0);
        }
    }

    //Save as image
    public static void saveCanvas(Canvas canvas) {

        BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.SCALE_FAST);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        canvas.paint(g2);
        try {
            ImageIO.write(image, "png", new File("img\\canvas1.png"));
            JOptionPane.showMessageDialog(canvas.getParent(), "COMPLETE", "Saved", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | IOException e) {
        }
    }

    public void control() {
        dfg.getPnlGraph().add(canvas);
        dfg.setVisible(true);
        buttonController();
    }

    public static void main(String[] args) {
        new Controler().control();

    }

}
