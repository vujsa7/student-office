package view.toolbar;

import javax.swing.*;

import controller.ProfessorController;
import controller.ProfessorHasSubjectsController;
import controller.SubjectController;
import view.dialogs.PredmetDialog;
import view.dialogs.PredmetEditDialog;
import view.dialogs.ProfessorDialog;
import view.dialogs.ProfessorEditDialog;
import view.dialogs.StudentDialog;
import view.dialogs.StudentEditDialog;
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
		        	PredmetDialog predmetDialog = PredmetDialog.getInstance();
		        	predmetDialog.setDefaultValues();
		        	predmetDialog.setVisible(true);
		        }
				
			} else if(buttonType.equals("Edit")) {
				
				if(TabBarButton.getActiveButton() == "Studenti") {
					String selectedEntityID = TablePanel.getInstance().getSelectedEntityID();
						if(selectedEntityID != "NO_SELECTION") {
							StudentEditDialog.stariIndeks = selectedEntityID;
							StudentEditDialog studentEditDialog = StudentEditDialog.getInstance();
							studentEditDialog.setProperValues();
							studentEditDialog.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "Prvo izaberite studenta kojeg želite da izmenite", "Napomena", JOptionPane.INFORMATION_MESSAGE);
						}
				} else if(TabBarButton.getActiveButton() == "Profesori") {
					String selectedEntityID = TablePanel.getInstance().getSelectedEntityID();
					if(selectedEntityID != "NO_SELECTION") {
						ProfessorEditDialog.entityID = selectedEntityID;
						ProfessorEditDialog professorEditDialog = ProfessorEditDialog.getInstance();
						professorEditDialog.setProperValues();
						professorEditDialog.setDefaultView();
						ProfessorHasSubjectsController.getInstance().nabaviIPostaviPredmeteOdProfesora();
						professorEditDialog.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Prvo izaberite profesora kojeg želite da izmenite", "Napomena", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					String selectedEntityID = TablePanel.getInstance().getSelectedEntityID();
					if(selectedEntityID != "NO_SELECTION") {
						PredmetEditDialog.staraSifra = selectedEntityID;
						PredmetEditDialog predmetEditDialog = PredmetEditDialog.getInstance();
						predmetEditDialog.setProperValues();
						predmetEditDialog.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Prvo izaberite predmet koji želite da izmenite", "Napomena", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
			} else {
				// Ako se brise
				if(TabBarButton.getActiveButton() == "Studenti") {
					// ako se brise student
				} else if(TabBarButton.getActiveButton() == "Profesori") {
					
				} else {
					// ako se brise predmet
					String selectedEntityID = TablePanel.getInstance().getSelectedEntityID();
					if(selectedEntityID != "NO_SELECTION") {
						String[] options = new String[2];
						options[0] = new String("Obriši");
						options[1] = new String("Poništi");
						int reply = JOptionPane.showOptionDialog(null, "Da li ste sigurni da želite da obrišete predmet?", "Brisanje predmeta", 0,
								JOptionPane.INFORMATION_MESSAGE, null, options, null);
						if(reply == 0) {
							SubjectController.getInstance().obrisiPredmet(selectedEntityID);
							ProfessorController.getInstance().obrisiPredmetSaProfesora(selectedEntityID);
							TablePanel.getInstance().setSelectedEntityID(-1);
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "Prvo izaberite predmet koji želite da izbrišete", "Napomena", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			
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
