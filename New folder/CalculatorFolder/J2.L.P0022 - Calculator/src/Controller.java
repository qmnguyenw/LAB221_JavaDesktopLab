
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MSI
 */
public class Controller {
    
    CalculatorFrame frame;
    JLabel screen;
    boolean append,isError,firstCal;
    String operator, preBt;
    double secondNumber, memory, secondMemory;
    
    public Controller(CalculatorFrame frame) {
        this.frame = frame;
        screen = frame.getLabelDisplay();
        frame.setLocationRelativeTo(frame);
        frame.setResizable(false);
        clearAll();
        clearSecMemory();
    }
    
    public void clickButton0() {
        numberDotButtonPerformed("0");
    }
    
    public void clickButton1() {
        numberDotButtonPerformed("1");
    }
    
    public void clickButton2() {
        numberDotButtonPerformed("2");
    }
    
    public void clickButton3() {
        numberDotButtonPerformed("3");
    }
    
    public void clickButton4() {
        numberDotButtonPerformed("4");
    }
    
    public void clickButton5() {
        numberDotButtonPerformed("5");
    }
    
    public void clickButton6() {
        numberDotButtonPerformed("6");
    }
    
    public void clickButton7() {
        numberDotButtonPerformed("7");
    }
    
    public void clickButton8() {
        numberDotButtonPerformed("8");
    }
    
    public void clickButton9() {
        numberDotButtonPerformed("9");
    }
    
    void clickAdd() {
        operatorButtonPerformed("+");
    }
    
    void clickSub() {
        operatorButtonPerformed("-");
    }
    
    void clickMul() {
        operatorButtonPerformed("*");
    }
    
    void clickDiv() {
        operatorButtonPerformed("/");
    }
    
    void clickDot() {
        numberDotButtonPerformed(".");
    }
    
    void clickSqr() {
        unaryOpeButton("sqrt");
    }
    
    void clickInverse() {
        unaryOpeButton("1/x");
    }
    
    void clickNegate() {
        unaryOpeButton("+/-");
    }
    
    void clickPercent() {
        unaryOpeButton("%");
    }
    
    void clickEqual() {
        if(isError || firstCal) return;
        calculate();
        operator = null;
        append = false;
        firstCal = true;
        preBt = "=";
    }
    
    void clickMp() {
        if(isError) return;
        secondMemory += getValueScreen();
    }
    
    void clickMm() {
        if(isError) return;
        secondMemory -= getValueScreen();
    }
    
    void clickMR() {
        display(secondMemory);
        append = false;
    }
    
    void clearSecMemory() {
        secondMemory = 0;
    }
    
    void clearAll() {
        display(0);
        memory = secondNumber =  0;
        append = isError = false;
        firstCal = true;
        operator = null;
        preBt = null;
    }
    
    void unaryOpeButton(String ope) {
        //if error, not change memory
        if(!isError) {
            //if not first calculate then cal before operation
            if(!firstCal) {
                calculate();
            //else cal current value on screen
            }else {
                memory = getValueScreen();
            }
        }
        append = false;
        firstCal = true;
        operator = null;
        //calculate with unary operation user has chosen
        switch(ope) {
            case "sqrt":
                //error case
                if(memory<0) {
                    error();
                    return;
                }
                memory = Math.sqrt(memory);
                break;
            case "1/x":
                //error case
                if(memory==0) {
                    error();
                    return;
                }
                memory = 1/memory;
                break;
            case "+/-":
                memory *= -1;
                break;
            case "%":
                memory /= 100;
                break;
        }
        display(memory);
        preBt = ope;
    }
    
    void numberDotButtonPerformed(String numberDot) {
        isError=false;
        //if in append mode, user can type the number
        if(append) {
            //maximum 1 dot
            if(screen.getText().contains(".") && numberDot.equals(".")) return;
            //if length of text on screen == 16, user can not type any more number
            if(screen.getText().length()==16) return;
            display(screen.getText()+numberDot);
        //else set the whole string on screen to the pressed number bt
        }else {
            if(numberDot.equals(".")) numberDot = "0"+numberDot;
            display(numberDot);
            append = true;
            if(operator != null) firstCal = false;
        }
        preBt = numberDot;
    }
    
    void error() {
        isError = true;
        display("ERROR");
    }
    
    void operatorButtonPerformed(String opeBt) {
        if(isError) return;
        append = false;
        //if before that, there is an operation (second number not null)
        //then calculate before calculation
        if(!firstCal && preBt.matches("[0-9]")) {
            calculate();
            if(isError) return;
        }
        //assign first number used in calculation
        memory = getValueScreen();
        //assign operator used in calculation
        operator = opeBt;
        preBt = opeBt;
    }
    
    void calculate() {
        //assign second number used in calculation
        secondNumber = getValueScreen();
        
        //calculate depend on operator
        switch(operator) {
            case "+":
                memory += secondNumber;
                break;
            case "-":
                memory -= secondNumber;
                break;
            case "*":
                memory *= secondNumber;
                break;
            case "/":
                //divide by 0 exception
                if(secondNumber==0) {
                    error();
                    return;
                }
                memory /= secondNumber;
                break;
        }
        display(memory);
    }
    
    void display(String s) {
        //if length of display text is more than 16, display only 16
        if(s.length()>16) s = s.substring(0, 16);
        screen.setText(s);
    }
    
    void display(double x) {
        screen.setText(formatNumber(x));
    }
    
    String formatNumber(double num) {
        String numStr;
        //int value of number
        long numInt = (long)num;
        //if number is an whole number
        if(num == numInt) numStr = String.valueOf(numInt);
        //if number is not whole number
        else numStr = String.valueOf(num);
        //if length of text display on screen is exceed 16
        //round it to 16 character (including dot and digits)
        if(numStr.length()>16) numStr = round16(numStr);
        return numStr;
    }
    
    double getValueScreen() {
        try{
            return Double.parseDouble(screen.getText());
        }catch(NumberFormatException e) {
            return 0;
        }
    }
    
    String round16(String numberStr) {
        double number = Double.parseDouble(numberStr);
        //if output is fractional numberStr
        if(numberStr.contains(".")) {
            //index of dot
            int pointIndex = numberStr.indexOf(".");
            //length of integer part
            int integerLength = numberStr.substring(0,pointIndex).length();
            //round to make the display number have length of 16 (including dot and digits)
            number = round(number, 15-integerLength);
            return String.valueOf(number);
        //output is integer
        }else {
            return numberStr.substring(0, 16);
        }
    }
    
    double round(double a, int places) {
        long factor = (long)Math.pow(10,places);
        return (double)Math.round(a*factor)/factor;
    }
}