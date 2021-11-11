/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bo.Memory;
import gui.Calculator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author Minh
 */
public class ControlCalculator implements ActionListener {

    private final Calculator calculator;
    private Memory m = null;
    private final DecimalFormat defaultFormat = new DecimalFormat("#.##############");
    private double result = 0;
    private static String operation = "";
    public boolean ready = true;
    public boolean performed = false;
    public boolean canCal = false;
    public String currentNumberText = "0";
    public boolean error = false;
    public boolean canAddDot = false;
    public boolean justPerform = false;     //If the btn performed has just activated

    public ControlCalculator(Calculator c) {
        this.calculator = c;
        defaultFormat.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.US));
        c.getBtnNum0().addActionListener(this);
        c.getBtnNum1().addActionListener(this);
        c.getBtnNum2().addActionListener(this);
        c.getBtnNum3().addActionListener(this);
        c.getBtnNum4().addActionListener(this);
        c.getBtnNum5().addActionListener(this);
        c.getBtnNum6().addActionListener(this);
        c.getBtnNum7().addActionListener(this);
        c.getBtnNum8().addActionListener(this);
        c.getBtnNum9().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        //if calculator has already number
        if (ready) {
            //if btnPerform was performed
            if (performed) {
                //when user want to add dot  after they push btnPerform
                if (canAddDot) {
                    if (!haveDot()) {
                        setText(getCurrentText() + ".");
                    } else {
                        setText("0." + command);
                    }
                //If canAddDot is false -> navigation a new calculation
                } else {
                    setText(command);
                }
                canAddDot = false;
                result = getCurrentNumber();
                operation = "";
                //If btnPerform wasn't performed -> navigation a new calculation
            } else {
                result = getCurrentNumber();
                setText(command);
            }
            ready = false;
            //if screen has a number already -> add number after that number
        } else {
            setText(getCurrentText() + command);
        }
        canCal = true;
        justPerform = false;
    }
    //Check if number have dot or not
    private boolean haveDot() {
        return calculator.getTxtDisplay().getText().contains(".");
    }
    //swap number
    private boolean haveSwapped() {
        return getCurrentNumber() <= 0;
    }
    //Get current number is displaying on screen
    public double getCurrentNumber() {
        return Double.parseDouble(getCurrentText());
    }
    //Check if it is number or string
    private boolean hasNumber(String s) {
        for (char c : s.toCharArray()) {
            if ("0123456789".contains("" + c)) {
                return true;
            }
        }
        return false;
    }

    //Return textString displayed on screen
    public String getCurrentText() {
        if (hasNumber(calculator.getTxtDisplay().getText())) {
            this.currentNumberText = calculator.getTxtDisplay().getText();
        } else {
            this.currentNumberText = "" + result;
        }
        return this.currentNumberText;
    }
    //SetText
    public void setText(String string) {
        calculator.getTxtDisplay().setText(string);
    }
    
    public void setText(double f) {
        calculator.getTxtDisplay().setText(defaultFormat.format(f));
    }
    
    //Btn addDot
    public void addDot() {
        if(justPerform){
            setText("0.");
            canAddDot = true;
            return;
        }
        if (!haveDot()) {
            setText(getCurrentText() + ".");
        } else {
            setText("0.");

        }
        canAddDot = true;

    }
    //Btn Swap number
    public void swap() {
        if (!haveSwapped()) {
            setText("-" + getCurrentText());
        } else {
            setText(getCurrentText().replace("-", ""));
        }
        perform();
    }
    //Btn sqrt number.
    public void sqrt() {
        double number = getCurrentNumber();

        if (number < 0) {
            setText("ERROR");
            return;
        }
        setText(Math.sqrt(getCurrentNumber()));
        ready = true;
        perform();

    }
    //Btn flip Number
    public void flip() {

        if (getCurrentNumber() == 0) {
            error = true;
            ready = true;
        } else {
            setText(1 / getCurrentNumber());
            ready = true;
        }
        perform();
        error = false;

    }
    //Btn percent number
    public void percent() {

        setText(getCurrentNumber() / 100);
        ready = true;
        perform();
        error = false;
    }
    //Btn Clear all data
    public void clear() {
        setText("0");
        mClear();
        result = 0;
        operation = "add";
    }
    
    //Btn perform result on screen
    public void perform() {       
        if (canCal) {
            calculator();
        }
        if (error) {
            setText("ERROR");

        } else {
            setText(result);
        }
        error = false;
        operation = "";
        ready = true;   //can navigatate to new calculation
        performed = true;   //this operation was performed
        canCal = true;  //can continue if user want continue calculation
        canAddDot = false;  //Can't not add Dot after performed to create new number

    }
    
    //Btn add number
    public void add() {

        if (canCal) {
            calculator();
        }
        operation = "add";
        canCal = false;

    }
    //Btn divide number
    public void div() {

        if (canCal) {
            calculator();
        }
        operation = "div";
        canCal = false;
    }
    //Btn substraction number
    public void sub() {

        if (canCal) {
            calculator();
        }
        operation = "sub";
        canCal = false;
    }
    //Btn multiple number
    public void multi() {

        if (canCal) {
            calculator();
        }
        operation = "multi";
        canCal = false;
    }
    //Calculator operation
    public void calculator() {

        if (operation.equalsIgnoreCase("add")) {
            result = result + getCurrentNumber();
        } else if (operation.equalsIgnoreCase("sub")) {
            result = result - getCurrentNumber();
        } else if (operation.equalsIgnoreCase("multi")) {
            result = result * getCurrentNumber();
        } else if (operation.equalsIgnoreCase("div")) {
            if (getCurrentNumber() == 0) {
                error = true;
                setText("ERROR");
                ready = true;
                return;
            } else {
                result = result / getCurrentNumber();
            }
        } else {
            result = getCurrentNumber();
            System.out.println(result);
            performed = false;
        }

        setText(result);
        ready = true;

    }
    //Memory Add
    public void mAdd() {
        if (m != null) {
            m.setM(m.getM() + getCurrentNumber());
        } else {
            m = new Memory(getCurrentNumber());
            calculator.getBtnMC().setEnabled(true);
            calculator.getBtnMR().setEnabled(true);
        }
        ready = true;
    }
    //Memory Sub
    public void mSub() {
        if (m != null) {
            m.setM(m.getM() - getCurrentNumber());
        } else {
            m = new Memory(0 - getCurrentNumber());
            calculator.getBtnMC().setEnabled(true);
            calculator.getBtnMR().setEnabled(true);
        }
        ready = true;
    }
    //clear in memory's data
    public void mClear() {
        calculator.getBtnMC().setEnabled(false);
        calculator.getBtnMR().setEnabled(false);
        m = null;
        ready = true;
    }
    //Display value of number in memory
    public void mRe() {
        setText(m.getM());
    }
}
