import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
/**
 *
 * @author admin
 */
public class ComboBox extends JFrame{
    public static void main(String[] args) {
        ComboBox comboBox = new ComboBox();
    }
  
    private final JLabel sampleText = new JLabel("The quick brown fox jumps over the lazy dog");
    private final JComboBox fontComboBox;
    private final String[] fonts;
    
    public ComboBox() {
        //Khoi tao Panel
        JPanel controlPanel = new JPanel();
        //Them title
        String str = "Change Font";
        TitledBorder border = BorderFactory.createTitledBorder(str);
        controlPanel.setBorder(border);
        //Chinh sua Panel
        this.setSize(420, 170);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FontListener fl = new FontListener();
        //Vi tri cua text
        this.add(sampleText, BorderLayout.SOUTH);
        //Gan font vao comboBox
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fonts = g.getAvailableFontFamilyNames();
        fontComboBox = new JComboBox(fonts);
        fontComboBox.addActionListener(fl);
        controlPanel.add(new JLabel("Family: "));
        controlPanel.add(fontComboBox);
        this.add(controlPanel, BorderLayout.NORTH);
        fl.updateText(); 
        //Thong bao ve kieu font da chon
        controlPanel.add(new JLabel("The text is in: " + (String) fontComboBox.getSelectedItem()));
        this.add(controlPanel,BorderLayout.CENTER);
        
        this.setVisible(true);
    }
    private class FontListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            updateText();
        }
        
        public void updateText() {
            String fontname = (String) fontComboBox.getSelectedItem();
            Font f = new Font(fontname, Font.PLAIN, 16);
            sampleText.setFont(f);  
        }
    }
}