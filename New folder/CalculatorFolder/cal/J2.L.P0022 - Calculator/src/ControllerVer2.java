
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Quang Nguyen
 */
public class ControllerVer2 {
    
    JLabel screen;
    double secondNumber, memory, secondMemory;
    boolean isAppend, isError, isFirstCalculator;
    String operator,preBt;
    
    public ControllerVer2(JLabel label, CalculatorFrame calculatorFrame) {
        this.screen = label;
        calculatorFrame.setResizable(false);
        calculatorFrame.setLocationRelativeTo(null);
        clearAll();
        clearSecMemory();
    }
    
    double getValueSrceen() {
        return Double.parseDouble(screen.getText());
    }
    
    void resetFlag() {
        isAppend = false;
        isFirstCalculator = true;
        operator = null;
    }
    
    public void clearAll() {
        display("0");
        //reset all variables 
        secondNumber = 0;
        memory = 0;
        secondMemory = 0;
        isAppend = false;
        isError = false;
        isFirstCalculator = true;
        operator = null;
        preBt = null;
    }
    
    void error() {
        isAppend = false;
        isError = true;
        display("Error");
    }
    
    void display(String str) {
        screen.setText(str);
    }
    
    void display(double number) {
        screen.setText(formatNumber(number));
        System.out.println(number);
    } 
    
    String formatNumber(double number) {
        String numberStr;
        int numberInt = (int)number;
        //if number equal integer value of number
        if (numberInt == number) {
            //set string to int value (no dot)
            numberStr = String.valueOf(numberInt);
        }
        //else set string to double value
        else {
            numberStr = String.valueOf(number);
        }
        //if length of number string is > 17, make it to 17 length
        if (numberStr.length() > 17) {
            numberStr = numberStr.substring(0,17);
        }
        return numberStr;
    }
    
    void buttonNumberDotPerform(String button) {
        isError = false;
        if (getValueSrceen() == 0) {
            isAppend = false;
        }
        //if in append mode
        if (isAppend) {
            //max 1 dot in screen
            if (screen.getText().contains(".") && button.equals(".")) {
                return;
            }
            //if current text on screen is max length (17), user cannot type more
            if (screen.getText().length() == 17) {
                return;
            }
            //append to current number on screen
            display(screen.getText() + button);
        }
        //not append mode
        else {
            //if not append mode and user press dot then display 0
            if (button.equals(".")) {
                display("0.");
            } else {
                display(button);
            }
            isAppend = true;
        }
        //set flag first calculation
        if (operator != null) {
            isFirstCalculator = false;
        }
        preBt = button;
    }
    
    void buttonOperatorPerform(String operatorStr) {
        //not first calculator and previous press button must be number and must not error
        if (!isFirstCalculator && preBt.matches("[0-9]") && !isError) {
            calculate();
            preBt = operatorStr;
            if (isError) {
                return;
            }
        }
        //assign value on screen as first number to do next calculation
        if (!isError) {
            memory = getValueSrceen();
        }
        operator = operatorStr;
        isAppend = false;
        preBt = operatorStr;
    }
    
    void buttonUnaryOperatorPerform(String unaryOperatorStr) {
        //calculate if there is previous operation of user and not error
        if (operator != null && !isError) {
            calculate();
            if (isError) {
                return;
            }
        }else if (!isError) {
            memory = getValueSrceen();
        }
        //do unary operation
        switch(unaryOperatorStr) {
            case "sqrt":
                //error case
                if (memory < 0) {
                    error();
                    return;
                }
                memory = Math.sqrt(memory);
                break;
            case "neg":
                memory *= -1;
                break;
            case "per":
                memory /= 100;
                break;
            case "inv":
                //error case
                if (memory == 0) {
                    error();
                    return;
                }
                memory = 1 / memory;
                break;
        }
        display(formatNumber(memory));
        resetFlag();
        preBt = unaryOperatorStr;
    }
    
    void calculate() {
        if (isError || operator == null || isFirstCalculator) {
            return;
        }
        secondNumber = getValueSrceen();
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
                if (secondNumber == 0) {
                    error();
                    return;
                }
                memory /= secondNumber;
                break;
        }
        display(memory);
    }
    
    public void clickEqual() {
        isAppend = false;
        preBt = "=";
        calculate();
        //return when error
        if (isError) {
            return;
        }
        resetFlag();
    }
    
    public void clickButton0() {
        buttonNumberDotPerform("0");
    }
    
    public void clickButton1() {
        buttonNumberDotPerform("1");
    }
    
    public void clickButton2() {
        buttonNumberDotPerform("2");
    }
    
    public void clickButton3() {
        buttonNumberDotPerform("3");
    }
    
    public void clickButton4() {
        buttonNumberDotPerform("4");
    }
    
    public void clickButton5() {
        buttonNumberDotPerform("5");
    }
    
    public void clickButton6() {
        buttonNumberDotPerform("6");
    }
    
    public void clickButton7() {
        buttonNumberDotPerform("7");
    }
    
    public void clickButton8() {
        buttonNumberDotPerform("8");
    }
    
    public void clickButton9() {
        buttonNumberDotPerform("9");
    }
    
    public void clickButtonDot() {
        buttonNumberDotPerform(".");
    }
    
    public void clickAdd() {
        buttonOperatorPerform("+");
    }
    
    public void clickSub() {
        buttonOperatorPerform("-");
    }
    
    public void clickMul() {
        buttonOperatorPerform("*");
    }
    
    public void clickDiv() {
        buttonOperatorPerform("/");
    }
    
    public void clickNegative() {
        buttonUnaryOperatorPerform("neg");
    }
    
    public void clickPercent() {
        buttonUnaryOperatorPerform("per");
    }
    
    public void clickSqrt() {
        buttonUnaryOperatorPerform("sqrt");
    }
    
    public void clickInverse() {
        buttonUnaryOperatorPerform("inv");
    }
    
    public void clearSecMemory() {
        secondMemory = 0;
    }
    
    public void clickMR() {
        display(secondMemory);
        isAppend = false;
    }
    
    public void clickMAdd() {
        if (isError) {
            return;
        }
        isAppend = false;
        secondMemory += getValueSrceen();
    }
    
    public void clickMSub() {
        if (isError) {
            return;
        }
        isAppend = false;
        secondMemory -= getValueSrceen();
    }
}
