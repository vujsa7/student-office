package view.menubar;
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import controller.PolozeniStudentiController;
import controller.ProfessorController;
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
 
 
public class MenuBar extends JMenuBar{
       
        /**
         *
         */
        private static final long serialVersionUID = 5667853204203527406L;
 
        public MenuBar(JFrame parent) {
               
                JMenu file = new JMenu("File");
                file.setMnemonic(KeyEvent.VK_F);
 
                JMenuItem minew = new JMenuItem("New");
                ImageIcon newIcon = getResizedIcon(new ImageIcon("assets" + File.separator + "icons" + File.separator + "new.png", "New"));
                minew.setIcon(newIcon);
                minew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
                minew.setMnemonic(KeyEvent.VK_N);
                minew.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
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
					}     	
                });
               
                JMenuItem close = new JMenuItem("Close");
                close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
                ImageIcon closeIcon = getResizedIcon(new ImageIcon("assets" + File.separator + "icons" + File.separator + "close.png", "Close"));
                close.setIcon(closeIcon);
                close.setMnemonic(KeyEvent.VK_C);
                close.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
                });
                
               
                file.add(minew);
                file.addSeparator();
                file.add(close);
               
               
                JMenu edit = new JMenu("Edit");
                edit.setMnemonic(KeyEvent.VK_T);
               
                JMenuItem miedit = new JMenuItem("Edit");
                miedit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
                ImageIcon editIcon = getResizedIcon(new ImageIcon("assets" + File.separator + "icons" + File.separator + "edit.png", "Edit"));
                miedit.setIcon(editIcon);
                miedit.setMnemonic(KeyEvent.VK_E);
                miedit.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(TabBarButton.getActiveButton() == "Studenti") {
							String selectedIndex = TablePanel.getInstance().getSelectedEntityID();
							if(selectedIndex != "NO_SELECTION") {
								StudentEditDialog.stariIndeks = selectedIndex;
								StudentEditDialog studentEditDialog = StudentEditDialog.getInstance();
								PolozeniStudentiController.getInstance().postaviPolozenePredmeteStudentu();
								StudentUnsettledSubjectsController.getInstance().postaviNepolozenePredmeteStudentu();
								studentEditDialog.setProperValues();
								studentEditDialog.setDefaultView();
								String prosek = SubjectController.getInstance().izracunajProsek();
								String espb = SubjectController.getInstance().izracunajESPB();
								StudentoviPolozeniIspitiTablePanel.getInstance().updateProsekAndESPB(prosek, espb);
								studentEditDialog.setVisible(true);
							} else {
								JOptionPane.showMessageDialog(null, "Označite studenta kojeg želite da izmenite", "Napomena", JOptionPane.INFORMATION_MESSAGE);
							}
							
						} else if(TabBarButton.getActiveButton() == "Profesori") {
							String selectedEntityID = TablePanel.getInstance().getSelectedEntityID();
							if(selectedEntityID != "NO_SELECTION") {
								ProfessorEditDialog.entityID = selectedEntityID;
								ProfessorEditDialog professorEditDialog = ProfessorEditDialog.getInstance();
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
						
					}
                });
               
                JMenuItem delete = new JMenuItem("Delete");
                delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
                ImageIcon deleteIcon = getResizedIcon(new ImageIcon("assets" + File.separator + "icons" + File.separator + "delete.png", "Delete"));
                delete.setIcon(deleteIcon);
                delete.setMnemonic(KeyEvent.VK_D);
                delete.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
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
                	
                });
               
                edit.add(miedit);
                edit.addSeparator();
                edit.add(delete);
               
                JMenu help = new JMenu("Help");
                help.setMnemonic(KeyEvent.VK_P);
               
                JMenuItem mihelp = new JMenuItem("Help");
                mihelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
                ImageIcon helpIcon = getResizedIcon(new ImageIcon("assets" + File.separator + "icons" + File.separator + "help.png", "Help"));
                mihelp.setIcon(helpIcon);
                mihelp.setMnemonic(KeyEvent.VK_H);
                
               
                mihelp.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						String text = "Aplikacija studentska služba služi referentu studentske službe i "
								+ "omogućava skladištenje neophodnih podataka o fakultetu i osoblju na njemu.\nPodaci o studentu podrazumevaju "
								+ "osnovne podatke(ime, prezime, datum rođenja, adresa stanovanja, kontakt, e-mail adresa), \n"
								+ "status, godinu upisa i trenutnu godinu studija, broj indeksa i prosek, spisak položenih i nepoloženih predmeta.\n"
								+ "Takođe, moguće je postojeću tabelu modifikovati shodno potrebama.\n"
								+ "Dodavanje novog studenta postiže se klikom miša na File, a zatim na New.\n"
								+ "Isti postupak je moguće odraditi putem tastature, otvaranje menija File se postiže pritiskom Alt+F, "
								+ "a komandom Alt+N/Shift+N se otvara prozor za dodavanje novog studenta.\n"
								+ "Takođe, postojeći student se može izmeniti ili obrisati, putem miša Edit->Edit za izmenu(Edit->Delete za brisanje), \n"
								+ "ili putem tastature Alt+T->Alt+E/Ctrl+E za izmenu(Alt+T->Alt+D/Ctrl+D za brisanje).\n"
								+ "Aplikaciju je moguće zatvoriti pritiskom na 'X' u gornjem desnom uglu, ili putem miša File->Close, odnosno Alt+F->Alt+C/Ctrl+W putem tastature.\n"
								+ "Ukoliko vam zatreba pomoć, odlaskom na Help->Help, tj. Alt+P->Alt+H/Ctrl+H, otvoriće se prozor u kom pišu sve neophodne informacije o rukovanju aplikacijom.\n"
								+ "Za više informacija o autorima ili realizaciji same aplikacije, možete pritisnuti mišem na Help->About, ili Alt+P->Alt+A/Ctrl+A putem tastature.\n"
								+ "Pored svega opisanog, dodavanje, izmena i brisanje studenta je moguće postići pritiskom na dugmad toolbar-a, \n"
								+ "odnosno na ikonice koje se nalaze ispod menu bara.\n"
								+ "Informacije o nazivu aplikacije, kao i o trenutnom vremenu, mogu se naći na dnu aplikacije.\n"
								+ "Takođe, u aplikaciji se nalaze informacije i o profesorima, predmetima i ocenama.\n"
								+ "Za profesora su vezane osnovne informacije kao što su ime, prezime, datum rođenja, kontakt, broj telefona i e-mail adresa,\n"
								+ "kao i broj lične karte, adresa kancelarije, titula, zvanje i spisak predmeta koje predaje.\n"
								+ "Što se tiče predmeta, postoje informacije o šifri, nazivu, predmetnom profesoru, kom semestru pripada predmet,\n"
								+ "godini studija u kojoj se izvodi predmet, broj ESPB bodova, spisak studenata koji su položili i spisak studenata koji nisu položili predmet,\n"
								+ "a što se tiče ocene na ispitu, tu su informacije o studentima koji su položili ispit, predmetu, visini ocene(6-10) i datumu polaganja ispita.\n"
								+ "Takođe, i profesore je moguce dodavati, brisati ili modofikovati,prateći malopre opisan postupak."
								+ "Ukoliko želite da dodate profesora na predmet neophodno je da prilikom izmene predmeta stisnete na dugme '+' i pojaviće se prozor sa svim profesorima u sistemu.\n"
								+ "Takođe, ukoliko želite da obrišete profesora sa predmeta, to možete postići pritiskom na dugme '-' .\n"
								+ "Isti cilj se može dostići ukoliko u izmeni profesora stisnete na tab predmeti, pritiskom na dugme 'dodaj' možete profesoru dodati predmet, a pritiskom na dugme 'obriši' se briše predmet profesoru.\n"
								+ "U izmeni studenta, odlaskom na tab položeni, možete poništiti ocenu studentu, \n"
								+ "dok odlaskom na tab nepoloženi, možete dodati studentu nepoložen ispit, obrisati ga, ili nakon što student položi ispit upisati mu ocenu i prebaciti ga u položene ispite.\n"
								+ "U tool-baru se nalazi polje za pretragu radi lakše pretrage podataka. Za studenta se prvo unosi prezime, ime pa broj indeksa, \n"
								+ "za profesora prezime, ime pa broj lične karte, a za predmet ime predmeta.\n"
								+ "\nNapomena: Profesora nije moguće obrisati ukoliko postoji predmet na kom on predaje,\n "
								+ "a predmet nije moguće obrisati ukoliko taj predmet postoji u evidenciji studentovih položenih ili nepoloženih ispita, kao i ukoliko neki profesor predaje na njemu.";
						
						JTextArea textArea = new JTextArea(text);
						JScrollPane scrollPane = new JScrollPane(textArea);
						scrollPane.setPreferredSize(new Dimension(770, 350));
						
						JOptionPane.showMessageDialog(null, scrollPane, "Help", JOptionPane.INFORMATION_MESSAGE);
					}
                });
               
                JMenuItem about = new JMenuItem("About");
                about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
                ImageIcon aboutIcon = getResizedIcon(new ImageIcon("assets" + File.separator + "icons" + File.separator + "about.png", "About"));
                about.setIcon(aboutIcon);
                about.setMnemonic(KeyEvent.VK_A);
                about.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						String text = "<html> STUDENTSKA SLUŽBA v1.0<br/><br/>"
								+ "Aplikacija služi referentu studentske službe fakulteta tehničkih nauka<br/> "
								+ "za unos svih podataka o studentima i zaposlenima na fakultetu.<br/>"
								+ "Godina pravljenja aplikacije: 2020.<br/>"
								+ "Autori: <br/>"
								+ " Vladimir Vrbica<br/>"
								+ "Student treće godine elektrotehnike, rođen u Subotici.<br/>"
								+ "Živi studentski život punim plućima.<br/><br/>"
								+ " Aleksa Vujisić<br/>"
								+ "Takođe student treće godine elektrotehnike, rođen u Kragujevcu.<br/>"
								+ "Noćna radilica, pravi primer uzornog studenta.<br/>";
								
						JOptionPane.showMessageDialog(null, text, "About", JOptionPane.INFORMATION_MESSAGE);
					}
                });
               
               
                help.add(mihelp);
                help.addSeparator();
                help.add(about);
               
                add(file);
                add(edit);
                add(help);
               
                setBackground(new Color(179,179,179));
                setPreferredSize(new Dimension(200, 35));
        }
       
        public ImageIcon getResizedIcon(ImageIcon icon) {
                Image image = icon.getImage();
                Image resizedImage = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
                icon = new ImageIcon(resizedImage);
                return icon;
        }
        
       
}
