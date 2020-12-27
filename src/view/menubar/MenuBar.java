package view.menubar;
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import main.MainFrame;
import view.dialogs.ProfessorDialog;
import view.dialogs.StudentDialog;
import view.tab.TabBarButton;
 
 
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
							try {
								ProfessorDialog professorDialog = new ProfessorDialog(MainFrame.getInstance());
								professorDialog.setVisible(true);
							} catch (FontFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							
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
               
                JMenuItem delete = new JMenuItem("Delete");
                delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
                ImageIcon deleteIcon = getResizedIcon(new ImageIcon("assets" + File.separator + "icons" + File.separator + "delete.png", "Delete"));
                delete.setIcon(deleteIcon);
                delete.setMnemonic(KeyEvent.VK_D);
               
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
						
						String text = "<html> Aplikacija studentska služba služi referentu studentske službe i "
								+ "omogućava skladištenje neophodnih podataka o fakultetu.<br/>Podaci o studentu podrazumevaju "
								+ "osnovne podatke(ime, prezime, datum rođenja, adresa stanovanja, kontakt, e-mail adresa), "
								+ "status, godinu upisa i trenutnu godinu studija, broj indeksa i prosek, spisak položenih i nepoloženih predmeta.<br/>"
								+ "Takođe, moguće je postojeću tabelu modifikovati shodno potrebama.<br/>"
								+ "Dodavanje novog studenta postiže se klikom miša na File, a zatim na New.<br/>"
								+ "Isti postupak je moguće odraditi putem tastature, otvaranje menija File se postiže pritiskom Alt+F, "
								+ "a komandom Alt+N/Shift+N se otvara prozor za dodavanje novog studenta.<br/>"
								+ "Takođe, postojeći student se može izmeniti ili obrisati, putem miša Edit->Edit za izmenu(Edit->Delete za brisanje), "
								+ "ili putem tastature Alt+T->Alt+E/Ctrl+E za izmenu(Alt+T->Alt+D/Ctrl+D za brisanje).<br/>"
								+ "Aplikaciju je moguće zatvoriti pritiskom na X u gornjem desnom uglu, ili putem miša File->Close, odnosno Alt+F->Alt+C/Ctrl+W putem tastature.<br/>"
								+ "Ukoliko vam zatreba pomoć, odlaskom na Help->Help, tj. Alt+P->Alt+H/Ctrl+H, otvoriće se prozor u kom pišu sve neophodne informacije o rukovanju aplikacijom.<br/>"
								+ "Za više informacija o autorima ili realizaciji same aplikacije, možete pritisnuti mišem na Help->About, ili Alt+P->Alt+A/Ctrl+A putem tastature.<br/>"
								+ "Pored svega opisanog, dodavanje, izmena i brisanje studenta je moguće postići pritiskom na dugmad toolbar-a, <br/>"
								+ "odnosno na ikonice koje se nalaze ispod menu bara.<br/>"
								+ "Informacije o nazivu aplikacije, kao i o trenutnom vremenu, mogu se naći na dnu aplikacije.<br/>"
								+ "Takođe, u aplikaciji se nalaze informacije i o profesorima, predmetima i ocenama.<br/>"
								+ "Za profesora su vezane osnovne informacije kao što su ime, prezime, datum rođenja, kontakt, broj telefona i e-mail adresa,"
								+ "kao i broj lične karte, adresa kancelarije, titula, zvanje i spisak predmeta koje predaje.<br/>"
								+ "Što se tiče predmeta, postoje informacije o šifri, nazivu, predmetnom profesoru, kom semestru pripada predmet,"
								+ "godini studija u kojoj se izvodi predmet, broj ESP bodova, spisak studenata koji su položili i spisak studenata koji nisu položili predmet,<br/>"
								+ "a što se tiče ocene na ispitu, tu su informacije o studentima koji su položili ispit, predmetu, visini ocene(6-10) i datumu polaganja ispita.<br/>"
								+ "Takođe, i profesore je moguce dodavati, brisati ili modofikovati,prateći malopre opisan postupak.</html>";
						
						
						JOptionPane.showMessageDialog(null, text, "Help", JOptionPane.INFORMATION_MESSAGE);
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
								+ "Godina proizvodnje: 2020.<br/>"
								+ "Autori: <br/>"
								+ "1. Vladimir Vrbica<br/>"
								+ "Student treće godine elektrotehnike, rođen u Subotici.<br/>"
								+ "Živi studentski život punim plućima.<br/>"
								+ "2. Aleksa Vujisić<br/>"
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
