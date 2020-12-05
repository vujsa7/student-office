package view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.InputEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;


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
		
		JMenuItem close = new JMenuItem("Close");
		close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		ImageIcon closeIcon = getResizedIcon(new ImageIcon("assets/icons/close.png"));
		close.setIcon(closeIcon);
		
		file.add(minew);
		file.addSeparator();
		file.add(close);
		
		
		JMenu edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_T);
		
		JMenuItem miedit = new JMenuItem("Edit");
		miedit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		ImageIcon editIcon = getResizedIcon(new ImageIcon("assets/icons/edit.png"));
		miedit.setIcon(editIcon);
		
		JMenuItem delete = new JMenuItem("Delete");
		delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		ImageIcon deleteIcon = getResizedIcon(new ImageIcon("assets/icons/delete.png"));
		delete.setIcon(deleteIcon);
		
		
		edit.add(miedit);
		edit.addSeparator();
		edit.add(delete);
		
		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_P);
		
		JMenuItem mihelp = new JMenuItem("Help");
		mihelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		ImageIcon helpIcon = getResizedIcon(new ImageIcon("assets/icons/help.png"));
		mihelp.setIcon(helpIcon);
		
		JMenuItem about = new JMenuItem("About");
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		ImageIcon aboutIcon = getResizedIcon(new ImageIcon("assets/icons/about.png"));
		about.setIcon(aboutIcon);
		
		
		help.add(mihelp);
		help.addSeparator();
		help.add(about);
		
		add(file);
		add(edit);
		add(help);
		
		this.setBackground(new Color(179,179,179));
	}
	
	public ImageIcon getResizedIcon(ImageIcon icon) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(resizedImage);
		return icon;
	}
	
}
