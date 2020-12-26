package view.dialogs;

import java.awt.Color;
import java.awt.Dimension;
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
import view.dialogs.components.CustomComboBox;
import view.dialogs.components.FieldName;

public class ProfessorDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5767616325660054524L;
	public static final String[] FIELD_TEXT = {"Ime", "Prezime", "Datum rođenja", "Adresa stanovanja", "Kontakt telefon", "E-mail adresa",
			"Adresa kancelarije", "Broj lične karte", "Titula", "Zvanje"};
	
	@SuppressWarnings("serial")
	public ArrayList<String> titule = new ArrayList<String>() {{
		   add("Prof. dr. sc.");
		   add("Doc. dr. sc.");
		   add("Dr. sc");
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
		for(int i = 1970; i <= 2001; i++)
			add(i);
	}};
	
	public static ArrayList<JTextField> list = new ArrayList<>();
	private DialogConfirmButton dialogConfirmButton;


	public ProfessorDialog(JFrame parent) {
		super(parent, "Dodavanje profesora", true);
		setPreferredSize(new Dimension(507, 721));
		setResizable(false);
		pack();
		setLocationRelativeTo(parent);

		
		dialogConfirmButton = new DialogConfirmButton(this);
		
		JPanel basePanel = new JPanel();
		BoxLayout box = new BoxLayout(basePanel, BoxLayout.Y_AXIS);
		basePanel.setLayout(box);
		basePanel.setBackground(new Color(249,249,249));
		basePanel.add(Box.createVerticalStrut(18));
		
		for(int i = 1; i <= 10; i++) {
			JPanel holderPanel = new JPanel();
			holderPanel.setPreferredSize(new Dimension(507, 49));
			holderPanel.setOpaque(false);
			
			BoxLayout boxHolder = new BoxLayout(holderPanel, BoxLayout.X_AXIS);
			holderPanel.setLayout(boxHolder);
						
			FieldName fieldName = new FieldName(FIELD_TEXT[i-1]);
			
			holderPanel.add(fieldName);
			holderPanel.add(Box.createHorizontalStrut(39));
			if(i == 9 || i == 10) {
				CustomComboBox customComboBox = new CustomComboBox(profesorLista.get(i-9));
				holderPanel.add(customComboBox);
			} else if (i == 3) {
				DateComboBox yearsComboBox = new DateComboBox(years, new Dimension(80, 36), "years");
				holderPanel.add(yearsComboBox);
				holderPanel.add(Box.createHorizontalStrut(8));
				DateComboBox monthsComboBox = new DateComboBox(months, new Dimension(58, 36), "months");
				holderPanel.add(monthsComboBox);
				holderPanel.add(Box.createHorizontalStrut(8));
				DateComboBox daysComboBox = new DateComboBox(days, new Dimension(58, 36), "days");
				holderPanel.add(daysComboBox);
			} else {
				JTextField textField = new JTextField();
				textField.getDocument().addDocumentListener(listener);
				list.add(textField);
				CustomTextField customTextField = new CustomTextField(dialogConfirmButton, textField);
				holderPanel.add(customTextField);
			}
			
			holderPanel.add(Box.createHorizontalStrut(54));
			
			basePanel.add(holderPanel);
			if(i%3 == 0 || i%8 == 0) {
				if(i%9==0) {
					basePanel.add(Box.createVerticalStrut(5));
				} else {
					basePanel.add(Box.createVerticalStrut(45));
				}
			} else {
				basePanel.add(Box.createVerticalStrut(5));
			}
			
		}
		
		ButtonHolderPanel buttonHolderPanel = new ButtonHolderPanel(dialogConfirmButton, this);
		
		basePanel.add(buttonHolderPanel);
		basePanel.add(Box.createVerticalStrut(18));
		
		
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
	        for (JTextField textField : list) {
	            if (textField.getText().isEmpty()) {
	            	enableButton = false;
	            }
	        }
	        dialogConfirmButton.setEnabled(enableButton);
	    }
	};
	
	
	
	
	
	
	
	
	

}
