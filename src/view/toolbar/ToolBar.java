package view.toolbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;

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

public class ToolBar extends JToolBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7607547575772558529L;
	

	public ToolBar() {
		super(SwingConstants.HORIZONTAL);
		setFloatable(false);
		setBackground(new Color(90, 90, 90));
		setBorderPainted(false);
		BoxLayout box = new BoxLayout(this, BoxLayout.X_AXIS);
		setLayout(box);
		HoverButton newBtn = new HoverButton("assets"+ File.separator +"icons"+ File.separator +"new_white.png", "New");
		KeyStroke keyNew = KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK); 
		Action performNew = new AbstractAction("New") {  
		    /**
			 * 
			 */
			private static final long serialVersionUID = -1236190707680017271L;

			public void actionPerformed(ActionEvent e) {     
		         new Thread(
							new Runnable() {
								public void run() {
										try {
											newBtn.setAlpha(1f);
											Thread.sleep(1000);
											newBtn.setAlpha(0.7f);
										} catch (Exception e) {
											
										}
									
								}
							}).start();
		         if(TabBarButton.getActiveButton() == "Studenti") {
		        	StudentDialog.getInstance().setDefaultValues();
					StudentDialog.getInstance().setVisible(true);
		         } else if(TabBarButton.getActiveButton() == "Profesori") {
					ProfessorDialog.getInstance().setDefaultValues();
					ProfessorDialog.getInstance().setVisible(true);
		         } else {
		        	PredmetDialog.getInstance().setDefaultValues();
		        	PredmetDialog.getInstance().setVisible(true);
		         }
		    }
		
		}; 
		newBtn.getActionMap().put("performNew", performNew);
		newBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyNew, "performNew"); 
		add(newBtn);
		
		HoverButton editBtn = new HoverButton("assets"+ File.separator +"icons"+ File.separator +"edit_white.png", "Edit");
		KeyStroke keyEdit = KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK); 
		Action performEdit = new AbstractAction("Edit") {  
		    /**
			 * 
			 */
			private static final long serialVersionUID = -4039649347818021451L;

			public void actionPerformed(ActionEvent e) {     
		         new Thread(
							new Runnable() {
								public void run() {
										try {
											editBtn.setAlpha(1f);
											Thread.sleep(1000);
											editBtn.setAlpha(0.7f);
										} catch (Exception e) {
											
										}
									
								}
							}).start();
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
						predmetEditDialog.canPlusBeVisible();
						predmetEditDialog.canMinusBeVisible();
						predmetEditDialog.setProperValues();
						predmetEditDialog.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Prvo izaberite predmet koji želite da izmenite", "Napomena", JOptionPane.INFORMATION_MESSAGE);
					}
				}
		    }
		}; 
		editBtn.getActionMap().put("performEdit", performEdit);
		editBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyEdit, "performEdit"); 
		add(editBtn);
		
		HoverButton deleteBtn = new HoverButton("assets"+ File.separator +"icons"+ File.separator +"delete_white1.png", "Delete");
		KeyStroke keyDelete = KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.CTRL_MASK); 
		Action performDelete = new AbstractAction("Delete") {  
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1384798606031478310L;

			public void actionPerformed(ActionEvent e) {     
		         new Thread(
							new Runnable() {
								public void run() {
										try {
											deleteBtn.setAlpha(1f);
											Thread.sleep(1000);
											deleteBtn.setAlpha(0.7f);
										} catch (Exception e) {
											
										}
									
								}
							}).start();
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
						options[0] = new String("Poništi");
						options[1] = new String("Obriši");
						int reply = JOptionPane.showOptionDialog(null, "Da li ste sigurni da želite da obrišete predmet?", "Brisanje predmeta", 0,
								JOptionPane.INFORMATION_MESSAGE, null, options, null);
						if(reply == 1) {
							if(!ProfessorController.getInstance().nekiProfesorImaPredmet(selectedEntityID)) {
								if(!StudentController.getInstance().nekiStudentImaPolozenIspit(selectedEntityID)) {
									if(!StudentController.getInstance().nekiStudentImaNepolozenIspit(selectedEntityID)) {
										SubjectController.getInstance().obrisiPredmet(selectedEntityID);
										ProfessorController.getInstance().obrisiPredmetSaProfesora(selectedEntityID);
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
		}; 
		deleteBtn.getActionMap().put("performDelete", performDelete);
		deleteBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyDelete, "performDelete"); 
		add(deleteBtn);
		
		
		add(Box.createGlue());
		
		SearchIconPanel searchIconPanel = new SearchIconPanel();
		add(searchIconPanel);
		
		SearchBarTextField searchBarTextField = new SearchBarTextField();
		
		JTextField searchTextField = new JTextField();
		searchTextField.setBorder(new EmptyBorder(0,0,0,10));
		searchTextField.setMinimumSize(new Dimension(170,35));
		searchTextField.setOpaque(false);
		((AbstractDocument)searchTextField.getDocument()).setDocumentFilter(new LimitDocumentFilter());
		
		SearchButton searchButton = new SearchButton(searchTextField);
		searchTextField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    changed();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    changed();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    changed();
			  }

			  public void changed() {
			     if (searchTextField.getText().equals("")){	
					searchButton.setEnabled(false);
					searchButton.setForeground(Color.black);
					searchButton.resetIcon();
					if(TablePanel.currentlyOpenedTable.equals(TablePanel.PROFESSOR_PANEL)) {
						ProfessorController.getInstance().vratiDefaultProfesore();
						TablePanel.selectedProfessorRow = -1;
					} else if(TablePanel.currentlyOpenedTable.equals(TablePanel.SUBJECT_PANEL)) {
						SubjectController.getInstance().vratiDefaultPredmete();
						TablePanel.selectedSubjectRow = -1;
					} else {
						StudentController.getInstance().postaviDefaultStudente();
						TablePanel.selectedStudentRow = -1;
					}
					
			     }
			     else {
			       searchButton.setEnabled(true);
			       LimitDocumentFilter.spaces = searchTextField.getText().replaceAll("[^ ]", "").length();
			    }

			  }
			});
		
		
		searchBarTextField.add(searchTextField);
		add(searchBarTextField);
		
		add(Box.createHorizontalStrut(12));
		add(searchButton);
		
		add(Box.createHorizontalStrut(12));
	}
	
}
