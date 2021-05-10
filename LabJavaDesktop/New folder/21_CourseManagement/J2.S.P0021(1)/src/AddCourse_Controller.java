
import com.sun.xml.internal.ws.util.StringUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
public class AddCourse_Controller {

    ArrayList<Courses> listCourse = new ArrayList<>();
    String listString = "";
    boolean flag = true;

    CourseManagement cm = new CourseManagement();
    AddCourse ac = new AddCourse();
    SearchCourse sc = new SearchCourse();
    ListCourse lc = new ListCourse();

    public void control() {
        ac.setVisible(true);
        clickClear();
        if (flag) {
            clickAdd();
        }
    }

    public void clickClear() {
        ac.getjButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
    }

    public void clear() {
        ac.getjTextField1().setText("");
        ac.getjTextField2().setText("");
        ac.getjTextField3().setText("");
    }

    public void clickAdd() {
        ac.getjButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ac.getjTextField1().getText().equals("") || ac.getjTextField2().getText().equals("") || ac.getjTextField3().getText().equals("")) {
                    JOptionPane.showMessageDialog(cm, "Code, name and credit cannot be empty");
                } else if (!ac.getjTextField3().getText().toString().matches("\\d+")) {
                    JOptionPane.showMessageDialog(cm, "Credit must be a positive number and less than or equals to 33");
                } else if (Integer.parseInt(ac.getjTextField3().getText().toString()) >= 33) {
                    JOptionPane.showMessageDialog(cm, "Credit must be a positive number and less than or equals to 33");
                } else if (!checkCourseExisted()) {
                    JOptionPane.showMessageDialog(cm, "Course's code is existed!");
                } else {
                    listString = ac.getjTextField1().getText().toUpperCase() + "   |   "
                            + convertToNormalForm(ac.getjTextField2().getText()) + "   |   "
                            + ac.getjTextField3().getText();
                    lc.getjTextArea1().append(listString);
                    lc.getjTextArea1().append("\n");
                    JOptionPane.showMessageDialog(cm, "Add successful!");
                }
                flag = false;
            }
        });
    }

    static String convertToNormalForm(String string) {
        // Create a char array of given String 
        char charArray[] = string.toCharArray();
        for (int i = 0; i < string.length(); i++) {
            // If first character of a word is found 
            if (i == 0 && charArray[i] != ' '
                    || charArray[i] != ' ' && charArray[i - 1] == ' ') {
                // If it is in lower-case 
                if (charArray[i] >= 'a' && charArray[i] <= 'z') {
                    // Convert into Upper-case 
                    charArray[i] = (char) (charArray[i] - 'a' + 'A');
                }
            } // If apart from first character 
            // Any one is in Upper-case 
            else if (charArray[i] >= 'A' && charArray[i] <= 'Z') // Convert into Lower-Case 
            {
                charArray[i] = (char) (charArray[i] + 'a' - 'A');
            }
        }
        // Convert the char array to equivalent String 
        String st = new String(charArray);
        return st;
    }

    public boolean checkCourseExisted() {
        String key = lc.getjTextArea1().getText();
        String[] lines = key.split("\\r?\\n");
        String[] code = null;
        String codeToCheck = ac.getjTextField1().getText();
        for (int i = 0; i < lines.length; i++) {
            code = lines[i].split("   \\|   ");
            if (code[0].toString().equalsIgnoreCase(codeToCheck)) {
                return false;
            }
        }
        return true;
    }

    public void callList() {
        lc.setVisible(true);
    }

    public void callSearch() {
        sc.setVisible(true);
    }

    public void clickSearch() {
        sc.getjButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = lc.getjTextArea1().getText();
                String[] lines = key.split("\\r?\\n");
                String[] code = null;
                String codeToSearch = sc.getjTextField1().getText();
                String name = null, credit = null;
                int count = 0;
                if (codeToSearch.isEmpty()) {
                    JOptionPane.showMessageDialog(cm, "Input code to search");
                } else {
                    for (int i = 0; i < lines.length; i++) {
                        code = lines[i].split("   \\|   ");
                        if (code[0].toString().equalsIgnoreCase(codeToSearch)) {
                            name = code[1].toString();
                            credit = code[2].toString();
                            sc.getjTextField1().setText(code[0]);
                            sc.getjTextField2().setText(name);
                            sc.getjTextField3().setText(credit);

                        } else {
                            count++;
                        }
                        if (count >= lines.length) {
                            JOptionPane.showMessageDialog(cm, "Not found any course");
                        }
                    }
                }

                sc.setVisible(true);
            }
        });
    }
}
