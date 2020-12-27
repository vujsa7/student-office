package view.toolbar;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SearchBarTextField extends JLabel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 939262419465403913L;
	SearchBarTextField() {
		setLayout(new GridLayout(0,1));
		ImageIcon icon = getResizedIcon(new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"search_text_field.png"));
		setIcon(icon);
		setMaximumSize(new Dimension(178,36));
	}
	
	public ImageIcon getResizedIcon(ImageIcon icon) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(178, 36,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(resizedImage);
		return icon;
	}
	
}
