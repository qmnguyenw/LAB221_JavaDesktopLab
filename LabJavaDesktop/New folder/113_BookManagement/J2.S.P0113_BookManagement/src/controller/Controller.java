/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import frame.BookManagementFrame;
import entity.Book;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JList;
import javax.swing.JOptionPane;


/**
 *
 * @author Quang Nguyen
 */
public class Controller {

    BookManagementFrame frame;
    ArrayList<Book> bookArrayList;
    JList<String> bookJList;
    boolean update;
    
    public Controller(BookManagementFrame frame) {
        this.frame = frame;
        frame.setLocationRelativeTo(frame);
        frame.setResizable(false);
        bookArrayList = new ArrayList<>();
        bookJList = frame.getBookList();
        update = false;
        loadCbbPublishedYear();
    }
    
    void loadCbbPublishedYear() {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        //get current year
        int year = Integer.parseInt(f.format(new Date()).split("-")[2]);
        //add years from 1990 to current year to combobox
        for (int i = 1990; i <= year; i++) {
            frame.getCbbPublishedYear().addItem(i + "");
        }
    }
    
    void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
    
    boolean valid(String code, String name, String author, String publisher) {
        //code field must be non-empty
        if (code.trim().isEmpty()) {
            showMessage("Code field is empty. Please re-input.");
            return false;
        }
        //name field must be non-empty
        if (name.trim().isEmpty()) {
            showMessage("Name field is empty. Please re-input.");
            return false;
        }
        //author field must be non-empty
        if (author.trim().isEmpty()) {
            showMessage("Author field is empty. Please re-input.");
            return false;
        }
        //publisher field must be non-empty
        if (publisher.trim().isEmpty()) {
            showMessage("Publisher field is empty. Please re-input.");
            return false;
        }
        return true;
    }
    
    boolean duplicateCodeWithIgnoreIndex(String newCode, int ignoreIndex) {
        //for each book in bookArrayList
        for (int i = 0; i < bookArrayList.size(); i++) {
            //if the position != old position and the new code has existed in the other position
            if (i != ignoreIndex && newCode.equalsIgnoreCase(bookArrayList.get(i).getCode())) {
                showMessage("This code has existed in list!!!");
                return true;
            }
        }
        return false;
    }
    
    boolean duplicateCode(String code) {
        //for each book in bookArrayList
        for (Book b : bookArrayList) {
            //if code is existed in list, show message
            if (b.getCode().equalsIgnoreCase(code)) {
                showMessage("This code has existed in list!!!");
                return true;
            }            
        }
        return false;
    }
    
    void displayDetail(int index) {
        Book selected = bookArrayList.get(index);
        //set values to corresponding component
        frame.getTxtCode().setText(selected.getCode());
        frame.getTxtName().setText(selected.getName());
        frame.getTxtAuthor().setText(selected.getAuthor());
        frame.getTxtPublisher().setText(selected.getPublisher());
        frame.getCbbPublishedYear().setSelectedItem(selected.getPublishYear() + "");
        frame.getCbIsRent().setSelected(selected.isForRent());
    }
    
    public void selectListAction() {
        int index = bookJList.getSelectedIndex();
        //if user click in list but not select
        if (index < 0 || index >= bookArrayList.size()) {
            return;
        }
        displayDetail(index);
        update = true;
    }
    
    void refreshList() {
        Vector<String> names = new Vector<>();
        //for all books in bookArrayList
        for (Book b : bookArrayList) {
            names.add(b.getName());
        }
        //display books in JList
        bookJList.setListData(names);
    }
    
    void update(String code, String name, String author, String publisher, int publishYear, boolean forRent, int index) {
        Book selected = bookArrayList.get(index);
        //update fields to selected book
        selected.setCode(code);
        selected.setName(name);
        selected.setAuthor(author);
        selected.setPublisher(publisher);
        selected.setPublishYear(publishYear);
        selected.setForRent(forRent);
    }
    
    public void btSaveAction() {
        String code = frame.getTxtCode().getText();
        String name = frame.getTxtName().getText();
        String author = frame.getTxtAuthor().getText();
        String publisher = frame.getTxtPublisher().getText();
        //validate text fields
        if (!valid(code, name, author, publisher)) {
            return;
        }
        int year = Integer.parseInt(frame.getCbbPublishedYear().getSelectedItem().toString());
        boolean rent = frame.getCbIsRent().isSelected();
        //if user perform update operation
        if (update) {
            int index = bookJList.getSelectedIndex();
            //if update book which has the old index and duplicate code with others  
            if (duplicateCodeWithIgnoreIndex(code, index)) {
                return;
            }
            //update selected book
            update(code, name, author, publisher, year, rent, index);
        }
        //if user perform add operation
        else {
            //if duplicate code
            if (duplicateCode(code)) {
                return;
            }
            //
            bookArrayList.add(new Book(code, name, author, publisher, year, rent));
        }
        refreshList();
        update = false;
    }
    
    public void btRemoveAction() {
        int index = bookJList.getSelectedIndex();
        //if user not select in list
        if (index < 0 || index >= bookArrayList.size()) {
            showMessage("Please select a book to remove");
            return;
        }
        bookArrayList.remove(index);
        refreshList();
        update = false;
    }
    
    public void btExitAction() {
        System.exit(0);
    }
    
    public static void main(String[] args) {
        new BookManagementFrame().setVisible(true);
    }
    
}
