/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dialog.SearchCourse;
import entity.Course;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import validate.ValidateData;

/**
 *
 * @author MSI
 */
public class SearchController {
    ArrayList<Course> courseList;
    SearchCourse dialog;
    
    public SearchController(SearchCourse dialog, ArrayList<Course> list) {
        courseList = list;
        this.dialog = dialog;
        dialog.setLocationRelativeTo(dialog);
        dialog.setResizable(false);
    }
    
    public void btSearch() {
        String searchCode = dialog.getTxtCode().getText();
        //search code cannot empty
        if(!ValidateData.validString(searchCode)) {
            showMessage("Search field cannot blank");
            return;
        }
        
        //for each course in list
        for (Course c: courseList) {
            //if found search code, show it
            if(searchCode.equalsIgnoreCase(c.getCode())) {
                dialog.getTxtCode().setText(c.getCode());
                dialog.getTxtCredit().setText(c.getCredit()+"");
                dialog.getTxtName().setText(c.getName());
                return;
            }
        }
        dialog.getTxtCode().setText("");
        dialog.getTxtCredit().setText("");
        //not found then show message
        showMessage("Search code not found!");
    }
    
    void showMessage(String message) {
        JOptionPane.showMessageDialog(dialog, message);
    }
}
