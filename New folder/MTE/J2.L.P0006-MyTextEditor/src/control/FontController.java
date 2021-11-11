/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import frame.FontDialog;
import frame.MTEFrame;
import frame.TestFrame;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Position;

/**
 *
 * @author MSI
 */
public class FontController implements ListSelectionListener,FocusListener {

    FontDialog fontDialog;
    MTEFrame mainFrame;
    JTextArea textArea;

    JList<String> listFamily;
    JList<String> listStyle;
    JList<String> listSize;
    
    JTextField txtFamily;
    JTextField txtStyle;
    JTextField txtSize;
    
    boolean isTxtFamilyFocus, isTxtStyleFocus, isTxtSizeFocus;

    HashMap<String, Integer> styleMap;

    public FontController(MTEFrame mainFrame) {
        fontDialog = new FontDialog(mainFrame, this);
        textArea = mainFrame.getTextArea();
        isTxtFamilyFocus = false;
        isTxtStyleFocus = false;
        isTxtSizeFocus = false;
        setFontDialog();
        setTxtDocumentListener(txtFamily);
        setTxtDocumentListener(txtStyle);
        setTxtDocumentListener(txtSize);
        txtFamily.addFocusListener(this);
        txtStyle.addFocusListener(this);
        txtSize.addFocusListener(this);
    }
    
    void setTxtDocumentListener(JTextField textField) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //do operation with each txtfield when user edit text
                if (textField.equals(txtFamily)) {
                    System.out.println("familytxt");
                    txtFamilyEditText();
                }else if (textField.equals(txtStyle)){
                    System.out.println("styletxt");
                    txtStyleEditText();
                }else {
                    System.out.println("sizetxt");
                    txtSizeEditText();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                insertUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                insertUpdate(e);
            }
        });
    }

    void setFontDialog() {
        fontDialog.setLocationRelativeTo(mainFrame);
        
        listFamily = fontDialog.getListFamily();
        listStyle = fontDialog.getListStyle();
        listSize = fontDialog.getListSize();
        
        System.out.println("visible row of list size: "+listSize.getVisibleRowCount());
        
        txtFamily = fontDialog.getTxtFamily();
        txtStyle = fontDialog.getTxtStyle();
        txtSize = fontDialog.getTxtSize();

        //get all font family of system
        String[] fontFamilies = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();
        //set all font to list
        listFamily.setListData(fontFamilies);

        String styles[] = {"Regular", "Italic", "Bold", "Bold Italic"};
        listStyle.setListData(styles);

        Vector<String> sizes = new Vector<>();
        //add size from 8 to 12
        for (int i = 8; i <= 12; i++) {
            sizes.add(i + "");
        }
        //add size from 14 to 28
        for (int i = 14; i <= 28; i += 2) {
            sizes.add(i + "");
        }
        //add the remaining
        sizes.add("36");
        sizes.add("48");
        sizes.add("72");
        listSize.setListData(sizes);

        listFamily.addListSelectionListener(this);
        listStyle.addListSelectionListener(this);
        listSize.addListSelectionListener(this);
        
        //create hash map of style
        styleMap = new HashMap<>();
        styleMap.put("Regular", Font.PLAIN);
        styleMap.put("Italic", Font.ITALIC);
        styleMap.put("Bold", Font.BOLD);
        styleMap.put("Bold Italic", Font.BOLD + Font.ITALIC);

    }

    void setTxtAreaFontToList() {

        Font currentFont = textArea.getFont();
        
        //set font to sample label
        fontDialog.getLbSample().setFont(currentFont);
        fontDialog.getTxtSample().setFont(currentFont);
        
        //set current size
        txtSize.setText(currentFont.getSize() + "");
        listSize.setSelectedValue(currentFont.getSize() + "", true);
        scrollVisibleMatchValueOnTop(listSize, txtSize.getText());
        //set current font family
        txtFamily.setText(currentFont.getFamily());
        listFamily.setSelectedValue(currentFont.getFamily(), true);
        scrollVisibleMatchValueOnTop(listFamily, txtFamily.getText());
        
        String currentStyle = getKeyFromValue(styleMap, currentFont.getStyle());

        //set current style
        txtStyle.setText(currentStyle);
        listStyle.setSelectedValue(currentStyle, false);

        
    }

    public void visibleFontDialog() {
        setTxtAreaFontToList();
        fontDialog.setVisible(true);
        testFrame.setVisible(true);
        g = testFrame.getjPanel1().getGraphics();
    }

    TestFrame testFrame = new TestFrame();
    Graphics g;

    public void btOK() {
        
        Font currentFont = getCurrentFont();
        if (currentFont != null) {
            textArea.setFont(currentFont);
            fontDialog.setVisible(false);
        }

    }

    Font getCurrentFont() {
        String fontFamily;
        String styleStr;
        String sizeStr;

        int style = -1, size = -1;

        fontFamily = fontDialog.getTxtFamily().getText();
        styleStr = fontDialog.getTxtStyle().getText();
        sizeStr = fontDialog.getTxtSize().getText();

        //if font in txt not in list, show msg
        if (getIndexAllMatchIgnoreCase(listFamily, fontFamily) == -1) {
            showMessage("There is no font with that name.\n"
                    + "Choose a font from the list of fonts.");
            return null;
        }

        //if style in txt not in list, show msg
        if (getIndexAllMatchIgnoreCase(listStyle, styleStr) == -1) {
            showMessage("This font is not available in that style.\n"
                    + "Choose a style from the list of styles.");
            return null;
        }

        style = styleMap.get(styleStr);

        try {
            size = Integer.parseInt(sizeStr);
            //size > 1000
            if (size > 1000) {
                showMessage("Size must not exceed 1000.");
                return null;
            }
            //size < 0
            if (size < 0) size = 1;
        //wrong number format
        } catch (NumberFormatException e) {
            showMessage("Size must be a number.");
            return null;
        }
        
        return new Font(fontFamily, style, size);
    }

    public void btCancel() {
        fontDialog.setVisible(false);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        
        Font sampleFont = fontDialog.getTxtSample().getFont();
        
        if (e.getSource().equals(listFamily)) {
            if(listFamily.isSelectionEmpty()) return;
            //selected font family
            String selectFamily = listFamily.getSelectedValue();
            //create new sample font with selected family
            sampleFont = new Font(selectFamily, sampleFont.getStyle(), 
                    sampleFont.getSize());
            //set new font to sample
            fontDialog.getTxtSample().setFont(sampleFont);
            fontDialog.getLbSample().setFont(sampleFont);
            if(!isTxtFamilyFocus) txtFamily.setText(selectFamily);
        }else if(e.getSource().equals(listStyle)) {
            if(listStyle.isSelectionEmpty()) return;
            //selected style
            String selectStyle = listStyle.getSelectedValue();
            //create new sample font with selected style
            sampleFont = sampleFont.deriveFont(styleMap.get(selectStyle));
            //set new font to sample
            fontDialog.getTxtSample().setFont(sampleFont);
            fontDialog.getLbSample().setFont(sampleFont);
            if (!isTxtStyleFocus) txtStyle.setText(selectStyle);
        }else {
            if(listSize.isSelectionEmpty()) return;
            //selected size
            String selectSize = listSize.getSelectedValue();
            //create new font with selected size
            sampleFont = new Font(sampleFont.getFamily(), sampleFont.getStyle(),
                    Integer.valueOf(selectSize));
            //set new font to sample
            fontDialog.getTxtSample().setFont(sampleFont);
            fontDialog.getLbSample().setFont(sampleFont);
            if(!isTxtSizeFocus) txtSize.setText(selectSize);
        }
        
        System.out.println("");
    }

    void txtFamilyEditText() {
        //if txtfield family is focused
        if (isTxtFamilyFocus) {
            scrollVisibleMatchValueOnTop(listFamily, txtFamily.getText());
            int index = getIndexAllMatchIgnoreCase(listFamily, txtFamily.getText());
            //if list family contain value in txtfield family, select the value
            if (index != -1) {
                System.out.println("contain");
                listFamily.setSelectedIndex(index);
//                txtFamily.setText(listFamily.getSelectedValue());
            //clear selection in list
            }else {
                listFamily.clearSelection();
            }
        }
    }

    void txtStyleEditText() {
        //if txtfield style is focused
        if (isTxtStyleFocus) {
            int index = getIndexAllMatchIgnoreCase(listStyle, txtStyle.getText());
            //if list style contain value in txtfield style, select the value
            if (index != -1) {
                listStyle.setSelectedIndex(index);
//                txtStyle.setText(listStyle.getSelectedValue());
            //clear selection in list
            }else {
                listStyle.clearSelection();
            }
        }
    }

    void txtSizeEditText() {
        //if txtfield size is focused
        if (isTxtSizeFocus) {
            scrollVisibleMatchValueOnTop(listSize, txtSize.getText());
            int index = getIndexAllMatchIgnoreCase(listSize, txtSize.getText());
            //if list size contain value in txtfield size, select the value
            if (index != -1) {
                listSize.setSelectedIndex(index);
            //clear selection in list
            }else {
                listSize.clearSelection();
            }
        }
    }

    public void btTest() {
        try {
            System.out.println("12");

//            DefaultListModel lm = new DefaultListModel();
//            listFamily.setModel(lm);
            int start = Integer.parseInt(fontDialog.testStart.getText());
            int end = Integer.parseInt(fontDialog.testEnd.getText());

            Rectangle draw = listSize.getCellBounds(start, end);

            listSize.scrollRectToVisible(draw);
            listSize.clearSelection();
            g.drawRect(draw.x, draw.y, draw.width, draw.height);

//            listFamily.setSelectedIndex(12);
//            listFamily.setSelectedValue(null, false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void showMessage(String message) {
        JOptionPane.showMessageDialog(fontDialog, message, "Font",
                JOptionPane.INFORMATION_MESSAGE);
    }

    String getKeyFromValue(HashMap<String, Integer> map, int value) {
        for (String key : map.keySet()) {
            if (map.get(key) == value) {
                return key;
            }
        }
        return null;
    }
    
    int getIndexAllMatchIgnoreCase(JList<String> list, String val) {
        ListModel<String> lm = list.getModel();
        //for each element in list
        for (int i = 0; i < lm.getSize(); i++) {
            if(val.equalsIgnoreCase(lm.getElementAt(i)))
                return i;
        }
        return -1;
    }
    
    int getIndexFirstMatchIgnoreCase(JList<String> list, String val) {
        return list.getNextMatch(val, 0, Position.Bias.Forward);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getComponent().equals(txtFamily)) {
            isTxtFamilyFocus = true;
            System.out.println("gain");
        }else if(e.getComponent().equals(txtStyle)) {
            isTxtStyleFocus = true;
        }else {
            isTxtSizeFocus = true;
        }
        JTextField txtfield = (JTextField)e.getSource();
        txtfield.selectAll();
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getComponent().equals(txtFamily)) {
            isTxtFamilyFocus = false;
            System.out.println("lost");
        }else if(e.getComponent().equals(txtStyle)) {
            isTxtStyleFocus = false;
        }else {
            isTxtSizeFocus = false;
        }
    }
    
    void scrollVisibleMatchValueOnTop(JList<String> list, String value) {
        //get the index of value in txtfield first match with a value in JList
        int startIndex = getIndexFirstMatchIgnoreCase(list, value);
        //if value is existed then scroll to visible on top, otherwise not scroll
        if (startIndex != -1) {
            //the end index of visible range
            int endIndex = startIndex + list.getVisibleRowCount();
            System.out.println("visible row: "+ list.getVisibleRowCount());
            System.out.println(startIndex + " " + endIndex);
            //if exceed the visible range, adjust it
            if (endIndex >= list.getModel().getSize()) {
                endIndex -= (endIndex - list.getModel().getSize()+1);
            }
            //get the visible range
            Rectangle visibleRange = list.getCellBounds(startIndex, endIndex);
            //scroll list to visible it
            list.scrollRectToVisible(visibleRange);
        }
    }
    
}
