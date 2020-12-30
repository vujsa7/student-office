package view.dialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import controller.ProfessorController;
import main.MainFrame;
import view.dialogs.components.ButtonHolderPanel;
import view.dialogs.components.CustomTextField;
import view.dialogs.components.DateComboBox;
import view.dialogs.components.DialogConfirmButton;
import view.dialogs.components.ErrorPanel;
import view.dialogs.components.CustomComboBox;
import view.dialogs.components.FieldName;
import view.listeners.ProfessorListener;

public class ProfessorDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5767616325660054524L;
	
	private static ProfessorDialog instance = null;
	
	public static ProfessorDialog getInstance() {	
		if(instance == null) {
			try {
				instance = new ProfessorDialog(MainFrame.getInstance());
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return instance;
	}
	
	private static final String[] fieldText = {"Ime", "Prezime", "Datum rođenja", "Adresa stanovanja", "Kontakt telefon", "E-mail adresa",
			"Adresa kancelarije", "Broj lične karte", "Titula", "Zvanje"};
	
	public static ArrayList<ErrorPanel> errorPanelList = new ArrayList<ErrorPanel>();
	public static ArrayList<CustomComboBox> customComboBoxes = new ArrayList<CustomComboBox>();
	public static ArrayList<DateComboBox> dateComboBoxes = new ArrayList<DateComboBox>();
	public static ArrayList<JTextField> textFieldList = new ArrayList<JTextField>();
	private static DialogConfirmButton dialogConfirmButton;
	
	public String[] textFieldName = {"0","1","2","3","4","5","6"};
	public String[] regex = {
			"[A-Za-zćčšđž]{1,20}",
			"[A-Za-zćčšđž]{1,20}",
			".+",
			"^(\\+381)?(\\s|-)?0?6(([0-6]|[8-9])\\d{7,8}){1}$",
			"^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
			".+",
			"\\d{9}"
	};
	public String[] errorText = {
			"Pogrešan format imena",
			"Pogrešan format prezimena",
			"Unesite grad, ulicu i broj",
			"Format telefona: (+381) (0)6x xxxxxxx(x)",
			"Pogrešan format e-mail adrese",
			"Unesite grad, ulicu i broj",
			"Potrebno 9 brojeva"
	};
	@SuppressWarnings("serial")
	public ArrayList<String> titule = new ArrayList<String>() {{
		   add("Prof. dr.");
		   add("Doc. dr.");
		   add("Dr.");
		   add("Dipl. prof.");
		}};
		
	@SuppressWarnings("serial")
	public ArrayList<String> zvanja = new ArrayList<String>() {{
		add("Asistent-pripravnik");
		add("Asistent");
		add("Docent");
		add("Vanredni profesor");
		add("Redovni profesor");
		add("Istraživač-pripravnik");
		add("Istraživač–saradnik");
		add("Naučni–saradnik");
		add("Viši naučni–saradnik");
		add("Naučni savetnik");
	}};
	@SuppressWarnings("serial")
	public ArrayList<ArrayList<String>> profesorLista = new ArrayList<ArrayList<String>>() {{
		   add(titule);
		   add(zvanja);
	}};
	
	@SuppressWarnings("serial")
	public ArrayList<Integer> days = new ArrayList<Integer>() {{
		for(int i = 1; i <= 31; i++)
			add(i);
	}};
	@SuppressWarnings("serial")
	public ArrayList<Integer> months = new ArrayList<Integer>() {{
		for(int i = 1; i <= 12; i++)
			add(i);
	}};
	@SuppressWarnings("serial")
	public ArrayList<Integer> years = new ArrayList<Integer>() {{
		for(int i = 1955; i <= 1995; i++)
			add(i);
	}};
	
	


	private ProfessorDialog(JFrame parent) {
		super(parent, "Dodavanje profesora", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(507, 750));
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
		basePanel.setPreferredSize(new Dimension(507,692));
		int regexCounter = 0;
		int textFieldCounter = 0;
		int errorTextCounter = 0;
		for(int i = 1; i <= 10; i++) {
			JPanel holderPanel = new JPanel();
			holderPanel.setPreferredSize(new Dimension(507, 49));
			holderPanel.setOpaque(false);
			
			BoxLayout boxHolder = new BoxLayout(holderPanel, BoxLayout.X_AXIS);
			holderPanel.setLayout(boxHolder);
						
			FieldName fieldName = new FieldName(fieldText[i-1]);
			
			holderPanel.add(fieldName);
			holderPanel.add(Box.createHorizontalStrut(39));
			
			
			
			if(i == 9 || i == 10) {
				CustomComboBox customComboBox = new CustomComboBox(profesorLista.get(i-9));
				customComboBoxes.add(customComboBox);
				holderPanel.add(customComboBox);
			} else if (i == 3) {
				DateComboBox yearsComboBox = new DateComboBox(years, new Dimension(80, 36), "years");
				dateComboBoxes.add(yearsComboBox);
				holderPanel.add(yearsComboBox);
				holderPanel.add(Box.createHorizontalStrut(8));
				DateComboBox monthsComboBox = new DateComboBox(months, new Dimension(58, 36), "months");
				dateComboBoxes.add(monthsComboBox);
				holderPanel.add(monthsComboBox);
				holderPanel.add(Box.createHorizontalStrut(8));
				DateComboBox daysComboBox = new DateComboBox(days, new Dimension(58, 36), "days");
				dateComboBoxes.add(daysComboBox);
				holderPanel.add(daysComboBox);
				daysComboBox.setDateComboBoxes(dateComboBoxes);
				monthsComboBox.setDateComboBoxes(dateComboBoxes);
				yearsComboBox.setDateComboBoxes(dateComboBoxes);
			} else {
				JPanel textAndErrorPanel = new JPanel();
				textAndErrorPanel.setPreferredSize(new Dimension(214, 49));
				textAndErrorPanel.setMaximumSize(new Dimension(214, 49));
				textAndErrorPanel.setOpaque(false);
				BoxLayout boxTextAndError = new BoxLayout(textAndErrorPanel, BoxLayout.Y_AXIS);
				textAndErrorPanel.setLayout(boxTextAndError);
				
				JPanel textPanel = new JPanel();
				textPanel.setPreferredSize(new Dimension(214, 36));
				textPanel.setMaximumSize(new Dimension(214, 36));
				textPanel.setLayout(new GridLayout(0,1));
				textPanel.setOpaque(false);
				JTextField textField = new JTextField();
				textField.getDocument().addDocumentListener(listener);
				textFieldList.add(textField);
				
				textField.addFocusListener(new ProfessorListener(regex[regexCounter++]));
				CustomTextField customTextField = new CustomTextField(textField, textFieldName[textFieldCounter++]);
				textPanel.add(customTextField);
				
				ErrorPanel errorPanel = new ErrorPanel(errorText[errorTextCounter++]);		
				errorPanelList.add(errorPanel);
				
				textAndErrorPanel.add(textPanel);
				textAndErrorPanel.add(errorPanel);
				holderPanel.add(textAndErrorPanel);
			}
			
			holderPanel.add(Box.createHorizontalStrut(54));
			
			basePanel.add(holderPanel);
			if(i%3 == 0 || i%8 == 0) {
				if(i%9==0) {
					basePanel.add(Box.createVerticalStrut(18));
				} else {
					basePanel.add(Box.createVerticalStrut(45));
				}
			} else {
				basePanel.add(Box.createVerticalStrut(5));
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
	
	public static void showIDErrorPanel() {
		ErrorPanel errorPanel = errorPanelList.get(6);
		errorPanel.setLabelText("Profesor sa tim brojem već postoji");
		errorPanel.setVisible(true);
		
	}

	public static void hideIDErrorPanel() {
		ErrorPanel errorPanel = errorPanelList.get(6);
		errorPanel.setLabelText("Potrebno 9 brojeva");
		errorPanel.setVisible(false);
	}

	public static void checkIfCanBeValidated() {
		dialogConfirmButton.validated = true;
		for(JPanel errorPanel : ProfessorDialog.errorPanelList) {
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
		for(DateComboBox dateComboBox : dateComboBoxes) {
			dateComboBox.setDefaultDate();
		}
		for(CustomComboBox customComboBox : customComboBoxes) {
			customComboBox.setDefaultValue();
		}
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
				ArrayList<String> customComboAnswers = new ArrayList<String>();
				for(CustomComboBox customComboBox : ProfessorDialog.customComboBoxes) {
					customComboAnswers.add(customComboBox.getField());
				}
				String date = DateComboBox.dateString;
				LocalDate localDate;
				if(date.contentEquals("yyyy-MM-dd")) {
					localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				} else if(date.contentEquals("yyyy-M-dd")){
					localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-M-dd"));
				} else if(date.contentEquals("yyyy-MM-d")){
					localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-d"));
				} else {
					localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-M-d"));
				}
				int i = 0;
				for(JTextField textField : textFieldList) {
					if(!Pattern.matches(regex[i++], textField.getText())){
						String textFieldName = textField.getName();
						ProfessorDialog.showErrorPanel(Integer.parseInt(textFieldName));
						ProfessorDialog.checkIfCanBeValidated();
					} else {
						String textFieldName = textField.getName();
						// Ako je broj licne karte
						if(textFieldName.equals("6")) {
							if(ProfessorController.getInstance().checkIDExists(textField.getText())) {
								System.out.println("woohoo");
								ProfessorDialog.showIDErrorPanel();
								ProfessorDialog.checkIfCanBeValidated();
							} else {
								ProfessorDialog.hideIDErrorPanel();
								ProfessorDialog.checkIfCanBeValidated();
							}
						} else {
							ProfessorDialog.hideErrorPanel(Integer.parseInt(textFieldName));
							ProfessorDialog.checkIfCanBeValidated();
						}
					}
				}
				if(dialogConfirmButton.validated) {
					ProfessorController.getInstance().dodajProfesora(textFieldList.get(0).getText(), textFieldList.get(1).getText(),
							localDate,textFieldList.get(2).getText(), textFieldList.get(3).getText(), textFieldList.get(4).getText(),
							textFieldList.get(5).getText(), textFieldList.get(6).getText(),customComboAnswers.get(0), customComboAnswers.get(1));
						dispose();
				}
			}		
		}
	}

	
}
