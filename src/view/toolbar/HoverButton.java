package view.toolbar;

import javax.swing.*;

import view.dialogs.ProfessorDialog;
import view.dialogs.ProfessorEditDialog;
import view.dialogs.StudentDialog;
import view.tab.TabBarButton;
import view.table.TablePanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HoverButton extends JButton{
  /**
	 * 
	 */
	private static final long serialVersionUID = 8701578317825563407L;
	private String buttonType;
	private float alpha = 0.7f;

	public HoverButton(String path, String toolTipText) {
		buttonType = toolTipText;
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

	public class MyMouseListener extends MouseAdapter{public void mouseEntered(MouseEvent mouseEvent) {
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
				
		if(buttonType.equals("New")) {
			
			if(TabBarButton.getActiveButton() == "Studenti") {
				StudentDialog studentDialog = StudentDialog.getInstance();
				studentDialog.setDefaultValues();
				studentDialog.setVisible(true);
			} else if(TabBarButton.getActiveButton() == "Profesori") {
				ProfessorDialog professorDialog = ProfessorDialog.getInstance();
	        	professorDialog.setDefaultValues();
	        	professorDialog.setVisible(true);
	        } else {
	        	 // ako se dodaje predmet
	        }
			
		} else if(buttonType.equals("Edit")) {
			
			if(TabBarButton.getActiveButton() == "Studenti") {
				// ako se menja student
			} else if(TabBarButton.getActiveButton() == "Profesori") {
				String selectedEntityID = TablePanel.getInstance().getSelectedEntityID();
				if(selectedEntityID != "NO_SELECTION") {
					ProfessorEditDialog.entityID = selectedEntityID;
					ProfessorEditDialog professorEditDialog = ProfessorEditDialog.getInstance();
					professorEditDialog.setProperValues();
					professorEditDialog.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Prvo izaberite profesora kojeg želite da izmenite", "Napomena", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				// ako se menja predmet
			}
			
		} else {
				// ako se menja predmet
		}
	
	}
}
	
}
