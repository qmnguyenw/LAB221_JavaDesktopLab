/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Quang Nguyen
 */
public class Controller {
    CtoF frame;

    public Controller(CtoF frame) {
        this.frame = frame;
    }
    
    String NUMBER_VALID = "[0-9]{1,}|[0-9]{1,}.{1}[0-9]{1,}";
    
    boolean checkNumber(String input){
        return input.matches(NUMBER_VALID);
    }
    
    void addAction(JTextField TextField, JButton Button, JLabel Label){
        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = TextField.getText().trim();
                if (!checkNumber(input)) {
                    System.out.println("false");
                    Label.setText("");  
                }else{
                    double converterNumber = Double.parseDouble(TextField.getText().trim()) * 33.8;
                    converterNumber = Math.round(converterNumber*100)/100.0;
                    String str = "<html><font color='red'>" + converterNumber + "</font> Fahrenheit </html>";
                    Label.setText(str);  
                }
            }
        });
    }
    
    void addAction(){
        addAction(frame.getjTextField1(), frame.getjButton1(), frame.getjLabel2());
    }
    
    void visibleFrame() {
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        Controller controller = new Controller(new CtoF());        
        controller.addAction();
        controller.visibleFrame();
    }
}
