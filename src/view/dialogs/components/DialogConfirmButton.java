package view.dialogs.components;

import java.awt.AlphaComposite;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DialogConfirmButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1985136620379147442L;
	private float alphaButton = 1f;
	@SuppressWarnings("unused")
	private ImageIcon hoveredConfirmIcon;
	public ImageIcon confirmIcon;
	public boolean validated;
	public static String entityID;
	
	public DialogConfirmButton() {
		setEnabled(false);
		confirmIcon = getResizedIcon(new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"potvrdi.png"));
		hoveredConfirmIcon = getResizedIcon(new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"potvrdi_hovered.png"));
		setIcon(confirmIcon);
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		

	}
	
	public ImageIcon getResizedIcon(ImageIcon icon) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(92, 36,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(resizedImage);
		return icon;
	}
	
	public float getAlpha() {
		return alphaButton;
	}

	public void setAlpha(float alpha) {
		this.alphaButton = alpha;
		this.repaint();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaButton));
		super.paintComponent(g2);
	}

	public void resetIcon() {
		setIcon(confirmIcon);
	}

	
}