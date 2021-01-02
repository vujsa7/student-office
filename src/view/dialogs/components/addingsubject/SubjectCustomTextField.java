package view.dialogs.components.addingsubject;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SubjectCustomTextField extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4142476707825887808L;

	public SubjectCustomTextField(JTextField textField, String textFieldName, boolean zaProfu){ //da li je poslednji textfield ili ne
		if(!zaProfu) {
			setLayout(new GridLayout(0,1));
			ImageIcon icon = new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"subject_text_field.png");
			setIcon(icon);
			setMaximumSize(new Dimension(265, 36));
			textField.setName(textFieldName);
			textField.setOpaque(false);
			textField.setBorder(new EmptyBorder(0,10,0,10));
			textField.setMaximumSize(new Dimension(265,36));
			textField.setMinimumSize(new Dimension(265,36));
			add(textField);
		} else {
			setLayout(new GridLayout(0,1));
			ImageIcon icon = new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"subject_for_prof_text_field.png");
			setIcon(icon);
			setMaximumSize(new Dimension(173, 36));
			textField.setName(textFieldName);
			textField.setOpaque(false);
			textField.setBorder(new EmptyBorder(0,10,0,10));
			textField.setMaximumSize(new Dimension(173,36));
			textField.setMinimumSize(new Dimension(173,36));
			add(textField);
		}
	}
	
	
	
}