/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.entity.Column;
import com.entity.Frog;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author vietddse62677
 */
public class HappyFrog extends javax.swing.JFrame implements Runnable {

    //variables
    public static final int WIDTH = 600,
            HEIGHT = 600,
            VELOCITY_COLUMN = 10,
            ACCELERATION_FROG = 1,
            JUMP_STRENGTH = 10;
    public Frog frog;
    public ArrayList<Column> columns;
    public Random random;
    public int score;
    public boolean isStarting;
    Thread thread;

    /**
     * Creates new form HappyFrog
     */
    public HappyFrog() {
        initComponents();
        this.setTitle("Flappy Frog");
        this.setLocationRelativeTo(null);
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (isStarting) {
                    //moves columns
                    moveColumns();
                    //Frog moves because of gravity
                    frogMoveGravity();
                    //check scorce
                    checkScore();;
                    //check die
                    checkDie();
                    //add to panel
                    addColumnToPanel();
                }
                Thread.currentThread().sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(HappyFrog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void addColumnToPanel() {
        for (Column column : columns) {
            if (column.getParent() == null) {
                panelMain.add(column);
            }
        }
    }

    /**
     * reset and repaint the panel, column, frog
     */
    public void initGame() {
        panelMain.removeAll();
        panelMain.repaint();
        score = 0;
        lbScore.setText("" + score);
        frog = new Frog(WIDTH / 2, HEIGHT / 2, 50, 50, "Frog");
        frog.setBounds(WIDTH / 2, HEIGHT / 2, 30, 30);
        panelMain.add(frog);
        columns = new ArrayList<>();
        random = new Random();
        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * adding columns to the panel
     *
     * @param start
     */
    public void addColumn(boolean start) {
        int space = 300;
        int width = 100;
        int height = 50 + random.nextInt(300);
        if (start) {
            columns.add(new Column(WIDTH + width + columns.size() * 300, HEIGHT - height, width, height));
            columns.add(new Column(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
        } else {
            columns.add(new Column(columns.get(columns.size() - 1).getBounds().x + 600, HEIGHT - height, width, height));
            columns.add(new Column(columns.get(columns.size() - 1).getBounds().x, 0, width, HEIGHT - height - space));
        }
    }

    private void checkDie() {
        if (frog.getY() < 0 || frog.getY() > HEIGHT) {
            isStarting = false;
            showGameOver();
        }
//        columns.stream().filter((column) -> (checkIntersect(frog, column))).forEachOrdered((_item) -> {
//            isStarting = false;
//            showGameOver();
//        });
        for (Column column : columns) {
            if (checkIntersect(frog, column)) {
                isStarting = false;
                showGameOver();
            }
        }
    }

    /**
     *
     * @param frog
     * @param column
     * @return isIntersect between Frog and Column
     */
    private boolean checkIntersect(Frog frog, Column column) {
        Rectangle r1 = new Rectangle(frog.getX(), frog.getY(), frog.getWidth(), frog.getHeight());
        Rectangle r2 = new Rectangle(column.getX(), column.getY(), column.getWidth(), column.getHeight());
        return r1.intersects(r2);
    }

    /**
     * Frog jumping
     */
    public void jump() {
        if (!isStarting) {
            return;
        }
        if (frog.getVelocityOy() > 0) {
            frog.setVelocityOy(0);
        }
        frog.setVelocityOy(frog.getVelocityOy() - JUMP_STRENGTH);
        frog.moveFrogOy();
    }

    private void moveColumns() {
        //move
        for (Column column : columns) {
            column.setVelocityOx(VELOCITY_COLUMN);
            column.moveColumnOx();
        }
        //if columns disappear
        ArrayList<Column> remove_list = new ArrayList<>();
        for (Column column : columns) {
            if (column.getX() + column.getWidth() < 0) {
                remove_list.add(column);
                panelMain.remove(column);
            }
        }
        //remove column that move out of the jpanel
        columns.removeAll(remove_list);
        for (int i = 0; i < remove_list.size() / 2; i++) {
            //re-adding
            addColumn(false);
        }
    }

    private void frogMoveGravity() {
        if (frog.getVelocityOy() < 15) {
            frog.setVelocityOy(frog.getVelocityOy() + ACCELERATION_FROG);
        }
        frog.moveFrogOy();
    }

    private void checkScore() {
        for (JButton column : columns) {
            if ((column.getX() + column.getWidth()) < frog.getX()) {
                score++;
                lbScore.setText("" + score);
            }
        }
    }

    private void showGameOver() {
        btnPause.setText("Start");
        String medal;
        if (score < 10) {
            medal = "No medal";
        } else if (score < 20) {
            medal = "Bronze medal";
        } else if (score < 30) {
            medal = "Sliver medal";
        } else if (score < 40) {
            medal = "Gold medal";
        } else {
            medal = "Platium medal";
        }
        JOptionPane.showMessageDialog(this,
                "Your score: " + score + "\nYou archived: " + medal,
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);
        btnPause.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        btnPause = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbScore = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMainMouseClicked(evt);
            }
        });
        panelMain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                panelMainKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 595, Short.MAX_VALUE)
        );

        btnPause.setText("Start");
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jLabel1.setText("Points:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPause)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbScore, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExit)
                        .addGap(66, 66, 66))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPause, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbScore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMainMouseClicked
        // TODO add your handling code here:
        jump();
    }//GEN-LAST:event_panelMainMouseClicked

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        // TODO add your handling code here:
        if (btnPause.getText() == "Start") {
            initGame();
            isStarting = true;
            btnPause.setText("Pause");
            panelMain.requestFocus();
        } else if (!isStarting) {
            btnPause.setText("Pause");
            isStarting = true;
            panelMain.requestFocus();
        } else {
            isStarting = false;
            btnPause.setText("Continue");
        }
    }//GEN-LAST:event_btnPauseActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void panelMainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_panelMainKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            jump();
        }
    }//GEN-LAST:event_panelMainKeyPressed

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
            java.util.logging.Logger.getLogger(HappyFrog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HappyFrog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HappyFrog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HappyFrog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HappyFrog().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnPause;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbScore;
    private javax.swing.JPanel panelMain;
    // End of variables declaration//GEN-END:variables

}
