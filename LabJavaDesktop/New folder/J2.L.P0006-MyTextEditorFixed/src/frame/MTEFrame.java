/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import control.MainController;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author MSI
 */
public class MTEFrame extends javax.swing.JFrame {
    
    MainController controller;
    
    public MTEFrame() {
        initComponents();
        controller = new MainController(this);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanel();
        jScrollPane1 = new JScrollPane();
        textArea = new JTextArea();
        jMenuBar1 = new JMenuBar();
        menuFile = new JMenu();
        itNew = new JMenuItem();
        itOpen = new JMenuItem();
        itemSave = new JMenuItem();
        itSaveAs = new JMenuItem();
        itExit = new JMenuItem();
        menuEdit = new JMenu();
        itSelect = new JMenuItem();
        itCut = new JMenuItem();
        itCopy = new JMenuItem();
        itPaste = new JMenuItem();
        itUndo = new JMenuItem();
        itRedo = new JMenuItem();
        itFind = new JMenuItem();
        itReplace = new JMenuItem();
        itFont = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("My Text Editor (MTE)");
        setLocation(new Point(600, 270));

        textArea.setColumns(20);
        textArea.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
        );

        menuFile.setText("File");

        itNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        itNew.setText("New");
        itNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itNewActionPerformed(evt);
            }
        });
        menuFile.add(itNew);

        itOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        itOpen.setText("Open");
        itOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itOpenActionPerformed(evt);
            }
        });
        menuFile.add(itOpen);

        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        itemSave.setText("Save");
        itemSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itemSaveActionPerformed(evt);
            }
        });
        menuFile.add(itemSave);

        itSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK));
        itSaveAs.setText("Save as");
        itSaveAs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itSaveAsActionPerformed(evt);
            }
        });
        menuFile.add(itSaveAs);

        itExit.setText("Exit");
        itExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itExitActionPerformed(evt);
            }
        });
        menuFile.add(itExit);

        jMenuBar1.add(menuFile);

        menuEdit.setText("Edit");
        menuEdit.addMenuListener(new MenuListener() {
            public void menuCanceled(MenuEvent evt) {
            }
            public void menuDeselected(MenuEvent evt) {
            }
            public void menuSelected(MenuEvent evt) {
                menuEditMenuSelected(evt);
            }
        });

        itSelect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        itSelect.setText("Select all");
        itSelect.setEnabled(false);
        itSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itSelectActionPerformed(evt);
            }
        });
        menuEdit.add(itSelect);

        itCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        itCut.setText("Cut");
        itCut.setEnabled(false);
        itCut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itCutActionPerformed(evt);
            }
        });
        menuEdit.add(itCut);

        itCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        itCopy.setText("Copy");
        itCopy.setEnabled(false);
        itCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itCopyActionPerformed(evt);
            }
        });
        menuEdit.add(itCopy);

        itPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        itPaste.setText("Paste");
        itPaste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itPasteActionPerformed(evt);
            }
        });
        menuEdit.add(itPaste);

        itUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        itUndo.setText("Undo");
        itUndo.setEnabled(false);
        itUndo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itUndoActionPerformed(evt);
            }
        });
        menuEdit.add(itUndo);

        itRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
        itRedo.setText("Redo");
        itRedo.setEnabled(false);
        itRedo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itRedoActionPerformed(evt);
            }
        });
        menuEdit.add(itRedo);

        itFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        itFind.setText("Find");
        itFind.setEnabled(false);
        itFind.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itFindActionPerformed(evt);
            }
        });
        menuEdit.add(itFind);

        itReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        itReplace.setText("Replace");
        itReplace.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itReplaceActionPerformed(evt);
            }
        });
        menuEdit.add(itReplace);

        itFont.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
        itFont.setText("Change font");
        itFont.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itFontActionPerformed(evt);
            }
        });
        menuEdit.add(itFont);

        jMenuBar1.add(menuEdit);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itSelectActionPerformed(ActionEvent evt) {//GEN-FIRST:event_itSelectActionPerformed
        controller.itemSelectAll();
    }//GEN-LAST:event_itSelectActionPerformed

    private void itOpenActionPerformed(ActionEvent evt) {//GEN-FIRST:event_itOpenActionPerformed
        controller.itemOpen();
    }//GEN-LAST:event_itOpenActionPerformed

    private void itNewActionPerformed(ActionEvent evt) {//GEN-FIRST:event_itNewActionPerformed
        controller.itemNew();
    }//GEN-LAST:event_itNewActionPerformed

    private void itemSaveActionPerformed(ActionEvent evt) {//GEN-FIRST:event_itemSaveActionPerformed
        controller.itemSave();
    }//GEN-LAST:event_itemSaveActionPerformed

    private void itSaveAsActionPerformed(ActionEvent evt) {//GEN-FIRST:event_itSaveAsActionPerformed
        controller.itemSaveAs();
    }//GEN-LAST:event_itSaveAsActionPerformed

    private void itExitActionPerformed(ActionEvent evt) {//GEN-FIRST:event_itExitActionPerformed
        controller.itemExit();
    }//GEN-LAST:event_itExitActionPerformed

    private void itCutActionPerformed(ActionEvent evt) {//GEN-FIRST:event_itCutActionPerformed
        controller.itemCut();
    }//GEN-LAST:event_itCutActionPerformed

    private void itCopyActionPerformed(ActionEvent evt) {//GEN-FIRST:event_itCopyActionPerformed
        controller.itemCopy();
    }//GEN-LAST:event_itCopyActionPerformed

    private void itPasteActionPerformed(ActionEvent evt) {//GEN-FIRST:event_itPasteActionPerformed
        controller.itemPaste();
    }//GEN-LAST:event_itPasteActionPerformed

    private void itUndoActionPerformed(ActionEvent evt) {//GEN-FIRST:event_itUndoActionPerformed
        controller.itemUndo();
    }//GEN-LAST:event_itUndoActionPerformed

    private void itRedoActionPerformed(ActionEvent evt) {//GEN-FIRST:event_itRedoActionPerformed
        controller.itemRedo();
    }//GEN-LAST:event_itRedoActionPerformed

    private void itFindActionPerformed(ActionEvent evt) {//GEN-FIRST:event_itFindActionPerformed
        controller.itemFind();
    }//GEN-LAST:event_itFindActionPerformed

    private void itReplaceActionPerformed(ActionEvent evt) {//GEN-FIRST:event_itReplaceActionPerformed
        controller.itemReplace();
    }//GEN-LAST:event_itReplaceActionPerformed

    private void itFontActionPerformed(ActionEvent evt) {//GEN-FIRST:event_itFontActionPerformed
        controller.itemFont();
    }//GEN-LAST:event_itFontActionPerformed

    private void menuEditMenuSelected(MenuEvent evt) {//GEN-FIRST:event_menuEditMenuSelected
        controller.updateEnableItem();
    }//GEN-LAST:event_menuEditMenuSelected

    public JMenuItem getItCopy() {
        return itCopy;
    }

    public JMenuItem getItCut() {
        return itCut;
    }

    public JMenuItem getItPaste() {
        return itPaste;
    }

    public JMenuItem getItRedo() {
        return itRedo;
    }

    public JMenuItem getItSelect() {
        return itSelect;
    }

    public JMenuItem getItUndo() {
        return itUndo;
    }

    public JMenuItem getItFind() {
        return itFind;
    }
    
    
    public static void main(String args[]) {
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MTEFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MTEFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MTEFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MTEFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MTEFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JMenuItem itCopy;
    private JMenuItem itCut;
    private JMenuItem itExit;
    private JMenuItem itFind;
    private JMenuItem itFont;
    private JMenuItem itNew;
    private JMenuItem itOpen;
    private JMenuItem itPaste;
    private JMenuItem itRedo;
    private JMenuItem itReplace;
    private JMenuItem itSaveAs;
    private JMenuItem itSelect;
    private JMenuItem itUndo;
    private JMenuItem itemSave;
    private JMenuBar jMenuBar1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JMenu menuEdit;
    private JMenu menuFile;
    private JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}
