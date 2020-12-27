package view.dialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.dialogs.components.ButtonHolderPanel;
import view.dialogs.components.CustomTextField;
import view.dialogs.components.DateComboBox;
import view.dialogs.components.DialogConfirmButton;
import view.dialogs.components.ErrorPanel;
import view.dialogs.components.CustomComboBox;
import view.dialogs.components.FieldName;

public class ProfessorDialog extends JDialog{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5767616325660054524L;
	private static ArrayList<JDialog> dialogs = new ArrayList<JDialog>();
	public static final String[] fieldText = {"Ime", "Prezime", "Datum rođenja", "Adresa stanovanja", "Kontakt telefon", "E-mail adresa",
			"Adresa kancelarije", "Broj lične karte", "Titula", "Zvanje"};
	private ArrayList<ErrorPanel> errorPanelList = new ArrayList<ErrorPanel>();
	public String[] textFieldName = {"0","1","2","3","4","5","6"};
	public String[] regex = {
			"[A-Z][a-z]{1,20}",
			"[A-Z][a-z]{1,20}",
			"[A-Z][a-z]{1,20}(\\s[A-Z][a-z]{1,20}){0,2},([\\s][A-Z][a-z]{1,20}){1,3}\\s\\d{1,5}[A-Za-z]?",
			"^(\\+381)?(\\s|-)?[0]6(([0-6]|[8-9])\\d{7,8}){1}$",
			"^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
			"[A-Z][a-z]{1,20}(\\\\s[A-Z][a-z]{1,20}){0,2},([\\\\s][A-Z][a-z]{1,20}){1,3}\\\\s\\\\d{1,5}[A-Za-z]?",
			"\\d{9}"
	};
	public String[] errorText = {
			"Pogrešan format imena",
			"Pogrešan format prezimena",
			"Format adrese: grad, ulica broj",
			"Format telefona: (+381) (0)xx xxxxxxx(x)",
			"Pogrešan format e-mail adrese",
			"Format adrese: grad, ulica broj",
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
	private ArrayList<JTextField> textFieldList = new ArrayList<>();
	private DialogConfirmButton dialogConfirmButton;


	public ProfessorDialog(JFrame parent) {
		super(parent, "Dodavanje profesora", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(507, 750));
		setResizable(false);
		pack();
		setLocationRelativeTo(parent);
		dialogs.add(this);

		
		dialogConfirmButton = new DialogConfirmButton(this, "profesor");
		
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
				holderPanel.add(customComboBox);
			} else if (i == 3) {
				DateComboBox.emptyComboBox();
				DateComboBox yearsComboBox = new DateComboBox(years, new Dimension(80, 36), "years");
				holderPanel.add(yearsComboBox);
				holderPanel.add(Box.createHorizontalStrut(8));
				DateComboBox monthsComboBox = new DateComboBox(months, new Dimension(58, 36), "months");
				holderPanel.add(monthsComboBox);
				holderPanel.add(Box.createHorizontalStrut(8));
				DateComboBox daysComboBox = new DateComboBox(days, new Dimension(58, 36), "days");
				holderPanel.add(daysComboBox);
			} else {
				JPanel textAndErrorPanel = new JPanel();
				textAndErrorPanel.setPreferredSize(new Dimension(214, 49));
				textAndErrorPanel.setMaximumSize(new Dimension(214, 49));
				textAndErrorPanel.setMinimumSize(new Dimension(214, 49));
				textAndErrorPanel.setOpaque(false);
				BoxLayout boxTextAndError = new BoxLayout(textAndErrorPanel, BoxLayout.Y_AXIS);
				textAndErrorPanel.setLayout(boxTextAndError);
				
				JPanel textPanel = new JPanel();
				textPanel.setPreferredSize(new Dimension(214, 36));
				textPanel.setMinimumSize(new Dimension(214, 36));
				textPanel.setMaximumSize(new Dimension(214, 36));
				textPanel.setLayout(new GridLayout(0,1));
				textPanel.setOpaque(false);
				JTextField textField = new JTextField();
				textField.getDocument().addDocumentListener(listener);
				textFieldList.add(textField);
				
				CustomTextField customTextField = new CustomTextField(dialogConfirmButton, textField, regex[regexCounter++], textFieldName[textFieldCounter++], "profesor");
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
		
		//basePanel.add(Box.createVerticalStrut(13));
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
		ArrayList<JDialog> dialogs = getDialogs();
		JDialog lastOpenedDialog = dialogs.get(dialogs.size()-1);
		ArrayList<ErrorPanel> errorPanelList = ((ProfessorDialog) lastOpenedDialog).getErrorPanelList();
		for(JPanel errorPanel : errorPanelList) {
			if(i == index) {
				errorPanel.setVisible(true);
			}
			i++;
		}
	}
	
	public static void hideErrorPanel(int index) {
		int i = 0;
		ArrayList<JDialog> dialogs = getDialogs();
		JDialog lastOpenedDialog = dialogs.get(dialogs.size()-1);
		ArrayList<ErrorPanel> errorPanelList = ((ProfessorDialog) lastOpenedDialog).getErrorPanelList();
		for(JPanel errorPanel : errorPanelList) {
			if(i == index) {
				errorPanel.setVisible(false);
			}
			i++;
		}
	}

	public static ArrayList<JDialog> getDialogs() {
		return dialogs;
	}

	public ArrayList<ErrorPanel> getErrorPanelList() {
		return errorPanelList;
	}

	public ArrayList<JTextField> getTextFieldList() {
		return textFieldList;
	}

	public static void showIDErrorPanel() {
		ArrayList<JDialog> dialogs = getDialogs();
		JDialog lastOpenedDialog = dialogs.get(dialogs.size()-1);
		ArrayList<ErrorPanel> errorPanelList = ((ProfessorDialog) lastOpenedDialog).getErrorPanelList();
		ErrorPanel errorPanel = errorPanelList.get(6);
		errorPanel.setLabelText("Profesor sa tim brojem već postoji");
		errorPanel.setVisible(true);
		
	}

	public static void hideIDErrorPanel() {
		ArrayList<JDialog> dialogs = getDialogs();
		JDialog lastOpenedDialog = dialogs.get(dialogs.size()-1);
		ArrayList<ErrorPanel> errorPanelList = ((ProfessorDialog) lastOpenedDialog).getErrorPanelList();
		ErrorPanel errorPanel = errorPanelList.get(6);
		errorPanel.setLabelText("Potrebno 9 brojeva");
		errorPanel.setVisible(false);
	}
	
}
