package view.listeners;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import controller.ProfessorController;
import view.dialogs.ProfessorEditDialog;

public class ProfessorEditListener implements FocusListener {

	private String regex;
	public ProfessorEditListener(String regex) {
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
			ProfessorEditDialog.showErrorPanel(Integer.parseInt(textFieldName));
			ProfessorEditDialog.checkIfCanBeValidated();
		} else {
			String textFieldName = textField.getName();
			// Ako je broj licne karte
			if(textFieldName.equals("6")) {
				if(ProfessorController.getInstance().checkIDExists(textField.getText())) {
					ProfessorEditDialog.showIDErrorPanel();
				} else {
					ProfessorEditDialog.hideIDErrorPanel();
				}
			} else {
				ProfessorEditDialog.hideErrorPanel(Integer.parseInt(textFieldName));
				ProfessorEditDialog.checkIfCanBeValidated();
			}
			
		}
	}

}
