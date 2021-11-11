
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CheckInput {

    DrawFunctionGraph dfg;
    MyCanvas canvas;
    String status = "";
    Controler ct;

    public String getStatus() {
        return status;
    }

    public CheckInput() {

    }

    public CheckInput(DrawFunctionGraph dfg, MyCanvas canvas, Controler ct) {
        this.dfg = dfg;
        this.canvas = canvas;
        this.ct = ct;
    }

    //check
    public void displayError() {
        JOptionPane.showMessageDialog(dfg.getRootPane(), "Wrong type of Function.", "WARNING", JOptionPane.ERROR_MESSAGE);
    }

    public boolean checkFunction() {

        String function = dfg.getTxtFunction().getText().replaceAll("\\s+", "").trim();

        //
        while (function.matches(".*\\u002b\\u002b.*") || function.matches(".*\\u002d\\u002d.*") 
                || function.matches(".*\\u002d\\u002b.*") || function.matches(".*\\u002b\\u002d.*")) {
            //replace '++' || '--' -> '+'
            function = function.replaceAll("\\u002b\\u002b", "+");
            function = function.replaceAll("\\u002d\\u002d", "+");
            //replace '+-' || '-+' -> '-'
            function = function.replaceAll("\\u002b\\u002d", "-");
            function = function.replaceAll("\\u002d\\u002b", "-");
        }

        dfg.setTxtFunction(function);
        //define 'a'-coefficient of 'x^2', 'b' - coeficient of 'x', 'c'
        //when user input include 'a','b','c'
        //user input equation include 'a' or 'b' or 'c'
        if (function.matches("[+-]?([0-9]*[.]?[0-9]*)\\u0078\\u005e\\u0032+[+-][0-9]*[.]?[0-9]*\\u0078[+-]\\d+[.]?[0-9]*")) {
            status = "ax^2+bx+c";
            return true;
        } //user input 'ax^2+bx'
        else if (function.matches("[+-]?[0-9]*[.]?[0-9]*+\\u0078+\\u005e+\\u0032+[+-][0-9]*[.]?[0-9]*+\\u0078")) {
            status = "ax^2+bx";
            return true;
        } //user input 'ax^2+c'
        else if (function.matches("[+-]?[0-9]*[.]?[0-9]*+\\u0078+\\u005e+\\u0032+[+-]\\d+[.]?[0-9]*")) {
            status = "ax^2+c";
            return true;
        } //user input 'ax^2'
        else if (function.matches("[+-]?[0-9]*[.]?[0-9]*+\\u0078+\\u005e+\\u0032")) {
            status = "ax^2";
            return true;
        }
        //when user input equation include 'b' or 'c' not include 'a'
        //user input 'bx+c'
        if (function.matches("[+-]?[0-9]*[.]?[0-9]*+\\u0078+[+-]\\d+[.]?[0-9]*")) {
            status = "bx+c";
            return true;
        } //user input 'bx'
        else if (function.matches("[+-]?[0-9]*[.]?[0-9]*+\\u0078")) {
            status = "bx";
            return true;
        } //when user input equation include 'c' not 'a','b'
        else if (function.matches("[+-]?\\d+[.]?[0-9]*")) {
            status = "c";
            return true;
        }
        return false;
    }

    //check From And To 
    public void checkFromAndTo() {
        String f = dfg.getTxtFrom().getText().replaceAll("\\s+", "");
        String t = dfg.getTxtTo().getText().replaceAll("\\s+", "");

        if (f.isEmpty() || t.isEmpty()) {
            JOptionPane.showMessageDialog(dfg, "FROM or To can't be empty.", "ERROR", JOptionPane.ERROR_MESSAGE);
            ct.setFlag(false);
            return;
        }
        double from = 0;
        double to = 0;

        try {
            from = Double.parseDouble(f);
            to = Double.parseDouble(t);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(dfg, "Wrong type of FROM or TO.", "ERROR", JOptionPane.ERROR_MESSAGE);
            ct.setFlag(false);
            return;
        }

        if (from > to) {
            JOptionPane.showMessageDialog(dfg, "FROM must be less or equal than TO.", "ERROR", JOptionPane.ERROR_MESSAGE);
            ct.setFlag(false);
            return;
        } else {
            dfg.setTxtFrom(f);
            dfg.setTxtTo(t);
            ct.setFrom(from);
            ct.setTo(to);
            ct.setFlag(true);
        }
    }

    //check the ratio
    public void checkRatio(ArrayList<Double> x_coordinates, ArrayList<Double> y_coordinates) {
        //inittiallize value of max_unit_horrizontal_coordinates
        double x = Math.abs(x_coordinates.get(0));
        double y = Math.abs(y_coordinates.get(0));
        for (int i = 1; i < x_coordinates.size() - 2; i++) {
            //find the max x_coordinate
            if (Math.abs(x_coordinates.get(i)) > x) {
                x = Math.abs(x_coordinates.get(i));
            }
            if (Math.abs(y_coordinates.get(i)) > y) {
                y = Math.abs(y_coordinates.get(i));
            }
        }
        //when x_coordiantes > max_unit_hoor || y_coordiante > max_unit_ver
        double max_coordiante = 0;
        if (x > y) {
            max_coordiante = x;
        } else if (x <= y) {
            max_coordiante = y;
        }
        canvas.setRatio(Math.ceil(max_coordiante / canvas.getMax_unit_horrizontal_coordinates_unchange()));
        canvas.setMax_unit_horrizontal(canvas.getMax_unit_horrizontal_coordinates_unchange() * canvas.getRatio());
        canvas.setMax_unit_vertical(canvas.getMax_unit_vertical_coordinates_unchange() * canvas.getRatio());
        canvas.setX_square(canvas.getX_square_unchange() / canvas.getRatio());
        canvas.setY_square(canvas.getY_square_unchange() / canvas.getRatio());
//        }
        canvas.setEach_unit(Math.ceil(canvas.getMax_unit_horrizontal() / canvas.getMax_unit_horrizontal_coordinates_unchange()));
    }
}
