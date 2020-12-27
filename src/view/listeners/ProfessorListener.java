package view.listeners;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import view.dialogs.ProfessorDialog;
import view.dialogs.components.DialogConfirmButton;

public class ProfessorListener implements FocusListener{

	private String regex;
	public ProfessorListener(String regex) {
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
			ProfessorDialog.showErrorPanel(Integer.parseInt(textFieldName));
			DialogConfirmButton.checkIfCanBeValidated();
		} else {
			String textFieldName = textField.getName();
			ProfessorDialog.hideErrorPanel(Integer.parseInt(textFieldName));
			DialogConfirmButton.checkIfCanBeValidated();
		}
	}

}
