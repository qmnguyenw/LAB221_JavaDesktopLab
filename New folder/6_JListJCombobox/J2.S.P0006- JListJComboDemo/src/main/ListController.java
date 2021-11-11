package main;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MSI
 */
public class ListController {
    ListDemo frame;
    
    public ListController(ListDemo frame) {
        this.frame = frame;
        
    }
    
    public void visibleFrame() {
        frame.setVisible(true);
    }
    
    void setGUI() {
        ImageTool tool = new ImageTool();
        
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(380,300);
        frame.setTitle("List Demo");
        
        JLabel label = frame.getjLabel1();
        JList list = frame.getjList1();
        list.setSelectedIndex(0);
        tool.setScaledLabelImgIcon(label, "img/0.jpg");
        
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tool.setScaledLabelImgIcon(label, "img/"+list.getSelectedIndex()+".jpg");
            }
        });
    }
    
    public static void main(String[] args) {
        ListController listControl = new ListController(new ListDemo());
        listControl.setGUI();
        ComboController comboControl = new ComboController(new ComboDemo());
        comboControl.setGUI();
        comboControl.visibleGUI();
        listControl.visibleFrame();
        
    }
    
}
