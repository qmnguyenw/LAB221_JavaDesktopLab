
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Charles
 */
public class CourseManagement_Controller {

    
    CourseManagement cm = new CourseManagement();
    AddCourse ac = new AddCourse();
    AddCourse_Controller acc = new AddCourse_Controller();
    SearchCourse sc = new SearchCourse();
    ListCourse lc = new ListCourse();

    public void control() {
        cm.setVisible(true);
        clickAdd();
        clickExit();
        clickDisplay();
        clickSearch();
    }

    public void clickAdd() {
        cm.getjButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acc.control();
            }
        });
    }

    public void clickDisplay() {
        cm.getjButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acc.callList();
            }
        });
    }

    public void clickSearch() {
        cm.getjButton3().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acc.callSearch();
                acc.clickSearch();
            }
        });
    }

    public void clickExit() {
        cm.getjButton4().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.dispose();
            }
        });
    }

}
