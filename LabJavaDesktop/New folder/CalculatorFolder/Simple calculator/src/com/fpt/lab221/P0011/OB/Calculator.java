/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.lab221.P0011.OB;

import java.util.ArrayList;

/**
 *
 * @author vietddse62677
 */
public class Calculator {

    private int operator = -1;
    private double firstNum = 0;
    private double secondNum = 0;
    private boolean calcu = false;
    private boolean calcuM = false;
    private String currentView = "0";
    ArrayList<Double> mlist = new ArrayList<>();
    private boolean actionUni = false;
    private boolean isFistNum = false;

    public void result() {

        String value = "" + firstNum;
        if (value.endsWith(".0")) {// neu la so nguyen thi bo .0 di
            value = value.replace(".0", "");
            currentView = value;
        } else {
            float convertNumber = Float.parseFloat("" + firstNum);

            currentView = (convertNumber + "");
        }
    }

    public void displayInt() {
        if (currentView.endsWith(".0")) {
            currentView = currentView.replace(".0", "");
        }

    }

    public void cal() throws Exception {
        if (!calcu) {
            if (operator == -1) {
                firstNum = getValue();

            } else {
                secondNum = getValue();
                if (operator == 1) {
                    firstNum += secondNum;
                    result();
                }
                if (operator == 2) {
                    firstNum -= secondNum;
                    result();
                }
                if (operator == 3) {
                    firstNum *= secondNum;
                    result();
                }
                if (operator == 4) {
                    if (secondNum == 0) {
                        currentView = "Error";
                    } else {
                        firstNum /= secondNum;
                        result();
                    }
                }
                if (operator == 5) {
                    firstNum = firstNum * secondNum / 100;
                    result();
                }
            }
            calcu = true;
        }
    }

    public float getValue() {
        String value = currentView;
        if (value.endsWith(".")) {
            value = value.replace(".", "");
        }
        if (value.endsWith(".0")) {
            value = value.replace(".0", "");
        }
        return Float.parseFloat(value);
    }

    public String getCurrentView() {
        return currentView;
    }

    public void setCurrentView(String currentView) {
        this.currentView = currentView;
    }

    private double getMR() {
        double sum = 0;
        for (int i = 0; i < mlist.size(); i++) {
            sum = sum + mlist.get(i);
        }
        return sum;
    }

    public void pressNumber(String value) {
        if (!isNumber(value)) {
            try {
                if (value.equals("MP")) {
                    if (currentView.charAt(0) == '-') {
                        currentView = currentView.substring(1);
                    } else {
                        currentView = "-" + currentView;
                    }
                } else if (value.equals("MC")) {
                    mlist.clear();
                } else if (value.equals("<--")) {
                    currentView = currentView.substring(0, currentView.length() - 1);
                } else if (value.equals("CE")) {
                    currentView = "0";
                } else if (value.equalsIgnoreCase("MR")) {
                    currentView = getMR() + "";
                    actionUni = true;
                } else if (value.equalsIgnoreCase("M-")) {
                    mlist.add(-Double.parseDouble(currentView));
                    actionUni = true;
                } else if (value.equalsIgnoreCase("M+")) {
                    mlist.add(Double.parseDouble(currentView));
                    actionUni = true;
                } else if (value.equals("1/x")) {
                    
                    currentView = (1 / Float.parseFloat(currentView)) + "";
                    if(currentView.endsWith(".0")){
                        currentView=currentView.replace(".0", "");
                    }
                    actionUni = true;
                } else if (value.equals("SQ")) {
                    currentView = Math.sqrt(Float.parseFloat(currentView)) + "";
                    if(currentView.endsWith(".0")){
                        currentView=currentView.replace(".0", "");
                    }
                    actionUni = true;
                } else if (value.equals("C")) {
                    currentView = "0";
                    operator = -1;
                    firstNum = 0;
                    secondNum = 0;
                    calcu = false;
                    calcuM = false;
                    calcu = true;
                } else {
                    cal();
                }
            } catch (Exception e) {
                currentView = ("MathError");
            }
            if (value.equals("+")) {
                firstNum = getValue();
                operator = 1;
            } else if (value.equals("-")) {
                firstNum = getValue();
                operator = 2;
            } else if (value.equals("*")) {
                firstNum = getValue();
                operator = 3;
            } else if (value.equals("/")) {
                firstNum = getValue();
                operator = 4;
            } else if (value.equals("%")) {
                firstNum = getValue();
                operator = 5;
            } else if (value.equals("=")) {
                firstNum = getValue();
                operator = -1;
            }
        } else {
            if (value.equals(".") && currentView.contains(".")) {
                return;
            }
            if (calcu || calcuM || actionUni) {
                currentView = "0";
                calcu = false;
                calcuM = false;
                actionUni = false;
            }
            if (currentView.equals("0") && !value.equals(".")) {
                currentView = value;

            } else {
                currentView = (currentView + value);
            }
        }
    }

    private boolean isNumber(String value) {
        try {
            if (value.equals(".")) {
                return true;
            }

            int a = Integer.valueOf(value);
            return true;
        } catch (Exception e) {
            if ((operator == -1 && currentView.equals("0") && value.equals("-"))) {
                return true;
            }
            return false;
        }
    }
  
}
