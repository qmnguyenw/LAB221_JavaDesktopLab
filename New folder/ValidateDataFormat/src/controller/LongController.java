/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import frame.LongFrame;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
        
/**
 *
 * @author Quang Nguyen
 */
public class LongController {

    LongFrame frame;

    public LongController(LongFrame frame) {
        this.frame = frame;
        frame.setLocationRelativeTo(frame);
        frame.setResizable(false);
    }
    
    void showMessage(String message){
        JOptionPane.showMessageDialog(frame, message);
    }
    
    boolean RegexChecker(String checkItem, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(checkItem);
        
        if (matcher.matches()) {
            return true;//correct form 
        }else {
            return false;//incorrect form
        }
    }
    
    void validtxtCode(){
        String code = frame.getTxtCode().getText();
        String regex = "\\S+";
        if (RegexChecker(code, regex)) {
            showMessage("Code Field is empty.");
        }else {
            regex = "[a-zA-Z0-9]{1,10}";
            if (!RegexChecker(code, regex)) {
                showMessage("Code Field is incorrect form (max length is 10, not contains special characters).");
            }
        }
    }
    
    void validtxtName(){
        String name = frame.getTxtName().getText();
        String regex = "\\s+";
        if (RegexChecker(name, regex)) {
            showMessage("Name Field is empty.");
        }else {
            regex = ".{1,30}";
            if (!RegexChecker(name, regex)) {
                showMessage("Name Field is incorrect form (max length is 30).");
            }
        }
    }
    
    void validtxtBirthDate(){
        String birthDate = frame.getTxtBirthDate().getText();
        String regex = "\\s+";
        if (RegexChecker(birthDate, regex)) {
            showMessage("Birth Date Field is empty.");
        }else {
            regex = "";
            if (!RegexChecker(birthDate, regex)) {
                showMessage("Code Field is incorrect form().");
            }
        }
    }
    
    void validtxtPhoneNumber(){
        String code = frame.getTxtCode().getText();
        String regex = "\\s+";
        if (RegexChecker(code, regex)) {
            showMessage("Code Field is empty.");
        }else {
            regex = "";
            if (!RegexChecker(regex, regex)) {
                showMessage("Code Field is incorrect form().");
            }
        }
    }
    
    void validtxtEmail(){
        String code = frame.getTxtCode().getText();
        String regex = "\\s+";
        if (RegexChecker(code, regex)) {
            showMessage("Code Field is empty.");
        }else {
            regex = "";
            if (!RegexChecker(regex, regex)) {
                showMessage("Code Field is incorrect form().");
            }
        }
    }
        
    public static void main(String[] args) {
        new LongFrame().setVisible(true);
    }
    
}
