package view.toolbar;

import javax.swing.*;

import controller.PolozeniStudentiController;
import controller.ProfessorController;
import controller.ProfessorHasSubjectsController;
import controller.StudentController;
import controller.StudentUnsettledSubjectsController;
import controller.SubjectController;
import view.dialogs.PredmetDialog;
import view.dialogs.PredmetEditDialog;
import view.dialogs.ProfessorDialog;
import view.dialogs.ProfessorEditDialog;
import view.dialogs.StudentDialog;
import view.dialogs.StudentEditDialog;
import view.dialogs.components.studentedit.StudentoviPolozeniIspitiTablePanel;
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
							StudentUnsettledSubjectsController.getInstance().postaviNepolozenePredmeteStudentu();
							PolozeniStudentiController.getInstance().postaviPolozenePredmeteStudentu();
							studentEditDialog.setProperValues();
							studentEditDialog.setDefaultView();
							String prosek = SubjectController.getInstance().izracunajProsek();
							String espb = SubjectController.getInstance().izracunajESPB();
							StudentoviPolozeniIspitiTablePanel.getInstance().updateProsekAndESPB(prosek, espb);
							studentEditDialog.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "Prvo izaberite studenta kojeg želite da izmenite", "Napomena", JOptionPane.INFORMATION_MESSAGE);
						}
				} else if(TabBarButton.getActiveButton() == "Profesori") {
					String selectedEntityID = TablePanel.getInstance().getSelectedEntityID();
					if(selectedEntityID != "NO_SELECTION") {
						ProfessorEditDialog.entityID = selectedEntityID;
						ProfessorEditDialog professorEditDialog = ProfessorEditDialog.getInstance();
						ProfessorHasSubjectsController.getInstance().nabaviIPostaviPredmeteOdProfesora();
						professorEditDialog.setProperValues();
						professorEditDialog.setDefaultView();
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
						predmetEditDialog.canPlusBeVisible();
						predmetEditDialog.canMinusBeVisible();
						predmetEditDialog.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Prvo izaberite predmet koji želite da izmenite", "Napomena", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
			} else {
				// Ako se brise
				if(TabBarButton.getActiveButton() == "Studenti") {
					String selectedIndex = TablePanel.getInstance().getSelectedEntityID();
					String[] opcije = new String[2];
					opcije[0] = new String("Obriši");
					opcije[1] = new String("Poništi");
					if(selectedIndex != "NO_SELECTION") {
						int brisanje = JOptionPane.showOptionDialog(null, "Da li ste sigurni da želite da obrišete studenta?", "Brisanje studenta", 0, 
								JOptionPane.INFORMATION_MESSAGE, null, opcije, null);
						//jer vraca broj u zavisnosti od toga koja je opcija birana
						if(brisanje == 0) {
							StudentController.getInstance().obrisiStudenta(selectedIndex);
							TablePanel.getInstance().setSelectedEntityID(-1);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Označite studenta kojeg želite da obrišete", "Napomena", JOptionPane.INFORMATION_MESSAGE);
					}
				} else if(TabBarButton.getActiveButton() == "Profesori") {
					String selectedProf = TablePanel.getInstance().getSelectedEntityID();
					String[] opcije = new String[2];
					opcije[0] = new String("Obriši");
					opcije[1] = new String("Poništi");
					if(selectedProf != "NO_SELECTION") {
						int brisanje = JOptionPane.showOptionDialog(null, "Da li ste sigurni da želite da obrišete profesora?", "Brisanje profesora", 0, 
								JOptionPane.INFORMATION_MESSAGE, null, opcije, null);
						
						if(brisanje == 0) {
							if(!SubjectController.getInstance().predmetImaProfesora(selectedProf)) {
								ProfessorController.getInstance().obrisiProfesora(selectedProf);
								TablePanel.getInstance().setSelectedEntityID(-1);
							} else
								JOptionPane.showMessageDialog(null, "Nemoguće obrisati profesora jer postoji predmet na kom on predaje!", "Neuspešna radnja", JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Označite profesora kojeg želite da obrišete", "Napomena", JOptionPane.INFORMATION_MESSAGE);
					}
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
							if(!ProfessorController.getInstance().nekiProfesorImaPredmet(selectedEntityID)) {
								if(!StudentController.getInstance().nekiStudentImaPolozenIspit(selectedEntityID)) {
									if(!StudentController.getInstance().nekiStudentImaNepolozenIspit(selectedEntityID)) {
										SubjectController.getInstance().obrisiPredmet(selectedEntityID);
										TablePanel.getInstance().setSelectedEntityID(-1);
									} else {
										JOptionPane.showMessageDialog(null, "Nemoguće obrisati predmet jer postoji student koji nije položio taj predmet!", "Neuspešna radnja", JOptionPane.INFORMATION_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null, "Nemoguće obrisati predmet jer postoji student koji je položio taj predmet!", "Neuspešna radnja", JOptionPane.INFORMATION_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "Nemoguće obrisati predmet jer postoji profesor koji predaje na njemu!", "Neuspešna radnja", JOptionPane.INFORMATION_MESSAGE);
							}
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
