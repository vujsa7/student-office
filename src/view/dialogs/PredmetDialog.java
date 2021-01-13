package view.dialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.SubjectController;
import main.MainFrame;
import model.Predmet;
import view.dialogs.components.ButtonHolderPanel;
import view.dialogs.components.DialogConfirmButton;
import view.dialogs.components.ErrorPanel;
import view.dialogs.components.FieldName;
import view.dialogs.components.addingsubject.SubjectCustomComboBox;
import view.dialogs.components.addingsubject.SubjectCustomTextField;
import view.listeners.PredmetListener;

public class PredmetDialog extends JDialog{
/**
	 * 
	 */
	private static final long serialVersionUID = 4860889121944555950L;
	private static PredmetDialog instance = null;
	
	public static PredmetDialog getInstance() {	
		if(instance == null) {
			try {
				instance = new PredmetDialog(MainFrame.getInstance());
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return instance;
	}
	
	public static ArrayList<ErrorPanel> errorPanelList = new ArrayList<ErrorPanel>();
	public static ArrayList<SubjectCustomComboBox> customComboBoxes = new ArrayList<SubjectCustomComboBox>();
	public static ArrayList<JTextField> textFieldList = new ArrayList<JTextField>();
	private static DialogConfirmButton dialogConfirmButton;
	
	public static final String[] fieldText = {"Šifra", "Naziv", "Semestar", "Godina", "ESPB", "Profesor"};
	public String[] textFieldName = {"0","1","2"};
	
	public String[] regex = {
			"[a-z]{1,2}[0-9]{1,2}",
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
	
	private PredmetDialog(JFrame parent) {
		super(parent, "Dodavanje predmeta", true);		
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
		
		for(int i = 1; i <= 5; i++) {
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
				
			} else {
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
				
				textField.addFocusListener(new PredmetListener(regex[regexCounter++]));
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
		for(JPanel errorPanel : PredmetDialog.errorPanelList) {
			if(errorPanel.isVisible()) {
				dialogConfirmButton.validated = false;
				break;
			}
		}
	}
	
	public void setDefaultValues() {
		for(JPanel errorPanel : errorPanelList) {
			errorPanel.setVisible(false);
		}
		for(JTextField textField : textFieldList) {
			textField.setText("");
		}
		for(SubjectCustomComboBox customComboBox : customComboBoxes) {
			customComboBox.setDefaultValue();
		}
		
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
				for(SubjectCustomComboBox customComboBox : PredmetDialog.customComboBoxes) {
					comboAnswers.add(customComboBox.getField());
				}
				
				int j = 0;
				for(JTextField textField : textFieldList) {
					if(!Pattern.matches(regex[j++], textField.getText())){
						String textFieldName = textField.getName();
						PredmetDialog.showErrorPanel(Integer.parseInt(textFieldName));
						PredmetDialog.checkIfCanBeValidated();
					} else {
						String textFieldName = textField.getName();
						if(textFieldName.equals("0")) {
							if(SubjectController.getInstance().proveriPostojanjeSifre(textField.getText())) {
								PredmetDialog.showSifraErrorPanel();
								PredmetDialog.checkIfCanBeValidated();
							} else {
								PredmetDialog.hideSifraErrorPanel();
								PredmetDialog.checkIfCanBeValidated();
							}
						} else {
							PredmetDialog.hideErrorPanel(Integer.parseInt(textFieldName));
							PredmetDialog.checkIfCanBeValidated();
						}
					}
					
				}
				
				if(dialogConfirmButton.validated) {
					 if(comboAnswers.get(0) == "LETNJI") {
						
						SubjectController.getInstance().dodajPredmet(textFieldList.get(0).getText(), textFieldList.get(1).getText(),
					 			Integer.parseInt(comboAnswers.get(1)), Predmet.TipSemestra.LETNJI, Integer.parseInt(textFieldList.get(2).getText()));
						dispose();
					 } else {
						 
						 SubjectController.getInstance().dodajPredmet(textFieldList.get(0).getText(), textFieldList.get(1).getText(),
								 Integer.parseInt(comboAnswers.get(1)), Predmet.TipSemestra.ZIMSKI, Integer.parseInt(textFieldList.get(2).getText()));
						dispose();
					 }
				}
			}		
		}
	}
	
}

