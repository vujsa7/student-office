package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchIconPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1967464923864376850L;

	public SearchIconPanel() {
		FlowLayout layout = (FlowLayout)this.getLayout();
		layout.setVgap(0);
		setMaximumSize(new Dimension(36,36));
		setPreferredSize(new Dimension(36,36));
		setOpaque(false);
		ImageIcon searchIcon = new ImageIcon("assets/icons/search_icon_panel.png");
		JLabel label = new JLabel();
		label.setOpaque(false);
		label.setIcon(getResizedIcon(searchIcon));
		add(label);
	}
	
	public ImageIcon getResizedIcon(ImageIcon icon) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(36, 36,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(resizedImage);
		return icon;
	}
	
}
