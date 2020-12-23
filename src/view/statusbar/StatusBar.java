package view.statusbar;
 
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Date;
import java.text.SimpleDateFormat;
 
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class StatusBar extends JPanel {
       
        /**
         *
         */
        private static final long serialVersionUID = 5669215768196097038L;
 
        public StatusBar() {
               
                BoxLayout boxStatus = new BoxLayout(this, BoxLayout.X_AXIS);
                setLayout(boxStatus);
                setBackground(new Color(179,179,179));
                setPreferredSize(new Dimension(1022, 35));
                setBorder(null);
               
                JLabel ime = new JLabel("Studentska služba");
               
                SimpleDateFormat formatter= new SimpleDateFormat("HH:mm    dd.MM.yyyy.");
               
                Date date = new Date(System.currentTimeMillis());
               
                JLabel vreme = new JLabel(formatter.format(date));
                
                add(Box.createHorizontalStrut(15));
                add(ime);
                add(Box.createGlue());
                add(vreme);
                add(Box.createHorizontalStrut(15));
                
                updateTime(vreme);
        }
       
        public void updateTime(JLabel vreme) {
               
                Thread t = new Thread() {
                       
                        public void run() {
                               
                                try {
                                        while(true) {
                                                SimpleDateFormat formatter= new SimpleDateFormat("HH:mm    dd.MM.yyyy.");
                                                Date date = new Date(System.currentTimeMillis());
                                                vreme.setText(formatter.format(date));
                                               
                                                Thread.sleep(1000);
                                        }      
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }      
                        }
                };
                t.start();
               
        }
}
