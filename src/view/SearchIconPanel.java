package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SearchIconPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1967464923864376850L;

	public SearchIconPanel() {
		FlowLayout layout = (FlowLayout)this.getLayout();
		layout.setVgap(0);
		setMaximumSize(new Dimension(25,25));
		setPreferredSize(new Dimension(25,25));
		setBackground(Color.white);
		ImageIcon searchIcon = new ImageIcon("assets/icons/search.png");
		JLabel label = new JLabel();
		//label.setOpaque(false);
		label.setMaximumSize(new Dimension(25,25));
		label.setPreferredSize(new Dimension(25,25));
		label.setIcon(getResizedIcon(searchIcon));
		setBorder(new EmptyBorder(0, 10, 0, 0));
		add(label);
	}
	
	public ImageIcon getResizedIcon(ImageIcon icon) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(14, 14,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(resizedImage);
		return icon;
	}
	
}
