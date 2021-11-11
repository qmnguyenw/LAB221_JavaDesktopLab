/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dialog.AddCourse;
import dialog.ListCourse;
import dialog.SearchCourse;
import entity.Course;
import frame.CourseManagement;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class MainController {
    
    ArrayList<Course> courseList;
    
    CourseManagement mainFrame;
    
    public MainController(CourseManagement f) {
        this.mainFrame = f;
        f.setLocationRelativeTo(f);
        f.setResizable(false);
        courseList = new ArrayList<>();
    }
    
    public void btAdd() {
        new AddCourse(mainFrame,true,courseList).setVisible(true);
    }
    
    public void btDisplay() {
        new ListCourse(mainFrame,true,courseList).setVisible(true);
    }
    
    public void btSearch() {
        new SearchCourse(mainFrame,true,courseList).setVisible(true);
    }
    
    public void btExit() {
        System.exit(0);
    }
}
