/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dialog.AddCourse;
import entity.Course;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import validate.ValidateData;

/**
 *
 * @author MSI
 */
public class AddController {
    
    ArrayList<Course> courseList;
    AddCourse dialog;
    
    public AddController(AddCourse dialog, ArrayList<Course> list) {
        this.courseList = list;
        this.dialog = dialog;
        dialog.setLocationRelativeTo(dialog);
        dialog.setResizable(false);
    }
    
    public void btAdd() {
        String code = dialog.getTxtCode().getText();
        //valid code
        if(!ValidateData.validString(code)) {
            showMessage("Code field cannot blank");
            return;
        }
        if(duplicateCode(code)) {
            showMessage("This code has existed");
            return;
        }
        //valid name
        String name = dialog.getTxtName().getText();
        if(!ValidateData.validString(name)) {
            showMessage("Name field cannot blank");
            return;
        }
        name = normalize(name);
        //valid credit
        String creditStr = dialog.getTxtCredit().getText();
        //credit not blank
        if(!ValidateData.validString(creditStr)) {
            showMessage("Credit field cannot blank");
            return;
        }
        //credit must be integer >0 <= 33
        if(!ValidateData.validPosInt(creditStr)) {
            showMessage("Credit field must be a positive number and"
                    + " less than equal 33");
            return;
        }
        courseList.add(new Course(code, name, Integer.parseInt(creditStr)));
        showMessage("Add successfully!");
    }
    
//    String normalize(String course) {
//        String courseNormal = "";
//        int len = course.length();
//        //traverse all characters in string
//        for (int i = 0; i < len; i++) {
//            //get a character at current index
//            String character = String.valueOf(course.charAt(i));
//            //if current character is not digit or alphabet and not at last of string
//            if(!character.matches("[A-Za-z0-9]") && i != len-1) {
//                //uppercase next character then pass it
//                String upperCase = String.valueOf(course.charAt(++i)).toUpperCase();
//                //append current and next character to normalized string
//                courseNormal+=character+upperCase;
//            //else lowercase current character then append it to normalized string
//            }else {
//                courseNormal+=character.toLowerCase();
//            }
//        }
//        //uppercase first character
//        courseNormal = courseNormal.substring(0, 1).toUpperCase()+courseNormal.substring(1);
//        return courseNormal;
//    }
    
    String normalize(String course) {
        String courseNormal = "";
        String[] part = course.split("\\s+");
        for (int i = 0; i < part.length; i++) {
            part[i] = part[i].substring(0,1).toUpperCase() + part[i].substring(1).toLowerCase();
            courseNormal += (part[i] + " ");
        }
        courseNormal = courseNormal.trim();
        return courseNormal;
    }
    
    public void btClear() {
        dialog.getTxtCode().setText("");
        dialog.getTxtName().setText("");
        dialog.getTxtCredit().setText("");
    }
    
    boolean duplicateCode(String code) {
        //for each course in course list
        for (Course c : courseList) {
            //if code is existed in course list => duplicate
            if(code.equalsIgnoreCase(c.getCode())) return true;
        }
        return false;
    }
    
    void showMessage(String message) {
        JOptionPane.showMessageDialog(dialog, message);
    }
}
