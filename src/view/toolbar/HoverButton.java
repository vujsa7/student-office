package view.toolbar;

import javax.swing.*;

import main.MainFrame;
import view.dialogs.StudentDialog;
import view.tab.TabBarButton;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class HoverButton extends JButton{
  /**
	 * 
	 */
	private static final long serialVersionUID = 8701578317825563407L;
	
	private float alpha = 0.7f;

	public HoverButton(String path, String toolTipText) {
		setIcon(getResizedIcon(new ImageIcon(path)));
		setToolTipText(toolTipText);
		setFocusPainted(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addMouseListener(new MyMouseListener());
	}
  
	public ImageIcon getResizedIcon(ImageIcon icon) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(26, 26,  java.awt.Image.SCALE_SMOOTH);
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
			if(TabBarButton.getActiveButton() == "Studenti") {
	        	 try {
					StudentDialog studentDialog = new StudentDialog(MainFrame.getInstance());
					studentDialog.setVisible(true);
				} catch (FontFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	 
	         } else if(TabBarButton.getActiveButton() == "Profesori") {
	        	 
	         } else {
	        	 
	         }
		}
	}
	
}
