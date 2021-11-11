package controller;

import bo.Memory;
import gui.Calculator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ControlCalculator implements ActionListener {

    private Calculator calculator;
    private Memory m = null;
    private final DecimalFormat defaultFormat = new DecimalFormat("#.#######");
    private double result = 0;
    private static String operation = "add";
    public boolean ready = true;
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
        if (ready) {
            result = getCurrentNumber();
            setText(command);
            ready = false;
        } else {
            setText(getCurrentText() + command);
        }
    }
    private boolean haveDot() {
        return calculator.txtDisplay.getText().contains(".");
    }

    private boolean haveSwapped() {
        return getCurrentNumber() <= 0;
    }

    public double getCurrentNumber() {
        return Double.parseDouble(getCurrentText());
    }

    public String getCurrentText() {
        return calculator.txtDisplay.getText();
    }

    public void setText(String string) {
        calculator.txtDisplay.setText(string);
    }

    public void setText(double f) {
        calculator.txtDisplay.setText(defaultFormat.format(f));
    }

    public void addDot() {
        if (!haveDot() && ready == false) {
            setText(getCurrentText() + ".");
            ready = false;
        }
        if (ready == true) {
            setText("0.");
            ready = false;
        }

    }
    // swap la +/-

    public void swap() {
        if (!haveSwapped()) {
            setText("-" + getCurrentText());
        } else {
            setText(getCurrentText().replace("-", ""));
        }
    }

    public void sqrt() {
        if (getCurrentNumber() < 0) {
            JOptionPane.showMessageDialog(calculator, "Can lam sao duoc so am :|");
            return;
        }
        setText(Math.sqrt(getCurrentNumber()));
        ready = true;
    }

    // flip la 1/x
    public void flip() {
        if (getCurrentNumber() == 0) {
            JOptionPane.showMessageDialog(calculator, "Khong duoc flip cho 0");
        } else {
            setText(1 / getCurrentNumber());
        }
        ready = true;
    }

    public void percent() {
        setText(getCurrentNumber() / 100);
        ready = true;
    }

    // delete la ham xoa nguoc
    public void delete() {
        if (!ready) {
            String str = getCurrentText();
            String result = "0";
            if (str.length() > 1) {
                result = str.substring(0, str.length() - 1);
            }
            if (str.length() == 1) {
                result = "0";
            } else if (str.startsWith("-") && str.length() == 2) {
                result = "0";
            }
            setText(result);
        }
    }

    public void clear() {
        setText("0");
        mClear();
        result = 0;
        operation = "add";
    }

    public void perform() {
        calculator();
        setText(result);
        operation = "";
        ready = true;
    }

    public void add() {
        if (ready) {
            operation = "add";
            return;
        }
        calculator();
        operation = "add";
    }

    public void div() {
        if (ready) {
            operation = "div";
            return;
        }
        calculator();
        operation = "div";
    }

    public void sub() {
        if (ready) {
            operation = "sub";
            return;
        }
        calculator();
        operation = "sub";
    }

    public void multi() {
        if (ready) {
            operation = "multi";
            return;
        }
        calculator();
        operation = "multi";
    }

    public void calculator() {
        if (operation.equalsIgnoreCase("add")) {
            result = result + getCurrentNumber();
        } else if (operation.equalsIgnoreCase("sub")) {
            result = result - getCurrentNumber();
        } else if (operation.equalsIgnoreCase("multi")) {
            result = result * getCurrentNumber();
        } else if (operation.equalsIgnoreCase("div")) {
            if (getCurrentNumber() == 0) {
                JOptionPane.showMessageDialog(calculator, "Khong duoc chia cho 0");
            } else {
                result = result / getCurrentNumber();
            }
        } else {
            result = getCurrentNumber();
        }
        setText(result);
        ready = true;
    }

    public void mAdd() {
        if (m != null) {
            m.setM(m.getM() + getCurrentNumber());
        } else {
            m = new Memory(getCurrentNumber());
            calculator.btnMC.setEnabled(true);
            calculator.btnMR.setEnabled(true);
        }
        ready = true;
    }

    //mstore là lưu cái đang hiển thị trên màn hình vào MR
    public void mStore() {
        m = new Memory(getCurrentNumber());
        calculator.btnMC.setEnabled(true);
        calculator.btnMR.setEnabled(true);
        ready = true;
    }

    public void mSub() {
        if (m != null) {
            m.setM(m.getM() - getCurrentNumber());
        } else {
            m = new Memory(0 - getCurrentNumber());
            calculator.btnMC.setEnabled(true);
            calculator.btnMR.setEnabled(true);
        }
        ready = true;
    }

    public void mClear() {
        calculator.btnMC.setEnabled(false);
        calculator.btnMR.setEnabled(false);
        m = null;
        ready = true;
    }

    public void mRe() {
        setText(m.getM());
        ready = true;
    }
}
