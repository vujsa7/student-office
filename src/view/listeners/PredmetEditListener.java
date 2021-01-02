package view.listeners;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import controller.SubjectController;
import view.dialogs.PredmetEditDialog;

public class PredmetEditListener implements FocusListener{
	
	private String regex;
	public PredmetEditListener(String regex) {
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

}
