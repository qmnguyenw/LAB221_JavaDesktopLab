
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
public class ComboController {
    ComboDemo frame;

    public ComboController(ComboDemo frame) {
        this.frame = frame;
    }
    
    public void setGUI() {
        frame.setLocation(800,300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("ComboBoxDemo");
        
        ImageTool tool = new ImageTool();
        JComboBox<String> cbb = frame.getjComboBox1();
        JLabel label = frame.getjLabel1();
        
        tool.setScaledLabelImgIcon(label, "img/0.jpg");
        
        cbb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tool.setScaledLabelImgIcon(label, "img/"+cbb.getSelectedIndex()+".jpg");
            }
        });
    }
    
    public void visibleGUI() {
        frame.setVisible(true);
    }
    
}
