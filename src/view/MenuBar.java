package view;
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
 
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
 
 
public class MenuBar extends JMenuBar{
       
        /**
         *
         */
        private static final long serialVersionUID = 5667853204203527406L;
 
        public MenuBar() {
               
                JMenu file = new JMenu("File");
                file.setMnemonic(KeyEvent.VK_F);
 
                JMenuItem minew = new JMenuItem("New");
                ImageIcon newIcon = getResizedIcon(new ImageIcon("assets/icons/new.png"));
                minew.setIcon(newIcon);
                minew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
                minew.setMnemonic(KeyEvent.VK_N);
               
                JMenuItem close = new JMenuItem("Close");
                close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
                ImageIcon closeIcon = getResizedIcon(new ImageIcon("assets/icons/close.png"));
                close.setIcon(closeIcon);
                close.setMnemonic(KeyEvent.VK_C);
               
                file.add(minew);
                file.addSeparator();
                file.add(close);
               
               
                JMenu edit = new JMenu("Edit");
                edit.setMnemonic(KeyEvent.VK_T);
               
                JMenuItem miedit = new JMenuItem("Edit");
                miedit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
                ImageIcon editIcon = getResizedIcon(new ImageIcon("assets/icons/edit.png"));
                miedit.setIcon(editIcon);
                miedit.setMnemonic(KeyEvent.VK_E);
               
                JMenuItem delete = new JMenuItem("Delete");
                delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
                ImageIcon deleteIcon = getResizedIcon(new ImageIcon("assets/icons/delete.png"));
                delete.setIcon(deleteIcon);
                delete.setMnemonic(KeyEvent.VK_D);
               
                edit.add(miedit);
                edit.addSeparator();
                edit.add(delete);
               
                JMenu help = new JMenu("Help");
                help.setMnemonic(KeyEvent.VK_P);
               
                JMenuItem mihelp = new JMenuItem("Help");
                mihelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
                ImageIcon helpIcon = getResizedIcon(new ImageIcon("assets/icons/help.png"));
                mihelp.setIcon(helpIcon);
                mihelp.setMnemonic(KeyEvent.VK_H);
               
                JMenuItem about = new JMenuItem("About");
                about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
                ImageIcon aboutIcon = getResizedIcon(new ImageIcon("assets/icons/about.png"));
                about.setIcon(aboutIcon);
                about.setMnemonic(KeyEvent.VK_A);
               
               
                help.add(mihelp);
                help.addSeparator();
                help.add(about);
               
                add(file);
                add(edit);
                add(help);
               
                setBackground(new Color(179,179,179));
                setPreferredSize(new Dimension(200, 35));
        }
       
        public ImageIcon getResizedIcon(ImageIcon icon) {
                Image image = icon.getImage();
                Image resizedImage = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
                icon = new ImageIcon(resizedImage);
                return icon;
        }
       
}
