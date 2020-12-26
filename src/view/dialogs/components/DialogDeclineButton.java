package view.dialogs.components;

import java.awt.AlphaComposite;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DialogDeclineButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8774864633285567819L;
	private ImageIcon declineIcon;
	private ImageIcon hoveredDeclineIcon;
	private float alphaButton = 1f;
	
	public DialogDeclineButton() {
		declineIcon = getResizedIcon(new ImageIcon("assets/icons/ponisti.png"));
		hoveredDeclineIcon = getResizedIcon(new ImageIcon("assets/icons/ponisti_hovered.png"));
		setIcon(declineIcon);
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addMouseListener(new MyMouseListener());
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
	
	
	public class MyMouseListener extends MouseAdapter{
		
		public void mouseEntered(MouseEvent mouseEvent) {
			JButton thisButton = (JButton) mouseEvent.getComponent();
			thisButton.setIcon(hoveredDeclineIcon);
			new Thread(
					new Runnable() {
						public void run() {
							for (float i = 0.3f; i <= 1f; i += 0.07f){
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
			JButton thisButton = (JButton) mouseEvent.getComponent();
			
			new Thread(
					new Runnable() {
						public void run() {
							for (float i = 1f; i >= 0.7f; i -= 0.07f){
								setAlpha(i);
								try {
									Thread.sleep(10);
								} catch (Exception e) {
									
								}
							}
							thisButton.setIcon(declineIcon);
							setAlpha(1f);
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
