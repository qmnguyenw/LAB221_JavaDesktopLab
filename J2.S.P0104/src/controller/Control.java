/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.Main;

/**
 *
 * @author Thuy Trieu
 */
public class Control {

    private Main main;
    private int size;

    public Control(Main main) {
        this.main = main;
        size = main.table.getRowCount();
    }

    public void first() {
        main.table.setRowSelectionInterval(0, 0);
    }

    public void last() {
        main.table.setRowSelectionInterval(size - 1, size - 1);
    }

    public void next() {
        int selected = main.table.getSelectedRow();
        selected++;
        if (selected == size) {
            first();
        } else {
            main.table.setRowSelectionInterval(selected, selected);
        }
    }

    public void previous() {
        int selected = main.table.getSelectedRow();
        selected--;
        if (selected == -1) {
            last();
        } else {
            main.table.setRowSelectionInterval(selected, selected);
        }
    }
}
