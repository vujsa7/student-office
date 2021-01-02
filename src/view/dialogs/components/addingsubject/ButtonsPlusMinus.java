package view.dialogs.components.addingsubject;

import java.awt.AlphaComposite;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class ButtonsPlusMinus extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8598026377845405100L;

	private float alpha = 0.7f;
	
	public ButtonsPlusMinus(String path) {
	
		setIcon(getResizedIcon(new ImageIcon(path)));
		
		
		setFocusPainted(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addMouseListener(new MyMouseListener());
	}
	
	public ImageIcon getResizedIcon(ImageIcon icon) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(36, 36,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(resizedImage);
		return icon;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
		this.repaint();
	}

	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		super.paintComponent(g2);
	}

	public class MyMouseListener extends MouseAdapter{
		public void mouseEntered(MouseEvent mouseEvent) {
			new Thread(
				new Runnable() {
					public void run() {
						for (float i = 0.7f; i <= 1f; i += 0.03f){
							setAlpha(i);
							try {
								Thread.sleep(10);
							} catch (Exception e) {
								
							}
						}
					}
				}).start();
	}
	
	public void mouseExited(MouseEvent mouseEvent) {
		new Thread(
				new Runnable() {
					public void run() {
						for (float i = 1f; i >= 0.7f; i -= 0.03f) {
							setAlpha(i);
							try{
								Thread.sleep(10);
							} catch (Exception e) {
								
							}
						}
					}
				}).start();
	}
	
	public void mousePressed(MouseEvent mouseEvent) {
		
		new Thread(
				new Runnable() {
					public void run() {
						for (float i = 1f; i >= 0.7f; i -= 0.1f) {
							setAlpha(i);
							try {
								Thread.sleep(1);
							} catch (Exception e) {
								
							}
						}
					}
				}).start();
	}
		
		
		
	}
}