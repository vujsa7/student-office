package view.toolbar;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class SearchButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1985136620379147442L;
	private float alphaButton = 1f;
	private ImageIcon hoveredSearchIcon;
	private ImageIcon searchIcon;
	
	public SearchButton() {
		setEnabled(false);
		searchIcon = getResizedIcon(new ImageIcon("assets/icons/search_icon.png"));
		hoveredSearchIcon = getResizedIcon(new ImageIcon("assets/icons/hovered_search_icon.png"));
		setIcon(searchIcon);
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
		setText("Search");
		setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addMouseListener(new MyMouseListener());

	}
	
	public ImageIcon getResizedIcon(ImageIcon icon) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(104, 36,  java.awt.Image.SCALE_SMOOTH);
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
			if(thisButton.isEnabled()) {
				thisButton.setIcon(hoveredSearchIcon);
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
				thisButton.setForeground(Color.white);
			}

		}
		
		public void mouseExited(MouseEvent mouseEvent) {
			JButton thisButton = (JButton) mouseEvent.getComponent();
			if(thisButton.isEnabled()) {
				thisButton.setIcon(searchIcon);
				setAlpha(1f);
				thisButton.setForeground(Color.black);
			}

		}
		
		public void mousePressed(MouseEvent mouseEvent) {
			if(isEnabled()) {
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


	public void resetIcon() {
		setIcon(searchIcon);
	}
	
}
