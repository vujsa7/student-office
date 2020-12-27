package view.listeners;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import view.dialogs.StudentDialog;
import view.dialogs.components.DialogConfirmButton;

public class StudentListener implements FocusListener{
	
	private String regex;
	public StudentListener(String regex) {
		this.regex = regex;
		
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField textField = (JTextField) e.getComponent();
		if(!Pattern.matches(regex, textField.getText())){
			String textFieldName = textField.getName();
			StudentDialog.showErrorPanel(Integer.parseInt(textFieldName));
			DialogConfirmButton.checkIfCanBeValidated();
		} else {
			String textFieldName = textField.getName();
			StudentDialog.hideErrorPanel(Integer.parseInt(textFieldName));
			DialogConfirmButton.checkIfCanBeValidated();
		}
		
	}
	
}
