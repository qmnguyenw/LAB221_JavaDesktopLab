/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 *
 * @author Quang Nguyen
 */
public class Controller {
    Frame frame;
    Thread time;

    public Controller(Frame frame) {
        this.frame = frame;
    }
    
    private void displayTime() {
        time = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                    frame.jlbTime.setText(sdf.format(calendar.getTime()));
                    try {
                        time.sleep(1000);
                    } catch (Exception ex) {
                    }
                }
            }
        });
        time.start();
    }
    
    public static void main(String[] args) {
        Controller control = new Controller(new J2P0102.Frame());
        control.displayTime();
//        time.start();
    }
    
}
