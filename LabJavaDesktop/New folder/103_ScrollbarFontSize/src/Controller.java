
import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Quang Nguyen
 */
public class Controller {
    GUI m;
    
    Font f;
    int min = 13;
    int max = 50;
    int extent = 5;

    public Controller(GUI m) {
        this.m = m;
    }
    
    void addAction(){
        m.getjScrollBar1().setMinimum(min);
        m.getjScrollBar1().setMaximum(max);
        m.getjScrollBar1().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                m.getjLabel1().setFont(new Font(m.getjLabel1().getName(), Font.PLAIN, m.getjScrollBar1().getValue()));       
            }
        });
    }
    
    void setVisible(){
        m.setVisible(true);
    }
    
    public static void main(String[] args) {
        Controller controller = new Controller(new GUI());        
        controller.setVisible();
        controller.addAction();
    }

}
