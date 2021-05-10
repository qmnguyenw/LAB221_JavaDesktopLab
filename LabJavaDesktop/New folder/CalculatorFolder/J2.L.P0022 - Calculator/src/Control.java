
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
public class Control {
    
    CalculatorFrame calculatorFrame;
    JLabel screen;
    double secondNumber, memory, secondMemory;
    boolean isAppend, isError, isFirstCal;
    String operator, preBt;
    
    public Control(CalculatorFrame calculatorFrame) {
        this.screen = calculatorFrame.getLabelDisplay();
        calculatorFrame.setResizable(false);
        calculatorFrame.setLocationRelativeTo(calculatorFrame);
        clearAll();
        clearSecMemory();
    }
    
    double getValueScreen() {
        return Double.parseDouble(screen.getText());
    }
    
    void resetFlag() {
        isAppend = false;
        isFirstCal = true;
        operator = null;
    }
    
    public void clearAll() {
        display("0");
        //reset all variables
        secondMemory = 0;
        memory = 0;
        secondNumber = 0;
        isAppend = false;
        isError = false;
        isFirstCal = true;
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
    }
    
    String formatNumber(double number) {
        String numberStr;
        int numbInt = (int)number;
        //if number equal integer value of number
        if (numbInt == number) {
            //set string to int value (no dot)
            numberStr = String.valueOf(numbInt);
        }
        //else set string to double value
        else {
            numberStr = String.valueOf(number);
        }
        //if length of number string is > 16, make it to 16 length
        if (numberStr.length() > 16) {
            numberStr = numberStr.substring(0,16);
        }
        return numberStr;
    }
    
    void buttonNumberDotPerform(String button) {
        isError = false;
        //if in append mode
        if (isAppend) {
            //max 1 dot on screen
            if (screen.getText().contains(".") && button.equals(".")) {
                return;
            }
            //if current text on screen is max length - 16, user cannot input more
            if (screen.getText().length() == 16) {
                return;
            }
            //append to current number on screen
            display(screen.getText() + button);
        }
        //not append mode
        else {
            //if not append to current number and usr press dot then displayy 0
            if (button.equals(".")) {
                display("0.");
            }else {
                display(button);
            }
            isAppend = true;
        }
        //set flag first calculator
        if (operator != null) {
            isFirstCal = false;
        }
        preBt = button;
    }
    
    void buttonUnaryOperationPerform(String unaryOperation) {
        //calculate if there is previous operation of ser and not error
        if (operator != null && !isError) {
            calculator();
            if (isError) {
                return;
            }
        }else if (!isError) {
            memory = getValueScreen();
        }
        //do unary operation 
        switch(unaryOperation) {
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
        preBt = unaryOperation;
    }
    
    void buttonOperationPerform(String operatorStr) {
        //not first calculation and previous press button must be number and must not error
        if (!isFirstCal && preBt.matches("[0-9]") && !isError) {
            calculator();
            preBt = operatorStr;
            if (isError) {
                return;
            }
        }
        //assign value on screen as first number to do next calculation
        if (!isError) {
            memory = getValueScreen();
        }
        operator = operatorStr;
        isAppend = false;
        preBt = operatorStr;
    }
    
    void calculator() {
        //if has error or has not operator or isFirstCal
        if (isError || operator == null || isFirstCal) {
            //situation not erro and user clickEqual, refix output
            if (!isError) {
                screen.setText(formatNumber(getValueScreen()));
            }
            return;
        }
        secondNumber = getValueScreen();
        //do operator
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
        calculator();
        //return when wrror
        if (isError) {
            return;
        }
        resetFlag();
    }
    
    public void clickBt0() {
        buttonNumberDotPerform("0");
    }
    
    public void clickBt1() {
        buttonNumberDotPerform("1");
    }
    
    public void clickBt2() {
        buttonNumberDotPerform("2");
    }
    
    public void clickBt3() {
        buttonNumberDotPerform("3");
    }
    
    public void clickBt4() {
        buttonNumberDotPerform("4");
    }
    
    public void clickBt5() {
        buttonNumberDotPerform("5");
    }
    
    public void clickBt6() {
        buttonNumberDotPerform("6");
    }
    
    public void clickBt7() {
        buttonNumberDotPerform("7");
    }
    
    public void clickBt8() {
        buttonNumberDotPerform("8");
    }
    
    public void clickBt9() {
        buttonNumberDotPerform("9");
    }
    
    public void clickBtDot() {
        buttonNumberDotPerform(".");
    }
    
    public void clickBtAdd() {
        buttonOperationPerform("+");
    }
    
    public void clickBtSubtract() {
        buttonOperationPerform("-");
    }
    
    public void clickBtMultiple() {
        buttonOperationPerform("*");
    }
    
    public void clickBtDivide() {
        buttonOperationPerform("/");
    }
    
    public void clickBtNegative() {
        buttonUnaryOperationPerform("neg");
    }
    
    public void clickBtPercent() {
        buttonUnaryOperationPerform("per");
    }
    
    public void clickBtInverse() {
        buttonUnaryOperationPerform("inv");
    }
    
    public void clickBtSqrt() {
        buttonUnaryOperationPerform("sqrt");
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
        secondMemory += getValueScreen();
    }
    
    public void clickMSub() {
        if (isError) {
            return;
        }
        isAppend = false;
        secondMemory -= getValueScreen();
    }
    
    
}
