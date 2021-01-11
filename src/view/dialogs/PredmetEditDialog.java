package view.dialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.AddProfesorToSubjectController;
import controller.SubjectController;
import main.MainFrame;
import model.AbstractProfessorTable;
import model.Predmet;
import model.Profesor;
import view.dialogs.components.ButtonHolderPanel;
import view.dialogs.components.DialogConfirmButton;
import view.dialogs.components.ErrorPanel;
import view.dialogs.components.FieldName;
import view.dialogs.components.addingsubject.ButtonsPlusMinus;
import view.dialogs.components.addingsubject.MinusButton;
import view.dialogs.components.addingsubject.SubjectCustomComboBox;
import view.dialogs.components.addingsubject.SubjectCustomTextField;
import view.listeners.PredmetEditListener;

public class PredmetEditDialog extends JDialog{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 4696773160457484593L;
	private static PredmetEditDialog instance = null;
	
	public static PredmetEditDialog getInstance() {	
		if(instance == null) {
			try {
				instance = new PredmetEditDialog(MainFrame.getInstance());
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return instance;
	}
	
	public static String staraSifra;
	public static ArrayList<ErrorPanel> errorPanelList = new ArrayList<ErrorPanel>();
	public static ArrayList<SubjectCustomComboBox> customComboBoxes = new ArrayList<SubjectCustomComboBox>();
	public static ArrayList<JTextField> textFieldList = new ArrayList<JTextField>();
	private static DialogConfirmButton dialogConfirmButton;
	public static JTextField textFieldForProfesorNameAndSurname;
	public static ButtonsPlusMinus plusBtn;
	
	public static final String[] fieldText = {"Šifra", "Naziv", "Semestar", "Godina", "ESPB", "Profesor"};
	public String[] textFieldName = {"0","1","4"};
	
	public String[] regex = {
			"[A-Z][0-9]{1,20}",
			".+",
			"[0-9]{1,2}"
	};
	public String[] errorText = {
			"Pogrešan format šifre predmeta",
			"Pogrešan format naziva",
			"ESPB bodovi sadrže samo brojeve"
	};
	@SuppressWarnings("serial")
	public ArrayList<String> semestar = new ArrayList<String>() {{
		   add("LETNJI");
		   add("ZIMSKI");
		}};
		
	@SuppressWarnings("serial")
	public ArrayList<String> godina = new ArrayList<String>() {{
		add("1");
		add("2");
		add("3");
		add("4");
	}};
	@SuppressWarnings("serial")
	public ArrayList<ArrayList<String>> predmetLista = new ArrayList<ArrayList<String>>() {{
		   add(semestar);
		   add(godina);
	}};
	
	private PredmetEditDialog(JFrame parent) {
		super(parent, "Izmena predmeta", true);		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(558, 520));
		setResizable(false);
		pack();
		setLocationRelativeTo(parent);
		
		dialogConfirmButton = new DialogConfirmButton();
		dialogConfirmButton.addMouseListener(new MyMouseListener());
		
		JPanel basePanel = new JPanel();
		BoxLayout box = new BoxLayout(basePanel, BoxLayout.Y_AXIS);
		basePanel.setLayout(box);
		basePanel.setBackground(new Color(249,249,249));
		basePanel.add(Box.createVerticalStrut(18));
		basePanel.setPreferredSize(new Dimension(558,500));
		int regexCounter = 0;
		int textFieldCounter = 0;
		int errorTextCounter = 0;
		
		for(int i = 1; i <= 6; i++) {
			JPanel holderPanel = new JPanel();
			holderPanel.setPreferredSize(new Dimension(458, 49));
			holderPanel.setOpaque(false);
			
			
			BoxLayout boxHolder = new BoxLayout(holderPanel, BoxLayout.X_AXIS);
			holderPanel.setLayout(boxHolder);
						
			FieldName fieldName = new FieldName(fieldText[i-1]);
			
			holderPanel.add(fieldName);
			holderPanel.add(Box.createHorizontalStrut(39));
			
			
			if(i == 3 || i == 4) {
				SubjectCustomComboBox customComboBox = new SubjectCustomComboBox(predmetLista.get(i-3));
				customComboBoxes.add(customComboBox);
				holderPanel.add(customComboBox);
			
			} else if (i == 6) {
				
				JPanel profesorPanel = new JPanel();
				
				profesorPanel.setPreferredSize(new Dimension(265, 36));
				profesorPanel.setMaximumSize(new Dimension(265, 36));
				profesorPanel.setMinimumSize(new Dimension(265, 36));
				profesorPanel.setOpaque(false);
				BoxLayout boxProfesor = new BoxLayout(profesorPanel, BoxLayout.X_AXIS);
				profesorPanel.setLayout(boxProfesor);
				
				textFieldForProfesorNameAndSurname = new JTextField();
				textFieldForProfesorNameAndSurname.setEditable(false);
				SubjectCustomTextField customTextField = new SubjectCustomTextField(textFieldForProfesorNameAndSurname, "3", true);
				plusBtn = new ButtonsPlusMinus("assets"+ File.separator +"icons"+ File.separator +"plus.png");
				MinusButton minusBtn = MinusButton.getInstance();
				
				plusBtn.addMouseListener(new MouseAddListener());
				
				plusBtn.setMaximumSize(new Dimension(36,36));
				
				plusBtn.setPreferredSize(new Dimension(36,36));
				
				
				profesorPanel.add(customTextField);
				profesorPanel.add(Box.createHorizontalStrut(10));
				profesorPanel.add(plusBtn);
				profesorPanel.add(Box.createHorizontalStrut(10));
				profesorPanel.add(minusBtn);
				holderPanel.add(profesorPanel);
				
			}else {
				JPanel textAndErrorPanel = new JPanel();
				textAndErrorPanel.setPreferredSize(new Dimension(265, 49));
				textAndErrorPanel.setMaximumSize(new Dimension(265, 49));
				textAndErrorPanel.setMinimumSize(new Dimension(265, 49));
				textAndErrorPanel.setOpaque(false);
				BoxLayout boxTextAndError = new BoxLayout(textAndErrorPanel, BoxLayout.Y_AXIS);
				textAndErrorPanel.setLayout(boxTextAndError);
				
				JPanel textPanel = new JPanel();
				textPanel.setPreferredSize(new Dimension(265, 36));
				textPanel.setMinimumSize(new Dimension(265, 36));
				textPanel.setMaximumSize(new Dimension(265, 36));
				textPanel.setLayout(new GridLayout(0,1));
				textPanel.setOpaque(false);
				JTextField textField = new JTextField();
				textField.getDocument().addDocumentListener(listener);
				textFieldList.add(textField);
				
				textField.addFocusListener(new PredmetEditListener(regex[regexCounter++]));
				SubjectCustomTextField customTextField = new SubjectCustomTextField(textField, textFieldName[textFieldCounter++], false);
				textPanel.add(customTextField);
					
				ErrorPanel errorPanel = new ErrorPanel(errorText[errorTextCounter++]);		
				errorPanelList.add(errorPanel);
				
				textAndErrorPanel.add(textPanel);
				textAndErrorPanel.add(errorPanel);
				holderPanel.add(textAndErrorPanel);
			}
			
			holderPanel.add(Box.createHorizontalStrut(54));
			
			basePanel.add(holderPanel);
			if(i%4 == 0) {
			    basePanel.add(Box.createVerticalStrut(45));
			} else {
				basePanel.add(Box.createVerticalStrut(17));
			}
			
		}
		
		basePanel.add(Box.createVerticalStrut(13));
		ButtonHolderPanel buttonHolderPanel = new ButtonHolderPanel(dialogConfirmButton, this);
		
		
		basePanel.add(buttonHolderPanel);
		basePanel.add(Box.createVerticalStrut(16));
		
		
		add(basePanel);
	}
	
	DocumentListener listener = new DocumentListener() {
	    @Override
	    public void removeUpdate(DocumentEvent e) {
	    	changedUpdate(e); 
	    }
	    @Override
	    public void insertUpdate(DocumentEvent e) { 
	    	changedUpdate(e); 
	    }
	    @Override
	    public void changedUpdate(DocumentEvent e) {
	        boolean enableButton = true;
	        for (JTextField textField : textFieldList) {
	            if (textField.getText().isEmpty()) {
	            	enableButton = false;
	            }
	        }
	        dialogConfirmButton.setEnabled(enableButton);
	    }
	};
	
	public static void showErrorPanel(int index) {
		int i = 0;
		for(JPanel errorPanel : errorPanelList) {
			if(i == index) {
				errorPanel.setVisible(true);
			}
			i++;
		}
	}
	
	public static void hideErrorPanel(int index) {
		int i = 0;
		for(JPanel errorPanel : errorPanelList) {
			if(i == index) {
				errorPanel.setVisible(false);
			}
			i++;
		}
	}
	
	public static void checkIfCanBeValidated() {
		dialogConfirmButton.validated = true;
		for(JPanel errorPanel : PredmetEditDialog.errorPanelList) {
			if(errorPanel.isVisible()) {
				dialogConfirmButton.validated = false;
				break;
			}
		}
	}
	
	public void setProperValues() {
		for(JPanel errorPanel : errorPanelList) {
			errorPanel.setVisible(false);
		}
		int textFieldCounter = 0;
		for(JTextField textField : textFieldList) {
			if(textFieldCounter > 1) {
				textField.setText(SubjectController.getInstance().getSelectedPredmetValue(4));
			} else {
				textField.setText(SubjectController.getInstance().getSelectedPredmetValue(textFieldCounter++));
			}
		}
		
		int i = 2;
		for(SubjectCustomComboBox customComboBox : customComboBoxes) {
			String value = SubjectController.getInstance().getSelectedPredmetValue(i++);
			customComboBox.setValue(value);
		}
	
		
		if(SubjectController.getInstance().getSelectedPredmetValue(5) != "")
			textFieldForProfesorNameAndSurname.setText(SubjectController.getInstance().getSelectedPredmetValue(5));
		else
			textFieldForProfesorNameAndSurname.setText("");
		
		
	}
	
	public static void showSifraErrorPanel() {
		ErrorPanel errorPanel = errorPanelList.get(0); 
		errorPanel.setLabelText("Predmet sa tom šifrom već postoji!");
		errorPanel.setVisible(true);
	}
	
	public static void hideSifraErrorPanel() {
		ErrorPanel errorPanel = errorPanelList.get(0);
		errorPanel.setLabelText("Pogrešan format šifre predmeta");
		errorPanel.setVisible(false);
	}
	
	private class MyMouseListener extends MouseAdapter{
		public void mouseEntered(MouseEvent mouseEvent) {
			JButton thisButton = (JButton) mouseEvent.getComponent();
			if(thisButton.isEnabled()) {
				thisButton.setIcon(dialogConfirmButton.hoveredConfirmIcon);
				new Thread(
						new Runnable() {
							public void run() {
								for (float i = 0.75f; i <= 1f; i += 0.02f){
									dialogConfirmButton.setAlpha(i);
									try {
										Thread.sleep(10);
									} catch (Exception e) {
										
									}
								}
							}
						}).start();
			}

		}
		
		public void mouseExited(MouseEvent mouseEvent) {
			JButton thisButton = (JButton) mouseEvent.getComponent();
			if(thisButton.isEnabled()) {
				new Thread(
						new Runnable() {
							public void run() {
								for (float i = 1f; i >= 0.8f; i -= 0.07f){
									dialogConfirmButton.setAlpha(i);
									try {
										Thread.sleep(10);
									} catch (Exception e) {
										
									}
								}
								thisButton.setIcon(dialogConfirmButton.confirmIcon);
								dialogConfirmButton.setAlpha(1f);
							}
						}).start();
			}
		}
		
		public void mousePressed(MouseEvent mouseEvent) {
			JButton thisButton = (JButton) mouseEvent.getComponent();
			if(thisButton.isEnabled()) {
				new Thread(
						new Runnable() {
							public void run() {
								for (float i = 1f; i >= 0.7f; i -= 0.1f) {
									dialogConfirmButton.setAlpha(i);
									try {
										Thread.sleep(1);
									} catch (Exception e) {
										
									}
								}
							}
						}).start();
				ArrayList<String> comboAnswers = new ArrayList<String>();
				for(SubjectCustomComboBox customComboBox : PredmetEditDialog.customComboBoxes) {
					comboAnswers.add(customComboBox.getField());
				}
				
				int j = 0;
				for(JTextField textField : textFieldList) {
					if(!Pattern.matches(regex[j++], textField.getText())){
						String textFieldName = textField.getName();
						PredmetEditDialog.showErrorPanel(Integer.parseInt(textFieldName));
						PredmetEditDialog.checkIfCanBeValidated();
					} else {
						String textFieldName = textField.getName();
						if(textFieldName.equals("0")) {
							if(SubjectController.getInstance().postojiLiSifra(textField.getText())) {
								PredmetEditDialog.showSifraErrorPanel();
								PredmetEditDialog.checkIfCanBeValidated();
							} else {
								PredmetEditDialog.hideSifraErrorPanel();
								PredmetEditDialog.checkIfCanBeValidated();
							}
						} else {
							PredmetEditDialog.hideErrorPanel(Integer.parseInt(textFieldName));
							PredmetEditDialog.checkIfCanBeValidated();
						}
					}
					
				}
				
				if(dialogConfirmButton.validated) {
					 if(comboAnswers.get(0) == "LETNJI") {
						Profesor trazeniProfesor = new Profesor();
						//System.out.println(PredmetEditDialog.textFieldForProfesorNameAndSurname.getText());
						for(Profesor profesor : AbstractProfessorTable.getInstance().getProfessors()) {
							if((profesor.getIme() + " " + profesor.getPrezime()).equals(PredmetEditDialog.textFieldForProfesorNameAndSurname.getText())) {
								trazeniProfesor = profesor;
								break;
							}
						}
						
						SubjectController.getInstance().izmeniPredmet(staraSifra, textFieldList.get(0).getText(), textFieldList.get(1).getText(),
					 			Integer.parseInt(comboAnswers.get(1)), Predmet.TipSemestra.LETNJI, Integer.parseInt(textFieldList.get(2).getText()), trazeniProfesor);
						dispose();
					 } else {
						 Profesor trazeniProfesor = new Profesor();
						 for(Profesor profesor : AbstractProfessorTable.getInstance().getProfessors()) {
							if((profesor.getIme() + " " + profesor.getPrezime()).equals(PredmetEditDialog.textFieldForProfesorNameAndSurname.getText())) {
								trazeniProfesor = profesor;
								break;
							}
			   			 }
						 
						 SubjectController.getInstance().izmeniPredmet(staraSifra, textFieldList.get(0).getText(), textFieldList.get(1).getText(),
								 Integer.parseInt(comboAnswers.get(1)), Predmet.TipSemestra.ZIMSKI, Integer.parseInt(textFieldList.get(2).getText()), trazeniProfesor);
						dispose();
					 }
				}
			}		
		}
	}
	
	private class MouseAddListener extends MouseAdapter {
		
		public void mousePressed(MouseEvent mouseEvent) {
			
			if(plusBtn.isEnabled()) {
				AddProfesorToSubjectController.getInstance().dobaviSveProfesore();
				AddProfesorToSubjectEditDialog.getInstance().setVisible(true);
				plusBtn.setEnabled(false);
			} else
				JOptionPane.showMessageDialog(null, "Morate prvo da obrišete profesora", "Napomena", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static JTextField getTextFieldForProfesorNameAndSurname() {
		return textFieldForProfesorNameAndSurname;
	}

	public static void setTextFieldForProfesorNameAndSurname(JTextField textFieldForProfesorNameAndSurname) {
		PredmetEditDialog.textFieldForProfesorNameAndSurname = textFieldForProfesorNameAndSurname;
	}
	
	public void canPlusBeVisible() {
		if(PredmetEditDialog.textFieldForProfesorNameAndSurname.getText().isEmpty())
			plusBtn.setEnabled(true);
		else
			plusBtn.setEnabled(false);
	}
	
	public void canMinusBeVisible() {
		if(PredmetEditDialog.textFieldForProfesorNameAndSurname.getText().isEmpty())
			MinusButton.getInstance().setEnabled(false);
		else
			MinusButton.getInstance().setEnabled(true);
	}
}
