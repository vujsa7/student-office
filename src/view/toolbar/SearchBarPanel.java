package view.toolbar;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SearchBarPanel extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 939262419465403913L;
	private Image image;
	SearchBarPanel(){
		ImageIcon icon = getResizedIcon(new ImageIcon("assets/icons/search_text_field.png"));
		image = icon.getImage();
		FlowLayout layout = (FlowLayout)this.getLayout();
		layout.setVgap(0);
		setMaximumSize(new Dimension(178,36));
		setOpaque(false);
	}
	
	public ImageIcon getResizedIcon(ImageIcon icon) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(178, 36,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(resizedImage);
		return icon;
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);        
    }
	
}
