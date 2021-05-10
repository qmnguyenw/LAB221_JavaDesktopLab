package gui;

import entity.Book;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

public class BookManagementUI extends javax.swing.JFrame {

    Vector<Book> bookList = new Vector<>();
    boolean isUpdate = false;

    public BookManagementUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bookCodeLabel = new javax.swing.JLabel();
        bookListLabel = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        bookNameLabel = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtAuthor = new javax.swing.JTextField();
        authorLabel = new javax.swing.JLabel();
        txtPublisher = new javax.swing.JTextField();
        publisherLabel = new javax.swing.JLabel();
        publishedYearLabel = new javax.swing.JLabel();
        rentCheckBox = new javax.swing.JCheckBox();
        exitB = new javax.swing.JButton();
        saveB = new javax.swing.JButton();
        removeB = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        cbxYear = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDisplay = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bookCodeLabel.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        bookCodeLabel.setText("Book code");
        getContentPane().add(bookCodeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 39, -1, -1));

        bookListLabel.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        bookListLabel.setText("Book List");
        getContentPane().add(bookListLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 6, -1, 17));
        getContentPane().add(txtCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 35, 155, -1));

        bookNameLabel.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        bookNameLabel.setText("Book name");
        getContentPane().add(bookNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 85, -1, -1));
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 81, 300, -1));
        getContentPane().add(txtAuthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 124, 300, -1));

        authorLabel.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        authorLabel.setText("Author");
        getContentPane().add(authorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 128, -1, -1));
        getContentPane().add(txtPublisher, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 170, 300, -1));

        publisherLabel.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        publisherLabel.setText("Publisher");
        getContentPane().add(publisherLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 174, -1, -1));

        publishedYearLabel.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        publishedYearLabel.setText("Published year");
        getContentPane().add(publishedYearLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 219, -1, -1));

        rentCheckBox.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        rentCheckBox.setText("For rent");
        getContentPane().add(rentCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 260, -1, -1));

        exitB.setText("Exit");
        exitB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBActionPerformed(evt);
            }
        });
        getContentPane().add(exitB, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 340, 112, -1));

        saveB.setText("Save");
        saveB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBActionPerformed(evt);
            }
        });
        getContentPane().add(saveB, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, 112, -1));

        removeB.setText("Remove");
        removeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBActionPerformed(evt);
            }
        });
        getContentPane().add(removeB, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 340, 112, -1));

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        getContentPane().add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 90, -1));

        cbxYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2000", "2001", "2002", "2003", "2004", "2005", "2006" }));
        getContentPane().add(cbxYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 120, -1));

        txtDisplay.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        txtDisplay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDisplayMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(txtDisplay);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 150, 320));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitBActionPerformed

    private void saveBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBActionPerformed
        txtCode.setEditable(true);

        String code = txtCode.getText().trim();
        String name = txtName.getText().trim().replaceAll("\\s+", " ");
        String author = txtAuthor.getText().trim();
        String publisher = txtPublisher.getText().trim();
        int position = cbxYear.getSelectedIndex();
        int year = 2000 + position;
        boolean rent = rentCheckBox.isSelected();

        int selectedBook = txtDisplay.getSelectedIndex();
        if (isUpdate == true) {
            if (checkValidData()) {
                bookList.get(selectedBook).setName(name);
                bookList.get(selectedBook).setAuthor(author);
                bookList.get(selectedBook).setPublisher(publisher);
                bookList.get(selectedBook).setPublishedYear(year);
                bookList.get(selectedBook).setRent(rent);
                JOptionPane.showMessageDialog(this, "Update successful");
                btnNewActionPerformed(evt);
            }

        } else if (isUpdate == false) {
            if (checkValidData() == true && findDupplicated() == true) {
                Book object = new Book(code, name, author, publisher, year, rent);
                bookList.add(object);
                JOptionPane.showMessageDialog(this, "Add successful");
                displayInList();
                btnNewActionPerformed(evt);
            }
        }
    }//GEN-LAST:event_saveBActionPerformed

    private void removeBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBActionPerformed
        if (bookList.size() == 0) {
            JOptionPane.showMessageDialog(this, "Data is empty");
        } else {
            int pos = txtDisplay.getSelectedIndex();
            if (pos == -1) {
                JOptionPane.showMessageDialog(this, "Please choose a name of book you want to remove");
            } else {
                bookList.remove(pos);
                isUpdate = false;
                displayInList();
                JOptionPane.showMessageDialog(this, "Remove successfull");
                btnNewActionPerformed(evt);
            }
        }
        txtDisplay.updateUI();
    }//GEN-LAST:event_removeBActionPerformed

    private void txtDisplayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDisplayMouseClicked
        int pos = txtDisplay.getSelectedIndex();
        txtCode.setText(bookList.get(pos).getCode());
        txtName.setText(bookList.get(pos).getName());
        txtAuthor.setText(bookList.get(pos).getAuthor());
        txtPublisher.setText(bookList.get(pos).getPublisher());
        int year = bookList.get(pos).getPublishedYear();
        int checkYear = year - 2000;
        cbxYear.setSelectedIndex(checkYear);
        boolean checkRent = bookList.get(pos).isRent();
        if (checkRent == true) {
            rentCheckBox.setSelected(true);
        } else {
            rentCheckBox.setSelected(false);
        }
        //Disable edit data
        txtCode.setEditable(false);
        isUpdate = true;
    }//GEN-LAST:event_txtDisplayMouseClicked

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        txtCode.setText("");
        txtName.setText("");
        txtAuthor.setText("");
        txtPublisher.setText("");
        cbxYear.setSelectedIndex(0);
        rentCheckBox.setSelected(false);
        isUpdate = false;

        txtCode.setEditable(true);
    }//GEN-LAST:event_btnNewActionPerformed

    public boolean checkValidData() {
        if (txtCode.getText().trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "Code is invalid", "Error", 0);
            txtCode.requestFocus();
            return false;
        } else if (txtName.getText().trim().replaceAll("\\s+", " ").length() <= 0) {
            JOptionPane.showMessageDialog(null, "Name is invalid", "Error", 0);
            txtName.requestFocus();
            return false;
        } else if (txtAuthor.getText().trim().replaceAll("\\s+", " ").length() <= 0) {
            JOptionPane.showMessageDialog(null, "Author is invalid", "Error", 0);
            txtAuthor.requestFocus();
            return false;
        } else if (txtPublisher.getText().trim().replaceAll("\\s+", " ").length() <= 0) {
            JOptionPane.showMessageDialog(null, "Publisher is invalid", "Error", 0);
            txtPublisher.requestFocus();
            return false;
        }
        return true;
    }

    public boolean findDupplicated() {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getCode().equals(txtCode.getText())) {
                JOptionPane.showMessageDialog(this, "Dupplicated Code");
                txtCode.requestFocus();
                return false;
            }
        }
        return true;
    }

    public void displayInList() {
        String[] listNameBook = new String[bookList.size()];
        for (int i = 0; i < bookList.size(); i++) {
            String nameOfOneBook = bookList.get(i).getName();
            listNameBook[i] = nameOfOneBook;
        }
         txtDisplay.setListData(listNameBook);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BookManagementUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookManagementUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookManagementUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookManagementUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookManagementUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authorLabel;
    private javax.swing.JLabel bookCodeLabel;
    private javax.swing.JLabel bookListLabel;
    private javax.swing.JLabel bookNameLabel;
    private javax.swing.JButton btnNew;
    private javax.swing.JComboBox<String> cbxYear;
    private javax.swing.JButton exitB;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel publishedYearLabel;
    private javax.swing.JLabel publisherLabel;
    private javax.swing.JButton removeB;
    private javax.swing.JCheckBox rentCheckBox;
    private javax.swing.JButton saveB;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JTextField txtCode;
    private javax.swing.JList<String> txtDisplay;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPublisher;
    // End of variables declaration//GEN-END:variables
}
