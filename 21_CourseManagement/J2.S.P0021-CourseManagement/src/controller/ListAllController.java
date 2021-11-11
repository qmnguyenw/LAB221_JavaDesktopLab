/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dialog.ListCourse;
import entity.Course;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JTextArea;

/**
 *
 * @author MSI
 */
public class ListAllController {
    ArrayList<Course> courseList;
    ListCourse dialog;
    
    public ListAllController(ListCourse dialog, ArrayList<Course> list) {
        courseList = list;
        this.dialog = dialog;
        dialog.setLocationRelativeTo(dialog);
        dialog.setResizable(false);
        setTextArea();
    }
    
    void setTextArea() {
        Collections.sort(courseList, new Comparator<Course>() {
            @Override
            public int compare(Course c1, Course c2) {
                return c1.getCredit()>c2.getCredit()?1:-1;
            }
        });
        JTextArea area = dialog.getAreaDisplay();
        //for each course in list
        for (Course c: courseList) {
            //display info of current course to text area
            area.append(c.getCode()+" | "+c.getName()+" | "+c.getCredit()+"\n");
        }
    }
}
