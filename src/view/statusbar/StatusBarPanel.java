package view.statusbar;
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
 
import javax.swing.JPanel;
 
public class StatusBarPanel extends JPanel{
 
        /**
         *
         */
        private static final long serialVersionUID = -6000004294565053682L;
 
        public StatusBarPanel() {
                setBackground(Color.gray);
                setMaximumSize(new Dimension(1920, 35));
                setMinimumSize(new Dimension(0, 35));
                setLayout(new GridLayout(0,1));
                setBorder(null);
                JPanel status = new StatusBar();
                add(status);
        }
 
}
