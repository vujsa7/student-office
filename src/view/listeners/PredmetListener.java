package view.listeners;


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import controller.SubjectController;
import view.dialogs.PredmetDialog;

public class PredmetListener implements FocusListener{
	
	private String regex;
	public PredmetListener(String regex) {
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
			PredmetDialog.showErrorPanel(Integer.parseInt(textFieldName));
			PredmetDialog.checkIfCanBeValidated();
		} else {
			String textFieldName = textField.getName();
		
			if(textFieldName.equals("0")) {
				if(SubjectController.getInstance().proveriPostojanjeSifre(textField.getText())) {
					PredmetDialog.showSifraErrorPanel();
				} else {
					PredmetDialog.hideSifraErrorPanel();
				}
			} else {
				PredmetDialog.hideErrorPanel(Integer.parseInt(textFieldName));
				PredmetDialog.checkIfCanBeValidated();
			}
			
		}
		
	}
	
}
