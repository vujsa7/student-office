package view.dialogs.components.studentedit;

import java.awt.AlphaComposite;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import controller.StudentController;

public class RemoveUnsettledSubjectButton extends JButton{

		/**
	 * 
	 */
	private static final long serialVersionUID = -4992327612374941532L;
		private float alphaButton = 1f;
		public ImageIcon hoveredIcon;
		public ImageIcon icon;

		public RemoveUnsettledSubjectButton() {
			icon = getResizedIcon(new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"delete_button.png"));
			hoveredIcon = getResizedIcon(new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"delete_button_hovered.png"));
			setIcon(icon);
			setBorderPainted(false);
			setFocusPainted(false);
			setContentAreaFilled(false);
			setText("Obriši");
			setHorizontalTextPosition(JButton.CENTER);
			setVerticalTextPosition(JButton.CENTER);
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
		
		private class MyMouseListener extends MouseAdapter{
			public void mouseEntered(MouseEvent mouseEvent) {
				setIcon(hoveredIcon);
				new Thread(
						new Runnable() {
							public void run() {
								for (float i = 0.75f; i <= 1f; i += 0.02f){
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
				setIcon(icon);
				new Thread(
						new Runnable() {
							public void run() {
								for (float i = 1f; i >= 0.8f; i -= 0.07f){
									setAlpha(i);
									try {
										Thread.sleep(10);
									} catch (Exception e) {
										
									}
								}
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
					
					String selectedPredmet = StudentUnsettledSubjectsTablePanel.getInstance().getSelectedPredmet();
					String[] opcije = new String[2];
					opcije[0] = new String("Da");
					opcije[1] = new String("Ne");
					if(selectedPredmet != "NO_SELECTION") {
						int ponistavanje = JOptionPane.showOptionDialog(null, "Da li ste sigurni da želite da uklonite predmet?", "Uklanjanje predmeta", 0, 
								JOptionPane.INFORMATION_MESSAGE, null, opcije, null);
						
						if(ponistavanje == 0) {
							StudentController.getInstance().obrisiNepolozenIspit(selectedPredmet);
							
						}
					} else {
						JOptionPane.showMessageDialog(null, "Označite ocenu koju želite da poništite", "Napomena", JOptionPane.INFORMATION_MESSAGE);
					}
				}
		}

}
